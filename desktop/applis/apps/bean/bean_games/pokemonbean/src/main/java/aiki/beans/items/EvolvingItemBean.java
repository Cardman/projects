package aiki.beans.items;

import aiki.db.DataBase;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionItem;
import code.scripts.pages.aiki.*;
import code.util.StringList;
import code.util.core.StringUtil;

public final class EvolvingItemBean extends EvolvingBean {

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        StringList pokemon_ = new StringList();
        for (String p: data_.getPokedex().getKeys()) {
            for (Evolution e: data_.getPokemon(p).getEvolutions().values()) {
                if (e instanceof EvolutionItem && StringUtil.quickEq(((EvolutionItem) e).getItem(), StringUtil.nullToEmpty(getName()))) {
                    pokemon_.add(p);
                }
            }
        }
        pk(pokemon_);
    }

    @Override
    protected String fileLoc() {
        return MessagesPkBean.IT_EVOITEM;
    }

    @Override
    protected String keyLoc() {
        return MessagesDataItemsEvoitem.M_P_19_ITEM;
    }
}