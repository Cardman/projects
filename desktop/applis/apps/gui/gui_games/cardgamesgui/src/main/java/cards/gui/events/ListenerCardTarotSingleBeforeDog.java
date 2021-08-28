package cards.gui.events;



import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.PlayingDog;
import code.gui.AbsMouseLocation;
import code.gui.ConfirmDialog;
import code.gui.GuiConstants;
import code.util.core.StringUtil;

public class ListenerCardTarotSingleBeforeDog extends AbstractListenerCardTarot {

    private ContainerSingleTarot container;
    public ListenerCardTarotSingleBeforeDog(ContainerSingleTarot _container,CardTarot _card) {
        super(_container, _card);
        container = _container;
    }
    @Override
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
    }
    @Override
    protected boolean canListen() {
        return container.isCanCall();
    }
    @Override
    protected void verifierRegles(){
        String lg_ = container.getOwner().getLanguageKey();
        GameTarot partie_=container.partieTarot();
        if (!partie_.getRegles().getDiscardAfterCall()) {
            if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                if (partie_.getPliEnCours().total() != partie_.getDistribution().derniereMain().total()) {
                    int remove_ = partie_.getDistribution().derniereMain().total();
                    remove_ -= partie_.getPliEnCours().total();
                    String mesCard_ = StringUtil.simpleNumberFormat(container.getMessages().getVal(WindowCards.HAS_TO_DISCARD), remove_);
                    ConfirmDialog.showMessage(container.getOwner(), mesCard_, container.getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE), lg_, GuiConstants.ERROR_MESSAGE);
                    return;
                }
            } else {
                partie_.gererChienInconnu();
            }
            container.setCanCall(false);
            container.setCanDiscard(false);
            HandTarot cartesAppel_ = new HandTarot();
            cartesAppel_.ajouter(getCarteVerif());
            partie_.initConfianceAppeleUtilisateur(cartesAppel_);
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
            return;
        }
        container.setCanCall(false);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(getCarteVerif());
        partie_.initConfianceAppeleUtilisateur(cartesAppel_);
        container.ajouterTexteDansZone(StringUtil.concat(container.pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(getCarteVerif(),lg_),ContainerGame.RETURN_LINE));
        if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            container.voirChien();
        } else {
            partie_.gererChienInconnu();
            container.getPanneauBoutonsJeu().removeAll();
            if(partie_.getContrat()!=BidTarot.SLAM) {
                container.getSlamButton().setEnabled(true);
                container.getSlamButton().setVisible(true);
                container.getPanneauBoutonsJeu().add(container.getSlamButton());
                container.addButtonNextTrickTarot(container.getMessages().getVal(WindowCards.GO_CARD_GAME), true);
                container.pack();
            } else {
                container.debutPliTarot();
            }
        }
    }
}
