package iRNode;
import java.util.ArrayList;

import tokenparser.Args;
import tokenparser.VisitAST;
public class FunDefNode extends StmNode{
	public String funcname;
	public Args args;
	public ArrayList<StmNode> funcbody;
	public FunDefNode(String funcname, Args args, ArrayList<StmNode> funcbody) {
		super();
		this.funcname = funcname;
		this.args = args;
		this.funcbody = funcbody;
	}
    public void accept(VisitAST visitor) {
    	visitor.visit(this);
	}
}
