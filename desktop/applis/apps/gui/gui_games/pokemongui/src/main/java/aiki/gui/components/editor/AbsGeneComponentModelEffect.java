package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.StringMap;

public abstract class AbsGeneComponentModelEffect implements ChangeableFormType {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList factory;
    private final AbsCommonFrame frame;
    private GeneComponentModelElt<String> effectKind;

    protected AbsGeneComponentModelEffect(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        frame = _f;
        programInfos = _core;
        facadeGame = _fac;
        factory = _fact;
    }

    protected void init(StringMap<String> _messages) {
        effectKind = new GeneComponentModelElt<String>(programInfos, _messages, new EmptyDefValue());
    }
    public AbsCommonFrame getFrame() {
        return frame;
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public GeneComponentModelElt<String> getEffectKind() {
        return effectKind;
    }

    public SubscribedTranslationList getFactory() {
        return factory;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }
}
