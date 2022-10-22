package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import org.junit.Test;

public final class FacadeGamePokemonPlayerTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data_);
        Pokemon w_ = new WildPk();
        w_.setName(NUCLEOS);
        w_.setGender(Gender.NO_GENDER);
        w_.setLevel((short) 2);
        w_.setAbility(ABSORB_EAU);
        w_.setItem(MULTI_EXP);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(w_, data_);
        game_.getPlayer().getBox().add(pkPlayer_);
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
        facadeGame_.setContentOfNameFirstBox(null);
        facadeGame_.setContentOfAbilityFirstBox(null);
        facadeGame_.setContentOfItemFirstBox(null);
        facadeGame_.setContentOfMoveFirstBox(null);
        facadeGame_.setSearchModeNameFirstBox(SearchingMode.WHOLE_STRING);
        facadeGame_.setSearchModeAbilityFirstBox(SearchingMode.WHOLE_STRING);
        facadeGame_.setSearchModeItemFirstBox(SearchingMode.WHOLE_STRING);
        facadeGame_.setSearchModeMoveFirstBox(SearchingMode.WHOLE_STRING);
        facadeGame_.setWithItemFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame_.setMinLevelFirstBox(null);
        facadeGame_.setMaxLevelFirstBox(null);
        facadeGame_.setMinNbPossEvolsFirstBox(null);
        facadeGame_.setMaxNbPossEvolsFirstBox(null);
        facadeGame_.setGenderFirstBox(null);
        facadeGame_.setNbResultsPerPageFirstBox(1);
        facadeGame_.setDeltaFirstBox(1);
        facadeGame_.setCmpAbilityIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpAbilityPriorityFirstBox(1);
        facadeGame_.setCmpNameIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpNamePriorityFirstBox(2);
        facadeGame_.setCmpItemIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpItemPriorityFirstBox(3);
        facadeGame_.setCmpLevelIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpLevelPriorityFirstBox(4);
        facadeGame_.setCmpPossEvosIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpPossEvosPriorityFirstBox(5);
        facadeGame_.setCmpGenderIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame_.setCmpGenderPriorityFirstBox(6);
        facadeGame_.searchPokemonFirstBox();
        facadeGame_.endFirstBox();
        facadeGame_.previousDeltaFirstBox();
        facadeGame_.previousFirstBox();
        facadeGame_.nextDeltaFirstBox();
        facadeGame_.nextFirstBox();
        facadeGame_.changePageFirstBox(0);
        facadeGame_.beginFirstBox();
        facadeGame_.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame_.enabledNextFirstBox());
        assertTrue(!facadeGame_.enabledPreviousFirstBox());
        assertEq(1, facadeGame_.pagesPk());
        assertEq(1, facadeGame_.getRenderedFirstBox().size());
        assertEq(0, facadeGame_.getNumberPageFirstBox());
        assertEq(0, facadeGame_.getLineFirstBox());
        assertNotNull(facadeGame_.getDisplayed());
        assertTrue(!facadeGame_.isSelectedTeamPokemon());
        facadeGame_.newSearchPokemonFirstBox();
        facadeGame_.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame_.enabledNextFirstBox());
        assertTrue(!facadeGame_.enabledPreviousFirstBox());
        assertEq(1, facadeGame_.pagesPk());
        assertEq(1, facadeGame_.getRenderedFirstBox().size());
        assertEq(0, facadeGame_.getNumberPageFirstBox());
        assertEq(0, facadeGame_.getLineFirstBox());
        facadeGame_.setLinePokemonFirstBox(0);
        assertTrue(!facadeGame_.enabledNextFirstBox());
        assertTrue(!facadeGame_.enabledPreviousFirstBox());
        assertEq(1, facadeGame_.pagesPk());
        assertEq(1, facadeGame_.getNbResultsPerPageFirstBox());
        assertEq(1, facadeGame_.getRenderedFirstBox().size());
        assertEq(0, facadeGame_.getNumberPageFirstBox());
        assertEq(0, facadeGame_.getLineFirstBox());
        facadeGame_.clearFoundResultsStoragePokemon();
        facadeGame_.clearFiltersFirstBox();
        facadeGame_.clearSortingFirstBox();
    }
}
