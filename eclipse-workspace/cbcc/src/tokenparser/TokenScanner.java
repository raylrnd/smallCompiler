package tokenparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import generator.Generator;
public class TokenScanner extends TokenManager{
	public static int first;
	public static int last;	
	static int SIZE;
	ArrayList<Token> tokenstream = new ArrayList<Token>();
	static char [] buffer = new char[1024];
	public static void main(String[] args) {
		TokenScanner paser = new TokenScanner();
		paser.readfile();
		paser.pasefile();
	}
	public  void pasefile() {
		first = last = 0;
		for(; last < SIZE; last++) {
			Token token = this.readToken(buffer);
			if(token.type != SPACE)  tokenstream.add(token);
		}
		BuildAST builder = new BuildAST();
		builder.buildTree(tokenstream);
		Generator generator = new Generator();
		generator.open_output_stream();
		generator.gen_stmts(builder.GetTree());
		generator.close_output_stream();		
	}
	 public  void readfile() {		
		try {
			File file = new File("/Users/zhongshunchao/eclipse-workspace/cbcc/src/file.txt");
			InputStream inputStream = new FileInputStream(file);
			Reader reader = new InputStreamReader(inputStream);
			SIZE = reader.read(buffer);	
		}catch(IOException e) {
			System.out.println("read_error");
		}
	}
	 //to-do 先read token化，再分析每一个token
	//把每个字符token化
	public Token  readToken(char[] buffer) {
		first = last;
		char c = buffer[first];		
		//int string 标识符
 		if(is_alpha(c) || c == '_' ) {
			last++;	
			while(true) {
				c = buffer[last];
				if(is_alpha(c) || c == '_' || is_num(c)) {
					last++;
				}else {
					--last;			
					break;
				}
			}
			String str = new String(buffer,first,last-first+1);
			if(str.equals("int") ) {
				return new Token("int",INT);
			}else if(str.equals("string")) {
				return new Token("string", STRING);
			}else {
				return new Token(str, IDENT);
			}
		}else if(is_num(c)) {
			while(is_num(buffer[++last]));
			last--;
			String str = new String(buffer,first,last-first+1);
			return new Token(str,NUM);
		}
 		else {			
			String str = new String (buffer,first,1);
			return consume_spacial_token(str);
		}
	}
	//词法分析阶段不考虑>=,<=,等语法分析阶段考虑
	private Token consume_spacial_token(String str) {
		if(str.equals("=")) {
			return new Token("=", ASSIGN);
		}else if(str.equals("\"")) {
			return new Token("\"", DQM);
		}else if(str.equals(">")) {
			return new Token(">", LAGER);
		}else if(str.equals("<")) {
			return new Token("<", LESSER);
		}else if(str.equals("{")) {
			return new Token("{", BL_BRACKET);
		}else if(str.equals("}")) {
			return new Token("}", BR_BRACKET);
		}else if(str.equals(";")) {
			return new Token(";", SEM);
		}else if(str.equals("(")) {
			return new Token("（", LL_BRACKET);
		}else if(str.equals(")")) {
			return new Token("）", LR_BRACKET);
		}else if(str.equals("\t") || str.equals("\n") || str.equals("\r") || str.equals(" ")) {
			return new Token("space", SPACE);
		}else if(str.equals(",")) {
			return new Token(",", COMMA);
		}
		else {
			return new Token("error", ERROR);
		}
	}
}
