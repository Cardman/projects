package aiki.fight.util;

import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class StatisticPokemons {
    private final CustList<StatisticPokemonByte> list;
    public StatisticPokemons() {
        list = new CustList<StatisticPokemonByte>();
    }
    public StatisticPokemons(CollCapacity _cap) {
        list = new CustList<StatisticPokemonByte>(_cap);
    }

    public CustList<StatisticPokemonByte> entryList() {
        return getList();
    }


    public CustList<StatisticPokemon> getKeys() {
        CustList<StatisticPokemon> l_ = new CustList<StatisticPokemon>();
        for (StatisticPokemonByte e: entryList()) {
            l_.add(e.getStat());
        }
        return l_;
    }

    public CustList<StatisticPokemonByte> getList() {
        return list;
    }


    public boolean isEmpty() {
        return getList().isEmpty();
    }


    public StatisticPokemonByte getEntryByKey(StatisticPokemon _key) {
        int index_ = indexOfEntry(_key);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return null;
        }
        return getList().get(index_);
    }


    public boolean contains(StatisticPokemon _key) {
        return getEntryByKey(_key) != null;
    }

    public byte getVal(StatisticPokemon _key) {
        StatisticPokemonByte e_ = getEntryByKey(_key);
        if (e_ == null) {
            return (byte)0;
        }
        return e_.getValue();
    }


    public int indexOfEntry(StatisticPokemon _key){
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (_key.eq(list.get(i).getStat())){
                return i;
            }
        }
        return -1;
    }

    public void addEntry(StatisticPokemon _k, Byte _v) {
        list.add(new StatisticPokemonByte(_k, _v));
    }

}
