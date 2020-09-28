package code.formathtml;

public abstract class RendSwitchPartCondition extends RendParentBlock {

    private String importedClassName = "";
    private String variableName;

    public RendSwitchPartCondition(int _offsetTrim, String importedClassName, String variableName) {
        super(_offsetTrim);
        this.importedClassName = importedClassName;
        this.variableName = variableName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public String getVariableName() {
        return variableName;
    }

}
