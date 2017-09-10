package aiki.map.characters;
import aiki.map.characters.enums.GeranceType;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public class GerantPokemon extends Person {

    private GeranceType gerance;

    public GeranceType getGerance() {
        return gerance;
    }

    public void setGerance(GeranceType _gerance) {
        gerance = _gerance;
    }

}
