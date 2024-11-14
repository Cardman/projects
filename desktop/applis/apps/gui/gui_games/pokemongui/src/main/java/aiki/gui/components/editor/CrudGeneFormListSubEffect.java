package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormListSubEffect extends CrudGeneFormListSub<Effect> {

    public CrudGeneFormListSubEffect(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub,_fr, null);
    }
    public void initForm(AbstractProgramInfos _core, CustList<Effect> _moves) {
        getCrudGeneFormSubContent().clear();
        initForm();
        initForm(new DisplayEntryCustEffect(), new GeneComponentModelEffect(getFrame(),_core, getCrudGeneFormSubContent().getFacadeGame(), getCrudGeneFormSubContent().getSubscription()), _moves);
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return new IdList<SubscribedTranslation>();
    }

}
