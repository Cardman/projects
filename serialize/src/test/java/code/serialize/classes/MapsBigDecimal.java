package code.serialize.classes;
import java.math.BigDecimal;

import code.util.NumberMap;
import code.util.annot.RwXml;

@RwXml
public class MapsBigDecimal {

    private NumberMap<BigDecimal,String> map;

    public void setMap(NumberMap<BigDecimal, String> _map) {
        map = _map;
    }

    public NumberMap<BigDecimal, String> getMap() {
        return map;
    }
}
