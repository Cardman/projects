package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.core.StringUtil;

public final class FctLgIntParse implements AbsFctRate {

    @Override
    public boolean valid(String _info) {
        return LgInt.isValid(_info);
    }

    @Override
    public RateStruct build(String _info) {
        return new RateStruct(new Rate(LgInt.newLgInt(StringUtil.nullToEmpty(_info))));
    }
}
