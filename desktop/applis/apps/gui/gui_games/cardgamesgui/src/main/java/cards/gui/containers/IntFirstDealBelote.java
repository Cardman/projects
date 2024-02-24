package cards.gui.containers;

import cards.belote.*;

public interface IntFirstDealBelote {
    GameBelote deal(ContainerBelote _container, RulesBelote _rules, long _nb);
    GameBelote deal(ContainerBelote _container);
}
