package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStringEnv;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.map.levels.enums.EnvironmentType;

public class EffectInvokeBean extends EffectBean {

    @Accessible
    private TreeMap<EnvironmentType, String> moveFctEnv;

    @Accessible
    private StringList globalMoves;

    @Accessible
    private boolean invokingMoveButUser;

    @Accessible
    private boolean invokingTargetChosenMove;

    @Accessible
    private boolean invokingUserMoveWhileSleep;

    @Accessible
    private boolean invokingAllyMove;

    @Accessible
    private boolean invokingTargetSuccesfulMove;

    @Accessible
    private boolean invokingSufferedMove;

    @Accessible
    private TreeMap<String, String> invokingMoveByUserTypes;

    @Accessible
    private StringList movesNotToBeInvoked;

    @Accessible
    private Rate rateInvokationMove;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        EffectInvoke effect_ = (EffectInvoke) getEffect();
        EnumMap<EnvironmentType,String> translatedEnvironments_ = data_.getTranslatedEnvironment().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        TreeMap<EnvironmentType, String> moveFctEnv_;
        moveFctEnv_ = new TreeMap<EnvironmentType, String>(new ComparatorTrStringEnv(translatedEnvironments_));
        for (EnvironmentType e: effect_.getMoveFctEnv().getKeys()) {
            moveFctEnv_.put(e, effect_.getMoveFctEnv().getVal(e));
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
                if (eff_.getInvokedMoveTerrain().isEmpty()) {
                    continue;
                }
                globalMoves_.add(m);
            }
        }
        globalMoves_.removeDuplicates();
        globalMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
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
        movesNotToBeInvoked_.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesNotToBeInvoked = movesNotToBeInvoked_;
        TreeMap<String, String> invokingMoveByUserTypes_;
        invokingMoveByUserTypes_ = new TreeMap<String, String>(new ComparatorTrStrings(translatedTypes_));
        for (String e: effect_.getInvokingMoveByUserTypes().getKeys()) {
            invokingMoveByUserTypes_.put(e, effect_.getInvokingMoveByUserTypes().getVal(e));
        }
        invokingMoveByUserTypes = invokingMoveByUserTypes_;
    }

    @Accessible
    private String clickMoveFctEnv(Long _index) {
        String st_ = moveFctEnv.getValue(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrEnv(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<EnvironmentType,String> translatedMoves_ = data_.getTranslatedEnvironment().getVal(getLanguage());
        EnvironmentType st_ = moveFctEnv.getKey(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String getTrMoveFctEnv(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = moveFctEnv.getValue(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String getTrGlobalMoveFctEnv(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = globalMoves.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String clickGlobalMoveFctEnv(Long _index) {
        String st_ = globalMoves.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String clickMoveNotInvok(Long _index) {
        String st_ = movesNotToBeInvoked.get(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private String getTrMoveNotInvok(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = movesNotToBeInvoked.get(_index.intValue());
        return translatedMoves_.getVal(st_);
    }

    @Accessible
    private String clickMoveUserTypes(Long _index) {
        String st_ = invokingMoveByUserTypes.getValue(_index.intValue());
        getForms().put(MOVE, st_);
        return MOVE;
    }

    @Accessible
    private boolean isType(Long _index) {
        String st_ = invokingMoveByUserTypes.getKey(_index.intValue());
        return !st_.isEmpty();
    }

    @Accessible
    private String getTrUserTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String st_ = invokingMoveByUserTypes.getKey(_index.intValue());
        return translatedTypes_.getVal(st_);
    }

    @Accessible
    private String getTrMoveUserTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = invokingMoveByUserTypes.getValue(_index.intValue());
        return translatedMoves_.getVal(st_);
    }
}
