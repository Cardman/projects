package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import aiki.fight.pokemon.evolution.Evolution;
import code.gui.AbsCommonFrame;
import code.gui.initialize.AbstractProgramInfos;

public class GeneComponentModelSubscribeFactoryEvolution implements AbsGeneComponentModelSubscribeFactory<Evolution> {

    private final AbstractProgramInfos core;
    private final FacadeGame facade;
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbsCommonFrame commonFrame;

    public GeneComponentModelSubscribeFactoryEvolution(AbstractProgramInfos _c, FacadeGame _f, SubscribedTranslationList _fact, AbsCommonFrame _def) {
        this.core = _c;
        this.facade = _f;
        subscribedTranslationList = _fact;
        commonFrame = _def;
    }

    @Override
    public AbsGeneComponentModelSubscribe<Evolution> build() {
        return new GeneComponentModelSubscribeEvolution(core, facade, subscribedTranslationList, commonFrame);
    }
}
