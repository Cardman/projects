package cards.facade;

import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public abstract class AbsCardGamesCrudImpl extends AbsCrudImpl implements AbsCardGamesCrud {
    private final StringMap<GameBelote> belote = new StringMap<GameBelote>();
    private final StringMap<GamePresident> president = new StringMap<GamePresident>();
    private final StringMap<GameTarot> tarot = new StringMap<GameTarot>();
    private HandBelote handBelote;
    private HandBelote handBeloteShort;
    private final StringMap<HandPresident> handPresident = new StringMap<HandPresident>();
    private HandTarot handTarot;

    protected AbsCardGamesCrudImpl(AbstractProgramInfos _p) {
        super(_p);
    }

    public StringMap<GameBelote> getBelote() {
        return belote;
    }

    public StringMap<GamePresident> getPresident() {
        return president;
    }

    public StringMap<GameTarot> getTarot() {
        return tarot;
    }

    public HandBelote getHandBelote() {
        return handBelote;
    }

    public void setHandBelote(HandBelote _h) {
        this.handBelote = _h;
    }

    public HandBelote getHandBeloteShort() {
        return handBeloteShort;
    }

    public void setHandBeloteShort(HandBelote _h) {
        this.handBeloteShort = _h;
    }

    public StringMap<HandPresident> getHandPresident() {
        return handPresident;
    }

    public HandTarot getHandTarot() {
        return handTarot;
    }

    public void setHandTarot(HandTarot _h) {
        this.handTarot = _h;
    }
}
