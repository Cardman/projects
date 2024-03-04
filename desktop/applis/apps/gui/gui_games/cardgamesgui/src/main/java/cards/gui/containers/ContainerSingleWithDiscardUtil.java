package cards.gui.containers;

import cards.gui.events.ListenerCardDiscard;
import cards.gui.labels.GraphicCard;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;
import code.util.IdList;

public final class ContainerSingleWithDiscardUtil<T> {
    private final ContainerSingleWithDiscard<T> container;

    public ContainerSingleWithDiscardUtil(ContainerSingleWithDiscard<T> _c) {
        this.container = _c;
    }

    public void listen(T _element) {
        if (container.hand().containsObj(_element)) {
            container.discard(_element);
        } else {
            container.restore(_element);
        }
        updateCardsInPanels(true);
/* public void afficherMainUtilisateurTarotChien() {
        GameTarot partie_=partieTarot();
        //Les regles du tarot ne sont pas modifiees
        //Seuls la facon d'afficher peut changer
        HandTarot mainUtilisateur_=partie_.mainUtilisateurTriee(getDisplayingTarot());
//        setCanDiscard(true);
        updateCardsInPanelTarotDog(getPanelHand(), mainUtilisateur_, true);


         GameTarot partie_=partieTarot();
        afficherMainUtilisateurTarotChien();
        setChien(partie_.getPliEnCours().getCartes(),true);


public void setChien(HandTarot _main,boolean _ecouteur) {
//        setCanDiscard(_ecouteur);
        updateCardsInPanelTarotDog(tapisTarot().getCenterDeck(), _main, _ecouteur);
    }

    }*/
        container.afterHands();
    }

    public void updateCardsInPanels(boolean _listener) {
        updateCardsInPanelTarotDog(container.getPanelHand(),container.hand(), _listener);
        updateCardsInPanelTarotDog(container.getCenterDeck(),container.discarded(), _listener);
        int c_ = container.getEcart() - container.getCenterDeck().getComponentCount();
        for (int i = 0; i < c_; i++) {
            container.getCenterDeck().add(new ContainerSingUtil<T>(container.converter()).prepare(container.window(), container.getOwner().getFrames().currentLg(), container.getCenterDeck().getComponentCount() == 0).getPaintableLabel());
        }
        container.getCenterDeck().setSize(container.getCenterDeck().getPreferredSizeValue());
    }

    public void updateCardsInPanelTarotDog(AbsPanel _panel, IdList<T> _hand, boolean _ecouteur) {
        _panel.removeAll();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        IdList<T> ecart_;
        if (_ecouteur) {
            ecart_ = container.ecartables();
        } else {
            ecart_ = new IdList<T>();
        }
        for (GraphicCard<T> c: new ContainerSingUtil<T>(container.converter()).getGraphicCardsGene(container.window(),lg_,_hand)) {
            if (_ecouteur) {
                String err_ = container.errMessage(ecart_, c.getCard());
                if (err_.isEmpty()) {
                    c.addMouseListener(new ListenerCardDiscard<T>(container,c.getCard()));
                } else {
                    c.getPaintableLabel().setToolTipText(err_);
                }
            }
            _panel.add(c.getPaintableLabel());
        }
//        boolean entered_ = false;
//        for(CardTarot c: _hand)
//        {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
////            carte_.addMouseListener(new EcouteurCarteTarotChien(_hand.carte(indice_),_inHand));
//            carte_.addMouseListener(new ListenerCardTarotSingleDog(this,c,_inHand));
//            _panel.add(carte_);
//            entered_ = true;
//        }
        _panel.setSize(_panel.getPreferredSizeValue());
    }

}
