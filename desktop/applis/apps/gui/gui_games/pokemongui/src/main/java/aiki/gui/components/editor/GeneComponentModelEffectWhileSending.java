package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
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
        form_.add(SubscribedTranslationList.line(getProgramInfos(),MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())),MessagesEditorSelect.EFF_TYPE,getEffectKind().geneEnum()));
        form_.add(contentEffect.effectForm(this));
        form_.add(contentEffectStatistic.effectForm(this));
        withEffect = compoFactory_.newCustCheckBox();
        form_.add(line(MessagesPkBean.SENDING,MessagesDataSending.M_P_84_STAT,withEffect));
        disableWeather = compoFactory_.newCustCheckBox();
        form_.add(line(MessagesPkBean.SENDING,MessagesDataSending.M_P_84_DISABLE_WEATHER,disableWeather));
        copyingAbility = compoFactory_.newCustCheckBox();
        form_.add(line(MessagesPkBean.SENDING,MessagesDataSending.M_P_84_COPY_AB,copyingAbility));
        enabledWeather = new GeneComponentModelSubscribeString(getProgramInfos(),getFacadeGame());
        form_.add(line(MessagesPkBean.SENDING,MessagesDataSending.M_P_84_WEATHER,enabledWeather.geneEnum()));
        enabledWeather.addComplete();
        multWeight = new GeneComponentModelRate(getProgramInfos());
        form_.add(line(MessagesPkBean.SENDING,MessagesDataSending.M_P_84_WEIGHT_INTRO,multWeight.geneRate()));
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
            getFactory().effectSendingAbility(_v);
        } else {
            getFactory().effectSendingItem(_v);
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
