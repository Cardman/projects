package code.mock;

import code.gui.*;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.util.CustList;
import code.util.IdList;

public class MockWindow implements WithListener, PlacableWindow{
    private final IdList<AbsWindowListenerClosing> windowClosListeners = new IdList<AbsWindowListenerClosing>();
    private final IdList<AbsWindowListener> windowListeners = new IdList<AbsWindowListener>();
    private String title;
    private AbsPanel pane;
    private String accessFile;
    private String languageKey;
    private boolean visible;
    private boolean mainFrame;
    private AbsMenuBar menu;
    private AbstractImage imageIconFrame;
    private int locationFirst;
    private int locationSecond;

    private MetaPoint locationOnScreen = new MetaPoint(0,0);

    public MockWindow() {
        pane = new MockPanel(MockLayout.LINE);
        menu = new MockMenuBar();
    }
    public void pack() {
        recalculate((MockCustComponent) pane);
    }
    public static void recalculate(MockCustComponent _compo) {
        _compo.setSize(_compo.getPreferredSizeValue());
        MockCustComponent curr_ = _compo;
        while(curr_ != null) {
            curr_.recalculate();
            if (curr_ instanceof AbsScrollPane) {
                ((AbsScrollPane)curr_).recalculateViewport();
            }

            AbsCustComponent child_ = childAt(curr_, 0);
            if (child_ instanceof MockCustComponent) {
                curr_ = (MockCustComponent) child_;
            } else {
                while(curr_ != null) {
                    MockCustComponent par_ = parentOrNull(curr_.getParent());
                    int index_ = indexOf(par_, curr_);
                    AbsCustComponent next_ = childAt(par_, index_ + 1);
                    if (next_ instanceof MockCustComponent) {
                        curr_ = (MockCustComponent) next_;
                        break;
                    }

                    curr_ = parent(par_,_compo);
                }
            }
        }
    }

    private static MockCustComponent parentOrNull(AbsCustComponent _par) {
        if (_par instanceof MockCustComponent) {
            return (MockCustComponent) _par;
        }
        return null;
    }
    private static MockCustComponent parent(MockCustComponent _par, MockCustComponent _compo) {
        if (_par == _compo) {
            return null;
        }
        return _par;
    }

    private static AbsCustComponent childAt(MockCustComponent _elt, int _index) {
        if (_elt == null) {
            return null;
        }
        CustList<AbsCustComponent> children_ = _elt.getChildren();
        if (!children_.isValidIndex(_index)) {
            return null;
        }
        return children_.get(_index);
    }

    private static int indexOf(MockCustComponent _par, MockCustComponent _elt) {
        if ( _par == null) {
            return -1;
        }
        return indexOf(_par.getChildren(), _elt);
    }

    public static int indexOf(CustList<AbsCustComponent> _list, MockCustComponent _elt) {
        int len_ = _list.size();
        for(int i = 0; i < len_; i++) {
            AbsCustComponent c = _list.get(i);
            if (c == _elt) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void setLocationRelativeTo(AbsCustComponent _c) {
        setVisible(isVisible());
    }

    @Override
    public void setLocationRelativeTo(AbsOtherDialog _c) {
        setVisible(isVisible());
    }

    @Override
    public void setLocationRelativeTo(AbsOtherFrame _f) {
        setVisible(isVisible());
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
        AbsPanel p_ = new MockPanel(MockLayout.LINE);
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

    public void setLocationRelativeToWindow(Iconifiable _a) {
        if (_a instanceof AbsCommonFrame) {
            setLocationRelativeTo((AbsCommonFrame)_a);
        } else {
            setLocationRelativeToNull();
        }
    }

    public void setLocationRelativeTo(MockCustComponent _c) {
        locationFirst = _c.getXcoords()+_c.getWidth()/2;
        locationSecond = _c.getYcoords()+_c.getHeight()/2;
    }

    public void setLocationRelativeTo(AbsCommonFrame _f) {
        locationFirst = _f.getLocationOnScreen().getXcoord()+_f.getWidth()/2;
        locationSecond = _f.getLocationOnScreen().getYcoord()+_f.getHeight()/2;
    }

    public void setLocationRelativeToNull() {
        locationFirst = 512;
        locationSecond = 64;
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

    public AbsPanel getContentPane() {
        return getPane();
    }

    public void setIconImage(AbstractImage _a) {
        setImageIconFrame(_a);
    }
}
