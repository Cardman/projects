package aiki.gui.components.editor;

import code.util.*;

public class GeneComponentModelSubscribeFactoryInts implements AbsGeneComponentModelSubscribeFactory<Ints> {

    private final GeneComponentModelSubscribeInts curd;

    public GeneComponentModelSubscribeFactoryInts(GeneComponentModelSubscribeInts _curd) {
        curd = _curd;
    }

    @Override
    public AbsGeneComponentModelSubscribe<Ints> build() {
        return curd;
    }
}
