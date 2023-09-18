package code.expressionlanguage.utilcompo.common;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.common.symbol.SymbolConstants;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.LgInt;
import code.maths.Rate;

public final class CommonOperDivRate implements CommonOperSymbol {
    private final boolean all;
    public CommonOperDivRate(boolean _allints) {
        all = _allints;
    }
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        if (((RateStruct)_second).getRate().isZero()) {
            return NullStruct.NULL_VALUE;
        }
        Rate quot_ = ((RateStruct)_first).getRate();
        Rate div_ = ((RateStruct)_second).getRate();
        if (all) {
            return new RateStruct(new Rate(LgInt.divide(quot_.intPart(),div_.intPart())));
        }
        return new RateStruct(Rate.divide(((RateStruct)_first).getRate(),((RateStruct)_second).getRate()));
    }

    @Override
    public String getSgn() {
        if (all) {
            return Long.toString(SymbolConstants.SYMBOL_DIV_PRIO)+"/"+ PrimitiveTypes.LG_INT;
        }
        return Long.toString(SymbolConstants.SYMBOL_DIV_PRIO)+"/"+PrimitiveTypes.RATE;
    }

}
