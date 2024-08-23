package code.gui;

import code.gui.events.AbsWindowListenerClosing;
import code.gui.images.AbstractImage;
import code.util.CustList;



public interface AbsCommonFrame extends ChangeableTitle {

    void setImageIconFrame(AbstractImage _imageIconFrame);
    void dispose();
    void requestFocus();
    int getLocationFirst();
    int getLocationSecond();

    void setLocation(int _x, int _y);
    int getWidth();
    int getHeight();
    void setFocusableWindowState(boolean _focusableWindowState);
    void setFocusable(boolean _focusable);
    AbsWindowListenerClosing addWindowListener(AbsWindowListenerClosing _l);

    AbsWindowListenerClosing removeWindowListener(AbsWindowListenerClosing _l);

    CustList<AbsWindowListenerClosing> getWindowListenersDef();
    void dispatchExit();

    void setIconImage(AbstractImage _image);
//    String getAccessFile();
//    void setAccessFile(String _accessFile);
    void setContentPane(AbsPanel _contentPane);
    void setContentPane(AbsScrollPane _contentPane);
    AbsMenuBar getJMenuBar();
    void setJMenuBar(AbsMenuBar _menu);
    void setVisible(boolean _b);
    void setLocationRelativeTo(AbsCustComponent _c);

    void setLocationRelativeTo(AbsCommonFrame _c);
    void setLocationRelativeToNull();
}
