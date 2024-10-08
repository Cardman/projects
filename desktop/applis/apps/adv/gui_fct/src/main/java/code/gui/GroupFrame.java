package code.gui;

import code.expressionlanguage.filenames.AbstractNameValidating;
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



public abstract class GroupFrame implements AbsGroupFrame {

    private final AbsCommonFrame commonFrame;
    private final AbstractProgramInfos abstractProgramInfos;

//    private final SetterLanguage languageDialog;

    protected GroupFrame(AbstractProgramInfos _list) {
        commonFrame = _list.getFrameFactory().newCommonFrame();
        abstractProgramInfos = _list;
//        languageDialog = _list.getSetterLanguage();
    }

    public void setImageIconFrame(AbstractImage _imageIconFrame) {
        commonFrame.setImageIconFrame(_imageIconFrame);
        setIconImage(_imageIconFrame);
    }
//    public void exitMode(AbstractProgramInfos _list) {
//        if (_list.getFrames().first() == this) {
//            setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
////        } else {
////            setDefaultCloseOperation(GuiConstants.HIDE_ON_CLOSE);
//        }
//    }

    //@Override
    public AbstractProgramInfos getFrames() {
        return abstractProgramInfos;
    }

    //@Override
//    public String getLanguageKey() {
//        return commonFrame.getLanguageKey();
//    }

    //@Override
    public void setLanguageKey(String _language) {
//        commonFrame.setLanguageKey(_language);
        getFrames().setLanguage(_language);
    }

    //@Override
//    public void setAccessFile(String _accessFile) {
//        commonFrame.setAccessFile(_accessFile);
//    }

    //@Override
    public void setFocusableWindowState(boolean _focusableWindowState) {
        commonFrame.setFocusableWindowState(_focusableWindowState);
    }

    //@Override
    public void setContentPane(AbsPanel _contentPane) {
        commonFrame.setContentPane(_contentPane);
    }

    //@Override
//    public void setDefaultCloseOperation(int _operation) {
//        commonFrame.setDefaultCloseOperation(_operation);
//    }

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
//    public String getAccessFile() {
//        return commonFrame.getAccessFile();
//    }

    public String getTitle() {
        return commonFrame.getTitle();
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

    public void pack() {
        commonFrame.pack();
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    //@Override
//    public void dispose() {
//        basicDispose();
//    }

//    public void basicDispose() {
//        //int index_ = CustList.FIRST_INDEX;
////        for (GroupFrame g: FRAMES) {
////            if (g == this) {
////                //super.dispose();
////                setVisible(false);
////                //FRAMES.remove(index_);
////                break;
////            }
////            //index_ ++;
////        }
//        setVisible(false);
////        int nbOpenedWindows_ = CustList.SIZE_EMPTY;
////        for (Window w: Window.getWindows()) {
////            if (w == null) {
////                continue;
////            }
////            if (w.isDisplayable()) {
////                nbOpenedWindows_ ++;
////            }
////        }
////        if(index_ == CustList.SIZE_EMPTY) {}
//        GuiBaseUtil.tryExit(this.getCommonFrame());
//        getFrames().getCounts().getVal(getApplicationName()).decrementAndGet();
//    }

    public void nativeExit() {
        GuiBaseUtil.removeAllListeners(getCommonFrame());
    }

    //@Override
    public void setVisible(boolean _b) {
        commonFrame.setVisible(_b);
    }

//    public boolean canChangeLanguageAll() {
//        return false;
////        boolean canChange_ = true;
////        for (GroupFrame g: frames.getFrames()) {
////            if (!g.canChangeLanguage()) {
////                canChange_ = false;
////                break;
////            }
////        }
////        return canChange_;
//    }

    public AbstractGenerator getGenerator() {
        return getFrames().getGenerator();
    }
    public void revalidateFrame() {
        PackingWindowAfter.pack(this.getCommonFrame(), getFrames().getCompoFactory());
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

    public TechStreams getStreams() {
        return getFrames().getStreams();
    }

    public AbstractFileCoreStream getFileCoreStream() {
        return getFrames().getFileCoreStream();
    }

    public AbstractNameValidating getValidator() {
        return getFrames().getValidator();
    }

//    public SetterLanguage getLanguageDialog() {
//        return languageDialog;
//    }

}
