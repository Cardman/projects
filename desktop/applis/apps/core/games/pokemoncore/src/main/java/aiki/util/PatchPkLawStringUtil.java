package aiki.util;

import code.maths.LgInt;
import code.maths.montecarlo.*;
import code.util.AbsMap;
import code.util.EntryCust;

public final class PatchPkLawStringUtil {

    private PatchPkLawStringUtil() {
    }
    public static MonteCarloString patch(MonteCarloString _l) {
        MonteCarloString adj_ = new MonteCarloString();
        AbsMap<String, LgInt> l_ = _l.getLaw();
        for (EntryCust<String, LgInt> s: l_.entryList()) {
            String ev_ = s.getKey();
            LgInt add_ = new LgInt(s.getValue());
            if (adj_.containsEvent(ev_)) {
                adj_.getLaw().getVal(ev_).addNb(add_);
            } else {
                adj_.addQuickEvent(ev_, add_);
            }
        }
        adj_.deleteZeroEvents();
        return adj_;
    }

}
