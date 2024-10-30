package aiki.gui;

import aiki.beans.facade.comparators.*;
import aiki.db.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import aiki.gui.components.editor.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
import org.junit.Test;

public final class EditorPkUtilTest extends EquallableAikiGuiUtil {
    @Test
    public void buildCategoryMults() {
        TreeMap<CategoryMult, Rate> tr_ = new TreeMap<CategoryMult, Rate>(new ComparatorCategoryMult());
        tr_.put(new CategoryMult("",(short)0), Rate.zero());
        assertEq(1,ConverterCommonMapUtil.buildCategoryMults(tr_).size());
    }
    @Test
    public void buildStatisticCategoryByte() {
        DataBase db_ = core();
        StringMap<String> cat_ = new StringMap<String>();
        cat_.addEntry("","");
        db_.getTranslatedCategories().addEntry("", cat_);
        IdMap<Statistic, String> id_ = new IdMap<Statistic, String>();
        id_.addEntry(Statistic.SPEED,"");
        db_.getTranslatedStatistics().addEntry("", id_);
        TreeMap<StatisticCategory, Byte> tr_ = new TreeMap<StatisticCategory, Byte>(new ComparatorStatisticCategory(db_,""));
        tr_.put(new StatisticCategory(Statistic.SPEED,""), (byte)0);
        assertEq(1,ConverterCommonMapUtil.buildStatisticCategoryByte(tr_).size());
    }
    @Test
    public void buildStatisticCategoryRate() {
        DataBase db_ = core();
        StringMap<String> cat_ = new StringMap<String>();
        cat_.addEntry("","");
        db_.getTranslatedCategories().addEntry("", cat_);
        IdMap<Statistic, String> id_ = new IdMap<Statistic, String>();
        id_.addEntry(Statistic.SPEED,"");
        db_.getTranslatedStatistics().addEntry("", id_);
        TreeMap<StatisticCategory, Rate> tr_ = new TreeMap<StatisticCategory, Rate>(new ComparatorStatisticCategory(db_,""));
        tr_.put(new StatisticCategory(Statistic.SPEED,""), Rate.zero());
        assertEq(1,ConverterCommonMapUtil.buildStatisticCategoryRate(tr_).size());
    }
    @Test
    public void buildStatisticPokemons() {
        DataBase db_ = core();
        StringMap<String> pk_ = new StringMap<String>();
        pk_.addEntry("","");
        db_.getTranslatedPokemon().addEntry("", pk_);
        IdMap<Statistic, String> id_ = new IdMap<Statistic, String>();
        id_.addEntry(Statistic.SPEED,"");
        db_.getTranslatedStatistics().addEntry("", id_);
        TreeMap<StatisticPokemon, Byte> tr_ = new TreeMap<StatisticPokemon, Byte>(new ComparatorStatisticPokemon(db_,""));
        tr_.put(new StatisticPokemon(Statistic.SPEED,""), (byte)0);
        assertEq(1,ConverterCommonMapUtil.buildStatisticPokemons(tr_).size());
    }
    @Test
    public void buildStatisticStatusList() {
        DataBase db_ = core();
        StringMap<String> st_ = new StringMap<String>();
        st_.addEntry("","");
        db_.getTranslatedStatus().addEntry("", st_);
        IdMap<Statistic, String> id_ = new IdMap<Statistic, String>();
        id_.addEntry(Statistic.SPEED,"");
        db_.getTranslatedStatistics().addEntry("", id_);
        TreeMap<StatisticStatus, Byte> tr_ = new TreeMap<StatisticStatus, Byte>(new ComparatorStatusStatistic(db_,""));
        tr_.put(new StatisticStatus(Statistic.SPEED,""), (byte)0);
        assertEq(1,ConverterCommonMapUtil.buildStatisticStatusList(tr_).size());
    }
    @Test
    public void buildStatisticTypeByte() {
        DataBase db_ = core();
        StringMap<String> ty_ = new StringMap<String>();
        ty_.addEntry("","");
        db_.getTranslatedTypes().addEntry("", ty_);
        IdMap<Statistic, String> id_ = new IdMap<Statistic, String>();
        id_.addEntry(Statistic.SPEED,"");
        db_.getTranslatedStatistics().addEntry("", id_);
        TreeMap<StatisticType, Byte> tr_ = new TreeMap<StatisticType, Byte>(new ComparatorStatisticType(db_,""));
        tr_.put(new StatisticType(Statistic.SPEED,""), (byte)0);
        assertEq(1,ConverterCommonMapUtil.buildStatisticTypeByte(tr_).size());
    }
    @Test
    public void buildStatisticTypeRate() {
        DataBase db_ = core();
        StringMap<String> ty_ = new StringMap<String>();
        ty_.addEntry("","");
        db_.getTranslatedTypes().addEntry("", ty_);
        IdMap<Statistic, String> id_ = new IdMap<Statistic, String>();
        id_.addEntry(Statistic.SPEED,"");
        db_.getTranslatedStatistics().addEntry("", id_);
        TreeMap<StatisticType, Rate> tr_ = new TreeMap<StatisticType, Rate>(new ComparatorStatisticType(db_,""));
        tr_.put(new StatisticType(Statistic.SPEED,""), Rate.zero());
        assertEq(1,ConverterCommonMapUtil.buildStatisticTypeRate(tr_).size());
    }
    @Test
    public void buildTypesDuos() {
        DataBase db_ = core();
        StringMap<String> ty_ = new StringMap<String>();
        ty_.addEntry("","");
        db_.getTranslatedTypes().addEntry("", ty_);
        StringMap<String> pk_ = new StringMap<String>();
        pk_.addEntry("","");
        db_.getTranslatedPokemon().addEntry("", pk_);
        TreeMap<TypesDuo, Rate> tr_ = new TreeMap<TypesDuo, Rate>(new ComparatorTypesDuo(db_,"",true,false));
        tr_.put(new TypesDuo("",""), Rate.zero());
        assertEq(1,ConverterCommonMapUtil.buildTypesDuos(tr_).size());
    }
    @Test
    public void buildWeatherTypes() {
        DataBase db_ = core();
        StringMap<String> ty_ = new StringMap<String>();
        ty_.addEntry("","");
        db_.getTranslatedTypes().addEntry("", ty_);
        StringMap<String> mv_ = new StringMap<String>();
        mv_.addEntry("","");
        db_.getTranslatedMoves().addEntry("", mv_);
        TreeMap<WeatherType, Rate> tr_ = new TreeMap<WeatherType, Rate>(new ComparatorWeatherType(db_,""));
        tr_.put(new WeatherType("",""), Rate.zero());
        assertEq(1,ConverterCommonMapUtil.buildWeatherTypes(tr_).size());
    }
    private DataBase core() {
        DataBase db_ = new DataBase(new DefaultGenerator(new CustomSeedGene()));
        db_.initTranslations();
        return db_;
    }
}
