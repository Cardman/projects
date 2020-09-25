package code.expressionlanguage.fwd.opers;

public final class AnaVariableContent {

    private boolean variable;

    private boolean catString;

    private String variableName = "";

    private final int off;

    private int deep;
    public AnaVariableContent(int _off) {
        off = _off;
    }

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean variable) {
        this.variable = variable;
    }

    public boolean isCatString() {
        return catString;
    }

    public void setCatString(boolean catString) {
        this.catString = catString;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public int getOff() {
        return off;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }
}
