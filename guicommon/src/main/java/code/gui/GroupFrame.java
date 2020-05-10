package code.gui;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import code.maths.montecarlo.AbstractGenerator;
import code.sml.stream.ExtractFromFiles;
import code.stream.AdvancedGenerator;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class GroupFrame extends CommonFrame {
    private static final String ACCESS = "gui.groupframe";

    private static final CustList<GroupFrame> FRAMES = new CustList<GroupFrame>();

    private static final String TITLE = "title";

    private static final String MESSAGE = "message";

    private StringMap<String> messages;

    private BufferedImage imageIconFrame;

    private boolean opened;

    private AbstractGenerator generator;

    protected GroupFrame(String _lg) {
        super(_lg);
        addInstance();
        if (FRAMES.size() == 1) {
            initGeneIfNull();
            FRAMES.first().messages = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _lg,ACCESS);
        }
        if (generator == null) {
            generator = FRAMES.first().generator;
        }
        initGeneIfNull();
    }

    private void addInstance() {
        FRAMES.add(this);
    }

    private void initGeneIfNull() {
        if (generator == null) {
            AdvancedGenerator generator_ = new AdvancedGenerator();
            generator_.setSeed(System.currentTimeMillis());
            generator = generator_;
        }
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

    public void setImageIconFrame(BufferedImage _imageIconFrame) {
        imageIconFrame = _imageIconFrame;
        setIconImage(imageIconFrame);
    }

    @Override
    public BufferedImage getImageIconFrame() {
        return imageIconFrame;
    }

    protected static GroupFrame getFrame(int _i) {
        return FRAMES.get(_i);
    }

    protected static int getFrameCount() {
        return FRAMES.size();
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
            index_++;
        }
        if(FRAMES.isEmpty()) {
            exit();
        }
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
        StringMap<String> messages_ = FRAMES.first().messages;
        ConfirmDialog.showMessage(_group, messages_.getVal(MESSAGE), messages_.getVal(TITLE), FRAMES.first().getLanguageKey(), JOptionPane.ERROR_MESSAGE);
    }

    public static void changeStaticLanguage(String _language) {
        FRAMES.first().messages = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, _language,ACCESS);
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

    public AbstractGenerator getGenerator() {
        return generator;
    }
}
