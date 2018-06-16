package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractInstancingPageEl;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.CurrentInvokingConstructor;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.InterfaceInvokingConstructor;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SuperInvokingConstructor;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;

public final class Line extends Leaf implements StackableBlock {

    private final String expression;

    private int expressionOffset;

    private CustList<OperationNode> opExp;

    public Line(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
    }

    public Line(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
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
    boolean canCallSuperThis() {
        if (!(getParent() instanceof ConstructorBlock)) {
            return false;
        }
        if (getParent().getFirstChild() != this) {
            return false;
        }
        return true;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        boolean stBlock_ = f_.isStaticContext();
        boolean st_ = stBlock_;
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.setRootAffect(true);
        Block previous_ = getPreviousSibling();
        opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(st_, stBlock_));
        if (previous_ instanceof DeclareVariable) {
            DeclareVariable dc_ = (DeclareVariable) previous_;
            if (dc_.isMerged()) {
                LocalVariable lv_ = new LocalVariable();
                String clName_ = dc_.getClassName();
                lv_.setClassName(_cont.resolveType(clName_, false));
                lv_.setFinalVariable(dc_.isFinalVariable());
                String varName_ = dc_.getVariableName();
                _cont.putLocalVar(varName_, lv_);
                AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
                AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
                ass_.getVariablesRoot().last().put(varName_,Assignment.assignClassic(true, false));
            }
        }
        _cont.setMerged(false);
        _cont.setFinalVariable(false);
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
    }

    public CustList<OperationNode> getExp() {
        return opExp;
    }

    public ConstructorId getConstId() {
        return opExp.last().getConstId();
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    public boolean isCallSuper() {
        if (opExp.isEmpty()) {
            return false;
        }
        return opExp.last() instanceof SuperInvokingConstructor;
    }

    public String getCalledInterface() {
        OperationNode last_ = opExp.last();
        if (!(last_ instanceof InterfaceInvokingConstructor)) {
            return "";
        }
        InterfaceInvokingConstructor int_ = (InterfaceInvokingConstructor) last_;
        String cl_ = int_.getConstId().getName();
        cl_ = Templates.getIdFromAllTypes(cl_);
        return cl_;
    }
    public boolean isCallInts() {
        if (opExp.isEmpty()) {
            return false;
        }
        return opExp.last() instanceof InterfaceInvokingConstructor;
    }

    public boolean isCallThis() {
        if (opExp.isEmpty()) {
            return false;
        }
        return opExp.last() instanceof CurrentInvokingConstructor;
    }

    @Override
    public String getTagName() {
        return TAG_LINE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont ,this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
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
                _cont.setInitFields(new NotInitializedFields(curClass_, global_));
                return;
            }
        }
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        processBlock(_cont);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return getRightEl();
    }
}
