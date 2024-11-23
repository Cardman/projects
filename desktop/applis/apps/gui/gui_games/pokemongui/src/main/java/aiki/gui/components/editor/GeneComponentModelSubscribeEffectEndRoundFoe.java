package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.util.*;

public final class GeneComponentModelSubscribeEffectEndRoundFoe implements AbsGeneComponentModelSubscribe<EffectEndRoundFoe> {
    private final GeneComponentModelEffectEndRoundFoe crud;
    public GeneComponentModelSubscribeEffectEndRoundFoe(GeneComponentModelEffectEndRoundFoe _c) {
        crud = _c;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneEffect();
    }

    @Override
    public EffectEndRoundFoe tryRet() {
        return crud.valueEffect();
    }

    @Override
    public void setupValue(EffectEndRoundFoe _value) {
        crud.valueEffect(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return crud.all();
    }

    public GeneComponentModelEffectEndRoundFoe getCrud() {
        return crud;
    }
}
