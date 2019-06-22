package aiki.game.fight;
import static org.junit.Assert.assertTrue;

import code.maths.Rate;
import org.junit.Test;

import aiki.game.Game;
import aiki.game.params.Difficulty;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.WildPk;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.util.*;
import code.util.StringList;


public class TeamValidationTest extends InitializationDataBase {


    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Point newPoint(int _x,int _y) {
        return new Point((short)_x, (short)_y);
    }    @Test
    public void validate1Test(){
        Game game_ = newGameInFight(Sex.GIRL);
        assertTrue(game_.getFight().getUserTeam().validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate2Test(){
        Game game_ = newGameInFight(Sex.BOY);
        assertTrue(game_.getFight().getUserTeam().validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate3Test(){
        Game game_ = newGameInFight(Sex.GIRL);
        assertTrue(game_.getFight().getFoeTeam().validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate4Test(){
        Game game_ = newGameInFight(Sex.BOY);
        assertTrue(game_.getFight().getFoeTeam().validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate5Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        assertTrue(game_.getFight().getUserTeam().validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate6Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        assertTrue(game_.getFight().getUserTeam().validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate7Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        assertTrue(game_.getFight().getFoeTeam().validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate8Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        assertTrue(game_.getFight().getFoeTeam().validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate9Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate10Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate11Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate12Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate13Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate14Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate15Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate16Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate17Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate18Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate19Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate20Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate21Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate22Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate23Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate24Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate25Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate26Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate27Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate28Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate29Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate30Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate31Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate32Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate33Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate34Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate35Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate36Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate37Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate38Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate39Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate40Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate41Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().put(new StringList(CHARGE), new ActivityOfMove(true));
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate42Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().put(new StringList(CHARGE), new ActivityOfMove(true));
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate43Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().put(new StringList(CHARGE), new ActivityOfMove(true));
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate44Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().put(new StringList(CHARGE), new ActivityOfMove(true));
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate45Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate46Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate47Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate48Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate49Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate50Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate51Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate52Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate53Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate54Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate55Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate56Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate57Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate58Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate59Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate60Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate61Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate62Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate63Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate64Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate65Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate66Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate67Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate68Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate69Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate70Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate71Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate72Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate73Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate74Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate75Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate76Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate77Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate78Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate79Test(){
        Game game_ = newGameInFightTrainer2(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate80Test(){
        Game game_ = newGameInFightTrainer2(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate81Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate82Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate83Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate84Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate85Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate86Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate87Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate88Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate89Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate90Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate91Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_PLAYER_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate92Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_PLAYER_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate93Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.FOE,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate94Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.FOE,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate95Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.PLAYER,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate96Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.PLAYER,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate97Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoRound((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate98Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoRound((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate99Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoRound((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate100Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoRound((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate101Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoPreviousRound((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate102Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoPreviousRound((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate103Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoPreviousRound((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate104Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoPreviousRound((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate105Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate106Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate107Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate108Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate109Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate110Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate111Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate112Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate113Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate114Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate115Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate116Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate117Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        team_.getPlayerFightersAgainstFoe().getVal((byte) 0).add((byte) 2);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate118Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        team_.getPlayerFightersAgainstFoe().getVal((byte) 0).add((byte) 2);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate119Test(){
        Game game_ = newGameInFight(Sex.GIRL);
        game_.getFight().getFoeTeam().getMembers().clear();
        assertTrue(!game_.getFight().getFoeTeam().validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate120Test(){
        Game game_ = newGameInFight(Sex.BOY);
        game_.getFight().getFoeTeam().getMembers().clear();
        assertTrue(!game_.getFight().getFoeTeam().validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate121Test(){
        Game game_ = newGameInFight(Sex.GIRL);
        game_.getFight().getUserTeam().getMembers().clear();
        assertTrue(!game_.getFight().getUserTeam().validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate122Test(){
        Game game_ = newGameInFight(Sex.BOY);
        game_.getFight().getUserTeam().getMembers().clear();
        assertTrue(!game_.getFight().getUserTeam().validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate123Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setDamage(new Rate(-1));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate124Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setDamage(new Rate(-1));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(_data_, Fight.PLAYER, game_.getFight()));
    }
    @Test
    public void validate125Test(){
        Game game_ = newGameInFightTrainer(Sex.GIRL);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().clear();
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
    }
    @Test
    public void validate126Test(){
        Game game_ = newGameInFightTrainer(Sex.BOY);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().clear();
        assertTrue(!team_.validate(_data_, Fight.FOE, game_.getFight()));
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
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
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
    }}