package aiki.beans.items;

import aiki.db.DataBase;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionStone;
import code.util.StringList;
import code.util.core.StringUtil;

public class EvolvingStoneBean extends EvolvingBean {

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        StringList pokemon_ = new StringList();
        for (String p: data_.getPokedex().getKeys()) {
            for (Evolution e: data_.getPokemon(p).getEvolutions().values()) {
                if (e instanceof EvolutionStone && StringUtil.quickEq(((EvolutionStone) e).getStone(), StringUtil.nullToEmpty(getName()))) {
                    pokemon_.add(p);
                }
            }
        }
        pk(pokemon_);
    }
}