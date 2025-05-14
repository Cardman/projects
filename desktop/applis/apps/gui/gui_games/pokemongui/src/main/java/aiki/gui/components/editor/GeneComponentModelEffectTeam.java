package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEffectTeam extends AbsGeneComponentModelEffect {
    private final ContentComponentModelEffect contentEffect = new ContentComponentModelEffect();
    private final ContentComponentModelEffectTeam contentEffectTeam = new ContentComponentModelEffectTeam();
    private EffectTeam edited;

    public GeneComponentModelEffectTeam(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        super(_f, _core, _fac, _fact);
    }

    public AbsPanel geneEffect() {
        init(MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())).getMapping());
        AbsCompoFactory compoFactory_ = getProgramInfos().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(SubscribedTranslationList.line(getProgramInfos(),MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())),MessagesEditorSelect.EFF_TYPE,getEffectKind().geneEnum()));
        form_.add(contentEffect.effectForm(this));
        form_.add(contentEffectTeam.effectForm(this));
        getEffectKind().getSelect().addListener(new ChangingTypeEvent(this));
        ConverterCommonMapUtil.trigger(getEffectKind(),MessagesEditorSelect.EFF_TEAM);
        return form_;
    }

    @Override
    public void applyChange() {
        edited = Instances.newEffectTeam();
        contentEffectTeam.display(true);
        getEffectKind().getSelect().getElements().setVisible(false);
        getFrame().pack();
    }

    public EffectTeam valueEffect() {
        contentEffect.buildEntity(edited);
        contentEffectTeam.buildEntity(edited);
        return edited;
    }

    public void valueEffect(EffectTeam _v) {
        contentEffect.feedForm(_v);
        contentEffectTeam.feedForm(_v);
        contentEffectTeam.display(true);
        edited = _v;
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getContentEffect().getTargetChoice().getSubs());
        ids_.addAllElts(getContentEffect().getFail().getSubs());
        ids_.addAllElts(GeneComponentModelEffect.team(getContentEffectTeam()));
        return ids_;
    }

    public ContentComponentModelEffect getContentEffect() {
        return contentEffect;
    }


    public ContentComponentModelEffectTeam getContentEffectTeam() {
        return contentEffectTeam;
    }

}
