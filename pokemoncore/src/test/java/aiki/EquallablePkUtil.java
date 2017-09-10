package aiki;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
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
import aiki.util.Coords;
import aiki.util.Point;

public final class EquallablePkUtil {

    private static final String DIFF = " != ";

    private EquallablePkUtil() {
    }

    public static void assertEq(String _expected, Object _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (StringList.quickEq(_expected, (String)_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(Number _expected, Number _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (sameValue(_expected, _result)) {
            return;
        }
        assertError(_expected, _result);
    }
    public static void assertEq(StringList _expected, StringList _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    public static void assertEq(Rate _expected, Rate _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    
    public static void assertEq(LgInt _expected, LgInt _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    public static void assertEq(TeamPosition _expected, TeamPosition _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    public static void assertEq(TargetCoords _expected, TargetCoords _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    public static void assertEq(Coords _expected, Coords _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    public static void assertEq(Point _expected, Point _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(Dims _expected, Dims _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(EndTurnType _expected, EndTurnType _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(RelationType _expected, RelationType _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(GenderRepartition _expected, GenderRepartition _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(Gender _expected, Gender _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(TargetChoice _expected, TargetChoice _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(InterfaceType _expected, InterfaceType _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(Direction _expected, Direction _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(FightState _expected, FightState _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(ActionType _expected, ActionType _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(FightType _expected, FightType _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(Statistic _expected, Statistic _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(IssueSimulation _expected, IssueSimulation _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(EnvironmentType _expected, EnvironmentType _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(PointViewChangementType _expected, PointViewChangementType _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }

    private static boolean checkNullity(Object _expected, Object _result) {
        if (allNull(_expected, _result)) {
            return true;
        }
        if (onlyOneNull(_expected, _result)) {
            throw new AssertionError(null);
        }
        return false;
    }
    private static boolean sameValue(Object _expected, Object _result) {
        boolean second_ = false;
        if (_result instanceof Number) {
            second_ = true;
        }
        if (_result instanceof Character) {
            second_ = true;
        }
        if (!second_) {
            return false;
        }
        if (_expected instanceof Double || _expected instanceof Float) {
            if (_result instanceof Number) {
                return ((Number)_expected).doubleValue() == ((Number)_result).doubleValue();
            }
            return false;
        }
        if (_expected instanceof Number && _result instanceof Number) {
            return ((Number)_expected).longValue() == ((Number)_result).longValue();
        }
        if (_expected instanceof Number && _result instanceof Character) {
            return ((Number)_expected).longValue() == ((Character)_result).charValue();
        }
        if (_expected instanceof Character && _result instanceof Number) {
            return ((Character)_expected).charValue() == ((Number)_result).longValue();
        }
        return ((Character)_expected).charValue() == ((Character)_result).charValue();
    }
    private static void assertError(Object _expected, Object _result) {
        throw new AssertionError(_expected+DIFF+_result);
    }

    private static boolean allNull(Object _expected, Object _result) {
        return _expected == null && _result == null;
    }

    private static boolean onlyOneNull(Object _expected, Object _result) {
        if (_expected == null) {
            return _result != null;
        }
        return _result == null;
    }
}
