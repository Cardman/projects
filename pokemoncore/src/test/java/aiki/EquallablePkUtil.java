package aiki;
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

public final class EquallablePkUtil {

    private static final String DIFF = " != ";

    private EquallablePkUtil() {
    }

    public static void assertEq(int[][] _expected, int[][] _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(equals(_expected, _result));
    }
    private static boolean equals(int[][] _expected, int[][] _result) {
        int expHeight_ = _expected.length;
        if (expHeight_ != _result.length) {
            return false;
        }
        for (int i = 0; i < expHeight_; i++) {
            int expWidth_ = _expected[i].length;
            if (expWidth_ != _result[i].length) {
                return false;
            }
            for (int j = 0; j < expWidth_; j++) {
                if (_expected[i][j] != _result[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected,DIFF,_result), StringList.quickEq(_expected, _result));
    }

    public static void assertEq(Integer _expected, Long _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), sameValue(_expected, _result));
    }

    public static void assertEq(Integer _expected, Integer _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), sameValue(_expected, _result));
    }

    public static void assertEq(Integer _expected, Short _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), sameValue(_expected, _result));
    }

    public static void assertEq(Integer _expected, Byte _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), sameValue(_expected, _result));
    }

    public static void assertEq(Byte _expected, Integer _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), sameValue(_expected, _result));
    }

    public static void assertEq(Byte _expected, Short _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), sameValue(_expected, _result));
    }
    public static void assertEq(Byte _expected, Byte _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), sameValue(_expected, _result));
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toNumberString(),DIFF,_result.toNumberString()), _expected.eq(_result));
    }
    
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toNumberString(),DIFF,_result.toNumberString()), _expected.eq(_result));
    }
    public static void assertEq(TeamPosition _expected, TeamPosition _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }
    public static void assertEq(TargetCoords _expected, TargetCoords _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }
    public static void assertEq(Coords _expected, Coords _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }
    public static void assertEq(Point _expected, Point _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }
    
    public static void assertEq(Dims _expected, Dims _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(ScreenCoords _expected, ScreenCoords _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
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

    private static boolean sameValue(Number _expected, Number _result) {
        if (_expected instanceof Double || _expected instanceof Float) {
            return _expected.doubleValue() == _result.doubleValue();
        }
        return _expected.longValue() == _result.longValue();
    }
}
