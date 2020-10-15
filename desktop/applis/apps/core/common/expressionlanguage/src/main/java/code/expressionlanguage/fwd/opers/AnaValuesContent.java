package code.expressionlanguage.fwd.opers;

public final class AnaValuesContent {
    private int argOffset;
    private int numberEnum=-1;
    public AnaValuesContent(int _argOffset) {
        argOffset = _argOffset;
    }
    public AnaValuesContent() {
    }

    public int getNumberEnum() {
        return numberEnum;
    }

    public int getArgOffset() {
        return argOffset;
    }

    public void setArgOffset(int _argOffset) {
        this.argOffset = _argOffset;
    }

    public void setNumberEnum(int _numberEnum) {
        this.numberEnum = _numberEnum;
    }
}
