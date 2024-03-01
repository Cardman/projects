package cards.gui.events;


import cards.facade.Games;
import cards.gui.containers.ContainerPlayableTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.core.StringUtil;

public class ListenerCardTarotHandful extends AbstractListenerCard<CardTarot> {


    private final ContainerPlayableTarot container;
    private final boolean included;
    public ListenerCardTarotHandful(ContainerPlayableTarot _container, CardTarot _pcarte, boolean _included) {
        super(_container, _pcarte);
        included = _included;
        container = _container;
    }
//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        return _event.getYcoord() < 0;
//    }
    @Override
    protected void verifierRegles() {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        CardTarot d_ = getCard();
        if (included) {
            container.getCurrentIncludedTrumps().jouer(d_);
            container.getCurrentExcludedTrumps().ajouter(d_);
        } else {
            container.getCurrentIncludedTrumps().ajouter(d_);
            container.getCurrentExcludedTrumps().jouer(d_);
        }
        container.displayTrumps();
        if (container.getChoosenHandful() != Handfuls.NO) {
            String mes_ = container.file().getVal(MessagesGuiCards.MAIN_REMOVE_TRUMPS_HANDFUL);
            int exces_ = container.getCurrentIncludedTrumps().total()- container.required();
            container.getInfoCurrentHandful().setText(StringUtil.simpleStringsFormat(mes_, Long.toString(exces_), Games.toString(container.getChoosenHandful(),lg_)));
        }
        container.refreshCurrentHand();
//        if(StringUtil.quickEq(getContainer().getRaisonCourante(),ContainerTarot.EMPTY)) {
//            CardTarot d_ = getCarteVerif();
//            if (included) {
//                getContainer().getCurrentIncludedTrumps().jouer(d_);
//                getContainer().getCurrentExcludedTrumps().ajouter(d_);
//            } else {
//                getContainer().getCurrentIncludedTrumps().ajouter(d_);
//                getContainer().getCurrentExcludedTrumps().jouer(d_);
//            }
//            getContainer().displayTrumps();
//            if (getContainer().getChoosenHandful() != Handfuls.NO) {
//                String mes_ = getContainer().file().getVal(MessagesGuiCards.MAIN_REMOVE_TRUMPS_HANDFUL);
//                int exces_ = getContainer().getCurrentIncludedTrumps().total()- getContainer().required();
//                getContainer().getInfoCurrentHandful().setText(StringUtil.simpleStringsFormat(mes_, Long.toString(exces_), Games.toString(getContainer().getChoosenHandful(),lg_)));
//            }
//
//        }else{
//            String finalMessage_ = StringUtil.concat(getContainer().file().getVal(MessagesGuiCards.MAIN_CANT_PLAY),getContainer().getRaisonCourante());
//            String title_ = getContainer().getMessages().getVal(WindowCards.TOO_GAME);
//            getContainer().getOwner().getFrames().getMessageDialogAbs().input(getContainer().getOwner().getCommonFrame(),finalMessage_,title_, GuiConstants.ERROR_MESSAGE);
//        }
    }
}
