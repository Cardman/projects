package cards.president.enumerations;

public final class PresidentCardsExporterUtil {
    private PresidentCardsExporterUtil() {
    }

    public static String fromEqualtyPlaying(EqualtyPlaying _role) {
        if (_role == EqualtyPlaying.FORBIDDEN) {
            return "FORBIDDEN";
        }
        if (_role == EqualtyPlaying.SKIP_ALWAYS_NEXT) {
            return "SKIP_ALWAYS_NEXT";
        }
        if (_role == EqualtyPlaying.SKIP_DIFF_NEXT_STOP) {
            return "SKIP_DIFF_NEXT_STOP";
        }
        return "NO_SKIP";
    }
}
