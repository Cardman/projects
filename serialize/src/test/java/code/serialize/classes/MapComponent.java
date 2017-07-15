package code.serialize.classes;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;
import code.util.ints.Equallable;

@RwXml
public final class MapComponent implements Equallable<MapComponent> {

    private StringMap<String> elements = new StringMap<String>();

    public StringMap<String> getElements() {
        return elements;
    }

    @Override
    public boolean eq(MapComponent _obj) {
        if (_obj.elements.size() != elements.size()) {
            return false;
        }
        if (!_obj.elements.containsAllAsKeys(elements.getKeys())) {
            return false;
        }
        if (!elements.containsAllAsKeys(_obj.elements.getKeys())) {
            return false;
        }
        StringList keys_ = elements.getKeys();
        for (String k: keys_) {
            if (!StringList.quickEq(elements.getVal(k),_obj.elements.getVal(k))) {
                return false;
            }
        }
        return true;
    }
}
