package generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import iRNode.FunCallNode;
import iRNode.FunDefNode;
import iRNode.IfNode;
import iRNode.StmNode;
import tokenparser.Args;
import tokenparser.VisitAST;
public class Generator extends VisitAST{
	private static ArrayList<StmNode> stmts;
	BufferedWriter writer;	
	public Generator() {
		super();
	}
	public void receiveTree(ArrayList<StmNode> stmts) {
		this.stmts = stmts;
	}
	public void open_output_stream() {
		try {			
			writer = new BufferedWriter(new FileWriter(new File("/Users/zhongshunchao/eclipse-workspace/cbcc/src/file1.txt"),true));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close_output_stream() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//generator BEGIN	
	public void gen_stmts(ArrayList<StmNode> stmts) {
		int size =  stmts.size();
		for (int i = 0; i < size; i++) {
			(stmts.get(i)).accept(this);			
		}
	}
	public void gen_file_head() throws IOException {
			writer.write(	".file	\"hellocbc.cb\"\n"+
							".text\n");		
	}
	public void gen_fundef(FunDefNode funDefNode) throws IOException {	
			writer.write(	"\n.globl  "+ funDefNode.funcname +"\n" +
							"	.type	"+ funDefNode.funcname + ",@function\n" +
							funDefNode.funcname + ":\n");			
			writer.write(
							"	pushl	%ebp\n" + 
							"	movl	%esp, %ebp\n");
			gen_stmts(funDefNode.funcbody);
			writer.write(
						  "\n	movl	%ebp, %esp\n" + 
							"	popl	%ebp\n" + 
							"	ret\n" + 
							"	.size	" + funDefNode.funcname +",.-" +funDefNode.funcname );
	}


	public void gen_args(Args args) throws IOException {	
		for(int i = args.args.size() -1; i >= 0; i--) {
			String str = args.args.get(i).image;
			writer.write(	"\n	movl	$" + str +",  %eax\n" + 
							"	pushl	%eax ");
		}
	}
	public void gen_funCall(FunCallNode funCallNode) throws IOException {
			writer.write(	"\n	subl	$4, %esp\n");
			gen_args(funCallNode.args);
			writer.write(	"\n	call	" + funCallNode.funname);
			String  num = "" + funCallNode.args.args.size() * 4;
			writer.write(	"\n	addl	$" + num + ", %esp");
	}
	//在所有visit方法里面catch异常
	@Override
	public void visit(FunDefNode funDefNode) {
		try {
			gen_fundef(funDefNode);
		} catch (IOException e) {
			System.out.println("funDefNode gen error");
		}
		
	}
	@Override
	public void visit(IfNode ifNode) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(FunCallNode funCallNode) {
		try {
			gen_funCall(funCallNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
