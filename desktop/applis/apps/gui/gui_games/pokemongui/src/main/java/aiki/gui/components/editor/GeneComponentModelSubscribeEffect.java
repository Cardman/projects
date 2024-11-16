package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.util.*;

public final class GeneComponentModelSubscribeEffect implements AbsGeneComponentModelSubscribe<Effect> {
    private final GeneComponentModelEffect crud;
    public GeneComponentModelSubscribeEffect(GeneComponentModelEffect _c) {
        crud = _c;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneEffect();
    }

    @Override
    public Effect tryRet() {
        return crud.valueEffect();
    }

    @Override
    public void setupValue(Effect _value) {
        crud.valueEffect(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return crud.all();
    }

    public GeneComponentModelEffect getCrud() {
        return crud;
    }
}
