package code.gui;

import java.awt.Dimension;

import javax.swing.*;

import code.util.CustList;
import code.util.Ints;

public class GraphicList<T> extends CustComponent implements AbsGraphicList<T>,AbsGraphicListDefBase {

    private final AbsGraphicListPainter graphicListPainter;
    private CustList<T> list;
    private final CustList<PreparedLabel> listComponents = new CustList<PreparedLabel>();
    private final CustList<IndexableListener> indexableMouse = new CustList<IndexableListener>();
    private final CustList<IndexableListener> indexableKey = new CustList<IndexableListener>();
    private final Ints selectedIndexes;

    private CustCellRender<T> render;

    private ListSelection listener;

    private final Panel panel;
    private final ScrollPane scroll;

    private int firstIndex = -1;

    private int lastIndex = -1;

    private final boolean simple;

    private int visibleRowCount = 8;

    private boolean enabled = true;

    public GraphicList(boolean _simple, AbsGraphicListPainter _graphicListPainter) {
        this(_simple, new Ints(), new CustList<T>(), _graphicListPainter);
        rebuild();
    }

    protected GraphicList(boolean _simple, Ints _selectedIndexes, CustList<T> _objects, AbsGraphicListPainter _graphicListPainter) {
        selectedIndexes = new Ints(_selectedIndexes);
        list = new CustList<T>(_objects);
        simple = _simple;
        panel = Panel.newPageBox();
        panel.setAutoscrolls(true);
        scroll = new ScrollPane(panel);
        graphicListPainter = _graphicListPainter;
    }

    protected GraphicList(boolean _simple, Ints _selectedIndexes, CustList<T> _objects, int _visible) {
        selectedIndexes = new Ints(_selectedIndexes);
        visibleRowCount = _visible;
        list = new CustList<T>(_objects);
        simple = _simple;
        panel = Panel.newPageBox();
        panel.setAutoscrolls(true);
        scroll = new ScrollPane(panel);
        graphicListPainter = new DefaultGraphicListPainter();
    }

    protected void setList(CustList<T> _list) {
        list = _list;
    }
    public void selectEvent(int _firstIndex, int _lastIndex, boolean _methodAction) {
        ListSelection listener_ = getListener();
        selectEvent(_firstIndex, _lastIndex, _methodAction, listener_);
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

    public void add(T _elt) {
        add(list.size(),_elt);
    }
    public void add(int _index, T _elt) {
        list.add(_index, _elt);
        PreparedLabel lab_ = new PreparedLabel();
        addLab(_index, lab_);
    }

    @Override
    public void add(int _index, PreparedLabel _lab, T _elt) {
        list.add(_index, _elt);
        simpleAddLab(_index, _lab);
        addListeners(_index, _lab);
        updateGraphics();
    }

    public void addLab(int _index, PreparedLabel _lab) {
        simpleAddLab(_index, _lab);
        repaintAdded(_index);
        resetDimensions();
        addListeners(_index, _lab);
    }

    public void addListeners(int _index, PreparedLabel _lab) {
        if (!simple) {
            MultSelectKeyEltList i_ = new MultSelectKeyEltList(this, _index, graphicListPainter);
            i_.setSelection(listener);
            _lab.addKeyListener(i_);
            indexableKey.add(i_);
            MultSelectEltList j_ = new MultSelectEltList(this, _index, graphicListPainter);
            j_.setSelection(listener);
            _lab.addMouseListener(j_);
            indexableMouse.add(j_);
            reindex(indexableMouse);
            reindex(indexableKey);
        } else {
            IndexableListener i_ = buildSingleSelect(_lab, _index);
            indexableMouse.add(i_);
            reindex(indexableMouse);
        }
    }

    public void simpleAddLab(int _index, PreparedLabel _lab) {
        Panel panel_ = getPanel();
        listComponents.add(_index, _lab);
        panel_.add(_lab, _index);
    }
    public void set(int _index, T _elt) {
        PreparedLabel lab_ = new PreparedLabel();
        set(_index,lab_,_elt);
    }
    public void set(int _index, PreparedLabel _lab, T _elt) {
        if (!list.isValidIndex(_index)) {
            return;
        }
        panel.remove(_index);
        panel.add(_lab,_index);
        list.set(_index, _elt);
        listComponents.set(_index, _lab);
        repaintAdded(_index);
        if (!simple) {
            MultSelectKeyEltList i_ = new MultSelectKeyEltList(this, _index, graphicListPainter);
            i_.setSelection(listener);
            _lab.addKeyListener(i_);
            indexableKey.set(_index,i_);
            MultSelectEltList j_ = new MultSelectEltList(this, _index, graphicListPainter);
            j_.setSelection(listener);
            _lab.addMouseListener(j_);
            indexableMouse.set(_index,j_);
            reindex(indexableMouse);
            reindex(indexableKey);
        } else {
            IndexableListener i_ = buildSingleSelect(_lab, _index);
            indexableMouse.set(_index,i_);
            reindex(indexableMouse);
        }
    }
    protected void repaintAdded(int _index) {
        CustCellRender<T> r_ = getRender();
        if (r_ != null) {
            r_.setList(list);
            PreparedLabel c_ = listComponents.get(_index);
            r_.getListCellRendererComponent(c_, _index, false, false);
            r_.paintComponent(c_);
        }
    }
    protected IndexableListener buildSingleSelect(PreparedLabel _lab,int _index) {
        SimpleSelectEltList i_ = new SimpleSelectEltList(this, _index, graphicListPainter);
        i_.setSelection(listener);
        _lab.addMouseListener(i_);
        return i_;
    }
    public void clear() {
        list.clear();
        Panel panel_ = getPanel();
        listComponents.clear();
        panel_.removeAll();
        selectedIndexes.clear();
        indexableKey.clear();
        indexableMouse.clear();
    }
    public void clearRevalidate() {
        clear();
        scroll.revalidate();
    }
    public void remove(int _index) {
        list.remove(_index);
        Panel panel_ = getPanel();
        listComponents.remove(_index);
        panel_.remove(_index);
        selectedIndexes.removeObj(_index);
        resetDimensions();
        
        if (!simple) {
            indexableKey.remove(_index);
            indexableMouse.remove(_index);
            reindex(indexableMouse);
            reindex(indexableKey);
        } else {
            indexableMouse.remove(_index);
            reindex(indexableMouse);
        }
        updateGraphics();
    }
    public final void rebuild() {
        CustCellRender<T> r_ = getRender();
        if (r_ == null) {
            return;
        }
        Panel panel_ = getPanel();
        panel_.removeAll();
        indexableMouse.clear();
        indexableKey.clear();
        repaintAll();
        if (simple) {
            int index_ = 0;
            for (PreparedLabel c: listComponents) {
                IndexableListener i_ = buildSingleSelect(c,index_);
                indexableMouse.add(i_);
                index_++;
            }
        } else {
            int index_ = 0;
            for (PreparedLabel c: listComponents) {
                MultSelectKeyEltList i_ = new MultSelectKeyEltList(this, index_, graphicListPainter);
                i_.setSelection(listener);
                indexableKey.add(i_);
                c.addKeyListener(i_);
                MultSelectEltList j_ = new MultSelectEltList(this, index_, graphicListPainter);
                j_.setSelection(listener);
                indexableMouse.add(j_);
                c.addMouseListener(j_);
                index_++;
            }
        }
        resetDimensions();
    }
    protected void repaintAll() {
        CustCellRender<T> r_ = getRender();
        int index_ = 0;
        Panel panel_ = getPanel();
        r_.setList(list);
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            PreparedLabel lab_ = new PreparedLabel();
            listComponents.add(lab_);
            panel_.add(lab_);
            PreparedLabel c_ = listComponents.get(index_);
            r_.getListCellRendererComponent(c_, index_, selectedIndexes.containsObj(index_), false);
            r_.paintComponent(c_);
            index_++;
        }
    }
    public int getVisibleRowCount() {
        return visibleRowCount;
    }
    public void setVisibleRowCount(int _visibleRowCount) {
        visibleRowCount = _visibleRowCount;
        if (visibleRowCount <= 0) {
            visibleRowCount = 1;
        }
        resetDimensions();
        updateGraphics();
    }

    public void addRange() {
        int min_ = Math.min(firstIndex, lastIndex);
        int max_ = Math.max(firstIndex, lastIndex);
        for (int i = min_; i <= max_; i++) {
            selectedIndexes.add(i);
        }
        selectedIndexes.removeDuplicates();
    }

    @Override
    public void updateGraphics() {
        int width_ = 0;
        for (PreparedLabel c: listComponents) {
            width_ = Math.max(width_, c.getWidth());
        }
        int h_ = 0;
        int c_ = 0;
        for (PreparedLabel c: listComponents) {
            h_ = Math.max(h_,c.getHeight());
            c_++;
        }
        scroll.setPreferredSize(new Dimension(width_ + 24, (h_ + 2)* Math.min(c_, visibleRowCount)));
        scroll.revalidate();
    }

    public void clearAllRange() {
        selectedIndexes.clear();
    }

    public void clearRange() {
        int min_ = Math.min(firstIndex, lastIndex);
        int max_ = Math.max(firstIndex, lastIndex);
        for (int i = min_; i <= max_; i++) {
            selectedIndexes.removeObj(i);
        }
    }
    public void setSelectedIndexes(int _min, int _max) {
        if (simple) {
            return;
        }
        int min_ = Math.min(_min, _max);
        int max_ = Math.min(_min, _max);
        for (int i = min_; i < max_; i++) {
            selectedIndexes.add(i);
        }
        selectedIndexes.removeDuplicates();
        CustCellRender<T> r_ = getRender();
        int index_ = 0;
        r_.setList(list);
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            PreparedLabel c_ = listComponents.get(index_);
            r_.getListCellRendererComponent(c_, index_, selectedIndexes.containsObj(index_), false);
            r_.paintComponent(c_);
            index_++;
        }
    }
    public void setSelectedIndice(int _min) {
        selectedIndexes.clear();
        if (_min > -1) {
            selectedIndexes.add(_min);
        }
        selectedIndexes.removeDuplicates();
        CustCellRender<T> r_ = getRender();
        r_.setList(list);
        int index_ = 0;
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            PreparedLabel c_ = listComponents.get(index_);
            r_.getListCellRendererComponent(c_, index_, selectedIndexes.containsObj(index_), false);
            r_.paintComponent(c_);
            index_++;
        }
    }

    @Override
    public void setSelectedIndexes(Ints _values) {
        selectedIndexes.clear();
        selectedIndexes.addAllElts(_values);
        selectedIndexes.removeDuplicates();
        CustCellRender<T> r_ = getRender();
        r_.setList(list);
        int index_ = 0;
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            PreparedLabel c_ = listComponents.get(index_);
            r_.getListCellRendererComponent(c_, index_, selectedIndexes.containsObj(index_), false);
            r_.paintComponent(c_);
            index_++;
        }
    }

    @Override
    public boolean selectOneAmongIntervalPaint(boolean _sel, int _index) {
        if (!enabled) {
            return false;
        }
        setFirstIndex(_index);
        setLastIndex(_index);
        CustCellRender<T> r_ = getRender();
        PreparedLabel c_ = getListComponents().get(_index);
        r_.setList(getList());
        r_.getListCellRendererComponent(c_, _index, _sel, false);
        c_.requestFocus();
        r_.paintComponent(c_);
        if (_sel) {
            addRange();
        } else {
            clearRange();
        }
        return true;
    }

    @Override
    public Interval selectIntervalKeyPaint(boolean _sel, int _index) {
        if (!enabled) {
            return null;
        }
        CustCellRender<T> r_ = getRender();
        r_.setList(getList());
        int index_ = 0;
        CustList<T> array_ = getList();
        int len_ = array_.size();
        for (int i = 0; i < len_; i++) {
            PreparedLabel c_ = getListComponents().get(index_);
            r_.getListCellRendererComponent(c_, index_, _sel, false);
            r_.paintComponent(c_);
            index_++;
        }
        if (!_sel) {
            setFirstIndex(0);
            setLastIndex(array_.size());
            clearRange();
            setFirstIndex(-1);
            setLastIndex(-1);
        } else {
            setFirstIndex(0);
            setLastIndex(array_.size()-1);
            addRange();
        }
        return new Interval(0,array_.size());
    }

    @Override
    public Interval selectIntervalPaint(boolean _sel, int _index) {
        setLastIndex(_index);
        int min_ = Math.min(getFirstIndex(), getLastIndex());
        int max_ = Math.max(getFirstIndex(), getLastIndex());
        CustCellRender<T> r_ = getRender();
        r_.setList(getList());
        for (int i = min_; i <= max_; i++) {
            PreparedLabel c_ = getListComponents().get(i);
            r_.getListCellRendererComponent(c_, i, _sel, false);
            r_.paintComponent(c_);
        }
        if (_sel) {
            addRange();
        } else {
            clearRange();
        }
        return new Interval(min_,max_);
    }

    @Override
    public Interval selectIntervalPaintBase(boolean _sel, int _index) {
        int firstIndex_ = getFirstIndex();
        int min_ = Math.min(firstIndex_, _index);
        int max_ = Math.max(firstIndex_, _index);
        if (!_sel) {
            for (int i = min_; i <= max_; i++) {
                getSelectedIndexes().removeObj(i);
            }
        } else {
            for (int i = min_; i <= max_; i++) {
                getSelectedIndexes().add(i);
            }
            getSelectedIndexes().removeDuplicates();
        }
        return new Interval(min_,max_);
    }

    @Override
    public void selectOneAmongIntervalPaintBase(boolean _sel, int _index) {
        setFirstIndex(_index);
        if (!_sel) {
            getSelectedIndexes().removeObj(_index);
        } else {
            getSelectedIndexes().add(_index);
            getSelectedIndexes().removeDuplicates();
        }
    }

    public void clearSelection() {
        CustCellRender<T> r_ = getRender();
        int index_ = 0;
        CustList<T> copy_ = new CustList<T>(list);
        r_.setList(list);
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            PreparedLabel c_ = listComponents.get(index_);
            r_.getListCellRendererComponent(c_, index_, false, false);
            r_.paintComponent(c_);
            index_++;
        }
        setFirstIndex(0);
        setLastIndex(copy_.size());
        clearRange();
        setFirstIndex(-1);
        setLastIndex(-1);
    }

    public Ints getSelectedIndexes() {
        return selectedIndexes;
    }

    public int getMaxWidth() {
        return getBasicMaxWidth(0);
    }

    protected void resetDimensions(){
        int width_ = getMaxWidth();
        width_ = getBasicMaxWidth(width_);
        int h_ = 0;
        int c_ = 0;
        for (PreparedLabel c: getListComponents()) {
            h_ = c.getPreferredSize().height;
            c.setPreferredSize(new Dimension(width_, h_));
            c_++;
        }
        scroll.setPreferredSize(new Dimension(width_ + 24, (h_ + 2)* Math.min(c_, visibleRowCount)));
        scroll.revalidate();
    }

    private int getBasicMaxWidth(int _width) {
        int width_ = _width;
        for (PreparedLabel c: getListComponents()) {
            width_ = Math.max(width_, c.getPreferredSize().width);
        }
        return width_;
    }

    protected void reindex(CustList<IndexableListener> _list) {
        int index_ = 0;
        for (IndexableListener c: _list) {
            c.setIndex(index_);
            index_++;
        }
    }

    public ListSelection getListener() {
        return listener;
    }

    public void setListener(ListSelection _listener) {
        for (IndexableListener i: indexableKey) {
            i.setSelection(_listener);
        }
        for (IndexableListener i: indexableMouse) {
            i.setSelection(_listener);
        }
        simpleSetListener(_listener);
    }

    public void simpleSetListener(ListSelection _listener) {
        listener = _listener;
    }

    public CustCellRender<T> getRender() {
        return render;
    }

    public void setRender(CustCellRender<T> _render) {
        render = _render;
    }

    public CustList<T> getList() {
        return list;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int _firstIndex) {
        firstIndex = _firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int _lastIndex) {
        lastIndex = _lastIndex;
    }

    public Panel getPanel() {
        return panel;
    }

    public ScrollPane getScroll() {
        return scroll;
    }

    public CustList<PreparedLabel> getListComponents() {
        return listComponents;
    }

    public int getSelectedIndex() {
        if (selectedIndexes.isEmpty()) {
            return -1;
        }
        return selectedIndexes.first();
    }

    public T getSelectedValue() {
        if (selectedIndexes.isEmpty()) {
            return null;
        }
        return list.get(selectedIndexes.first());
    }

    public CustList<T> getSelectedValuesLs() {
        CustList<T> list_ = new CustList<T>();
        for (int i: selectedIndexes) {
            list_.add(get(i));
        }
        return list_;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _enabled) {
        enabled = _enabled;
    }

    public T get(int _i) {
        return list.get(_i);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T last() {
        return list.last();
    }

    public boolean isSelectionEmpty() {
        return selectedIndexes.isEmpty();
    }

    public int size() {
        return list.size();
    }

    @Override
    protected JComponent getComponent() {
        return scroll.getComponent();
    }

    @Override
    public CustComponent self() {
        return this;
    }

    @Override
    public CustComponent scroll() {
        return scroll;
    }

    @Override
    public CustComponent visible() {
        return panel;
    }

    public AbsGraphicListPainter getGraphicListPainter() {
        return graphicListPainter;
    }
}
