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
public final class TrGym{
private TrGym(){}
public static GymTrainer p0t0(){
GymTrainer gymTrainer_=Instances.newGymTrainer();
gymTrainer_.setReward((short)1000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("RATTATA");
pkTrainer_.setLevel((short)2);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("CRAN");
pkTrainer_.setMoves(new StringList("DERNIERECOUR","DAMOCLES","LASER_GLACE","TONNERRE"));
custListPkTrainer_.add(pkTrainer_);
gymTrainer_.setTeam(custListPkTrainer_);
gymTrainer_.setMultiplicityFight((byte)1);
gymTrainer_.setImageMaxiFileName("TRAINER_F.txt");
gymTrainer_.setImageMiniFileName("TRAINER_F.txt");
return gymTrainer_;
}
public static GymLeader p0(){
GymLeader gymLeader_=Instances.newGymLeader();
gymLeader_.setTm((short)8);
gymLeader_.setName("CONSTANT");
gymLeader_.setReward((short)2000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(2));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("EXCELANGUE");
pkTrainer_.setLevel((short)5);
pkTrainer_.setGender(Gender.MALE);
pkTrainer_.setAbility("TEMPO_PERSO");
pkTrainer_.setMoves(new StringList("DAMOCLES","DEVOREVE","LASER_GLACE","TONNERRE"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("PORYGON");
pkTrainer_.setLevel((short)6);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("TELECHARGE");
pkTrainer_.setMoves(new StringList("DERNIERECOUR","DAMOCLES","DEVOREVE","TRICHERIE"));
custListPkTrainer_.add(pkTrainer_);
gymLeader_.setTeam(custListPkTrainer_);
gymLeader_.setMultiplicityFight((byte)1);
gymLeader_.setImageMaxiFileName("CONSTANT.txt");
gymLeader_.setImageMiniFileName("CONSTANT.txt");
return gymLeader_;
}
public static GymTrainer p2t0(){
GymTrainer gymTrainer_=Instances.newGymTrainer();
gymTrainer_.setReward((short)1000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("RATTATA");
pkTrainer_.setLevel((short)2);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("CRAN");
pkTrainer_.setMoves(new StringList("DERNIERECOUR","DAMOCLES","LASER_GLACE","TONNERRE"));
custListPkTrainer_.add(pkTrainer_);
gymTrainer_.setTeam(custListPkTrainer_);
gymTrainer_.setMultiplicityFight((byte)1);
gymTrainer_.setImageMaxiFileName("TRAINER_M.txt");
gymTrainer_.setImageMiniFileName("TRAINER_M.txt");
return gymTrainer_;
}
public static GymTrainer p2t1(){
GymTrainer gymTrainer_=Instances.newGymTrainer();
gymTrainer_.setReward((short)1000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("RATTATA");
pkTrainer_.setLevel((short)2);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("CRAN");
pkTrainer_.setMoves(new StringList("DERNIERECOUR","DAMOCLES","LASER_GLACE","TONNERRE"));
custListPkTrainer_.add(pkTrainer_);
gymTrainer_.setTeam(custListPkTrainer_);
gymTrainer_.setMultiplicityFight((byte)1);
gymTrainer_.setImageMaxiFileName("TRAINER_F.txt");
gymTrainer_.setImageMiniFileName("TRAINER_F.txt");
return gymTrainer_;
}
public static GymLeader p2(){
GymLeader gymLeader_=Instances.newGymLeader();
gymLeader_.setTm((short)7);
gymLeader_.setName("ADRIANA");
gymLeader_.setReward((short)2000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(2));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("ARKEAPTI");
pkTrainer_.setLevel((short)14);
pkTrainer_.setGender(Gender.FEMALE);
pkTrainer_.setAbility("REGARD_VIF");
pkTrainer_.setMoves(new StringList("DRACOCHOC","TELLURIFORCE","BROUHAHA","PUIS_CACHEE"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("CORNEBRE");
pkTrainer_.setLevel((short)16);
pkTrainer_.setGender(Gender.FEMALE);
pkTrainer_.setAbility("INSOMNIA");
pkTrainer_.setMoves(new StringList("RAPACE","DAMOCLES","DEVOREVE","TRICHERIE"));
custListPkTrainer_.add(pkTrainer_);
gymLeader_.setTeam(custListPkTrainer_);
gymLeader_.setMultiplicityFight((byte)1);
gymLeader_.setImageMaxiFileName("ADRIANA.txt");
gymLeader_.setImageMiniFileName("ADRIANA.txt");
return gymLeader_;
}
public static GymTrainer p4t0(){
GymTrainer gymTrainer_=Instances.newGymTrainer();
gymTrainer_.setReward((short)1000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("RATTATA");
pkTrainer_.setLevel((short)2);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("CRAN");
pkTrainer_.setMoves(new StringList("DERNIERECOUR","DAMOCLES","LASER_GLACE","TONNERRE"));
custListPkTrainer_.add(pkTrainer_);
gymTrainer_.setTeam(custListPkTrainer_);
gymTrainer_.setMultiplicityFight((byte)1);
gymTrainer_.setImageMaxiFileName("TRAINER_F.txt");
gymTrainer_.setImageMiniFileName("TRAINER_F.txt");
return gymTrainer_;
}
public static GymTrainer p4t1(){
GymTrainer gymTrainer_=Instances.newGymTrainer();
gymTrainer_.setReward((short)1000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(1));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("RATTATA");
pkTrainer_.setLevel((short)2);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("CRAN");
pkTrainer_.setMoves(new StringList("DERNIERECOUR","DAMOCLES","LASER_GLACE","TONNERRE"));
custListPkTrainer_.add(pkTrainer_);
gymTrainer_.setTeam(custListPkTrainer_);
gymTrainer_.setMultiplicityFight((byte)1);
gymTrainer_.setImageMaxiFileName("TRAINER_M.txt");
gymTrainer_.setImageMiniFileName("TRAINER_M.txt");
return gymTrainer_;
}
public static GymLeader p4(){
GymLeader gymLeader_=Instances.newGymLeader();
gymLeader_.setTm((short)6);
gymLeader_.setName("ALISE");
gymLeader_.setReward((short)4000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(3));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("PHANPY");
pkTrainer_.setLevel((short)22);
pkTrainer_.setGender(Gender.MALE);
pkTrainer_.setAbility("FERMETE");
pkTrainer_.setMoves(new StringList("DERNIERECOUR","DAMOCLES","SURPUISSANCE","TELLURIFORCE"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("SABELETTE");
pkTrainer_.setLevel((short)24);
pkTrainer_.setGender(Gender.MALE);
pkTrainer_.setAbility("FERMETE");
pkTrainer_.setMoves(new StringList("DAMOCLES","TELLURIFORCE","PUIS_CACHEE","PLAQUAGE"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("KRAKNOIX");
pkTrainer_.setLevel((short)25);
pkTrainer_.setGender(Gender.MALE);
pkTrainer_.setAbility("HYPER_CUTTER");
pkTrainer_.setMoves(new StringList("DAMOCLES","SURPUISSANCE","TELLURIFORCE","PUIS_CACHEE"));
custListPkTrainer_.add(pkTrainer_);
gymLeader_.setTeam(custListPkTrainer_);
gymLeader_.setMultiplicityFight((byte)1);
gymLeader_.setImageMaxiFileName("ALISE.txt");
gymLeader_.setImageMiniFileName("ALISE.txt");
return gymLeader_;
}
}
