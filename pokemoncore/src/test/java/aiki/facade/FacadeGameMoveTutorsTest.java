package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;

public final class FacadeGameMoveTutorsTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 6));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        game = game_;
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.directInteraction();
        facadeGame_.interact();
        facadeGame = facadeGame_;
    }

    @Test
    public void listMovesTest() {
        facadeGame.choosePokemonForMoveTutors((short)0);
        assertEq(3,facadeGame.getSelectedMoves().size());
        assertEq(1,facadeGame.getUnselectedMoves().size());
        String move_ = facadeGame.getUnselectedMoves().get(0);
        facadeGame.addOrDeleteMove(move_);
        assertEq(4,facadeGame.getSelectedMoves().size());
        assertEq(0,facadeGame.getUnselectedMoves().size());
        facadeGame.addOrDeleteMove(move_);
        assertEq(3,facadeGame.getSelectedMoves().size());
        assertEq(1,facadeGame.getUnselectedMoves().size());
    }

    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }

}
