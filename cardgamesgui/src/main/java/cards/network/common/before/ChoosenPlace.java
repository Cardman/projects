package cards.network.common.before;
import code.util.NatTreeMap;
import code.util.annot.RwXml;

@RwXml
public class ChoosenPlace extends PlayerActionBeforeGame {

    private int place;

    private NatTreeMap<Integer, Byte> placesPlayers;

    public int getPlace() {
        return place;
    }

    public void setPlace(int _place) {
        place = _place;
    }

    public NatTreeMap<Integer, Byte> getPlacesPlayers() {
        return placesPlayers;
    }

    public void setPlacesPlayers(NatTreeMap<Integer, Byte> _placesPlayers) {
        placesPlayers = _placesPlayers;
    }
}
