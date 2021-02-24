package aiki.sml.init;
import aiki.map.characters.*;
import aiki.instances.*;
import aiki.util.*;
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
public final class TrDual{
private TrDual(){}
public static DualFight p9l0d0(){
DualFight dualFight_=Instances.newDualFight();
Ally ally_=Instances.newAlly();
CustList<PkTrainer> custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(4));
PkTrainer pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("NYMPHALI");
pkTrainer_.setLevel((short)26);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("PEAU_FEERIQUE");
pkTrainer_.setMoves(new StringList("ECLAT_MAGIQUE","CHOC_PSY"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("CARABAFFE");
pkTrainer_.setLevel((short)26);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("TORRENT");
pkTrainer_.setMoves(new StringList("BULLES_D_O","COUP_D_BOULE"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("REPTINCEL");
pkTrainer_.setLevel((short)26);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("BRASIER");
pkTrainer_.setMoves(new StringList("POING_DE_FEU","GRIFFE"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("HERBIZARRE");
pkTrainer_.setLevel((short)26);
pkTrainer_.setGender(Gender.NO_GENDER);
pkTrainer_.setAbility("ENGRAIS");
pkTrainer_.setMoves(new StringList("FEUILLEMAGIK","ECLAIR"));
custListPkTrainer_.add(pkTrainer_);
ally_.setTeam(custListPkTrainer_);
dualFight_.setAlly(ally_);
TempTrainer tempTrainer_=Instances.newTempTrainer();
tempTrainer_.setImageMiniSecondTrainerFileName("TCHEREN.txt");
tempTrainer_.setReward((short)2500);
custListPkTrainer_=new CustList<PkTrainer>(new CollCapacity(4));
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("CORNEBRE");
pkTrainer_.setLevel((short)26);
pkTrainer_.setGender(Gender.FEMALE);
pkTrainer_.setAbility("REGARD_VIF");
pkTrainer_.setMoves(new StringList("RAPACE","DAMOCLES","DEVOREVE","TRICHERIE"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("CORNEBRE");
pkTrainer_.setLevel((short)26);
pkTrainer_.setGender(Gender.MALE);
pkTrainer_.setAbility("REGARD_VIF");
pkTrainer_.setMoves(new StringList("RAPACE","DAMOCLES","DEVOREVE","TRICHERIE"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("TADMORV");
pkTrainer_.setLevel((short)26);
pkTrainer_.setGender(Gender.FEMALE);
pkTrainer_.setAbility("POINT_POISON");
pkTrainer_.setMoves(new StringList("EXPLOSION","TONNERRE","LANCE_FLAMME","BOMB_BEURK"));
custListPkTrainer_.add(pkTrainer_);
pkTrainer_=Instances.newPkTrainer();
pkTrainer_.setName("TADMORV");
pkTrainer_.setLevel((short)26);
pkTrainer_.setGender(Gender.MALE);
pkTrainer_.setAbility("POINT_POISON");
pkTrainer_.setMoves(new StringList("EXPLOSION","TONNERRE","LANCE_FLAMME","BOMB_BEURK"));
custListPkTrainer_.add(pkTrainer_);
tempTrainer_.setTeam(custListPkTrainer_);
tempTrainer_.setMultiplicityFight((byte)2);
tempTrainer_.setImageMaxiFileName("COUPLE_1.txt");
tempTrainer_.setImageMiniFileName("BIANCA.txt");
dualFight_.setFoeTrainer(tempTrainer_);
dualFight_.setNames(new StringList("BIANCA","TCHEREN"));
dualFight_.setPt(new Point((short)9,(short)6));
return dualFight_;
}
}
