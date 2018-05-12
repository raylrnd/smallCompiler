package tokenparser;

public interface ConstType {
	public static final int INT     	= 0;
	public static final int STRING 		= 1;
	public static final int IF 			= 2;
	public static final int ELSE 		= 3;
	public static final int BL_BRACKET 	= 4;//{
	public static final int BR_BRACKET 	= 5;
	public static final int ML_BRACKET 	= 6;
	public static final int MR_BRACKET 	= 7;
	public static final int LL_BRACKET 	= 8;//(
	public static final int LR_BRACKET 	= 9;
	public static final int ASSIGN 		= 10;
	public static final int LAGER 		= 11;
	public static final int LESSER 		= 12;
	public static final int DQM 		= 13;
	public static final int ERROR 		= 14;
	public static final int IDENT 		= 15;
	public static final int SEM 		= 16;//semicolon
	public static final int SPACE 		= 17;
	public static final int COMMA 		= 18;//","
	public static final int EMPTYTOKEN 	= 19;//","
	public static final int NUM 		= 20;//","
	
}
