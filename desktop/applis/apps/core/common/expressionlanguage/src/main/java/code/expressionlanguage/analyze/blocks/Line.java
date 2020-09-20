package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AbstractInvokingConstructor;
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
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        MethodAccessKind st_ = f_.getStaticContext();
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        String import_ = _page.getStandards().getAliasObject();
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(expression, Calculation.staticCalculation(st_), _page);
        if (!_page.getCurrentEmptyPartErr().isEmpty()) {
            getErrorsBlock().add(_page.getCurrentEmptyPartErr());
            setReachableError(true);
        }
        root = _page.getCurrentRoot();
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
        if (root instanceof AbstractInvokingConstructor) {
            constId =((AbstractInvokingConstructor)root).getConstId();
        }
        if (_page.isMerged()) {
            StringList vars_ = _page.getVariablesNames();
            DeclareVariable declaring_ = (DeclareVariable) getPreviousSibling();
            import_ = declaring_.getImportedClassName();
            String err_ = AffectationOperation.processInfer(import_, _page);
            declaring_.setErrInf(err_);
            declaring_.getVariableNames().addAllElts(vars_);
        }
        ExecDeclareVariable ex_ = _page.getExecDeclareVariable();
        if (ex_ != null) {
            ex_.setImportedClassName(import_);
        }
        _page.setExecDeclareVariable(null);
        _page.setMerged(false);
        _page.setAcceptCommaInstr(false);
        _page.setFinalVariable(false);
        ExecLine exec_ = new ExecLine(getOffset(), expressionOffset,op_);
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getCoverage().putBlockOperations(exec_,this);
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
