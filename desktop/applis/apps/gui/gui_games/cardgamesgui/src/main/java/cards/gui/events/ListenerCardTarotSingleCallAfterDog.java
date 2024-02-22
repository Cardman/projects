package cards.gui.events;


import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.enumerations.CardTarot;

public class ListenerCardTarotSingleCallAfterDog extends AbstractListenerCardTarot {

    private final ContainerSingleTarot container;
    public ListenerCardTarotSingleCallAfterDog(ContainerSingleTarot _container, CardTarot _card) {
        super(_container, _card);
        container = _container;
    }
//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        return _event.getYcoord() < 0;
//    }
    @Override
    protected void verifierRegles(){
        container.setCalledCard(getCarteVerif());
//        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
//        GameTarot partie_=container.partieTarot();
//        if (partie_.getContrat().getJeuChien() != PlayingDog.WITH) {
//            partie_.gererChienInconnu();
//        }
//        if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
//            if (partie_.getPliEnCours().total() != partie_.getDistribution().derniereMain().total()) {
//                int remove_ = partie_.getDistribution().derniereMain().total();
//                remove_ -= partie_.getPliEnCours().total();
//                String mesCard_ = StringUtil.simpleNumberFormat(container.file().getVal(MessagesGuiCards.MAIN_HAS_TO_DISCARD), remove_);
//                container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(), mesCard_, container.getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE), GuiConstants.ERROR_MESSAGE);
//                return;
//            }
//        } else {
//            partie_.gererChienInconnu();
//        }
        container.updateCardsInPanelTarotCallAfterDog();
        container.afficherMainUtilisateurTarotChien();
        container.setChien(container.partieTarot().getPliEnCours().getCards(),true);
        container.updateButtons();
        container.pack();
//        container.setCanDiscard(false);
//        HandTarot cartesAppel_ = new HandTarot();
//        cartesAppel_.ajouter(getCarteVerif());
//        partie_.initConfianceAppeleUtilisateur(container.getOwner().baseWindow().getIa().getTarot().strategieAppelUser(cartesAppel_));
//        container.ajouterTexteDansZone(StringUtil.concat(container.pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(getCarteVerif(),lg_),ContainerGame.RETURN_LINE));
//        container.getPanneauBoutonsJeu().removeAll();
//        if(partie_.getContrat()!=BidTarot.SLAM) {
//            container.getValidateDog().setEnabled(true);
//            container.getPanneauBoutonsJeu().add(container.getValidateDog());
//            container.getSlamButton().setEnabled(true);
//            container.getSlamButton().setVisible(true);
//            container.getPanneauBoutonsJeu().add(container.getSlamButton());
//            container.pack();
//        } else {
//            container.debutPliTarot();
//        }
    }
}
