package code.gui;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import code.maths.montecarlo.AbstractGenerator;
import code.sml.stream.ExtractFromFiles;
import code.maths.random.AdvancedGenerator;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class GroupFrame extends CommonFrame {
    private static final String ACCESS = "gui.groupframe";

    private static final String TITLE = "title";

    private static final String MESSAGE = "message";

    private StringMap<String> messages;

    private BufferedImage imageIconFrame;

    private boolean opened;

    private AbstractGenerator generator;
    private CustList<GroupFrame> frames;
    protected GroupFrame(String _lg, CustList<GroupFrame> _list) {
        super(_lg);
        frames = _list;
        frames.add(this);
        if (frames.size() == 1) {
            initGeneIfNull();
            frames.first().messages = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _lg,ACCESS);
        }
        if (generator == null) {
            generator = frames.first().generator;
        }
        initGeneIfNull();
    }

    private void initGeneIfNull() {
        if (generator == null) {
            generator = new AdvancedGenerator();
        }
    }

    public static boolean tryToReopen(String _applicationName, CustList<GroupFrame> _list) {
        for (GroupFrame g: _list) {
            if (StringUtil.quickEq(g.getApplicationName(), _applicationName)) {
                g.pack();
                g.setVisible(true);
                return true;
            }
        }
        return false;
    }

    public void setImageIconFrame(BufferedImage _imageIconFrame) {
        imageIconFrame = _imageIconFrame;
        setIconImage(imageIconFrame);
    }

    @Override
    public BufferedImage getImageIconFrame() {
        return imageIconFrame;
    }

    public abstract void quit();

    public abstract String getApplicationName();

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
        if(!frames.first().opened) {
            exit();
        }
    }

    protected void exit() {
        nativeExit();
    }

    protected void nativeExit() {
        for (WindowListener l: getFrame().getWindowListeners()) {
            getFrame().removeWindowListener(l);
        }
        getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void setVisible(boolean _b) {
        opened = _b;
        super.setVisible(_b);
    }

    public abstract boolean canChangeLanguage();

    protected static void showDialogError(GroupFrame _group) {
        StringMap<String> messages_ = _group.getFrames().first().messages;
        ConfirmDialog.showMessage(_group, messages_.getVal(MESSAGE), messages_.getVal(TITLE), _group.getFrames().first().getLanguageKey(), JOptionPane.ERROR_MESSAGE);
    }

    public static void changeStaticLanguage(String _language, CustList<GroupFrame> _list) {
        _list.first().messages = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _language,ACCESS);
        _list.first().changeLanguage(_language);
    }

    protected boolean canChangeLanguageAll() {
        boolean canChange_ = true;
        for (GroupFrame g: frames) {
            if (!g.canChangeLanguage()) {
                canChange_ = false;
                break;
            }
        }
        return canChange_;
    }

    public abstract void changeLanguage(String _language);

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public CustList<GroupFrame> getFrames() {
        return frames;
    }
}
