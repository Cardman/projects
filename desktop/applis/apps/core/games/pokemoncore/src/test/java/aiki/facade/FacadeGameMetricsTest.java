package aiki.facade;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.Game;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.enums.Sex;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import org.junit.Test;

public final class FacadeGameMetricsTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
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
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        return facadeGame_;
    }

    @Test
    public void act1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getMiniHeros();
        facadeGame_.getBackHeros();
        facadeGame_.getTranslatedBooleansCurLanguage();
        facadeGame_.getTranslatedGendersCurLanguage();
        facadeGame_.getTranslatedAbilitiesCurLanguage();
        facadeGame_.translatePokemon(PIKACHU);
        facadeGame_.translateItem(MASTER_BALL);
        facadeGame_.translateStatus(POISON_ST);
        facadeGame_.translateAbility(PRESSION);
        facadeGame_.translateMove(JACKPOT);
        facadeGame_.translateGenders(Gender.NO_GENDER);
        facadeGame_.translateType(ELECTRIQUE);
        facadeGame_.translatedTargets(TargetChoice.ANY_FOE);
        assertEq(11, facadeGame_.getMapHeight());
        assertEq(1, facadeGame_.getMapWidth());
        assertEq("R 1", facadeGame_.getName(0,0));
        assertEq(0, facadeGame_.getMaxWidthPk());
        assertEq(0, facadeGame_.getMaxHeightPk());
        assertEq(0, facadeGame_.getBackgroundImages().size());
        assertEq(0, facadeGame_.getForegroundImages().size());
        assertEq(-1, facadeGame_.getMiniMapCoords().getXcoords());
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
