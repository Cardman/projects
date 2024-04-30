package aiki.gui;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.main.AikiNatLgNamesNavigation;
import org.junit.Test;

public final class PlayerMenusTest extends InitDbGuiAiki {
    @Test
    public void progress() {
        WindowAiki window_ = newProg();
        window_.getCore().getAikiFactory().setPreparedProgTask(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav()));
        window_.setPreparedProgTask(window_.getCore().getAikiFactory().getPreparedProgTask());
        DataBase db_ = coreDataBase();
        window_.getFacade().setData(db_);
        Game g_ = new Game(db_);
        g_.initUserInteract("__", Sex.NO,g_.getDifficulty(),db_);
        window_.getFacade().load(g_);
        window_.getFacade().changeCamera();
        window_.afterLoadGame();
        tryClick(window_.getScenePanel().getGame());
        assertTrue(window_.getDialogGameProgess().getAbsDialog().isVisible());
    }
}
