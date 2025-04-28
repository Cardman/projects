package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.maths.*;

public final class GeneComponentModelSubscribeFactoryStringLgInt implements AbsGeneComponentModelSubscribeFactory<EditedCrudPair<String, LgInt>> {

    private final AbsGeneComponentModelEffect core;
    private final FacadeGame facadeGame;

    public GeneComponentModelSubscribeFactoryStringLgInt(AbsGeneComponentModelEffect _c, FacadeGame _fac) {
        this.core = _c;
        this.facadeGame = _fac;
    }

    @Override
    public AbsGeneComponentModelSubscribe<EditedCrudPair<String, LgInt>> build() {
        return new GeneComponentModelSubscribeStringLgInt(core, facadeGame);
    }
}
