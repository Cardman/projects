package code.expressionlanguage.opers.exec;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.QuickOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;


public abstract class ExecQuickOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    public ExecQuickOperation(QuickOperation _q) {
        super(_q);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        Struct abs_ = absorbingStruct();
        QuickOperation.tryGetResult(_conf, this, abs_,true);
    }
    @Override
    public final void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode first_ = chidren_.first();
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (absorbingStruct().sameReference(abs_)) {
            setQuickConvertSimpleArgument(f_, _conf, _nodes);
            return;
        }
        ExecOperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = getArgument(_nodes,last_);
        setSimpleArgument(a_, _conf, _nodes);
    }

    public abstract BooleanStruct absorbingStruct();
}
