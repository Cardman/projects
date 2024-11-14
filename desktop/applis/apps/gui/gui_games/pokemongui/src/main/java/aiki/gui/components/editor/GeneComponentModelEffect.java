package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class GeneComponentModelEffect extends AbsGeneComponentModelEffect implements GeneComponentModel<Effect> {
    private final ContentComponentModelEffect contentEffect = new ContentComponentModelEffect();
    private Effect edited;

    public GeneComponentModelEffect(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        super(_f, _core, _fac, _fact);
    }

    @Override
    public AbsCustComponent gene(int _select) {
        init(MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(getProgramInfos().currentLg())).getMapping());
        AbsCompoFactory compoFactory_ = getProgramInfos().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(getEffectKind().geneEnum(""));
        form_.add(contentEffect.effectForm(getFrame(), getProgramInfos(), getFacadeGame(), getFactory()));
        getEffectKind().getSelect().addListener(new ChangingEffectEvent(this));
        getEffectKind().getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_DAMAGE));
        getEffectKind().getSelect().events(null);
        return form_;
    }

    @Override
    public void applyChange() {
        edited = Instances.newEffectDamage();
        getEffectKind().getSelect().repaint();
        getFrame().pack();
    }

    @Override
    public Effect value() {
        contentEffect.buildEntity(edited);
        return edited;
    }

    @Override
    public void value(Effect _v) {
        contentEffect.feedForm(_v);
        edited = _v;
    }

    public GeneComponentModelString getFail() {
        return contentEffect.getFail();
    }

}
