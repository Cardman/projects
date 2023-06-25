package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.utilcompo.MathAdvAliases;
import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.core.StringUtil;

public final class FctLgIntParse implements AbsFctRate {
    private final MathAdvAliases aliases;
    public FctLgIntParse(MathAdvAliases _m) {
        aliases = _m;
    }
    @Override
    public boolean valid(String _info) {
        return LgInt.isValid(_info);
    }

    @Override
    public RateStruct build(String _info) {
        return new RateStruct(new Rate(LgInt.newLgInt(StringUtil.nullToEmpty(_info))),aliases);
    }
}
