package code.formathtml.util;

public abstract class FieldUpdates {
    private String className = "";
    protected FieldUpdates() {
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        this.className = _className;
    }
}
