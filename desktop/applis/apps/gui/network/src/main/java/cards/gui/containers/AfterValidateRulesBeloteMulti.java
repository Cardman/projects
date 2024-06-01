package cards.gui.containers;

import cards.belote.RulesBelote;
import cards.gui.dialogs.AfterValidateRulesBelote;
import cards.gui.dialogs.DialogBeloteContent;
import code.gui.events.AbsActionListener;

public final class AfterValidateRulesBeloteMulti implements AbsActionListener,AfterValidateRulesBelote {
    private final DialogBeloteContent dialogBeloteContent;
    private final ContainerMultiBelote container;

    public AfterValidateRulesBeloteMulti(DialogBeloteContent _content, ContainerMultiBelote _c) {
        this.dialogBeloteContent = _content;
        this.container = _c;
    }

    @Override
    public void action() {
        apply(null);
    }

    @Override
    public void apply(RulesBelote _rules) {
        dialogBeloteContent.validateRules();
        RulesBelote rules_ = dialogBeloteContent.getReglesBelote();
        container.setRulesBeloteMulti(rules_);
        container.getContainerMultiContent().window().sendObject(rules_);
    }
}
