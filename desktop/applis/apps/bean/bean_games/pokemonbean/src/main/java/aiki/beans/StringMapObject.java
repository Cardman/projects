package aiki.beans;

import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public final class StringMapObject {

    private final StringMap<Object> map = new StringMap<Object>();

    public Iterable<EntryCust<String,Object>> entryList() {
        return map.entryList();
    }

    public CustList<String> getKeys() {
        return map.getKeys();
    }

    public void clear() {
        map.clear();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public int size() {
        return map.size();
    }

//    public void put(String _key, AreaApparition _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, Ally _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, CharacterInRoadCave _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, Pokemon _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, PokemonPlayerDto _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, Person _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, Rate _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, Point _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, Coords _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, Number _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, String _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, Boolean _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, StringList _v) {
//        getForms().put(_key, _v);
//    }
//
//    public void put(String _key, Enum<?> _v) {
//        getForms().put(_key, _v);
//    }

    public void put(String _key, Object _v) {
        map.put(_key, _v);
    }

    public boolean contains(String _key) {
        return map.contains(_key);
    }

    public Object getVal(String _key) {
        return map.getVal(_key);
    }

    public void putAllMap(StringMapObject _m) {
        for (EntryCust<String,Object> e: _m.entryList()) {
            map.put(e.getKey(), e.getValue());
        }
    }

    public void removeKey(String _key) {
        map.removeKey(_key);
    }

}
