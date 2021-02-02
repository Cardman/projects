package aiki.facade;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import org.junit.Before;
import org.junit.Test;

public final class FacadeGamePokemonPlayerTest extends InitializationDataBase {

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
        Pokemon w_ = new WildPk();
        w_.setName(NUCLEOS);
        w_.setGender(Gender.NO_GENDER);
        w_.setLevel((short) 2);
        w_.setAbility(ABSORB_EAU);
        w_.setItem(MULTI_EXP);
        PokemonPlayer pkPlayer_ = new PokemonPlayer(w_, data);
        game_.getPlayer().getBox().add(pkPlayer_);
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
        facadeGame.setContentOfNameFirstBox(null);
        facadeGame.setContentOfAbilityFirstBox(null);
        facadeGame.setContentOfItemFirstBox(null);
        facadeGame.setContentOfMoveFirstBox(null);
        facadeGame.setSearchModeNameFirstBox(SearchingMode.WHOLE_STRING);
        facadeGame.setSearchModeAbilityFirstBox(SearchingMode.WHOLE_STRING);
        facadeGame.setSearchModeItemFirstBox(SearchingMode.WHOLE_STRING);
        facadeGame.setSearchModeMoveFirstBox(SearchingMode.WHOLE_STRING);
        facadeGame.setWithItemFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame.setMinLevelFirstBox(null);
        facadeGame.setMaxLevelFirstBox(null);
        facadeGame.setMinNbPossEvolsFirstBox(null);
        facadeGame.setMaxNbPossEvolsFirstBox(null);
        facadeGame.setGenderFirstBox(null);
        facadeGame.setNbResultsPerPageFirstBox(1);
        facadeGame.setDeltaFirstBox(1);
        facadeGame.setCmpAbilityIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpAbilityPriorityFirstBox(1);
        facadeGame.setCmpNameIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpNamePriorityFirstBox(2);
        facadeGame.setCmpItemIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpItemPriorityFirstBox(3);
        facadeGame.setCmpLevelIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpLevelPriorityFirstBox(4);
        facadeGame.setCmpPossEvosIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpPossEvosPriorityFirstBox(5);
        facadeGame.setCmpGenderIncreasingFirstBox(SelectedBoolean.YES_AND_NO);
        facadeGame.setCmpGenderPriorityFirstBox(6);
        facadeGame.searchPokemonFirstBox();
        facadeGame.endFirstBox();
        facadeGame.previousDeltaFirstBox();
        facadeGame.previousFirstBox();
        facadeGame.nextDeltaFirstBox();
        facadeGame.nextFirstBox();
        facadeGame.changePageFirstBox(0);
        facadeGame.beginFirstBox();
        facadeGame.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame.enabledNextFirstBox());
        assertTrue(!facadeGame.enabledPreviousFirstBox());
        assertEq(1, facadeGame.pagesPk());
        assertEq(1, facadeGame.getRenderedFirstBox().size());
        assertEq(0, facadeGame.getNumberPageFirstBox());
        assertEq(0, facadeGame.getLineFirstBox());
        assertTrue(facadeGame.isSelectedBoxPokemon());
        assertTrue(!facadeGame.isSelectedTeamPokemon());
        assertTrue(!facadeGame.isSelectedOtherPokemon());
        facadeGame.newSearchPokemonFirstBox();
        facadeGame.checkLinePokemonFirstBox(0);
        assertTrue(!facadeGame.enabledNextFirstBox());
        assertTrue(!facadeGame.enabledPreviousFirstBox());
        assertEq(1, facadeGame.pagesPk());
        assertEq(1, facadeGame.getRenderedFirstBox().size());
        assertEq(0, facadeGame.getNumberPageFirstBox());
        assertEq(0, facadeGame.getLineFirstBox());
        facadeGame.setLinePokemonFirstBox(0);
        assertTrue(!facadeGame.enabledNextFirstBox());
        assertTrue(!facadeGame.enabledPreviousFirstBox());
        assertEq(1, facadeGame.pagesPk());
        assertEq(1, facadeGame.getNbResultsPerPageFirstBox());
        assertEq(1, facadeGame.getRenderedFirstBox().size());
        assertEq(0, facadeGame.getNumberPageFirstBox());
        assertEq(0, facadeGame.getLineFirstBox());
        facadeGame.clearFoundResultsStoragePokemon();
        facadeGame.clearFiltersFirstBox();
        facadeGame.clearSortingFirstBox();
    }
}
