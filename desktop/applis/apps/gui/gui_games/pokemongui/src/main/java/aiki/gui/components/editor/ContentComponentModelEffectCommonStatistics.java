package aiki.gui.components.editor;

import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectCommonStatistics {

    private CrudGeneFormSimpleFormSub<Statistic, String> commonValue;

    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        commonValue = new CrudGeneFormSimpleFormSub<Statistic,String>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        commonValue.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,String>(_core.getFactory().getFactoryStat(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core.getProgramInfos(), _core.getFactory().getFactoryStat(), _core.getFacadeGame()), new GeneComponentModelSubscribeFactoryString(_core.getProgramInfos(),_core.getFacadeGame()),MessagesPkBean.EFF_COMMONSTATISTICS,MessagesDataEffcommonstatistics.M_P_41_COMMON_STAT,MessagesDataEffcommonstatistics.M_P_41_COMMON_VALUE);
        selected_.add(line(_core,MessagesDataEffcommonstatistics.M_P_41_COMMON,commonValue.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }

    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_COMMONSTATISTICS, _key,_input);
    }
    void buildEntity(EffectCommonStatistics _edited) {
        _edited.setCommonValue(ConverterCommonMapUtil.buildIdMapStatisticString(commonValue.getList()));
    }
    void feedForm(EffectCommonStatistics _edited) {
        commonValue.setupValues(new MapToEntriesListUtil<Statistic,String>().build(_edited.getCommonValue()));
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    public CrudGeneFormSimpleFormSub<Statistic, String> getCommonValue() {
        return commonValue;
    }
}
