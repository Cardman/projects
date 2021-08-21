package code.gui;

import code.gui.events.AbsWindowListener;
import code.util.CustList;

public interface WithListener {
    void addWindowListener(AbsWindowListener _l);

    void removeWindowListener(AbsWindowListener _l);

    CustList<AbsWindowListener> getWindowListeners();

    void setDefaultCloseOperation(int _option);

    String getTitle();
    void setTitle(String _title);
    void setVisible(boolean _v);

    void setContentPane(Panel _p);

    void setLocationRelativeTo(CustComponent _c);

    void setLocationRelativeTo(AbsOtherDialog _c);
    void setLocationRelativeTo(AbsOtherFrame _c);
    void setLocationRelativeToNull();
    boolean isVisible();
    void pack();

    void dispose();
}
