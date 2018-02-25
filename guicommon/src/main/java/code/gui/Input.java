package code.gui;

import javax.swing.JComponent;

import code.util.Numbers;
import code.util.StringList;

public interface Input {

    JComponent getGlobal();

    Numbers<Integer> getSelectedIndexes();
    StringList getSelectedValues();
}
