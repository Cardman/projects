package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AddOperation;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.ReplacementStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
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
        StringBuilder str_ = new StringBuilder();
        str_.append(((DisplayableStruct)a_.getStruct()).getDisplayedString(_conf).getInstance());
        str_.append(((DisplayableStruct)c_.getStruct()).getDisplayedString(_conf).getInstance());
        setSimpleArgumentAna(new Argument(new StringStruct(str_.toString())), _conf);
    }

    public static Argument localSumDiff(Argument _a, Argument _b,
            ExecutableCode _cont) {
        StringBuilder str_ = new StringBuilder();
        Struct a_ = _a.getStruct();
        if (!(a_ instanceof DisplayableStruct)) {
            str_.append(((StringStruct) _cont.getStandards().getStringOfObject(_cont.getContextEl(),a_)).getInstance());
        } else {
            str_.append(((DisplayableStruct) a_).getDisplayedString(_cont).getInstance());
        }
        Struct b_ = _b.getStruct();
        if (!(b_ instanceof DisplayableStruct)) {
            str_.append(((StringStruct) _cont.getStandards().getStringOfObject(_cont.getContextEl(),b_)).getInstance());
        } else {
            str_.append(((DisplayableStruct) b_).getDisplayedString(_cont).getInstance());
        }
        return new Argument(new StringStruct(str_.toString()));
    }

}
