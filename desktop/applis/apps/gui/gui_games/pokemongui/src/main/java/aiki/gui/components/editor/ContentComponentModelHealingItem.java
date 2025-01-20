package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.items.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelHealingItem {
    private CrudGeneFormSimpleFormSub<String,Long> happiness;
    private AbsCustCheckBox healingTeam;
    private AbsPanel healingItemForm;
    AbsPanel form(GeneComponentModelItem _parent) {
        AbsCompoFactory compoFactory_ = _parent.getCompoFactory().getCompoFactory();
        healingItemForm = compoFactory_.newLineBox();
        happiness = new CrudGeneFormSimpleFormSub<String, Long>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        happiness.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Long>(_parent.getSubscribedTranslationList().getFactoryIt(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryIt(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_parent.getCompoFactory())));
        healingItemForm.add(happiness.getGroup());
        healingTeam=compoFactory_.newCustCheckBox();
        healingItemForm.add(healingTeam);
        healingItemForm.setVisible(false);
        return healingItemForm;
    }
    void display(String _eff) {
        healingItemForm.setVisible(StringUtil.quickEq(_eff, Item.HEALING_HP) || StringUtil.quickEq(_eff, Item.HEALING_HP_STATUS) || StringUtil.quickEq(_eff, Item.HEALING_ITEM) || StringUtil.quickEq(_eff, Item.HEALING_PP) || StringUtil.quickEq(_eff, Item.HEALING_STATUS));
    }
    void buildEntity(HealingItem _item) {
        _item.setHappiness(ConverterCommonMapUtil.buildStringMapInteger(happiness.getList()));
        _item.setHealingTeam(healingTeam.isSelected());
    }
    void feedForm(HealingItem _item) {
        happiness.setupValues(new MapToEntriesListUtil<String,Long>().build(_item.getHappiness()));
        healingTeam.setSelected(_item.getHealingTeam());
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(happiness.subscribeButtons());
        return ids_;
    }

    public AbsCustCheckBox getHealingTeam() {
        return healingTeam;
    }

    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
}
