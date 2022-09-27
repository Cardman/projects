package aiki.db;

import aiki.facade.enums.SelectedBoolean;
import aiki.fight.Combos;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.enums.EndTurnType;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.status.Status;
import aiki.game.Game;
import aiki.game.HostPokemonDuo;
import aiki.game.enums.InterfaceType;
import aiki.game.fight.*;
import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.animations.AutoEffectKind;
import aiki.game.fight.animations.EffectKind;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.enums.IssueSimulation;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.MoveTarget;
import aiki.game.fight.util.MovesAbilities;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.Inventory;
import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.map.DataMap;
import aiki.map.buildings.Building;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.Link;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.BuildingArea;
import aiki.map.tree.util.Dims;
import aiki.map.util.ScreenCoords;
import aiki.map.util.TileMiniMap;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.IntMonteCarlo;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;
import code.util.ints.Countable;
import org.junit.Assert;

public abstract class EquallablePkUtil {

    private static final String DIFF = " != ";
    public static void assertNotNull(Dims _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Sex _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(MoveTarget _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Link _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(HostPokemonDuo _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(int[][] _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(TrainerMultiFights _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Person _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(CharacterInRoadCave _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(EffectWhileSending _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Fighter _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Fight _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Team _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Player _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Difficulty _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Game _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(DataMap _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Item _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Inventory _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Combos _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(MovesAbilities _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(ChoiceOfEvolutionAndMoves _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Building _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(DualFight _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Point _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(ActivityOfMove _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Block _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(AbstractAction _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(AffectedMove _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(IntMonteCarlo _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(Countable _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(TeamPosition _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(BoolVal _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(PkTrainer _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(WildPk _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Coords _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(TileMiniMap _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Rate _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(ExchangedData _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(MoveData _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(BuildingArea _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Status _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(UsablePokemon _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(TargetCoords _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNull(Countable _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(TargetCoords _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(AbstractAction _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(UsablePokemon _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Sex _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(LevelPoint _value) {
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
    public static void assertSame(ExpType _expected, ExpType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(DifficultyWinPointsFight _expected, DifficultyWinPointsFight _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(DifficultyModelLaw _expected, DifficultyModelLaw _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(SelectedBoolean _expected, SelectedBoolean _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(EnvironmentType _expected, EnvironmentType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(TargetChoice _expected, TargetChoice _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Gender _expected, Gender _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Sex _expected, Sex _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertSame(Block _expected, Block _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(IssueSimulation _expected, IssueSimulation _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(AreaApparition _expected, AreaApparition _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(UsablePokemon _expected, UsablePokemon _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Fighter _expected, Fighter _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(AutoEffectKind _expected, AutoEffectKind _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(EffectKind _expected, EffectKind _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(Gender _expected, Gender _result) {
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
        Assert.assertEquals(_expected.getList(), _result.getList());
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
        assertTrue(_expected.eq(_result));
//        Assert.assertEquals(_expected.getNumberPlace(),_result.getNumberPlace());
//        Assert.assertEquals(_expected.isInside(),_result.isInside());
//        if (_expected.isInside()) {
//            Assert.assertEquals(_expected.getInsideBuilding().getx(),_result.getInsideBuilding().getx());
//            Assert.assertEquals(_expected.getInsideBuilding().gety(),_result.getInsideBuilding().gety());
//        }
//        Assert.assertEquals(_expected.getLevel().getLevelIndex(),_result.getLevel().getLevelIndex());
//        Assert.assertEquals(_expected.getLevel().getPoint().getx(),_result.getLevel().getPoint().getx());
//        Assert.assertEquals(_expected.getLevel().getPoint().gety(),_result.getLevel().getPoint().gety());
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
