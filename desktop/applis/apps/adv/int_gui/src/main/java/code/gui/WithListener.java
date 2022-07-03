package code.gui;

import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.util.CustList;

public interface WithListener {
    void addWindowListener(AbsWindowListener _l);
    AbsWindowListenerClosing addWindowListener(AbsWindowListenerClosing _l);

    void removeWindowListener(AbsWindowListener _l);
    AbsWindowListenerClosing removeWindowListener(AbsWindowListenerClosing _l);

    CustList<AbsWindowListener> getWindowListeners();

    void setDefaultCloseOperation(int _option);

    String getTitle();
    void setTitle(String _title);
    void setVisible(boolean _v);

    void setContentPane(AbsPanel _p);

    boolean isVisible();
    void pack();

    void dispose();
}
