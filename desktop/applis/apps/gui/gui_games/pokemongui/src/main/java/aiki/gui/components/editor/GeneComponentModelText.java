package aiki.gui.components.editor;

import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;

public final class GeneComponentModelText {
    private final AbstractProgramInfos compoFactory;
    private AbsTextPane textPane;

    public GeneComponentModelText(AbstractProgramInfos _c) {
        this.compoFactory = _c;
    }

    public AbsCustComponent geneString() {
        textPane = compoFactory.getCompoFactory().newTextPane();
        AbsScrollPane sc_ = compoFactory.getCompoFactory().newAbsScrollPane(textPane);
        sc_.setPreferredSize(new MetaDimension(320, 32));
        return sc_;
    }

    public String valueString() {
        return textPane.getText();
    }

    public String valueString(String _v) {
        String p_ = textPane.getText();
        textPane.setText(_v);
        return p_;
    }

    public AbsTextPane getTextPane() {
        return textPane;
    }
}
