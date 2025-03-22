package code.scripts.pages.cards;
import code.bean.*;
import code.sml.util.*;
public final class HelpCards14 extends AbsHelpCards {
public void format(IntBeanBuilderHelperCommon _i, TranslationsLg _lg) {
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_PRESIDENT_ORDRE_DES_CARTES,MessagesHelpCards14.M_14_0);
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_PRESIDENT_ORDRE_DES_CARTES,MessagesHelpCards14.M_14_1);
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_PRESIDENT_ORDRE_DES_CARTES,MessagesHelpCards14.M_14_2);
_i.initGrid();
_i.colCount(13);
feedImg(_i,_lg,C_HEART_2);
feedImg(_i,_lg,C_HEART_1);
feedImg(_i,_lg,C_HEART_KING);
feedImg(_i,_lg,C_HEART_QUEEN);
feedImg(_i,_lg,C_HEART_JACK);
feedImg(_i,_lg,C_HEART_10);
feedImg(_i,_lg,C_HEART_9);
feedImg(_i,_lg,C_HEART_8);
feedImg(_i,_lg,C_HEART_7);
feedImg(_i,_lg,C_HEART_6);
feedImg(_i,_lg,C_HEART_5);
feedImg(_i,_lg,C_HEART_4);
feedImg(_i,_lg,C_HEART_3);
_i.feedParents();
}
}
