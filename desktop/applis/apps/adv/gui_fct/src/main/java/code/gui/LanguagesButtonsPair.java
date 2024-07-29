package code.gui;

public final class LanguagesButtonsPair {
    private final EnabledMenu lgMenu;
    private final AbsButton mainButton;
    private final LanguageComponentButtons buttons;

    public LanguagesButtonsPair(EnabledMenu _l, AbsButton _m, LanguageComponentButtons _b) {
        this.lgMenu = _l;
        this.mainButton = _m;
        this.buttons = _b;
    }

    public AbsButton getMainButton() {
        return mainButton;
    }

    public EnabledMenu getLgMenu() {
        return lgMenu;
    }

    public LanguageComponentButtons getButtons() {
        return buttons;
    }
}
