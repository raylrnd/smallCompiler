package tokenparser;

public class TokenManager implements ConstType{

	/*
	enum type{
		INT,
		STRING,
		IF,
		ELSE,
		BL_BRACKET,//{
		BR_BRACKET,//}
		ML_BRACKET,//[
		MR_BRACKET,//]
		LL_BRACKET,//(
		LR_BRACKET,//)
		ASSIGN,//=
		LAGER,//>
		LESSER,//<
		DQM,//double quotation marks;// "	
		ERROR			
	}
	*/
	boolean is_num(char c) {
		if(c <= '9' && c >= '0' ) return true;
		return false;
	}

	boolean is_alpha(char c) {
		if(c <= 'z' && c >= 'a' || c >= 'A' && c <= 'Z') return true;
		return false;
	}
	
	
	
}
