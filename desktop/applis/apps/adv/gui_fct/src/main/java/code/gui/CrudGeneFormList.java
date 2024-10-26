package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class CrudGeneFormList<E> extends AbsCrudGeneForm {
    private DisplayEntryCust<Integer,E> displayEntry;
    private GeneComponentModel<E> gene;
    private CustList<E> list;
    private int selectedIndex = -1;
    private AbsSpinner indicator;

    public CrudGeneFormList(AbstractProgramInfos _fact) {
        super(_fact);
        list = new CustList<E>();
    }

    public void initForm(AbsCommonFrame _fr, DisplayEntryCust<Integer, E> _disp, GeneComponentModel<E> _k, CustList<E> _map) {
        indicator = getFactory().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        indicator.setEnabled(false);
        gene = _k;
        displayEntry = _disp;
        list = new CustList<E>(_map);
        initForm(_fr);
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
        getElement().add(gene.gene(list.get(_index)));
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
        if (selectedIndex < 0) {
            list.add(gene.value());
        } else {
            list.set(selectedIndex, gene.value());
        }
        afterModif();
    }

    @Override
    public void validRemove() {
        list.remove(selectedIndex);
        afterModif();
    }

    public CustList<E> getList() {
        return list;
    }

    public GeneComponentModel<E> getGene() {
        return gene;
    }
}
