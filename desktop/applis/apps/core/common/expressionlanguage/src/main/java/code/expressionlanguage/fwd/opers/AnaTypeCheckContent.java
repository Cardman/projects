package code.expressionlanguage.fwd.opers;

public final class AnaTypeCheckContent {

    private String className;
    private final int offset;
    public AnaTypeCheckContent(int _offset) {
        offset = _offset;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getOffset() {
        return offset;
    }
}
