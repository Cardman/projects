package aiki.game;

import aiki.db.DataBase;
import aiki.map.pokemon.*;
import aiki.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import org.junit.Test;

import aiki.comments.Comment;
import aiki.db.ImageHeroKey;
import aiki.fight.enums.Statistic;
import aiki.game.enums.InterfaceType;
import aiki.game.fight.FightFacade;
import aiki.game.fight.InitializationDataBase;
import aiki.game.fight.enums.FightState;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.map.DataMap;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Campaign;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.ScreenCoords;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;


public class GameTest extends InitializationDataBase {


    @Test
    public void closestTile1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        assertEq(newCoords(0, 0, 3, 2), game_.closestTile(data_.getMap()));
    }

    @Test
    public void directInteraction1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DON_OBJET, game_.getInterfaceType());
    }

    @Test
    public void directInteraction5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.PK_LEG, game_.getInterfaceType());
    }

    @Test
    public void directInteraction6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(MEW);
        pk_.setAbility(PARATONNERRE);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 1);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takenObjects(newCoords(0, 0, 0, 1));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction12Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction13Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 0, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction14Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.ECH_BOITE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction15Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 0, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.SOIN_PK, game_.getInterfaceType());
    }

    @Test
    public void directInteraction16Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 0, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.FOSSILE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction17Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 8, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.PENSION, game_.getInterfaceType());
    }

    @Test
    public void directInteraction18Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.ACHATS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction19Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.ACHATS_CT, game_.getInterfaceType());
    }

    @Test
    public void directInteraction20Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 6));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.MOVE_TUTORS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction21Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction22Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.GYM_LEADER, game_.getInterfaceType());
    }

    @Test
    public void directInteraction23Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction24Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction25Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction26Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.GYM_LEADER, game_.getInterfaceType());
    }

    @Test
    public void directInteraction27Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.GYM_LEADER, game_.getInterfaceType());
    }

    @Test
    public void directInteraction28Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction29Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction30Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 6));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction31Test() {
        DataBase data_ = initDb();
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
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction32Test() {
        DataBase data_ = initDb();
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
        game_.setRankLeague((byte) 1);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.PERSONNAGE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction33Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.OBJ_RAMAS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction34Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.OBJ_RAMAS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction35Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.OBJ_RAMAS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction36Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 6, 5));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction37Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 7, 5));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction38Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 8, 5));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction39Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction40Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction41Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.DOWN);
        //newCoords(0, 0, 0, 6) is in this data invalid
        game_.directInteraction(newCoords(0, 0, 0, 6), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction42Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.LEFT);
        //newCoords(0, 0, 0, 6) is in this data invalid
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void isEmpty1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(game_.isEmpty(data_.getMap(), newCoords(0, 0, 3, 2)));
    }

    @Test
    public void isEmpty2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(game_.isEmpty(data_.getMap(), newCoords(0, 0, 4, 2)));
    }

    @Test
    public void isEmpty3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(0, 0, 1, 1)));
    }

    @Test
    public void isEmpty4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 11, 2)));
    }

    @Test
    public void isEmpty5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(MEW);
        pk_.setAbility(PARATONNERRE);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 1);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        assertTrue(game_.isEmpty(data_.getMap(), newCoords(2, 0, 11, 2)));
    }

    @Test
    public void isEmpty6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 2, 0)));
    }

    @Test
    public void isEmpty7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 3, 0)));
    }

    @Test
    public void isEmpty8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        assertTrue(game_.isEmpty(data_.getMap(), newCoords(2, 0, 2, 0)));
    }

    @Test
    public void isEmpty9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        assertTrue(game_.isEmpty(data_.getMap(), newCoords(2, 0, 3, 0)));
    }

    @Test
    public void isEmpty10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 2, 0)));
    }

    @Test
    public void isEmpty11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 3, 0)));
    }

    @Test
    public void isEmpty12Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 4, 0)));
    }

    @Test
    public void isEmpty13Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 5, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 5, 0)));
    }

    @Test
    public void isEmpty14Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 6, 5)));
    }

    @Test
    public void isEmpty15Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 7, 5)));
    }

    @Test
    public void isEmpty16Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(2, 0, 8, 5)));
    }

    @Test
    public void isEmpty17Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 6, 5));
        assertTrue(game_.isEmpty(data_.getMap(), newCoords(2, 0, 6, 5)));
    }

    @Test
    public void isEmpty18Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 7, 5));
        assertTrue(game_.isEmpty(data_.getMap(), newCoords(2, 0, 7, 5)));
    }

    @Test
    public void isEmpty19Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 8, 5));
        assertTrue(game_.isEmpty(data_.getMap(), newCoords(2, 0, 8, 5)));
    }

    @Test
    public void isEmpty20Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(1, 0, 1, 1, 4, 0)));
    }

    @Test
    public void isEmpty21Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 0, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(data_.getMap(), newCoords(1, 0, 1, 1, 0, 4)));
    }

    @Test
    public void isEmpty22Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(game_.isEmpty(data_.getMap(), newCoords(1, 0, 1, 1, 4, 1)));
    }

    @Test
    public void takeObject1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takeObject(data_);
        assertEq(4, game_.getTakenObjects().size());
        assertEq(1, nbTakenObjects(game_.getTakenObjects(),true));
        assertEq(3, nbTakenObjects(game_.getTakenObjects(),false));
        assertSame(BoolVal.TRUE,game_.getTakenObjects().getVal(newCoords(2, 0, 6, 5)));
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
    }

    @Test
    public void takeObject2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takeObject(data_);
        assertEq(4, game_.getTakenObjects().size());
        assertEq(1, nbTakenObjects(game_.getTakenObjects(),true));
        assertEq(3, nbTakenObjects(game_.getTakenObjects(),false));
        assertSame(BoolVal.TRUE,game_.getTakenObjects().getVal(newCoords(2, 0, 7, 5)));
        assertTrue(game_.getPlayer().getInventory().gotTm().containsObj((short) 2));
    }

    @Test
    public void takeObject3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takeObject(data_);
        assertEq(4, game_.getTakenObjects().size());
        assertEq(1, nbTakenObjects(game_.getTakenObjects(),true));
        assertEq(3, nbTakenObjects(game_.getTakenObjects(),false));
        assertSame(BoolVal.TRUE,game_.getTakenObjects().getVal(newCoords(2, 0, 8, 5)));
        assertTrue(game_.getPlayer().getInventory().gotHm().containsObj((short) 1));
    }

    @Test
    public void takeObject4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takeObject(data_);
        assertEq(4, game_.getTakenObjects().size());
        assertEq(1, nbTakenObjects(game_.getTakenObjects(),true));
        assertEq(3, nbTakenObjects(game_.getTakenObjects(),false));
        assertSame(BoolVal.TRUE,game_.getTakenObjects().getVal(newCoords(0, 0, 0, 1)));
        assertTrue(game_.getPlayer().getInventory().gotTm().containsObj((short) 5));
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
    }

    @Test
    public void healTeamWithoutUsingObjectTest() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        PokemonPlayer pk_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        pk_.setRemainedHp(Rate.one());
        game_.healTeamWithoutUsingObject(data_);
        assertEq(new Rate("3037/100"), pk_.getRemainingHp());
    }
    private static int nbTakenObjects(CoordssBoolVal _map, boolean _taken) {
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
        return DataBase.countValues(_map.values(), ComparatorBoolean.of(_taken));
    }

    @Test
    public void roundAllThrowers1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 24);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, data_);
        game_.setChosenHealingItem(EAU_FRAICHE, data_);
        game_.roundAllThrowers(data_, false);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
    }

    @Test
    public void roundAllThrowers2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
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
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, data_);
        game_.setChosenHealingItem(EAU_FRAICHE, data_);
        game_.roundAllThrowers(data_, false);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        assertTrue(!FightFacade.koTeam(game_.getFight()));
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
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
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(BULLES_D_O, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, false);
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        assertTrue(FightFacade.koTeam(game_.getFight()));
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(TETARTE);
        pk_.setLevel((short) 100);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(TETARTE, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(BOUE_BOMBE, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, false);
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(FightFacade.win(game_.getFight()));
    }

    @Test
    public void roundAllThrowers5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 5);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.roundAllThrowers(data_, false);
        assertTrue(!FightFacade.koTeam(game_.getFight()));
    }

    @Test
    public void roundAllThrowers6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 5);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 5);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.getFight().wildPokemon().setRemainedHp(new Rate("56/5"));
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        assertTrue(FightFacade.koTeam(game_.getFight()));
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 5);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.getFight().wildPokemon().setRemainedHp(new Rate("56/5"));
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.chooseBackFighter((byte) 0, data_);
        game_.roundAllThrowers(data_, false);
        assertTrue(FightFacade.win(game_.getFight()));
    }

    @Test
    public void roundAllThrowers9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 5);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.chooseBackFighter((byte) 0, data_);
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 5);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, data_);
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(DEMI_TOUR, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, true);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
        assertTrue(game_.getFight().getFightType().isWild());
        assertTrue(game_.getFight().getTemp().isKeepRound());
    }

    @Test
    public void endRoundFightBasic1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 24);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, data_);
        game_.setChosenHealingItem(EAU_FRAICHE, data_);
        game_.roundAllThrowers(data_, true);
        game_.roundUser(data_);
        game_.roundUser(data_);
        game_.endRoundFightBasic(data_);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
    }

    @Test
    public void endRoundFightBasic2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
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
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, data_);
        game_.setChosenHealingItem(EAU_FRAICHE, data_);
        game_.roundAllThrowers(data_, true);
        game_.roundUser(data_);
        game_.roundUser(data_);
        game_.endRoundFightBasic(data_);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        assertTrue(!FightFacade.koTeam(game_.getFight()));
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void endRoundFightBasic3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(TETARTE);
        pk_.setLevel((short) 100);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(TETARTE, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(BOUE_BOMBE, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, true);
        game_.roundUser(data_);
        game_.endRoundFightBasic(data_);
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(FightFacade.win(game_.getFight()));
    }

    @Test
    public void learnAndEvolve1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
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
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(BULLES_D_O, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        game_.addOrForgetMove(ECUME);
        game_.learnAndEvolve(data_);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
    }

    @Test
    public void learnAndEvolve2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
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
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(BULLES_D_O, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(data_);
        assertEq(FightState.SWITCH_PROPOSE, game_.getFight().getState());
    }

    @Test
    public void learnAndEvolve3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
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
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, data_);
        game_.setChosenHealingItem(EAU_FRAICHE, data_);
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(data_);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void learnAndEvolve4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getPlayer().getItem(EAU_FRAICHE);
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
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlace((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelCompaignByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(BULLES_D_O, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(data_);
        assertTrue(FightFacade.win(game_.getFight()));
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void sendSubstitutes1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
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
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(BULLES_D_O, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(data_);
        assertEq(FightState.SWITCH_PROPOSE, game_.getFight().getState());
        game_.sendSubstitutes(data_);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
    }

    @Test
    public void sendSubstitutes2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
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
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(BULLES_D_O, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).increment();
        game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(Rate.one());
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(data_);
        assertEq(FightState.SWITCH_PROPOSE, game_.getFight().getState());
        game_.sendSubstitutes(data_);
        assertEq(new LgInt("3000"),game_.getPlayer().getMoney());
        assertTrue(FightFacade.win(game_.getFight()));
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.learnAndEvolve(data_);
        assertEq(new LgInt("17000"),game_.getPlayer().getMoney());
    }

    @Test
    public void sendSubstitutes3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(TETARTE);
        pk_.setLevel((short) 100);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.initTrainerFight(data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, data_);
        game_.chooseMove(BOUE_BOMBE, data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).increment();
        game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(Rate.one());
        game_.roundAllThrowers(data_, false);
        assertEq(FightState.SWITCH_PROPOSE, game_.getFight().getState());
        game_.sendSubstitutes(data_);
        assertTrue(FightFacade.win(game_.getFight()));
        assertEq(new LgInt("17000"),game_.getPlayer().getMoney());
    }

    @Test
    public void movingHero1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 4));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(2, 0, 2, 3), game_.getPlayerCoords());
    }

    @Test
    public void movingHero2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(4, 0, 2, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(2, 0, 2, 5), game_.getPlayerCoords());
    }

    @Test
    public void movingHero3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 1, 4, 8), game_.getPlayerCoords());
    }

    @Test
    public void movingHero4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 1, 4, 7), game_.getPlayerCoords());
    }

    @Test
    public void movingHero6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 7));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 7, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 1, 7, 8), game_.getPlayerCoords());
    }

    @Test
    public void movingHero8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(1, 0, 4, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(4, 0, 0, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(5, 0, 7, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(2, 0, 11, 3), game_.getPlayerCoords());
    }

    @Test
    public void movingHero11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 7, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(data_);
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(4, 0, 0, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero12Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.movingHero(data_);
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(5, 1, 7, 5), game_.getPlayerCoords());
    }

    @Test
    public void movingHero13Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 7, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(5, 0, 7, 4), game_.getPlayerCoords());
    }

    @Test
    public void movingHero14Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(5, 0, 1, 4), game_.getPlayerCoords());
    }

    @Test
    public void movingHero15Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(4, 0, 1, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(4, 0, 1, 3), game_.getPlayerCoords());
    }

    @Test
    public void movingHero16Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(4, 0, 1, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(4, 0, 1, 4), game_.getPlayerCoords());
    }

    @Test
    public void movingHero17Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        //map game_.setPlayerCoords(newCoords(4, 0, 4, 4));
        game_.setPlayerCoords(newCoords(4, 0, 5, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(data_);
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 0, 4, 8), game_.getPlayerCoords());
    }

    @Test
    public void movingHero18Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(6, 0, 4, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 0, 4, 7), game_.getPlayerCoords());
    }

    @Test
    public void movingHero19Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(6, 0, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 0, 4, 0), game_.getPlayerCoords());
    }

    @Test
    public void movingHero20Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
    }

    @Test
    public void movingHero21Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(6, 0, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setRankLeague((byte) 1);
        game_.movingHero(data_);
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 1, 4, 8), game_.getPlayerCoords());
    }

    @Test
    public void movingHero22Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(6, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setRankLeague((byte) 1);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 1, 4, 0), game_.getPlayerCoords());
    }

    @Test
    public void movingHero23Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(6, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setRankLeague((byte) 2);
        game_.movingHero(data_);
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void movingHero24Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.movingHero(data_);
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void processWalkingAreaApparition0Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.movingHero(data_);
        game_.attract(data_);
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
    }
    @Test
    public void processWalkingAreaApparition1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.movingHero(data_);
        game_.processWalkingAreaApparition(game_.closestTile(data_.getMap()), data_);
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 3));
        game_.setPlayerOrientation(Direction.UP);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.movingHero(data_);
        game_.processWalkingAreaApparition(game_.closestTile(data_.getMap()), data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.DON_OBJET, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 1, 2));
        game_.setPlayerOrientation(Direction.LEFT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.movingHero(data_);
        game_.processWalkingAreaApparition(game_.closestTile(data_.getMap()), data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 3, 0));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.movingHero(data_);
        game_.processWalkingAreaApparition(game_.closestTile(data_.getMap()), data_);
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.movingHero(data_);
        game_.processWalkingAreaApparition(game_.closestTile(data_.getMap()), data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 3, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.movingHero(data_);
        game_.processWalkingAreaApparition(game_.closestTile(data_.getMap()), data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(4, 0, 5, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.movingHero(data_);
        game_.processWalkingAreaApparition(game_.closestTile(data_.getMap()), data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.movingHero(data_);
        game_.processWalkingAreaApparition(game_.closestTile(data_.getMap()), data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(data_);
        game_.movingHero(data_);
        game_.processWalkingAreaApparition(game_.closestTile(data_.getMap()), data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void calculateImagesFromTiles1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(5,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(0,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,1)).first());
        assertEq(data_.getPerson(TRAINER_ONE), foreGround_.getVal(new ScreenCoords(8,0)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(6,8)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 9, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(73, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(6,4)).first());
        assertEq(data_.getPerson(TRAINER_TWO), foreGround_.getVal(new ScreenCoords(0,0)).first());
        assertEq(data_.getMiniPk().getVal(ARTIKODIN), foreGround_.getVal(new ScreenCoords(4,5)).first());
        assertEq(data_.getMiniPk().getVal(MEW), foreGround_.getVal(new ScreenCoords(6,2)).first());
        assertEq(data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(3,5)).first());
        assertEq(data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(2,5)).first());
        assertEq(data_.getMiniItems().getVal(HYPER_BALL), foreGround_.getVal(new ScreenCoords(1,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 9, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.takenObjects(newCoords(2, 0, 6, 5));
        game_.takenObjects(newCoords(2, 0, 7, 5));
        game_.takenObjects(newCoords(2, 0, 8, 5));
        game_.takenPokemon(newCoords(2, 0, 9, 5));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(6,4)).first());
        assertEq(data_.getMiniPk().getVal(MEW), foreGround_.getVal(new ScreenCoords(6,2)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(5,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(0,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,1)).first());
        assertEq(data_.getPerson(TRAINER_ONE), foreGround_.getVal(new ScreenCoords(8,0)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(6,8)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 9, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(73, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(6,4)).first());
        assertEq(data_.getPerson(TRAINER_TWO), foreGround_.getVal(new ScreenCoords(0,0)).first());
        assertEq(data_.getMiniPk().getVal(ARTIKODIN), foreGround_.getVal(new ScreenCoords(4,5)).first());
        assertEq(data_.getMiniPk().getVal(MEW), foreGround_.getVal(new ScreenCoords(6,2)).first());
        assertEq(data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(3,5)).first());
        assertEq(data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(2,5)).first());
        assertEq(data_.getMiniItems().getVal(HYPER_BALL), foreGround_.getVal(new ScreenCoords(1,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 9, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.takenObjects(newCoords(2, 0, 6, 5));
        game_.takenObjects(newCoords(2, 0, 7, 5));
        game_.takenObjects(newCoords(2, 0, 8, 5));
        game_.takenPokemon(newCoords(2, 0, 9, 5));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(6,4)).first());
        assertEq(data_.getMiniPk().getVal(MEW), foreGround_.getVal(new ScreenCoords(6,2)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(5, 1, 6, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,4)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,2)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(3,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        assertTrue(!data_.getOverWorldHeros().contains(key_));
        key_ = new ImageHeroKey(EnvironmentType.ROAD, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(5, 1, 6, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,4)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,2)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(3,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        assertTrue(!data_.getOverWorldHeros().contains(key_));
        key_ = new ImageHeroKey(EnvironmentType.ROAD, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(0,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,1)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(6,8)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles12Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(0,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,1)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(6,8)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles13Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(4, 0, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(75, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,3)).first());
        //map assertEq(data.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,6)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,5)).first());
        assertEq(data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(8,0)).first());
        assertEq(data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(7,0)).first());
        assertEq(data_.getMiniItems().getVal(HYPER_BALL), foreGround_.getVal(new ScreenCoords(6,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    private static CustList<ScreenCoords> getEmptyTiles(
            ScreenCoordssCustListInt _foreGround) {
        return ScreenCoordssCustListInt.emptyTiles(_foreGround);
    }

    @Test
    public void calculateImagesFromTiles14Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(4, 0, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(75, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,3)).first());
        //map assertEq(data.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,6)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,5)).first());
        assertEq(data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(8,0)).first());
        assertEq(data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(7,0)).first());
        assertEq(data_.getMiniItems().getVal(HYPER_BALL), foreGround_.getVal(new ScreenCoords(6,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles15Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,7)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(7,7)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles16Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,7)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(7,7)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles17Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(74, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(data_.getStorage(), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(0,4)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,4)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,5)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,6)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles18Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(74, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(data_.getStorage(), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(0,4)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,4)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,5)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,6)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles19Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(data_.getStorage(), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(0,4)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,4)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles20Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(data_.getStorage(), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(0,4)).first());
        assertEq(data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,4)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles21Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(5, 0, 6, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,4)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,2)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,1)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        assertTrue(!data_.getOverWorldHeros().contains(key_));
        key_ = new ImageHeroKey(EnvironmentType.ROAD, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles22Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(5, 0, 6, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,4)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,2)).first());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,1)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        assertTrue(!data_.getOverWorldHeros().contains(key_));
        key_ = new ImageHeroKey(EnvironmentType.ROAD, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles23Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 0, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles24Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 0, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles25Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles26Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles27Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(79, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,3)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles28Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(79, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,3)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles29Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(79, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,3)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles30Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(data_);
        game_.calculateImagesFromTiles(data_, 0, 0);
        ScreenCoordssCustListInt foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(79, getEmptyTiles(foreGround_).size());
        assertEq(data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,3)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void incrementStepsToLayEggs1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.incrementStepsToLayEggs(data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        assertTrue(hosted_.isFree());
        assertEq(0, hosted_.getNbSteps());
    }

    @Test
    public void incrementStepsToLayEggs2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertNotSame(pokemonFemale_.getGender(), pokemonMale_.getGender());
        game_.incrementStepsToLayEggs(data_);
        assertTrue(!hosted_.isFree());
        assertEq(1, hosted_.getNbSteps());
    }

    @Test
    public void incrementStepsToLayEggs3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setNbSteps(1024);
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertNotSame(pokemonFemale_.getGender(), pokemonMale_.getGender());
        game_.incrementStepsToLayEggs(data_);
        assertTrue(!hosted_.isFree());
        assertEq(1024, hosted_.getNbSteps());
    }

    @Test
    public void canStoreThesePokemonToHost1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
    }

    @Test
    public void canStoreThesePokemonToHost2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(NUCLEOS);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost12Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(NUCLEOS);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost13Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost14Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost15Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost16Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost17Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost18Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost19Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost20Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost21Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost22Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost23Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_M);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_F);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost24Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_M);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStorePokemonToHost1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(NUCLEOS);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost12Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(NUCLEOS);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost13Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost14Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost15Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost16Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost17Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost18Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost19Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost20Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost21Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, data_));
    }

    @Test
    public void canStorePokemonToHost22Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost23Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_M);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_F);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void canStorePokemonToHost24Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_M);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, data_));
    }

    @Test
    public void storePokemonToHost1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer second_ = (PokemonPlayer) game_.getPlayer().getTeam().get(2);
        game_.storePokemonToHost((short) 1,(short) 2, newCoords(3, 0, 2, 1, 8, 4));
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        assertEq(0, hosted_.getNbSteps());
        assertSame(first_, hosted_.getFirstPokemon());
        assertSame(second_, hosted_.getSecondPokemon());
    }

    @Test
    public void storePokemonToHost2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer second_ = (PokemonPlayer) game_.getPlayer().getTeam().get(2);
        game_.storePokemonToHost((short) 2,(short) 1, newCoords(3, 0, 2, 1, 8, 4));
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        assertEq(0, hosted_.getNbSteps());
        assertSame(second_, hosted_.getFirstPokemon());
        assertSame(first_, hosted_.getSecondPokemon());
    }

    @Test
    public void attemptForStoringPokemonToHost1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 0,(short) 1, data_);
        assertEq(2, game_.getPlayer().getTeam().size());
        assertTrue(game_.isReinitInteraction());
        assertTrue(game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void attemptForStoringPokemonToHost2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.isReinitInteraction());
        assertTrue(game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void attemptForStoringPokemonToHost3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer second_ = (PokemonPlayer) game_.getPlayer().getTeam().get(2);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(1, game_.getPlayer().getTeam().size());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        assertEq(0, hosted_.getNbSteps());
        assertSame(first_, hosted_.getFirstPokemon());
        assertSame(second_, hosted_.getSecondPokemon());
    }

    @Test
    public void attemptForStoringPokemonToHost4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer second_ = (PokemonPlayer) game_.getPlayer().getTeam().get(2);
        game_.attemptForStoringPokemonToHost((short) 2,(short) 1, data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(1, game_.getPlayer().getTeam().size());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        assertEq(0, hosted_.getNbSteps());
        assertSame(second_, hosted_.getFirstPokemon());
        assertSame(first_, hosted_.getSecondPokemon());
    }

    @Test
    public void nbRemainingSteps1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertEq(256, game_.nbRemainingSteps(newCoords(3, 0, 2, 1, 8, 4), data_));
        game_.incrementStepsToLayEggs(data_);
        assertEq(255, game_.nbRemainingSteps(newCoords(3, 0, 2, 1, 8, 4), data_));
    }

    @Test
    public void nbRemainingSteps2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertEq(1024, game_.nbRemainingSteps(newCoords(3, 0, 2, 1, 8, 4), data_));
        game_.incrementStepsToLayEggs(data_);
        assertEq(1023, game_.nbRemainingSteps(newCoords(3, 0, 2, 1, 8, 4), data_));
    }

    @Test
    public void canGetEgg1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        assertTrue(!game_.canGetEgg(newCoords(3, 0, 2, 1, 8, 4), data_));
    }

    @Test
    public void canGetEgg2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertTrue(!game_.canGetEgg(newCoords(3, 0, 2, 1, 8, 4), data_));
    }

    @Test
    public void canGetEgg3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        assertTrue(game_.canGetEgg(newCoords(3, 0, 2, 1, 8, 4), data_));
    }

    @Test
    public void lawForProductedEgg1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), data_);
        assertEq(1, law_.nbEvents());
        assertTrue(law_.containsEvent(PTITARD));
    }

    @Test
    public void lawForProductedEgg2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), data_);
        assertEq(2, law_.nbEvents());
        assertEq(LgInt.one(), law_.rate(PTITARD));
        assertEq(LgInt.one(), law_.rate(BABIMANTA));
    }

    @Test
    public void lawForProductedEgg3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer pokemonNoGender_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonNoGender_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), data_);
        assertEq(1, law_.nbEvents());
        assertTrue(law_.containsEvent(PTITARD));
    }

    @Test
    public void lawForProductedEgg4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer pokemonNoGender_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonNoGender_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), data_);
        assertEq(1, law_.nbEvents());
        assertTrue(law_.containsEvent(PTITARD));
    }

    @Test
    public void lawForProductedEgg5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer firstPokemonNoGender_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer secondPokemonNoGender_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(firstPokemonNoGender_);
        hosted_.setSecondPokemon(secondPokemonNoGender_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), data_);
        assertEq(1, law_.nbEvents());
        assertTrue(law_.containsEvent(PIKACHU));
    }

    @Test
    public void lawForProductedEgg6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer firstPokemonNoGender_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer secondPokemonNoGender_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(firstPokemonNoGender_);
        hosted_.setSecondPokemon(secondPokemonNoGender_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), data_);
        assertEq(1, law_.nbEvents());
        assertTrue(law_.containsEvent(LIMAGMA));
    }

    @Test
    public void productedEgg1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        Egg egg_ = game_.productedEgg(newCoords(3, 0, 2, 1, 8, 4), data_);
        assertEq(PTITARD,egg_.getName());
        assertEq(0, egg_.getSteps());
    }

    @Test
    public void takeProductedEgg1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        game_.takeProductedEgg(newCoords(3, 0, 2, 1, 8, 4));
        assertEq(0, hosted_.getNbSteps());
        assertSame(pokemonFemale_, hosted_.getFirstPokemon());
        assertSame(pokemonMale_, hosted_.getSecondPokemon());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void takePokemonFromHost1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        game_.takePokemonFromHost(newCoords(3, 0, 2, 1, 8, 4));
        assertEq(3, game_.getPlayer().getTeam().size());
        assertSame(pokemonFemale_, game_.getPlayer().getTeam().get(1));
        assertSame(pokemonMale_, game_.getPlayer().getTeam().get(2));
    }

    @Test
    public void takablePokemonFromHost1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        assertTrue(game_.takablePokemonFromHost(data_));
    }

    @Test
    public void takablePokemonFromHost2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        assertTrue(!game_.takablePokemonFromHost(data_));
    }

    @Test
    public void receiveOnlyEgg1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        game_.receiveOnlyEgg(data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(1, game_.getPlayer().getTeam().size());
    }

    @Test
    public void receiveOnlyEgg2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        game_.receiveOnlyEgg(data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(2, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveOnlyEgg3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        assertEq(6, game_.getPlayer().getTeam().size());
        game_.receiveOnlyEgg(data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(6, game_.getPlayer().getTeam().size());
        assertEq(256, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveOnlyEgg4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(5, game_.getPlayer().getTeam().size());
        game_.receiveOnlyEgg(data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(5, game_.getPlayer().getTeam().size());
    }

    @Test
    public void receiveOnlyEgg5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(4, game_.getPlayer().getTeam().size());
        game_.receiveOnlyEgg(data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(4, game_.getPlayer().getTeam().size());
    }

    @Test
    public void receiveEggOrParents1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        assertEq(1, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(4, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveEggOrParents2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        assertEq(4, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(5, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveEggOrParents3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        assertEq(6, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(6, game_.getPlayer().getTeam().size());
        assertEq(256, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveEggOrParents5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        assertEq(1, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(3, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveEggOrParents6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), data_);
        assertEq(5, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(5, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void moving1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.moving(Direction.LEFT, data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 2, 1, 7, 4),game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.moving(Direction.RIGHT, data_);
        assertEq(Direction.RIGHT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 2, 1, 7, 4),game_.getPlayerCoords());
        assertEq(InterfaceType.PENSION, game_.getInterfaceType());
        assertEq(0, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.moving(Direction.LEFT, data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(0, 0, 0, 0),game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.moving(Direction.RIGHT, data_);
        assertEq(Direction.RIGHT,game_.getPlayerOrientation());
        assertEq(newCoords(0, 0, 1, 0),game_.getPlayerCoords());
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
        assertEq(1, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void moving5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 0, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.moving(Direction.LEFT, data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 2, 1, 0, 4),game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 3, 2));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.moving(Direction.LEFT, data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 2, 2),game_.getPlayerCoords());
        assertEq(4, game_.getVisitedPlaces().size());
        assertEq(4, game_.getVisitedPlacesNb().size());
        assertEq(3, game_.getUnVisited().size());
        assertSame(BoolVal.TRUE,game_.getVisitedPlaces().getVal(newCoords(3, 0, 2, 2)));
        assertSame(BoolVal.FALSE,game_.getVisitedPlaces().getVal(newCoords(1, 0, 1, 2)));
        assertSame(BoolVal.FALSE,game_.getVisitedPlaces().getVal(newCoords(7, 0, 3, 4)));
        assertSame(BoolVal.FALSE,game_.getVisitedPlaces().getVal(newCoords(8, 0, 3, 4)));
        assertSame(BoolVal.TRUE,game_.getVisitedPlacesNb().getVal((short) 3));
        assertSame(BoolVal.FALSE,game_.getVisitedPlacesNb().getVal((short) 1));
        assertSame(BoolVal.FALSE,game_.getVisitedPlacesNb().getVal((short) 7));
        assertSame(BoolVal.FALSE,game_.getVisitedPlacesNb().getVal((short) 8));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(1, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 4, 2));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.moving(Direction.LEFT, data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 3, 2),game_.getPlayerCoords());
        assertEq(4, game_.getVisitedPlaces().size());
        assertEq(4, game_.getVisitedPlacesNb().size());
        assertEq(4, game_.getUnVisited().size());
        assertSame(BoolVal.FALSE,game_.getVisitedPlaces().getVal(newCoords(3, 0, 2, 2)));
        assertSame(BoolVal.FALSE,game_.getVisitedPlaces().getVal(newCoords(1, 0, 1, 2)));
        assertSame(BoolVal.FALSE,game_.getVisitedPlaces().getVal(newCoords(7, 0, 3, 4)));
        assertSame(BoolVal.FALSE,game_.getVisitedPlaces().getVal(newCoords(8, 0, 3, 4)));
        assertSame(BoolVal.FALSE,game_.getVisitedPlacesNb().getVal((short) 3));
        assertSame(BoolVal.FALSE,game_.getVisitedPlacesNb().getVal((short) 1));
        assertSame(BoolVal.FALSE,game_.getVisitedPlacesNb().getVal((short) 7));
        assertSame(BoolVal.FALSE,game_.getVisitedPlacesNb().getVal((short) 8));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(1, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 4, 2));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.catchAll(data_);
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.beatGymLeader(newCoords(5, 0, 2, 0));
        game_.beatGymLeader(newCoords(6, 0, 4, 8));
        game_.visitPlace(newCoords(1, 0, 1, 2));
        game_.visitPlace(newCoords(3, 0, 2, 2));
        game_.visitPlace(newCoords(7, 0, 3, 4));
        game_.visitPlace(newCoords(8, 0, 3, 4));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(9, 0, 1, 1), 1));
        game_.beatTrainer(new NbFightCoords(newCoords(2, 0, 11, 4), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 0, 1, 5), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(5, 1, 5, 1), 0));
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().first();
        first_.setLevel((short) data_.getMaxLevel());
        first_.setHappiness((short) data_.getHappinessMax());
        game_.moving(Direction.LEFT, data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 3, 2),game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(1, game_.getNbSteps());
        assertTrue(game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void isFrontOfTrainer1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer3Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer4Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer5Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer6Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer7Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(2, 0, 5, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer8Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer9Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer10Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer11Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer12Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 6));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer13Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 6));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(data_));
    }

    @Test
    public void isFrontOfTrainer14Test() {
        DataBase data_ = initDb();
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
        game_.directInteraction(game_.closestTile(data_.getMap()), data_.getMap());
        assertTrue(game_.isFrontOfTrainer(data_));
    }

    @Test
    public void doRevivingFossil1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getItem(LAVA);
        game_.getDifficulty().setRandomWildFight(false);
        game_.doRevivingFossil(LAVA, data_);
        assertEq(2, game_.getTeam().size());
    }

    @Test
    public void nickname1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        PokemonPlayer pkUserTeam_ = (PokemonPlayer) game_.getTeam().get(0);
        assertEq(PIKACHU, pkUserTeam_.getNickname());
        game_.setChosenTeamPokemon((short) 0);
        game_.nickname(NULL_REF, data_);
        pkUserTeam_ = (PokemonPlayer) game_.getTeam().get(0);
        assertEq(PIKACHU, pkUserTeam_.getNickname());
    }

    @Test
    public void initIv1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        Player player_ = game_.getPlayer();
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        Pokemon pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(FOUR);
        pk_.setLevel((short) 7);
        pk_.setItem(NULL_REF);
        player_.getBox().add(new PokemonPlayer(pk_, data_));
        egg_ = new Egg(PIKACHU);
        player_.getBox().add(egg_);
        PokemonPlayer pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        pkPlayer_.getIv().clear();
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        pkPlayer_.getIv().clear();
        game_.initIv(data_);
        pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP));
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void initIv2Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        Player player_ = game_.getPlayer();
        Egg egg_ = new Egg(PIKACHU);
        player_.getTeam().add(egg_);
        Pokemon pk_ = new WildPk();
        pk_.setName(LIMAGMA);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(FOUR);
        pk_.setLevel((short) 7);
        pk_.setItem(NULL_REF);
        player_.getBox().add(new PokemonPlayer(pk_, data_));
        egg_ = new Egg(PIKACHU);
        player_.getBox().add(egg_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 1);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.FEMALE);
        hosted_.setFirstPokemon(new PokemonPlayer(pk_, data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 1);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.MALE);
        hosted_.setSecondPokemon(new PokemonPlayer(pk_, data_));
        PokemonPlayer pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        pkPlayer_.getIv().clear();
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        pkPlayer_.getIv().clear();
        hosted_.getFirstPokemon().getIv().clear();
        hosted_.getSecondPokemon().getIv().clear();
        game_.initIv(data_);
        pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP));
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP));
        pkPlayer_ = hosted_.getFirstPokemon();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP));
        pkPlayer_ = hosted_.getSecondPokemon();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED));
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP));
    }

    @Test
    public void clearMessages1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getItem(LAVA);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getCommentGame().addMessage(NICKNAME);
        game_.clearMessages();
        assertEq(0, game_.getCommentGame().getMessages().size());
    }

    @Test
    public void addMessageGymLeader1Test() {
        DataBase data_ = initDb();
        Game game_ = new Game(data_);
        game_.initUtilisateur(NICKNAME, new Difficulty(), data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getItem(LAVA);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getCommentGame().addMessage(NICKNAME);
        game_.addMessageGymLeader(data_);
        assertEq(1, game_.getCommentGame().getMessages().size());
    }

    @Test
    public void new_Game_Test() {
        Game game_ = new Game();
        assertEq(0, game_.getBeatGymTrainer().size());
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
    }
}
