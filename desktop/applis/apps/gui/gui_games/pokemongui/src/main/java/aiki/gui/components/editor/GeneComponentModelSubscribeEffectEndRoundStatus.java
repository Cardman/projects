package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.util.*;

public final class GeneComponentModelSubscribeEffectEndRoundStatus implements AbsGeneComponentModelSubscribe<EffectEndRoundStatus> {
    private final GeneComponentModelEffectEndRoundStatus crud;
    public GeneComponentModelSubscribeEffectEndRoundStatus(GeneComponentModelEffectEndRoundStatus _c) {
        crud = _c;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneEffect();
    }

    @Override
    public EffectEndRoundStatus tryRet() {
        return crud.valueEffect();
    }

    @Override
    public void setupValue(EffectEndRoundStatus _value) {
        crud.valueEffect(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return crud.all();
    }

}
