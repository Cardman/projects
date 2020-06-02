package aiki.game.fight;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import aiki.game.fight.actions.Action;
import org.junit.Before;
import org.junit.Test;

import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.util.MoveTarget;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.*;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class FightValidationTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }    @Test
    public void validAllyChoices1Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices2Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices3Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(NULL_REF, POKEMON_FOE_TARGET_ZERO));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices4Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(NULL_REF, POKEMON_FOE_TARGET_ZERO));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices5Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices6Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices7Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices8Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices9Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices10Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices11Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_TWO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices12Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_TWO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices13Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_TWO));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices14Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_TWO));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices15Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, new TargetCoords((short) 2,(short) 0)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices16Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, new TargetCoords((short) 2,(short) 0)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices17Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords((short) 2,(short) 0)), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices18Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords((short) 2,(short) 0)), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices19Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,Fighter.BACK)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices20Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,Fighter.BACK)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices21Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,Fighter.BACK)), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices22Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,Fighter.BACK)), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices23Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,(short) (Fighter.BACK-1))));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices24Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,(short) (Fighter.BACK-1))));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices25Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,(short) (Fighter.BACK-1))), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices26Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,(short) (Fighter.BACK-1))), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices27Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(BROUHAHA, POKEMON_FOE_TARGET_ZERO));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validAllyChoices28Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(BROUHAHA, POKEMON_FOE_TARGET_ZERO));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data));
    }
    @Test
    public void validPlacesSubst1Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.FOE, false));
    }
    @Test
    public void validPlacesSubst2Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.FOE, false));
    }
    @Test
    public void validPlacesSubst3Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.PLAYER, false));
    }
    @Test
    public void validPlacesSubst4Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.PLAYER, false));
    }
    @Test
    public void validPlacesSubst5Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.PLAYER, true));
    }
    @Test
    public void validPlacesSubst6Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.PLAYER, true));
    }
    @Test
    public void validPlacesSubst7Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.PLAYER, true));
    }
    @Test
    public void validPlacesSubst8Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.PLAYER, true));
    }
    @Test
    public void validPlacesSubst9Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).exitFrontBattleForBeingSubstitued();
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.PLAYER, false));
    }
    @Test
    public void validPlacesSubst10Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).exitFrontBattleForBeingSubstitued();
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.PLAYER, false));
    }
    @Test
    public void validPlaces1Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.FOE));
    }
    @Test
    public void validPlaces2Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.FOE));
    }
    @Test
    public void validPlaces3Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.PLAYER));
    }
    @Test
    public void validPlaces4Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.PLAYER));
    }
    @Test
    public void validPlaces5Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.PLAYER));
    }
    @Test
    public void validPlaces6Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.PLAYER));
    }
    @Test
    public void validPlaces7Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.PLAYER));
    }
    @Test
    public void validPlaces8Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.PLAYER));
    }
    @Test
    public void validSwitchTeam1Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.PLAYER));
    }
    @Test
    public void validSwitchTeam2Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.PLAYER));
    }
    @Test
    public void validSwitchTeam3Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.FOE));
    }
    @Test
    public void validSwitchTeam4Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.FOE));
    }
    @Test
    public void validSwitchTeam5Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validSwitchTeam(fight_, Fight.PLAYER));
    }
    @Test
    public void validSwitchTeam6Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validSwitchTeam(fight_, Fight.PLAYER));
    }
    @Test
    public void validSubstitutingTeam1Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam2Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam3Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam4Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam5Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam6Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam7Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam8Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam9Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
//        FightRound.beginRound(fight_, player_, diff_, data);
//        FightRound.roundUser(fight_, diff_, player_, data);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        game_.sendSubstitutes(data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam10Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
//        FightRound.beginRound(fight_, player_, diff_, data);
//        FightRound.roundUser(fight_, diff_, player_, data);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        game_.sendSubstitutes(data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam11Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
//        FightRound.beginRound(fight_, player_, diff_, data);
//        FightRound.roundUser(fight_, diff_, player_, data);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        game_.sendSubstitutes(data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam12Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
//        FightRound.beginRound(fight_, player_, diff_, data);
//        FightRound.roundUser(fight_, diff_, player_, data);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        game_.sendSubstitutes(data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam13Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data);
        game_.chooseBackFighter((byte) 0, data);
        game_.setChosenHealingItem(RAPPEL, data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, true);
        game_.roundUser(data);
        Fighter fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam14Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data);
        game_.chooseBackFighter((byte) 0, data);
        game_.setChosenHealingItem(RAPPEL, data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, true);
        game_.roundUser(data);
        Fighter fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam15Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data);
        game_.chooseBackFighter((byte) 0, data);
        game_.setChosenHealingItem(RAPPEL, data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, true);
        game_.roundUser(data);
        Fighter fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam16Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data);
        game_.chooseBackFighter((byte) 0, data);
        game_.setChosenHealingItem(RAPPEL, data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, true);
        game_.roundUser(data);
        Fighter fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam17Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam18Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam19Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam20Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam21Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam22Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam23Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam24Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam25Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam26Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam27Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam28Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam29Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)0, (byte)0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam30Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)0, (byte)0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam31Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte)3, (byte)1);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam32Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte)3, (byte)1);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam33Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte)3, (byte)2);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam34Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte)3, (byte)2);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam35Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)3, (byte)2);
        fight_.getFirstPositFoeFighters().put((byte)4, (byte)1);
        fight_.getFirstPositFoeFighters().put((byte)2, (byte)0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam36Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)3, (byte)2);
        fight_.getFirstPositFoeFighters().put((byte)4, (byte)1);
        fight_.getFirstPositFoeFighters().put((byte)2, (byte)0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam37Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)2, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam38Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)2, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam39Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.sendSubstitutes(data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, true);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), data);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam40Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.sendSubstitutes(data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, true);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), data);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }
    @Test
    public void validSubstitutingTeam41Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, true);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), data);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte)3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validSubstitutingTeam42Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, true);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), data);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte)3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }
    @Test
    public void validate1Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate2Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate3Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate4Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate5Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate6Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate7Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate8Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate9Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate10Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate11Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate12Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate13Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate14Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate15Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelAllyArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesAlly_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesAlly_.add(new StringMap<Short>());
        movesAlly_.add(new StringMap<Short>());
        movesAlly_.add(moves_);
        movesAlly_.add(new StringMap<Short>());
        replacePlayerMoves(fight_, movesAlly_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate16Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelAllyArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesAlly_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesAlly_.add(new StringMap<Short>());
        movesAlly_.add(new StringMap<Short>());
        movesAlly_.add(moves_);
        movesAlly_.add(new StringMap<Short>());
        replacePlayerMoves(fight_, movesAlly_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate17Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
//        cancelAllyArtificialIntelligence(fight_);
//        CustList<Map<String, Short>> movesAlly_ = new CustList<>();
//        Map<String, Short> moves_ = new Map<String,Short>();
//        moves_.put(BROUHAHA, (short) 10);
//        movesAlly_.add(moves_);
//        movesAlly_.add(new Map<String, Short>());
//        movesAlly_.add(new Map<String, Short>());
//        movesAlly_.add(new Map<String, Short>());
//        replacePlayerMoves(fight_, movesAlly_);
        //FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate18Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
//        cancelAllyArtificialIntelligence(fight_);
//        CustList<Map<String, Short>> movesAlly_ = new CustList<>();
//        Map<String, Short> moves_ = new Map<String,Short>();
//        moves_.put(BROUHAHA, (short) 10);
//        movesAlly_.add(moves_);
//        movesAlly_.add(new Map<String, Short>());
//        movesAlly_.add(new Map<String, Short>());
//        movesAlly_.add(new Map<String, Short>());
//        replacePlayerMoves(fight_, movesAlly_);
        //FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate19Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate20Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate21Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate22Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate23Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate24Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate25Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate26Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate27Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate28Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate29Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.sendSubstitutes(data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate30Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.sendSubstitutes(data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate31Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.sendSubstitutes(data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate32Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.sendSubstitutes(data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate33Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate34Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate35Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(MUNJA);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate36Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(MUNJA);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate37Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate38Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate39Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual5(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate40Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual5(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate41Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate42Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate43Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate44Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate45Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data);
        assertTrue(FightFacade.validate(game_.getFight(), data, game_.getPlayer(), game_.getDifficulty()));
    }
    @Test
    public void validate46Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data);
        assertTrue(FightFacade.validate(game_.getFight(), data, game_.getPlayer(), game_.getDifficulty()));
    }
    @Test
    public void validate47Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data);
        game_.chooseBackFighter((byte) 0, data);
        game_.setChosenHealingItem(RAPPEL, data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, true);
        game_.roundUser(data);
        Fighter fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data);
        assertTrue(FightFacade.validate(game_.getFight(), data, game_.getPlayer(), game_.getDifficulty()));
//        FightFacade.chooseBackFighter(game_.getFight(), (byte) 0, data);
//        FightFacade.setSubstituteBack(game_.getFight(), (byte) 1);
    }
    @Test
    public void validate48Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data);
        game_.chooseBackFighter((byte) 0, data);
        game_.setChosenHealingItem(RAPPEL, data);
        game_.chooseFrontFighter((byte) 0, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, true);
        game_.roundUser(data);
        Fighter fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data);
        assertTrue(FightFacade.validate(game_.getFight(), data, game_.getPlayer(), game_.getDifficulty()));
//        FightFacade.chooseBackFighter(game_.getFight(), (byte) 0, data);
//        FightFacade.setSubstituteBack(game_.getFight(), (byte) 1);
    }
    @Test
    public void validate49Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate50Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate51Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate52Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate53Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual8(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate54Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual8(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate55Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate56Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate57Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate58Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate59Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //Never mind for the nickname
        game_.catchKoWildPokemon(MASTER_BALL, NICKNAME, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate60Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //Never mind for the nickname
        game_.catchKoWildPokemon(MASTER_BALL, NICKNAME, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate61Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate62Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate63Test(){
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate64Test(){
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate65Test(){
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(JACKPOT, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget((short) 2));
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate66Test(){
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(JACKPOT, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget((short) 2));
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate67Test(){
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(JACKPOT, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget((short) -1));
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate68Test(){
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(JACKPOT, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget((short) -1));
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate69Test(){
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(JACKPOT, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setSubstitute((byte) 2);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate70Test(){
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(JACKPOT, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setSubstitute((byte) 2);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate71Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate72Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate73Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate74Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate75Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE).setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        Player player_ = game_.getPlayer();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate76Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE).setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        Player player_ = game_.getPlayer();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate77Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.SWITCH, data);
        game_.chooseBackFighter((byte) 0, data);
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate78Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.SWITCH, data);
        game_.chooseBackFighter((byte) 0, data);
        assertTrue(FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate79Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.SWITCH, data);
        game_.chooseBackFighter((byte) 0, data);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate80Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.SWITCH, data);
        game_.chooseBackFighter((byte) 0, data);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate81Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.SWITCH, data);
        game_.chooseBackFighter((byte) 0, data);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate82Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.SWITCH, data);
        game_.chooseBackFighter((byte) 0, data);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate83Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        game_.initTrainerFight(data);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, game_.getPlayer(), game_.getDifficulty()));
    }
    @Test
    public void validate84Test(){
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data);
        //[1;5,1;0_4,1, 3;4,1;0_4,1, 2;0_4,0, 5;0_2,0, 2;0_2,0, 6;0_4,8]
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(PIKACHU);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        game_.initTrainerFight(data);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, game_.getPlayer(), game_.getDifficulty()));
    }
    @Test
    public void validate85Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate86Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate87Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 0);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate88Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 0);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate89Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate90Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate91Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate92Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate93Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate94Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate95Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setPlayerMaxNumberFrontFighters((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate96Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setPlayerMaxNumberFrontFighters((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate97Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getTeams().removeKey(Fight.FOE);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate98Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getTeams().removeKey(Fight.FOE);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate99Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getTeams().removeKey(Fight.PLAYER);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate100Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getTeams().removeKey(Fight.PLAYER);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate101Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        //invalid data
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate102Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        //invalid data
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate103Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setNbFleeAttempt((short) -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate104Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setNbFleeAttempt((short) -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate105Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setNbRounds(new LgInt("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate106Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setNbRounds(new LgInt("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate107Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setWinningMoney(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate108Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setWinningMoney(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate109Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getCaughtEvolutions().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate110Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getCaughtEvolutions().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate111Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getLostObjects().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate112Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getLostObjects().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate113Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getUsedItemsWhileRound().put(INVALID_DATA_KEY, (short) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate114Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getUsedItemsWhileRound().put(INVALID_DATA_KEY, (short) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate115Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        player_.getItem(BAIE_ORAN);
        //invalid data
        game_.getFight().getUsedItemsWhileRound().put(BAIE_ORAN, (short) -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate116Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        player_.getItem(BAIE_ORAN);
        //invalid data
        game_.getFight().getUsedItemsWhileRound().put(BAIE_ORAN, (short) -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate117Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setWeight(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate118Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setWeight(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate119Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        player_.setChosenTeamPokemon((short) 1);
        player_.switchTeamOrder((short) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate120Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        player_.setChosenTeamPokemon((short) 1);
        player_.switchTeamOrder((short) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate121Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getUserTeam().getPlayerFightersAgainstFoe().put((byte) 2, new Bytes());
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate122Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getUserTeam().getPlayerFightersAgainstFoe().put((byte) 2, new Bytes());
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate123Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate124Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate125Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 4, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate126Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 4, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate127Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 1, (byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate128Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 1, (byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate129Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 3, (byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate130Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 3, (byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate131Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate132Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate133Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getStillEnabledMoves().put(GRAVITE, false);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate134Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getStillEnabledMoves().put(GRAVITE, false);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate135Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getEnabledMoves().getVal(GRAVITE).setNbTurn((short) -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate136Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getEnabledMoves().getVal(GRAVITE).setNbTurn((short) -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate137Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate138Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate139Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate140Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate141Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setSubstitute((byte) 2);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate142Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setSubstitute((byte) 2);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ONE).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate143Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate144Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate145Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate146Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate147Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlace((byte) 1);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate148Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlace((byte) 1);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate149Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate150Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate151Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate152Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate153Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.setCurrentUser(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
        //invalid data
    }
    @Test
    public void validate154Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.setCurrentUser(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
        //invalid data
    }
    @Test
    public void validate155Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        ActionMove actionMove_ = (ActionMove) fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        actionMove_.setFinalChosenMove(CHARGE);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate156Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        ActionMove actionMove_ = (ActionMove) fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        actionMove_.setFinalChosenMove(CHARGE);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate157Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setActed(false);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate158Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setActed(false);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate159Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ONE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).choisirAttaqueFin();
        fight_.setCurrentUser(POKEMON_PLAYER_FIGHTER_TWO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setActed(true);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate160Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ONE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).choisirAttaqueFin();
        fight_.setCurrentUser(POKEMON_PLAYER_FIGHTER_TWO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setActed(true);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate161Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ONE), new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
        //invalid data
    }
    @Test
    public void validate162Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ONE), new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
        //invalid data
    }
    @Test
    public void validate163Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //invalid data
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ONE), new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
        //invalid data
    }
    @Test
    public void validate164Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //invalid data
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ONE), new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
        //invalid data
    }
    @Test
    public void validate165Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate166Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate167Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getMovesAbilitiesEvos().clear();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getMovesToBeLearnt().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate168Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getMovesAbilitiesEvos().clear();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getMovesToBeLearnt().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate169Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().getVal((byte) 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate170Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().getVal((byte) 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate171Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        //invalid data
        fight_.getChoices().getVal((byte) 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate172Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        //invalid data
        fight_.getChoices().getVal((byte) 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate173Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().getVal((byte) 0).setAbility(PRESSION);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate174Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().getVal((byte) 0).setAbility(PRESSION);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate175Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        fight_.getKos().put(Fight.FOE, false);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate176Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        fight_.getKos().put(Fight.FOE, false);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate177Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate178Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate179Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.setState(FightState.ATTAQUES);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate180Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.setState(FightState.ATTAQUES);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate181Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.setState(FightState.SWITCH_PROPOSE);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate182Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.setState(FightState.SWITCH_PROPOSE);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate183Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate184Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate185Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        fight_.getKos().put(Fight.FOE, false);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate186Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        fight_.getKos().put(Fight.FOE, false);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate187Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate188Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate189Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.getKos().put(Fight.FOE, true);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate190Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.getKos().put(Fight.FOE, true);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate191Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate192Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate193Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 0, (byte) 0);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate194Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(JACKPOT, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data);
        Fighter fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT,(short) 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(POKEMON_FOE_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, data);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, data);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 0, (byte) 0);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate195Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate196Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate197Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 0, (byte) 0);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate198Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 0, (byte) 0);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate199Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getKos().put(Fight.FOE, true);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate200Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getKos().put(Fight.FOE, true);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate201Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
        //fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
//        game_.chooseFrontFighter((byte) 0, data);
//        game_.changeAction(ActionType.MOVE, data);
//        game_.chooseMove(DEMI_TOUR, data);
//        game_.setFirstChosenMoveFoeTarget((byte) 0);
//        game_.roundAllThrowers(data, false);

    }
    @Test
    public void validate202Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
        //fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
//        game_.chooseFrontFighter((byte) 0, data);
//        game_.changeAction(ActionType.MOVE, data);
//        game_.chooseMove(DEMI_TOUR, data);
//        game_.setFirstChosenMoveFoeTarget((byte) 0);
//        game_.roundAllThrowers(data, false);

    }
    @Test
    public void validate203Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        //invalid data
        fight_.setCatchingBall(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate204Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        //invalid data
        fight_.setCatchingBall(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate205Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        //invalid data
        fight_.setCatchingBall(BAIE_ORAN);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate206Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        //invalid data
        fight_.setCatchingBall(BAIE_ORAN);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate207Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate208Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate209Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        //invalid data
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate210Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, data, true);
        //invalid data
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate211Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate212Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate213Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        player_.useInInventory(MASTER_BALL);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate214Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        player_.useInInventory(MASTER_BALL);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate215Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate216Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate217Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.setState(FightState.REDESSIN_SCENE);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate218Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.setState(FightState.REDESSIN_SCENE);
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate219Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().getVal((byte) 0).setName(PIKACHU);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate220Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(BROUHAHA, data);
        game_.roundAllThrowers(data, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, data);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().getVal((byte) 0).setName(PIKACHU);
        assertTrue(!FightFacade.validate(game_.getFight(), data, player_, diff_));
    }
    @Test
    public void validate221Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setAction(new Action());
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate222Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setAction(new Action());
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate223Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.setCurrentUser(Fight.toUserFighter((byte) 6));
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    @Test
    public void validate224Test(){
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        addBackFoeFighter(pk_, game_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, data);
        game_.changeAction(ActionType.MOVE, data);
        game_.chooseMove(DEMI_TOUR, data);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data, false);
        //invalid data
        fight_.setCurrentUser(Fight.toUserFighter((byte) 6));
        assertTrue(!FightFacade.validate(fight_, data, player_, diff_));
    }
    private void addBackFoeFighter(PkTrainer _pk, Game _game) {
        Fight fight_ = _game.getFight();
        Fighter fighter_ = new Fighter(_pk, data, Fighter.BACK);
        fighter_.initIvAdv(_game.getDifficulty(),NULL_REF);
        ByteMap<Fighter> team_ = fight_.getFoeTeam().getMembers();
        TeamPosition key_ = Fight.toFoeFighter((byte) team_.size());
        fight_.getFirstPositFoeFighters().put((byte) team_.size(), Fighter.BACK);
        team_.put((byte) team_.size(), fighter_);
        EqList<TeamPosition> fs_ = FightOrder.fighters(fight_);
        fighter_.initCreatureRelationsAutre(fs_, data);
        for (TeamPosition t: fs_) {
            fight_.getFighter(t).ajouterRelationAutre(key_, data);
        }
    }

    private void cancelFoeArtificialIntelligence(Fight _fight) {
        for (TeamPosition t: FightOrder.fighters(_fight)) {
            _fight.getFighter(t).cancelActions();
        }
    }

    private void cancelAllyArtificialIntelligence(Fight _fight) {
        _fight.getAllyChoice().clear();
    }

    private void replaceFoeMoves(Fight _fight, CustList<StringMap<Short>> _moves) {
        byte i_ = CustList.FIRST_INDEX;
        for (StringMap<Short> m: _moves) {
            if (m.isEmpty()) {
                i_++;
                continue;
            }
            Fighter f_ = _fight.getFoeTeam().getMembers().getVal(i_);
            f_.getMoves().clear();
            f_.getCurrentMoves().clear();
            for (String k: m.getKeys()) {
                f_.getMoves().put(k, new UsesOfMove(m.getVal(k)));
                f_.getCurrentMoves().put(k, new UsesOfMove(m.getVal(k)));
            }
            i_++;
        }
    }

    private void replacePlayerMoves(Fight _fight, CustList<StringMap<Short>> _moves) {
        byte i_ = CustList.FIRST_INDEX;
        for (StringMap<Short> m: _moves) {
            if (m.isEmpty()) {
                i_++;
                continue;
            }
            Fighter f_ = _fight.getUserTeam().getMembers().getVal(i_);
            f_.getMoves().clear();
            f_.getCurrentMoves().clear();
            for (String k: m.getKeys()) {
                f_.getMoves().put(k, new UsesOfMove(m.getVal(k)));
                f_.getCurrentMoves().put(k, new UsesOfMove(m.getVal(k)));
            }
            i_++;
        }
    }

    private Game newGameInFightTrainerDual8(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainerDual7(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainerDual5(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        Pokemon pk_ = new WildPk();
        pk_.setName(TETARTE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 26);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, DANSE_PLUIE, data);
//        pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        pkPl_.getWonExpSinceLastLevel().addNb(new Rate("49"));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainerDual4(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, COGNOBIDON, data);
//        pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainerDual3(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, FAUX_CHAGE, data);
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainerDual2(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, HATE, data);
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainerDual1(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainerDual6(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(DEMI_TOUR, FAUX_CHAGE, data);
        //game_.getPlayerOrientation() == UP
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainer4(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.getPlayer().getItem(RAPPEL);
        game_.setPlayerCoords(newCoords(9, 0, 1, 0));
        game_.setPlayerOrientation(Direction.DOWN);

        //game_.getPlayerOrientation() == UP
//        game_.moving(Direction.DOWN, data);
//        game_.moving(Direction.DOWN, data);
//        game_.moving(Direction.RIGHT, data);
//        game_.moving(Direction.RIGHT, data);
//        game_.moving(Direction.DOWN, data);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainer3(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 2);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, HATE, data);
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainer2(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        pkPl_.learnMove(BROUHAHA, HATE, data);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFightTrainer1(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.DOWN, data);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(data);
        return game_;
    }

    private Game newGameInFight3(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(MASTER_BALL);
        game_.getPlayer().getItem(REPOUSSE);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        pkPl_.learnMove(BROUHAHA, HATE, data);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        return game_;
    }

    private Game newGameInFight2(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        game_.getPlayer().getItem(MASTER_BALL);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        return game_;
    }

    private Game newGameInFight(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(data);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.DOWN, data);
        game_.moving(Direction.RIGHT, data);
        game_.moving(Direction.RIGHT, data);
        return game_;
    }}