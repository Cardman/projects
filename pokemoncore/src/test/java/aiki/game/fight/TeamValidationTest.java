package aiki.game.fight;
import static junitparams.JUnitParamsRunner.$;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import aiki.exceptions.GameLoadException;
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
import code.util.NumberMap;
import code.util.StringList;

@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
public class TeamValidationTest extends InitializationDataBase {

    private static final String SEX = "sex";

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
    }

    Object[] sex() {
        return $($(Sex.GIRL),$(Sex.BOY));
    }

    @Test
    @Parameters(method=SEX)
    public void validate1Test(Sex _sex) {
        Game game_ = newGameInFight(_sex);
        game_.getFight().getUserTeam().validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate2Test(Sex _sex) {
        Game game_ = newGameInFight(_sex);
        game_.getFight().getFoeTeam().validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate3Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        game_.getFight().getUserTeam().validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate4Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        game_.getFight().getFoeTeam().validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate5Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate6Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate7Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate8Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate9Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate10Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate11Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate12Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(CHARGE);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate13Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate14Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().put(CHARGE, new ActivityOfMove(true));
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate15Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate16Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().put(CHARGE, LgInt.zero());
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate17Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate18Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CHARGE, 0);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate19Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate20Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(CHARGE, 0);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate21Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().put(new StringList(CHARGE), new ActivityOfMove(true));
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate22Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().put(new StringList(CHARGE), new ActivityOfMove(true));
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate23Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        NumberMap<Byte,StacksOfUses> map_;
        map_ = new NumberMap<Byte,StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate24Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        NumberMap<Byte,StacksOfUses> map_;
        map_ = new NumberMap<Byte,StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        team_.getHealAfter().put(CHARGE, map_);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate25Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        NumberMap<Byte,Anticipation> map_;
        map_ = new NumberMap<Byte,Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate26Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        NumberMap<Byte,Anticipation> map_;
        map_ = new NumberMap<Byte,Anticipation>();
        map_.put((byte) 0, new Anticipation());
        team_.getMovesAnticipation().put(CHARGE, map_);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate27Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

//    @Test
//    @Parameters(method="sex")
//    public void validate28Test(Sex _sex) {
//        Game game_ = newGameInFightTrainer2(_sex);
//        Team team_ = game_.getFight().getUserTeam();
//        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
//        team_.validate(data, Fight.PLAYER, game_.getFight());
//    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate29Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

//    @Test
//    @Parameters(method="sex")
//    public void validate30Test(Sex _sex) {
//        Game game_ = newGameInFightTrainer2(_sex);
//        Team team_ = game_.getFight().getFoeTeam();
//        team_.getSuccessfulMovesRound().add(CHARGE);
//        team_.validate(data, Fight.FOE, game_.getFight());
//    }

//    @Test
//    @Parameters(method="sex")
//    public void validate31Test(Sex _sex) {
//        Game game_ = newGameInFightTrainer2(_sex);
//        Team team_ = game_.getFight().getFoeTeam();
//        team_.getSuccessfulMovesRound().add(INVALID_DATA_KEY);
//        team_.validate(data, Fight.FOE, game_.getFight());
//    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate32Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMoves().getVal(MUR_LUMIERE).setNbTurn((short) -1);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

//    @Test
//    @Parameters(method="sex")
//    public void validate33Test(Sex _sex) {
//        Game game_ = newGameInFightTrainer2(_sex);
//        Team team_ = game_.getFight().getFoeTeam();
//        team_.getEnabledMovesWhileSendingFoe().put(CHARGE, false);
//        team_.validate(data, Fight.FOE, game_.getFight());
//    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate34Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesWhileSendingFoeUses().getVal(PICOTS).affect(new LgInt("-1"));
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate35Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate36Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMoves().put(CASSE, -1);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate37Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate38Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getNbUsesMovesRound().put(AIRE_D_EAU, -1);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate39Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate40Test(Sex _sex) {
        Game game_ = newGameInFightTrainer2(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getEnabledMovesByGroup().getVal(new StringList(AIRE_D_EAU,AIRE_D_HERBE)).setNbTurn((short) -1);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate41Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        NumberMap<Byte,StacksOfUses> map_;
        map_ = new NumberMap<Byte,StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate42Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        NumberMap<Byte,StacksOfUses> map_;
        map_ = new NumberMap<Byte,StacksOfUses>();
        map_.put((byte) 0, new StacksOfUses());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getHealAfter().put(VOEU, map_);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate43Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        NumberMap<Byte,Anticipation> map_;
        map_ = new NumberMap<Byte,Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate44Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        NumberMap<Byte,Anticipation> map_;
        map_ = new NumberMap<Byte,Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setNbRounds((byte) -1);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate45Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        NumberMap<Byte,Anticipation> map_;
        map_ = new NumberMap<Byte,Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_FOE_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test
    @Parameters(method=SEX)
    public void validate46Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        NumberMap<Byte,Anticipation> map_;
        map_ = new NumberMap<Byte,Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(POKEMON_PLAYER_TARGET_ZERO);
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }


    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate51Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        NumberMap<Byte,Anticipation> map_;
        map_ = new NumberMap<Byte,Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.FOE,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate52Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        NumberMap<Byte,Anticipation> map_;
        map_ = new NumberMap<Byte,Anticipation>();
        map_.put((byte) 0, new Anticipation());
        map_.getVal((byte) 0).setTargetPosition(new TargetCoords(Fight.PLAYER,(byte) (Fighter.BACK-1)));
        team_.getMovesAnticipation().put(PRESCIENCE, map_);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate53Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoRound((byte) -1);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate54Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoRound((byte) -1);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate55Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.setNbKoPreviousRound((byte) -1);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate56Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.setNbKoPreviousRound((byte) -1);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate57Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate58Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlace((byte) 1);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate59Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate60Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) 1);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate61Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate62Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getFoeTeam();
        team_.getMembers().getVal((byte) 0).setGroundPlaceSubst((byte) -1);
        team_.validate(_data_, Fight.FOE, game_.getFight());
    }

    //
    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate63Test(Sex _sex) {
        Game game_ = newGameInFightTrainer(_sex);
        Team team_ = game_.getFight().getUserTeam();
        team_.getPlayerFightersAgainstFoe().getVal((byte) 0).add((byte) 2);
        team_.validate(_data_, Fight.PLAYER, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate64Test(Sex _sex) {
        Game game_ = newGameInFight(_sex);
        game_.getFight().getFoeTeam().getMembers().clear();
        game_.getFight().getFoeTeam().validate(_data_, Fight.FOE, game_.getFight());
    }

    @Test(expected=GameLoadException.class)
    @Parameters(method=SEX)
    public void validate65Test(Sex _sex) {
        Game game_ = newGameInFight(_sex);
        game_.getFight().getUserTeam().getMembers().clear();
        game_.getFight().getUserTeam().validate(_data_, Fight.PLAYER, game_.getFight());
    }

//    private static Game newGameInFightTrainer3(Sex _sex) {
//        return newGameInFightTrainer3(_sex, new Difficulty());
//    }
//
//    private static Game newGameInFightTrainer3(Sex _sex, Difficulty _diff) {
//        //move to 2, 0, 2, 0 or 2, 0, 4, 0 for check less of substitutes
//        Game game_ = new Game(data);
//        game_.initUtilisateur(NICKNAME, _sex, _diff, data);
//        game_.getPlayer().getItem(REPOUSSE);
//        game_.getPlayer().chooseObject(REPOUSSE);
//        game_.getPlayer().useObject(data);
//        Pokemon pk_ = new WildPk();
//        pk_.setName(NINGALE);
//        pk_.setItem(MULTI_EXP);
//        pk_.setGender(_sex.getGender());
//        pk_.setAbility(ABSORB_EAU);
//        game_.getPlayer().recevoirPokemon(pk_, _diff, data);
//        game_.getPlayer().setChosenTeamPokemon((short) 0);
//        game_.getPlayer().switchTeamOrder((short) 1);
//        //game_.getPlayerOrientation() == UP
//        game_.moving(Direction.DOWN, data);
//        game_.moving(Direction.DOWN, data);
//        game_.moving(Direction.RIGHT, data);
//        game_.moving(Direction.RIGHT, data);
//        game_.moving(Direction.DOWN, data);
//        //trainer: 0, 0, 1, 1
//        game_.initTrainerFight(data);
//        return game_;
//    }

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
    }
}
