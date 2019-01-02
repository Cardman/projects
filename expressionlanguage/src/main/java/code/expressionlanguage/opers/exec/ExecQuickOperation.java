package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.QuickOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;


public abstract class ExecQuickOperation extends ExecReflectableOpering {

    public ExecQuickOperation(QuickOperation _q) {
        super(_q);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
        Struct abs_ = absorbingStruct();
        QuickOperation.tryGetResult(_conf, this, abs_);
    }
    @Override
    public final Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode first_ = chidren_.first();
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (absorbingStruct().sameReference(abs_)) {
            setQuickSimpleArgument(f_, _conf, _nodes);
            return f_;
        }
        ExecOperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = getArgument(_nodes,last_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    public abstract BooleanStruct absorbingStruct();
}
