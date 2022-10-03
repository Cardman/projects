package cards.tarot.enumerations;

import cards.tarot.HandTarot;
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
    public static ModeTarot toModeTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"NORMAL_WITH_MISERE")) {
            return ModeTarot.NORMAL_WITH_MISERE;
        }
        if (StringUtil.quickEq(r_,"NORMAL_WITH_ONE_FOR_ONE")) {
            return ModeTarot.NORMAL_WITH_ONE_FOR_ONE;
        }
        if (StringUtil.quickEq(r_,"MISERE")) {
            return ModeTarot.MISERE;
        }
        if (StringUtil.quickEq(r_,"ONE_FOR_ONE")) {
            return ModeTarot.ONE_FOR_ONE;
        }
        return ModeTarot.NORMAL;
    }
    public static DealingTarot toDealingTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"DEAL_1_VS_2")) {
            return DealingTarot.DEAL_1_VS_2;
        }
        if (StringUtil.quickEq(r_,"DEAL_1_VS_3")) {
            return DealingTarot.DEAL_1_VS_3;
        }
        if (StringUtil.quickEq(r_,"DEAL_2_VS_2_WITHOUT_CALL")) {
            return DealingTarot.DEAL_2_VS_2_WITHOUT_CALL;
        }
        if (StringUtil.quickEq(r_,"DEAL_2_VS_2_CALL_KING")) {
            return DealingTarot.DEAL_2_VS_2_CALL_KING;
        }
        if (StringUtil.quickEq(r_,"DEAL_2_VS_2_CALL_CHAR")) {
            return DealingTarot.DEAL_2_VS_2_CALL_CHAR;
        }
        if (StringUtil.quickEq(r_,"DEAL_1_VS_4")) {
            return DealingTarot.DEAL_1_VS_4;
        }
        if (StringUtil.quickEq(r_,"DEAL_2_VS_3_CALL_CHAR")) {
            return DealingTarot.DEAL_2_VS_3_CALL_CHAR;
        }
        if (StringUtil.quickEq(r_,"DEAL_2_VS_4_WITHOUT_CALL")) {
            return DealingTarot.DEAL_2_VS_4_WITHOUT_CALL;
        }
        if (StringUtil.quickEq(r_,"DEAL_2_VS_4_CALL_KING")) {
            return DealingTarot.DEAL_2_VS_4_CALL_KING;
        }
        if (StringUtil.quickEq(r_,"DEAL_2_VS_4_CALL_CHAR")) {
            return DealingTarot.DEAL_2_VS_4_CALL_CHAR;
        }
        return DealingTarot.DEAL_2_VS_3_CALL_KING;
    }
    public static Handfuls toHandfuls(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"ONE")) {
            return Handfuls.ONE;
        }
        if (StringUtil.quickEq(r_,"TWO")) {
            return Handfuls.TWO;
        }
        if (StringUtil.quickEq(r_,"THREE")) {
            return Handfuls.THREE;
        }
        if (StringUtil.quickEq(r_,"FOUR")) {
            return Handfuls.FOUR;
        }
        return Handfuls.NO;
    }
    public static Miseres toMiseres(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"POINT")) {
            return Miseres.POINT;
        }
        if (StringUtil.quickEq(r_,"CHARACTER")) {
            return Miseres.CHARACTER;
        }
        if (StringUtil.quickEq(r_,"SUIT")) {
            return Miseres.SUIT;
        }
        if (StringUtil.quickEq(r_,"LOW_CARDS")) {
            return Miseres.LOW_CARDS;
        }
        return Miseres.TRUMP;
    }
    public static EndDealTarot toEndDealTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"ATTACK_LOOSE")) {
            return EndDealTarot.ATTACK_LOOSE;
        }
        if (StringUtil.quickEq(r_,"ATTACK_WIN")) {
            return EndDealTarot.ATTACK_WIN;
        }
        return EndDealTarot.ZERO;
    }
    public static CardTarot toCardTarot(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (CardTarot c: HandTarot.pileBase()) {
            if (StringUtil.quickEq(r_,c.getId().getSt())) {
                return c;
            }
        }
        return CardTarot.WHITE;
    }
}
