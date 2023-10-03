package code.gui;


import code.expressionlanguage.structs.Struct;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.Ints;
import code.util.core.NumberUtil;

public final class SelectionUtil {
    private SelectionUtil(){
    }
    public static void selectEvent(int _firstIndex, int _lastIndex, GraphicComboGrInt _origin, ListSelection _list, boolean _methodAction, int _old) {
        if (_list != null && _old != _firstIndex) {
            int min_ = NumberUtil.min(_firstIndex, _lastIndex);
            int max_ = NumberUtil.max(_firstIndex, _lastIndex);
            SelectionInfo ev_ = new SelectionInfo(min_, max_, _methodAction);
            _list.valueChanged(ev_);
        }
        tryUp(_origin);
    }

    public static void tryUp(GraphicComboGrInt _combo) {
        if (_combo instanceof GraphicComboGrIntBase) {
            ((GraphicComboGrIntBase) _combo).update();
        }
    }

    public static void invokeLater(Runnable _r, AbsCompoFactory _compoFactory) {
        _compoFactory.invokeLater(_r);
    }

    public static Ints selectedIndexes(int _selectedIndex) {
        if (_selectedIndex == -1) {
            return new Ints();
        }
        return Ints.newList(_selectedIndex);
    }

    public static void addIt(String _object, AbsGraphicCombo _curr) {
        _curr.getGrList().add(_object);
        if (_curr.getGrList().getList().size() == 1) {
            _curr.setSelectedIndex(0);
            int w_ = _curr.getGrList().getMaxWidth();
            int s_ = _curr.getCompoFactory().heightFont(_curr.getPanel().getMetaFont()) + 2;
            AbstractImage img_ = _curr.getFact().newImageRgb(w_, s_);
//            CustGraphics gr_ = new CustGraphics(img_.createGraphics());
            img_.setFont(_curr.getLabel());
            img_.setColor(GuiConstants.WHITE);
            img_.fillRect(0, 0, w_, s_);
            img_.setColor(GuiConstants.BLACK);
            img_.drawString(_object, 0, s_ - 1);
            _curr.getLabel().setIcon(_curr.getFact(),img_);
        }
    }

    public static MetaDimension dimension(AbsGraphicListCommon _curr) {
        int width_ = _curr.getMaxWidth();
        width_ = getBasicMaxWidth(width_, _curr);
        int h_ = 0;
        int c_ = 0;
        for (AbsPreparedLabel c: _curr.getListComponents()) {
            h_ = c.getPreferredSizeValue().getHeight();
            c.setPreferredSize(new MetaDimension(width_, h_));
            c_++;
        }
        return new MetaDimension(width_ + 24, (h_ + 2) * NumberUtil.min(c_, _curr.getVisibleRowCount()));
    }

    public static void paintList(AbsCustCellRender _r, int _len, AbsGraphicListDefBase _curr) {
        int index_ = 0;
        for (int i = 0; i < _len; i++) {
            AbsPreparedLabel c_ = _curr.getListComponents().get(index_);
            _r.getListCellRendererComponent(c_, index_, false, false);
            _r.paintComponent(c_);
            index_++;
        }
    }

    public static void updatedSelected(boolean _sel, int _index, AbsGraphicListDef _curr) {
        if (!_sel) {
            _curr.getSelectedIndexes().removeObj(_index);
        } else {
            _curr.getSelectedIndexes().add(_index);
            _curr.getSelectedIndexes().removeDuplicates();
        }
    }

    public static int getBasicMaxWidth(int _width, AbsGraphicListDefBase _curr) {
        int width_ = _width;
        for (AbsPreparedLabel c: _curr.getListComponents()) {
            width_ = NumberUtil.max(width_, c.getPreferredSizeValue().getWidth());
        }
        return width_;
    }

    public static void removeIt(int _index, AbsGraphicCombo _curr) {
        if (!_curr.getGrList().getList().isValidIndex(_index)) {
            return;
        }
        int oldIndex_ = _curr.getSelectedIndex();
        _curr.getGrList().remove(_index);
        if (oldIndex_ == _index) {
            if (!_curr.getGrList().getList().isEmpty()) {
                updateLoc(_curr);
            } else {
                _curr.setSelectedIndex(-1);
                setNoSelected(_curr);
            }
        }
    }

    public static void updateLoc(AbsGraphicCombo _curr) {
        String selected_ = _curr.getSelectedItem();
        if (selected_ == null) {
            return;
        }
        int w_ = _curr.getGrList().getMaxWidth();
        int s_ = _curr.getCompoFactory().heightFont(_curr.getPanel().getMetaFont()) + 2;
        AbstractImage img_ = _curr.getFact().newImageRgb(w_, s_);
//        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        img_.setFont(_curr.getLabel());
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, w_, s_);
        img_.setColor(GuiConstants.BLACK);
        img_.drawString(selected_, 0, s_ - 1);
        _curr.getLabel().setIcon(_curr.getFact(),img_);
    }

    public static void simpleSelect(int _index, AbsGraphicCombo _curr) {
        if (_index < 0) {
            return;
        }
        _curr.setSelectedIndex(_index);
        _curr.getGrList().setFirstIndex(_index);
        _curr.getGrList().setLastIndex(_index);
        _curr.getGrList().addRange();
    }

    public static void pop(AbsGraphicCombo _curr) {
        if (!_curr.isEnabled()) {
            return;
        }
        int len_ = _curr.getGrList().getListComponents().size();
        for (int i = 0; i < len_; i++) {
            _curr.getGrList().repaintSelect(i,false);
        }
        _curr.showMenu();
    }

    public static CustList<ListSelection> listeners(ListSelection _listener) {
        if (_listener == null) {
            return new CustList<ListSelection>();
        }
        return new CustList<ListSelection>(_listener);
    }

    public static void addList(ListSelection _listener, WithListListener _curr) {
        if (_listener == null) {
            return;
        }
        _curr.simpleSetListener(_listener);
    }

    public static void removeList(ListSelection _listener, WithListListener _curr) {
        if (_curr.getListener() != _listener) {
            return;
        }
        _curr.simpleSetListener(null);
    }

    public static void remAllIts(AbsGraphicCombo _curr) {
        int len_ = _curr.getGrList().getList().size();
        for (int i = len_ - 1; i > -1; i--) {
            _curr.removeItem(i);
        }
    }

    public static String selIt(AbsGraphicCombo _curr) {
        CustList<String> list_ = _curr.getGrList().getList();
        if (!list_.isValidIndex(_curr.getSelectedIndex())) {
            return null;
        }
        return list_.get(_curr.getSelectedIndex());
    }

    public static void remSingleMult(int _index, AbsGraphicListCommon _curr) {
        if (!_curr.isSimple()) {
            _curr.getIndexableKey().remove(_index);
            _curr.getIndexableMouse().remove(_index);
            reindex(_curr.getIndexableMouse());
            reindex(_curr.getIndexableKey());
        } else {
            _curr.getIndexableMouse().remove(_index);
            reindex(_curr.getIndexableMouse());
        }
    }

    public static void reindex(CustList<IndexableListener> _list) {
        int index_ = 0;
        for (IndexableListener c: _list) {
            c.setIndex(index_);
            index_++;
        }
    }

    public static AbsCustCellRender fwd(AbsCustCellRender _r) {
        return _r.fwd();
    }

    public static int firstOrNeg(Ints _selectedIndexes) {
        if (_selectedIndexes.isEmpty()) {
            return -1;
        }
        return _selectedIndexes.first();
    }

    public static void selection(ListSelection _listener, AbsGraphicListCommon _curr) {
        for (IndexableListener i: _curr.getIndexableKey()) {
            i.setSelection(_listener);
        }
        for (IndexableListener i: _curr.getIndexableMouse()) {
            i.setSelection(_listener);
        }
    }

    public static void updatedSelectedBis(boolean _sel, int _min, int _max, AbsGraphicListDef _curr) {
        if (!_sel) {
            for (int i = _min; i <= _max; i++) {
                _curr.getSelectedIndexes().removeObj(i);
            }
        } else {
            for (int i = _min; i <= _max; i++) {
                _curr.getSelectedIndexes().add(i);
            }
            _curr.getSelectedIndexes().removeDuplicates();
        }
    }

    public static void paintSelected(boolean _sel, int _min, int _max, AbsCustCellRender _r, AbsGraphicListCommon _curr) {
        for (int i = _min; i <= _max; i++) {
            AbsPreparedLabel c_ = _curr.getListComponents().get(i);
            _r.getListCellRendererComponent(c_, i, _sel, false);
            _r.paintComponent(c_);
        }
        if (_sel) {
            _curr.addRange();
        } else {
            _curr.clearRange();
        }
    }

    public static Interval interval(boolean _sel, AbsGraphicListCommon _curr) {
        if (!_curr.isEnabled()) {
            return null;
        }
        AbsCustCellRender r_ = _curr.setted();
        int index_ = 0;
        int len_ = _curr.size();
        for (int i = 0; i < len_; i++) {
            AbsPreparedLabel c_ = _curr.getListComponents().get(index_);
            r_.getListCellRendererComponent(c_, index_, _sel, false);
            r_.paintComponent(c_);
            index_++;
        }
        if (!_sel) {
            _curr.setFirstIndex(0);
            _curr.setLastIndex(_curr.size());
            _curr.clearRange();
            _curr.setFirstIndex(-1);
            _curr.setLastIndex(-1);
        } else {
            _curr.setFirstIndex(0);
            _curr.setLastIndex(_curr.size()-1);
            _curr.addRange();
        }
        return new Interval(0,_curr.size());
    }

    public static boolean intervalPaint(boolean _sel, int _index, AbsGraphicListCommon _curr) {
        if (!_curr.isEnabled()) {
            return false;
        }
        _curr.setFirstIndex(_index);
        _curr.setLastIndex(_index);
        AbsCustCellRender r_ = _curr.setted();
        AbsPreparedLabel c_ = _curr.getListComponents().get(_index);
        r_.getListCellRendererComponent(c_, _index, _sel, false);
        c_.requestFocusInWindow();
        r_.paintComponent(c_);
        if (_sel) {
            _curr.addRange();
        } else {
            _curr.clearRange();
        }
        return true;
    }

    public static void selectedIndex(AbsCustCellRender _r, int _len, CustList<AbsPreparedLabel> _listComponents, Ints _selectedIndexes) {
        int index_ = 0;
        for (int i = 0; i < _len; i++) {
            AbsPreparedLabel c_ = _listComponents.get(index_);
            _r.getListCellRendererComponent(c_, index_, _selectedIndexes.containsObj(index_), false);
            _r.paintComponent(c_);
            index_++;
        }
    }

    public static void addSelect(int _min, Ints _selectedIndexes) {
        if (_min > -1) {
            _selectedIndexes.add(_min);
        }
    }

    public static void removeIndexes(int _min, int _max, Ints _selectedIndexes) {
        for (int i = _min; i <= _max; i++) {
            _selectedIndexes.removeObj(i);
        }
    }

    public static MetaDimension updateDim(AbsGraphicListCommon _curr) {
        int width_ = 0;
        for (AbsPreparedLabel c: _curr.getListComponents()) {
            width_ = NumberUtil.max(width_, c.getWidth());
        }
        int h_ = 0;
        int c_ = 0;
        for (AbsPreparedLabel c: _curr.getListComponents()) {
            h_ = NumberUtil.max(h_,c.getHeight());
            c_++;
        }
        return new MetaDimension(width_ + 24, (h_ + 2) * NumberUtil.min(c_, _curr.getVisibleRowCount()));
    }

    public static void addIndexes(int _min, int _max, Ints _selectedIndexes) {
        for (int i = _min; i <= _max; i++) {
            _selectedIndexes.add(i);
        }
    }

    public static void all(AbsCustCellRender _r, int _len, AbsGraphicListCommon _curr, AbsGraphicListPainter _graphicListPainter) {
        AbsPanel panel_ = _curr.getPanel();
        int index_ = 0;
        for (int i = 0; i < _len; i++) {
            AbsPreparedLabel lab_ = GuiBaseUtil.prep(_graphicListPainter.getFact());
            _curr.getListComponents().add(lab_);
            panel_.add(lab_);
            AbsPreparedLabel c_ = _curr.getListComponents().get(index_);
            _r.getListCellRendererComponent(c_, index_, _curr.getSelectedIndexes().containsObj(index_), false);
            _r.paintComponent(c_);
            index_++;
        }
    }

    public static void selectSingleOrMult(AbsGraphicListCommon _curr) {
        if (_curr.isSimple()) {
            int index_ = 0;
            for (AbsPreparedLabel c: _curr.getListComponents()) {
                _curr.singleSelect(index_, c);
                index_++;
            }
        } else {
            int index_ = 0;
            for (AbsPreparedLabel c: _curr.getListComponents()) {
                _curr.multSelect(index_, c);
                index_++;
            }
        }
    }

    public static void reb(AbsGraphicListCommon _curr) {
        _curr.rebuildAct();
    }

    public static void repAdd(int _index, AbsCustCellRender _r, CustList<AbsPreparedLabel> _listComponents) {
        _r.fwd();
        AbsPreparedLabel c_ = _listComponents.get(_index);
        _r.getListCellRendererComponent(c_, _index, false, false);
        _r.paintComponent(c_);
    }

    public static void singleMultSel(AbsGraphicListCommon _curr, int _index, AbsPreparedLabel _lab) {
        if (!_curr.isSimple()) {
            _curr.multSelSet(_index, _lab);
        } else {
            _curr.singleSelSet(_index, _lab);
        }
    }

    public static void addLists(AbsGraphicListCommon _curr, int _index, AbsPreparedLabel _lab) {
        if (!_curr.isSimple()) {
            _curr.addMultSel(_index, _lab);
        } else {
            _curr.addSingleSel(_index, _lab);
        }
    }

    public static void prSelect(AbsGraphicStringList _grList, int _selectedIndex, AbsGraphicCombo _current) {
        if (!_grList.getList().isEmpty()) {
            _current.selectItem(_selectedIndex);
        } else {
            setNoSelected(_current);
        }
    }

    public static void selectEvent(int _firstIndex, int _lastIndex, boolean _methodAction, ListSelection _listener) {
        if (_listener == null) {
            return;
        }
        int min_ = NumberUtil.min(_firstIndex, _lastIndex);
        int max_ = NumberUtil.max(_firstIndex, _lastIndex);
        SelectionInfo ev_ = new SelectionInfo(min_, max_, _methodAction);
        _listener.valueChanged(ev_);
    }

    public static void setNoSelected(AbsGraphicCombo _current) {
        int s_ = _current.getCompoFactory().heightFont(_current.getPanel().getMetaFont()) + 2;
        int w_ = 5;
        AbstractImage img_ = _current.getFact().newImageRgb(w_, s_);
//        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        img_.setFont(_current.getLabel());
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, w_, s_);
        _current.getLabel().setIcon(_current.getFact(),img_);
    }

    public static void commonSet(Struct _grComp, AbsGraphicListPainter _p) {
        if (_p != null) {
            _p.setValue(_grComp);
        }
    }

    public static int maxWidth(AbsGraphicListCommon _current, CustList<String> _list, AbsCompoFactory _compoFactory) {
        AbsPanel panel_ = _current.getPanel();
        return maxWidth(panel_, _list,_compoFactory);
    }

    public static int maxWidth(AbsPanel _current, CustList<String> _list, AbsCompoFactory _compoFactory) {
        int width_ = 4;
        for (String s: _list) {
            width_ = NumberUtil.max(width_, _compoFactory.stringWidth(_current.getMetaFont(),s));
        }
        return width_;
    }

    public static AbstractImage repaintSelected(int _index, boolean _sel, AbsGraphicStringList _curr, DefaultCellRender _simpleRender, AbsCompoFactory _compoFactory) {
        String elt_ = _curr.getElements().get(_index);
        AbsPanel panel_ = _curr.getPanel();
        _curr.setHeightList(NumberUtil.max(_curr.getHeightList(),_compoFactory.heightFont(panel_.getMetaFont())));
        _simpleRender.setMaxWidth(NumberUtil.max(_simpleRender.getMaxWidth(),_compoFactory.stringWidth(panel_.getMetaFont(),elt_)));
        AbstractImage buff_ = _curr.getFact().newImageRgb(_simpleRender.getWidth(),_compoFactory.heightFont(panel_.getMetaFont()));
//        CustGraphics gr_ = new CustGraphics(buff_.getGraphics());
        buff_.setFont(panel_);
        int h_ = _compoFactory.heightFont(panel_.getMetaFont());
        int w_ = _compoFactory.stringWidth(panel_.getMetaFont(),elt_);
        if (_sel) {
            LabelButtonUtil.paintDefaultLabel(buff_, elt_, w_, _simpleRender.getMaxWidth(), h_, GuiConstants.WHITE, GuiConstants.BLUE);
        } else {
            LabelButtonUtil.paintDefaultLabel(buff_, elt_, w_, _simpleRender.getMaxWidth(), h_, GuiConstants.BLACK, GuiConstants.WHITE);
        }
        return buff_;
    }

    public static void repAll(AbsGraphicStringList _curr, AbsCompoFactory _compoFactory) {
        AbsPanel panel_ =  _curr.getPanel();
        int len_ =  _curr.getElements().size();
        for (int i = 0; i < len_; i++) {
            AbstractImage buff_ = repaintSelected(i,  _curr.getSelectedIndexes().containsObj(i), _curr, (DefaultCellRender)_curr.getSimpleRender(),_compoFactory);
            AbsPreparedLabel lab_ = _curr.getCompoFactory().newPreparedLabel(buff_);
            _curr.getListComponents().add(lab_);
            panel_.add(lab_);
        }
    }
}
