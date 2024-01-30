package cards.facade;

import code.gui.initialize.AbstractProgramInfos;

public abstract class AbsNicknamesCrudImpl extends AbsCrudImpl implements AbsNicknamesCrud {
    private Nicknames nicknames = new Nicknames();

    protected AbsNicknamesCrudImpl(AbstractProgramInfos _p) {
        super(_p);
    }

    public Nicknames getNicknames() {
        return nicknames;
    }

    public void setNicknames(Nicknames _n) {
        this.nicknames = _n;
    }
}
