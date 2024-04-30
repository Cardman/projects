package aiki.gui.dialogs;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.events.ClosingSelectButtonEvt;
import aiki.gui.dialogs.events.ValidateSelectionEvent;
import code.gui.AbsButton;
import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.initialize.AbsFrameFactory;
import code.util.StringMap;

public abstract class SelectDialog {

    protected static final String RETURN_LINE = "\n";

    protected static final String SPACE = " ";
    private static final String CANCEL = "cancel";
    private final AbsWindowListenerClosing built;
    private FacadeGame facade;

    private final AbsCommonFrame selectDial;
    private boolean ok;
    private final WindowAiki mainWindow;
    private AbsButton okButton;
    private AbsButton cancelButton;

    protected SelectDialog(AbsFrameFactory _fact, WindowAiki _window) {
        mainWindow = _window;
        built = build();
        selectDial = _fact.newCommonFrame("", _window.getFrames(), null);
        selectDial.addWindowListener(built);
    }
    public void buttons(WindowAiki _parent, AbsPanel _buttons, StringMap<String> _messages) {
        okButton = _parent.getCompoFactory().newPlainButton(WindowAiki.OK);
        okButton.addActionListener(new ValidateSelectionEvent(this));
        _buttons.add(okButton);
        cancelButton = _parent.getCompoFactory().newPlainButton(_messages.getVal(CANCEL));
        cancelButton.addActionListener(new ClosingSelectButtonEvt(getSelectDial(), _parent));
        _buttons.add(cancelButton);
    }

    public AbsButton getOkButton() {
        return okButton;
    }

    public AbsButton getCancelButton() {
        return cancelButton;
    }

    public WindowAiki getMainWindow() {
        return mainWindow;
    }

    protected abstract AbsWindowListenerClosing build();
    public AbsCommonFrame getSelectDial() {
        return selectDial;
    }

    public abstract void validateChoice();

    public void okChoice() {
        ok = true;
    }

    public void closWindow() {
        getMainWindow().getModal().set(false);
        selectDial.setVisible(false);
    }

    public AbsWindowListenerClosing getBuilt() {
        return built;
    }

    protected boolean isSelected() {
        return ok;
    }

    protected void initOk() {
        ok = false;
    }

    public FacadeGame getFacade() {
        return facade;
    }

    public void setFacade(FacadeGame _f) {
        this.facade = _f;
    }
}
