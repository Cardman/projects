package aiki.beans.moves.effects;

import aiki.beans.PokemonStandards;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.map.levels.enums.EnvironmentType;
import code.util.AbsMap;
import code.util.StringList;
import code.util.StringMap;

public class EffectSwitchTypesBean extends EffectBean {
    private DictionaryComparator<String, String> chgtTypeByEnv;
    private StringList globalMoves;

    private ConstValuesType constValuesType;

    private ExchangeType exchangeTypes;
    private StringList constTypes;
    private StringList addedTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchTypes effect_ = (EffectSwitchTypes) getEffect();
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        DictionaryComparator<String, String> chgtTypeByEnv_;
        chgtTypeByEnv_ = DictionaryComparatorUtil.buildEnvStr(data_,getLanguage());
        for (EnvironmentType env_: effect_.getChgtTypeByEnv().getKeys()) {
            String type_;
            type_ = effect_.getChgtTypeByEnv().getVal(env_);
            chgtTypeByEnv_.put(env_.getEnvName(), translatedTypes_.getVal(type_));
        }
        chgtTypeByEnv = chgtTypeByEnv_;
        StringList globalMoves_ = globalMoves(data_);
        globalMoves_.removeDuplicates();
        globalMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        globalMoves = globalMoves_;
        constValuesType = effect_.getConstValuesType();
        exchangeTypes = effect_.getExchangeTypes();
        StringList constTypes_;
        constTypes_ = new StringList();
        for (String type_: effect_.getConstTypes()) {
            constTypes_.add(type_);
        }
        constTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        constTypes = constTypes_;
        StringList addedTypes_;
        addedTypes_ = new StringList();
        for (String type_: effect_.getAddedTypes()) {
            addedTypes_.add(type_);
        }
        addedTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        addedTypes = addedTypes_;
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
        EnvironmentType env_ = PokemonStandards.getEnvByName(chgtTypeByEnv.getKey(_index));
        DataBase data_ = getDataBase();
        AbsMap<EnvironmentType,String> translatedTypes_ = data_.getTranslatedEnvironment().getVal(getLanguage());
        return translatedTypes_.getVal(env_);
    }
    public String getTrGlobalMoveFctEnv(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = globalMoves.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickGlobalMoveFctEnv(int _index) {
        String st_ = globalMoves.get(_index);
        return tryRedirectMv(st_);
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
    public String getTrConstType(int _index) {
        String type_ = constTypes.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String getTrAddedType(int _index) {
        String type_ = addedTypes.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }

    public DictionaryComparator<String,String> getChgtTypeByEnv() {
        return chgtTypeByEnv;
    }

    public StringList getGlobalMoves() {
        return globalMoves;
    }

    public StringList getAddedTypes() {
        return addedTypes;
    }

    public StringList getConstTypes() {
        return constTypes;
    }
}