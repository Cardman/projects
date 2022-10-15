package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import org.junit.Test;

public final class DifficultyBeanTest extends InitDbBean {

    @Test
    public void getAllowCatchingKo1() {
        FacadeGame fac_ = fac(2);
        fac_.getGame().getDifficulty().setAllowCatchingKo(true);
        assertTrue(callDifficultyBeanAllowCatchingKoGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getAllowCatchingKo2() {
        FacadeGame fac_ = fac(2);
        fac_.getGame().getDifficulty().setAllowCatchingKo(false);
        assertFalse(callDifficultyBeanAllowCatchingKoGet(displaying(beanDiff(EN, fac_))));
    }

    private FacadeGame fac(int _iv) {
        DataBase di_ = diff(_iv);
        FacadeGame f_ = new FacadeGame();
        f_.setData(di_);
        f_.setGame(new Game());
        return f_;
    }
}
