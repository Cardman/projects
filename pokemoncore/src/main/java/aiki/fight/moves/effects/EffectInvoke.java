package aiki.fight.moves.effects;

import aiki.DataBase;
import aiki.map.levels.enums.EnvironmentType;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public final class EffectInvoke extends Effect {

    private EnumMap<EnvironmentType, String> moveFctEnv;
    private boolean invokingMoveButUser;
    private boolean invokingTargetChosenMove;
    private boolean invokingUserMoveWhileSleep;
    private boolean invokingAllyMove;
    private boolean invokingTargetSuccesfulMove;
    private boolean invokingSufferedMove;
    private StringMap<String> invokingMoveByUserTypes;
    private StringList movesNotToBeInvoked;
    private Rate rateInvokationMove;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getMoves().containsAllAsKeys(movesNotToBeInvoked)) {
            _data.setError(true);
            return;

        }
        for (EntryCust<EnvironmentType, String> e : moveFctEnv.entryList()) {
            if (!EnvironmentType.getEnvironments().containsObj(e.getKey())) {
                _data.setError(true);
                return;

            }
            if (!_data.getMoves().contains(e.getValue())) {
                _data.setError(true);
                return;

            }
        }
        for (String k : invokingMoveByUserTypes.getKeys()) {
            if (!k.isEmpty() && !_data.getTypes().containsObj(k)) {
                _data.setError(true);
                return;

            }
            if (!_data.getMoves().contains(invokingMoveByUserTypes.getVal(k))) {
                _data.setError(true);
                return;

            }
        }
        if (!rateInvokationMove.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
    }

    public EnumMap<EnvironmentType, String> getMoveFctEnv() {
        return moveFctEnv;
    }

    public void setMoveFctEnv(EnumMap<EnvironmentType, String> _moveFctEnv) {
        moveFctEnv = _moveFctEnv;
    }

    public boolean getInvokingMoveButUser() {
        return invokingMoveButUser;
    }

    public void setInvokingMoveButUser(boolean _invokingMoveButUser) {
        invokingMoveButUser = _invokingMoveButUser;
    }

    public boolean getInvokingTargetChosenMove() {
        return invokingTargetChosenMove;
    }

    public void setInvokingTargetChosenMove(boolean _invokingTargetChosenMove) {
        invokingTargetChosenMove = _invokingTargetChosenMove;
    }

    public boolean getInvokingUserMoveWhileSleep() {
        return invokingUserMoveWhileSleep;
    }

    public void setInvokingUserMoveWhileSleep(
            boolean _invokingUserMoveWhileSleep) {
        invokingUserMoveWhileSleep = _invokingUserMoveWhileSleep;
    }

    public boolean getInvokingAllyMove() {
        return invokingAllyMove;
    }

    public void setInvokingAllyMove(boolean _invokingAllyMove) {
        invokingAllyMove = _invokingAllyMove;
    }

    public boolean getInvokingTargetSuccesfulMove() {
        return invokingTargetSuccesfulMove;
    }

    public void setInvokingTargetSuccesfulMove(
            boolean _invokingTargetSuccesfulMove) {
        invokingTargetSuccesfulMove = _invokingTargetSuccesfulMove;
    }

    public boolean getInvokingSufferedMove() {
        return invokingSufferedMove;
    }

    public void setInvokingSufferedMove(boolean _invokingSufferedMove) {
        invokingSufferedMove = _invokingSufferedMove;
    }

    public StringMap<String> getInvokingMoveByUserTypes() {
        return invokingMoveByUserTypes;
    }

    public void setInvokingMoveByUserTypes(
            StringMap<String> _invokingMoveByUserTypes) {
        invokingMoveByUserTypes = _invokingMoveByUserTypes;
    }

    public StringList getMovesNotToBeInvoked() {
        return movesNotToBeInvoked;
    }

    public void setMovesNotToBeInvoked(StringList _movesNotToBeInvoked) {
        movesNotToBeInvoked = _movesNotToBeInvoked;
    }

    public Rate getRateInvokationMove() {
        return rateInvokationMove;
    }

    public void setRateInvokationMove(Rate _rateInvokationMove) {
        rateInvokationMove = _rateInvokationMove;
    }
}
