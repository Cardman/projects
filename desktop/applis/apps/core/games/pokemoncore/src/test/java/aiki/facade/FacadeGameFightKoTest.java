package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.FightFacade;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.actions.ActionHeal;
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
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class FacadeGameFightKoTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, null, diff_, data);
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
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        facadeGame_.setChosenTeamPokemon((short) 0);
        facadeGame_.switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data);
        game = game_;
        facadeGame = facadeGame_;
    }

    @Test
    public void act1Test() {
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame.roundAllThrowers(false);
        assertEq(FightState.APPRENDRE_EVOLUER, facadeGame.getFight().getState());
        assertTrue(FightFacade.koTeam(facadeGame.getFight()));
        assertTrue(facadeGame.getFight().getFightType().isWild());
    }

    @Test
    public void act2Test() {
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame.roundAllThrowers(false);
        facadeGame.learnAndEvolve();
        assertTrue(facadeGame.isEnabledMovingHero());
    }

    @Test
    public void act3Test() {
        facadeGame.getPlayer().getItem(POKE_BALL);
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame.roundAllThrowers(false);
        facadeGame.learnAndEvolve();
        assertEq(FightState.CAPTURE_KO, facadeGame.getFight().getState());
    }

    @Test
    public void act4Test() {
        facadeGame.getPlayer().getItem(POKE_BALL);
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame.roundAllThrowers(false);
        facadeGame.learnAndEvolve();
        facadeGame.catchKoWildPokemon(POKE_BALL,"WILD");
        assertTrue(facadeGame.isEnabledMovingHero());
        assertEq(3,facadeGame.getPlayer().getTeam().size());
    }

    @Test
    public void act5Test() {
        facadeGame.getPlayer().getItem(POKE_BALL);
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame.roundAllThrowers(false);
        facadeGame.learnAndEvolve();
        facadeGame.notCatchKoWildPokemon();
        assertTrue(facadeGame.isEnabledMovingHero());
        assertEq(2,facadeGame.getPlayer().getTeam().size());
    }

    @Test
    public void act6Test() {
        facadeGame.getPlayer().getItem(POKE_BALL);
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame.roundAllThrowers(true);
        facadeGame.roundUser();
        facadeGame.endRoundFightBasic();
        facadeGame.learnAndEvolve();
        facadeGame.catchKoWildPokemon(POKE_BALL,"WILD");
        assertTrue(facadeGame.isEnabledMovingHero());
        assertEq(3,facadeGame.getPlayer().getTeam().size());
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
