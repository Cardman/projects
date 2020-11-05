package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;



public final class ExecCatOperation extends ExecNumericOperation {

    public ExecCatOperation(ExecOperationContent _opCont, int _opOffset) {
        super(_opCont, _opOffset);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument a_ = getFirstArgument(_nodes,this);
        Argument c_ = getLastArgument(_nodes,this);
        setRelOffsetPossibleLastPage(getOpOffset(), _conf);
        Argument r_ = localSumDiff(a_, c_, _conf);
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
        return getDisplayable(_value,_cont).getInstance();
    }
    public static StringStruct getDisplayable(Argument _value,ContextEl _cont) {
        Struct a_ = Argument.getNullableValue(_value).getStruct();
        if (a_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)a_).getDisplayedString(_cont);
        }
        return _cont.getStandards().getStringOfObject(_cont,a_);
    }
}
