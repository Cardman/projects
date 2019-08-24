package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.Fighter;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.actions.Action;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import org.junit.Before;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class FacadeGameFightKoTwoTest extends InitializationDataBase {

    private DataBase data;
    private Game game;
    private FacadeGame facadeGame;
    @Before
    public void initTests() {
        data = initDb();
        Game game_ = new Game(data);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 25);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.initTrainerFight(data);
        game = game_;
        FacadeGame facadeGame_ = new FacadeGame();
        facadeGame_.setData(data);
        facadeGame_.setLanguage(LANGUAGE);
        facadeGame_.setGame(game_);
        facadeGame = facadeGame_;
    }

    @Test
    public void act1Test() {
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame.roundAllThrowers(false);
        assertTrue(facadeGame.isChosableForLearningAndEvolving((byte) 0));
        facadeGame.choosePokemonForLearningAndEvolving((byte) 0);
        assertEq(2,facadeGame.getEvolutions().size());
        assertEq(1,facadeGame.getAbilities().size());
        assertEq(NULL_REF,facadeGame.getAbility());
        facadeGame.setEvolution(TETARTE);
        assertEq(10,facadeGame.getMoves().size());
        facadeGame.addOrForgetMove(BULLES_D_O);
        facadeGame.addOrForgetMove(ECUME);
        facadeGame.learnAndEvolve();
        assertEq(TETARTE, facadeGame.getFight().getUserTeam().getMembers().firstValue().getName());
        assertEq(TETARTE, facadeGame.getFight().getUserTeam().getMembers().firstValue().getCurrentName());
    }

    @Test
    public void act2Test() {
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame.roundAllThrowers(false);
        assertEq(FightState.APPRENDRE_EVOLUER, facadeGame.getFight().getState());
        facadeGame.choosePokemonForLearningAndEvolving((byte) 0);
        facadeGame.setEvolution(TETARTE);
        facadeGame.learnAndEvolve();
        assertEq(FightState.SWITCH_PROPOSE, facadeGame.getFight().getState());
        assertEq(2, facadeGame.getPlayerTeam().size());
        assertEq(1, facadeGame.getUnionFrontTeam().size());
        assertEq(1, facadeGame.getPlayerBackTeam().size());
        assertEq(1, facadeGame.getPlayerFrontTeam().size());
        assertEq(1, facadeGame.getPlayerBackTeamForSubstituting().size());
        assertEq(1, facadeGame.getPlayerFrontTeamForSubstituting().size());
        assertEq(1, facadeGame.getFoeFrontTeam().size());
        facadeGame.sendSubstitutes();
        assertEq(FightState.ATTAQUES, facadeGame.getFight().getState());
    }

    @Test
    public void act3Test() {
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.changeAction(ActionType.SWITCH);
        AbstractAction action_;
        action_ = facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ instanceof Action);
        assertEq(Fighter.BACK, facadeGame.getFight().getChosenIndexBack());
        assertEq(0, facadeGame.getFight().getChosenIndexFront());
        assertEq(ActionType.SWITCH, facadeGame.getFight().getSelectedActionCurFighter());
        assertEq(0, facadeGame.getFight().getChosableFoeTargets().size());
        assertEq(0, facadeGame.getFight().getChosablePlayerTargets().size());
    }

    @Test
    public void act4Test() {
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.changeAction(ActionType.SWITCH);
        facadeGame.chooseBackFighter((byte) 0);
        AbstractAction action_;
        action_ = facadeGame.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertEq(1, ((ActionSwitch) action_).getSubstitute());
        assertEq(Fighter.BACK, facadeGame.getFight().getChosenIndexBack());
        assertEq(Fighter.BACK, facadeGame.getFight().getChosenIndexFront());
        assertEq(ActionType.NOTHING, facadeGame.getFight().getSelectedActionCurFighter());
        assertEq(0, facadeGame.getFight().getChosableFoeTargets().size());
        assertEq(0, facadeGame.getFight().getChosablePlayerTargets().size());
    }

    @Test
    public void act5Test() {
        facadeGame.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame.roundAllThrowers(false);
        assertEq(FightState.APPRENDRE_EVOLUER, facadeGame.getFight().getState());
        facadeGame.choosePokemonForLearningAndEvolving((byte) 0);
        facadeGame.setEvolution(TETARTE);
        facadeGame.learnAndEvolve();
        assertEq(FightState.SWITCH_PROPOSE, facadeGame.getFight().getState());
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.setSubstituteEndRound(Fighter.BACK);
        facadeGame.chooseBackFighter((byte) 0);
        facadeGame.setSubstituteEndRound((byte) 0);
        assertEq(Fighter.BACK, facadeGame.getFight().getChosenIndexBack());
        assertEq(Fighter.BACK, facadeGame.getFight().getChosenIndexFront());
        assertEq(Fighter.BACK, facadeGame.getFight().getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, facadeGame.getFight().getFirstPositPlayerFighters().getVal((byte) 1));
    }

    @Test
    public void act6Test() {
        facadeGame.setChangeToFightScene(false);
        facadeGame.getTrainerImage();
        facadeGame.chooseFrontFighter((byte) 0);
        facadeGame.chooseMove(BULLES_D_O);
        facadeGame.setFirstChosenMoveFoeTarget((byte) 0);
        assertEq(2,facadeGame.sortedFightersBeginRound().size());
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
