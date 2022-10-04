package cards.president;

import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.PresidentCardsRetrieverUtil;
import org.junit.Test;

public final class PresidentCardsRetrieverUtilTest extends EquallablePresidentUtil {
    @Test
    public void equalty1() {
        assertSame(EqualtyPlaying.FORBIDDEN, PresidentCardsRetrieverUtil.toEqualtyPlaying("FORBIDDEN"));
    }
    @Test
    public void equalty2() {
        assertSame(EqualtyPlaying.SKIP_ALWAYS_NEXT, PresidentCardsRetrieverUtil.toEqualtyPlaying("SKIP_ALWAYS_NEXT"));
    }
    @Test
    public void equalty3() {
        assertSame(EqualtyPlaying.SKIP_DIFF_NEXT_STOP, PresidentCardsRetrieverUtil.toEqualtyPlaying("SKIP_DIFF_NEXT_STOP"));
    }
    @Test
    public void equalty4() {
        assertSame(EqualtyPlaying.NO_SKIP, PresidentCardsRetrieverUtil.toEqualtyPlaying("NO_SKIP"));
    }
    @Test
    public void toCardPresident1() {
        assertEq(CardPresident.HEART_2, PresidentCardsRetrieverUtil.toCardPresident("HEART_2"));
    }
    @Test
    public void toCardPresident2() {
        assertEq(CardPresident.WHITE, PresidentCardsRetrieverUtil.toCardPresident("WHITE"));
    }
}
