package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeFactorySelEltEnum<T> implements AbsGeneComponentModelSubscribeFactory<T> {

    private final AbstractProgramInfos core;
    private final SubscribedTranslationMessagesFactoryCore<T> factory;
    private final FacadeGame facadeGame;

    public GeneComponentModelSubscribeFactorySelEltEnum(AbstractProgramInfos _c, SubscribedTranslationMessagesFactoryCore<T> _facto, FacadeGame _fa) {
        this.core = _c;
        factory = _facto;
        facadeGame = _fa;
    }

    @Override
    public AbsGeneComponentModelSubscribe<T> build() {
        return new SubscribeBuilderUtil<T>(factory).merge(core,facadeGame,new CustList<T>(),new IdMap<T, String>());
    }
}
