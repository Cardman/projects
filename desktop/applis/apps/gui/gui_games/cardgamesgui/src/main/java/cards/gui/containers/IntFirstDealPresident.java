package cards.gui.containers;

import cards.president.GamePresident;
import cards.president.RulesPresident;

public interface IntFirstDealPresident {
    GamePresident deal(ContainerPresident _container, RulesPresident _rules, long _nb);
}
