package cards.gui.events;
import cards.belote.enumerations.CardBelote;
import cards.gui.containers.ContainerBelote;

public abstract class AbstractListenerCardBelote extends AbstractListenerCard {

    private ContainerBelote container;
    private CardBelote carte;
    private CardBelote carteVerif;
    protected AbstractListenerCardBelote(ContainerBelote _container,CardBelote _pcarte) {
        super(_container);
        container = _container;
        carte=_pcarte;
    }

//    protected ContainerBelote getContainer() {
//        return container;
//    }

    protected CardBelote getCarteVerif() {
        return carteVerif;
    }
    @Override
    protected void affecterCarteSurvolee() {
        container.setCarteSurvoleeBelote(carte);
    }
    @Override
    protected void jeuCarte(boolean _carteSurvolee) {
        if(_carteSurvolee) {
            carteVerif = container.getCarteSurvoleeBelote();
        }else{
            carteVerif = carte;
        }
        verifierRegles();
    }
}
