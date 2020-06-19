package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.NotInitializedFields;
import code.expressionlanguage.exec.opers.ExecCurrentInvokingConstructor;
import code.expressionlanguage.exec.opers.ExecInterfaceInvokingConstructor;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.opers.ExecSuperInvokingConstructor;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.StackableBlock;
import code.expressionlanguage.methods.WithNotEmptyEl;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;

public final class ExecLine extends ExecLeaf implements StackableBlock, WithNotEmptyEl {
    private final String expression;

    private int expressionOffset;

    private CustList<ExecOperationNode> opExp;
    public ExecLine(OffsetsBlock _offset, String _expression, int _expressionOffset, CustList<ExecOperationNode> _opExp) {
        super(_offset);
        expression = _expression;
        expressionOffset = _expressionOffset;
        opExp = _opExp;
    }

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getRightEl() {
        return new ExpressionLanguage(opExp);
    }

    public CustList<ExecOperationNode> getExp() {
        return opExp;
    }

    public boolean isCallSuper() {
        return opExp.last() instanceof ExecSuperInvokingConstructor;
    }

    public boolean isCallInts() {
        ExecOperationNode last_ = opExp.last();
        return last_ instanceof ExecInterfaceInvokingConstructor;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int blOffset_ = expressionOffset;
        int endBl_ = blOffset_ + getExpression().length();
        ElUtil.buildCoverageReport(_cont,blOffset_,this,getExp(),endBl_,_parts);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return getRightEl();
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opExp.last();
        opExp = ElUtil.getReducedNodes(r_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();

        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont ,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        ElUtil.tryToCalculate(_cont,el_,0);
        if (_cont.callsOrException()) {
            return;
        }
        if (ip_ instanceof AbstractCallingInstancingPageEl&&(isCallSuper() || isCallInts())) {
            AbstractCallingInstancingPageEl inst_ = (AbstractCallingInstancingPageEl)ip_;
            String curClass_ = inst_.getGlobalClass();

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
                Argument global_ = inst_.getGlobalArgument();
                _cont.setCallingState(new NotInitializedFields(curClass_, global_));
                return;
            }
            //fields of the current class are initialized if there is no other interface constructors to call
        }
        ip_.clearCurrentEls();
        processBlock(_cont);
    }
}
