package aiki.fight.pokemon;

import aiki.db.EquallablePkUtil;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionLevelGender;
import aiki.fight.pokemon.evolution.EvolutionLevelSimple;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;


public class PokemonDataTest extends EquallablePkUtil {

    public static PokemonData dataBase() {
        PokemonData pk_ = new PokemonData();
        pk_.setWeight(new Rate(3));
        pk_.setHeight(new Rate(1,10));
        pk_.setTypes(new StringList("ELETRICK"));
        IdMap<Statistic,StatBaseEv> statistics_;
        statistics_ = new IdMap<Statistic,StatBaseEv>();
        statistics_.put(Statistic.ATTACK, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.DEFENSE, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.SPECIAL_ATTACK, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.SPECIAL_DEFENSE, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.SPEED, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.HP, new StatBaseEv((short)80,(short)0));
        pk_.setStatistics(statistics_);
        CustList<LevelMove> levMoves_ = new CustList<LevelMove>();
        levMoves_.add(new LevelMove((short)1,"ECLAIR"));
        levMoves_.add(new LevelMove((short)3,"CHARGE"));
        levMoves_.add(new LevelMove((short)6,"VIVE_ATTAQUE"));
        levMoves_.add(new LevelMove((short)15,"TONNERRE"));
        levMoves_.add(new LevelMove((short)15,"FOUDRE"));
        pk_.setLevMoves(levMoves_);
        pk_.setGenderRep(GenderRepartition.NO_GENDER);
        pk_.setAbilities(new StringList("STATIK"));
        pk_.setBaseEvo("PIKACHU");
        pk_.setCatchingRate((short) 200);
        pk_.setExpEvo(ExpType.P);
        pk_.setTechnicalMoves(new Shorts());
        pk_.setHiddenMoves(new Shorts());
        pk_.setMoveTutors(new StringList("VIVE_ATTAQUE"));
        pk_.setExpRate(100l);
        pk_.setHatchingSteps(new LgInt(200));
        pk_.setHappiness((short) 70);
        pk_.setHappinessHatch((short) 140);
        pk_.setEvolutions(new StringMap<Evolution>());
        pk_.setEggGroups(new StringList("SOURIS"));
        return pk_;
    }

    @Test
    public void getMovesAtLevel1Test() {
        PokemonData data_ = dataBase();
        StringList moves_ = data_.getMovesAtLevel((short) 1, 4);
        assertEq(1, moves_.size());
        assertEq("ECLAIR", moves_.get(0));
    }

    @Test
    public void getMovesAtLevel2Test() {
        PokemonData data_ = dataBase();
        StringList moves_ = data_.getMovesAtLevel((short) 3, 1);
        assertEq(1, moves_.size());
        assertEq("CHARGE", moves_.get(0));
    }

    @Test
    public void getMovesAtLevel3Test() {
        PokemonData data_ = dataBase();
        StringList moves_ = data_.getMovesAtLevel((short) 3, 2);
        assertEq(2, moves_.size());
        assertEq("CHARGE", moves_.get(0));
        assertEq("ECLAIR", moves_.get(1));
    }

    @Test
    public void getMovesAtLevel4Test() {
        PokemonData data_ = dataBase();
        StringList moves_ = data_.getMovesAtLevel((short) 6, 2);
        assertEq(2, moves_.size());
        assertEq("VIVE_ATTAQUE", moves_.get(0));
        assertEq("CHARGE", moves_.get(1));
    }

    @Test
    public void getMovesAtLevel5Test() {
        PokemonData data_ = dataBase();
        StringList moves_ = data_.getMovesAtLevel((short) 7, 2);
        assertEq(2, moves_.size());
        assertEq("VIVE_ATTAQUE", moves_.get(0));
        assertEq("CHARGE", moves_.get(1));
    }

    @Test
    public void getMovesAtLevel6Test() {
        PokemonData data_ = dataBase();
        StringList moves_ = data_.getMovesAtLevel((short) 15, 2);
        assertEq(2, moves_.size());
        assertEq("FOUDRE", moves_.get(0));
        assertEq("TONNERRE", moves_.get(1));
    }

    public static PokemonData dataBase(StringMap<Evolution> _evos) {
        PokemonData pk_ = dataBase();
        pk_.getEvolutions().putAllMap(_evos);
        return pk_;
    }

    @Test
    public void getDirectEvolutions1Test() {
        StringMap<Evolution> evos_ = new StringMap<Evolution>();
        evos_.put("RAICHU", new EvolutionLevelSimple());
        EvolutionLevelGender evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.FEMALE);
        evos_.put("PIKA", evo_);
        evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.MALE);
        evos_.put("CHU", evo_);
        PokemonData pk_ = dataBase(evos_);
        StringList evosRes_ = pk_.getDirectEvolutions(Gender.NO_GENDER, false);
        assertEq(3, evosRes_.size());
        assertTrue(StringUtil.contains(evosRes_, "RAICHU"));
        assertTrue(StringUtil.contains(evosRes_, "PIKA"));
        assertTrue(StringUtil.contains(evosRes_, "CHU"));
    }

    @Test
    public void getDirectEvolutions2Test() {
        StringMap<Evolution> evos_ = new StringMap<Evolution>();
        evos_.put("RAICHU", new EvolutionLevelSimple());
        EvolutionLevelGender evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.FEMALE);
        evos_.put("PIKA", evo_);
        evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.MALE);
        evos_.put("CHU", evo_);
        PokemonData pk_ = dataBase(evos_);
        StringList evosRes_ = pk_.getDirectEvolutions(Gender.FEMALE, false);
        assertEq(3, evosRes_.size());
        assertTrue(StringUtil.contains(evosRes_, "RAICHU"));
        assertTrue(StringUtil.contains(evosRes_, "PIKA"));
        assertTrue(StringUtil.contains(evosRes_, "CHU"));
    }

    @Test
    public void getDirectEvolutions3Test() {
        StringMap<Evolution> evos_ = new StringMap<Evolution>();
        evos_.put("RAICHU", new EvolutionLevelSimple());
        EvolutionLevelGender evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.FEMALE);
        evos_.put("PIKA", evo_);
        evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.MALE);
        evos_.put("CHU", evo_);
        PokemonData pk_ = dataBase(evos_);
        StringList evosRes_ = pk_.getDirectEvolutions(Gender.MALE, false);
        assertEq(3, evosRes_.size());
        assertTrue(StringUtil.contains(evosRes_, "RAICHU"));
        assertTrue(StringUtil.contains(evosRes_, "PIKA"));
        assertTrue(StringUtil.contains(evosRes_, "CHU"));
    }

    @Test
    public void getDirectEvolutions4Test() {
        StringMap<Evolution> evos_ = new StringMap<Evolution>();
        evos_.put("RAICHU", new EvolutionLevelSimple());
        EvolutionLevelGender evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.FEMALE);
        evos_.put("PIKA", evo_);
        evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.MALE);
        evos_.put("CHU", evo_);
        PokemonData pk_ = dataBase(evos_);
        StringList evosRes_ = pk_.getDirectEvolutions(Gender.NO_GENDER, true);
        assertEq(1, evosRes_.size());
        assertTrue(StringUtil.contains(evosRes_, "RAICHU"));
    }

    @Test
    public void getDirectEvolutions5Test() {
        StringMap<Evolution> evos_ = new StringMap<Evolution>();
        evos_.put("RAICHU", new EvolutionLevelSimple());
        EvolutionLevelGender evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.FEMALE);
        evos_.put("PIKA", evo_);
        evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.MALE);
        evos_.put("CHU", evo_);
        PokemonData pk_ = dataBase(evos_);
        StringList evosRes_ = pk_.getDirectEvolutions(Gender.FEMALE, true);
        assertEq(2, evosRes_.size());
        assertTrue(StringUtil.contains(evosRes_, "RAICHU"));
        assertTrue(StringUtil.contains(evosRes_, "PIKA"));
    }

    @Test
    public void getDirectEvolutions6Test() {
        StringMap<Evolution> evos_ = new StringMap<Evolution>();
        evos_.put("RAICHU", new EvolutionLevelSimple());
        EvolutionLevelGender evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.FEMALE);
        evos_.put("PIKA", evo_);
        evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.MALE);
        evos_.put("CHU", evo_);
        PokemonData pk_ = dataBase(evos_);
        StringList evosRes_ = pk_.getDirectEvolutions(Gender.MALE, true);
        assertEq(2, evosRes_.size());
        assertTrue(StringUtil.contains(evosRes_, "RAICHU"));
        assertTrue(StringUtil.contains(evosRes_, "CHU"));
    }

    @Test
    public void getDirectEvolutions7Test() {
        StringMap<Evolution> evos_ = new StringMap<Evolution>();
        evos_.put("RAICHU", new EvolutionLevelSimple());
        EvolutionLevelGender evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.FEMALE);
        evos_.put("PIKA", evo_);
        evo_ = new EvolutionLevelGender();
        evo_.setGender(Gender.MALE);
        evos_.put("CHU", evo_);
        PokemonData pk_ = dataBase(evos_);
        StringList evosRes_ = pk_.getDirectEvolutions();
        assertEq(3, evosRes_.size());
        assertTrue(StringUtil.contains(evosRes_, "RAICHU"));
        assertTrue(StringUtil.contains(evosRes_, "PIKA"));
        assertTrue(StringUtil.contains(evosRes_, "CHU"));
    }
}
