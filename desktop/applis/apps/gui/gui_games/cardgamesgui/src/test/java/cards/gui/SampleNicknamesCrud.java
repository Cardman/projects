package cards.gui;

import cards.facade.AbsNicknamesCrudImpl;
import cards.facade.Nicknames;
import code.gui.initialize.AbstractProgramInfos;

public final class SampleNicknamesCrud extends AbsNicknamesCrudImpl {
    public SampleNicknamesCrud(AbstractProgramInfos _p) {
        super(_p);
    }

    @Override
    public Nicknames value() {
        return getNicknames();
    }

    @Override
    public void value(Nicknames _n) {
        setNicknames(_n);
    }
}
