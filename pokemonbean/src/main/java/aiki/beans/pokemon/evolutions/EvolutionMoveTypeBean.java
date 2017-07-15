package aiki.beans.pokemon.evolutions;
import code.bean.Accessible;
import code.util.StringMap;
import aiki.DataBase;
import aiki.fight.pokemon.evolution.EvolutionMoveType;

public class EvolutionMoveTypeBean extends EvolutionBean {

    @Accessible
    private String type;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionMoveType evo_ = (EvolutionMoveType) getEvo();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsType_;
        translationsType_ = data_.getTranslatedTypes().getVal(getLanguage());
        type = translationsType_.getVal(evo_.getType());
    }
}
