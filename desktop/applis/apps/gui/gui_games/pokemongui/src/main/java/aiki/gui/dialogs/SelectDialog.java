package aiki.gui.dialogs;
import aiki.facade.FacadeGame;
import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;
import code.gui.initialize.AbsFrameFactory;

public abstract class SelectDialog {

    private final AbsCloseableDialog built;
    private FacadeGame facade;

    private final AbsDialog selectDial;
    private boolean ok;

    protected SelectDialog(AbsFrameFactory _fact) {
        built = build();
        selectDial = _fact.newDialog(built);
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
