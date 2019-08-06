package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AddOperation;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.ReplacementStruct;
import code.expressionlanguage.structs.StringStruct;
import code.util.CustList;
import code.util.IdMap;



public final class ExecCatOperation extends ExecNumericOperation {

    public ExecCatOperation(AddOperation _a) {
        super(_a);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode o_ = chidren_.first();
        Argument a_ = getArgument(_nodes,o_);
        o_ = chidren_.last();
        Argument c_ = getArgument(_nodes,o_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        Argument r_;
        r_ = localSumDiff(a_, c_, _conf);
        setSimpleArgument(r_, _conf, _nodes);
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        if (!(a_.getStruct() instanceof DisplayableStruct)) {
            return;
        }
        Argument c_ = chidren_.last().getArgument();
        if (!(c_.getStruct() instanceof DisplayableStruct)) {
            return;
        }
        Argument r_;
        r_ = localSumDiff(a_, c_, _conf);
        setSimpleArgumentAna(r_, _conf);
    }

    private Argument localSumDiff(Argument _a, Argument _b,
            Analyzable _cont) {
        StringBuilder str_ = new StringBuilder();
        str_.append(((DisplayableStruct)_a.getStruct()).getDisplayedString(_cont).getInstance());
        str_.append(((DisplayableStruct)_b.getStruct()).getDisplayedString(_cont).getInstance());
        return new Argument(new StringStruct(str_.toString()));
    }

}
