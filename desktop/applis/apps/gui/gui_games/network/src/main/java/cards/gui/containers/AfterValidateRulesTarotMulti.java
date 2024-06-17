package cards.gui.containers;

import cards.gui.dialogs.AfterValidateRulesTarot;
import cards.gui.dialogs.DialogTarotContent;
import cards.tarot.RulesTarot;
import code.gui.events.AbsActionListener;

public final class AfterValidateRulesTarotMulti implements AbsActionListener,AfterValidateRulesTarot {
    private final DialogTarotContent dialogTarotContent;
    private final ContainerMultiTarot container;

    public AfterValidateRulesTarotMulti(DialogTarotContent _content, ContainerMultiTarot _c) {
        this.dialogTarotContent = _content;
        this.container = _c;
    }

    @Override
    public void action() {
        apply(null);
    }

    @Override
    public void apply(RulesTarot _rules) {
        dialogTarotContent.validateRules();
        RulesTarot rules_ = dialogTarotContent.getReglesTarot();
        container.setRulesTarotMulti(rules_);
        container.getContainerMultiContent().window().sendObject(rules_);
    }
}
