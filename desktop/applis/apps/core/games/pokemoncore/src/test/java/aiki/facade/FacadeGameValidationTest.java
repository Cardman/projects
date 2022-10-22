package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Test;

public final class FacadeGameValidationTest extends InitializationDataBase {

    public static FacadeGame initTests(Sex _s) {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, _s, game_.getDifficulty(), data_);
        return facade(data_, game_);
    }

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, game_.getDifficulty(), data_);
        return facade(data_, game_);
    }

    private static FacadeGame facade(DataBase _data, Game _game) {
        _game.setPlayerOrientation(Direction.UP);
        _game.getDifficulty().setRandomWildFight(false);
        _game.getPlayer().getItem(LAVA);
        Difficulty diff_ = _game.getDifficulty();
        _game.getPlayer().doRevivingFossil(LAVA, diff_, _data);
        PokemonPlayer pk_ = (PokemonPlayer) _game.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setGame(_game);
        facadeGame_.setData(_data);
        facadeGame_.setLanguage(LANGUAGE);
        return facadeGame_;
    }

    @Test
    public void validate1Test() {
        FacadeGame facadeGame_ = initTests(Sex.GIRL);
        assertTrue(facadeGame_.checkAndSetGame(facadeGame_.getGame()));
    }

    @Test
    public void validate2Test() {
        FacadeGame facadeGame_ = initTests(Sex.BOY);
        assertTrue(facadeGame_.checkAndSetGame(facadeGame_.getGame()));
    }

    @Test
    public void validate3Test() {
        FacadeGame facadeGame_ = initTests();
        assertTrue(!facadeGame_.checkAndSetGame(facadeGame_.getGame()));
    }
}
