package code.expressionlanguage.fwd.opers;

public final class AnaExplicitContent {
    private String className;
    private String classNameOwner = "";
    private int offset;

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        this.className = _className;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _offset) {
        this.offset = _offset;
    }

    public String getClassNameOwner() {
        return classNameOwner;
    }

    public void setClassNameOwner(String _classNameOwner) {
        this.classNameOwner = _classNameOwner;
    }
}
