package cards.facade;

import cards.belote.GameBelote;
import cards.president.GamePresident;
import cards.tarot.GameTarot;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public abstract class AbsCardGamesCrudImpl extends AbsCrudImpl implements AbsCardGamesCrud {
    private final StringMap<GameBelote> belote = new StringMap<GameBelote>();
    private final StringMap<GamePresident> president = new StringMap<GamePresident>();
    private final StringMap<GameTarot> tarot = new StringMap<GameTarot>();

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
}
