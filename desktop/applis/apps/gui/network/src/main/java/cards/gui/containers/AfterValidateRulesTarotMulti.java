package cards.gui.containers;

import cards.gui.dialogs.AfterValidateRulesTarot;
import cards.tarot.RulesTarot;

public final class AfterValidateRulesTarotMulti implements AfterValidateRulesTarot {
    private final ContainerMultiTarot container;

    public AfterValidateRulesTarotMulti(ContainerMultiTarot _c) {
        this.container = _c;
    }

    @Override
    public void apply(RulesTarot _rules) {
        container.setRulesTarotMulti(_rules);
        container.window().sendObject(_rules);
    }
}
