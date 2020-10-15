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

    public void setClassName(String _className) {
        this.className = _className;
    }

    public int getOffset() {
        return offset;
    }
}
