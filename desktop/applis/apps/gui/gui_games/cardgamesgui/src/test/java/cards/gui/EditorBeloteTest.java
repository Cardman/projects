package cards.gui;

import cards.facade.enumerations.GameEnum;
import cards.gui.animations.PreparedPagesCards;
import code.util.StringMap;
import org.junit.Test;

public final class EditorBeloteTest extends EquallableCardsGuiUtil {
    @Test
    public void rules1() {
        WindowCards wc_ = frame();
        tryClick(wc_.getEditGames().getVal(GameEnum.BELOTE));
        assertTrue(wc_.getEditorBelote().getCardDialog().isVisible());
    }

    private WindowCards frame() {
        return new WindowCards("en", build(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>(), new StringMap<StringMap<PreparedPagesCards>>());
    }
}
