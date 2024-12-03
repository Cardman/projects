package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelEffectCommonStatistics {

    private CrudGeneFormSimpleFormSub<Statistic, String> commonValue;

    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        commonValue = new CrudGeneFormSimpleFormSub<Statistic,String>(_core, _fac, _fact, _f);
        commonValue.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,String>(_fact.getFactoryStat(),_core,_fac, new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_core, _fact.getFactoryStat(), _fac), new GeneComponentModelSubscribeFactoryString(_core,_fac));
        selected_.add(commonValue.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
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
