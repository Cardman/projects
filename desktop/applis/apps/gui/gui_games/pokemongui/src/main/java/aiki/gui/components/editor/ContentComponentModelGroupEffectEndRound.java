package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.util.core.*;

public final class ContentComponentModelGroupEffectEndRound {
    private final ContentComponentModelEffectEndRound contentEffectEndRound = new ContentComponentModelEffectEndRound();
    private final ContentComponentModelEffectEndRoundFoe contentEffectEndRoundFoe = new ContentComponentModelEffectEndRoundFoe();
    private final ContentComponentModelEffectEndRoundTeam contentEffectEndRoundTeam = new ContentComponentModelEffectEndRoundTeam();

    public static Effect instance(String _eff) {
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_FOE)) {
            return Instances.newEffectEndRoundFoe();
        }
        return Instances.newEffectEndRoundTeam();
    }

    void effectForm(AbsPanel _panel,AbsGeneComponentModelEffect _core) {
        _panel.add(contentEffectEndRound.effectForm(_core.getProgramInfos()));
        _panel.add(contentEffectEndRoundFoe.effectForm(_core.getProgramInfos()));
        _panel.add(contentEffectEndRoundTeam.effectForm(_core.getProgramInfos()));
    }

    public boolean display(String _eff) {
        boolean seen_ = StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_FOE) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_TEAM);
        contentEffectEndRound.display(seen_);
        contentEffectEndRoundFoe.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_FOE));
        contentEffectEndRoundTeam.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_TEAM));
        return seen_;
    }

    public void buildEntity(EffectEndRound _edited) {
        contentEffectEndRound.buildEntity(_edited);
        if (_edited instanceof EffectEndRoundFoe) {
            contentEffectEndRoundFoe.buildEntity((EffectEndRoundFoe) _edited);
        }
        if (_edited instanceof EffectEndRoundTeam) {
            contentEffectEndRoundTeam.buildEntity((EffectEndRoundTeam) _edited);
        }
    }

    public void feedForm(EffectEndRound _v) {
        contentEffectEndRound.feedForm(_v);
        if (_v instanceof EffectEndRoundFoe) {
            contentEffectEndRoundFoe.feedForm((EffectEndRoundFoe) _v);
        }
        if (_v instanceof EffectEndRoundTeam) {
            contentEffectEndRoundTeam.feedForm((EffectEndRoundTeam) _v);
        }
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
}
