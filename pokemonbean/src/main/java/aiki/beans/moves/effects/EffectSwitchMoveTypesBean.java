package aiki.beans.moves.effects;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class EffectSwitchMoveTypesBean extends EffectBean {
    private StringList replacingTypes;
    private TreeMap<String,String> changeTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchMoveTypes effect_ = (EffectSwitchMoveTypes) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringList replacingTypes_;
        replacingTypes_ = new StringList();
        for (String t: effect_.getReplacingTypes()) {
            replacingTypes_.add(t);
        }
        replacingTypes_.sortElts(new ComparatorTrStrings(translatedTypes_));
        replacingTypes = replacingTypes_;
        TreeMap<String,String> changeTypes_;
        changeTypes_ = new TreeMap<String, String>(new ComparatorTrStrings(translatedTypes_));
        for (String t: effect_.getChangeTypes().getKeys()) {
            changeTypes_.put(t, translatedTypes_.getVal(effect_.getChangeTypes().getVal(t)));
        }
        changeTypes = changeTypes_;
    }
    public String getTrReplacingTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String st_ = replacingTypes.get(_index.intValue());
        return translatedTypes_.getVal(st_);
    }
    public String getTrChangedTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String st_ = changeTypes.getKey(_index.intValue());
        return translatedTypes_.getVal(st_);
    }

    public StringList getReplacingTypes() {
        return replacingTypes;
    }

    public TreeMap<String,String> getChangeTypes() {
        return changeTypes;
    }
}