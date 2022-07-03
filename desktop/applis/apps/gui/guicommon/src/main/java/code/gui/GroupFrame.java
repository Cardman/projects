package code.gui;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaPoint;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.AbstractFileCoreStream;
import code.stream.core.TechStreams;
import code.threads.AbstractThreadFactory;
import code.util.StringMap;



public abstract class GroupFrame implements AbsGroupFrame,WithDialogs {

    private final AbsCommonFrame commonFrame;
    private StringMap<String> messages;

    private boolean opened;

    private FileOpenDialog fileOpenDialog;
    private FileSaveDialog fileSaveDialog;
    private FolderOpenDialog folderOpenDialog;
    private ConfirmDialog confirmDialog;
    private LanguageDialog languageDialog;
    private ConfirmDialogTextAbs confirmDialogText;
    private ConfirmDialogAnsAbs confirmDialogAns;
    private FolderOpenDialogAbs folderOpenDialogInt;
    private FileOpenDialogAbs fileOpenDialogInt;
    private FileSaveDialogAbs fileSaveDialogInt;

    protected GroupFrame(String _lg, AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        choose(_lg, _list);
    }

    private void choose(String _lg, AbstractProgramInfos _list) {
        FrameUtil.choose(_lg, _list, this);
    }

    public final void init(AbstractProgramInfos _list) {
        confirmDialogText = _list.getConfirmDialogText();
        confirmDialogAns = _list.getConfirmDialogAns();
        folderOpenDialogInt = _list.getFolderOpenDialogInt();
        fileOpenDialogInt = _list.getFileOpenDialogInt();
        fileSaveDialogInt = _list.getFileSaveDialogInt();
        fileOpenDialog = new FileOpenDialog(_list.getThreadFactory().newAtomicBoolean(), _list.getThreadFactory().newAtomicBoolean(), _list);
        fileSaveDialog = new FileSaveDialog(_list);
        folderOpenDialog = new FolderOpenDialog(_list);
        confirmDialog = new ConfirmDialog(_list);
        languageDialog = new LanguageDialog(_list);
    }

    public final void setByFirst(AbsGroupFrame _first) {
        confirmDialogText = ((WithDialogs)_first).getConfirmDialogText();
        confirmDialogAns = ((WithDialogs)_first).getConfirmDialogAns();
        folderOpenDialogInt = ((WithDialogs)_first).getFolderOpenDialogInt();
        fileOpenDialogInt = ((WithDialogs)_first).getFileOpenDialogInt();
        fileSaveDialogInt = ((WithDialogs)_first).getFileSaveDialogInt();
        fileOpenDialog = ((WithDialogs)_first).getFileOpenDialog();
        fileSaveDialog = ((WithDialogs)_first).getFileSaveDialog();
        folderOpenDialog = ((WithDialogs)_first).getFolderOpenDialog();
        confirmDialog = ((WithDialogs)_first).getConfirmDialog();
        languageDialog = ((WithDialogs)_first).getLanguageDialog();
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
    public void setContentPane(AbsPanel _contentPane) {
        commonFrame.setContentPane(_contentPane);
    }

    //@Override
    public void setContentPane(AbsScrollPane _contentPane) {
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
    public void setJMenuBar(AbsMenuBar _menu) {
        commonFrame.setJMenuBar(_menu);
    }

    //@Override
    public void setLocation(int _x, int _y) {
        commonFrame.setLocation(_x, _y);
    }

    //@Override
    public void setLocationRelativeTo(AbsCommonFrame _c) {
        commonFrame.setLocationRelativeTo(_c);
    }

    //@Override
    public void setLocationRelativeTo(AbsCustComponent _c) {
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
    public AbsPanel getPane() {
        return commonFrame.getPane();
    }

    //@Override
    public MetaPoint getLocation() {
        return new MetaPoint(commonFrame.getLocationFirst(),commonFrame.getLocationSecond());
    }

    //@Override
    public AbsMenuBar getJMenuBar() {
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
    public MetaPoint getLocationOnScreen() {
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

    public void addWindowListener(AbsWindowListenerClosing _l) {
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

    @Override
    public FolderOpenDialogAbs getFolderOpenDialogInt() {
        return folderOpenDialogInt;
    }

    @Override
    public FileOpenDialogAbs getFileOpenDialogInt() {
        return fileOpenDialogInt;
    }

    @Override
    public FileSaveDialogAbs getFileSaveDialogInt() {
        return fileSaveDialogInt;
    }

    @Override
    public ConfirmDialogTextAbs getConfirmDialogText() {
        return confirmDialogText;
    }

    @Override
    public ConfirmDialogAnsAbs getConfirmDialogAns() {
        return confirmDialogAns;
    }
}
