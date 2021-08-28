package code.gui;

import code.gui.events.AbsWindowListener;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

import java.awt.Point;

public abstract class ChildFrame implements AbsChildFrame {

    private final AbsCommonFrame absCommonFrame;

    protected ChildFrame(String _lg, AbsGroupFrame _group) {
        AbstractProgramInfos frames_ = _group.getCommonFrame().getFrames();
        absCommonFrame = frames_.getFrameFactory().newCommonFrame(_lg, frames_, null);
    }

    //@Override
    public AbstractProgramInfos getFrames() {
        return absCommonFrame.getFrames();
    }

    //@Override
    public String getLanguageKey() {
        return absCommonFrame.getLanguageKey();
    }

    //@Override
    public void setLanguageKey(String _language) {
        absCommonFrame.setLanguageKey(_language);
    }

    //@Override
    public AbstractImage getImageIconFrame() {
        return absCommonFrame.getImageIconFrame();
    }

    //@Override
    public void setAccessFile(String _accessFile) {
        absCommonFrame.setAccessFile(_accessFile);
    }

    //@Override
    public int getWidth() {
        return absCommonFrame.getWidth();
    }

    //@Override
    public void setFocusableWindowState(boolean _focusableWindowState) {
        absCommonFrame.setFocusableWindowState(_focusableWindowState);
    }

    //@Override
    public void setContentPane(AbsPanel _contentPane) {
        absCommonFrame.setContentPane(_contentPane);
    }

    //@Override
    public void setContentPane(AbsScrollPane _contentPane) {
        absCommonFrame.setContentPane(_contentPane);
    }

    //@Override
    public void setDefaultCloseOperation(int _operation) {
        absCommonFrame.setDefaultCloseOperation(_operation);
    }

    //@Override
    public void setFocusable(boolean _focusable) {
        absCommonFrame.setFocusable(_focusable);
    }

    //@Override
    public void setIconImage(AbstractImage _image) {
        absCommonFrame.setIconImage(_image);
    }

    //@Override
    public void setImageIconFrame(AbstractImage _imageIconFrame) {
        absCommonFrame.setIconImage(_imageIconFrame);
    }

    //@Override
    public void setJMenuBar(AbsMenuBar _menu) {
        absCommonFrame.setJMenuBar(_menu);
    }

    //@Override
    public void setLocation(int _x, int _y) {
        absCommonFrame.setLocation(_x, _y);
    }

    //@Override
    public void setLocationRelativeTo(AbsCustComponent _c) {
        absCommonFrame.setLocationRelativeTo(_c);
    }

    //@Override
    public AbsCommonFrame getCommonFrame() {
        return absCommonFrame;
    }

    //@Override
    public void setLocationRelativeTo(AbsGroupFrame _c) {
        absCommonFrame.setLocationRelativeTo(_c);
    }

    //@Override
    public void setLocationRelativeToNull() {
        absCommonFrame.setLocationRelativeToNull();
    }

    //@Override
    public void setVisible(boolean _b) {
        absCommonFrame.setVisible(_b);
    }

    //@Override
    public void setOwner(Ownable _owner) {
        absCommonFrame.setOwner(_owner);
    }

    //@Override
    public void setTitle(String _title) {
        absCommonFrame.setTitle(_title);
    }

    //@Override
    public CustList<AbsWindowListener> getWindowListeners() {
        return absCommonFrame.getWindowListeners();
    }

    //@Override
    public AbsPanel getPane() {
        return absCommonFrame.getPane();
    }

    //@Override
    public Point getLocation() {
        return absCommonFrame.getLocation();
    }

    //@Override
    public AbsMenuBar getJMenuBar() {
        return absCommonFrame.getJMenuBar();
    }

    //@Override
    public int getHeight() {
        return absCommonFrame.getHeight();
    }

    //@Override
    public String getAccessFile() {
        return absCommonFrame.getAccessFile();
    }

    //@Override
    public Ownable getOwner() {
        return absCommonFrame.getOwner();
    }

    //@Override
    public Point getLocationOnScreen() {
        return absCommonFrame.getLocationOnScreen();
    }

    //@Override
    public String getTitle() {
        return absCommonFrame.getTitle();
    }

    //@Override
    public boolean isVisible() {
        return absCommonFrame.isVisible();
    }

    //@Override
    public void removeWindowListener(AbsWindowListener _l) {
        absCommonFrame.removeWindowListener(_l);
    }

    //@Override
    public void addWindowListener(AbsWindowListener _l) {
        absCommonFrame.addWindowListener(_l);
    }

    //@Override
    public void dispose() {
        absCommonFrame.dispose();
    }

    //@Override
    public void dispatchExit() {
        absCommonFrame.dispatchExit();
    }

    //@Override
    public void requestFocus() {
        absCommonFrame.requestFocus();
    }

    //@Override
    public void pack() {
        absCommonFrame.pack();
    }

    public void setDialogIcon(AbsGroupFrame _group) {
        setIconImage(_group.getImageIconFrame());
        setImageIconFrame(_group.getImageIconFrame());
    }

    public abstract void closeWindow();

    public AbsCommonFrame getAbsCommonFrame() {
        return absCommonFrame;
    }
}
