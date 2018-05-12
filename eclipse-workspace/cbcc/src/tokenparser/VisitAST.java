package tokenparser;

import java.io.IOException;

import iRNode.FunCallNode;
import iRNode.FunDefNode;
import iRNode.IfNode;

public abstract class VisitAST{
	public abstract void visit(FunDefNode funDefNode);
	public abstract void visit(IfNode ifNode);
	public abstract void visit(FunCallNode funCallNode);

}
