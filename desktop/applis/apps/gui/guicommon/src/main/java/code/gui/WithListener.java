package code.gui;

import java.awt.event.WindowListener;

public interface WithListener {
    void addWindowListener(WindowListener _l);
    void removeWindowListener(WindowListener _l);
    WindowListener[] getWindowListeners();

    void setDefaultCloseOperation(int _option);

    void setVisible(boolean _v);

    void setContentPane(Panel _p);

    void setLocationRelativeTo(CustComponent _c);
    void setLocationRelativeTo(WithListener _c);
    void setLocationRelativeToNull();
    boolean isVisible();

    void dispose();
}
