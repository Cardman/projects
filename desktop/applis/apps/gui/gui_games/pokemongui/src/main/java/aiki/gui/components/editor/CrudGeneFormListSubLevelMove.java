package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormListSubLevelMove extends CrudGeneFormListSub<LevelMove> {

    private GeneComponentModelLevelMove geneComponentModelLevelMove;

    public CrudGeneFormListSubLevelMove(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fact, _facade, _sub, null);
    }
    public void initForm(AbstractProgramInfos _core, CustList<LevelMove> _moves) {
        getCrudGeneFormSubContent().clear();
        StringMap<String> messages_ = new StringMap<String>(getCrudGeneFormSubContent().getFacadeGame().getData().getTranslatedMoves().getVal(_core.getLanguage()));
        geneComponentModelLevelMove = new GeneComponentModelLevelMove(_core, getCrudGeneFormSubContent().getFacadeGame());
        subscribeAll();
        initForm();
        initForm(new DisplayEntryCustLevelMove(messages_), geneComponentModelLevelMove, _moves,new ComparingLevelMove(messages_));
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return geneComponentModelLevelMove.all();
    }

    public GeneComponentModelLevelMove getGeneComponentModelLevelMove() {
        return geneComponentModelLevelMove;
    }

    @Override
    protected void afterModif(int _index, LevelMove _value) {
        if (_index > -1) {
            getList().remove(_index);
        }
        afterChange();
    }

    @Override
    protected void afterChange() {
        getCrudGeneFormSubContent().removeOpenSub();
        subscribeAll();
        afterModif();
    }

    @Override
    protected IdList<SubscribedTranslation> subscribe() {
        return new IdList<SubscribedTranslation>();
    }

}
