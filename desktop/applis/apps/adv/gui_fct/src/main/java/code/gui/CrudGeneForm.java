package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.EntryCust;
import code.util.TreeMap;
import code.util.ints.Comparing;

public final class CrudGeneForm<K,V> {
    private final AbstractProgramInfos factory;
    private DisplayEntryCust<K,V> displayEntry;
    private GeneComponentModel<K> geneKey;
    private GeneComponentModel<V> geneValue;
    private AbsMap<K,V> list;
    private AbsPanel elements;
    private AbsPanel element;
    private final AbsPanel group;
    private AbsButton validAddEdit;
    private AbsButton add;
    private AbsButton validRemove;
    private AbsButton cancel;
    private AbsPanel buttons;
    private boolean visibleSingle;
    private AbsCommonFrame frame;

    public CrudGeneForm(AbstractProgramInfos _fact, Comparing<K> _cmp) {
        factory = _fact;
        group = _fact.getCompoFactory().newPageBox();
        list = new TreeMap<K, V>(_cmp);
    }

    public void initForm(AbsCommonFrame _fr,DisplayEntryCust<K,V> _disp, GeneComponentModel<K> _k, GeneComponentModel<V> _v, Comparing<K> _cmp, AbsMap<K,V> _map) {
        visibleSingle = false;
        frame = _fr;
        elements = factory.getCompoFactory().newPageBox();
        element = factory.getCompoFactory().newPageBox();
        buttons = factory.getCompoFactory().newLineBox();
        AbsPanel buttons_ = buttons;
        geneKey = _k;
        geneValue = _v;
        displayEntry = _disp;
        list = new TreeMap<K, V>(_cmp);
        list.putAllMap(_map);
        for (EntryCust<K,V> e: list.entryList()) {
            AbsButton but_ = factory.getCompoFactory().newPlainButton(_disp.display(e.getKey(), e.getValue()));
            but_.addActionListener(new SelectCrudGeneFormEvent<K,V>(this,e));
            elements.add(but_);
        }
        add = factory.getCompoFactory().newPlainButton("+");
        add.addActionListener(new AddCrudGeneFormEvent<K,V>(this));
        buttons_.add(add);
        validAddEdit = factory.getCompoFactory().newPlainButton("OK");
        validAddEdit.setEnabled(false);
        validAddEdit.addActionListener(new ValidAddEditCrudGeneFormEvent<K,V>(this));
        buttons_.add(validAddEdit);
        validRemove = factory.getCompoFactory().newPlainButton("-");
        validRemove.setEnabled(false);
        validRemove.addActionListener(new ValidRemoveCrudGeneFormEvent<K,V>(this));
        buttons_.add(validRemove);
        cancel = factory.getCompoFactory().newPlainButton("CANCEL");
        cancel.setEnabled(false);
        cancel.addActionListener(new CancelCrudGeneFormEvent<K,V>(this));
        buttons_.add(cancel);
        getGroup().removeAll();
        getGroup().add(elements);
        getGroup().add(element);
        getGroup().add(buttons_);
    }

    public AbsPanel getGroup() {
        return group;
    }

    public void select(EntryCust<K,V> _e) {
        element.removeAll();
        element.add(geneKey.gene(_e.getKey()));
        element.add(geneValue.gene(_e.getValue()));
        validAddEdit.setEnabled(true);
        validRemove.setEnabled(true);
        cancel.setEnabled(true);
        add.setEnabled(false);
        visibleSingle = true;
        frame.pack();
    }
    public void refresh() {
        elements.removeAll();
        for (EntryCust<K,V> e: list.entryList()) {
            AbsButton but_ = factory.getCompoFactory().newPlainButton(displayEntry.display(e.getKey(), e.getValue()));
            but_.addActionListener(new SelectCrudGeneFormEvent<K,V>(this,e));
            elements.add(but_);
        }
    }

    public void formAdd() {
        element.removeAll();
        element.add(geneKey.gene());
        element.add(geneValue.gene());
        validAddEdit.setEnabled(true);
        validRemove.setEnabled(true);
        cancel.setEnabled(true);
        add.setEnabled(false);
        visibleSingle = true;
        frame.pack();
    }
    public void cancel() {
        element.removeAll();
        validAddEdit.setEnabled(false);
        validRemove.setEnabled(false);
        cancel.setEnabled(false);
        add.setEnabled(true);
        visibleSingle = false;
        frame.pack();
    }
    public void validAddEdit() {
        element.removeAll();
        list.put(geneKey.value(), geneValue.value());
        validAddEdit.setEnabled(false);
        validRemove.setEnabled(false);
        cancel.setEnabled(false);
        add.setEnabled(true);
        refresh();
        visibleSingle = false;
        frame.pack();
    }
    public void validRemove() {
        element.removeAll();
        list.removeKey(geneKey.value());
        validAddEdit.setEnabled(false);
        validRemove.setEnabled(false);
        cancel.setEnabled(false);
        add.setEnabled(true);
        refresh();
        visibleSingle = false;
        frame.pack();
    }

    public AbsButton getAdd() {
        return add;
    }

    public AbsButton getValidAddEdit() {
        return validAddEdit;
    }

    public AbsButton getValidRemove() {
        return validRemove;
    }

    public AbsPanel getElements() {
        return elements;
    }

    public AbsButton getCancel() {
        return cancel;
    }

    public GeneComponentModel<K> getGeneKey() {
        return geneKey;
    }

    public GeneComponentModel<V> getGeneValue() {
        return geneValue;
    }

    public AbstractProgramInfos getFactory() {
        return factory;
    }

    public AbsMap<K, V> getList() {
        return list;
    }

    public AbsPanel getButtons() {
        return buttons;
    }

    public boolean isVisibleSingle() {
        return visibleSingle;
    }
}
