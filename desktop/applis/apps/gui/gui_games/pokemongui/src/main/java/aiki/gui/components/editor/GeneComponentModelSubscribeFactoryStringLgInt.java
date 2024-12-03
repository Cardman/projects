package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;

public final class GeneComponentModelSubscribeFactoryStringLgInt implements AbsGeneComponentModelSubscribeFactory<EditedCrudPair<String, LgInt>> {

    private final AbstractProgramInfos core;
    private final FacadeGame facadeGame;

    public GeneComponentModelSubscribeFactoryStringLgInt(AbstractProgramInfos _c, FacadeGame _fac) {
        this.core = _c;
        this.facadeGame = _fac;
    }

    @Override
    public AbsGeneComponentModelSubscribe<EditedCrudPair<String, LgInt>> build() {
        return new GeneComponentModelSubscribeStringLgInt(core, facadeGame);
    }
}
