package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.IdList;
import code.util.core.*;

public final class GeneComponentModelEffect extends AbsGeneComponentModelEffect implements GeneComponentModel<Effect> {
    private final ContentComponentModelEffect contentEffect = new ContentComponentModelEffect();
    private final ContentComponentModelEffectDamage contentEffectDamage = new ContentComponentModelEffectDamage();
    private final ContentComponentModelEffectStatistic contentEffectStatistic = new ContentComponentModelEffectStatistic();
    private Effect edited;

    public GeneComponentModelEffect(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        super(_f, _core, _fac, _fact);
    }

    @Override
    public AbsCustComponent gene(int _select) {
        init(MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())).getMapping());
        AbsCompoFactory compoFactory_ = getProgramInfos().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(getEffectKind().geneEnum(""));
        form_.add(contentEffect.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectDamage.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectStatistic.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        getEffectKind().getSelect().addListener(new ChangingEffectEvent(this));
        getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_DAMAGE));
        getEffectKind().getSelect().events(null);
        return form_;
    }

    @Override
    public void applyChange() {
        String eff_ = getEffectKind().tryRet("");
        contentEffectDamage.display(StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_DAMAGE));
        contentEffectStatistic.display(StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_STATIS));
        if (StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_DAMAGE)) {
            edited = Instances.newEffectDamage();
        }
        if (StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_STATIS)) {
            edited = Instances.newEffectStatistic();
        }
        getEffectKind().getSelect().repaint();
        getFrame().pack();
    }

    @Override
    public Effect value() {
        contentEffect.buildEntity(edited);
        if (edited instanceof EffectDamage) {
            contentEffectDamage.buildEntity((EffectDamage) edited);
        }
        if (edited instanceof EffectStatistic) {
            contentEffectStatistic.buildEntity((EffectStatistic) edited);
        }
        return edited;
    }

    @Override
    public void value(Effect _v) {
        contentEffect.feedForm(_v);
        if (_v instanceof EffectDamage) {
            contentEffectDamage.feedForm((EffectDamage) _v);
        }
        if (_v instanceof EffectStatistic) {
            contentEffectStatistic.feedForm((EffectStatistic) _v);
        }
        edited = _v;
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getContentEffectDamage().getStatisAtt().getSubs());
        ids_.addAllElts(getContentEffectDamage().getStatisDef().getSubs());
        ids_.addAllElts(getContentEffectDamage().getIgnVarStatTargetPos().getSubs());
        ids_.addAllElts(getContentEffectDamage().getIgnVarStatUserNeg().getSubs());
        ids_.addAllElts(getContentEffectDamage().getMultDamageAgainst().subscribeButtons());
        ids_.addAllElts(getContentEffectDamage().getBoostStatisOnceKoFoe().subscribeButtons());
        ids_.addAllElts(getContentEffectStatistic().getCancelChgtStat().getSubs());
        ids_.addAllElts(getContentEffectStatistic().getCancelLowStat().getSubs());
        ids_.addAllElts(getContentEffectStatistic().getCopyBoost().getSubs());
        ids_.addAllElts(getContentEffectStatistic().getSwapBoostStatis().getSubs());
        ids_.addAllElts(getContentEffectStatistic().getStatisVarRank().subscribeButtons());
        ids_.addAllElts(getContentEffectStatistic().getLocalFailStatis().subscribeButtons());
        ids_.addAllElts(getContentEffectStatistic().getLocalFailSwapBoostStatis().subscribeButtons());
        ids_.addAllElts(getContentEffectStatistic().getSubscribedTranslations());
        return ids_;
    }

    public GeneComponentModelString getFail() {
        return getContentEffect().getFail();
    }

    public ContentComponentModelEffect getContentEffect() {
        return contentEffect;
    }

    public ContentComponentModelEffectDamage getContentEffectDamage() {
        return contentEffectDamage;
    }

    public ContentComponentModelEffectStatistic getContentEffectStatistic() {
        return contentEffectStatistic;
    }
}
