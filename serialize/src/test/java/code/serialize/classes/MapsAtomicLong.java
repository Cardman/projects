package code.serialize.classes;
import java.util.concurrent.atomic.AtomicLong;

import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsAtomicLong {

    private NumberMap<AtomicLong,String> map;

    public void setMap(NumberMap<AtomicLong, String> _map) {
        map = _map;
    }

    public NumberMap<AtomicLong, String> getMap() {
        return map;
    }
}
