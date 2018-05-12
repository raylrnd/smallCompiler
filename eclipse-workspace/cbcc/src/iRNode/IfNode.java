package iRNode;

import tokenparser.VisitAST;

public class IfNode extends StmNode{
	protected ExprNode cond;
    protected StmNode thenBody;
    protected StmNode elseBody;
    public IfNode(ExprNode cond, StmNode thenBody, StmNode elseBody) {
		super();
		this.cond = cond;
		this.thenBody = thenBody;
		this.elseBody = elseBody;
	}
    public void accept(VisitAST visitor) {
    	visitor.visit(this);
	}
    
    
}
//1.判断表达式的布尔值
//
