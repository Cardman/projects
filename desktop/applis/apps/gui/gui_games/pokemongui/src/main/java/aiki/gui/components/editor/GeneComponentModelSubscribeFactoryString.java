package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;

public final class GeneComponentModelSubscribeFactoryString implements AbsGeneComponentModelSubscribeFactory<String> {

    private final AbstractProgramInfos core;
    private final FacadeGame facadeGame;

    public GeneComponentModelSubscribeFactoryString(AbstractProgramInfos _c, FacadeGame _fac) {
        this.core = _c;
        this.facadeGame = _fac;
    }

    @Override
    public AbsGeneComponentModelSubscribe<String> build() {
        return new GeneComponentModelSubscribeString(core,facadeGame);
    }
}
