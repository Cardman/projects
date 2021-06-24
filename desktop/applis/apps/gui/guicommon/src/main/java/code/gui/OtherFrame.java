package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

public final class OtherFrame implements ChangeableTitle,WithListener {

    private AbstractImage imageIconFrame;

    private boolean mainFrame;

    private Ownable owner;
    private final JFrame frame = new JFrame();

    public void setMainFrame(boolean _mainFrame) {
        mainFrame = _mainFrame;
    }

    @Override
    public Point getLocationOnScreen() {
        return frame.getLocationOnScreen();
    }
    @Override
    public String getTitle() {
        return frame.getTitle();
    }

    @Override
    public void setTitle(String _title) {
        frame.setTitle(_title);
    }
    public void dispose() {
        setVisible(false);
    }

    public boolean isMainFrame() {
        return mainFrame;
    }

    public void setImageIconFrame(AbstractImageFactory _fact, AbstractImage _imageIconFrame) {
        imageIconFrame = _imageIconFrame;
        setIconImage(_fact,imageIconFrame);
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return imageIconFrame;
    }

    public void setFocusableWindowState(boolean _focusableWindowState) {
        frame.setFocusableWindowState(_focusableWindowState);
    }

    public void setFocusable(boolean _focusable) {
        frame.setFocusable(_focusable);
    }
    public void revalidateFrame() {
        PackingWindowAfter.packg(this);
    }

    void setIconImage(AbstractImageFactory _fact, AbstractImage _group) {
        frame.setIconImage(((ImageIcon)PreparedLabel.buildIcon(_fact,_group)).getImage());
    }

    public void addWindowListener(WindowListener _l) {
        frame.addWindowListener(_l);
    }

    @Override
    public void removeWindowListener(WindowListener _l) {
        frame.removeWindowListener(_l);
    }

    @Override
    public WindowListener[] getWindowListeners() {
        return frame.getWindowListeners();
    }

    @Override
    public void setDefaultCloseOperation(int _option) {
        frame.setDefaultCloseOperation(_option);
    }

    @Override
    public void setVisible(boolean _v) {
        frame.setVisible(_v);
    }
    public void setJMenuBar(MenuBar _menu) {
        frame.setJMenuBar(_menu.getMenuBar());
    }
    @Override
    public void setContentPane(Panel _p) {
        frame.setContentPane(_p.getComponent());
    }

    @Override
    public void setLocationRelativeTo(CustComponent _c) {
        frame.setLocationRelativeTo(_c.getComponent());
    }

    @Override
    public void setLocationRelativeTo(OtherDialog _c) {
        frame.setLocationRelativeTo(_c.getComponent());
    }

    @Override
    public void setLocationRelativeTo(OtherFrame _c) {
        frame.setLocationRelativeTo(_c.getComponent());
    }

    @Override
    public void setLocationRelativeToNull() {
        frame.setLocationRelativeTo(null);
    }

    @Override
    public boolean isVisible() {
        return frame.isVisible();
    }

    @Override
    public void pack() {
        frame.pack();
    }

    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    Window getComponent() {
        return frame;
    }

    public void requestFocus() {
        frame.requestFocus();
    }

    public Point getLocation() {
        return frame.getLocation();
    }

    public void setLocation(int _x, int _y) {
        frame.setLocation(_x, _y);
    }

    public void setLocation(Point _p) {
        frame.setLocation(_p);
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }
}
