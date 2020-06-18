package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.StringList;

public abstract class Condition extends BracedBlock implements BuildableElMethod {

    private String condition;

    private int conditionOffset;

    private CustList<ExecOperationNode> opCondition;

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
        opCondition = ElUtil.getAnalyzedOperationsReadOnly(condition, _cont, Calculation.staticCalculation(f_.getStaticContext()));
        ExecCondition exec_ = newCondition(condition, conditionOffset, opCondition);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
        processBoolean(_cont);
    }

    protected abstract ExecCondition newCondition(String _condition, int _conditionOffset,CustList<ExecOperationNode> _ops);

    private void processBoolean(ContextEl _cont) {
        ExecOperationNode elCondition_ = opCondition.last();
        LgNames stds_ = _cont.getStandards();
        if (!elCondition_.getResultClass().isBoolType(_cont)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(conditionOffset);
            //key word len
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(elCondition_.getResultClass().getNames(),"&"));
            _cont.addError(un_);
        }
        elCondition_.getResultClass().setUnwrapObject(stds_.getAliasPrimBoolean());
    }


    public ExecOperationNode getRoot() {
        return getOpCondition().last();
    }
    public CustList<ExecOperationNode> getOpCondition() {
        return opCondition;
    }

    public final String getCondition() {
        return condition;
    }



}
