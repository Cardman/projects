package cards.gui;

import cards.gui.dialogs.EditorCardsNonModalEvent;
import code.util.StringList;
import org.junit.Test;

public final class WindowCardsTest extends EquallableCardsGuiUtil {
    @Test
    public void modal1() {
        WindowCards fr_ = frameDialogSoft("/__/", "/_/");
        assertTrue(new EditorCardsNonModalEvent(fr_).act());
    }
    @Test
    public void modal2() {
        WindowCards fr_ = frameDialogSoft("/__/", "/_/");
        tryClick(fr_.baseWindow().getInteract());
        assertFalse(new EditorCardsNonModalEvent(fr_).act());
    }
    @Test
    public void load1() {
        WindowCards fr_ =loadBeloteOtherDisplay(new StringList(""));
        assertTrue(fr_.getCommonFrame().isVisible());
    }
    @Test
    public void load2() {
        WindowCards fr_ =loadBeloteOtherDisplay(new StringList());
        assertTrue(fr_.getCommonFrame().isVisible());
    }
    private WindowCards loadBeloteOtherDisplay(StringList _ls) {
        WindowCards wc_ = frameDialogSoft("/__/", "/_/");
        wc_.getCommonFrame().setVisible(true);
        wc_.loadGameBegin(_ls);
        return wc_;
    }

}
