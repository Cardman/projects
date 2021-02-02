package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import org.junit.Before;
import org.junit.Test;

public final class FacadeGameHostTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 8, 5));
        game_.setPlayerOrientation(Direction.UP);
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
    public void host1Test() {
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        game.getPlayer().recevoirPokemon(givPk_, game.getDifficulty(), data);
        facadeGame.attemptForStoringPokemonToHost();
        assertEq(3,facadeGame.getPlayer().getPokemonPlayerList().size());
        assertEq(-1,facadeGame.getRemaingingSteps());
    }

    @Test
    public void host2Test() {
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        game.getPlayer().recevoirPokemon(givPk_, game.getDifficulty(), data);
        facadeGame.setSelectPkToHost((short) 1);
        facadeGame.attemptForStoringPokemonToHost();
        assertEq(3,facadeGame.getPlayer().getPokemonPlayerList().size());
        assertEq(-1,facadeGame.getRemaingingSteps());
    }

    @Test
    public void host3Test() {
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        game.getPlayer().recevoirPokemon(givPk_, game.getDifficulty(), data);
        facadeGame.setSelectPkToHost((short) 1);
        facadeGame.setSelectPkToHost((short) 2);
        facadeGame.attemptForStoringPokemonToHost();
        assertEq(1,facadeGame.getPlayer().getPokemonPlayerList().size());
        assertEq(1024,facadeGame.getRemaingingSteps());
        facadeGame.receiveFromHost(true);
        assertEq(1,facadeGame.getPlayer().getTeam().size());
        assertEq(LIMAGMA,facadeGame.getHostedPokemon(true,game.closestTile(data.getMap())).getName());
        assertEq(MELOFEE,facadeGame.getHostedPokemon(false,game.closestTile(data.getMap())).getName());
        facadeGame.receiveFromHost(false);
        assertEq(3,facadeGame.getPlayer().getTeam().size());
    }

    @Test
    public void host4Test() {
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        game.getPlayer().recevoirPokemon(givPk_, game.getDifficulty(), data);
        facadeGame.setSelectPkToHost((short) 1);
        facadeGame.setSelectPkToHost((short) 2);
        facadeGame.attemptForStoringPokemonToHost();
        assertEq(1,facadeGame.getPlayer().getPokemonPlayerList().size());
        assertEq(5,facadeGame.getRemainingRooms());
        Coords coords_ = facadeGame.closestTile();
        assertEq(1024,facadeGame.getRemaingingSteps(coords_));
        facadeGame.setHostedPokemon(false,coords_);
        assertNotNull(facadeGame.getHostedPokemon());
        facadeGame.setHostedPokemon(true,coords_);
        assertNotNull(facadeGame.getHostedPokemon());
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
