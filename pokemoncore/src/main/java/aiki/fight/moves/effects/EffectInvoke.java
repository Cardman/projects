package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.levels.enums.EnvironmentType;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public class EffectInvoke extends Effect {

    private EnumMap<EnvironmentType,String> moveFctEnv;
    @CheckedData
    private boolean invokingMoveButUser;
    @CheckedData
    private boolean invokingTargetChosenMove;
    @CheckedData
    private boolean invokingUserMoveWhileSleep;
    @CheckedData
    private boolean invokingAllyMove;
    @CheckedData
    private boolean invokingTargetSuccesfulMove;
    @CheckedData
    private boolean invokingSufferedMove;
    private StringMap<String> invokingMoveByUserTypes;
    private StringList movesNotToBeInvoked;
    @CheckedData
    private Rate rateInvokationMove;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getMoves().containsAllAsKeys(movesNotToBeInvoked)) {
            throw new DataException();
        }
        if (!_data.getMoves().containsAllAsKeys(moveFctEnv.values())) {
            throw new DataException();
        }
        for (String k: invokingMoveByUserTypes.getKeys()) {
            if (!k.isEmpty() && !_data.getTypes().containsObj(k)) {
                throw new DataException();
            }
            if (!_data.getMoves().contains(invokingMoveByUserTypes.getVal(k))) {
                throw new DataException();
            }
        }
        if (!rateInvokationMove.isZeroOrGt()) {
            throw new DataException();
        }
    }

    public EnumMap<EnvironmentType,String> getMoveFctEnv() {
        return moveFctEnv;
    }
    public void setMoveFctEnv(EnumMap<EnvironmentType,String> _moveFctEnv) {
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
    public void setInvokingUserMoveWhileSleep(boolean _invokingUserMoveWhileSleep) {
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
    public void setInvokingTargetSuccesfulMove(boolean _invokingTargetSuccesfulMove) {
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
    public void setInvokingMoveByUserTypes(StringMap<String> _invokingMoveByUserTypes) {
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
