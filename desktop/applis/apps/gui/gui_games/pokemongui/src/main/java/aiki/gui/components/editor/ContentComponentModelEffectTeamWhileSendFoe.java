package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectTeamWhileSendFoe {

    private GeneComponentModelSubscribeString failSending;
    private GeneComponentModelSubscribeString damageRateAgainstFoe;
    private GeneComponentModelLsStrSub<String,StringList> deletedByFoeTypes;

    private CrudGeneFormSimpleFormSub<Long, String> statusByNbUses;
    private CrudGeneFormSimpleFormSub<Statistic, Long> statistics;

    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        failSending = new GeneComponentModelSubscribeString(_core.getProgramInfos(),_core.getFacadeGame());
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_SENDING,failSending.geneEnum()));
        failSending.addComplete();
        damageRateAgainstFoe = new GeneComponentModelSubscribeString(_core.getProgramInfos(),_core.getFacadeGame());
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_DAMAGE_RATE_AGAINST_FOE_INTRO,damageRateAgainstFoe.geneEnum()));
        damageRateAgainstFoe.addComplete();
        deletedByFoeTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_DELETE_STATUS_IF_TYPES,deletedByFoeTypes.geneEnum()));
        statusByNbUses = new CrudGeneFormSimpleFormSub<Long, String>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        statusByNbUses.initForm(new DisplayKeyOnlyShort<String>(), new ComparingShortKey<String>(), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core.getProgramInfos())),buildPart(_core, _core.getFactory().getFactorySt(),new StringMap<String>()),MessagesPkBean.EFF_TEAMWHILESENDINGFOE,MessagesDataEffteamwhilesendingfoe.M_P_67_NB_USES,MessagesDataEffteamwhilesendingfoe.M_P_67_STATUS);
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_STATUS_IF_NB,statusByNbUses.getGroup()));
        statistics = new CrudGeneFormSimpleFormSub<Statistic, Long>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        statistics.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(_core.getFactory().getFactoryStat(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<Statistic, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core.getProgramInfos(), _core.getFactory().getFactoryStat(), _core.getFacadeGame()),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core.getProgramInfos())),MessagesPkBean.EFF_TEAMWHILESENDINGFOE,MessagesDataEffteamwhilesendingfoe.M_P_67_STATISTIC,MessagesDataEffteamwhilesendingfoe.M_P_67_BOOST);
        selected_.add(line(_core,MessagesDataEffteamwhilesendingfoe.M_P_67_STATISTICS,statistics.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_TEAMWHILESENDINGFOE, _key,_input);
    }

    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
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
