package aiki.game;

import aiki.db.DataBase;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.util.*;
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
import code.maths.LgInt;
import code.maths.Rate;


public class GameFightTest extends InitializationDataBase {


    @Test
    public void getTrainerImage1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Coords closest_ = game_.closestTile(data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace( 0);
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
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace( 2);
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
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace( 2);
        LevelRoad l_ = road_.getLevelRoad();
        DualFight t_ = l_.getDualFights().getVal(newPoint(2, 0));
        String fileName_ = t_.getFoeTrainer().getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage4(){
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Coords closest_ = game_.closestTile(data_.getMap());
        City city_ = (City) data_.getMap().getPlace( 1);
        LevelIndoorGym l_ = (LevelIndoorGym) city_.getLevelByCoords(closest_);
        GymLeader t_ = l_.getGymLeader();
        String fileName_ = t_.getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage5(){
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 6));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Coords closest_ = game_.closestTile(data_.getMap());
        City city_ = (City) data_.getMap().getPlace( 1);
        LevelIndoorGym l_ = (LevelIndoorGym) city_.getLevelByCoords(closest_);
        GymTrainer t_ = l_.getGymTrainers().getVal(newPoint(1, 7));
        String fileName_ = t_.getImageMaxiFileName();
        int[][] img_ = data_.getTrainer(fileName_);
        assertEq(img_,game_.getTrainerImage(data_));
    }
    @Test
    public void getTrainerImage6(){
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal(1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal(1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal(3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal(3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Coords closest_ = game_.closestTile(data_.getMap());
        League city_ = (League) data_.getMap().getPlace( 6);
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
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
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
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace( 2);
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
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 5, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        Road road_ = (Road) data_.getMap().getPlace( 2);
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
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.GIRL)).getImage();
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
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.BOY)).getImage();
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
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.GIRL)).getImage();
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
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.BOY)).getImage();
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
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.GIRL.getOppositeSex())).getImage();
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
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.BOY.getOppositeSex())).getImage();
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
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.GIRL.getOppositeSex())).getImage();
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
        int[][] exp_ = data_.getBackHeros().getVal(new ImageHeroKey(EnvironmentType.ROAD, Sex.BOY.getOppositeSex())).getImage();
        assertEq(exp_, game_.getBackHerosSexOpposite(data_));
    }
    @Test
    public void chooseFrontFighter1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(Fighter.BACK, game_.getFight().getTemp().getChosenIndexBack());
        assertEq(0, game_.getFight().getTemp().getChosenIndexFront());
        assertEq(0, game_.getFight().getTemp().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getTemp().getChosablePlayerTargets().size());
    }
    @Test
    public void chooseMove1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        assertEq(SEISME, ((ActionMove)action_).getFirstChosenMove());
        assertEq(0, ((ActionMove)action_).getChosenTargets().size());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(0, game_.getFight().getTemp().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getTemp().getChosablePlayerTargets().size());
    }
    @Test
    public void setFirstChosenMoveFoeTarget1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(CHARGE, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(CHARGE, data_);
        game_.setFirstChosenMoveFoeTarget( 0);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        assertEq(CHARGE, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(tc(KEY_FOE, POKEMON_TARGET_ZERO), ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(2, game_.getFight().getTemp().getChosableFoeTargets().size());
        assertEq(PIKACHU,getName(game_.getFight().getTemp().getChosableFoeTargets(), 0));
        assertEq(0,getKey(game_.getFight().getTemp().getChosableFoeTargets(), 0));
        assertEq(PIKACHU,getName(game_.getFight().getTemp().getChosableFoeTargets(), 1));
        assertEq(1,getKey(game_.getFight().getTemp().getChosableFoeTargets(), 1));
        assertSame(BoolVal.TRUE, game_.getFight().getTemp().getChosableFoeTargets().get(0).getChosable());
        assertSame(BoolVal.TRUE, game_.getFight().getTemp().getChosableFoeTargets().get(1).getChosable());
        assertEq(2, game_.getFight().getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE, game_.getFight().getTemp().getChosablePlayerTargets().get(0).getChosable());
        assertSame(BoolVal.TRUE, game_.getFight().getTemp().getChosablePlayerTargets().get(1).getChosable());
    }

    private String getName(CustList<ChosableTargetName> _chosablePlayerTargets, int _i) {
        return _chosablePlayerTargets.get(_i).getName();
    }

    private int getKey(CustList<ChosableTargetName> _chosablePlayerTargets, int _i) {
        return _chosablePlayerTargets.get(_i).getKey();
    }

    @Test
    public void setFirstChosenMovePlayerTarget1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(CHARGE, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(CHARGE, data_);
        game_.setFirstChosenMovePlayerTarget( 1);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        assertEq(CHARGE, ((ActionMove)action_).getFirstChosenMove());
        assertEq(1, ((ActionMove)action_).getChosenTargets().size());
        assertEq(tc(KEY_PLAYER, POKEMON_TARGET_ONE), ((ActionMove)action_).getChosenTargets().first());
        assertEq(Fighter.BACK, ((ActionMove)action_).getSubstitute());
        assertEq(2, game_.getFight().getTemp().getChosableFoeTargets().size());
        assertSame(BoolVal.TRUE, game_.getFight().getTemp().getChosableFoeTargets().get(0).getChosable());
        assertSame(BoolVal.TRUE, game_.getFight().getTemp().getChosableFoeTargets().get(1).getChosable());
        assertEq(2, game_.getFight().getTemp().getChosablePlayerTargets().size());
        assertSame(BoolVal.FALSE, game_.getFight().getTemp().getChosablePlayerTargets().get(0).getChosable());
        assertSame(BoolVal.TRUE, game_.getFight().getTemp().getChosablePlayerTargets().get(1).getChosable());
    }
    @Test
    public void changeAction1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(Fighter.BACK, game_.getFight().getTemp().getChosenIndexBack());
        assertEq(0, game_.getFight().getTemp().getChosenIndexFront());
        assertEq(ActionType.SWITCH, game_.getFight().getTemp().getSelectedActionCurFighter());
        assertEq(0, game_.getFight().getTemp().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getTemp().getChosablePlayerTargets().size());
    }
    @Test
    public void chooseBackFighter1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.SWITCH, data_);
        game_.chooseSubstituteFighter( 0, data_);
        AbstractAction action_;
        action_ = game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        assertEq(1, ((ActionSwitch) action_).getSubstitute());
        assertEq(Fighter.BACK, game_.getFight().getTemp().getChosenIndexBack());
        assertEq(0, game_.getFight().getTemp().getChosenIndexFront());
        assertEq(ActionType.SWITCH, game_.getFight().getTemp().getSelectedActionCurFighter());
        assertEq(0, game_.getFight().getTemp().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getTemp().getChosablePlayerTargets().size());
    }
    @Test
    public void deselect1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.initTrainerFight(data_);
        game_.deselect();
        AbstractAction action_;
        action_ = game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        assertNull(action_);
        assertEq(Fighter.BACK, game_.getFight().getTemp().getChosenIndexBack());
        assertEq(Fighter.BACK, game_.getFight().getTemp().getChosenIndexFront());
        assertEq(ActionType.NOTHING, game_.getFight().getTemp().getSelectedActionCurFighter());
        assertEq(0, game_.getFight().getTemp().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getTemp().getChosablePlayerTargets().size());
    }
    @Test
    public void setChosenHealingItem1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
//        Pokemon pk_ = new WildPk();
//        pk_.setName(NINJASK);
//        pk_.setItem(MULTI_EXP);
//        pk_.setGender(Gender.NO_GENDER);
//        pk_.setAbility(ABSORB_EAU);
//        pk_.setLevel( 100);
//        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data);
//        game_.getPlayer().setChosenTeamPokemon( 0);
//        game_.getPlayer().switchTeamOrder( 1);
//        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
//        pkPl_.learnMove(SEISME, HATE, data);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.changeAction(ActionType.HEALING, data_);
        game_.setChosenHealingItem(EAU_FRAICHE, data_);
        //game_.deselect();
        AbstractAction action_;
        action_ = game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO).getAction();
        assertEq(Fighter.BACK, game_.getFight().getTemp().getChosenIndexBack());
        assertEq(0, game_.getFight().getTemp().getChosenIndexFront());
        assertEq(ActionType.HEALING, game_.getFight().getTemp().getSelectedActionCurFighter());
        assertEq(EAU_FRAICHE, ((ActionSimpleHeal)action_).getChosenHealingItem());
        assertEq(0, game_.getFight().getTemp().getChosableFoeTargets().size());
        assertEq(0, game_.getFight().getTemp().getChosablePlayerTargets().size());
    }
    @Test
    public void calculateCatchingRates1(){
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(MASTER_BALL);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        NatStringTreeMap<BallNumberRate> balls_ = game_.calculateCatchingRatesSingle(data_, 0, 0);
        assertEq(1, balls_.size());
        BallNumberRate ball_ = balls_.getVal(MASTER_BALL);
        assertEq(MASTER_BALL, ball_.getName());
        assertEq(LgInt.one(), ball_.getNumber());
        assertEq(1, ball_.getLaw().nbEvents());
        assertEq(Rate.one(), ball_.getLaw().getEvent(0));
//        assertEq("100", ball_.getPercent());
    }
    @Test
    public void setSubstituteEndRound1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.chooseBackFighter( 0, data_);
        game_.setSubstituteEndRound( 0);
        assertEq(0, game_.getFight().getTemp().getChosenIndexBack());
        assertEq(Fighter.BACK, game_.getFight().getTemp().getChosenIndexFront());
        assertEq(0, game_.getFight().getFirstPositPlayerFighters().getVal( 0));
        assertEq(0, game_.getFight().getFirstPositPlayerFighters().getVal( 1));
    }
    @Test
    public void remainingThrowersTargetsHp1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        TeamPositionsStringMapTeamPositionsRate map_;
        map_ = game_.remainingThrowersTargetsHp(data_);
        assertEq(2, map_.size());
        assertEq(3, map_.getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)).size());
        assertEq(1, map_.getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)).size());
        StringMap<TeamPositionsPairRatesPair> mapMoves_;
        mapMoves_ = map_.getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        assertEq(2, mapMoves_.getVal(SEISME).getFoe().size());
        assertEq(2, mapMoves_.getVal(SEISME).getPlayer().size());
        assertEq(2, mapMoves_.getVal(PLAIE_CROIX).getFoe().size());
        assertEq(2, mapMoves_.getVal(PLAIE_CROIX).getPlayer().size());
        assertEq(2, mapMoves_.getVal(GIGA_SANGSUE).getFoe().size());
        assertEq(2, mapMoves_.getVal(GIGA_SANGSUE).getPlayer().size());
        mapMoves_ = map_.getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        assertEq(2, mapMoves_.getVal(JACKPOT).getFoe().size());
        assertEq(2, mapMoves_.getVal(JACKPOT).getPlayer().size());
        TeamPositionsPairRatesPair mapFighters_;
        mapMoves_ = map_.getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        mapFighters_ = mapMoves_.getVal(SEISME);
        assertEq(2, mapFighters_.getFoe().size());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)).getFront());
        assertEq(new Rate("232/5"), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)).getFront());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)).getBack());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)).getBack());
        assertEq(2, mapFighters_.getPlayer().size());
        assertEq(new Rate("263"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)).getFront());
        assertEq(new Rate("3037/100"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)).getFront());
        assertEq(new Rate("263"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)).getBack());
        assertEq(Rate.zero(), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)).getBack());
        mapFighters_ = mapMoves_.getVal(PLAIE_CROIX);
        assertEq(2, mapFighters_.getFoe().size());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)).getFront());
        assertEq(new Rate("232/5"), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)).getFront());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)).getBack());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)).getBack());
        assertEq(2, mapFighters_.getPlayer().size());
        assertEq(new Rate("263"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)).getFront());
        assertEq(new Rate("3037/100"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)).getFront());
        assertEq(new Rate("263"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)).getBack());
        assertEq(new Rate("3037/100"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)).getBack());
        mapFighters_ = mapMoves_.getVal(GIGA_SANGSUE);
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)).getFront());
        assertEq(new Rate("232/5"), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)).getFront());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)).getBack());
        assertEq(Rate.zero(), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)).getBack());
        assertEq(new Rate("263"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)).getFront());
        assertEq(new Rate("3037/100"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)).getFront());
        assertEq(new Rate("263"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)).getBack());
        assertEq(new Rate("3037/100"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)).getBack());
        mapMoves_ = map_.getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE));
        mapFighters_ = mapMoves_.getVal(JACKPOT);
        assertEq(2, mapFighters_.getFoe().size());
        assertEq(new Rate("76457/1875"), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)).getFront());
        assertEq(new Rate("232/5"), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)).getFront());
        assertEq(new Rate("76457/1875"), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ZERO)).getBack());
        assertEq(new Rate("516992/11875"), mapFighters_.getFoe().getVal(tp(KEY_FOE, POKEMON_FIGHTER_ONE)).getBack());
        assertEq(2, mapFighters_.getPlayer().size());
        assertEq(new Rate("263"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)).getFront());
        assertEq(new Rate("3037/100"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)).getFront());
        assertEq(new Rate("263"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO)).getBack());
        assertEq(new Rate("3037/100"), mapFighters_.getPlayer().getVal(tp(KEY_PLAYER, POKEMON_FIGHTER_ONE)).getBack());
    }
    @Test
    public void sortedFightersBeginRoundWildFight1(){
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(MASTER_BALL);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        CustList<MovesListTeamPositionsList> map_;
        map_ = game_.sortedFightersBeginRoundWildFight(data_);
        assertEq(1, map_.size());
//        assertEq(1, map_.getVal(JACKPOT).size());
//        TeamPositionList mapMoves_;
//        mapMoves_ = map_.getVal(JACKPOT);
//        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), mapMoves_.first());
    }
    @Test
    public void sortedFightersBeginRoundWildFight2(){
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        //coords begin = newCoords(0, 0, 0, 0)
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(MASTER_BALL);
        //game_.getPlayerOrientation() == UP
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.DOWN, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.moving(Direction.RIGHT, data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(JACKPOT, data_);
        CustList<MovesListTeamPositionsList> map_;
        map_ = game_.sortedFightersBeginRoundWildFight(data_);
        assertEq(1, map_.size());
//        assertEq(2, map_.getVal(JACKPOT).size());
//        TeamPositionList mapMoves_;
//        mapMoves_ = map_.getVal(JACKPOT);
//        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), mapMoves_.first());
//        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), mapMoves_.last());
    }
    @Test
    public void sortedFightersBeginRound1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        TeamPositionList tree_;
        tree_ = game_.sortedFightersBeginRound(data_);
        assertEq(tp(KEY_PLAYER, POKEMON_FIGHTER_ZERO), tree_.first());
        assertEq(tp(KEY_FOE, POKEMON_FIGHTER_ZERO), tree_.last());
    }
    @Test
    public void getPlayerTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        CustList< FighterPosition> team_ = game_.getPlayerTeam();
        assertEq(2, team_.size());
        assertSame(team_.get( 0).getFighter(), game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
        assertSame(team_.get( 1).getFighter(), game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE));
    }
    @Test
    public void getFoeFrontTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        IntTreeMap< FighterPosition> team_ = game_.getFoeFrontTeam();
        assertEq(1, team_.size());
        assertSame(team_.getVal( 0).getFighter(), game_.getFight().getFighter(KEY_FOE, POKEMON_FIGHTER_ZERO));
    }
    @Test
    public void getUnionFrontTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        IntTreeMap< FighterPosition> team_ = game_.getUnionFrontTeam();
        assertEq(1, team_.size());
        assertSame(team_.getVal( 0).getFighter(), game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
    }
    @Test
    public void getPlayerFrontTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        CustList< FighterPosition> team_ = game_.getPlayerFrontTeam();
        assertEq(1, team_.size());
        assertSame(team_.get( 0).getFighter(), game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
    }
    @Test
    public void getPlayerBackTeam1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        CustList< FighterPosition> team_ = game_.getPlayerBackTeam();
        assertEq(1, team_.size());
        assertSame(team_.get( 0).getFighter(), game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE));
    }
    @Test
    public void getPlayerFrontTeamForSubstituting1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        CustList< FighterPosition> team_ = game_.getPlayerFrontTeam();
        assertEq(1, team_.size());
        assertSame(team_.get( 0).getFighter(), game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ZERO));
    }
    @Test
    public void getPlayerBackTeamForSubstituting1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINJASK);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, HATE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        CustList< FighterPosition> team_ = game_.getPlayerBackTeam();
        assertEq(1, team_.size());
        assertSame(team_.get( 0).getFighter(), game_.getFight().getFighter(KEY_PLAYER, POKEMON_FIGHTER_ONE));
    }
    @Test
    public void isChosableForLearningAndEvolving1Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        assertTrue(game_.isChosableForLearningAndEvolving( 0));
    }
    @Test
    public void isChosableForLearningAndEvolving2Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        assertTrue(!game_.isChosableForLearningAndEvolving( 1));
    }
    @Test
    public void choosePokemonForLearningAndEvolving1Test(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        assertEq(0, game_.getChosenIndex());
        NatStringTreeMap<BoolVal> moves_ = game_.getMoves();
        assertEq(4, moves_.size());
        assertSame(BoolVal.TRUE,moves_.getVal(COUD_BOUE));
        assertSame(BoolVal.TRUE,moves_.getVal(GRIFFE_ACIER));
        assertSame(BoolVal.TRUE,moves_.getVal(SEISME));
        assertSame(BoolVal.TRUE,moves_.getVal(TUNNEL));
        EvolutionChoiceMap evolutions_ = game_.getEvolutions();
        assertEq(3, evolutions_.size());
        assertSame(BoolVal.TRUE,evolutions_.getVal(NULL_REF));
        assertSame(BoolVal.FALSE,evolutions_.getVal(NINJASK));
        assertSame(BoolVal.FALSE,evolutions_.getVal(MUNJA));
        StringList abilities_ = game_.getAbilities();
        assertEq(1, abilities_.size());
        assertEq(ABSORB_EAU, abilities_.get(0));
        assertEq(NULL_REF, game_.getAbility());
    }
    @Test
    public void setEvolution1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(NINJASK);
        assertEq(0, game_.getChosenIndex());
        NatStringTreeMap<BoolVal> moves_ = game_.getMoves();
        assertEq(20, moves_.size());
        assertSame(BoolVal.FALSE,moves_.getVal(ARMURE));
        assertSame(BoolVal.FALSE,moves_.getVal(COMBO_GRIFFE));
        assertSame(BoolVal.TRUE,moves_.getVal(COUD_BOUE));
        assertSame(BoolVal.FALSE,moves_.getVal(DANSE_LAMES));
        assertSame(BoolVal.FALSE,moves_.getVal(GIGA_SANGSUE));
        assertSame(BoolVal.FALSE,moves_.getVal(GRIFFE));
        assertSame(BoolVal.TRUE,moves_.getVal(GRIFFE_ACIER));
        assertSame(BoolVal.FALSE,moves_.getVal(GRINCEMENT));
        assertSame(BoolVal.FALSE,moves_.getVal(HATE));
        assertSame(BoolVal.FALSE,moves_.getVal(JET_DE_SABLE));
        assertSame(BoolVal.FALSE,moves_.getVal(LIRE_ESPRIT));
        assertSame(BoolVal.FALSE,moves_.getVal(PIQURE));
        assertSame(BoolVal.FALSE,moves_.getVal(PLAIE_CROIX));
        assertSame(BoolVal.FALSE,moves_.getVal(REFLET));
        assertSame(BoolVal.FALSE,moves_.getVal(RELAIS));
        assertSame(BoolVal.TRUE,moves_.getVal(SEISME));
        assertSame(BoolVal.FALSE,moves_.getVal(TAILLADE));
        assertSame(BoolVal.FALSE,moves_.getVal(TRANCHE));
        assertSame(BoolVal.TRUE,moves_.getVal(TUNNEL));
        assertSame(BoolVal.FALSE,moves_.getVal(VAMPIRISME));
        EvolutionChoiceMap evolutions_ = game_.getEvolutions();
        assertEq(3, evolutions_.size());
        assertSame(BoolVal.FALSE,evolutions_.getVal(NULL_REF));
        assertSame(BoolVal.TRUE,evolutions_.getVal(NINJASK));
        assertSame(BoolVal.FALSE,evolutions_.getVal(MUNJA));
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
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(TARINOR);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, LIRE_ESPRIT, data_);
        pkPl_.setHappiness( 120);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(TARINORME);
        game_.setAbility(MAGNEPIEGE);
        assertEq(0, game_.getChosenIndex());
        NatStringTreeMap<BoolVal> moves_ = game_.getMoves();
        assertEq(17, moves_.size());
        assertSame(BoolVal.FALSE,moves_.getVal(BOMBAIMANT));
        assertSame(BoolVal.FALSE,moves_.getVal(CAGE_ECLAIR));
        assertSame(BoolVal.FALSE,moves_.getVal(CHARGE));
        assertSame(BoolVal.FALSE,moves_.getVal(COUP_D_JUS));
        assertSame(BoolVal.FALSE,moves_.getVal(EBOULEMENT));
        assertSame(BoolVal.TRUE,moves_.getVal(ELECANON));
        assertSame(BoolVal.FALSE,moves_.getVal(GRAVITE));
        assertSame(BoolVal.TRUE,moves_.getVal(LAME_DE_ROC));
        assertSame(BoolVal.FALSE,moves_.getVal(LIRE_ESPRIT));
        assertSame(BoolVal.FALSE,moves_.getVal(MUR_DE_FER));
        assertSame(BoolVal.FALSE,moves_.getVal(RAYON_GEMME));
        assertSame(BoolVal.FALSE,moves_.getVal(REGARD_NOIR));
        assertSame(BoolVal.FALSE,moves_.getVal(REPOS));
        assertSame(BoolVal.TRUE,moves_.getVal(SEISME));
        assertSame(BoolVal.TRUE,moves_.getVal(TELLURIFORCE));
        assertSame(BoolVal.FALSE,moves_.getVal(TEMPETESABLE));
        assertSame(BoolVal.FALSE,moves_.getVal(VOL_MAGNETIK));
        EvolutionChoiceMap evolutions_ = game_.getEvolutions();
        assertEq(2, evolutions_.size());
        assertSame(BoolVal.FALSE,evolutions_.getVal(NULL_REF));
        assertSame(BoolVal.TRUE,evolutions_.getVal(TARINORME));
        StringList abilities_ = game_.getAbilities();
        assertEq(2, abilities_.size());
        assertTrue(StringUtil.contains(abilities_, FERMETE));
        assertTrue(StringUtil.contains(abilities_, MAGNEPIEGE));
        assertEq(MAGNEPIEGE, game_.getAbility());
        //assertEq(TURBO, game_.getFight().getChoices().getVal( 0).getAbility());
    }
    @Test
    public void addOrForgetMove1(){
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        Pokemon pk_ = new WildPk();
        pk_.setName(NINGALE);
        pk_.setItem(MULTI_EXP);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(ABSORB_EAU);
        pk_.setLevel( 100);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon( 0);
        game_.getPlayer().switchTeamOrder( 1);
        PokemonPlayer pkPl_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pkPl_.learnMove(SEISME, FAUX_CHAGE, data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.chooseFrontFighter( 0, data_);
        game_.chooseMove(SEISME, data_);
        game_.roundAllThrowers(data_, false);
        game_.deselect();
        game_.choosePokemonForLearningAndEvolving( 0, data_);
        game_.setEvolution(NINJASK);
        game_.addOrForgetMove(PLAIE_CROIX);
        assertEq(0, game_.getChosenIndex());
        NatStringTreeMap<BoolVal> moves_ = game_.getMoves();
        assertEq(20, moves_.size());
        assertSame(BoolVal.FALSE,moves_.getVal(ARMURE));
        assertSame(BoolVal.FALSE,moves_.getVal(COMBO_GRIFFE));
        assertSame(BoolVal.TRUE,moves_.getVal(COUD_BOUE));
        assertSame(BoolVal.FALSE,moves_.getVal(DANSE_LAMES));
        assertSame(BoolVal.FALSE,moves_.getVal(GIGA_SANGSUE));
        assertSame(BoolVal.FALSE,moves_.getVal(GRIFFE));
        assertSame(BoolVal.TRUE,moves_.getVal(GRIFFE_ACIER));
        assertSame(BoolVal.FALSE,moves_.getVal(GRINCEMENT));
        assertSame(BoolVal.FALSE,moves_.getVal(HATE));
        assertSame(BoolVal.FALSE,moves_.getVal(JET_DE_SABLE));
        assertSame(BoolVal.FALSE,moves_.getVal(LIRE_ESPRIT));
        assertSame(BoolVal.FALSE,moves_.getVal(PIQURE));
        assertSame(BoolVal.TRUE,moves_.getVal(PLAIE_CROIX));
        assertSame(BoolVal.FALSE,moves_.getVal(REFLET));
        assertSame(BoolVal.FALSE,moves_.getVal(RELAIS));
        assertSame(BoolVal.TRUE,moves_.getVal(SEISME));
        assertSame(BoolVal.FALSE,moves_.getVal(TAILLADE));
        assertSame(BoolVal.FALSE,moves_.getVal(TRANCHE));
        assertSame(BoolVal.TRUE,moves_.getVal(TUNNEL));
        assertSame(BoolVal.FALSE,moves_.getVal(VAMPIRISME));
        EvolutionChoiceMap evolutions_ = game_.getEvolutions();
        assertEq(3, evolutions_.size());
        assertSame(BoolVal.FALSE,evolutions_.getVal(NULL_REF));
        assertSame(BoolVal.TRUE,evolutions_.getVal(NINJASK));
        assertSame(BoolVal.FALSE,evolutions_.getVal(MUNJA));
        StringList abilities_ = game_.getAbilities();
        assertEq(1, abilities_.size());
        assertEq(TURBO, abilities_.get(0));
    }
}