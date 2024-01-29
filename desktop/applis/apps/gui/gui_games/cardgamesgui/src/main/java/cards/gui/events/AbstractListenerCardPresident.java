package cards.gui.events;

import cards.gui.containers.ContainerPlayablePresident;
import cards.president.enumerations.CardPresident;

public abstract class AbstractListenerCardPresident extends AbstractListenerCard {

    private final ContainerPlayablePresident container;
    private final CardPresident carte;
    private final byte index;
    private CardPresident carteVerif;
    private byte indexVerif;

    protected AbstractListenerCardPresident(ContainerPlayablePresident _container, CardPresident _card, byte _index) {
        super(_container);
        container = _container;
        carte = _card;
        index = _index;
    }

    protected CardPresident getCarteVerif() {
        return carteVerif;
    }

    protected byte getIndexVerif() {
        return indexVerif;
    }

    @Override
    protected void affecterCarteSurvolee() {
        container.setCarteSurvoleePresident(carte);
        container.setIndexCard(index);
    }

    @Override
    protected void jeuCarte(boolean _carteSurvolee) {
        if(_carteSurvolee) {
            carteVerif = container.getCarteSurvoleePresident();
            indexVerif = container.getIndexCard();
        }else{
            carteVerif = carte;
            indexVerif = index;
        }
        verifierRegles();
    }
}
