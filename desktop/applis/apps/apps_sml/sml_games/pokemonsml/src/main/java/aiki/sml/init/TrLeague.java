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
public final class TrLeague{
private TrLeague(){}
public static TrainerLeague p10t0(){
TrainerLeague trainerLeague_=Instances.newTrainerLeague();
trainerLeague_.setName("KARINA");
trainerLeague_.setReward((short)20000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(2));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("ARTIKODIN");
pkTrainer_.setLevel((short)105);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("PRESSION");
pkTrainer_.setMoves(new StringList("DAMOCLES","LASER_GLACE","PUIS_CACHEE","RAYON_SIGNAL"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("LAMANTINE");
pkTrainer_.setLevel((short)105);
pkTrainer_.setGender(Gender.MALE);
pkTrainer_.setAbility("CUVETTE");
pkTrainer_.setMoves(new StringList("DAMOCLES","LASER_GLACE","PUIS_CACHEE","PLAQUAGE"));
custListPkTrainer_.add(pkTrainer_);
trainerLeague_.setTeam(custListPkTrainer_);
trainerLeague_.setMultiplicityFight((byte)2);
trainerLeague_.setImageMaxiFileName("KARINA.txt");
trainerLeague_.setImageMiniFileName("KARINA.txt");
return trainerLeague_;
}
public static TrainerLeague p10t1(){
TrainerLeague trainerLeague_=Instances.newTrainerLeague();
trainerLeague_.setName("STEVE");
trainerLeague_.setReward((short)25000);
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(2));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("GROLEM");
pkTrainer_.setLevel((short)110);
pkTrainer_.setGender(Gender.MALE);
pkTrainer_.setAbility("FERMETE");
pkTrainer_.setMoves(new StringList("EXPLOSION","DAMOCLES","SURPUISSANCE","LANCE_FLAMME"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("TYRANOCIF");
pkTrainer_.setLevel((short)105);
pkTrainer_.setGender(Gender.MALE);
pkTrainer_.setAbility("ATTENTION");
pkTrainer_.setMoves(new StringList("COLERE","DAMOCLES","SURPUISSANCE","TRICHERIE"));
custListPkTrainer_.add(pkTrainer_);
trainerLeague_.setTeam(custListPkTrainer_);
trainerLeague_.setMultiplicityFight((byte)2);
trainerLeague_.setImageMaxiFileName("STEVE.txt");
trainerLeague_.setImageMiniFileName("STEVE.txt");
return trainerLeague_;
}
}
