package code.gui;

import code.gui.events.AbsEnabledAction;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaRect;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;
import code.util.core.NumberUtil;

public abstract class ScrollCustomGraphicList<T> implements AbsGenerateImg<T>, Input,SelectableIndexes{
    private final AbsScrollPane scrollPane;
    private final AbsPanel elements;
    private final CustList<AbsEnabledAction> actions = new CustList<AbsEnabledAction>();
    private RowGraphicList<T> first;
    private RowGraphicList<T> last;
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
    private int minChange=-1;
    private int maxChange=-1;
    protected ScrollCustomGraphicList(AbsCompoFactory _compo, AbstractImageFactory _img, boolean _s) {
        compoFactory = _compo;
        imageFactory = _img;
        elements = _compo.newPageBox();
        elements.setAutoscrolls(true);
        elements.setFocusable(true);
        elements.addFocusListener(new RefreshFocusEvent<T>(this));
        elements.addMouseListener(new SelectingGraphicListEvent<T>(this));
        scrollPane = _compo.newAbsScrollPane(elements);
        this.single = _s;
        elements.setBackground(GuiConstants.WHITE);
        elements.setForeground(GuiConstants.BLACK);
        setSelectedBg(GuiConstants.BLUE);
        setSelectedFg(GuiConstants.WHITE);
    }
    protected void buildActions() {
        AbsEnabledAction moveDownAction_ = moveGraphicSelectEvent(1);
        actions.add(moveDownAction_);
        elements.registerKeyboardAction(moveDownAction_,GuiConstants.VK_DOWN,0);
        AbsEnabledAction moveUpAction_ = moveGraphicSelectEvent(-1);
        actions.add(moveUpAction_);
        elements.registerKeyboardAction(moveUpAction_,GuiConstants.VK_UP,0);
        AbsEnabledAction focusDownAction_ = moveGraphicFocusEvent(1);
        actions.add(focusDownAction_);
        elements.registerKeyboardAction(focusDownAction_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction focusUpAction_ = moveGraphicFocusEvent(-1);
        actions.add(focusUpAction_);
        elements.registerKeyboardAction(focusUpAction_,GuiConstants.VK_UP,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction anchorDownAction_ = moveGraphicAnchorEvent(1);
        actions.add(anchorDownAction_);
        elements.registerKeyboardAction(anchorDownAction_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction anchorUpAction_ = moveGraphicAnchorEvent(-1);
        actions.add(anchorUpAction_);
        elements.registerKeyboardAction(anchorUpAction_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction moveShiftDownAction_ = moveGraphicSelectShiftEvent(1);
        actions.add(moveShiftDownAction_);
        elements.registerKeyboardAction(moveShiftDownAction_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        AbsEnabledAction moveShiftUpAction_ = moveGraphicSelectShiftEvent(-1);
        actions.add(moveShiftUpAction_);
        elements.registerKeyboardAction(moveShiftUpAction_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        AbsEnabledAction movePageUpAction_ = moveGraphicSelectPageEvent(1);
        actions.add(movePageUpAction_);
        elements.registerKeyboardAction(movePageUpAction_,GuiConstants.VK_PAGE_DOWN,0);
        AbsEnabledAction movePageDownAction_ = moveGraphicSelectPageEvent(-1);
        actions.add(movePageDownAction_);
        elements.registerKeyboardAction(movePageDownAction_,GuiConstants.VK_PAGE_UP,0);
        AbsEnabledAction focusPageUpAction_ = moveGraphicFocusPageEvent(1);
        actions.add(focusPageUpAction_);
        elements.registerKeyboardAction(focusPageUpAction_,GuiConstants.VK_PAGE_DOWN,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction focusPageDownAction_ = moveGraphicFocusPageEvent(-1);
        actions.add(focusPageDownAction_);
        elements.registerKeyboardAction(focusPageDownAction_,GuiConstants.VK_PAGE_UP,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction anchorPageUpAction_ = moveGraphicAnchorPageEvent(1);
        actions.add(anchorPageUpAction_);
        elements.registerKeyboardAction(anchorPageUpAction_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction anchorPageDownAction_ = moveGraphicAnchorPageEvent(-1);
        actions.add(anchorPageDownAction_);
        elements.registerKeyboardAction(anchorPageDownAction_,GuiConstants.VK_PAGE_UP,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction movePageShiftUpAction_ = moveGraphicSelectShiftPageEvent(1);
        actions.add(movePageShiftUpAction_);
        elements.registerKeyboardAction(movePageShiftUpAction_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        AbsEnabledAction movePageShiftDownAction_ = moveGraphicSelectShiftPageEvent(-1);
        actions.add(movePageShiftDownAction_);
        elements.registerKeyboardAction(movePageShiftDownAction_,GuiConstants.VK_PAGE_UP,GuiConstants.SHIFT_DOWN_MASK);
        AbsEnabledAction movePageHomeAction_ = moveGraphicSelectBoundEvent(-1);
        actions.add(movePageHomeAction_);
        elements.registerKeyboardAction(movePageHomeAction_,GuiConstants.VK_HOME,0);
        AbsEnabledAction movePageEndAction_ = moveGraphicSelectBoundEvent(1);
        actions.add(movePageEndAction_);
        elements.registerKeyboardAction(movePageEndAction_,GuiConstants.VK_END,0);
        AbsEnabledAction focusPageHomeAction_ = moveGraphicFocusBoundEvent(-1);
        actions.add(focusPageHomeAction_);
        elements.registerKeyboardAction(focusPageHomeAction_,GuiConstants.VK_HOME,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction focusPageEndAction_ = moveGraphicFocusBoundEvent(1);
        actions.add(focusPageEndAction_);
        elements.registerKeyboardAction(focusPageEndAction_,GuiConstants.VK_END,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction anchorPageHomeAction_ = moveGraphicAnchorBoundEvent(-1);
        actions.add(anchorPageHomeAction_);
        elements.registerKeyboardAction(anchorPageHomeAction_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction anchorPageEndAction_ = moveGraphicAnchorBoundEvent(1);
        actions.add(anchorPageEndAction_);
        elements.registerKeyboardAction(anchorPageEndAction_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction movePageShiftHomeAction_ = moveGraphicSelectShiftBoundEvent(-1);
        actions.add(movePageShiftHomeAction_);
        elements.registerKeyboardAction(movePageShiftHomeAction_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        AbsEnabledAction movePageShiftEndAction_ = moveGraphicSelectShiftBoundEvent(1);
        actions.add(movePageShiftEndAction_);
        elements.registerKeyboardAction(movePageShiftEndAction_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        AbsEnabledAction selectAllAction_ = moveGraphicSelectAllAction();
        actions.add(selectAllAction_);
        elements.registerKeyboardAction(selectAllAction_,GuiConstants.VK_A,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction deselectAllAction_ = moveGraphicDeselectAllAction();
        actions.add(deselectAllAction_);
        elements.registerKeyboardAction(deselectAllAction_,GuiConstants.VK_W,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction addIntToSelection_ = moveGraphicSelectAddAncIntervalEvent(true);
        actions.add(addIntToSelection_);
        elements.registerKeyboardAction(addIntToSelection_,GuiConstants.VK_B,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction remIntToSelection_ = moveGraphicSelectAddAncIntervalEvent(false);
        actions.add(remIntToSelection_);
        elements.registerKeyboardAction(remIntToSelection_,GuiConstants.VK_N,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction addToSelection_ = moveGraphicAddTo();
        actions.add(addToSelection_);
        elements.registerKeyboardAction(addToSelection_,GuiConstants.VK_SPACE,0);
        AbsEnabledAction toggleSelection_ = moveGraphicToggle();
        actions.add(toggleSelection_);
        elements.registerKeyboardAction(toggleSelection_,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
        AbsEnabledAction extendToSelection_ = moveGraphicExtend();
        actions.add(extendToSelection_);
        elements.registerKeyboardAction(extendToSelection_,GuiConstants.VK_SPACE,GuiConstants.SHIFT_DOWN_MASK);
        AbsEnabledAction changeSelection_ = moveGraphicChange();
        actions.add(changeSelection_);
        elements.registerKeyboardAction(changeSelection_,GuiConstants.VK_SPACE,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
    }
    protected abstract AbsEnabledAction moveGraphicSelectEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicSelectShiftEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicSelectPageEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicSelectBoundEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicSelectShiftBoundEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicAnchorBoundEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicFocusBoundEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicSelectShiftPageEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicAnchorPageEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicFocusPageEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicFocusEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicAnchorEvent(int _d);
    protected abstract AbsEnabledAction moveGraphicSelectAddAncIntervalEvent(boolean _d);
    protected abstract AbsEnabledAction moveGraphicSelectAllAction();
    protected abstract AbsEnabledAction moveGraphicDeselectAllAction();
    protected abstract AbsEnabledAction moveGraphicAddTo();
    protected abstract AbsEnabledAction moveGraphicToggle();
    protected abstract AbsEnabledAction moveGraphicExtend();
    protected abstract AbsEnabledAction moveGraphicChange();
    private void enable(boolean _en) {
        for (AbsEnabledAction a: actions) {
            a.setEnabled(_en);
        }
    }
    public void add(T _i) {
        int s_ = size();
        RowGraphicList<T> elt_ = new RowGraphicList<T>(generate(s_,_i,false,false,false),_i);
        append(elt_);
        elements.add(elt_.getLabel());
    }
    public void add(int _index, T _i) {
        if (_index < 0) {
            return;
        }
        add(_index,generate(_index,_i,false,false,false),_i);
    }
    public void add(int _index, AbsPreparedLabel _label, T _i) {
        add(_index, new RowGraphicList<T>(_label, _i));
    }
    public void add(int _index, RowGraphicList<T> _i) {
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
                _i.setPrevious(pr_);
                pr_.setNext(_i);
            } else {
                first = _i;
            }
            _i.setNext(next_);
            next_.setPrevious(_i);
        } else {
            append(_i);
        }
        elements.add(_i.getLabel(), _index);
    }
    public AbsPreparedLabel generate(int _index, T _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored) {
        AbsPreparedLabel lab_ = preparedLabel();
        lab_.setFont(getElements().getMetaFont());
        lab_.setIcon(getImageFactory(), generateImg(getElements().getMetaFont(), _index, _info, _isSelected, _cellHasFocus, _cellIsAnchored));
        return lab_;
    }

    public AbsPreparedLabel preparedLabel() {
        return compoFactory.newPreparedLabel(imageFactory.newImageRgb(1, 1));
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    public AbstractImageFactory getImageFactory() {
        return imageFactory;
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

    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return elements.getComponentCount();
    }
    public CustList<T> getList(){
        CustList<T> elts_ = new CustList<T>();
        RowGraphicList<T> c_ = first;
        while (c_ != null) {
            elts_.add(c_.getInfo());
            c_ = c_.getNext();
        }
        return elts_;
    }

    public T last() {
        if (last == null) {
            return null;
        }
        return last.getInfo();
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
        next_.setInfo(_i);
        next_.refresh(imageFactory, generateImg(next_.getLabel().getMetaFont(), _index, next_.getInfo(), next_.isSelected(), next_.isFocused(), next_.isAnchored()));
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
    }
    public void clearRevalidate() {
        clear();
        revalidate();
    }
    public void clear() {
        anchor = new RowGraphicListIndex<T>(null,-1);
        focused = new RowGraphicListIndex<T>(null,-1);
        first = null;
        last = null;
        elements.removeAll();
    }

    private void updateAnchor(RowGraphicList<T> _current, RowGraphicList<T> _repl, int _index, int _i) {
        if (anchor.getRow() == _current) {
            anchor = new RowGraphicListIndex<T>(_repl, _i);
            possibleAnchor(_repl);
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
        possibleSynchro();
    }

    private void possibleFocus(RowGraphicList<T> _next) {
        if (_next != null && _next.focus(elements.isFocused())) {
            updateMin(focused.getIndex());
            maxChange = NumberUtil.max(maxChange, focused.getIndex());
        }
    }

    private void possibleAnchor(RowGraphicList<T> _next) {
        if (_next != null && _next.anchor(elements.isFocused())) {
            updateMin(anchor.getIndex());
            maxChange = NumberUtil.max(maxChange, anchor.getIndex());
        }
    }

    public void select(int _index) {
        select(Ints.newList(_index));
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
            focus(sel_);
            events();
        }
    }

    public void extendFromAnchor(int _y) {
        RowGraphicListIndex<T> sel_ = selectedCoords(_y);
        if (sel_.getRow() != null) {
            boolean ancSel_ = ancSet();
            selectRange(sel_.getRow(),sel_.getIndex(),anchor.getRow(),ancSel_);
            focus(sel_);
            events();
        }
    }

    private boolean ancSet() {
        boolean ancSel_ = anchor.getRow() != null && anchor.getRow().isSelected();
        possibleUpdate();
        return ancSel_;
    }

    public void selectCoords(int _y) {
        RowGraphicListIndex<T> sel_ = selectedCoords(_y);
        select(sel_.getIndex(), sel_.getRow());
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

    private void select(int _index, RowGraphicList<T> _next) {
        if (_next == null) {
            return;
        }
        deselectAll();
        updateAnchor(_next, _index);
        updateFocus(_next, _index);
        select(_next,true,_index);
        events();
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
                return;
            }
            int index_ = valid_.last();
            select(row_.getRow(),true,index_);
            updateAnchor(row_.getRow(), index_);
            updateFocus(row_.getRow(), index_);
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
                select(c_,true,s_);
                if (index_ > m_) {
                    updateAnchor(c_, s_);
                    updateFocus(c_, s_);
                    m_ = index_;
                }
            }
            s_++;
            c_ = c_.getNext();
        }
    }

//    public void fireEventsProg() {
//        fireEvents(true);
//    }
    public void fireEvents() {
        SelectionInfo s_ = generateAndSet(false);
        for (ListSelection l: selections) {
            l.valueChanged(s_);
        }
    }
    public SelectionInfo generateAndSet(boolean _meth) {
        int min_ = NumberUtil.min(minChange,maxChange);
        int max_ = NumberUtil.max(minChange,maxChange);
        minChange = -1;
        maxChange = -1;
        return new SelectionInfo(min_, max_, _meth);
    }

    public void addOrRemoveToSelectCoords(int _y) {
        RowGraphicListIndex<T> sel_ = selectedCoords(_y);
        if (sel_.getRow() != null) {
            addOrRemoveToSelect(sel_.getIndex(), !sel_.getRow().isSelected(), sel_.getRow());
        }
    }

    public void deselectAllAction() {
        deselectAll();
        events();
    }
    public void selectAll() {
        if (single) {
            select(focused.getIndex());
            return;
        }
        RowGraphicList<T> c_ = first;
        int s_ = 0;
        while (c_ != null) {
            select(c_,true,s_);
            c_ = c_.getNext();
            s_++;
        }
        events();
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
        updateAnchor(prev_, ind_+ _down);
        updateFocus(prev_, ind_+ _down);
        select(prev_,true,focused.getIndex());
        scrollPage(_down, focused);
        events();
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
        scrollPage(_down, focused);
        events();
    }

    private void possibleUpdate() {
        int indAnc_ = this.anchor.getIndex();
        if (indAnc_ < 0) {
            anchor = new RowGraphicListIndex<T>(boundRow(-1),boundIndex(-1));
        }
    }

    private void selectRange(RowGraphicList<T> _next, int _ni, RowGraphicList<T> _ancRow, boolean _newValue) {
        if (anchor.getIndex() < _ni) {
            loop(_ancRow, _next, _newValue,anchor.getIndex());
        } else if (anchor.getIndex() > _ni){
            loop(_next, _ancRow, _newValue,_ni);
        } else {
            select(_next,_newValue,_ni);
        }
    }

    private void loop(RowGraphicList<T> _from, RowGraphicList<T> _to, boolean _newValue, int _f) {
        RowGraphicList<T> curr_ = _from;
        int s_ = _f;
        while (curr_ != _to) {
            select(curr_,_newValue,s_);
            curr_ = curr_.getNext();
            s_++;
        }
        select(_to,_newValue,s_);
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
        RowGraphicListIndex<T> next_ = nextIndex(_down, this.focused);
        if (next_.getRow() == foc_) {
            return;
        }
        deselectAll();
        int ind_ = next_.getIndex();
        possibleUpdate();
        selectRange(next_.getRow(), ind_, anchor.getRow(), true);
        updateFocus(next_.getRow(), ind_);
        scrollPage(_down, focused);
        events();
    }


    public void scrollPage(int _down) {
        scrollPage(_down, focused);
    }
    private void scrollPage(int _down, RowGraphicListIndex<T> _f) {
        if (_down > 0) {
            scrollPageDown(_f.getRow());
        } else {
            scrollPageUp(_f.getRow());
        }
    }
    private void scrollPageDown(RowGraphicList<T> _dest) {
        if (_dest == null) {
            return;
        }
        MetaRect rect_ = scrollPane.viewRect();
        int bot_ = _dest.getLabel().getYcoords()+ _dest.getLabel().getHeight();
        int sc_ = rect_.getHeight() + rect_.getPoint().getYcoord();
        if (bot_ >= sc_) {
            scrollPane.setVerticalValue(scrollPane.getVerticalValue()+bot_-sc_);
        }
    }

    private void scrollPageUp(RowGraphicList<T> _prev) {
        if (_prev == null) {
            return;
        }
        int top_ = _prev.getLabel().getYcoords();
        int sc_ = scrollPane.viewRect().getPoint().getYcoord();
        if (top_ <= sc_) {
            scrollPane.setVerticalValue(scrollPane.getVerticalValue()+top_-sc_);
        }
    }

    public int forceRefresh() {
        RowGraphicList<T> cu_ = first;
        int s_ = 0;
        while (cu_ != null) {
            cu_.refresh(imageFactory, this.generateImg(cu_.getLabel().getMetaFont(), s_, cu_.getInfo(), cu_.isSelected(), cu_.isFocused(), cu_.isAnchored()));
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
        updateFocus(b_, ind_);
        scrollPage(_down, focused);
        events();
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
        selectRange(f_, ind_, focused.getRow(), true);
        events();
    }

    public void addIntToSelection(boolean _v) {
        RowGraphicList<T> f_ = focused.getRow();
        if (f_ == null) {
            return;
        }
        if (single) {
            addOrRemoveToSelect(focused.getIndex(),_v,f_);
            return;
        }
        int ind_ = focused.getIndex();
        possibleUpdate();
        selectRange(f_, ind_, anchor.getRow(), _v);
        events();
    }

    public void events() {
        fireEvents();
        repaint();
    }
    public void extendTo() {
        RowGraphicList<T> foc_ = this.focused.getRow();
        if (foc_ == null){
            return;
        }
        if (single) {
            select(focused.getIndex());
            return;
        }
        boolean ancSel_ = ancSet();
        deselectAll();
        selectRange(foc_,focused.getIndex(),anchor.getRow(),ancSel_);
        events();
    }
    public void changeSelection() {
        select(focused.getIndex());
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
        selectRange(f_, ind_, focused.getRow(), !f_.isSelected());
        anchor = focused;
        events();
    }
    public void goBound(int _down) {
        deselectAll();
        RowGraphicList<T> b_ = boundRow(_down);
        if (b_ != null) {
            updateAnchor(b_, boundIndex(_down));
            updateFocus(b_, boundIndex(_down));
            select(b_,true,focused.getIndex());
            scrollBound(_down);
            events();
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
        RowGraphicListIndex<T> prev_ = nextIndex(_down, this.focused);
        if (prev_.getRow() == null || prev_.getRow() == focused.getRow()) {
            return;
        }
        deselectAll();
        focus(prev_);
        select(prev_.getRow(),true,prev_.getIndex());
        scrollPage(_down, focused);
        events();
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
        scrollPage(_down, focused);
        events();
    }

    public void moveAnchor(int _down) {
        if (single) {
            move(_down);
            return;
        }
        RowGraphicList<T> foc_ = this.anchor.getRow();
        if (foc_ == null){
            anchorBound(-_down);
            return;
        }
        RowGraphicList<T> prev_ = after(foc_,_down);
        if (prev_ == null) {
            return;
        }
        int ind_ = this.anchor.getIndex();
        updateAnchor(prev_, ind_+ _down);
        scrollPage(_down, anchor);
        events();
    }

    public void movePageFocus(int _down) {
        if (single) {
            movePage(_down);
            return;
        }
        RowGraphicListIndex<T> prev_ = nextIndex(_down, this.focused);
        if (prev_.getRow() == null || prev_.getRow() == focused.getRow()) {
            return;
        }
        focus(prev_);
        scrollPage(_down, focused);
        events();
    }

    public void movePageAnchor(int _down) {
        if (single) {
            movePage(_down);
            return;
        }
        RowGraphicListIndex<T> prev_ = nextIndex(_down, this.anchor);
        if (prev_.getRow() == null || prev_.getRow() == anchor.getRow()) {
            return;
        }
        anchor(prev_);
        scrollPage(_down, anchor);
        events();
    }

    public void focusBound(int _down) {
        if (single) {
            goBound(_down);
            return;
        }
        RowGraphicList<T> b_ = boundRow(_down);
        if (b_ != null) {
            updateFocus(b_, boundIndex(_down));
            scrollBound(_down);
            events();
        }
    }

    public void anchorBound(int _down) {
        if (single) {
            goBound(_down);
            return;
        }
        RowGraphicList<T> b_ = boundRow(_down);
        if (b_ != null) {
            updateAnchor(b_, boundIndex(_down));
            scrollBound(_down);
            events();
        }
    }
    private RowGraphicListIndex<T> nextIndex(int _down, RowGraphicListIndex<T> _fr) {
        int y_ = elements.getYcoords();
        int ym_ = y_ + _down * scrollPane.viewRect().getHeight();
        int i_ = _fr.getIndex();
        RowGraphicList<T> curr_ = _fr.getRow();
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
        if (_index != focused.getIndex()) {
            updateMin(_index);
            maxChange = NumberUtil.max(maxChange,_index);
        }
        updateAnchor(_n, _index);
        updateFocus(_n, _index);
        select(_n,_selected,_index);
        events();
    }

    private void updateAnchor(RowGraphicList<T> _n, int _index) {
        anchor(new RowGraphicListIndex<T>(_n, _index));
    }

    private void updateFocus(RowGraphicList<T> _n, int _index) {
        focus(new RowGraphicListIndex<T>(_n, _index));
    }

    private void focus(RowGraphicListIndex<T> _f) {
        if (focused.getRow() == _f.getRow()) {
            return;
        }
        if (focused.getRow() != null && focused.getRow().focus(false)) {
            updateMin(focused.getIndex());
            maxChange = NumberUtil.max(maxChange, focused.getIndex());
        }
        focused = _f;
        possibleFocus(_f.getRow());
        possibleSynchro();
    }
    private void possibleSynchro() {
        if (single) {
            anchor = focused;
            possibleAnchor(anchor.getRow());
        }
    }

    private void anchor(RowGraphicListIndex<T> _f) {
        if (anchor.getRow() == _f.getRow()) {
            return;
        }
        if (anchor.getRow() != null && anchor.getRow().anchor(false)) {
            updateMin(anchor.getIndex());
            maxChange = NumberUtil.max(maxChange, anchor.getIndex());
        }
        anchor = _f;
        possibleAnchor(_f.getRow());
    }

    public RowGraphicListIndex<T> getFocused() {
        return focused;
    }

    public RowGraphicListIndex<T> getAnchor() {
        return anchor;
    }

    public void deselectAll() {
        RowGraphicList<T> c_ = first;
        int s_ = 0;
        while (c_ != null) {
            select(c_,false,s_);
            c_ = c_.getNext();
            s_++;
        }
    }
    private void select(RowGraphicList<T> _r, boolean _v, int _i) {
        if (_r.select(_v)) {
            updateMin(_i);
            maxChange = NumberUtil.min(maxChange,_i);
        }
    }
    private void updateMin(int _i) {
        if (minChange < 0) {
            minChange = _i;
            return;
        }
        minChange = NumberUtil.min(minChange,_i);
    }
    public void repaint() {
        refreshAll();
    }
    public void revalidate() {
        scrollPane.recalculateViewport();
        elements.recalculate();
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
        if (!_elt.isDirty()) {
            return;
        }
        _elt.refresh(imageFactory, generateImg(_elt.getLabel().getMetaFont(), _i, _elt.getInfo(), _elt.isSelected(), _elt.isFocused(), _elt.isAnchored()));
    }

    void refreshFocused() {
        RowGraphicList<T> r_ = focused.getRow();
        if (r_ != null) {
            r_.focus(elements.isFocused());
            refresh(r_,focused.getIndex());
        }
        RowGraphicList<T> a_ = anchor.getRow();
        if (a_ != null) {
            a_.anchor(elements.isFocused());
            refresh(a_,anchor.getIndex());
        }
    }

    public int getSelectedValuesLsLen() {
        return getSelectedIndexes().size();
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

    public boolean isSelectionEmpty() {
        return getSelectedIndex()==-1;
    }
    public int getSelectedIndex() {
        int s_ = 0;
        RowGraphicList<T> c_ = first;
        while (c_ != null) {
            if (c_.isSelected()) {
                return s_;
            }
            s_++;
            c_ = c_.getNext();
        }
        return -1;
    }

    public void applyRows(){
        scrollPane.setPreferredSize(GuiBaseUtil.dimension(elements,scrollPane.getPreferredSizeValue().getWidth(),visibleRowCount));
    }

    public void addListener(ListSelection _listener){
        selections.add(_listener);
    }


    public void setListener(ListSelection _listener){
        selections.clear();
        if (_listener == null) {
            return;
        }
        selections.add(_listener);
    }

    public void removeListener(ListSelection _listener){
        selections.removeObj(_listener);
    }
    protected ColorsGroupList colorGroup() {
        return new ColorsGroupList(elements.getBackgroundValue(), elements.getForegroundValue(), getSelectedBg(), getSelectedFg());
    }

    public AbsScrollPane getScrollPane() {
        return scrollPane;
    }

    @Override
    public AbsCustComponent getGlobal() {
        return getScrollPane();
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
