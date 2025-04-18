package aiki.game.fight;

import aiki.db.DataBase;
import aiki.util.TeamPositionList;
import code.util.core.BoolVal;
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
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class FightValidationTest extends InitializationDataBase {
    @Test
    public void validAllyChoices1Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices2Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices3Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(NULL_REF, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices4Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(NULL_REF, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices5Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices6Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices7Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(INVALID_DATA_KEY, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices8Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(INVALID_DATA_KEY, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices9Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(INVALID_DATA_KEY, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices10Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(INVALID_DATA_KEY, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices11Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_TWO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices12Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_TWO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices13Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_TWO)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices14Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_TWO)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices15Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, new TargetCoords( 2, 0)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices16Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, new TargetCoords( 2, 0)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices17Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords( 2, 0)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices18Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords( 2, 0)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices19Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, new TargetCoords(Fight.CST_PLAYER,Fighter.BACK)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices20Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, new TargetCoords(Fight.CST_PLAYER,Fighter.BACK)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices21Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.CST_PLAYER,Fighter.BACK)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices22Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.CST_PLAYER,Fighter.BACK)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices23Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, new TargetCoords(Fight.CST_PLAYER, (Fighter.BACK-1))));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices24Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(JACKPOT, new TargetCoords(Fight.CST_PLAYER, (Fighter.BACK-1))));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices25Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.CST_PLAYER, (Fighter.BACK-1))), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices26Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, new TargetCoords(Fight.CST_PLAYER, (Fighter.BACK-1))), new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices27Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(BROUHAHA, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices28Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(BROUHAHA, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices29Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(NULL_REF, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(BROUHAHA, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validAllyChoices30Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        game_.getFight().getAllyChoice().clear();
        game_.getFight().getAllyChoice().put(new MoveTarget(NULL_REF, tc(KEY_FOE, POKEMON_TARGET_ZERO)), new MoveTarget(BROUHAHA, tc(KEY_FOE, POKEMON_TARGET_ZERO)));
        assertTrue(FightFacade.validAllyChoices(game_.getFight(), data_));
    }
    @Test
    public void validPlacesSubst1Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.CST_FOE, false));
    }
    @Test
    public void validPlacesSubst2Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.CST_FOE, false));
    }
    @Test
    public void validPlacesSubst3Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.CST_PLAYER, false));
    }
    @Test
    public void validPlacesSubst4Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlacesSubst(fight_, Fight.CST_PLAYER, false));
    }
    @Test
    public void validPlacesSubst5Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.CST_PLAYER, true));
    }
    @Test
    public void validPlacesSubst6Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.CST_PLAYER, true));
    }
    @Test
    public void validPlacesSubst7Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setGroundPlaceSubst( 0);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.CST_PLAYER, true));
    }
    @Test
    public void validPlacesSubst8Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setGroundPlaceSubst( 0);
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.CST_PLAYER, true));
    }
    @Test
    public void validPlacesSubst9Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).exitFrontBattleForBeingSubstitued();
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.CST_PLAYER, false));
    }
    @Test
    public void validPlacesSubst10Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).exitFrontBattleForBeingSubstitued();
        assertTrue(!FightFacade.validPlacesSubst(fight_, Fight.CST_PLAYER, false));
    }
    @Test
    public void validPlaces1Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.CST_FOE));
    }
    @Test
    public void validPlaces2Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.CST_FOE));
    }
    @Test
    public void validPlaces3Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validPlaces4Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validPlaces(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validPlaces5Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlace( 1);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validPlaces6Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlace( 1);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validPlaces7Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setGroundPlace( 0);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validPlaces8Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setGroundPlace( 0);
        assertTrue(!FightFacade.validPlaces(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validSwitchTeam1Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validSwitchTeam2Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validSwitchTeam3Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.CST_FOE));
    }
    @Test
    public void validSwitchTeam4Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validSwitchTeam(fight_, Fight.CST_FOE));
    }
    @Test
    public void validSwitchTeam5Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setSubstitute( 2);
        assertTrue(!FightFacade.validSwitchTeam(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validSwitchTeam6Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setSubstitute( 2);
        assertTrue(!FightFacade.validSwitchTeam(fight_, Fight.CST_PLAYER));
    }
    @Test
    public void validSubstitutingTeam1Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam2Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam3Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam4Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam5Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam6Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam7Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam8Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam9Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
//        FightRound.beginRound(fight_, player_, diff_, data_);
//        FightRound.roundUser(fight_, diff_, player_, data_);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        game_.sendSubstitutes(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam10Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
//        FightRound.beginRound(fight_, player_, diff_, data_);
//        FightRound.roundUser(fight_, diff_, player_, data_);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        game_.sendSubstitutes(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam11Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
//        FightRound.beginRound(fight_, player_, diff_, data_);
//        FightRound.roundUser(fight_, diff_, player_, data_);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        game_.sendSubstitutes(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam12Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
//        FightRound.beginRound(fight_, player_, diff_, data_);
//        FightRound.roundUser(fight_, diff_, player_, data_);
//        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        game_.sendSubstitutes(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(fight_, Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam13Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data_);
        game_.chooseBackFighter( 0, data_);
        game_.setChosenHealingItem(RAPPEL, data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, true);
        game_.roundUser(data_);
        Fighter fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam14Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data_);
        game_.chooseBackFighter( 0, data_);
        game_.setChosenHealingItem(RAPPEL, data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, true);
        game_.roundUser(data_);
        Fighter fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam15Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data_);
        game_.chooseBackFighter( 0, data_);
        game_.setChosenHealingItem(RAPPEL, data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, true);
        game_.roundUser(data_);
        Fighter fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam16Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data_);
        game_.chooseBackFighter( 0, data_);
        game_.setChosenHealingItem(RAPPEL, data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, true);
        game_.roundUser(data_);
        Fighter fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam17Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam18Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam19Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam20Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam21Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam22Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam23Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam24Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam25Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam26Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam27Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam28Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validSubstitutingTeam(game_.getFight(), FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam29Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositFoeFighters().put(0, 0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam30Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositFoeFighters().put(0, 0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam31Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put(3, 1);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam32Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put(3, 1);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam33Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put(3, 2);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam34Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put(3, 2);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam35Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositFoeFighters().put(3, 2);
        fight_.getFirstPositFoeFighters().put(4, 1);
        fight_.getFirstPositFoeFighters().put(2, 0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam36Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositFoeFighters().put(3, 2);
        fight_.getFirstPositFoeFighters().put(4, 1);
        fight_.getFirstPositFoeFighters().put(2, 0);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam37Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositFoeFighters().put(2, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam38Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositFoeFighters().put(2, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam39Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.sendSubstitutes(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, true);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), data_);
        //invalid data_
        fight_.getFirstPositFoeFighters().put(3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam40Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.sendSubstitutes(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, true);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_TWO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), data_);
        //invalid data_
        fight_.getFirstPositFoeFighters().put(3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_FOE)));
    }
    @Test
    public void validSubstitutingTeam41Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, true);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), data_);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put(3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validSubstitutingTeam42Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, true);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_TWO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, game_.getPlayer(), data_);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put(3, Fighter.BACK);
        assertTrue(!FightFacade.validSubstitutingTeam(fight_, FightOrder.fighters(game_.getFight(), Fight.CST_PLAYER)));
    }
    @Test
    public void validate1Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate2Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate3Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate4Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate5Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate6Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate7Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(BROUHAHA,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate8Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(BROUHAHA,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate9Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(BROUHAHA,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate10Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(BROUHAHA,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate11Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(DEMI_TOUR,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstituteForMove( 1);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate12Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(DEMI_TOUR,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstituteForMove( 1);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate13Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(DEMI_TOUR,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate14Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(DEMI_TOUR,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate15Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelAllyArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesAlly_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(BROUHAHA,  10);
        movesAlly_.add(new StringMap<Integer>());
        movesAlly_.add(new StringMap<Integer>());
        movesAlly_.add(moves_);
        movesAlly_.add(new StringMap<Integer>());
        replacePlayerMoves(fight_, movesAlly_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate16Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelAllyArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesAlly_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(BROUHAHA,  10);
        movesAlly_.add(new StringMap<Integer>());
        movesAlly_.add(new StringMap<Integer>());
        movesAlly_.add(moves_);
        movesAlly_.add(new StringMap<Integer>());
        replacePlayerMoves(fight_, movesAlly_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate17Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
//        cancelAllyArtificialIntelligence(fight_);
//        CustList<Map<String, Integer>> movesAlly_ = new CustList<>();
//        Map<String, Integer> moves_ = new Map<String,Integer>();
//        moves_.put(BROUHAHA,  10);
//        movesAlly_.add(moves_);
//        movesAlly_.add(new Map<String, Integer>());
//        movesAlly_.add(new Map<String, Integer>());
//        movesAlly_.add(new Map<String, Integer>());
//        replacePlayerMoves(fight_, movesAlly_);
        //FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate18Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
//        cancelAllyArtificialIntelligence(fight_);
//        CustList<Map<String, Integer>> movesAlly_ = new CustList<>();
//        Map<String, Integer> moves_ = new Map<String,Integer>();
//        moves_.put(BROUHAHA,  10);
//        movesAlly_.add(moves_);
//        movesAlly_.add(new Map<String, Integer>());
//        movesAlly_.add(new Map<String, Integer>());
//        movesAlly_.add(new Map<String, Integer>());
//        replacePlayerMoves(fight_, movesAlly_);
        //FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate19Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate20Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate21Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate22Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate23Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate24Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate25Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate26Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate27Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(DEMI_TOUR,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate28Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(DEMI_TOUR,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate29Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.sendSubstitutes(data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate30Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.sendSubstitutes(data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate31Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.sendSubstitutes(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate32Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.sendSubstitutes(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate33Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate34Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate35Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(MUNJA);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate36Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual3(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(MUNJA);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate37Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate38Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate39Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual5(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate40Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual5(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate41Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate42Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate43Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate44Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate45Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data_);
        assertTrue(FightFacade.validate(game_.getFight(), data_, game_.getPlayer(), game_.getDifficulty()));
    }
    @Test
    public void validate46Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data_);
        assertTrue(FightFacade.validate(game_.getFight(), data_, game_.getPlayer(), game_.getDifficulty()));
    }
    @Test
    public void validate47Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data_);
        game_.chooseBackFighter( 0, data_);
        game_.setChosenHealingItem(RAPPEL, data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, true);
        game_.roundUser(data_);
        Fighter fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data_);
        assertTrue(FightFacade.validate(game_.getFight(), data_, game_.getPlayer(), game_.getDifficulty()));
//        FightFacade.chooseBackFighter(game_.getFight(),  0, data_);
//        FightFacade.setSubstituteBack(game_.getFight(),  1);
    }
    @Test
    public void validate48Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        PokemonPlayer pkLast_ = (PokemonPlayer) game_.getPlayer().getTeam().last();
        pkLast_.getRemainingHp().affectZero();
        game_.initTrainerFight(data_);
        game_.chooseBackFighter( 0, data_);
        game_.setChosenHealingItem(RAPPEL, data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, true);
        game_.roundUser(data_);
        Fighter fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        game_.getFight().getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        fighter_.setActed(true);
        FightRound.endRoundShowActions(game_.getFight(), game_.getDifficulty(), game_.getPlayer(), data_);
        assertTrue(FightFacade.validate(game_.getFight(), data_, game_.getPlayer(), game_.getDifficulty()));
//        FightFacade.chooseBackFighter(game_.getFight(),  0, data_);
//        FightFacade.setSubstituteBack(game_.getFight(),  1);
    }
    @Test
    public void validate49Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate50Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate51Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate52Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate53Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual8(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate54Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual8(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        fight_.getUserTeam().activerEffetEquipe(AIR_VEINARD);
        fight_.getFoeTeam().activerEffetEquipe(AIR_VEINARD);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate55Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate56Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate57Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate58Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate59Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //Never mind for the nickname
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        fight_.getCatchingBalls().first().setNickname(NICKNAME);
        game_.catchKoWildPokemon(data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate60Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //Never mind for the nickname
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        fight_.getCatchingBalls().first().setNickname(NICKNAME);
        game_.catchKoWildPokemon(data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate61Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate62Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        FightRound.initRound(fight_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(JACKPOT);
        fighter_.setLastUsedMove(JACKPOT);
        fighter_.setActed(true);
        fight_.getFoeTeam().addSuccessfulMoveRound(JACKPOT);
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate63Test(){
        //newGameInFightTrainerDual1
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(BROUHAHA,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate64Test(){
        //newGameInFightTrainerDual1
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(BROUHAHA,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMove(SEISME);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate65Test(){
        //newGameInFightTrainerDual1
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(JACKPOT,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget( 2));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate66Test(){
        //newGameInFightTrainerDual1
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(JACKPOT,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget( 2));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate67Test(){
        //newGameInFightTrainerDual1
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(JACKPOT,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget( -1));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate68Test(){
        //newGameInFightTrainerDual1
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(JACKPOT,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setFirstChosenMoveTarget(JACKPOT, TargetCoords.toUserTarget( -1));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate69Test(){
        //newGameInFightTrainerDual1
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(JACKPOT,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate70Test(){
        //newGameInFightTrainerDual1
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        cancelFoeArtificialIntelligence(fight_);
        CustList<StringMap<Integer>> movesFoe_ = new CustList<StringMap<Integer>>();
        StringMap<Integer> moves_ = new StringMap<Integer>();
        moves_.put(JACKPOT,  10);
        movesFoe_.add(moves_);
        movesFoe_.add(new StringMap<Integer>());
        movesFoe_.add(new StringMap<Integer>());
        replaceFoeMoves(fight_, movesFoe_);
        FightArtificialIntelligence.choiceArtificialIntelligence(fight_, diff_, data_);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate71Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate72Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate73Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate74Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate75Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        Player player_ = game_.getPlayer();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate76Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setFirstChosenMoveTarget(JACKPOT, tc(KEY_FOE, POKEMON_TARGET_ZERO));
        Player player_ = game_.getPlayer();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate77Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        game_.chooseSubstituteFighter( 0, data_);
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate78Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        game_.chooseSubstituteFighter( 0, data_);
        assertTrue(FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate79Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        game_.chooseSubstituteFighter( 0, data_);
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate80Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        game_.chooseSubstituteFighter( 0, data_);
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate81Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        game_.chooseSubstituteFighter( 0, data_);
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate82Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        game_.chooseSubstituteFighter( 0, data_);
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate83Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.initTrainerFight(data_);
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, game_.getPlayer(), game_.getDifficulty()));
    }
    @Test
    public void validate84Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, game_.getDifficulty(), data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.initTrainerFight(data_);
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, game_.getPlayer(), game_.getDifficulty()));
    }
    @Test
    public void validate85Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate86Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).exitFrontBattle();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate87Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setMult( 0);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate88Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setMult( 0);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate89Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setMult( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate90Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setMult( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate91Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setMult( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate92Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setMult( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate93Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setMult( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate94Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setMult( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate95Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setPlayerMaxNumberFrontFighters( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate96Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setPlayerMaxNumberFrontFighters( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate97Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getTeams().removeKey(Fight.CST_FOE);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate98Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getTeams().removeKey(Fight.CST_FOE);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate99Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getTeams().removeKey(Fight.CST_PLAYER);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate100Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getTeams().removeKey(Fight.CST_PLAYER);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate101Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        //invalid data_
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate102Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        //invalid data_
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate103Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setNbFleeAttempt( -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate104Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setNbFleeAttempt( -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate105Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setNbRounds(new LgInt("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate106Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setNbRounds(new LgInt("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate107Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setWinningMoney(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate108Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().setWinningMoney(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate109Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getCaughtEvolutions().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate110Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getCaughtEvolutions().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate111Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getLostObjects().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate112Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getLostObjects().add(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate113Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getUsedItemsWhileRound().put(INVALID_DATA_KEY,1L);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate114Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getUsedItemsWhileRound().put(INVALID_DATA_KEY,1L);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate115Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        player_.getItem(BAIE_ORAN);
        //invalid data_
        game_.getFight().getUsedItemsWhileRound().put(BAIE_ORAN,-1L);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate116Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        player_.getItem(BAIE_ORAN);
        //invalid data_
        game_.getFight().getUsedItemsWhileRound().put(BAIE_ORAN,-1L);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate117Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setWeight(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate118Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setWeight(new Rate("-1"));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate119Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        player_.setChosenTeamPokemon( 1);
        player_.switchTeamOrder( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate120Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        //invalid data_
        player_.setChosenTeamPokemon( 1);
        player_.switchTeamOrder( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate121Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getUserTeam().getPlayerFightersAgainstFoe().put( 2, new Ints());
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate122Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getUserTeam().getPlayerFightersAgainstFoe().put( 2, new Ints());
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate123Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFirstPositFoeFighters().put( 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate124Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFirstPositFoeFighters().put( 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate125Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFirstPositPlayerFighters().put( 4, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate126Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFirstPositPlayerFighters().put( 4, Fighter.BACK);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate127Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFirstPositFoeFighters().put( 1,  2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate128Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFirstPositFoeFighters().put( 1,  2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate129Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFirstPositPlayerFighters().put( 3,  2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate130Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFirstPositPlayerFighters().put( 3,  2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate131Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate132Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate133Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getStillEnabledMoves().put(GRAVITE, BoolVal.FALSE);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate134Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getStillEnabledMoves().put(GRAVITE, BoolVal.FALSE);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate135Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getEnabledMoves().getVal(GRAVITE).setNbTurn( -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate136Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getEnabledMoves().getVal(GRAVITE).setNbTurn( -1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate137Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setGroundPlace( 0);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setGroundPlaceSubst( 0);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate138Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setGroundPlace( 0);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setGroundPlaceSubst( 0);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate139Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).setGroundPlace( 1);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate140Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual1(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).setGroundPlace( 1);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_THREE).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate141Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).setSubstitute( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate142Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE).setSubstitute( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate143Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setSubstitute( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate144Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setSubstitute( 2);
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).setSubstitute( 2);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate145Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlace( 1);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate146Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlace( 1);
        //fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate147Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setGroundPlace( 1);
        //fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate148Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setGroundPlace( 1);
        //fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate149Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate150Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate151Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate152Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainer4(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO).setGroundPlaceSubst( 1);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate153Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.setCurrentUser(tp(KEY_FOE, POKEMON_FIGHTER_ZERO));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
        //invalid data_
    }
    @Test
    public void validate154Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.setCurrentUser(tp(KEY_FOE, POKEMON_FIGHTER_ZERO));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
        //invalid data_
    }
    @Test
    public void validate155Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        ActionMove actionMove_ = (ActionMove) fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        actionMove_.setFinalChosenMove(CHARGE);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate156Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        ActionMove actionMove_ = (ActionMove) fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        actionMove_.setFinalChosenMove(CHARGE);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate157Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setActed(false);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate158Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setActed(false);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate159Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).choisirAttaqueFin();
        fight_.setCurrentUser(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).setActed(true);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate160Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).setFirstChosenMoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ONE));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).choisirAttaqueFin();
        fight_.setCurrentUser(tp(KEY_PLAYER, POKEMON_FIGHTER_TWO));
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO).setActed(true);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate161Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(INVALID_DATA_KEY, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
        //invalid data_
    }
    @Test
    public void validate162Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(INVALID_DATA_KEY, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
        //invalid data_
    }
    @Test
    public void validate163Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //invalid data_
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(INVALID_DATA_KEY, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
        //invalid data_
    }
    @Test
    public void validate164Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //invalid data_
        fight_.getAllyChoice().put(new MoveTarget(DEMI_TOUR, tc(KEY_FOE, POKEMON_TARGET_ONE)), new MoveTarget(INVALID_DATA_KEY, tc(KEY_FOE, POKEMON_TARGET_ONE)));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
        //invalid data_
    }
    @Test
    public void validate165Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getChoices().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate166Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getChoices().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate167Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getMovesAbilitiesEvos().clear();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getMovesToBeLearnt().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate168Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getMovesAbilitiesEvos().clear();
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getMovesToBeLearnt().clear();
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate169Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getChoices().getVal( 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate170Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getChoices().getVal( 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate171Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        //invalid data_
        fight_.getChoices().getVal( 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate172Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        //invalid data_
        fight_.getChoices().getVal( 0).getKeptMoves().add(ZENITH);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate173Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getChoices().getVal( 0).setAbility(PRESSION);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate174Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getChoices().getVal( 0).setAbility(PRESSION);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate175Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.FALSE);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate176Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.FALSE);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate177Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate178Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate179Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.setState(FightState.ATTAQUES);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate180Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.setState(FightState.ATTAQUES);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate181Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.setState(FightState.SWITCH_PROPOSE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate182Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.setState(FightState.SWITCH_PROPOSE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate183Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate184Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.setState(FightState.SWITCH_WHILE_KO_USER);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate185Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.FALSE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate186Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.FALSE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate187Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate188Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate189Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate190Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate191Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put( 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put( 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate192Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put( 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put( 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate193Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.getFirstPositFoeFighters().put( 0,  0);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate194Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.getFirstPositFoeFighters().put( 0,  0);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate195Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put( 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put( 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate196Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositPlayerFighters().put( 1, Fighter.BACK);
        fight_.getFirstPositPlayerFighters().put( 2, Fighter.BACK);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate197Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositFoeFighters().put( 0,  0);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate198Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFirstPositFoeFighters().put( 0,  0);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate199Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate200Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate201Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
        //fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
//        game_.chooseFrontFighter( 0, data_);
//        game_.changeAction(ActionType.MOVE, data_);
//        game_.chooseMove(DEMI_TOUR, data_);
//        game_.setFirstChosenMoveFoeTarget( 0);
//        game_.roundAllThrowers(data_, false);

    }
    @Test
    public void validate202Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE).getRemainingHp().affectZero();
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
        //fight_.setState(FightState.SWITCH_APRES_ATTAQUE);
//        game_.chooseFrontFighter( 0, data_);
//        game_.changeAction(ActionType.MOVE, data_);
//        game_.chooseMove(DEMI_TOUR, data_);
//        game_.setFirstChosenMoveFoeTarget( 0);
//        game_.roundAllThrowers(data_, false);

    }
    @Test
    public void validate203Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getCatchingBalls().first().setCatchingBall(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate204Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getCatchingBalls().first().setCatchingBall(INVALID_DATA_KEY);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate205Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getCatchingBalls().first().setCatchingBall(BAIE_ORAN);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate206Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getCatchingBalls().first().setCatchingBall(BAIE_ORAN);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate207Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate208Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate209Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate210Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate211Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate212Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.setFightType(FightType.DRESSEUR);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate213Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        player_.useInInventory(MASTER_BALL);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate214Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        player_.useInInventory(MASTER_BALL);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate215Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate216Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate217Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.setState(FightState.REDESSIN_SCENE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate218Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight3(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        //invalid data_
        fight_.setState(FightState.REDESSIN_SCENE);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate219Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getChoices().getVal( 0).setName(PIKACHU);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate220Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getChoices().getVal( 0).setName(PIKACHU);
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate221Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setAction(null);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate222Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).setAction(null);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate223Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.setCurrentUser(Fight.toUserFighter( 6));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate224Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual6(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.setCurrentUser(Fight.toUserFighter( 6));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate225Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.getAllyChoice().clear();
        fight_.getAllyChoice().addEntry(new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()),new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate226Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual7(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(JACKPOT, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        //this round is simulated (possible round)
        FightRound.beginRound(fight_, diff_, data_);
        Fighter fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.usePowerPointsByMove(diff_,JACKPOT, 1);
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_PLAYER, POKEMON_FIGHTER_TWO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(BROUHAHA);
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.setActed(true);
        fighter_ =fight_.getFighter(KEY_FOE, POKEMON_FIGHTER_ONE);
        fighter_.affectNoRoundBeforeUsingMove();
        fighter_.setDisappeared(false);
        fighter_.ajouterAttaquesDejaInvoqueesTour(fighter_.getFinalChosenMove());
        fighter_.setLastUsedMove(fighter_.getFinalChosenMove());
        fighter_.successUsingMove();
        fight_.getFoeTeam().addSuccessfulMoveRound(fighter_.getFinalChosenMove());
        fighter_.incrementConsecutiveUsesMove();
        FightKo.setKoMoveTeams(fight_, tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), diff_, data_);
        FightKo.setKoMoveTeams(fight_, tp(KEY_FOE, POKEMON_FIGHTER_ZERO), diff_, data_);
        fighter_.setActed(true);
        FightRound.endRoundShowActions(fight_, diff_, player_, data_);
        //invalid data_
        fight_.getAllyChoice().clear();
        fight_.getAllyChoice().addEntry(new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()),new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate227Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getAllyChoice().clear();
        fight_.getAllyChoice().addEntry(new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()),new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate228Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual4(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TETARTE);
        //invalid data_
        fight_.getAllyChoice().clear();
        fight_.getAllyChoice().addEntry(new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()),new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()));
        assertTrue(!FightFacade.validate(game_.getFight(), data_, player_, diff_));
    }
    @Test
    public void validate229Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.GIRL, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getAllyChoice().clear();
        fight_.getAllyChoice().addEntry(new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()),new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate230Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFightTrainerDual2(Sex.BOY, diff_, data_);
        PkTrainer pk_ = new PkTrainer();
        pk_.setAbility(MOITEUR);
        pk_.setItem(NULL_REF);
        pk_.setLevel( 1);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setName(PTITARD);
        pk_.setMoves(new StringList(PISTOLET_A_O, CHARGE));
        addBackFoeFighter(pk_, game_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.MOVE, data_);
        game_.chooseMove(BROUHAHA, data_);
        game_.roundAllThrowers(data_, false);
        //invalid data_
        fight_.getAllyChoice().clear();
        fight_.getAllyChoice().addEntry(new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()),new MoveTarget(INVALID_DATA_KEY,TargetCoords.def()));
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate231Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getCatchingBalls().clear();
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate232Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getCatchingBalls().clear();
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate233Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getCatchingBalls().clear();
        fight_.setMult( 127);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate234Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        //invalid data_
        fight_.getCatchingBalls().clear();
        fight_.setMult( 127);
        assertTrue(!FightFacade.validate(fight_, data_, player_, diff_));
    }
    @Test
    public void validate235Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.GIRL, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        fight_.getCatchingBalls().first().setPlayer( -1);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
        assertEq(POKEMON_FIGHTER_ZERO,fight_.getCatchingBalls().first().getPlayer());
    }
    @Test
    public void validate236Test(){
        DataBase data_ = initDb();
        Difficulty diff_ = new Difficulty();
        Game game_ = newGameInFight2(Sex.BOY, diff_, data_);
        Player player_ = game_.getPlayer();
        Fight fight_ = game_.getFight();
        fight_.getCatchingBalls().first().setCatchingBall(MASTER_BALL);
        fight_.getCatchingBalls().first().setPlayer(POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        fight_.getCatchingBalls().first().setPlayer( -1);
        assertTrue(FightFacade.validate(fight_, data_, player_, diff_));
        assertEq(POKEMON_FIGHTER_ZERO,fight_.getCatchingBalls().first().getPlayer());
    }
    private static void addBackFoeFighter(PkTrainer _pk, Game _game, DataBase _data) {
        Fight fight_ = _game.getFight();
        Fighter fighter_ = new Fighter(_pk, _data, Fighter.BACK);
        fighter_.initIvAdv(_game.getDifficulty(),NULL_REF);
        fighter_.setRemainingHp(fighter_.pvMax());
        IntMap<Fighter> team_ = fight_.getFoeTeam().getMembers();
        TeamPosition key_ = Fight.toFoeFighter( team_.size());
        fight_.getFirstPositFoeFighters().put( team_.size(), Fighter.BACK);
        team_.put( team_.size(), fighter_);
        TeamPositionList fs_ = FightOrder.fighters(fight_);
        fighter_.initCreatureRelationsAutre(fs_, _data);
        FightFacade.ajouterRelationAutre(_data, fight_, key_, fs_);
    }

    private static void cancelFoeArtificialIntelligence(Fight _fight) {
        FightFacade.cancelActions(_fight,FightOrder.fighters(_fight));
//        for (TeamPosition t: FightOrder.fighters(_fight)) {
//            _fight.getFighter(t).cancelActions();
//        }
    }

    private static void cancelAllyArtificialIntelligence(Fight _fight) {
        _fight.getAllyChoice().clear();
    }

    private static void replaceFoeMoves(Fight _fight, CustList<StringMap<Integer>> _moves) {
        Team.replace(_moves, _fight.getFoeTeam());
    }

    private static void replacePlayerMoves(Fight _fight, CustList<StringMap<Integer>> _moves) {
        Team.replace(_moves, _fight.getUserTeam());
    }

    private static Game newGameInFightTrainerDual8(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.getMoves().put(DEMI_TOUR, new UsesOfMove(10));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainerDual7(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainerDual5(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        Pokemon pk_ = new WildPk();
        pk_.setName(TETARTE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 26);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, DANSE_PLUIE, _data);
//        pkPl_.getMoves().put(BROUHAHA, new UsesOfMove(10));
        pkPl_.getWonExpSinceLastLevel().addNb(new Rate("49"));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainerDual4(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, COGNOBIDON, _data);
//        pkPl_.getMoves().put(BROUHAHA, new UsesOfMove(10));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainerDual3(Sex _sex, Difficulty _diff, DataBase _data) {
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
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, FAUX_CHAGE, _data);
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove(10));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainerDual2(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, HATE, _data);
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove(10));
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainerDual1(Sex _sex, Difficulty _diff, DataBase _data) {
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
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainerDual6(Sex _sex, Difficulty _diff, DataBase _data) {
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
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(DEMI_TOUR, FAUX_CHAGE, _data);
        //game_.getPlayerOrientation() == UP
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainer4(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
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
//        game_.moving(Direction.DOWN, data_);
//        game_.moving(Direction.DOWN, data_);
//        game_.moving(Direction.RIGHT, data_);
//        game_.moving(Direction.RIGHT, data_);
//        game_.moving(Direction.DOWN, data_);
        //trainer: 0, 0, 1, 1
        game_.initTrainerFight(_data);
        return game_;
    }

    private static Game newGameInFightTrainer3(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        game_.getPlayer().recupererOeufPensions(new Egg(PTITARD));
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 2);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(BROUHAHA, HATE, _data);
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove(10));
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

    private static Game newGameInFightTrainer2(Sex _sex, Difficulty _diff, DataBase _data) {
        Game game_ = new Game(_data);
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove(10));
        pkPl_.learnMove(BROUHAHA, HATE, _data);
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

    private static Game newGameInFightTrainer1(Sex _sex, Difficulty _diff, DataBase _data) {
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

    private static Game newGameInFight3(Sex _sex, Difficulty _diff, DataBase _data) {
        _data.initializeWildPokemon();
        Game game_ = new Game(_data);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(MASTER_BALL);
        game_.getPlayer().getItem(REPOUSSE);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(_sex.getGender());
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, _diff, _data);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        //pkPl_.getMoves().put(BROUHAHA, new UsesOfMove(10));
        pkPl_.learnMove(BROUHAHA, HATE, _data);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.RIGHT, _data);
        game_.moving(Direction.RIGHT, _data);
        return game_;
    }

    private static Game newGameInFight2(Sex _sex, Difficulty _diff, DataBase _data) {
        _data.initializeWildPokemon();
        Game game_ = new Game(_data);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, _sex, _diff, _data);
        game_.getPlayer().getItem(MASTER_BALL);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.DOWN, _data);
        game_.moving(Direction.RIGHT, _data);
        game_.moving(Direction.RIGHT, _data);
        return game_;
    }

    private static Game newGameInFight(Sex _sex, Difficulty _diff, DataBase _data) {
        _data.initializeWildPokemon();
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
}