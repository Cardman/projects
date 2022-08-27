package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
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
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.TreeMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FacadeGameFightKoThreeTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(TARINOR);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, LIRE_ESPRIT, data_);
        pkPl_.setHappiness((short) 120);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        return facadeGame_;
    }

    @Test
    public void act1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(SEISME);
        facadeGame_.roundAllThrowers(false);
        facadeGame_.deselect();
        facadeGame_.choosePokemonForLearningAndEvolving((byte) 0);
        facadeGame_.setEvolution(TARINORME);
        facadeGame_.setAbility(MAGNEPIEGE);
        assertEq(0, facadeGame_.getChosenIndex());
        NatStringTreeMap<BoolVal> moves_ = facadeGame_.getMoves();
        assertEq(17, moves_.size());
        assertSame(BoolVal.FALSE,moves_.getVal(BOMBAIMANT));
        assertSame(BoolVal.FALSE,moves_.getVal(CAGE_ECLAIR));
        assertSame(BoolVal.FALSE,moves_.getVal(CHARGE));
        assertSame(BoolVal.FALSE,moves_.getVal(COUP_D_JUS));
        assertSame(BoolVal.FALSE,moves_.getVal(EBOULEMENT));
        assertSame(BoolVal.TRUE,moves_.getVal(ELECANON));
        assertSame(BoolVal.FALSE,moves_.getVal(GRAVITE));
        assertSame(BoolVal.TRUE,moves_.getVal(LAME_DE_ROC));
        assertSame(BoolVal.FALSE,moves_.getVal(LIRE_ESPRIT));
        assertSame(BoolVal.FALSE,moves_.getVal(MUR_DE_FER));
        assertSame(BoolVal.FALSE,moves_.getVal(RAYON_GEMME));
        assertSame(BoolVal.FALSE,moves_.getVal(REGARD_NOIR));
        assertSame(BoolVal.FALSE,moves_.getVal(REPOS));
        assertSame(BoolVal.TRUE,moves_.getVal(SEISME));
        assertSame(BoolVal.TRUE,moves_.getVal(TELLURIFORCE));
        assertSame(BoolVal.FALSE,moves_.getVal(TEMPETESABLE));
        assertSame(BoolVal.FALSE,moves_.getVal(VOL_MAGNETIK));
        TreeMap<String, BoolVal> evolutions_ = facadeGame_.getEvolutions();
        assertEq(2, evolutions_.size());
        assertSame(BoolVal.FALSE,evolutions_.getVal(NULL_REF));
        assertSame(BoolVal.TRUE,evolutions_.getVal(TARINORME));
        StringList abilities_ = facadeGame_.getAbilities();
        assertEq(2, abilities_.size());
        assertTrue(StringUtil.contains(abilities_, FERMETE));
        assertTrue(StringUtil.contains(abilities_, MAGNEPIEGE));
        assertEq(MAGNEPIEGE, facadeGame_.getAbility());
    }

    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }
    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }

}
