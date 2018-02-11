package aiki.beans.pokemon.evolutions;
import aiki.DataBase;
import aiki.fight.pokemon.evolution.EvolutionLevelGender;
import aiki.map.pokemon.enums.Gender;
import code.util.EnumMap;


public class EvolutionLevelGenderBean extends EvolutionLevelBean {
    private String gender;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        EvolutionLevelGender evo_ = (EvolutionLevelGender) getEvo();
        gender = translationsGenders_.getVal(evo_.getGender());
    }

    public String getGender() {
        return gender;
    }
}