package code.scripts.pages.cards;
import code.bean.*;
import code.sml.util.*;
public final class HelpCards19 extends AbsHelpCards {
public void format(IntBeanBuilderHelperCommon _i, TranslationsLg _lg) {
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_TAROT_CHIEN,MessagesHelpCards19.M_19_0);
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_TAROT_CHIEN,MessagesHelpCards19.M_19_1);
_i.getOrderedLists().add(0);
elementOrd(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_TAROT_CHIEN,MessagesHelpCards19.M_19_2);
elementOrd(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_TAROT_CHIEN,MessagesHelpCards19.M_19_12);
_i.getOrderedLists().removeQuicklyLast();
}
}
