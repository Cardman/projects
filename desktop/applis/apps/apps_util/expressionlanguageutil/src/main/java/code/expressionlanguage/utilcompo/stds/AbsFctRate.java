package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.utilcompo.RateStruct;

public interface AbsFctRate {
    boolean valid(String _safe);
    RateStruct build(String _safe);
}
