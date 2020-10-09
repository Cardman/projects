package cards.gui.events;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.gui.labels.GraphicTarotCard;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.ReasonDiscard;
import code.gui.ConfirmDialog;
import code.util.core.StringUtil;

public class ListenerCardTarotSingleDog extends AbstractListenerCardTarot {

    private ContainerSingleTarot container;
    private boolean inHand;
    private GraphicTarotCard component;
    public ListenerCardTarotSingleDog(ContainerSingleTarot _container,CardTarot _pcarte,boolean _inHand, GraphicTarotCard _component) {
        super(_container,_pcarte);
        container = _container;
        inHand = _inHand;
        component = _component;
    }
    @Override
    protected boolean canListen() {
        return container.isCanDiscard();
    }
    @Override
    protected boolean playCardExited(MouseEvent _event) {
        if (inHand) {
            return _event.getY() < 0;
        }
        return _event.getY() > component.getHeight();
    }
    @Override
    protected void verifierRegles() {
        GameTarot partie_=container.partieTarot();
        String lg_ = container.getOwner().getLanguageKey();
        if(partie_.getDistribution().hand().contient(getCarteVerif())) {
            ReasonDiscard reason_ = partie_.autoriseEcartDe(getCarteVerif());
            if(reason_ == ReasonDiscard.NOTHING){
                container.ajouterUneCarteAuChien(getCarteVerif());
            }else{
                String mesCard_ = StringUtil.simpleStringsFormat(container.getMessages().getVal(MainWindow.CANT_DISCARD), Games.toString(getCarteVerif(),lg_));
                String mesReason_ = StringUtil.simpleStringsFormat(container.getMessages().getVal(MainWindow.REASON), Games.autoriseMessEcartDe(partie_,reason_,getCarteVerif(),lg_).toString());
                ConfirmDialog.showMessage(container.getOwner(),
                        StringUtil.concat(mesCard_,ContainerGame.RETURN_LINE,mesReason_),
                        container.getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE), lg_, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            container.retirerUneCarteDuChien(getCarteVerif());
        }

    }
}
