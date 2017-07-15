package cards.gui.events;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import code.gui.ConfirmDialog;
import code.util.StringList;
import code.util.consts.Constants;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.gui.containers.ContainerTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.PlayingDog;

public class ListenerCardTarotSingleBeforeDog extends AbstractListenerCardTarot {

    private ContainerSingleTarot container;
    public ListenerCardTarotSingleBeforeDog(ContainerSingleTarot _container,CardTarot _card) {
        super(_container, _card);
        container = _container;
    }
    @Override
    protected boolean playCardExited(MouseEvent _event) {
        return _event.getPoint().y < 0;
    }
    @Override
    protected boolean canListen() {
        return container.isCanCall();
    }
    @Override
    protected void verifierRegles(){
        GameTarot partie_=container.partieTarot();
        if (!partie_.getRegles().getDiscardAfterCall()) {
            if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                if (partie_.getPliEnCours().total() != partie_.getDistribution().derniereMain().total()) {
                    int remove_ = partie_.getDistribution().derniereMain().total();
                    remove_ -= partie_.getPliEnCours().total();
                    String mesCard_ = StringList.simpleFormat(container.getMessages().getVal(MainWindow.HAS_TO_DISCARD), remove_);
                    ConfirmDialog.showMessage(container.getOwner(), mesCard_, container.getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE), Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
                    //JOptionPane.showMessageDialog(container.getOwner(),mesCard_,container.getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),JOptionPane.ERROR_MESSAGE);
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
            container.ajouterTexteDansZone(container.pseudo()+ContainerGame.INTRODUCTION_PTS+getCarteVerif()+ContainerTarot.RETURN_LINE_CHAR);
            container.getPanneauBoutonsJeu().removeAll();
            if(partie_.getContrat()!=BidTarot.SLAM) {
//                container.ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                container.getValidateDog().setEnabledLabel(true);
                container.getPanneauBoutonsJeu().add(container.getValidateDog());
                container.getSlamButton().setEnabledLabel(true);
                container.getSlamButton().setVisible(true);
                container.getPanneauBoutonsJeu().add(container.getSlamButton());
//                container.addButtonValidateDogTarot(container.getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                container.pack();
            } else {
                container.debutPliTarot(false);
            }
            return;
        }
        container.setCanCall(false);
        HandTarot cartesAppel_ = new HandTarot();
        cartesAppel_.ajouter(getCarteVerif());
        partie_.initConfianceAppeleUtilisateur(cartesAppel_);
        container.ajouterTexteDansZone(container.pseudo()+ContainerGame.INTRODUCTION_PTS+getCarteVerif()+ContainerTarot.RETURN_LINE_CHAR);
        if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            container.voirChien();
        } else {
            partie_.gererChienInconnu();
            container.getPanneauBoutonsJeu().removeAll();
            if(partie_.getContrat()!=BidTarot.SLAM) {
                container.getSlamButton().setEnabledLabel(true);
                container.getSlamButton().setVisible(true);
                container.getPanneauBoutonsJeu().add(container.getSlamButton());
//                container.ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                container.addButtonNextTrickTarot(container.getMessages().getVal(MainWindow.GO_CARD_GAME), true);
                container.pack();
            } else {
                container.debutPliTarot(false);
            }
        }
    }
}
