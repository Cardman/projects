package aiki.sml.init;
import aiki.map.characters.*;
import aiki.instances.*;
import code.util.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
public final class TrLeague extends CstIgame{
private TrLeague(){}
public static TrainerLeague p10t0(){
TrainerLeague m2051trainerLeague_=Instances.newTrainerLeague();
m2051trainerLeague_.setName(I_KARINA);
m2051trainerLeague_.setReward(20000);
CustList<PkTrainer> m2051custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(2));
PkTrainer m2051pkTrainer_=Instances.newPkTrainer();
m2051pkTrainer_.setName(I_ARTIKODIN);
m2051pkTrainer_.setLevel(105);
m2051pkTrainer_.setGender(Gender.NO_GENDER);
m2051pkTrainer_.setAbility(I_PRESSION);
m2051pkTrainer_.setMoves(new StringList(I_DAMOCLES,I_LASER_GLACE,I_PUIS_CACHEE,I_RAYON_SIGNAL));
m2051custListPkTrainer_.add(m2051pkTrainer_);
m2051pkTrainer_=Instances.newPkTrainer();
m2051pkTrainer_.setName(I_LAMANTINE);
m2051pkTrainer_.setLevel(105);
m2051pkTrainer_.setGender(Gender.MALE);
m2051pkTrainer_.setAbility(I_CUVETTE);
m2051pkTrainer_.setMoves(new StringList(I_DAMOCLES,I_LASER_GLACE,I_PUIS_CACHEE,I_PLAQUAGE));
m2051custListPkTrainer_.add(m2051pkTrainer_);
m2051trainerLeague_.setTeam(m2051custListPkTrainer_);
m2051trainerLeague_.setMultiplicityFight(2);
m2051trainerLeague_.setImageMaxiFileName(F_KARINA_TXT);
m2051trainerLeague_.setImageMiniFileName(F_KARINA_TXT);
return m2051trainerLeague_;
}
public static TrainerLeague p10t1(){
TrainerLeague m2052trainerLeague_=Instances.newTrainerLeague();
m2052trainerLeague_.setName(I_STEVE);
m2052trainerLeague_.setReward(25000);
CustList<PkTrainer> m2052custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(2));
PkTrainer m2052pkTrainer_=Instances.newPkTrainer();
m2052pkTrainer_.setName(I_GROLEM);
m2052pkTrainer_.setLevel(110);
m2052pkTrainer_.setGender(Gender.MALE);
m2052pkTrainer_.setAbility(I_FERMETE);
m2052pkTrainer_.setMoves(new StringList(I_EXPLOSION,I_DAMOCLES,I_SURPUISSANCE,I_LANCE_FLAMME));
m2052custListPkTrainer_.add(m2052pkTrainer_);
m2052pkTrainer_=Instances.newPkTrainer();
m2052pkTrainer_.setName(I_TYRANOCIF);
m2052pkTrainer_.setLevel(105);
m2052pkTrainer_.setGender(Gender.MALE);
m2052pkTrainer_.setAbility(I_ATTENTION);
m2052pkTrainer_.setMoves(new StringList(I_COLERE,I_DAMOCLES,I_SURPUISSANCE,I_TRICHERIE));
m2052custListPkTrainer_.add(m2052pkTrainer_);
m2052trainerLeague_.setTeam(m2052custListPkTrainer_);
m2052trainerLeague_.setMultiplicityFight(2);
m2052trainerLeague_.setImageMaxiFileName(F_STEVE_TXT);
m2052trainerLeague_.setImageMiniFileName(F_STEVE_TXT);
return m2052trainerLeague_;
}
}
