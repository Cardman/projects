package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectCounterAttack {

    private CrudGeneFormSimpleFormSub<String, Rate> sufferingDamageTypes;
    private CrudGeneFormSimpleFormSub<Statistic, Long> droppedStatDirectMove;
    private GeneComponentModelRate sufferingDamageDirectMove;
    private GeneComponentModelSubscribeString protectFail;
    private GeneComponentModelSubscribeString counterFail;

    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        protectFail = new GeneComponentModelSubscribeString(_core.getProgramInfos(),_core.getFacadeGame());
        selected_.add(line(_core,MessagesDataEffcounterattack.M_P_44_FAILS_PROTECT,protectFail.geneEnum()));
        protectFail.addComplete();
        counterFail = new GeneComponentModelSubscribeString(_core.getProgramInfos(),_core.getFacadeGame());
        selected_.add(line(_core,MessagesDataEffcounterattack.M_P_44_FAILS_COUNTER,counterFail.geneEnum()));
        counterFail.addComplete();
        sufferingDamageDirectMove = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffcounterattack.M_P_44_SUFFERING_DIRECT_INTRO,sufferingDamageDirectMove.geneRate()));
        sufferingDamageTypes = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        sufferingDamageTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String, Rate>(_core.getFactory().getFactoryTy(), _core.getProgramInfos(), _core.getFacadeGame(), new StringMap<String>()),buildPart(_core, _core.getFactory().getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.EFF_COUNTERATTACK,MessagesDataEffcounterattack.M_P_44_SUFFERING_TYPES_T,MessagesDataEffcounterattack.M_P_44_SUFFERING_TYPES_HP);
        selected_.add(line(_core,MessagesDataEffcounterattack.M_P_44_SUFFERING_TYPES_INTRO,sufferingDamageTypes.getGroup()));
        droppedStatDirectMove = new CrudGeneFormSimpleFormSub<Statistic, Long>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        droppedStatDirectMove.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic, Long>(_core.getFactory().getFactoryStat(), _core.getProgramInfos(), _core.getFacadeGame(), new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core.getProgramInfos(), _core.getFactory().getFactoryStat(), _core.getFacadeGame()),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core.getProgramInfos())),MessagesPkBean.EFF_COUNTERATTACK,MessagesDataEffcounterattack.M_P_44_DROPPED_STAT_S,MessagesDataEffcounterattack.M_P_44_DROPPED_STAT_V);
        selected_.add(line(_core,MessagesDataEffcounterattack.M_P_44_DROPPED_STAT_INTRO,droppedStatDirectMove.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_COUNTERATTACK, _key,_input);
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
    }
    void buildEntity(EffectCounterAttack _edited) {
        _edited.setProtectFail(protectFail.tryRet());
        _edited.setCounterFail(counterFail.tryRet());
        _edited.setSufferingDamageDirectMove(sufferingDamageDirectMove.valueRate());
        _edited.setSufferingDamageTypes(ConverterCommonMapUtil.buildStringMapRate(sufferingDamageTypes.getList()));
        _edited.setDroppedStatDirectMove(ConverterCommonMapUtil.buildIdMapStatisticInteger(droppedStatDirectMove.getList()));
    }
    void feedForm(EffectCounterAttack _edited) {
        protectFail.setupValue(_edited.getProtectFail());
        counterFail.setupValue(_edited.getCounterFail());
        sufferingDamageDirectMove.valueRate(_edited.getSufferingDamageDirectMove());
        sufferingDamageTypes.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getSufferingDamageTypes()));
        droppedStatDirectMove.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(_edited.getDroppedStatDirectMove()));
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }


    public GeneComponentModelSubscribeString getProtectFail() {
        return protectFail;
    }

    public GeneComponentModelSubscribeString getCounterFail() {
        return counterFail;
    }

    public GeneComponentModelRate getSufferingDamageDirectMove() {
        return sufferingDamageDirectMove;
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getSufferingDamageTypes() {
        return sufferingDamageTypes;
    }

    public CrudGeneFormSimpleFormSub<Statistic, Long> getDroppedStatDirectMove() {
        return droppedStatDirectMove;
    }
}
