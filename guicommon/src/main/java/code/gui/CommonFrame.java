package code.gui;
import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import code.sml.stream.ExtractFromFiles;
import code.util.StringMap;

public abstract class CommonFrame implements ChangeableTitle {

    private String accessFile;

    private Panel contentPane = new Panel();

    private Ownable owner;
    private JFrame frame = new JFrame();
    private MenuBar menuBar;
    private String languageKey;
    protected CommonFrame(String _languageKey) {
        languageKey = _languageKey;
    }
    public String getLanguageKey() {
        return languageKey;
    }
    public void setLanguageKey(String _language) {
        languageKey = _language;
    }
    public void dispose() {
        frame.dispose();
    }

    public void setSize(Dimension _d) {
        frame.setSize(_d);
    }

    public void setSize(int _width, int _height) {
        frame.setSize(_width, _height);
    }

    public void setLocationRelativeTo(CommonFrame _c) {
        frame.setLocationRelativeTo(_c.getFrame());
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

    public Dimension getSize() {
        return frame.getSize();
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    public Dimension getSize(Dimension _rv) {
        return frame.getSize(_rv);
    }

    public Point getLocation(Point _rv) {
        return frame.getLocation(_rv);
    }

    @Override
    public void pack() {
        frame.pack();
        contentPane.repaint();
    }

    public void setFocusableWindowState(boolean _focusableWindowState) {
        frame.setFocusableWindowState(_focusableWindowState);
    }

    public void setFocusable(boolean _focusable) {
        frame.setFocusable(_focusable);
    }

    public void addWindowListener(WindowListener _l) {
        frame.addWindowListener(_l);
    }

    public void setDefaultCloseOperation(int _operation) {
        frame.setDefaultCloseOperation(_operation);
    }

    @Override
    public Window getComponent() {
        return frame;
    }
    public void revalidateFrame() {
        PackingWindowAfter.pack(this);
    }

    public void setIconImage(Image _image) {
        frame.setIconImage(_image);
    }

    protected StringMap<String> getMessages(CommonFrame _c,String _messageFolder) {
        String lg_ = _c.getLanguageKey();
        return ExtractFromFiles.getMessagesFromLocaleClass(_messageFolder, lg_, accessFile);
    }

    protected void setAccessFile(String _accessFile) {
        accessFile = _accessFile;
    }
    
    public void setContentPane(Panel _contentPane) {
        frame.setContentPane(_contentPane.getComponent());
        contentPane = _contentPane;
    }

    public void setContentPane(ScrollPane _contentPane) {
        Panel p_ = new Panel();
        p_.add(_contentPane);
        setContentPane(p_);
    }

    public Panel getPane() {
        return contentPane;
    }

    protected MenuBar getJMenuBar() {
        return menuBar;
    }
    public void setJMenuBar(MenuBar _menu) {
        frame.setJMenuBar(_menu.getMenuBar());
        menuBar = _menu;
    }
    JFrame getFrame() {
        return frame;
    }
    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    public void setVisible(boolean _b) {
        frame.setVisible(_b);
    }

    @Override
    public boolean isVisible() {
        return frame.isVisible();
    }

    @Override
    public String getTitle() {
        return frame.getTitle();
    }

    @Override
    public void setTitle(String _title) {
        frame.setTitle(_title);
    }

    public BufferedImage printAll() {
        int w_ = getWidth();
        int h_ = getHeight();
        BufferedImage b_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = b_.createGraphics();
        frame.printAll(g_);
        return b_;
    }
}
