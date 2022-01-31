package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.ExecElementContent;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;

public final class ExecInnerElementBlock extends ExecRootBlock implements ExecInnerTypeOrElement {

    private final ExecElementContainer elementContent;

    private String importedClassName;
    private String realImportedClassName;

    public ExecInnerElementBlock(ExecRootBlockContent _rootBlockContent, AccessEnum _access, ExecElementContent _elementContent, int _trOffset) {
        super(_rootBlockContent, _access);
        elementContent = new ExecElementContainer(_elementContent, _trOffset);
    }

    @Override
    public String getUniqueFieldName() {
        return elementContent.getElementContent().getFieldName();
    }

    @Override
    public String getImportedClassName() {
        return importedClassName;
    }

    public void setImportedClassName(String _importedClassName) {
        this.importedClassName = _importedClassName;
    }

    public void setRealImportedClassName(String _realImportedClassName) {
        this.realImportedClassName = _realImportedClassName;
    }

    @Override
    public String getRealImportedClassName() {
        return realImportedClassName;
    }

    @Override
    public boolean withoutInstance() {
        return true;
    }

    @Override
    public ExecMemberContainer getElementContent() {
        return elementContent;
    }

}
