package aiki.beans.pokemon.evolutions;
import aiki.db.DataBase;
import aiki.fight.pokemon.evolution.EvolutionMoveType;
import code.util.StringMap;

public class EvolutionMoveTypeBean extends EvolutionBean {
    private String type;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionMoveType evo_ = (EvolutionMoveType) getEvo();
        DataBase data_ = getDataBase();
        StringMap<String> translationsType_;
        translationsType_ = data_.getTranslatedTypes().getVal(getLanguage());
        type = translationsType_.getVal(evo_.getType());
    }

    public String getType() {
        return type;
    }
}