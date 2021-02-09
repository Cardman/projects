package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.FightFacade;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.map.DataMap;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Campaign;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.Rate;
import org.junit.Test;

public final class FacadeGameFightKoTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 25);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data_);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        facadeGame_.setChosenTeamPokemon((short) 0);
        facadeGame_.switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        return facadeGame_;
    }

    @Test
    public void act1Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame_.roundAllThrowers(false);
        assertEq(FightState.APPRENDRE_EVOLUER, facadeGame_.getFight().getState());
        assertTrue(FightFacade.koTeam(facadeGame_.getFight()));
        assertTrue(facadeGame_.getFight().getFightType().isWild());
    }

    @Test
    public void act2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame_.roundAllThrowers(false);
        facadeGame_.learnAndEvolve();
        assertTrue(facadeGame_.isEnabledMovingHero());
    }

    @Test
    public void act3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(POKE_BALL);
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame_.roundAllThrowers(false);
        facadeGame_.learnAndEvolve();
        assertEq(FightState.CAPTURE_KO, facadeGame_.getFight().getState());
    }

    @Test
    public void act4Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(POKE_BALL);
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame_.roundAllThrowers(false);
        facadeGame_.learnAndEvolve();
        facadeGame_.catchKoWildPokemon(POKE_BALL,"WILD");
        assertTrue(facadeGame_.isEnabledMovingHero());
        assertEq(3,facadeGame_.getPlayer().getTeam().size());
    }

    @Test
    public void act5Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(POKE_BALL);
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame_.roundAllThrowers(false);
        facadeGame_.learnAndEvolve();
        facadeGame_.notCatchKoWildPokemon();
        assertTrue(facadeGame_.isEnabledMovingHero());
        assertEq(2,facadeGame_.getPlayer().getTeam().size());
    }

    @Test
    public void act6Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getPlayer().getItem(POKE_BALL);
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame_.roundAllThrowers(true);
        facadeGame_.roundUser();
        facadeGame_.endRoundFightBasic();
        facadeGame_.learnAndEvolve();
        facadeGame_.catchKoWildPokemon(POKE_BALL,"WILD");
        assertTrue(facadeGame_.isEnabledMovingHero());
        assertEq(3,facadeGame_.getPlayer().getTeam().size());
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
