package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.IdList;
import code.util.core.*;

public final class GeneComponentModelEffect extends AbsGeneComponentModelEffect {
    private final ContentComponentModelEffect contentEffect = new ContentComponentModelEffect();
    private final ContentComponentModelEffectDamage contentEffectDamage = new ContentComponentModelEffectDamage();
    private final ContentComponentModelEffectGlobal contentEffectGlobal = new ContentComponentModelEffectGlobal();
    private final ContentComponentModelEffectInvoke contentEffectInvoke = new ContentComponentModelEffectInvoke();
    private final ContentComponentModelEffectStatistic contentEffectStatistic = new ContentComponentModelEffectStatistic();
    private final ContentComponentModelEffectStatus contentEffectStatus = new ContentComponentModelEffectStatus();
    private final ContentComponentModelEffectTeam contentEffectTeam = new ContentComponentModelEffectTeam();
    private final ContentComponentModelEffectTeamWhileSendFoe contentEffectTeamWhileSendFoe = new ContentComponentModelEffectTeamWhileSendFoe();
    private final ContentComponentModelGroupEffectEndRound contentGroupEffectEndRound = new ContentComponentModelGroupEffectEndRound();
    private Effect edited;

    public GeneComponentModelEffect(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        super(_f, _core, _fac, _fact);
    }

    public AbsPanel geneEffect() {
        init(MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())).getMapping());
        AbsCompoFactory compoFactory_ = getProgramInfos().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(getEffectKind().geneEnum());
        form_.add(contentEffect.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectDamage.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectGlobal.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectInvoke.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectStatistic.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectStatus.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectTeam.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectTeamWhileSendFoe.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        contentGroupEffectEndRound.effectForm(form_,this);
        getEffectKind().getSelect().addListener(new ChangingEffectEvent(this));
        ConverterCommonMapUtil.trigger(getEffectKind(),MessagesEditorSelect.EFF_DAMAGE);
        return form_;
    }

    @Override
    public void applyChange() {
        String eff_ = getEffectKind().tryRet();
        String display_ = display(eff_);
        if (!display_.isEmpty()) {
            edited = ContentComponentModelGroupEffectEndRound.instance(eff_);
        }
        if (StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_DAMAGE)) {
            edited = Instances.newEffectDamage();
        }
        if (StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_GLOBAL)) {
            edited = Instances.newEffectGlobal();
        }
        if (StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_INVOKE)) {
            edited = Instances.newEffectInvoke();
        }
        if (StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_STATIS)) {
            edited = Instances.newEffectStatistic();
        }
        if (StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_STATUS)) {
            edited = Instances.newEffectStatus();
        }
        if (StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_TEAM)) {
            edited = Instances.newEffectTeam();
        }
        if (StringUtil.quickEq(eff_,MessagesEditorSelect.EFF_TEAM_WHILE_SENDING_FOE)) {
            edited = Instances.newEffectTeamWhileSendFoe();
        }
        getEffectKind().getSelect().repaint();
        getFrame().pack();
    }

    public Effect valueEffect() {
        contentEffect.buildEntity(edited);
        if (edited instanceof EffectDamage) {
            contentEffectDamage.buildEntity((EffectDamage) edited);
        }
        if (edited instanceof EffectGlobal) {
            contentEffectGlobal.buildEntity((EffectGlobal) edited);
        }
        if (edited instanceof EffectInvoke) {
            contentEffectInvoke.buildEntity((EffectInvoke) edited);
        }
        if (edited instanceof EffectStatistic) {
            contentEffectStatistic.buildEntity((EffectStatistic) edited);
        }
        if (edited instanceof EffectStatus) {
            contentEffectStatus.buildEntity((EffectStatus) edited);
        }
        if (edited instanceof EffectTeam) {
            contentEffectTeam.buildEntity((EffectTeam) edited);
        }
        if (edited instanceof EffectTeamWhileSendFoe) {
            contentEffectTeamWhileSendFoe.buildEntity((EffectTeamWhileSendFoe) edited);
        }
        if (edited instanceof EffectEndRound) {
            contentGroupEffectEndRound.buildEntity((EffectEndRound)edited);
        }
        return edited;
    }

    public void valueEffect(Effect _v) {
        contentEffect.feedForm(_v);
        if (_v instanceof EffectDamage) {
            contentEffectDamage.feedForm((EffectDamage) _v);
            displayRepaint(MessagesEditorSelect.EFF_DAMAGE);
        }
        if (_v instanceof EffectGlobal) {
            contentEffectGlobal.feedForm((EffectGlobal) _v);
            displayRepaint(MessagesEditorSelect.EFF_GLOBAL);
        }
        if (_v instanceof EffectInvoke) {
            contentEffectInvoke.feedForm((EffectInvoke) _v);
            displayRepaint(MessagesEditorSelect.EFF_INVOKE);
        }
        if (_v instanceof EffectStatistic) {
            contentEffectStatistic.feedForm((EffectStatistic) _v);
            displayRepaint(MessagesEditorSelect.EFF_STATIS);
        }
        if (_v instanceof EffectStatus) {
            contentEffectStatus.feedForm((EffectStatus) _v);
            displayRepaint(MessagesEditorSelect.EFF_STATUS);
        }
        if (_v instanceof EffectTeam) {
            contentEffectTeam.feedForm((EffectTeam) _v);
            displayRepaint(MessagesEditorSelect.EFF_TEAM);
        }
        if (_v instanceof EffectTeamWhileSendFoe) {
            contentEffectTeamWhileSendFoe.feedForm((EffectTeamWhileSendFoe) _v);
            displayRepaint(MessagesEditorSelect.EFF_TEAM_WHILE_SENDING_FOE);
        }
        if (_v instanceof EffectEndRound) {
            displayRepaint(contentGroupEffectEndRound.feedForm((EffectEndRound) _v));
        }
        edited = _v;
    }
    private void displayRepaint(String _eff) {
        display(_eff);
        getEffectKind().setupValue(_eff);
        getEffectKind().getSelect().repaint();
    }

    private String display(String _eff) {
        contentEffectDamage.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_DAMAGE));
        contentEffectGlobal.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_GLOBAL));
        contentEffectInvoke.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_INVOKE));
        contentEffectStatistic.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_STATIS));
        contentEffectStatus.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_STATUS));
        contentEffectTeam.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_TEAM));
        contentEffectTeamWhileSendFoe.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_TEAM_WHILE_SENDING_FOE));
        return contentGroupEffectEndRound.display(_eff);
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getContentEffect().getTargetChoice().getSubs());
        ids_.addAllElts(getContentEffectDamage().getStatisAtt().getSubs());
        ids_.addAllElts(getContentEffectDamage().getStatisDef().getSubs());
        ids_.addAllElts(getContentEffectDamage().getIgnVarStatTargetPos().getSubs());
        ids_.addAllElts(getContentEffectDamage().getIgnVarStatUserNeg().getSubs());
        ids_.addAllElts(getContentEffectDamage().getMultDamageAgainst().subscribeButtons());
        ids_.addAllElts(getContentEffectDamage().getBoostStatisOnceKoFoe().subscribeButtons());
        ids_.addAllElts(getContentEffectGlobal().getEfficiencyMoves().subscribeButtons());
        ids_.addAllElts(getContentEffectGlobal().getMultStatIfContainsType().subscribeButtons());
        ids_.addAllElts(getContentEffectGlobal().getMultDamagePrepaRound().subscribeButtons());
        ids_.addAllElts(getContentEffectGlobal().getMultPowerMoves().subscribeButtons());
        ids_.addAllElts(getContentEffectGlobal().getMultDamageTypesMoves().subscribeButtons());
        ids_.addAllElts(getContentEffectGlobal().getPreventStatus().getSubs());
        ids_.addAllElts(getContentEffectGlobal().getCancelProtectingAbilities().getSubs());
        ids_.addAllElts(getContentEffectGlobal().getChangedTypesTerrain().getSubs());
        ids_.addAllElts(getContentEffectGlobal().getDisableImmuAgainstTypes().getSubs());
        ids_.addAllElts(getContentEffectGlobal().getMovesUsedByTargetedFighters().getSubs());
        ids_.addAllElts(getContentEffectGlobal().getImmuneTypes().getSubs());
        ids_.addAllElts(getContentEffectGlobal().getCancelChgtStat().getSubs());
        ids_.addAllElts(getContentEffectGlobal().getUnusableMoves().getSubs());
        ids_.addAllElts(getContentEffectGlobal().getCancelEffects().getSubs());
        ids_.addAllElts(getContentEffectGlobal().getInvokedMoveTerrain().getSubs());
        ids_.addAllElts(getContentEffectInvoke().getMovesNotToBeInvoked().getSubs());
        ids_.addAllElts(getContentEffectInvoke().getInvokingMoveByUserTypes().subscribeButtons());
        ids_.addAllElts(getContentEffectInvoke().getMoveFctEnv().subscribeButtons());
        ids_.addAllElts(getContentEffectStatistic().getCancelChgtStat().getSubs());
        ids_.addAllElts(getContentEffectStatistic().getCancelLowStat().getSubs());
        ids_.addAllElts(getContentEffectStatistic().getCopyBoost().getSubs());
        ids_.addAllElts(getContentEffectStatistic().getSwapBoostStatis().getSubs());
        ids_.addAllElts(getContentEffectStatistic().getStatisVarRank().subscribeButtons());
        ids_.addAllElts(getContentEffectStatistic().getLocalFailStatis().subscribeButtons());
        ids_.addAllElts(getContentEffectStatistic().getLocalFailSwapBoostStatis().subscribeButtons());
        ids_.addAllElts(getContentEffectStatistic().getLawBoost().subscribeButtons());
        ids_.addAllElts(getContentEffectStatus().getDeletedStatus().getSubs());
        ids_.addAllElts(getContentEffectStatus().getLocalFailStatus().subscribeButtons());
        ids_.addAllElts(getContentEffectStatus().getLawStatus().subscribeButtons());
        ids_.addAllElts(getContentEffectTeam().getMultStatistic().subscribeButtons());
        ids_.addAllElts(getContentEffectTeam().getMultStatisticFoe().subscribeButtons());
        ids_.addAllElts(getContentEffectTeam().getMultDamage().subscribeButtons());
        ids_.addAllElts(getContentEffectTeam().getForbiddenBoost().getSubs());
        ids_.addAllElts(getContentEffectTeam().getCancelChgtStatTeam().getSubs());
        ids_.addAllElts(getContentEffectTeam().getCancelChgtStatFoeTeam().getSubs());
        ids_.addAllElts(getContentEffectTeam().getProtectAgainstLowStat().getSubs());
        ids_.addAllElts(getContentEffectTeam().getProtectAgainstStatus().getSubs());
        ids_.addAllElts(getContentEffectTeam().getDisableFoeTeamStatus().getSubs());
        ids_.addAllElts(getContentEffectTeam().getUnusableMoves().getSubs());
        ids_.addAllElts(getContentEffectTeam().getDisableFoeTeamEffects().getSubs());
        ids_.addAllElts(getContentEffectTeamWhileSendFoe().getStatistics().subscribeButtons());
        ids_.addAllElts(getContentEffectTeamWhileSendFoe().getStatusByNbUses().subscribeButtons());
        ids_.addAllElts(getContentEffectTeamWhileSendFoe().getDeletedByFoeTypes().getSubs());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundIndividual().getUserStatusEndRound().getSubs());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundIndividual().getHealHpByOwnerTypes().subscribeButtons());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundIndividual().getMultDamageStatus().subscribeButtons());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundMultiRelation().getDamageByStatus().subscribeButtons());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().subscribeButtons());
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

    public ContentComponentModelEffectGlobal getContentEffectGlobal() {
        return contentEffectGlobal;
    }

    public ContentComponentModelEffectInvoke getContentEffectInvoke() {
        return contentEffectInvoke;
    }

    public ContentComponentModelEffectStatistic getContentEffectStatistic() {
        return contentEffectStatistic;
    }

    public ContentComponentModelEffectStatus getContentEffectStatus() {
        return contentEffectStatus;
    }

    public ContentComponentModelEffectTeam getContentEffectTeam() {
        return contentEffectTeam;
    }

    public ContentComponentModelEffectTeamWhileSendFoe getContentEffectTeamWhileSendFoe() {
        return contentEffectTeamWhileSendFoe;
    }

    public ContentComponentModelGroupEffectEndRound getContentGroupEffectEndRound() {
        return contentGroupEffectEndRound;
    }
}
