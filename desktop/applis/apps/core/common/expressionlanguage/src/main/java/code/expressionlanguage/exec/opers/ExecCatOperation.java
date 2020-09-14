package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.AddOperation;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;



public final class ExecCatOperation extends ExecNumericOperation {

    public ExecCatOperation(AddOperation _a) {
        super(_a,_a);
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

    public static Argument localSumDiff(Argument _a, Argument _b,
                                        ContextEl _cont) {
        StringBuilder str_ = new StringBuilder();
        str_.append(getString(_a,_cont));
        str_.append(getString(_b,_cont));
        return new Argument(new StringStruct(str_.toString()));
    }
    public static String getString(Argument _value,ContextEl _cont) {
        return getDisplayable(_value,_cont).getDisplayedString(_cont).getInstance();
    }
    public static DisplayableStruct getDisplayable(Argument _value,ContextEl _cont) {
        Struct a_ = _value.getStruct();
        if (a_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)a_);
        }
        return _cont.getStandards().getStringOfObject(_cont,a_);
    }
}
