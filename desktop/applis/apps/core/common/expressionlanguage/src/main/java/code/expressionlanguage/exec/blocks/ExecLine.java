package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.NotInitializedFields;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ExecLine extends ExecLeaf implements StackableBlock, WithNotEmptyEl {

    private final int expressionOffset;

    private final CustList<ExecOperationNode> opExp;
    private final boolean callSuper;
    private final boolean callInts;
    public ExecLine(int _expressionOffset, CustList<ExecOperationNode> _opExp, int _offsetTrim, boolean _callSuper, boolean _callInts) {
        super(_offsetTrim);
        expressionOffset = _expressionOffset;
        opExp = _opExp;
        callSuper = _callSuper;
        callInts = _callInts;
    }

    private boolean isCallSuper() {
        return callSuper;
    }

    public boolean isCallInts() {
        return callInts;
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return opExp;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();

        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont ,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        ExpressionLanguage.tryToCalculate(_cont,el_,0, _stack);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        if (ip_ instanceof AbstractCallingInstancingPageEl&&(isCallSuper() || isCallInts())) {
            AbstractCallingInstancingPageEl inst_ = (AbstractCallingInstancingPageEl)ip_;

            boolean initFields_ = false;
            ExecBlock bl_ = getNextSibling();
            if (!(bl_ instanceof ExecLine)) {
                initFields_ = true;
            } else {
                ExecLine l_ = (ExecLine) bl_;
                if (!l_.isCallInts()) {
                    initFields_ = true;
                }
            }
            //initialize fields if there is no interface constructors to call
            if (!inst_.isFirstField() && initFields_) {
                inst_.setFirstField(true);
                _stack.setCallingState(new NotInitializedFields(inst_));
                return;
            }
            //fields of the current class are initialized if there is no other interface constructors to call
        }
        ip_.clearCurrentEls();
        processBlock(_cont, _stack);
    }
}
