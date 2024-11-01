package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.ints.Comparing;

public abstract class AbsCrudGeneFormList<E> extends AbsCrudGeneForm {
    private DisplayEntryCust<Integer,E> displayEntry;
    private GeneComponentModel<E> gene;
    private CustList<E> list;
    private int selectedIndex = -1;
    private AbsSpinner indicator;
    private Comparing<E> cmp;

    protected AbsCrudGeneFormList(AbstractProgramInfos _fact, Comparing<E> _c) {
        super(_fact);
        list = new CustList<E>();
        cmp = _c;
    }

    public void initForm(DisplayEntryCust<Integer, E> _disp, GeneComponentModel<E> _k, CustList<E> _map) {
        initForm(_disp, _k, _map, null);
    }
    public void initForm(DisplayEntryCust<Integer, E> _disp, GeneComponentModel<E> _k, CustList<E> _map, Comparing<E> _c) {
        indicator = getFactory().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        indicator.setEnabled(false);
        gene = _k;
        displayEntry = _disp;
        cmp = _c;
        list = new CustList<E>();
        setupValues(_map);
    }

    @Override
    protected int size() {
        return list.size();
    }

    @Override
    public String display(int _index) {
        return displayEntry.display(_index, list.get(_index));
    }

    @Override
    public void select(int _index) {
        selectedIndex = _index;
        getElement().removeAll();
        indicator.setValue(_index);
        getElement().add(indicator);
        AbsCustComponent elt_ = gene.gene();
        gene.value(list.get(_index));
        getElement().add(elt_);
        selectOrAdd();
    }

    @Override
    public void formAdd() {
        selectedIndex = -1;
        indicator.setValue(list.size());
        getElement().removeAll();
        getElement().add(indicator);
        getElement().add(gene.gene());
        selectOrAdd();
    }

    @Override
    public void validAddEdit() {
       E value_ = gene.value();
        if (selectedIndex < 0) {
            list.add(value_);
        } else {
            list.set(selectedIndex, value_);
        }
        possibleSort();
        afterModif(-1,value_);
    }

    @Override
    public void validRemove() {
        afterModif(getSelectedIndex(), list.get(getSelectedIndex()));
    }
    protected abstract void afterModif(int _index, E _value);

    public void setupValues(CustList<E> _values) {
        list.clear();
        list.addAllElts(_values);
        possibleSort();
        refresh();
    }
    public void possibleSort() {
        if (cmp != null) {
            list.sortElts(cmp);
        }
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public CustList<E> getList() {
        return list;
    }

    public GeneComponentModel<E> getGene() {
        return gene;
    }
}
