package code.gui;
import javax.swing.ListCellRenderer;
import code.util.CustList;
import code.util.Ints;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Dimension;

public class CustGrList<T> extends CustComponent implements AbsGraphicList<T> {

	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
	private final JScrollPane scroll = new JScrollPane(list);
	private CustList<T> elts = new CustList<T>();
	private CustCellRender<T> inner;
	private CustSelList<T> innerTwo;

    private ListSelection listener;

    public ListSelection getListener() {
        return listener;
    }

    public void setListener(final ListSelection _listener) {
        listener = _listener;
		list.addListSelectionListener(new LocalListSelectionListener(_listener));
    }

    public CustCellRender<T> getRender() {return inner;}
    public void setRender(CustCellRender<T> _render) {
		CustSelList<T> r_ = new CustSelList<T>();
		r_.setRender(_render);
		r_.setList(elts);
		innerTwo = r_;
		inner = _render;
		list.setCellRenderer(r_);
    }

    public void add(T _elt) {
        add(elts.size(),_elt);
    }
    public void add(int _index, T _elt) {
        elts.add(_index, _elt);
		model.add(_index, _elt);
    }

    public void clear() {
        elts.clear();
        model.clear();
    }
    public void clearRevalidate() {
        clear();
        scroll.revalidate();
    }
    public void remove(int _index) {
        model.remove(_index);
        elts.remove(_index);
    }

    public int getVisibleRowCount() {
        return list.getVisibleRowCount();
    }
    public void setVisibleRowCount(int _visibleRowCount) {
        int visibleRowCount_ = _visibleRowCount;
        if (visibleRowCount_ <= 0) {
            visibleRowCount_ = 1;
        }
		list.setVisibleRowCount(visibleRowCount_);
    }
	protected void resetDimensions(){
		if (inner == null){
			return;
		}
        int h_ = inner.getHeight();
        int width_ = inner.getWidth();
        scroll.setPreferredSize(new Dimension(width_ + 24, (h_ + 2)* Math.min(size(),list.getVisibleRowCount())));
        scroll.revalidate();
    }

    public void clearAllRange() {
        list.clearSelection();
    }

    public void setSelectedIndexes(int _min, int _max) {
        if (list.getSelectionMode() == ListSelectionModel.SINGLE_SELECTION) {
            return;
        }
        int min_ = Math.min(_min, _max);
        int max_ = Math.min(_min, _max);
		list.addSelectionInterval(min_,max_);
    }
    public void setSelectedIndice(int _min) {
        list.setSelectedIndex(_min);
    }
    public void clearSelection() {
		list.clearSelection();
    }

    public Ints getSelectedIndexes() {
		Ints out_ = new Ints();
		for (int i:list.getSelectedIndices()){
			out_.add(i);
		}
		return out_;
    }


    public CustList<T> getList() {
        return elts;
    }

    public int getSelectedIndex() {
        if (getSelectedIndexes().isEmpty()) {
            return -1;
        }
        return getSelectedIndexes().first();
    }

    public T getSelectedValue() {
        if (getSelectedIndexes().isEmpty()) {
            return null;
        }
        return elts.get(getSelectedIndexes().first());
    }

    public CustList<T> getSelectedValuesLs() {
        CustList<T> list_ = new CustList<T>();
        for (int i: getSelectedIndexes()) {
            list_.add(get(i));
        }
        return list_;
    }
    public T get(int _i) {
        return elts.get(_i);
    }

    public boolean isEmpty() {
        return elts.isEmpty();
    }

    public T last() {
        return elts.last();
    }

    public boolean isSelectionEmpty() {
        return getSelectedIndexes().isEmpty();
    }

    public int size() {
        return elts.size();
    }

    @Override
    protected JComponent getComponent() {
        return scroll;
    }

    public CustComponent self() {
        return this;
    }
}