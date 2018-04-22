package code.gui;
import java.awt.Image;

import javax.swing.JOptionPane;

import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public abstract class GroupFrame extends CommonFrame implements ChangeableTitle {
    private static final String ACCESS = "gui.GroupFrame";

    private static final CustList<GroupFrame> FRAMES = new CustList<GroupFrame>();

    private static final String TITLE = "title";

    private static final String MESSAGE = "message";

    private static StringMap<String> _messages_ = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, Constants.getLanguage(),ACCESS);

    private Image imageIconFrame;

    private boolean opened;

    protected GroupFrame() {
        FRAMES.add(this);
        WindowUtils.addInArray(getFrame());
    }

    public static boolean tryToReopen(String _applicationName) {
        for (GroupFrame g: FRAMES) {
            if (StringList.quickEq(g.getApplicationName(), _applicationName)) {
                g.pack();
                g.setVisible(true);
                return true;
            }
        }
        return false;
    }

    public void setImageIconFrame(Image _imageIconFrame) {
        imageIconFrame = _imageIconFrame;
        setIconImage(imageIconFrame);
    }

    @Override
    public Image getImageIconFrame() {
        return imageIconFrame;
    }

    protected static GroupFrame getFrame(int _i) {
        return FRAMES.get(_i);
    }

    public abstract void quit();

    public abstract String getApplicationName();

    public void destroy() {
        int index_ = CustList.FIRST_INDEX;
        for (GroupFrame g: FRAMES) {
            if (g == this) {
                FRAMES.removeAt(index_);
                setVisible(false);
                nativeDispose();
                break;
            }
            index_ ++;
        }
        if(FRAMES.isEmpty()) {
            Constants.exit();
        }
//        System.gc();
    }

    public void nativeDispose() {
        super.dispose();
    }

    @Override
    public void dispose() {
        //int index_ = CustList.FIRST_INDEX;
//        for (GroupFrame g: FRAMES) {
//            if (g == this) {
//                //super.dispose();
//                setVisible(false);
//                //FRAMES.remove(index_);
//                break;
//            }
//            //index_ ++;
//        }
        setVisible(false);
//        int nbOpenedWindows_ = CustList.SIZE_EMPTY;
//        for (Window w: Window.getWindows()) {
//            if (w == null) {
//                continue;
//            }
//            if (w.isDisplayable()) {
//                nbOpenedWindows_ ++;
//            }
//        }
//        if(index_ == CustList.SIZE_EMPTY) {}
        if(!FRAMES.first().opened) {
            Constants.exit();
        }
//        System.gc();
    }

    @Override
    public void setVisible(boolean _b) {
        opened = _b;
        super.setVisible(_b);
        if (!_b) {
            WindowUtils.removeWindow(getFrame());
        }
    }

    public abstract boolean canChangeLanguage();

    protected static void showDialogError(GroupFrame _group) {
        ConfirmDialog.showMessage(_group, _messages_.getVal(MESSAGE), _messages_.getVal(TITLE), Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
    }

    public static void changeStaticLanguage(String _language) {
        _messages_ = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _language,ACCESS);
        FRAMES.first().changeLanguage(_language);
    }

    protected static boolean canChangeLanguageAll() {
        boolean canChange_ = true;
        for (GroupFrame g: FRAMES) {
            if (!g.canChangeLanguage()) {
                canChange_ = false;
                break;
            }
        }
        return canChange_;
    }

    public abstract void changeLanguage(String _language);
}
