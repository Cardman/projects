package cards.president;

import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.PresidentCardsExporterUtil;
import cards.president.enumerations.PresidentResoucesAccess;
import org.junit.Test;

public final class PresidentCardsExporterUtilTest extends EquallablePresidentUtil {
    @Test
    public void equalty1() {
        assertEq("FORBIDDEN", PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.FORBIDDEN));
    }
    @Test
    public void equalty2() {
        assertEq("SKIP_ALWAYS_NEXT", PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_ALWAYS_NEXT));
    }
    @Test
    public void equalty3() {
        assertEq("SKIP_DIFF_NEXT_STOP", PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_DIFF_NEXT_STOP));
    }
    @Test
    public void equalty4() {
        assertEq("NO_SKIP", PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.NO_SKIP));
    }
    @Test
    public void key1() {
        assertEq("cards.president.enumerations.EqualtyPlaying.FORBIDDEN", PresidentResoucesAccess.key(EqualtyPlaying.FORBIDDEN));
    }
    @Test
    public void key2() {
        assertEq("cards.president.enumerations.CardPresident.WHITE", PresidentResoucesAccess.key(CardPresident.WHITE));
    }
    @Test
    public void fromCardPresident() {
        assertEq("WHITE", PresidentCardsExporterUtil.fromCardPresident(CardPresident.WHITE));
    }
}
