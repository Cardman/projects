package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.stds.LgNames;
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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(conditionOffset);
        page_.setOffset(0);
        CustList<ExecOperationNode> opCondition_ = ElUtil.getAnalyzedOperationsReadOnly(condition, _cont, Calculation.staticCalculation(f_.getStaticContext()));
        err = page_.getCurrentEmptyPartErr();
        root = page_.getCurrentRoot();
        ExecCondition exec_ = newCondition(condition, conditionOffset, opCondition_);
        exec_.setFile(page_.getBlockToWrite().getFile());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        page_.getCoverage().putBlockOperationsConditions(_cont,this);
        page_.getCoverage().putBlockOperations(_cont, exec_,this);
        ExecOperationNode last_ = opCondition_.last();
        processBoolean(_cont, last_);
        argument = last_.getArgument();
    }

    protected abstract ExecCondition newCondition(String _condition, int _conditionOffset,CustList<ExecOperationNode> _ops);

    private void processBoolean(ContextEl _cont, ExecOperationNode _elCondition) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        ClassArgumentMatching resultClass_ = _elCondition.getResultClass();
        if (!resultClass_.isBoolType(page_)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_cont, page_.getStandards().getAliasPrimBoolean(), resultClass_);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                resultClass_.getImplicits().add(cl_);
                resultClass_.setRootNumber(res_.getRootNumber());
                resultClass_.setMemberNumber(res_.getMemberNumber());
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(_cont, resultClass_);
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
                    un_.buildError(_cont.getAnalysisMessages().getUnexpectedType(),
                            StringList.join(resultClass_.getNames(),"&"));
                    _cont.addError(un_);
                    setReachableError(true);
                    getErrorsBlock().add(un_.getBuiltError());
                }
            }
        }
        ElUtil.setImplicits(_elCondition,_cont);
        resultClass_.setUnwrapObject(stds_.getAliasPrimBoolean());
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
