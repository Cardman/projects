package aiki.map.characters;
import aiki.map.characters.enums.GeranceType;
import code.util.annot.RwXml;

@RwXml
public final class GerantPokemon extends Person {

    private GeranceType gerance;

    public GeranceType getGerance() {
        return gerance;
    }

    public void setGerance(GeranceType _gerance) {
        gerance = _gerance;
    }

}
