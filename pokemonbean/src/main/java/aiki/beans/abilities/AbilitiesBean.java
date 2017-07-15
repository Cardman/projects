package aiki.beans.abilities;
import code.bean.Accessible;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStrings;

public class AbilitiesBean extends CommonBean {

    @Accessible
    private StringList sortedAbilities = new StringList();

    @Accessible
    private String typedAbility = DataBase.EMPTY_STRING;

    @Override
    public void beforeDisplaying() {
        sortedAbilities = (StringList) getForms().getVal(ABILITIES_SET);
//        typedAbility = StringList.replace(typedAbility, QUOTE, ESCAPED_QUOTE);
        typedAbility = escapedStringQuote(typedAbility);
    }

    @Accessible
    private String search() {
        StringList sortedAbilities_;
        sortedAbilities_ = new StringList();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        for (String i: data_.getAbilities().getKeys()) {
            String ab_ = translationsAbilities_.getVal(i);
            if (!StringList.match(ab_, typedAbility)) {
                continue;
            }
            sortedAbilities_.add(i);
        }
        if (sortedAbilities_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(ABILITY, sortedAbilities_.first());
            return ABILITY;
        }
        sortedAbilities_.sortElts(new ComparatorTrStrings(translationsAbilities_));
        getForms().put(ABILITIES_SET, sortedAbilities_);
        return ABILITIES;
    }

    @Accessible
    private String clickAbility(Long _index) {
        getForms().put(ABILITY, sortedAbilities.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbility(Long _index) {
        String ability_ = sortedAbilities.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translationsAbilities_.getVal(ability_);
    }
}
