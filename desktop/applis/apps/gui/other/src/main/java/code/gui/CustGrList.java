package code.gui;
import code.util.CustList;
import code.util.Ints;
import javax.swing.*;

public class CustGrList<T> extends CustComponent implements AbsGraphicList<T> {

	private final DefaultListModel<T> model = new DefaultListModel<>();
	private final JList<T> list = new JList<>(model);
	private final JScrollPane scroll = new JScrollPane(list);
	private final ScrollPane custScroll = new ScrollPane(scroll);
	private final CustList<T> elts = new CustList<>();
	private CustCellRender<T> inner;

    private ListSelection listener;

    public CustGrList(boolean _simple) {
        if (_simple) {
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } else {
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }
    }
    public ListSelection getListener() {
        return listener;
    }

    public void setListener(ListSelection _listener) {
        listener = _listener;
		list.addListSelectionListener(new LocalListSelectionListener(_listener));
    }

    public CustCellRender<T> getRender() {return inner;}
    public void setRender(CustCellRender<T> _render) {
		CustSelList<T> r_ = new CustSelList<>();
		r_.setRender(_render);
		r_.setList(elts);
		inner = _render;
		list.setCellRenderer(r_);
    }

    public JList<T> getListView() {
        return list;
    }
    public void add(T _elt) {
        add(elts.size(),_elt);
    }

    @Override
    public void add(int _index, PreparedLabel _lab, T _elt) {
        elts.add(_index, _elt);
        model.add(_index, _elt);
    }

    public void add(int _index, T _elt) {
        elts.add(_index, _elt);
		model.add(_index, _elt);
    }

    @Override
    public void set(int _index, T _elt) {
        elts.set(_index, _elt);
        model.set(_index, _elt);
    }

    @Override
    public void set(int _index, PreparedLabel _lab, T _elt) {
        elts.set(_index, _elt);
        model.set(_index, _elt);
    }

    public void clear() {
        elts.clear();
        model.clear();
    }

    public void clearRevalidate() {
        clear();
        custScroll.revalidate();
    }
    public void remove(int _index) {
        model.remove(_index);
        elts.remove(_index);
    }

    public int getVisibleRowCount() {
        return list.getVisibleRowCount();
    }
    public void setVisibleRowCount(int _visibleRowCount) {
        list.setVisibleRowCount(_visibleRowCount);
    }

    public void clearAllRange() {
        list.clearSelection();
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

    public void setSelectedIndexes(Ints _values) {
        int len_ = _values.size();
        int[] inds_ = new int[len_];
        for (int i = 0; i < len_; i++) {
            inds_[i] = _values.get(i);
        }
        list.setSelectedIndices(inds_);
    }


    public CustList<T> getList() {
        return elts;
    }

    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    public T getSelectedValue() {
        return list.getSelectedValue();
    }

    public CustList<T> getSelectedValuesLs() {
        CustList<T> list_ = new CustList<>();
        for (T i: list.getSelectedValuesList()) {
            list_.add(i);
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
        return list.isSelectionEmpty();
    }

    public int size() {
        return elts.size();
    }

    public boolean isEnabled() {
        return list.isEnabled();
    }

    public void setEnabled(boolean _enabled) {
        list.setEnabled(_enabled);
    }

    @Override
    protected JComponent getComponent() {
        return custScroll.getComponent();
    }

    public CustComponent self() {
        return this;
    }

    @Override
    public CustComponent scroll() {
        return custScroll;
    }

    @Override
    public CustComponent visible() {
        return this;
    }

}