package aiki.util;

import code.maths.montecarlo.*;

public final class PatchPkLawStringUtil {

    private PatchPkLawStringUtil() {
    }
    public static MonteCarloString patch(MonteCarloString _l) {
        MonteCarloString adj_ = new MonteCarloString();
        for (String s: _l.eventsDiff()) {
            adj_.addQuickEvent(s, _l.rate(s));
        }
        adj_.deleteZeroEvents();
        return adj_;
    }

}
