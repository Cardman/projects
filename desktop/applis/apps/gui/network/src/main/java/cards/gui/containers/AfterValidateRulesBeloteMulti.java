package cards.gui.containers;

import cards.belote.RulesBelote;
import cards.gui.dialogs.AfterValidateRulesBelote;

public final class AfterValidateRulesBeloteMulti implements AfterValidateRulesBelote {
    private final ContainerMultiBelote container;

    public AfterValidateRulesBeloteMulti(ContainerMultiBelote _c) {
        this.container = _c;
    }

    @Override
    public void apply(RulesBelote _rules) {
        container.setRulesBeloteMulti(_rules);
        container.window().sendObject(_rules);
    }
}
