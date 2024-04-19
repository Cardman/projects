package aiki.gui.dialogs;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;
import code.gui.initialize.AbsFrameFactory;

public abstract class SelectDialog {

    protected static final String RETURN_LINE = "\n";

    protected static final String SPACE = " ";
    private final AbsCloseableDialog built;
    private FacadeGame facade;

    private final AbsDialog selectDial;
    private boolean ok;
    private final WindowAiki mainWindow;

    protected SelectDialog(AbsFrameFactory _fact, WindowAiki _window) {
        mainWindow = _window;
        built = build();
        selectDial = _fact.newDialog(built);
    }

    public WindowAiki getMainWindow() {
        return mainWindow;
    }

    protected abstract AbsCloseableDialog build();
    public AbsDialog getSelectDial() {
        return selectDial;
    }

    public void validateChoice() {
        ok = true;
        built.closeWindow();
        selectDial.closeWindow();
    }

    public void validateChoiceSingle() {
        okChoice();
        closWindow();
    }

    public void okChoice() {
        ok = true;
    }

    public void closWindow() {
        selectDial.closeWindow();
    }

    public AbsCloseableDialog getBuilt() {
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
