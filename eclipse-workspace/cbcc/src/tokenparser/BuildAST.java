package tokenparser;

import java.util.ArrayList;

import iRNode.FunCallNode;
import iRNode.FunDefNode;
import iRNode.StmNode;
public class BuildAST implements ConstType{
	private static ArrayList<StmNode> stmts = new ArrayList<StmNode>();
	private static int i = 0;
	private static ArrayList<Token> tokenStream = new ArrayList<Token>();
	private static int TOKEN_STREAM_SIZE;
	public Token current_token() {
		return tokenStream.get(i);
	}
	public Token next_token() {
		if (i < TOKEN_STREAM_SIZE-1) {
			return tokenStream.get(i+1);
		}
		return new Token("EMPTYTOKEN", EMPTYTOKEN);
	}
	public Token before_token() {
		if(i > 0) {
			return tokenStream.get(i-1);
		}
		return new Token("EMPTYTOKEN", EMPTYTOKEN);
	}
	public void buildTree(ArrayList<Token> tokenstream) {
		tokenStream = tokenstream;
		TOKEN_STREAM_SIZE = tokenStream.size();
		for (; i < TOKEN_STREAM_SIZE; i++) {
			parseStmts(stmts);		
			//System.out.println("test");
		}	
		System.out.println(stmts.size());
	}

	public ArrayList<StmNode> GetTree() {
		return this.stmts;
	}
	public void skip_one_token() {
		i = i + 2;
	}
	private void parseStmts(ArrayList<StmNode> stmts) {		
		switch (current_token().type) {
		case IDENT:		
			if(judgeToken(next_token(),LL_BRACKET)) {	
				String funname = current_token().image;
				if(judgeToken(before_token(), INT) || judgeToken(before_token(), STRING)) {						
					skip_one_token();
					Args args = parseFunDefArgs();
					//错误检查
					//expect（"{"）;
					//System.out.println(current_token().image);
					
					if(next_token().type == BL_BRACKET) {
						skip_one_token();
						ArrayList<StmNode> funbody1 = new ArrayList<StmNode>();
						for(;current_token().type != BR_BRACKET; i++) {
							parseStmts(funbody1);
						}
						stmts.add(new FunDefNode(funname, args, funbody1));
					}else {
						System.out.println("funDef Error");
					}

					//stmts.add(new FunDefNode(funname, args, funbody));

				}else {
					skip_one_token();
					//System.out.println(current_token().image);
					Args args = parseFunCallArgs();
					stmts.add(new FunCallNode(funname, args));
				}									
			}
			break;
		default:
			break;
		}
	}
	//测试通过
	private Args parseFunCallArgs() {	
		//current_token == first args
		ArrayList<Token> args = new ArrayList<Token>();
		for(;i < TOKEN_STREAM_SIZE && current_token().type  != LR_BRACKET;i++) {
			if(current_token().type == IDENT || current_token().type == NUM) {
				args.add(current_token());
			}else if(current_token().type == COMMA) {
				if(next_token().type != IDENT && next_token().type != NUM) System.out.println("parseFunCallArgs: arg is not ident");
			}
			else {
				System.out.println("parseFunCallArgs: unlegal token");
			}
		}
		return new Args(args);
	}
	public boolean is_intORstring(Token token) {
		if(token.type == INT || token.type == STRING) return true;
		return false;
	}
	//测试通过
	private Args parseFunDefArgs() {
		ArrayList<Token> args = new ArrayList<Token>();
		for(;i < TOKEN_STREAM_SIZE && current_token().type != LR_BRACKET; i++) {
			if(is_intORstring(current_token())) {
				if(next_token().type != IDENT) System.out.println("parseFunCallArgs: arg is not INT or STRING");
			}else if(current_token().type == IDENT && is_intORstring(before_token())) {
				args.add(current_token());
			}else if(current_token().type == COMMA) {
				if(!is_intORstring(next_token())) System.out.println("parseFunCallArgs: args is not ident");
			}else {
				System.out.println("parseFunDefArgs : unlegal token");
			}
		}
		return new Args(args);
	}
	private boolean judgeToken(Token token, int type) {
		if (token.type == type) return true;
		return false;		
	}
	
}
