package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Test;

public final class FacadeGameMoveTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data_);
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
        facadeGame_.getPlayer().getTm((short) 2);
        facadeGame_.setContentOfNameMove(null);
        facadeGame_.setContentOfTypeMove(null);
        facadeGame_.setSearchModeNameMove(SearchingMode.WHOLE_STRING);
        facadeGame_.setSearchModeTypeMove(SearchingMode.WHOLE_STRING);
        facadeGame_.setMinPriceMove(null);
        facadeGame_.setMaxPriceMove(null);
        facadeGame_.setMinPpMove(null);
        facadeGame_.setMaxPpMove(null);
        facadeGame_.setMinPrioMove(null);
        facadeGame_.setMaxPrioMove(null);
        facadeGame_.setSelectedClassMove(NULL_REF);
        facadeGame_.setTargetChoiceMove(null);
        facadeGame_.setNbResultsPerPageMove(1);
        facadeGame_.setDeltaMove(1);
        facadeGame_.setCmpPriceIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpPricePriorityMove(1);
        facadeGame_.setCmpPppIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpPppPriorityMove(2);
        facadeGame_.setCmpDescriptionIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpDescriptionPriorityMove(3);
        facadeGame_.setCmpNameIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpNamePriorityMove(4);
        facadeGame_.setCmpPrioIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpPrioPriorityMove(5);
        facadeGame_.setCmpTargetChoiceIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpTargetChoicePriorityMove(6);
        facadeGame_.searchTmToUse();
        facadeGame_.endMove();
        facadeGame_.previousDeltaMove();
        facadeGame_.previousMove();
        facadeGame_.nextDeltaMove();
        facadeGame_.nextMove();
        facadeGame_.changePageMove(0);
        facadeGame_.beginMove();
        facadeGame_.checkLineMove(0);
        assertTrue(!facadeGame_.enabledNextMove());
        assertTrue(!facadeGame_.enabledPreviousMove());
        assertEq(1, facadeGame_.pagesMove());
        assertEq(1, facadeGame_.getRenderedMove().size());
        assertEq(0, facadeGame_.getNumberPageMove());
        assertEq(0, facadeGame_.getLineMove());
        assertNotNull(facadeGame_.getSelectedMove());
        facadeGame_.newSearchMove();
        facadeGame_.checkLineMove(0);
        assertTrue(!facadeGame_.enabledNextMove());
        assertTrue(!facadeGame_.enabledPreviousMove());
        assertEq(1, facadeGame_.getNbResultsPerPageMove());
        assertEq(1, facadeGame_.pagesMove());
        assertEq(1, facadeGame_.getRenderedMove().size());
        assertEq(0, facadeGame_.getNumberPageMove());
        assertEq(0, facadeGame_.getLineMove());
        facadeGame_.clearFoundResultsTm();
        facadeGame_.clearFiltersMove();
        facadeGame_.clearSortingMove();
    }
}
