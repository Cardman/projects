package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.utilcompo.MathAdvAliases;
import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.Rate;
import code.util.core.StringUtil;

public final class FctRateParse implements AbsFctRate {
    private final MathAdvAliases aliases;
    public FctRateParse(MathAdvAliases _m) {
        aliases = _m;
    }

    @Override
    public boolean valid(String _info) {
        return Rate.isValid(_info);
    }

    @Override
    public RateStruct build(String _info) {
        return new RateStruct(Rate.newRate(StringUtil.nullToEmpty(_info)),aliases);
    }
}
