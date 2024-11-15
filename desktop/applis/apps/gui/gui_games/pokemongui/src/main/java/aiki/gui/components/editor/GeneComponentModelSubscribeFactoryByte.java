package aiki.gui.components.editor;

import code.gui.initialize.*;

public class GeneComponentModelSubscribeFactoryByte implements AbsGeneComponentModelSubscribeFactory<Byte> {

    private final AbstractProgramInfos core;

    public GeneComponentModelSubscribeFactoryByte(AbstractProgramInfos _c) {
        this.core = _c;
    }

    @Override
    public AbsGeneComponentModelSubscribe<Byte> build() {
        return new GeneComponentModelSubscribeByte(core);
    }
}
