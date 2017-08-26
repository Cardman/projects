package aiki;
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
import code.util.StringList;

public final class EquallablePkUtil {
    private static final String EXPECTED_NULL = "the result is not null.";
    private static final String EXPECTED_NOT_NULL = "the result is null.";

    private static final String EXPECTED_BUT_WAS = "expected:{0} but was:{1}";

    private EquallablePkUtil() {
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
            if (_expected == null) {
                throw new AssertionError(EXPECTED_NULL);
            }
            throw new AssertionError(EXPECTED_NOT_NULL);
        }
        return false;
    }

    private static void assertError(Object _expected, Object _result) {
        String message_;
        message_ = StringList.simpleFormat(EXPECTED_BUT_WAS, _expected, _result);
        throw new AssertionError(message_);
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
