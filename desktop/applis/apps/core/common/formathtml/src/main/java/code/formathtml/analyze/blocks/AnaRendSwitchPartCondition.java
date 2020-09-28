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

    public void setImportedClassName(String importedClassName) {
        this.importedClassName = importedClassName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

}
