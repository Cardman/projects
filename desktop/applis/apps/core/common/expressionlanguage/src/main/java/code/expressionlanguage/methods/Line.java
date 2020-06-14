package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.NotInitializedFields;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecCurrentInvokingConstructor;
import code.expressionlanguage.exec.opers.ExecInterfaceInvokingConstructor;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.opers.ExecSuperInvokingConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodAccessKind;
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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        MethodAccessKind st_ = f_.getStaticContext();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opExp = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(st_));
        if (page_.isMerged()) {
            StringList vars_ = page_.getVariablesNames();
            DeclareVariable declaring_ = (DeclareVariable) getPreviousSibling();
            String import_ = declaring_.getImportedClassName();
            AffectationOperation.processInfer(_cont, import_);
            declaring_.getVariableNames().addAllElts(vars_);
        }
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
        page_.setFinalVariable(false);
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
        ElUtil.tryToCalculate(_cont,el_,0);
        if (_cont.callsOrException()) {
            return;
        }
        if (isCallSuper() || isCallInts()) {
            AbstractCallingInstancingPageEl inst_ = (AbstractCallingInstancingPageEl)ip_;
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

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getRightEl();
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int blOffset_ = expressionOffset;
        int endBl_ = blOffset_ + getExpression().length();
        ElUtil.buildCoverageReport(_cont,blOffset_,this,getExp(),endBl_,_parts);
    }
}
