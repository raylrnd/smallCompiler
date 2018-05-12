package iRNode;

import tokenparser.VisitAST;

public abstract class StmNode{
	abstract public void accept(VisitAST visitor);
}


