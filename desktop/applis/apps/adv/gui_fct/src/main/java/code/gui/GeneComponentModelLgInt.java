package code.gui;

import code.gui.events.*;
import code.gui.initialize.*;
import code.maths.*;

public final class GeneComponentModelLgInt implements GeneComponentModel<LgInt> {
    private final AbstractProgramInfos compoFactory;
    private AbsTextField textLgInt;
    public GeneComponentModelLgInt(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    @Override
    public AbsCustComponent gene() {
        return gene(LgInt.zero());
    }

    public AbsCustComponent gene(LgInt _d) {
        textLgInt = compoFactory.getCompoFactory().newTextField(_d.toNumberString());
        textLgInt.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new ChgLgTextInput(1,textLgInt)), GuiConstants.VK_UP,0);
        textLgInt.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new ChgLgTextInput(-1,textLgInt)),GuiConstants.VK_DOWN,0);
        return textLgInt;
    }

    @Override
    public LgInt value() {
        return LgInt.newLgInt(textLgInt.getText());
    }

    @Override
    public LgInt value(LgInt _v) {
        String p_ = textLgInt.getText();
        textLgInt.setText(_v.toNumberString());
        return LgInt.newLgInt(p_);
    }
}
