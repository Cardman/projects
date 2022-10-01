package cards.president;

import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.PresidentCardsExporterUtil;
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
}
