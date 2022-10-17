package aiki.game.fight;

import aiki.db.DataBase;
import aiki.game.fight.util.ListActivityOfMove;
import code.maths.Rate;
import code.util.core.BoolVal;
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
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        assertTrue(game_.getFight().getUserTeam().validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate2Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        assertTrue(game_.getFight().getUserTeam().validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate3Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        assertTrue(game_.getFight().getFoeTeam().validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate4Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        assertTrue(game_.getFight().getFoeTeam().validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate5Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        assertTrue(game_.getFight().getUserTeam().validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate6Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        assertTrue(game_.getFight().getUserTeam().validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate7Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        assertTrue(game_.getFight().getFoeTeam().validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate8Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        assertTrue(game_.getFight().getFoeTeam().validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate9Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate10Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate11Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate12Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate13Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate14Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate15Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate16Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate17Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate18Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate19Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, BoolVal.FALSE);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate20Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, BoolVal.FALSE);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate21Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate22Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate23Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate24Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate25Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate26Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate27Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate28Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate29Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, BoolVal.FALSE);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate30Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, BoolVal.FALSE);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate31Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate32Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate33Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate34Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate35Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate36Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate37Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate38Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate39Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate40Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate41Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().add(new ListActivityOfMove(new StringList(CHARGE), new ActivityOfMove(true)));
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate42Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().add(new ListActivityOfMove(new StringList(CHARGE), new ActivityOfMove(true)));
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate43Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().add(new ListActivityOfMove(new StringList(CHARGE), new ActivityOfMove(true)));
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate44Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().add(new ListActivityOfMove(new StringList(CHARGE), new ActivityOfMove(true)));
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate45Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate46Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate47Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate48Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate49Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate50Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate51Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate52Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate53Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate54Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate55Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, BoolVal.FALSE);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate56Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, BoolVal.FALSE);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate57Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate58Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate59Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate60Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        assertTrue(team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate61Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate62Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate63Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate64Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate65Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, BoolVal.FALSE);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate66Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, BoolVal.FALSE);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate67Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate68Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate69Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate70Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate71Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate72Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate73Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate74Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate75Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate76Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate77Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate78Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate79Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate80Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer2(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate81Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate82Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate83Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate84Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<StacksOfUses> map_;
        map_ = new ByteMap<StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate85Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate86Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate87Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate88Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate89Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate90Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate91Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_PLAYER_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate92Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_PLAYER_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate93Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.CST_FOE,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate94Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.CST_FOE,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate95Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.CST_PLAYER,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate96Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.CST_PLAYER,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate97Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoRound((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate98Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoRound((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate99Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoRound((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate100Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoRound((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate101Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoPreviousRound((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate102Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoPreviousRound((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate103Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoPreviousRound((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate104Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoPreviousRound((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate105Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate106Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate107Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate108Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate109Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate110Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate111Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate112Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate113Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate114Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate115Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate116Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate117Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getPlayerFightersAgainstFoe().getVal((byte) 0).add((byte) 2);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate118Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        team_.getPlayerFightersAgainstFoe().getVal((byte) 0).add((byte) 2);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate119Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        game_.getFight().getFoeTeam().getMembers().clear();
        assertTrue(!game_.getFight().getFoeTeam().validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate120Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        game_.getFight().getFoeTeam().getMembers().clear();
        assertTrue(!game_.getFight().getFoeTeam().validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate121Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.GIRL, data_);
        game_.getFight().getUserTeam().getMembers().clear();
        assertTrue(!game_.getFight().getUserTeam().validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate122Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFight(Sex.BOY, data_);
        game_.getFight().getUserTeam().getMembers().clear();
        assertTrue(!game_.getFight().getUserTeam().validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate123Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setDamage(new Rate(-1));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate124Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setDamage(new Rate(-1));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate125Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().clear();
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate126Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().clear();
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate127Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords((short) 2,(byte) 0));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate128Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getUserTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords((short) 2,(byte) 0));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_PLAYER, game_.getFight()));
    }
    @Test
    public void validate129Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.GIRL, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords((short) 2,(byte) 0));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
    }
    @Test
    public void validate130Test(){
        DataBase data_ = initDb();
        Game game_ = newGameInFightTrainer(Sex.BOY, data_);
        Team team_ = game_.getFight().getFoeTeam();
        ByteMap<Anticipation> map_;
        map_ = new ByteMap<Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords((short) 2,(byte) 0));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        assertTrue(!team_.validate(data_, Fight.CST_FOE, game_.getFight()));
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
        game_.setPlayerCoords(newCoords(2, 0, 2, 1));
        //game_.getPlayerOrientation() == UP
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
    }}