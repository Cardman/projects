package code.formathtml.util;

public abstract class FieldUpdates {
    private String varName = "";
    private String className = "";
    protected FieldUpdates() {
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String _varName) {
        varName = _varName;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        this.className = _className;
    }
}
