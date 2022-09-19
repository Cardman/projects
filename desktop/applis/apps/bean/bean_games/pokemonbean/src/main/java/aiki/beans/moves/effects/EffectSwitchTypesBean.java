package aiki.beans.moves.effects;
import aiki.beans.PokemonStandards;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.map.levels.enums.EnvironmentType;
import code.util.*;

public class EffectSwitchTypesBean extends EffectBean {
    private TreeMap<String, String> chgtTypeByEnv;
    private StringList globalMoves;

    private ConstValuesType constValuesType;

    private ExchangeType exchangeTypes;
    private StringList constTypes;
    private StringList addedTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchTypes effect_ = (EffectSwitchTypes) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        AbsMap<EnvironmentType,String> translatedEnvironments_ = data_.getTranslatedEnvironment().getVal(getLanguage());
        StringMap<String> translated_ = new StringMap<String>();
        for (EntryCust<EnvironmentType,String> s: translatedEnvironments_.entryList()) {
            translated_.addEntry(s.getKey().name(),s.getValue());
        }
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        TreeMap<String, String> chgtTypeByEnv_;
        chgtTypeByEnv_ = new TreeMap<String, String>(new ComparatorTrStrings(translated_));
        for (EnvironmentType env_: effect_.getChgtTypeByEnv().getKeys()) {
            String type_;
            type_ = effect_.getChgtTypeByEnv().getVal(env_);
            chgtTypeByEnv_.put(env_.name(), translatedTypes_.getVal(type_));
        }
        chgtTypeByEnv = chgtTypeByEnv_;
        StringList globalMoves_;
        globalMoves_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal eff_ = (EffectGlobal) e;
                if (eff_.getChangedTypesTerrain().isEmpty()) {
                    continue;
                }
                globalMoves_.add(m);
            }
        }
        globalMoves_.removeDuplicates();
        globalMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        globalMoves = globalMoves_;
        constValuesType = effect_.getConstValuesType();
        exchangeTypes = effect_.getExchangeTypes();
        StringList constTypes_;
        constTypes_ = new StringList();
        for (String type_: effect_.getConstTypes()) {
            constTypes_.add(type_);
        }
        constTypes_.sortElts(new ComparatorTrStrings(translatedTypes_));
        constTypes = constTypes_;
        StringList addedTypes_;
        addedTypes_ = new StringList();
        for (String type_: effect_.getAddedTypes()) {
            addedTypes_.add(type_);
        }
        addedTypes_.sortElts(new ComparatorTrStrings(translatedTypes_));
        addedTypes = addedTypes_;
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
        DataBase data_ = (DataBase) getDataBase();
        AbsMap<EnvironmentType,String> translatedTypes_ = data_.getTranslatedEnvironment().getVal(getLanguage());
        return translatedTypes_.getVal(env_);
    }
    public String getTrGlobalMoveFctEnv(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String st_ = globalMoves.get(_index);
        return translatedMoves_.getVal(st_);
    }
    public String clickGlobalMoveFctEnv(int _index) {
        String st_ = globalMoves.get(_index);
        getForms().put(CST_MOVE, st_);
        return CST_MOVE;
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
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String getTrAddedType(int _index) {
        String type_ = addedTypes.get(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }

    public TreeMap<String,String> getChgtTypeByEnv() {
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