package aiki.beans.simulation;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public class SelectAbilityBean extends CommonBean {
    private StringList sortedAbilities = new StringList();
    private String typedAbility = DataBase.EMPTY_STRING;

    @Override
    public void beforeDisplaying() {
        sortedAbilities = (StringList) getForms().getVal(CST_ABILITIES_SET);
//        typedAbility = StringList.replace(typedAbility, QUOTE, ESCAPED_QUOTE);
        typedAbility = escapedStringQuote(typedAbility);
    }
    public static String cancel() {
        return CST_ABILITY;
    }
    public String search() {
        StringList sortedAbilities_;
        sortedAbilities_ = new StringList();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        for (String i: data_.getAbilities().getKeys()) {
            String ab_ = translationsAbilities_.getVal(i);
            if (!StringUtil.match(ab_, typedAbility)) {
                continue;
            }
            sortedAbilities_.add(i);
        }
        if (sortedAbilities_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(CST_POKEMON_ABILITY_EDIT, sortedAbilities_.first());
            return CST_ABILITY;
        }
        sortedAbilities_.sortElts(new ComparatorTrStrings(translationsAbilities_));
        getForms().put(CST_ABILITIES_SET, sortedAbilities_);
        return CST_ABILITIES;
    }
    public String clickAbility(int _index) {
        getForms().put(CST_POKEMON_ABILITY_EDIT, sortedAbilities.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbility(int _index) {
        String ability_ = sortedAbilities.get(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translationsAbilities_.getVal(ability_);
    }

    public void setTypedAbility(String _typedAbility) {
        typedAbility = _typedAbility;
    }

    public String getTypedAbility() {
        return typedAbility;
    }

    public StringList getSortedAbilities() {
        return sortedAbilities;
    }
}