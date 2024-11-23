package aiki.gui.components.editor;

import aiki.fight.effects.*;
import code.gui.*;
import code.util.*;

public final class GeneComponentModelSubscribeEffectWhileSending implements AbsGeneComponentModelSubscribe<EffectWhileSendingWithStatistic> {
    private final GeneComponentModelEffectWhileSending crud;
    public GeneComponentModelSubscribeEffectWhileSending(GeneComponentModelEffectWhileSending _c) {
        crud = _c;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneEffect();
    }

    @Override
    public EffectWhileSendingWithStatistic tryRet() {
        return crud.valueEffect();
    }

    @Override
    public void setupValue(EffectWhileSendingWithStatistic _value) {
        crud.valueEffect(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return crud.all();
    }

    public GeneComponentModelEffectWhileSending getCrud() {
        return crud;
    }
}
