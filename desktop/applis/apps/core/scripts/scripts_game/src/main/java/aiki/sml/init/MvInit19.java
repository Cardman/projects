package aiki.sml.init;
import aiki.instances.*;
import aiki.fight.util.*;
import aiki.fight.enums.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.map.levels.enums.*;
import aiki.fight.moves.enums.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
final class MvInit19 extends CstIgame{
private MvInit19(){}
static MoveData m475(){
DamagingMoveData m845damagingMoveData_ = Instances.newDamagingMoveData();
m845damagingMoveData_.setCategory(I_SPECIALE);
m845damagingMoveData_.setStoppableMoveKoSingle(true);
m845damagingMoveData_.setPp((short)5);
StringList m845stringList_=new StringList(new CollCapacity(1));
m845stringList_.add(I_FEU);
m845damagingMoveData_.setTypes(m845stringList_);
m845stringList_=new StringList(new CollCapacity(1));
m845stringList_.add(I_FEU);
m845damagingMoveData_.setBoostedTypes(m845stringList_);
m845damagingMoveData_.setAccuracy(R_9_10);
CustList<Effect> m845custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage m845effectDamage_=Instances.newEffectDamage();
m845effectDamage_.setPower(R_150);
m845effectDamage_.setUserAttack(true);
m845effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
m845effectDamage_.setTargetDefense(true);
m845effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
m845effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
m845effectDamage_.setFail(ES);
m845custListEffect_.add(m845effectDamage_);
m845damagingMoveData_.setEffects(m845custListEffect_);
m845damagingMoveData_.setRechargeRound(true);
m845damagingMoveData_.setConstUserChoice(true);
m845damagingMoveData_.setStoppableMoveSolo(true);
m845damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return m845damagingMoveData_;
}
static MoveData m476(){
DamagingMoveData m846damagingMoveData_ = Instances.newDamagingMoveData();
m846damagingMoveData_.setCategory(I_SPECIALE);
m846damagingMoveData_.setStoppableMoveKoSingle(true);
m846damagingMoveData_.setPp((short)20);
StringList m846stringList_=new StringList(new CollCapacity(1));
m846stringList_.add(I_PSY);
m846damagingMoveData_.setTypes(m846stringList_);
m846stringList_=new StringList(new CollCapacity(1));
m846stringList_.add(I_PSY);
m846damagingMoveData_.setBoostedTypes(m846stringList_);
m846damagingMoveData_.setAccuracy(R_1);
CustList<Effect> m846custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage m846effectDamage_=Instances.newEffectDamage();
m846effectDamage_.setPower(R_65);
m846effectDamage_.setUserAttack(true);
m846effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
m846effectDamage_.setTargetDefense(true);
m846effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
m846effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m846effectDamage_.setFail(ES);
m846custListEffect_.add(m846effectDamage_);
EffectStatus m846effectStatus_=Instances.newEffectStatus();
MonteCarloString m846monteCarloString_=new MonteCarloString(new CollCapacity(2));
m846monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_9));
m846monteCarloString_.addQuickEvent(I_CONFUSION,LgInt.newLgInt(R_1));
m846effectStatus_.setLawStatus(m846monteCarloString_);
StringMap<String> m846stringMapString_=new StringMap<String>(new CollCapacity(1));
m846stringMapString_.addEntry(I_CONFUSION,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_CONFUSION+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0);
m846effectStatus_.setLocalFailStatus(m846stringMapString_);
m846effectStatus_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m846effectStatus_.setFail(ES);
Ints m846ints_=new Ints(new CollCapacity(1));
m846ints_.add(0);
m846effectStatus_.setRequiredSuccessfulEffects(m846ints_);
m846custListEffect_.add(m846effectStatus_);
m846damagingMoveData_.setEffects(m846custListEffect_);
m846damagingMoveData_.setStoppableMoveSolo(true);
m846damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return m846damagingMoveData_;
}
static MoveData m477(){
StatusMoveData m847statusMoveData_ = Instances.newStatusMoveData();
m847statusMoveData_.setCounterableMove(true);
m847statusMoveData_.setPp((short)30);
StringList m847stringList_=new StringList(new CollCapacity(1));
m847stringList_.add(I_NORMAL);
m847statusMoveData_.setTypes(m847stringList_);
m847stringList_=new StringList(new CollCapacity(1));
m847stringList_.add(I_NORMAL);
m847statusMoveData_.setBoostedTypes(m847stringList_);
m847statusMoveData_.setAccuracy(R_1);
CustList<Effect> m847custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic m847effectStatistic_=Instances.newEffectStatistic();
IdMap<Statistic,Byte> m847enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(2));
m847enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)-1);
m847enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)-1);
m847effectStatistic_.setStatisVarRank(m847enumMapStatisticByte_);
IdMap<Statistic,String> m847enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(2));
m847enumMapStatisticString_.addEntry(Statistic.ATTACK,V_CIBLE_CLONE+GT+R_0);
m847enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,V_CIBLE_CLONE+GT+R_0);
m847effectStatistic_.setLocalFailStatis(m847enumMapStatisticString_);
m847effectStatistic_.setEvtRate(Rate.newRate(R_1));
m847effectStatistic_.setTargetChoice(TargetChoice.ADJ_UNIQ);
m847effectStatistic_.setFail(ES);
m847custListEffect_.add(m847effectStatistic_);
m847statusMoveData_.setEffects(m847custListEffect_);
m847statusMoveData_.setStoppableMoveSolo(true);
m847statusMoveData_.setIgnVarAccurUserNeg(true);
m847statusMoveData_.setIgnVarEvasTargetPos(true);
m847statusMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return m847statusMoveData_;
}
static MoveData m478(){
DamagingMoveData m848damagingMoveData_ = Instances.newDamagingMoveData();
m848damagingMoveData_.setCategory(I_SPECIALE);
m848damagingMoveData_.setStoppableMoveKoSingle(true);
m848damagingMoveData_.setPp((short)25);
StringList m848stringList_=new StringList(new CollCapacity(1));
m848stringList_.add(I_TENEBRE);
m848damagingMoveData_.setTypes(m848stringList_);
m848stringList_=new StringList(new CollCapacity(1));
m848stringList_.add(I_TENEBRE);
m848damagingMoveData_.setBoostedTypes(m848stringList_);
m848damagingMoveData_.setAccuracy(R_1);
CustList<Effect> m848custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage m848effectDamage_=Instances.newEffectDamage();
m848effectDamage_.setPower(R_65);
m848effectDamage_.setUserAttack(true);
m848effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
m848effectDamage_.setTargetDefense(true);
m848effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
m848effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m848effectDamage_.setFail(ES);
m848custListEffect_.add(m848effectDamage_);
m848damagingMoveData_.setEffects(m848custListEffect_);
m848damagingMoveData_.setStoppableMoveSolo(true);
m848damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return m848damagingMoveData_;
}
static MoveData m479(){
DamagingMoveData m849damagingMoveData_ = Instances.newDamagingMoveData();
m849damagingMoveData_.setCategory(I_PHYSIQUE);
m849damagingMoveData_.setDirect(true);
m849damagingMoveData_.setStoppableMoveKoSingle(true);
m849damagingMoveData_.setPp((short)15);
StringList m849stringList_=new StringList(new CollCapacity(1));
m849stringList_.add(I_VOL);
m849damagingMoveData_.setTypes(m849stringList_);
m849stringList_=new StringList(new CollCapacity(1));
m849stringList_.add(I_VOL);
m849damagingMoveData_.setBoostedTypes(m849stringList_);
m849damagingMoveData_.setAccuracy(R_1);
CustList<Effect> m849custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage m849effectDamage_=Instances.newEffectDamage();
m849effectDamage_.setPower(R_120);
m849effectDamage_.setUserAttack(true);
m849effectDamage_.setStatisAtt(Statistic.ATTACK);
m849effectDamage_.setTargetDefense(true);
m849effectDamage_.setStatisDef(Statistic.DEFENSE);
m849effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
m849effectDamage_.setFail(ES);
m849custListEffect_.add(m849effectDamage_);
EffectDamageRate m849effectDamageRate_=Instances.newEffectDamageRate();
m849effectDamageRate_.setRateDamage(Rate.newRate(OD+R_1_3));
m849effectDamageRate_.setTargetChoice(TargetChoice.LANCEUR);
m849effectDamageRate_.setFail(ES);
Ints m849ints_=new Ints(new CollCapacity(1));
m849ints_.add(0);
m849effectDamageRate_.setRequiredSuccessfulEffects(m849ints_);
m849custListEffect_.add(m849effectDamageRate_);
m849damagingMoveData_.setEffects(m849custListEffect_);
m849damagingMoveData_.setStoppableMoveSolo(true);
m849damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return m849damagingMoveData_;
}
static MoveData m480(){
DamagingMoveData m850damagingMoveData_ = Instances.newDamagingMoveData();
m850damagingMoveData_.setCategory(I_SPECIALE);
m850damagingMoveData_.setStoppableMoveKoSingle(true);
m850damagingMoveData_.setPp((short)10);
StringList m850stringList_=new StringList(new CollCapacity(1));
m850stringList_.add(I_ELECTRIQUE);
m850damagingMoveData_.setTypes(m850stringList_);
m850stringList_=new StringList(new CollCapacity(1));
m850stringList_.add(I_ELECTRIQUE);
m850damagingMoveData_.setBoostedTypes(m850stringList_);
m850damagingMoveData_.setAccuracy(R_9_10);
CustList<Effect> m850custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage m850effectDamage_=Instances.newEffectDamage();
m850effectDamage_.setPower(R_50);
m850effectDamage_.setUserAttack(true);
m850effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
m850effectDamage_.setTargetDefense(true);
m850effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
m850effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m850effectDamage_.setFail(ES);
m850custListEffect_.add(m850effectDamage_);
EffectStatistic m850effectStatistic_=Instances.newEffectStatistic();
IdMap<Statistic,Byte> m850enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m850enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)1);
m850effectStatistic_.setStatisVarRank(m850enumMapStatisticByte_);
m850effectStatistic_.setEvtRate(Rate.newRate(R_7_10));
m850effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
m850effectStatistic_.setFail(ES);
Ints m850ints_=new Ints(new CollCapacity(1));
m850ints_.add(0);
m850effectStatistic_.setRequiredSuccessfulEffects(m850ints_);
m850custListEffect_.add(m850effectStatistic_);
m850damagingMoveData_.setEffects(m850custListEffect_);
m850damagingMoveData_.setStoppableMoveSolo(true);
m850damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return m850damagingMoveData_;
}
static MoveData m481(){
DamagingMoveData m851damagingMoveData_ = Instances.newDamagingMoveData();
m851damagingMoveData_.setCategory(I_SPECIALE);
m851damagingMoveData_.setStoppableMoveKoSingle(true);
m851damagingMoveData_.setPp((short)20);
StringList m851stringList_=new StringList(new CollCapacity(1));
m851stringList_.add(I_ROCHE);
m851damagingMoveData_.setTypes(m851stringList_);
m851stringList_=new StringList(new CollCapacity(1));
m851stringList_.add(I_ROCHE);
m851damagingMoveData_.setBoostedTypes(m851stringList_);
m851damagingMoveData_.setAccuracy(R_1);
CustList<Effect> m851custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage m851effectDamage_=Instances.newEffectDamage();
m851effectDamage_.setPower(R_90);
m851effectDamage_.setUserAttack(true);
m851effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
m851effectDamage_.setTargetDefense(true);
m851effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
m851effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
m851effectDamage_.setFail(ES);
m851custListEffect_.add(m851effectDamage_);
m851damagingMoveData_.setEffects(m851custListEffect_);
m851damagingMoveData_.setStoppableMoveSolo(true);
m851damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return m851damagingMoveData_;
}
static MoveData m482(){
StatusMoveData m852statusMoveData_ = Instances.newStatusMoveData();
m852statusMoveData_.setThievableMove(true);
m852statusMoveData_.setPp((short)5);
StringList m852stringList_=new StringList(new CollCapacity(1));
m852stringList_.add(I_NORMAL);
m852statusMoveData_.setTypes(m852stringList_);
m852stringList_=new StringList(new CollCapacity(1));
m852stringList_.add(I_NORMAL);
m852statusMoveData_.setBoostedTypes(m852stringList_);
m852statusMoveData_.setAccuracy(R_1);
CustList<Effect> m852custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectFullHpRate m852effectFullHpRate_=Instances.newEffectFullHpRate();
m852effectFullHpRate_.setRestoredHp(R_1_2+OM+A_CARACFERME+LP+A_CARDINAL+LP+LB+V_CLIMATS+RB+RP+OC+R_0+OC+R_0+RP+OP+A_CARACDROITEFERME+LP+A_CARDINAL+LP+LB+V_CLIMATS+RB+RP+OC+R_1+RP+OM+LP+R_2_3+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_ZENITH+RB+RP+RP+OP+R_1_5+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_ORAGE+RB+RP+RP+OP+R_1_4+OM+A_CARDINAL+LP+A_INTER+LP+LB+V_CLIMATS+RB+OC+LB+I_GRELE+OS+I_DANSE_PLUIE+OS+I_TEMPETESABLE+RB+RP+RP+RP);
m852effectFullHpRate_.setTargetChoice(TargetChoice.LANCEUR);
m852effectFullHpRate_.setFail(ES);
m852custListEffect_.add(m852effectFullHpRate_);
m852statusMoveData_.setEffects(m852custListEffect_);
m852statusMoveData_.setStoppableMoveMulti(true);
m852statusMoveData_.setIgnVarAccurUserNeg(true);
m852statusMoveData_.setIgnVarEvasTargetPos(true);
m852statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m852statusMoveData_;
}
static MoveData m483(){
DamagingMoveData m853damagingMoveData_ = Instances.newDamagingMoveData();
m853damagingMoveData_.setCategory(I_SPECIALE);
m853damagingMoveData_.setStoppableMoveKoSingle(true);
m853damagingMoveData_.setPp((short)15);
StringList m853stringList_=new StringList(new CollCapacity(1));
m853stringList_.add(I_INSECTE);
m853damagingMoveData_.setTypes(m853stringList_);
m853stringList_=new StringList(new CollCapacity(1));
m853stringList_.add(I_INSECTE);
m853damagingMoveData_.setBoostedTypes(m853stringList_);
m853damagingMoveData_.setAccuracy(R_1);
CustList<Effect> m853custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage m853effectDamage_=Instances.newEffectDamage();
m853effectDamage_.setPower(R_75);
m853effectDamage_.setUserAttack(true);
m853effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
m853effectDamage_.setTargetDefense(true);
m853effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
m853effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m853effectDamage_.setFail(ES);
m853custListEffect_.add(m853effectDamage_);
EffectStatus m853effectStatus_=Instances.newEffectStatus();
MonteCarloString m853monteCarloString_=new MonteCarloString(new CollCapacity(2));
m853monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_9));
m853monteCarloString_.addQuickEvent(I_CONFUSION,LgInt.newLgInt(R_1));
m853effectStatus_.setLawStatus(m853monteCarloString_);
StringMap<String> m853stringMapString_=new StringMap<String>(new CollCapacity(1));
m853stringMapString_.addEntry(I_CONFUSION,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_CONFUSION+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0);
m853effectStatus_.setLocalFailStatus(m853stringMapString_);
m853effectStatus_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m853effectStatus_.setFail(ES);
Ints m853ints_=new Ints(new CollCapacity(1));
m853ints_.add(0);
m853effectStatus_.setRequiredSuccessfulEffects(m853ints_);
m853custListEffect_.add(m853effectStatus_);
m853damagingMoveData_.setEffects(m853custListEffect_);
m853damagingMoveData_.setStoppableMoveSolo(true);
m853damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return m853damagingMoveData_;
}
static MoveData m484(){
StatusMoveData m854statusMoveData_ = Instances.newStatusMoveData();
m854statusMoveData_.setPp((short)15);
StringList m854stringList_=new StringList(new CollCapacity(1));
m854stringList_.add(I_NORMAL);
m854statusMoveData_.setTypes(m854stringList_);
m854stringList_=new StringList(new CollCapacity(1));
m854stringList_.add(I_NORMAL);
m854statusMoveData_.setBoostedTypes(m854stringList_);
m854statusMoveData_.setAccuracy(R_1);
CustList<Effect> m854custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectSwitchAbilities m854effectSwitchAbilities_=Instances.newEffectSwitchAbilities();
m854effectSwitchAbilities_.setExchangeAbility(ExchangeType.GIVE_CONST);
m854effectSwitchAbilities_.setConstAbility(I_SIMPLE);
m854effectSwitchAbilities_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m854effectSwitchAbilities_.setFail(ES);
m854custListEffect_.add(m854effectSwitchAbilities_);
m854statusMoveData_.setEffects(m854custListEffect_);
m854statusMoveData_.setStoppableMoveSolo(true);
m854statusMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return m854statusMoveData_;
}
static MoveData m485(){
DamagingMoveData m855damagingMoveData_ = Instances.newDamagingMoveData();
m855damagingMoveData_.setCategory(I_PHYSIQUE);
m855damagingMoveData_.setDirect(true);
m855damagingMoveData_.setStoppableMoveKoSingle(true);
m855damagingMoveData_.setPp((short)5);
StringList m855stringList_=new StringList(new CollCapacity(1));
m855stringList_.add(I_VOL);
m855damagingMoveData_.setTypes(m855stringList_);
m855stringList_=new StringList(new CollCapacity(1));
m855stringList_.add(I_VOL);
m855damagingMoveData_.setBoostedTypes(m855stringList_);
m855damagingMoveData_.setAccuracy(R_17_20);
CustList<Effect> m855custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage m855effectDamage_=Instances.newEffectDamage();
m855effectDamage_.setPower(R_85);
m855effectDamage_.setUserAttack(true);
m855effectDamage_.setStatisAtt(Statistic.ATTACK);
m855effectDamage_.setTargetDefense(true);
m855effectDamage_.setStatisDef(Statistic.DEFENSE);
m855effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m855effectDamage_.setFail(ES);
m855custListEffect_.add(m855effectDamage_);
EffectStatus m855effectStatus_=Instances.newEffectStatus();
MonteCarloString m855monteCarloString_=new MonteCarloString(new CollCapacity(2));
m855monteCarloString_.addQuickEvent(ES,LgInt.newLgInt(R_7));
m855monteCarloString_.addQuickEvent(I_PARALYSIE,LgInt.newLgInt(R_3));
m855effectStatus_.setLawStatus(m855monteCarloString_);
StringMap<String> m855stringMapString_=new StringMap<String>(new CollCapacity(1));
m855stringMapString_.addEntry(I_PARALYSIE,A_CARDINAL+LP+A_INTER+LP+LB+V_CIBLE_STATUTS+RB+OC+LB+I_PARALYSIE+RB+RP+RP+GT+R_0+OO+V_CIBLE_CLONE+GT+R_0);
m855effectStatus_.setLocalFailStatus(m855stringMapString_);
m855effectStatus_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m855effectStatus_.setFail(ES);
Ints m855ints_=new Ints(new CollCapacity(1));
m855ints_.add(0);
m855effectStatus_.setRequiredSuccessfulEffects(m855ints_);
m855custListEffect_.add(m855effectStatus_);
m855damagingMoveData_.setEffects(m855custListEffect_);
m855damagingMoveData_.setNbPrepaRound((short)1);
m855damagingMoveData_.setDisappearBeforeUse(true);
m855damagingMoveData_.setStoppableMoveSolo(true);
m855damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return m855damagingMoveData_;
}
static MoveData m486(){
DamagingMoveData m856damagingMoveData_ = Instances.newDamagingMoveData();
m856damagingMoveData_.setCategory(I_SPECIALE);
m856damagingMoveData_.setStoppableMoveKoSingle(true);
m856damagingMoveData_.setPp((short)15);
StringList m856stringList_=new StringList(new CollCapacity(1));
m856stringList_.add(I_FEU);
m856damagingMoveData_.setTypes(m856stringList_);
m856stringList_=new StringList(new CollCapacity(1));
m856stringList_.add(I_FEU);
m856damagingMoveData_.setBoostedTypes(m856stringList_);
m856damagingMoveData_.setAccuracy(R_1);
CustList<Effect> m856custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage m856effectDamage_=Instances.newEffectDamage();
m856effectDamage_.setPower(R_70);
m856effectDamage_.setUserAttack(true);
m856effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
m856effectDamage_.setTargetDefense(true);
m856effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
m856effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
m856effectDamage_.setFail(ES);
m856custListEffect_.add(m856effectDamage_);
EffectFullHpRate m856effectFullHpRate_=Instances.newEffectFullHpRate();
m856effectFullHpRate_.setRestoredHp(ES);
m856effectFullHpRate_.setClosestFoeDamageRateHp(Rate.newRate(R_1_8));
m856effectFullHpRate_.setTargetChoice(TargetChoice.LANCEUR);
m856effectFullHpRate_.setFail(ES);
Ints m856ints_=new Ints(new CollCapacity(1));
m856ints_.add(0);
m856effectFullHpRate_.setRequiredSuccessfulEffects(m856ints_);
m856custListEffect_.add(m856effectFullHpRate_);
m856damagingMoveData_.setEffects(m856custListEffect_);
m856damagingMoveData_.setStoppableMoveSolo(true);
m856damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return m856damagingMoveData_;
}
static MoveData m487(){
StatusMoveData m857statusMoveData_ = Instances.newStatusMoveData();
m857statusMoveData_.setThievableMove(true);
m857statusMoveData_.setPp((short)10);
StringList m857stringList_=new StringList(new CollCapacity(1));
m857stringList_.add(I_NORMAL);
m857statusMoveData_.setTypes(m857stringList_);
m857stringList_=new StringList(new CollCapacity(1));
m857stringList_.add(I_NORMAL);
m857statusMoveData_.setBoostedTypes(m857stringList_);
m857statusMoveData_.setAccuracy(R_1);
CustList<Effect> m857custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectSwitchItems m857effectSwitchItems_=Instances.newEffectSwitchItems();
m857effectSwitchItems_.setMoveObject(MoveItemType.REUSE_LAST_OBJECT);
m857effectSwitchItems_.setTargetChoice(TargetChoice.LANCEUR);
m857effectSwitchItems_.setFail(A_CARDINAL+LP+LB+V_LANCEUR_OBJET+RB+RP+GT+R_0);
m857custListEffect_.add(m857effectSwitchItems_);
m857statusMoveData_.setEffects(m857custListEffect_);
m857statusMoveData_.setStoppableMoveMulti(true);
m857statusMoveData_.setIgnVarAccurUserNeg(true);
m857statusMoveData_.setIgnVarEvasTargetPos(true);
m857statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m857statusMoveData_;
}
static MoveData m488(){
StatusMoveData m858statusMoveData_ = Instances.newStatusMoveData();
m858statusMoveData_.setThievableMove(true);
m858statusMoveData_.setPp((short)15);
StringList m858stringList_=new StringList(new CollCapacity(1));
m858stringList_.add(I_NORMAL);
m858statusMoveData_.setTypes(m858stringList_);
m858stringList_=new StringList(new CollCapacity(1));
m858stringList_.add(I_NORMAL);
m858statusMoveData_.setBoostedTypes(m858stringList_);
m858statusMoveData_.setAccuracy(R_1);
CustList<Effect> m858custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic m858effectStatistic_=Instances.newEffectStatistic();
IdMap<Statistic,Byte> m858enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m858enumMapStatisticByte_.addEntry(Statistic.EVASINESS,(byte)1);
m858effectStatistic_.setStatisVarRank(m858enumMapStatisticByte_);
m858effectStatistic_.setEvtRate(Rate.newRate(R_1));
m858effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
m858effectStatistic_.setFail(ES);
m858custListEffect_.add(m858effectStatistic_);
m858statusMoveData_.setEffects(m858custListEffect_);
m858statusMoveData_.setStoppableMoveMulti(true);
m858statusMoveData_.setIgnVarAccurUserNeg(true);
m858statusMoveData_.setIgnVarEvasTargetPos(true);
m858statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m858statusMoveData_;
}
static MoveData m489(){
StatusMoveData m859statusMoveData_ = Instances.newStatusMoveData();
m859statusMoveData_.setPp((short)15);
StringList m859stringList_=new StringList(new CollCapacity(1));
m859stringList_.add(I_PSY);
m859statusMoveData_.setTypes(m859stringList_);
m859stringList_=new StringList(new CollCapacity(1));
m859stringList_.add(I_PSY);
m859statusMoveData_.setBoostedTypes(m859stringList_);
m859statusMoveData_.setPriority((byte)4);
m859statusMoveData_.setAccuracy(R_1);
CustList<Effect> m859custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectSwitchPointView m859effectSwitchPointView_=Instances.newEffectSwitchPointView();
m859effectSwitchPointView_.setPointViewChangement(PointViewChangementType.MIRROR_AGAINST_THROWER);
m859effectSwitchPointView_.setTargetChoice(TargetChoice.LANCEUR);
m859effectSwitchPointView_.setFail(ES);
m859custListEffect_.add(m859effectSwitchPointView_);
m859statusMoveData_.setEffects(m859custListEffect_);
m859statusMoveData_.setStoppableMoveMulti(true);
m859statusMoveData_.setStoppableMovePrio(true);
m859statusMoveData_.setIgnVarAccurUserNeg(true);
m859statusMoveData_.setIgnVarEvasTargetPos(true);
m859statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m859statusMoveData_;
}
static MoveData m490(){
StatusMoveData m860statusMoveData_ = Instances.newStatusMoveData();
m860statusMoveData_.setPp((short)5);
StringList m860stringList_=new StringList(new CollCapacity(1));
m860stringList_.add(I_SPECTRE);
m860statusMoveData_.setTypes(m860stringList_);
m860stringList_=new StringList(new CollCapacity(1));
m860stringList_.add(I_SPECTRE);
m860statusMoveData_.setBoostedTypes(m860stringList_);
m860statusMoveData_.setAccuracy(R_1);
CustList<Effect> m860custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectTeam m860effectTeam_=Instances.newEffectTeam();
IdMap<Statistic,Rate> m860enumMapStatisticRate_=new IdMap<Statistic,Rate>(new CollCapacity(1));
m860enumMapStatisticRate_.addEntry(Statistic.ACCURACY,Rate.newRate(R_2));
m860effectTeam_.setMultStatistic(m860enumMapStatisticRate_);
m860enumMapStatisticRate_=new IdMap<Statistic,Rate>(new CollCapacity(1));
m860enumMapStatisticRate_.addEntry(Statistic.EVASINESS,Rate.newRate(R_1_2));
m860effectTeam_.setMultStatisticFoe(m860enumMapStatisticRate_);
m860effectTeam_.setTargetChoice(TargetChoice.LANCEUR);
m860effectTeam_.setFail(ES);
m860custListEffect_.add(m860effectTeam_);
m860statusMoveData_.setEffects(m860custListEffect_);
m860statusMoveData_.setRankIncrementNbRound((short)12);
m860statusMoveData_.setStoppableMoveMulti(true);
m860statusMoveData_.setIgnVarAccurUserNeg(true);
m860statusMoveData_.setIgnVarEvasTargetPos(true);
m860statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m860statusMoveData_;
}
static MoveData m491(){
StatusMoveData m861statusMoveData_ = Instances.newStatusMoveData();
m861statusMoveData_.setPp((short)30);
StringList m861stringList_=new StringList(new CollCapacity(1));
m861stringList_.add(I_FEE);
m861statusMoveData_.setTypes(m861stringList_);
m861stringList_=new StringList(new CollCapacity(1));
m861stringList_.add(I_FEE);
m861statusMoveData_.setBoostedTypes(m861stringList_);
m861statusMoveData_.setPriority((byte)1);
m861statusMoveData_.setAccuracy(R_1);
CustList<Effect> m861custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic m861effectStatistic_=Instances.newEffectStatistic();
IdMap<Statistic,Byte> m861enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m861enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)-1);
m861effectStatistic_.setStatisVarRank(m861enumMapStatisticByte_);
IdMap<Statistic,String> m861enumMapStatisticString_=new IdMap<Statistic,String>(new CollCapacity(1));
m861enumMapStatisticString_.addEntry(Statistic.ATTACK,V_CIBLE_CLONE+GT+R_0);
m861effectStatistic_.setLocalFailStatis(m861enumMapStatisticString_);
m861effectStatistic_.setEvtRate(Rate.newRate(R_1));
m861effectStatistic_.setTargetChoice(TargetChoice.ADJ_UNIQ);
m861effectStatistic_.setFail(ES);
m861custListEffect_.add(m861effectStatistic_);
m861statusMoveData_.setEffects(m861custListEffect_);
m861statusMoveData_.setStoppableMoveSolo(true);
m861statusMoveData_.setStoppableMovePrio(true);
m861statusMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return m861statusMoveData_;
}
static MoveData m492(){
StatusMoveData m862statusMoveData_ = Instances.newStatusMoveData();
m862statusMoveData_.setThievableMove(true);
m862statusMoveData_.setPp((short)20);
StringList m862stringList_=new StringList(new CollCapacity(1));
m862stringList_.add(I_NORMAL);
m862statusMoveData_.setTypes(m862stringList_);
m862stringList_=new StringList(new CollCapacity(1));
m862stringList_.add(I_NORMAL);
m862statusMoveData_.setBoostedTypes(m862stringList_);
m862statusMoveData_.setAccuracy(R_1);
CustList<Effect> m862custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatus m862effectStatus_=Instances.newEffectStatus();
m862stringList_=new StringList(new CollCapacity(13));
m862stringList_.add(I_POISON_GRAVE);
m862stringList_.add(I_PEUR);
m862stringList_.add(I_BRULURE);
m862stringList_.add(I_VAMPIGRAINE_ST);
m862stringList_.add(I_SIMPLE_POISON);
m862stringList_.add(I_GEL);
m862stringList_.add(I_AMOUR);
m862stringList_.add(I_SOMMEIL);
m862stringList_.add(I_SOMMEIL_REPOS);
m862stringList_.add(I_MAUDIT);
m862stringList_.add(I_CAUCHEMAR_ST);
m862stringList_.add(I_CONFUSION);
m862stringList_.add(I_PARALYSIE);
m862effectStatus_.setDeletedStatus(m862stringList_);
m862effectStatus_.setTargetChoice(TargetChoice.LANCEUR);
m862effectStatus_.setFail(ES);
m862custListEffect_.add(m862effectStatus_);
m862statusMoveData_.setEffects(m862custListEffect_);
m862statusMoveData_.setStoppableMoveMulti(true);
m862statusMoveData_.setIgnVarAccurUserNeg(true);
m862statusMoveData_.setIgnVarEvasTargetPos(true);
m862statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m862statusMoveData_;
}
static MoveData m493(){
DamagingMoveData m863damagingMoveData_ = Instances.newDamagingMoveData();
m863damagingMoveData_.setCategory(I_SPECIALE);
m863damagingMoveData_.setStoppableMoveKoSingle(true);
m863damagingMoveData_.setPp((short)10);
StringList m863stringList_=new StringList(new CollCapacity(1));
m863stringList_.add(I_NORMAL);
m863damagingMoveData_.setTypes(m863stringList_);
m863stringList_=new StringList(new CollCapacity(1));
m863stringList_.add(I_NORMAL);
m863damagingMoveData_.setBoostedTypes(m863stringList_);
m863damagingMoveData_.setAccuracy(R_1);
CustList<Effect> m863custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage m863effectDamage_=Instances.newEffectDamage();
m863effectDamage_.setPower(A_MIN+LP+V_LANCEUR_NB_UTILISATION+SE+I_STOCKAGE+OC+R_3+RP+OM+R_100);
m863effectDamage_.setRandMax(true);
m863effectDamage_.setUserAttack(true);
m863effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
m863effectDamage_.setTargetDefense(true);
m863effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
m863effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
m863effectDamage_.setFail(V_LANCEUR_NB_UTILISATION+SE+I_STOCKAGE+LT+R_1);
m863custListEffect_.add(m863effectDamage_);
m863damagingMoveData_.setEffects(m863custListEffect_);
m863damagingMoveData_.setStoppableMoveSolo(true);
m863damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return m863damagingMoveData_;
}
static MoveData m494(){
StatusMoveData m864statusMoveData_ = Instances.newStatusMoveData();
m864statusMoveData_.setPp((short)40);
StringList m864stringList_=new StringList(new CollCapacity(1));
m864stringList_.add(I_NORMAL);
m864statusMoveData_.setTypes(m864stringList_);
m864stringList_=new StringList(new CollCapacity(1));
m864stringList_.add(I_NORMAL);
m864statusMoveData_.setBoostedTypes(m864stringList_);
m864statusMoveData_.setAccuracy(R_1);
CustList<Effect> m864custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectBatonPass m864effectBatonPass_=Instances.newEffectBatonPass();
m864effectBatonPass_.setTargetChoice(TargetChoice.LANCEUR);
m864effectBatonPass_.setFail(V_PAS_PARTENAIRE_ARRIERE);
m864custListEffect_.add(m864effectBatonPass_);
m864statusMoveData_.setEffects(m864custListEffect_);
m864statusMoveData_.setStoppableMoveMulti(true);
m864statusMoveData_.setIgnVarAccurUserNeg(true);
m864statusMoveData_.setIgnVarEvasTargetPos(true);
m864statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m864statusMoveData_;
}
static MoveData m495(){
StatusMoveData m865statusMoveData_ = Instances.newStatusMoveData();
m865statusMoveData_.setThievableMove(true);
m865statusMoveData_.setPp((short)30);
StringList m865stringList_=new StringList(new CollCapacity(1));
m865stringList_.add(I_NORMAL);
m865statusMoveData_.setTypes(m865stringList_);
m865stringList_=new StringList(new CollCapacity(1));
m865stringList_.add(I_NORMAL);
m865statusMoveData_.setBoostedTypes(m865stringList_);
m865statusMoveData_.setAccuracy(R_1);
CustList<Effect> m865custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic m865effectStatistic_=Instances.newEffectStatistic();
IdMap<Statistic,Byte> m865enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(2));
m865enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)1);
m865enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)1);
m865effectStatistic_.setStatisVarRank(m865enumMapStatisticByte_);
m865effectStatistic_.setEvtRate(Rate.newRate(R_1));
m865effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
m865effectStatistic_.setFail(ES);
m865custListEffect_.add(m865effectStatistic_);
m865statusMoveData_.setEffects(m865custListEffect_);
m865statusMoveData_.setStoppableMoveMulti(true);
m865statusMoveData_.setIgnVarAccurUserNeg(true);
m865statusMoveData_.setIgnVarEvasTargetPos(true);
m865statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m865statusMoveData_;
}
static MoveData m496(){
DamagingMoveData m866damagingMoveData_ = Instances.newDamagingMoveData();
m866damagingMoveData_.setCategory(I_SPECIALE);
m866damagingMoveData_.setStoppableMoveKoSingle(true);
m866damagingMoveData_.setPp((short)25);
StringList m866stringList_=new StringList(new CollCapacity(1));
m866stringList_.add(I_TENEBRE);
m866damagingMoveData_.setTypes(m866stringList_);
m866stringList_=new StringList(new CollCapacity(1));
m866stringList_.add(I_TENEBRE);
m866damagingMoveData_.setBoostedTypes(m866stringList_);
m866damagingMoveData_.setAccuracy(R_1);
CustList<Effect> m866custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage m866effectDamage_=Instances.newEffectDamage();
m866effectDamage_.setPower(R_65);
m866effectDamage_.setUserAttack(true);
m866effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
m866effectDamage_.setTargetDefense(true);
m866effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
m866effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m866effectDamage_.setFail(ES);
m866custListEffect_.add(m866effectDamage_);
m866damagingMoveData_.setEffects(m866custListEffect_);
m866damagingMoveData_.setStoppableMoveSolo(true);
m866damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return m866damagingMoveData_;
}
static MoveData m497(){
StatusMoveData m867statusMoveData_ = Instances.newStatusMoveData();
m867statusMoveData_.setThievableMove(true);
m867statusMoveData_.setPp((short)40);
StringList m867stringList_=new StringList(new CollCapacity(1));
m867stringList_.add(I_EAU);
m867statusMoveData_.setTypes(m867stringList_);
m867stringList_=new StringList(new CollCapacity(1));
m867stringList_.add(I_EAU);
m867statusMoveData_.setBoostedTypes(m867stringList_);
m867statusMoveData_.setAccuracy(R_1);
CustList<Effect> m867custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic m867effectStatistic_=Instances.newEffectStatistic();
IdMap<Statistic,Byte> m867enumMapStatisticByte_=new IdMap<Statistic,Byte>(new CollCapacity(1));
m867enumMapStatisticByte_.addEntry(Statistic.DEFENSE,(byte)1);
m867effectStatistic_.setStatisVarRank(m867enumMapStatisticByte_);
m867effectStatistic_.setEvtRate(Rate.newRate(R_1));
m867effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
m867effectStatistic_.setFail(ES);
m867custListEffect_.add(m867effectStatistic_);
m867statusMoveData_.setEffects(m867custListEffect_);
m867statusMoveData_.setStoppableMoveMulti(true);
m867statusMoveData_.setIgnVarAccurUserNeg(true);
m867statusMoveData_.setIgnVarEvasTargetPos(true);
m867statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m867statusMoveData_;
}
static MoveData m498(){
StatusMoveData m868statusMoveData_ = Instances.newStatusMoveData();
m868statusMoveData_.setThievableMove(true);
m868statusMoveData_.setPp((short)10);
StringList m868stringList_=new StringList(new CollCapacity(1));
m868stringList_.add(I_PSY);
m868statusMoveData_.setTypes(m868stringList_);
m868stringList_=new StringList(new CollCapacity(1));
m868stringList_.add(I_PSY);
m868statusMoveData_.setBoostedTypes(m868stringList_);
m868statusMoveData_.setAccuracy(R_1);
CustList<Effect> m868custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectStatus m868effectStatus_=Instances.newEffectStatus();
MonteCarloString m868monteCarloString_=new MonteCarloString(new CollCapacity(1));
m868monteCarloString_.addQuickEvent(I_SOMMEIL_REPOS,LgInt.newLgInt(R_1));
m868effectStatus_.setLawStatus(m868monteCarloString_);
m868stringList_=new StringList(new CollCapacity(9));
m868stringList_.add(I_GEL);
m868stringList_.add(I_PARALYSIE);
m868stringList_.add(I_AMOUR);
m868stringList_.add(I_BRULURE);
m868stringList_.add(I_CONFUSION);
m868stringList_.add(I_MAUDIT);
m868stringList_.add(I_POISON_GRAVE);
m868stringList_.add(I_SIMPLE_POISON);
m868stringList_.add(I_VAMPIGRAINE_ST);
m868effectStatus_.setDeletedStatus(m868stringList_);
StringMap<String> m868stringMapString_=new StringMap<String>(new CollCapacity(1));
m868stringMapString_.addEntry(I_SOMMEIL_REPOS,A_CARDINAL+LP+A_INTER+LP+LB+V_LANCEUR_STATUTS+RB+OC+LB+I_SOMMEIL+OS+I_SOMMEIL_REPOS+RB+RP+RP+GT+R_0);
m868effectStatus_.setLocalFailStatus(m868stringMapString_);
m868effectStatus_.setTargetChoice(TargetChoice.LANCEUR);
m868effectStatus_.setFail(V_LANCEUR_PV_RESTANTS+OE+V_LANCEUR_PV_MAX);
m868custListEffect_.add(m868effectStatus_);
EffectFullHpRate m868effectFullHpRate_=Instances.newEffectFullHpRate();
m868effectFullHpRate_.setRestoredHp(R_1);
m868effectFullHpRate_.setTargetChoice(TargetChoice.LANCEUR);
m868effectFullHpRate_.setFail(ES);
Ints m868ints_=new Ints(new CollCapacity(1));
m868ints_.add(0);
m868effectFullHpRate_.setRequiredSuccessfulEffects(m868ints_);
m868custListEffect_.add(m868effectFullHpRate_);
m868statusMoveData_.setEffects(m868custListEffect_);
m868statusMoveData_.setStoppableMoveMulti(true);
m868statusMoveData_.setIgnVarAccurUserNeg(true);
m868statusMoveData_.setIgnVarEvasTargetPos(true);
m868statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return m868statusMoveData_;
}
static MoveData m499(){
DamagingMoveData m869damagingMoveData_ = Instances.newDamagingMoveData();
m869damagingMoveData_.setCategory(I_PHYSIQUE);
m869damagingMoveData_.setDirect(true);
m869damagingMoveData_.setStoppableMoveKoSingle(true);
m869damagingMoveData_.setPp((short)10);
StringList m869stringList_=new StringList(new CollCapacity(1));
m869stringList_.add(I_TENEBRE);
m869damagingMoveData_.setTypes(m869stringList_);
m869stringList_=new StringList(new CollCapacity(1));
m869stringList_.add(I_TENEBRE);
m869damagingMoveData_.setBoostedTypes(m869stringList_);
m869damagingMoveData_.setAccuracy(R_1);
CustList<Effect> m869custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage m869effectDamage_=Instances.newEffectDamage();
m869effectDamage_.setPower(R_50+OM+LP+A_CARACFERME+LP+V_LANCEUR_DEGATS_RECUS_TOTAL_TOUR+OC+R_0+OC+R_0+RP+OP+R_2+OM+A_CARACDROITEOUVERT+LP+V_LANCEUR_DEGATS_RECUS_TOTAL_TOUR+OC+R_0+RP+RP);
m869effectDamage_.setUserAttack(true);
m869effectDamage_.setStatisAtt(Statistic.ATTACK);
m869effectDamage_.setTargetDefense(true);
m869effectDamage_.setStatisDef(Statistic.DEFENSE);
m869effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
m869effectDamage_.setFail(ES);
m869custListEffect_.add(m869effectDamage_);
m869damagingMoveData_.setEffects(m869custListEffect_);
m869damagingMoveData_.setStoppableMoveSolo(true);
m869damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return m869damagingMoveData_;
}
}
