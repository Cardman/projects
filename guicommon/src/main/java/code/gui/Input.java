package code.gui;

import javax.swing.JComponent;

import code.util.Ints;
import code.util.StringList;

public interface Input {

    JComponent getGlobal();

    Ints getSelectedIndexes();
    StringList getSelectedValues();
}
