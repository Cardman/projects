package cards.gui;

import cards.facade.AbsNicknamesCrudImpl;
import cards.facade.Nicknames;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;

public final class SampleNicknamesCrud extends AbsNicknamesCrudImpl {
    public SampleNicknamesCrud(AbstractProgramInfos _p) {
        super(_p);
    }

    @Override
    public Nicknames value() {
        return getNicknames();
    }

    @Override
    public Nicknames value(TranslationsLg _lg) {
        return new Nicknames();
    }

    @Override
    public boolean isValidNicknames(Nicknames _n) {
        return true;
    }

    @Override
    public void value(Nicknames _n) {
        setNicknames(_n);
    }
}
