package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeLevelMove implements AbsGeneComponentModelSubscribe<LevelMove> {
    private final GeneComponentModelLevelMove crud;
    public GeneComponentModelSubscribeLevelMove(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        crud = new GeneComponentModelLevelMove(_fact,_facade,_sub);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        return crud.geneLevelMove();
    }

    @Override
    public LevelMove tryRet() {
        return crud.valueLevelMove();
    }

    @Override
    public void setupValue(LevelMove _value) {
        crud.valueLevelMove(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return crud.all();
    }

    public GeneComponentModelLevelMove getCrud() {
        return crud;
    }
}
