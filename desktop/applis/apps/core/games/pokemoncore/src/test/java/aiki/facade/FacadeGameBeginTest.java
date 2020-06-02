package aiki.facade;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.enums.Sex;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class FacadeGameBeginTest extends InitializationDataBase {

    private DataBase data;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.put(LANGUAGE,LANGUAGE);
        facadeGame_.setLanguages(new StringList(LANGUAGE));
        facadeGame_.setDisplayLanguages(displayLanguages_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setZipName("zip");
        facadeGame = facadeGame_;
    }

    @Test
    public void act1Test() {
        facadeGame.clearGame();
        facadeGame.newGame("",null);
        assertEq(1, facadeGame.getPlayer().getPokemonPlayerList().size());
        facadeGame.initIv();
        assertTrue(!facadeGame.isShowEndGame());
        facadeGame.getEndGameMessage();
        facadeGame.getGameProgression();
        facadeGame.setLoadedData(true);
        assertTrue(facadeGame.isLoadedData());
        assertEq(1, facadeGame.getLanguages().size());
        assertEq(1, facadeGame.getDisplayLanguages().size());
        assertEq(LANGUAGE, facadeGame.getLanguage());
        assertEq("zip", facadeGame.getZipName());
        assertEq("", facadeGame.getComment().join());
        assertEq(LANGUAGE, facadeGame.getData().getLanguage());
    }

    @Test
    public void act2Test() {
        facadeGame.newGame("", Sex.GIRL);
        facadeGame.getBackHerosSexOpposite();
        facadeGame.getFrontChosenHeros();
        facadeGame.getFrontChosenHerosOppositeSex();
        assertEq(1, facadeGame.getPlayer().getPokemonPlayerList().size());
    }

    @Test
    public void act3Test() {
        facadeGame.newGame("", Sex.BOY);
        facadeGame.getBackHerosSexOpposite();
        facadeGame.getFrontChosenHeros();
        facadeGame.getFrontChosenHerosOppositeSex();
        assertEq(1, facadeGame.getPlayer().getPokemonPlayerList().size());
    }
    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

}
