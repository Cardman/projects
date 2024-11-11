package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public class GeneComponentModelSubscribeFactoryInts implements AbsGeneComponentModelSubscribeFactory<Ints> {

    private final AbstractProgramInfos core;
    private final FacadeGame facade;
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbsCommonFrame commonFrame;

    public GeneComponentModelSubscribeFactoryInts(AbstractProgramInfos _c, FacadeGame _f, SubscribedTranslationList _fact, AbsCommonFrame _def) {
        this.core = _c;
        this.facade = _f;
        subscribedTranslationList = _fact;
        commonFrame = _def;
    }

    @Override
    public AbsGeneComponentModelSubscribe<Ints> build() {
        return new GeneComponentModelSubscribeInts(core, facade, subscribedTranslationList, commonFrame);
    }
}
