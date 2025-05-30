package aiki.fight.pokemon;

import aiki.db.EquallablePkUtil;
import code.maths.montecarlo.DefaultGenerator;
import code.util.*;
import code.util.core.StringUtil;
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


public class PokemonFamilyTest extends EquallablePkUtil {

//    @BeforeClass
//    public static void initDataBase() {
//        InitializationDataBase.initDataBase();
//    }

    @Test
    public void new_PokemonFamily_DataBase_String_1Test() {
        DataBase dataBase_ = newData();
        dataBase_.initializeMembers();
        PokemonData pkData_ = newPokemonData("PIKACHU");
        dataBase_.completeMembers("PIKACHU", pkData_);
        PokemonFamily pk_ = getPokemonFamily(dataBase_, "PIKACHU");
        assertTrue(!dataBase_.isError());
        assertEq(1, pk_.getStages().size());
        assertEq(1, pk_.getStages().first().size());
        assertTrue(StringUtil.contains(pk_.getStages().first(), "PIKACHU"));
        assertEq(1, pk_.getAllPokemon().size());
        assertTrue(StringUtil.contains(pk_.getAllPokemon(), "PIKACHU"));
    }

    private static DataBase newData() {
        return new DataBase(DefaultGenerator.oneElt());
    }

    @Test
    public void new_PokemonFamily_DataBase_String_2Test() {
        DataBase dataBase_ = newData();
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
        assertTrue(StringUtil.contains(pk_.getStages().first(), "PTITARD"));
        assertEq(1, pk_.getStages().get(1).size());
        assertTrue(StringUtil.contains(pk_.getStages().get(1), "TETARTE"));
        assertEq(2, pk_.getStages().get(2).size());
        assertTrue(StringUtil.contains(pk_.getStages().get(2), "TARTARD"));
        assertTrue(StringUtil.contains(pk_.getStages().get(2), "TARPAUD"));
        assertEq(4, pk_.getAllPokemon().size());
        assertTrue(StringUtil.contains(pk_.getAllPokemon(), "PTITARD"));
        assertTrue(StringUtil.contains(pk_.getAllPokemon(), "TETARTE"));
        assertTrue(StringUtil.contains(pk_.getAllPokemon(), "TARTARD"));
        assertTrue(StringUtil.contains(pk_.getAllPokemon(), "TARPAUD"));
    }

    @Test
    public void new_PokemonFamily_DataBase_String_1FailTest() {
        DataBase dataBase_ = newData();
        dataBase_.initializeMembers();
        PokemonData pkData_ = newPokemonData("PIKACHU");
        pkData_.getEvolutions().put("PIKACHU", new EvolutionHappiness());
        dataBase_.completeMembers("PIKACHU", pkData_);
        getPokemonFamily(dataBase_, "PIKACHU");
        assertTrue(dataBase_.isError());
    }

    @Test
    public void new_PokemonFamily_DataBase_String_2FailTest() {
        DataBase dataBase_ = newData();
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
        DataBase dataBase_ = newData();
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
        pk_.setWeight(new Rate(3));
        pk_.setHeight(new Rate(1,10));
        pk_.setTypes(new StringList("ELETRICK"));
        IdMap<Statistic,StatBaseEv> statistics_;
        statistics_ = new IdMap<Statistic,StatBaseEv>();
        statistics_.put(Statistic.ATTACK, new StatBaseEv(50,0));
        statistics_.put(Statistic.DEFENSE, new StatBaseEv(50,0));
        statistics_.put(Statistic.SPECIAL_ATTACK, new StatBaseEv(50,0));
        statistics_.put(Statistic.SPECIAL_DEFENSE, new StatBaseEv(50,0));
        statistics_.put(Statistic.SPEED, new StatBaseEv(50,0));
        statistics_.put(Statistic.HP, new StatBaseEv(80,0));
        pk_.setStatistics(statistics_);
        CustList<LevelMove> levMoves_ = new CustList<LevelMove>();
        levMoves_.add(new LevelMove(1,"ECLAIR"));
        levMoves_.add(new LevelMove(3,"CHARGE"));
        levMoves_.add(new LevelMove(6,"VIVE_ATTAQUE"));
        levMoves_.add(new LevelMove(15,"TONNERRE"));
        levMoves_.add(new LevelMove(15,"FOUDRE"));
        pk_.setLevMoves(levMoves_);
        pk_.setGenderRep(GenderRepartition.NO_GENDER);
        pk_.setAbilities(new StringList("STATIK"));
        pk_.setBaseEvo(_base);
        pk_.setCatchingRate( 200);
        pk_.setExpEvo(ExpType.P);
        pk_.setTechnicalMoves(new Ints());
        pk_.setHiddenMoves(new Ints());
        pk_.setMoveTutors(new StringList("VIVE_ATTAQUE"));
        pk_.setExpRate(100l);
        pk_.setHatchingSteps(new LgInt(200));
        pk_.setHappiness( 70);
        pk_.setHappinessHatch( 140);
        pk_.setEvolutions(new StringMap<Evolution>());
        pk_.setEggGroups(new StringList("SOURIS"));
        return pk_;
    }

    private PokemonFamily getPokemonFamily(DataBase _data, String _name) {
        return new PokemonFamily(_data, _name);
    }
}
