package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Test;

public final class FacadeGameEggTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data_);
        Egg egg_;
        egg_ = new Egg(NUCLEOS);
        egg_.versEclosion((short) 15);
        game_.getPlayer().getBox().add(egg_);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        return facadeGame_;
    }
    @Test
    public void searchTest() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setContentOfNameEgg(null);
        facadeGame_.setSearchModeNameEgg(SearchingMode.WHOLE_STRING);
        facadeGame_.setMinStepsEgg(null);
        facadeGame_.setMaxStepsEgg(null);
        facadeGame_.setNbResultsPerPageEgg(1);
        facadeGame_.setDeltaEgg(1);
        facadeGame_.setCmpNameIncreasingEgg(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpNamePriorityEgg(1);
        facadeGame_.setCmpStepsIncreasingEgg(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpStepsPriorityEgg(2);
        facadeGame_.searchEgg();
        facadeGame_.endEgg();
        facadeGame_.previousDeltaEgg();
        facadeGame_.previousEgg();
        facadeGame_.nextDeltaEgg();
        facadeGame_.nextEgg();
        facadeGame_.changePageEgg(0);
        facadeGame_.beginEgg();
        facadeGame_.checkLineEggs(0);
        assertTrue(!facadeGame_.enabledNextEgg());
        assertTrue(!facadeGame_.enabledPreviousEgg());
        assertEq(1, facadeGame_.pagesEgg());
        assertEq(1, facadeGame_.getRenderedEgg().size());
        assertEq(0, facadeGame_.getNumberPageEgg());
        assertEq(0, facadeGame_.getLineEgg());
        facadeGame_.newSearchEgg();
        facadeGame_.checkLineEggs(0);
        assertTrue(!facadeGame_.enabledNextEgg());
        assertTrue(!facadeGame_.enabledPreviousEgg());
        assertEq(1, facadeGame_.pagesEgg());
        assertEq(1, facadeGame_.getRenderedEgg().size());
        assertEq(0, facadeGame_.getNumberPageEgg());
        assertEq(0, facadeGame_.getLineEgg());
        assertNotNull(facadeGame_.getSelectedEggBox());
        facadeGame_.setLineEggs(0);
        assertTrue(!facadeGame_.enabledNextEgg());
        assertTrue(!facadeGame_.enabledPreviousEgg());
        assertEq(1, facadeGame_.pagesEgg());
        assertEq(1, facadeGame_.getNbResultsPerPageEgg());
        assertEq(1, facadeGame_.getRenderedEgg().size());
        assertEq(0, facadeGame_.getNumberPageEgg());
        assertEq(0, facadeGame_.getLineEgg());
        facadeGame_.clearFiltersEgg();
        facadeGame_.clearSortingEgg();
    }
}
