package aiki.db;

import aiki.fight.abilities.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.pokemon.*;
import aiki.fight.status.*;
import aiki.fight.util.*;
import aiki.instances.*;
import aiki.util.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
import org.junit.Test;

public final class DataBaseIdUsageTest extends DataBaseValidationCommon {
    @Test
    public void used1() {
        Ball b_ = Instances.newBall();
        b_.setCatchingRate(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used2() {
        Ball b_ = Instances.newBall();
        b_.setCatchingRate(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedItInExp(PIKACHU));
    }
    @Test
    public void used3() {
        ItemForBattle b_ = Instances.newItemForBattle();
        b_.setMultPower(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used4() {
        ItemForBattle b_ = Instances.newItemForBattle();
        b_.setMultDamage(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used5() {
        ItemForBattle b_ = Instances.newItemForBattle();
        b_.getMultStat().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used6() {
        ItemForBattle b_ = Instances.newItemForBattle();
        b_.getMultStat().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedItInExp(PIKACHU));
    }
    @Test
    public void used7() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used_7() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingSimple();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used8() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().getLocalFailStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used9() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().getLocalFailSwapBoostStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used10() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedItInExp(PIKACHU));
    }
    @Test
    public void used11() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().getLocalFailStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedItInExp(PIKACHU));
    }
    @Test
    public void used12() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().getLocalFailSwapBoostStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedItInExp(PIKACHU));
    }
    @Test
    public void used13() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used14() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedItInExp(PARATONNERRE));
    }
    @Test
    public void used15() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedItInExp(PIKACHU));
    }
    @Test
    public void used16() {
        ItemForBattle b_ = Instances.newItemForBattle();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedItInExp(PIKACHU));
    }
    @Test
    public void used17() {
        AbilityData b_ = Instances.newAbilityData();
        b_.setMultPower(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used18() {
        AbilityData b_ = Instances.newAbilityData();
        b_.setMultDamage(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used19() {
        AbilityData b_ = Instances.newAbilityData();
        b_.getMultStat().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used20() {
        AbilityData b_ = Instances.newAbilityData();
        b_.getMultStat().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedAbInExp(PIKACHU));
    }
    @Test
    public void used21() {
        AbilityData b_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used_21() {
        AbilityData b_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingSimple();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used22() {
        AbilityData b_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().getLocalFailStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used23() {
        AbilityData b_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().getLocalFailSwapBoostStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used24() {
        AbilityData b_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedAbInExp(PIKACHU));
    }
    @Test
    public void used25() {
        AbilityData b_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().getLocalFailStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedAbInExp(PIKACHU));
    }
    @Test
    public void used26() {
        AbilityData b_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic eff_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(eff_);
        eff_.getEffect().getLocalFailSwapBoostStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedAbInExp(PIKACHU));
    }
    @Test
    public void used27() {
        AbilityData b_ = Instances.newAbilityData();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used28() {
        AbilityData b_ = Instances.newAbilityData();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used29() {
        AbilityData b_ = Instances.newAbilityData();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedAbInExp(PIKACHU));
    }
    @Test
    public void used30() {
        AbilityData b_ = Instances.newAbilityData();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedAbInExp(PIKACHU));
    }
    @Test
    public void used31() {
        AbilityData b_ = Instances.newAbilityData();
        b_.getFailStatus().addEntry(TREMPETTE,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedAbInExp(PARATONNERRE));
    }
    @Test
    public void used32() {
        AbilityData b_ = Instances.newAbilityData();
        b_.getFailStatus().addEntry(TREMPETTE,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedAbInExp(PIKACHU));
    }
    @Test
    public void used33() {
        MoveData b_ = Instances.newDamagingMoveData();
        b_.setAccuracy(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used34() {
        MoveData b_ = Instances.newDamagingMoveData();
        b_.setAccuracy(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used35() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectDamage e_ = Instances.newEffectDamage();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used36() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectDamage e_ = Instances.newEffectDamage();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used37() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectDamage e_ = Instances.newEffectDamage();
        e_.setPower(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used38() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectDamage e_ = Instances.newEffectDamage();
        e_.setPower(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used39() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectDamage e_ = Instances.newEffectDamage();
        e_.getDamageLaw().addQuickEvent(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP, LgInt.one());
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used40() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectDamage e_ = Instances.newEffectDamage();
        e_.getDamageLaw().addQuickEvent(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP, LgInt.one());
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used41() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectTeamWhileSendFoe e_ = Instances.newEffectTeamWhileSendFoe();
        e_.setFailSending(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used42() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectTeamWhileSendFoe e_ = Instances.newEffectTeamWhileSendFoe();
        e_.setFailSending(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used43() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectTeamWhileSendFoe e_ = Instances.newEffectTeamWhileSendFoe();
        e_.setDamageRateAgainstFoe(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used44() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectTeamWhileSendFoe e_ = Instances.newEffectTeamWhileSendFoe();
        e_.setDamageRateAgainstFoe(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used45() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectCommonStatistics e_ = Instances.newEffectCommonStatistics();
        e_.getCommonValue().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used46() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectCommonStatistics e_ = Instances.newEffectCommonStatistics();
        e_.getCommonValue().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used47() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectStatistic e_ = Instances.newEffectStatistic();
        e_.getLocalFailStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used48() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectStatistic e_ = Instances.newEffectStatistic();
        e_.getLocalFailStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used49() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectStatus e_ = Instances.newEffectStatus();
        e_.getLocalFailStatus().addEntry(TREMPETTE,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used50() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectStatus e_ = Instances.newEffectStatus();
        e_.getLocalFailStatus().addEntry(TREMPETTE,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used51() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectFullHpRate e_ = Instances.newEffectFullHpRate();
        e_.setRestoredHp(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used52() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectFullHpRate e_ = Instances.newEffectFullHpRate();
        e_.setRestoredHp(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used53() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffects().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used54() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffects().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used55() {
        Status b_ = Instances.newStatusSimple();
        EffectEndRoundStatus eff_ = Instances.newEffectEndRoundSingleStatus();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedStatusInExp(PARATONNERRE));
    }
    @Test
    public void used56() {
        Status b_ = Instances.newStatusSimple();
        EffectEndRoundStatus eff_ = Instances.newEffectEndRoundSingleStatus();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertTrue(db_.usedStatusInExp(PARATONNERRE));
    }
    @Test
    public void used57() {
        Status b_ = Instances.newStatusSimple();
        EffectEndRoundStatus eff_ = Instances.newEffectEndRoundSingleStatus();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedStatusInExp(PIKACHU));
    }
    @Test
    public void used58() {
        Status b_ = Instances.newStatusSimple();
        EffectEndRoundStatus eff_ = Instances.newEffectEndRoundSingleStatus();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        assertFalse(db_.usedStatusInExp(PIKACHU));
    }
    @Test
    public void used59() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used60() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used61() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used62() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        assertFalse(db_.usedMoveInExp(PIKACHU));
    }
    @Test
    public void used63() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+V_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT+SE+PARATONNERRE);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        assertTrue(db_.usedTypeInExp(PARATONNERRE));
    }
    @Test
    public void used64() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+V_NB_TOUR_GLOBAL+SE+PARATONNERRE);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        assertTrue(db_.usedMoveInExp(PARATONNERRE));
    }
    @Test
    public void used65() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+V_CIBLE_POSSEDE_STATUT_RELATION+SE+PARATONNERRE);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        assertTrue(db_.usedStatusInExp(PARATONNERRE));
    }
    @Test
    public void used66() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+V_NB_TOUR_GLOBAL+SE+PARATONNERRE);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        assertFalse(db_.usedCategoryInExp(PARATONNERRE));
    }
    @Test
    public void isUsed1() {
        DataBase db_ = newData();
        initTr(db_.getTranslatedItems());
        assertTrue(db_.isUsed(POKE_BALL));
    }
    @Test
    public void isUsed2() {
        DataBase db_ = newData();
        initTr(db_.getTranslatedAbilities());
        assertTrue(db_.isUsed(POKE_BALL));
    }
    @Test
    public void isUsed3() {
        DataBase db_ = newData();
        initTr(db_.getTranslatedMoves());
        assertTrue(db_.isUsed(POKE_BALL));
    }
    @Test
    public void isUsed4() {
        DataBase db_ = newData();
        initTr(db_.getTranslatedPokemon());
        assertTrue(db_.isUsed(POKE_BALL));
    }
    @Test
    public void isUsed5() {
        DataBase db_ = newData();
        initTr(db_.getTranslatedStatus());
        assertTrue(db_.isUsed(POKE_BALL));
    }
    @Test
    public void isUsed6() {
        DataBase db_ = newData();
        initTr(db_.getTranslatedTypes());
        assertTrue(db_.isUsed(POKE_BALL));
    }
    @Test
    public void isUsed7() {
        DataBase db_ = newData();
        initTr(db_.getTranslatedCategories());
        assertTrue(db_.isUsed(POKE_BALL));
    }
    @Test
    public void isUsed8() {
        DataBase db_ = newData();
        assertFalse(db_.isUsed(POKE_BALL));
    }
    @Test
    public void replace1() {
        StringMap<String> str_ = new StringMap<String>();
        str_.addEntry(PIKACHU,PARATONNERRE);
        str_.addEntry(CHARGE,CHARGE2);
        ChangeStringValueUtil<String> ut_ = new ChangeStringValueUtil<String>(str_);
        ut_.replace(PARATONNERRE,POKE_BALL);
        assertEq(2,str_.size());
        assertEq(POKE_BALL,str_.getVal(PIKACHU));
        assertEq(CHARGE2,str_.getVal(CHARGE));
    }
    @Test
    public void replace2() {
        StringMap<String> str_ = new StringMap<String>();
        str_.addEntry(PIKACHU,PARATONNERRE);
        str_.addEntry(CHARGE,CHARGE2);
        ChangeStringValueUtil<String> ut_ = new ChangeStringValueUtil<String>(str_);
        ut_.replace(CHARGE3,POKE_BALL);
        assertEq(2,str_.size());
        assertEq(PARATONNERRE,str_.getVal(PIKACHU));
        assertEq(CHARGE2,str_.getVal(CHARGE));
    }
    @Test
    public void changeName1() {
        Ball b_ = Instances.newBall();
        b_.setCatchingRate(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, b_.getCatchingRate());
    }
    @Test
    public void changeName2() {
        ItemForBattle b_ = Instances.newItemForBattle();
        b_.setMultPower(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, b_.getMultPower());
    }
    @Test
    public void changeName3() {
        ItemForBattle b_ = Instances.newItemForBattle();
        b_.setMultDamage(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, b_.getMultDamage());
    }
    @Test
    public void changeName4() {
        AbilityData b_ = Instances.newAbilityData();
        b_.setMultPower(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, b_.getMultPower());
    }
    @Test
    public void changeName5() {
        AbilityData b_ = Instances.newAbilityData();
        b_.setMultDamage(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, b_.getMultDamage());
    }
    @Test
    public void changeName6() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectDamage e_ = Instances.newEffectDamage();
        e_.getDamageLaw().addQuickEvent(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP, LgInt.one());
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP,  e_.getDamageLaw().getEvent(0));
    }
    @Test
    public void changeName7() {
        MoveData b_ = Instances.newDamagingMoveData();
        Effect e_ = Instances.newEffectDamage();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, e_.getFail());
    }
    @Test
    public void changeName8() {
        MoveData b_ = Instances.newDamagingMoveData();
        Effect e_ = Instances.newEffectTeamWhileSendFoe();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, e_.getFail());
    }
    @Test
    public void changeName9() {
        MoveData b_ = Instances.newDamagingMoveData();
        Effect e_ = Instances.newEffectCommonStatistics();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, e_.getFail());
    }
    @Test
    public void changeName10() {
        MoveData b_ = Instances.newDamagingMoveData();
        EffectStatistic e_ = Instances.newEffectStatistic();
        e_.getLocalFailStatis().addEntry(Statistic.SPEED,ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, e_.getLocalFailStatis().getVal(Statistic.SPEED));
    }
    @Test
    public void changeName11() {
        MoveData b_ = Instances.newDamagingMoveData();
        Effect e_ = Instances.newEffectStatus();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, e_.getFail());
    }
    @Test
    public void changeName12() {
        MoveData b_ = Instances.newDamagingMoveData();
        Effect e_ = Instances.newEffectFullHpRate();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, e_.getFail());
    }
    @Test
    public void changeName13() {
        MoveData b_ = Instances.newDamagingMoveData();
        Effect e_ = Instances.newEffectEndRoundFoe();
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        b_.getEffects().add(e_);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, e_.getFail());
    }
    @Test
    public void changeName14() {
        AbilityData b_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        b_.getEffectSending().add(e_);
        e_.getEffect().setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, e_.getEffect().getFail());
    }
    @Test
    public void changeName_14() {
        AbilityData b_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingSimple();
        b_.getEffectSending().add(e_);
        e_.getEffect().setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP, e_.getEffect().getFail());
    }
    @Test
    public void changeName15() {
        AbilityData b_ = Instances.newAbilityData();
        EffectEndRoundFoe e_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(e_);
        e_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, e_.getFail());
    }
    @Test
    public void changeName16() {
        Status b_ = Instances.newStatusSimple();
        EffectEndRoundStatus eff_ = Instances.newEffectEndRoundSingleStatus();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.completeMembers(POKE_BALL,b_);
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, eff_.getFail());
    }
    @Test
    public void changeName17() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFail(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PARATONNERRE+RB+RP);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        db_.changeNameDefInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+A_CARDINAL+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+PIKACHU+RB+RP, eff_.getFail());
    }
    @Test
    public void changeName18() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+V_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT+SE+PARATONNERRE);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        db_.changeNameTypeInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+V_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT+SE+PIKACHU, eff_.getFailEndRound());
    }
    @Test
    public void changeName19() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+V_NB_TOUR_GLOBAL+SE+PARATONNERRE);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        db_.changeNameMoveInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+V_NB_TOUR_GLOBAL+SE+PIKACHU, eff_.getFailEndRound());
    }
    @Test
    public void changeName20() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+V_CIBLE_POSSEDE_STATUT_RELATION+SE+PARATONNERRE);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        db_.changeNameStatusInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+V_CIBLE_POSSEDE_STATUT_RELATION+SE+PIKACHU, eff_.getFailEndRound());
    }
    @Test
    public void changeName21() {
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+V_NB_TOUR_GLOBAL+SE+PARATONNERRE);
        DataBase db_ = newData();
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        db_.changeNameCategoryInExp(PARATONNERRE,PIKACHU);
        assertEq(ES+V_NB_TOUR_GLOBAL+SE+PARATONNERRE, eff_.getFailEndRound());
    }
    @Test
    public void changeMidInNumericExpressions() {
        DataBase db_ = newData();
        db_.initValueOther(DataBaseConstants.PREFIX_KEY,"v");
        db_.initValueOther(DataBaseConstants.KEY_NB_TOUR,"nb_tr");
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+"v"+SE+"nb_tr"+SE+PARATONNERRE);
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        db_.changeMidInNumericExpressions("nb_tr","nbtr");
        assertEq(ES+"v"+SE+"nbtr"+SE+PARATONNERRE, eff_.getFailEndRound());
    }
    @Test
    public void changePrefInNumericExpressions() {
        DataBase db_ = newData();
        db_.initValueOther(DataBaseConstants.PREFIX_KEY,"v");
        db_.initValueOther(DataBaseConstants.KEY_NB_TOUR,"nb_tr");
        EffectCombo b_ = Instances.newEffectCombo();
        EffectEndRoundFoe eff_ = Instances.newEffectEndRoundFoe();
        b_.getEffectEndRound().add(eff_);
        eff_.setFailEndRound(ES+"v"+SE+"nb_tr"+SE+PARATONNERRE);
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(POKE_BALL),b_));
        db_.changePrefInNumericExpressions("v","w");
        assertEq(ES+"w"+SE+"nb_tr"+SE+PARATONNERRE, eff_.getFailEndRound());
    }
    private void initTr(StringMap<StringMap<String>> _tr) {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(POKE_BALL,POKE_BALL);
        _tr.addEntry(NULL_REF, map_);
    }

    private static DataBase newData() {
        DataBase db_ = new DataBase(DefaultGenerator.oneElt());
        db_.initializeMembers();
        db_.defValues();
        db_.setCombos(Instances.newCombos());
        return db_;
    }
}
