package cards.gui.events;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cards.gui.MainWindow;
import cards.gui.containers.ContainerMultiTarot;
import cards.gui.containers.ContainerTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.ConfirmDialog;
import code.util.StringList;
import code.util.consts.Constants;

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
        return _event.getPoint().y < 0;
    }
    @Override
    protected void verifierRegles() {
        if(StringList.quickEq(container.getRaisonCourante(),ContainerTarot.EMPTY)) {
            if (included) {
                container.getCurrentIncludedTrumps().jouer(getCarteVerif());
                container.getCurrentExcludedTrumps().ajouter(getCarteVerif());
            } else {
                container.getCurrentIncludedTrumps().ajouter(getCarteVerif());
                container.getCurrentExcludedTrumps().jouer(getCarteVerif());
            }
            container.displayTrumpsForHandfulMulti(GameTarot.atoutsPoignee(container.getPlayerHand().couleurs()));
            if (container.getChoosenHandful() != Handfuls.NO) {
                String mes_ = container.getMessages().getVal(MainWindow.REMOVE_TRUMPS_HANDFUL);
                int exces_ = container.getCurrentIncludedTrumps().total()-container.getRequiredTrumps().getVal(container.getChoosenHandful());
                container.getInfoCurrentHandful().setText(StringList.simpleStringsFormat(mes_, Long.toString(exces_), container.getChoosenHandful().display()));
            }
        }else{
            String finalMessage_ = StringList.concat(container.getMessages().getVal(MainWindow.CANT_PLAY),container.getRaisonCourante());
            String title_ = container.getMessages().getVal(MainWindow.TOO_GAME);
            ConfirmDialog.showMessage(container.getOwner(), finalMessage_,title_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(container.getOwner(),container.getMessages().getVal(MainWindow.CANT_PLAY)+container.getRaisonCourante(),container.getMessages().getVal(MainWindow.TOO_GAME),JOptionPane.ERROR_MESSAGE);
        }
    }

}
