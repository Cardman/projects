package code.gui;

import code.gui.events.ChgLgInput;
import code.gui.initialize.*;
import code.util.core.NumberUtil;

public final class GeneComponentModelLong implements GeneComponentModel<Long> {
    private final AbstractProgramInfos compoFactory;
    private AbsTextField textLong;
    public GeneComponentModelLong(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    @Override
    public AbsCustComponent gene() {
        return gene(0L);
    }

    public AbsCustComponent gene(Long _d) {
        textLong = compoFactory.getCompoFactory().newTextField(Long.toString(_d), 20);
        textLong.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new ChgLgInput(1,textLong)), GuiConstants.VK_UP,0);
        textLong.registerKeyboardAction(compoFactory.getCompoFactory().wrap(new ChgLgInput(-1,textLong)),GuiConstants.VK_DOWN,0);
        return textLong;
    }

    @Override
    public Long value() {
        return NumberUtil.parseLongZero(textLong.getText());
    }

    @Override
    public Long value(Long _v) {
        String p_ = textLong.getText();
        textLong.setText(Long.toString(_v));
        return NumberUtil.parseLongZero(p_);
    }
}
