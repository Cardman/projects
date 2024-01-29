package cards.facade;

import cards.belote.DefGameBelote;
import cards.belote.IntGameBelote;
import cards.president.DefGamePresident;
import cards.president.IntGamePresident;
import cards.tarot.DefGameTarot;
import cards.tarot.IntGameTarot;

public final class IntArtCardGames {
    private IntGameBelote belote = new DefGameBelote();
    private IntGamePresident president = new DefGamePresident();
    private IntGameTarot tarot = new DefGameTarot();

    public IntGameBelote getBelote() {
        return belote;
    }

    public void setBelote(IntGameBelote _b) {
        this.belote = _b;
    }

    public IntGamePresident getPresident() {
        return president;
    }

    public void setPresident(IntGamePresident _p) {
        this.president = _p;
    }

    public IntGameTarot getTarot() {
        return tarot;
    }

    public void setTarot(IntGameTarot _t) {
        this.tarot = _t;
    }
}
