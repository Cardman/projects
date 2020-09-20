package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
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
        CustList<ExecOperationNode> opCondition_ = ElUtil.getAnalyzedOperationsReadOnly(condition, Calculation.staticCalculation(f_.getStaticContext()), _page);
        err = _page.getCurrentEmptyPartErr();
        root = _page.getCurrentRoot();
        ExecCondition exec_ = newCondition(condition, conditionOffset, opCondition_);
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _page.getCoverage().putBlockOperationsConditions(this);
        _page.getCoverage().putBlockOperations(exec_,this);
        ExecOperationNode last_ = opCondition_.last();
        processBoolean(last_, root, _page);
        argument = last_.getArgument();
    }

    protected abstract ExecCondition newCondition(String _condition, int _conditionOffset,CustList<ExecOperationNode> _ops);

    private void processBoolean(ExecOperationNode _elCondition, OperationNode _root, AnalyzedPageEl _page) {
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
        ElUtil.setImplicits(_elCondition, _page, _root);
    }


    public Argument getArgument() {
        return argument;
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
