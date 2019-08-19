package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractInstancingPageEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.NotInitializedFields;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecCurrentInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecInterfaceInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.exec.ExecSuperInvokingConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.StringList;

public final class Line extends Leaf implements StackableBlock, WithNotEmptyEl,BuildableElMethod {

    private final String expression;

    private int expressionOffset;

    private CustList<ExecOperationNode> opExp;

    public Line(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }
    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getRightEl() {
        return new ExpressionLanguage(opExp);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        boolean st_ = f_.isStaticContext();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(st_));
        if (_cont.isMerged()) {
            StringList vars_ = _cont.getVariablesNames();
            ((DeclareVariable)getPreviousSibling()).getVariableNames().addAllElts(vars_);
        }
        _cont.setMerged(false);
        _cont.setFinalVariable(false);
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opExp.last();
        opExp = ElUtil.getReducedNodes(r_);
    }

    public CustList<ExecOperationNode> getExp() {
        return opExp;
    }

    public ConstructorId getConstId() {
        return ((ExecCurrentInvokingConstructor) opExp.last()).getConstId();
    }

    public boolean isCallSuper() {
        return opExp.last() instanceof ExecSuperInvokingConstructor;
    }

    public String getCalledInterface() {
        ExecOperationNode last_ = opExp.last();
        ExecInterfaceInvokingConstructor int_ = (ExecInterfaceInvokingConstructor) last_;
        String cl_ = int_.getConstId().getName();
        cl_ = Templates.getIdFromAllTypes(cl_);
        return cl_;
    }
    public boolean isCallInts() {
        ExecOperationNode last_ = opExp.last();
        if (!(last_ instanceof ExecInterfaceInvokingConstructor)) {
            return false;
        }
        ExecInterfaceInvokingConstructor int_ = (ExecInterfaceInvokingConstructor) last_;
        return int_.getConstId() != null;
    }

    public boolean isCallThis() {
        return opExp.last() instanceof ExecCurrentInvokingConstructor;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont ,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        el_.calculateMember(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        if (isCallSuper() || isCallInts()) {
            AbstractInstancingPageEl inst_ = (AbstractInstancingPageEl)ip_;
            String curClass_ = inst_.getGlobalClass();

            boolean initFields_ = false;
            Block bl_ = getNextSibling();
            if (!(bl_ instanceof Line)) {
                initFields_ = true;
            } else {
                Line l_ = (Line) bl_;
                if (!l_.isCallInts()) {
                    initFields_ = true;
                }
            }
            if (!inst_.isFirstField() && initFields_) {
                inst_.setFirstField(true);
                Argument global_ = inst_.getGlobalArgument();
                _cont.setCallingState(new NotInitializedFields(curClass_, global_));
                return;
            }
        }
        ip_.clearCurrentEls();
        processBlock(_cont);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getRightEl();
    }
}
