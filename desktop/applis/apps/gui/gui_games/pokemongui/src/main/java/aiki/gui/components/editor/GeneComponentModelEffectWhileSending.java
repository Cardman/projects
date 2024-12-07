package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEffectWhileSending extends AbsGeneComponentModelEffect {
    private final ContentComponentModelEffect contentEffect = new ContentComponentModelEffect();
    private final ContentComponentModelEffectStatistic contentEffectStatistic = new ContentComponentModelEffectStatistic();
    private AbsCustCheckBox withEffect;
    private AbsCustCheckBox disableWeather;
    private AbsCustCheckBox copyingAbility;
    private GeneComponentModelSubscribeString enabledWeather;
    private GeneComponentModelRate multWeight;
    private EffectWhileSendingWithStatistic edited;
    private final boolean procAbility;

    public GeneComponentModelEffectWhileSending(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, boolean _ability) {
        super(_f, _core, _fac, _fact);
        procAbility = _ability;
    }

    public AbsPanel geneEffect() {
        init(MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())).getMapping());
        AbsCompoFactory compoFactory_ = getProgramInfos().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(getEffectKind().geneEnum());
        form_.add(contentEffect.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectStatistic.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        withEffect = compoFactory_.newCustCheckBox();
        form_.add(withEffect);
        disableWeather = compoFactory_.newCustCheckBox();
        form_.add(disableWeather);
        copyingAbility = compoFactory_.newCustCheckBox();
        form_.add(copyingAbility);
        enabledWeather = new GeneComponentModelSubscribeString(getProgramInfos(),getFacadeGame());
        form_.add(enabledWeather.geneEnum());
        enabledWeather.addComplete();
        multWeight = new GeneComponentModelRate(getProgramInfos());
        form_.add(multWeight.geneRate());
        getEffectKind().getSelect().addListener(new ChangingTypeEvent(this));
        ConverterCommonMapUtil.trigger(getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_FOE);
        return form_;
    }

    @Override
    public void applyChange() {
        edited = Instances.newEffectWhileSendingSimple();
        effectSub(edited);
        contentEffectStatistic.display(true);
        getEffectKind().getSelect().getElements().setVisible(false);
        getFrame().pack();
    }

    public EffectWhileSendingWithStatistic valueEffect() {
        edited.setWithEffect(withEffect.isSelected());
        edited.setDisableWeather(disableWeather.isSelected());
        edited.setCopyingAbility(copyingAbility.isSelected());
        edited.setEnabledWeather(enabledWeather.tryRet());
        edited.setMultWeight(multWeight.valueRate());
        contentEffect.buildEntity(edited.getEffect());
        contentEffectStatistic.buildEntity(edited.getEffect());
        return edited;
    }

    public void valueEffect(EffectWhileSendingWithStatistic _v) {
        withEffect.setSelected(_v.isWithEffect());
        disableWeather.setSelected(_v.getDisableWeather());
        copyingAbility.setSelected(_v.getCopyingAbility());
        enabledWeather.setupValue(_v.getEnabledWeather());
        multWeight.valueRate(_v.getMultWeight());
        contentEffect.feedForm(_v.getEffect());
        contentEffectStatistic.feedForm(_v.getEffect());
        edited = _v;
        effectSub(_v);
    }

    private void effectSub(EffectWhileSendingWithStatistic _v) {
        if (procAbility) {
            getFactory().getModifiedEntitiesRenameMid().setEffectSendingAbility(_v);
            getFactory().getModifiedEntitiesRenamePref().setEffectSendingAbility(_v);
            getFactory().getFactoryAb().setEffectSendingAbility(_v);
            getFactory().getFactoryCa().setEffectSendingAbility(_v);
            getFactory().getFactoryIt().setEffectSendingAbility(_v);
            getFactory().getFactoryMv().setEffectSendingAbility(_v);
            getFactory().getFactoryPk().setEffectSendingAbility(_v);
            getFactory().getFactorySt().setEffectSendingAbility(_v);
            getFactory().getFactoryTy().setEffectSendingAbility(_v);
        } else {
            getFactory().getModifiedEntitiesRenameMid().setEffectSendingItem(_v);
            getFactory().getModifiedEntitiesRenamePref().setEffectSendingItem(_v);
            getFactory().getFactoryAb().setEffectSendingItem(_v);
            getFactory().getFactoryCa().setEffectSendingItem(_v);
            getFactory().getFactoryIt().setEffectSendingItem(_v);
            getFactory().getFactoryMv().setEffectSendingItem(_v);
            getFactory().getFactoryPk().setEffectSendingItem(_v);
            getFactory().getFactorySt().setEffectSendingItem(_v);
            getFactory().getFactoryTy().setEffectSendingItem(_v);
        }
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getContentEffect().getTargetChoice().getSubs());
        ids_.addAllElts(getContentEffect().getFail().getSubs());
        ids_.addAllElts(enabledWeather.getSubs());
        ids_.addAllElts(GeneComponentModelEffect.stats(getContentEffectStatistic()));
        return ids_;
    }

    public ContentComponentModelEffect getContentEffect() {
        return contentEffect;
    }

    public ContentComponentModelEffectStatistic getContentEffectStatistic() {
        return contentEffectStatistic;
    }

    public AbsCustCheckBox getWithEffect() {
        return withEffect;
    }

    public AbsCustCheckBox getCopyingAbility() {
        return copyingAbility;
    }

    public AbsCustCheckBox getDisableWeather() {
        return disableWeather;
    }
}
