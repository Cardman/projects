package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class GameProgressionBeanTest extends InitDbBean {
    static final String GIRL = "G";
    static final String BOY = "B";

    @Test
    public void nickname1() {
        assertEq(GIRL, callGameProgressionBeanNicknameGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void nickname2() {
        assertEq(BOY, callGameProgressionBeanNicknameGet(displaying(beanProg(EN, fac(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void heroImage1() {
        assertEq(H_1, callGameProgressionBeanHeroImageGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void heroImage2() {
        assertEq(H_2, callGameProgressionBeanHeroImageGet(displaying(beanProg(EN, fac(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void heroImageOppositeSex1() {
        assertEq(H_2, callGameProgressionBeanHeroImageOppositeSexGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void heroImageOppositeSex2() {
        assertEq(H_1, callGameProgressionBeanHeroImageOppositeSexGet(displaying(beanProg(EN, fac(progress(),BOY, Sex.BOY)))));
    }

    private FacadeGame fac(DataBase _init, String _nickname,Sex _s) {
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(_init);
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.put(EN,EN);
        facadeGame_.setLanguages(new StringList(EN));
        facadeGame_.setDisplayLanguages(displayLanguages_);
        facadeGame_.setLanguage(EN);
        facadeGame_.setZipName("zip");
        facadeGame_.setLanguages(new StringList(EN));
        _init.setMessages(facadeGame_.getData());
        facadeGame_.setLoadedData(true);
        Game game_ = new Game(_init);
        game_.initUtilisateur(_nickname, _s, game_.getDifficulty(), _init);
        game_.setPlayerOrientation(Direction.UP);
        facadeGame_.setGame(game_);
        return facadeGame_;
    }
}
