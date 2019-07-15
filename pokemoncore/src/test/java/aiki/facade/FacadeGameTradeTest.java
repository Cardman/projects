package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.UsesOfMove;
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
import code.maths.Rate;
import code.util.StringMap;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.*;

public final class FacadeGameTradeTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
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
    public void receive1Test() {
        facadeGame.initTrading();
        facadeGame.setIndexTeamTrading(-1);
        assertTrue(!facadeGame.isSelectedIndexTeamTrading());
        facadeGame.setIndexTeamTrading(0);
        assertTrue(facadeGame.isSelectedIndexTeamTrading());
        PokemonPlayer pokemonPlayer_ = newPokemonPlayer(MELOFEE, STATIK, Gender.NO_GENDER, PP_PLUS);
        facadeGame.receivePokemonPlayer(pokemonPlayer_);
        assertEq(2,facadeGame.getPlayer().getPokemonPlayerList().size());
    }

    @Test
    public void receive2Test() {
        facadeGame.initTrading();
        facadeGame.setIndexTeamTrading(0);
        assertTrue(facadeGame.isSelectedIndexTeamTrading());
        PokemonPlayer pokemonPlayer_ = newPokemonPlayer("UNKNOWN", STATIK, Gender.NO_GENDER, PP_PLUS);
        facadeGame.receivePokemonPlayer(pokemonPlayer_);
        assertEq(2,facadeGame.getPlayer().getPokemonPlayerList().size());
    }
    private static PokemonPlayer newPokemonPlayer(String _name, String _ability, Gender _gender, String _item) {
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(_name);
        sent_.setLevel((short) 1);
        sent_.setAbility(_ability);
        sent_.setItem(_item);
        sent_.setGender(_gender);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(CHARGE, new UsesOfMove((short) 10));
        sent_.setHappiness((short) 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        return sent_;
    }
}
