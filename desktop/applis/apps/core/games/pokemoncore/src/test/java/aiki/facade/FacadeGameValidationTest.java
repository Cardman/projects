package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Test;

public final class FacadeGameValidationTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getPlayer().getItem(LAVA);
        game_.getPlayer().doRevivingFossil(LAVA, diff_, data_);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        pk_.setItem(PIERRE_LUNE);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setGame(game_);
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        return facadeGame_;
    }

    @Test
    public void validate1Test() {
        FacadeGame facadeGame_ = initTests();
        assertTrue(facadeGame_.checkAndSetGame(facadeGame_.getGame()));
    }

    @Test
    public void validate2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getGame().getPlayer().getTeam().clear();
        assertTrue(!facadeGame_.checkAndSetGame(facadeGame_.getGame()));
    }
}
