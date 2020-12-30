package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.fwd.blocks.ExecElementContent;
import code.expressionlanguage.fwd.blocks.ExecRootBlockContent;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecInnerElementBlock extends ExecRootBlock implements ExecInnerTypeOrElement, ExecUniqueRootedBlock {

    private final ExecElementContent elementContent;

    private String importedClassName;
    private String realImportedClassName;

    private CustList<ExecOperationNode> opValue;

    private final int trOffset;
    private final CustList<ExecRootBlock> anonymous = new CustList<ExecRootBlock>();
    private final CustList<ExecAnonymousFunctionBlock> anonymousLambda = new CustList<ExecAnonymousFunctionBlock>();
    public ExecInnerElementBlock(int _offsetTrim, ExecRootBlockContent _rootBlockContent, AccessEnum _access, ExecElementContent _elementContent, int _trOffset) {
        super(_offsetTrim, _rootBlockContent, _access);
        elementContent = _elementContent;
        trOffset = _trOffset;
    }

    @Override
    public String getUniqueFieldName() {
        return elementContent.getFieldName();
    }

    public void setOpValue(CustList<ExecOperationNode> _op) {
        this.opValue = _op;
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
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (ip_ instanceof StaticInitPageEl) {
            ip_.setGlobalOffset(elementContent.getFieldNameOffest());
            ip_.setOffset(0);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
            ExpressionLanguage.tryToCalculate(_cont,el_, trOffset, _stack);
            if (_cont.callsOrException(_stack)) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont, _stack);
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
