package aiki.game;
import static aiki.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.ImageHeroKey;
import aiki.comments.Comment;
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
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.ScreenCoords;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;

@SuppressWarnings("static-method")
public class GameTest extends InitializationDataBase {

    @Test
    public void closestTile1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        assertEq(newCoords(0, 0, 3, 2), game_.closestTile(_data_.getMap()));
    }

    @Test
    public void directInteraction1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DON_OBJET, game_.getInterfaceType());
    }

    @Test
    public void directInteraction5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.PK_LEG, game_.getInterfaceType());
    }

    @Test
    public void directInteraction6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(MEW);
        pk_.setAbility(PARATONNERRE);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 1);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction9Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction10Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.PECHE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction11Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takenObjects(newCoords(0, 0, 0, 1));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction12Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction13Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 0, 0));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction14Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.ECH_BOITE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction15Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 0, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.SOIN_PK, game_.getInterfaceType());
    }

    @Test
    public void directInteraction16Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 0, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.FOSSILE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction17Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 8, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.PENSION, game_.getInterfaceType());
    }

    @Test
    public void directInteraction18Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.ACHATS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction19Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.ACHATS_CT, game_.getInterfaceType());
    }

    @Test
    public void directInteraction20Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 7, 6));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.MOVE_TUTORS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction21Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction22Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.GYM_LEADER, game_.getInterfaceType());
    }

    @Test
    public void directInteraction23Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction24Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction25Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction26Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.GYM_LEADER, game_.getInterfaceType());
    }

    @Test
    public void directInteraction27Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.GYM_LEADER, game_.getInterfaceType());
    }

    @Test
    public void directInteraction28Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction29Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction30Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 6));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction31Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction32Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.setRankLeague((byte) 1);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.PERSONNAGE, game_.getInterfaceType());
    }

    @Test
    public void directInteraction33Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.OBJ_RAMAS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction34Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.OBJ_RAMAS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction35Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.OBJ_RAMAS, game_.getInterfaceType());
    }

    @Test
    public void directInteraction36Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 6, 5));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction37Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 7, 5));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction38Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 8, 5));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction39Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction40Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 0));
        game_.beatTrainer(new NbFightCoords(newCoords(0, 0, 1, 1), 1));
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void directInteraction41Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.DOWN);
        //newCoords(0, 0, 0, 6) is in this data invalid
        game_.directInteraction(newCoords(0, 0, 0, 6), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void directInteraction42Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.LEFT);
        //newCoords(0, 0, 0, 6) is in this data invalid
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void isEmpty1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(game_.isEmpty(_data_.getMap(), newCoords(0, 0, 3, 2)));
    }

    @Test
    public void isEmpty2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 3, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(game_.isEmpty(_data_.getMap(), newCoords(0, 0, 4, 2)));
    }

    @Test
    public void isEmpty3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(0, 0, 1, 1)));
    }

    @Test
    public void isEmpty4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 11, 2)));
    }

    @Test
    public void isEmpty5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(MEW);
        pk_.setAbility(PARATONNERRE);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 1);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        assertTrue(game_.isEmpty(_data_.getMap(), newCoords(2, 0, 11, 2)));
    }

    @Test
    public void isEmpty6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 2, 0)));
    }

    @Test
    public void isEmpty7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 3, 0)));
    }

    @Test
    public void isEmpty8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        assertTrue(game_.isEmpty(_data_.getMap(), newCoords(2, 0, 2, 0)));
    }

    @Test
    public void isEmpty9Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        assertTrue(game_.isEmpty(_data_.getMap(), newCoords(2, 0, 3, 0)));
    }

    @Test
    public void isEmpty10Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 2, 0)));
    }

    @Test
    public void isEmpty11Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 4, 0));
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 3, 0)));
    }

    @Test
    public void isEmpty12Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 4, 0)));
    }

    @Test
    public void isEmpty13Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 5, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 5, 0)));
    }

    @Test
    public void isEmpty14Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 6, 5)));
    }

    @Test
    public void isEmpty15Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 7, 5)));
    }

    @Test
    public void isEmpty16Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(2, 0, 8, 5)));
    }

    @Test
    public void isEmpty17Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 6, 5));
        assertTrue(game_.isEmpty(_data_.getMap(), newCoords(2, 0, 6, 5)));
    }

    @Test
    public void isEmpty18Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 7, 5));
        assertTrue(game_.isEmpty(_data_.getMap(), newCoords(2, 0, 7, 5)));
    }

    @Test
    public void isEmpty19Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.takenObjects(newCoords(2, 0, 8, 5));
        assertTrue(game_.isEmpty(_data_.getMap(), newCoords(2, 0, 8, 5)));
    }

    @Test
    public void isEmpty20Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(1, 0, 1, 1, 4, 0)));
    }

    @Test
    public void isEmpty21Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 0, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isEmpty(_data_.getMap(), newCoords(1, 0, 1, 1, 0, 4)));
    }

    @Test
    public void isEmpty22Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(game_.isEmpty(_data_.getMap(), newCoords(1, 0, 1, 1, 4, 1)));
    }

    @Test
    public void takeObject1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 6, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takeObject(_data_);
        assertEq(4, game_.getTakenObjects().size());
        assertEq(1, nbTakenObjects(game_.getTakenObjects(),true));
        assertEq(3, nbTakenObjects(game_.getTakenObjects(),false));
        assertTrue(game_.getTakenObjects().getVal(newCoords(2, 0, 6, 5)));
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
    }

    @Test
    public void takeObject2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takeObject(_data_);
        assertEq(4, game_.getTakenObjects().size());
        assertEq(1, nbTakenObjects(game_.getTakenObjects(),true));
        assertEq(3, nbTakenObjects(game_.getTakenObjects(),false));
        assertTrue(game_.getTakenObjects().getVal(newCoords(2, 0, 7, 5)));
        assertTrue(game_.getPlayer().getInventory().gotTm().containsObj((short) 2));
    }

    @Test
    public void takeObject3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 8, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takeObject(_data_);
        assertEq(4, game_.getTakenObjects().size());
        assertEq(1, nbTakenObjects(game_.getTakenObjects(),true));
        assertEq(3, nbTakenObjects(game_.getTakenObjects(),false));
        assertTrue(game_.getTakenObjects().getVal(newCoords(2, 0, 8, 5)));
        assertTrue(game_.getPlayer().getInventory().gotHm().containsObj((short) 1));
    }

    @Test
    public void takeObject4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.takeObject(_data_);
        assertEq(4, game_.getTakenObjects().size());
        assertEq(1, nbTakenObjects(game_.getTakenObjects(),true));
        assertEq(3, nbTakenObjects(game_.getTakenObjects(),false));
        assertTrue(game_.getTakenObjects().getVal(newCoords(0, 0, 0, 1)));
        assertTrue(game_.getPlayer().getInventory().gotTm().containsObj((short) 5));
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(HYPER_BALL));
    }

    private int nbTakenObjects(ObjectMap<Coords,Boolean> _map,boolean _taken) {
        int n_ = CustList.FIRST_INDEX;
        if (_taken) {
            for (EntryCust<Coords, Boolean> e: _map.entryList()) {
                if (e.getValue()) {
                    n_++;
                }
            }
        } else {
            for (EntryCust<Coords, Boolean> e: _map.entryList()) {
                if (!e.getValue()) {
                    n_++;
                }
            }
        }
        return n_;
    }

    @Test
    public void roundAllThrowers1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.setChosenHealingItem(EAU_FRAICHE, _data_);
        game_.roundAllThrowers(_data_, false);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
    }

    @Test
    public void roundAllThrowers2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.setChosenHealingItem(EAU_FRAICHE, _data_);
        game_.roundAllThrowers(_data_, false);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        assertTrue(!FightFacade.koTeam(game_.getFight()));
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(BULLES_D_O, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        assertTrue(FightFacade.koTeam(game_.getFight()));
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(TETARTE, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(BOUE_BOMBE, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(FightFacade.win(game_.getFight()));
    }

    @Test
    public void roundAllThrowers5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, _data_);
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.roundAllThrowers(_data_, false);
        assertTrue(!FightFacade.koTeam(game_.getFight()));
    }

    @Test
    public void roundAllThrowers6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, _data_);
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, _data_);
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.getFight().wildPokemon().setRemainedHp(new Rate("56/5"));
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        assertTrue(FightFacade.koTeam(game_.getFight()));
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, _data_);
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.getFight().wildPokemon().setRemainedHp(new Rate("56/5"));
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.chooseBackFighter((byte) 0, _data_);
        game_.roundAllThrowers(_data_, false);
        assertTrue(FightFacade.win(game_.getFight()));
    }

    @Test
    public void roundAllThrowers9Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, _data_);
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.SWITCH_APRES_ATTAQUE, game_.getFight().getState());
        game_.chooseBackFighter((byte) 0, _data_);
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void roundAllThrowers10Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.getPlayer().setSelectedMove(DEMI_TOUR);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().learnMove(TOURNIQUET, _data_);
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getEnabledMoves().getVal(TEMPETESABLE).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(DEMI_TOUR, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, true);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
        assertTrue(game_.getFight().getFightType().isWild());
        assertTrue(game_.getFight().isKeepRound());
    }

    @Test
    public void endRoundFightBasic1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.setChosenHealingItem(EAU_FRAICHE, _data_);
        game_.roundAllThrowers(_data_, true);
        game_.roundUser(_data_);
        game_.roundUser(_data_);
        game_.endRoundFightBasic(_data_);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
    }

    @Test
    public void endRoundFightBasic2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.setChosenHealingItem(EAU_FRAICHE, _data_);
        game_.roundAllThrowers(_data_, true);
        game_.roundUser(_data_);
        game_.roundUser(_data_);
        game_.endRoundFightBasic(_data_);
        assertEq(LgInt.zero(), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        assertTrue(!FightFacade.koTeam(game_.getFight()));
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void endRoundFightBasic3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(TETARTE, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(BOUE_BOMBE, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, true);
        game_.roundUser(_data_);
        game_.endRoundFightBasic(_data_);
        assertEq(new LgInt("1"), game_.getPlayer().getInventory().getNumber(EAU_FRAICHE));
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertTrue(FightFacade.win(game_.getFight()));
    }

    @Test
    public void learnAndEvolve1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 25);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.initTrainerFight(_data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(BULLES_D_O, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.addOrForgetMove(ECUME);
        game_.learnAndEvolve(_data_);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
    }

    @Test
    public void learnAndEvolve2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 25);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.initTrainerFight(_data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(BULLES_D_O, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(_data_);
        assertEq(FightState.SWITCH_PROPOSE, game_.getFight().getState());
    }

    @Test
    public void learnAndEvolve3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFighter(POKEMON_PLAYER_FIGHTER_ZERO).setRemainedHp(Rate.one());
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.setChosenHealingItem(EAU_FRAICHE, _data_);
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(_data_);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void learnAndEvolve4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        assertEq(PTITARD, ((PokemonPlayer) game_.getPlayer().getTeam().get(0)).getName());
        DataMap map_ = _data_.getMap();
        Campaign pl_ = (Campaign) map_.getPlaces().getVal((short) 0);
        Coords current_ = game_.getPlayerCoords();
        LevelWithWildPokemon level_ = pl_.getLevelByCoords(current_);
        AreaApparition area_;
        area_ = level_.getAreaByPoint(current_.getLevel().getPoint());
        game_.newIndex(true, 0, area_, _data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(BULLES_D_O, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(_data_);
        assertTrue(FightFacade.win(game_.getFight()));
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void sendSubstitutes1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 25);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.initTrainerFight(_data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(BULLES_D_O, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(_data_);
        assertEq(FightState.SWITCH_PROPOSE, game_.getFight().getState());
        game_.sendSubstitutes(_data_);
        assertEq(FightState.ATTAQUES, game_.getFight().getState());
    }

    @Test
    public void sendSubstitutes2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 25);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.initTrainerFight(_data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(BULLES_D_O, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).increment();
        game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(Rate.one());
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.choosePokemonForLearningAndEvolving((byte) 0, _data_);
        game_.setEvolution(TETARTE);
        game_.learnAndEvolve(_data_);
        assertEq(FightState.SWITCH_PROPOSE, game_.getFight().getState());
        game_.sendSubstitutes(_data_);
        assertEq(new LgInt("3000"),game_.getPlayer().getMoney());
        assertTrue(FightFacade.win(game_.getFight()));
        assertEq(FightState.APPRENDRE_EVOLUER, game_.getFight().getState());
        game_.learnAndEvolve(_data_);
        assertEq(new LgInt("17000"),game_.getPlayer().getMoney());
    }

    @Test
    public void sendSubstitutes3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        Pokemon pk_ = new WildPk();
        pk_.setName(TETARTE);
        pk_.setLevel((short) 100);
        pk_.setAbility(ABSORB_EAU);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setItem(NULL_REF);
        game_.getPlayer().recevoirPokemon(pk_, game_.getDifficulty(), _data_);
        game_.getPlayer().setChosenTeamPokemon((short) 0);
        game_.getPlayer().switchTeamOrder((short) 1);
        game_.initTrainerFight(_data_);
        game_.getFight().getUserTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.getFight().getFoeTeam().getEnabledMoves().getVal(AIR_VEINARD).enable();
        game_.chooseFrontFighter((byte) 0, _data_);
        game_.chooseMove(BOUE_BOMBE, _data_);
        game_.setFirstChosenMoveFoeTarget((byte) 0);
        game_.getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).increment();
        game_.getFight().getFighter(POKEMON_FOE_FIGHTER_ONE).setRemainedHp(Rate.one());
        game_.roundAllThrowers(_data_, false);
        assertEq(FightState.SWITCH_PROPOSE, game_.getFight().getState());
        game_.sendSubstitutes(_data_);
        assertTrue(FightFacade.win(game_.getFight()));
        assertEq(new LgInt("17000"),game_.getPlayer().getMoney());
    }

    @Test
    public void movingHero1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 4));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(2, 0, 2, 3), game_.getPlayerCoords());
    }

    @Test
    public void movingHero2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(4, 0, 2, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(2, 0, 2, 5), game_.getPlayerCoords());
    }

    @Test
    public void movingHero3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 1, 4, 8), game_.getPlayerCoords());
    }

    @Test
    public void movingHero4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 1, 4, 7), game_.getPlayerCoords());
    }

    @Test
    public void movingHero6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 7));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 7, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(1, 0, 5, 1, 7, 8), game_.getPlayerCoords());
    }

    @Test
    public void movingHero8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(1, 0, 4, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero9Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(4, 0, 0, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(5, 0, 7, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero10Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 11, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(2, 0, 11, 3), game_.getPlayerCoords());
    }

    @Test
    public void movingHero11Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(5, 0, 7, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.movingHero(_data_.getMap());
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(4, 0, 0, 2), game_.getPlayerCoords());
    }

    @Test
    public void movingHero12Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(5, 0, 7, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.movingHero(_data_.getMap());
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(5, 1, 7, 5), game_.getPlayerCoords());
    }

    @Test
    public void movingHero13Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(5, 0, 7, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(5, 0, 7, 4), game_.getPlayerCoords());
    }

    @Test
    public void movingHero14Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(5, 0, 1, 4));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(5, 0, 1, 4), game_.getPlayerCoords());
    }

    @Test
    public void movingHero15Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(4, 0, 1, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(4, 0, 1, 3), game_.getPlayerCoords());
    }

    @Test
    public void movingHero16Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(4, 0, 1, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(4, 0, 1, 4), game_.getPlayerCoords());
    }

    @Test
    public void movingHero17Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        //map game_.setPlayerCoords(newCoords(4, 0, 4, 4));
        game_.setPlayerCoords(newCoords(4, 0, 5, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(_data_.getMap());
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 0, 4, 8), game_.getPlayerCoords());
    }

    @Test
    public void movingHero18Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(6, 0, 4, 8));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 0, 4, 7), game_.getPlayerCoords());
    }

    @Test
    public void movingHero19Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(6, 0, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 0, 4, 0), game_.getPlayerCoords());
    }

    @Test
    public void movingHero20Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(6, 0, 4, 5), game_.getPlayerCoords());
    }

    @Test
    public void movingHero21Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(6, 0, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setRankLeague((byte) 1);
        game_.movingHero(_data_.getMap());
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 1, 4, 8), game_.getPlayerCoords());
    }

    @Test
    public void movingHero22Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(6, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setRankLeague((byte) 1);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(6, 1, 4, 0), game_.getPlayerCoords());
    }

    @Test
    public void movingHero23Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(6, 1, 4, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setRankLeague((byte) 2);
        game_.movingHero(_data_.getMap());
        assertTrue(game_.isPlaceChanged());
        assertEq(1, game_.getNbSteps());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void movingHero24Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.movingHero(_data_.getMap());
        assertTrue(!game_.isPlaceChanged());
        assertEq(0, game_.getNbSteps());
        assertEq(newCoords(0, 0, 0, 0), game_.getPlayerCoords());
    }

    @Test
    public void processWalkingAreaApparition1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.movingHero(_data_.getMap());
        game_.processWalkingAreaApparition(game_.closestTile(_data_.getMap()), _data_);
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 3));
        game_.setPlayerOrientation(Direction.UP);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.movingHero(_data_.getMap());
        game_.processWalkingAreaApparition(game_.closestTile(_data_.getMap()), _data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.DON_OBJET, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 1, 2));
        game_.setPlayerOrientation(Direction.LEFT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(ARTIKODIN);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.movingHero(_data_.getMap());
        game_.processWalkingAreaApparition(game_.closestTile(_data_.getMap()), _data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 3, 0));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.movingHero(_data_.getMap());
        game_.processWalkingAreaApparition(game_.closestTile(_data_.getMap()), _data_);
        assertTrue(game_.getFight().getFightType().isWild());
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.movingHero(_data_.getMap());
        game_.processWalkingAreaApparition(game_.closestTile(_data_.getMap()), _data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 3, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.movingHero(_data_.getMap());
        game_.processWalkingAreaApparition(game_.closestTile(_data_.getMap()), _data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.DRESSEUR, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(4, 0, 5, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.movingHero(_data_.getMap());
        game_.processWalkingAreaApparition(game_.closestTile(_data_.getMap()), _data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.movingHero(_data_.getMap());
        game_.processWalkingAreaApparition(game_.closestTile(_data_.getMap()), _data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void processWalkingAreaApparition9Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getPlayer().getItem(REPOUSSE);
        game_.getPlayer().chooseObject(REPOUSSE);
        game_.getPlayer().useObject(_data_);
        game_.movingHero(_data_.getMap());
        game_.processWalkingAreaApparition(game_.closestTile(_data_.getMap()), _data_);
        assertTrue(!game_.getFight().getFightType().isExisting());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
    }

    @Test
    public void calculateImagesFromTiles1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(5,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(0,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,1)).first());
        assertEq(_data_.getPerson(TRAINER_ONE), foreGround_.getVal(new ScreenCoords(8,0)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(6,8)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 9, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(73, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(6,4)).first());
        assertEq(_data_.getPerson(TRAINER_TWO), foreGround_.getVal(new ScreenCoords(0,0)).first());
        assertEq(_data_.getMiniPk().getVal(ARTIKODIN), foreGround_.getVal(new ScreenCoords(4,5)).first());
        assertEq(_data_.getMiniPk().getVal(MEW), foreGround_.getVal(new ScreenCoords(6,2)).first());
        assertEq(_data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(3,5)).first());
        assertEq(_data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(2,5)).first());
        assertEq(_data_.getMiniItems().getVal(HYPER_BALL), foreGround_.getVal(new ScreenCoords(1,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(6,4)).first());
        assertEq(_data_.getMiniPk().getVal(MEW), foreGround_.getVal(new ScreenCoords(6,2)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(5,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(0,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,1)).first());
        assertEq(_data_.getPerson(TRAINER_ONE), foreGround_.getVal(new ScreenCoords(8,0)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(6,8)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(2, 0, 9, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(73, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(6,4)).first());
        assertEq(_data_.getPerson(TRAINER_TWO), foreGround_.getVal(new ScreenCoords(0,0)).first());
        assertEq(_data_.getMiniPk().getVal(ARTIKODIN), foreGround_.getVal(new ScreenCoords(4,5)).first());
        assertEq(_data_.getMiniPk().getVal(MEW), foreGround_.getVal(new ScreenCoords(6,2)).first());
        assertEq(_data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(3,5)).first());
        assertEq(_data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(2,5)).first());
        assertEq(_data_.getMiniItems().getVal(HYPER_BALL), foreGround_.getVal(new ScreenCoords(1,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
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
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(6,4)).first());
        assertEq(_data_.getMiniPk().getVal(MEW), foreGround_.getVal(new ScreenCoords(6,2)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles9Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(5, 1, 6, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,4)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,2)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(3,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        assertTrue(!_data_.getOverWorldHeros().contains(key_));
        key_ = new ImageHeroKey(EnvironmentType.ROAD, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles10Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(5, 1, 6, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,4)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,2)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(3,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        assertTrue(!_data_.getOverWorldHeros().contains(key_));
        key_ = new ImageHeroKey(EnvironmentType.ROAD, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles11Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(0,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,1)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(6,8)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles12Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.beatGymLeader(newCoords(2, 0, 2, 0));
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(PERSON), foreGround_.getVal(new ScreenCoords(0,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,1)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(6,8)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles13Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(4, 0, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(75, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,3)).first());
        //map assertEq(data.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,6)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,5)).first());
        assertEq(_data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(8,0)).first());
        assertEq(_data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(7,0)).first());
        assertEq(_data_.getMiniItems().getVal(HYPER_BALL), foreGround_.getVal(new ScreenCoords(6,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    private EqList<ScreenCoords> getEmptyTiles(
            ObjectMap<ScreenCoords, CustList<int[][]>> _foreGround) {
        EqList<ScreenCoords> k_;
        k_ = new EqList<ScreenCoords>();
        for (EntryCust<ScreenCoords, CustList<int[][]>> e: _foreGround.entryList()) {
            if (e.getValue().isEmpty()) {
                k_.add(e.getKey());
            }
        }
        return k_;
    }

    @Test
    public void calculateImagesFromTiles14Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(4, 0, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(75, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,3)).first());
        //map assertEq(data.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,6)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,5)).first());
        assertEq(_data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(8,0)).first());
        assertEq(_data_.getImageTmHm(), foreGround_.getVal(new ScreenCoords(7,0)).first());
        assertEq(_data_.getMiniItems().getVal(HYPER_BALL), foreGround_.getVal(new ScreenCoords(6,0)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles15Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,7)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(7,7)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles16Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(1,7)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(7,7)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles17Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(74, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(_data_.getStorage(), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(0,4)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,4)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,5)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,6)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles18Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(74, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(_data_.getStorage(), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(0,4)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,4)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,5)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,6)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles19Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(_data_.getStorage(), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(0,4)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,4)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles20Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 4, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(76, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,8)).first());
        assertEq(_data_.getStorage(), foreGround_.getVal(new ScreenCoords(4,0)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(0,4)).first());
        assertEq(_data_.getPerson(GERANT), foreGround_.getVal(new ScreenCoords(8,4)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles21Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(5, 0, 6, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,4)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,2)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,1)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        assertTrue(!_data_.getOverWorldHeros().contains(key_));
        key_ = new ImageHeroKey(EnvironmentType.ROAD, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles22Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(5, 0, 6, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(77, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,4)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(0,2)).first());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(5,1)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        assertTrue(!_data_.getOverWorldHeros().contains(key_));
        key_ = new ImageHeroKey(EnvironmentType.ROAD, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles23Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 0, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles24Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 0, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles25Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles26Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 3));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(78, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getLink(LINK), foreGround_.getVal(new ScreenCoords(4,1)).first());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,5)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles27Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(79, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,3)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles28Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(79, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,3)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles29Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.GIRL, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(79, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,3)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void calculateImagesFromTiles30Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, Sex.BOY, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(6, 1, 4, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        DataMap map_ = _data_.getMap();
        map_.calculateIntersectWithScreen(game_.getPlayerCoords());
        map_.getBackgroundImages().clear();
        map_.calculateBackgroundImagesFromTiles(_data_, 0, 0);
        game_.calculateImagesFromTiles(_data_, 0, 0);
        ObjectMap<ScreenCoords,CustList<int[][]>> foreGround_;
        foreGround_ = map_.getForegroundImages();
        assertEq(81, foreGround_.size());
        assertEq(79, getEmptyTiles(foreGround_).size());
        assertEq(_data_.getPerson(TRAINER), foreGround_.getVal(new ScreenCoords(4,3)).first());
        EnvironmentType currentEnv_ = map_.currentBlock(game_.getPlayerCoords()).getType();
        Sex sex_ = game_.getPlayer().getSex();
        ImageHeroKey key_;
        key_ = new ImageHeroKey(currentEnv_, game_.getPlayerOrientation(), sex_);
        int[][] img_ = _data_.getOverWorldHeros().getVal(key_);
        assertEq(img_, foreGround_.getVal(new ScreenCoords(4,4)).first());
    }

    @Test
    public void incrementStepsToLayEggs1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.incrementStepsToLayEggs(_data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        assertTrue(hosted_.isFree());
        assertEq(0, hosted_.getNbSteps());
    }

    @Test
    public void incrementStepsToLayEggs2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertNotEquals(pokemonFemale_.getGender(), pokemonMale_.getGender());
        game_.incrementStepsToLayEggs(_data_);
        assertTrue(!hosted_.isFree());
        assertEq(1, hosted_.getNbSteps());
    }

    @Test
    public void incrementStepsToLayEggs3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setNbSteps(1024);
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertNotEquals(pokemonFemale_.getGender(), pokemonMale_.getGender());
        game_.incrementStepsToLayEggs(_data_);
        assertTrue(!hosted_.isFree());
        assertEq(1024, hosted_.getNbSteps());
    }

    @Test
    public void canStoreThesePokemonToHost1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
    }

    @Test
    public void canStoreThesePokemonToHost2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost9Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost10Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost11Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(NUCLEOS);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost12Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(NUCLEOS);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost13Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost14Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost15Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost16Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost17Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost18Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost19Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost20Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost21Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 1,(short) 2, data));
    }

    @Test
    public void canStoreThesePokemonToHost22Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(!Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(!game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost23Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_M);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_F);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStoreThesePokemonToHost24Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_M);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        PokemonPlayer pkOne_=(PokemonPlayer) game_.getPlayer().getTeam().get(2);
        PokemonPlayer pkTwo_=(PokemonPlayer) game_.getPlayer().getTeam().get(1);
        assertTrue(Game.canStoreThesePokemonToHost(new Comment(), pkOne_,pkTwo_, _data_));
        //assertTrue(game_.canStoreThesePokemonToHost((short) 2,(short) 1, data));
    }

    @Test
    public void canStorePokemonToHost1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost9Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost10Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost11Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(NUCLEOS);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost12Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(NUCLEOS);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost13Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost14Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost15Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost16Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost17Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost18Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost19Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost20Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        PokemonPlayer pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new PokemonPlayer();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost21Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 1,(short) 2, _data_));
    }

    @Test
    public void canStorePokemonToHost22Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(!game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost23Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_M);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_F);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void canStorePokemonToHost24Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA_M);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.canStorePokemonToHost((short) 2,(short) 1, _data_));
    }

    @Test
    public void storePokemonToHost1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
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
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
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
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 0,(short) 1, _data_);
        assertEq(2, game_.getPlayer().getTeam().size());
        assertTrue(game_.isReinitInteraction());
        assertTrue(game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void attemptForStoringPokemonToHost2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        assertEq(3, game_.getPlayer().getTeam().size());
        assertTrue(game_.isReinitInteraction());
        assertTrue(game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void attemptForStoringPokemonToHost3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer second_ = (PokemonPlayer) game_.getPlayer().getTeam().get(2);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
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
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        PokemonPlayer first_ = (PokemonPlayer) game_.getPlayer().getTeam().get(1);
        PokemonPlayer second_ = (PokemonPlayer) game_.getPlayer().getTeam().get(2);
        game_.attemptForStoringPokemonToHost((short) 2,(short) 1, _data_);
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
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertEq(256, game_.nbRemainingSteps(newCoords(3, 0, 2, 1, 8, 4), _data_));
        game_.incrementStepsToLayEggs(_data_);
        assertEq(255, game_.nbRemainingSteps(newCoords(3, 0, 2, 1, 8, 4), _data_));
    }

    @Test
    public void nbRemainingSteps2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertEq(1024, game_.nbRemainingSteps(newCoords(3, 0, 2, 1, 8, 4), _data_));
        game_.incrementStepsToLayEggs(_data_);
        assertEq(1023, game_.nbRemainingSteps(newCoords(3, 0, 2, 1, 8, 4), _data_));
    }

    @Test
    public void canGetEgg1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        assertTrue(!game_.canGetEgg(newCoords(3, 0, 2, 1, 8, 4), _data_));
    }

    @Test
    public void canGetEgg2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        assertTrue(!game_.canGetEgg(newCoords(3, 0, 2, 1, 8, 4), _data_));
    }

    @Test
    public void canGetEgg3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        assertTrue(game_.canGetEgg(newCoords(3, 0, 2, 1, 8, 4), _data_));
    }

    @Test
    public void lawForProductedEgg1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), _data_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(PTITARD));
    }

    @Test
    public void lawForProductedEgg2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(DEMANTA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), _data_);
        assertEq(2, law_.events().size());
        assertEq(LgInt.one(), law_.rate(PTITARD));
        assertEq(LgInt.one(), law_.rate(BABIMANTA));
    }

    @Test
    public void lawForProductedEgg3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer pokemonNoGender_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonNoGender_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), _data_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(PTITARD));
    }

    @Test
    public void lawForProductedEgg4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PTITARD);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer pokemonNoGender_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonNoGender_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), _data_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(PTITARD));
    }

    @Test
    public void lawForProductedEgg5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer firstPokemonNoGender_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer secondPokemonNoGender_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(firstPokemonNoGender_);
        hosted_.setSecondPokemon(secondPokemonNoGender_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), _data_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(PIKACHU));
    }

    @Test
    public void lawForProductedEgg6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer firstPokemonNoGender_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(LIMAGMA);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        PokemonPlayer secondPokemonNoGender_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(firstPokemonNoGender_);
        hosted_.setSecondPokemon(secondPokemonNoGender_);
        hosted_.setNbSteps(256);
        MonteCarloString law_ = game_.lawForProductedEgg(newCoords(3, 0, 2, 1, 8, 4), _data_);
        assertEq(1, law_.events().size());
        assertTrue(law_.events().containsObj(LIMAGMA));
    }

    @Test
    public void productedEgg1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        Egg egg_ = game_.productedEgg(newCoords(3, 0, 2, 1, 8, 4), _data_);
        assertEq(PTITARD,egg_.getName());
        assertEq(0, egg_.getSteps());
    }

    @Test
    public void takeProductedEgg1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
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
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
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
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        assertTrue(game_.takablePokemonFromHost(_data_));
    }

    @Test
    public void takablePokemonFromHost2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(0, 0, 0, 5));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        PokemonPlayer pokemonFemale_ = new PokemonPlayer(pokemonDonne_, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        PokemonPlayer pokemonMale_ = new PokemonPlayer(pokemonDonne_, _data_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        hosted_.setFirstPokemon(pokemonFemale_);
        hosted_.setSecondPokemon(pokemonMale_);
        hosted_.setNbSteps(256);
        assertTrue(!game_.takablePokemonFromHost(_data_));
    }

    @Test
    public void receiveOnlyEgg1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        game_.receiveOnlyEgg(_data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(1, game_.getPlayer().getTeam().size());
    }

    @Test
    public void receiveOnlyEgg2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        game_.receiveOnlyEgg(_data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(2, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveOnlyEgg3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        assertEq(6, game_.getPlayer().getTeam().size());
        game_.receiveOnlyEgg(_data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(6, game_.getPlayer().getTeam().size());
        assertEq(256, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveOnlyEgg4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(5, game_.getPlayer().getTeam().size());
        game_.receiveOnlyEgg(_data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(5, game_.getPlayer().getTeam().size());
    }

    @Test
    public void receiveOnlyEgg5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(4, game_.getPlayer().getTeam().size());
        game_.receiveOnlyEgg(_data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(4, game_.getPlayer().getTeam().size());
    }

    @Test
    public void receiveEggOrParents1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        assertEq(1, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(_data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(4, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveEggOrParents2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        assertEq(4, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(_data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(5, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveEggOrParents3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).setNbSteps(256);
        assertEq(6, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(_data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(6, game_.getPlayer().getTeam().size());
        assertEq(256, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveEggOrParents5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        assertEq(1, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(_data_);
        assertTrue(!game_.isReinitInteraction());
        assertEq(3, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void receiveEggOrParents6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(true);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        Pokemon pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.FEMALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(TETARTE);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.MALE);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.attemptForStoringPokemonToHost((short) 1,(short) 2, _data_);
        pokemonDonne_ = new WildPk();
        pokemonDonne_.setName(PIKACHU);
        pokemonDonne_.setLevel((short) 1);
        pokemonDonne_.setAbility(STATIK);
        pokemonDonne_.setGender(Gender.NO_GENDER);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        game_.getPlayer().recevoirPokemon(pokemonDonne_, game_.getDifficulty(), _data_);
        assertEq(5, game_.getPlayer().getTeam().size());
        game_.receiveEggOrParents(_data_);
        assertTrue(game_.isReinitInteraction());
        assertEq(5, game_.getPlayer().getTeam().size());
        assertEq(0, game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4)).getNbSteps());
        assertTrue(!game_.availableHosting(newCoords(3, 0, 2, 1, 8, 4)));
    }

    @Test
    public void moving1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.moving(Direction.LEFT, _data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 2, 1, 7, 4),game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 7, 4));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.moving(Direction.RIGHT, _data_);
        assertEq(Direction.RIGHT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 2, 1, 7, 4),game_.getPlayerCoords());
        assertEq(InterfaceType.PENSION, game_.getInterfaceType());
        assertEq(0, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.moving(Direction.LEFT, _data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(0, 0, 0, 0),game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(0, 0, 0, 0));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.moving(Direction.RIGHT, _data_);
        assertEq(Direction.RIGHT,game_.getPlayerOrientation());
        assertEq(newCoords(0, 0, 1, 0),game_.getPlayerCoords());
        assertEq(InterfaceType.COMBAT_PK_SAUV, game_.getInterfaceType());
        assertEq(1, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(game_.getFight().getFightType().isWild());
    }

    @Test
    public void moving5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 2, 1, 0, 4));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.moving(Direction.LEFT, _data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 2, 1, 0, 4),game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(0, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 3, 2));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.moving(Direction.LEFT, _data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 2, 2),game_.getPlayerCoords());
        assertEq(4, game_.getVisitedPlaces().size());
        assertEq(3, game_.getUnVisited().size());
        assertTrue(game_.getVisitedPlaces().getVal(newCoords(3, 0, 2, 2)));
        assertTrue(!game_.getVisitedPlaces().getVal(newCoords(1, 0, 1, 2)));
        assertTrue(!game_.getVisitedPlaces().getVal(newCoords(7, 0, 3, 4)));
        assertTrue(!game_.getVisitedPlaces().getVal(newCoords(8, 0, 3, 4)));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(1, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 4, 2));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.moving(Direction.LEFT, _data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 3, 2),game_.getPlayerCoords());
        assertEq(4, game_.getVisitedPlaces().size());
        assertEq(4, game_.getUnVisited().size());
        assertTrue(!game_.getVisitedPlaces().getVal(newCoords(3, 0, 2, 2)));
        assertTrue(!game_.getVisitedPlaces().getVal(newCoords(1, 0, 1, 2)));
        assertTrue(!game_.getVisitedPlaces().getVal(newCoords(7, 0, 3, 4)));
        assertTrue(!game_.getVisitedPlaces().getVal(newCoords(8, 0, 3, 4)));
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(1, game_.getNbSteps());
        assertTrue(!game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void moving8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getDifficulty().setRandomWildFight(false);
        game_.setPlayerCoords(newCoords(3, 0, 4, 2));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.catchAll(_data_);
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
        first_.setLevel((short) _data_.getMaxLevel());
        first_.setHappiness((short) _data_.getHappinessMax());
        game_.moving(Direction.LEFT, _data_);
        assertEq(Direction.LEFT,game_.getPlayerOrientation());
        assertEq(newCoords(3, 0, 3, 2),game_.getPlayerCoords());
        assertEq(InterfaceType.RIEN, game_.getInterfaceType());
        assertEq(1, game_.getNbSteps());
        assertTrue(game_.isShowEndGame());
        assertTrue(!game_.getFight().getFightType().isExisting());
    }

    @Test
    public void isFrontOfTrainer1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        assertTrue(!game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 2));
        game_.setPlayerOrientation(Direction.RIGHT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer3Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 2, 1));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer4Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer5Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 3, 1));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer6Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(0, 0, 0, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer7Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(2, 0, 5, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer8Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 3));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer9Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 1, 1, 4, 1));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.getDifficulty().setRandomWildFight(false);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer10Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 3));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer11Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 4, 2));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer12Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(1, 0, 5, 1, 1, 6));
        game_.setPlayerOrientation(Direction.DOWN);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer13Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 6));
        game_.setPlayerOrientation(Direction.LEFT);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(!game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void isFrontOfTrainer14Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 1).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(1, 0, 5, 1, 4, 1));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(1, 7));
        game_.getBeatGymTrainer().getVal((short) 3).add(newPoint(7, 7));
        game_.beatGymLeader(newCoords(3, 0, 4, 1, 4, 1));
        game_.setPlayerCoords(newCoords(6, 0, 4, 5));
        game_.setPlayerOrientation(Direction.UP);
        game_.directInteraction(game_.closestTile(_data_.getMap()), _data_.getMap());
        assertTrue(game_.isFrontOfTrainer(_data_));
    }

    @Test
    public void doRevivingFossil1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getItem(LAVA);
        game_.getDifficulty().setRandomWildFight(false);
        game_.doRevivingFossil(LAVA, _data_);
        assertEq(2, game_.getTeam().size());
    }

    @Test
    public void nickname1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        PokemonPlayer pkUserTeam_ = (PokemonPlayer) game_.getTeam().get(0);
        assertEq(PIKACHU, pkUserTeam_.getNickname());
        game_.setChosenTeamPokemon((short) 0);
        game_.nickname(NULL_REF, _data_);
        pkUserTeam_ = (PokemonPlayer) game_.getTeam().get(0);
        assertEq(PIKACHU, pkUserTeam_.getNickname());
    }

    @Test
    public void initIv1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        player_.getBox().add(new PokemonPlayer(pk_, _data_));
        egg_ = new Egg(PIKACHU);
        player_.getBox().add(egg_);
        PokemonPlayer pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        pkPlayer_.getIv().clear();
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        pkPlayer_.getIv().clear();
        game_.initIv(_data_);
        pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP).intValue());
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP).intValue());
    }

    @Test
    public void initIv2Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        player_.getBox().add(new PokemonPlayer(pk_, _data_));
        egg_ = new Egg(PIKACHU);
        player_.getBox().add(egg_);
        HostPokemonDuo hosted_;
        hosted_ = game_.getHostedPk().getVal(newCoords(3, 0, 2, 1, 8, 4));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 1);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.FEMALE);
        hosted_.setFirstPokemon(new PokemonPlayer(pk_, _data_));
        pk_ = new WildPk();
        pk_.setName(PTITARD);
        pk_.setLevel((short) 1);
        pk_.setAbility(STATIK);
        pk_.setGender(Gender.MALE);
        hosted_.setSecondPokemon(new PokemonPlayer(pk_, _data_));
        PokemonPlayer pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        pkPlayer_.getIv().clear();
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        pkPlayer_.getIv().clear();
        hosted_.getFirstPokemon().getIv().clear();
        hosted_.getSecondPokemon().getIv().clear();
        game_.initIv(_data_);
        pkPlayer_ = (PokemonPlayer) player_.getTeam().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP).intValue());
        pkPlayer_ = (PokemonPlayer) player_.getBox().first();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP).intValue());
        pkPlayer_ = hosted_.getFirstPokemon();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP).intValue());
        pkPlayer_ = hosted_.getSecondPokemon();
        assertEq(6, pkPlayer_.getIv().size());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_ATTACK).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPECIAL_DEFENSE).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.SPEED).intValue());
        assertEq(31, pkPlayer_.getIv().getVal(Statistic.HP).intValue());
    }

    @Test
    public void clearMessages1Test() {
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
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
        Game game_ = new Game(_data_);
        game_.initUtilisateur(NICKNAME, null, new Difficulty(), _data_);
        game_.setPlayerCoords(newCoords(5, 0, 0, 0));
        game_.setPlayerOrientation(Direction.UP);
        game_.getItem(LAVA);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getCommentGame().addMessage(NICKNAME);
        game_.addMessageGymLeader();
        assertEq(1, game_.getCommentGame().getMessages().size());
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
