package code.expressionlanguage.structs;


public abstract class CharSequenceStruct extends WithoutParentStruct implements DisplayableStruct {

    public Struct isEmpty() {
        return BooleanStruct.of(length() == 0);
    }

    public boolean isValidIndex(int ind_) {
        return ind_ < 0 || ind_ >= length();
    }

    public boolean isCorrectSub(int begin_, int end_) {
        return begin_ < 0 || end_ > length() || begin_ > end_;
    }


    public abstract int length();
    public abstract char charAt(int _i);
    public abstract String toStringInstance();
    public abstract String substring(int _i,int _j);

}
