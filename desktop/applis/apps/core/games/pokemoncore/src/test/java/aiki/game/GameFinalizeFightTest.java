package aiki.game;

import aiki.db.DataBase;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.map.levels.AbsAreaApparition;
import aiki.util.*;
import code.util.IdMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.game.enums.InterfaceType;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.player.Player;
import aiki.map.DataMap;
import aiki.map.characters.Ally;
import aiki.map.characters.DualFight;
import aiki.map.characters.TempTrainer;
import aiki.map.enums.Direction;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Campaign;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;

import code.util.StringList;


public class GameFinalizeFightTest extends InitializationDataBase{

    private static final String NICKNAME_SAMPLE = "MY_GREAT_POKEMON";

    @Test
    public void endFight1Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(false);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(newCoords(0, 0, 2, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight2Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(FightState.CAPTURE_KO, game_.getFight().getState());
        assertEq(newCoords(0, 0, 2, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
    }

    @Test
    public void endFight3Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(newCoords(0, 0, 2, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight4Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(newCoords(0, 0, 2, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight5Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(false);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(new LgInt("1000"), game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 2, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight6Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(false);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(new LgInt("3000"), game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 2, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight7Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(6, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(1, nbBeatTrainers(game_.getBeatTrainer(),true));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(0, 0, 1, 1), 0)));
        assertEq(new LgInt("17000"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 2, 1), game_.getPlayerCoords());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void endFight8Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),0));
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(5, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(2, nbBeatTrainers(game_.getBeatTrainer(),true));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(0, 0, 1, 1), 0)));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(0, 0, 1, 1), 1)));
        assertEq(new LgInt("57000"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 2, 1), game_.getPlayerCoords());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void endFight9Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),1));
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(5, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(2, nbBeatTrainers(game_.getBeatTrainer(),true));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(0, 0, 1, 1), 0)));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(0, 0, 1, 1), 1)));
        assertEq(new LgInt("57000"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 2, 1), game_.getPlayerCoords());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void endFight10Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 10, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(6, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(1, nbBeatTrainers(game_.getBeatTrainer(),true));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(2, 0, 11, 4), 0)));
        assertEq(new LgInt("17000"),game_.getPlayer().getMoney());
        assertEq(newCoords(2, 0, 10, 4), game_.getPlayerCoords());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void endFight11Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 10, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4),0));
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(6, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(1, nbBeatTrainers(game_.getBeatTrainer(),true));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(2, 0, 11, 4), 0)));
        assertEq(new LgInt("17000"),game_.getPlayer().getMoney());
        assertEq(newCoords(2, 0, 10, 4), game_.getPlayerCoords());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void endFight12Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(5, game_.getUnBeatenGymLeader().size());
        assertEq(1, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(2, 0, 2, 0)));
        assertEq(new LgInt("42000"),game_.getPlayer().getMoney());
        assertEq(newCoords(2, 0, 2, 1), game_.getPlayerCoords());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void endFight13Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(5, game_.getUnBeatenGymLeader().size());
        assertEq(1, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(2, 0, 2, 0)));
        assertEq(new LgInt("42000"),game_.getPlayer().getMoney());
        assertEq(newCoords(2, 0, 3, 1), game_.getPlayerCoords());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void endFight14Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(2, game_.getBeatGymTrainer().size());
        assertEq(1, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(newPoint(1, 7), game_.getBeatGymTrainer().getVal((short) 1).get(0));
        assertEq(new LgInt("17000"),game_.getPlayer().getMoney());
        assertEq(newCoords(1, 0, 5, 1, 1, 8), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight15Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(5, game_.getUnBeatenGymLeader().size());
        assertEq(1, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(1, 0, 5, 1, 4, 1)));
        assertEq(new LgInt("68000"),game_.getPlayer().getMoney());
        assertEq(newCoords(1, 0, 5, 1, 4, 2), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight16Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(1, game_.getRankLeague());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(4, game_.getUnBeatenGymLeader().size());
        assertEq(2, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(1, 0, 5, 1, 4, 1)));
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(3, 0, 4, 1, 4, 1)));
        assertEq(new LgInt("1463000"),game_.getPlayer().getMoney());
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
        assertEq(InterfaceType.PERSONNAGE, game_.getInterfaceType());
    }

    @Test
    public void endFight17Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.setRankLeague((byte) 1);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(2, game_.getRankLeague());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(3, game_.getUnBeatenGymLeader().size());
        assertEq(3, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(1, 0, 5, 1, 4, 1)));
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(3, 0, 4, 1, 4, 1)));
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(6, 0, 4, 8)));
        assertEq(new LgInt("1463000"),game_.getPlayer().getMoney());
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(InterfaceType.PERSONNAGE, game_.getInterfaceType());
    }

    @Test
    public void endFight18Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.setRankLeague((byte) 1);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(2, game_.getRankLeague());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(3, game_.getUnBeatenGymLeader().size());
        assertEq(3, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(1, 0, 5, 1, 4, 1)));
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(3, 0, 4, 1, 4, 1)));
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(6, 0, 4, 8)));
        assertEq(new LgInt("1463000"),game_.getPlayer().getMoney());
        assertEq(newCoords(6, 1, 4, 5), game_.getPlayerCoords());
        assertEq(InterfaceType.PERSONNAGE, game_.getInterfaceType());
    }

    @Test
    public void endFight19Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(7, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight20Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),0));
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(6, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(1, nbBeatTrainers(game_.getBeatTrainer(),true));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(0, 0, 1, 1), 0)));
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight21Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1),1));
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(5, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(2, nbBeatTrainers(game_.getBeatTrainer(),true));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(0, 0, 1, 1), 0)));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(0, 0, 1, 1), 1)));
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight22Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 10, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(7, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight23Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 10, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4),0));
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(7, game_.getBeatTrainer().size());
        assertEq(6, nbBeatTrainers(game_.getBeatTrainer(),false));
        assertEq(1, nbBeatTrainers(game_.getBeatTrainer(),true));
        assertSame(BoolVal.TRUE,game_.getBeatTrainer().getVal(new NbFightCoords(newCoords(2, 0, 11, 4), 0)));
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight24Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(6, game_.getUnBeatenGymLeader().size());
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight25Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(6, game_.getUnBeatenGymLeader().size());
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight26Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(2, game_.getBeatGymTrainer().size());
        assertEq(0, game_.getBeatGymTrainer().getVal((short) 1).size());
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight27Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(6, game_.getUnBeatenGymLeader().size());
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight28Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(0, game_.getRankLeague());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(4, game_.getUnBeatenGymLeader().size());
        assertEq(2, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(1, 0, 5, 1, 4, 1)));
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(3, 0, 4, 1, 4, 1)));
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(4, 0, 5, 4), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight29Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.setRankLeague((byte) 1);
        game_.initTrainerFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(1, game_.getRankLeague());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(4, game_.getUnBeatenGymLeader().size());
        assertEq(2, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(1, 0, 5, 1, 4, 1)));
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(3, 0, 4, 1, 4, 1)));
        assertEq(new LgInt("0"),game_.getPlayer().getMoney());
        assertEq(newCoords(4, 0, 5, 4), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight30Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.initLegendaryPokemonFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertSame(BoolVal.FALSE,game_.getTakenPokemon().getVal(newCoords(2, 0, 11, 2)));
        assertEq(newCoords(2, 0, 11, 1), game_.getPlayerCoords());
        assertEq(InterfaceType.PK_LEG, game_.getInterfaceType());
    }

    @Test
    public void endFight31Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.setRankLeague((byte) 1);
        game_.initTrainerFight(data_);
        //Because of bad data
        game_.getPlayerCoords().affect(newCoords(0, 0, 0, 0));
        game_.setRankLeague((byte) 0);
        game_.getFight().getTemp().getKos().put(Fight.CST_PLAYER, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(4, game_.getUnBeatenGymLeader().size());
        assertEq(2, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(1, 0, 5, 1, 4, 1)));
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(3, 0, 4, 1, 4, 1)));
        assertEq(new LgInt("3000"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endFight32Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.setRankLeague((byte) 1);
        game_.initTrainerFight(data_);
        //Because of bad data
        game_.getPlayerCoords().affect(newCoords(0, 0, 0, 0));
        game_.setRankLeague((byte) 0);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(0, game_.getRankLeague());
        assertEq(6, game_.getBeatGymLeader().size());
        assertEq(4, game_.getUnBeatenGymLeader().size());
        assertEq(2, game_.getBeatenGymLeader().size());
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(1, 0, 5, 1, 4, 1)));
        assertSame(BoolVal.TRUE,game_.getBeatGymLeader().getVal(newCoords(3, 0, 4, 1, 4, 1)));
        assertEq(new LgInt("3000"),game_.getPlayer().getMoney());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    private static int nbBeatTrainers(NbFightCoordss _map, boolean _taken) {
//        int n_ = IndexConstants.FIRST_INDEX;
//        if (_taken) {
//            for (BoolVal e: _map.values()) {
//                if (e == BoolVal.TRUE) {
//                    n_++;
//                }
//            }
//        } else {
//            for (BoolVal e: _map.values()) {
//                if (e != BoolVal.TRUE) {
//                    n_++;
//                }
//            }
//        }
//        return n_;
        return countValues(_map.values(), ComparatorBoolean.of(_taken));
    }

    @Test
    public void nextLegPk1Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        Campaign pl_ = (Campaign) map_.getPlace(current_.getNumberPlace());
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.nextLegPk(data_));
    }

    @Test
    public void nextLegPk2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.initLegendaryPokemonFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(game_.nextLegPk(data_));
    }

    @Test
    public void nextLegPk3Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)1));
        Campaign pl_ = (Campaign) map_.getPlace(current_.getNumberPlace());
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        assertTrue(!game_.nextLegPk(data_));
    }

    @Test
    public void nextLegPk4Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)1));
        Campaign pl_ = (Campaign) map_.getPlace(current_.getNumberPlace());
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.LEFT);
        assertTrue(!game_.nextLegPk(data_));
    }

    @Test
    public void catchWildPokemon1Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Coords current_ = game_.getPlayerCoords();
        Campaign pl_ = (Campaign) map_.getPlace(current_.getNumberPlace());
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.getFight().getCatchingBalls().first().setNickname(NICKNAME_SAMPLE);
        game_.getFight().getCatchingBalls().first().setCaught(true);
        game_.getFight().getCatchingBalls().first().setTeam(true);
        game_.catchWildPokemon(data_);
        assertEq(2, game_.getPlayer().getTeam().size());
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertEq(NICKNAME_SAMPLE, ((PokemonPlayer) game_.getPlayer().getTeam().get(1)).getNickname());
        assertEq(ARTIKODIN, ((PokemonPlayer) game_.getPlayer().getTeam().get(1)).getName());
        assertTrue(game_.isEmpty(map_, newCoords(2, 0, 9, 5)));
        assertTrue(!game_.isEmpty(map_, newCoords(2, 0, 11, 2)));
        assertEq(HYPER_BALL, ((PokemonPlayer) game_.getPlayer().getTeam().get(1)).getUsedBallCatching());
        assertEq(2, game_.getTakenPokemon().size());
        assertEq(2, nbPokemon(game_.getTakenPokemon(),false));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void catchWildPokemon2Test() {
        DataBase data_ = initDbAccessSimple();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.initLegendaryPokemonFight(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.getFight().getCatchingBalls().first().setCaught(true);
        game_.getFight().getCatchingBalls().first().setNickname(NICKNAME_SAMPLE);
        game_.getFight().getCatchingBalls().first().setTeam(true);
        game_.catchWildPokemon(data_);
        assertEq(2, game_.getPlayer().getTeam().size());
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        DataMap map_ = data_.getMap();
        assertTrue(!game_.isEmpty(map_, newCoords(2, 0, 9, 5)));
        assertTrue(game_.isEmpty(map_, newCoords(2, 0, 11, 2)));
        assertEq(NICKNAME_SAMPLE, ((PokemonPlayer) game_.getPlayer().getTeam().get(1)).getNickname());
        assertEq(HYPER_BALL, ((PokemonPlayer) game_.getPlayer().getTeam().get(1)).getUsedBallCatching());
        assertSame(BoolVal.TRUE,game_.getTakenPokemon().getVal(newCoords(2, 0, 11, 2)));
        assertEq(2, game_.getTakenPokemon().size());
        assertEq(1, nbPokemon(game_.getTakenPokemon(),false));
        assertEq(1, nbPokemon(game_.getTakenPokemon(),true));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void catchWildPokemon3Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.initFishing(data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.getFight().getCatchingBalls().first().setCaught(true);
        game_.getFight().getCatchingBalls().first().setTeam(true);
        game_.catchWildPokemon(data_);
        assertEq(2, game_.getPlayer().getTeam().size());
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(1, game_.getIndexPeriodFishing());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void catchWildPokemon4Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Coords current_ = game_.getPlayerCoords();
        Campaign pl_ = (Campaign) map_.getPlace(current_.getNumberPlace());
        current_.getLevel().getPoint().affect(new Point((short)2,(short)1));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.getFight().getCatchingBalls().first().setCaught(true);
        game_.getFight().getCatchingBalls().first().setNickname(NICKNAME_SAMPLE);
        game_.getFight().getCatchingBalls().first().setTeam(true);
        game_.catchWildPokemon(data_);
        assertEq(2, game_.getPlayer().getTeam().size());
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertEq(NICKNAME_SAMPLE, ((PokemonPlayer) game_.getPlayer().getTeam().get(1)).getNickname());
        assertEq(ARTIKODIN, ((PokemonPlayer) game_.getPlayer().getTeam().get(1)).getName());
        assertTrue(game_.isEmpty(map_, newCoords(2, 0, 9, 5)));
        assertTrue(!game_.isEmpty(map_, newCoords(2, 0, 11, 2)));
        assertEq(HYPER_BALL, ((PokemonPlayer) game_.getPlayer().getTeam().get(1)).getUsedBallCatching());
        assertEq(2, game_.getTakenPokemon().size());
        assertEq(2, nbPokemon(game_.getTakenPokemon(),false));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void catchWildPokemon5Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Coords current_ = game_.getPlayerCoords();
        Campaign pl_ = (Campaign) map_.getPlace(current_.getNumberPlace());
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.getFight().getCatchingBalls().first().setNickname(NICKNAME_SAMPLE);
        game_.getFight().getCatchingBalls().first().setCaught(true);
        game_.getFight().getCatchingBalls().first().setTeam(false);
        game_.catchWildPokemon(data_);
        assertEq(1, game_.getPlayer().getTeam().size());
        assertEq(1, game_.getPlayer().getBox().size());
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertEq(NICKNAME_SAMPLE, ((PokemonPlayer) game_.getPlayer().getBox().get(0)).getNickname());
        assertEq(ARTIKODIN, ((PokemonPlayer) game_.getPlayer().getBox().get(0)).getName());
        assertTrue(game_.isEmpty(map_, newCoords(2, 0, 9, 5)));
        assertTrue(!game_.isEmpty(map_, newCoords(2, 0, 11, 2)));
        assertEq(HYPER_BALL, ((PokemonPlayer) game_.getPlayer().getBox().get(0)).getUsedBallCatching());
        assertEq(2, game_.getTakenPokemon().size());
        assertEq(2, nbPokemon(game_.getTakenPokemon(),false));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }
    private static int nbPokemon(CoordssBoolVal _map, boolean _taken) {
//        int n_ = IndexConstants.FIRST_INDEX;
//        if (_taken) {
//            for (BoolVal e: _map.values()) {
//                if (e == BoolVal.TRUE) {
//                    n_++;
//                }
//            }
//        } else {
//            for (BoolVal e: _map.values()) {
//                if (e != BoolVal.TRUE) {
//                    n_++;
//                }
//            }
//        }
//        return n_;
        return countValues(_map.values(), ComparatorBoolean.of(_taken));
    }

    @Test
    public void catchKoWildPokemon1Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.getFight().getCatchingBalls().first().setNickname(NICKNAME_SAMPLE);
        game_.catchKoWildPokemon(data_);
        assertEq(2, game_.getPlayer().getTeam().size());
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertEq(NICKNAME_SAMPLE, ((PokemonPlayer) game_.getPlayer().getTeam().get(1)).getNickname());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void notCatchKoWildPokemon1Test() {
        DataBase data_ = initDbFacade();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        current_.getLevel().getPoint().affect(new Point((short)2,(short)0));
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        newRandomPokemon(game_, area_, data_);
        game_.getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        game_.endFight(data_);
        game_.notCatchKoWildPokemon(data_);
        assertEq(1, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getPlayer().getBox().size());
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void attemptCatchingWildPokemon1Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        ((PokemonPlayer)game_.getPlayer().getTeam().get(0)).setRemainedHp(new Rate("1/2"));
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        assertTrue(game_.getFight().getFightType().isWild());
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, false);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(FightFacade.loose(game_.getFight()));
    }

    @Test
    public void attemptCatchingWildPokemon2Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        assertTrue(game_.getFight().getFightType().isWild());
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, false);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
    }

    @Test
    public void attemptCatchingWildPokemon3Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        ((PokemonPlayer)game_.getPlayer().getTeam().get(0)).setRemainedHp(Rate.one());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        assertTrue(game_.getFight().getFightType().isWild());
        game_.getFight().wildPokemon().setRemainedHp(Rate.one());
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, false);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(FightState.SURNOM, game_.getFight().getState());
    }

    @Test
    public void attemptCatchingWildPokemon4Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        ((PokemonPlayer)game_.getPlayer().getTeam().get(0)).setRemainedHp(Rate.one());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        assertTrue(game_.getFight().getFightType().isWild());
        game_.getFight().wildPokemon().setRemainedHp(Rate.one());
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(game_.getFight().getFightType().isWild());
        assertTrue(!game_.getFight().getTemp().isKeepRound());
        assertEq(FightState.SURNOM, game_.getFight().getState());
    }

    @Test
    public void endRoundFightBall1Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        ((PokemonPlayer)game_.getPlayer().getTeam().get(0)).setRemainedHp(new Rate("1/2"));
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        assertTrue(game_.getFight().getFightType().isWild());
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        game_.roundUser(data_);
        game_.endRoundFightBall(data_);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(FightFacade.loose(game_.getFight()));
    }

    @Test
    public void endRoundFightBall2Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        assertTrue(game_.getFight().getFightType().isWild());
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        game_.roundUser(data_);
        game_.endRoundFightBall(data_);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
    }

    @Test
    public void endRoundFightSuccessBall1Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        ((PokemonPlayer)game_.getPlayer().getTeam().get(0)).setRemainedHp(Rate.one());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        assertTrue(game_.getFight().getFightType().isWild());
        game_.getFight().wildPokemon().setRemainedHp(Rate.one());
        game_.getFight().getCatchingBalls().first().setCatchingBall(HYPER_BALL);
        game_.getFight().getCatchingBalls().first().setPlayer((byte)POKEMON_FIGHTER_ZERO);
        game_.attemptCatchingWildPokemon(data_, true);
        game_.endRoundFightSuccessBall(data_);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(FightState.SURNOM, game_.getFight().getState());
    }

    @Test
    public void calculateFleeingRate1Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setAllowCatchingKo(false);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.getPlayer().getItem(HYPER_BALL);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        ((PokemonPlayer)game_.getPlayer().getTeam().get(0)).setRemainedHp(new Rate("1/2"));
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        assertTrue(game_.getFight().getFightType().isWild());
        IdMap<UsefulValueLaw, Rate> rate_ = game_.calculateFleeingRate(data_);
        assertEq(Rate.one(), rate_.getVal(UsefulValueLaw.MINI));
        assertEq(Rate.one(), rate_.getVal(UsefulValueLaw.MAXI));
        assertEq(Rate.one(), rate_.getVal(UsefulValueLaw.MOY));
        assertEq(Rate.zero(), rate_.getVal(UsefulValueLaw.VAR));
    }

    @Test
    public void attemptFlee1Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        assertTrue(game_.getFight().getFightType().isWild());
        game_.attemptFlee(data_, false);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(!FightFacade.koTeam(game_.getFight()));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void attemptFlee2Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        ((PokemonPlayer)game_.getPlayer().getTeam().get(0)).setRemainedHp(new Rate("1/2"));
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) 6);
        game_.getFight().getUserTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) -6);
        assertTrue(game_.getFight().getFightType().isWild());
        game_.attemptFlee(data_, false);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(FightFacade.loose(game_.getFight()));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void attemptFlee3Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) 6);
        game_.getFight().getUserTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) -6);
        assertTrue(game_.getFight().getFightType().isWild());
        game_.attemptFlee(data_, false);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(!FightFacade.koTeam(game_.getFight()));
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
    }

    @Test
    public void attemptFlee4Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) 6);
        game_.getFight().getUserTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) -6);
        assertTrue(game_.getFight().getFightType().isWild());
        game_.attemptFlee(data_, true);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(!FightFacade.koTeam(game_.getFight()));
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
    }

    @Test
    public void endRoundFightFlee1Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(true);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        assertTrue(game_.getFight().getFightType().isWild());
        game_.attemptFlee(data_, true);
        game_.endRoundFightFlee(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(!FightFacade.koTeam(game_.getFight()));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endRoundFightFlee2Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        ((PokemonPlayer)game_.getPlayer().getTeam().get(0)).setRemainedHp(new Rate("1/2"));
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) 6);
        game_.getFight().getUserTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) -6);
        assertTrue(game_.getFight().getFightType().isWild());
        game_.attemptFlee(data_, true);
        game_.roundUser(data_);
        game_.endRoundFightFlee(data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(FightFacade.loose(game_.getFight()));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void endRoundFightFlee3Test() {
        DataBase data_ = initDbFacade();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setRandomWildFight(false);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setStillPossibleFlee(false);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, diff_, data_);
        game_.setDifficulty(diff_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AbsAreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) 6);
        game_.getFight().getUserTeam().getMembers().values().first().getStatisBoost().put(Statistic.SPEED, (byte) -6);
        assertTrue(game_.getFight().getFightType().isWild());
        game_.attemptFlee(data_, true);
        game_.roundUser(data_);
        game_.endRoundFightFlee(data_);
        assertTrue(game_.getFight().getFightType().isExisting());
        assertTrue(!FightFacade.koTeam(game_.getFight()));
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
    }

    //roundWhileKoPlayer
    @Test
    public void roundWhileKoPlayer1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.setPlayer(player_);
        //game_.initTrainerFight(data);
        Fight fight_ = game_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_;
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)17);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)17);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)17);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)17);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)19);
        allyPokemon_.setMoves(new StringList(JACKPOT));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, player_, diff_, dual_, data_);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data_);
        //fight_.setEnvType(EnvironmentType.ROAD);
        //FightSending.firstEffectWhileSendingTeams(fight_, diff_, data);
        //FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,data);
        fight_.getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        fight_.getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        //assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        game_.roundWhileKoPlayer(data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        //assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void roundWhileKoPlayer2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.setPlayer(player_);
        //game_.initTrainerFight(data);
        Fight fight_ = game_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_;
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)17);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)17);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)17);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)17);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)19);
        allyPokemon_.setMoves(new StringList(JACKPOT));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, player_, diff_, dual_, data_);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data_);
        //fight_.setEnvType(EnvironmentType.ROAD);
        //FightSending.firstEffectWhileSendingTeams(fight_, diff_, data);
        //FightArtificialIntelligence.choiceArtificialIntelligence(fight_,diff_,data);
        fight_.getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        fight_.getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        //assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
        game_.roundWhileKoPlayer(data_, true);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        //assertTrue(!FightEndRound.proponedSwitchWhileKoPlayer(fight_));
    }

    @Test
    public void roundWhileKoPlayer3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.setPlayer(player_);
        Fight fight_ = game_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_;
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)50);
        allyPokemon_.setMoves(new StringList(JACKPOT));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, player_, diff_, dual_, data_);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data_);
        fight_.getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        fight_.getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        game_.roundWhileKoPlayer(data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(FightFacade.koTeam(fight_));
    }

    @Test
    public void roundWhileKoPlayer4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.setPlayer(player_);
        Fight fight_ = game_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_;
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)50);
        allyPokemon_.setMoves(new StringList(JACKPOT));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, player_, diff_, dual_, data_);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data_);
        fight_.getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        fight_.getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        game_.roundWhileKoPlayer(data_, true);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightFacade.koTeam(fight_));
    }

    @Test
    public void endRoundFightKoUser1Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.setPlayer(player_);
        Fight fight_ = game_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_;
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)50);
        allyPokemon_.setMoves(new StringList(JACKPOT));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, player_, diff_, dual_, data_);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data_);
        fight_.getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        fight_.getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        game_.roundWhileKoPlayer(data_, true);
        game_.roundUser(data_);
        game_.endRoundFightKoUser(data_);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(FightFacade.koTeam(fight_));
    }

    @Test
    public void endRoundFightKoUser2Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.setPlayer(player_);
        Fight fight_ = game_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_;
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)50);
        allyPokemon_.setMoves(new StringList(JACKPOT));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, player_, diff_, dual_, data_);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data_);
        fight_.getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        fight_.getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        game_.roundWhileKoPlayer(data_, true);
        game_.endRoundFightKoUser(data_);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightFacade.koTeam(fight_));
    }

    @Test
    public void endRoundFightKoUser3Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.setPlayer(player_);
        Fight fight_ = game_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_;
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)50);
        allyPokemon_.setMoves(new StringList(JACKPOT));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, player_, diff_, dual_, data_);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data_);
        fight_.getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        fight_.getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        FightFacade.roundWhileKoPlayer(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        game_.roundWhileKoPlayer(data_, false);
        game_.roundWhileKoPlayer(data_, true);
        game_.roundUser(data_);
        game_.roundUser(data_);
        game_.endRoundFightKoUser(data_);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightFacade.koTeam(fight_));
    }

    @Test
    public void endRoundFightKoUser4Test() {
        DataBase data_ = initDb();
        Difficulty diff_= new Difficulty();
        diff_.setEnabledClosing(true);
        diff_.setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MAX);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(false);
        Player player_ = Player.build(NICKNAME,diff_,false,data_);
        Pokemon pokemon_ = new WildPk();
        pokemon_.setName(PTITARD);
        pokemon_.setItem(PLAQUE_DRACO);
        pokemon_.setAbility(METEO);
        pokemon_.setGender(Gender.NO_GENDER);
        pokemon_.setLevel((short) 1);
        PokemonPlayer lasPk_ = new PokemonPlayer(pokemon_,data_);
        lasPk_.initIv(diff_);
        lasPk_.initPvRestants(data_);
        player_.getTeam().add(lasPk_);
        player_.recupererOeufPensions(new Egg(PTITARD));
        Game game_ = new Game(data_);
        game_.setDifficulty(diff_);
        game_.setPlayer(player_);
        Fight fight_ = game_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        PkTrainer foePokemon_;
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        foePokemon_ = new PkTrainer();
        foePokemon_.setName(TARTARD);
        foePokemon_.setItem(PLAQUE_DRACO);
        foePokemon_.setAbility(MULTITYPE);
        foePokemon_.setGender(Gender.NO_GENDER);
        foePokemon_.setLevel((short)7);
        foePokemon_.setMoves(new StringList(CHARGE));
        foeTeam_.add(foePokemon_);
        DualFight dual_ = new DualFight();
        Ally ally_ = new Ally();
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        PkTrainer allyPokemon_ = new PkTrainer();
        allyPokemon_.setName(TARTARD);
        allyPokemon_.setItem(PLAQUE_DRACO);
        allyPokemon_.setAbility(MULTITYPE);
        allyPokemon_.setGender(Gender.NO_GENDER);
        allyPokemon_.setLevel((short)50);
        allyPokemon_.setMoves(new StringList(JACKPOT));
        allyTeam_.add(allyPokemon_);
        ally_.setTeam(allyTeam_);
        dual_.setAlly(ally_);
        TempTrainer trainer_ = new TempTrainer();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        dual_.setFoeTrainer(trainer_);
        FightFacade.initFight(fight_, player_, diff_, dual_, data_);
        FightFacade.initTypeEnv(fight_, newCoords(0, 0, 0, 0), diff_, data_);
        fight_.getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        fight_.getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        FightFacade.chooseFrontFighter(fight_, (byte) 0, diff_, data_);
        FightFacade.chooseMove(fight_, TOURNIQUET, diff_, data_);
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight_, diff_, player_, data_, false);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        game_.roundWhileKoPlayer(data_, true);
        game_.roundUser(data_);
        game_.roundUser(data_);
        game_.endRoundFightKoUser(data_);
        assertEq(FightState.SWITCH_WHILE_KO_USER, fight_.getState());
        assertTrue(!FightFacade.koTeam(fight_));
    }

    private void newRandomPokemon(Game _game, AbsAreaApparition _area, DataBase _data) {
        _game.newRandomPokemon(_area.getWildPokemonRand(), _data);
    }
}
