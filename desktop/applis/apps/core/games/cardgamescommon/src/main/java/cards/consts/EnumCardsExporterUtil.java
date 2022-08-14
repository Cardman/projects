package cards.consts;

public final class EnumCardsExporterUtil {
    private EnumCardsExporterUtil(){
    }
    public static String fromSuit(Suit _role) {
        if (_role == Suit.TRUMP) {
            return "TRUMP";
        }
        if (_role == Suit.HEART) {
            return "HEART";
        }
        if (_role == Suit.SPADE) {
            return "SPADE";
        }
        if (_role == Suit.DIAMOND) {
            return "DIAMOND";
        }
        if (_role == Suit.CLUB) {
            return "CLUB";
        }
        return "UNDEFINED";
    }
    public static String fromGameType(GameType _role) {
        if (_role == GameType.EDIT) {
            return "EDIT";
        }
        if (_role == GameType.TRAINING) {
            return "TRAINING";
        }
        return "RANDOM";
    }
    public static String fromOrder(Order _role) {
        if (_role == Order.TRUMP) {
            return "TRUMP";
        }
        if (_role == Order.SUIT) {
            return "SUIT";
        }
        return "NOTHING";
    }
    public static String fromMixCardsChoice(MixCardsChoice _role) {
        if (_role == MixCardsChoice.EACH_DEAL) {
            return "EACH_DEAL";
        }
        if (_role == MixCardsChoice.ONCE_ONLY) {
            return "ONCE_ONLY";
        }
        if (_role == MixCardsChoice.EACH_LAUNCHING) {
            return "EACH_LAUNCHING";
        }
        return "NEVER";
    }

}
