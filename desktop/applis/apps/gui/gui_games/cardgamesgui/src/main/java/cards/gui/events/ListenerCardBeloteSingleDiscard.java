package cards.gui.events;


import cards.belote.GameBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.containers.ContainerSingleBelote;

public class ListenerCardBeloteSingleDiscard extends AbstractListenerCardBelote {

    private final ContainerSingleBelote container;

    public ListenerCardBeloteSingleDiscard(ContainerSingleBelote _container, CardBelote _pcarte) {
        super(_container,_pcarte);
        container = _container;
    }
//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        if (inHand) {
//            return _event.getYcoord() < 0;
//        }
//        return _event.getYcoord() > component.getHeight();
//    }
    @Override
    protected void verifierRegles() {
        GameBelote partie_=container.partieBelote();
        if(partie_.getDistribution().hand().contient(getCarteVerif())) {
            container.ajouterUneCarteAuChien(getCarteVerif());
        } else {
            container.retirerUneCarteDuChien(getCarteVerif());
        }

    }
}
