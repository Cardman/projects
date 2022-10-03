package cards.consts;

public final class EnumCardsExporterUtil {
    private EnumCardsExporterUtil(){
    }
    public static String fromRole(Role _role) {
        if (_role == Role.TAKER) {
            return "TAKER";
        }
        if (_role == Role.CALLED_PLAYER) {
            return "CALLED_PLAYER";
        }
        return "DEFENDER";
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
    public static String fromCardChar(CardChar _role) {
        if (_role == CardChar.EXCUSE) {
            return "EXCUSE";
        }
        if (_role == CardChar.KING) {
            return "KING";
        }
        if (_role == CardChar.QUEEN) {
            return "QUEEN";
        }
        if (_role == CardChar.KNIGHT) {
            return "KNIGHT";
        }
        if (_role == CardChar.JACK) {
            return "JACK";
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
