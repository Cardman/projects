package code.serialize.classes;
import java.math.BigInteger;

import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsBigInteger {

    private NumberMap<BigInteger,String> map;

    public void setMap(NumberMap<BigInteger, String> _map) {
        map = _map;
    }

    public NumberMap<BigInteger, String> getMap() {
        return map;
    }
}
