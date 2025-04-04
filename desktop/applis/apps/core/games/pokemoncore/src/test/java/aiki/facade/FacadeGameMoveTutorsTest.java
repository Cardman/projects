package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Test;

public final class FacadeGameMoveTutorsTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 6));
        game_.setPlayerOrientation(Direction.RIGHT);
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
    public void listMoves1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.choosePokemonForMoveTutors(0);
        assertEq(3,facadeGame_.getSelectedMoves().size());
        assertEq(1,facadeGame_.getUnselectedMoves().size());
        String move_ = facadeGame_.getUnselectedMoves().get(0);
        facadeGame_.addOrDeleteMove(move_);
        assertEq(4,facadeGame_.getSelectedMoves().size());
        assertEq(0,facadeGame_.getUnselectedMoves().size());
        facadeGame_.addOrDeleteMove(move_);
        assertEq(3,facadeGame_.getSelectedMoves().size());
        assertEq(1,facadeGame_.getUnselectedMoves().size());
    }

    @Test
    public void listMoves2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.choosePokemonForMoveTutors(0);
        assertEq(3,facadeGame_.getSelectedMoves().size());
        assertEq(1,facadeGame_.getUnselectedMoves().size());
        String move_ = facadeGame_.getUnselectedMoves().get(0);
        facadeGame_.addOrDeleteMove(move_);
        assertEq(4,facadeGame_.getSelectedMoves().size());
        assertEq(0,facadeGame_.getUnselectedMoves().size());
        facadeGame_.addOrDeleteMove(move_);
        assertEq(3,facadeGame_.getSelectedMoves().size());
        assertEq(1,facadeGame_.getUnselectedMoves().size());
        facadeGame_.cancelLearningMoveOnPokemon();
        assertEq(3,facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0).getMoves().size());
    }

    @Test
    public void listMoves3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.choosePokemonForMoveTutors(0);
        assertEq(3,facadeGame_.getSelectedMoves().size());
        assertEq(1,facadeGame_.getUnselectedMoves().size());
        String move_ = facadeGame_.getUnselectedMoves().get(0);
        facadeGame_.addOrDeleteMove(move_);
        assertEq(4,facadeGame_.getSelectedMoves().size());
        assertEq(0,facadeGame_.getUnselectedMoves().size());
        assertEq(3,facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0).getMoves().size());
        facadeGame_.learnMovesByMoveTutor();
        assertEq(4,facadeGame_.getGame().getPlayer().getPokemonPlayerList().getValue(0).getMoves().size());

    }

}
