package aiki.game.fight;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

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
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
public class FightValidationTest extends InitializationDataBase {

    private static final String SEX = "sex";

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
    }

    Object[] sex() {
        return $($(Sex.GIRL),$(Sex.BOY));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices1Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices2Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(NULL_REF, POKEMON_FOE_TARGET_ZERO));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices3Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices4Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices5Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices6Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_TWO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices7Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_TWO));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices8Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, new TargetCoords((short) 2,(short) 0)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices9Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords((short) 2,(short) 0)), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices10Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,Fighter.BACK)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices11Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,Fighter.BACK)), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices12Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE), new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,(short) (Fighter.BACK-1))));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices13Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.PLAYER,(short) (Fighter.BACK-1))), new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validAllyChoices14Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO), new MoveTarget(BROUHAHA, POKEMON_FOE_TARGET_ZERO));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), _data_));
    }

    @Test
    @Parameters(method=SEX)
    public void validPlacesSubst1Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.FOE, false));
    }

    @Test
    @Parameters(method=SEX)
    public void validPlacesSubst2Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.PLAYER, false));
    }

    @Test
    @Parameters(method=SEX)
    public void validPlacesSubst3Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.PLAYER, true));
    }

    @Test
    @Parameters(method=SEX)
    public void validPlacesSubst4Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.PLAYER, true));
    }

    @Test
    @Parameters(method=SEX)
    public void validPlacesSubst5Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).exitFrontBattleForBeingSubstitued();
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.PLAYER, false));
    }

    @Test
    @Parameters(method=SEX)
    public void validPlaces1Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.FOE));
    }

    @Test
    @Parameters(method=SEX)
    public void validPlaces2Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.PLAYER));
    }

    @Test
    @Parameters(method=SEX)
    public void validPlaces3Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.PLAYER));
    }

    @Test
    @Parameters(method=SEX)
    public void validPlaces4Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.PLAYER));
    }

    @Test
    @Parameters(method=SEX)
    public void validSwitchTeam1Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.PLAYER));
    }

    @Test
    @Parameters(method=SEX)
    public void validSwitchTeam2Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.FOE));
    }

    @Test
    @Parameters(method=SEX)
    public void validSwitchTeam3Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(_sex, diff_);
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validSwitchTeam(fight_, Fight.PLAYER));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam1Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam2Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam3Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam4Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam5Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
//        FightRound.beginRound(fight_, player_, diff_, data);
//        FightRound.roundUser(fight_, diff_, player_, data);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        game_.sendSubstitutes(_data_);
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam6Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
//        FightRound.beginRound(fight_, player_, diff_, data);
//        FightRound.roundUser(fight_, diff_, player_, data);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data);
        game_.sendSubstitutes(_data_);
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam7Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, game_.getDifficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(_data_);
        game_.chooseBackFighter((byte) 0, _data_);
        game_.setChosenHealingItem(RAPPEL, _data_);
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, true);
        game_.roundUser(_data_);
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
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), _data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam8Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, game_.getDifficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(_data_);
        game_.chooseBackFighter((byte) 0, _data_);
        game_.setChosenHealingItem(RAPPEL, _data_);
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, true);
        game_.roundUser(_data_);
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
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), _data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam9Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam10Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam11Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam12Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam13Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam14Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam15Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)0, (byte)0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam16Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte)3, (byte)1);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam17Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel((short) 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte)3, (byte)2);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam18Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)3, (byte)2);
        fight_.getFirstPositFoeFighters().put((byte)4, (byte)1);
        fight_.getFirstPositFoeFighters().put((byte)2, (byte)0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam19Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)2, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam20Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.sendSubstitutes(_data_);
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, true);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_TWO, diff_, _data_);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), _data_);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte)3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.FOE)));
    }

    @Test
    @Parameters(method=SEX)
    public void validSubstitutingTeam21Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, true);
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_TWO, diff_, _data_);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), _data_);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte)3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.PLAYER)));
    }

    @Test
    @Parameters(method=SEX)
    public void validate1Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate2Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(_sex, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate3Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate4Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate5Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate6Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setSubstituteForMove((byte) 1);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate7Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate8Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
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
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate9Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate10Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate11Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate12Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate13Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate14Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(DEMI_TOUR, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate15Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.sendSubstitutes(_data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate16Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.sendSubstitutes(_data_);
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate17Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate18Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(MUNJA);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate19Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate20Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual5(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate21Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }


    @Test
    @Parameters(method=SEX)
    public void validate22Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate23Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, game_.getDifficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(_data_);
        assertTrue(FightFacade.validate(game_.getFight(), _data_, game_.getPlayer(), game_.getDifficulty()));
    }

    @Test
    @Parameters(method=SEX)
    public void validate24Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, game_.getDifficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(_data_);
        game_.chooseBackFighter((byte) 0, _data_);
        game_.setChosenHealingItem(RAPPEL, _data_);
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, true);
        game_.roundUser(_data_);
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
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), _data_);
        assertTrue(FightFacade.validate(game_.getFight(), _data_, game_.getPlayer(), game_.getDifficulty()));
//        FightFacade.chooseBackFighter(game_.getFight(), (byte) 0, data);
//        FightFacade.setSubstituteBack(game_.getFight(), (byte) 1);
    }

    @Test
    @Parameters(method=SEX)
    public void validate25Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate26Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(_sex, diff_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate27Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual8(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate28Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, _data_, true);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate29Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate30Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //Never mind for the nickname
        game_.catchKoWildPokemon(MASTER_BALL, NICKNAME, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate31Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate32Test(Sex _sex) {
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(BROUHAHA, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate33Test(Sex _sex) {
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(JACKPOT, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget((short) 2));
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate34Test(Sex _sex) {
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Short>> movesFoe_ = new CustList<StringMap<Short>>();
        StringMap<Short> moves_ = new StringMap<Short>();
        moves_.put(JACKPOT, (short) 10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Short>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget((short) -1));
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate35Test(Sex _sex) {
        //newGameInFightTrainerDual1
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
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
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, _data_);
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setSubstitute((byte) 2);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate36Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        assertTrue(FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate37Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate38Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE).setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        Player player_ = game_.getPlayer();
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate39Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.SWITCH, _data_);
        game_.chooseBackFighter((byte) 0, _data_);
        assertTrue(FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate40Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.SWITCH, _data_);
        game_.chooseBackFighter((byte) 0, _data_);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate41Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.SWITCH, _data_);
        game_.chooseBackFighter((byte) 0, _data_);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate42Test(Sex _sex) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, game_.getDifficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.initTrainerFight(_data_);
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, game_.getPlayer(), game_.getDifficulty()));
    }

    @Test
    @Parameters(method=SEX)
    public void validate43Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate44Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 0);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate45Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate46Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate47Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setMult((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate48Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setPlayerMaxNumberFrontFighters((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate49Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getTeams().removeKey(Fight.FOE);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate50Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getTeams().removeKey(Fight.PLAYER);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate51Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
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
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate52Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setNbFleeAttempt((short) -1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate53Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setNbRounds(new LgInt("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate54Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().setWinningMoney(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate55Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getCaughtEvolutions().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate56Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getLostObjects().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate57Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getUsedItemsWhileRound().put(INVALID_DATA_KEY, (short) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate58Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        player_.getItem(BAIE_ORAN);
        //invalid data
        game_.getFight().getUsedItemsWhileRound().put(BAIE_ORAN, (short) -1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate59Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setWeight(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate60Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(_sex, diff_);
        Player player_ = game_.getPlayer();
        //invalid data
        player_.setChosenTeamPokemon((short) 1);
        player_.switchTeamOrder((short) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate61Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getUserTeam().getPlayerFightersAgainstFoe().put((byte) 2, new Numbers<Byte>());
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate62Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate63Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 4, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate64Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 1, (byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate65Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 3, (byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate66Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate67Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getStillEnabledMoves().put(GRAVITE, false);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate68Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getEnabledMoves().getVal(GRAVITE).setNbTurn((short) -1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate69Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlace((byte) 0);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setGroundPlaceSubst((byte) 0);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate70Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).setGroundPlace((byte) 1);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_THREE).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate71Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate72Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setSubstitute((byte) 2);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).setSubstitute((byte) 2);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate73Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlace((byte) 1);
        //fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate74Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlace((byte) 1);
        //fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate75Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate76Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getFighter(POKEMON_FOE_FIGHTER_ZERO).setGroundPlaceSubst((byte) 1);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate77Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.setCurrentUser(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
        //invalid data
    }

    @Test
    @Parameters(method=SEX)
    public void validate78Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        ActionMove actionMove_ = (ActionMove) fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        actionMove_.setFinalChosenMove(CHARGE);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }


    @Test
    @Parameters(method=SEX)
    public void validate79Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setActed(false);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate80Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ONE);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).choisirAttaqueFin();
        fight_.setCurrentUser(POKEMON_PLAYER_FIGHTER_TWO);
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_TWO).setActed(true);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate81Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ONE), new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
        //invalid data
    }

    @Test
    @Parameters(method=SEX)
    public void validate82Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //invalid data
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ONE), new MoveTarget(INVALID_DATA_KEY, POKEMON_FOE_TARGET_ONE));
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
        //invalid data
    }

    @Test
    @Parameters(method=SEX)
    public void validate83Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate84Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getMovesAbilitiesEvos().clear();
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getMovesToBeLearnt().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate85Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().getVal((byte) 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate86Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        //invalid data
        fight_.getChoices().getVal((byte) 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }


    @Test
    @Parameters(method=SEX)
    public void validate87Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getChoices().getVal((byte) 0).setAbility(PRESSION);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate88Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        fight_.getKos().put(Fight.FOE, false);
        assertTrue(!FightFacade.validate(game_.getFight(), _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate89Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate90Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        //invalid data
        fight_.setState(FightState.ATTAQUES);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate91Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.setState(FightState.SWITCH_PROPOSE);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate92Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate93Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        fight_.getKos().put(Fight.FOE, false);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate94Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        //invalid data
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate95Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        //invalid data
        fight_.getKos().put(Fight.FOE, true);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate96Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate97Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(JACKPOT, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, player_, diff_, _data_);
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
        FightKo.setKoMoveTeams(fight_, POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        FightKo.setKoMoveTeams(fight_, POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, _data_);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 0, (byte) 0);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate98Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFirstPositPlayerFighters().put((byte) 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put((byte) 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate99Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFirstPositFoeFighters().put((byte) 0, (byte) 0);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate100Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getKos().put(Fight.FOE, true);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate101Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(_sex, diff_);
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
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getFighter(POKEMON_PLAYER_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
        //fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
//        game_.chooseFrontFighter((byte) 0, data);
//        game_.changeAction(ActionType.MOVE, data);
//        game_.chooseMove(DEMI_TOUR, data);
//        game_.setFirstChosenMoveFoeTarget((byte) 0);
//        game_.roundAllThrowers(data, false);

    }

    @Test
    @Parameters(method=SEX)
    public void validate102Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, _data_, true);
        //invalid data
        fight_.setCatchingBall(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate103Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, _data_, true);
        //invalid data
        fight_.setCatchingBall(BAIE_ORAN);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate104Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, _data_, true);
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate105Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.attemptCatchingWildPokemon(MASTER_BALL, _data_, true);
        //invalid data
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate106Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate107Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        player_.useInInventory(MASTER_BALL);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate108Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.changeAction(ActionType.MOVE, _data_);
        game_.chooseMove(BROUHAHA, _data_);
        game_.roundAllThrowers(_data_, false);
        //invalid data
        fight_.getKos().put(Fight.PLAYER, true);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    @Test
    @Parameters(method=SEX)
    public void validate109Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(_sex, diff_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data
        fight_.setState(FightState.REDESSIN_SCENE);
        assertTrue(!FightFacade.validate(fight_, _data_, player_, diff_));
    }

    /*Difficulty diff_ = new Difficulty();

    */
    //
    private static void addBackFoeFighter(PkTrainer _pk, Game _game) {
        Fight fight_ = _game.getFight();
        Fighter fighter_ = new Fighter(_pk, _data_, Fighter.BACK);
        fighter_.initIvAdv(_game.getDifficulty(),NULL_REF);
        NumberMap<Byte,Fighter> team_ = fight_.getFoeTeam().getMembers();
        TeamPosition key_ = Fight.toFoeFighter((byte) team_.size());
        fight_.getFirstPositFoeFighters().put((byte) team_.size(), Fighter.BACK);
        team_.put((byte) team_.size(), fighter_);
        EqList<TeamPosition> fs_ = FightOrder.fighters(fight_);
        fighter_.initCreatureRelationsAutre(fs_, _data_);
        for (TeamPosition t: fs_) {
            fight_.getFighter(t).ajouterRelationAutre(key_, _data_);
        }
    }

    private static void cancelFoeArtificialIntelligence(Fight _fight) {
        for (TeamPosition t: FightOrder.fighters(_fight)) {
            _fight.getFighter(t).cancelActions();
        }
    }

    private static void cancelAllyArtificialIntelligence(Fight _fight) {
        _fight.getAllyChoice().clear();
    }

    private static void replaceFoeMoves(Fight _fight, CustList<StringMap<Short>> _moves) {
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

    private static void replacePlayerMoves(Fight _fight, CustList<StringMap<Short>> _moves) {
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

    private static Game newGameInFightTrainerDual8(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainerDual7(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainerDual5(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(TETARTE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 26);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, DANSE_PLUIE, _data_);
//        pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        pkPl_.getWonExpSinceLastLevel().addNb(new Rate("49"));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainerDual4(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, COGNOBIDON, _data_);
//        pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainerDual3(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, FAUX_CHAGE, _data_);
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainerDual2(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, HATE, _data_);
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainerDual1(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainerDual6(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(DEMI_TOUR, FAUX_CHAGE, _data_);
        //game_.getPlayerOrientation() == UP
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainer4(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
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
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainer3(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 2);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, HATE, _data_);
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainer2(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        pkPl_.learnMove(BROUHAHA, HATE, _data_);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFightTrainer1(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.DOWN, _data_);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(_data_);
        return game_;
    }

    private static Game newGameInFight3(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(MASTER_BALL);
        game_.getPlayer().getItem(REPOUSSE);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove((short)10));
        pkPl_.learnMove(BROUHAHA, HATE, _data_);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        return game_;
    }

    private static Game newGameInFight2(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        game_.getPlayer().getItem(MASTER_BALL);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        return game_;
    }

    private static Game newGameInFight(Sex _sex, Difficulty _diff) {
        Game game_ = new Game(_data_);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data_);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.DOWN, _data_);
        game_.moving(Direction.RIGHT, _data_);
        game_.moving(Direction.RIGHT, _data_);
        return game_;
    }

}
