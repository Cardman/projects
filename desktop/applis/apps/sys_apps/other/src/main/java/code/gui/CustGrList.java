package code.gui;
import code.adv.BoolIntChoiceImpl;
import code.adv.BoolIntChoiceUtil;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;
import javax.swing.*;
import java.util.Arrays;
import java.util.Optional;

public class CustGrList<T> extends CustComponent implements AbsGraphicList<T> {

	private final DefaultListModel<T> model = new DefaultListModel<>();
	private final JList<T> list = new JList<>(model);
	private final JScrollPane scroll = new JScrollPane(list);
	private final ScrollPane custScroll = new ScrollPane(scroll);
	private final CustList<T> elts = new CustList<>();
	private final IdList<LocalListSelectionListener> listeners = new IdList<>();
	private CustCellRender<T> inner;

    public CustGrList(boolean _simple) {
        setup(BoolIntChoiceUtil.choice(new BoolIntChoiceImpl(_simple),
                ListSelectionModel.SINGLE_SELECTION,
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION));
    }
    private void setup(int _value) {
        list.setSelectionMode(_value);
    }
    public void setListener(ListSelection _listener) {
        listeners.list().forEach(list::removeListSelectionListener);
        listeners.clear();
        addListener(_listener);
    }

    public ListSelection[] getListeners() {
        return listeners.list().stream().map(AbstractSelectionListener::getListener).toArray(ListSelection[]::new);
    }

    public void addListener(ListSelection _listener) {
        Optional.ofNullable(_listener).ifPresent(this::simpleAdd);
    }

    private void simpleAdd(ListSelection _l) {
        LocalListSelectionListener loc_ = new LocalListSelectionListener(_l);
        list.addListSelectionListener(loc_);
        listeners.add(loc_);
    }

    public void removeListener(ListSelection _listener) {
        ListSelectionWrapper lsw_ = new ListSelectionWrapper(_listener);
        Optional<LocalListSelectionListener> result_ = listeners.list().stream().filter(lsw_::match).findFirst();
        result_.ifPresent(this::remove);
    }
    private void remove(LocalListSelectionListener _listener) {
        list.removeListSelectionListener(_listener);
        listeners.removeObj(_listener);
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
    public void add(int _index, AbsPreparedLabel _lab, T _elt) {
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
    public void set(int _index, AbsPreparedLabel _lab, T _elt) {
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
        Arrays.stream(list.getSelectedIndices()).forEach(out_::add);
		return out_;
    }

    public void setSelectedIndexes(Ints _values) {
        list.setSelectedIndices(_values.list().stream().mapToInt(Integer::intValue).toArray());
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
        list.getSelectedValuesList().forEach(list_::add);
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
    protected JComponent getNatComponent() {
        return custScroll.getNatComponent();
    }

    public AbsCustComponent self() {
        return this;
    }

    @Override
    public AbsCustComponent scroll() {
        return custScroll;
    }

    @Override
    public AbsCustComponent visible() {
        return this;
    }

}