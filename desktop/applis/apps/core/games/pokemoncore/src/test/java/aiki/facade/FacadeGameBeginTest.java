package aiki.facade;

import aiki.db.DataBase;
import aiki.game.fight.InitializationDataBase;
import aiki.game.player.enums.Sex;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class FacadeGameBeginTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.put(LANGUAGE,LANGUAGE);
        facadeGame_.setLanguages(new StringList(LANGUAGE));
        facadeGame_.setDisplayLanguages(displayLanguages_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setZipName("zip");
        return facadeGame_;
    }

    @Test
    public void sexList() {
        assertEq(2,initTests().getSexList().all().size());
    }
    @Test
    public void act1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.clearGame();
        facadeGame_.newGame("",Sex.NO);
        assertEq(1, facadeGame_.getPlayer().getPokemonPlayerList().size());
        facadeGame_.initIv();
        assertTrue(!facadeGame_.isShowEndGame());
        facadeGame_.getEndGameMessage();
        facadeGame_.getGameProgression();
        facadeGame_.setLoadedData(true);
        assertTrue(facadeGame_.isLoadedData());
        assertEq(1, facadeGame_.getLanguages().size());
        assertEq(1, facadeGame_.getDisplayLanguages().size());
        assertEq(LANGUAGE, facadeGame_.getLanguage());
        assertEq("zip", facadeGame_.getZipName());
        assertEq("", facadeGame_.getComment().join());
        assertEq(LANGUAGE, facadeGame_.getData().getLanguage());
    }

    @Test
    public void act2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.newGame("", Sex.GIRL);
        facadeGame_.getBackHerosSexOpposite();
        facadeGame_.getFrontChosenHeros();
        facadeGame_.getFrontChosenHerosOppositeSex();
        assertEq(1, facadeGame_.getPlayer().getPokemonPlayerList().size());
    }

    @Test
    public void act3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.newGame("", Sex.BOY);
        facadeGame_.getBackHerosSexOpposite();
        facadeGame_.getFrontChosenHeros();
        facadeGame_.getFrontChosenHerosOppositeSex();
        assertEq(1, facadeGame_.getPlayer().getPokemonPlayerList().size());
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
