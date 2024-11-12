package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelEffect implements GeneComponentModel<Effect> {
    private final AbstractProgramInfos programInfos;
    private GeneComponentModelEltEnum<String> effectKind;
    private GeneComponentModelString fail;
    private Effect edited;
    private final AbsCommonFrame frame;

    public GeneComponentModelEffect(AbsCommonFrame _f, AbstractProgramInfos _core) {
        frame = _f;
        programInfos = _core;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectEffectTr(MessagesPkEditor.getAppliTr(programInfos.currentLg())).getMapping();
        effectKind = new GeneComponentModelEltEnum<String>(programInfos, messages_);
        fail = new GeneComponentModelString(programInfos,new StringList(),new DefValidateText());
        AbsCompoFactory compoFactory_ = programInfos.getCompoFactory();
        AbsPanel evoForm_ = compoFactory_.newLineBox();
        AbsPanel selected_ = compoFactory_.newLineBox();
        evoForm_.add(effectKind.geneEnum(""));
        evoForm_.add(selected_);
        selected_.add(fail.geneString());
        effectKind.getSelect().addListener(new ChangingEffectEvent(this));
        effectKind.getSelect().select(NumberUtil.parseInt(MessagesEditorSelect.EFF_DAMAGE));
        effectKind.getSelect().events(null);
        return evoForm_;
    }

    public void applyChange() {
        edited = Instances.newEffectDamage();
        effectKind.getSelect().repaint();
        frame.pack();
    }

    @Override
    public Effect value() {
        edited.setFail(fail.valueString());
        return edited;
    }

    @Override
    public void value(Effect _v) {
        fail.valueString(_v.getFail());
        edited = _v;
    }

    public GeneComponentModelString getFail() {
        return fail;
    }

    public GeneComponentModelEltEnum<String> getEffectKind() {
        return effectKind;
    }

}
