package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Before;
import org.junit.Test;

public final class FacadeGameMoveTest extends InitializationDataBase {

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
        facadeGame.getPlayer().getTm((short) 2);
        facadeGame.setContentOfNameMove(null);
        facadeGame.setContentOfTypeMove(null);
        facadeGame.setSearchModeNameMove(SearchingMode.WHOLE_STRING);
        facadeGame.setSearchModeTypeMove(SearchingMode.WHOLE_STRING);
        facadeGame.setMinPriceMove(null);
        facadeGame.setMaxPriceMove(null);
        facadeGame.setMinPpMove(null);
        facadeGame.setMaxPpMove(null);
        facadeGame.setMinPrioMove(null);
        facadeGame.setMaxPrioMove(null);
        facadeGame.setSelectedClassMove(NULL_REF);
        facadeGame.setTargetChoiceMove(null);
        facadeGame.setNbResultsPerPageMove(1);
        facadeGame.setDeltaMove(1);
        facadeGame.setCmpPriceIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpPricePriorityMove(1);
        facadeGame.setCmpPppIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpPppPriorityMove(2);
        facadeGame.setCmpDescriptionIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpDescriptionPriorityMove(3);
        facadeGame.setCmpNameIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpNamePriorityMove(4);
        facadeGame.setCmpPrioIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpPrioPriorityMove(5);
        facadeGame.setCmpTargetChoiceIncreasingMove(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpTargetChoicePriorityMove(6);
        facadeGame.searchTmToUse();
        facadeGame.endMove();
        facadeGame.previousDeltaMove();
        facadeGame.previousMove();
        facadeGame.nextDeltaMove();
        facadeGame.nextMove();
        facadeGame.changePageMove(0);
        facadeGame.beginMove();
        facadeGame.checkLineMove(0);
        assertTrue(!facadeGame.enabledNextMove());
        assertTrue(!facadeGame.enabledPreviousMove());
        assertEq(1, facadeGame.pagesMove());
        assertEq(1, facadeGame.getRenderedMove().size());
        assertEq(0, facadeGame.getNumberPageMove());
        assertEq(0, facadeGame.getLineMove());
        assertNotNull(facadeGame.getSelectedMove());
        facadeGame.newSearchMove();
        facadeGame.checkLineMove(0);
        assertTrue(!facadeGame.enabledNextMove());
        assertTrue(!facadeGame.enabledPreviousMove());
        assertEq(1, facadeGame.getNbResultsPerPageMove());
        assertEq(1, facadeGame.pagesMove());
        assertEq(1, facadeGame.getRenderedMove().size());
        assertEq(0, facadeGame.getNumberPageMove());
        assertEq(0, facadeGame.getLineMove());
        facadeGame.clearFoundResultsTm();
        facadeGame.clearFiltersMove();
        facadeGame.clearSortingMove();
    }
}
