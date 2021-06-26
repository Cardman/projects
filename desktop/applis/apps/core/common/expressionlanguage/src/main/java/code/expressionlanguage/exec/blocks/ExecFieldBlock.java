package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.fwd.blocks.AnaFieldContent;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.expressionlanguage.fwd.blocks.ExecFieldContent;
import code.util.CustList;
import code.util.StringList;

public final class ExecFieldBlock extends ExecLeaf implements ExecInfoBlock {

    private final StringList fieldName = new StringList();

    private String importedClassName;

    private final ExecExpFieldContainer elementContent;

    private final CustList<ExecAnnotContent> annotationsOps = new CustList<ExecAnnotContent>();
    public ExecFieldBlock(AnaFieldContent _fieldContent) {
        elementContent = new ExecExpFieldContainer(new ExecFieldContent(_fieldContent));
    }

    @Override
    public String getImportedClassName() {
        return getRealImportedClassName();
    }

    @Override
    public String getRealImportedClassName() {
        return importedClassName;
    }

    public void setImportedClassName(String _importedClassName) {
        this.importedClassName = _importedClassName;
    }

    public boolean isStaticField() {
        return elementContent.getFieldContent().isStaticField();
    }

    public boolean isFinalField() {
        return elementContent.getFieldContent().isFinalField();
    }

    public StringList getFieldName() {
        return fieldName;
    }

    public AccessEnum getAccess() {
        return elementContent.getFieldContent().getAccess();
    }

    @Override
    public CustList<ExecAnnotContent> getAnnotationsOps() {
        return annotationsOps;
    }

    public void setOpValue(CustList<ExecOperationNode> _opValue) {
        elementContent.setOpValue(_opValue);
    }

    @Override
    public ExecExpFieldContainer getElementContent() {
        return elementContent;
    }

}
