package aiki.beans.pokemon.evolutions;
import aiki.db.DataBase;
import aiki.fight.pokemon.evolution.EvolutionStoneGender;
import aiki.map.pokemon.enums.Gender;
import code.util.EnumMap;

public class EvolutionStoneGenderBean extends EvolutionStoneBean {
    private String gender;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        EvolutionStoneGender evo_ = (EvolutionStoneGender) getEvo();
        EnumMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        gender = translationsGenders_.getVal(evo_.getGender());
    }

    public String getGender() {
        return gender;
    }
}