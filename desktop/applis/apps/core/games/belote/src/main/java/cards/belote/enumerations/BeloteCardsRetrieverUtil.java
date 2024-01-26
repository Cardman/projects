package cards.belote.enumerations;

import cards.belote.HandBelote;
import code.util.core.StringUtil;

public final class BeloteCardsRetrieverUtil {
    private BeloteCardsRetrieverUtil() {
    }

    public static DeclaresBelote toDeclaresBelote(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (DeclaresBelote d: DeclaresBelote.annoncesValides()) {
            if (StringUtil.quickEq(r_,d.getSt())) {
                return d;
            }
        }
        return DeclaresBelote.UNDEFINED;
    }

    public static BeloteTrumpPartner toBeloteTrumpPartner(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (BeloteTrumpPartner d: BeloteTrumpPartner.all()) {
            if (StringUtil.quickEq(r_,d.getBeloteTrSt())) {
                return d;
            }
        }
        return BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP;
    }

    public static BidBelote toBidBelote(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (BidBelote d: BidBelote.getNonZeroBids()) {
            if (StringUtil.quickEq(r_,d.getSt())) {
                return d;
            }
        }
        return BidBelote.FOLD;
    }

    public static DealingBelote toDealingBelote(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (DealingBelote d: DealingBelote.getRepartitionsValides()) {
            if (StringUtil.quickEq(r_,d.getSt())) {
                return d;
            }
        }
        return DealingBelote.CLASSIC_2_VS_2;
    }

    public static CardBelote toCardBelote(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (CardBelote c: HandBelote.pileBase()) {
            if (StringUtil.quickEq(r_,Integer.toString(c.getId().nb()))) {
                return c;
            }
        }
        return CardBelote.WHITE;
    }
}
