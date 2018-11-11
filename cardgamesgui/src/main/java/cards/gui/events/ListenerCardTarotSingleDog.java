package cards.gui.events;
import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.ConfirmDialog;
import code.util.StringList;

public class ListenerCardTarotSingleDog extends AbstractListenerCardTarot {

    private ContainerSingleTarot container;
    private boolean inHand;
    public ListenerCardTarotSingleDog(ContainerSingleTarot _container,CardTarot _pcarte,boolean _inHand) {
        super(_container,_pcarte);
        container = _container;
        inHand = _inHand;
    }
    @Override
    protected boolean canListen() {
        return container.isCanDiscard();
    }
    @Override
    protected boolean playCardExited(MouseEvent _event) {
        if (inHand) {
            return _event.getPoint().y < 0;
        }
        Component c_ = _event.getComponent();
        if (c_ == null) {
            return false;
        }
        return _event.getPoint().y > c_.getHeight();
    }
    @Override
    protected void verifierRegles() {
        GameTarot partie_=container.partieTarot();
        String lg_ = container.getOwner().getLanguageKey();
        if(partie_.getDistribution().main().contient(getCarteVerif())) {
            if(partie_.autoriseEcartDe(getCarteVerif(), lg_)){
                container.ajouterUneCarteAuChien(getCarteVerif());
            }else{
                String mesCard_ = StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.CANT_DISCARD), getCarteVerif().toString(lg_));
                String mesReason_ = StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.REASON), partie_.getErreurDEcart());
                ConfirmDialog.showMessage(container.getOwner(), StringList.concat(mesCard_,ContainerGame.RETURN_LINE,mesReason_),container.getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE), lg_, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            container.retirerUneCarteDuChien(getCarteVerif());
        }

    }
}
