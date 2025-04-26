package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectEndRoundMultiRelation {
    private CrudGeneFormSimpleFormSub<String,Rate> damageByStatus;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        damageByStatus = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        damageByStatus.initFormWithVal(new DisplayEntryCustSubElementImpl<String, Rate>(_core.getFactory().getFactorySt(), _core.getProgramInfos(),_core.getFacadeGame(),new StringMap<String>()), buildPart(_core, _core.getFactory().getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.ENDROUND_MULTIRELATION,MessagesDataEndroundMultirelation.M_P_7_DAMAGE_STATUS_KEY,MessagesDataEndroundMultirelation.M_P_7_DAMAGE_STATUS_RATE);
        selected_.add(line(_core,MessagesDataEndroundMultirelation.M_P_7_DAMAGE_STATUS,damageByStatus.getGroup()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.ENDROUND_MULTIRELATION, _key,_input);
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
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
