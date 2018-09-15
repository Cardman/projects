package code.expressionlanguage;

public enum ConstType {
    NUMBER,
    CHARACTER,STRING,
    STATIC_ACCESS,VARARG,LAMBDA,CLASS_INFO,
    LOC_VAR(true),PARAM(true),CATCH_VAR(true),LOOP_VAR(true),LOOP_INDEX(true),
    CUST_FIELD,WORD,CLASSCHOICE_KEYWORD,SUPER_ACCESS_KEYWORD,SUPER_KEYWORD,
    THIS_KEYWORD,TRUE_CST,NULL_CST,FALSE_CST,
    NOTHING;
    private final boolean variable;
    ConstType(){
        variable = false;
    }
    ConstType(boolean _variable){
        variable = _variable;
    }
    public boolean isVariable() {
        return variable;
    }
}
