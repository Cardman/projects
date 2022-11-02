package aiki.game.fight;

import code.util.core.BoolVal;
import org.junit.Test;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.MovesAbilities;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.WildPk;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;


public class FighterValidationTest extends InitializationDataBase {


    private static final byte BAD_GROUND_PLACE = (byte) (Fighter.BACK / 2)-2;

    @Test
    public void validate1Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate2Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate3Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate4Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate5Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate6Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate7Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(OEIL_MIRACLE);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate8Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(OEIL_MIRACLE);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate9Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate10Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate11Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setFirstChosenMove(OEIL_MIRACLE);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate12Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setFirstChosenMove(OEIL_MIRACLE);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate13Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO, (byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate14Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO, (byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate15Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate16Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate17Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate18Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_FOE_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate19Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate20Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setFirstChosenMoveTarget(JACKPOT, POKEMON_PLAYER_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate21Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setSubstitute((byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate22Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setSubstitute((byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate23Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setSubstitute((byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate24Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setSubstitute((byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate25Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate26Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate27Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate28Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTarget(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate29Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.setFirstChosenMove(RELAIS);
        figther_.setSubstituteForMove((byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate30Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.setFirstChosenMove(RELAIS);
        figther_.setSubstituteForMove((byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate31Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.setFirstChosenMove(RELAIS);
        figther_.setSubstituteForMove((byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate32Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(RELAIS, new UsesOfMove((short)10));
        figther_.setFirstChosenMove(RELAIS);
        figther_.setSubstituteForMove((byte) 1);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate33Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(POTION, data_);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate34Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(POTION, data_);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate35Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setChosenHealingObject(POTION, data_);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate36Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setChosenHealingObject(POTION, data_);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate37Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE, JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate38Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE, JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate39Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE, JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate40Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE, JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate41Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        FightKo.setKoMoveTeams(game_.getFight(), POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate42Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        FightKo.setKoMoveTeams(game_.getFight(), POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate43Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        FightKo.setKoMoveTeams(game_.getFight(), POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate44Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        FightKo.setKoMoveTeams(game_.getFight(), POKEMON_FOE_FIGHTER_ZERO, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate45Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate46Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate47Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate48Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate49Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.successUsingMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate50Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.successUsingMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate51Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.successUsingMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate52Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.successUsingMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate53Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSufferedMove(DEMI_TOUR);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate54Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSufferedMove(DEMI_TOUR);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate55Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setLastSufferedMove(DEMI_TOUR);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate56Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setLastSufferedMove(DEMI_TOUR);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate57Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSufferedMoveTypes(new StringList(INSECTE));
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate58Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSufferedMoveTypes(new StringList(INSECTE));
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate59Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setLastSufferedMoveTypes(new StringList(INSECTE));
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate60Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setLastSufferedMoveTypes(new StringList(INSECTE));
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate61Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate62Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate63Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate64Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate65Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate66Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate67Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate68Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate69Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate70Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate71Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate72Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate73Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(DEMI_TOUR, COPIE, data_);
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate74Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(DEMI_TOUR, COPIE, data_);
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate75Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(DEMI_TOUR, COPIE, data_);
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate76Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(DEMI_TOUR, COPIE, data_);
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_PLAYER_TARGET_ZERO, (byte) 1);
        figther_.choisirAttaqueFin();
        figther_.ajouterAttaquesDejaInvoqueesTour(DEMI_TOUR);
        figther_.invokeMove();
        figther_.setLastUsedMove();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate77Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(POSSESSIF, COPIE, data_);
        figther_.setFirstChosenMove(POSSESSIF);
        figther_.choisirAttaqueFin();
        figther_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,POKEMON_FOE_FIGHTER_ZERO)).add(JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate78Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(POSSESSIF, COPIE, data_);
        figther_.setFirstChosenMove(POSSESSIF);
        figther_.choisirAttaqueFin();
        figther_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,POKEMON_FOE_FIGHTER_ZERO)).add(JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate79Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(POSSESSIF, COPIE, data_);
        figther_.setFirstChosenMove(POSSESSIF);
        figther_.choisirAttaqueFin();
        figther_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,POKEMON_PLAYER_FIGHTER_ZERO)).add(JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate80Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(POSSESSIF, COPIE, data_);
        figther_.setFirstChosenMove(POSSESSIF);
        figther_.choisirAttaqueFin();
        figther_.getPrivateMoves().getVal(new MoveTeamPosition(POSSESSIF,POKEMON_PLAYER_FIGHTER_ZERO)).add(JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate81Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(ENCORE, COPIE, data_);
        figther_.setFirstChosenMoveTarget(ENCORE, POKEMON_FOE_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        AffectedMove aff_ = figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,POKEMON_FOE_FIGHTER_ZERO));
        aff_.setMove(JACKPOT);
        aff_.getActivity().enableReset();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate82Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(ENCORE, COPIE, data_);
        figther_.setFirstChosenMoveTarget(ENCORE, POKEMON_FOE_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        AffectedMove aff_ = figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,POKEMON_FOE_FIGHTER_ZERO));
        aff_.setMove(JACKPOT);
        aff_.getActivity().enableReset();
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate83Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(ENCORE, COPIE, data_);
        figther_.setFirstChosenMoveTarget(ENCORE, POKEMON_PLAYER_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        AffectedMove aff_ = figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,POKEMON_FOE_FIGHTER_ZERO));
        aff_.setMove(JACKPOT);
        aff_.getActivity().enableReset();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate84Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(ENCORE, COPIE, data_);
        figther_.setFirstChosenMoveTarget(ENCORE, POKEMON_PLAYER_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        AffectedMove aff_ = figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE,POKEMON_FOE_FIGHTER_ZERO));
        aff_.setMove(JACKPOT);
        aff_.getActivity().enableReset();
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate85Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        figther_.setFirstChosenMoveTarget(TUNNEL, POKEMON_FOE_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        figther_.setNbPrepaRound((short) 1);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate86Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        figther_.setFirstChosenMoveTarget(TUNNEL, POKEMON_FOE_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        figther_.setNbPrepaRound((short) 1);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate87Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        figther_.setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        figther_.setNbPrepaRound((short) 1);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate88Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(TUNNEL, COPIE, data_);
        figther_.setFirstChosenMoveTarget(TUNNEL, POKEMON_PLAYER_TARGET_ZERO);
        figther_.choisirAttaqueFin();
        figther_.setNbPrepaRound((short) 1);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate89Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate90Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        Difficulty diff_ = new Difficulty();
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate91Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.backUpObject(NULL_REF);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate92Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.backUpObject(NULL_REF);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate93Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.backUpObject(NULL_REF);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate94Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.backUpObject(NULL_REF);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate95Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setName(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate96Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setName(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate97Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setCurrentName(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate98Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setCurrentName(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate99Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setAbility(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate100Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setAbility(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate101Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setCurrentAbility(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate102Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setCurrentAbility(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate103Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setItem(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate104Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setItem(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate105Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastUsedItem(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate106Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastUsedItem(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate107Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setExpItem(POTION);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate108Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setExpItem(POTION);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate109Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.affecterTypes(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate110Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.affecterTypes(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate111Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(INVALID_DATA_KEY, new UsesOfMove((short) 1));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate112Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(INVALID_DATA_KEY, new UsesOfMove((short) 1));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate113Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(INVALID_DATA_KEY, new UsesOfMove((short) 1));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate114Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(INVALID_DATA_KEY, new UsesOfMove((short) 1));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate115Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getAlreadyInvokedMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate116Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getAlreadyInvokedMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate117Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getLastSufferedMoveTypes().add(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate118Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getLastSufferedMoveTypes().add(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate119Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSufferedMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate120Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSufferedMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate121Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatus().put(INVALID_DATA_KEY, (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate122Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatus().put(INVALID_DATA_KEY, (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate123Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatus().put(VAMPIGRAINE, (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate124Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatus().put(VAMPIGRAINE, (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate125Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(INVALID_DATA_KEY,POKEMON_FOE_FIGHTER_ZERO), (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate126Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(INVALID_DATA_KEY,POKEMON_FOE_FIGHTER_ZERO), (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate127Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(GEL,POKEMON_FOE_FIGHTER_ZERO), (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate128Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(GEL,POKEMON_FOE_FIGHTER_ZERO), (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate129Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(VAMPIGRAINE,POKEMON_FOE_FIGHTER_TWO), (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate130Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(VAMPIGRAINE,POKEMON_FOE_FIGHTER_TWO), (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate131Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatus().put(GEL, (short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate132Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatus().put(GEL, (short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate133Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(VAMPIGRAINE,POKEMON_FOE_FIGHTER_ZERO), (short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate134Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatusRelat().put(new MoveTeamPosition(VAMPIGRAINE,POKEMON_FOE_FIGHTER_ZERO), (short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate135Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getProtectedAgainstMoveTypes().add(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate136Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getProtectedAgainstMoveTypes().add(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate137Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateInflictedByType().put(INVALID_DATA_KEY, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate138Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateInflictedByType().put(INVALID_DATA_KEY, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate139Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateSufferedByType().put(INVALID_DATA_KEY, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate140Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateSufferedByType().put(INVALID_DATA_KEY, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate141Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCateg().put(INVALID_DATA_KEY, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate142Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCateg().put(INVALID_DATA_KEY, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate143Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCategRound().put(INVALID_DATA_KEY, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate144Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCategRound().put(INVALID_DATA_KEY, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate145Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCateg().put(AUTRE, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate146Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCateg().put(AUTRE, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate147Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCategRound().put(AUTRE, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate148Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCategRound().put(AUTRE, Rate.one());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate149Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedBallCatching(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate150Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedBallCatching(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate151Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedBallCatching(POTION);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate152Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedBallCatching(POTION);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate153Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate154Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate155Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesConstChoices().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate156Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesConstChoices().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate157Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesEndRound().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate158Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesEndRound().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate159Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesForAlly().put(CHARGE, BoolVal.TRUE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate160Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesForAlly().put(CHARGE, BoolVal.TRUE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate161Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesProt().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate162Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesProt().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate163Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesUnprot().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate164Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesUnprot().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate165Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMoves().getVal(PROVOC).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate166Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMoves().getVal(PROVOC).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate167Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesConstChoices().getVal(ROULADE).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate168Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesConstChoices().getVal(ROULADE).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate169Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesEndRound().getVal(ANNEAU_HYDRO).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate170Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesEndRound().getVal(ANNEAU_HYDRO).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate171Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesProt().getVal(VOL_MAGNETIK).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate172Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesProt().getVal(VOL_MAGNETIK).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate173Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesUnprot().getVal(ANTI_AIR).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate174Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledMovesUnprot().getVal(ANTI_AIR).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate175Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledChangingTypesMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate176Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledChangingTypesMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate177Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledCounteringMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate178Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledCounteringMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate179Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledChangingTypesMoves().getVal(ELECTRISATION).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate180Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledChangingTypesMoves().getVal(ELECTRISATION).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate181Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate182Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEnabledCounteringMoves().getVal(NUEE_DE_POUDRE).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate183Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCopiedMoves().put(CHARGE, new CopiedMove(NULL_REF,(short) 5));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate184Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCopiedMoves().put(CHARGE, new CopiedMove(NULL_REF,(short) 5));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate185Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrackingMoves().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), new AffectedMove(NULL_REF, new ActivityOfMove(true)));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate186Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrackingMoves().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), new AffectedMove(NULL_REF, new ActivityOfMove(true)));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate187Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrappingMoves().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate188Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrappingMoves().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), new ActivityOfMove(true));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate189Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getPrivateMoves().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), new StringList());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate190Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getPrivateMoves().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), new StringList());
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate191Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getPrivateMoves().put(new MoveTeamPosition(POSSESSIF, POKEMON_FOE_FIGHTER_ZERO), new StringList(INVALID_DATA_KEY));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate192Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getPrivateMoves().put(new MoveTeamPosition(POSSESSIF, POKEMON_FOE_FIGHTER_ZERO), new StringList(INVALID_DATA_KEY));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate193Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getIncrUserAccuracy().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate194Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getIncrUserAccuracy().put(new MoveTeamPosition(CHARGE, POKEMON_FOE_FIGHTER_ZERO), BoolVal.TRUE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate195Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrackingMoves().put(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO), new AffectedMove(INVALID_DATA_KEY, new ActivityOfMove(true)));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate196Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrackingMoves().put(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO), new AffectedMove(INVALID_DATA_KEY, new ActivityOfMove(true)));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate197Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCopiedMoves().put(COPIE, new CopiedMove(INVALID_DATA_KEY,(short) 5));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate198Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCopiedMoves().put(COPIE, new CopiedMove(INVALID_DATA_KEY,(short) 5));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate199Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCopiedMoves().put(COPIE, new CopiedMove(CHARGE,(short) -5));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate200Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCopiedMoves().put(COPIE, new CopiedMove(CHARGE,(short) -5));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate201Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO)).setMove(CHARGE);
        figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO)).getActivity().setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate202Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO)).setMove(CHARGE);
        figther_.getTrackingMoves().getVal(new MoveTeamPosition(ENCORE, POKEMON_FOE_FIGHTER_ZERO)).getActivity().setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate203Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate204Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getTrappingMoves().getVal(new MoveTeamPosition(SIPHON, POKEMON_FOE_FIGHTER_ZERO)).setNbTurn((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate205Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getNbUsesMoves().put(CHARGE, 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate206Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getNbUsesMoves().put(CHARGE, 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate207Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getNbUsesMoves().put(STOCKAGE, -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate208Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getNbUsesMoves().put(STOCKAGE, -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate209Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSuccessfulMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate210Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastSuccessfulMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate211Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedMoveLastRound(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate212Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedMoveLastRound(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate213Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedMoveLastRound(CHARGE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate214Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setUsedMoveLastRound(CHARGE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate215Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastUsedMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate216Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLastUsedMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate217Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesToBeLearnt().add(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate218Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesToBeLearnt().add(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate219Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(INVALID_DATA_KEY, new MovesAbilities(new StringList(), new StringList()));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate220Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(INVALID_DATA_KEY, new MovesAbilities(new StringList(), new StringList()));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate221Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(NINJASK, new MovesAbilities(new StringList(INVALID_DATA_KEY), new StringList()));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate222Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(NINJASK, new MovesAbilities(new StringList(INVALID_DATA_KEY), new StringList()));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate223Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(NINJASK, new MovesAbilities(new StringList(), new StringList(INVALID_DATA_KEY)));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate224Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(NINJASK, new MovesAbilities(new StringList(), new StringList(INVALID_DATA_KEY)));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate225Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesToBeLearnt().add(GRIFFE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate226Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesToBeLearnt().add(GRIFFE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate227Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(NINJASK, new MovesAbilities(new StringList(GRIFFE), new StringList()));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate228Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        variationGainExperience(figther_, data_);
        calculateNewLevel(figther_, diff_, data_, new StringList());
        figther_.getMovesAbilitiesEvos().put(NINJASK, new MovesAbilities(new StringList(GRIFFE), new StringList()));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }

    @Test
    public void validate229Test(){
        Difficulty diff_ = new Difficulty();
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateInflictedByType().getVal(EAU).affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate230Test(){
        Difficulty diff_ = new Difficulty();
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateInflictedByType().getVal(EAU).affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate231Test(){
        Difficulty diff_ = new Difficulty();
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateSufferedByType().getVal(EAU).affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate232Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageRateSufferedByType().getVal(EAU).affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate233Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCateg().getVal(PHYSIQUE).affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate234Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCateg().getVal(PHYSIQUE).affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate235Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCategRound().getVal(PHYSIQUE).affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate236Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getDamageSufferedCategRound().getVal(PHYSIQUE).affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate237Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbPrepaRound((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate238Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbPrepaRound((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate239Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbPrepaRound((short) 1);
        figther_.setNeedingToRecharge(true);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate240Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbPrepaRound((short) 1);
        figther_.setNeedingToRecharge(true);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate241Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbRepeatingSuccessfulMoves(new LgInt("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate242Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbRepeatingSuccessfulMoves(new LgInt("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate243Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        FightKo.setKoMoveTeams(game_.getFight(), POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlace((byte) 0);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate244Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        FightKo.setKoMoveTeams(game_.getFight(), POKEMON_PLAYER_FIGHTER_ZERO, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlace((byte) 0);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate245Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlace(BAD_GROUND_PLACE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate246Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlace(BAD_GROUND_PLACE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate247Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlaceSubst(BAD_GROUND_PLACE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate248Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlaceSubst(BAD_GROUND_PLACE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate249Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlaceSubst(Fighter.BACK);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate250Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setGroundPlaceSubst(Fighter.BACK);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate251Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getClone().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate252Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getClone().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate253Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getWonExp().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate254Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getWonExp().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate255Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getWonExpSinceLastLevel().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate256Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getWonExpSinceLastLevel().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate257Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getWeight().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate258Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getWeight().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate259Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getHeight().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate260Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getHeight().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate261Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getRemainingHp().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate262Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getRemainingHp().affect(new Rate("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate263Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getRemainingHp().affect(new Rate("10000"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate264Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getRemainingHp().affect(new Rate("10000"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate265Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationBoostStatistique(Statistic.ATTACK, (byte) -10);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate266Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationBoostStatistique(Statistic.ATTACK, (byte) -10);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate267Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationBoostStatistique(Statistic.ATTACK, (byte) 10);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate268Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.variationBoostStatistique(Statistic.ATTACK, (byte) 10);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate269Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getNbRounds().affect(new LgInt("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate270Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getNbRounds().affect(new LgInt("-1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate271Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatisBase().getVal(Statistic.ATTACK).affectZero();
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate272Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatisBase().getVal(Statistic.ATTACK).affectZero();
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate273Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEv().put(Statistic.ATTACK, (short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate274Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEv().put(Statistic.ATTACK, (short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate275Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatisBase().put(Statistic.ACCURACY, new Rate("1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate276Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatisBase().put(Statistic.ACCURACY, new Rate("1"));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate277Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEv().put(Statistic.ACCURACY, (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate278Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getEv().put(Statistic.ACCURACY, (short) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate279Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatisBoost().put(Statistic.HP, (byte) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate280Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getStatisBoost().put(Statistic.HP, (byte) 1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate281Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLevel((short) 0);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate282Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLevel((short) 0);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate283Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLevel((short) 1000);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate284Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setLevel((short) 1000);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate285Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(LUTTE, new UsesOfMove((short)10));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate286Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(LUTTE, new UsesOfMove((short)10));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate287Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(LUTTE, new UsesOfMove((short)10));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate288Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(LUTTE, new UsesOfMove((short)10));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate289Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(LUTTE, new UsesOfMove((short)10));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate290Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(LUTTE, new UsesOfMove((short)10));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate291Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(JACKPOT, new UsesOfMove((short)0));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate292Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(JACKPOT, new UsesOfMove((short)0));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate293Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(JACKPOT, new UsesOfMove((short)0));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate294Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(JACKPOT, new UsesOfMove((short)0));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate295Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().getVal(JACKPOT).setCurrent((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate296Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().getVal(JACKPOT).setCurrent((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate297Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().getVal(JACKPOT).setCurrent((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate298Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().getVal(JACKPOT).setCurrent((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate299Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().getVal(JACKPOT).setCurrent((short) 1000);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate300Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().getVal(JACKPOT).setCurrent((short) 1000);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate301Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().getVal(JACKPOT).setCurrent((short) 1000);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate302Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().getVal(JACKPOT).setCurrent((short) 1000);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate303Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(JACKPOT, COPIE, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate304Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(COPIE, new UsesOfMove((short)10));
        figther_.apprendreAttaqueEcrasant(JACKPOT, COPIE, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate305Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTarget(SEISME, POKEMON_FOE_TARGET_ZERO);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate306Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTarget(SEISME, POKEMON_FOE_TARGET_ZERO);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate307Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate308Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate309Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(JACKPOT);
        ActionMove act_ = (ActionMove) figther_.getAction();
        act_.getChosenTargets().add(POKEMON_FOE_TARGET_ZERO);
        act_.getChosenTargets().add(POKEMON_FOE_TARGET_ONE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate310Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(JACKPOT);
        ActionMove act_ = (ActionMove) figther_.getAction();
        act_.getChosenTargets().add(POKEMON_FOE_TARGET_ZERO);
        act_.getChosenTargets().add(POKEMON_FOE_TARGET_ONE);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate311Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ONE, (byte) 2);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate312Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setFirstChosenMoveTargetSubstitute(DEMI_TOUR, POKEMON_FOE_TARGET_ONE, (byte) 2);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate313Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setSubstitute((byte) 2);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate314Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setSubstitute((byte) 2);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate315Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setSubstitute(Fighter.BACK);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate316Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(DEMI_TOUR, new UsesOfMove((short)10));
        figther_.setSubstitute(Fighter.BACK);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate317Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate318Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setFirstChosenMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate319Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.setFirstChosenMove(SEISME);
        figther_.choisirAttaqueFin();
        ActionMove action_ = (ActionMove) figther_.getAction();
        action_.setFinalChosenMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate320Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.getCurrentMoves().put(SEISME, new UsesOfMove((short)10));
        figther_.setFirstChosenMove(SEISME);
        figther_.choisirAttaqueFin();
        ActionMove action_ = (ActionMove) figther_.getAction();
        action_.setFinalChosenMove(INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate321Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(MULTI_EXP, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate322Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(MULTI_EXP, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate323Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(POTION, JACKPOT);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate324Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(POTION, JACKPOT);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate325Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate326Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO);
        figther_.setChosenHealingObject(BAIE_ORAN, data_);
        assertTrue(figther_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate327Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE, INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate328Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE, INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate329Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setHappiness((short) 1000);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate330Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setHappiness((short) 1000);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate331Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.GIRL, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setHappiness((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate332Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer(Sex.BOY, diff_, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setHappiness((short) -1);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate333Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE_MAX, JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate334Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(HUILE_MAX, JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate335Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(BAIE_MEPO, JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate336Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(BAIE_MEPO, JACKPOT);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate337Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(ELIXIR, data_);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate338Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(ELIXIR, data_);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate339Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(MAX_ELIXIR, data_);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate340Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(MAX_ELIXIR, data_);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate341Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(BAIE_MEPO, INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate342Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(BAIE_MEPO, INVALID_DATA_KEY);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate343Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(ELIXIR, JACKPOT);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate344Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(ELIXIR, JACKPOT);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate345Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(MAX_ELIXIR, JACKPOT);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate346Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(MAX_ELIXIR, JACKPOT);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate347Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(BAIE_MEPO, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate348Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(BAIE_MEPO, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate349Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(HUILE, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate350Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(HUILE, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate351Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(BAIE_ORAN, JACKPOT);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate352Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObjectMove(BAIE_ORAN, JACKPOT);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate353Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(HUILE_MAX, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate354Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setChosenHealingObject(HUILE_MAX, data_);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate355Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbPrepaRound((short) 1);
        figther_.setDisappeared(true);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate356Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setNbPrepaRound((short) 1);
        figther_.setDisappeared(true);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate357Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setDisappeared(true);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate358Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setDisappeared(true);
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate359Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().clear();
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate360Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().clear();
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate361Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(CHARGE, new UsesOfMove((byte)15));
        figther_.getMoves().put(ECLAIR, new UsesOfMove((byte)15));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate362Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getMoves().put(CHARGE, new UsesOfMove((byte)15));
        figther_.getMoves().put(ECLAIR, new UsesOfMove((byte)15));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate363Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(CHARGE, new UsesOfMove((byte)15));
        figther_.getCurrentMoves().put(ECLAIR, new UsesOfMove((byte)15));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate364Test(){
        //JACKPOT, PASSE_PASSE, OEIL_MIRACLE
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getCurrentMoves().put(CHARGE, new UsesOfMove((byte)15));
        figther_.getCurrentMoves().put(ECLAIR, new UsesOfMove((byte)15));
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate365Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getIncrUserAccuracy().clear();
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate366Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.getIncrUserAccuracy().clear();
        assertTrue(!figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate367Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setCurrentAbility(NULL_REF);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate368Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Fighter figther_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO);
        figther_.setCurrentAbility(NULL_REF);
        assertTrue(figther_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }

    private static Game newGameInFightTrainer2(Sex _sex, DataBase _data) {
        return newGameInFightTrainer2(_sex, new Difficulty(), _data);
    }

    private static Game newGameInFightTrainer2(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.RIGHT, _data);
        game_.moving(Direction.RIGHT, _data);
        game_.moving(Direction.DOWN, _data);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainer(Sex _sex, DataBase _data) {
        return newGameInFightTrainer(_sex, new Difficulty(), _data);
    }

    private static Game newGameInFightTrainer(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.RIGHT, _data);
        game_.moving(Direction.RIGHT, _data);
        game_.moving(Direction.DOWN, _data);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFight(Sex _sex, DataBase _data) {
        return newGameInFight(_sex, new Difficulty(), _data);
    }

    private static Game newGameInFight(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.RIGHT, _data);
        game_.moving(Direction.RIGHT, _data);
        return game_;
    }
    private void variationGainExperience(Fighter _f, DataBase _d) {
        Rate v_ = new Rate("10000");
        _f.variationGainExperience(v_);
        _f.variationGainExperienceMessage(v_, _d);
    }

}