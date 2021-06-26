package code.formathtml.exec.blocks;

public abstract class RendSwitchPartCondition extends RendParentBlock {

    private String importedClassName;
    private String variableName;

    public RendSwitchPartCondition(String _importedClassName, String _variableName) {
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
