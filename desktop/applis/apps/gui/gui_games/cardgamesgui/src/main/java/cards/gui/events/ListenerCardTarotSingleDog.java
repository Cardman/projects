package cards.gui.events;



import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.gui.labels.GraphicTarotCard;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.ReasonDiscard;
import code.gui.AbsMouseLocation;
import code.gui.GuiConstants;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
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
    public boolean canListen() {
        return container.isCanDiscard();
    }
    @Override
    protected boolean playCardExited(AbsMouseLocation _event) {
        if (inHand) {
            return _event.getYcoord() < 0;
        }
        return _event.getYcoord() > component.getHeight();
    }
    @Override
    protected void verifierRegles() {
        GameTarot partie_=container.partieTarot();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if(partie_.getDistribution().hand().contient(getCarteVerif())) {
            ReasonDiscard reason_ = partie_.autoriseEcartDe(getCarteVerif());
            if(reason_ == ReasonDiscard.NOTHING){
                container.ajouterUneCarteAuChien(getCarteVerif());
            }else{
                String mesCard_ = StringUtil.simpleStringsFormat(container.file().getVal(MessagesGuiCards.MAIN_CANT_DISCARD), Games.toString(getCarteVerif(),lg_));
                String mesReason_ = Games.autoriseMessEcartDe(reason_,getCarteVerif(),lg_).toString();
//                String mesReason_ = StringUtil.simpleStringsFormat(container.file().getVal(MessagesGuiCards.REASON), Games.autoriseMessEcartDe(reason_,getCarteVerif(),lg_).toString());
                container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(),
                        StringUtil.concat(mesCard_,ContainerGame.RETURN_LINE,mesReason_),
                        container.getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE), GuiConstants.ERROR_MESSAGE);
            }
        } else {
            container.retirerUneCarteDuChien(getCarteVerif());
        }

    }
}
