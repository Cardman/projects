package aiki.sml.trs;
import aiki.db.*;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsStatistics extends CstIgame{
private MessagesTrsStatistics(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(10);
e.add(DataBase.DEF_STAT_ACCURACY,"ACCURACY");
e.add(DataBase.DEF_STAT_PV_RESTANTS,"LEFT_HP");
e.add(DataBase.DEF_STAT_EVASINESS,"EVASINESS");
e.add(DataBase.DEF_STAT_ATTACK,"ATTACK");
e.add(DataBase.DEF_STAT_SPECIAL_ATTACK,"SPECIAL_ATTACK");
e.add(DataBase.DEF_STAT_SPECIAL_DEFENSE,"SPECIAL_DEFENSE");
e.add(DataBase.DEF_STAT_HP,"HP");
e.add(DataBase.DEF_STAT_CRITICAL_HIT,"CRITICAL_HIT");
e.add(DataBase.DEF_STAT_DEFENSE,"DEFENSE");
e.add(DataBase.DEF_STAT_SPEED,"SPEED");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(10);
f.add(DataBase.DEF_STAT_ACCURACY,"PRECISION");
f.add(DataBase.DEF_STAT_PV_RESTANTS,"PV_RESTANTS");
f.add(DataBase.DEF_STAT_EVASINESS,"ESQUIVE");
f.add(DataBase.DEF_STAT_ATTACK,"ATTAQUE");
f.add(DataBase.DEF_STAT_SPECIAL_ATTACK,"ATTAQUE_SPECIALE");
f.add(DataBase.DEF_STAT_SPECIAL_DEFENSE,"DEFENSE_SPECIALE");
f.add(DataBase.DEF_STAT_HP,"PV");
f.add(DataBase.DEF_STAT_CRITICAL_HIT,"COUP_CRITIQUE");
f.add(DataBase.DEF_STAT_DEFENSE,"DEFENSE");
f.add(DataBase.DEF_STAT_SPEED,"VITESSE");
return f;
}
}
