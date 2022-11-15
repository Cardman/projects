package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import org.junit.Test;

public final class FacadeGameHostTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 8, 5));
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
    public void host1Test() {
        FacadeGame facadeGame_ = initTests();
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        facadeGame_.getGame().getPlayer().recevoirPokemon(givPk_, facadeGame_.getGame().getDifficulty(), facadeGame_.getData());
        facadeGame_.attemptForStoringPokemonToHost();
        assertEq(3,facadeGame_.getPlayer().getPokemonPlayerList().size());
        assertEq(-1,facadeGame_.getRemaingingSteps());
    }

    @Test
    public void host2Test() {
        FacadeGame facadeGame_ = initTests();
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        facadeGame_.getGame().getPlayer().recevoirPokemon(givPk_, facadeGame_.getGame().getDifficulty(), facadeGame_.getData());
        facadeGame_.setSelectPkToHost((short) 1);
        facadeGame_.attemptForStoringPokemonToHost();
        assertEq(3,facadeGame_.getPlayer().getPokemonPlayerList().size());
        assertEq(-1,facadeGame_.getRemaingingSteps());
    }

    @Test
    public void host3Test() {
        FacadeGame facadeGame_ = initTests();
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        facadeGame_.getGame().getPlayer().recevoirPokemon(givPk_, facadeGame_.getGame().getDifficulty(), facadeGame_.getData());
        facadeGame_.setSelectPkToHost((short) 1);
        facadeGame_.setSelectPkToHost((short) 2);
        facadeGame_.attemptForStoringPokemonToHost();
        assertEq(1,facadeGame_.getPlayer().getPokemonPlayerList().size());
        assertEq(1024,facadeGame_.getRemaingingSteps());
        facadeGame_.receiveFromHost(true);
        assertEq(1,facadeGame_.getPlayer().getTeam().size());
        assertEq(LIMAGMA,facadeGame_.getHostedPokemon(true,facadeGame_.getGame().closestTile(facadeGame_.getData().getMap())).getName());
        assertEq(MELOFEE,facadeGame_.getHostedPokemon(false,facadeGame_.getGame().closestTile(facadeGame_.getData().getMap())).getName());
        facadeGame_.receiveFromHost(false);
        assertEq(3,facadeGame_.getPlayer().getTeam().size());
    }

    @Test
    public void host4Test() {
        FacadeGame facadeGame_ = initTests();
        Pokemon givPk_ = new WildPk();
        givPk_.setName(MELOFEE);
        givPk_.setGender(Gender.NO_GENDER);
        givPk_.setAbility(STATIK);
        givPk_.setLevel((short) 7);
        givPk_.setItem(PP_PLUS);
        facadeGame_.getGame().getPlayer().recevoirPokemon(givPk_, facadeGame_.getGame().getDifficulty(), facadeGame_.getData());
        facadeGame_.setSelectPkToHost((short) 1);
        facadeGame_.setSelectPkToHost((short) 2);
        facadeGame_.attemptForStoringPokemonToHost();
        assertEq(1,facadeGame_.getPlayer().getPokemonPlayerList().size());
        assertEq(5,facadeGame_.getRemainingRooms());
        Coords coords_ = facadeGame_.closestTile();
        assertEq(1024,facadeGame_.getRemaingingSteps(coords_));
        facadeGame_.setHostedPokemon(false,coords_);
        assertNotNull(facadeGame_.getDisplayed());
        facadeGame_.setHostedPokemon(true,coords_);
        assertNotNull(facadeGame_.getDisplayed());
    }
    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.affectInside(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }

}
