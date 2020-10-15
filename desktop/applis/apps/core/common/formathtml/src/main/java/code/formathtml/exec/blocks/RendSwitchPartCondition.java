package code.formathtml.exec.blocks;

public abstract class RendSwitchPartCondition extends RendParentBlock {

    private String importedClassName;
    private String variableName;

    public RendSwitchPartCondition(int _offsetTrim, String _importedClassName, String _variableName) {
        super(_offsetTrim);
        this.importedClassName = _importedClassName;
        this.variableName = _variableName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public String getVariableName() {
        return variableName;
    }

}
