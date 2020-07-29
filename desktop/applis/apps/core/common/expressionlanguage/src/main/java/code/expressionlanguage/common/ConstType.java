package code.expressionlanguage.common;

public enum ConstType {
    NUMBER,
    CHARACTER,STRING,
    STATIC_ACCESS,STATIC_CALL_ACCESS,VARARG,DEFAULT_VALUE,LAMBDA,ID,CLASS_INFO,SIMPLE_ANNOTATION,
    LOC_VAR(true),PARAM(true),FIX_VAR(true),MUTABLE_LOOP_VAR(true),LOOP_INDEX(true),
    CUST_FIELD,WORD,CLASSCHOICE_KEYWORD,SUPER_ACCESS_KEYWORD,SUPER_KEYWORD,
    THIS_KEYWORD,PARENT_KEY_WORD,TRUE_CST,NULL_CST,FALSE_CST,ACCESS_INDEXER,VALUES,
    NOTHING,ERROR,ERROR_INST;
    private final boolean variable;
    ConstType(){
        variable = false;
    }
    ConstType(boolean _variable){
        variable = _variable;
    }
}
