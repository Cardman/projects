package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;

public final class GeneComponentModelSubscribeFactorySelLs<E,F> implements AbsGeneComponentModelSubscribeFactory<F> {

    private final AbstractProgramInfos core;
    private final FacadeGame facade;
    private final SubscribedTranslationMessagesFactoryCoreMessages<E> content;
    private final AbsSubscribeBuilderUtilFactory<E,F> convert;

    public GeneComponentModelSubscribeFactorySelLs(AbstractProgramInfos _c, FacadeGame _f, SubscribedTranslationMessagesFactoryCoreMessages<E> _fact, AbsSubscribeBuilderUtilFactory<E,F> _conv) {
        this.core = _c;
        this.facade = _f;
        content = _fact;
        convert = _conv;
    }

    @Override
    public AbsGeneComponentModelSubscribe<F> build() {
        return convert.build(content).mergeLs(core, facade);
    }
}
