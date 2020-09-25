package code.expressionlanguage.fwd.opers;

public final class AnaExplicitContent {
    private String className;
    private String classNameOwner = "";
    private int offset;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getClassNameOwner() {
        return classNameOwner;
    }

    public void setClassNameOwner(String classNameOwner) {
        this.classNameOwner = classNameOwner;
    }
}
