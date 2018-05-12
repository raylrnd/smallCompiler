package iRNode;

import tokenparser.Args;
import tokenparser.VisitAST;

public class FunCallNode extends StmNode{
	public FunCallNode(String funname, Args args) {
		super();
		this.funname = funname;
		this.args = args;
	}
	public String funname;
	public Args args;
    public void accept(VisitAST visitor) {
    	visitor.visit(this);
	}
}
