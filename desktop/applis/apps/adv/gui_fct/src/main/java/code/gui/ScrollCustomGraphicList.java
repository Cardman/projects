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
    private final AbsEnabledAction focusDownAction;
    private final AbsEnabledAction focusUpAction;
    private final AbsEnabledAction moveShiftDownAction;
    private final AbsEnabledAction moveShiftUpAction;
    private final AbsEnabledAction movePageUpAction;
    private final AbsEnabledAction movePageDownAction;
    private final AbsEnabledAction focusPageUpAction;
    private final AbsEnabledAction focusPageDownAction;
    private final AbsEnabledAction movePageShiftUpAction;
    private final AbsEnabledAction movePageShiftDownAction;
    private final AbsEnabledAction movePageHomeAction;
    private final AbsEnabledAction movePageEndAction;
    private final AbsEnabledAction focusPageHomeAction;
    private final AbsEnabledAction focusPageEndAction;
    private final AbsEnabledAction movePageShiftHomeAction;
    private final AbsEnabledAction movePageShiftEndAction;
    private final AbsEnabledAction selectAllAction;
    private final AbsEnabledAction addToSelection;
    private final AbsEnabledAction toggleSelection;
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
    private RowGraphicListIndex<T> anchor = new RowGraphicListIndex<T>(null,-1);
    private int selectedFg;
    private int selectedBg;
    public ScrollCustomGraphicList(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<T> _render, boolean _s) {
        compoFactory = _compo;
        imageFactory = _img;
        elements = _compo.newPageBox();
        elements.setAutoscrolls(true);
        elements.setFocusable(true);
        elements.addFocusListener(new RefreshFocusEvent<T>(this));
        elements.addMouseListener(new SelectingGraphicListEvent<T>(this));
        moveDownAction = compoFactory.wrap(new MoveGraphicSelectEvent<T>(this,1));
        elements.registerKeyboardAction(moveDownAction,GuiConstants.VK_DOWN,0);
        moveUpAction = compoFactory.wrap(new MoveGraphicSelectEvent<T>(this, -1));
        elements.registerKeyboardAction(moveUpAction,GuiConstants.VK_UP,0);
        focusDownAction = compoFactory.wrap(new MoveGraphicFocusEvent<T>(this,1));
        elements.registerKeyboardAction(focusDownAction,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        focusUpAction = compoFactory.wrap(new MoveGraphicFocusEvent<T>(this, -1));
        elements.registerKeyboardAction(focusUpAction,GuiConstants.VK_UP,GuiConstants.CTRL_DOWN_MASK);
        moveShiftDownAction = compoFactory.wrap(new MoveGraphicSelectShiftEvent<T>(this, 1));
        elements.registerKeyboardAction(moveShiftDownAction,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        moveShiftUpAction = compoFactory.wrap(new MoveGraphicSelectShiftEvent<T>(this, -1));
        elements.registerKeyboardAction(moveShiftUpAction,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        movePageUpAction = compoFactory.wrap(new MoveGraphicSelectPageEvent<T>(this,1));
        elements.registerKeyboardAction(movePageUpAction,GuiConstants.VK_PAGE_DOWN,0);
        movePageDownAction = compoFactory.wrap(new MoveGraphicSelectPageEvent<T>(this,-1));
        elements.registerKeyboardAction(movePageDownAction,GuiConstants.VK_PAGE_UP,0);
        focusPageUpAction = compoFactory.wrap(new MoveGraphicFocusPageEvent<T>(this,1));
        elements.registerKeyboardAction(focusPageUpAction,GuiConstants.VK_PAGE_DOWN,GuiConstants.CTRL_DOWN_MASK);
        focusPageDownAction = compoFactory.wrap(new MoveGraphicFocusPageEvent<T>(this,-1));
        elements.registerKeyboardAction(focusPageDownAction,GuiConstants.VK_PAGE_UP,GuiConstants.CTRL_DOWN_MASK);
        movePageShiftUpAction = compoFactory.wrap(new MoveGraphicSelectShiftPageEvent<T>(this,1));
        elements.registerKeyboardAction(movePageShiftUpAction,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        movePageShiftDownAction = compoFactory.wrap(new MoveGraphicSelectShiftPageEvent<T>(this,-1));
        elements.registerKeyboardAction(movePageShiftDownAction,GuiConstants.VK_PAGE_UP,GuiConstants.SHIFT_DOWN_MASK);
        movePageHomeAction = compoFactory.wrap(new MoveGraphicSelectBoundEvent<T>(this, -1));
        elements.registerKeyboardAction(movePageHomeAction,GuiConstants.VK_HOME,0);
        movePageEndAction = compoFactory.wrap(new MoveGraphicSelectBoundEvent<T>(this,1));
        elements.registerKeyboardAction(movePageEndAction,GuiConstants.VK_END,0);
        focusPageHomeAction = compoFactory.wrap(new MoveGraphicFocusBoundEvent<T>(this, -1));
        elements.registerKeyboardAction(focusPageHomeAction,GuiConstants.VK_HOME,GuiConstants.CTRL_DOWN_MASK);
        focusPageEndAction = compoFactory.wrap(new MoveGraphicFocusBoundEvent<T>(this,1));
        elements.registerKeyboardAction(focusPageEndAction,GuiConstants.VK_END,GuiConstants.CTRL_DOWN_MASK);
        movePageShiftHomeAction = compoFactory.wrap(new MoveGraphicSelectShiftBoundEvent<T>(this, -1));
        elements.registerKeyboardAction(movePageShiftHomeAction,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        movePageShiftEndAction = compoFactory.wrap(new MoveGraphicSelectShiftBoundEvent<T>(this,1));
        elements.registerKeyboardAction(movePageShiftEndAction,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        selectAllAction = compoFactory.wrap(new MoveGraphicSelectSelectAllEvent<T>(this));
        elements.registerKeyboardAction(selectAllAction,GuiConstants.VK_A,GuiConstants.CTRL_DOWN_MASK);
        addToSelection = compoFactory.wrap(new MoveGraphicSelectAddIntervalEvent<T>(this));
        elements.registerKeyboardAction(addToSelection,GuiConstants.VK_SPACE,0);
        toggleSelection = compoFactory.wrap(new MoveGraphicSelectToggleIntervalEvent<T>(this));
        elements.registerKeyboardAction(toggleSelection,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
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
        focusDownAction.setEnabled(_en);
        focusUpAction.setEnabled(_en);
        moveShiftDownAction.setEnabled(_en);
        moveShiftUpAction.setEnabled(_en);
        movePageUpAction.setEnabled(_en);
        movePageDownAction.setEnabled(_en);
        focusPageUpAction.setEnabled(_en);
        focusPageDownAction.setEnabled(_en);
        movePageShiftUpAction.setEnabled(_en);
        movePageShiftDownAction.setEnabled(_en);
        movePageHomeAction.setEnabled(_en);
        movePageEndAction.setEnabled(_en);
        focusPageHomeAction.setEnabled(_en);
        focusPageEndAction.setEnabled(_en);
        movePageShiftHomeAction.setEnabled(_en);
        movePageShiftEndAction.setEnabled(_en);
        selectAllAction.setEnabled(_en && !single);
        addToSelection.setEnabled(_en);
        toggleSelection.setEnabled(_en);
    }
    public void add(T _i) {
        int s_ = size();
        RowGraphicList<T> elt_ = new RowGraphicList<T>(this,_i, s_, compoFactory, imageFactory, custCellRenderGene, colorGroup());
        append(elt_);
        elements.add(elt_.getLabel());
    }
    public void add(int _index, T _i) {
        if (_index < 0) {
            return;
        }
        RowGraphicList<T> elt_ = new RowGraphicList<T>(this,_i, _index, compoFactory, imageFactory, custCellRenderGene, colorGroup());
        RowGraphicList<T> next_ = getRow(_index);
        if (next_ != null) {
            if (focused.getIndex() > -1 && _index <= focused.getIndex()) {
                focused = new RowGraphicListIndex<T>(focused.getRow(),focused.getIndex()+1);
            }
            if (anchor.getIndex() > -1 && _index <= anchor.getIndex()) {
                anchor = new RowGraphicListIndex<T>(anchor.getRow(),anchor.getIndex()+1);
            }
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
                updateAnchor(current_, null, _index, -1);
                updateFocus(current_, null, _index, -1);
                first = null;
                last = null;
            } else {
                updateAnchor(current_, next_, _index, 0);
                updateFocus(current_, next_, _index, 0);
                first = next_;
                current_.setNext(null);
                next_.setPrevious(null);
            }
        } else {
            updateAnchor(current_, pre_, _index, anchor.getIndex()-1);
            updateFocus(current_, pre_, _index, focused.getIndex()-1);
            if (next_ == null) {
                last = pre_;
                current_.setPrevious(null);
                pre_.setNext(null);
            } else {
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

    private void updateAnchor(RowGraphicList<T> _current, RowGraphicList<T> _repl, int _index, int _i) {
        if (anchor.getRow() == _current) {
            anchor = new RowGraphicListIndex<T>(_repl, _i);
        } else if (anchor.getIndex() > -1 && _index < anchor.getIndex()) {
            anchor = new RowGraphicListIndex<T>(anchor.getRow(),anchor.getIndex()-1);
        }
    }

    private void updateFocus(RowGraphicList<T> _current, RowGraphicList<T> _repl, int _index, int _i) {
        if (focused.getRow() == _current) {
            focused = new RowGraphicListIndex<T>(_repl, _i);
            possibleFocus(_repl);
        } else if (focused.getIndex() > -1 && _index < focused.getIndex()) {
            focused = new RowGraphicListIndex<T>(focused.getRow(),focused.getIndex()-1);
        }
    }

    private void possibleFocus(RowGraphicList<T> _next) {
        if (_next != null) {
            _next.focus(elements.isFocused());
        }
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
        if (sel_.getRow() != null){
            possibleUpdate();
            deselectAll();
            selectRange(sel_.getRow(),sel_.getIndex(),anchor.getRow(), true);
            int ind_ = anchor.getIndex();
            focus(sel_);
            fireEvents(ind_, sel_.getIndex(), false);
            refreshAll();
        }
    }

    public void extendFromAnchor(int _y) {
        RowGraphicListIndex<T> sel_ = selectedCoords(_y);
        if (sel_.getRow() != null) {
            boolean ancSel_ = anchor.getRow() != null && anchor.getRow().isSelected();
            possibleUpdate();
            selectRange(sel_.getRow(),sel_.getIndex(),anchor.getRow(),ancSel_);
            focus(sel_);
            int ind_ = anchor.getIndex();
            fireEvents(ind_, sel_.getIndex(), false);
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
        anchor = new RowGraphicListIndex<T>(_next,_index);
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
            anchor = new RowGraphicListIndex<T>(row_.getRow(), index_);
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
                    anchor = new RowGraphicListIndex<T>(c_,s_);
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

    public void move(int _down) {
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            goBound(-_down);
            return;
        }
        RowGraphicList<T> prev_ = after(foc_,_down);
        if (prev_ == null) {
            return;
        }
        deselectAll();
        int ind_ = this.focused.getIndex();
        anchor = new RowGraphicListIndex<T>(prev_,ind_+ _down);
        updateFocus(prev_, ind_+ _down);
        int ni_ = ind_+ _down;
        prev_.select(true);
        scrollPage(_down, scrollPane.viewRect());
        fireEvents(ni_, ni_, false);
        refreshAll();
    }

    public void moveShift(int _down) {
        if (single) {
            move(_down);
            return;
        }
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            goBound(-_down);
            return;
        }
        RowGraphicList<T> next_ = after(foc_,_down);
        if (next_ == null) {
            return;
        }
        deselectAll();
        int ind_ = this.focused.getIndex();
        possibleUpdate();
        int ni_ = ind_+ _down;
        selectRange(next_, ni_, anchor.getRow(), true);
        updateFocus(next_, ind_+ _down);
        scrollPage(_down, scrollPane.viewRect());
        fireEvents(ni_, ni_, false);
        refreshAll();
    }

    private void possibleUpdate() {
        int indAnc_ = this.anchor.getIndex();
        if (indAnc_ < 0) {
            anchor = new RowGraphicListIndex<T>(boundRow(-1),boundIndex(-1));
        }
    }

    private void selectRange(RowGraphicList<T> _next, int _ni, RowGraphicList<T> _ancRow, boolean _newValue) {
        if (anchor.getIndex() < _ni) {
            loop(_ancRow, _next, _newValue);
        } else if (anchor.getIndex() > _ni){
            loop(_next, _ancRow, _newValue);
        } else {
            _next.select(_newValue);
        }
    }

    private void loop(RowGraphicList<T> _from, RowGraphicList<T> _to, boolean _newValue) {
        RowGraphicList<T> curr_ = _from;
        while (curr_ != _to) {
            curr_.select(_newValue);
            curr_ = curr_.getNext();
        }
        _to.select(_newValue);
    }

    public void moveShiftPage(int _down) {
        if (single) {
            movePage(_down);
            return;
        }
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            goBound(-_down);
            return;
        }
        RowGraphicListIndex<T> next_ = nextIndex(_down);
        if (next_.getRow() == foc_) {
            return;
        }
        deselectAll();
        int ind_ = next_.getIndex();
        int ni_ = this.focused.getIndex();
        possibleUpdate();
        selectRange(next_.getRow(), ind_, anchor.getRow(), true);
        updateFocus(next_.getRow(), ind_);
        scrollPage(_down, scrollPane.viewRect());
        int first_ = NumberUtil.min(ni_-_down,ind_);
        int last_ = NumberUtil.max(ni_-_down,ind_);
        fireEvents(first_, last_, false);
        refreshAll();
    }


    private void scrollPage(int _down, MetaRect _rect) {
        if (_down > 0) {
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


    private RowGraphicList<T> after(RowGraphicList<T> _focused, int _down) {
        RowGraphicList<T> cur_;
        if (_down < 0) {
            cur_ = _focused.getPrevious();
        } else {
            cur_ = _focused.getNext();
        }
        return cur_;
    }
    public void moveShiftBound(int _down) {
        if (single) {
            goBound(_down);
            return;
        }
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            goBound(-_down);
            return;
        }
        RowGraphicList<T> b_ = boundRow(_down);
        if (b_ == foc_) {
            return;
        }
        deselectAll();
        RowGraphicListIndex<T> next_ = new RowGraphicListIndex<T>(b_, boundIndex(_down));
        int ind_ = next_.getIndex();
        possibleUpdate();
        selectRange(next_.getRow(), ind_, anchor.getRow(), true);
        int ni_ = this.focused.getIndex();
        updateFocus(b_, ind_);
        scrollPage(_down, scrollPane.viewRect());
        int first_ = NumberUtil.min(ni_+ _down,ind_);
        int last_ = NumberUtil.max(ni_+ _down,ind_);
        fireEvents(first_, last_, false);
        refreshAll();
    }
    public void addToSelection() {
        if (single) {
            select(focused.getIndex());
            return;
        }
        RowGraphicList<T> f_ = focused.getRow();
        if (f_ == null) {
            return;
        }
        int ind_ = focused.getIndex();
        int ni_ = focused.getIndex();
        selectRange(f_, ind_, focused.getRow(), true);
        int first_ = NumberUtil.min(ni_,ind_);
        int last_ = NumberUtil.max(ni_,ind_);
        fireEvents(first_, last_, false);
        refreshAll();
    }
    public void toggleSelection() {
        RowGraphicList<T> f_ = focused.getRow();
        if (f_ == null) {
            return;
        }
        if (single) {
            addOrRemoveToSelect(focused.getIndex(),!f_.isSelected(),f_);
            return;
        }
        int ind_ = focused.getIndex();
        int ni_ = focused.getIndex();
        selectRange(f_, ind_, focused.getRow(), !f_.isSelected());
        anchor = focused;
        int first_ = NumberUtil.min(ni_,ind_);
        int last_ = NumberUtil.max(ni_,ind_);
        fireEvents(first_, last_, false);
        refreshAll();
    }
    public void goBound(int _down) {
        deselectAll();
        RowGraphicList<T> b_ = boundRow(_down);
        if (b_ != null) {
            anchor = new RowGraphicListIndex<T>(b_,boundIndex(_down));
            updateFocus(b_, boundIndex(_down));
            b_.select(true);
            scrollBound(_down);
            fireEvents(focused.getIndex(), focused.getIndex(), false);
            refreshAll();
        }
    }
    private void scrollBound(int _down) {
        if (_down < 0) {
            scrollPane.setVerticalValue(0);
        } else {
            int bot_ = last.getLabel().getYcoords()+last.getLabel().getHeight();
            int sc_ = scrollPane.viewRect().getHeight();
            scrollPane.setVerticalValue(bot_-sc_);
        }
    }

    public void movePage(int _down) {
        MetaRect rect_ = scrollPane.viewRect();
        RowGraphicListIndex<T> prev_ = nextIndex(_down);
        if (prev_.getRow() == null || prev_.getRow() == focused.getRow()) {
            return;
        }
        deselectAll();
        focus(prev_);
        prev_.getRow().select(true);
        scrollPage(_down, rect_);
        int ind_ = prev_.getIndex();
        fireEvents(ind_, ind_, false);
        refreshAll();
    }

    public void moveFocus(int _down) {
        if (single) {
            move(_down);
            return;
        }
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            focusBound(-_down);
            return;
        }
        RowGraphicList<T> prev_ = after(foc_,_down);
        if (prev_ == null) {
            return;
        }
        int ind_ = this.focused.getIndex();
        updateFocus(prev_, ind_+ _down);
        int ni_ = ind_+ _down;
        scrollPage(_down, scrollPane.viewRect());
        fireEvents(ni_, ni_, false);
        refreshAll();
    }

    public void movePageFocus(int _down) {
        if (single) {
            movePage(_down);
            return;
        }
        MetaRect rect_ = scrollPane.viewRect();
        RowGraphicListIndex<T> prev_ = nextIndex(_down);
        if (prev_.getRow() == null || prev_.getRow() == focused.getRow()) {
            return;
        }
        focus(prev_);
        scrollPage(_down, rect_);
        int ind_ = prev_.getIndex();
        fireEvents(ind_, ind_, false);
        refreshAll();
    }

    public void focusBound(int _down) {
        if (single) {
            goBound(_down);
            return;
        }
        RowGraphicList<T> b_ = boundRow(_down);
        if (b_ != null) {
            anchor = new RowGraphicListIndex<T>(b_,boundIndex(_down));
            updateFocus(b_, boundIndex(_down));
            scrollBound(_down);
            fireEvents(focused.getIndex(), focused.getIndex(), false);
            refreshAll();
        }
    }
    public RowGraphicListIndex<T> nextIndex(int _down) {
        int y_ = elements.getYcoords();
        int ym_ = y_ + _down * scrollPane.viewRect().getHeight();
        int i_ = this.focused.getIndex();
        RowGraphicList<T> curr_ = this.focused.getRow();
        while (_down * i_ <= until(_down)) {
            if (curr_ == null) {
                return new RowGraphicListIndex<T>(null,-1);
            }
            int h_ = elements.getComponent(i_).getHeight();
            if (_down * (y_ + _down * h_) >= _down * ym_) {
                return new RowGraphicListIndex<T>(curr_,i_);
            }
            y_ += _down * h_;
            i_ += _down;
            curr_ = after(curr_,_down);
        }
        return new RowGraphicListIndex<T>(boundRow(_down),boundIndex(_down));
    }
    private RowGraphicList<T> boundRow(int _down) {
        if (elements.getComponentCount() > 0) {
            if (_down < 0) {
                return first;
            }
            return last;
        }
        return null;
    }

    private int boundIndex(int _down) {
        if (elements.getComponentCount() > 0) {
            return until(_down);
        }
        return -1;
    }

    private int until(int _down) {
        if (_down < 0) {
            return 0;
        }
        return elements.getComponentCount() - 1;
    }

    private void addOrRemoveToSelect(int _index, boolean _selected, RowGraphicList<T> _n) {
        if (_selected && single) {
            deselectAll();
        }
        anchor = new RowGraphicListIndex<T>(_n,_index);
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
        possibleFocus(_f.getRow());
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

    void refreshFocused() {
        RowGraphicList<T> r_ = focused.getRow();
        if (r_ != null) {
            r_.focus(elements.isFocused());
            r_.select(focused.getIndex(),imageFactory,custCellRenderGene,colorGroup());
        }
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
