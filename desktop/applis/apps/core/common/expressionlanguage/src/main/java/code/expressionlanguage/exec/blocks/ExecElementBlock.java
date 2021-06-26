package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.expressionlanguage.fwd.blocks.ExecElementContent;
import code.util.CustList;

public final class ExecElementBlock extends ExecLeaf implements ExecInnerTypeOrElement{

    private final ExecElementContainer elementContent;

    private String importedClassName;

    private final CustList<ExecAnnotContent> annotationsOps = new CustList<ExecAnnotContent>();
    public ExecElementBlock(ExecElementContent _elementContent, int _trOffset) {
        elementContent = new ExecElementContainer(_elementContent, _trOffset);

    }

    public void setImportedClassName(String _importedClassName) {
        this.importedClassName = _importedClassName;
    }

    @Override
    public String getUniqueFieldName() {
        return elementContent.getElementContent().getFieldName();
    }

    @Override
    public String getImportedClassName() {
        return getRealImportedClassName();
    }

    @Override
    public String getRealImportedClassName() {
        return importedClassName;
    }

    @Override
    public CustList<ExecAnnotContent> getAnnotationsOps() {
        return annotationsOps;
    }

    @Override
    public ExecMemberContainer getElementContent() {
        return elementContent;
    }

}
