package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormListSubEffect extends CrudGeneFormListSub<Effect> {

    private GeneComponentModelEffect componentModelEffect;

    public CrudGeneFormListSubEffect(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub,_fr, null);
    }
    public void initForm(AbstractProgramInfos _core, CustList<Effect> _moves) {
        getCrudGeneFormSubContent().clear();
        initForm();
        componentModelEffect = new GeneComponentModelEffect(getFrame(), _core, getCrudGeneFormSubContent().getFacadeGame(), getCrudGeneFormSubContent().getSubscription());
        initForm(new DisplayEntryCustEffect(), componentModelEffect, _moves);
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return componentModelEffect.all();
    }

}
