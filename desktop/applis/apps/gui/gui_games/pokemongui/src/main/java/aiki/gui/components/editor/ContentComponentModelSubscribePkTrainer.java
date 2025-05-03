package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelSubscribePkTrainer {
    private CrudGeneFormSimpleElementSub<PkTrainer> walk;

    public AbsPanel form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f, String _k) {
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        walk = new CrudGeneFormSimpleElementSub<PkTrainer>(_core, _fac, _fact, _f);
        walk.initForm(new DisplayEntryCustSubElementEffect<PkTrainer>(),new GeneComponentModelSubscribeFactoryDirect<PkTrainer>(new GeneComponentModelSubscribePkTrainer(_core,_fac,_fact, _f)));
        form_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,_k,walk.getGroup()));
        return form_;
    }

    public CrudGeneFormSimpleElementSub<PkTrainer> getWalk() {
        return walk;
    }

}
