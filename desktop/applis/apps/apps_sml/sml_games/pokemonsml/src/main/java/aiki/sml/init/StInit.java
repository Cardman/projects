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
public final class StInit{
private StInit(){}
public static StringMap<Status> st(){
 StringMap<Status> s_ = new StringMap<Status>(new CollCapacity(13));
s_.addEntry("AMOUR",m0());
s_.addEntry("BRULURE",m1());
s_.addEntry("CAUCHEMAR_ST",m2());
s_.addEntry("CONFUSION",m3());
s_.addEntry("GEL",m4());
s_.addEntry("MAUDIT",m5());
s_.addEntry("PARALYSIE",m6());
s_.addEntry("PEUR",m7());
s_.addEntry("POISON_GRAVE",m8());
s_.addEntry("SIMPLE_POISON",m9());
s_.addEntry("SOMMEIL",m10());
s_.addEntry("SOMMEIL_REPOS",m11());
s_.addEntry("VAMPIGRAINE_ST",m12());
return s_;
}
static Status m0(){
StatusBeginRoundSimple statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
monteCarloBoolean_.addQuickEvent(false,LgInt.newLgInt("1"));
monteCarloBoolean_.addQuickEvent(true,LgInt.newLgInt("1"));
statusBeginRoundSimple_.setLawForUsingAMoveIfFoe(monteCarloBoolean_);
statusBeginRoundSimple_.setStatusType(StatusType.RELATION_UNIQUE);
statusBeginRoundSimple_.setCatchingRate(Rate.newRate("3"));
CustList<EffectPartnerStatus> custListEffectPartnerStatus_ = new CustList<EffectPartnerStatus>(new CollCapacity(1));
EffectPartnerStatus effectPartnerStatus_=Instances.newEffectPartnerStatus();
effectPartnerStatus_.setMultDamageAgainstFoe(Rate.newRate("2"));
effectPartnerStatus_.setWeddingAlly(true);
effectPartnerStatus_.setRestoredHpRateLovedAlly(Rate.newRate("1/2"));
custListEffectPartnerStatus_.add(effectPartnerStatus_);
statusBeginRoundSimple_.setEffectsPartner(custListEffectPartnerStatus_);
statusBeginRoundSimple_.setFail("");
return statusBeginRoundSimple_;
}
static Status m1(){
StatusSimple statusSimple_ = Instances.newStatusSimple();
statusSimple_.setStatusType(StatusType.INDIVIDUEL);
statusSimple_.setCatchingRate(Rate.newRate("3/2"));
CustList<EffectEndRoundStatus> custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundSingleStatus effectEndRoundSingleStatus_=Instances.newEffectEndRoundSingleStatus();
effectEndRoundSingleStatus_.setIncrementingDamageByRounds(false);
effectEndRoundSingleStatus_.setInflictedRateHpTarget(Rate.newRate("1/8"));
effectEndRoundSingleStatus_.setFailEndRound("");
effectEndRoundSingleStatus_.setEndRoundRank(55);
effectEndRoundSingleStatus_.setTargetChoice(TargetChoice.NOTHING);
effectEndRoundSingleStatus_.setFail("");
custListEffectEndRoundStatus_.add(effectEndRoundSingleStatus_);
statusSimple_.setEffectEndRound(custListEffectEndRoundStatus_);
EnumMap<Statistic,Rate> enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
enumMapStatisticRate_.addEntry(Statistic.ATTACK,Rate.newRate("1/2"));
statusSimple_.setMultStat(enumMapStatisticRate_);
statusSimple_.setFail("VAR__FIGHTER_CLONE>0|cardinal(inter({VAR__FIGHTER_TYPES},{FEU}))>0");
return statusSimple_;
}
static Status m2(){
StatusSimple statusSimple_ = Instances.newStatusSimple();
statusSimple_.setStatusType(StatusType.RELATION_UNIQUE);
statusSimple_.setCatchingRate(Rate.newRate("3/2"));
CustList<EffectEndRoundStatus> custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundStatusRelation effectEndRoundStatusRelation_=Instances.newEffectEndRoundStatusRelation();
effectEndRoundStatusRelation_.setThievedHpRateTargetToUser(Rate.newRate("0"));
effectEndRoundStatusRelation_.setInflictedRateHpTarget(Rate.newRate("1/4"));
effectEndRoundStatusRelation_.setFailEndRound("cardinal(inter({VAR__CIBLE_STATUTS},{SOMMEIL;SOMMEIL_REPOS}))=0");
effectEndRoundStatusRelation_.setEndRoundRank(58);
effectEndRoundStatusRelation_.setTargetChoice(TargetChoice.NOTHING);
effectEndRoundStatusRelation_.setFail("cardinal(inter({VAR__CIBLE_STATUTS},{SOMMEIL;SOMMEIL_REPOS}))=0");
custListEffectEndRoundStatus_.add(effectEndRoundStatusRelation_);
statusSimple_.setEffectEndRound(custListEffectEndRoundStatus_);
statusSimple_.setFail("");
return statusSimple_;
}
static Status m3(){
StatusBeginRoundAutoDamage statusBeginRoundAutoDamage_ = Instances.newStatusBeginRoundAutoDamage();
statusBeginRoundAutoDamage_.setPower(Rate.newRate("40"));
statusBeginRoundAutoDamage_.setAttack(Statistic.ATTACK);
statusBeginRoundAutoDamage_.setDefense(Statistic.DEFENSE);
MonteCarloBoolean monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
monteCarloBoolean_.addQuickEvent(false,LgInt.newLgInt("1"));
monteCarloBoolean_.addQuickEvent(true,LgInt.newLgInt("3"));
statusBeginRoundAutoDamage_.setLawForUsingAMove(monteCarloBoolean_);
statusBeginRoundAutoDamage_.setStatusType(StatusType.INDIVIDUEL);
statusBeginRoundAutoDamage_.setCatchingRate(Rate.newRate("3"));
statusBeginRoundAutoDamage_.setDisabledEffIfSwitch(true);
statusBeginRoundAutoDamage_.setFail("");
return statusBeginRoundAutoDamage_;
}
static Status m4(){
StatusBeginRoundSimple statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
monteCarloBoolean_.addQuickEvent(false,LgInt.newLgInt("9"));
monteCarloBoolean_.addQuickEvent(true,LgInt.newLgInt("1"));
statusBeginRoundSimple_.setLawForUsingAMove(monteCarloBoolean_);
monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(1));
monteCarloBoolean_.addQuickEvent(true,LgInt.newLgInt("1"));
statusBeginRoundSimple_.setLawForFullHealIfMove(monteCarloBoolean_);
statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
statusBeginRoundSimple_.setCatchingRate(Rate.newRate("5/2"));
statusBeginRoundSimple_.setFail("VAR__FIGHTER_CLONE>0|cardinal(inter({VAR__FIGHTER_TYPES},{GLACE}))>0");
return statusBeginRoundSimple_;
}
static Status m5(){
StatusSimple statusSimple_ = Instances.newStatusSimple();
statusSimple_.setStatusType(StatusType.RELATION_UNIQUE);
statusSimple_.setCatchingRate(Rate.newRate("3/2"));
CustList<EffectEndRoundStatus> custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundStatusRelation effectEndRoundStatusRelation_=Instances.newEffectEndRoundStatusRelation();
effectEndRoundStatusRelation_.setThievedHpRateTargetToUser(Rate.newRate("0"));
effectEndRoundStatusRelation_.setInflictedRateHpTarget(Rate.newRate("1/8"));
effectEndRoundStatusRelation_.setFailEndRound("");
effectEndRoundStatusRelation_.setEndRoundRank(57);
effectEndRoundStatusRelation_.setTargetChoice(TargetChoice.NOTHING);
effectEndRoundStatusRelation_.setFail("");
custListEffectEndRoundStatus_.add(effectEndRoundStatusRelation_);
statusSimple_.setEffectEndRound(custListEffectEndRoundStatus_);
statusSimple_.setFail("");
return statusSimple_;
}
static Status m6(){
StatusBeginRoundSimple statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(2));
monteCarloBoolean_.addQuickEvent(false,LgInt.newLgInt("1"));
monteCarloBoolean_.addQuickEvent(true,LgInt.newLgInt("3"));
statusBeginRoundSimple_.setLawForUsingAMove(monteCarloBoolean_);
statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
statusBeginRoundSimple_.setCatchingRate(Rate.newRate("2"));
EnumMap<Statistic,Rate> enumMapStatisticRate_=new EnumMap<Statistic,Rate>(new CollCapacity(1));
enumMapStatisticRate_.addEntry(Statistic.SPEED,Rate.newRate("1/4"));
statusBeginRoundSimple_.setMultStat(enumMapStatisticRate_);
statusBeginRoundSimple_.setFail("");
return statusBeginRoundSimple_;
}
static Status m7(){
StatusBeginRoundSimple statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(1));
monteCarloBoolean_.addQuickEvent(false,LgInt.newLgInt("1"));
statusBeginRoundSimple_.setLawForUsingAMove(monteCarloBoolean_);
statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
statusBeginRoundSimple_.setCatchingRate(Rate.newRate("5/4"));
statusBeginRoundSimple_.setDisabledEffIfSwitch(true);
statusBeginRoundSimple_.setIncrementEndRound(63);
statusBeginRoundSimple_.setIncrementingEndRound(true);
statusBeginRoundSimple_.setFail("");
return statusBeginRoundSimple_;
}
static Status m8(){
StatusSimple statusSimple_ = Instances.newStatusSimple();
statusSimple_.setStatusType(StatusType.INDIVIDUEL);
statusSimple_.setCatchingRate(Rate.newRate("3/2"));
CustList<EffectEndRoundStatus> custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundSingleStatus effectEndRoundSingleStatus_=Instances.newEffectEndRoundSingleStatus();
effectEndRoundSingleStatus_.setIncrementingDamageByRounds(true);
effectEndRoundSingleStatus_.setInflictedRateHpTarget(Rate.newRate("1/4"));
effectEndRoundSingleStatus_.setFailEndRound("");
effectEndRoundSingleStatus_.setEndRoundRank(56);
effectEndRoundSingleStatus_.setTargetChoice(TargetChoice.NOTHING);
effectEndRoundSingleStatus_.setFail("");
custListEffectEndRoundStatus_.add(effectEndRoundSingleStatus_);
statusSimple_.setEffectEndRound(custListEffectEndRoundStatus_);
statusSimple_.setFail("VAR__FIGHTER_CLONE>0|cardinal(inter({VAR__FIGHTER_TYPES},{POISON;ACIER}))>0");
return statusSimple_;
}
static Status m9(){
StatusSimple statusSimple_ = Instances.newStatusSimple();
statusSimple_.setStatusType(StatusType.INDIVIDUEL);
statusSimple_.setCatchingRate(Rate.newRate("3/2"));
CustList<EffectEndRoundStatus> custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundSingleStatus effectEndRoundSingleStatus_=Instances.newEffectEndRoundSingleStatus();
effectEndRoundSingleStatus_.setIncrementingDamageByRounds(false);
effectEndRoundSingleStatus_.setInflictedRateHpTarget(Rate.newRate("1/8"));
effectEndRoundSingleStatus_.setFailEndRound("");
effectEndRoundSingleStatus_.setEndRoundRank(54);
effectEndRoundSingleStatus_.setTargetChoice(TargetChoice.NOTHING);
effectEndRoundSingleStatus_.setFail("");
custListEffectEndRoundStatus_.add(effectEndRoundSingleStatus_);
statusSimple_.setEffectEndRound(custListEffectEndRoundStatus_);
statusSimple_.setFail("VAR__FIGHTER_CLONE>0|cardinal(inter({VAR__FIGHTER_TYPES},{POISON;ACIER}))>0");
return statusSimple_;
}
static Status m10(){
StatusBeginRoundSimple statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(1));
monteCarloBoolean_.addQuickEvent(false,LgInt.newLgInt("1"));
statusBeginRoundSimple_.setLawForUsingAMove(monteCarloBoolean_);
statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
statusBeginRoundSimple_.setCatchingRate(Rate.newRate("5/2"));
statusBeginRoundSimple_.setFail("");
return statusBeginRoundSimple_;
}
static Status m11(){
StatusBeginRoundSimple statusBeginRoundSimple_ = Instances.newStatusBeginRoundSimple();
MonteCarloBoolean monteCarloBoolean_=new MonteCarloBoolean(new CollCapacity(1));
monteCarloBoolean_.addQuickEvent(false,LgInt.newLgInt("1"));
statusBeginRoundSimple_.setLawForUsingAMove(monteCarloBoolean_);
statusBeginRoundSimple_.setStatusType(StatusType.INDIVIDUEL);
statusBeginRoundSimple_.setCatchingRate(Rate.newRate("5/2"));
statusBeginRoundSimple_.setFail("");
return statusBeginRoundSimple_;
}
static Status m12(){
StatusSimple statusSimple_ = Instances.newStatusSimple();
statusSimple_.setStatusType(StatusType.RELATION_UNIQUE);
statusSimple_.setCatchingRate(Rate.newRate("3/2"));
CustList<EffectEndRoundStatus> custListEffectEndRoundStatus_ = new CustList<EffectEndRoundStatus>(new CollCapacity(1));
EffectEndRoundStatusRelation effectEndRoundStatusRelation_=Instances.newEffectEndRoundStatusRelation();
effectEndRoundStatusRelation_.setThievedHpRateTargetToUser(Rate.newRate("1/8"));
effectEndRoundStatusRelation_.setInflictedRateHpTarget(Rate.newRate("0"));
effectEndRoundStatusRelation_.setFailEndRound("VAR__CIBLE_CLONE>0|cardinal(inter({VAR__CIBLE_TYPES},{PLANTE}))>0");
effectEndRoundStatusRelation_.setEndRoundRank(38);
effectEndRoundStatusRelation_.setTargetChoice(TargetChoice.NOTHING);
effectEndRoundStatusRelation_.setFail("");
custListEffectEndRoundStatus_.add(effectEndRoundStatusRelation_);
statusSimple_.setEffectEndRound(custListEffectEndRoundStatus_);
statusSimple_.setFail("VAR__FIGHTER_CLONE>0|cardinal(inter({VAR__FIGHTER_TYPES},{PLANTE}))>0");
return statusSimple_;
}
}
