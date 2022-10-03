package cards.tarot.enumerations;

import code.util.core.StringUtil;

public final class TarotCardsRetrieverUtil {
    private TarotCardsRetrieverUtil(){
    }

    public static BidTarot toBidTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"TAKE")) {
            return BidTarot.TAKE;
        }
        if (StringUtil.quickEq(r_,"GUARD")) {
            return BidTarot.GUARD;
        }
        if (StringUtil.quickEq(r_,"GUARD_WITHOUT")) {
            return BidTarot.GUARD_WITHOUT;
        }
        if (StringUtil.quickEq(r_,"GUARD_AGAINST")) {
            return BidTarot.GUARD_AGAINST;
        }
        if (StringUtil.quickEq(r_,"SLAM")) {
            return BidTarot.SLAM;
        }
        return BidTarot.FOLD;
    }
}
