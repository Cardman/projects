package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.util.*;

public final class GeneComponentModelSubscribeEffectEndRound implements AbsGeneComponentModelSubscribe<EffectEndRound> {
    private final GeneComponentModelEffectEndRound crud;
    public GeneComponentModelSubscribeEffectEndRound(GeneComponentModelEffectEndRound _c) {
        crud = _c;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneEffect();
    }

    @Override
    public EffectEndRound tryRet() {
        return crud.valueEffect();
    }

    @Override
    public void setupValue(EffectEndRound _value) {
        crud.valueEffect(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return crud.all();
    }

    public GeneComponentModelEffectEndRound getCrud() {
        return crud;
    }
}
