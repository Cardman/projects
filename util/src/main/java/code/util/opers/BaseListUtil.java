package code.util.opers;
import code.util.AbsMap;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

public final class BaseListUtil {

    private BaseListUtil() {
    }

    public static void set(Object _map, boolean _key, int _index, Object _oldObject, Object _newObject) {
        if (_key) {
            ((ListableEntries<Object, ?>)_map).move(_oldObject, _newObject);
        } else {
            AbsMap.setGeneValue((ListableEntries<?,?>)_map, _index, _newObject);
//            ((ListableEntries<?, Object>)_map).entryList().get(_index).setValue(_newObject);
        }
    }
    public static void set(Object _list, int _index, Object _obj) {
        ((Listable<Object>)_list).set(_index, _obj);
    }

    public static Object get(Object _list, int _index) {
        return ((Listable<?>)_list).get(_index);
    }

    public static int size(Object _list) {
        return ((Listable<?>)_list).size();
    }
}
