package aiki.gui.dialogs;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import code.gui.AbsCommonFrame;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.initialize.AbsFrameFactory;

public abstract class SelectDialog {

    protected static final String RETURN_LINE = "\n";

    protected static final String SPACE = " ";
    private final AbsWindowListenerClosing built;
    private FacadeGame facade;

    private final AbsCommonFrame selectDial;
    private boolean ok;
    private final WindowAiki mainWindow;

    protected SelectDialog(AbsFrameFactory _fact, WindowAiki _window) {
        mainWindow = _window;
        built = build();
        selectDial = _fact.newCommonFrame("", _window.getFrames(), null);
        selectDial.addWindowListener(built);
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
