package aiki.facade;

import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.game.fight.InitializationDataBase;
import aiki.game.player.enums.Sex;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class FacadeGameBeginTest extends InitializationDataBase {


    @Test
    public void sexList() {
        CustList<Sex> real_ = initTests().getSexList().all();
        assertEq(2, real_.size());
        assertSame(Sex.GIRL,real_.get(0));
        assertSame(Sex.BOY,real_.get(1));
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
        facadeGame_.getFrontChosenHeros(MessagesDataBaseConstants.BASE);
        facadeGame_.getFrontChosenHerosOppositeSex(MessagesDataBaseConstants.BASE);
        assertEq(1, facadeGame_.getPlayer().getPokemonPlayerList().size());
    }

    @Test
    public void act3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.newGame("", Sex.BOY);
        facadeGame_.getBackHerosSexOpposite();
        facadeGame_.getFrontChosenHeros(MessagesDataBaseConstants.BASE);
        facadeGame_.getFrontChosenHerosOppositeSex(MessagesDataBaseConstants.BASE);
        assertEq(1, facadeGame_.getPlayer().getPokemonPlayerList().size());
    }

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.put(LANGUAGE,LANGUAGE);
        facadeGame_.setLanguages(indexes());
        facadeGame_.setDisplayLanguages(displayLanguages_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setZipName("zip");
        return facadeGame_;
    }
    public static StringList indexes(){
        return new StringList(LANGUAGE);
    }

}
