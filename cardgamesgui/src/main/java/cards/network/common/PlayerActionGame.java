package cards.network.common;
import code.util.annot.RwXml;

@RwXml
public class PlayerActionGame extends PlayerAction {

    private byte place;

    private String locale;

    public byte getPlace() {
        return place;
    }

    public void setPlace(byte _place) {
        place = _place;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String _locale) {
        locale = _locale;
    }
}
