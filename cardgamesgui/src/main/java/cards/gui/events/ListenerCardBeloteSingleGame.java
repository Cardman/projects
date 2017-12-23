package cards.gui.events;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import code.gui.ConfirmDialog;
import code.util.StringList;
import code.util.consts.Constants;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerSingleBelote;
import cards.gui.labels.GraphicBeloteCard;

public class ListenerCardBeloteSingleGame extends AbstractListenerCardBelote {

    private ContainerSingleBelote container;

    public ListenerCardBeloteSingleGame(ContainerSingleBelote _container,
            CardBelote _pcarte) {
        super(_container, _pcarte);
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
    protected void verifierRegles(){
        if(StringList.quickEq(container.getRaisonCourante(),ContainerBelote.EMPTY)){
            GameBelote partie_=container.partieBelote();
            boolean autorise_ = partie_.autorise(getCarteVerif(),Constants.getLanguage());
            if(container.isAnnonceBeloteRebelote()) {
                boolean annonceBeloteRebelote_ = partie_.cartesBeloteRebelote().contient(getCarteVerif());
                if (!annonceBeloteRebelote_) {
                    autorise_ = false;
                }
                if(!annonceBeloteRebelote_) {
                    //Si l'utilisateur joue une carte de la belote rebelote en l'annoncant mais en respectant pas les regles
                    //alors c'est le message d'erreur sur la jouerie des cartes qui est prioritaire au
                    //message d'erreur sur la belote rebelote
                    HandBelote cartesBeloteRebelote_=partie_.cartesBeloteRebelote();
                    /*On ordonne la poignee d'atouts*/
                    JPanel panneau_=new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
                    for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(cartesBeloteRebelote_)) {
                        panneau_.add(c);
                    }
//                    boolean entered_ = false;
//                    for(CardBelote c: cartesBeloteRebelote_)
//                    {
//                        GraphicBeloteCard carte_=new GraphicBeloteCard(c,SwingConstants.RIGHT,!entered_);
//                        carte_.setPreferredSize(entered_);
//                        panneau_.add(carte_);
//                        entered_ = true;
//                    }
                    ConfirmDialog.showComponent(container.getOwner(), panneau_, container.getMessages().getVal(MainWindow.HAVE_TO_PLAY), Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
                    //JOptionPane.showMessageDialog(container.getOwner(),panneau_,container.getMessages().getVal(MainWindow.HAVE_TO_PLAY),JOptionPane.INFORMATION_MESSAGE);
                    return;
                    //il ne faut pas afficher deux boites de dialgue
                }
            }
            if(autorise_){
                container.setaJoueCarte(true);
                container.finPliBelote(getCarteVerif());
            }else{
                String mes_ = StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.CANT_PLAY_CARD), getCarteVerif().toString());
                String finalMessage_ = mes_+ContainerBelote.RETURN_LINE_CHAR+partie_.getErreurDeJeu();
                String title_ = container.getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE);
                ConfirmDialog.showMessage(container.getOwner(), finalMessage_, title_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
                //JOptionPane.showMessageDialog(container.getOwner(),mes_+ContainerBelote.RETURN_LINE_CHAR+partie_.getErreurDeJeu(),container.getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),JOptionPane.ERROR_MESSAGE);
            }
        }else{
            String finalMessage_ = container.getMessages().getVal(MainWindow.CANT_PLAY)+container.getRaisonCourante();
            String title_ = container.getMessages().getVal(MainWindow.TOO_GAME);
            ConfirmDialog.showMessage(container.getOwner(), finalMessage_, title_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(container.getOwner(),container.getMessages().getVal(MainWindow.CANT_PLAY)+container.getRaisonCourante(),container.getMessages().getVal(MainWindow.TOO_GAME),JOptionPane.ERROR_MESSAGE);
        }
    }
}
