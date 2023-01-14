package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.Rate;
import code.util.core.StringUtil;

public final class FctRateParse implements AbsFctRate {

    @Override
    public boolean valid(String _info) {
        return Rate.isValid(_info);
    }

    @Override
    public RateStruct build(String _info) {
        return new RateStruct(Rate.newRate(StringUtil.nullToEmpty(_info)));
    }
}
