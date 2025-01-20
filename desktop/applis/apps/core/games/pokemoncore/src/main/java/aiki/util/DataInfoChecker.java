package aiki.util;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Item;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.Status;
import aiki.fight.status.StatusType;
import aiki.fight.util.StatisticCategory;
import aiki.fight.util.StatisticStatus;
import aiki.fight.util.StatisticType;
import aiki.fight.util.WeatherType;
import aiki.map.levels.Level;
import aiki.map.levels.Link;
import aiki.map.tree.BuildingArea;
import aiki.map.tree.LevelArea;
import aiki.map.tree.Tree;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.IntMonteCarlo;
import code.util.*;
import code.util.core.StringUtil;

public final class DataInfoChecker {
    private DataInfoChecker() {
    }

    public static StringMap<Status> filterStatusExclude(DataBase _data, StatusType _value) {
        StringMap<Status> res_ = new StringMap<Status>();
        for (EntryCust<String,Status> e:_data.getStatus().entryList()) {
            if (e.getValue().getStatusType() != _value) {
                res_.addEntry(e.getKey(),e.getValue());
            }
        }
        return res_;
    }

    public static StringMap<Item> itemsBall(DataBase _data) {
        StringMap<Item> res_ = new StringMap<Item>();
        for (EntryCust<String, Item> e:_data.getItems().entryList()) {
            if (e.getValue() instanceof Ball) {
                res_.addEntry(e.getKey(),e.getValue());
            }
        }
        return res_;
    }

    public static void checkStatisticListContains(CustList<Statistic> _possible, CustList<Statistic> _field, DataBase _data) {
        if (!Statistic.containsAll(_possible,_field)) {
            _data.setError(true);
        }
    }

    public static void checkStatisticListContains(CustList<Statistic> _possible, Statistic _field, DataBase _data) {
        if (!Statistic.containsStatistic(_possible,_field)) {
            _data.setError(true);
        }
    }

    public static void checkStringListContains(CustList<String> _possible, CustList<String> _field, DataBase _data) {
        if (!StringUtil.containsAllObj(_possible,_field)) {
            _data.setError(true);
        }
    }

    public static void checkStringListContainsOrEmpty(CustList<String> _possible, CustList<String> _field, DataBase _data) {
        for (String v: _field) {
            checkStringListContainsOrEmpty(_possible,v,_data);
        }
    }

    public static void checkStringListContainsOrEmpty(CustList<String> _possible, String _field, DataBase _data) {
        if (!_field.isEmpty()&&!StringUtil.contains(_possible,_field)) {
            _data.setError(true);
        }
    }

    public static void checkStringListContains(CustList<String> _possible, String _field, DataBase _data) {
        if (!StringUtil.contains(_possible,_field)) {
            _data.setError(true);
        }
    }

    public static void checkEvents(IntMonteCarlo _law, DataBase _data) {
        if (!_law.checkEvents()) {
            _data.setError(true);
        }
    }

    public static void checkIntegers(CustList<Rate> _value, DataBase _data) {
        for (Rate r: _value) {
            checkInteger(r,_data);
        }
    }

    public static void checkInteger(Rate _value, DataBase _data) {
        if (!_value.isInteger()) {
            _data.setError(true);
        }
    }

    public static void checkPositiveRates(CustList<Rate> _value, DataBase _data) {
        for (Rate r: _value) {
            checkPositive(r,_data);
        }
    }

    public static void checkPositiveOrZeroRates(CustList<Rate> _value, DataBase _data) {
        for (Rate r: _value) {
            checkPositiveOrZero(r,_data);
        }
    }

    public static void checkPositive(Rate _value, DataBase _data) {
        checkNonZero(_value, _data);
        checkPositiveOrZero(_value, _data);
    }

    public static void checkNonZero(Rate _value, DataBase _data) {
        if (_value.isZero()) {
            _data.setError(true);
        }
    }

    public static void checkZero(Rate _value, DataBase _data) {
        if (!_value.isZero()) {
            _data.setError(true);
        }
    }

    public static void checkLowerOneEq(Rate _value, DataBase _data) {
        if (Rate.strGreater(_value,Rate.one())) {
            _data.setError(true);
        }
    }
    public static void checkPositiveOrZero(Rate _value, DataBase _data) {
        if (!_value.isZeroOrGt()) {
            _data.setError(true);
        }
    }

    public static void checkPositiveOrZero(LgInt _value, DataBase _data) {
        if (!_value.isZeroOrGt()) {
            _data.setError(true);
        }
    }
    public static void checkPositiveLongs(CustList<Long> _value, DataBase _data) {
        for (long s : _value) {
            checkPositive(s,_data);
        }
    }

    public static void checkPositiveOrZeroLongs(CustList<Long> _value, DataBase _data) {
        for (long s : _value) {
            checkPositiveOrZero(s,_data);
        }
    }

    public static void checkPositive(long _value, DataBase _data) {
        checkNonZero(_value, _data);
        checkPositiveOrZero(_value, _data);
    }

    public static void checkNegativeBytes(CustList<Long> _value, DataBase _data) {
        for (long s : _value) {
            checkNegative(s,_data);
        }
    }

    public static void checkNegative(long _value, DataBase _data) {
        checkNonZero(_value, _data);
        checkNegativeOrZero(_value, _data);
    }

    public static void checkNonZero(long _value, DataBase _data) {
        if (_value == 0) {
            _data.setError(true);
        }
    }

    public static void checkZero(long _value, DataBase _data) {
        if (_value != 0) {
            _data.setError(true);
        }
    }

    public static void checkPositiveOrZero(long _value, DataBase _data) {
        if (_value < 0) {
            _data.setError(true);
        }
    }

    public static void checkNegativeOrZero(long _value, DataBase _data) {
        if (_value > 0) {
            _data.setError(true);
        }
    }

    public static void checkLower(long _min,long _value, DataBase _data) {
        if (_value < _min) {
            _data.setError(true);
        }
    }

    public static void checkGreater(long _max,long _value, DataBase _data) {
        if (_value > _max) {
            _data.setError(true);
        }
    }

    public static void checkEmptyStatisticList(CustList<Statistic> _possible, DataBase _data) {
        if (!_possible.isEmpty()) {
            _data.setError(true);
        }
    }

    public static void checkTargetNot(TargetChoice _exclude, TargetChoice _field, DataBase _data) {
        if (_exclude == _field) {
            _data.setError(true);
        }
    }

    public static void checkTargetWithChoice(TargetChoice _field, DataBase _data) {
        if (!_field.isWithChoice()) {
            _data.setError(true);
        }
    }

    public static void checkTargets(TargetChoice _must1, TargetChoice _must2, TargetChoice _field, DataBase _data) {
        if (_must1 != _field && _must2 != _field) {
            _data.setError(true);
        }
    }

    public static void checkTargets(TargetChoice _must1, TargetChoice _must2, TargetChoice _must3, TargetChoice _field, DataBase _data) {
        if (_must1 != _field && _must2 != _field && _must3 != _field) {
            _data.setError(true);
        }
    }

    public static void checkTarget(TargetChoice _must, TargetChoice _field, DataBase _data) {
        if (_must != _field) {
            _data.setError(true);
        }
    }

    public static void checkStatistics(Statistic _must1, Statistic _must2, Statistic _field, DataBase _data) {
        if (_must1 != _field && _must2 != _field) {
            _data.setError(true);
        }
    }

    public static void checkEmptyString(String _value, DataBase _data) {
        if (!_value.isEmpty()) {
            _data.setError(true);
        }
    }

    public static void checkEmptyNotString(String _value, DataBase _data) {
        if (_value.isEmpty()) {
            _data.setError(true);
        }
    }

    public static void checkEmptyStringList(CustList<String> _value, DataBase _data) {
        if (!_value.isEmpty()) {
            _data.setError(true);
        }
    }

    public static void checkEmptyInts(Ints _value, DataBase _data) {
        if (!_value.isEmpty()) {
            _data.setError(true);
        }
    }

    public static void checkEmptyNotStringList(CustList<String> _value, DataBase _data) {
        if (_value.isEmpty()) {
            _data.setError(true);
        }
    }

    public static void checkStringListContainsAll(CustList<String> _possible, CustList<StringList> _lists, DataBase _data) {
        for (StringList k : _lists) {
            checkStringListContains(_possible,k, _data);
        }
    }

    public static void checkStatisticStatus(CustList<StatisticStatus> _field, DataBase _data) {
        for (StatisticStatus t : _field) {
            checkStatisticListContains(Statistic.getStatisticsWithBoost(),t.getStatistic(), _data);
            checkStringListContains(_data.getStatus().getKeys(), t.getStatus(), _data);
        }
    }

    public static void checkStatisticCategory(StringList _possible, CustList<StatisticCategory> _field, DataBase _data) {
        for (StatisticCategory k : _field) {
            checkStatisticListContains(Statistic.getStatisticsWithBoost(),k.getStatistic(), _data);
            checkStringListContains(_possible, k.getCategory(), _data);
        }
    }

    public static void checkWeatherTypes(CustList<WeatherType> _list, DataBase _data) {
        for (WeatherType t : _list) {
            checkStringListContainsOrEmpty(_data.getMovesEffectGlobalWeather(), t.getWeather(), _data);
            checkStringListContains(_data.getTypes(), t.getType(), _data);
        }
    }

    public static void checkStatisticType(CustList<StatisticType> _list, DataBase _data) {
        for (StatisticType k : _list) {
            checkStatisticListContains(Statistic.getStatisticsWithBoost(),k.getStatistic(), _data);
            checkStringListContains(_data.getTypes(), k.getType(), _data);
        }
    }

    public static void checkIfNotEmptyListHasDef(StringMap<String> _map, DataBase _data) {
        if (!_map.isEmpty() && !_map.contains(DataBase.EMPTY_STRING)) {
            _data.setError(true);
        }
    }

    public static void checkShortsContains(CustList<Integer> _list, CustList<Integer> _field, DataBase _data) {
        for (int s: _field) {
            checkShortsContains(_list,s,_data);
        }
    }


    public static void checkShortsContains(CustList<Integer> _list, int _field, DataBase _data) {
        for (int s: _list) {
            if (s == _field) {
                return;
            }
        }
        _data.setError(true);
    }

    public static void checkLink(DataBase _data, Link _level) {
        if (!_level.isValid(_data)) {
            _data.setError(true);
        }
    }

    public static void checkEmpty(DataBase _data, Level _level, NullablePoint _key) {
        if (!_key.patchValid()||!_level.isEmpty(_key.getPoint())) {
            _data.setError(true);
        }
    }

    public static void checkEmptyForAdding(DataBase _data, Level _level, Point _key) {
        if (!_level.isEmptyForAdding(_key)) {
            _data.setError(true);
        }
    }

    public static void checkKey(DataBase _data, Tree _tree, Coords _key) {
        if (!_tree.isValid(_key, true)) {
            _data.setError(true);
        }
    }

    public static void checkKey(DataBase _data, LevelArea _level, Point _key, boolean _accessible) {
        checkKey(_data, _level, new NullablePoint(_key), _accessible);
    }
    public static void checkKey(DataBase _data, LevelArea _level, NullablePoint _key, boolean _accessible) {
        if (!_key.patchValid()||!_level.isValid(_key.getPoint(), _accessible)) {
            _data.setError(true);
        }
    }

    public static void checkKey(DataBase _data, BuildingArea _building, NullablePoint _key) {
        if (!_key.patchValid()||!_building.isValid(_key.getPoint())) {
            _data.setError(true);
        }
//        if (_building == null||!_building.isValid(_key, _accessible)) {
//            _data.setError(true);
//        }
    }
}
