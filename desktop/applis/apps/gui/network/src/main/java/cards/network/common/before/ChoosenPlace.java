package cards.network.common.before;
import code.util.*;


public final class ChoosenPlace extends PlayerActionBeforeGameCards {

    private int place;

    private IntTreeMap< Byte> placesPlayers;

    public int getPlace() {
        return place;
    }

    public void setPlace(int _place) {
        place = _place;
    }

    public IntTreeMap< Byte> getPlacesPlayers() {
        return placesPlayers;
    }

    public void setPlacesPlayers(IntTreeMap< Byte> _placesPlayers) {
        placesPlayers = _placesPlayers;
    }
}
