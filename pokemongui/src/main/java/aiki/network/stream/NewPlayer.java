package aiki.network.stream;
import code.network.AddingPlayer;
import code.util.annot.RwXml;

@RwXml
public final class NewPlayer extends PlayerActionBeforeGame implements AddingPlayer {

    private String pseudo;

    private boolean arriving;

    private String language;

    private boolean acceptable;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String _pseudo) {
        pseudo = _pseudo;
    }

    public boolean isArriving() {
        return arriving;
    }

    public void setArriving(boolean _arriving) {
        arriving = _arriving;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String _language) {
        language = _language;
    }

    @Override
    public void setAcceptable(boolean _acceptable) {
        acceptable = _acceptable;
    }

    @Override
    public boolean isAcceptable() {
        return acceptable;
    }
}
