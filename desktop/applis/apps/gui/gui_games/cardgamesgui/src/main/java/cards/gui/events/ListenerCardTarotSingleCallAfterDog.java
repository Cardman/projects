package cards.gui.events;



import cards.facade.Games;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.PlayingDog;
import code.gui.AbsMouseLocation;
import code.sml.util.TranslationsLg;
import code.util.core.StringUtil;

public class ListenerCardTarotSingleCallAfterDog extends AbstractListenerCardTarot {

    private final ContainerSingleTarot container;
    public ListenerCardTarotSingleCallAfterDog(ContainerSingleTarot _container, CardTarot _card) {
        super(_container, _card);
        container = _container;
    }
    @Override
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
    }
    @Override
    protected void verifierRegles(){
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        GameTarot partie_=container.partieTarot();
        if (partie_.getContrat().getJeuChien() != PlayingDog.WITH) {
            partie_.gererChienInconnu();
        }
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
        container.updateCardsInPanelTarotCallAfterDog(false);
        container.updateCardsInPanelTarotJeu(false);
//        container.setCanDiscard(false);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(getCarteVerif());
        partie_.initConfianceAppeleUtilisateur(container.getOwner().baseWindow().getIa().getTarot().strategieAppelUser(cartesAppel_));
        container.ajouterTexteDansZone(StringUtil.concat(container.pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(getCarteVerif(),lg_),ContainerGame.RETURN_LINE));
        container.getPanneauBoutonsJeu().removeAll();
        if(partie_.getContrat()!=BidTarot.SLAM) {
            container.getValidateDog().setEnabled(true);
            container.getPanneauBoutonsJeu().add(container.getValidateDog());
            container.getSlamButton().setEnabled(true);
            container.getSlamButton().setVisible(true);
            container.getPanneauBoutonsJeu().add(container.getSlamButton());
            container.pack();
        } else {
            container.debutPliTarot();
        }
    }
}
