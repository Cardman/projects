package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.StringList;

public final class Line extends Leaf implements BuildableElMethod {

    private final String expression;

    private final int expressionOffset;

    private ConstructorId constId;
    private boolean callSuper;
    private boolean callThis;
    private boolean callInts;
    private boolean callFromCtorToCtor;
    private final ResultExpression res = new ResultExpression();

    public Line(OffsetStringInfo _left, int _offset) {
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
        MethodAccessKind st_ = _page.getStaticContext();
        _page.setSumOffset(res.getSumOffset());
        _page.zeroOffset();
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, Calculation.staticCalculation(st_), _page));
        if (!_page.getCurrentEmptyPartErr().isEmpty()) {
            addErrorBlock(_page.getCurrentEmptyPartErr());
        }
        if (res.getRoot() instanceof CurrentInvokingConstructor) {
            callFromCtorToCtor = true;
            callThis = true;
        }
        if (res.getRoot() instanceof SuperInvokingConstructor) {
            callFromCtorToCtor = true;
            callSuper = true;
        }
        if (res.getRoot() instanceof InterfaceInvokingConstructor) {
            callFromCtorToCtor = true;
            callInts = true;
        }
        if (res.getRoot() instanceof AbstractInvokingConstructor) {
            constId =((AbstractInvokingConstructor) res.getRoot()).getConstId();
        }
        if (_page.getLineDeclarator() != null) {
            StringList vars_ = _page.getVariablesNames();
            DeclareVariable declaring_ = (DeclareVariable) getPreviousSibling();
            if (declaring_.isRefVariable()) {
                checkOpers(res.getRoot(), _page);
            }
            String import_ = declaring_.getImportedClassName();
            String err_ = AffectationOperation.processInfer(import_, _page);
            declaring_.setErrInf(err_);
            declaring_.getVariableNames().addAllElts(vars_);
        }
        _page.setLineDeclarator(null);
        _page.setAcceptCommaInstr(false);
    }

    public static void checkOpers(OperationNode _root, AnalyzedPageEl _page) {
        if (!(_root instanceof DeclaringOperation)&&!(_root instanceof AffectationOperation)&&!(_root instanceof ErrorPartOperation)||_root instanceof AffectationOperation&&!(((AffectationOperation) _root).getChildrenNodes().last() instanceof WrappOperation)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(_page.getCurrentFile());
            b_.setIndexFile(_page);
            //variable name len
            b_.buildError(_page.getAnalysisMessages().getNotRetrievedFields());
            _page.getLocalizer().addError(b_);
            _root.addErr(b_.getBuiltError());
        } else if (_root instanceof DeclaringOperation) {
            for (OperationNode c: ((DeclaringOperation) _root).getChildrenNodes()) {
                if (!(c instanceof AffectationOperation)||!(((AffectationOperation) c).getChildrenNodes().last() instanceof WrappOperation)) {
                    FoundErrorInterpret b_ = new FoundErrorInterpret();
                    b_.setFile(_page.getCurrentFile());
                    b_.setIndexFile(_page);
                    //variable name len
                    b_.buildError(_page.getAnalysisMessages().getNotRetrievedFields());
                    _page.getLocalizer().addError(b_);
                    c.addErr(b_.getBuiltError());
                }
            }
        }
    }

    public ResultExpression getRes() {
        return res;
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
        return res.getRoot();
    }

}
