package aiki.fight.util;

import aiki.fight.enums.Statistic;
import aiki.map.tree.util.Dims;
import aiki.map.util.ScreenCoords;
import code.maths.Rate;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;

public final class RecordsTest {
    @Test
    public void test1() {
        CategoryMult c_ = CategoryMult.newCategoryMult("");
        c_.setMult((short) 1);
        c_.setCategory("");
        c_.display();
        assertEq(1,c_.getMult());
        c_ = new CategoryMult();
        assertEq(0,c_.getMult());
    }
    @Test
    public void test2() {
        StatisticType c_ = StatisticType.newStatisticType("");
        c_.setStatistic(Statistic.SPEED);
        c_.setType("");
        c_.display();
        assertEq(Statistic.SPEED,c_.getStatistic());
        c_ = new StatisticType();
        c_.setType("");
        assertEq("",c_.getType());
    }
    @Test
    public void test3() {
        StatisticCategory c_ = StatisticCategory.newStatisticCategory("");
        c_.setStatistic(Statistic.SPEED);
        c_.setCategory("");
        c_.display();
        assertEq(Statistic.SPEED,c_.getStatistic());
        c_ = new StatisticCategory();
        c_.setCategory("");
        assertEq("",c_.getCategory());
    }
    @Test
    public void test4() {
        LevelMove c_ = LevelMove.newLevelMove("");
        c_.setLevel((short) 1);
        c_.setMove("");
        c_.display();
        assertEq(1,c_.getLevel());
        c_ = new LevelMove();
        assertEq(0,c_.getLevel());
    }
    @Test
    public void test5() {
        TypesDuo c_ = TypesDuo.newTypesDuo("");
        c_.setDamageType("");
        c_.setPokemonType("");
        c_.display();
        assertEq("",c_.getDamageType());
        c_ = new TypesDuo();
        c_.setDamageType("");
        c_.setPokemonType("");
        assertEq("",c_.getDamageType());
    }
    @Test
    public void test6() {
        WeatherType c_ = WeatherType.newWeatherType("");
        c_.setType("");
        c_.setWeather("");
        c_.display();
        assertEq("",c_.getType());
        c_ = new WeatherType();
        c_.setType("");
        c_.setWeather("");
        assertEq("",c_.getType());
    }
    @Test
    public void test7() {
        BoostHpRate c_ = BoostHpRate.newBoostHpRate("0;0");
        c_.display();
        assertEq(0,c_.getBoost());
    }
    @Test
    public void test8() {
        StatisticPokemon c_ = StatisticPokemon.newStatisticPokemon("SPEED;");
        c_.display();
        assertEq(Statistic.SPEED,c_.getStatistic());
    }
    @Test
    public void test9() {
        EfficiencyRate c_ = EfficiencyRate.newEfficiencyRate("0;0");
        c_.display();
        assertEq(Rate.zero(),c_.getEff());
    }
    @Test
    public void test10() {
        StatBaseEv c_ = StatBaseEv.newStatBaseEv("0;0");
        c_.display();
        assertEq(0,c_.getBase());
    }
    @Test
    public void test11() {
        TypeDamageBoost c_ = TypeDamageBoost.newTypeDamageBoost("0;0");
        c_.display();
        assertEq("0",c_.getType());
    }
    @Test
    public void test12() {
        StatisticStatus c_ = StatisticStatus.newStatisticStatus("0;0");
        c_.display();
        assertEq("0",c_.getStatus());
    }
    @Test
    public void test13() {
        assertEq("0,0",new Dims().display());
    }
    @Test
    public void test14() {
        assertEq("0;0",new ScreenCoords().display());
    }
}
