package aiki.db;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.EndTurnType;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectAccuracy;
import aiki.fight.moves.effects.EffectAlly;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectCommonStatistics;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectCounterAttack;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundFoe;
import aiki.fight.moves.effects.EffectEndRoundGlobal;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionTargetRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleStatus;
import aiki.fight.moves.effects.EffectEndRoundStatus;
import aiki.fight.moves.effects.EffectEndRoundStatusRelation;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import aiki.fight.moves.effects.EffectFullHpRate;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectProtectFromTypes;
import aiki.fight.moves.effects.EffectProtection;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.status.Status;
import aiki.fight.status.StatusSimple;
import aiki.fight.status.StatusType;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class DataBaseTest {

    @Test
    public void checkCaseOfFiles1Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StringList files_ = new StringList();
        data_.checkCaseOfFiles("folder", files_);
        assertEq(0, data_.getFilesWithSameNameDifferentCase().size());
    }

    @Test
    public void checkCaseOfFiles2Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StringList files_ = new StringList();
        files_.add("file_one");
        files_.add("file_TWO");
        data_.checkCaseOfFiles("folder", files_);
        assertEq(0, data_.getFilesWithSameNameDifferentCase().size());
    }

    @Test
    public void checkCaseOfFiles3Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StringList files_ = new StringList();
        files_.add("file_one");
        files_.add("file_ONE");
        files_.add("file_TWO");
        data_.checkCaseOfFiles("folder", files_);
        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
        assertTrue(StringList.contains(data_.getFilesWithSameNameDifferentCase(), "folder/FILE_ONE"));
    }

    @Test
    public void checkCaseOfFiles4Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StringList files_ = new StringList();
        files_.add("file_one");
        files_.add("file_ONE");
        files_.add("File_ONE");
        files_.add("file_TWO");
        data_.checkCaseOfFiles("folder", files_);
        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
        assertTrue(StringList.contains(data_.getFilesWithSameNameDifferentCase(), "folder/FILE_ONE"));
    }

    @Test
    public void completeMembers1Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory("SPECIAL");
        moveDamage_.setDirect(false);
        moveDamage_.setPp((short) 40);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("ECLAIR", moveDamage_);
        assertEq(1, data_.getCategories().size());
        assertTrue(StringList.contains(data_.getCategories(), "SPECIAL"));
        assertEq(1, data_.getAllCategories().size());
        assertTrue(StringList.contains(data_.getAllCategories(), "SPECIAL"));
    }

    @Test
    public void completeMembers2Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory("PHYSIQUE");
        moveDamage_.setDirect(false);
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("TONNERRE", moveDamage_);
        assertEq(1, data_.getCategories().size());
        assertTrue(StringList.contains(data_.getCategories(), "PHYSIQUE"));
        assertEq(1, data_.getAllCategories().size());
        assertTrue(StringList.contains(data_.getAllCategories(), "PHYSIQUE"));
    }

    @Test
    public void completeMembers3Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getCategories().size());
        assertEq(1, data_.getAllCategories().size());
        assertTrue(StringList.contains(data_.getAllCategories(), DataBase.AUTRE));
    }

    @Test
    public void completeMembers4Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy(StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getCategories().size());
        assertEq(1, data_.getVariables().size());
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers5Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getCategories().size());
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers6Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower("50");
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers7Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail(StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        damage_.setPower("50");
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers8Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower(StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers9Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower("50");
        damage_.setDamageLaw(new MonteCarloString());
        damage_.getDamageLaw().addEvent(StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"), LgInt.one());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers10Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatistic effStatis_;
        effStatis_ = new EffectStatistic();
        effStatis_.setFail("");
        effStatis_.setLocalFailStatis(new EnumMap<Statistic,String>());
        effStatis_.setLocalFailSwapBoostStatis(new EnumMap<Statistic,String>());
        moveDamage_.getEffects().add(effStatis_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers11Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatistic effStatis_;
        effStatis_ = new EffectStatistic();
        effStatis_.setFail("");
        effStatis_.setLocalFailStatis(new EnumMap<Statistic,String>());
        effStatis_.getLocalFailStatis().put(Statistic.ATTACK,StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        effStatis_.setLocalFailSwapBoostStatis(new EnumMap<Statistic,String>());
        moveDamage_.getEffects().add(effStatis_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers12Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatistic effStatis_;
        effStatis_ = new EffectStatistic();
        effStatis_.setFail("");
        effStatis_.setLocalFailStatis(new EnumMap<Statistic,String>());
        effStatis_.setLocalFailSwapBoostStatis(new EnumMap<Statistic,String>());
        effStatis_.getLocalFailSwapBoostStatis().put(Statistic.ATTACK,StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatis_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers13Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effStatus_;
        effStatus_ = new EffectStatus();
        effStatus_.setFail("");
        effStatus_.setLocalFailStatus(new StringMap<String>());
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers14Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effStatus_;
        effStatus_ = new EffectStatus();
        effStatus_.setFail("");
        effStatus_.setLocalFailStatus(new StringMap<String>());
        effStatus_.getLocalFailStatus().put("PSN",StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers15Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCommonStatistics effStatus_;
        effStatus_ = new EffectCommonStatistics();
        effStatus_.setFail("");
        effStatus_.setCommonValue(new EnumMap<Statistic,String>());
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers16Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCommonStatistics effStatus_;
        effStatus_ = new EffectCommonStatistics();
        effStatus_.setFail("");
        effStatus_.setCommonValue(new EnumMap<Statistic,String>());
        effStatus_.getCommonValue().put(Statistic.ATTACK,StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers17Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectFullHpRate effStatus_;
        effStatus_ = new EffectFullHpRate();
        effStatus_.setFail("");
        effStatus_.setRestoredHp("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers18Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectFullHpRate effStatus_;
        effStatus_ = new EffectFullHpRate();
        effStatus_.setFail("");
        effStatus_.setRestoredHp(StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers19Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeamWhileSendFoe effStatus_;
        effStatus_ = new EffectTeamWhileSendFoe();
        effStatus_.setFail("");
        effStatus_.setDamageRateAgainstFoe("");
        effStatus_.setFailSending("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
        assertEq(1, data_.getMovesEffectWhileSending().size());
        assertTrue(StringList.contains(data_.getMovesEffectWhileSending(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers20Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeamWhileSendFoe effStatus_;
        effStatus_ = new EffectTeamWhileSendFoe();
        effStatus_.setFail("");
        effStatus_.setFailSending("");
        effStatus_.setDamageRateAgainstFoe(StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
        assertEq(1, data_.getMovesEffectWhileSending().size());
        assertTrue(StringList.contains(data_.getMovesEffectWhileSending(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers21Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCopyMove effStatus_;
        effStatus_ = new EffectCopyMove();
        effStatus_.setFail("");
        effStatus_.setCopyingMoveForUser((short) 0);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesCopyingTemp().size());
    }

    @Test
    public void completeMembers22Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCopyMove effStatus_;
        effStatus_ = new EffectCopyMove();
        effStatus_.setFail("");
        effStatus_.setCopyingMoveForUser((short) 1);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesCopyingTemp().size());
    }

    @Test
    public void completeMembers23Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setFail("");
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers24Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstPrio(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers25Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtSingle(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(1, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers26Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstMultTargets(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(1, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers27Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.one());
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(1, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers28Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectAccuracy effStatus_;
        effStatus_ = new EffectAccuracy();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesAccuracy().size());
    }

    @Test
    public void completeMembers29Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectAlly effStatus_;
        effStatus_ = new EffectAlly();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectAlly().size());
    }

    @Test
    public void completeMembers30Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeam effStatus_;
        effStatus_ = new EffectTeam();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectTeam().size());
    }

    @Test
    public void completeMembers31Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectUnprotectFromTypes effStatus_;
        effStatus_ = new EffectUnprotectFromTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectUnprot().size());
    }

    @Test
    public void completeMembers32Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtectFromTypes effStatus_;
        effStatus_ = new EffectProtectFromTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectProt().size());
    }

    @Test
    public void completeMembers33Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectGlobal effStatus_;
        effStatus_ = new EffectGlobal();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectGlobal().size());
        assertEq(0, data_.getMovesEffectGlobalWeather().size());
    }

    @Test
    public void completeMembers34Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectGlobal effStatus_;
        effStatus_ = new EffectGlobal();
        effStatus_.setFail("");
        effStatus_.setWeather(true);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectGlobal().size());
        assertEq(1, data_.getMovesEffectGlobalWeather().size());
    }

    @Test
    public void completeMembers35Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.FORCE);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesEffectIndiv().size());
        assertEq(1, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers36Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.FORBIDDEN);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesEffectIndiv().size());
        assertEq(1, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers37Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.LANCEUR_ATTAQUES);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesEffectIndiv().size());
        assertEq(0, data_.getMovesActingMoveUses().size());
        assertEq(1, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers38Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.DER);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesEffectIndivIncr().size());
        assertEq(1, data_.getMovesEffectIndiv().size());
        assertEq(0, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers39Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.DER);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectIndivIncr().size());
        assertEq(1, data_.getMovesEffectIndiv().size());
        assertEq(0, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers40Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtectFromTypes effStatus_;
        effStatus_ = new EffectProtectFromTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectIndivIncr().size());
        assertEq(1, data_.getMovesEffectProt().size());
    }

    @Test
    public void completeMembers41Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRankIncrementNbRound((short) 1);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(1, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq("QUEUE_DE_CHEVAL", data_.getEvtEndRound().first().getElement());
        assertEq(EndTurnType.ATTAQUE, data_.getEvtEndRound().first().getEndRoundType());
        assertTrue(data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers42Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundGlobal effect_;
        effect_ = new EffectEndRoundGlobal();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq("QUEUE_DE_CHEVAL", data_.getEvtEndRound().first().getElement());
        assertEq(EndTurnType.ATTAQUE, data_.getEvtEndRound().first().getEndRoundType());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers43Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundPositionTargetRelation effect_;
        effect_ = new EffectEndRoundPositionTargetRelation();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(1, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers44Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundPositionRelation effect_;
        effect_ = new EffectEndRoundPositionRelation();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(1, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers45Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(1, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers46Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(1, data_.getMovesEffEndRoundIndiv().size());
        assertEq(1, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers47Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(1, data_.getMovesEffEndRoundIndiv().size());
        assertEq(1, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers48Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundSingleRelation effect_;
        effect_ = new EffectEndRoundSingleRelation();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        effect_.setRateDamageFunctionOfNbRounds(new LongMap<Rate>());
        effect_.setLawForEnablingEffect(new MonteCarloNumber());
        effect_.getLawForEnablingEffect().addEvent(Rate.one(), LgInt.zero());
        effect_.getRateDamageFunctionOfNbRounds().put(1L, new Rate(1,2));
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(1, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers49Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.setConstUserChoice(true);
        moveDamage_.getRepeatRoundLaw().addEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers50Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effect_ = new EffectStatus();
        effect_.setFail("");
        effect_.setLocalFailStatus(new StringMap<String>());
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers51Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effect_ = new EffectStatus();
        effect_.setFail("");
        effect_.setLocalFailStatus(new StringMap<String>());
        effect_.setKoUserHealSubst(true);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(1, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers52Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectInvoke effect_ = new EffectInvoke();
        effect_.setFail("");
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(1, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers53Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        Berry berry_;
        berry_ = new Berry();
        data_.completeMembers("STRAW_BERRY", berry_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers54Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        ItemForBattle berry_;
        berry_ = new ItemForBattle();
        berry_.setEffectEndRound(new CustList<EffectEndRound>());
        berry_.setMultDamage("");
        berry_.setMultPower("");
        berry_.setMultStat(new EnumMap<Statistic,String>());
        berry_.setFailStatus(new StringMap<String>());
        data_.completeMembers("STRAW_BERRY", berry_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers55Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        ItemForBattle berry_;
        berry_ = new ItemForBattle();
        berry_.setMultDamage("");
        berry_.setMultPower("");
        berry_.setMultStat(new EnumMap<Statistic,String>());
        berry_.setFailStatus(new StringMap<String>());
        berry_.setEffectEndRound(new CustList<EffectEndRound>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setEndRoundRank(3);
        effect_.setHealHp(new Rate(1,2));
        berry_.getEffectEndRound().add(effect_);
        data_.completeMembers("STRAW_BERRY", berry_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(3, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.OBJET, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("STRAW_BERRY", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.INDIVIDUEL, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers56Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        AbilityData ability_;
        ability_ = new AbilityData();
        ability_.setEffectEndRound(new CustList<EffectEndRound>());
        ability_.setMultDamage("");
        ability_.setMultPower("");
        ability_.setMultStat(new EnumMap<Statistic,String>());
        ability_.setFailStatus(new StringMap<String>());
        data_.completeMembers("FLYING", ability_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers57Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        AbilityData ability_;
        ability_ = new AbilityData();
        ability_.setEffectEndRound(new CustList<EffectEndRound>());
        ability_.setMultDamage("");
        ability_.setMultPower("");
        ability_.setMultStat(new EnumMap<Statistic,String>());
        ability_.setFailStatus(new StringMap<String>());
        EffectEndRoundTeam effect_;
        effect_ = new EffectEndRoundTeam();
        effect_.setEndRoundRank(3);
        effect_.setDeleteAllStatusAlly(new Rate(1,2));
        ability_.getEffectEndRound().add(effect_);
        data_.completeMembers("FLYING", ability_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(3, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.CAPACITE, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.EQUIPE, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers58Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        data_.completeMembers("PAR", status_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers59Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        EffectEndRoundSingleStatus eff_ = new EffectEndRoundSingleStatus();
        eff_.setEndRoundRank(2);
        status_.getEffectEndRound().add(eff_);
        data_.completeMembers("PAR", status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers60Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        EffectEndRoundStatusRelation eff_ = new EffectEndRoundStatusRelation();
        eff_.setEndRoundRank(2);
        status_.getEffectEndRound().add(eff_);
        data_.completeMembers("PAR", status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT_RELATION, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers61Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        status_.setIncrementingEndRound(true);
        status_.setIncrementEndRound(4);
        status_.setStatusType(StatusType.INDIVIDUEL);
        data_.completeMembers("PAR", status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers62Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        status_.setIncrementingEndRound(true);
        status_.setIncrementEndRound(4);
        status_.setStatusType(StatusType.RELATION_UNIQUE);
        data_.completeMembers("PAR", status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT_RELATION, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers63Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        data_.completeMembers(new StringList("FLYING","SURF"), eff_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers64Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        eff_.setRankIncrementNbRound((short) 2);
        data_.completeMembers(new StringList("FLYING","SURF"), eff_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.ATTAQUE_COMBI, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING;SURF", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.EQUIPE, data_.getEvtEndRound().first().getRelation());
        assertTrue(data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers65Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        EffectEndRoundFoe effect_ = new EffectEndRoundFoe();
        effect_.setEndRoundRank(4);
        eff_.getEffectEndRound().add(effect_);
        data_.completeMembers(new StringList("FLYING","SURF"), eff_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.ATTAQUE_COMBI, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING;SURF", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.ADV, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers66Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeamWhileSendFoe effStatus_;
        effStatus_ = new EffectTeamWhileSendFoe();
        effStatus_.setFail("");
        effStatus_.setFailSending(StringList.concat(DataBase.VAR_PREFIX,"NB_TURN>1"));
        effStatus_.setDamageRateAgainstFoe("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
        assertEq(1, data_.getMovesEffectWhileSending().size());
        assertTrue(StringList.contains(data_.getMovesEffectWhileSending(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers67Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCounterAttack effStatus_;
        effStatus_ = new EffectCounterAttack();
        effStatus_.setFail("");
        effStatus_.setCounterFail(StringList.concat(DataBase.VAR_PREFIX,"NB_TURN>1"));
        effStatus_.setProtectFail(StringList.concat(DataBase.VAR_PREFIX,"USED_MOVE=TACKLE"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "NB_TURN")));
        assertTrue(StringList.contains(data_.getVariables(), StringList.concat(DataBase.VAR_PREFIX, "USED_MOVE")));
        assertEq(1, data_.getMovesCountering().size());
        assertTrue(StringList.contains(data_.getMovesCountering(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers68Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectSwitchMoveTypes effStatus_;
        effStatus_ = new EffectSwitchMoveTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesChangingTypes().size());
        assertTrue(StringList.contains(data_.getMovesChangingTypes(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers69Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstStatusMoves(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
    }

    @Test
    public void completeMembers70Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstDamageMoves(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesProtAgainstDamageMoves().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
    }

    @Test
    public void completeMembersCombos1Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        EffectEndRoundFoe effect_ = new EffectEndRoundFoe();
        effect_.setEndRoundRank(4);
        eff_.getEffectEndRound().add(effect_);
        data_.initCombosTest();
        data_.getCombos().getEffects().put(new StringList("FLYING","SURF"), eff_);
        data_.completeMembersCombos();
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.ATTAQUE_COMBI, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING;SURF", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.ADV, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeVariables1Test() {
        DataBase data_ = new DataBase();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);
        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower(StringList.concat("1+",DataBase.VAR_PREFIX,"NB_TURN__CHARGE*100+",DataBase.VAR_PREFIX,"NB_TURN__FLYING*100"));
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        data_.completeVariables();
        assertEq(1, data_.getVarParamsMove().size());
        assertTrue(data_.getVarParamsMove().contains("NB_TURN"));
        assertEq(2, data_.getVarParamsMove().getVal("NB_TURN").size());
        assertTrue(StringList.contains(data_.getVarParamsMove().getVal("NB_TURN"), "CHARGE"));
        assertTrue(StringList.contains(data_.getVarParamsMove().getVal("NB_TURN"), "FLYING"));
    }
}
