package cards.consts;

public final class EnumCardsExporterUtil {
    public static final String SUITS = "0";
    public static final String ROLE = "1";

    private EnumCardsExporterUtil(){
    }
    public static String fromRole(Role _role) {
        return _role.getRoleSt();
    }
    public static String fromSuit(Suit _role) {
        return _role.getSuitSt();
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
        return _role.getGameTypeSt();
    }
    public static String fromOrder(Order _role) {
        return _role.getOrderSt();
    }
    public static String fromMixCardsChoice(MixCardsChoice _role) {
        return _role.getMixSt();
    }

}
