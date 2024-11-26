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
    private CrudGeneFormSimpleFormSub<Statistic, Byte> droppedStatDirectMove;
    private GeneComponentModelRate sufferingDamageDirectMove;
    private GeneComponentModelText protectFail;
    private GeneComponentModelText counterFail;

    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        protectFail = new GeneComponentModelText(_core);
        selected_.add(protectFail.geneString());
        counterFail = new GeneComponentModelText(_core);
        selected_.add(counterFail.geneString());
        sufferingDamageDirectMove = new GeneComponentModelRate(_core);
        selected_.add(sufferingDamageDirectMove.geneRate());
        sufferingDamageTypes = new CrudGeneFormSimpleFormSub<String, Rate>(_core, _fac, _fact, _f);
        sufferingDamageTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String, Rate>(_fact.getFactoryTy(), _core, _fac, new StringMap<String>()),buildPart(_core,_fac,_fact.getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core)));
        selected_.add(sufferingDamageTypes.getGroup());
        droppedStatDirectMove = new CrudGeneFormSimpleFormSub<Statistic, Byte>(_core, _fac, _fact, _f);
        droppedStatDirectMove.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic, Byte>(_fact.getFactoryStat(), _core, _fac, new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac),new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(_core)));
        selected_.add(droppedStatDirectMove.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void buildEntity(EffectCounterAttack _edited) {
        _edited.setProtectFail(protectFail.valueString());
        _edited.setCounterFail(counterFail.valueString());
        _edited.setSufferingDamageDirectMove(sufferingDamageDirectMove.valueRate());
        _edited.setSufferingDamageTypes(ConverterCommonMapUtil.buildStringMapRate(sufferingDamageTypes.getList()));
        _edited.setDroppedStatDirectMove(ConverterCommonMapUtil.buildIdMapStatisticByte(droppedStatDirectMove.getList()));
    }
    void feedForm(EffectCounterAttack _edited) {
        protectFail.valueString(_edited.getProtectFail());
        counterFail.valueString(_edited.getCounterFail());
        sufferingDamageDirectMove.valueRate(_edited.getSufferingDamageDirectMove());
        sufferingDamageTypes.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getSufferingDamageTypes()));
        droppedStatDirectMove.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(_edited.getDroppedStatDirectMove()));
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }


    public GeneComponentModelRate getSufferingDamageDirectMove() {
        return sufferingDamageDirectMove;
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getSufferingDamageTypes() {
        return sufferingDamageTypes;
    }

    public CrudGeneFormSimpleFormSub<Statistic, Byte> getDroppedStatDirectMove() {
        return droppedStatDirectMove;
    }
}
