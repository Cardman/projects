package aiki.beans.pokemon.evolutions;
import aiki.db.DataBase;

public class EvolutionHappinessBean extends EvolutionBean {
    private long min;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        min = data_.getHappinessEvo();
    }

    public long getMin() {
        return min;
    }
}