package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEffectEndRoundFoe extends AbsGeneComponentModelEffect {
    private final ContentComponentModelEffect contentEffect = new ContentComponentModelEffect();
    private final ContentComponentModelEffectEndRound contentEffectEndRound = new ContentComponentModelEffectEndRound();
    private final ContentComponentModelEffectEndRoundFoe contentEffectEndRoundFoe = new ContentComponentModelEffectEndRoundFoe();
    private EffectEndRoundFoe edited;

    public GeneComponentModelEffectEndRoundFoe(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        super(_f, _core, _fac, _fact);
    }

    public AbsPanel geneEffect() {
        init(MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())).getMapping());
        AbsCompoFactory compoFactory_ = getProgramInfos().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(getEffectKind().geneEnum());
        form_.add(contentEffect.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        form_.add(contentEffectEndRound.effectForm(getProgramInfos()));
        form_.add(contentEffectEndRoundFoe.effectForm(getProgramInfos()));
        getEffectKind().getSelect().addListener(new ChangingEffectEvent(this));
        ConverterCommonMapUtil.trigger(getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_FOE);
        getEffectKind().getSelect().getElements().setVisible(false);
        return form_;
    }

    @Override
    public void applyChange() {
        edited = Instances.newEffectEndRoundFoe();
        contentEffectEndRound.display(true);
        contentEffectEndRoundFoe.display(true);
        getEffectKind().getSelect().getElements().setVisible(false);
        getFrame().pack();
    }

    public EffectEndRoundFoe valueEffect() {
        contentEffect.buildEntity(edited);
        contentEffectEndRound.buildEntity(edited);
        contentEffectEndRoundFoe.buildEntity(edited);
        return edited;
    }

    public void valueEffect(EffectEndRoundFoe _v) {
        contentEffect.feedForm(_v);
        contentEffectEndRound.feedForm(_v);
        contentEffectEndRoundFoe.feedForm(_v);
        edited = _v;
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getContentEffect().getTargetChoice().getSubs());
        return ids_;
    }

    public ContentComponentModelEffect getContentEffect() {
        return contentEffect;
    }

    public ContentComponentModelEffectEndRoundFoe getContentEffectEndRoundFoe() {
        return contentEffectEndRoundFoe;
    }

}
