package cards.gui.events;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerPresident;
import cards.gui.containers.ContainerSinglePresident;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.enumerations.CardPresident;
import code.gui.ConfirmDialog;
import code.util.StringList;

public class ListenerCardPresidentSingleGame extends
        AbstractListenerCardPresident {

    private ContainerSinglePresident container;

    public ListenerCardPresidentSingleGame(ContainerSinglePresident _container,
            CardPresident _card, byte _index) {
        super(_container, _card, _index);
        container = _container;
    }

    @Override
    protected boolean canListen() {
        return container.isCanPlay();
    }

    @Override
    protected boolean playCardExited(MouseEvent _event) {
        return _event.getPoint().y < 0;
    }

    @Override
    protected void verifierRegles() {
        String lg_ = container.getOwner().getLanguageKey();
        if(StringList.quickEq(container.getRaisonCourante(),ContainerPresident.EMPTY)){
            GamePresident game_ = container.partiePresident();
            boolean allow_ = game_.allowPlaying(DealPresident.NUMERO_UTILISATEUR, getCarteVerif(), getIndexVerif());
            if (!allow_) {
                StringBuilder mes_ = new StringBuilder(StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.CANT_PLAY_CARD), Games.toString(getCarteVerif(),lg_)));
                mes_.append(ContainerGame.RETURN_LINE);
                mes_.append(Games.autorisePresident(game_,DealPresident.NUMERO_UTILISATEUR, getCarteVerif(), getIndexVerif(), lg_));
                String finalMessage_ = mes_.toString();
                String title_ = container.getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE);
                ConfirmDialog.showMessage(container.getOwner(), finalMessage_, title_, lg_, JOptionPane.ERROR_MESSAGE);
            } else {
                container.setaJoueCarte(true);
                container.finPliPresident(getCarteVerif(), getIndexVerif());
            }
        }else{
            String finalMessage_ = StringList.concat(container.getMessages().getVal(MainWindow.CANT_PLAY),container.getRaisonCourante());
            String title_ = container.getMessages().getVal(MainWindow.TOO_GAME);
            ConfirmDialog.showMessage(container.getOwner(), finalMessage_, title_, lg_, JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(container.getOwner(),container.getMessages().getVal(MainWindow.CANT_PLAY)+container.getRaisonCourante(),container.getMessages().getVal(MainWindow.TOO_GAME),JOptionPane.ERROR_MESSAGE);
        }
    }
}
