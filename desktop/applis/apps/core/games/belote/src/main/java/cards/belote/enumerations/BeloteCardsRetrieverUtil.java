package cards.belote.enumerations;

import code.util.core.StringUtil;

public final class BeloteCardsRetrieverUtil {
    private BeloteCardsRetrieverUtil() {
    }

    public static DeclaresBelote toDeclaresBelote(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"FOUR_1")) {
            return DeclaresBelote.FOUR_1;
        }
        if (StringUtil.quickEq(r_,"FOUR_KING")) {
            return DeclaresBelote.FOUR_KING;
        }
        if (StringUtil.quickEq(r_,"FOUR_QUEEN")) {
            return DeclaresBelote.FOUR_QUEEN;
        }
        if (StringUtil.quickEq(r_,"FOUR_JACK")) {
            return DeclaresBelote.FOUR_JACK;
        }
        if (StringUtil.quickEq(r_,"FOUR_10")) {
            return DeclaresBelote.FOUR_10;
        }
        if (StringUtil.quickEq(r_,"FOUR_9")) {
            return DeclaresBelote.FOUR_9;
        }
        if (StringUtil.quickEq(r_,"FOUR_8")) {
            return DeclaresBelote.FOUR_8;
        }
        if (StringUtil.quickEq(r_,"FOUR_7")) {
            return DeclaresBelote.FOUR_7;
        }
        if (StringUtil.quickEq(r_,"HUNDRED")) {
            return DeclaresBelote.HUNDRED;
        }
        if (StringUtil.quickEq(r_,"FIFTY")) {
            return DeclaresBelote.FIFTY;
        }
        if (StringUtil.quickEq(r_,"THIRTY")) {
            return DeclaresBelote.THIRTY;
        }
        return DeclaresBelote.UNDEFINED;
    }

    public static BeloteTrumpPartner toBeloteTrumpPartner(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"OVERTRUMP_ONLY")) {
            return BeloteTrumpPartner.OVERTRUMP_ONLY;
        }
        if (StringUtil.quickEq(r_,"UNDERTRUMP_ONLY")) {
            return BeloteTrumpPartner.UNDERTRUMP_ONLY;
        }
        if (StringUtil.quickEq(r_,"UNDERTRUMP_OVERTRUMP")) {
            return BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP;
        }
        return BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP;
    }

    public static BidBelote toBidBelote(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"SUIT")) {
            return BidBelote.SUIT;
        }
        if (StringUtil.quickEq(r_,"OTHER_SUIT")) {
            return BidBelote.OTHER_SUIT;
        }
        if (StringUtil.quickEq(r_,"NO_TRUMP")) {
            return BidBelote.NO_TRUMP;
        }
        if (StringUtil.quickEq(r_,"ALL_TRUMP")) {
            return BidBelote.ALL_TRUMP;
        }
        return BidBelote.FOLD;
    }

    public static DealingBelote toDealingBelote(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"COINCHE_2_VS_2")) {
            return DealingBelote.COINCHE_2_VS_2;
        }
        return DealingBelote.CLASSIC_2_VS_2;
    }

}
