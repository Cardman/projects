package aiki.facade;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.fight.Fighter;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.actions.AbstractAction;
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
import org.junit.Test;

public final class FacadeGameFightKoTwoTest extends InitializationDataBase {

    public static FacadeGame initTests() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 25);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
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
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame_.roundAllThrowers(false);
        assertTrue(facadeGame_.isChosableForLearningAndEvolving((byte) 0));
        facadeGame_.choosePokemonForLearningAndEvolving((byte) 0);
        assertEq(2,facadeGame_.getEvolutions().size());
        assertEq(1,facadeGame_.getAbilities().size());
        assertEq(NULL_REF,facadeGame_.getAbility());
        facadeGame_.setEvolution(TETARTE);
        assertEq(10,facadeGame_.getMoves().size());
        facadeGame_.addOrForgetMove(BULLES_D_O);
        facadeGame_.addOrForgetMove(ECUME);
        facadeGame_.learnAndEvolve();
        assertEq(TETARTE, facadeGame_.getFight().getUserTeam().getMembers().firstValue().getName());
        assertEq(TETARTE, facadeGame_.getFight().getUserTeam().getMembers().firstValue().getCurrentName());
    }

    @Test
    public void act2Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame_.roundAllThrowers(false);
        assertEq(FightState.APPRENDRE_EVOLUER, facadeGame_.getFight().getState());
        facadeGame_.choosePokemonForLearningAndEvolving((byte) 0);
        facadeGame_.setEvolution(TETARTE);
        facadeGame_.learnAndEvolve();
        assertEq(FightState.SWITCH_PROPOSE, facadeGame_.getFight().getState());
        assertEq(2, facadeGame_.getPlayerTeam().size());
        assertEq(1, facadeGame_.getUnionFrontTeam().size());
        assertEq(1, facadeGame_.getPlayerBackTeam().size());
        assertEq(1, facadeGame_.getPlayerFrontTeam().size());
        assertEq(1, facadeGame_.getPlayerBackTeamForSubstituting().size());
        assertEq(1, facadeGame_.getPlayerFrontTeamForSubstituting().size());
        assertEq(1, facadeGame_.getFoeFrontTeam().size());
        facadeGame_.sendSubstitutes();
        assertEq(FightState.ATTAQUES, facadeGame_.getFight().getState());
    }

    @Test
    public void act3Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.changeAction(ActionType.SWITCH);
        AbstractAction action_;
        action_ = facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(Fighter.BACK, facadeGame_.getFight().getChosenIndexBack());
        assertEq(0, facadeGame_.getFight().getChosenIndexFront());
        assertEq(ActionType.SWITCH, facadeGame_.getFight().getSelectedActionCurFighter());
        assertEq(0, facadeGame_.getFight().getChosableFoeTargets().size());
        assertEq(0, facadeGame_.getFight().getChosablePlayerTargets().size());
    }

    @Test
    public void act4Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.changeAction(ActionType.SWITCH);
        facadeGame_.chooseBackFighter((byte) 0);
        AbstractAction action_;
        action_ = facadeGame_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertEq(1, ((ActionSwitch) action_).getSubstitute());
        assertEq(Fighter.BACK, facadeGame_.getFight().getChosenIndexBack());
        assertEq(Fighter.BACK, facadeGame_.getFight().getChosenIndexFront());
        assertEq(ActionType.NOTHING, facadeGame_.getFight().getSelectedActionCurFighter());
        assertEq(0, facadeGame_.getFight().getChosableFoeTargets().size());
        assertEq(0, facadeGame_.getFight().getChosablePlayerTargets().size());
    }

    @Test
    public void act5Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        facadeGame_.roundAllThrowers(false);
        assertEq(FightState.APPRENDRE_EVOLUER, facadeGame_.getFight().getState());
        facadeGame_.choosePokemonForLearningAndEvolving((byte) 0);
        facadeGame_.setEvolution(TETARTE);
        facadeGame_.learnAndEvolve();
        assertEq(FightState.SWITCH_PROPOSE, facadeGame_.getFight().getState());
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.setSubstituteEndRound(Fighter.BACK);
        facadeGame_.chooseBackFighter((byte) 0);
        facadeGame_.setSubstituteEndRound((byte) 0);
        assertEq(Fighter.BACK, facadeGame_.getFight().getChosenIndexBack());
        assertEq(Fighter.BACK, facadeGame_.getFight().getChosenIndexFront());
        assertEq(Fighter.BACK, facadeGame_.getFight().getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, facadeGame_.getFight().getFirstPositPlayerFighters().getVal((byte) 1));
    }

    @Test
    public void act6Test() {
        FacadeGame facadeGame_ = initTests();
        facadeGame_.setChangeToFightScene(false);
        facadeGame_.getTrainerImage();
        facadeGame_.chooseFrontFighter((byte) 0);
        facadeGame_.chooseMove(BULLES_D_O);
        facadeGame_.setFirstChosenMoveFoeTarget((byte) 0);
        assertEq(2,facadeGame_.sortedFightersBeginRound().size());
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
