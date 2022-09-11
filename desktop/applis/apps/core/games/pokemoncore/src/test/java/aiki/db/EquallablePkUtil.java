package aiki.db;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
import org.junit.Assert;

import aiki.fight.enums.EndTurnType;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.game.enums.InterfaceType;
import aiki.game.fight.TargetCoords;
import aiki.game.fight.TeamPosition;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.enums.IssueSimulation;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.util.Dims;
import aiki.map.util.ScreenCoords;
import aiki.util.Coords;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;

public abstract class EquallablePkUtil {

    private static final String DIFF = " != ";

    public static void assertNotNull(Object _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNull(Object _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Object _expected, Object _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(Object _expected, Object _result) {
        Assert.assertNotSame(_expected, _result);
    }


    public static void assertEq(int[][] _expected, int[][] _result) {
        Assert.assertNotNull(_result);
        Assert.assertArrayEquals(_expected,_result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, short _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, byte _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(byte _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(byte _expected, short _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(byte _expected, byte _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        int size_ = _expected.size();
        Assert.assertEquals(size_, _result.size());
        for (int i = 0; i < size_; i++) {
            assertEq(_expected.get(i),_result.get(i));
        }
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringUtil.concat(_expected.toNumberString(),DIFF,_result.toNumberString()), _expected.eq(_result));
    }
    
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringUtil.concat(_expected.toNumberString(),DIFF,_result.toNumberString()), _expected.eq(_result));
    }
    public static void assertEq(TeamPosition _expected, TeamPosition _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getTeam(),_result.getTeam());
        Assert.assertEquals(_expected.getPosition(),_result.getPosition());
    }
    public static void assertEq(TargetCoords _expected, TargetCoords _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getTeam(),_result.getTeam());
        Assert.assertEquals(_expected.getPosition(),_result.getPosition());
    }
    public static void assertEq(Coords _expected, Coords _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getNumberPlace(),_result.getNumberPlace());
        Assert.assertEquals(_expected.isInside(),_result.isInside());
        if (_expected.isInside()) {
            Assert.assertEquals(_expected.getInsideBuilding().getx(),_result.getInsideBuilding().getx());
            Assert.assertEquals(_expected.getInsideBuilding().gety(),_result.getInsideBuilding().gety());
        }
        Assert.assertEquals(_expected.getLevel().getLevelIndex(),_result.getLevel().getLevelIndex());
        Assert.assertEquals(_expected.getLevel().getPoint().getx(),_result.getLevel().getPoint().getx());
        Assert.assertEquals(_expected.getLevel().getPoint().gety(),_result.getLevel().getPoint().gety());
    }
    public static void assertEq(Point _expected, Point _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getx(),_result.getx());
        Assert.assertEquals(_expected.gety(),_result.gety());
    }
    
    public static void assertEq(Dims _expected, Dims _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getHeight(),_result.getHeight());
        Assert.assertEquals(_expected.getWidth(),_result.getWidth());
    }

    public static void assertEq(ScreenCoords _expected, ScreenCoords _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getXcoords(),_result.getXcoords());
        Assert.assertEquals(_expected.getYcoords(),_result.getYcoords());
    }

    public static void assertEq(EndTurnType _expected, EndTurnType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(RelationType _expected, RelationType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(GenderRepartition _expected, GenderRepartition _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(Gender _expected, Gender _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(TargetChoice _expected, TargetChoice _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(InterfaceType _expected, InterfaceType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(Direction _expected, Direction _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(FightState _expected, FightState _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(ActionType _expected, ActionType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(FightType _expected, FightType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(Statistic _expected, Statistic _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(IssueSimulation _expected, IssueSimulation _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(EnvironmentType _expected, EnvironmentType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(PointViewChangementType _expected, PointViewChangementType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void initDefaultConsts(String _ballDef, String _rateCatching,
                                  String _rateFleeing, String _rateBoost,
                                  String _rateBoostCriticalHit, String _damageFormula,
                                  String _defMove, String _defaultEggGoup, DataBase _db) {
        _db.setBallDef(_ballDef);
        _db.setRateCatching(_rateCatching);
        _db.setRateFleeing(_rateFleeing);
        _db.setRateBoost(_rateBoost);
        _db.setRateBoostCriticalHit(_rateBoostCriticalHit);
        _db.setDamageFormula(_damageFormula);
        _db.setDefMove(_defMove);
        _db.setDefaultEggGroup(_defaultEggGoup);
    }
}
