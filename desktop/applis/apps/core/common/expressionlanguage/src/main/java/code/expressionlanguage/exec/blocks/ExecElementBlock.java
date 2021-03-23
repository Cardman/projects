package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractInitPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.fwd.blocks.ExecElementContent;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecElementBlock extends ExecLeaf implements ExecInnerTypeOrElement{

    private final ExecElementContent elementContent;

    private String importedClassName;

    private CustList<ExecOperationNode> opValue;

    private final int trOffset;

    private final CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private final CustList<ExecRootBlock> anonymous = new CustList<ExecRootBlock>();
    private final CustList<ExecAnonymousFunctionBlock> anonymousLambda = new CustList<ExecAnonymousFunctionBlock>();
    private final CustList<ExecAbstractSwitchMethod> switchMethods = new CustList<ExecAbstractSwitchMethod>();
    public ExecElementBlock(int _offsetTrim, ExecElementContent _elementContent, int _trOffset) {
        super(_offsetTrim);
        elementContent = _elementContent;
        trOffset = _trOffset;

    }

    @Override
    public AccessEnum getAccess() {
        return AccessEnum.PUBLIC;
    }

    public void setImportedClassName(String _importedClassName) {
        this.importedClassName = _importedClassName;
    }

    @Override
    public String getUniqueFieldName() {
        return elementContent.getFieldName();
    }

    @Override
    public void setOpValue(CustList<ExecOperationNode> _opValue) {
        this.opValue = _opValue;
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
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return new ExpressionLanguage(opValue);
    }

    public void processEl(ContextEl _cont, StackCall _stack, AbstractInitPageEl _last) {
        _last.setGlobalOffset(elementContent.getFieldNameOffest());
        _last.setOffset(0);
        ExpressionLanguage el_ = _last.getCurrentEl(_cont, this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        ExpressionLanguage.tryToCalculate(_cont,el_, trOffset, _stack);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        _last.clearCurrentEls();
        processMemberBlock(_last);
    }

    @Override
    public CustList<ExecRootBlock> getAnonymous() {
        return anonymous;
    }

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        return anonymousLambda;
    }

    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        return switchMethods;
    }
}
