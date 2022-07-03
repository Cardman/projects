package code.mock;

import code.gui.*;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.IdList;

public abstract class MockWindow implements WithListener{
    private final IdList<AbsWindowListenerClosing> windowClosListeners = new IdList<AbsWindowListenerClosing>();
    private final IdList<AbsWindowListener> windowListeners = new IdList<AbsWindowListener>();
    private String title;
    private AbsPanel pane;
    private String accessFile;
    private String languageKey;
    private boolean visible;
    private boolean mainFrame;
    private AbsMenuBar menu;
    private final AbstractProgramInfos frames;
    private AbstractImage imageIconFrame;
    private Ownable owner;
    private int locationFirst;
    private int locationSecond;

    private MetaPoint locationOnScreen = new MetaPoint(0,0);

    protected MockWindow(AbstractProgramInfos _f) {
        this.frames = _f;
        pane = _f.getCompoFactory().newLineBox();
        menu = _f.getCompoFactory().newMenuBar();
    }

    public void addWindowListener(AbsWindowListener _l) {
        windowListeners.add(_l);
    }

    public void removeWindowListener(AbsWindowListener _l) {
        windowListeners.removeObj(_l);
    }

    public CustList<AbsWindowListener> getWindowListeners() {
        return new CustList<AbsWindowListener>(windowListeners);
    }

    public AbsWindowListenerClosing addWindowListener(AbsWindowListenerClosing _l) {
        windowClosListeners.add(_l);
        return _l;
    }

    public AbsWindowListenerClosing removeWindowListener(AbsWindowListenerClosing _l) {
        windowClosListeners.removeObj(_l);
        return _l;
    }
    public CustList<AbsWindowListenerClosing> getWindowListenersDef() {
        return new CustList<AbsWindowListenerClosing>(windowClosListeners);
    }

    public AbstractProgramInfos getFrames() {
        return frames;
    }

    public String getLanguageKey() {
        return languageKey;
    }

    public void setLanguageKey(String _s) {
        languageKey = _s;
    }

    public void setImageIconFrame(AbstractImage _i) {
        imageIconFrame = _i;
    }

    public void dispose() {
        setVisible(false);
    }

    public void requestFocus() {
        setVisible(isVisible());
    }

    public int getLocationFirst() {
        return locationFirst;
    }

    public int getLocationSecond() {
        return locationSecond;
    }

    public void setLocation(int _x, int _y) {
        locationFirst = _x;
        locationSecond = _y;
    }

    public int getWidth() {
        return pane.getWidth();
    }

    public int getHeight() {
        return pane.getHeight();
    }

    public String getAccessFile() {
        return accessFile;
    }

    public void setAccessFile(String _s) {
        accessFile = _s;
    }

    public void setContentPane(AbsPanel _p) {
        pane = _p;
    }

    public void setContentPane(AbsScrollPane _contentPane) {
        AbsPanel p_ = frames.getCompoFactory().newLineBox();
        p_.add(_contentPane);
        setContentPane(p_);
    }

    public AbsPanel getPane() {
        return pane;
    }

    public AbsMenuBar getJMenuBar() {
        return menu;
    }

    public void setJMenuBar(AbsMenuBar _m) {
        menu = _m;
    }

    public boolean isMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(boolean _b) {
        mainFrame = _b;
    }

    public void setVisible(boolean _b) {
        visible = _b;
    }

    public void setLocationRelativeTo(AbsDialog _a) {
        locationFirst = _a.getLocationOnScreen().getXcoord()+((MockAbsDialog)_a).getWidth()/2;
        locationSecond = _a.getLocationOnScreen().getYcoord()+((MockAbsDialog)_a).getHeight()/2;
    }

    public void setLocationRelativeToWindow(Iconifiable _a) {
        if (_a instanceof MockAbsDialog) {
            setLocationRelativeTo((MockAbsDialog)_a);
        } else if (_a instanceof AbsCommonFrame) {
            setLocationRelativeTo((AbsCommonFrame)_a);
        } else {
            setLocationRelativeToNull();
        }
    }

    public void setLocationRelativeTo(AbsCustComponent _c) {
        locationFirst = _c.getXcoords()+_c.getWidth()/2;
        locationSecond = _c.getYcoords()+_c.getHeight()/2;
    }

    public void setLocationRelativeTo(AbsCommonFrame _f) {
        locationFirst = _f.getLocationOnScreen().getXcoord()+_f.getWidth()/2;
        locationSecond = _f.getLocationOnScreen().getYcoord()+_f.getHeight()/2;
    }

    public void setLocationRelativeToNull() {
        locationFirst = frames.getScreenWidth()/2;
        locationSecond = frames.getScreenHeight()/2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String _s) {
        title = _s;
    }

    public MetaPoint getLocationOnScreen() {
        return locationOnScreen;
    }

    public void setLocationOnScreen(MetaPoint _l) {
        this.locationOnScreen = _l;
    }

    public AbstractImage getImageIconFrame() {
        return imageIconFrame;
    }

    public boolean isVisible() {
        return visible;
    }

    public Ownable getOwner() {
        return owner;
    }

    public void setOwner(Ownable _o) {
        owner = _o;
    }

    public AbsPanel getContentPane() {
        return getPane();
    }

    public void setDefaultCloseOperation(int _i) {
        setVisible(isVisible());
    }

    public void setIconImage(AbstractImage _a) {
        setImageIconFrame(_a);
    }
}
