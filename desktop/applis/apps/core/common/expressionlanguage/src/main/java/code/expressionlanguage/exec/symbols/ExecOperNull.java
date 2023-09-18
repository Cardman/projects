package code.expressionlanguage.exec.symbols;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.exec.AbstractStackCall;
import code.expressionlanguage.exec.calls.IntAbstractPageEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class ExecOperNull implements ExecOperSymbol{
    private final CommonOperSymbol symbol;
    private final StringList names;

    public ExecOperNull(CommonOperSymbol _s, StringList _n) {
        this.symbol = _s;
        this.names = _n;
    }
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, ContextEl _cont, IntAbstractPageEl _stackCall) {
        Struct res_ = symbol.calculateOperator(_first, _second);
        return ExecClassArgumentMatching.convertFormatted(res_,_cont,names,_stackCall);
    }

    @Override
    public Struct afterCalculateExc(Struct _str, ContextEl _cont, AbstractStackCall _stackCall) {
        return _str;
    }

    @Override
    public String getSgn() {
        return symbol.getSgn();
    }

}
