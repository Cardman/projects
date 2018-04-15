package aiki.fight.pokemon;
import static aiki.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionHappiness;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class PokemonFamilyTest {

//    @BeforeClass
//    public static void initDataBase() {
//        InitializationDataBase.initDataBase();
//    }

    @Test
    public void new_PokemonFamily_DataBase_String_1Test() {
        DataBase dataBase_ = new DataBase();
        dataBase_.initializeMembers();
        PokemonData pkData_ = newPokemonData("PIKACHU");
        dataBase_.completeMembers("PIKACHU", pkData_);
        PokemonFamily pk_ = getPokemonFamily(dataBase_, "PIKACHU");
        assertTrue(!dataBase_.isError());
        assertEq(1, pk_.getStages().size());
        assertEq(1, pk_.getStages().first().size());
        assertTrue(pk_.getStages().first().containsObj("PIKACHU"));
        assertEq(1, pk_.getAllPokemon().size());
        assertTrue(pk_.getAllPokemon().containsObj("PIKACHU"));
    }

    @Test
    public void new_PokemonFamily_DataBase_String_2Test() {
        DataBase dataBase_ = new DataBase();
        dataBase_.initializeMembers();
        PokemonData pkData_ = newPokemonData("PTITARD");
        PokemonData pkDataTwo_ = newPokemonData("TETARTE");
        pkDataTwo_.setBaseEvo("PTITARD");
        pkData_.getEvolutions().put("TETARTE", new EvolutionHappiness());
        PokemonData pkDataThree_ = newPokemonData("TARTARD");
        pkDataThree_.setBaseEvo("PTITARD");
        pkDataTwo_.getEvolutions().put("TARTARD", new EvolutionHappiness());
        PokemonData pkDataFour_ = newPokemonData("TARPAUD");
        pkDataFour_.setBaseEvo("PTITARD");
        pkDataTwo_.getEvolutions().put("TARPAUD", new EvolutionHappiness());
        dataBase_.completeMembers("PTITARD", pkData_);
        dataBase_.completeMembers("TETARTE", pkDataTwo_);
        dataBase_.completeMembers("TARTARD", pkDataThree_);
        dataBase_.completeMembers("TARPAUD", pkDataFour_);
        PokemonFamily pk_ = getPokemonFamily(dataBase_, "PTITARD");
        assertTrue(!dataBase_.isError());
        assertEq(3, pk_.getStages().size());
        assertEq(1, pk_.getStages().first().size());
        assertTrue(pk_.getStages().first().containsObj("PTITARD"));
        assertEq(1, pk_.getStages().get(1).size());
        assertTrue(pk_.getStages().get(1).containsObj("TETARTE"));
        assertEq(2, pk_.getStages().get(2).size());
        assertTrue(pk_.getStages().get(2).containsObj("TARTARD"));
        assertTrue(pk_.getStages().get(2).containsObj("TARPAUD"));
        assertEq(4, pk_.getAllPokemon().size());
        assertTrue(pk_.getAllPokemon().containsObj("PTITARD"));
        assertTrue(pk_.getAllPokemon().containsObj("TETARTE"));
        assertTrue(pk_.getAllPokemon().containsObj("TARTARD"));
        assertTrue(pk_.getAllPokemon().containsObj("TARPAUD"));
    }

    @Test
    public void new_PokemonFamily_DataBase_String_1FailTest() {
        DataBase dataBase_ = new DataBase();
        dataBase_.initializeMembers();
        PokemonData pkData_ = newPokemonData("PIKACHU");
        pkData_.getEvolutions().put("PIKACHU", new EvolutionHappiness());
        dataBase_.completeMembers("PIKACHU", pkData_);
        getPokemonFamily(dataBase_, "PIKACHU");
        assertTrue(dataBase_.isError());
    }

    @Test
    public void new_PokemonFamily_DataBase_String_2FailTest() {
        DataBase dataBase_ = new DataBase();
        dataBase_.initializeMembers();
        PokemonData pkData_ = newPokemonData("PIKACHU");
        PokemonData pkDataTwo_ = newPokemonData("RAICHU");
        pkData_.getEvolutions().put("RAICHU", new EvolutionHappiness());
        pkDataTwo_.setBaseEvo("RAICHU");
        dataBase_.completeMembers("PIKACHU", pkData_);
        dataBase_.completeMembers("RAICHU", pkDataTwo_);
        getPokemonFamily(dataBase_, "PIKACHU");
        assertTrue(dataBase_.isError());
    }

    @Test
    public void new_PokemonFamily_DataBase_String_3FailTest() {
        DataBase dataBase_ = new DataBase();
        dataBase_.initializeMembers();
        PokemonData pkData_ = newPokemonData("PIKACHU");
        PokemonData pkDataTwo_ = newPokemonData("RAICHU");
        PokemonData pkDataThree_ = newPokemonData("RAICHU_2");
        PokemonData pkDataFour_ = newPokemonData("RAICHU_3");
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

    private static PokemonData newPokemonData(String _base) {
        PokemonData pk_ = new PokemonData();
        pk_.setNumber(3);
        pk_.setWeight(new Rate(3));
        pk_.setHeight(new Rate(1,10));
        pk_.setTypes(new StringList("ELETRICK"));
        EnumMap<Statistic,StatBaseEv> statistics_;
        statistics_ = new EnumMap<Statistic,StatBaseEv>();
        statistics_.put(Statistic.ATTACK, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.DEFENSE, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.SPECIAL_ATTACK, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.SPECIAL_DEFENSE, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.SPEED, new StatBaseEv((short)50,(short)0));
        statistics_.put(Statistic.HP, new StatBaseEv((short)80,(short)0));
        pk_.setStatistics(statistics_);
        EqList<LevelMove> levMoves_ = new EqList<LevelMove>();
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
        pk_.setTechnicalMoves(new Numbers<Short>());
        pk_.setHiddenMoves(new Numbers<Short>());
        pk_.setMoveTutors(new StringList("VIVE_ATTAQUE"));
        pk_.setExpRate(100l);
        pk_.setHatchingSteps(new LgInt(200));
        pk_.setHappiness((short) 70);
        pk_.setHappinessHatch((short) 140);
        pk_.setEvolutions(new StringMap<Evolution>());
        pk_.setEggGroups(new StringList("SOURIS"));
        return pk_;
    }

    private PokemonFamily getPokemonFamily(DataBase _data, String _name) {
        return new PokemonFamily(_data, _name);
    }
}
