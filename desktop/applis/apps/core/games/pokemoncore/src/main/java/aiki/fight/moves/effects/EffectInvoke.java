package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.map.levels.enums.EnvironmentType;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;


public final class EffectInvoke extends Effect {

    private IdMap<EnvironmentType, String> moveFctEnv;
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
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),movesNotToBeInvoked,_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),moveFctEnv.values(),_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),invokingMoveByUserTypes.values(),_data);
        DataInfoChecker.checkStringListContainsOrEmpty(_data.getTypes(),invokingMoveByUserTypes.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZero(rateInvokationMove,_data);
    }

    public IdMap<EnvironmentType, String> getMoveFctEnv() {
        return moveFctEnv;
    }

    public void setMoveFctEnv(IdMap<EnvironmentType, String> _moveFctEnv) {
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
