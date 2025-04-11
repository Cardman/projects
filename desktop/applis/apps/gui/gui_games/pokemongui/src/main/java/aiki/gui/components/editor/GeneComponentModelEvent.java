package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;

public abstract class GeneComponentModelEvent<E> implements GeneComponentModel<EditedCrudPair<E, LgInt>> {
    private final AbstractProgramInfos core;
    private final GeneComponentModelLgInt proba;
    private final String textIntroKey;
    private final String textIntroValue;

    protected GeneComponentModelEvent(AbstractProgramInfos _c) {
        this(_c,"","");
    }

    protected GeneComponentModelEvent(AbstractProgramInfos _c, String _k, String _v) {
        this.core = _c;
        proba = new GeneComponentModelLgInt(core);
        textIntroKey = _k;
        textIntroValue = _v;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        AbsCompoFactory compoFactory_ = core.getCompoFactory();
        AbsPanel line_ = compoFactory_.newLineBox();
        line_.add(SubscribedTranslationList.lineDir(core,textIntroKey,geneKey(_select)));
        line_.add(SubscribedTranslationList.lineDir(core,textIntroValue,proba.geneLgInt()));
        return line_;
    }

    protected abstract AbsCustComponent geneKey(int _select);

    @Override
    public EditedCrudPair<E, LgInt> value() {
        return new EditedCrudPair<E, LgInt>(valEvent(),proba.valueLgInt());
    }

    protected abstract E valEvent();

    @Override
    public void value(EditedCrudPair<E, LgInt> _v) {
        updateEvent(_v.getKey());
        proba.valueLgInt(_v.getValue());
    }

    protected abstract void updateEvent(E _e);

    public GeneComponentModelLgInt getProba() {
        return proba;
    }
}
