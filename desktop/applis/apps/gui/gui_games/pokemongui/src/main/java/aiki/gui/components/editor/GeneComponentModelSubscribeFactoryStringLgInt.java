package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;

public final class GeneComponentModelSubscribeFactoryStringLgInt implements AbsGeneComponentModelSubscribeFactory<EditedCrudPair<String, LgInt>> {

    private final AbstractProgramInfos core;

    public GeneComponentModelSubscribeFactoryStringLgInt(AbstractProgramInfos _c) {
        this.core = _c;
    }

    @Override
    public AbsGeneComponentModelSubscribe<EditedCrudPair<String, LgInt>> build() {
        return new GeneComponentModelSubscribeStringLgInt(core);
    }
}
