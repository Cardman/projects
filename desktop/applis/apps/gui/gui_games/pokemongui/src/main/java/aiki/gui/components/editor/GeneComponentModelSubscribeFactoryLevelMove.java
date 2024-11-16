package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.initialize.*;

public class GeneComponentModelSubscribeFactoryLevelMove implements AbsGeneComponentModelSubscribeFactory<LevelMove> {

    private final AbstractProgramInfos core;
    private final FacadeGame facade;
    private final SubscribedTranslationList subscribedTranslationList;

    public GeneComponentModelSubscribeFactoryLevelMove(AbstractProgramInfos _c, FacadeGame _f, SubscribedTranslationList _fact) {
        this.core = _c;
        this.facade = _f;
        subscribedTranslationList = _fact;
    }

    @Override
    public AbsGeneComponentModelSubscribe<LevelMove> build() {
        return new GeneComponentModelSubscribeLevelMove(core, facade, subscribedTranslationList);
    }
}
