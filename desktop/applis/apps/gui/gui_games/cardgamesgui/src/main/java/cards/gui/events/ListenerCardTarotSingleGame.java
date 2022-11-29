package cards.gui.events;



import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.gui.containers.ContainerTarot;
import cards.gui.labels.MiniTarotCard;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.gui.AbsMouseLocation;
import code.gui.AbsPanel;

import code.gui.GuiConstants;
import code.util.IdList;
import code.util.core.StringUtil;

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
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
    }
    @Override
    protected void verifierRegles() {
        String lg_ = container.getOwner().getLanguageKey();
        if(StringUtil.quickEq(container.getRaisonCourante(),ContainerTarot.EMPTY)) {
            GameTarot partie_=container.partieTarot();
            if (container.getChoosenHandful() != Handfuls.NO) {
                String messErr_ = Games.isValidHandfulMessage(partie_, container.getChoosenHandful(), container.getCurrentIncludedTrumps(), container.getCurrentExcludedTrumps(), lg_);
                if (!partie_.isValidHandful(container.getChoosenHandful(), container.getCurrentIncludedTrumps(), container.getCurrentExcludedTrumps())) {
                    String mes_ = StringUtil.simpleStringsFormat(container.getMessages().getVal(WindowCards.CANT_DECLARE_DETAIL), Games.toString(container.getChoosenHandful(),lg_));
                    String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,messErr_);
                    String title_ = container.getMessages().getVal(WindowCards.CANT_DECLARE_TITLE);
                    container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(),finalMessage_,title_,lg_, GuiConstants.ERROR_MESSAGE);
                    return;
                }
                String pseudo_=container.pseudo();
                IdList<Handfuls> an_=new IdList<Handfuls>();
                an_.add(container.getChoosenHandful());
                partie_.setAnnoncesPoignees(DealTarot.NUMERO_UTILISATEUR,an_);
                container.getHandfuls().getVal(DealTarot.NUMERO_UTILISATEUR).setText(Games.toString(container.getChoosenHandful(),lg_));
                AbsPanel panelToSet_ = container.getDeclaredHandfuls().getVal(DealTarot.NUMERO_UTILISATEUR);
                panelToSet_.removeAll();
                for(CardTarot c: container.getCurrentIncludedTrumps()) {
                    MiniTarotCard carte_=new MiniTarotCard(lg_, c, container.getOwner().getCompoFactory());
                    panelToSet_.add(carte_.getPaintableLabel());
                }
                partie_.ajouterPoignee(container.getCurrentIncludedTrumps(),DealTarot.NUMERO_UTILISATEUR);
                container.ajouterTexteDansZone(StringUtil.concat(pseudo_,ContainerGame.INTRODUCTION_PTS,Games.toString(container.getChoosenHandful(),lg_),ContainerGame.RETURN_LINE));
            }
            IdList<Miseres> selectedMiseres_ = container.getAllowedMiseres();
            if (!selectedMiseres_.isEmpty()) {
                IdList<Miseres> miseres_ = partie_.getAnnoncesMiseresPossibles(DealTarot.NUMERO_UTILISATEUR);
                IdList<Miseres> allowedSelectedMiseres_ = new IdList<Miseres>();
                for (Miseres m: selectedMiseres_) {
                    if (!miseres_.containsObj(m)) {
                        continue;
                    }
                    container.ajouterTexteDansZone(StringUtil.concat(container.pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(m,lg_)));
                    allowedSelectedMiseres_.add(m);
                }
                partie_.setAnnoncesMiseres(DealTarot.NUMERO_UTILISATEUR,allowedSelectedMiseres_);
            }
            if(partie_.autorise(getCarteVerif())) {
                if (container.getScrollDeclaringHandful().isVisible()) {
                    container.getScrollDeclaringHandful().setVisible(false);
                    container.pack();
                }
                container.setaJoueCarte(true);
                container.finPliTarot(getCarteVerif());
            }else{
                String mes_ = StringUtil.simpleStringsFormat(container.getMessages().getVal(WindowCards.CANT_PLAY_CARD), Games.toString(getCarteVerif(),lg_));
                String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,Games.autoriseTarot(partie_, lg_));
                String title_ = container.getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE);
                container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(),finalMessage_,title_,lg_,GuiConstants.ERROR_MESSAGE);
            }
        }else{
            String finalMessage_ = StringUtil.concat(container.getMessages().getVal(WindowCards.CANT_PLAY),container.getRaisonCourante());
            String title_ = container.getMessages().getVal(WindowCards.TOO_GAME);
            container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(),finalMessage_,title_, lg_,GuiConstants.ERROR_MESSAGE);
        }
    }
}
