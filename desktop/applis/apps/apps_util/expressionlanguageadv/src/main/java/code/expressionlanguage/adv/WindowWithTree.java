package code.expressionlanguage.adv;

import code.gui.AbsTabbedPane;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNode;
import code.util.CustList;
import code.util.StringList;

public interface WindowWithTree {
    AbsTreeGui getTree();
    void changeEnable(AbstractMutableTreeNode _en);
    void changeEnable(boolean _en);
    String pathToSrc();
    CustList<TabEditor> getTabs();
    AbsTabbedPane getEditors();

    WindowCdmEditor getMainFrame();

    StringList openedFiles();
}
