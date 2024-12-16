package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import org.junit.Test;

public final class FacadeGameFishTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame_.setupMovingHeros();
        facadeGame_.directInteraction();
        facadeGame_.interact();
        return facadeGame_;
    }

    @Test
    public void act1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.initFishing();
        assertTrue(!facadeGame_.isErrorFight());
        assertTrue(facadeGame_.getFight().getFightType().isExisting());
        assertTrue(facadeGame_.getFight().getFightType().isWild());
    }

    @Test
    public void act2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.attract();
        assertTrue(!facadeGame_.isErrorFight());
        assertTrue(facadeGame_.getFight().getFightType().isExisting());
        assertTrue(facadeGame_.getFight().getFightType().isWild());
    }
}
