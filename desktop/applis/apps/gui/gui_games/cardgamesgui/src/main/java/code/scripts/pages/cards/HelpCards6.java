package code.scripts.pages.cards;
import code.bean.*;
import code.sml.util.*;
public final class HelpCards6 extends AbsHelpCards {
public void format(IntBeanBuilderHelperCommon _i, TranslationsLg _lg) {
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES,MessagesHelpCards6.M_6_0);
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES,MessagesHelpCards6.M_6_1);
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES,MessagesHelpCards6.M_6_2);
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES,MessagesHelpCards6.M_6_3);
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES,MessagesHelpCards6.M_6_4);
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES,MessagesHelpCards6.M_6_5);
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES,MessagesHelpCards6.M_6_6);
_i.initGrid();
_i.colCount(8);
feedImg(_i,_lg,C_HEART_JACK);
feedImg(_i,_lg,C_HEART_9);
feedImg(_i,_lg,C_HEART_1);
feedImg(_i,_lg,C_HEART_10);
feedImg(_i,_lg,C_HEART_KING);
feedImg(_i,_lg,C_HEART_QUEEN);
feedImg(_i,_lg,C_HEART_8);
feedImg(_i,_lg,C_HEART_7);
_i.feedParents();
formatMessage(_i,_lg,MessagesHelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES,MessagesHelpCards6.M_6_7);
_i.initGrid();
_i.colCount(8);
feedImg(_i,_lg,C_SPADE_1);
feedImg(_i,_lg,C_SPADE_10);
feedImg(_i,_lg,C_SPADE_KING);
feedImg(_i,_lg,C_SPADE_QUEEN);
feedImg(_i,_lg,C_SPADE_JACK);
feedImg(_i,_lg,C_SPADE_9);
feedImg(_i,_lg,C_SPADE_8);
feedImg(_i,_lg,C_SPADE_7);
_i.feedParents();
}
}
