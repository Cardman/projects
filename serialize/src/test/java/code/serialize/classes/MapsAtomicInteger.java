package code.serialize.classes;
import java.util.concurrent.atomic.AtomicInteger;

import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsAtomicInteger {

    private NumberMap<AtomicInteger,String> map;

    public void setMap(NumberMap<AtomicInteger, String> _map) {
        map = _map;
    }

    public NumberMap<AtomicInteger, String> getMap() {
        return map;
    }
}
