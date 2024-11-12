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
        messages = subscription().getFactoryMv().buildMessages(_core,getCrudGeneFormSubContent().getFacadeGame());
        geneComponentModelLevelMove = new GeneComponentModelLevelMove(_core, getCrudGeneFormSubContent().getFacadeGame(),subscription());
        initForm();
        initForm(new DisplayEntryCustLevelMove(messages), geneComponentModelLevelMove, _moves, new ComparingLevelMove(messages));
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return geneComponentModelLevelMove.all();
    }

    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages(messages,subscription().getFactoryMv(), new StringMap<String>()));
        ids_.add(new SubscribedTranslationPkKey<LevelMove>(this));
        return ids_;
    }
}
