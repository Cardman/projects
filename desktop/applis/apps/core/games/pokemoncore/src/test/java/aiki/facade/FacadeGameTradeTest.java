package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.StringMap;
import org.junit.Test;

public final class FacadeGameTradeTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
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
    public void receive1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.initTrading();
        facadeGame_.setIndexTeamTrading(-1);
        assertTrue(!facadeGame_.isSelectedIndexTeamTrading());
        facadeGame_.setIndexTeamTrading(0);
        assertTrue(facadeGame_.isSelectedIndexTeamTrading());
        PokemonPlayer pokemonPlayer_ = newPokemonPlayer(MELOFEE, STATIK, Gender.NO_GENDER, PP_PLUS);
        facadeGame_.receivePokemonPlayer(pokemonPlayer_);
        assertEq(2,facadeGame_.getPlayer().getPokemonPlayerList().size());
        assertNotNull(facadeGame_.getExchangeData());
        assertNotNull(facadeGame_.getSentPokemon());
        assertNotNull(facadeGame_.getDisplayed());
    }

    @Test
    public void receive2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.initTrading();
        facadeGame_.setIndexTeamTrading(0);
        assertTrue(facadeGame_.isSelectedIndexTeamTrading());
        PokemonPlayer pokemonPlayer_ = newPokemonPlayer("UNKNOWN", STATIK, Gender.NO_GENDER, PP_PLUS);
        facadeGame_.receivePokemonPlayer(pokemonPlayer_);
        assertEq(2,facadeGame_.getPlayer().getPokemonPlayerList().size());
    }
    @Test
    public void tradeTest() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.initTrading();
        facadeGame_.setIndexTeamTrading(-1);
        facadeGame_.setIndexTeamTrading(0);
        PokemonPlayer pokemonPlayer_ = newPokemonPlayer(MELOFEE, STATIK, Gender.NO_GENDER, PP_PLUS);
        facadeGame_.receivePokemonPlayer(pokemonPlayer_);
        facadeGame_.applyTrading();
        assertEq(2,facadeGame_.getPlayer().getPokemonPlayerList().size());
        facadeGame_.closeTrading();
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
