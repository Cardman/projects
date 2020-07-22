package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecDeclareVariable;
import code.expressionlanguage.exec.blocks.ExecLine;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;

public final class Line extends Leaf implements BuildableElMethod {

    private final String expression;

    private int expressionOffset;

    private ConstructorId constId;
    private boolean callSuper;
    private boolean callThis;
    private boolean callInts;
    private boolean callFromCtorToCtor;
    private OperationNode root;

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

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
        MethodAccessKind st_ = f_.getStaticContext();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        String import_ = _cont.getStandards().getAliasObject();
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(st_));
        if (!page_.getCurrentEmptyPartErr().isEmpty()) {
            getErrorsBlock().add(page_.getCurrentEmptyPartErr());
            setReachableError(true);
        }
        root = page_.getCurrentRoot();
        if (op_.last() instanceof ExecCurrentInvokingConstructor) {
            callFromCtorToCtor = true;
            callThis = true;
        }
        if (op_.last() instanceof ExecSuperInvokingConstructor) {
            callFromCtorToCtor = true;
            callSuper = true;
        }
        if (op_.last() instanceof ExecInterfaceInvokingConstructor) {
            callFromCtorToCtor = true;
            callInts = true;
        }
        if (op_.last() instanceof ExecAbstractInvokingConstructor) {
            constId =((ExecAbstractInvokingConstructor)op_.last()).getConstId();
        }
        if (page_.isMerged()) {
            StringList vars_ = page_.getVariablesNames();
            DeclareVariable declaring_ = (DeclareVariable) getPreviousSibling();
            import_ = declaring_.getImportedClassName();
            String err_ = AffectationOperation.processInfer(_cont, import_);
            declaring_.setErrInf(err_);
            declaring_.getVariableNames().addAllElts(vars_);
        }
        ExecDeclareVariable ex_ = page_.getExecDeclareVariable();
        if (ex_ != null) {
            ex_.setImportedClassName(import_);
        }
        page_.setExecDeclareVariable(null);
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
        page_.setFinalVariable(false);
        ExecLine exec_ = new ExecLine(getOffset(), expressionOffset,op_);
        exec_.setFile(page_.getBlockToWrite().getFile());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }


    public ConstructorId getConstId() {
        return constId;
    }

    public boolean isCallSuper() {
        return callSuper;
    }

    public boolean isCallFromCtorToCtor() {
        return callFromCtorToCtor;
    }

    public boolean isCallInts() {
        return callInts;
    }
    public ConstructorId getCallInts() {
        if (!callInts) {
            return null;
        }
        return constId;
    }

    public boolean isCallThis() {
        return callThis;
    }

    public OperationNode getRoot() {
        return root;
    }
}
