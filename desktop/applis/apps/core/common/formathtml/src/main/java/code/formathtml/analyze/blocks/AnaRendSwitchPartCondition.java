package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class AnaRendSwitchPartCondition extends AnaRendParentBlock {

    private String importedClassName = "";
    private String variableName;
    AnaRendSwitchPartCondition(OffsetsBlock _offset) {
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
