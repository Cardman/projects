package cards.gui;

import cards.facade.enumerations.GameEnum;
import cards.gui.animations.PreparedPagesCards;
import code.util.StringMap;
import org.junit.Test;

public final class EditorBeloteTest extends EquallableCardsGuiUtil {
    @Test
    public void rules1() {
        WindowCards wc_ = frame();
        assertTrue(wc_.getEditGames().getVal(GameEnum.BELOTE).isVisible());
        assertTrue(wc_.getEditGames().getVal(GameEnum.BELOTE).isEnabled());
        wc_.getEditGames().getVal(GameEnum.BELOTE).getActionListeners().get(0).action();
        assertTrue(wc_.getEditorBelote().getCardDialog().isVisible());
    }

    private WindowCards frame() {
        return new WindowCards("en", build(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }
}
