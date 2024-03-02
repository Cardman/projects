package cards.gui.containers;

import cards.gui.dialogs.AfterValidateRulesPresident;
import cards.gui.dialogs.DialogPresidentContent;
import cards.president.RulesPresident;
import code.gui.events.AbsActionListener;

public final class AfterValidateRulesPresidentMulti implements AbsActionListener,AfterValidateRulesPresident {
    private final DialogPresidentContent dialogPresidentContent;
    private final ContainerMultiPresident container;

    public AfterValidateRulesPresidentMulti(DialogPresidentContent _content,ContainerMultiPresident _c) {
        this.dialogPresidentContent = _content;
        this.container = _c;
    }

    @Override
    public void action() {
        apply(null);
    }

    @Override
    public void apply(RulesPresident _rules) {
        dialogPresidentContent.validateRules();
        RulesPresident rules_ = dialogPresidentContent.getReglesPresident();
        container.setRulesPresidentMulti(rules_);
        container.window().sendObject(rules_);
    }
}
