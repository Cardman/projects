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
public final class TrBase extends CstIgame{
private TrBase(){}
public static TrainerMultiFights p1l0t0(){
TrainerMultiFights m2032trainerMultiFights_=Instances.newTrainerMultiFights();
CustList<PokemonTeam> m2032custListPokemonTeam_=new CustList<PokemonTeam>(new CollCapacity(1));
PokemonTeam m2032pokemonTeam_=Instances.newPokemonTeam();
CustList<PkTrainer> m2032custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(8));
m2032custListPkTrainer_.add(one());
m2032custListPkTrainer_.add(one());
m2032custListPkTrainer_.add(one());
m2032custListPkTrainer_.add(one());
m2032custListPkTrainer_.add(one());
m2032custListPkTrainer_.add(one());
m2032custListPkTrainer_.add(one());
m2032custListPkTrainer_.add(one());
m2032pokemonTeam_.setTeam(m2032custListPkTrainer_);
m2032pokemonTeam_.setReward((short)4000);
m2032custListPokemonTeam_.add(m2032pokemonTeam_);
m2032trainerMultiFights_.setTeamsRewards(m2032custListPokemonTeam_);
m2032trainerMultiFights_.setMultiplicityFight((byte)1);
m2032trainerMultiFights_.setImageMaxiFileName(F_TRAINER_M_TXT);
m2032trainerMultiFights_.setImageMiniFileName(F_TRAINER_M_TXT);
return m2032trainerMultiFights_;
}
private static PkTrainer one(){
PkTrainer m2032pkTrainer_=Instances.newPkTrainer();
m2032pkTrainer_.setName(I_RATTATA);
m2032pkTrainer_.setLevel((short)4);
m2032pkTrainer_.setGender(Gender.MALE);
m2032pkTrainer_.setAbility(I_CRAN);
m2032pkTrainer_.setMoves(new StringList(I_CHARGE));
return m2032pkTrainer_;
}
public static TrainerMultiFights p1l0t1(){
TrainerMultiFights m2033trainerMultiFights_=Instances.newTrainerMultiFights();
CustList<PokemonTeam> m2033custListPokemonTeam_=new CustList<PokemonTeam>(new CollCapacity(1));
PokemonTeam m2033pokemonTeam_=Instances.newPokemonTeam();
CustList<PkTrainer> m2033custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(8));
m2033custListPkTrainer_.add(two());
m2033custListPkTrainer_.add(two());
m2033custListPkTrainer_.add(two());
m2033custListPkTrainer_.add(two());
m2033custListPkTrainer_.add(two());
m2033custListPkTrainer_.add(two());
m2033custListPkTrainer_.add(two());
m2033custListPkTrainer_.add(two());
m2033pokemonTeam_.setTeam(m2033custListPkTrainer_);
m2033pokemonTeam_.setReward((short)4000);
m2033custListPokemonTeam_.add(m2033pokemonTeam_);
m2033trainerMultiFights_.setTeamsRewards(m2033custListPokemonTeam_);
m2033trainerMultiFights_.setMultiplicityFight((byte)1);
m2033trainerMultiFights_.setImageMaxiFileName(F_TRAINER_F_TXT);
m2033trainerMultiFights_.setImageMiniFileName(F_TRAINER_F_TXT);
return m2033trainerMultiFights_;
}
private static PkTrainer two(){
PkTrainer m2033pkTrainer_=Instances.newPkTrainer();
m2033pkTrainer_.setName(I_RATTATA);
m2033pkTrainer_.setLevel((short)4);
m2033pkTrainer_.setGender(Gender.FEMALE);
m2033pkTrainer_.setAbility(I_CRAN);
m2033pkTrainer_.setMoves(new StringList(I_CHARGE));
return m2033pkTrainer_;
}
public static TrainerMultiFights p3l0t0(){
TrainerMultiFights m2034trainerMultiFights_=Instances.newTrainerMultiFights();
CustList<PokemonTeam> m2034custListPokemonTeam_=new CustList<PokemonTeam>(new CollCapacity(2));
PokemonTeam m2034pokemonTeam_=Instances.newPokemonTeam();
CustList<PkTrainer> m2034custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(8));
PkTrainer m2034pkTrainer_=Instances.newPkTrainer();
m2034pkTrainer_.setName(I_RATTATAC);
m2034pkTrainer_.setLevel((short)24);
m2034pkTrainer_.setGender(Gender.FEMALE);
m2034pkTrainer_.setAbility(I_CRAN);
m2034pkTrainer_.setMoves(new StringList(I_CHARGE,I_MORSURE));
m2034custListPkTrainer_.add(three1());
m2034custListPkTrainer_.add(three1());
m2034custListPkTrainer_.add(three1());
m2034custListPkTrainer_.add(three1());
m2034custListPkTrainer_.add(three1());
m2034custListPkTrainer_.add(three1());
m2034custListPkTrainer_.add(three1());
m2034custListPkTrainer_.add(three1());
m2034pokemonTeam_.setTeam(m2034custListPkTrainer_);
m2034pokemonTeam_.setReward((short)25000);
m2034custListPokemonTeam_.add(m2034pokemonTeam_);
m2034pokemonTeam_=Instances.newPokemonTeam();
m2034custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(8));
m2034custListPkTrainer_.add(three2());
m2034custListPkTrainer_.add(three2());
m2034custListPkTrainer_.add(three2());
m2034custListPkTrainer_.add(three2());
m2034custListPkTrainer_.add(three2());
m2034custListPkTrainer_.add(three2());
m2034custListPkTrainer_.add(three2());
m2034custListPkTrainer_.add(three2());
m2034pokemonTeam_.setTeam(m2034custListPkTrainer_);
m2034pokemonTeam_.setReward((short)30000);
m2034custListPokemonTeam_.add(m2034pokemonTeam_);
m2034trainerMultiFights_.setTeamsRewards(m2034custListPokemonTeam_);
m2034trainerMultiFights_.setMultiplicityFight((byte)1);
m2034trainerMultiFights_.setImageMaxiFileName(F_TRAINER_F_TXT);
m2034trainerMultiFights_.setImageMiniFileName(F_TRAINER_F_TXT);
return m2034trainerMultiFights_;
}
private static PkTrainer three1(){
PkTrainer m2034pkTrainer_=Instances.newPkTrainer();
m2034pkTrainer_.setName(I_RATTATAC);
m2034pkTrainer_.setLevel((short)24);
m2034pkTrainer_.setGender(Gender.FEMALE);
m2034pkTrainer_.setAbility(I_CRAN);
m2034pkTrainer_.setMoves(new StringList(I_CHARGE,I_MORSURE));
return m2034pkTrainer_;
}
private static PkTrainer three2(){
PkTrainer m2034pkTrainer_=Instances.newPkTrainer();
m2034pkTrainer_.setName(I_RATTATAC);
m2034pkTrainer_.setLevel((short)28);
m2034pkTrainer_.setGender(Gender.FEMALE);
m2034pkTrainer_.setAbility(I_CRAN);
m2034pkTrainer_.setMoves(new StringList(I_CHARGE,I_MORSURE));
return m2034pkTrainer_;
}
public static TrainerMultiFights p3l0t1(){
TrainerMultiFights m2035trainerMultiFights_=Instances.newTrainerMultiFights();
CustList<PokemonTeam> m2035custListPokemonTeam_=new CustList<PokemonTeam>(new CollCapacity(2));
PokemonTeam m2035pokemonTeam_=Instances.newPokemonTeam();
CustList<PkTrainer> m2035custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(8));
m2035custListPkTrainer_.add(four1());
m2035custListPkTrainer_.add(four1());
m2035custListPkTrainer_.add(four1());
m2035custListPkTrainer_.add(four1());
m2035custListPkTrainer_.add(four1());
m2035custListPkTrainer_.add(four1());
m2035custListPkTrainer_.add(four1());
m2035custListPkTrainer_.add(four1());
m2035pokemonTeam_.setTeam(m2035custListPkTrainer_);
m2035pokemonTeam_.setReward((short)25000);
m2035custListPokemonTeam_.add(m2035pokemonTeam_);
m2035pokemonTeam_=Instances.newPokemonTeam();
m2035custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(8));
m2035custListPkTrainer_.add(four2());
m2035custListPkTrainer_.add(four2());
m2035custListPkTrainer_.add(four2());
m2035custListPkTrainer_.add(four2());
m2035custListPkTrainer_.add(four2());
m2035custListPkTrainer_.add(four2());
m2035custListPkTrainer_.add(four2());
m2035custListPkTrainer_.add(four2());
m2035pokemonTeam_.setTeam(m2035custListPkTrainer_);
m2035pokemonTeam_.setReward((short)30000);
m2035custListPokemonTeam_.add(m2035pokemonTeam_);
m2035trainerMultiFights_.setTeamsRewards(m2035custListPokemonTeam_);
m2035trainerMultiFights_.setMultiplicityFight((byte)1);
m2035trainerMultiFights_.setImageMaxiFileName(F_TRAINER_M_TXT);
m2035trainerMultiFights_.setImageMiniFileName(F_TRAINER_M_TXT);
return m2035trainerMultiFights_;
}
private static PkTrainer four1(){
PkTrainer m2035pkTrainer_=Instances.newPkTrainer();
m2035pkTrainer_.setName(I_RATTATAC);
m2035pkTrainer_.setLevel((short)24);
m2035pkTrainer_.setGender(Gender.MALE);
m2035pkTrainer_.setAbility(I_CRAN);
m2035pkTrainer_.setMoves(new StringList(I_CHARGE,I_MORSURE));
return m2035pkTrainer_;
}
private static PkTrainer four2(){
PkTrainer m2035pkTrainer_=Instances.newPkTrainer();
m2035pkTrainer_.setName(I_RATTATAC);
m2035pkTrainer_.setLevel((short)28);
m2035pkTrainer_.setGender(Gender.MALE);
m2035pkTrainer_.setAbility(I_CRAN);
m2035pkTrainer_.setMoves(new StringList(I_CHARGE,I_MORSURE));
return m2035pkTrainer_;
}
}
