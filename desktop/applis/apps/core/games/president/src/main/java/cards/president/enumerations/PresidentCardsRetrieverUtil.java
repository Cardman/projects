package cards.president.enumerations;

import code.util.core.StringUtil;

public final class PresidentCardsRetrieverUtil {
    private PresidentCardsRetrieverUtil() {
    }
    public static EqualtyPlaying toEqualtyPlaying(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"FORBIDDEN")) {
            return EqualtyPlaying.FORBIDDEN;
        }
        if (StringUtil.quickEq(r_,"NO_SKIP")) {
            return EqualtyPlaying.NO_SKIP;
        }
        if (StringUtil.quickEq(r_,"SKIP_DIFF_NEXT_STOP")) {
            return EqualtyPlaying.SKIP_DIFF_NEXT_STOP;
        }
        return EqualtyPlaying.SKIP_ALWAYS_NEXT;
    }
}
