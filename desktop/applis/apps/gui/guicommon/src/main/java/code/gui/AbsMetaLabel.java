package code.gui;

import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;


public abstract class AbsMetaLabel extends AbsMetaLabelCom implements AbsMetaLabelInt{
    private final AbsPaintableLabel paintableLabel;
    protected AbsMetaLabel(AbsCompoFactory _compoFactory) {
        paintableLabel = _compoFactory.newAbsPaintableLabel(this);
    }
    public abstract void paintComponent(AbstractImage _g);
    public AbsPaintableLabel getPaintableLabel() {
        return paintableLabel;
    }
}
