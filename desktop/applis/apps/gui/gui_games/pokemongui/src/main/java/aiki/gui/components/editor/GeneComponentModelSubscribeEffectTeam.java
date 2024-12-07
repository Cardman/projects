package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.util.*;

public final class GeneComponentModelSubscribeEffectTeam implements AbsGeneComponentModelSubscribe<EffectTeam> {
    private final GeneComponentModelEffectTeam crud;
    public GeneComponentModelSubscribeEffectTeam(GeneComponentModelEffectTeam _c) {
        crud = _c;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneEffect();
    }

    @Override
    public EffectTeam tryRet() {
        return crud.valueEffect();
    }

    @Override
    public void setupValue(EffectTeam _value) {
        crud.valueEffect(ConverterCommonMapUtil.copyEffectTeam(_value));
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return getCrud().all();
    }

    public GeneComponentModelEffectTeam getCrud() {
        return crud;
    }
}
