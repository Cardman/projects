package cards.gui.events;


import cards.gui.containers.ContainerSingleWithDiscard;
import cards.gui.containers.ContainerSingleWithDiscardUtil;

public class ListenerCardDiscard<T> extends AbstractListenerCard<T> {

    private final ContainerSingleWithDiscard<T> container;

    public ListenerCardDiscard(ContainerSingleWithDiscard<T> _container, T _pcarte) {
        super(_container,_pcarte);
        container = _container;
    }
//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        if (inHand) {
//            return _event.getYcoord() < 0;
//        }
//        return _event.getYcoord() > component.getHeight();
//    }
    @Override
    protected void verifierRegles() {
        new ContainerSingleWithDiscardUtil<T>(container).listen(getCard());
//        GameTarot partie_=container.partieTarot();
////        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
//        if(partie_.getDistribution().hand().contient(getCard())) {
//            container.ajouterUneCarteAuChien(getCard());
////            ReasonDiscard reason_ = partie_.autoriseEcartDe(getCarteVerif());
////            if(reason_ == ReasonDiscard.NOTHING){
////                container.ajouterUneCarteAuChien(getCarteVerif());
////            }else{
////                String mesCard_ = StringUtil.simpleStringsFormat(container.file().getVal(MessagesGuiCards.MAIN_CANT_DISCARD), Games.toString(getCarteVerif(),lg_));
////                String mesReason_ = Games.autoriseMessEcartDe(reason_,getCarteVerif(),lg_).toString();
//////                String mesReason_ = StringUtil.simpleStringsFormat(container.file().getVal(MessagesGuiCards.REASON), Games.autoriseMessEcartDe(reason_,getCarteVerif(),lg_).toString());
////                container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(),
////                        StringUtil.concat(mesCard_,ContainerGame.RETURN_LINE,mesReason_),
////                        container.getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE), GuiConstants.ERROR_MESSAGE);
////            }
//        } else {
//            container.retirerUneCarteDuChien(getCard());
//        }

    }
}
