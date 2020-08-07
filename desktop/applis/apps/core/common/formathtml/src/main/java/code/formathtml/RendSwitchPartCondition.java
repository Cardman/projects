package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;

public abstract class RendSwitchPartCondition extends RendParentBlock {

    private String importedClassName = "";
    private String variableName;
    RendSwitchPartCondition(OffsetsBlock _offset) {
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
