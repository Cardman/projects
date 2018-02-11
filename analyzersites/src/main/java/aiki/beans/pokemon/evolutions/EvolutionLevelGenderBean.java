package aiki.beans.pokemon.evolutions;
import code.bean.Accessible;
import code.util.EnumMap;
import aiki.DataBase;
import aiki.fight.pokemon.evolution.EvolutionLevelGender;
import aiki.map.pokemon.enums.Gender;


public class EvolutionLevelGenderBean extends EvolutionLevelBean {

    @Accessible
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
}
