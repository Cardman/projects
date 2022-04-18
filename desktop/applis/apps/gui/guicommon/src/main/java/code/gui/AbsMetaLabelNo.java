package code.gui;

import code.gui.initialize.AbsCompoFactory;


public abstract class AbsMetaLabelNo extends AbsMetaLabelCom {
    private final AbsPaintableLabel paintableLabel;
    protected AbsMetaLabelNo(AbsCompoFactory _compoFactory) {
        paintableLabel = _compoFactory.newAbsPaintableLabel();
    }
    public AbsPaintableLabel getPaintableLabel() {
        return paintableLabel;
    }
}
