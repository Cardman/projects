package code.formathtml.analyze.blocks;

public abstract class AnaRendSwitchPartCondition extends AnaRendParentBlock implements AnaRendBuildEl {

    private String importedClassName = "";
    private String variableName;
    AnaRendSwitchPartCondition(int _offset) {
        super(_offset);
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public void setImportedClassName(String _importedClassName) {
        this.importedClassName = _importedClassName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String _variableName) {
        this.variableName = _variableName;
    }

}
