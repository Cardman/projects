package aiki.facade;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.map.DataMap;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Campaign;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import org.junit.Before;
import org.junit.Test;

public final class FacadeGameMetricsTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, diff_, data);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game = game_;
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame = facadeGame_;
    }

    @Test
    public void act1Test() {
        facadeGame.getMiniHeros();
        facadeGame.getBackHeros();
        facadeGame.getTranslatedBooleansCurLanguage();
        facadeGame.getTranslatedGendersCurLanguage();
        facadeGame.getTranslatedAbilitiesCurLanguage();
        facadeGame.translatePokemon(PIKACHU);
        facadeGame.translateItem(MASTER_BALL);
        facadeGame.translateStatus(POISON_ST);
        facadeGame.translateAbility(PRESSION);
        facadeGame.translateMove(JACKPOT);
        facadeGame.translateGenders(Gender.NO_GENDER);
        facadeGame.translateType(ELECTRIQUE);
        facadeGame.translatedTargets(TargetChoice.ANY_FOE);
        assertEq(11, facadeGame.getMapHeight());
        assertEq(1, facadeGame.getMapWidth());
        assertEq("R 1", facadeGame.getName(0,0));
        assertEq(0, facadeGame.getMaxWidthPk());
        assertEq(0, facadeGame.getMaxHeightPk());
        assertEq(0, facadeGame.getBackgroundImages().size());
        assertEq(0, facadeGame.getForegroundImages().size());
        assertEq(-1, facadeGame.getMiniMapCoords().getXcoords());
    }

    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

}
