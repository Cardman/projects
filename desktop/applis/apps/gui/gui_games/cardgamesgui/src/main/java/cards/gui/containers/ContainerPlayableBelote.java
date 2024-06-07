package cards.gui.containers;

import cards.belote.BidBeloteSuit;
import cards.belote.enumerations.CardBelote;
import cards.gui.labels.LabelPoints;
import cards.gui.labels.SuitLabel;
import code.gui.AbsButton;
import code.gui.AbsPanel;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public interface ContainerPlayableBelote extends ContainerPlayableSlam,ContainerSingle<CardBelote>,ContainerSingleWithDiscard<CardBelote> {
    void prendreCartesChien();
    void validateDiscard();
    void bid();
    void fold();
    void setBid(BidBeloteSuit _suit);
    void setPoints(int _points);

    CustList<LabelPoints> getPointsButtons();

    void setPanneauBoutonsJeuPoints(AbsPanel _p);

    AbsPanel getPanneauBoutonsJeuPoints();

    void setBidOk(AbsButton _b);

    AbsButton getBidOk();

    CustList<SuitLabel> getBidsButtons();

    void setFold(AbsButton _b);

    CustList<BidBeloteSuit> getBids();

    AbsActionListener bid(BidBeloteSuit _action);

//    void setCarteSurvoleeBelote(CardBelote _c);
//
//    CardBelote getCarteSurvoleeBelote();
}
