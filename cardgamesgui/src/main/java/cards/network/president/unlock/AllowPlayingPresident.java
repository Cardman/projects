package cards.network.president.unlock;
import cards.president.enumerations.Playing;


public final class AllowPlayingPresident {

    private boolean enabledPass;

    private Playing status;

    private boolean reversed;

    public boolean isEnabledPass() {
        return enabledPass;
    }

    public void setEnabledPass(boolean _enabledPass) {
        enabledPass = _enabledPass;
    }

    public Playing getStatus() {
        return status;
    }

    public void setStatus(Playing _status) {
        status = _status;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean _reversed) {
        reversed = _reversed;
    }
}
