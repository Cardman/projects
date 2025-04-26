package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.util.core.*;

public final class ContentComponentModelGroupEffectEndRound {
    private final ContentComponentModelEffectEndRound contentEffectEndRound = new ContentComponentModelEffectEndRound();
    private final ContentComponentModelEffectEndRoundFoe contentEffectEndRoundFoe = new ContentComponentModelEffectEndRoundFoe();
    private final ContentComponentModelEffectEndRoundIndividual contentEffectEndRoundIndividual = new ContentComponentModelEffectEndRoundIndividual();
    private final ContentComponentModelEffectEndRoundMultiRelation contentEffectEndRoundMultiRelation = new ContentComponentModelEffectEndRoundMultiRelation();
    private final ContentComponentModelEffectEndRoundPositionRelation contentEffectEndRoundPositionRelation = new ContentComponentModelEffectEndRoundPositionRelation();
    private final ContentComponentModelEffectEndRoundSingleRelation contentEffectEndRoundSingleRelation = new ContentComponentModelEffectEndRoundSingleRelation();
    private final ContentComponentModelEffectEndRoundTeam contentEffectEndRoundTeam = new ContentComponentModelEffectEndRoundTeam();
    private final ContentComponentModelGroupEffectEndRoundStatus groupEffectEndRoundStatus = new ContentComponentModelGroupEffectEndRoundStatus();

    public static EffectEndRound instance(String _eff) {
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_FOE)) {
            return Instances.newEffectEndRoundFoe();
        }
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_GLOBAL)) {
            return Instances.newEffectEndRoundGlobal();
        }
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_INDIVIDUAL)) {
            return Instances.newEffectEndRoundIndividual();
        }
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_MULTI_RELATION)) {
            return Instances.newEffectEndRoundMultiRelation();
        }
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_POSITION_RELATION)) {
            return Instances.newEffectEndRoundPositionRelation();
        }
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_SINGLE_RELATION)) {
            return Instances.newEffectEndRoundSingleRelation();
        }
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_POSITION_TARGET)) {
            return Instances.newEffectEndRoundPositionTargetRelation();
        }
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_TEAM)) {
            return Instances.newEffectEndRoundTeam();
        }
        return ContentComponentModelGroupEffectEndRoundStatus.instance(_eff);
    }

    void effectForm(AbsPanel _panel,AbsGeneComponentModelEffect _core) {
        _panel.add(contentEffectEndRound.effectForm(_core));
        _panel.add(contentEffectEndRoundFoe.effectForm(_core));
        _panel.add(contentEffectEndRoundIndividual.effectForm(_core));
        _panel.add(contentEffectEndRoundMultiRelation.effectForm(_core));
        _panel.add(contentEffectEndRoundPositionRelation.effectForm(_core));
        _panel.add(contentEffectEndRoundSingleRelation.effectForm(_core));
        _panel.add(contentEffectEndRoundTeam.effectForm(_core));
        groupEffectEndRoundStatus.effectForm(_panel,_core);
    }

    public String display(String _eff) {
        boolean seen_ = !groupEffectEndRoundStatus.display(_eff).isEmpty() || matches(_eff);
        contentEffectEndRound.display(seen_);
        contentEffectEndRoundFoe.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_FOE));
        contentEffectEndRoundIndividual.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_INDIVIDUAL));
        contentEffectEndRoundMultiRelation.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_MULTI_RELATION));
        contentEffectEndRoundPositionRelation.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_POSITION_RELATION));
        contentEffectEndRoundSingleRelation.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_SINGLE_RELATION));
        contentEffectEndRoundTeam.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_TEAM));
        if (seen_) {
            return _eff;
        }
        return "";
    }

    public static boolean matches(String _eff) {
        return StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_FOE) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_GLOBAL) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_POSITION_TARGET) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_TEAM) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_INDIVIDUAL) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_MULTI_RELATION) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_POSITION_RELATION) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_SINGLE_RELATION);
    }

    public void buildEntity(EffectEndRound _edited) {
        contentEffectEndRound.buildEntity(_edited);
        if (_edited instanceof EffectEndRoundFoe) {
            contentEffectEndRoundFoe.buildEntity((EffectEndRoundFoe) _edited);
        }
        if (_edited instanceof EffectEndRoundIndividual) {
            contentEffectEndRoundIndividual.buildEntity((EffectEndRoundIndividual) _edited);
        }
        if (_edited instanceof EffectEndRoundMultiRelation) {
            contentEffectEndRoundMultiRelation.buildEntity((EffectEndRoundMultiRelation) _edited);
        }
        if (_edited instanceof EffectEndRoundPositionRelation) {
            contentEffectEndRoundPositionRelation.buildEntity((EffectEndRoundPositionRelation) _edited);
        }
        if (_edited instanceof EffectEndRoundSingleRelation) {
            contentEffectEndRoundSingleRelation.buildEntity((EffectEndRoundSingleRelation) _edited);
        }
        if (_edited instanceof EffectEndRoundTeam) {
            contentEffectEndRoundTeam.buildEntity((EffectEndRoundTeam) _edited);
        }
        if (_edited instanceof EffectEndRoundStatus) {
            groupEffectEndRoundStatus.buildEntity((EffectEndRoundStatus) _edited);
        }
    }

    public String feedForm(EffectEndRound _v) {
        contentEffectEndRound.feedForm(_v);
        String o_ = "";
        if (_v instanceof EffectEndRoundFoe) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_FOE;
            contentEffectEndRoundFoe.feedForm((EffectEndRoundFoe) _v);
        }
        if (_v instanceof EffectEndRoundIndividual) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_INDIVIDUAL;
            contentEffectEndRoundIndividual.feedForm((EffectEndRoundIndividual) _v);
        }
        if (_v instanceof EffectEndRoundMultiRelation) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_MULTI_RELATION;
            contentEffectEndRoundMultiRelation.feedForm((EffectEndRoundMultiRelation) _v);
        }
        if (_v instanceof EffectEndRoundPositionRelation) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_POSITION_RELATION;
            contentEffectEndRoundPositionRelation.feedForm((EffectEndRoundPositionRelation) _v);
        }
        if (_v instanceof EffectEndRoundSingleRelation) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_SINGLE_RELATION;
            contentEffectEndRoundSingleRelation.feedForm((EffectEndRoundSingleRelation) _v);
        }
        if (_v instanceof EffectEndRoundTeam) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_TEAM;
            contentEffectEndRoundTeam.feedForm((EffectEndRoundTeam) _v);
        }
        if (_v instanceof EffectEndRoundStatus) {
            o_ = groupEffectEndRoundStatus.feedForm((EffectEndRoundStatus) _v);
        }
        if (_v instanceof EffectEndRoundGlobal) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_GLOBAL;
        }
        if (_v instanceof EffectEndRoundPositionTargetRelation) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_POSITION_TARGET;
        }
        return o_;
    }

    public ContentComponentModelEffectEndRound getContentEffectEndRound() {
        return contentEffectEndRound;
    }

    public ContentComponentModelEffectEndRoundFoe getContentEffectEndRoundFoe() {
        return contentEffectEndRoundFoe;
    }

    public ContentComponentModelEffectEndRoundIndividual getContentEffectEndRoundIndividual() {
        return contentEffectEndRoundIndividual;
    }

    public ContentComponentModelEffectEndRoundMultiRelation getContentEffectEndRoundMultiRelation() {
        return contentEffectEndRoundMultiRelation;
    }

    public ContentComponentModelEffectEndRoundPositionRelation getContentEffectEndRoundPositionRelation() {
        return contentEffectEndRoundPositionRelation;
    }

    public ContentComponentModelEffectEndRoundSingleRelation getContentEffectEndRoundSingleRelation() {
        return contentEffectEndRoundSingleRelation;
    }

    public ContentComponentModelEffectEndRoundTeam getContentEffectEndRoundTeam() {
        return contentEffectEndRoundTeam;
    }

    public ContentComponentModelGroupEffectEndRoundStatus getGroupEffectEndRoundStatus() {
        return groupEffectEndRoundStatus;
    }
}
