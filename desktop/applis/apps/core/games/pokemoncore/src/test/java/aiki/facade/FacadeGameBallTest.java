package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.map.DataMap;
import aiki.map.levels.AbsAreaApparition;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Campaign;
import aiki.util.*;
import code.maths.LgInt;
import org.junit.Test;

public final class FacadeGameBallTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace( 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
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
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        assertTrue(facadeGame_.getFight().getFightType().isWild());
        facadeGame_.getGame().getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        facadeGame_.getGame().getFight().getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        facadeGame_.attemptCatchingWildPokemon(true);
        facadeGame_.roundUser();
        facadeGame_.endRoundFightBall();
        assertEq(LgInt.zero(), facadeGame_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(facadeGame_.getFight().getFightType().isWild());
        assertEq(FightState.ATTAQUES, facadeGame_.getFight().getState());
    }

}
