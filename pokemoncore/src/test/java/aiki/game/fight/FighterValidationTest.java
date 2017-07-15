package aiki.game.fight;
import static junitparams.JUnitParamsRunner.$;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
import aiki.DataBase;
import aiki.exceptions.GameLoadException;
import aiki.fight.enums.Statistic;
import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.Fight;
import aiki.game.fight.FightKo;
import aiki.game.fight.Fighter;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.MoveTeamPosition;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.MovesAbilities;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.WildPk;

@RunWith(JUnitParamsRunner.class)
@SuppressWarnings("static-method")
public class FighterValidationTest extends InitializationDataBase {

    private static final String SEX = "sex";

    @BeforeClass
    public static void initDataBase() {
        InitializationDataBase.initDataBase();
    }

    Object[] sex() {
        return $($(Sex.GIRL),$(Sex.BOY));
    }

    @Test
    @Parameters(method=SEX)
    public void validate1Test(Sex _sex) {
        Game game_ = newGameInFight(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate2Test(Sex _sex) {
        Game game_ = newGameInFight(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate3Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFight(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate4Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFight(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(OEIL_MIRACLE);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate5Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFight(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate6Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFight(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setFirstChosenMove(OEIL_MIRACLE);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate7Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO, (byte) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate8Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate9Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFight(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate10Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFight(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate11Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setSubstitute((byte) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate12Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setSubstitute((byte) 1);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate13Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate14Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate15Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.setFirstChosenMove(RELAIS);
        figther_.setSubstituteForMove((byte) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate16Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.setFirstChosenMove(RELAIS);
        figther_.setSubstituteForMove((byte) 1);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate17Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(POTION, _data_);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate18Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setChosenHealingObject(POTION, _data_);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate19Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE, JACKPOT);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate20Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE, JACKPOT);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate21Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        FightKo.setKoMoveTeams(game_.getFight(), POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate22Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        FightKo.setKoMoveTeams(game_.getFight(), POKEMON_FOE_FIGHTER_ZERO, diff_, _data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate23Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate24Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate25Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.successUsingMove();
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate26Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.successUsingMove();
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate27Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSufferedMove(DEMI_TOUR);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate28Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setLastSufferedMove(DEMI_TOUR);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate29Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSufferedMoveTypes(new StringList(INSECTE));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate30Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setLastSufferedMoveTypes(new StringList(INSECTE));
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate31Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastUsedMove();
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate32Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setLastUsedMove();
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate33Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.setLastUsedMove();
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate34Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.setLastUsedMove();
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate35Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate36Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate37Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(DEMI_TOUR, COPIE, _data_);
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate38Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(DEMI_TOUR, COPIE, _data_);
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate39Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(POSSESSIF, COPIE, _data_);
        figther_.setFirstChosenMove(POSSESSIF);
        figther_.choisirAttaqueFin();
        figther_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,POKEMON_FOE_FIGHTER_ZERO)).add(JACKPOT);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate40Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(POSSESSIF, COPIE, _data_);
        figther_.setFirstChosenMove(POSSESSIF);
        figther_.choisirAttaqueFin();
        figther_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,POKEMON_PLAYER_FIGHTER_ZERO)).add(JACKPOT);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate41Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(ENCORE, COPIE, _data_);
        figther_.setFirstChosenMoveTarget(ENCORE, POKEMON_FOE_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        AffectedMove aff_ = figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,POKEMON_FOE_FIGHTER_ZERO));
        aff_.setMove(JACKPOT);
        aff_.getActivity().enableReset();
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate42Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(ENCORE, COPIE, _data_);
        figther_.setFirstChosenMoveTarget(ENCORE, POKEMON_PLAYER_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        AffectedMove aff_ = figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,POKEMON_FOE_FIGHTER_ZERO));
        aff_.setMove(JACKPOT);
        aff_.getActivity().enableReset();
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate43Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(TUNNEL, COPIE, _data_);
        figther_.setFirstChosenMoveTarget(TUNNEL, POKEMON_FOE_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        figther_.setNbPrepaRound((short) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate44Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(TUNNEL, COPIE, _data_);
        figther_.setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        figther_.setNbPrepaRound((short) 1);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate45Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationGainExperience(new Rate("10000"), _data_);
        figther_.calculateNewLevel(diff_, _data_, new StringList());
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate46Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.backUpObject(NULL_REF);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate47Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.backUpObject(NULL_REF);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate48Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setName(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate49Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setCurrentName(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate50Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setAbility(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate51Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setCurrentAbility(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate52Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setItem(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate53Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastUsedItem(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate54Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setExpItem(POTION);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate55Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.affecterTypes(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate56Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(INVALID_DATA_KEY, new UsesOfMove((short) 1));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate57Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(INVALID_DATA_KEY, new UsesOfMove((short) 1));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate58Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getAlreadyInvokedMovesRound().add(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate59Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getLastSufferedMoveTypes().add(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate60Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSufferedMove(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate61Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatus().put(INVALID_DATA_KEY, (short) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate62Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatus().put(VAMPIGRAINE, (short) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate63Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(INVALID_DATA_KEY,POKEMON_FOE_FIGHTER_ZERO), (short) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate64Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(GEL,POKEMON_FOE_FIGHTER_ZERO), (short) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate65Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(VAMPIGRAINE,POKEMON_FOE_FIGHTER_TWO), (short) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate66Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatus().put(GEL, (short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate67Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(VAMPIGRAINE,POKEMON_FOE_FIGHTER_ZERO), (short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate68Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getProtectedAgainstMoveTypes().add(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate69Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateInflictedByType().put(INVALID_DATA_KEY, Rate.one());
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate70Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateSufferedByType().put(INVALID_DATA_KEY, Rate.one());
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate71Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCateg().put(INVALID_DATA_KEY, Rate.one());
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate72Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCategRound().put(INVALID_DATA_KEY, Rate.one());
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate73Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCateg().put(DataBase.AUTRE, Rate.one());
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate74Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCategRound().put(DataBase.AUTRE, Rate.one());
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate75Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedBallCatching(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate76Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedBallCatching(POTION);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate77Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate78Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesConstChoices().put(CHARGE, new ActivityOfMove(true));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate79Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesEndRound().put(CHARGE, new ActivityOfMove(true));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate80Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesForAlly().put(CHARGE, true);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate81Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesProt().put(CHARGE, new ActivityOfMove(true));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate82Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesUnprot().put(CHARGE, new ActivityOfMove(true));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate83Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMoves().getVal(PROVOC).setNbTurn((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate84Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesConstChoices().getVal(ROULADE).setNbTurn((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate85Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesEndRound().getVal(ANNEAU_HYDRO).setNbTurn((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate86Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesProt().getVal(VOL_MAGNETIK).setNbTurn((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate87Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesUnprot().getVal(ANTI_AIR).setNbTurn((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate88Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledChangingTypesMoves().put(CHARGE, new ActivityOfMove(true));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate89Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledCounteringMoves().put(CHARGE, new ActivityOfMove(true));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate90Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledChangingTypesMoves().getVal(ELECTRISATION).setNbTurn((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate91Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE).setNbTurn((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate92Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCopiedMoves().put(CHARGE, new CopiedMove(NULL_REF,(short) 5));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate93Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrackingMoves().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), new AffectedMove(NULL_REF, new ActivityOfMove(true)));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate94Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrappingMoves().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), new ActivityOfMove(true));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate95Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getPrivateMoves().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), new StringList());
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate96Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getPrivateMoves().put(new MoveTeamPosition(POSSESSIF, POKEMON_FOE_FIGHTER_ZERO), new StringList(INVALID_DATA_KEY));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate97Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getIncrUserAccuracy().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), true);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate98Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrackingMoves().put(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO), new AffectedMove(INVALID_DATA_KEY, new ActivityOfMove(true)));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate99Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCopiedMoves().put(COPIE, new CopiedMove(INVALID_DATA_KEY,(short) 5));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate100Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCopiedMoves().put(COPIE, new CopiedMove(CHARGE,(short) -5));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate101Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO)).setMove(CHARGE);
        figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO)).getActivity().setNbTurn((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate102Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).setNbTurn((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate103Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getNbUsesMoves().put(CHARGE, 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate104Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getNbUsesMoves().put(STOCKAGE, -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate105Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSuccessfulMove(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate106Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedMoveLastRound(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate107Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedMoveLastRound(CHARGE);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate108Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastUsedMove(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate109Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationGainExperience(new Rate("10000"), _data_);
        figther_.calculateNewLevel(diff_, _data_, new StringList());
        figther_.getMovesToBeLearnt().add(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate110Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationGainExperience(new Rate("10000"), _data_);
        figther_.calculateNewLevel(diff_, _data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(INVALID_DATA_KEY, new MovesAbilities(new StringList(), new StringList()));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate111Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationGainExperience(new Rate("10000"), _data_);
        figther_.calculateNewLevel(diff_, _data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(NINJASK, new MovesAbilities(new StringList(INVALID_DATA_KEY), new StringList()));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate112Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationGainExperience(new Rate("10000"), _data_);
        figther_.calculateNewLevel(diff_, _data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(NINJASK, new MovesAbilities(new StringList(), new StringList(INVALID_DATA_KEY)));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate113Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationGainExperience(new Rate("10000"), _data_);
        figther_.calculateNewLevel(diff_, _data_, new StringList());
        figther_.getMovesToBeLearnt().add(GRIFFE);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate114Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationGainExperience(new Rate("10000"), _data_);
        figther_.calculateNewLevel(diff_, _data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(NINJASK, new MovesAbilities(new StringList(GRIFFE), new StringList()));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate115Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateInflictedByType().getVal(EAU).affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate116Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateSufferedByType().getVal(EAU).affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate117Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCateg().getVal(PHYSIQUE).affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate118Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCategRound().getVal(PHYSIQUE).affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate119Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbPrepaRound((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate120Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbPrepaRound((short) 1);
        figther_.setNeedingToRecharge(true);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate121Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbRepeatingSuccessfulMoves(new LgInt("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate122Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        FightKo.setKoMoveTeams(game_.getFight(), POKEMON_PLAYER_FIGHTER_ZERO, diff_, _data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlace((byte) 0);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate123Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlace((byte) (Fighter.BACK/2));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate124Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlaceSubst((byte) (Fighter.BACK/2));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate125Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlaceSubst(Fighter.BACK);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate126Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getClone().affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate127Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getWonExp().affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate128Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getWonExpSinceLastLevel().affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate129Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getWeight().affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate130Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getHeight().affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate131Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getRemainingHp().affect(new Rate("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate132Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getRemainingHp().affect(new Rate("10000"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate133Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationBoostStatistique(Statistic.ATTACK, (byte) -10);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate134Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationBoostStatistique(Statistic.ATTACK, (byte) 10);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate135Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getNbRounds().affect(new LgInt("-1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate136Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatisBase().getVal(Statistic.ATTACK).affectZero();
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate137Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEv().put(Statistic.ATTACK, (short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate138Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatisBase().put(Statistic.ACCURACY, new Rate("1"));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate139Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEv().put(Statistic.ACCURACY, (short) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate140Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatisBoost().put(Statistic.HP, (byte) 1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate141Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLevel((short) 0);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate142Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLevel((short) 1000);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate143Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(LUTTE, new UsesOfMove((short)10));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate144Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(LUTTE, new UsesOfMove((short)10));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate145Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(LUTTE, new UsesOfMove((short)10));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate146Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(JACKPOT, new UsesOfMove((short)0));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate147Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(JACKPOT, new UsesOfMove((short)0));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate148Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().getVal(JACKPOT).setCurrent((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate149Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().getVal(JACKPOT).setCurrent((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate150Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().getVal(JACKPOT).setCurrent((short) 1000);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate151Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().getVal(JACKPOT).setCurrent((short) 1000);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate152Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(JACKPOT, COPIE, _data_);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate153Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTarget(SEISME, POKEMON_FOE_TARGET_ZERO);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate154Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(JACKPOT);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate155Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(JACKPOT);
        ActionMove act_ = (ActionMove) figther_.getAction();
        act_.getChosenTargets().add(POKEMON_FOE_TARGET_ZERO);
        act_.getChosenTargets().add(POKEMON_FOE_TARGET_ONE);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

//    @Test(expected=GameLoadException.class)
//    @Parameters(method="sex")
//    public void validate155Test(Sex _sex) {
//        Difficulty diff_ = new Difficulty();
//        Game game_ = newGameInFightTrainer(_sex, diff_);
//        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
//        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ONE);
//        figther_.validate(data, Fight.PLAYER, game_.getFight());
//    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate156Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ONE, (byte) 2);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate157Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setSubstitute((byte) 2);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate158Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setSubstitute(Fighter.BACK);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate159Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate160Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.setFirstChosenMove(SEISME);
        figther_.choisirAttaqueFin();
        ActionMove action_ = (ActionMove) figther_.getAction();
        action_.setFinalChosenMove(INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate161Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(MULTI_EXP, _data_);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate162Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(POTION, JACKPOT);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate163Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setChosenHealingObject(BAIE_ORAN, _data_);
        figther_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate164Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE, INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate165Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setHappiness((short) 1000);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate166Test(Sex _sex) {
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(_sex, diff_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setHappiness((short) -1);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate167Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE_MAX, JACKPOT);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate168Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(BAIE_MEPO, JACKPOT);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate169Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(ELIXIR, _data_);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate170Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(MAX_ELIXIR, _data_);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate171Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(BAIE_MEPO, INVALID_DATA_KEY);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate172Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(ELIXIR, JACKPOT);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate173Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(MAX_ELIXIR, JACKPOT);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate174Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(BAIE_MEPO, _data_);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate175Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(HUILE, _data_);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate176Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(BAIE_ORAN, JACKPOT);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate177Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(HUILE_MAX, _data_);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate178Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbPrepaRound((short) 1);
        figther_.setDisappeared(true);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate179Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setDisappeared(true);
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate180Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().clear();
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate181Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(CHARGE, new UsesOfMove((byte)15));
        figther_.getMoves().put(ECLAIR, new UsesOfMove((byte)15));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate182Test(Sex _sex) {
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Game game_ = newGameInFightTrainer(_sex);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(CHARGE, new UsesOfMove((byte)15));
        figther_.getCurrentMoves().put(ECLAIR, new UsesOfMove((byte)15));
        figther_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    private static Game newGameInFightTrainer2(Sex _sex) {
        return newGameInFightTrainer2(_sex, new Difficulty());
    }

    private static Game newGameInFightTrainer2(Sex _sex, Difficulty _diff) {
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

    private static Game newGameInFightTrainer(Sex _sex) {
        return newGameInFightTrainer(_sex, new Difficulty());
    }

    private static Game newGameInFightTrainer(Sex _sex, Difficulty _diff) {
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

    private static Game newGameInFight(Sex _sex) {
        return newGameInFight(_sex, new Difficulty());
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
