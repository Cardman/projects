package cards.gui.events;
import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.CardTarot;


public abstract class AbstractListenerCardTarot extends AbstractListenerCard {

    private ContainerTarot container;


    private CardTarot carte;
    private CardTarot carteVerif;
    //private CardTarot carteSurvoleeTarot;
    protected AbstractListenerCardTarot(ContainerTarot _container,CardTarot _pcarte) {
        super(_container);
        container = _container;
        carte=_pcarte;
    }
    protected CardTarot getCarteVerif() {
        return carteVerif;
    }
    @Override
    protected void affecterCarteSurvolee() {
        container.setCarteSurvoleeTarot(carte);
    }
    @Override
    protected void jeuCarte(boolean _carteSurvolee) {
        if(_carteSurvolee) {
            carteVerif = container.getCarteSurvoleeTarot();
        }else{
            carteVerif = carte;
        }
        verifierRegles();
    }

}
