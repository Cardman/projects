package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.util.core.*;

public final class ContentComponentModelGroupEffectEndRound {
    private final ContentComponentModelEffectEndRound contentEffectEndRound = new ContentComponentModelEffectEndRound();
    private final ContentComponentModelEffectEndRoundFoe contentEffectEndRoundFoe = new ContentComponentModelEffectEndRoundFoe();
    private final ContentComponentModelEffectEndRoundTeam contentEffectEndRoundTeam = new ContentComponentModelEffectEndRoundTeam();
    private final ContentComponentModelGroupEffectEndRoundStatus groupEffectEndRoundStatus = new ContentComponentModelGroupEffectEndRoundStatus();

    public static EffectEndRound instance(String _eff) {
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_FOE)) {
            return Instances.newEffectEndRoundFoe();
        }
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_TEAM)) {
            return Instances.newEffectEndRoundTeam();
        }
        return ContentComponentModelGroupEffectEndRoundStatus.instance(_eff);
    }

    void effectForm(AbsPanel _panel,AbsGeneComponentModelEffect _core) {
        _panel.add(contentEffectEndRound.effectForm(_core.getProgramInfos()));
        _panel.add(contentEffectEndRoundFoe.effectForm(_core.getProgramInfos()));
        _panel.add(contentEffectEndRoundTeam.effectForm(_core.getProgramInfos()));
        groupEffectEndRoundStatus.effectForm(_panel,_core);
    }

    public String display(String _eff) {
        boolean seen_ = !groupEffectEndRoundStatus.display(_eff).isEmpty() || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_FOE) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_TEAM);
        contentEffectEndRound.display(seen_);
        contentEffectEndRoundFoe.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_FOE));
        contentEffectEndRoundTeam.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_TEAM));
        if (seen_) {
            return _eff;
        }
        return "";
    }

    public void buildEntity(EffectEndRound _edited) {
        contentEffectEndRound.buildEntity(_edited);
        if (_edited instanceof EffectEndRoundFoe) {
            contentEffectEndRoundFoe.buildEntity((EffectEndRoundFoe) _edited);
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
        if (_v instanceof EffectEndRoundTeam) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_TEAM;
            contentEffectEndRoundTeam.feedForm((EffectEndRoundTeam) _v);
        }
        if (_v instanceof EffectEndRoundStatus) {
            o_ = groupEffectEndRoundStatus.feedForm((EffectEndRoundStatus) _v);
        }
        return o_;
    }

    public ContentComponentModelEffectEndRound getContentEffectEndRound() {
        return contentEffectEndRound;
    }

    public ContentComponentModelEffectEndRoundFoe getContentEffectEndRoundFoe() {
        return contentEffectEndRoundFoe;
    }

    public ContentComponentModelEffectEndRoundTeam getContentEffectEndRoundTeam() {
        return contentEffectEndRoundTeam;
    }

    public ContentComponentModelGroupEffectEndRoundStatus getGroupEffectEndRoundStatus() {
        return groupEffectEndRoundStatus;
    }
}
