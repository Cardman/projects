package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelEffectTeamWhileSendFoe {

    private GeneComponentModelText failSending;
    private GeneComponentModelText damageRateAgainstFoe;
    private GeneComponentModelLsStrSub<String,StringList> deletedByFoeTypes;

    private CrudGeneFormSimpleFormSub<Short, String> statusByNbUses;
    private CrudGeneFormSimpleFormSub<Statistic, Byte> statistics;

    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        failSending = new GeneComponentModelText(_core);
        selected_.add(failSending.geneString());
        damageRateAgainstFoe = new GeneComponentModelText(_core);
        selected_.add(damageRateAgainstFoe.geneString());
        deletedByFoeTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(deletedByFoeTypes.geneEnum());
        statusByNbUses = new CrudGeneFormSimpleFormSub<Short, String>(_core, _fac, _fact, _f);
        statusByNbUses.initForm(new DisplayKeyOnlyShort<String>(), new ComparingShortKey<String>(), new GeneComponentModelSubscribeFactoryDirect<Short>(new GeneComponentModelSubscribeShort(_core)),buildPart(_core,_fac,_fact.getFactorySt(),new StringMap<String>()));
        selected_.add(statusByNbUses.getGroup());
        statistics = new CrudGeneFormSimpleFormSub<Statistic, Byte>(_core, _fac, _fact, _f);
        statistics.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(_fact.getFactoryStat(),_core,_fac, new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac),new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(_core)));
        selected_.add(statistics.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void buildEntity(EffectTeamWhileSendFoe _edited) {
        _edited.setFailSending(failSending.valueString());
        _edited.setDamageRateAgainstFoe(damageRateAgainstFoe.valueString());
        _edited.setDeletedByFoeTypes(deletedByFoeTypes.tryRet());
        _edited.setStatusByNbUses(ConverterCommonMapUtil.buildShortMapString(statusByNbUses.getList()));
        _edited.setStatistics(ConverterCommonMapUtil.buildIdMapStatisticByte(statistics.getList()));
    }
    void feedForm(EffectTeamWhileSendFoe _edited) {
        failSending.valueString(_edited.getFailSending());
        damageRateAgainstFoe.valueString(_edited.getDamageRateAgainstFoe());
        deletedByFoeTypes.setupValue(_edited.getDeletedByFoeTypes());
        statusByNbUses.setupValues(new MapToEntriesListUtil<Short,String>().build(_edited.getStatusByNbUses()));
        statistics.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(_edited.getStatistics()));
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    public GeneComponentModelLsStrSub<String,StringList> getDeletedByFoeTypes() {
        return deletedByFoeTypes;
    }

    public CrudGeneFormSimpleFormSub<Short, String> getStatusByNbUses() {
        return statusByNbUses;
    }

    public CrudGeneFormSimpleFormSub<Statistic, Byte> getStatistics() {
        return statistics;
    }
}
