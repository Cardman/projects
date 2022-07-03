package cards.gui.events;



import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleBelote;
import cards.gui.labels.GraphicBeloteCard;
import code.gui.AbsMouseLocation;
import code.gui.AbsPanel;
import code.gui.ConfirmDialog;

import code.gui.GuiConstants;
import code.util.core.StringUtil;

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
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
    }
    @Override
    protected void verifierRegles(){
        String lg_ = container.getOwner().getLanguageKey();
        if(StringUtil.quickEq(container.getRaisonCourante(),ContainerBelote.EMPTY)){
            GameBelote partie_=container.partieBelote();
            boolean autorise_ = partie_.autorise(getCarteVerif());
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
                    AbsPanel panneau_= container.getOwner().getCompoFactory().newLineBox();
                    for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(container.getWindow(),lg_,cartesBeloteRebelote_.getCards())) {
                        panneau_.add(c);
                    }
                    container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(), panneau_, container.getMessages().getVal(WindowCards.HAVE_TO_PLAY), lg_, GuiConstants.ERROR_MESSAGE);
                    return;
                    //il ne faut pas afficher deux boites de dialgue
                }
            }
            if(autorise_){
                container.setaJoueCarte(true);
                container.finPliBelote(getCarteVerif());
            }else{
                String mes_ = StringUtil.simpleStringsFormat(container.getMessages().getVal(WindowCards.CANT_PLAY_CARD), Games.toString(getCarteVerif(),lg_));
                String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,Games.autoriseBelote(partie_,lg_));
                String title_ = container.getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE);
                container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(), finalMessage_, title_, lg_, GuiConstants.ERROR_MESSAGE);
            }
        }else{
            String finalMessage_ = StringUtil.concat(container.getMessages().getVal(WindowCards.CANT_PLAY),container.getRaisonCourante());
            String title_ = container.getMessages().getVal(WindowCards.TOO_GAME);
            container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(), finalMessage_, title_, lg_, GuiConstants.ERROR_MESSAGE);
        }
    }
}
