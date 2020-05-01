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
        Argument c_ = chidren_.last().getArgument();
        setSimpleArgumentAna(localSumDiff(a_,c_,_conf.getContextEl()),_conf);
    }

    public static Argument localSumDiff(Argument _a, Argument _b,
            ExecutableCode _cont) {
        StringBuilder str_ = new StringBuilder();
        str_.append(getString(_a,_cont));
        str_.append(getString(_b,_cont));
        return new Argument(new StringStruct(str_.toString()));
    }
    public static String getString(Argument _value,ExecutableCode _cont) {
        return getDisplayable(_value,_cont).getDisplayedString(_cont).getInstance();
    }
    public static DisplayableStruct getDisplayable(Argument _value,ExecutableCode _cont) {
        Struct a_ = _value.getStruct();
        if (!(a_ instanceof DisplayableStruct)) {
            return _cont.getStandards().getStringOfObject(_cont.getContextEl(),a_);
        }
        return (DisplayableStruct) a_;
    }
}
