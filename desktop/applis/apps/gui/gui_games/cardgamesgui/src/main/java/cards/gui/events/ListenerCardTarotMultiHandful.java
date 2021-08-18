package cards.gui.events;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerMultiTarot;
import cards.gui.containers.ContainerTarot;
import cards.tarot.GameTarotCommonPlaying;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.ConfirmDialog;
import code.util.core.StringUtil;

public class ListenerCardTarotMultiHandful extends AbstractListenerCardTarot {
    private ContainerMultiTarot container;
    private boolean included;
    public ListenerCardTarotMultiHandful(ContainerMultiTarot _container,CardTarot _pcarte, boolean _included) {
        super(_container,_pcarte);
        container = _container;
        included = _included;
    }
    @Override
    protected boolean canListen() {
        return container.isCanExcludeTrumps();
    }
    @Override
    protected boolean playCardExited(MouseEvent _event) {
        return _event.getY() < 0;
    }
    @Override
    protected void verifierRegles() {
        String lg_ = container.getOwner().getLanguageKey();
        if(StringUtil.quickEq(container.getRaisonCourante(),ContainerTarot.EMPTY)) {
            if (included) {
                container.getCurrentIncludedTrumps().jouer(getCarteVerif());
                container.getCurrentExcludedTrumps().ajouter(getCarteVerif());
            } else {
                container.getCurrentIncludedTrumps().ajouter(getCarteVerif());
                container.getCurrentExcludedTrumps().jouer(getCarteVerif());
            }
            container.displayTrumpsForHandfulMulti(GameTarotCommonPlaying.atoutsPoignee(container.getPlayerHand().couleurs()));
            if (container.getChoosenHandful() != Handfuls.NO) {
                String mes_ = container.getMessages().getVal(WindowCards.REMOVE_TRUMPS_HANDFUL);
                int exces_ = container.getCurrentIncludedTrumps().total()-container.getRequiredTrumps().getVal(container.getChoosenHandful());
                container.getInfoCurrentHandful().setText(StringUtil.simpleStringsFormat(mes_, Long.toString(exces_), Games.toString(container.getChoosenHandful(),lg_)));
            }
        }else{
            String finalMessage_ = StringUtil.concat(container.getMessages().getVal(WindowCards.CANT_PLAY),container.getRaisonCourante());
            String title_ = container.getMessages().getVal(WindowCards.TOO_GAME);
            ConfirmDialog.showMessage(container.getOwner(), finalMessage_,title_, lg_, JOptionPane.ERROR_MESSAGE);
        }
    }

}
