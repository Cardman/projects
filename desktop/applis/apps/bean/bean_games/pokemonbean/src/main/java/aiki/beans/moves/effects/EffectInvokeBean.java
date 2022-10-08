package aiki.beans.moves.effects;

import aiki.beans.PokemonStandards;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.map.levels.enums.EnvironmentType;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.StringList;
import code.util.StringMap;

public class EffectInvokeBean extends EffectBean {
    private DictionaryComparator<String, String> moveFctEnv;
    private StringList globalMoves;
    private boolean invokingMoveButUser;
    private boolean invokingTargetChosenMove;
    private boolean invokingUserMoveWhileSleep;
    private boolean invokingAllyMove;
    private boolean invokingTargetSuccesfulMove;
    private boolean invokingSufferedMove;
    private DictionaryComparator<String, String> invokingMoveByUserTypes;
    private StringList movesNotToBeInvoked;
    private Rate rateInvokationMove;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        EffectInvoke effect_ = (EffectInvoke) getEffect();
        DictionaryComparator<String, String> moveFctEnv_;
        moveFctEnv_ = DictionaryComparatorUtil.buildEnvStr(data_,getLanguage());
        for (EnvironmentType e: effect_.getMoveFctEnv().getKeys()) {
            moveFctEnv_.put(e.name(), effect_.getMoveFctEnv().getVal(e));
        }
        moveFctEnv = moveFctEnv_;
        StringList globalMoves_;
        globalMoves_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal eff_ = (EffectGlobal) e;
                if (!eff_.getInvokedMoveTerrain().isEmpty()) {
                    globalMoves_.add(m);
                }
            }
        }
        globalMoves_.removeDuplicates();
        globalMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        globalMoves = globalMoves_;
        invokingMoveButUser = effect_.getInvokingMoveButUser();
        invokingSufferedMove = effect_.getInvokingSufferedMove();
        invokingTargetChosenMove = effect_.getInvokingTargetChosenMove();
        invokingUserMoveWhileSleep = effect_.getInvokingUserMoveWhileSleep();
        invokingAllyMove = effect_.getInvokingAllyMove();
        invokingTargetSuccesfulMove = effect_.getInvokingTargetSuccesfulMove();
        rateInvokationMove = effect_.getRateInvokationMove();
        StringList movesNotToBeInvoked_;
        movesNotToBeInvoked_ = new StringList();
        for (String m: effect_.getMovesNotToBeInvoked()) {
            movesNotToBeInvoked_.add(m);
        }
        movesNotToBeInvoked_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesNotToBeInvoked = movesNotToBeInvoked_;
        DictionaryComparator<String, String> invokingMoveByUserTypes_;
        invokingMoveByUserTypes_ = DictionaryComparatorUtil.buildTypesStr(data_,getLanguage());
        for (String e: effect_.getInvokingMoveByUserTypes().getKeys()) {
            invokingMoveByUserTypes_.put(e, effect_.getInvokingMoveByUserTypes().getVal(e));
        }
        invokingMoveByUserTypes = invokingMoveByUserTypes_;
    }
    public String clickMoveFctEnv(int _index) {
        String st_ = moveFctEnv.getValue(_index);
        getForms().put(CST_MOVE, st_);
        return CST_MOVE;
    }
    public String getTrEnv(int _index) {
        DataBase data_ = getDataBase();
        AbsMap<EnvironmentType,String> translatedMoves_ = data_.getTranslatedEnvironment().getVal(getLanguage());
        EnvironmentType st_ = PokemonStandards.getEnvByName(moveFctEnv.getKey(_index));
        return translatedMoves_.getVal(st_);
    }
    public String getTrMoveFctEnv(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = moveFctEnv.getValue(_index);
        return translatedMoves_.getVal(st_);
    }
    public String getTrGlobalMoveFctEnv(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = globalMoves.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickGlobalMoveFctEnv(int _index) {
        String st_ = globalMoves.get(_index);
        getForms().put(CST_MOVE, st_);
        return CST_MOVE;
    }
    public String clickMoveNotInvok(int _index) {
        String st_ = movesNotToBeInvoked.get(_index);
        getForms().put(CST_MOVE, st_);
        return CST_MOVE;
    }
    public String getTrMoveNotInvok(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = movesNotToBeInvoked.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickMoveUserTypes(int _index) {
        String st_ = invokingMoveByUserTypes.getValue(_index);
        getForms().put(CST_MOVE, st_);
        return CST_MOVE;
    }
    public boolean isType(int _index) {
        String st_ = invokingMoveByUserTypes.getKey(_index);
        return !st_.isEmpty();
    }
    public String getTrUserTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String st_ = invokingMoveByUserTypes.getKey(_index);
        return translatedTypes_.getVal(st_);
    }
    public String getTrMoveUserTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = invokingMoveByUserTypes.getValue(_index);
        return translatedMoves_.getVal(st_);
    }

    public boolean getInvokingMoveButUser() {
        return invokingMoveButUser;
    }

    public boolean getInvokingTargetChosenMove() {
        return invokingTargetChosenMove;
    }

    public boolean getInvokingUserMoveWhileSleep() {
        return invokingUserMoveWhileSleep;
    }

    public boolean getInvokingAllyMove() {
        return invokingAllyMove;
    }

    public boolean getInvokingTargetSuccesfulMove() {
        return invokingTargetSuccesfulMove;
    }

    public boolean getInvokingSufferedMove() {
        return invokingSufferedMove;
    }

    public Rate getRateInvokationMove() {
        return rateInvokationMove;
    }

    public DictionaryComparator<String,String> getMoveFctEnv() {
        return moveFctEnv;
    }

    public StringList getGlobalMoves() {
        return globalMoves;
    }

    public DictionaryComparator<String,String> getInvokingMoveByUserTypes() {
        return invokingMoveByUserTypes;
    }

    public StringList getMovesNotToBeInvoked() {
        return movesNotToBeInvoked;
    }
}