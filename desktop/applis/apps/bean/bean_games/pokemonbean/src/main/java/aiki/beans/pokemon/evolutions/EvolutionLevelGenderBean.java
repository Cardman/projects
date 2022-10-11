package aiki.beans.pokemon.evolutions;

import aiki.db.DataBase;
import aiki.fight.pokemon.evolution.EvolutionLevelGender;
import aiki.map.pokemon.enums.Gender;
import code.util.AbsMap;


public class EvolutionLevelGenderBean extends EvolutionLevelBean {
    private String gender;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        AbsMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        EvolutionLevelGender evo_ = (EvolutionLevelGender) getEvo();
        gender = translationsGenders_.getVal(evo_.getGender());
    }

    public String getGender() {
        return gender;
    }
}