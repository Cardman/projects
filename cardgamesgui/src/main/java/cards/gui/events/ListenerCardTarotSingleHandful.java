package cards.gui.events;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerSingleTarot;
import cards.gui.containers.ContainerTarot;
import cards.tarot.GameTarot;
import cards.tarot.GameTarotCommonPlaying;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.ConfirmDialog;
import code.util.StringList;

public class ListenerCardTarotSingleHandful extends AbstractListenerCardTarot {

    private ContainerSingleTarot container;
    private boolean included;
    public ListenerCardTarotSingleHandful(ContainerSingleTarot _container,CardTarot _pcarte, boolean _included) {
        super(_container, _pcarte);
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
        if(StringList.quickEq(container.getRaisonCourante(),ContainerTarot.EMPTY)) {
            if (included) {
                container.getCurrentIncludedTrumps().jouer(getCarteVerif());
                container.getCurrentExcludedTrumps().ajouter(getCarteVerif());
            } else {
                container.getCurrentIncludedTrumps().ajouter(getCarteVerif());
                container.getCurrentExcludedTrumps().jouer(getCarteVerif());
            }
            GameTarot partie_=container.partieTarot();
            RulesTarot regles_ = partie_.getRegles();
            container.displayTrumpsForHandful(GameTarotCommonPlaying.atoutsPoignee(partie_.getDistribution().hand().couleurs()));
            if (container.getChoosenHandful() != Handfuls.NO) {
                String mes_ = container.getMessages().getVal(MainWindow.REMOVE_TRUMPS_HANDFUL);
                int exces_ = container.getCurrentIncludedTrumps().total()-regles_.getPoigneesAutorisees().getVal(container.getChoosenHandful());
                container.getInfoCurrentHandful().setText(StringList.simpleStringsFormat(mes_, Long.toString(exces_), Games.toString(container.getChoosenHandful(),lg_)));
            }

        }else{
            String finalMessage_ = StringList.concat(container.getMessages().getVal(MainWindow.CANT_PLAY),container.getRaisonCourante());
            String title_ = container.getMessages().getVal(MainWindow.TOO_GAME);
            ConfirmDialog.showMessage(container.getOwner(),finalMessage_,title_,lg_,JOptionPane.ERROR_MESSAGE);
        }
    }
}
