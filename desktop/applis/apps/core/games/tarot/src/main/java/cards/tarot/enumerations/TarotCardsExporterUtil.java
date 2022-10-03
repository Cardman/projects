package cards.tarot.enumerations;

public final class TarotCardsExporterUtil {
    private TarotCardsExporterUtil(){
    }

    public static String fromBidTarot(BidTarot _role) {
        if (_role == BidTarot.TAKE) {
            return "TAKE";
        }
        if (_role == BidTarot.GUARD) {
            return "GUARD";
        }
        if (_role == BidTarot.GUARD_WITHOUT) {
            return "GUARD_WITHOUT";
        }
        if (_role == BidTarot.GUARD_AGAINST) {
            return "GUARD_AGAINST";
        }
        if (_role == BidTarot.SLAM) {
            return "SLAM";
        }
        return "FOLD";
    }
    public static String fromModeTarot(ModeTarot _role) {
        if (_role == ModeTarot.NORMAL) {
            return "NORMAL";
        }
        if (_role == ModeTarot.NORMAL_WITH_MISERE) {
            return "NORMAL_WITH_MISERE";
        }
        if (_role == ModeTarot.NORMAL_WITH_ONE_FOR_ONE) {
            return "NORMAL_WITH_ONE_FOR_ONE";
        }
        if (_role == ModeTarot.MISERE) {
            return "MISERE";
        }
        return "ONE_FOR_ONE";
    }
    public static String fromDealingTarot(DealingTarot _role) {
        if (_role == DealingTarot.DEAL_1_VS_2) {
            return "DEAL_1_VS_2";
        }
        if (_role == DealingTarot.DEAL_1_VS_3) {
            return "DEAL_1_VS_3";
        }
        if (_role == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL) {
            return "DEAL_2_VS_2_WITHOUT_CALL";
        }
        if (_role == DealingTarot.DEAL_2_VS_2_CALL_KING) {
            return "DEAL_2_VS_2_CALL_KING";
        }
        if (_role == DealingTarot.DEAL_2_VS_2_CALL_CHAR) {
            return "DEAL_2_VS_2_CALL_CHAR";
        }
        if (_role == DealingTarot.DEAL_1_VS_4) {
            return "DEAL_1_VS_4";
        }
        if (_role == DealingTarot.DEAL_2_VS_3_CALL_KING) {
            return "DEAL_2_VS_3_CALL_KING";
        }
        if (_role == DealingTarot.DEAL_2_VS_3_CALL_CHAR) {
            return "DEAL_2_VS_3_CALL_CHAR";
        }
        if (_role == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL) {
            return "DEAL_2_VS_4_WITHOUT_CALL";
        }
        if (_role == DealingTarot.DEAL_2_VS_4_CALL_KING) {
            return "DEAL_2_VS_4_CALL_KING";
        }
        return "DEAL_2_VS_4_CALL_CHAR";
    }
}
