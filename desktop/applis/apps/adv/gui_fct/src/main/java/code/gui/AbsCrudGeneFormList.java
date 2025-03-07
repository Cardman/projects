package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.ints.Comparing;

public abstract class AbsCrudGeneFormList<E> extends AbsCrudGeneForm implements AbsCrudGeneFormInt<E> {
    private DisplayEntryCust<Integer,E> displayEntry;
    private GeneComponentModel<E> gene;
    private CustList<E> list;
    private int selectedIndex = -1;
    private AbsSpinner indicator;
    private Comparing<E> cmp;
    private IntValidateElementAdd<E> validator;
    private final CustList<AbsButton> allButtons = new CustList<AbsButton>();
    private boolean enabledButtons;
    protected AbsCrudGeneFormList(AbstractProgramInfos _fact, Comparing<E> _c) {
        super(_fact);
        list = new CustList<E>();
        cmp = _c;
    }

    public void initForm(DisplayEntryCust<Integer, E> _disp, GeneComponentModel<E> _k) {
        initForm(_disp, _k, null);
    }
    public void initForm(DisplayEntryCust<Integer, E> _disp, GeneComponentModel<E> _k, Comparing<E> _c) {
        initForm(_disp,_k, _c,null);
    }
    public void initForm(DisplayEntryCust<Integer, E> _disp, GeneComponentModel<E> _k, Comparing<E> _c, IntValidateElementAdd<E> _val) {
        validator = _val;
        indicator = getFactory().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        indicator.setEnabled(false);
        gene = _k;
        displayEntry = _disp;
        cmp = _c;
        list = new CustList<E>();
    }

    @Override
    public void initForm() {
        getAllButtons().clear();
        super.initForm();
        setEnabledButtons(true);
    }

    protected int size() {
        return list.size();
    }

    public String display(int _index) {
        return displayEntry.display(_index, list.get(_index));
    }

    @Override
    public void selectOrAdd() {
        setEnabledButtons(false);
        super.selectOrAdd();
        enable(false);
    }

    public void select(int _index) {
        selectedIndex = _index;
        getElement().removeAll();
        indicator.setValue(_index);
        getElement().add(indicator);
        AbsCustComponent elt_ = gene.gene(_index);
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
        getElement().add(gene.gene(-1));
        selectOrAdd();
        getValidRemove().setEnabled(false);
    }

    @Override
    public void validAddEdit() {
       E value_ = gene.value();
        if (validator != null && !validator.valid(list,selectedIndex,value_)) {
            return;
        }
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
    @Override
    public void afterModif(int _index, E _value) {
        if (_index > -1) {
            getList().remove(_index);
        }
        afterModif();
    }
    @Override
    public void refresh() {
        getElements().removeAll();
        getAllButtons().clear();
        int len_ = size();
        for (int i = 0; i < len_; i++) {
            AbsButton but_ = getFactory().getCompoFactory().newPlainButton(display(i));
            but_.addActionListener(new SelectCrudGeneFormEvent<E>(this, i));
            but_.setEnabled(isEnabledButtons());
            getElements().add(but_);
            getAllButtons().add(but_);
        }
    }

    @Override
    public void afterModif() {
        setEnabledButtons(true);
        super.afterModif();
    }

    @Override
    public void cancel() {
        setEnabledButtons(true);
        super.cancel();
        enable(true);
    }

    private void enable(boolean _e) {
        for (AbsButton b: getAllButtons()) {
            b.setEnabled(_e);
        }
    }
    public CustList<AbsButton> getAllButtons() {
        return allButtons;
    }
    public void setupValues(CustList<E> _values) {
        list.clear();
        list.addAllElts(_values);
        possibleSort();
        refresh();
    }
    public void possibleSort() {
        selectedIndex = new ComparingCrudUtil<E>(cmp).shiftPair(list,selectedIndex);
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

    public boolean isEnabledButtons() {
        return enabledButtons;
    }

    public void setEnabledButtons(boolean _e) {
        this.enabledButtons = _e;
    }

}
