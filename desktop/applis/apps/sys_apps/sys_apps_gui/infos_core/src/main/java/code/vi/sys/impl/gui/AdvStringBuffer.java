package code.vi.sys.impl.gui;

import code.gui.initialize.AbsStringBuffer;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public final class AdvStringBuffer implements AbsStringBuffer {
    @Override
    public void copy(String _c) {
        StringSelection str_ = new StringSelection(_c);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str_,str_);
    }

    @Override
    public String paste() {
        try {
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).getTransferData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            return null;
        }
    }
}
