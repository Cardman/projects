package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectTeamWhileSendFoe {

    private GeneComponentModelSubscribeString failSending;
    private GeneComponentModelSubscribeString damageRateAgainstFoe;
    private GeneComponentModelLsStrSub<String,StringList> deletedByFoeTypes;

    private CrudGeneFormSimpleFormSub<Long, String> statusByNbUses;
    private CrudGeneFormSimpleFormSub<Statistic, Long> statistics;

    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        failSending = new GeneComponentModelSubscribeString(_core,_fac);
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_SENDING,failSending.geneEnum()));
        failSending.addComplete();
        damageRateAgainstFoe = new GeneComponentModelSubscribeString(_core,_fac);
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_DAMAGE_RATE_AGAINST_FOE_INTRO,damageRateAgainstFoe.geneEnum()));
        damageRateAgainstFoe.addComplete();
        deletedByFoeTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_DELETE_STATUS_IF_TYPES,deletedByFoeTypes.geneEnum()));
        statusByNbUses = new CrudGeneFormSimpleFormSub<Long, String>(_core, _fac, _fact, _f);
        statusByNbUses.initForm(new DisplayKeyOnlyShort<String>(), new ComparingShortKey<String>(), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core)),buildPart(_core,_fac,_fact.getFactorySt(),new StringMap<String>()),formatTxt(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_NB_USES),formatTxt(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_STATUS));
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_STATUS_IF_NB,statusByNbUses.getGroup()));
        statistics = new CrudGeneFormSimpleFormSub<Statistic, Long>(_core, _fac, _fact, _f);
        statistics.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(_fact.getFactoryStat(),_core,_fac, new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core)),formatTxt(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_STATISTIC),formatTxt(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_BOOST));
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_STATISTICS,statistics.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbstractProgramInfos _core, String _key, AbsCustComponent _input) {
        return SubscribedTranslationList.lineDir(_core,formatTxt(_core,_key),_input);
    }

    private String formatTxt(AbstractProgramInfos _core,String _key) {
        return SubscribedTranslationList.formatTxt(_core, MessagesPkBean.EFF_TEAMWHILESENDINGFOE, _key);
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void buildEntity(EffectTeamWhileSendFoe _edited) {
        _edited.setFailSending(failSending.tryRet());
        _edited.setDamageRateAgainstFoe(damageRateAgainstFoe.tryRet());
        _edited.setDeletedByFoeTypes(deletedByFoeTypes.tryRet());
        _edited.setStatusByNbUses(ConverterCommonMapUtil.buildIntegerMapString(statusByNbUses.getList()));
        _edited.setStatistics(ConverterCommonMapUtil.buildIdMapStatisticInteger(statistics.getList()));
    }
    void feedForm(EffectTeamWhileSendFoe _edited) {
        failSending.setupValue(_edited.getFailSending());
        damageRateAgainstFoe.setupValue(_edited.getDamageRateAgainstFoe());
        deletedByFoeTypes.setupValue(_edited.getDeletedByFoeTypes());
        statusByNbUses.setupValues(new MapToEntriesListUtil<Long,String>().build(_edited.getStatusByNbUses()));
        statistics.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(_edited.getStatistics()));
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    public GeneComponentModelSubscribeString getFailSending() {
        return failSending;
    }

    public GeneComponentModelSubscribeString getDamageRateAgainstFoe() {
        return damageRateAgainstFoe;
    }

    public GeneComponentModelLsStrSub<String,StringList> getDeletedByFoeTypes() {
        return deletedByFoeTypes;
    }

    public CrudGeneFormSimpleFormSub<Long, String> getStatusByNbUses() {
        return statusByNbUses;
    }

    public CrudGeneFormSimpleFormSub<Statistic, Long> getStatistics() {
        return statistics;
    }
}
