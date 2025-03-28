package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.map.levels.enums.EnvironmentType;
import code.scripts.pages.aiki.MessagesDataEffswitchtypes;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;
import code.util.core.NumberUtil;

public class EffectSwitchTypesBean extends EffectBean {
    private DictionaryComparator<TranslatedKey, TranslatedKey> chgtTypeByEnv;
    private CustList<TranslatedKey> globalMoves;

    private ConstValuesType constValuesType;

    private ExchangeType exchangeTypes;
    private CustList<TranslatedKey> constTypes;
    private CustList<TranslatedKey> addedTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchTypes effect_ = (EffectSwitchTypes) getEffect();
        DataBase data_ = getDataBase();
        DictionaryComparator<TranslatedKey, TranslatedKey> chgtTypeByEnv_;
        chgtTypeByEnv_ = DictionaryComparatorUtil.buildEnvStr();
        for (EnvironmentType env_: effect_.getChgtTypeByEnv().getKeys()) {
            String type_;
            type_ = effect_.getChgtTypeByEnv().getVal(env_);
            chgtTypeByEnv_.put(buildEnv(getFacade(),env_), buildTy(getFacade(),type_));
        }
        chgtTypeByEnv = chgtTypeByEnv_;
        StringList globalMoves_ = globalMoves(data_);
        globalMoves_.removeDuplicates();
        globalMoves = listTrStringsMv(globalMoves_,getFacade());
        constValuesType = effect_.getConstValuesType();
        exchangeTypes = effect_.getExchangeTypes();
//        StringList constTypes_;
//        constTypes_ = new StringList();
//        for (String type_: effect_.getConstTypes()) {
//            constTypes_.add(type_);
//        }
//        constTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        constTypes = listTrStringsTy(effect_.getConstTypes(),getFacade());
//        StringList addedTypes_;
//        addedTypes_ = new StringList();
//        for (String type_: effect_.getAddedTypes()) {
//            addedTypes_.add(type_);
//        }
//        addedTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        addedTypes = listTrStringsTy(effect_.getAddedTypes(),getFacade());
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_SWITCHTYPES,MessagesDataEffswitchtypes.M_P_65_EFFECT);
    }

    @Override
    public void buildSubEff() {
        displayBoolTrue(toInt(isResTypes()), MessagesPkBean.EFF_SWITCHTYPES, MessagesDataEffswitchtypes.M_P_65_RES_MOVES);
        displayBoolTrue(toInt(isUserTypes()), MessagesPkBean.EFF_SWITCHTYPES, MessagesDataEffswitchtypes.M_P_65_USER_MOVES);
        if (!isConstTypes()) {
            new BeanDisplayMap<TranslatedKey,TranslatedKey>(new BeanDisplayTranslatedKey(),new BeanDisplayTranslatedKey()).displayGrid(this, getChgtTypeByEnv(), MessagesPkBean.EFF_SWITCHTYPES, MessagesDataEffswitchtypes.M_P_65_ENVIR,MessagesDataEffswitchtypes.M_P_65_ENVIR_ENV,MessagesDataEffswitchtypes.M_P_65_ENVIR_TYPE);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getGlobalMoves(), NumberUtil.signum(getChgtTypeByEnv().size()),MessagesPkBean.EFF_SWITCHTYPES,MessagesDataEffswitchtypes.M_P_65_ENVIR_ENV_EXC);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getAddedTypes(),MessagesPkBean.EFF_SWITCHTYPES,MessagesDataEffswitchtypes.M_P_65_ADDED_TYPES);
            procExchangeType(getExchangeTypes(),ExchangeType.GIVE_TO_TARGET,getAddedTypes(),new CustList<TranslatedKey>(),MessagesDataEffswitchtypes.M_P_65_AFFECT_TYPES_NOT_CONST_TARGET);
            procExchangeType(getExchangeTypes(),ExchangeType.GIVE_TO_THROWER,getAddedTypes(),new CustList<TranslatedKey>(),MessagesDataEffswitchtypes.M_P_65_AFFECT_TYPES_NOT_CONST_USER);
            procExchangeType(getExchangeTypes(),ExchangeType.EXCHANGE,getAddedTypes(),new CustList<TranslatedKey>(),MessagesDataEffswitchtypes.M_P_65_SWITCH_TYPES);
            procExchangeType(getExchangeTypes(),ExchangeType.GIVE_CONST,getAddedTypes(),getConstTypes(),MessagesDataEffswitchtypes.M_P_65_AFFECT_TYPES);
        }
    }

    public static StringList globalMoves(DataBase _data) {
        StringList globalMoves_ = new StringList();
        for (String m: _data.getMoves().getKeys()) {
            MoveData move_ = _data.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getChangedTypesTerrain().isEmpty()) {
                    globalMoves_.add(m);
                }
            }
        }
        return globalMoves_;
    }

    public boolean isConstTypes() {
        return constValuesType != ConstValuesType.NOTHING;
    }
    public boolean isResTypes() {
        return constValuesType == ConstValuesType.TYPES_ATTAQUES_RES;
    }
    public boolean isUserTypes() {
        return constValuesType == ConstValuesType.LANCEUR_ATTAQUES_TYPES;
    }
    public String getTrEnv(int _index) {
        return chgtTypeByEnv.getKey(_index).getTranslation();
//        EnvironmentType env_ = PokemonStandards.getEnvByName(chgtTypeByEnv.getKey(_index));
//        DataBase data_ = getDataBase();
//        AbsMap<EnvironmentType,String> translatedTypes_ = data_.getTranslatedEnvironment().getVal(getLanguage());
//        return translatedTypes_.getVal(env_);
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
    public boolean giveToTarget() {
        return exchangeTypes == ExchangeType.GIVE_TO_TARGET;
    }
    public boolean giveToUser() {
        return exchangeTypes == ExchangeType.GIVE_TO_THROWER;
    }
    public boolean giveConst() {
        return exchangeTypes == ExchangeType.GIVE_CONST;
    }
    public boolean switchTypes() {
        return exchangeTypes == ExchangeType.EXCHANGE;
    }

    public ExchangeType getExchangeTypes() {
        return exchangeTypes;
    }

    public String getTrConstType(int _index) {
        return constTypes.get(_index).getTranslation();
//        String type_ = constTypes.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translatedTypes_.getVal(type_);
    }
    public String getTrAddedType(int _index) {
        return addedTypes.get(_index).getTranslation();
//        String type_ = addedTypes.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translatedTypes_.getVal(type_);
    }

    public DictionaryComparator<TranslatedKey,TranslatedKey> getChgtTypeByEnv() {
        return chgtTypeByEnv;
    }

    public CustList<TranslatedKey> getGlobalMoves() {
        return globalMoves;
    }

    public CustList<TranslatedKey> getAddedTypes() {
        return addedTypes;
    }

    public CustList<TranslatedKey> getConstTypes() {
        return constTypes;
    }
}