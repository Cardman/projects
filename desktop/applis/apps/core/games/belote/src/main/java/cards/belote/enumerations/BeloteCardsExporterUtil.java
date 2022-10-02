package cards.belote.enumerations;

public final class BeloteCardsExporterUtil {
    private BeloteCardsExporterUtil() {
    }

    public static String fromDeclaresBelote(DeclaresBelote _role) {
        if (_role == DeclaresBelote.FOUR_1) {
            return "FOUR_1";
        }
        if (_role == DeclaresBelote.FOUR_KING) {
            return "FOUR_KING";
        }
        if (_role == DeclaresBelote.FOUR_QUEEN) {
            return "FOUR_QUEEN";
        }
        if (_role == DeclaresBelote.FOUR_JACK) {
            return "FOUR_JACK";
        }
        if (_role == DeclaresBelote.FOUR_10) {
            return "FOUR_10";
        }
        if (_role == DeclaresBelote.FOUR_9) {
            return "FOUR_9";
        }
        if (_role == DeclaresBelote.FOUR_8) {
            return "FOUR_8";
        }
        if (_role == DeclaresBelote.FOUR_7) {
            return "FOUR_7";
        }
        if (_role == DeclaresBelote.HUNDRED) {
            return "HUNDRED";
        }
        if (_role == DeclaresBelote.FIFTY) {
            return "FIFTY";
        }
        if (_role == DeclaresBelote.THIRTY) {
            return "THIRTY";
        }
        return "UNDEFINED";
    }
}
