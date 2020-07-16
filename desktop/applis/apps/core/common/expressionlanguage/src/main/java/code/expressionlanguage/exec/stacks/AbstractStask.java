package code.expressionlanguage.exec.stacks;

public abstract class AbstractStask implements RemovableVars {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String _label) {
        label = _label;
    }

}
