package cards.gui;

import cards.facade.enumerations.GameEnum;
import org.junit.Test;

public final class EditorBeloteTest extends EquallableCardsGuiUtil {
    @Test
    public void rules1() {
        WindowCards wc_ = frame1();
        tryClick(wc_.getEditGames().getVal(GameEnum.BELOTE));
        assertTrue(wc_.getEditorBelote().getCardDialog().isVisible());
        assertNotNull(wc_.getEditorBelote().getNbGames());
    }
}
