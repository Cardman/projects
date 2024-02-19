package cards.tarot.enumerations;

import cards.tarot.HandTarot;
import code.util.core.StringUtil;

public final class TarotCardsRetrieverUtil {
    private TarotCardsRetrieverUtil(){
    }

    public static BidTarot toBidTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (BidTarot c: BidTarot.getNonZeroBidsWithSlam()) {
            if (StringUtil.quickEq(r_,c.getSt())) {
                return c;
            }
        }
        return BidTarot.FOLD;
    }
    public static ModeTarot toModeTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (ModeTarot c: ModeTarot.all()) {
            if (StringUtil.quickEq(r_,c.getSt())) {
                return c;
            }
        }
        return ModeTarot.NORMAL;
    }
    public static DealingTarot toDealingTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (DealingTarot c: DealingTarot.getRepartitionsValides()) {
            if (StringUtil.quickEq(r_,c.getSt())) {
                return c;
            }
        }
        return DealingTarot.DEAL_2_VS_3_CALL_KING;
    }
    public static Handfuls toHandfuls(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (Handfuls c: Handfuls.getDeclarableHandFuls()) {
            if (StringUtil.quickEq(r_,c.getSt())) {
                return c;
            }
        }
        return Handfuls.NO;
    }
    public static Miseres toMiseres(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (Miseres c: Miseres.all()) {
            if (StringUtil.quickEq(r_,c.getSt())) {
                return c;
            }
        }
        return Miseres.TRUMP;
    }
    public static EndDealTarot toEndDealTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (EndDealTarot c: EndDealTarot.all()) {
            if (StringUtil.quickEq(r_,c.getSt())) {
                return c;
            }
        }
        return EndDealTarot.ZERO;
    }
    public static CardTarot toCardTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (CardTarot c: HandTarot.pileBase()) {
            if (StringUtil.quickEq(r_,Integer.toString(c.getId().nb()))) {
                return c;
            }
        }
        return CardTarot.WHITE;
    }
}
