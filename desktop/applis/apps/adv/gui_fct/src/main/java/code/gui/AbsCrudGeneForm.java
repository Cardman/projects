package code.gui;

import code.gui.initialize.*;

public abstract class AbsCrudGeneForm {
    private final AbstractProgramInfos factory;
    private AbsPanel elements;
    private AbsPanel element;
    private AbsPanel group;
    private AbsButton validAddEdit;
    private AbsButton add;
    private AbsButton validRemove;
    private AbsButton cancel;
    private AbsPanel buttons;
    private boolean visibleSingle;
    private boolean enabledButtons;
    private AbsCommonFrame frame;

    protected AbsCrudGeneForm(AbstractProgramInfos _fact) {
        factory = _fact;
    }

    public void setFrame(AbsCommonFrame _fr) {
        this.frame = _fr;
    }

    public void initForm() {
        visibleSingle = false;
        setGroup(factory.getCompoFactory().newPageBox());
        group.removeAll();
        elements = factory.getCompoFactory().newPageBox();
        element = factory.getCompoFactory().newPageBox();
        buttons = factory.getCompoFactory().newLineBox();
        AbsPanel buttons_ = buttons;
//        int len_ = size();
//        for (int i = 0; i < len_; i++) {
//            AbsButton but_ = factory.getCompoFactory().newPlainButton(display(i));
//            but_.addActionListener(new SelectCrudGeneFormEvent(this, i));
//            elements.add(but_);
//            getAllButtons().add(but_);
//        }
        add = factory.getCompoFactory().newPlainButton("+");
        add.addActionListener(new AddCrudGeneFormEvent(this));
        buttons_.add(add);
        validAddEdit = factory.getCompoFactory().newPlainButton("\u2611");
        validAddEdit.setForeground(GuiConstants.GREEN);
        validAddEdit.setEnabled(false);
        validAddEdit.addActionListener(new ValidAddEditCrudGeneFormEvent(this));
        buttons_.add(validAddEdit);
        validRemove = factory.getCompoFactory().newPlainButton("-");
        validRemove.setEnabled(false);
        validRemove.addActionListener(new ValidRemoveCrudGeneFormEvent(this));
        buttons_.add(validRemove);
        cancel = factory.getCompoFactory().newPlainButton("\u2716");
        cancel.setForeground(GuiConstants.RED);
        cancel.setEnabled(false);
        cancel.addActionListener(new CancelCrudGeneFormEvent(this));
        buttons_.add(cancel);
        getGroup().removeAll();
        getGroup().add(buttons_);
        getGroup().add(elements);
        getGroup().add(element);
        setEnabledButtons(true);
    }
    public abstract void refresh();

    public AbsPanel getGroup() {
        return group;
    }

    public void setGroup(AbsPanel _g) {
        this.group = _g;
    }

    public abstract void formAdd();

    public abstract void validAddEdit();
    public abstract void validRemove();

    public void selectOrAdd() {
        validAddEdit.setEnabled(true);
        validRemove.setEnabled(true);
        cancel.setEnabled(true);
        add.setEnabled(false);
        visibleSingle = true;
        setEnabledButtons(false);
        getFrame().pack();
    }

    public void afterModif() {
        element.removeAll();
        validAddEdit.setEnabled(false);
        validRemove.setEnabled(false);
        cancel.setEnabled(false);
        add.setEnabled(true);
        setEnabledButtons(true);
        refresh();
        visibleSingle = false;
        getFrame().pack();
    }
    public void cancel() {
        element.removeAll();
        validAddEdit.setEnabled(false);
        validRemove.setEnabled(false);
        cancel.setEnabled(false);
        add.setEnabled(true);
        setEnabledButtons(true);
        visibleSingle = false;
        getFrame().pack();
    }

    public boolean isEnabledButtons() {
        return enabledButtons;
    }

    public void setEnabledButtons(boolean _e) {
        this.enabledButtons = _e;
    }

    public AbsPanel getElement() {
        return element;
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

    public AbstractProgramInfos getFactory() {
        return factory;
    }

    public AbsPanel getButtons() {
        return buttons;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public boolean isVisibleSingle() {
        return visibleSingle;
    }
}
