package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeFactorySelEltEnum<T> implements AbsGeneComponentModelSubscribeFactory<T> {

    private final AbstractProgramInfos core;
    private final AbsMap<T,String> messages;

    public GeneComponentModelSubscribeFactorySelEltEnum(AbstractProgramInfos _c, AbsMap<T, String> _m) {
        this.core = _c;
        messages = _m;
    }

    @Override
    public AbsGeneComponentModelSubscribe<T> build() {
        return new GeneComponentModelEltEnumSub<T>(new GeneComponentModelEltEnum<T>(core,messages));
    }
}
