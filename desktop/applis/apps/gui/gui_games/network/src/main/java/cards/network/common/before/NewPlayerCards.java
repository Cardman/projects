package cards.network.common.before;
import code.network.AddingPlayer;

public final class NewPlayerCards extends PlayerActionBeforeGameCards implements AddingPlayer {

    private String pseudo;

//    private IntTreeMap< Byte> placesPlayers;

//    private IntMap<BoolVal> readyPlayers;
//    private boolean arriving;

//    private String language;

//    private boolean acceptable;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String _pseudo) {
        pseudo = _pseudo;
    }

//    public IntTreeMap< Byte> getPlacesPlayers() {
//        return placesPlayers;
//    }
//
//    public void setPlacesPlayers(IntTreeMap< Byte> _placesPlayers) {
//        placesPlayers = _placesPlayers;
//    }
//
//    public IntMap<BoolVal> getReadyPlayers() {
//        return readyPlayers;
//    }
//
//    public void setReadyPlayers(IntMap<BoolVal> _readyPlayers) {
//        readyPlayers = _readyPlayers;
//    }
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(String _language) {
//        language = _language;
//    }

}
