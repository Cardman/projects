package cards.gui.events;

import cards.gui.containers.ContainerPlayableTarot;
import cards.tarot.enumerations.CardTarot;


public abstract class AbstractListenerCardTarot extends AbstractListenerCard {

    private final ContainerPlayableTarot container;


    private final CardTarot carte;
    private CardTarot carteVerif;
    //private CardTarot carteSurvoleeTarot;
    protected AbstractListenerCardTarot(ContainerPlayableTarot _container,CardTarot _pcarte) {
        super(_container);
        container = _container;
        carte=_pcarte;
    }

    public ContainerPlayableTarot getContainer() {
        return container;
    }

    protected CardTarot getCarteVerif() {
        return carteVerif;
    }
//    @Override
//    protected void affecterCarteSurvolee() {
//        container.setCarteSurvoleeTarot(carte);
//    }
    @Override
    protected void jeuCarte(boolean _carteSurvolee) {
//        if(_carteSurvolee) {
//            carteVerif = container.getCarteSurvoleeTarot();
//        }else{
//            carteVerif = carte;
//        }
        carteVerif = carte;
        verifierRegles();
    }

}
