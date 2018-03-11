package aiki.map.util;
import code.util.annot.RwXml;

@RwXml
public final class TileMiniMap {

    private String file;

    private short place;

    private boolean heros;

    public String getFile() {
        return file;
    }

    public void setFile(String _file) {
        file = _file;
    }

    public short getPlace() {
        return place;
    }

    public void setPlace(short _place) {
        place = _place;
    }

    public boolean isHeros() {
        return heros;
    }

    public void setHeros(boolean _heros) {
        heros = _heros;
    }
}
