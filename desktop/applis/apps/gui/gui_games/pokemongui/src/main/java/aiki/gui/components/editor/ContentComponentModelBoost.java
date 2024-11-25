package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelBoost {
    private CrudGeneFormSimpleFormSub<Statistic, Short> evs;
    private CrudGeneFormSimpleFormSub<String,Short> happiness;
    private GeneComponentModelRate winPp;
    private AbsPanel boostForm;
    AbsPanel form(GeneComponentModelItem _parent) {
        AbsCompoFactory compoFactory_ = _parent.getCompoFactory().getCompoFactory();
        boostForm = compoFactory_.newLineBox();
        evs=new CrudGeneFormSimpleFormSub<Statistic,Short>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        evs.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Short>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<Short>(new GeneComponentModelSubscribeShort(_parent.getCompoFactory())));
        boostForm.add(evs.getGroup());
        happiness = new CrudGeneFormSimpleFormSub<String, Short>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        happiness.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Short>(_parent.getSubscribedTranslationList().getFactoryIt(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryIt(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Short>(new GeneComponentModelSubscribeShort(_parent.getCompoFactory())));
        boostForm.add(happiness.getGroup());
        winPp=new GeneComponentModelRate(_parent.getCompoFactory());
        boostForm.add(winPp.geneRate());
        boostForm.setVisible(false);
        return boostForm;
    }
    void display(String _eff) {
        boostForm.setVisible(StringUtil.quickEq(_eff, Item.BOOST));
    }
    void buildEntity(Boost _item) {
        _item.setEvs(ConverterCommonMapUtil.buildIdMapStatisticShort(evs.getList()));
        _item.setHappiness(ConverterCommonMapUtil.buildStringMapShort(happiness.getList()));
        _item.setWinPp(winPp.valueRate());
    }
    void feedForm(Boost _item) {
        evs.setupValues(new MapToEntriesListUtil<Statistic,Short>().build(_item.getEvs()));
        happiness.setupValues(new MapToEntriesListUtil<String,Short>().build(_item.getHappiness()));
        winPp.valueRate(_item.getWinPp());
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(evs.subscribeButtons());
        ids_.addAllElts(happiness.subscribeButtons());
        return ids_;
    }

    public GeneComponentModelRate getWinPp() {
        return winPp;
    }

    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
}
