package code.serialize.classes;
import code.util.EnumMap;
import code.util.annot.RwXml;

@RwXml
public class MapsEnum {

    private EnumMap<MyEnum,String> map;

    public void setMap(EnumMap<MyEnum, String> _map) {
        map = _map;
    }

    public EnumMap<MyEnum, String> getMap() {
        return map;
    }
}
