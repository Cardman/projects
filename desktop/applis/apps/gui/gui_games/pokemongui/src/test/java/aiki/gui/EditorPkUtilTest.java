package aiki.gui;

import aiki.fight.enums.*;
import aiki.fight.util.*;
import aiki.gui.components.editor.*;
import code.gui.*;
import code.maths.*;
import code.util.*;
import org.junit.Test;

public final class EditorPkUtilTest extends EquallableAikiGuiUtil {
    @Test
    public void buildCategoryMults() {
        CustList<EditedCrudPair<CategoryMult, Rate>> tr_ = new CustList<EditedCrudPair<CategoryMult, Rate>>();
        tr_.add(new EditedCrudPair<CategoryMult, Rate>(new CategoryMult("",(short)0), Rate.zero()));
        assertEq(1,ConverterCommonMapUtil.buildCategoryMults(tr_).size());
    }
    @Test
    public void buildStatisticCategoryByte() {
        CustList<EditedCrudPair<StatisticCategory, Byte>> tr_ = new CustList<EditedCrudPair<StatisticCategory, Byte>>();
        tr_.add(new EditedCrudPair<StatisticCategory, Byte>(new StatisticCategory(Statistic.SPEED,""), (byte)0));
        assertEq(1,ConverterCommonMapUtil.buildStatisticCategoryByte(tr_).size());
    }
    @Test
    public void buildStatisticCategoryRate() {
        CustList<EditedCrudPair<StatisticCategory, Rate>> tr_ = new CustList<EditedCrudPair<StatisticCategory, Rate>>();
        tr_.add(new EditedCrudPair<StatisticCategory, Rate>(new StatisticCategory(Statistic.SPEED,""), Rate.zero()));
        assertEq(1,ConverterCommonMapUtil.buildStatisticCategoryRate(tr_).size());
    }
    @Test
    public void buildStatisticPokemons() {
        CustList<EditedCrudPair<StatisticPokemon, Byte>> tr_ = new CustList<EditedCrudPair<StatisticPokemon, Byte>>();
        tr_.add(new EditedCrudPair<StatisticPokemon, Byte>(new StatisticPokemon(Statistic.SPEED,""), (byte)0));
        assertEq(1,ConverterCommonMapUtil.buildStatisticPokemons(tr_).size());
    }
    @Test
    public void buildStatisticStatusList() {
        CustList<EditedCrudPair<StatisticStatus, Byte>> tr_ = new CustList<EditedCrudPair<StatisticStatus, Byte>>();
        tr_.add(new EditedCrudPair<StatisticStatus, Byte>(new StatisticStatus(Statistic.SPEED,""), (byte)0));
        assertEq(1,ConverterCommonMapUtil.buildStatisticStatusList(tr_).size());
    }
    @Test
    public void buildStatisticTypeByte() {
        CustList<EditedCrudPair<StatisticType, Byte>> tr_ = new CustList<EditedCrudPair<StatisticType, Byte>>();
        tr_.add(new EditedCrudPair<StatisticType, Byte>(new StatisticType(Statistic.SPEED,""), (byte)0));
        assertEq(1,ConverterCommonMapUtil.buildStatisticTypeByte(tr_).size());
    }
    @Test
    public void buildStatisticTypeRate() {
        CustList<EditedCrudPair<StatisticType, Rate>> tr_ = new CustList<EditedCrudPair<StatisticType, Rate>>();
        tr_.add(new EditedCrudPair<StatisticType, Rate>(new StatisticType(Statistic.SPEED,""), Rate.zero()));
        assertEq(1,ConverterCommonMapUtil.buildStatisticTypeRate(tr_).size());
    }
    @Test
    public void buildTypesDuos() {
        CustList<EditedCrudPair<TypesDuo, Rate>> tr_ = new CustList<EditedCrudPair<TypesDuo, Rate>>();
        tr_.add(new EditedCrudPair<TypesDuo, Rate>(new TypesDuo("",""), Rate.zero()));
        assertEq(1,ConverterCommonMapUtil.buildTypesDuos(tr_).size());
    }
    @Test
    public void buildWeatherTypes() {
        CustList<EditedCrudPair<WeatherType, Rate>> tr_ = new CustList<EditedCrudPair<WeatherType, Rate>>();
        tr_.add(new EditedCrudPair<WeatherType, Rate>(new WeatherType("",""), Rate.zero()));
        assertEq(1,ConverterCommonMapUtil.buildWeatherTypes(tr_).size());
    }
}
