package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
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

    @Test
    public void finish1() {
        assertFalse(callGameProgressionBeanFinishedGameGet(displaying(beanProg(EN, fac(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void finish2() {
        assertFalse(callGameProgressionBeanFinishedGameGet(displaying(beanProg(EN, fac(progress(),BOY, Sex.BOY)))));
    }

    @Test
    public void finish3() {
        assertTrue(callGameProgressionBeanFinishedGameGet(displaying(beanProg(EN, finish(progress(),GIRL,Sex.GIRL)))));
    }

    @Test
    public void finish4() {
        assertTrue(callGameProgressionBeanFinishedGameGet(displaying(beanProg(EN, finish(progress(),BOY, Sex.BOY)))));
    }

    private FacadeGame finish(DataBase _init, String _nickname,Sex _s) {
        FacadeGame fac_ = fac(_init, _nickname, _s);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK1, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK2, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK3, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK4, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK5, BoolVal.TRUE);
        fac_.getGame().getPlayer().getCaughtPk().set(PROG_PK6, BoolVal.TRUE);
        PokemonPlayer pk_ = fac_.getGame().getPlayer().getPokemonPlayerList().getValue(0);
        pk_.setLevel((short) fac_.getData().getMaxLevel());
        pk_.setHappiness((short) fac_.getData().getHappinessMax());
        return fac_;
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
