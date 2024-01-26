package cards.president.enumerations;

import cards.president.HandPresident;
import code.util.core.StringUtil;

public final class PresidentCardsRetrieverUtil {
    private PresidentCardsRetrieverUtil() {
    }
    public static EqualtyPlaying toEqualtyPlaying(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (EqualtyPlaying c: EqualtyPlaying.all()) {
            if (StringUtil.quickEq(r_,c.getSt())) {
                return c;
            }
        }
        return EqualtyPlaying.SKIP_ALWAYS_NEXT;
    }

    public static CardPresident toCardPresident(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (CardPresident c: HandPresident.pileBase()) {
            if (StringUtil.quickEq(r_,Integer.toString(c.getId().nb()))) {
                return c;
            }
        }
        return CardPresident.WHITE;
    }
}
