package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectEndRoundMultiRelation {
    private CrudGeneFormSimpleFormSub<String,Rate> damageByStatus;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        damageByStatus = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        damageByStatus.initForm(new DisplayEntryCustSubImpl<String>(_core.getFactory().getFactorySt(), new StringMap<String>()),_core.getFactory().getFactorySt().buildMessages(_core.getProgramInfos(),_core.getFacadeGame()), buildPart(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory().getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())));
        selected_.add(damageByStatus.getGroup());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundMultiRelation _edited) {
        _edited.setDamageByStatus(ConverterCommonMapUtil.buildStringMapRate(damageByStatus.getList()));
    }
    void feedForm(EffectEndRoundMultiRelation _edited) {
        damageByStatus.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getDamageByStatus()));
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getDamageByStatus() {
        return damageByStatus;
    }

}
