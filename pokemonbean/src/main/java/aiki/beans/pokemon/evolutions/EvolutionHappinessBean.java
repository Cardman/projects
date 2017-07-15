package aiki.beans.pokemon.evolutions;
import code.bean.Accessible;
import aiki.DataBase;

public class EvolutionHappinessBean extends EvolutionBean {

    @Accessible
    private long min;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        min = data_.getHappinessEvo();
    }
}
