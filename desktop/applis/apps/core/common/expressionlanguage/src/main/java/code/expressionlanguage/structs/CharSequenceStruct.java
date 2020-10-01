package code.expressionlanguage.structs;


public abstract class CharSequenceStruct extends WithoutParentStruct implements DisplayableStruct {


    public abstract int length();
    public abstract char charAt(int _i);
    public abstract String toStringInstance();
    public abstract String substring(int _i,int _j);

}
