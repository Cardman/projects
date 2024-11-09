package code.gui;

import code.gui.initialize.*;
import code.maths.*;

public abstract class GeneComponentModelEvent<E> implements GeneComponentModel<EditedCrudPair<E, LgInt>> {
    private final AbstractProgramInfos core;
    private final GeneComponentModelLgInt proba;

    protected GeneComponentModelEvent(AbstractProgramInfos _c) {
        this.core = _c;
        proba = new GeneComponentModelLgInt(core);
    }

    @Override
    public AbsCustComponent gene(int _select) {
        AbsCompoFactory compoFactory_ = core.getCompoFactory();
        AbsPanel line_ = compoFactory_.newLineBox();
        line_.add(geneKey(_select));
        line_.add(proba.gene(LgInt.zero()));
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
