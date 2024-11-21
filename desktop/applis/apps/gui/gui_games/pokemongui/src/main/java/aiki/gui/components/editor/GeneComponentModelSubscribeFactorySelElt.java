package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeFactorySelElt implements AbsGeneComponentModelSubscribeFactory<String> {

    private final AbstractProgramInfos core;
    private final FacadeGame facade;
    private final SubscribedTranslationMessagesFactory content;
    private final AbsMap<String,String> withEmpty;

    public GeneComponentModelSubscribeFactorySelElt(AbstractProgramInfos _c, FacadeGame _f, SubscribedTranslationMessagesFactory _fact, AbsMap<String,String> _def) {
        this.core = _c;
        this.facade = _f;
        content = _fact;
        withEmpty = _def;
    }

    @Override
    public AbsGeneComponentModelSubscribe<String> build() {
        return ConverterCommonMapUtil.merge(core, facade, content, new CustList<String>(), withEmpty);
    }
}
