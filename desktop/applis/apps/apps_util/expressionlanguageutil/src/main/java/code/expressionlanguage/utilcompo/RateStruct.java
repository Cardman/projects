package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;
import code.maths.Rate;

public final class RateStruct extends WithoutParentStruct implements AnaDisplayableStruct, DisplayableStruct {
    private final Rate rate;

    public RateStruct(Rate _r) {
        this.rate = _r;
    }

    public static RateStruct def(Struct _d) {
        if (_d instanceof RateStruct) {
            return (RateStruct) _d;
        }
        return new RateStruct(Rate.zero());
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        if (rate.isInteger()) {
            return ((LgNamesWithNewAliases)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasLgInt();
        }
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasRate();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof RateStruct)) {
            return false;
        }
        return Rate.eq(rate,((RateStruct)_other).rate);
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(build());
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        return buildStr();
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return buildStr();
    }

    private StringStruct buildStr() {
        return new StringStruct(build());
    }

    private String build() {
        return rate.toNumberString();
    }

    public Rate getRate() {
        return rate;
    }
}
