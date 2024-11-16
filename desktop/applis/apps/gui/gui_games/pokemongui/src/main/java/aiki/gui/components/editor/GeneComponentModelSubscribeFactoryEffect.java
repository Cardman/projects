package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;

public class GeneComponentModelSubscribeFactoryEffect implements AbsGeneComponentModelSubscribeFactory<Effect> {

    private final GeneComponentModelEffect curd;

    public GeneComponentModelSubscribeFactoryEffect(GeneComponentModelEffect _c) {
        curd = _c;
    }

    @Override
    public AbsGeneComponentModelSubscribe<Effect> build() {
        return new GeneComponentModelSubscribeEffect(curd);
    }
}
