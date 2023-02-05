package code.gui;

import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;


public final class FrameUtil {

    private FrameUtil() {
    }

    public static void setLocationRelativeToWin(Iconifiable _i, AbsDialog _to) {
        if (_i instanceof AbsGroupFrame) {
            _to.setLocationRelativeTo(((AbsGroupFrame) _i).getCommonFrame());
        } else if (_i instanceof AbsDialog) {
            _to.setLocationRelativeTo((AbsDialog) _i);
        }
    }

//    public static void repaint(AbstractImageFactory _fact, AbsPaintableLabel _paintableLabel, AbsMetaLabelInt _metaLabel) {
//        int w_ = _paintableLabel.getWidth();
//        int h_ = _paintableLabel.getHeight();
//        if (w_ <= 0 || h_ <= 0) {
//            _paintableLabel.setEmptyIcon();
//            return;
//        }
//        AbstractImage img_ = _fact.newImageArgb(w_, h_);
////        CustGraphics gr_ = img_.getGraphics();
//        img_.setFont(_paintableLabel);
//        _metaLabel.paintComponent(img_);
//        _paintableLabel.setIcon(_fact,img_);
//    }
//    public static void repaintNo(AbstractImageFactory _fact, AbsPaintableLabel _paintableLabel) {
//        int w_ = _paintableLabel.getWidth();
//        int h_ = _paintableLabel.getHeight();
//        if (w_ <= 0 || h_ <= 0) {
//            _paintableLabel.setEmptyIcon();
//            return;
//        }
//        AbstractImage img_ = _fact.newImageArgb(w_, h_);
////        CustGraphics gr_ = img_.getGraphics();
//        img_.setFont(_paintableLabel);
//        _paintableLabel.setIcon(_fact,img_);
//    }
    public static int pref(int _dim, int _pr) {
        if (_dim > 0) {
            return _dim;
        }
        return _pr;
    }

//    public static void repaintLists(AbstractImageFactory _fact, AbsPanel _panel) {
//        for (AbsCustComponent c: _panel.getChildren()) {
//            procCh(_fact, c);
//        }
//        _panel.validate();
//    }

//    private static void procCh(AbstractImageFactory _fact, AbsCustComponent _c) {
//        if (_c instanceof AbsPaintableLabel) {
//            ((AbsPaintableLabel) _c).repaintLabel(_fact);
//        } else if (_c instanceof AbsPanel) {
//            for (AbsCustComponent d: _c.getChildren()) {
//                proc(_fact, d);
//            }
//            _c.validate();
//        }
//    }

//    public static void repaintList(AbstractImageFactory _fact, AbsPanel _panel) {
//        for (AbsCustComponent c: _panel.getChildren()) {
//            proc(_fact, c);
//        }
//        _panel.validate();
//    }

//    private static void proc(AbstractImageFactory _fact, AbsCustComponent _c) {
//        if (_c instanceof AbsPaintableLabel) {
//            ((AbsPaintableLabel) _c).repaintLabel(_fact);
//        }
//    }

    public static void removeListeners(AbsGraphicListDef _compo) {
        for (ListSelection l: _compo.getListeners()) {
            _compo.removeListener(l);
        }
    }

    public static void removeListeners(GraphicComboInt _compo) {
        for (ListSelection l: _compo.getListeners()) {
            _compo.removeListener(l);
        }
    }

    public static void remAllFromPanel(CustList<AbsCustComponent> _children) {
        for (AbsCustComponent c: _children) {
            c.setParent(null);
        }
    }

    public static int removeOne(AbsPanel _panel, AbsCustComponent _cust) {
        int i_ = 0;
        int index_ = -1;
        CustList<AbsCustComponent> rem_ = new CustList<AbsCustComponent>();
        for (AbsCustComponent c: _panel.getChildren()) {
            if (c == _cust) {
                c.setParent(null);
                index_ = i_;
            } else {
                rem_.add(c);
            }
            i_++;
        }
        _panel.innerRemoveAll();
        for (AbsCustComponent c: rem_) {
            _panel.innAdd(c);
        }
        return index_;
    }

    public static void adCts(AbsPanel _panel, AbsCustComponent _comp, String _constraints) {
        if (_comp.getParent() != null) {
            return;
        }
        _panel.innerAdd(_comp, _constraints);
    }

    public static void addIndex(AbsPanel _panel, AbsCustComponent _comp, int _index) {
        if (_comp.getParent() != null) {
            return;
        }
        _panel.innerAdd(_comp, _index);
    }

    public static void addOne(AbsPanel _panel, AbsCustComponent _comp) {
        if (_comp.getParent() != null) {
            return;
        }
        _panel.innerAdd(_comp);
    }

    public static void removeChild(CustList<AbsCustComponent> _children) {
        if (!_children.isEmpty()) {
            _children.first().setParent(null);
            _children.clear();
        }
    }

    public static void selectedIndex(int _index, AbsTabbedPane _tabbedPane) {
        if (!_tabbedPane.getChildren().isValidIndex(_index)) {
            return;
        }
        _tabbedPane.selectIndex(_index);
    }

    public static void added(String _title, AbsCustComponent _component, AbsTabbedPane _tabbedPane) {
        if (_component.getParent() != null) {
            return;
        }
        _tabbedPane.addIntTab(_title, _component);
    }

    public static boolean setTab(int _index, AbsCustComponent _component, AbsTabbedPane _curr) {
        if (_component.getParent() != null) {
            return false;
        }
        _curr.setTabComponentAt(_index, _component);
        return true;
    }

    public static void left(AbsCustComponent _scroll, AbsSplitPane _curr) {
        if (_scroll.getParent() != null) {
            return;
        }
        _curr.innerLeft(_scroll);
    }

    public static void right(AbsCustComponent _scroll, AbsSplitPane _curr) {
        if (_scroll.getParent() != null) {
            return;
        }
        _curr.innerRight(_scroll);
    }

    public static void ins(AbsTextArea _curr, String _str, int _pos) {
        if (_pos < 0) {
            return;
        }
        _curr.forceInsert(_str, _pos);
    }

    public static void replRange(AbsTextArea _curr, String _str, int _start, int _end) {
        if (_start < 0) {
            return;
        }
        if (_end < _start) {
            return;
        }
        _curr.forceReplaceRange(_str, _start, _end);
    }

    public static void feed(AbsGraphicList<String> _gr, StringList _list) {
        for (String l: _list) {
            _gr.add(l);
        }
    }

    public static void feed(GraphicComboInt _gr, StringList _list) {
        for (String l: _list) {
            _gr.addItem(l);
        }
    }
    public static Ints toList(int[] _ints) {
        Ints ints_ = new Ints();
        for (int i: _ints) {
            ints_.add(i);
        }
        return ints_;
    }
    public static int[] toList(Ints _ints) {
        int size_ = _ints.size();
        int[] ints_ = new int[size_];
        for (int i = 0; i < size_; i++) {
            set(_ints, ints_, i);
        }
        return ints_;
    }

    private static void set(Ints _ints, int[] _dest, int _i) {
        _dest[_i] = _ints.get(_i);
    }

    public static void paintLabel(int _render, AbstractImage _g, AbsPreparedLabel _label, String _text, boolean _selected, AbsCompoFactory _compo) {
        int h_ = _label.heightFont();
        int w_ = _compo.stringWidth(_label.getMetaFont(),_text);
        if (_selected) {
            LabelButtonUtil.paintDefaultLabel(_g, _text, w_, _render, h_, GuiConstants.WHITE, GuiConstants.BLUE);
        } else {
            LabelButtonUtil.paintDefaultLabel(_g, _text, w_, _render, h_, GuiConstants.BLACK, GuiConstants.WHITE);
        }
    }
    public static void act(ListSelection _list,SelectionInfo _e, boolean _skip) {
        if (_skip) {
            return;
        }
        _list.valueChanged(_e);
    }
    public static void act(ListSelection _list,SelectionInfo _e, int _state, int _value) {
        if (_state == _value) {
            _list.valueChanged(_e);
        }
    }
}
