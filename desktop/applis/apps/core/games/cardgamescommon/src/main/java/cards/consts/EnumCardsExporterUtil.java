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
            return "0";
        }
        if (_role == CardChar.KING) {
            return "1";
        }
        if (_role == CardChar.QUEEN) {
            return "2";
        }
        if (_role == CardChar.KNIGHT) {
            return "3";
        }
        if (_role == CardChar.JACK) {
            return "4";
        }
        return "5";
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
