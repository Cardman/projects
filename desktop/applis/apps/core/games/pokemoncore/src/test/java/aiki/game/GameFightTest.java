package aiki.game;

import aiki.db.DataBase;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.db.ImageHeroKey;
import aiki.game.fight.enums.ActionType;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.characters.DualFight;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.enums.Direction;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Road;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.Rate;


public class GameFightTest extends InitializationDataBase {


    @Test
    public void getTrainerImage1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Coords closest_ = game_.closestTile(data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace((short) 0);
        LevelRoad l_ = road_.getLevelRoad();
        TrainerMultiFights t_ = (TrainerMultiFights) l_.getCharacters().getVal(closest_.getLevel().getPoint());
        String fileName_ = t_.getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage2(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace((short) 2);
        LevelRoad l_ = road_.getLevelRoad();
        DualFight t_ = l_.getDualFights().getVal(newPoint(2, 0));
        String fileName_ = t_.getFoeTrainer().getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage3(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace((short) 2);
        LevelRoad l_ = road_.getLevelRoad();
        DualFight t_ = l_.getDualFights().getVal(newPoint(2, 0));
        String fileName_ = t_.getFoeTrainer().getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage4(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Coords closest_ = game_.closestTile(data_.getMap());
        City city_ = (City) data_.getMap().getPlace((short) 1);
        LevelIndoorGym l_ = (LevelIndoorGym) city_.getLevelByCoords(closest_);
        GymLeader t_ = l_.getGymLeader();
        String fileName_ = t_.getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage5(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 6));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Coords closest_ = game_.closestTile(data_.getMap());
        City city_ = (City) data_.getMap().getPlace((short) 1);
        LevelIndoorGym l_ = (LevelIndoorGym) city_.getLevelByCoords(closest_);
        GymTrainer t_ = l_.getGymTrainers().getVal(newPoint(1, 7));
        String fileName_ = t_.getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage6(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Coords closest_ = game_.closestTile(data_.getMap());
        League city_ = (League) data_.getMap().getPlace((short) 6);
        LevelLeague l_ = city_.getLevelLeague(closest_.getLevel().getLevelIndex());
        TrainerLeague t_ = l_.getTrainer();
        String fileName_ = t_.getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage7(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(new int[0][0],game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage8(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace((short) 2);
        LevelRoad l_ = road_.getLevelRoad();
        DualFight t_ = l_.getDualFights().getVal(newPoint(4, 0));
        String fileName_ = t_.getFoeTrainer().getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage9(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 5, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace((short) 2);
        LevelRoad l_ = road_.getLevelRoad();
        DualFight t_ = l_.getDualFights().getVal(newPoint(4, 0));
        String fileName_ = t_.getFoeTrainer().getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getBackHeros1Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.GIRL));
        assertEq(exp_, game_.getBackHeros(data_));
    }
    @Test
    public void getBackHeros2Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.BOY));
        assertEq(exp_, game_.getBackHeros(data_));
    }
    @Test
    public void getBackHeros3Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.GIRL));
        assertEq(exp_, game_.getBackHeros(data_));
    }
    @Test
    public void getBackHeros4Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.BOY));
        assertEq(exp_, game_.getBackHeros(data_));
    }
    @Test
    public void getBackHerosSexOpposite1Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.GIRL.getOppositeSex()));
        assertEq(exp_, game_.getBackHerosSexOpposite(data_));
    }
    @Test
    public void getBackHerosSexOpposite2Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.BOY.getOppositeSex()));
        assertEq(exp_, game_.getBackHerosSexOpposite(data_));
    }
    @Test
    public void getBackHerosSexOpposite3Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.GIRL.getOppositeSex()));
        assertEq(exp_, game_.getBackHerosSexOpposite(data_));
    }
    @Test
    public void getBackHerosSexOpposite4Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.BOY.getOppositeSex()));
        assertEq(exp_, game_.getBackHerosSexOpposite(data_));
    }
    @Test
    public void chooseFrontFighter1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(Fighter.BACK, game_.getFight().getChosenIndexBack());
        assertEq(0, game_.getFight().getChosenIndexFront());
        assertEq(0, game_.getFight().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getChosablePlayerTargets().size());
    }
    @Test
    public void chooseMove1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertEq(SEISME, ((ActionMove)action_).getFirstChosenMove());
        assertEq(0, ((ActionMove)action_).getChosenTargets().size());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, game_.getFight().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getChosablePlayerTargets().size());
    }
    @Test
    public void setFirstChosenMoveFoeTarget1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(CHARGE, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(CHARGE, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertEq(CHARGE, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(POKEMON_FOE_TARGET_ZERO, ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(2, game_.getFight().getChosableFoeTargets().size());
        assertEq(PIKACHU,getName(game_.getFight().getChosableFoeTargets(), 0));
        assertEq(0,getKey(game_.getFight().getChosableFoeTargets(), 0));
        assertEq(PIKACHU,getName(game_.getFight().getChosableFoeTargets(), 1));
        assertEq(1,getKey(game_.getFight().getChosableFoeTargets(), 1));
        assertTrue(getBoolVal(game_.getFight().getChosableFoeTargets(), 0));
        assertTrue(getBoolVal(game_.getFight().getChosableFoeTargets(), 1));
        assertEq(2, game_.getFight().getChosablePlayerTargets().size());
        assertTrue(!getBoolVal(game_.getFight().getChosablePlayerTargets(), 0));
        assertTrue(getBoolVal(game_.getFight().getChosablePlayerTargets(), 1));
    }

    private String getName(CustList<ChosableTargetName> _chosablePlayerTargets, int _i) {
        return _chosablePlayerTargets.get(_i).getName();
    }

    private byte getKey(CustList<ChosableTargetName> _chosablePlayerTargets, int _i) {
        return _chosablePlayerTargets.get(_i).getKey();
    }

    private boolean getBoolVal(CustList<ChosableTargetName> _chosablePlayerTargets, int _i) {
        return _chosablePlayerTargets.get(_i).getChosable() == BoolVal.TRUE;
    }

    @Test
    public void setFirstChosenMovePlayerTarget1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(CHARGE, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(CHARGE, data_);
        game_.setFirstChosenMovePlayerTarget((byte) 1);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertEq(CHARGE, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(POKEMON_PLAYER_TARGET_ONE, ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(2, game_.getFight().getChosableFoeTargets().size());
        assertTrue(getBoolVal(game_.getFight().getChosableFoeTargets(), 0));
        assertTrue(getBoolVal(game_.getFight().getChosableFoeTargets(), 1));
        assertEq(2, game_.getFight().getChosablePlayerTargets().size());
        assertTrue(!getBoolVal(game_.getFight().getChosablePlayerTargets(), 0));
        assertTrue(getBoolVal(game_.getFight().getChosablePlayerTargets(), 1));
    }
    @Test
    public void changeAction1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(Fighter.BACK, game_.getFight().getChosenIndexBack());
        assertEq(0, game_.getFight().getChosenIndexFront());
        assertEq(ActionType.SWITCH, game_.getFight().getSelectedActionCurFighter());
        assertEq(0, game_.getFight().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getChosablePlayerTargets().size());
    }
    @Test
    public void chooseBackFighter1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        game_.chooseBackFighter((byte) 0, data_);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertEq(1, ((ActionSwitch) action_).getSubstitute());
        assertEq(Fighter.BACK, game_.getFight().getChosenIndexBack());
        assertEq(Fighter.BACK, game_.getFight().getChosenIndexFront());
        assertEq(ActionType.NOTHING, game_.getFight().getSelectedActionCurFighter());
        assertEq(0, game_.getFight().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getChosablePlayerTargets().size());
    }
    @Test
    public void deselect1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.deselect();
        AbstractAction action_;
        action_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertTrue(action_ == null);
        assertEq(Fighter.BACK, game_.getFight().getChosenIndexBack());
        assertEq(Fighter.BACK, game_.getFight().getChosenIndexFront());
        assertEq(ActionType.NOTHING, game_.getFight().getSelectedActionCurFighter());
        assertEq(0, game_.getFight().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getChosablePlayerTargets().size());
    }
    @Test
    public void setChosenHealingItem1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
//        Pokemon pk_ = new WildPk();
//        pk_.setName(NINJASK);
//        pk_.setItem(MULTI_EXP);
//        pk_.setGender(Gender.NO_GENDER);
//        pk_.setAbility(ABSORB_EAU);
//        pk_.setLevel((short) 100);
//        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
//        game_.getPlayer().setChosenTeamPokemon((short) 0);
//        game_.getPlayer().switchTeamOrder((short) 1);
//        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
//        pkPl_.learnMove(SEISME, HATE, data);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.changeAction(ActionType.HEALING, data_);
        game_.setChosenHealingItem(EAU_FRAICHE, data_);
        //game_.deselect();
        AbstractAction action_;
        action_ = game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).getAction();
        assertEq(Fighter.BACK, game_.getFight().getChosenIndexBack());
        assertEq(0, game_.getFight().getChosenIndexFront());
        assertEq(ActionType.HEALING, game_.getFight().getSelectedActionCurFighter());
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertEq(0, game_.getFight().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getChosablePlayerTargets().size());
    }
    @Test
    public void calculateCatchingRates1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.getPlayer().getItem(MASTER_BALL);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        NatStringTreeMap<BallNumberRate> balls_ = game_.calculateCatchingRates(data_);
        assertEq(1, balls_.size());
        BallNumberRate ball_ = balls_.getVal(MASTER_BALL);
        assertEq(MASTER_BALL, ball_.getName());
        assertEq(LgInt.one(), ball_.getNumber());
        assertEq(Rate.one(), ball_.getRate());
        assertEq("100", ball_.getPercent());
    }
    @Test
    public void setSubstituteEndRound1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.chooseBackFighter((byte) 0, data_);
        game_.setSubstituteEndRound((byte) 0);
        assertEq(Fighter.BACK, game_.getFight().getChosenIndexBack());
        assertEq(Fighter.BACK, game_.getFight().getChosenIndexFront());
        assertEq(0, game_.getFight().getFirstPositPlayerFighters().getVal((byte) 0));
        assertEq(0, game_.getFight().getFirstPositPlayerFighters().getVal((byte) 1));
    }
    @Test
    public void remainingThrowersTargetsHp1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        ObjectMap<TeamPosition,StringMap<ObjectMap<TeamPosition,Rate>>> map_;
        map_ = game_.remainingThrowersTargetsHp(data_);
        assertEq(2, map_.size());
        assertEq(3, map_.getVal(POKEMON_PLAYER_FIGHTER_ZERO).size());
        assertEq(1, map_.getVal(POKEMON_PLAYER_FIGHTER_ONE).size());
        StringMap<ObjectMap<TeamPosition,Rate>> mapMoves_;
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ZERO);
        assertEq(3, mapMoves_.getVal(SEISME).size());
        assertEq(3, mapMoves_.getVal(PLAIE_CROIX).size());
        assertEq(3, mapMoves_.getVal(GIGA_SANGSUE).size());
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ONE);
        assertEq(3, mapMoves_.getVal(JACKPOT).size());
        ObjectMap<TeamPosition,Rate> mapFighters_;
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ZERO);
        mapFighters_ = mapMoves_.getVal(SEISME);
        assertEq(3, mapFighters_.size());
        assertEq(Rate.zero(), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("263"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(Rate.zero(), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        mapFighters_ = mapMoves_.getVal(PLAIE_CROIX);
        assertEq(3, mapFighters_.size());
        assertEq(Rate.zero(), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("263"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(new Rate("3037/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        mapFighters_ = mapMoves_.getVal(GIGA_SANGSUE);
        assertEq(Rate.zero(), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("263"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(new Rate("3037/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
        mapMoves_ = map_.getVal(POKEMON_PLAYER_FIGHTER_ONE);
        mapFighters_ = mapMoves_.getVal(JACKPOT);
        assertEq(3, mapFighters_.size());
        assertEq(new Rate("76457/1875"), mapFighters_.getVal(POKEMON_FOE_FIGHTER_ZERO));
        assertEq(new Rate("263"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ZERO));
        assertEq(new Rate("3037/100"), mapFighters_.getVal(POKEMON_PLAYER_FIGHTER_ONE));
    }
    @Test
    public void sortedFightersBeginRoundWildFight1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.getPlayer().getItem(MASTER_BALL);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        NatStringTreeMap<EqList<TeamPosition>> map_;
        map_ = game_.sortedFightersBeginRoundWildFight(data_);
        assertEq(1, map_.size());
        assertEq(1, map_.getVal(JACKPOT).size());
        EqList<TeamPosition> mapMoves_;
        mapMoves_ = map_.getVal(JACKPOT);
        assertEq(POKEMON_FOE_FIGHTER_ZERO, mapMoves_.first());
    }
    @Test
    public void sortedFightersBeginRoundWildFight2(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.getPlayer().getItem(MASTER_BALL);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(JACKPOT, data_);
        NatStringTreeMap<EqList<TeamPosition>> map_;
        map_ = game_.sortedFightersBeginRoundWildFight(data_);
        assertEq(1, map_.size());
        assertEq(2, map_.getVal(JACKPOT).size());
        EqList<TeamPosition> mapMoves_;
        mapMoves_ = map_.getVal(JACKPOT);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, mapMoves_.first());
        assertEq(POKEMON_FOE_FIGHTER_ZERO, mapMoves_.last());
    }
    @Test
    public void sortedFightersBeginRound1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        EqList<TeamPosition> tree_;
        tree_ = game_.sortedFightersBeginRound(data_);
        assertEq(POKEMON_PLAYER_FIGHTER_ZERO, tree_.first());
        assertEq(POKEMON_FOE_FIGHTER_ZERO, tree_.last());
    }
    @Test
    public void getPlayerTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        CustList< Fighter> team_ = game_.getPlayerTeam();
        assertEq(2, team_.size());
        assertSame(team_.get((byte) 0), game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
        assertSame(team_.get((byte) 1), game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE));
    }
    @Test
    public void getFoeFrontTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        ByteTreeMap< Fighter> team_ = game_.getFoeFrontTeam();
        assertEq(1, team_.size());
        assertSame(team_.getVal((byte) 0), game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ZERO));
    }
    @Test
    public void getUnionFrontTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        ByteTreeMap< Fighter> team_ = game_.getUnionFrontTeam();
        assertEq(1, team_.size());
        assertSame(team_.getVal((byte) 0), game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }
    @Test
    public void getPlayerFrontTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        CustList< Fighter> team_ = game_.getPlayerFrontTeam();
        assertEq(1, team_.size());
        assertSame(team_.get((byte) 0), game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }
    @Test
    public void getPlayerBackTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        CustList< Fighter> team_ = game_.getPlayerBackTeam();
        assertEq(1, team_.size());
        assertSame(team_.get((byte) 0), game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE));
    }
    @Test
    public void getPlayerFrontTeamForSubstituting1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        CustList< Fighter> team_ = game_.getPlayerFrontTeamForSubstituting();
        assertEq(1, team_.size());
        assertSame(team_.get((byte) 0), game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO));
    }
    @Test
    public void getPlayerBackTeamForSubstituting1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        CustList< Fighter> team_ = game_.getPlayerBackTeamForSubstituting();
        assertEq(1, team_.size());
        assertSame(team_.get((byte) 0), game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ONE));
    }
    @Test
    public void isChosableForLearningAndEvolving1Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        assertTrue(game_.isChosableForLearningAndEvolving((byte) 0));
    }
    @Test
    public void isChosableForLearningAndEvolving2Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        assertTrue(!game_.isChosableForLearningAndEvolving((byte) 1));
    }
    @Test
    public void choosePokemonForLearningAndEvolving1Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        assertEq(0, game_.getChosenIndex());
        NatStringTreeMap<Boolean> moves_ = game_.getMoves();
        assertEq(4, moves_.size());
        assertTrue(moves_.getVal(COUD_BOUE));
        assertTrue(moves_.getVal(GRIFFE_ACIER));
        assertTrue(moves_.getVal(SEISME));
        assertTrue(moves_.getVal(TUNNEL));
        TreeMap<String,Boolean> evolutions_ = game_.getEvolutions();
        assertEq(3, evolutions_.size());
        assertTrue(evolutions_.getVal(NULL_REF));
        assertTrue(!evolutions_.getVal(NINJASK));
        assertTrue(!evolutions_.getVal(MUNJA));
        StringList abilities_ = game_.getAbilities();
        assertEq(1, abilities_.size());
        assertEq(ABSORB_EAU, abilities_.get(0));
        assertEq(NULL_REF, game_.getAbility());
    }
    @Test
    public void setEvolution1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        game_.setEvolution(NINJASK);
        assertEq(0, game_.getChosenIndex());
        NatStringTreeMap<Boolean> moves_ = game_.getMoves();
        assertEq(20, moves_.size());
        assertTrue(!moves_.getVal(ARMURE));
        assertTrue(!moves_.getVal(COMBO_GRIFFE));
        assertTrue(moves_.getVal(COUD_BOUE));
        assertTrue(!moves_.getVal(DANSE_LAMES));
        assertTrue(!moves_.getVal(GIGA_SANGSUE));
        assertTrue(!moves_.getVal(GRIFFE));
        assertTrue(moves_.getVal(GRIFFE_ACIER));
        assertTrue(!moves_.getVal(GRINCEMENT));
        assertTrue(!moves_.getVal(HATE));
        assertTrue(!moves_.getVal(JET_DE_SABLE));
        assertTrue(!moves_.getVal(LIRE_ESPRIT));
        assertTrue(!moves_.getVal(PIQURE));
        assertTrue(!moves_.getVal(PLAIE_CROIX));
        assertTrue(!moves_.getVal(REFLET));
        assertTrue(!moves_.getVal(RELAIS));
        assertTrue(moves_.getVal(SEISME));
        assertTrue(!moves_.getVal(TAILLADE));
        assertTrue(!moves_.getVal(TRANCHE));
        assertTrue(moves_.getVal(TUNNEL));
        assertTrue(!moves_.getVal(VAMPIRISME));
        TreeMap<String,Boolean> evolutions_ = game_.getEvolutions();
        assertEq(3, evolutions_.size());
        assertTrue(!evolutions_.getVal(NULL_REF));
        assertTrue(evolutions_.getVal(NINJASK));
        assertTrue(!evolutions_.getVal(MUNJA));
        StringList abilities_ = game_.getAbilities();
        assertEq(1, abilities_.size());
        assertEq(TURBO, abilities_.get(0));
//        assertEq(NULL_REF, game_.getAbility());
        assertEq(TURBO, game_.getAbility());
    }
    @Test
    public void setAbility1(){
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
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        game_.setEvolution(TARINORME);
        game_.setAbility(MAGNEPIEGE);
        assertEq(0, game_.getChosenIndex());
        NatStringTreeMap<Boolean> moves_ = game_.getMoves();
        assertEq(17, moves_.size());
        assertTrue(!moves_.getVal(BOMBAIMANT));
        assertTrue(!moves_.getVal(CAGE_ECLAIR));
        assertTrue(!moves_.getVal(CHARGE));
        assertTrue(!moves_.getVal(COUP_D_JUS));
        assertTrue(!moves_.getVal(EBOULEMENT));
        assertTrue(moves_.getVal(ELECANON));
        assertTrue(!moves_.getVal(GRAVITE));
        assertTrue(moves_.getVal(LAME_DE_ROC));
        assertTrue(!moves_.getVal(LIRE_ESPRIT));
        assertTrue(!moves_.getVal(MUR_DE_FER));
        assertTrue(!moves_.getVal(RAYON_GEMME));
        assertTrue(!moves_.getVal(REGARD_NOIR));
        assertTrue(!moves_.getVal(REPOS));
        assertTrue(moves_.getVal(SEISME));
        assertTrue(moves_.getVal(TELLURIFORCE));
        assertTrue(!moves_.getVal(TEMPETESABLE));
        assertTrue(!moves_.getVal(VOL_MAGNETIK));
        TreeMap<String,Boolean> evolutions_ = game_.getEvolutions();
        assertEq(2, evolutions_.size());
        assertTrue(!evolutions_.getVal(NULL_REF));
        assertTrue(evolutions_.getVal(TARINORME));
        StringList abilities_ = game_.getAbilities();
        assertEq(2, abilities_.size());
        assertTrue(StringUtil.contains(abilities_, FERMETE));
        assertTrue(StringUtil.contains(abilities_, MAGNEPIEGE));
        assertEq(MAGNEPIEGE, game_.getAbility());
        //assertEq(TURBO, game_.getFight().getChoices().getVal((byte) 0).getAbility());
    }
    @Test
    public void addOrForgetMove1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel((short) 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        game_.setEvolution(NINJASK);
        game_.addOrForgetMove(PLAIE_CROIX);
        assertEq(0, game_.getChosenIndex());
        NatStringTreeMap<Boolean> moves_ = game_.getMoves();
        assertEq(20, moves_.size());
        assertTrue(!moves_.getVal(ARMURE));
        assertTrue(!moves_.getVal(COMBO_GRIFFE));
        assertTrue(moves_.getVal(COUD_BOUE));
        assertTrue(!moves_.getVal(DANSE_LAMES));
        assertTrue(!moves_.getVal(GIGA_SANGSUE));
        assertTrue(!moves_.getVal(GRIFFE));
        assertTrue(moves_.getVal(GRIFFE_ACIER));
        assertTrue(!moves_.getVal(GRINCEMENT));
        assertTrue(!moves_.getVal(HATE));
        assertTrue(!moves_.getVal(JET_DE_SABLE));
        assertTrue(!moves_.getVal(LIRE_ESPRIT));
        assertTrue(!moves_.getVal(PIQURE));
        assertTrue(moves_.getVal(PLAIE_CROIX));
        assertTrue(!moves_.getVal(REFLET));
        assertTrue(!moves_.getVal(RELAIS));
        assertTrue(moves_.getVal(SEISME));
        assertTrue(!moves_.getVal(TAILLADE));
        assertTrue(!moves_.getVal(TRANCHE));
        assertTrue(moves_.getVal(TUNNEL));
        assertTrue(!moves_.getVal(VAMPIRISME));
        TreeMap<String,Boolean> evolutions_ = game_.getEvolutions();
        assertEq(3, evolutions_.size());
        assertTrue(!evolutions_.getVal(NULL_REF));
        assertTrue(evolutions_.getVal(NINJASK));
        assertTrue(!evolutions_.getVal(MUNJA));
        StringList abilities_ = game_.getAbilities();
        assertEq(1, abilities_.size());
        assertEq(TURBO, abilities_.get(0));
    }


    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setInsideBuilding(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(new Point((short)_x, (short)_y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }}