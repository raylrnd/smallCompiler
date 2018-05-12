package tokenparser;

import java.util.ArrayList;

public class Args {
	public Args(ArrayList<Token> args) {
		super();
		this.args = args;
		this.argnum = args.size();
	}	
	public ArrayList<Token> args; 
	public int argnum;
}
