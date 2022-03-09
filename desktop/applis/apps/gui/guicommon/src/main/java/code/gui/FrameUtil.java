package code.gui;

import code.expressionlanguage.structs.Struct;
import code.gui.events.AbsWindowListener;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;




public final class FrameUtil {
    private static final String ACCESS = "gui.groupframe";
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";

    private FrameUtil() {
    }

    public static boolean tryToReopen(String _applicationName, AbstractProgramInfos _list) {
        for (AbsGroupFrame g: _list.getFrames()) {
            if (StringUtil.quickEq(g.getApplicationName(), _applicationName)) {
                g.pack();
                g.getCommonFrame().setVisible(true);
                return true;
            }
        }
        return false;
    }

    public static void changeStaticLanguage(String _language, AbstractProgramInfos _list) {
        _list.getFrames().first().setMessages(group(_language));
        _list.getFrames().first().changeLanguage(_language);
    }

    public static StringMap<String> group(String _language) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _language, ACCESS);
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    public static void removeAllListeners(AbsGroupFrame _groupFrame) {
        for (AbsWindowListener l: _groupFrame.getCommonFrame().getWindowListeners()) {
            _groupFrame.getCommonFrame().removeWindowListener(l);
        }
    }

    public static void tryExit(AbsGroupFrame _groupFrame) {
        if(!_groupFrame.getCommonFrame().getFrames().getFrames().first().isOpened()) {
            _groupFrame.exit();
        }
    }

    public static void choose(String _lg, AbstractProgramInfos _list, AbsGroupFrame _this) {
        _list.getFrames().add(_this);
        AbsGroupFrame first_ = _list.getFrames().first();
        if (_list.getFrames().size() == 1) {
            _this.init(_list);
            first_.setMessages(group(_lg));
        } else {
            _this.setByFirst(first_);
        }
    }

    public static void showDialogError(AbsGroupFrame _group, int _errorMessage) {
        StringMap<String> messages_ = _group.getCommonFrame().getFrames().getFrames().first().getMessages();
        ConfirmDialog.showMessage(_group, messages_.getVal(MESSAGE), messages_.getVal(TITLE), _group.getCommonFrame().getFrames().getFrames().first().getCommonFrame().getLanguageKey(), _errorMessage);
    }

    public static void setLocationRelativeToWin(Iconifiable _i, AbsDialog _to) {
        if (_i instanceof AbsGroupFrame) {
            _to.setLocationRelativeTo((AbsGroupFrame) _i);
        } else if (_i instanceof AbsDialog) {
            _to.setLocationRelativeTo((AbsDialog) _i);
        }
    }

    public static void recalculate(AbsCustComponent _compo) {
        _compo.setSize(_compo.getPreferredSizeValue());
        AbsCustComponent curr_ = _compo;
        while (curr_ != null) {
            curr_.recalculate();
            if (curr_ instanceof AbsScrollPane) {
                ((AbsScrollPane)curr_).recalculateViewport();
            }
            AbsCustComponent child_ = childAt(curr_, 0);
            if (child_ != null) {
                curr_ = child_;
                continue;
            }
            while (curr_ != null) {
                AbsCustComponent par_ = curr_.getParent();
                int index_ = indexOf(par_,curr_);
                AbsCustComponent next_ = childAt(par_, index_ + 1);
                if (next_ != null) {
                    curr_ = next_;
                    break;
                }
                curr_ = par_;

            }
        }
    }
    private static AbsCustComponent childAt(AbsCustComponent _elt, int _index) {
        if (_elt == null) {
            return null;
        }
        CustList<AbsCustComponent> children_ = _elt.getChildren();
        if (!children_.isValidIndex(_index)) {
            return null;
        }
        return children_.get(_index);
    }
    private static int indexOf(AbsCustComponent _par,AbsCustComponent _elt) {
        if (_par == null) {
            return -1;
        }
        return indexOf(_par.getChildren(), _elt);
    }
    public static int indexOf(CustList<AbsCustComponent> _list,AbsCustComponent _elt) {
        int index_ = 0;
        for (AbsCustComponent c: _list) {
            if (c == _elt) {
                return index_;
            }
            index_++;
        }
        return -1;
    }
    public static void repaint(AbstractImageFactory _fact, AbsPaintableLabel _paintableLabel, AbsMetaLabel _metaLabel) {
        int w_ = _paintableLabel.getWidth();
        int h_ = _paintableLabel.getHeight();
        if (w_ <= 0 || h_ <= 0) {
            _paintableLabel.setEmptyIcon();
            return;
        }
        AbstractImage img_ = _fact.newImageArgb(w_, h_);
//        CustGraphics gr_ = img_.getGraphics();
        img_.setFont(_paintableLabel);
        _metaLabel.paintComponent(img_);
        _paintableLabel.setIcon(_fact,img_);
    }

    public static int pref(int _dim, int _pr) {
        if (_dim > 0) {
            return _dim;
        }
        return _pr;
    }

    public static void invokeLater(Runnable _r, AbsCompoFactory _compoFactory) {
        _compoFactory.invokeLater(_r);
    }
    public static void invokeLater(Runnable _r, AbstractLightProgramInfos _compoFactory) {
        _compoFactory.getCompoFactory().invokeLater(_r);
    }

    public static AbsPreparedLabel prep(AbstractImageFactory _img) {
        return _img.newImageArgb(1,1).newAbsPreparedLabel();
    }

    public static void repaintLists(AbstractImageFactory _fact, AbsPanel _panel) {
        for (AbsCustComponent c: _panel.getChildren()) {
            procCh(_fact, c);
        }
        _panel.validate();
    }

    private static void procCh(AbstractImageFactory _fact, AbsCustComponent _c) {
        if (_c instanceof AbsPaintableLabel) {
            ((AbsPaintableLabel) _c).repaintLabel(_fact);
        } else if (_c instanceof AbsPanel) {
            for (AbsCustComponent d: _c.getChildren()) {
                proc(_fact, d);
            }
            _c.validate();
        }
    }

    public static void repaintList(AbstractImageFactory _fact, AbsPanel _panel) {
        for (AbsCustComponent c: _panel.getChildren()) {
            proc(_fact, c);
        }
        _panel.validate();
    }

    private static void proc(AbstractImageFactory _fact, AbsCustComponent _c) {
        if (_c instanceof AbsPaintableLabel) {
            ((AbsPaintableLabel) _c).repaintLabel(_fact);
        }
    }

    public static Ints selectedIndexes(int _selectedIndex) {
        if (_selectedIndex == -1) {
            return new Ints();
        }
        return new Ints(_selectedIndex);
    }

    public static int maxWidth(AbsGraphicListCommon _current, CustList<String> _list) {
        AbsPanel panel_ = _current.getPanel();
        return maxWidth(panel_, _list);
    }

    public static int maxWidth(AbsPanel _current, CustList<String> _list) {
        int width_ = 4;
        for (String s: _list) {
            width_ = Math.max(width_, _current.stringWidth(s));
        }
        return width_;
    }

    public static void commonSet(Struct _grComp, AbsGraphicListCommon _graphicListStr) {
        AbsGraphicListPainter graphicListPainter_ = _graphicListStr.getGraphicListPainter();
        if (graphicListPainter_ != null) {
            graphicListPainter_.setValue(_grComp);
        }
    }

    public static void selectEvent(int _firstIndex, int _lastIndex, boolean _methodAction, ListSelection _listener) {
        if (_listener == null) {
            return;
        }
        int min_ = Math.min(_firstIndex, _lastIndex);
        int max_ = Math.max(_firstIndex, _lastIndex);
        SelectionInfo ev_ = new SelectionInfo(min_, max_, _methodAction);
        _listener.valueChanged(ev_);
    }

    public static void setNoSelected(AbsGraphicCombo _current) {
        int s_ = _current.getPanel().heightFont() + 2;
        int w_ = 5;
        AbstractImage img_ = _current.getFact().newImageRgb(w_, s_);
//        CustGraphics gr_ = new CustGraphics(img_.createGraphics());
        img_.setFont(_current.getLabel());
        img_.setColor(GuiConstants.WHITE);
        img_.fillRect(0, 0, w_, s_);
        _current.getLabel().setIcon(_current.getFact(),img_);
    }

    public static void prSelect(AbsGraphicStringList _grList, int _selectedIndex, AbsGraphicCombo _current) {
        if (!_grList.getList().isEmpty()) {
            _current.selectItem(_selectedIndex);
        } else {
            setNoSelected(_current);
        }
    }

    public static void updateLoc(AbsGraphicCombo _curr) {
        String selected_ = _curr.getSelectedItem();
        if (selected_ == null) {
            return;
        }
        int w_ = _curr.getGrList().getMaxWidth();
        int s_ = _curr.getPanel().heightFont() + 2;
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

    public static void addIt(String _object, AbsGraphicCombo _curr) {
        _curr.getGrList().add(_object);
        if (_curr.getGrList().getList().size() == 1) {
            _curr.setSelectedIndex(0);
            int w_ = _curr.getGrList().getMaxWidth();
            int s_ = _curr.getPanel().heightFont() + 2;
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

    public static String selIt(AbsGraphicCombo _curr) {
        CustList<String> list_ = _curr.getGrList().getList();
        if (!list_.isValidIndex(_curr.getSelectedIndex())) {
            return null;
        }
        return list_.get(_curr.getSelectedIndex());
    }

    public static void reindex(CustList<IndexableListener> _list) {
        int index_ = 0;
        for (IndexableListener c: _list) {
            c.setIndex(index_);
            index_++;
        }
    }

    public static AbsCustCellRender fwd(AbsCustCellRender _r) {
        _r.fwd();
        return _r;
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

    public static int getBasicMaxWidth(int _width, AbsGraphicListDefBase _curr) {
        int width_ = _width;
        for (AbsPreparedLabel c: _curr.getListComponents()) {
            width_ = Math.max(width_, c.getPreferredSizeValue().getWidth());
        }
        return width_;
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
        return new MetaDimension(width_ + 24, (h_ + 2) * Math.min(c_, _curr.getVisibleRowCount()));
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
        c_.requestFocus();
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
            width_ = Math.max(width_, c.getWidth());
        }
        int h_ = 0;
        int c_ = 0;
        for (AbsPreparedLabel c: _curr.getListComponents()) {
            h_ = Math.max(h_,c.getHeight());
            c_++;
        }
        return new MetaDimension(width_ + 24, (h_ + 2) * Math.min(c_, _curr.getVisibleRowCount()));
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
            AbsPreparedLabel lab_ = prep(_graphicListPainter.getFact());
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

    public static void setTab(int _index, AbsCustComponent _component, AbsTabbedPane _curr) {
        if (!_curr.getChildren().isValidIndex(_index)) {
            return;
        }
        if (_component.getParent() != null) {
            return;
        }
        _curr.setTabComponentAt(_index, _component);
    }

    public static String title(int _index, AbsTabbedPane _curr) {
        if (!_curr.getChildren().isValidIndex(_index)) {
            return "";
        }
        return _curr.getTitleAt(_index);
    }

    public static void title(int _index, String _title, AbsTabbedPane _curr) {
        if (!_curr.getChildren().isValidIndex(_index)) {
            return;
        }
        _curr.setTitleAt(_index, _title);
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

    public static boolean invalidSpinner(int _value, int _min, int _max) {
        if (_value < _min) {
            return true;
        }
        return _value > _max;
    }

    public static void initModel(AbsSpinner _curr, int _value, int _min, int _max, int _step) {
        if (invalidSpinner(_value, _min, _max)) {
            _curr.defValues();
        } else {
            _curr.mod(_value, _min, _max, _step);
        }
    }

    public static void rg(AbsSpinner _curr, int _min, int _max) {
        if (invalidSpinner(_curr.getValue(), _min, _max)) {
            return;
        }
        _curr.range(_min, _max);
    }

    public static void rgValue(AbsSpinner _curr, int _value, int _min, int _max) {
        if (invalidSpinner(_value, _min, _max)) {
            return;
        }
        _curr.rangeValue(_value, _min, _max);
    }

    public static void mn(AbsSpinner _curr, int _min) {
        if (invalidSpinner(_curr.getValue(), _min, _curr.getMax())) {
            return;
        }
        _curr.min(_min);
    }

    public static void mx(AbsSpinner _curr, int _max) {
        if (invalidSpinner(_curr.getValue(), _curr.getMin(), _max)) {
            return;
        }
        _curr.max(_max);
    }

    public static void vl(AbsSpinner _curr, int _value) {
        if (invalidSpinner(_value, _curr.getMin(), _curr.getMax())) {
            return;
        }
        _curr.updateModel(_value);
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

    public static void paintLabel(int _render, AbstractImage _g, AbsPreparedLabel _label, String _text, boolean _selected) {
        int h_ = _label.heightFont();
        int w_ = _label.stringWidth(_text);
        if (_selected) {
            LabelButtonUtil.paintDefaultLabel(_g, _text, w_, _render, h_, GuiConstants.WHITE, GuiConstants.BLUE);
        } else {
            LabelButtonUtil.paintDefaultLabel(_g, _text, w_, _render, h_, GuiConstants.BLACK, GuiConstants.WHITE);
        }
    }
}
