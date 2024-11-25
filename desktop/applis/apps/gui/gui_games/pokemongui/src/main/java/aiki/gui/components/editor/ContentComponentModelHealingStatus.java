package aiki.gui.components.editor;

import aiki.fight.items.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelHealingStatus {
    private GeneComponentModelLsStrSub<String,StringList> status;
    private AbsCustCheckBox healingKo;
    private AbsPanel healingStatusForm;
    AbsPanel form(GeneComponentModelItem _parent) {
        AbsCompoFactory compoFactory_ = _parent.getCompoFactory().getCompoFactory();
        healingStatusForm = compoFactory_.newLineBox();
        status=ConverterCommonMapUtil.buildStatusList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        healingStatusForm.add(status.geneEnum());
        healingKo=compoFactory_.newCustCheckBox();
        healingStatusForm.add(healingKo);
        healingStatusForm.setVisible(false);
        return healingStatusForm;
    }
    void display(String _eff) {
        healingStatusForm.setVisible(StringUtil.quickEq(_eff, Item.HEALING_STATUS) || StringUtil.quickEq(_eff, Item.HEALING_HP_STATUS));
    }
    void buildEntity(HealingStatus _item) {
        _item.setStatus(status.tryRet());
        _item.setHealingKo(healingKo.isSelected());
    }
    void feedForm(HealingStatus _item) {
        status.setupValue(_item.getStatus());
        healingKo.setSelected(_item.getHealingKo());
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(status.getSubs());
        return ids_;
    }

    public AbsCustCheckBox getHealingKo() {
        return healingKo;
    }

}
