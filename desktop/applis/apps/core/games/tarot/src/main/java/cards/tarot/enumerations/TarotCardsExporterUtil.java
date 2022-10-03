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
}
