package aiki.gui.components.editor;

import code.gui.initialize.*;
import code.maths.*;

public class GeneComponentModelSubscribeFactoryRate implements AbsGeneComponentModelSubscribeFactory<Rate> {

    private final AbstractProgramInfos core;

    public GeneComponentModelSubscribeFactoryRate(AbstractProgramInfos _c) {
        this.core = _c;
    }

    @Override
    public AbsGeneComponentModelSubscribe<Rate> build() {
        return new GeneComponentModelSubscribeRate(core);
    }
}
