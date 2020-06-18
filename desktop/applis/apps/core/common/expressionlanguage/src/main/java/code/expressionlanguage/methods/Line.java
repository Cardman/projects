package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecLine;
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

public final class Line extends Leaf implements BuildableElMethod {

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
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
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
            declaring_.getExec().setImportedClassName(import_);
        }
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
        page_.setFinalVariable(false);
        ExecLine exec_ = new ExecLine(getOffset(),expression,expressionOffset,opExp);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
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

}
