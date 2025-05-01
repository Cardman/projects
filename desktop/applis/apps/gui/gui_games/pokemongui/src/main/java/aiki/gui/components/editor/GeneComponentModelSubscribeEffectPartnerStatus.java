package aiki.gui.components.editor;

import aiki.fight.status.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class GeneComponentModelSubscribeEffectPartnerStatus implements AbsGeneComponentModelSubscribe<EffectPartnerStatus> {
    private final GeneComponentModelStatus programInfos;
    private final GeneComponentModelRate multDamageAgainstFoe;
    private final GeneComponentModelRate restoredHpRateLovedAlly;
    private AbsCustCheckBox weddingAlly;

    public GeneComponentModelSubscribeEffectPartnerStatus(GeneComponentModelStatus _fact) {
        programInfos = _fact;
        multDamageAgainstFoe = new GeneComponentModelRate(_fact.getCompoFactory());
        restoredHpRateLovedAlly = new GeneComponentModelRate(_fact.getCompoFactory());
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().getCompoFactory().newLineBox();
        form_.add(line(MessagesDataStatus.M_P_88_DAMAGED_FOES_INTRO,multDamageAgainstFoe.geneRate()));
        form_.add(line(MessagesDataStatus.M_P_88_HEAL_HP_INTRO,restoredHpRateLovedAlly.geneRate()));
        weddingAlly = programInfos.getCompoFactory().getCompoFactory().newCustCheckBox();
        form_.add(line(MessagesDataStatus.M_P_88_WEDDING,weddingAlly));
        return form_;
    }
    private AbsCustComponent line(String _key, AbsCustComponent _input) {
        return programInfos.line(MessagesPkBean.STATUS, _key,_input);
    }
    @Override
    public EffectPartnerStatus tryRet() {
        EffectPartnerStatus lv_ = new EffectPartnerStatus();
        lv_.setMultDamageAgainstFoe(multDamageAgainstFoe.valueRate());
        lv_.setRestoredHpRateLovedAlly(restoredHpRateLovedAlly.valueRate());
        lv_.setWeddingAlly(weddingAlly.isSelected());
        return lv_;
    }

    @Override
    public void setupValue(EffectPartnerStatus _value) {
        multDamageAgainstFoe.valueRate(_value.getMultDamageAgainstFoe());
        restoredHpRateLovedAlly.valueRate(_value.getRestoredHpRateLovedAlly());
        weddingAlly.setSelected(_value.getWeddingAlly());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

    public AbsCustCheckBox getWeddingAlly() {
        return weddingAlly;
    }
}
