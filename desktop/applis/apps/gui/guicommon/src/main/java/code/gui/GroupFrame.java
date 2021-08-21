package code.gui;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.events.AbsWindowListener;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.AbstractFileCoreStream;
import code.stream.core.TechStreams;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringMap;

import java.awt.Point;

public abstract class GroupFrame implements AbsGroupFrame {

    private final AbsCommonFrame commonFrame;
    private StringMap<String> messages;

    private boolean opened;

    private FileOpenDialog fileOpenDialog;
    private FileSaveDialog fileSaveDialog;
    private FolderOpenDialog folderOpenDialog;
    private ConfirmDialog confirmDialog;
    private LanguageDialog languageDialog;

    protected GroupFrame(String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        choose(_lg, _list);
    }

    private void choose(String _lg, AbstractProgramInfos _list) {
        FrameUtil.choose(_lg, _list, this);
    }

    public final void init(AbstractProgramInfos _list) {
        fileOpenDialog = new FileOpenDialog(_list.getThreadFactory().newAtomicBoolean(), _list.getThreadFactory().newAtomicBoolean(), _list.getFrameFactory());
        fileSaveDialog = new FileSaveDialog(_list.getFrameFactory());
        folderOpenDialog = new FolderOpenDialog(_list.getFrameFactory());
        confirmDialog = new ConfirmDialog(_list);
        languageDialog = new LanguageDialog(_list.getFrameFactory());
    }

    public final void setByFirst(AbsGroupFrame _first) {
        fileOpenDialog = _first.getFileOpenDialog();
        fileSaveDialog = _first.getFileSaveDialog();
        folderOpenDialog = _first.getFolderOpenDialog();
        confirmDialog = _first.getConfirmDialog();
        languageDialog = _first.getLanguageDialog();
    }

    public void setImageIconFrame(AbstractImage _imageIconFrame) {
        commonFrame.setImageIconFrame(_imageIconFrame);
        setIconImage(_imageIconFrame);
    }

    //@Override
    public AbstractProgramInfos getFrames() {
        return commonFrame.getFrames();
    }

    //@Override
    public String getLanguageKey() {
        return commonFrame.getLanguageKey();
    }

    //@Override
    public void setLanguageKey(String _language) {
        commonFrame.setLanguageKey(_language);
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return commonFrame.getImageIconFrame();
    }

    //@Override
    public void setAccessFile(String _accessFile) {
        commonFrame.setAccessFile(_accessFile);
    }

    //@Override
    public int getWidth() {
        return commonFrame.getWidth();
    }

    //@Override
    public void setFocusableWindowState(boolean _focusableWindowState) {
        commonFrame.setFocusableWindowState(_focusableWindowState);
    }

    //@Override
    public void setContentPane(Panel _contentPane) {
        commonFrame.setContentPane(_contentPane);
    }

    //@Override
    public void setContentPane(ScrollPane _contentPane) {
        commonFrame.setContentPane(_contentPane);
    }

    //@Override
    public void setDefaultCloseOperation(int _operation) {
        commonFrame.setDefaultCloseOperation(_operation);
    }

    //@Override
    public void setFocusable(boolean _focusable) {
        commonFrame.setFocusable(_focusable);
    }

    //@Override
    public void setIconImage(AbstractImage _image) {
        commonFrame.setIconImage(_image);
    }

    //@Override
    public void setJMenuBar(MenuBar _menu) {
        commonFrame.setJMenuBar(_menu);
    }

    //@Override
    public void setLocation(int _x, int _y) {
        commonFrame.setLocation(_x, _y);
    }

    //@Override
    public void setLocationRelativeTo(AbsGroupFrame _c) {
        commonFrame.setLocationRelativeTo(_c);
    }

    //@Override
    public void setLocationRelativeTo(CustComponent _c) {
        commonFrame.setLocationRelativeTo(_c);
    }

    //@Override
    public void setLocationRelativeToNull() {
        commonFrame.setLocationRelativeToNull();
    }

    @Override
    public void setOwner(Ownable _owner) {
        commonFrame.setOwner(_owner);
    }

    @Override
    public void setTitle(String _title) {
        commonFrame.setTitle(_title);
    }

    //@Override
    public CustList<AbsWindowListener> getWindowListeners() {
        return commonFrame.getWindowListeners();
    }

    //@Override
    public Panel getPane() {
        return commonFrame.getPane();
    }

    //@Override
    public Point getLocation() {
        return commonFrame.getLocation();
    }

    //@Override
    public MenuBar getJMenuBar() {
        return commonFrame.getJMenuBar();
    }

    //@Override
    public int getHeight() {
        return commonFrame.getHeight();
    }

    //@Override
    public String getAccessFile() {
        return commonFrame.getAccessFile();
    }

    @Override
    public Ownable getOwner() {
        return commonFrame.getOwner();
    }

    @Override
    public Point getLocationOnScreen() {
        return commonFrame.getLocationOnScreen();
    }

    @Override
    public String getTitle() {
        return commonFrame.getTitle();
    }

    @Override
    public boolean isVisible() {
        return commonFrame.isVisible();
    }

    //@Override
    public void removeWindowListener(AbsWindowListener _l) {
        commonFrame.removeWindowListener(_l);
    }

    //@Override
    public void addWindowListener(AbsWindowListener _l) {
        commonFrame.addWindowListener(_l);
    }

    //@Override
    public void dispatchExit() {
        commonFrame.dispatchExit();
    }

    //@Override
    public void requestFocus() {
        commonFrame.requestFocus();
    }

    @Override
    public void pack() {
        commonFrame.pack();
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public abstract void quit();

    public abstract String getApplicationName();

    //@Override
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
        FrameUtil.tryExit(this);
        getFrames().getCounts().getVal(getApplicationName()).decrementAndGet();
    }

    public void exit() {
        nativeExit();
    }

    public void nativeExit() {
        FrameUtil.removeAllListeners(this);
        dispatchExit();
    }

    public boolean isOpened() {
        return opened;
    }

    //@Override
    public void setVisible(boolean _b) {
        opened = _b;
        commonFrame.setVisible(_b);
    }

    public abstract boolean canChangeLanguage();

    public StringMap<String> getMessages() {
        return messages;
    }

    public void setMessages(StringMap<String> _messages) {
        this.messages = _messages;
    }

    protected boolean canChangeLanguageAll() {
        return false;
//        boolean canChange_ = true;
//        for (GroupFrame g: frames.getFrames()) {
//            if (!g.canChangeLanguage()) {
//                canChange_ = false;
//                break;
//            }
//        }
//        return canChange_;
    }

    public abstract void changeLanguage(String _language);

    public AbstractGenerator getGenerator() {
        return getFrames().getGenerator();
    }
    public void revalidateFrame() {
        PackingWindowAfter.pack(this);
    }

    public AbstractImageFactory getImageFactory(){
        return getFrames().getImageFactory();
    }
    public AbsCompoFactory getCompoFactory() {
        return getFrames().getCompoFactory();
    }
    public AbstractThreadFactory getThreadFactory() {
        return getFrames().getThreadFactory();
    }
    public AbstractInterceptor getInterceptor() {
        return getFrames().getInterceptor();
    }
    public TechStreams getStreams() {
        return getFrames().getStreams();
    }

    public AbstractFileCoreStream getFileCoreStream() {
        return getFrames().getFileCoreStream();
    }

    public AbstractNameValidating getValidator() {
        return getFrames().getValidator();
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
