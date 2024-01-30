package cards.facade;

import code.gui.initialize.AbstractProgramInfos;

public final class CardGamesStream {
    private AbsCardGamesCrud cardGamesCrud;
    private AbsNicknamesCrud nicknamesCrud;
    public CardGamesStream(AbstractProgramInfos _pr) {
        setCardGamesCrud(new DefCardGamesCrud(_pr));
        setNicknamesCrud(new DefNicknamesCrud(_pr));
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
