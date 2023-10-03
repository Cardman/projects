package code.gui;

import code.gui.events.AbsEnabledAction;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaRect;
import code.gui.initialize.AbsCompoFactory;
import code.util.IdList;
import code.util.Ints;
import code.util.core.NumberUtil;

public final class ScrollCustomGraphicList<T> {
    private final AbsScrollPane scrollPane;
    private final AbsPanel elements;
    private final AbsEnabledAction moveDownAction;
    private final AbsEnabledAction moveUpAction;
    private final AbsEnabledAction moveShiftDownAction;
    private final AbsEnabledAction moveShiftUpAction;
    private final AbsEnabledAction movePageUpAction;
    private final AbsEnabledAction movePageDownAction;
    private final AbsEnabledAction movePageShiftUpAction;
    private final AbsEnabledAction movePageShiftDownAction;
    private final AbsEnabledAction movePageHomeAction;
    private final AbsEnabledAction movePageEndAction;
    private final AbsEnabledAction movePageShiftHomeAction;
    private final AbsEnabledAction movePageShiftEndAction;
    private final AbsEnabledAction selectAllAction;
    private RowGraphicList<T> first;
    private RowGraphicList<T> last;
    private final AbsCustCellRenderGene<T> custCellRenderGene;
    private final IdList<ListSelection> selections = new IdList<ListSelection>();
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;
    private final boolean single;
    private int visibleRowCount = 8;

    private boolean enabled = true;
    private RowGraphicListIndex<T> focused = new RowGraphicListIndex<T>(null,-1);
    private int selectedFg;
    private int selectedBg;
    public ScrollCustomGraphicList(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<T> _render, boolean _s) {
        compoFactory = _compo;
        imageFactory = _img;
        elements = _compo.newPageBox();
        elements.setAutoscrolls(true);
        elements.setFocusable(true);
        elements.addMouseListener(new SelectingGraphicListEvent<T>(this));
        moveDownAction = compoFactory.wrap(new MoveGraphicSelectDownEvent<T>(this));
        elements.registerKeyboardAction(moveDownAction,GuiConstants.VK_DOWN,0);
        moveUpAction = compoFactory.wrap(new MoveGraphicSelectUpEvent<T>(this));
        elements.registerKeyboardAction(moveUpAction,GuiConstants.VK_UP,0);
        moveShiftDownAction = compoFactory.wrap(new MoveGraphicSelectShiftDownEvent<T>(this));
        elements.registerKeyboardAction(moveShiftDownAction,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        moveShiftUpAction = compoFactory.wrap(new MoveGraphicSelectShiftUpEvent<T>(this));
        elements.registerKeyboardAction(moveShiftUpAction,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        movePageUpAction = compoFactory.wrap(new MoveGraphicSelectPageDownEvent<T>(this));
        elements.registerKeyboardAction(movePageUpAction,GuiConstants.VK_PAGE_DOWN,0);
        movePageDownAction = compoFactory.wrap(new MoveGraphicSelectPageUpEvent<T>(this));
        elements.registerKeyboardAction(movePageDownAction,GuiConstants.VK_PAGE_UP,0);
        movePageShiftUpAction = compoFactory.wrap(new MoveGraphicSelectShiftPageDownEvent<T>(this));
        elements.registerKeyboardAction(movePageShiftUpAction,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        movePageShiftDownAction = compoFactory.wrap(new MoveGraphicSelectShiftPageUpEvent<T>(this));
        elements.registerKeyboardAction(movePageShiftDownAction,GuiConstants.VK_PAGE_UP,GuiConstants.SHIFT_DOWN_MASK);
        movePageHomeAction = compoFactory.wrap(new MoveGraphicSelectHomeEvent<T>(this));
        elements.registerKeyboardAction(movePageHomeAction,GuiConstants.VK_HOME,0);
        movePageEndAction = compoFactory.wrap(new MoveGraphicSelectEndEvent<T>(this));
        elements.registerKeyboardAction(movePageEndAction,GuiConstants.VK_END,0);
        movePageShiftHomeAction = compoFactory.wrap(new MoveGraphicSelectShiftHomeEvent<T>(this));
        elements.registerKeyboardAction(movePageShiftHomeAction,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        movePageShiftEndAction = compoFactory.wrap(new MoveGraphicSelectShiftEndEvent<T>(this));
        elements.registerKeyboardAction(movePageShiftEndAction,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        selectAllAction = compoFactory.wrap(new MoveGraphicSelectSelectAllEvent<T>(this));
        elements.registerKeyboardAction(selectAllAction,GuiConstants.VK_A,GuiConstants.CTRL_DOWN_MASK);
        scrollPane = _compo.newAbsScrollPane(elements);
        custCellRenderGene = _render;
        this.single = _s;
        enable(enabled);
        elements.setBackground(GuiConstants.WHITE);
        elements.setForeground(GuiConstants.BLACK);
        setSelectedBg(GuiConstants.BLUE);
        setSelectedFg(GuiConstants.WHITE);
    }
    private void enable(boolean _en) {
        moveDownAction.setEnabled(_en);
        moveUpAction.setEnabled(_en);
        moveShiftDownAction.setEnabled(_en);
        moveShiftUpAction.setEnabled(_en);
        movePageUpAction.setEnabled(_en);
        movePageDownAction.setEnabled(_en);
        movePageShiftUpAction.setEnabled(_en);
        movePageShiftDownAction.setEnabled(_en);
        movePageHomeAction.setEnabled(_en);
        movePageEndAction.setEnabled(_en);
        movePageShiftHomeAction.setEnabled(_en);
        movePageShiftEndAction.setEnabled(_en);
        selectAllAction.setEnabled(_en && !single);
    }
    public void add(T _i) {
        int s_ = size();
        RowGraphicList<T> elt_ = new RowGraphicList<T>(_i, s_, compoFactory, imageFactory, custCellRenderGene, colorGroup());
        append(elt_);
        elements.add(elt_.getLabel());
    }
    public void add(int _index, T _i) {
        if (_index < 0) {
            return;
        }
        RowGraphicList<T> elt_ = new RowGraphicList<T>(_i, _index, compoFactory, imageFactory, custCellRenderGene, colorGroup());
        RowGraphicList<T> next_ = getRow(_index);
        if (next_ != null) {
            RowGraphicList<T> pr_ = next_.getPrevious();
            if (pr_ != null) {
                elt_.setPrevious(pr_);
                pr_.setNext(elt_);
            } else {
                first = elt_;
            }
            elt_.setNext(next_);
            next_.setPrevious(elt_);
        } else {
            append(elt_);
        }
        elements.add(elt_.getLabel(), _index);
    }

    private void append(RowGraphicList<T> _e) {
        if (last != null) {
            _e.setPrevious(last);
            last.setNext(_e);
            last = _e;
        } else {
            last = _e;
            first = _e;
        }
    }

    public int size() {
        int s_ = 0;
        RowGraphicList<T> c_ = first;
        while (c_ != null) {
            s_++;
            c_ = c_.getNext();
        }
        return s_;
    }

    public T get(int _index) {
        RowGraphicList<T> next_ = getRow(_index);
        if (next_ == null) {
            return null;
        }
        return next_.getInfo();
    }

    public void set(int _index, T _i) {
        RowGraphicList<T> next_ = getRow(_index);
        if (next_ == null) {
            return;
        }
        next_.update(_index,_i, imageFactory,custCellRenderGene, colorGroup());
    }

    public void remove(int _index) {
        RowGraphicList<T> current_ = getRow(_index);
        if (current_ == null) {
            return;
        }
        RowGraphicList<T> pre_ = current_.getPrevious();
        RowGraphicList<T> next_ = current_.getNext();
        if (pre_ == null) {
            if (next_ == null) {
                focus(new RowGraphicListIndex<T>(null,-1));
                first = null;
                last = null;
            } else {
                focus(new RowGraphicListIndex<T>(next_,0));
                first = next_;
                current_.setNext(null);
                next_.setPrevious(null);
            }
        } else {
            if (next_ == null) {
                focus(new RowGraphicListIndex<T>(pre_,focused.getIndex()-1));
                last = pre_;
                current_.setPrevious(null);
                pre_.setNext(null);
            } else {
                focus(new RowGraphicListIndex<T>(next_,focused.getIndex()));
                pre_.setNext(next_);
                next_.setPrevious(pre_);
                current_.setPrevious(null);
                current_.setNext(null);
            }
        }
        elements.remove(current_.getLabel());
        fireEvents(_index, _index, true);
        scrollPane.recalculateViewport();
    }

    public void select(int _index) {
        RowGraphicList<T> next_ = getRow(_index);
        select(_index, next_, true);

    }

    public RowGraphicListIndex<T> getRowOrEmpty(Ints _index) {
        if (_index.isEmpty()) {
            return new RowGraphicListIndex<T>(null,-1);
        }
        return new RowGraphicListIndex<T>(getRow(_index.last()),_index.last());
    }
    public RowGraphicList<T> getRow(int _index) {
        if (_index < 0) {
            return null;
        }
        int s_ = 0;
        RowGraphicList<T> c_ = first;
        while (c_ != null) {
            if (s_ == _index) {
                return c_;
            }
            s_++;
            c_ = c_.getNext();
        }
        return null;
    }
    public void extendsCoords(int _y) {
        if (single) {
            selectCoords(_y);
            return;
        }
        RowGraphicListIndex<T> sel_ = selectedCoords(_y);
        if (sel_.getRow() != null && focused.getRow() != null && sel_.getRow() != focused.getRow()) {
            boolean selected_ = !sel_.getRow().isSelected();
            boolean rev_ = sel_.getRow().getLabel().getYcoords() < focused.getRow().getLabel().getYcoords();
            selectRange(sel_, selected_, rev_, rev(focused.getRow(), rev_));
            sel_.getRow().select(selected_);
            if (!selected_) {
                focused.getRow().select(false);
            }
            int ind_ = focused.getIndex() + rate(rev_);
            focus(sel_);
            fireEvents(ind_, sel_.getIndex(), false);
            refreshAll();
        }
    }

    private void selectRange(RowGraphicListIndex<T> _sel, boolean _selected, boolean _rev, RowGraphicList<T> _from) {
        RowGraphicList<T> cur_ = _from;
        while (cur_ != _sel.getRow()) {
            cur_.select(_selected);
            cur_ = rev(cur_, _rev);
        }
    }

    public void focusChange(int _y) {
        RowGraphicListIndex<T> sel_ = selectedCoords(_y);
        if (sel_.getRow() != null) {
            focus(sel_);
            refreshAll();
        }
    }
    public void selectCoords(int _y) {
        RowGraphicListIndex<T> sel_ = selectedCoords(_y);
        select(sel_.getIndex(), sel_.getRow(), false);
    }
    private RowGraphicListIndex<T> selectedCoords(int _y) {
        int s_ = 0;
        int sum_ = 0;
        RowGraphicList<T> c_ = first;
        while (c_ != null) {
            int inf_ = sum_;
            if (inf_ <= _y && _y < inf_ + c_.getLabel().getHeight()) {
                break;
            }
            s_++;
            sum_ += c_.getLabel().getHeight();
            c_ = c_.getNext();
        }
        if (c_ != null) {
            return new RowGraphicListIndex<T>(c_,s_);
        }
        return new RowGraphicListIndex<T>(null,-1);
    }

    private void select(int _index, RowGraphicList<T> _next, boolean _meth) {
        if (_next == null) {
            return;
        }
        deselectAll();
        updateFocus(_next, _index);
        _next.select(true);
        fireEvents(_index, _index, _meth);
        refreshAll();
    }

    public void select(Ints _indices) {
        deselectAll();
        if (single) {
            int s_ = size();
            Ints valid_ = new Ints();
            for (int i: _indices.toArrInt()) {
                if (i>=0 && i < s_) {
                    valid_.add(i);
                }
            }
            RowGraphicListIndex<T> row_ = getRowOrEmpty(valid_);
            if (row_.getRow() == null) {
                fireEvents(-1, -1, true);
                refreshAll();
                return;
            }
            row_.getRow().select(true);
            int index_ = valid_.last();
            updateFocus(row_.getRow(), index_);
            fireEvents(index_, index_, true);
            refreshAll();
            return;
        }
        RowGraphicList<T> c_ = first;
        int s_ = 0;
        Ints selIndexes_ = new Ints();
        int m_ = -1;
        while (c_ != null) {
            int index_ = _indices.indexOfNb(s_);
            if (index_ > -1) {
                selIndexes_.add(s_);
                c_.select(true);
                if (index_ > m_) {
                    updateFocus(c_, s_);
                    m_ = index_;
                }
            }
            s_++;
            c_ = c_.getNext();
        }
        fireEvents((int) selIndexes_.getMinimum(-1), (int) selIndexes_.getMaximum(-1), true);
        refreshAll();
    }

    private void fireEvents(int _first, int _last, boolean _meth) {
        for (ListSelection l: selections) {
            l.valueChanged(new SelectionInfo(_first, _last, _meth));
        }
    }

    public void addOrRemoveToSelectCoords(int _y) {
        RowGraphicListIndex<T> sel_ = selectedCoords(_y);
        if (sel_.getRow() != null) {
            addOrRemoveToSelect(sel_.getIndex(), !sel_.getRow().isSelected(), sel_.getRow());
        }
    }

    public void selectAll() {
        RowGraphicList<T> c_ = first;
        while (c_ != null) {
            c_.select(true);
            c_ = c_.getNext();
        }
        fireEvents(0, elements.getComponentCount()-1, false);
        refreshAll();
    }
    public void moveDown() {
        move(true);
    }

    public void moveUp() {
        move(false);
    }

    public void move(boolean _down) {
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            goBound(_down);
            return;
        }
        RowGraphicList<T> prev_ = rev(foc_,!_down);
        if (prev_ == null) {
            return;
        }
        deselectAll();
        int ind_ = this.focused.getIndex();
        updateFocus(prev_, ind_+rate(!_down));
        int ni_ = ind_+rate(!_down);
        prev_.select(true);
        scrollPage(_down, scrollPane.viewRect());
        fireEvents(ni_, ni_, false);
        refreshAll();
    }
    public void moveShiftDown() {
        moveShift(true);
    }

    public void moveShiftUp() {
        moveShift(false);
    }
    public void moveShift(boolean _down) {
        if (single) {
            move(_down);
            return;
        }
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            goBound(_down);
            return;
        }
        RowGraphicList<T> next_ = rev(foc_,!_down);
        if (next_ == null) {
            return;
        }
        deselect(foc_, _down);
        int ind_ = this.focused.getIndex();
        int ni_ = ind_+rate(!_down);
        toUpdate(next_,foc_).select(!next_.isSelected());
        updateFocus(next_, ind_+rate(!_down));
        scrollPage(_down, scrollPane.viewRect());
        fireEvents(ni_, ni_, false);
        refreshAll();
    }
    private RowGraphicList<T> toUpdate(RowGraphicList<T> _next, RowGraphicList<T> _foc) {
        if (_next.isSelected() == _foc.isSelected()) {
            return _foc;
        } else {
            return _next;
        }
    }

    public void moveShiftPageDown() {
        moveShiftPage(true);
    }
    public void moveShiftPage(boolean _down) {
        if (single) {
            movePage(_down);
            return;
        }
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            goBound(_down);
            return;
        }
        moveShiftPage(_down, foc_, scrollPane.viewRect());
    }

    public void moveShiftPage(boolean _rev, RowGraphicList<T> _foc, MetaRect _rect) {
        boolean sel_ = select(_foc, !_rev);
        RowGraphicListIndex<T> next_ = nextIndex(_rect.getHeight(), !_rev);
        if (next_.getRow() == _foc) {
            return;
        }
        deselect(_foc, _rev);
        toggle(_foc, next_, !_rev, sel_);
        int ind_ = next_.getIndex();
        int ni_ = this.focused.getIndex();
        updateFocus(next_.getRow(), ind_);
        scrollPage(_rev, _rect);
        int first_ = NumberUtil.min(ni_+rate(!_rev),ind_);
        int last_ = NumberUtil.max(ni_+rate(!_rev),ind_);
        fireEvents(first_, last_, false);
        refreshAll();
    }

    private boolean select(RowGraphicList<T> _f, boolean _rev) {
        RowGraphicList<T> n_ = rev(_f, _rev);
        boolean sel_;
        if (n_ != null) {
            sel_ = n_.isSelected();
        } else {
            sel_ = false;
        }
        return sel_;
    }

    public void moveShiftPageUp() {
        moveShiftPage(false);
    }
    private void deselect(RowGraphicList<T> _foc, boolean _reve) {
        RowGraphicList<T> cu_ = interval(_foc, _reve);
        while (cu_ != null) {
            cu_.select(false);
            cu_ = rev(cu_, _reve);
        }
    }


    private void scrollPage(boolean _rev, MetaRect _rect) {
        if (_rev) {
            scrollPageDown(focused.getRow(), _rect);
        } else {
            scrollPageUp(focused.getRow(), _rect);
        }
    }
    private void scrollPageDown(RowGraphicList<T> _dest, MetaRect _rect) {
        int bot_ = _dest.getLabel().getYcoords()+ _dest.getLabel().getHeight();
        int sc_ = _rect.getHeight() + _rect.getPoint().getYcoord();
        if (bot_ >= sc_) {
            scrollPane.setVerticalValue(scrollPane.getVerticalValue()+bot_-sc_);
        }
    }

    private void scrollPageUp(RowGraphicList<T> _prev, MetaRect _rect) {
        int top_ = _prev.getLabel().getYcoords();
        int sc_ = _rect.getPoint().getYcoord();
        if (top_ <= sc_) {
            scrollPane.setVerticalValue(scrollPane.getVerticalValue()+top_-sc_);
        }
    }
    public void moveShiftTop() {
        moveShiftBound(false);
    }

    public int forceRefresh() {
        RowGraphicList<T> cu_ = first;
        int s_ = 0;
        while (cu_ != null) {
            cu_.forceRefresh(s_,imageFactory,custCellRenderGene, colorGroup());
            cu_ = cu_.getNext();
            s_++;
        }
        return s_;
    }


    private RowGraphicList<T> interval(RowGraphicList<T> _current, boolean _reversed) {
        RowGraphicList<T> cu_ = _current;
        while (cu_ != null) {
            if (cu_.isSelected() != _current.isSelected()) {
                return cu_;
            }
            cu_ = rev(cu_,_reversed);
        }
        return null;
    }


    private void toggle(RowGraphicList<T> _focused, RowGraphicListIndex<T> _dest, boolean _reversed, boolean _n) {
        RowGraphicList<T> cur_ = rev(_focused, _reversed);
        boolean nextSel_ = !_n;
        selectRange(_dest,nextSel_,_reversed, cur_);
        toUpdate(_dest.getRow(),_focused).select(nextSel_);
    }

    private RowGraphicList<T> rev(RowGraphicList<T> _focused, boolean _rev) {
        RowGraphicList<T> cur_;
        if (_rev) {
            cur_ = _focused.getPrevious();
        } else {
            cur_ = _focused.getNext();
        }
        return cur_;
    }
    public void moveShiftBottom() {
        moveShiftBound(true);
    }
    public void moveShiftBound(boolean _down) {
        if (single) {
            goBound(!_down);
            return;
        }
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            goBound(_down);
            return;
        }
        boolean sel_ = select(foc_, !_down);
        RowGraphicList<T> b_ = bound(!_down);
        if (b_ == foc_) {
            return;
        }
        deselect(foc_, _down);
        RowGraphicListIndex<T> next_ = new RowGraphicListIndex<T>(b_, boundIndex(!_down));
        toggle(foc_, next_, !_down, sel_);
        int ind_ = next_.getIndex();
        int ni_ = this.focused.getIndex();
        updateFocus(b_, ind_);
        scrollPage(_down, scrollPane.viewRect());
        int first_ = NumberUtil.min(ni_+rate(!_down),ind_);
        int last_ = NumberUtil.max(ni_+rate(!_down),ind_);
        fireEvents(first_, last_, false);
        refreshAll();
    }
    public void goBottom() {
        goBound(false);
    }

    public void goTop() {
        goBound(true);
    }
    public void goBound(boolean _up) {
        deselectAll();
        RowGraphicList<T> b_ = bound(_up);
        if (b_ != null) {
            updateFocus(b_, boundIndex(_up));
            b_.select(true);
            scrollBound(_up);
            fireEvents(focused.getIndex(), focused.getIndex(), false);
            refreshAll();
        }
    }
    private void scrollBound(boolean _rev) {
        if (_rev) {
            scrollPane.setVerticalValue(0);
        } else {
            int bot_ = last.getLabel().getYcoords()+last.getLabel().getHeight();
            int sc_ = scrollPane.viewRect().getHeight();
            scrollPane.setVerticalValue(bot_-sc_);
        }
    }

    public void movePageDown() {
        movePage(true);
    }
    public void movePageUp() {
        movePage(false);
    }
    public void movePage(boolean _down) {
        MetaRect rect_ = scrollPane.viewRect();
        movePage(_down, rect_);
    }

    public void movePage(boolean _rev, MetaRect _rect) {
        RowGraphicListIndex<T> prev_ = nextIndex(_rect.getHeight(), !_rev);
        if (prev_.getRow() == null || prev_.getRow() == focused.getRow()) {
            return;
        }
        deselectAll();
        focus(prev_);
        prev_.getRow().select(true);
        scrollPage(_rev, _rect);
        int ind_ = prev_.getIndex();
        fireEvents(ind_, ind_, false);
        refreshAll();
    }

    public RowGraphicListIndex<T> nextIndex(int _rect, boolean _rev) {
        int y_ = elements.getYcoords();
        int ym_ = y_ + rate(_rev) * _rect;
        int i_ = this.focused.getIndex();
        RowGraphicList<T> curr_ = this.focused.getRow();
        while (rate(_rev) * i_ <= until(_rev)) {
            if (curr_ == null) {
                return new RowGraphicListIndex<T>(null,-1);
            }
            int h_ = elements.getComponent(i_).getHeight();
            if (rate(_rev) * (y_ + rate(_rev) * h_) >= rate(_rev) * ym_) {
                return new RowGraphicListIndex<T>(curr_,i_);
            }
            y_ += rate(_rev) * h_;
            i_ += rate(_rev);
            curr_ = rev(curr_,_rev);
        }
        return new RowGraphicListIndex<T>(bound(_rev),boundIndex(_rev));
    }
    private RowGraphicList<T> bound(boolean _rev) {
        if (elements.getComponentCount() > 0) {
            if (_rev) {
                return first;
            }
            return last;
        }
        return null;
    }

    private int boundIndex(boolean _rev) {
        if (elements.getComponentCount() > 0) {
            return until(_rev);
        }
        return -1;
    }

    private int until(boolean _rev) {
        if (_rev) {
            return 0;
        }
        return elements.getComponentCount() - 1;
    }
    private static int rate(boolean _rev) {
        if (_rev) {
            return -1;
        }
        return 1;
    }

    private void addOrRemoveToSelect(int _index, boolean _selected, RowGraphicList<T> _n) {
        if (_selected && single) {
            deselectAll();
        }
        updateFocus(_n, _index);
        _n.select(_selected);
        fireEvents(_index, _index, false);
        refreshAll();
    }

    private void updateFocus(RowGraphicList<T> _n, int _index) {
        focus(new RowGraphicListIndex<T>(_n, _index));
    }

    private void focus(RowGraphicListIndex<T> _f) {
        if (focused.getRow() == _f.getRow()) {
            return;
        }
        if (focused.getRow() != null) {
            focused.getRow().focus(false);
        }
        focused = _f;
        if (_f.getRow() != null) {
            _f.getRow().focus(true);
        }
    }

    public RowGraphicListIndex<T> getFocused() {
        return focused;
    }

    private void deselectAll() {
        RowGraphicList<T> c_ = first;
        while (c_ != null) {
            c_.select(false);
            c_ = c_.getNext();
        }
    }

    private void refreshAll() {
        RowGraphicList<T> c_ = first;
        int s_ = 0;
        while (c_ != null) {
            refresh(c_, s_);
            c_ = c_.getNext();
            s_++;
        }
    }

    private void refresh(RowGraphicList<T> _elt, int _i) {
        _elt.select(_i, imageFactory,custCellRenderGene, colorGroup());
    }


    public Ints getSelectedIndexes() {
        int s_ = 0;
        Ints arr_ = new Ints();
        RowGraphicList<T> c_ = first;
        while (c_ != null) {
            if (c_.isSelected()) {
                arr_.add(s_);
            }
            s_++;
            c_ = c_.getNext();
        }
        return arr_;
    }
    public void rowCount(int _r) {
        setVisibleRowCount(_r);
        scrollPane.setPreferredSize(GuiBaseUtil.dimension(elements,_r));
    }

    private ColorsGroupList colorGroup() {
        return new ColorsGroupList(elements.getBackgroundValue(), elements.getForegroundValue(), getSelectedBg(), getSelectedFg());
    }

    public AbsScrollPane getScrollPane() {
        return scrollPane;
    }

    public int getVisibleRowCount() {
        return visibleRowCount;
    }

    public void setVisibleRowCount(int _v) {
        this.visibleRowCount = _v;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
        enable(_e);
    }

    public AbsPanel getElements() {
        return elements;
    }

    public IdList<ListSelection> getSelections() {
        return selections;
    }

    public int getSelectedBg() {
        return selectedBg;
    }

    public void setSelectedBg(int _s) {
        this.selectedBg = _s;
    }

    public int getSelectedFg() {
        return selectedFg;
    }

    public void setSelectedFg(int _s) {
        this.selectedFg = _s;
    }
}
