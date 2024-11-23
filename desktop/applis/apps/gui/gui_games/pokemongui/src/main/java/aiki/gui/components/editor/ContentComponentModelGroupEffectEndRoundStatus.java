package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.util.core.*;

public final class ContentComponentModelGroupEffectEndRoundStatus {
    private final ContentComponentModelEffectEndRoundStatus contentEffectEndRoundStatus = new ContentComponentModelEffectEndRoundStatus();
    private final ContentComponentModelEffectEndRoundSingleStatus contentEffectEndRoundSingleStatus = new ContentComponentModelEffectEndRoundSingleStatus();
    private final ContentComponentModelEffectEndRoundStatusRelation contentEffectEndRoundStatusRelation = new ContentComponentModelEffectEndRoundStatusRelation();

    public static EffectEndRoundStatus instance(String _eff) {
        if (StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_STATUS)) {
            return Instances.newEffectEndRoundSingleStatus();
        }
        return Instances.newEffectEndRoundStatusRelation();
    }

    void effectForm(AbsPanel _panel,AbsGeneComponentModelEffect _core) {
        _panel.add(contentEffectEndRoundStatus.effectForm(_core.getProgramInfos()));
        _panel.add(contentEffectEndRoundSingleStatus.effectForm(_core.getProgramInfos()));
        _panel.add(contentEffectEndRoundStatusRelation.effectForm(_core.getProgramInfos()));
    }

    public String display(String _eff) {
        boolean seen_ = matches(_eff);
        contentEffectEndRoundStatus.display(seen_);
        contentEffectEndRoundSingleStatus.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_STATUS));
        contentEffectEndRoundStatusRelation.display(StringUtil.quickEq(_eff,MessagesEditorSelect.EFF_END_ROUND_STATUS_RELATION));
        if (seen_) {
            return _eff;
        }
        return "";
    }

    public static boolean matches(String _eff) {
        return StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_STATUS) || StringUtil.quickEq(_eff, MessagesEditorSelect.EFF_END_ROUND_STATUS_RELATION);
    }

    public void buildEntity(EffectEndRoundStatus _edited) {
        contentEffectEndRoundStatus.buildEntity(_edited);
        if (_edited instanceof EffectEndRoundSingleStatus) {
            contentEffectEndRoundSingleStatus.buildEntity((EffectEndRoundSingleStatus) _edited);
        }
        if (_edited instanceof EffectEndRoundStatusRelation) {
            contentEffectEndRoundStatusRelation.buildEntity((EffectEndRoundStatusRelation) _edited);
        }
    }

    public String feedForm(EffectEndRoundStatus _v) {
        contentEffectEndRoundStatus.feedForm(_v);
        String o_ = "";
        if (_v instanceof EffectEndRoundSingleStatus) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_STATUS;
            contentEffectEndRoundSingleStatus.feedForm((EffectEndRoundSingleStatus) _v);
        }
        if (_v instanceof EffectEndRoundStatusRelation) {
            o_ = MessagesEditorSelect.EFF_END_ROUND_STATUS_RELATION;
            contentEffectEndRoundStatusRelation.feedForm((EffectEndRoundStatusRelation) _v);
        }
        return o_;
    }

    public ContentComponentModelEffectEndRoundStatus getContentEffectEndRoundStatus() {
        return contentEffectEndRoundStatus;
    }

    public ContentComponentModelEffectEndRoundSingleStatus getContentEffectEndRoundSingleStatus() {
        return contentEffectEndRoundSingleStatus;
    }

    public ContentComponentModelEffectEndRoundStatusRelation getContentEffectEndRoundStatusRelation() {
        return contentEffectEndRoundStatusRelation;
    }
}
