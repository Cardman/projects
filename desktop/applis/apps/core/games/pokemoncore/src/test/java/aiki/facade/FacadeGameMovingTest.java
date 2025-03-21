package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import org.junit.Test;

public final class FacadeGameMovingTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, data_);
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
    public void move1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setupMovingHeros();
        facadeGame_.move(Direction.UP);
        assertEq(newCoords(1,0,0,5),facadeGame_.getGame().getPlayerCoords());
    }

    @Test
    public void move2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setupMovingHeros();
        facadeGame_.openMenu();
        facadeGame_.move(Direction.UP);
        assertEq(newCoords(0,0,0,0),facadeGame_.getGame().getPlayerCoords());
    }

}
