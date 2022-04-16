package aiki.sml.init;
import aiki.instances.*;
import aiki.fight.util.*;
import aiki.fight.effects.*;
import aiki.fight.moves.effects.*;
import aiki.fight.status.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.status.*;
import aiki.fight.enums.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
import code.util.core.BoolVal;

public final class StInit extends CstIgame{
private StInit(){}
public static StringMap<Status> st(){
 StringMap<Status> m2016s_ = new StringMap<Status>(new CollCapacity(13));
m2016s_.addEntry(I_AMOUR,m0());
m2016s_.addEntry(I_BRULURE,m1());
m2016s_.addEntry(I_CAUCHEMAR_ST,m2());
m2016s_.addEntry(I_CONFUSION,m3());
m2016s_.addEntry(I_GEL,m4());
m2016s_.addEntry(I_MAUDIT,m5());
m2016s_.addEntry(I_PARALYSIE,m6());
m2016s_.addEntry(I_PEUR,m7());
m2016s_.addEntry(I_POISON_GRAVE,m8());
m2016s_.addEntry(I_SIMPLE_POISON,m9());
m2016s_.addEntry(I_SOMMEIL,m10());
m2016s_.addEntry(I_SOMMEIL_REPOS,m11());
m2016s_.addEntry(I_VAMPIGRAINE_ST,m12());
return m2016s_;
}
static Status m0(){
StatusBeginRoundSimple m2017statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean m2017monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
m2017monteCarloBoolean_.addQuickEvent(BoolVal.FALSE,LgInt.newLgInt(R_1));
m2017monteCarloBoolean_.addQuickEvent(BoolVal.TRUE,LgInt.newLgInt(R_1));
m2017statusBeginRoundSimple_.setLawForUsingAMoveIfFoe(m2017monteCarloBoolean_);
m2017statusBeginRoundSimple_.setStatusType(StatusType.RELATION_UNIQUE);
m2017statusBeginRoundSimple_.setCatchingRate(Rate.newRate(R_3));
CustList<EffectPartnerStatus> m2017custListEffectPartnerStatus_ = new CustList<EffectPartnerStatus>(new CollCapacity(1));
EffectPartnerStatus m2017effectPartnerStatus_=Instances.newEffectPartnerStatus();
m2017effectPartnerStatus_.setMultDamageAgainstFoe(Rate.newRate(R_2));
m2017effectPartnerStatus_.setWeddingAlly(true);
m2017effectPartnerStatus_.setRestoredHpRateLovedAlly(Rate.newRate(R_1_2));
m2017custListEffectPartnerStatus_.add(m2017effectPartnerStatus_);
m2017statusBeginRoundSimple_.setEffectsPartner(m2017custListEffectPartnerStatus_);
m2017statusBeginRoundSimple_.setFail(ES);
return m2017statusBeginRoundSimple_;
}
static Status m1(){
StatusSimple m2018statusSimple_ = Instances.newStatusSimple();
m2018statusSimple_.setStatusType(StatusType.INDIVIDUEL);
m2018statusSimple_.setCatchingRate(Rate.newRate(R_3_2));
CustList<EffectEndRoundStatus> m2018custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundSingleStatus m2018effectEndRoundSingleStatus_=Instances.newEffectEndRoundSingleStatus();
m2018effectEndRoundSingleStatus_.setIncrementingDamageByRounds(false);
m2018effectEndRoundSingleStatus_.setInflictedRateHpTarget(Rate.newRate(R_1_8));
m2018effectEndRoundSingleStatus_.setFailEndRound(ES);
m2018effectEndRoundSingleStatus_.setEndRoundRank(55);
m2018effectEndRoundSingleStatus_.setTargetChoice(TargetChoice.NOTHING);
m2018effectEndRoundSingleStatus_.setFail(ES);
m2018custListEffectEndRoundStatus_.add(m2018effectEndRoundSingleStatus_);
m2018statusSimple_.setEffectEndRound(m2018custListEffectEndRoundStatus_);
EnumMap<Statistic,Rate> m2018enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
m2018enumMapStatisticRate_.addEntry(Statistic.ATTACK,Rate.newRate(R_1_2));
m2018statusSimple_.setMultStat(m2018enumMapStatisticRate_);
m2018statusSimple_.setFail(V_FIGHTER_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_TYPES+RB+OC+LB+I_FEU+RB+RP+RP+GT+R_0);
return m2018statusSimple_;
}
static Status m2(){
StatusSimple m2019statusSimple_ = Instances.newStatusSimple();
m2019statusSimple_.setStatusType(StatusType.RELATION_UNIQUE);
m2019statusSimple_.setCatchingRate(Rate.newRate(R_3_2));
CustList<EffectEndRoundStatus> m2019custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundStatusRelation m2019effectEndRoundStatusRelation_=Instances.newEffectEndRoundStatusRelation();
m2019effectEndRoundStatusRelation_.setThievedHpRateTargetToUser(Rate.newRate(R_0));
m2019effectEndRoundStatusRelation_.setInflictedRateHpTarget(Rate.newRate(R_1_4));
m2019effectEndRoundStatusRelation_.setFailEndRound(A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_SOMMEIL+OS+I_SOMMEIL_REPOS+RB+RP+RP+OE+R_0);
m2019effectEndRoundStatusRelation_.setEndRoundRank(58);
m2019effectEndRoundStatusRelation_.setTargetChoice(TargetChoice.NOTHING);
m2019effectEndRoundStatusRelation_.setFail(A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_SOMMEIL+OS+I_SOMMEIL_REPOS+RB+RP+RP+OE+R_0);
m2019custListEffectEndRoundStatus_.add(m2019effectEndRoundStatusRelation_);
m2019statusSimple_.setEffectEndRound(m2019custListEffectEndRoundStatus_);
m2019statusSimple_.setFail(ES);
return m2019statusSimple_;
}
static Status m3(){
StatusBeginRoundAutoDamage m2020statusBeginRoundAutoDamage_ = Instances.newStatusBeginRoundAutoDamage();
m2020statusBeginRoundAutoDamage_.setPower(Rate.newRate(R_40));
m2020statusBeginRoundAutoDamage_.setAttack(Statistic.ATTACK);
m2020statusBeginRoundAutoDamage_.setDefense(Statistic.DEFENSE);
MonteCarloBoolean m2020monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
m2020monteCarloBoolean_.addQuickEvent(BoolVal.FALSE,LgInt.newLgInt(R_1));
m2020monteCarloBoolean_.addQuickEvent(BoolVal.TRUE,LgInt.newLgInt(R_3));
m2020statusBeginRoundAutoDamage_.setLawForUsingAMove(m2020monteCarloBoolean_);
m2020statusBeginRoundAutoDamage_.setStatusType(StatusType.INDIVIDUEL);
m2020statusBeginRoundAutoDamage_.setCatchingRate(Rate.newRate(R_3));
m2020statusBeginRoundAutoDamage_.setDisabledEffIfSwitch(true);
m2020statusBeginRoundAutoDamage_.setFail(ES);
return m2020statusBeginRoundAutoDamage_;
}
static Status m4(){
StatusBeginRoundSimple m2021statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean m2021monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
m2021monteCarloBoolean_.addQuickEvent(BoolVal.FALSE,LgInt.newLgInt(R_9));
m2021monteCarloBoolean_.addQuickEvent(BoolVal.TRUE,LgInt.newLgInt(R_1));
m2021statusBeginRoundSimple_.setLawForUsingAMove(m2021monteCarloBoolean_);
m2021monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(1));
m2021monteCarloBoolean_.addQuickEvent(BoolVal.TRUE,LgInt.newLgInt(R_1));
m2021statusBeginRoundSimple_.setLawForFullHealIfMove(m2021monteCarloBoolean_);
m2021statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
m2021statusBeginRoundSimple_.setCatchingRate(Rate.newRate(R_5_2));
m2021statusBeginRoundSimple_.setFail(V_FIGHTER_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_TYPES+RB+OC+LB+I_GLACE+RB+RP+RP+GT+R_0);
return m2021statusBeginRoundSimple_;
}
static Status m5(){
StatusSimple m2022statusSimple_ = Instances.newStatusSimple();
m2022statusSimple_.setStatusType(StatusType.RELATION_UNIQUE);
m2022statusSimple_.setCatchingRate(Rate.newRate(R_3_2));
CustList<EffectEndRoundStatus> m2022custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundStatusRelation m2022effectEndRoundStatusRelation_=Instances.newEffectEndRoundStatusRelation();
m2022effectEndRoundStatusRelation_.setThievedHpRateTargetToUser(Rate.newRate(R_0));
m2022effectEndRoundStatusRelation_.setInflictedRateHpTarget(Rate.newRate(R_1_8));
m2022effectEndRoundStatusRelation_.setFailEndRound(ES);
m2022effectEndRoundStatusRelation_.setEndRoundRank(57);
m2022effectEndRoundStatusRelation_.setTargetChoice(TargetChoice.NOTHING);
m2022effectEndRoundStatusRelation_.setFail(ES);
m2022custListEffectEndRoundStatus_.add(m2022effectEndRoundStatusRelation_);
m2022statusSimple_.setEffectEndRound(m2022custListEffectEndRoundStatus_);
m2022statusSimple_.setFail(ES);
return m2022statusSimple_;
}
static Status m6(){
StatusBeginRoundSimple m2023statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean m2023monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
m2023monteCarloBoolean_.addQuickEvent(BoolVal.FALSE,LgInt.newLgInt(R_1));
m2023monteCarloBoolean_.addQuickEvent(BoolVal.TRUE,LgInt.newLgInt(R_3));
m2023statusBeginRoundSimple_.setLawForUsingAMove(m2023monteCarloBoolean_);
m2023statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
m2023statusBeginRoundSimple_.setCatchingRate(Rate.newRate(R_2));
EnumMap<Statistic,Rate> m2023enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
m2023enumMapStatisticRate_.addEntry(Statistic.SPEED,Rate.newRate(R_1_4));
m2023statusBeginRoundSimple_.setMultStat(m2023enumMapStatisticRate_);
m2023statusBeginRoundSimple_.setFail(ES);
return m2023statusBeginRoundSimple_;
}
static Status m7(){
StatusBeginRoundSimple m2024statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean m2024monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(1));
m2024monteCarloBoolean_.addQuickEvent(BoolVal.FALSE,LgInt.newLgInt(R_1));
m2024statusBeginRoundSimple_.setLawForUsingAMove(m2024monteCarloBoolean_);
m2024statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
m2024statusBeginRoundSimple_.setCatchingRate(Rate.newRate(R_5_4));
m2024statusBeginRoundSimple_.setDisabledEffIfSwitch(true);
m2024statusBeginRoundSimple_.setIncrementEndRound(63);
m2024statusBeginRoundSimple_.setIncrementingEndRound(true);
m2024statusBeginRoundSimple_.setFail(ES);
return m2024statusBeginRoundSimple_;
}
static Status m8(){
StatusSimple m2025statusSimple_ = Instances.newStatusSimple();
m2025statusSimple_.setStatusType(StatusType.INDIVIDUEL);
m2025statusSimple_.setCatchingRate(Rate.newRate(R_3_2));
CustList<EffectEndRoundStatus> m2025custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundSingleStatus m2025effectEndRoundSingleStatus_=Instances.newEffectEndRoundSingleStatus();
m2025effectEndRoundSingleStatus_.setIncrementingDamageByRounds(true);
m2025effectEndRoundSingleStatus_.setInflictedRateHpTarget(Rate.newRate(R_1_4));
m2025effectEndRoundSingleStatus_.setFailEndRound(ES);
m2025effectEndRoundSingleStatus_.setEndRoundRank(56);
m2025effectEndRoundSingleStatus_.setTargetChoice(TargetChoice.NOTHING);
m2025effectEndRoundSingleStatus_.setFail(ES);
m2025custListEffectEndRoundStatus_.add(m2025effectEndRoundSingleStatus_);
m2025statusSimple_.setEffectEndRound(m2025custListEffectEndRoundStatus_);
m2025statusSimple_.setFail(V_FIGHTER_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_TYPES+RB+OC+LB+I_POISON+OS+I_ACIER+RB+RP+RP+GT+R_0);
return m2025statusSimple_;
}
static Status m9(){
StatusSimple m2026statusSimple_ = Instances.newStatusSimple();
m2026statusSimple_.setStatusType(StatusType.INDIVIDUEL);
m2026statusSimple_.setCatchingRate(Rate.newRate(R_3_2));
CustList<EffectEndRoundStatus> m2026custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundSingleStatus m2026effectEndRoundSingleStatus_=Instances.newEffectEndRoundSingleStatus();
m2026effectEndRoundSingleStatus_.setIncrementingDamageByRounds(false);
m2026effectEndRoundSingleStatus_.setInflictedRateHpTarget(Rate.newRate(R_1_8));
m2026effectEndRoundSingleStatus_.setFailEndRound(ES);
m2026effectEndRoundSingleStatus_.setEndRoundRank(54);
m2026effectEndRoundSingleStatus_.setTargetChoice(TargetChoice.NOTHING);
m2026effectEndRoundSingleStatus_.setFail(ES);
m2026custListEffectEndRoundStatus_.add(m2026effectEndRoundSingleStatus_);
m2026statusSimple_.setEffectEndRound(m2026custListEffectEndRoundStatus_);
m2026statusSimple_.setFail(V_FIGHTER_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_TYPES+RB+OC+LB+I_POISON+OS+I_ACIER+RB+RP+RP+GT+R_0);
return m2026statusSimple_;
}
static Status m10(){
StatusBeginRoundSimple m2027statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean m2027monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(1));
m2027monteCarloBoolean_.addQuickEvent(BoolVal.FALSE,LgInt.newLgInt(R_1));
m2027statusBeginRoundSimple_.setLawForUsingAMove(m2027monteCarloBoolean_);
m2027statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
m2027statusBeginRoundSimple_.setCatchingRate(Rate.newRate(R_5_2));
m2027statusBeginRoundSimple_.setFail(ES);
return m2027statusBeginRoundSimple_;
}
static Status m11(){
StatusBeginRoundSimple m2028statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean m2028monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(1));
m2028monteCarloBoolean_.addQuickEvent(BoolVal.FALSE,LgInt.newLgInt(R_1));
m2028statusBeginRoundSimple_.setLawForUsingAMove(m2028monteCarloBoolean_);
m2028statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
m2028statusBeginRoundSimple_.setCatchingRate(Rate.newRate(R_5_2));
m2028statusBeginRoundSimple_.setFail(ES);
return m2028statusBeginRoundSimple_;
}
static Status m12(){
StatusSimple m2029statusSimple_ = Instances.newStatusSimple();
m2029statusSimple_.setStatusType(StatusType.RELATION_UNIQUE);
m2029statusSimple_.setCatchingRate(Rate.newRate(R_3_2));
CustList<EffectEndRoundStatus> m2029custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundStatusRelation m2029effectEndRoundStatusRelation_=Instances.newEffectEndRoundStatusRelation();
m2029effectEndRoundStatusRelation_.setThievedHpRateTargetToUser(Rate.newRate(R_1_8));
m2029effectEndRoundStatusRelation_.setInflictedRateHpTarget(Rate.newRate(R_0));
m2029effectEndRoundStatusRelation_.setFailEndRound(V_CIBLE_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_TYPES+RB+OC+LB+I_PLANTE+RB+RP+RP+GT+R_0);
m2029effectEndRoundStatusRelation_.setEndRoundRank(38);
m2029effectEndRoundStatusRelation_.setTargetChoice(TargetChoice.NOTHING);
m2029effectEndRoundStatusRelation_.setFail(ES);
m2029custListEffectEndRoundStatus_.add(m2029effectEndRoundStatusRelation_);
m2029statusSimple_.setEffectEndRound(m2029custListEffectEndRoundStatus_);
m2029statusSimple_.setFail(V_FIGHTER_CLONE+GT+R_0+OO+A_CARDINAL+LP+A_INTER+LP+LB+V_FIGHTER_TYPES+RB+OC+LB+I_PLANTE+RB+RP+RP+GT+R_0);
return m2029statusSimple_;
}
}
