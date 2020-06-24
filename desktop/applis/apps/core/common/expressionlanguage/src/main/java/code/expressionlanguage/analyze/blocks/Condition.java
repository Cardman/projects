package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
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

    private Argument argument;

    private OperationNode root;

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
        root = page_.getCurrentRoot();
        ExecCondition exec_ = newCondition(condition, conditionOffset, opCondition_);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperationsConditions(_cont,this);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
        ExecOperationNode last_ = opCondition_.last();
        processBoolean(_cont, last_);
        argument = last_.getArgument();
    }

    protected abstract ExecCondition newCondition(String _condition, int _conditionOffset,CustList<ExecOperationNode> _ops);

    private void processBoolean(ContextEl _cont, ExecOperationNode _elCondition) {
        LgNames stds_ = _cont.getStandards();
        if (!_elCondition.getResultClass().isBoolType(_cont)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(conditionOffset);
            //key word len
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(_elCondition.getResultClass().getNames(),"&"));
            _cont.addError(un_);
        }
        _elCondition.getResultClass().setUnwrapObject(stds_.getAliasPrimBoolean());
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
}
