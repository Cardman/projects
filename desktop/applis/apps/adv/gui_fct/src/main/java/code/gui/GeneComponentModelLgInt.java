package code.gui;

import code.gui.events.*;
import code.gui.initialize.*;
import code.maths.*;

public final class GeneComponentModelLgInt {
    private final AbstractProgramInfos compoFactory;
    private AbsTextField textLgInt;
    public GeneComponentModelLgInt(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    public AbsCustComponent geneLgInt() {
        return gene(LgInt.zero());
    }

    public AbsCustComponent gene(LgInt _d) {
        textLgInt = compoFactory.getCompoFactory().newTextField(_d.toNumberString(), 20);
        textLgInt.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new ChgLgTextInput(1,textLgInt)), GuiConstants.VK_UP,0);
        textLgInt.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new ChgLgTextInput(-1,textLgInt)),GuiConstants.VK_DOWN,0);
        return textLgInt;
    }

    public LgInt value() {
        return valueLgInt();
    }

    public LgInt valueLgInt() {
        return LgInt.newLgInt(getTextLgInt().getText());
    }

    public void value(LgInt _v) {
        valueLgInt(_v);
    }

    public LgInt valueLgInt(LgInt _v) {
        String p_ = textLgInt.getText();
        textLgInt.setText(_v.toNumberString());
        return LgInt.newLgInt(p_);
    }

    public AbsTextField getTextLgInt() {
        return textLgInt;
    }
}
