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
final class MvInit21{
private MvInit21(){}
static MoveData m525(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("SPECIALE");
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)10);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("GLACE");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("GLACE");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("9/10");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("40");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return damagingMoveData_;
}
static MoveData m526(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("PHYSIQUE");
damagingMoveData_.setDirect(true);
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)20);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("NORMAL");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("NORMAL");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("3/4");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("80");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return damagingMoveData_;
}
static MoveData m527(){
StatusMoveData statusMoveData_ = Instances.newStatusMoveData();
statusMoveData_.setPp((short)10);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("TENEBRE");
statusMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("TENEBRE");
statusMoveData_.setBoostedTypes(stringList_);
statusMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(2));
enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)-1);
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)-1);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(2));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"VAR__CIBLE_CLONE>0");
enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,"VAR__CIBLE_CLONE>0");
effectStatistic_.setLocalFailStatis(enumMapStatisticString_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectStatistic_.setFail("");
custListEffect_.add(effectStatistic_);
statusMoveData_.setEffects(custListEffect_);
statusMoveData_.setStoppableMoveMulti(true);
statusMoveData_.setIgnVarAccurUserNeg(true);
statusMoveData_.setIgnVarEvasTargetPos(true);
statusMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return statusMoveData_;
}
static MoveData m528(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("SPECIALE");
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)5);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("DRAGON");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("DRAGON");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setChRate((byte)1);
effectDamage_.setPower("100");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return damagingMoveData_;
}
static MoveData m529(){
StatusMoveData statusMoveData_ = Instances.newStatusMoveData();
statusMoveData_.setCounterableMove(true);
statusMoveData_.setPp((short)15);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
statusMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
statusMoveData_.setBoostedTypes(stringList_);
statusMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatus effectStatus_=Instances.newEffectStatus();
MonteCarloString monteCarloString_=new MonteCarloString(new CollCapacity(1));
monteCarloString_.addQuickEvent("SOMMEIL",LgInt.newLgInt("1"));
effectStatus_.setLawStatus(monteCarloString_);
StringMap<String> stringMapString_=new StringMap<String>(new CollCapacity(1));
stringMapString_.addEntry("SOMMEIL","cardinal(inter({VAR__CIBLE_STATUTS},{SOMMEIL;SOMMEIL_REPOS}))>0|VAR__CIBLE_CLONE>0");
effectStatus_.setLocalFailStatus(stringMapString_);
effectStatus_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectStatus_.setFail("");
custListEffect_.add(effectStatus_);
statusMoveData_.setEffects(custListEffect_);
statusMoveData_.setStoppableMoveSolo(true);
statusMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return statusMoveData_;
}
static MoveData m530(){
StatusMoveData statusMoveData_ = Instances.newStatusMoveData();
statusMoveData_.setCounterableMove(true);
statusMoveData_.setPp((short)40);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
statusMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
statusMoveData_.setBoostedTypes(stringList_);
statusMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPEED,(byte)-2);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPEED,"VAR__CIBLE_CLONE>0");
effectStatistic_.setLocalFailStatis(enumMapStatisticString_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectStatistic_.setFail("");
custListEffect_.add(effectStatistic_);
statusMoveData_.setEffects(custListEffect_);
statusMoveData_.setStoppableMoveSolo(true);
statusMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return statusMoveData_;
}
static MoveData m531(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("PHYSIQUE");
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)30);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("GLACE");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("GLACE");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("25");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return damagingMoveData_;
}
static MoveData m532(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("PHYSIQUE");
damagingMoveData_.setDirect(true);
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)10);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("NORMAL");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("NORMAL");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("60*(cardinal(inter({VAR__CIBLE_STATUTS},{PARALYSIE}))*caracferme(VAR__CIBLE_CLONE,0,0)+1)");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
EffectStatus effectStatus_=Instances.newEffectStatus();
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PARALYSIE");
effectStatus_.setDeletedStatus(stringList_);
effectStatus_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectStatus_.setFail("");
Ints ints_=new Ints(new CollCapacity(1));
ints_.add(0);
effectStatus_.setRequiredSuccessfulEffects(ints_);
custListEffect_.add(effectStatus_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return damagingMoveData_;
}
static MoveData m533(){
StatusMoveData statusMoveData_ = Instances.newStatusMoveData();
statusMoveData_.setThievableMove(true);
statusMoveData_.setPp((short)10);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("NORMAL");
statusMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("NORMAL");
statusMoveData_.setBoostedTypes(stringList_);
statusMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(2));
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_DEFENSE,(byte)1);
enumMapStatisticByte_.addEntry(Statistic.DEFENSE,(byte)1);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
effectStatistic_.setFail("VAR__LANCEUR_NB_UTILISATION__STOCKAGE>3");
custListEffect_.add(effectStatistic_);
statusMoveData_.setEffects(custListEffect_);
statusMoveData_.setStoppableMoveMulti(true);
statusMoveData_.setIgnVarAccurUserNeg(true);
statusMoveData_.setIgnVarEvasTargetPos(true);
statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return statusMoveData_;
}
static MoveData m534(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("PHYSIQUE");
damagingMoveData_.setDirect(true);
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)15);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("COMBAT");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("COMBAT");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("9/10");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("85*(caracferme(inclusnum({VAR__CIBLE_ATTAQUE_CHOISIE},{ENVOL;REBOND;CHUTE_LIBRE})*VAR__CIBLE_DISPARAIT,1,1)+caracferme(VAR__CIBLE_DISPARAIT,0,0))");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
stringList_=new StringList(new CollCapacity(3));
stringList_.add("ENVOL");
stringList_.add("REBOND");
stringList_.add("CHUTE_LIBRE");
damagingMoveData_.setAchieveDisappearedPkUsingMove(stringList_);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return damagingMoveData_;
}
static MoveData m535(){
StatusMoveData statusMoveData_ = Instances.newStatusMoveData();
statusMoveData_.setCounterableMove(true);
statusMoveData_.setPp((short)35);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ACIER");
statusMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ACIER");
statusMoveData_.setBoostedTypes(stringList_);
statusMoveData_.setAccuracy("19/20");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_DEFENSE,(byte)-2);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_DEFENSE,"VAR__CIBLE_CLONE>0");
effectStatistic_.setLocalFailStatis(enumMapStatisticString_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.ADJ_MULT);
effectStatistic_.setFail("");
custListEffect_.add(effectStatistic_);
statusMoveData_.setEffects(custListEffect_);
statusMoveData_.setStoppableMoveSolo(true);
statusMoveData_.setStoppableMoveMulti(true);
statusMoveData_.setTargetChoice(TargetChoice.ADJ_MULT);
return statusMoveData_;
}
static MoveData m536(){
StatusMoveData statusMoveData_ = Instances.newStatusMoveData();
statusMoveData_.setCounterableMove(true);
statusMoveData_.setPp((short)10);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("POISON");
statusMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("POISON");
statusMoveData_.setBoostedTypes(stringList_);
statusMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectSwitchAbilities effectSwitchAbilities_=Instances.newEffectSwitchAbilities();
effectSwitchAbilities_.setExchangeAbility(ExchangeType.GIVE_CONST);
effectSwitchAbilities_.setConstAbility("");
effectSwitchAbilities_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
effectSwitchAbilities_.setFail("");
custListEffect_.add(effectSwitchAbilities_);
statusMoveData_.setEffects(custListEffect_);
statusMoveData_.setStoppableMoveSolo(true);
statusMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return statusMoveData_;
}
static MoveData m537(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("SPECIALE");
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)5);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("9/10");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("140");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)-2);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,"VAR__LANCEUR_CLONE>0");
effectStatistic_.setLocalFailStatis(enumMapStatisticString_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
effectStatistic_.setFail("");
Ints ints_=new Ints(new CollCapacity(1));
ints_.add(0);
effectStatistic_.setRequiredSuccessfulEffects(ints_);
custListEffect_.add(effectStatistic_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return damagingMoveData_;
}
static MoveData m538(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("SPECIALE");
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)15);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("EAU");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("95*(2*caracferme(inclusnum({VAR__CIBLE_ATTAQUE_CHOISIE},{PLONGEE})*VAR__CIBLE_DISPARAIT,1,1)+caracferme(VAR__CIBLE_DISPARAIT,0,0))");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_MULT);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setStoppableMoveMulti(true);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLONGEE");
damagingMoveData_.setAchieveDisappearedPkUsingMove(stringList_);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_MULT);
return damagingMoveData_;
}
static MoveData m539(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("PHYSIQUE");
damagingMoveData_.setDirect(true);
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)5);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("COMBAT");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("COMBAT");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("120");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(2));
enumMapStatisticByte_.addEntry(Statistic.ATTACK,(byte)-1);
enumMapStatisticByte_.addEntry(Statistic.DEFENSE,(byte)-1);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(2));
enumMapStatisticString_.addEntry(Statistic.ATTACK,"VAR__LANCEUR_CLONE>0");
enumMapStatisticString_.addEntry(Statistic.DEFENSE,"VAR__LANCEUR_CLONE>0");
effectStatistic_.setLocalFailStatis(enumMapStatisticString_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.LANCEUR);
effectStatistic_.setFail("");
Ints ints_=new Ints(new CollCapacity(1));
ints_.add(0);
effectStatistic_.setRequiredSuccessfulEffects(ints_);
custListEffect_.add(effectStatistic_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return damagingMoveData_;
}
static MoveData m540(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("SPECIALE");
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)20);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("INSECTE");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("INSECTE");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(2));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("30");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_ADV);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.SPECIAL_ATTACK,(byte)-1);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.SPECIAL_ATTACK,"VAR__CIBLE_CLONE>0");
effectStatistic_.setLocalFailStatis(enumMapStatisticString_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.ADJ_ADV);
effectStatistic_.setFail("");
Ints ints_=new Ints(new CollCapacity(1));
ints_.add(0);
effectStatistic_.setRequiredSuccessfulEffects(ints_);
custListEffect_.add(effectStatistic_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setStoppableMoveMulti(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_ADV);
return damagingMoveData_;
}
static MoveData m541(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("SPECIALE");
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)15);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PSY");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PSY");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("70");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_MULT);
effectDamage_.setFail("cardinal(inter({VAR__CIBLE_TYPES},{VAR__LANCEUR_TYPES}))=0");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setStoppableMoveMulti(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_MULT);
return damagingMoveData_;
}
static MoveData m542(){
StatusMoveData statusMoveData_ = Instances.newStatusMoveData();
statusMoveData_.setThievableMove(true);
statusMoveData_.setPp((short)5);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
statusMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PLANTE");
statusMoveData_.setBoostedTypes(stringList_);
statusMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectFullHpRate effectFullHpRate_=Instances.newEffectFullHpRate();
effectFullHpRate_.setRestoredHp("1/2*caracferme(cardinal({VAR__CLIMATS}),0,0)+caracdroiteferme(cardinal({VAR__CLIMATS}),1)*(2/3*cardinal(inter({VAR__CLIMATS},{ZENITH}))+1/5*cardinal(inter({VAR__CLIMATS},{ORAGE}))+1/4*cardinal(inter({VAR__CLIMATS},{GRELE;DANSE_PLUIE;TEMPETESABLE})))");
effectFullHpRate_.setTargetChoice(TargetChoice.LANCEUR);
effectFullHpRate_.setFail("");
custListEffect_.add(effectFullHpRate_);
statusMoveData_.setEffects(custListEffect_);
statusMoveData_.setStoppableMoveMulti(true);
statusMoveData_.setIgnVarAccurUserNeg(true);
statusMoveData_.setIgnVarEvasTargetPos(true);
statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return statusMoveData_;
}
static MoveData m543(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("PHYSIQUE");
damagingMoveData_.setDirect(true);
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)10);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("FEU");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("(min(max(ent(div(VAR__LANCEUR_MASSE,VAR__CIBLE_MASSE)),1),5)+1)*20");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.AUTRE_UNIQ);
return damagingMoveData_;
}
static MoveData m544(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("PHYSIQUE");
damagingMoveData_.setDirect(true);
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)10);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("ACIER");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("ACIER");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("(min(max(ent(div(VAR__LANCEUR_MASSE,VAR__CIBLE_MASSE)),1),5)+1)*20");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return damagingMoveData_;
}
static MoveData m545(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("PHYSIQUE");
damagingMoveData_.setDirect(true);
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)20);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("INSECTE");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("INSECTE");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("19/20");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("puis(2,min(VAR__LANCEUR_NB_UTILISATION__TAILLADE,3))*20");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return damagingMoveData_;
}
static MoveData m546(){
StatusMoveData statusMoveData_ = Instances.newStatusMoveData();
statusMoveData_.setPp((short)10);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("COMBAT");
statusMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("COMBAT");
statusMoveData_.setBoostedTypes(stringList_);
statusMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectProtection effectProtection_=Instances.newEffectProtection();
effectProtection_.setProtTeamAgainstDamageMoves(true);
effectProtection_.setTargetChoice(TargetChoice.LANCEUR);
effectProtection_.setFail("");
custListEffect_.add(effectProtection_);
statusMoveData_.setEffects(custListEffect_);
statusMoveData_.setTargetChoice(TargetChoice.LANCEUR);
return statusMoveData_;
}
static MoveData m547(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("SPECIALE");
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)5);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("NORMAL");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("NORMAL");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("85");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.SPECIAL_ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.SPECIAL_DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveSolo(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return damagingMoveData_;
}
static MoveData m548(){
StatusMoveData statusMoveData_ = Instances.newStatusMoveData();
statusMoveData_.setCounterableMove(true);
statusMoveData_.setPp((short)15);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PSY");
statusMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PSY");
statusMoveData_.setBoostedTypes(stringList_);
statusMoveData_.setAccuracy("4/5");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectStatistic effectStatistic_=Instances.newEffectStatistic();
EnumMap<Statistic,Byte> enumMapStatisticByte_=new EnumMap<Statistic,Byte>(new CollCapacity(1));
enumMapStatisticByte_.addEntry(Statistic.ACCURACY,(byte)-1);
effectStatistic_.setStatisVarRank(enumMapStatisticByte_);
EnumMap<Statistic,String> enumMapStatisticString_=new EnumMap<Statistic,String>(new CollCapacity(1));
enumMapStatisticString_.addEntry(Statistic.ACCURACY,"VAR__CIBLE_CLONE>0");
effectStatistic_.setLocalFailStatis(enumMapStatisticString_);
effectStatistic_.setEvtRate(Rate.newRate("1"));
effectStatistic_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectStatistic_.setFail("");
custListEffect_.add(effectStatistic_);
statusMoveData_.setEffects(custListEffect_);
statusMoveData_.setStoppableMoveSolo(true);
statusMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return statusMoveData_;
}
static MoveData m549(){
DamagingMoveData damagingMoveData_ = Instances.newDamagingMoveData();
damagingMoveData_.setCategory("SPECIALE");
damagingMoveData_.setDirect(true);
damagingMoveData_.setStoppableMoveKoSingle(true);
damagingMoveData_.setPp((short)20);
StringList stringList_=new StringList(new CollCapacity(1));
stringList_.add("PSY");
damagingMoveData_.setTypes(stringList_);
stringList_=new StringList(new CollCapacity(1));
stringList_.add("PSY");
damagingMoveData_.setBoostedTypes(stringList_);
damagingMoveData_.setAccuracy("1");
CustList<Effect> custListEffect_ = new CustList<Effect>(new CollCapacity(1));
EffectDamage effectDamage_=Instances.newEffectDamage();
effectDamage_.setPower("70");
effectDamage_.setUserAttack(true);
effectDamage_.setStatisAtt(Statistic.ATTACK);
effectDamage_.setTargetDefense(true);
effectDamage_.setStatisDef(Statistic.DEFENSE);
effectDamage_.setTargetChoice(TargetChoice.ADJ_UNIQ);
effectDamage_.setFail("");
custListEffect_.add(effectDamage_);
damagingMoveData_.setEffects(custListEffect_);
damagingMoveData_.setStoppableMoveMulti(true);
damagingMoveData_.setIgnVarAccurUserNeg(true);
damagingMoveData_.setIgnVarEvasTargetPos(true);
damagingMoveData_.setTargetChoice(TargetChoice.ADJ_UNIQ);
return damagingMoveData_;
}
}
