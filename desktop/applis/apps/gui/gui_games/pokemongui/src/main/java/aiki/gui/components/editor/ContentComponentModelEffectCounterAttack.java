package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectCounterAttack {

    private CrudGeneFormSimpleFormSub<String, Rate> sufferingDamageTypes;
    private CrudGeneFormSimpleFormSub<Statistic, Long> droppedStatDirectMove;
    private GeneComponentModelRate sufferingDamageDirectMove;
    private GeneComponentModelSubscribeString protectFail;
    private GeneComponentModelSubscribeString counterFail;

    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        protectFail = new GeneComponentModelSubscribeString(_core,_fac);
        selected_.add(protectFail.geneEnum());
        protectFail.addComplete();
        counterFail = new GeneComponentModelSubscribeString(_core,_fac);
        selected_.add(counterFail.geneEnum());
        counterFail.addComplete();
        sufferingDamageDirectMove = new GeneComponentModelRate(_core);
        selected_.add(sufferingDamageDirectMove.geneRate());
        sufferingDamageTypes = new CrudGeneFormSimpleFormSub<String, Rate>(_core, _fac, _fact, _f);
        sufferingDamageTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String, Rate>(_fact.getFactoryTy(), _core, _fac, new StringMap<String>()),buildPart(_core,_fac,_fact.getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(sufferingDamageTypes.getGroup());
        droppedStatDirectMove = new CrudGeneFormSimpleFormSub<Statistic, Long>(_core, _fac, _fact, _f);
        droppedStatDirectMove.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic, Long>(_fact.getFactoryStat(), _core, _fac, new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core)));
        selected_.add(droppedStatDirectMove.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
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
