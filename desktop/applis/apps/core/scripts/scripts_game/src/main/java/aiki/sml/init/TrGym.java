package aiki.sml.init;
import aiki.map.characters.*;
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
public final class TrGym extends CstIgame{
private TrGym(){}
public static GymTrainer p0t0(){
GymTrainer m2041gymTrainer_=Instances.newGymTrainer();
m2041gymTrainer_.setReward((short)1000);
CustList<PkTrainer> m2041custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer m2041pkTrainer_=Instances.newPkTrainer();
m2041pkTrainer_.setName(I_RATTATA);
m2041pkTrainer_.setLevel((short)2);
m2041pkTrainer_.setGender(Gender.NO_GENDER);
m2041pkTrainer_.setAbility(I_CRAN);
m2041pkTrainer_.setMoves(new StringList(I_DERNIERECOUR,I_DAMOCLES,I_LASER_GLACE,I_TONNERRE));
m2041custListPkTrainer_.add(m2041pkTrainer_);
m2041gymTrainer_.setTeam(m2041custListPkTrainer_);
m2041gymTrainer_.setMultiplicityFight((byte)1);
m2041gymTrainer_.setImageMaxiFileName(F_TRAINER_F_TXT);
m2041gymTrainer_.setImageMiniFileName(F_TRAINER_F_TXT);
return m2041gymTrainer_;
}
public static GymLeader p0(){
GymLeader m2042gymLeader_=Instances.newGymLeader();
m2042gymLeader_.setTm((short)8);
m2042gymLeader_.setName(I_CONSTANT);
m2042gymLeader_.setReward((short)2000);
CustList<PkTrainer> m2042custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(2));
PkTrainer m2042pkTrainer_=Instances.newPkTrainer();
m2042pkTrainer_.setName(I_EXCELANGUE);
m2042pkTrainer_.setLevel((short)5);
m2042pkTrainer_.setGender(Gender.MALE);
m2042pkTrainer_.setAbility(I_TEMPO_PERSO);
m2042pkTrainer_.setMoves(new StringList(I_DAMOCLES,I_DEVOREVE,I_LASER_GLACE,I_TONNERRE));
m2042custListPkTrainer_.add(m2042pkTrainer_);
m2042pkTrainer_=Instances.newPkTrainer();
m2042pkTrainer_.setName(I_PORYGON);
m2042pkTrainer_.setLevel((short)6);
m2042pkTrainer_.setGender(Gender.NO_GENDER);
m2042pkTrainer_.setAbility(I_TELECHARGE);
m2042pkTrainer_.setMoves(new StringList(I_DERNIERECOUR,I_DAMOCLES,I_DEVOREVE,I_TRICHERIE));
m2042custListPkTrainer_.add(m2042pkTrainer_);
m2042gymLeader_.setTeam(m2042custListPkTrainer_);
m2042gymLeader_.setMultiplicityFight((byte)1);
m2042gymLeader_.setImageMaxiFileName(F_CONSTANT_TXT);
m2042gymLeader_.setImageMiniFileName(F_CONSTANT_TXT);
return m2042gymLeader_;
}
public static GymTrainer p2t0(){
GymTrainer m2043gymTrainer_=Instances.newGymTrainer();
m2043gymTrainer_.setReward((short)1000);
CustList<PkTrainer> m2043custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer m2043pkTrainer_=Instances.newPkTrainer();
m2043pkTrainer_.setName(I_RATTATA);
m2043pkTrainer_.setLevel((short)2);
m2043pkTrainer_.setGender(Gender.NO_GENDER);
m2043pkTrainer_.setAbility(I_CRAN);
m2043pkTrainer_.setMoves(new StringList(I_DERNIERECOUR,I_DAMOCLES,I_LASER_GLACE,I_TONNERRE));
m2043custListPkTrainer_.add(m2043pkTrainer_);
m2043gymTrainer_.setTeam(m2043custListPkTrainer_);
m2043gymTrainer_.setMultiplicityFight((byte)1);
m2043gymTrainer_.setImageMaxiFileName(F_TRAINER_M_TXT);
m2043gymTrainer_.setImageMiniFileName(F_TRAINER_M_TXT);
return m2043gymTrainer_;
}
public static GymTrainer p2t1(){
GymTrainer m2044gymTrainer_=Instances.newGymTrainer();
m2044gymTrainer_.setReward((short)1000);
CustList<PkTrainer> m2044custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer m2044pkTrainer_=Instances.newPkTrainer();
m2044pkTrainer_.setName(I_RATTATA);
m2044pkTrainer_.setLevel((short)2);
m2044pkTrainer_.setGender(Gender.NO_GENDER);
m2044pkTrainer_.setAbility(I_CRAN);
m2044pkTrainer_.setMoves(new StringList(I_DERNIERECOUR,I_DAMOCLES,I_LASER_GLACE,I_TONNERRE));
m2044custListPkTrainer_.add(m2044pkTrainer_);
m2044gymTrainer_.setTeam(m2044custListPkTrainer_);
m2044gymTrainer_.setMultiplicityFight((byte)1);
m2044gymTrainer_.setImageMaxiFileName(F_TRAINER_F_TXT);
m2044gymTrainer_.setImageMiniFileName(F_TRAINER_F_TXT);
return m2044gymTrainer_;
}
public static GymLeader p2(){
GymLeader m2045gymLeader_=Instances.newGymLeader();
m2045gymLeader_.setTm((short)7);
m2045gymLeader_.setName(I_ADRIANA);
m2045gymLeader_.setReward((short)2000);
CustList<PkTrainer> m2045custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(2));
PkTrainer m2045pkTrainer_=Instances.newPkTrainer();
m2045pkTrainer_.setName(I_ARKEAPTI);
m2045pkTrainer_.setLevel((short)14);
m2045pkTrainer_.setGender(Gender.FEMALE);
m2045pkTrainer_.setAbility(I_REGARD_VIF);
m2045pkTrainer_.setMoves(new StringList(I_DRACOCHOC,I_TELLURIFORCE,I_BROUHAHA,I_PUIS_CACHEE));
m2045custListPkTrainer_.add(m2045pkTrainer_);
m2045pkTrainer_=Instances.newPkTrainer();
m2045pkTrainer_.setName(I_CORNEBRE);
m2045pkTrainer_.setLevel((short)16);
m2045pkTrainer_.setGender(Gender.FEMALE);
m2045pkTrainer_.setAbility(I_INSOMNIA);
m2045pkTrainer_.setMoves(new StringList(I_RAPACE,I_DAMOCLES,I_DEVOREVE,I_TRICHERIE));
m2045custListPkTrainer_.add(m2045pkTrainer_);
m2045gymLeader_.setTeam(m2045custListPkTrainer_);
m2045gymLeader_.setMultiplicityFight((byte)1);
m2045gymLeader_.setImageMaxiFileName(F_ADRIANA_TXT);
m2045gymLeader_.setImageMiniFileName(F_ADRIANA_TXT);
return m2045gymLeader_;
}
public static GymTrainer p4t0(){
GymTrainer m2046gymTrainer_=Instances.newGymTrainer();
m2046gymTrainer_.setReward((short)1000);
CustList<PkTrainer> m2046custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer m2046pkTrainer_=Instances.newPkTrainer();
m2046pkTrainer_.setName(I_RATTATA);
m2046pkTrainer_.setLevel((short)2);
m2046pkTrainer_.setGender(Gender.NO_GENDER);
m2046pkTrainer_.setAbility(I_CRAN);
m2046pkTrainer_.setMoves(new StringList(I_DERNIERECOUR,I_DAMOCLES,I_LASER_GLACE,I_TONNERRE));
m2046custListPkTrainer_.add(m2046pkTrainer_);
m2046gymTrainer_.setTeam(m2046custListPkTrainer_);
m2046gymTrainer_.setMultiplicityFight((byte)1);
m2046gymTrainer_.setImageMaxiFileName(F_TRAINER_F_TXT);
m2046gymTrainer_.setImageMiniFileName(F_TRAINER_F_TXT);
return m2046gymTrainer_;
}
public static GymTrainer p4t1(){
GymTrainer m2047gymTrainer_=Instances.newGymTrainer();
m2047gymTrainer_.setReward((short)1000);
CustList<PkTrainer> m2047custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer m2047pkTrainer_=Instances.newPkTrainer();
m2047pkTrainer_.setName(I_RATTATA);
m2047pkTrainer_.setLevel((short)2);
m2047pkTrainer_.setGender(Gender.NO_GENDER);
m2047pkTrainer_.setAbility(I_CRAN);
m2047pkTrainer_.setMoves(new StringList(I_DERNIERECOUR,I_DAMOCLES,I_LASER_GLACE,I_TONNERRE));
m2047custListPkTrainer_.add(m2047pkTrainer_);
m2047gymTrainer_.setTeam(m2047custListPkTrainer_);
m2047gymTrainer_.setMultiplicityFight((byte)1);
m2047gymTrainer_.setImageMaxiFileName(F_TRAINER_M_TXT);
m2047gymTrainer_.setImageMiniFileName(F_TRAINER_M_TXT);
return m2047gymTrainer_;
}
public static GymLeader p4(){
GymLeader m2048gymLeader_=Instances.newGymLeader();
m2048gymLeader_.setTm((short)6);
m2048gymLeader_.setName(I_ALISE);
m2048gymLeader_.setReward((short)4000);
CustList<PkTrainer> m2048custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(3));
PkTrainer m2048pkTrainer_=Instances.newPkTrainer();
m2048pkTrainer_.setName(I_PHANPY);
m2048pkTrainer_.setLevel((short)22);
m2048pkTrainer_.setGender(Gender.MALE);
m2048pkTrainer_.setAbility(I_FERMETE);
m2048pkTrainer_.setMoves(new StringList(I_DERNIERECOUR,I_DAMOCLES,I_SURPUISSANCE,I_TELLURIFORCE));
m2048custListPkTrainer_.add(m2048pkTrainer_);
m2048pkTrainer_=Instances.newPkTrainer();
m2048pkTrainer_.setName(I_SABELETTE);
m2048pkTrainer_.setLevel((short)24);
m2048pkTrainer_.setGender(Gender.MALE);
m2048pkTrainer_.setAbility(I_FERMETE);
m2048pkTrainer_.setMoves(new StringList(I_DAMOCLES,I_TELLURIFORCE,I_PUIS_CACHEE,I_PLAQUAGE));
m2048custListPkTrainer_.add(m2048pkTrainer_);
m2048pkTrainer_=Instances.newPkTrainer();
m2048pkTrainer_.setName(I_KRAKNOIX);
m2048pkTrainer_.setLevel((short)25);
m2048pkTrainer_.setGender(Gender.MALE);
m2048pkTrainer_.setAbility(I_HYPER_CUTTER);
m2048pkTrainer_.setMoves(new StringList(I_DAMOCLES,I_SURPUISSANCE,I_TELLURIFORCE,I_PUIS_CACHEE));
m2048custListPkTrainer_.add(m2048pkTrainer_);
m2048gymLeader_.setTeam(m2048custListPkTrainer_);
m2048gymLeader_.setMultiplicityFight((byte)1);
m2048gymLeader_.setImageMaxiFileName(F_ALISE_TXT);
m2048gymLeader_.setImageMiniFileName(F_ALISE_TXT);
return m2048gymLeader_;
}
}
