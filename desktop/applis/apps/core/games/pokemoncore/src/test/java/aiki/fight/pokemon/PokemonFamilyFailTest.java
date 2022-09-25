package aiki.fight.pokemon;

import aiki.db.EquallablePkUtil;
import code.maths.montecarlo.DefaultGenerator;
import org.junit.Test;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionHappiness;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.IdMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;


public class PokemonFamilyFailTest extends EquallablePkUtil {

    public static PokemonData dataBase1(String _base) {
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
        pk_.setBaseEvo(_base);
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
    public void new_PokemonFamily_DataBase_String_1FailTest() {
        DataBase dataBase_ = newData();
        dataBase_.initializeMembers();
        PokemonData pkData_ = dataBase1("PIKACHU");
        pkData_.getEvolutions().put("PIKACHU", new EvolutionHappiness());
        dataBase_.completeMembers("PIKACHU", pkData_);
        getPokemonFamily(dataBase_, "PIKACHU");
        assertTrue(dataBase_.isError());
    }

    private static DataBase newData() {
        return new DataBase(new DefaultGenerator());
    }

    @Test
    public void new_PokemonFamily_DataBase_String_2FailTest() {
        DataBase dataBase_ = newData();
        dataBase_.initializeMembers();
        PokemonData pkData_ = dataBase1("PIKACHU");
        PokemonData pkDataTwo_ = dataBase1("RAICHU");
        pkData_.getEvolutions().put("RAICHU", new EvolutionHappiness());
        pkDataTwo_.setBaseEvo("RAICHU");
        dataBase_.completeMembers("PIKACHU", pkData_);
        dataBase_.completeMembers("RAICHU", pkDataTwo_);
        getPokemonFamily(dataBase_, "PIKACHU");
        assertTrue(dataBase_.isError());
    }

    @Test
    public void new_PokemonFamily_DataBase_String_3FailTest() {
        DataBase dataBase_ = newData();
        dataBase_.initializeMembers();
        PokemonData pkData_ = dataBase1("PIKACHU");
        PokemonData pkDataTwo_ = dataBase1("RAICHU");
        PokemonData pkDataThree_ = dataBase1("RAICHU_2");
        PokemonData pkDataFour_ = dataBase1("RAICHU_3");
        pkData_.getEvolutions().put("RAICHU", new EvolutionHappiness());
        pkData_.getEvolutions().put("RAICHU_2", new EvolutionHappiness());
        pkDataTwo_.setBaseEvo("PIKACHU");
        pkDataTwo_.getEvolutions().put("RAICHU_3", new EvolutionHappiness());
        pkDataThree_.setBaseEvo("PIKACHU");
        pkDataThree_.getEvolutions().put("RAICHU_3", new EvolutionHappiness());
        pkDataFour_.setBaseEvo("PIKACHU");
        dataBase_.completeMembers("PIKACHU", pkData_);
        dataBase_.completeMembers("RAICHU", pkDataTwo_);
        dataBase_.completeMembers("RAICHU_2", pkDataThree_);
        dataBase_.completeMembers("RAICHU_3", pkDataFour_);
        getPokemonFamily(dataBase_, "PIKACHU");
        assertTrue(dataBase_.isError());
    }

    private PokemonFamily getPokemonFamily(DataBase _data, String _name) {
        return new PokemonFamily(_data, _name);
    }
}
