package code.gui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import code.util.CustList;
import code.util.Numbers;

public class GraphicList<T> extends CustComponent implements GraphicListable {

    private CustList<T> list;
    private CustList<JComponent> listComponents = new CustList<JComponent>();
    private CustList<IndexableListener> indexableMouse = new CustList<IndexableListener>();
    private CustList<IndexableListener> indexableKey = new CustList<IndexableListener>();
    private Numbers<Integer> selectedIndexes = new Numbers<Integer>();

    private CustCellRender render;

    private ListSelection listener;

    private JPanel panel;
    private JScrollPane scroll;

    private int firstIndex = -1;

    private int lastIndex = -1;

    private boolean simple;

    private int visibleRowCount = 8;

    private boolean owned;

    public GraphicList(boolean _owned, boolean _simple, T... _objects) {
        this(_owned, _simple, new Numbers<Integer>(), _objects);
    }

    public GraphicList(boolean _owned, boolean _simple, Numbers<Integer> _selectedIndexes, T... _objects) {
        selectedIndexes = new Numbers<Integer>(selectedIndexes);
        owned = _owned;
        list = new CustList<T>(_objects);
        simple = _simple;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setAutoscrolls(true);
        scroll = new JScrollPane(panel);
        buildList();
    }

    protected void buildList() {
        rebuild();
    }

    public void add(T _elt) {
        add(list.size(),_elt);
    }
    public void add(int _index, T _elt) {
        list.add(_index, _elt);
        JPanel panel_ = getPanel();
        JLabel lab_ = new JLabel();
        listComponents.add(_index, lab_);
        panel_.add(lab_, _index);
        CustCellRender r_ = getRender();
        if (r_ != null) {
            JLabel c_ = r_.getListCellRendererComponent(this, _elt, _index, false, false);
            r_.paintComponent(c_);
        }
        resetDimensions();
        if (!simple) {
            MultSelectKeyEltList i_ = new MultSelectKeyEltList(this, _index);
            lab_.addKeyListener(i_);
            indexableKey.add(i_);
            MultSelectEltList j_ = new MultSelectEltList(this, _index);
            lab_.addMouseListener(j_);
            indexableMouse.add(j_);
            reindex(indexableMouse);
            reindex(indexableKey);
        } else {
            SimpleSelectEltList i_ = new SimpleSelectEltList(this, _index);
            lab_.addMouseListener(i_);
            indexableMouse.add(i_);
            reindex(indexableMouse);
        }
    }
    public void clear() {
        list.clear();
        JPanel panel_ = getPanel();
        listComponents.clear();
        panel_.removeAll();
        selectedIndexes.clear();
        indexableKey.clear();
        indexableMouse.clear();
    }
    public void clearRevalidate() {
        list.clear();
        JPanel panel_ = getPanel();
        listComponents.clear();
        panel_.removeAll();
        selectedIndexes.clear();
        panel_.revalidate();
        panel_.repaint();
        scroll.revalidate();
        indexableKey.clear();
        indexableMouse.clear();
    }
    public void remove(int _index) {
        list.remove(_index);
        JPanel panel_ = getPanel();
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
    public void rebuild() {
        CustCellRender r_ = getRender();
        if (r_ == null) {
            return;
        }
        JPanel panel_ = getPanel();
        panel_.removeAll();
        indexableMouse.clear();
        indexableKey.clear();
        int index_ = 0;
        for (Object o: getList()) {
            JLabel lab_ = new JLabel();
            listComponents.add(lab_);
            panel_.add(lab_);
            JLabel c_ = r_.getListCellRendererComponent(this, o, index_, selectedIndexes.containsObj(index_), false);
            r_.paintComponent(c_);
            index_++;
        }
        index_ = 0;
        if (simple) {
            for (Component c: listComponents) {
                SimpleSelectEltList i_ = new SimpleSelectEltList(this, index_);
                indexableMouse.add(i_);
                c.addMouseListener(i_);
                index_++;
            }
        } else {
            for (Component c: listComponents) {
                MultSelectKeyEltList i_ = new MultSelectKeyEltList(this, index_);
                indexableKey.add(i_);
                c.addKeyListener(i_);
                MultSelectEltList j_ = new MultSelectEltList(this, index_);
                indexableMouse.add(j_);
                c.addMouseListener(j_);
                index_++;
            }
        }
        resetDimensions();
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
    @Override
    public void addRange() {
        int min_ = Math.min(firstIndex, lastIndex);
        int max_ = Math.max(firstIndex, lastIndex);
        for (int i = min_; i <= max_; i++) {
            selectedIndexes.add(i);
        }
    }
    @Override
    public void clearAllRange() {
        selectedIndexes.clear();
    }
    @Override
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
        CustCellRender r_ = getRender();
        int index_ = 0;
        Object[] array_ = getList().toArray();
        for (Object v: array_) {
            JLabel c_;
            c_ = r_.getListCellRendererComponent(this, v, index_, selectedIndexes.containsObj(index_), false);
            r_.paintComponent(c_);
            index_++;
        }
        SelectionEvent sel_ = new SelectionEvent(min_, max_, this);
        SwingUtilities.invokeLater(sel_);
    }
    public void setSelectedIndice(int _min) {
        selectedIndexes.add(_min);
        selectedIndexes.removeDuplicates();
        CustCellRender r_ = getRender();
        int index_ = 0;
        Object[] array_ = getList().toArray();
        for (Object v: array_) {
            JLabel c_;
            c_ = r_.getListCellRendererComponent(this, v, index_, selectedIndexes.containsObj(index_), false);
            r_.paintComponent(c_);
            index_++;
        }
        SelectionEvent sel_ = new SelectionEvent(_min, _min, this);
        SwingUtilities.invokeLater(sel_);
    }
    public void clearSelection() {
        CustCellRender r_ = getRender();
        int index_ = 0;
        Object[] array_ = getList().toArray();
        for (Object v: array_) {
            JLabel c_;
            c_ = r_.getListCellRendererComponent(this, v, index_, false, false);
            r_.paintComponent(c_);
            index_++;
        }
        setFirstIndex(0);
        setLastIndex(array_.length);
        clearRange();
        setFirstIndex(-1);
        setLastIndex(-1);
        SelectionEvent sel_ = new SelectionEvent(0, array_.length, this);
        SwingUtilities.invokeLater(sel_);
    }
    @Override
    public Numbers<Integer> getSelectedIndexes() {
        return selectedIndexes;
    }
    @Override
    public int getMaxWidth() {
        return 0;
    }
    protected void resetDimensions(){
        JPanel panel_ = getPanel();
        int width_ = getMaxWidth();
        for (Component c: getListComponents()) {
            width_ = Math.max(width_, c.getPreferredSize().width);
        }
        int h_ = 0;
        int c_ = 0;
        for (Component c: getListComponents()) {
            h_ = c.getPreferredSize().height;
            c.setPreferredSize(new Dimension(width_, h_));
            c_++;
        }
        scroll.setPreferredSize(new Dimension(width_ + 4, (h_ + 2)* Math.min(c_, visibleRowCount)));
        panel_.revalidate();
        scroll.revalidate();
        panel_.repaint();
    }
    protected static void reindex(CustList<IndexableListener> _list) {
        int index_ = 0;
        for (IndexableListener c: _list) {
            c.setIndex(index_);
            index_++;
        }
    }

    @Override
    public ListSelection getListener() {
        return listener;
    }

    @Override
    public void setListener(ListSelection _listener) {
        listener = _listener;
    }

    @Override
    public CustCellRender getRender() {
        return render;
    }
    @Override
    public void setRender(CustCellRender _render) {
        render = _render;
    }
    @Override
    public CustList<T> getList() {
        return list;
    }

    @Override
    public int getFirstIndex() {
        return firstIndex;
    }

    @Override
    public void setFirstIndex(int _firstIndex) {
        firstIndex = _firstIndex;
    }

    @Override
    public int getLastIndex() {
        return lastIndex;
    }

    @Override
    public void setLastIndex(int _lastIndex) {
        lastIndex = _lastIndex;
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public JScrollPane getScroll() {
        return scroll;
    }

    @Override
    public CustList<JComponent> getListComponents() {
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
    public JComponent getComponent() {
        return scroll;
    }
}
