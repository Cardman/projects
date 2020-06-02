package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class FacadeGameEggTest extends InitializationDataBase {

    private DataBase data;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data);
        Egg egg_;
        egg_ = new Egg(NUCLEOS);
        egg_.versEclosion((short) 15);
        game_.getPlayer().getBox().add(egg_);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        facadeGame = facadeGame_;
    }
    @Test
    public void searchTest() {
        facadeGame.setContentOfNameEgg(null);
        facadeGame.setSearchModeNameEgg(SearchingMode.WHOLE_STRING);
        facadeGame.setMinStepsEgg(null);
        facadeGame.setMaxStepsEgg(null);
        facadeGame.setNbResultsPerPageEgg(1);
        facadeGame.setDeltaEgg(1);
        facadeGame.setCmpNameIncreasingEgg(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpNamePriorityEgg(1);
        facadeGame.setCmpStepsIncreasingEgg(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpStepsPriorityEgg(2);
        facadeGame.searchEgg();
        facadeGame.endEgg();
        facadeGame.previousDeltaEgg();
        facadeGame.previousEgg();
        facadeGame.nextDeltaEgg();
        facadeGame.nextEgg();
        facadeGame.changePageEgg(0);
        facadeGame.beginEgg();
        facadeGame.checkLineEggs(0);
        assertTrue(!facadeGame.enabledNextEgg());
        assertTrue(!facadeGame.enabledPreviousEgg());
        assertEq(1, facadeGame.pagesEgg());
        assertEq(1, facadeGame.getRenderedEgg().size());
        assertEq(0, facadeGame.getNumberPageEgg());
        assertEq(0, facadeGame.getLineEgg());
        facadeGame.newSearchEgg();
        facadeGame.checkLineEggs(0);
        assertTrue(!facadeGame.enabledNextEgg());
        assertTrue(!facadeGame.enabledPreviousEgg());
        assertEq(1, facadeGame.pagesEgg());
        assertEq(1, facadeGame.getRenderedEgg().size());
        assertEq(0, facadeGame.getNumberPageEgg());
        assertEq(0, facadeGame.getLineEgg());
        assertNotNull(facadeGame.getSelectedEggBox());
        facadeGame.setLineEggs(0);
        assertTrue(!facadeGame.enabledNextEgg());
        assertTrue(!facadeGame.enabledPreviousEgg());
        assertEq(1, facadeGame.pagesEgg());
        assertEq(1, facadeGame.getNbResultsPerPageEgg());
        assertEq(1, facadeGame.getRenderedEgg().size());
        assertEq(0, facadeGame.getNumberPageEgg());
        assertEq(0, facadeGame.getLineEgg());
        facadeGame.clearFiltersEgg();
        facadeGame.clearSortingEgg();
    }
}
