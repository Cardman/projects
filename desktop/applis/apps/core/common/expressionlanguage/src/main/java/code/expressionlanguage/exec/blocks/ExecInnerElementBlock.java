package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.fwd.blocks.ExecElementContent;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;
import code.util.CustList;
import code.util.StringList;

public final class ExecInnerElementBlock extends ExecRootBlock implements ExecInnerTypeOrElement, ExecUniqueRootedBlock {

    private ExecElementContent elementContent;

    private String importedClassName;
    private String realImportedClassName;

    private CustList<ExecOperationNode> opValue;

    private int trOffset;
    private CustList<ExecRootBlock> anonymous = new CustList<ExecRootBlock>();
    private CustList<ExecAnonymousFunctionBlock> anonymousLambda = new CustList<ExecAnonymousFunctionBlock>();
    public ExecInnerElementBlock(int _offsetTrim, ExecRootBlockContent _rootBlockContent, AccessEnum _access, ExecElementContent _elementContent) {
        super(_offsetTrim, _rootBlockContent, _access);
        elementContent = _elementContent;
    }

    @Override
    public String getUniqueFieldName() {
        return elementContent.getFieldName();
    }

    @Override
    public void setTrOffset(int trOffset) {
        this.trOffset = trOffset;
    }

    public void setOpValue(CustList<ExecOperationNode> opValue) {
        this.opValue = opValue;
    }

    @Override
    public String getImportedClassName() {
        return importedClassName;
    }

    public void setImportedClassName(String importedClassName) {
        this.importedClassName = importedClassName;
    }

    public void setRealImportedClassName(String realImportedClassName) {
        this.realImportedClassName = realImportedClassName;
    }

    @Override
    public String getRealImportedClassName() {
        return realImportedClassName;
    }

    @Override
    public boolean isStaticField() {
        return true;
    }

    @Override
    public boolean isFinalField() {
        return true;
    }

    @Override
    public StringList getFieldName() {
        return new StringList(elementContent.getFieldName());
    }

    @Override
    public boolean isStaticType() {
        return false;
    }

    @Override
    public boolean withoutInstance() {
        return true;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return new ExpressionLanguage(opValue);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (ip_ instanceof StaticInitPageEl) {
            ip_.setGlobalOffset(elementContent.getFieldNameOffest());
            ip_.setOffset(0);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            ExpressionLanguage.tryToCalculate(_cont,el_, trOffset);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont);
    }

    @Override
    public CustList<ExecRootBlock> getAnonymous() {
        return anonymous;
    }

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        return anonymousLambda;
    }
}
