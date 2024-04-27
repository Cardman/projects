package aiki.gui;

import code.mock.*;
import org.junit.*;

public final class WindowAikiTest extends InitDbGuiAiki {
    @Test
    public void initGame() {
        WindowAiki window_ = newGame();
        window_.pack();
        window_.setVisible(true);
        window_.getFacade().setSexList(new MockLSexList());
        window_.getFacade().setData(coreDataBase());
        window_.getFacade().setLanguage(LANGUAGE);
        tryClick(window_.getNewGame());
        assertEq(3,((MockCustComponent)window_.getCommonFrame().getPane()).getTreeAccessible().size());
    }
}
