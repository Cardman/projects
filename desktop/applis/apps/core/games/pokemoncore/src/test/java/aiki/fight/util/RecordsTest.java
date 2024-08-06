package aiki.fight.util;

import aiki.db.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.status.*;
import aiki.game.*;
import aiki.game.fight.*;
import aiki.game.fight.enums.*;
import aiki.game.fight.util.*;
import aiki.game.params.enums.*;
import aiki.game.player.enums.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.characters.enums.*;
import aiki.map.levels.enums.*;
import aiki.map.pokemon.enums.*;
import aiki.map.tree.util.*;
import aiki.map.util.*;
import aiki.util.*;
import code.maths.*;
import code.util.*;
import org.junit.Test;

public final class RecordsTest extends EquallablePkUtil {
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
        StatisticPokemon c_ = StatisticPokemon.newStatisticPokemon(DataBase.DEF_STAT_SPEED+";");
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
    @Test
    public void test15() {
        assertEq(0,new ListEffectCombos().getVal(new StringList()).getEffectEndRound().size());
    }
    @Test
    public void test16() {
        ListEffectCombos l_ = new ListEffectCombos();
        l_.add(new ListEffectCombo(new StringList(),Instances.newEffectCombo()));
        assertEq(0, l_.getVal(new StringList()).getEffectEndRound().size());
    }
    @Test
    public void test17() {
        ListEffectCombos l_ = new ListEffectCombos();
        l_.add(new ListEffectCombo(new StringList(""),Instances.newEffectCombo()));
        assertEq(0, l_.getVal(new StringList()).getEffectEndRound().size());
    }
    @Test
    public void test18() {
        Condition c_ = new Condition();
        c_.add(newCoords(1,1,1,1));
        c_.retainAllElements(new Condition());
        assertEq(0, c_.size());
    }
    @Test
    public void test19() {
        CoordssCoords  m_ = new CoordssCoords();
        m_.set(new Coords(),new Coords());
        assertEq(0, m_.size());
        assertNotNull(m_.getVal(new Coords()));
    }
    @Test
    public void test20() {
        NbEffectFighterCoordss  m_ = new NbEffectFighterCoordss();
        assertNotNull(m_.getVal(new NbEffectFighterCoords("")));
    }
    @Test
    public void test21() {
        TeamPositionsRate  m_ = new TeamPositionsRate();
        assertNotNull(m_.getVal(new TeamPosition("")));
    }
    @Test
    public void test22() {
        TeamPositionsStringMapTeamPositionsRate m_ = new TeamPositionsStringMapTeamPositionsRate();
        assertNotNull(m_.getVal(new TeamPosition("")));
    }
    @Test
    public void test23() {
        TeamPositionsMonteCarloNumber  m_ = new TeamPositionsMonteCarloNumber();
        assertNotNull(m_.getVal(new TeamPosition("")));
    }
    @Test
    public void test24() {
        MoveTeamPositionsAffectedMove  m_ = new MoveTeamPositionsAffectedMove();
        assertNotNull(m_.getVal(new MoveTeamPosition("")));
    }
    @Test
    public void test25() {
        MoveTeamPositionsActivityOfMove  m_ = new MoveTeamPositionsActivityOfMove();
        assertNotNull(m_.getVal(new MoveTeamPosition("")));
    }
    @Test
    public void test26() {
        MoveTeamPositionsStringList  m_ = new MoveTeamPositionsStringList();
        assertNotNull(m_.getVal(new MoveTeamPosition("")));
    }
    @Test
    public void test27() {
        MoveTeamPositionsShort  m_ = new MoveTeamPositionsShort();
        assertEq(0,m_.getVal(new MoveTeamPosition("")));
    }
    @Test
    public void test28() {
        MoveTeamPositionsBoolVal  m_ = new MoveTeamPositionsBoolVal();
        assertNotNull(m_.getVal(new MoveTeamPosition("")));
    }
    @Test
    public void test29() {
        MoveTargets m_ = new MoveTargets();
        assertNotNull(m_.getVal(new MoveTarget("")));
    }
    @Test
    public void test30() {
        CoordssCondition m_ = new CoordssCondition();
        assertNotNull(m_.getVal(new Coords()));
    }
    @Test
    public void test31() {
        CoordssBoolVal m_ = new CoordssBoolVal();
        assertNotNull(m_.getVal(new Coords()));
    }
    @Test
    public void test32() {
        CoordssCustListGenderName m_ = new CoordssCustListGenderName();
        assertNotNull(m_.getVal(new Coords()));
    }
    @Test
    public void test33() {
        CoordssHostPokemonDuo m_ = new CoordssHostPokemonDuo();
        assertNotNull(m_.getVal(new Coords()));
    }
    @Test
    public void test34() {
        PlaceLevelsCustListGenderName m_ = new PlaceLevelsCustListGenderName();
        assertNotNull(m_.getVal(new PlaceLevel()));
    }
    @Test
    public void test35() {
        PlaceLevelsInts m_ = new PlaceLevelsInts();
        assertNotNull(m_.getVal(new PlaceLevel()));
    }
    @Test
    public void test36() {
        ScreenCoordssCoords m_ = new ScreenCoordssCoords();
        assertNotNull(m_.getVal(new ScreenCoords()));
    }
    @Test
    public void test37() {
        ScreenCoordssCustListInt m_ = new ScreenCoordssCustListInt();
        assertNotNull(m_.getVal(new ScreenCoords()));
    }
    @Test
    public void test38() {
        ScreenCoordssInt m_ = new ScreenCoordssInt();
        assertNotNull(m_.getVal(new ScreenCoords()));
    }
    @Test
    public void test39() {
        NbFightCoordss m_ = new NbFightCoordss();
        assertNotNull(m_.getVal(new NbFightCoords("")));
    }
    @Test
    public void test40() {
        TargetCoordssRate m_ = new TargetCoordssRate();
        assertNotNull(m_.getVal(TargetCoords.def()));
    }
    @Test
    public void test41() {
        TypeStatistics m_ = new TypeStatistics();
        assertNotNull(m_.getVal(new TypeStatistic("",null)));
    }
    @Test
    public void test42() {
        PointEqList m_ = new PointEqList(new CollCapacity(0));
        assertEq(0, m_.size());
    }
    @Test
    public void test43() {
        assertFalse(PlaceLevelsCustListGenderName.match(new GenderName(Gender.NO_GENDER,"_"),new GenderName(Gender.NO_GENDER,"-")));
    }
    @Test
    public void test44() {
        assertFalse(PlaceLevelsCustListGenderName.match(new GenderName(Gender.all().first(),"_"),new GenderName(Gender.all().last(),"_")));
    }
    @Test
    public void test45() {
        assertSame(Sex.GIRL,Sex.getSexByName("GIRL",new SexListImpl()));
    }
    @Test
    public void test46() {
        assertSame(Sex.BOY,Sex.getSexByName("BOY",new SexListImpl()));
    }
    @Test
    public void test47() {
        assertNotNull(Sex.getSexByName("",new SexListImpl()));
    }
    @Test
    public void test48() {
        assertSame(Gender.NO_GENDER,Gender.getGenderByName("NO_GENDER"));
    }
    @Test
    public void test49() {
        assertSame(Gender.NO_GENDER,Gender.getGenderByName(""));
    }
    @Test
    public void test50() {
        assertSame(TargetChoice.NOTHING, TargetChoice.getTargetChoiceByName("NOTHING"));
    }
    @Test
    public void test51() {
        assertSame(TargetChoice.NOTHING,TargetChoice.getTargetChoiceByName(""));
    }
    @Test
    public void test52() {
        assertSame(EnvironmentType.NOTHING, EnvironmentType.getEnvByName("NOTHING"));
    }
    @Test
    public void test53() {
        assertSame(EnvironmentType.NOTHING,EnvironmentType.getEnvByName(""));
    }
    @Test
    public void test54() {
        assertSame(SelectedBoolean.YES_AND_NO, SelectedBoolean.getBoolByName("YES_AND_NO"));
    }
    @Test
    public void test55() {
        assertSame(SelectedBoolean.YES_AND_NO,SelectedBoolean.getBoolByName(""));
    }
    @Test
    public void test56() {
        assertSame(DifficultyModelLaw.UNIFORME, DifficultyModelLaw.getModelByName("UNIFORME"));
    }
    @Test
    public void test57() {
        assertSame(DifficultyModelLaw.UNIFORME,DifficultyModelLaw.getModelByName(""));
    }
    @Test
    public void test58() {
        assertSame(DifficultyWinPointsFight.TRES_FACILE, DifficultyWinPointsFight.getDiffWonPtsByName("TRES_FACILE"));
    }
    @Test
    public void test59() {
        assertSame(DifficultyWinPointsFight.TRES_FACILE,DifficultyWinPointsFight.getDiffWonPtsByName(""));
    }
    @Test
    public void test60() {
        assertSame(ExpType.E, ExpType.getExpTypeByName("E"));
    }
    @Test
    public void test61() {
        assertSame(ExpType.F, ExpType.getExpTypeByName("F"));
    }
    @Test
    public void test62() {
        assertSame(ExpType.L, ExpType.getExpTypeByName("L"));
    }
    @Test
    public void test63() {
        assertSame(ExpType.M, ExpType.getExpTypeByName("M"));
    }
    @Test
    public void test64() {
        assertSame(ExpType.P, ExpType.getExpTypeByName("P"));
    }
    @Test
    public void test65() {
        assertSame(ExpType.R, ExpType.getExpTypeByName("R"));
    }
    @Test
    public void test66() {
        assertSame(ExpType.M, ExpType.getExpTypeByName(""));
    }
    @Test
    public void test67() {
        TeamPositionsPairRates  m_ = new TeamPositionsPairRates();
        assertNotNull(m_.getVal(new TeamPosition("")).getFront());
        assertNotNull(m_.getVal(new TeamPosition("")).getBack());
    }
    @Test
    public void test68() {
        assertSame(GenderRepartition.NO_GENDER, GenderRepartition.getGenderRepartitionByName("NO_GENDER"));
    }
    @Test
    public void test69() {
        assertSame(GenderRepartition.NO_GENDER,GenderRepartition.getGenderRepartitionByName(""));
    }
    @Test
    public void test70() {
        assertSame(ConstValuesType.NOTHING, ConstValuesType.getConstValuesTypeByName("_"));
    }
    @Test
    public void test71() {
        assertSame(ConstValuesType.NOTHING,ConstValuesType.getConstValuesTypeByName(""));
    }
    @Test
    public void test72() {
        assertSame(ExchangeType.NOTHING, ExchangeType.getExchangeTypeByName("_"));
    }
    @Test
    public void test73() {
        assertSame(ExchangeType.NOTHING,ExchangeType.getExchangeTypeByName(""));
    }
    @Test
    public void test74() {
        assertSame(MoveChoiceRestrictionType.NOTHING, MoveChoiceRestrictionType.getMoveChoiceRestrictionTypeByName("_"));
    }
    @Test
    public void test75() {
        assertSame(MoveChoiceRestrictionType.NOTHING,MoveChoiceRestrictionType.getMoveChoiceRestrictionTypeByName(""));
    }
    @Test
    public void test76() {
        assertSame(MoveItemType.REUSE_LAST_OBJECT, MoveItemType.getMoveItemTypeTypeByName("_"));
    }
    @Test
    public void test77() {
        assertSame(MoveItemType.REUSE_LAST_OBJECT,MoveItemType.getMoveItemTypeTypeByName(""));
    }
    @Test
    public void test78() {
        assertSame(PointViewChangementType.NOTHING, PointViewChangementType.getPointViewChangementTypeByName("_"));
    }
    @Test
    public void test79() {
        assertSame(PointViewChangementType.NOTHING,PointViewChangementType.getPointViewChangementTypeByName(""));
    }
    @Test
    public void test80() {
        assertSame(SwitchType.NOTHING, SwitchType.getSwitchTypeByName("_"));
    }
    @Test
    public void test81() {
        assertSame(SwitchType.NOTHING,SwitchType.getSwitchTypeByName(""));
    }
    @Test
    public void test82() {
        assertSame(StatusType.INDIVIDUEL, StatusType.getStatusTypeByName("_"));
    }
    @Test
    public void test83() {
        assertSame(StatusType.INDIVIDUEL,StatusType.getStatusTypeByName(""));
    }
    @Test
    public void test84() {
        assertSame(SellType.ITEM, SellType.getSellTypeByName("_"));
    }
    @Test
    public void test85() {
        assertSame(SellType.ITEM,SellType.getSellTypeByName(""));
    }
    @Test
    public void test86() {
        assertSame(GeranceType.HEAL, GeranceType.getGeranceTypeByName("_"));
    }
    @Test
    public void test87() {
        assertSame(GeranceType.HEAL,GeranceType.getGeranceTypeByName(""));
    }
    @Test
    public void test88() {
        assertSame(FightType.NOTHING, FightType.getFightTypeByName("_"));
    }
    @Test
    public void test89() {
        assertSame(FightType.NOTHING,FightType.getFightTypeByName(""));
    }
    @Test
    public void test90() {
        assertSame(FightState.RIEN, FightState.getFightStateByName("_"));
    }
    @Test
    public void test91() {
        assertSame(FightState.RIEN,FightState.getFightStateByName(""));
    }
    @Test
    public void test92() {
        assertSame(SearchingMode.WHOLE_STRING, SearchingMode.getSearchingModeByName("0"));
    }
    @Test
    public void test93() {
        assertSame(SearchingMode.WHOLE_STRING,SearchingMode.getSearchingModeByName(""));
    }
    @Test
    public void test94() {
        assertFalse(ActionType.MOVE.getKeyAction().isEmpty());
    }
    private static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace((short) _place);
        begin_.affectInside(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex((byte) _level);
        begin_.getLevel().setPoint(newPoint(_x, _y));
        return begin_;
    }

    private static Point newPoint(int _x, int _y) {
        return new Point((short)_x, (short)_y);
    }

}
