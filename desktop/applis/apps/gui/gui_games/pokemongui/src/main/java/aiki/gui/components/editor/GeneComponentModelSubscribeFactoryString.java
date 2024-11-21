package aiki.gui.components.editor;

import code.gui.initialize.*;

public final class GeneComponentModelSubscribeFactoryString implements AbsGeneComponentModelSubscribeFactory<String> {

    private final AbstractProgramInfos core;

    public GeneComponentModelSubscribeFactoryString(AbstractProgramInfos _c) {
        this.core = _c;
    }

    @Override
    public AbsGeneComponentModelSubscribe<String> build() {
        return new GeneComponentModelSubscribeString(core);
    }
}
