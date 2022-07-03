package code.gui;

import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public interface AbsGraphicStringList extends AbsGraphicListCommon,AbsGraphicList<String> {
    int getHeightList();
    AbstractImageFactory getFact();
    AbsCompoFactory getCompoFactory();
    void setHeightList(int _height);
    void repaintSelect(int _index, boolean _sel);

    StringList getElements();

    AbsPanel getPanel();

    AbsCustComponent getGlobal();

    int getMaxWidth();

    CustList<AbsPreparedLabel> getListComponents();

    Ints getSelectedIndexes();

    ListSelection getListener();

    void simpleSetListener(ListSelection _sel);
}
