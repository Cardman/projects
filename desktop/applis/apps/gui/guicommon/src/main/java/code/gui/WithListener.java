package code.gui;

import code.gui.events.AbsWindowListener;
import code.util.CustList;

public interface WithListener {
    void addWindowListener(AbsWindowListener _l);

    void removeWindowListener(AbsWindowListener _l);

    CustList<AbsWindowListener> getWindowListeners();

    void setDefaultCloseOperation(int _option);

    void setVisible(boolean _v);

    void setContentPane(Panel _p);

    void setLocationRelativeTo(CustComponent _c);

    void setLocationRelativeTo(OtherDialog _c);
    void setLocationRelativeTo(OtherFrame _c);
    void setLocationRelativeToNull();
    boolean isVisible();

    void dispose();
}
