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
    private final ContentComponentModelEffectAlly contentEffectAlly = new ContentComponentModelEffectAlly();
    private final ContentComponentModelEffectClone contentEffectClone = new ContentComponentModelEffectClone();
    private final ContentComponentModelEffectCommonStatistics contentEffectCommonStatistics = new ContentComponentModelEffectCommonStatistics();
    private final ContentComponentModelEffectCopyFighter contentEffectCopyFighter = new ContentComponentModelEffectCopyFighter();
    private final ContentComponentModelEffectCopyMove contentEffectCopyMove = new ContentComponentModelEffectCopyMove();
    private final ContentComponentModelEffectCounterAttack contentEffectCounterAttack = new ContentComponentModelEffectCounterAttack();
    private final ContentComponentModelEffectDamage contentEffectDamage = new ContentComponentModelEffectDamage();
    private final ContentComponentModelEffectDamageRate contentEffectDamageRate = new ContentComponentModelEffectDamageRate();
    private final ContentComponentModelEffectFullHpRate contentEffectFullHpRate = new ContentComponentModelEffectFullHpRate();
    private final ContentComponentModelEffectGlobal contentEffectGlobal = new ContentComponentModelEffectGlobal();
    private final ContentComponentModelEffectInvoke contentEffectInvoke = new ContentComponentModelEffectInvoke();
    private final ContentComponentModelEffectMultMovePower contentEffectMultMovePower = new ContentComponentModelEffectMultMovePower();
    private final ContentComponentModelEffectOrder contentEffectOrder = new ContentComponentModelEffectOrder();
    private final ContentComponentModelEffectProtectFromTypes contentEffectProtectFromTypes = new ContentComponentModelEffectProtectFromTypes();
    private final ContentComponentModelEffectProtection contentEffectProtection = new ContentComponentModelEffectProtection();
    private final ContentComponentModelEffectRemainedHpRate contentEffectRemainedHpRate = new ContentComponentModelEffectRemainedHpRate();
    private final ContentComponentModelEffectRestriction contentEffectRestriction = new ContentComponentModelEffectRestriction();
    private final ContentComponentModelEffectStatistic contentEffectStatistic = new ContentComponentModelEffectStatistic();
    private final ContentComponentModelEffectStatus contentEffectStatus = new ContentComponentModelEffectStatus();
    private final ContentComponentModelEffectSwitchAbilities contentEffectSwitchAbilities = new ContentComponentModelEffectSwitchAbilities();
    private final ContentComponentModelEffectSwitchItems contentEffectSwitchItems = new ContentComponentModelEffectSwitchItems();
    private final ContentComponentModelEffectSwitchMoveTypes contentEffectSwitchMoveTypes = new ContentComponentModelEffectSwitchMoveTypes();
    private final ContentComponentModelEffectSwitchPointView contentEffectSwitchPointView = new ContentComponentModelEffectSwitchPointView();
    private final ContentComponentModelEffectSwitchTypes contentEffectSwitchTypes = new ContentComponentModelEffectSwitchTypes();
    private final ContentComponentModelEffectTeam contentEffectTeam = new ContentComponentModelEffectTeam();
    private final ContentComponentModelEffectTeamWhileSendFoe contentEffectTeamWhileSendFoe = new ContentComponentModelEffectTeamWhileSendFoe();
    private final ContentComponentModelEffectUnprotectFromTypes contentEffectUnprotectFromTypes = new ContentComponentModelEffectUnprotectFromTypes();
    private final ContentComponentModelEffectVarPP contentEffectVarPP = new ContentComponentModelEffectVarPP();
    private final ContentComponentModelEffectWinMoney contentEffectWinMoney = new ContentComponentModelEffectWinMoney();
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
        form_.add(contentEffectAlly.effectForm(this));
        form_.add(contentEffectClone.effectForm(this));
        form_.add(contentEffectCommonStatistics.effectForm(getFrame(),getProgramInfos(),getFacadeGame(),getFactory()));
        form_.add(contentEffectCopyFighter.effectForm(this));
        form_.add(contentEffectCopyMove.effectForm(getProgramInfos(),getFacadeGame(),getFactory()));
        form_.add(contentEffectCounterAttack.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectDamage.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectDamageRate.effectForm(this));
        form_.add(contentEffectFullHpRate.effectForm(this));
        form_.add(contentEffectGlobal.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectInvoke.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectMultMovePower.effectForm(this));
        form_.add(contentEffectOrder.effectForm(this));
        form_.add(contentEffectProtectFromTypes.effectForm(getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectProtection.effectForm(this));
        form_.add(contentEffectRemainedHpRate.effectForm(this));
        form_.add(contentEffectRestriction.effectForm(this));
        form_.add(contentEffectStatistic.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectStatus.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectSwitchAbilities.effectForm(this));
        form_.add(contentEffectSwitchItems.effectForm(this));
        form_.add(contentEffectSwitchMoveTypes.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectSwitchPointView.effectForm(this));
        form_.add(contentEffectSwitchTypes.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectTeam.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectTeamWhileSendFoe.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectUnprotectFromTypes.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectVarPP.effectForm(this));
        form_.add(contentEffectWinMoney.effectForm(this));
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
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.EFF_ACCURACY)) {
            edited = Instances.newEffectAccuracy();
        }
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.EFF_ALLY)) {
            edited = Instances.newEffectAlly();
        }
        if (StringUtil.quickEq(eff_, MessagesEditorSelect.EFF_BATON_PASS)) {
            edited = Instances.newEffectBatonPass();
        }
        init1(eff_);
        init2(eff_);
        getEffectKind().getSelect().repaint();
        getFrame().pack();
    }

    private void init1(String _eff) {
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_CLONE)) {
            edited = Instances.newEffectClone();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_COMMON_STATISTICS)) {
            edited = Instances.newEffectCommonStatistics();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_COPY_FIGHTER)) {
            edited = Instances.newEffectCopyFighter();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_COPY_MOVE)) {
            edited = Instances.newEffectCopyMove();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_COUNTER_ATTACK)) {
            edited = Instances.newEffectCounterAttack();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_DAMAGE)) {
            edited = Instances.newEffectDamage();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_DAMAGE_RATE)) {
            edited = Instances.newEffectDamageRate();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_FULL_HP_RATE)) {
            edited = Instances.newEffectFullHpRate();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_GLOBAL)) {
            edited = Instances.newEffectGlobal();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_INVOKE)) {
            edited = Instances.newEffectInvoke();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_MULT_SUFFERED_MOVE_POWER)) {
            edited = Instances.newEffectMultSufferedMovePower();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_MULT_USED_MOVE_POWER)) {
            edited = Instances.newEffectMultUsedMovePower();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_ORDER)) {
            edited = Instances.newEffectOrder();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_PROTECT_FROM_TYPES)) {
            edited = Instances.newEffectProtectFromTypes();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_PROTECTION)) {
            edited = Instances.newEffectProtection();
        }
    }

    private void init2(String _eff) {
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_REMAINED_HP_RATE)) {
            edited = Instances.newEffectRemainedHpRate();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_RESTRICTION)) {
            edited = Instances.newEffectRestriction();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_STATIS)) {
            edited = Instances.newEffectStatistic();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_STATUS)) {
            edited = Instances.newEffectStatus();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_ABILITIES)) {
            edited = Instances.newEffectSwitchAbilities();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_ITEMS)) {
            edited = Instances.newEffectSwitchItems();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_MOVES_TYPES)) {
            edited = Instances.newEffectSwitchMoveTypes();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_POINT_VIEW)) {
            edited = Instances.newEffectSwitchPointView();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_POSITION)) {
            edited = Instances.newEffectSwitchPosition();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_TYPES)) {
            edited = Instances.newEffectSwitchTypes();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_TEAM)) {
            edited = Instances.newEffectTeam();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_TEAM_WHILE_SENDING_FOE)) {
            edited = Instances.newEffectTeamWhileSendFoe();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_UNPROTECT_FROM_TYPES)) {
            edited = Instances.newEffectUnprotectFromTypes();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_VAR_PP)) {
            edited = Instances.newEffectVarPP();
        }
        if (StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_WIN_MONEY)) {
            edited = Instances.newEffectWinMoney();
        }
    }

    public Effect valueEffect() {
        contentEffect.buildEntity(edited);
        if (edited instanceof EffectAlly) {
            contentEffectAlly.buildEntity((EffectAlly) edited);
        }
        value1();
        value2();
        if (edited instanceof EffectEndRound) {
            contentGroupEffectEndRound.buildEntity((EffectEndRound)edited);
        }
        return edited;
    }

    private void value1() {
        if (edited instanceof EffectClone) {
            contentEffectClone.buildEntity((EffectClone) edited);
        }
        if (edited instanceof EffectCommonStatistics) {
            contentEffectCommonStatistics.buildEntity((EffectCommonStatistics) edited);
        }
        if (edited instanceof EffectCopyFighter) {
            contentEffectCopyFighter.buildEntity((EffectCopyFighter) edited);
        }
        if (edited instanceof EffectCopyMove) {
            contentEffectCopyMove.buildEntity((EffectCopyMove) edited);
        }
        if (edited instanceof EffectCounterAttack) {
            contentEffectCounterAttack.buildEntity((EffectCounterAttack) edited);
        }
        if (edited instanceof EffectDamage) {
            contentEffectDamage.buildEntity((EffectDamage) edited);
        }
        if (edited instanceof EffectDamageRate) {
            contentEffectDamageRate.buildEntity((EffectDamageRate) edited);
        }
        if (edited instanceof EffectFullHpRate) {
            contentEffectFullHpRate.buildEntity((EffectFullHpRate) edited);
        }
        if (edited instanceof EffectGlobal) {
            contentEffectGlobal.buildEntity((EffectGlobal) edited);
        }
        if (edited instanceof EffectInvoke) {
            contentEffectInvoke.buildEntity((EffectInvoke) edited);
        }
        if (edited instanceof EffectMultMovePower) {
            contentEffectMultMovePower.buildEntity((EffectMultMovePower) edited);
        }
        if (edited instanceof EffectOrder) {
            contentEffectOrder.buildEntity((EffectOrder) edited);
        }
        if (edited instanceof EffectProtectFromTypes) {
            contentEffectProtectFromTypes.buildEntity((EffectProtectFromTypes) edited);
        }
        if (edited instanceof EffectProtection) {
            contentEffectProtection.buildEntity((EffectProtection) edited);
        }
    }

    private void value2() {
        if (edited instanceof EffectRemainedHpRate) {
            contentEffectRemainedHpRate.buildEntity((EffectRemainedHpRate) edited);
        }
        if (edited instanceof EffectRestriction) {
            contentEffectRestriction.buildEntity((EffectRestriction) edited);
        }
        if (edited instanceof EffectStatistic) {
            contentEffectStatistic.buildEntity((EffectStatistic) edited);
        }
        if (edited instanceof EffectStatus) {
            contentEffectStatus.buildEntity((EffectStatus) edited);
        }
        if (edited instanceof EffectSwitchAbilities) {
            contentEffectSwitchAbilities.buildEntity((EffectSwitchAbilities) edited);
        }
        if (edited instanceof EffectSwitchItems) {
            contentEffectSwitchItems.buildEntity((EffectSwitchItems) edited);
        }
        if (edited instanceof EffectSwitchMoveTypes) {
            contentEffectSwitchMoveTypes.buildEntity((EffectSwitchMoveTypes) edited);
        }
        if (edited instanceof EffectSwitchPointView) {
            contentEffectSwitchPointView.buildEntity((EffectSwitchPointView) edited);
        }
        if (edited instanceof EffectSwitchTypes) {
            contentEffectSwitchTypes.buildEntity((EffectSwitchTypes) edited);
        }
        if (edited instanceof EffectTeam) {
            contentEffectTeam.buildEntity((EffectTeam) edited);
        }
        if (edited instanceof EffectTeamWhileSendFoe) {
            contentEffectTeamWhileSendFoe.buildEntity((EffectTeamWhileSendFoe) edited);
        }
        if (edited instanceof EffectUnprotectFromTypes) {
            contentEffectUnprotectFromTypes.buildEntity((EffectUnprotectFromTypes) edited);
        }
        if (edited instanceof EffectVarPP) {
            contentEffectVarPP.buildEntity((EffectVarPP) edited);
        }
        if (edited instanceof EffectWinMoney) {
            contentEffectWinMoney.buildEntity((EffectWinMoney) edited);
        }
    }

    public void valueEffect(Effect _v) {
        contentEffect.feedForm(_v);
        if (_v instanceof EffectAccuracy) {
            displayRepaint(MessagesEditorSelect.EFF_ACCURACY);
        }
        if (_v instanceof EffectAlly) {
            contentEffectAlly.feedForm((EffectAlly) _v);
            displayRepaint(MessagesEditorSelect.EFF_ALLY);
        }
        if (_v instanceof EffectBatonPass) {
            displayRepaint(MessagesEditorSelect.EFF_BATON_PASS);
        }
        value1(_v);
        value2(_v);
        if (_v instanceof EffectEndRound) {
            displayRepaint(contentGroupEffectEndRound.feedForm((EffectEndRound) _v));
        }
        edited = _v;
    }

    private void value1(Effect _v) {
        if (_v instanceof EffectClone) {
            contentEffectClone.feedForm((EffectClone) _v);
            displayRepaint(MessagesEditorSelect.EFF_CLONE);
        }
        if (_v instanceof EffectCommonStatistics) {
            contentEffectCommonStatistics.feedForm((EffectCommonStatistics) _v);
            displayRepaint(MessagesEditorSelect.EFF_COMMON_STATISTICS);
        }
        if (_v instanceof EffectCopyFighter) {
            contentEffectCopyFighter.feedForm((EffectCopyFighter) _v);
            displayRepaint(MessagesEditorSelect.EFF_COPY_FIGHTER);
        }
        if (_v instanceof EffectCopyMove) {
            contentEffectCopyMove.feedForm((EffectCopyMove) _v);
            displayRepaint(MessagesEditorSelect.EFF_COPY_MOVE);
        }
        if (_v instanceof EffectCounterAttack) {
            contentEffectCounterAttack.feedForm((EffectCounterAttack) _v);
            displayRepaint(MessagesEditorSelect.EFF_COUNTER_ATTACK);
        }
        if (_v instanceof EffectDamage) {
            contentEffectDamage.feedForm((EffectDamage) _v);
            displayRepaint(MessagesEditorSelect.EFF_DAMAGE);
        }
        if (_v instanceof EffectDamageRate) {
            contentEffectDamageRate.feedForm((EffectDamageRate) _v);
            displayRepaint(MessagesEditorSelect.EFF_DAMAGE_RATE);
        }
        if (_v instanceof EffectFullHpRate) {
            contentEffectFullHpRate.feedForm((EffectFullHpRate) _v);
            displayRepaint(MessagesEditorSelect.EFF_FULL_HP_RATE);
        }
        if (_v instanceof EffectGlobal) {
            contentEffectGlobal.feedForm((EffectGlobal) _v);
            displayRepaint(MessagesEditorSelect.EFF_GLOBAL);
        }
        if (_v instanceof EffectInvoke) {
            contentEffectInvoke.feedForm((EffectInvoke) _v);
            displayRepaint(MessagesEditorSelect.EFF_INVOKE);
        }
        if (_v instanceof EffectMultSufferedMovePower) {
            contentEffectMultMovePower.feedForm((EffectMultSufferedMovePower) _v);
            displayRepaint(MessagesEditorSelect.EFF_MULT_SUFFERED_MOVE_POWER);
        }
        if (_v instanceof EffectMultUsedMovePower) {
            contentEffectMultMovePower.feedForm((EffectMultUsedMovePower) _v);
            displayRepaint(MessagesEditorSelect.EFF_MULT_USED_MOVE_POWER);
        }
        if (_v instanceof EffectOrder) {
            contentEffectOrder.feedForm((EffectOrder) _v);
            displayRepaint(MessagesEditorSelect.EFF_ORDER);
        }
        if (_v instanceof EffectProtectFromTypes) {
            contentEffectProtectFromTypes.feedForm((EffectProtectFromTypes) _v);
            displayRepaint(MessagesEditorSelect.EFF_PROTECT_FROM_TYPES);
        }
        if (_v instanceof EffectProtection) {
            contentEffectProtection.feedForm((EffectProtection) _v);
            displayRepaint(MessagesEditorSelect.EFF_PROTECTION);
        }
    }

    private void value2(Effect _v) {
        if (_v instanceof EffectRemainedHpRate) {
            contentEffectRemainedHpRate.feedForm((EffectRemainedHpRate) _v);
            displayRepaint(MessagesEditorSelect.EFF_REMAINED_HP_RATE);
        }
        if (_v instanceof EffectRestriction) {
            contentEffectRestriction.feedForm((EffectRestriction) _v);
            displayRepaint(MessagesEditorSelect.EFF_RESTRICTION);
        }
        if (_v instanceof EffectStatistic) {
            contentEffectStatistic.feedForm((EffectStatistic) _v);
            displayRepaint(MessagesEditorSelect.EFF_STATIS);
        }
        if (_v instanceof EffectStatus) {
            contentEffectStatus.feedForm((EffectStatus) _v);
            displayRepaint(MessagesEditorSelect.EFF_STATUS);
        }
        if (_v instanceof EffectSwitchAbilities) {
            contentEffectSwitchAbilities.feedForm((EffectSwitchAbilities) _v);
            displayRepaint(MessagesEditorSelect.EFF_SWITCH_ABILITIES);
        }
        if (_v instanceof EffectSwitchItems) {
            contentEffectSwitchItems.feedForm((EffectSwitchItems) _v);
            displayRepaint(MessagesEditorSelect.EFF_SWITCH_ITEMS);
        }
        if (_v instanceof EffectSwitchMoveTypes) {
            contentEffectSwitchMoveTypes.feedForm((EffectSwitchMoveTypes) _v);
            displayRepaint(MessagesEditorSelect.EFF_SWITCH_MOVES_TYPES);
        }
        if (_v instanceof EffectSwitchPointView) {
            contentEffectSwitchPointView.feedForm((EffectSwitchPointView) _v);
            displayRepaint(MessagesEditorSelect.EFF_SWITCH_POINT_VIEW);
        }
        if (_v instanceof EffectSwitchPosition) {
            displayRepaint(MessagesEditorSelect.EFF_SWITCH_POSITION);
        }
        if (_v instanceof EffectSwitchTypes) {
            contentEffectSwitchTypes.feedForm((EffectSwitchTypes) _v);
            displayRepaint(MessagesEditorSelect.EFF_SWITCH_TYPES);
        }
        if (_v instanceof EffectTeam) {
            contentEffectTeam.feedForm((EffectTeam) _v);
            displayRepaint(MessagesEditorSelect.EFF_TEAM);
        }
        if (_v instanceof EffectTeamWhileSendFoe) {
            contentEffectTeamWhileSendFoe.feedForm((EffectTeamWhileSendFoe) _v);
            displayRepaint(MessagesEditorSelect.EFF_TEAM_WHILE_SENDING_FOE);
        }
        if (_v instanceof EffectUnprotectFromTypes) {
            contentEffectUnprotectFromTypes.feedForm((EffectUnprotectFromTypes) _v);
            displayRepaint(MessagesEditorSelect.EFF_UNPROTECT_FROM_TYPES);
        }
        if (_v instanceof EffectVarPP) {
            contentEffectVarPP.feedForm((EffectVarPP) _v);
            displayRepaint(MessagesEditorSelect.EFF_VAR_PP);
        }
        if (_v instanceof EffectWinMoney) {
            contentEffectWinMoney.feedForm((EffectWinMoney) _v);
            displayRepaint(MessagesEditorSelect.EFF_WIN_MONEY);
        }
    }

    private void displayRepaint(String _eff) {
        display(_eff);
        getEffectKind().setupValue(_eff);
        getEffectKind().getSelect().repaint();
    }

    private String display(String _eff) {
        contentEffectAlly.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_ALLY));
        contentEffectClone.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_CLONE));
        contentEffectCommonStatistics.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_COMMON_STATISTICS));
        contentEffectCopyFighter.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_COPY_FIGHTER));
        contentEffectCopyMove.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_COPY_MOVE));
        contentEffectCounterAttack.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_COUNTER_ATTACK));
        contentEffectDamage.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_DAMAGE));
        contentEffectDamageRate.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_DAMAGE_RATE));
        contentEffectFullHpRate.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_FULL_HP_RATE));
        contentEffectGlobal.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_GLOBAL));
        contentEffectInvoke.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_INVOKE));
        contentEffectMultMovePower.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_MULT_SUFFERED_MOVE_POWER) || StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_MULT_USED_MOVE_POWER));
        contentEffectOrder.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_ORDER));
        contentEffectProtectFromTypes.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_PROTECT_FROM_TYPES));
        contentEffectProtection.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_PROTECTION));
        contentEffectRemainedHpRate.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_REMAINED_HP_RATE));
        contentEffectRestriction.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_RESTRICTION));
        contentEffectStatistic.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_STATIS));
        contentEffectStatus.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_STATUS));
        contentEffectSwitchAbilities.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_ABILITIES));
        contentEffectSwitchItems.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_ITEMS));
        contentEffectSwitchMoveTypes.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_MOVES_TYPES));
        contentEffectSwitchPointView.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_POINT_VIEW));
        contentEffectSwitchTypes.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_SWITCH_TYPES));
        contentEffectTeam.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_TEAM));
        contentEffectTeamWhileSendFoe.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_TEAM_WHILE_SENDING_FOE));
        contentEffectUnprotectFromTypes.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_UNPROTECT_FROM_TYPES));
        contentEffectVarPP.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_VAR_PP));
        contentEffectWinMoney.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_WIN_MONEY));
        return contentGroupEffectEndRound.display(_eff);
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getContentEffect().getTargetChoice().getSubs());
        ids_.addAllElts(getContentEffectCommonStatistics().getCommonValue().subscribeButtons());
        ids_.addAllElts(getContentEffectCopyMove().getMovesNotToBeCopied().getSubs());
        ids_.addAllElts(getContentEffectCounterAttack().getSufferingDamageTypes().subscribeButtons());
        ids_.addAllElts(getContentEffectCounterAttack().getDroppedStatDirectMove().subscribeButtons());
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
        ids_.addAllElts(getContentEffectMultMovePower().getMultMovePowerFctType().subscribeButtons());
        ids_.addAllElts(getContentEffectProtectFromTypes().getImmuAgainstTypes().getSubs());
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
        ids_.addAllElts(getContentEffectSwitchAbilities().getConstAbility().getSubs());
        ids_.addAllElts(getContentEffectSwitchMoveTypes().getChangeTypes().subscribeButtons());
        ids_.addAllElts(getContentEffectSwitchMoveTypes().getReplacingTypes().getSubs());
        ids_.addAllElts(getContentEffectSwitchTypes().getAddedTypes().getSubs());
        ids_.addAllElts(getContentEffectSwitchTypes().getConstTypes().getSubs());
        ids_.addAllElts(getContentEffectSwitchTypes().getChgtTypeByEnv().subscribeButtons());
        ids_.addAllElts(team(getContentEffectTeam()));
        ids_.addAllElts(getContentEffectTeamWhileSendFoe().getStatistics().subscribeButtons());
        ids_.addAllElts(getContentEffectTeamWhileSendFoe().getStatusByNbUses().subscribeButtons());
        ids_.addAllElts(getContentEffectTeamWhileSendFoe().getDeletedByFoeTypes().getSubs());
        ids_.addAllElts(getContentEffectUnprotectFromTypes().getTypes().subscribeButtons());
        ids_.addAllElts(getContentEffectUnprotectFromTypes().getAttackTargetWithTypes().getSubs());
        ids_.addAllElts(getContentEffectUnprotectFromTypes().getDisableImmuFromMoves().getSubs());
        ids_.addAllElts(getContentEffectUnprotectFromTypes().getDisableImmuAgainstTypes().getSubs());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundIndividual().getUserStatusEndRound().getSubs());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundIndividual().getHealHpByOwnerTypes().subscribeButtons());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundIndividual().getMultDamageStatus().subscribeButtons());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundMultiRelation().getDamageByStatus().subscribeButtons());
        ids_.addAllElts(getContentGroupEffectEndRound().getContentEffectEndRoundSingleRelation().getRateDamageFunctionOfNbRounds().subscribeButtons());
        return ids_;
    }
    public static IdList<SubscribedTranslation> team(ContentComponentModelEffectTeam _cont) {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(_cont.getMultStatistic().subscribeButtons());
        ids_.addAllElts(_cont.getMultStatisticFoe().subscribeButtons());
        ids_.addAllElts(_cont.getMultDamage().subscribeButtons());
        ids_.addAllElts(_cont.getForbiddenBoost().getSubs());
        ids_.addAllElts(_cont.getCancelChgtStatTeam().getSubs());
        ids_.addAllElts(_cont.getCancelChgtStatFoeTeam().getSubs());
        ids_.addAllElts(_cont.getProtectAgainstLowStat().getSubs());
        ids_.addAllElts(_cont.getProtectAgainstStatus().getSubs());
        ids_.addAllElts(_cont.getDisableFoeTeamStatus().getSubs());
        ids_.addAllElts(_cont.getUnusableMoves().getSubs());
        ids_.addAllElts(_cont.getDisableFoeTeamEffects().getSubs());
        return ids_;
    }

    public GeneComponentModelString getFail() {
        return getContentEffect().getFail();
    }

    public ContentComponentModelEffect getContentEffect() {
        return contentEffect;
    }

    public ContentComponentModelEffectAlly getContentEffectAlly() {
        return contentEffectAlly;
    }

    public ContentComponentModelEffectClone getContentEffectClone() {
        return contentEffectClone;
    }

    public ContentComponentModelEffectCommonStatistics getContentEffectCommonStatistics() {
        return contentEffectCommonStatistics;
    }

    public ContentComponentModelEffectCopyFighter getContentEffectCopyFighter() {
        return contentEffectCopyFighter;
    }

    public ContentComponentModelEffectCopyMove getContentEffectCopyMove() {
        return contentEffectCopyMove;
    }

    public ContentComponentModelEffectCounterAttack getContentEffectCounterAttack() {
        return contentEffectCounterAttack;
    }

    public ContentComponentModelEffectDamage getContentEffectDamage() {
        return contentEffectDamage;
    }

    public ContentComponentModelEffectDamageRate getContentEffectDamageRate() {
        return contentEffectDamageRate;
    }

    public ContentComponentModelEffectFullHpRate getContentEffectFullHpRate() {
        return contentEffectFullHpRate;
    }

    public ContentComponentModelEffectGlobal getContentEffectGlobal() {
        return contentEffectGlobal;
    }

    public ContentComponentModelEffectInvoke getContentEffectInvoke() {
        return contentEffectInvoke;
    }

    public ContentComponentModelEffectMultMovePower getContentEffectMultMovePower() {
        return contentEffectMultMovePower;
    }

    public ContentComponentModelEffectOrder getContentEffectOrder() {
        return contentEffectOrder;
    }

    public ContentComponentModelEffectProtectFromTypes getContentEffectProtectFromTypes() {
        return contentEffectProtectFromTypes;
    }

    public ContentComponentModelEffectProtection getContentEffectProtection() {
        return contentEffectProtection;
    }

    public ContentComponentModelEffectRemainedHpRate getContentEffectRemainedHpRate() {
        return contentEffectRemainedHpRate;
    }

    public ContentComponentModelEffectRestriction getContentEffectRestriction() {
        return contentEffectRestriction;
    }

    public ContentComponentModelEffectStatistic getContentEffectStatistic() {
        return contentEffectStatistic;
    }

    public ContentComponentModelEffectStatus getContentEffectStatus() {
        return contentEffectStatus;
    }

    public ContentComponentModelEffectSwitchAbilities getContentEffectSwitchAbilities() {
        return contentEffectSwitchAbilities;
    }

    public ContentComponentModelEffectSwitchItems getContentEffectSwitchItems() {
        return contentEffectSwitchItems;
    }

    public ContentComponentModelEffectSwitchMoveTypes getContentEffectSwitchMoveTypes() {
        return contentEffectSwitchMoveTypes;
    }

    public ContentComponentModelEffectSwitchPointView getContentEffectSwitchPointView() {
        return contentEffectSwitchPointView;
    }

    public ContentComponentModelEffectSwitchTypes getContentEffectSwitchTypes() {
        return contentEffectSwitchTypes;
    }

    public ContentComponentModelEffectTeam getContentEffectTeam() {
        return contentEffectTeam;
    }

    public ContentComponentModelEffectTeamWhileSendFoe getContentEffectTeamWhileSendFoe() {
        return contentEffectTeamWhileSendFoe;
    }

    public ContentComponentModelEffectUnprotectFromTypes getContentEffectUnprotectFromTypes() {
        return contentEffectUnprotectFromTypes;
    }

    public ContentComponentModelEffectVarPP getContentEffectVarPP() {
        return contentEffectVarPP;
    }

    public ContentComponentModelEffectWinMoney getContentEffectWinMoney() {
        return contentEffectWinMoney;
    }

    public ContentComponentModelGroupEffectEndRound getContentGroupEffectEndRound() {
        return contentGroupEffectEndRound;
    }
}
