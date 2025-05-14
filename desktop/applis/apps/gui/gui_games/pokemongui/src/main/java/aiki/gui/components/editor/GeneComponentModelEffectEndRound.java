package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelEffectEndRound extends AbsGeneComponentModelEffect {
    private final ContentComponentModelEffect contentEffect = new ContentComponentModelEffect();
    private final ContentComponentModelGroupEffectEndRound contentGroupEffectEndRound = new ContentComponentModelGroupEffectEndRound();
    private EffectEndRound edited;
    private final boolean procAbility;

    public GeneComponentModelEffectEndRound(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, boolean _ability) {
        super(_f, _core, _fac, _fact);
        procAbility = _ability;
    }

    public AbsPanel geneEffect() {
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())).getMapping();
        StringMap<String> filtered_ = new StringMap<String>();
        for (EntryCust<String,String> e: messages_.entryList()) {
            if (matches(e.getKey())) {
                filtered_.addEntry(e.getKey(),e.getValue());
            }
        }
        init(filtered_);
        AbsCompoFactory compoFactory_ = getProgramInfos().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(SubscribedTranslationList.line(getProgramInfos(),MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())),MessagesEditorSelect.EFF_TYPE,getEffectKind().geneEnum()));
        form_.add(contentEffect.effectForm(this));
        contentGroupEffectEndRound.effectForm(form_,this);
        getEffectKind().getSelect().addListener(new ChangingTypeEvent(this));
        ConverterCommonMapUtil.trigger(getEffectKind(),MessagesEditorSelect.EFF_END_ROUND_STATUS);
        return form_;
    }

    private static boolean matches(String _key) {
        return ContentComponentModelGroupEffectEndRound.matches(_key) || ContentComponentModelGroupEffectEndRoundStatus.matches(_key);
    }
    @Override
    public void applyChange() {
        String eff_ = getEffectKind().tryRet();
        display(eff_);
        edited = ContentComponentModelGroupEffectEndRound.instance(eff_);
        getEffectKind().getSelect().repaint();
        getFrame().pack();
        effectSub(edited);
    }

    public EffectEndRound valueEffect() {
        contentEffect.buildEntity(edited);
        contentGroupEffectEndRound.buildEntity(edited);
        return edited;
    }

    public void valueEffect(EffectEndRound _v) {
        contentEffect.feedForm(_v);
        displayRepaint(contentGroupEffectEndRound.feedForm(_v));
        edited = _v;
        effectSub(_v);
    }

    private void effectSub(EffectEndRound _v) {
        if (procAbility) {
            getFactory().effectEndRoundAbility(_v);
        } else {
            getFactory().effectEndRoundItem(_v);
        }
    }
    private void displayRepaint(String _eff) {
        display(_eff);
        getEffectKind().setupValue(_eff);
        getEffectKind().getSelect().repaint();
    }

    private void display(String _eff) {
        contentGroupEffectEndRound.display(_eff);
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
