package code.serialize.classes;
import code.util.AbsMap;
import code.util.annot.RwXml;

@RwXml
public class Maps<K> {

    private AbsMap<K,String> map;

    public void setMap(AbsMap<K, String> _map) {
        map = _map;
    }

    public AbsMap<K, String> getMap() {
        return map;
    }
}
