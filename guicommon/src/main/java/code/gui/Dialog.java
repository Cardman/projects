package code.gui;
import java.awt.Component;
import java.awt.Image;

import javax.swing.JDialog;

import code.gui.events.CrossClosingDialogEvent;
import code.util.StringMap;
import code.util.consts.Constants;
import code.xml.util.ExtractFromFiles;

public abstract class Dialog extends JDialog implements ChangeableTitle {

    private String accessFile;

    private Image imageIconFrame;
    protected Dialog() {
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new CrossClosingDialogEvent(this));
        WindowUtils.addInArray(this);
    }

    public void setLocationRelativeToWindow(Iconifiable _i) {
        if (_i instanceof Component) {
            setLocationRelativeTo((Component)_i);
        }
    }

    protected StringMap<String> getMessages(String _messageFolder) {
        return ExtractFromFiles.getMessagesFromLocaleClass(_messageFolder, Constants.getLanguage(), accessFile);
    }

    protected void setAccessFile(String _accessFile) {
        accessFile = _accessFile;
    }

    public void closeWindow() {
        setVisible(false);
        getContentPane().removeAll();
    }

    protected void setDialogIcon(Iconifiable _group) {
        setIconImage(_group.getImageIconFrame());
        imageIconFrame = _group.getImageIconFrame();
    }

//    protected void setDialogIcon(Window _group) {
//        try {
//            setIconImage(_group.getIconImages().get(List.FIRST_INDEX));
//            imageIconFrame = _group.getIconImages().get(List.FIRST_INDEX);
//        } catch (Exception e_) {
//            e_.printStackTrace();
//        }
//    }

    @Override
    public Image getImageIconFrame() {
        return imageIconFrame;
    }

    @Override
    public void setVisible(boolean _b) {
        super.setVisible(_b);
        if (!_b) {
            WindowUtils.removeWindow(this);
        }
    }
}
