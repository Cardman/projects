package cards.gui.events;


import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerPlayableTarot;
import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.AbsMouseLocation;
import code.gui.GuiConstants;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.core.StringUtil;

public class ListenerCardTarotHandful extends AbstractListenerCardTarot {

    private final boolean included;
    public ListenerCardTarotHandful(ContainerPlayableTarot _container, CardTarot _pcarte, boolean _included) {
        super(_container, _pcarte);
        included = _included;
    }
    @Override
    public boolean canListen() {
        return true;
    }
    @Override
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
    }
    @Override
    protected void verifierRegles() {
        TranslationsLg lg_ = getContainer().getOwner().getFrames().currentLg();
        if(StringUtil.quickEq(getContainer().getRaisonCourante(),ContainerTarot.EMPTY)) {
            CardTarot d_ = getCarteVerif();
            if (included) {
                getContainer().getCurrentIncludedTrumps().jouer(d_);
                getContainer().getCurrentExcludedTrumps().ajouter(d_);
            } else {
                getContainer().getCurrentIncludedTrumps().ajouter(d_);
                getContainer().getCurrentExcludedTrumps().jouer(d_);
            }
            getContainer().displayTrumps();
            if (getContainer().getChoosenHandful() != Handfuls.NO) {
                String mes_ = getContainer().file().getVal(MessagesGuiCards.MAIN_REMOVE_TRUMPS_HANDFUL);
                int exces_ = getContainer().getCurrentIncludedTrumps().total()- getContainer().required();
                getContainer().getInfoCurrentHandful().setText(StringUtil.simpleStringsFormat(mes_, Long.toString(exces_), Games.toString(getContainer().getChoosenHandful(),lg_)));
            }

        }else{
            String finalMessage_ = StringUtil.concat(getContainer().file().getVal(MessagesGuiCards.MAIN_CANT_PLAY),getContainer().getRaisonCourante());
            String title_ = getContainer().getMessages().getVal(WindowCards.TOO_GAME);
            getContainer().getOwner().getFrames().getMessageDialogAbs().input(getContainer().getOwner().getCommonFrame(),finalMessage_,title_, GuiConstants.ERROR_MESSAGE);
        }
    }
}
