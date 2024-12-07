package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.evolution.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeEvolution implements AbsGeneComponentModelSubscribe<Evolution> {
    private final GeneComponentModelEvolution crud;
    public GeneComponentModelSubscribeEvolution(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        crud = new GeneComponentModelEvolution(_fr,_fact,_facade,_sub);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneEvo();
    }

    @Override
    public Evolution tryRet() {
        return crud.valueEvo();
    }

    @Override
    public void setupValue(Evolution _value) {
        crud.valueEvo(ConverterCommonMapUtil.copyEvolution(_value));
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return crud.all();
    }

    public GeneComponentModelEvolution getCrud() {
        return crud;
    }
}
