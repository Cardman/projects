package cards.gui.containers;

import cards.gui.dialogs.AfterValidateRulesPresident;
import cards.president.RulesPresident;

public final class AfterValidateRulesPresidentMulti implements AfterValidateRulesPresident {
    private final ContainerMultiPresident container;

    public AfterValidateRulesPresidentMulti(ContainerMultiPresident _c) {
        this.container = _c;
    }

    @Override
    public void apply(RulesPresident _rules) {
        container.setRulesPresidentMulti(_rules);
        container.window().sendObject(_rules);
    }
}
