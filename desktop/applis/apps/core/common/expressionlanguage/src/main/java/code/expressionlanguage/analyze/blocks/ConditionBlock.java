package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.util.AnaTypeFctPair;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.core.StringUtil;

public abstract class ConditionBlock extends BracedBlock implements BuildableElMethod,WithConditionPart {

    private final String condition;

    private final int conditionOffset;

    private Argument argument;

    private final ResultExpression res = new ResultExpression();

    private String err = "";

    private final AnaTypeFctPair functions = new AnaTypeFctPair();

    private int conditionNb;

    protected ConditionBlock(OffsetStringInfo _condition, int _offset) {
        this(_condition,_offset,new OffsetStringInfo(0,""));
    }
    protected ConditionBlock(OffsetStringInfo _condition, int _offset, OffsetStringInfo _lab) {
        super(_offset,_lab);
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    public int getConditionOffset() {
        return conditionOffset;
    }
    

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        _page.setSumOffset(res.getSumOffset());
        _page.zeroOffset();
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, Calculation.staticCalculation(f_.getStaticContext()), _page));
        err = _page.getCurrentEmptyPartErr();
        AnaTypeFctPair fcts_ = processBoolean(this, res.getRoot(), _page, conditionOffset);
        functions.setFunction(fcts_.getFunction());
        functions.setFunctionImpl(fcts_.getFunctionImpl());
    }

    static AnaTypeFctPair processBoolean(AbsBk _abs, OperationNode _root, AnalyzedPageEl _page, int _off) {
        AnaTypeFctPair pair_ = new AnaTypeFctPair();
        AnaClassArgumentMatching resultClass_ = _root.getResultClass();
        if (!resultClass_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), resultClass_, _page);
            if (res_ != null) {
                resultClass_.implicitInfosCore(res_);
                pair_.setFunctionImpl(res_.getParametrableContent().getPair());
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(resultClass_, _page);
                if (trueOp_ != null) {
                    resultClass_.implicitInfosTest(trueOp_);
                    pair_.setFunction(trueOp_.getParametrableContent().getPair());
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_abs.getFile());
                    un_.setIndexFile(_off);
                    //key word len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(resultClass_.getNames(), ExportCst.JOIN_TYPES));
                    _page.addLocError(un_);
                    _abs.addErrorBlock(un_.getBuiltError());
                }
            }
        }
        resultClass_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        return pair_;
    }

    public ResultExpression getRes() {
        return res;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _arg) {
        this.argument = _arg;
    }

    public final String getCondition() {
        return condition;
    }


    public OperationNode getRoot() {
        return res.getRoot();
    }

    public String getErr() {
        return err;
    }

    public AnaTypeFctPair getFunctions() {
        return functions;
    }

    public void setTestOffset(int _testOffset) {
        functions.setTestOffset(_testOffset);
    }

    public int getConditionNb() {
        return conditionNb;
    }

    public void setConditionNb(int _conditionNb) {
        conditionNb = _conditionNb;
    }
}
