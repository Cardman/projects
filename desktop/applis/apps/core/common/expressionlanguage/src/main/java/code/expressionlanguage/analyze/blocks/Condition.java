package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.StringList;

public abstract class Condition extends BracedBlock implements BuildableElMethod {

    private String condition;

    private int conditionOffset;
    private int testOffset;

    private Argument argument;

    private OperationNode root;

    private String err = "";

    private ClassMethodId test;

    public Condition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_offset);
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    public int getConditionOffset() {
        return conditionOffset;
    }
    

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        _page.setGlobalOffset(conditionOffset);
        _page.setOffset(0);
        root = ElUtil.getRootAnalyzedOperationsReadOnly(condition, Calculation.staticCalculation(f_.getStaticContext()), _page);
        err = _page.getCurrentEmptyPartErr();
        processBoolean(root, _page);
    }

    private void processBoolean(OperationNode _root, AnalyzedPageEl _page) {
        AnaClassArgumentMatching resultClass_ = _root.getResultClass();
        if (!resultClass_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getStandards().getAliasPrimBoolean(), resultClass_, _page);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                resultClass_.getImplicits().add(cl_);
                resultClass_.setRootNumber(res_.getRootNumber());
                resultClass_.setMemberNumber(res_.getMemberNumber());
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(resultClass_, _page);
                if (trueOp_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(trueOp_.getId().getClassName(),trueOp_.getRealId());
                    resultClass_.getImplicitsTest().add(cl_);
                    resultClass_.setRootNumberTest(trueOp_.getRootNumber());
                    resultClass_.setMemberNumberTest(trueOp_.getMemberNumber());
                    test = cl_;
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(conditionOffset);
                    //key word len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringList.join(resultClass_.getNames(),"&"));
                    _page.addLocError(un_);
                    setReachableError(true);
                    getErrorsBlock().add(un_.getBuiltError());
                }
            }
        }
        resultClass_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
    }


    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument argument) {
        this.argument = argument;
    }

    public final String getCondition() {
        return condition;
    }


    public OperationNode getRoot() {
        return root;
    }

    public String getErr() {
        return err;
    }

    public ClassMethodId getTest() {
        return test;
    }

    public int getTestOffset() {
        return testOffset;
    }

    public void setTestOffset(int _testOffset) {
        testOffset = _testOffset;
    }
}
