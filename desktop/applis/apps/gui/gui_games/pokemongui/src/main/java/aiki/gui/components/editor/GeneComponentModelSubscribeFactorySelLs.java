package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeFactorySelLs implements AbsGeneComponentModelSubscribeFactory<StringList> {

    private final AbstractProgramInfos core;
    private final FacadeGame facade;
    private final SubscribedTranslationMessagesFactory content;

    public GeneComponentModelSubscribeFactorySelLs(AbstractProgramInfos _c, FacadeGame _f, SubscribedTranslationMessagesFactory _fact) {
        this.core = _c;
        this.facade = _f;
        content = _fact;
    }

    @Override
    public AbsGeneComponentModelSubscribe<StringList> build() {
        return ConverterCommonMapUtil.mergeLs(core, facade, content);
    }
}
