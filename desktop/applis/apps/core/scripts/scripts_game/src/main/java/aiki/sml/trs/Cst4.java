package aiki.sml.trs;
import aiki.util.*;
import code.util.*;
import code.maths.*;
import code.maths.montecarlo.*;
import aiki.game.params.enums.*;
import aiki.sml.init.CstIgame;
public final class Cst4 extends CstIgame{
static final String CS_DECROISSANT="DECROISSANT";
static final String CS_CROISSANT="CROISSANT";
static final String CS_UNIFORME="UNIFORME";
static final String CS_CONSTANT_MAX="CONSTANT_MAX";
static final String CS_CONSTANT_MIN="CONSTANT_MIN";
private static final String R_22_25="22/25";
private static final String R_23_25="23/25";
private static final String R_24_25="24/25";
private static final String R_43_50="43/50";
private static final String R_47_50="47/50";
private static final String R_49_50="49/50";
private static final String R_87_100="87/100";
private static final String R_89_100="89/100";
private static final String R_91_100="91/100";
private static final String R_93_100="93/100";
private static final String R_97_100="97/100";
private static final String R_99_100="99/100";
private Cst4(){}
static IdMap<DifficultyModelLaw,LawNumber> lg(){
IdMap<DifficultyModelLaw,LawNumber> l=new IdMap<DifficultyModelLaw,LawNumber>(new CollCapacity(5));
l.addEntry(DifficultyModelLaw.DECROISSANT,new LawNumber(l3(),(short)2));
l.addEntry(DifficultyModelLaw.CROISSANT,new LawNumber(l1(),(short)4));
l.addEntry(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(l4(),(short)5));
l.addEntry(DifficultyModelLaw.UNIFORME,new LawNumber(l2(),(short)3));
l.addEntry(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(l0(),(short)1));
return l;
}
static MonteCarloNumber l3(){
MonteCarloNumber l=new MonteCarloNumber(new CollCapacity(16));
l.addQuickEvent(Rate.newRate(R_49_50),LgInt.newLgInt(R_3));
l.addQuickEvent(Rate.newRate(R_93_100),LgInt.newLgInt(R_8));
l.addQuickEvent(Rate.newRate(R_1),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_47_50),LgInt.newLgInt(R_7));
l.addQuickEvent(Rate.newRate(R_9_10),LgInt.newLgInt(R_11));
l.addQuickEvent(Rate.newRate(R_23_25),LgInt.newLgInt(R_9));
l.addQuickEvent(Rate.newRate(R_97_100),LgInt.newLgInt(R_4));
l.addQuickEvent(Rate.newRate(R_99_100),LgInt.newLgInt(R_2));
l.addQuickEvent(Rate.newRate(R_19_20),LgInt.newLgInt(R_6));
l.addQuickEvent(Rate.newRate(R_89_100),LgInt.newLgInt(R_12));
l.addQuickEvent(Rate.newRate(R_91_100),LgInt.newLgInt(R_10));
l.addQuickEvent(Rate.newRate(R_17_20),LgInt.newLgInt(R_16));
l.addQuickEvent(Rate.newRate(R_87_100),LgInt.newLgInt(R_14));
l.addQuickEvent(Rate.newRate(R_43_50),LgInt.newLgInt(R_15));
l.addQuickEvent(Rate.newRate(R_22_25),LgInt.newLgInt(R_13));
l.addQuickEvent(Rate.newRate(R_24_25),LgInt.newLgInt(R_5));
return l;
}
static MonteCarloNumber l1(){
MonteCarloNumber l=new MonteCarloNumber(new CollCapacity(16));
l.addQuickEvent(Rate.newRate(R_43_50),LgInt.newLgInt(R_2));
l.addQuickEvent(Rate.newRate(R_47_50),LgInt.newLgInt(R_10));
l.addQuickEvent(Rate.newRate(R_22_25),LgInt.newLgInt(R_4));
l.addQuickEvent(Rate.newRate(R_19_20),LgInt.newLgInt(R_11));
l.addQuickEvent(Rate.newRate(R_9_10),LgInt.newLgInt(R_6));
l.addQuickEvent(Rate.newRate(R_87_100),LgInt.newLgInt(R_3));
l.addQuickEvent(Rate.newRate(R_91_100),LgInt.newLgInt(R_7));
l.addQuickEvent(Rate.newRate(R_23_25),LgInt.newLgInt(R_8));
l.addQuickEvent(Rate.newRate(R_89_100),LgInt.newLgInt(R_5));
l.addQuickEvent(Rate.newRate(R_1),LgInt.newLgInt(R_16));
l.addQuickEvent(Rate.newRate(R_99_100),LgInt.newLgInt(R_15));
l.addQuickEvent(Rate.newRate(R_24_25),LgInt.newLgInt(R_12));
l.addQuickEvent(Rate.newRate(R_97_100),LgInt.newLgInt(R_13));
l.addQuickEvent(Rate.newRate(R_17_20),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_49_50),LgInt.newLgInt(R_14));
l.addQuickEvent(Rate.newRate(R_93_100),LgInt.newLgInt(R_9));
return l;
}
static MonteCarloNumber l4(){
MonteCarloNumber l=new MonteCarloNumber(new CollCapacity(1));
l.addQuickEvent(Rate.newRate(R_1),LgInt.newLgInt(R_1));
return l;
}
static MonteCarloNumber l2(){
MonteCarloNumber l=new MonteCarloNumber(new CollCapacity(16));
l.addQuickEvent(Rate.newRate(R_89_100),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_22_25),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_91_100),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_47_50),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_23_25),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_19_20),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_17_20),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_87_100),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_49_50),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_24_25),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_43_50),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_97_100),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_99_100),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_93_100),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_1),LgInt.newLgInt(R_1));
l.addQuickEvent(Rate.newRate(R_9_10),LgInt.newLgInt(R_1));
return l;
}
static MonteCarloNumber l0(){
MonteCarloNumber l=new MonteCarloNumber(new CollCapacity(1));
l.addQuickEvent(Rate.newRate(R_17_20),LgInt.newLgInt(R_1));
return l;
}
}
