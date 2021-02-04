package code.gui;

import java.awt.Dimension;

import javax.swing.*;

import code.util.CustList;
import code.util.Ints;

public class GraphicList<T> extends CustComponent implements AbsGraphicList<T> {

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

    public GraphicList(boolean _simple) {
        this(_simple, new Ints(), new CustList<T>());
        rebuild();
    }

    protected GraphicList(boolean _simple, Ints _selectedIndexes, CustList<T> _objects) {
        selectedIndexes = new Ints(_selectedIndexes);
        list = new CustList<T>(_objects);
        simple = _simple;
        panel = Panel.newPageBox();
        panel.setAutoscrolls(true);
        scroll = new ScrollPane(panel);
    }

    protected GraphicList(boolean _simple, Ints _selectedIndexes, CustList<T> _objects, int _visible) {
        selectedIndexes = new Ints(_selectedIndexes);
        visibleRowCount = _visible;
        list = new CustList<T>(_objects);
        simple = _simple;
        panel = Panel.newPageBox();
        panel.setAutoscrolls(true);
        scroll = new ScrollPane(panel);
    }

    protected void setList(CustList<T> _list) {
        list = _list;
    }
    public void selectEvent(int _firstIndex, int _lastIndex, boolean _methodAction) {
        ListSelection listener_ = getListener();
        if (listener_ == null) {
            return;
        }
        int min_ = Math.min(_firstIndex, _lastIndex);
        int max_ = Math.max(_firstIndex, _lastIndex);
        SelectionInfo ev_ = new SelectionInfo(min_, max_, _methodAction);
        listener_.valueChanged(ev_);
    }
    public void add(T _elt) {
        add(list.size(),_elt);
    }
    public void add(int _index, T _elt) {
        list.add(_index, _elt);
        Panel panel_ = getPanel();
        PreparedLabel lab_ = new PreparedLabel();
        listComponents.add(_index, lab_);
        panel_.add(lab_, _index);
        repaintAdded(_index);
        resetDimensions();
        if (!simple) {
            MultSelectKeyEltList<T> i_ = new MultSelectKeyEltList<T>(this, _index);
            lab_.addKeyListener(i_);
            indexableKey.add(i_);
            MultSelectEltList<T> j_ = new MultSelectEltList<T>(this, _index);
            lab_.addMouseListener(j_);
            indexableMouse.add(j_);
            reindex(indexableMouse);
            reindex(indexableKey);
        } else {
            IndexableListener i_ = buildSingleSelect(lab_,_index);
            indexableMouse.add(i_);
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
        SimpleSelectEltList<T> i_ = new SimpleSelectEltList<T>(this, _index);
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
                MultSelectKeyEltList<T> i_ = new MultSelectKeyEltList<T>(this, index_);
                indexableKey.add(i_);
                c.addKeyListener(i_);
                MultSelectEltList<T> j_ = new MultSelectEltList<T>(this, index_);
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
    }

    public void addRange() {
        int min_ = Math.min(firstIndex, lastIndex);
        int max_ = Math.max(firstIndex, lastIndex);
        for (int i = min_; i <= max_; i++) {
            selectedIndexes.add(i);
        }
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
        selectedIndexes.add(_min);
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

    protected static void reindex(CustList<IndexableListener> _list) {
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
}
