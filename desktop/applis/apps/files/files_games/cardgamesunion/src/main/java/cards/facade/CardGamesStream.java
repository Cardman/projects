package cards.facade;

import code.gui.initialize.AbstractProgramInfos;

public final class CardGamesStream {
    private AbsCardGamesCrud cardGamesCrud;
    private AbsNicknamesCrud nicknamesCrud;
    public CardGamesStream(AbstractProgramInfos _pr, String _tmpFolder) {
        DefCardGamesCrud cg_ = new DefCardGamesCrud(_pr);
        cg_.setTempFolder(_tmpFolder);
        setCardGamesCrud(cg_);
        DefNicknamesCrud n_ = new DefNicknamesCrud(_pr);
        n_.setTempFolder(_tmpFolder);
        setNicknamesCrud(n_);
    }

    public AbsCardGamesCrud getCardGamesCrud() {
        return cardGamesCrud;
    }

    public void setCardGamesCrud(AbsCardGamesCrud _c) {
        this.cardGamesCrud = _c;
    }

    public AbsNicknamesCrud getNicknamesCrud() {
        return nicknamesCrud;
    }

    public void setNicknamesCrud(AbsNicknamesCrud _c) {
        this.nicknamesCrud = _c;
    }
}
