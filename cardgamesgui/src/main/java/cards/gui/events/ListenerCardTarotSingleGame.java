package cards.gui.events;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.gui.containers.ContainerTarot;
import cards.gui.labels.MiniTarotCard;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.gui.ConfirmDialog;
import code.gui.Panel;
import code.util.EnumList;
import code.util.StringList;

public class ListenerCardTarotSingleGame extends AbstractListenerCardTarot {

    private ContainerSingleTarot container;
    public ListenerCardTarotSingleGame(ContainerSingleTarot _container,CardTarot _pcarte) {
        super(_container,_pcarte);
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
        if(StringList.quickEq(container.getRaisonCourante(),ContainerTarot.EMPTY)) {
            GameTarot partie_=container.partieTarot();
            if (container.getChoosenHandful() != Handfuls.NO) {
                if (!partie_.isValidHandful(container.getChoosenHandful(), container.getCurrentIncludedTrumps(), container.getCurrentExcludedTrumps(), lg_)) {
                    String mes_ = StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.CANT_DECLARE_DETAIL), container.getChoosenHandful().toString(lg_));
                    String finalMessage_ = StringList.concat(mes_,ContainerGame.RETURN_LINE,partie_.getErrorHandful());
                    String title_ = container.getMessages().getVal(MainWindow.CANT_DECLARE_TITLE);
                    ConfirmDialog.showMessage(container.getOwner(),finalMessage_,title_,lg_, JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String pseudo_=container.pseudo();
                EnumList<Handfuls> an_=new EnumList<Handfuls>();
                an_.add(container.getChoosenHandful());
                partie_.ajouterAnnoncesPoignees(DealTarot.NUMERO_UTILISATEUR,an_);
                container.getHandfuls().getVal(DealTarot.NUMERO_UTILISATEUR).setText(container.getChoosenHandful().toString(lg_));
                Panel panelToSet_ = container.getDeclaredHandfuls().getVal(DealTarot.NUMERO_UTILISATEUR);
                panelToSet_.removeAll();
                for(CardTarot c: container.getCurrentIncludedTrumps()) {
                    MiniTarotCard carte_=new MiniTarotCard(lg_, c);
                    panelToSet_.add(carte_);
                }
                partie_.ajouterPoignee(container.getCurrentIncludedTrumps(),DealTarot.NUMERO_UTILISATEUR);
                container.ajouterTexteDansZone(StringList.concat(pseudo_,ContainerGame.INTRODUCTION_PTS,container.getChoosenHandful().toString(lg_),ContainerGame.RETURN_LINE));
            }
            EnumList<Miseres> selectedMiseres_ = container.getAllowedMiseres();
            if (!selectedMiseres_.isEmpty()) {
                EnumList<Miseres> miseres_ = partie_.getAnnoncesMiseresPossibles(DealTarot.NUMERO_UTILISATEUR);
                EnumList<Miseres> allowedSelectedMiseres_ = new EnumList<Miseres>();
                for (Miseres m: selectedMiseres_) {
                    if (!miseres_.containsObj(m)) {
                        continue;
                    }
                    container.ajouterTexteDansZone(StringList.concat(container.pseudo(),ContainerGame.INTRODUCTION_PTS,m.toString(lg_)));
                    allowedSelectedMiseres_.add(m);
                }
                partie_.ajouterAnnoncesMiseres(DealTarot.NUMERO_UTILISATEUR,allowedSelectedMiseres_);
            }
            if(partie_.autorise(getCarteVerif(), lg_)) {
                if (container.getScrollDeclaringHandful().isVisible()) {
                    container.getScrollDeclaringHandful().setVisible(false);
                    container.pack();
                }
                container.setaJoueCarte(true);
                container.finPliTarot(getCarteVerif());
            }else{
                String mes_ = StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.CANT_PLAY_CARD), getCarteVerif().toString(lg_));
                String finalMessage_ = StringList.concat(mes_,ContainerGame.RETURN_LINE,partie_.getErreurDeJeu());
                String title_ = container.getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE);
                ConfirmDialog.showMessage(container.getOwner(),finalMessage_,title_,lg_,JOptionPane.ERROR_MESSAGE);
            }
        }else{
            String finalMessage_ = StringList.concat(container.getMessages().getVal(MainWindow.CANT_PLAY),container.getRaisonCourante());
            String title_ = container.getMessages().getVal(MainWindow.TOO_GAME);
            ConfirmDialog.showMessage(container.getOwner(),finalMessage_,title_, lg_,JOptionPane.ERROR_MESSAGE);
        }
    }
}
