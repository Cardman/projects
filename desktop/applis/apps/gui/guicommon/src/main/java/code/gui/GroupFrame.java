package code.gui;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.AbstractGenerator;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.AbstractFileCoreStream;
import code.threads.AbstractThreadFactory;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class GroupFrame extends CommonFrame {
    private static final String ACCESS = "gui.groupframe";

    private static final String TITLE = "title";

    private static final String MESSAGE = "message";

    private StringMap<String> messages;

    private BufferedImage imageIconFrame;

    private boolean opened;

    private final AbstractProgramInfos frames;
    private final FileOpenDialog fileOpenDialog;
    private final FileSaveDialog fileSaveDialog;
    private final FolderOpenDialog folderOpenDialog;
    private final ConfirmDialog confirmDialog;
    private final LanguageDialog languageDialog;
    protected GroupFrame(String _lg, AbstractProgramInfos _list) {
        super(_lg);
        frames = _list;
        frames.getFrames().add(this);
        if (frames.getFrames().size() == 1) {
            fileOpenDialog = new FileOpenDialog();
            fileSaveDialog = new FileSaveDialog();
            folderOpenDialog = new FolderOpenDialog();
            confirmDialog = new ConfirmDialog();
            languageDialog = new LanguageDialog();
            frames.getFrames().first().messages = group(_lg);
        } else {
            fileOpenDialog = frames.getFrames().first().fileOpenDialog;
            fileSaveDialog = frames.getFrames().first().fileSaveDialog;
            folderOpenDialog = frames.getFrames().first().folderOpenDialog;
            confirmDialog = frames.getFrames().first().confirmDialog;
            languageDialog = frames.getFrames().first().languageDialog;
        }
    }

    public static boolean tryToReopen(String _applicationName, AbstractProgramInfos _list) {
        for (GroupFrame g: _list.getFrames()) {
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
        basicDispose();
    }

    public void basicDispose() {
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
        if(!frames.getFrames().first().opened) {
            exit();
        }
        getFrames().getCounts().getVal(getApplicationName()).decrementAndGet();
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
        StringMap<String> messages_ = _group.getFrames().getFrames().first().messages;
        ConfirmDialog.showMessage(_group, messages_.getVal(MESSAGE), messages_.getVal(TITLE), _group.getFrames().getFrames().first().getLanguageKey(), JOptionPane.ERROR_MESSAGE);
    }

    public static void changeStaticLanguage(String _language, AbstractProgramInfos _list) {
        _list.getFrames().first().messages = group(_language);
        _list.getFrames().first().changeLanguage(_language);
    }

    private static StringMap<String> group(String _language) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _language, ACCESS);
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    protected boolean canChangeLanguageAll() {
        boolean canChange_ = true;
        for (GroupFrame g: frames.getFrames()) {
            if (!g.canChangeLanguage()) {
                canChange_ = false;
                break;
            }
        }
        return canChange_;
    }

    public abstract void changeLanguage(String _language);

    public AbstractGenerator getGenerator() {
        return frames.getGenerator();
    }
    public void revalidateFrame() {
        PackingWindowAfter.pack(this);
    }

    public AbstractThreadFactory getThreadFactory() {
        return frames.getThreadFactory();
    }
    public AbstractFileCoreStream getFileCoreStream() {
        return frames.getFileCoreStream();
    }

    public AbstractNameValidating getValidator() {
        return frames.getValidator();
    }

    public AbstractProgramInfos getFrames() {
        return frames;
    }

    public FileOpenDialog getFileOpenDialog() {
        return fileOpenDialog;
    }

    public FileSaveDialog getFileSaveDialog() {
        return fileSaveDialog;
    }

    public FolderOpenDialog getFolderOpenDialog() {
        return folderOpenDialog;
    }

    public ConfirmDialog getConfirmDialog() {
        return confirmDialog;
    }

    public LanguageDialog getLanguageDialog() {
        return languageDialog;
    }
}
