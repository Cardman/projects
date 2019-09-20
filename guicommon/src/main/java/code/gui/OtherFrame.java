package code.gui;

import code.util.CustList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

public final class OtherFrame extends AbsFrame implements ChangeableTitle,WithListener {

    private static final CustList<OtherFrame> FRAMES = new CustList<OtherFrame>();

    private BufferedImage imageIconFrame;

    private boolean opened;

    private Ownable owner;
    private JFrame frame = new JFrame();

    public OtherFrame() {
        SetStyle.setupStyle(this);
        FRAMES.add(this);
    }

    protected static OtherFrame getFrame(int _i) {
        return FRAMES.get(_i);
    }

    protected static int getFrameCount() {
        return FRAMES.size();
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
        if(!FRAMES.first().opened) {
            exit();
        }
    }
    protected void exit() {
        ThreadUtil.exit();
    }

    public void setImageIconFrame(BufferedImage _imageIconFrame) {
        imageIconFrame = _imageIconFrame;
        setIconImage(imageIconFrame);
    }

    @Override
    public BufferedImage getImageIconFrame() {
        return imageIconFrame;
    }

    public void setFocusableWindowState(boolean _focusableWindowState) {
        frame.setFocusableWindowState(_focusableWindowState);
    }

    public void setFocusable(boolean _focusable) {
        frame.setFocusable(_focusable);
    }
    public void revalidateFrame() {
        PackingWindowAfter.pack(this);
    }

    protected void setIconImage(BufferedImage _image) {
        frame.setIconImage(_image);
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
        opened = _v;
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
        if (_c != null) {
            frame.setLocationRelativeTo(_c.getComponent());
        } else {
            frame.setLocationRelativeTo(null);
        }
    }

    @Override
    public void setLocationRelativeTo(WithListener _c) {
        if (_c instanceof CommonFrame) {
            frame.setLocationRelativeTo(((CommonFrame)_c).getComponent());
        } else if (_c instanceof OtherDialog) {
            frame.setLocationRelativeTo(((OtherDialog)_c).getComponent());
        } else {
            frame.setLocationRelativeTo(null);
        }
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

    @Override
    protected Window getComponent() {
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
