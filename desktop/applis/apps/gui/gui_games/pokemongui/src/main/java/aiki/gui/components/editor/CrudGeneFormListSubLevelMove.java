package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.AbsCommonFrame;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormListSubLevelMove extends CrudGeneFormListSub<LevelMove> {

    private StringMap<String> messages;
    private GeneComponentModelLevelMove geneComponentModelLevelMove;

    public CrudGeneFormListSubLevelMove(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub,_fr, null);
    }
    public void initForm(AbstractProgramInfos _core, CustList<LevelMove> _moves) {
        getCrudGeneFormSubContent().clear();
        messages = new StringMap<String>(getCrudGeneFormSubContent().getFacadeGame().getData().getTranslatedMoves().getVal(getFactory().getLanguage()));
        geneComponentModelLevelMove = new GeneComponentModelLevelMove(_core, getCrudGeneFormSubContent().getFacadeGame());
        getCrudGeneFormSubContent().subscribeAll();
        initForm();
        initForm(new DisplayEntryCustLevelMove(messages), geneComponentModelLevelMove, _moves, new ComparingLevelMove(messages));
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return geneComponentModelLevelMove.all();
    }

    @Override
    public IdList<SubscribedTranslation> subscribe() {
        return new IdList<SubscribedTranslation>();
    }
    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMvMessages(messages));
        ids_.add(new SubscribedTranslationPkKey(this));
        return ids_;
    }
}
