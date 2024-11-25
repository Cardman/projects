package aiki.gui.components.editor;

import aiki.fight.status.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.Rate;
import code.util.*;

public final class GeneComponentModelSubscribeEffectPartnerStatus implements AbsGeneComponentModelSubscribe<EffectPartnerStatus> {
    private final AbstractProgramInfos programInfos;
    private final GeneComponentModelRate multDamageAgainstFoe;
    private final GeneComponentModelRate restoredHpRateLovedAlly;
    private AbsCustCheckBox weddingAlly;

    public GeneComponentModelSubscribeEffectPartnerStatus(AbstractProgramInfos _fact) {
        programInfos = _fact;
        multDamageAgainstFoe = new GeneComponentModelRate(_fact);
        restoredHpRateLovedAlly = new GeneComponentModelRate(_fact);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        form_.add(multDamageAgainstFoe.geneRate(Rate.zero()));
        form_.add(restoredHpRateLovedAlly.geneRate(Rate.zero()));
        weddingAlly = programInfos.getCompoFactory().newCustCheckBox();
        form_.add(weddingAlly);
        return form_;
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
