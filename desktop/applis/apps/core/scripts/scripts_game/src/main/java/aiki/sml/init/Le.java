package aiki.sml.init;
import aiki.instances.*;
import aiki.fight.util.*;
import aiki.fight.*;
import aiki.fight.effects.*;
import aiki.fight.moves.effects.*;
import aiki.fight.status.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.status.*;
import aiki.fight.enums.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
import aiki.map.pokemon.*;
import aiki.map.levels.*;
import aiki.map.pokemon.enums.*;
public final class Le extends CstIgame{
private Le(){}
public static WildPk p11l0w0(){
WildPk m492wildPk_=Instances.newWildPk();
m492wildPk_.setName(I_KYOGRE);
m492wildPk_.setLevel((short)50);
m492wildPk_.setGender(Gender.NO_GENDER);
m492wildPk_.setAbility(I_CRACHIN);
return m492wildPk_;
}
public static WildPk p11l0w1(){
WildPk m493wildPk_=Instances.newWildPk();
m493wildPk_.setName(I_MEWTWO);
m493wildPk_.setLevel((short)50);
m493wildPk_.setGender(Gender.NO_GENDER);
m493wildPk_.setAbility(I_PRESSION);
return m493wildPk_;
}
public static WildPk p11l0w2(){
WildPk m494wildPk_=Instances.newWildPk();
m494wildPk_.setName(I_RAIKOU);
m494wildPk_.setLevel((short)50);
m494wildPk_.setGender(Gender.NO_GENDER);
m494wildPk_.setAbility(I_PRESSION);
return m494wildPk_;
}
public static WildPk p11l0w3(){
WildPk m495wildPk_=Instances.newWildPk();
m495wildPk_.setName(I_MEW);
m495wildPk_.setLevel((short)50);
m495wildPk_.setGender(Gender.NO_GENDER);
m495wildPk_.setAbility(I_SYNCHRO);
return m495wildPk_;
}
public static WildPk p11l0w4(){
WildPk m496wildPk_=Instances.newWildPk();
m496wildPk_.setName(I_LATIAS);
m496wildPk_.setLevel((short)50);
m496wildPk_.setGender(Gender.NO_GENDER);
m496wildPk_.setAbility(I_LEVITATION);
return m496wildPk_;
}
public static WildPk p11l0w5(){
WildPk m497wildPk_=Instances.newWildPk();
m497wildPk_.setName(I_CELEBI);
m497wildPk_.setLevel((short)50);
m497wildPk_.setGender(Gender.NO_GENDER);
m497wildPk_.setAbility(I_MEDIC_NATURE);
return m497wildPk_;
}
public static WildPk p11l0w6(){
WildPk m498wildPk_=Instances.newWildPk();
m498wildPk_.setName(I_ELECTHOR);
m498wildPk_.setLevel((short)50);
m498wildPk_.setGender(Gender.NO_GENDER);
m498wildPk_.setAbility(I_PRESSION);
return m498wildPk_;
}
public static WildPk p11l0w7(){
WildPk m499wildPk_=Instances.newWildPk();
m499wildPk_.setName(I_REGICE);
m499wildPk_.setLevel((short)50);
m499wildPk_.setGender(Gender.NO_GENDER);
m499wildPk_.setAbility(I_CORPS_SAIN);
return m499wildPk_;
}
public static WildPk p11l0w8(){
WildPk m500wildPk_=Instances.newWildPk();
m500wildPk_.setName(I_ARTIKODIN);
m500wildPk_.setLevel((short)50);
m500wildPk_.setGender(Gender.NO_GENDER);
m500wildPk_.setAbility(I_PRESSION);
return m500wildPk_;
}
public static WildPk p11l0w9(){
WildPk m501wildPk_=Instances.newWildPk();
m501wildPk_.setName(I_ENTEI);
m501wildPk_.setLevel((short)50);
m501wildPk_.setGender(Gender.NO_GENDER);
m501wildPk_.setAbility(I_PRESSION);
return m501wildPk_;
}
public static WildPk p11l0w10(){
WildPk m502wildPk_=Instances.newWildPk();
m502wildPk_.setName(I_REGIROCK);
m502wildPk_.setLevel((short)50);
m502wildPk_.setGender(Gender.NO_GENDER);
m502wildPk_.setAbility(I_CORPS_SAIN);
return m502wildPk_;
}
public static WildPk p11l0w11(){
WildPk m503wildPk_=Instances.newWildPk();
m503wildPk_.setName(I_HO_OH);
m503wildPk_.setLevel((short)50);
m503wildPk_.setGender(Gender.NO_GENDER);
m503wildPk_.setAbility(I_PRESSION);
return m503wildPk_;
}
public static WildPk p11l0w12(){
WildPk m504wildPk_=Instances.newWildPk();
m504wildPk_.setName(I_SUICUNE);
m504wildPk_.setLevel((short)50);
m504wildPk_.setGender(Gender.NO_GENDER);
m504wildPk_.setAbility(I_PRESSION);
return m504wildPk_;
}
public static WildPk p11l0w13(){
WildPk m505wildPk_=Instances.newWildPk();
m505wildPk_.setName(I_SULFURA);
m505wildPk_.setLevel((short)50);
m505wildPk_.setGender(Gender.NO_GENDER);
m505wildPk_.setAbility(I_PRESSION);
return m505wildPk_;
}
public static WildPk p11l0w14(){
WildPk m506wildPk_=Instances.newWildPk();
m506wildPk_.setName(I_REGISTEEL);
m506wildPk_.setLevel((short)50);
m506wildPk_.setGender(Gender.NO_GENDER);
m506wildPk_.setAbility(I_CORPS_SAIN);
return m506wildPk_;
}
public static WildPk p11l0w15(){
WildPk m507wildPk_=Instances.newWildPk();
m507wildPk_.setName(I_JIRACHI);
m507wildPk_.setLevel((short)50);
m507wildPk_.setGender(Gender.NO_GENDER);
m507wildPk_.setAbility(I_SERENITE);
return m507wildPk_;
}
public static WildPk p11l0w16(){
WildPk m508wildPk_=Instances.newWildPk();
m508wildPk_.setName(I_LUGIA);
m508wildPk_.setLevel((short)50);
m508wildPk_.setGender(Gender.NO_GENDER);
m508wildPk_.setAbility(I_PRESSION);
return m508wildPk_;
}
public static WildPk p11l0w17(){
WildPk m509wildPk_=Instances.newWildPk();
m509wildPk_.setName(I_RAYQUAZA);
m509wildPk_.setLevel((short)50);
m509wildPk_.setGender(Gender.NO_GENDER);
m509wildPk_.setAbility(I_AIR_LOCK);
return m509wildPk_;
}
public static WildPk p11l0w18(){
WildPk m510wildPk_=Instances.newWildPk();
m510wildPk_.setName(I_REGIGIGAS);
m510wildPk_.setLevel((short)50);
m510wildPk_.setGender(Gender.NO_GENDER);
m510wildPk_.setAbility(I_ADAPTABILITE);
return m510wildPk_;
}
public static WildPk p11l0w19(){
WildPk m511wildPk_=Instances.newWildPk();
m511wildPk_.setName(I_DEOXYS);
m511wildPk_.setLevel((short)50);
m511wildPk_.setGender(Gender.NO_GENDER);
m511wildPk_.setAbility(I_PRESSION);
return m511wildPk_;
}
public static WildPk p11l0w20(){
WildPk m512wildPk_=Instances.newWildPk();
m512wildPk_.setName(I_LATIOS);
m512wildPk_.setLevel((short)50);
m512wildPk_.setGender(Gender.NO_GENDER);
m512wildPk_.setAbility(I_LEVITATION);
return m512wildPk_;
}
public static WildPk p11l0w21(){
WildPk m513wildPk_=Instances.newWildPk();
m513wildPk_.setName(I_GROUDON);
m513wildPk_.setLevel((short)50);
m513wildPk_.setGender(Gender.NO_GENDER);
m513wildPk_.setAbility(I_SECHERESSE);
return m513wildPk_;
}
}
