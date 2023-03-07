package code.expressionlanguage.adv;

import code.gui.AbsTabbedPane;
import code.gui.AbsTreeGui;
import code.util.CustList;
import code.util.StringList;

public interface WindowWithTree {
    AbsTreeGui getTree();
    void changeEnable(boolean _en);
    String pathToSrc();
    CustList<TabEditor> getTabs();
    AbsTabbedPane getEditors();

    WindowCdmEditor getMainFrame();

    void removeObj(String _rel);

    StringList openedFiles();
}
