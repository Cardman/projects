package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.map.levels.enums.EnvironmentType;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesDataEffinvoke;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;

public class EffectInvokeBean extends EffectBean {
    private DictionaryComparator<TranslatedKey, TranslatedKey> moveFctEnv;
    private CustList<TranslatedKey> globalMoves;
    private boolean invokingMoveButUser;
    private boolean invokingTargetChosenMove;
    private boolean invokingUserMoveWhileSleep;
    private boolean invokingAllyMove;
    private boolean invokingTargetSuccesfulMove;
    private boolean invokingSufferedMove;
    private DictionaryComparator<TranslatedKey, TranslatedKey> invokingMoveByUserTypes;
    private CustList<TranslatedKey> movesNotToBeInvoked;
    private Rate rateInvokationMove;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        EffectInvoke effect_ = (EffectInvoke) getEffect();
        DictionaryComparator<TranslatedKey, TranslatedKey> moveFctEnv_;
        moveFctEnv_ = DictionaryComparatorUtil.buildEnvStr();
        for (EnvironmentType e: effect_.getMoveFctEnv().getKeys()) {
            moveFctEnv_.put(buildEnv(getFacade(),e), buildMv(getFacade(),effect_.getMoveFctEnv().getVal(e)));
        }
        moveFctEnv = moveFctEnv_;
        StringList globalMoves_ = globalMoves(data_);
        globalMoves_.removeDuplicates();
        globalMoves = listTrStringsMv(globalMoves_,getFacade());
        invokingMoveButUser = effect_.getInvokingMoveButUser();
        invokingSufferedMove = effect_.getInvokingSufferedMove();
        invokingTargetChosenMove = effect_.getInvokingTargetChosenMove();
        invokingUserMoveWhileSleep = effect_.getInvokingUserMoveWhileSleep();
        invokingAllyMove = effect_.getInvokingAllyMove();
        invokingTargetSuccesfulMove = effect_.getInvokingTargetSuccesfulMove();
        rateInvokationMove = effect_.getRateInvokationMove();
        movesNotToBeInvoked = listTrStringsMv(effect_.getMovesNotToBeInvoked(),getFacade());
        invokingMoveByUserTypes = invokingMoveByUserTypes(effect_);
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_EFFECT);
    }

    @Override
    public void buildSubEff() {
        displayBoolTrue(toInt(getInvokingMoveButUser()), MessagesPkBean.EFF_INVOKE, MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_BUT_USER);
        displayBoolTrue(toInt(getInvokingTargetChosenMove()), MessagesPkBean.EFF_INVOKE, MessagesDataEffinvoke.M_P_50_INVOKE_TARGET_CHOSEN_MOVE);
        displayBoolTrue(toInt(getInvokingUserMoveWhileSleep()), MessagesPkBean.EFF_INVOKE, MessagesDataEffinvoke.M_P_50_INVOKE_USER_MOVE_WHILE_SLEEP);
        displayBoolTrue(toInt(getInvokingAllyMove()), MessagesPkBean.EFF_INVOKE, MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_PART);
        displayBoolTrue(toInt(getInvokingTargetSuccesfulMove()), MessagesPkBean.EFF_INVOKE, MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_SUCCESS_TARGET);
        displayBoolTrue(toInt(getInvokingSufferedMove()), MessagesPkBean.EFF_INVOKE, MessagesDataEffinvoke.M_P_50_INVOKE_SUFFERED_MOVE);
        displayIntDef(getRateInvokationMove(), MessagesPkBean.EFF_INVOKE, MessagesDataEffinvoke.M_P_50_RATE_INVOKE_MOVE);
        new BeanDisplayMap<TranslatedKey,TranslatedKey>(new BeanDisplayTranslatedKey(),new BeanDisplayTranslatedKey()).displayGrid(this,getMoveFctEnv(),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_MOVE_FCT_ENV,MessagesDataEffinvoke.M_P_50_ENV_TYPE,MessagesDataEffinvoke.M_P_50_INVOKED_MOVE);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getGlobalMoves(), NumberUtil.signum(getMoveFctEnv().size()),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_MOVE_FCT_ENV_EXC);
        new BeanDisplayMap<TranslatedKey,TranslatedKey>(new BeanDisplayTranslatedKey(MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_OTHER_OWNED_TYPE),new BeanDisplayTranslatedKey()).displayGrid(this,getInvokingMoveByUserTypes(),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_INVOKE_MOVE_TYPE,MessagesDataEffinvoke.M_P_50_OWNED_TYPE,MessagesDataEffinvoke.M_P_50_INVOKED_MOVE);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getMovesNotToBeInvoked(),MessagesPkBean.EFF_INVOKE,MessagesDataEffinvoke.M_P_50_MOVES_NOT_INVOKED);

    }

    private DictionaryComparator<TranslatedKey, TranslatedKey> invokingMoveByUserTypes(Effect _eff) {
        EffectInvoke effect_ = (EffectInvoke) _eff;
        DictionaryComparator<TranslatedKey, TranslatedKey> invokingMoveByUserTypes_;
        invokingMoveByUserTypes_ = DictionaryComparatorUtil.buildTypesStr();
        for (String e: effect_.getInvokingMoveByUserTypes().getKeys()) {
            invokingMoveByUserTypes_.put(buildTy(getFacade(),e), buildMv(getFacade(),effect_.getInvokingMoveByUserTypes().getVal(e)));
        }
        return invokingMoveByUserTypes_;
    }

    public static StringList globalMoves(DataBase _data) {
        StringList globalMoves_ = new StringList();
        for (String m: _data.getMoves().getKeys()) {
            MoveData move_ = _data.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getInvokedMoveTerrain().isEmpty()) {
                    globalMoves_.add(m);
                }
            }
        }
        return globalMoves_;
    }

    public String clickMoveFctEnv(int _index) {
        return tryRedirect(moveFctEnv.getValue(_index));
    }
    public String getTrEnv(int _index) {
        return moveFctEnv.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<EnvironmentType,String> translatedMoves_ = data_.getTranslatedEnvironment().getVal(getLanguage());
//        EnvironmentType st_ = PokemonStandards.getEnvByName(moveFctEnv.getKey(_index));
//        return translatedMoves_.getVal(st_);
    }
    public String getTrMoveFctEnv(int _index) {
        return moveFctEnv.getValue(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = moveFctEnv.getValue(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String getTrGlobalMoveFctEnv(int _index) {
        return globalMoves.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = globalMoves.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickGlobalMoveFctEnv(int _index) {
        return tryRedirect(globalMoves.get(_index));
    }
    public String clickMoveNotInvok(int _index) {
        return tryRedirect(movesNotToBeInvoked.get(_index));
    }
    public String getTrMoveNotInvok(int _index) {
        return movesNotToBeInvoked.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = movesNotToBeInvoked.get(_index);
//        return translatedMoves_.getVal(st_);
    }
    public String clickMoveUserTypes(int _index) {
        return tryRedirect(invokingMoveByUserTypes.getValue(_index));
    }
    public boolean isType(int _index) {
        String st_ = invokingMoveByUserTypes.getKey(_index).getKey();
        return !st_.isEmpty();
    }
    public String getTrUserTypes(int _index) {
        return invokingMoveByUserTypes.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String st_ = invokingMoveByUserTypes.getKey(_index);
//        return translatedTypes_.getVal(st_);
    }
    public String getTrMoveUserTypes(int _index) {
        return invokingMoveByUserTypes.getValue(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String st_ = invokingMoveByUserTypes.getValue(_index);
//        return translatedMoves_.getVal(st_);
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

    public DictionaryComparator<TranslatedKey,TranslatedKey> getMoveFctEnv() {
        return moveFctEnv;
    }

    public CustList<TranslatedKey> getGlobalMoves() {
        return globalMoves;
    }

    public DictionaryComparator<TranslatedKey,TranslatedKey> getInvokingMoveByUserTypes() {
        return invokingMoveByUserTypes;
    }

    public CustList<TranslatedKey> getMovesNotToBeInvoked() {
        return movesNotToBeInvoked;
    }
}