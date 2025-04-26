package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEffectEndRoundStatus extends AbsGeneComponentModelEffect {
    private final ContentComponentModelEffect contentEffect = new ContentComponentModelEffect();
    private final ContentComponentModelGroupEffectEndRound contentGroupEffectEndRound = new ContentComponentModelGroupEffectEndRound();
    private final ContentComponentModelGroupEffectEndRoundStatus contentGroupEffectEndRoundStatus = new ContentComponentModelGroupEffectEndRoundStatus();
    private EffectEndRoundStatus edited;

    public GeneComponentModelEffectEndRoundStatus(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        super(_f, _core, _fac, _fact);
    }

    public AbsPanel geneEffect() {
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())).getMapping();
        StringMap<String> filtered_ = new StringMap<String>();
        for (EntryCust<String,String> e: messages_.entryList()) {
            if (ContentComponentModelGroupEffectEndRoundStatus.matches(e.getKey())) {
                filtered_.addEntry(e.getKey(),e.getValue());
            }
        }
        init(filtered_);
        AbsCompoFactory compoFactory_ = getProgramInfos().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(getEffectKind().geneEnum());
        form_.add(contentEffect.effectForm(this));
        contentGroupEffectEndRound.effectForm(form_,this);
        contentGroupEffectEndRoundStatus.effectForm(form_,this);
        getEffectKind().getSelect().addListener(new ChangingTypeEvent(this));
        ConverterCommonMapUtil.trigger(getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_STATUS);
        return form_;
    }

    @Override
    public void applyChange() {
        String eff_ = getEffectKind().tryRet();
        display(eff_);
        edited = ContentComponentModelGroupEffectEndRoundStatus.instance(eff_);
        effectSub(edited);
        getEffectKind().getSelect().repaint();
        getFrame().pack();
    }

    public EffectEndRoundStatus valueEffect() {
        contentEffect.buildEntity(edited);
        contentGroupEffectEndRound.buildEntity(edited);
        contentGroupEffectEndRoundStatus.buildEntity(edited);
        return edited;
    }

    public void valueEffect(EffectEndRoundStatus _v) {
        contentEffect.feedForm(_v);
        contentGroupEffectEndRound.feedForm(_v);
        displayRepaint(contentGroupEffectEndRoundStatus.feedForm(_v));
        edited = _v;
        effectSub(_v);
    }
    private void effectSub(EffectEndRoundStatus _v) {
        getFactory().effectEndRoundStatus(_v);
    }

    private void displayRepaint(String _eff) {
        display(_eff);
        getEffectKind().setupValue(_eff);
        getEffectKind().getSelect().repaint();
    }

    private void display(String _eff) {
        contentGroupEffectEndRound.display(_eff);
        contentGroupEffectEndRoundStatus.display(_eff);
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getContentEffect().getTargetChoice().getSubs());
        ids_.addAllElts(getContentEffect().getFail().getSubs());
        ids_.addAllElts(GeneComponentModelEffect.endRound(getContentGroupEffectEndRound()));
        return ids_;
    }

    public ContentComponentModelEffect getContentEffect() {
        return contentEffect;
    }

    public ContentComponentModelGroupEffectEndRound getContentGroupEffectEndRound() {
        return contentGroupEffectEndRound;
    }

}
