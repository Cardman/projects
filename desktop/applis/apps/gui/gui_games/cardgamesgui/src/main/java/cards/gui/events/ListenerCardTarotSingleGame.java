package cards.gui.events;


import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.IdList;

public class ListenerCardTarotSingleGame extends AbstractListenerCardTarot {

    private final ContainerSingleTarot container;
    public ListenerCardTarotSingleGame(ContainerSingleTarot _container,CardTarot _pcarte) {
        super(_container,_pcarte);
        container = _container;
    }
//    @Override
//    protected boolean playCardExited(AbsMouseLocation _event) {
//        return _event.getYcoord() < 0;
//    }
    @Override
    protected void verifierRegles() {
//        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        GameTarot partie_=container.partieTarot();
        Handfuls ch_ = container.getChoosenHandful();
        if (ch_ != Handfuls.NO) {
            HandTarot handful_ = container.getOwner().baseWindow().getIa().getTarot().handfulCard(container.getCurrentIncludedTrumps());
//            if (!partie_.isValidHandful(ch_, handful_, container.getCurrentExcludedTrumps())) {
//                String messErr_ = Games.isValidHandfulMessage(partie_, ch_, handful_, container.getCurrentExcludedTrumps(), lg_);
//                String mes_ = StringUtil.simpleStringsFormat(container.file().getVal(MessagesGuiCards.MAIN_CANT_DECLARE_DETAIL), Games.toString(ch_,lg_));
//                String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,messErr_);
//                container.ajouterTexteDansZone(finalMessage_);
//                return;
//            }
//            String pseudo_=container.pseudo();
            IdList<Handfuls> an_=new IdList<Handfuls>();
            an_.add(ch_);
            partie_.setAnnoncesPoignees(DealTarot.NUMERO_UTILISATEUR,container.getOwner().baseWindow().getIa().getTarot().handful(an_));
//            container.getHandfuls().getVal(DealTarot.NUMERO_UTILISATEUR).setText(Games.toString(ch_,lg_));
//            AbsPanel panelToSet_ = container.getDeclaredHandfuls().getVal(DealTarot.NUMERO_UTILISATEUR);
//            panelToSet_.removeAll();
//            for(CardTarot c: handful_) {
//                MiniCard carte_=new MiniCard(lg_, container.getOwner(), c.getId().nb());
//                panelToSet_.add(carte_.getPaintableLabel());
//                AbsMetaLabelCard.paintCard(container.getWindow().getImageFactory(),carte_);
//            }
            partie_.ajouterPoignee(handful_,DealTarot.NUMERO_UTILISATEUR);
//            container.ajouterTexteDansZone(StringUtil.concat(pseudo_,ContainerGame.INTRODUCTION_PTS,Games.toString(ch_,lg_),ContainerGame.RETURN_LINE));
        }
        IdList<Miseres> selectedMiseres_ = container.getAllowedMiseres();
        if (!selectedMiseres_.isEmpty()) {
            IdList<Miseres> miseres_ = partie_.getAnnoncesMiseresPossibles(DealTarot.NUMERO_UTILISATEUR);
            IdList<Miseres> allowedSelectedMiseres_ = new IdList<Miseres>();
            for (Miseres m: selectedMiseres_) {
                if (!miseres_.containsObj(m)) {
                    continue;
                }
//                container.ajouterTexteDansZone(StringUtil.concat(container.pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(m,lg_)));
                allowedSelectedMiseres_.add(m);
            }
            partie_.setAnnoncesMiseres(DealTarot.NUMERO_UTILISATEUR,container.getOwner().baseWindow().getIa().getTarot().misere(allowedSelectedMiseres_));
        }
        if (container.getScrollDeclaringHandful().isVisible()) {
            container.getScrollDeclaringHandful().setVisible(false);
            container.pack();
        }
//                container.setaJoueCarte(true);
        container.finPliTarot(getCarteVerif());
//        if(partie_.autorise(getCarteVerif())) {
//            if (container.getScrollDeclaringHandful().isVisible()) {
//                container.getScrollDeclaringHandful().setVisible(false);
//                container.pack();
//            }
////                container.setaJoueCarte(true);
//            container.finPliTarot(getCarteVerif());
//        }else{
//            String mes_ = StringUtil.simpleStringsFormat(container.file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(getCarteVerif(),lg_));
//            String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,Games.autoriseTarot(partie_, lg_));
//            String title_ = container.getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE);
//            container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(),finalMessage_,title_, GuiConstants.ERROR_MESSAGE);
//        }
//        if(StringUtil.quickEq(container.getRaisonCourante(),ContainerTarot.EMPTY)) {
//            GameTarot partie_=container.partieTarot();
//            Handfuls ch_ = container.getChoosenHandful();
//            if (ch_ != Handfuls.NO) {
//                HandTarot handful_ = container.getOwner().baseWindow().getIa().getTarot().handfulCard(container.getCurrentIncludedTrumps());
//                String messErr_ = Games.isValidHandfulMessage(partie_, ch_, handful_, container.getCurrentExcludedTrumps(), lg_);
//                if (!partie_.isValidHandful(ch_, handful_, container.getCurrentExcludedTrumps())) {
//                    String mes_ = StringUtil.simpleStringsFormat(container.file().getVal(MessagesGuiCards.MAIN_CANT_DECLARE_DETAIL), Games.toString(ch_,lg_));
//                    String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,messErr_);
//                    String title_ = container.getMessages().getVal(WindowCards.CANT_DECLARE_TITLE);
//                    container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(),finalMessage_,title_, GuiConstants.ERROR_MESSAGE);
//                    return;
//                }
//                String pseudo_=container.pseudo();
//                IdList<Handfuls> an_=new IdList<Handfuls>();
//                an_.add(ch_);
//                partie_.setAnnoncesPoignees(DealTarot.NUMERO_UTILISATEUR,container.getOwner().baseWindow().getIa().getTarot().handful(an_));
//                container.getHandfuls().getVal(DealTarot.NUMERO_UTILISATEUR).setText(Games.toString(ch_,lg_));
//                AbsPanel panelToSet_ = container.getDeclaredHandfuls().getVal(DealTarot.NUMERO_UTILISATEUR);
//                panelToSet_.removeAll();
//                for(CardTarot c: handful_) {
//                    MiniCard carte_=new MiniCard(lg_, container.getOwner(), c.getId().nb());
//                    panelToSet_.add(carte_.getPaintableLabel());
//                    AbsMetaLabelCard.paintCard(container.getWindow().getImageFactory(),carte_);
//                }
//                partie_.ajouterPoignee(handful_,DealTarot.NUMERO_UTILISATEUR);
//                container.ajouterTexteDansZone(StringUtil.concat(pseudo_,ContainerGame.INTRODUCTION_PTS,Games.toString(ch_,lg_),ContainerGame.RETURN_LINE));
//            }
//            IdList<Miseres> selectedMiseres_ = container.getAllowedMiseres();
//            if (!selectedMiseres_.isEmpty()) {
//                IdList<Miseres> miseres_ = partie_.getAnnoncesMiseresPossibles(DealTarot.NUMERO_UTILISATEUR);
//                IdList<Miseres> allowedSelectedMiseres_ = new IdList<Miseres>();
//                for (Miseres m: selectedMiseres_) {
//                    if (!miseres_.containsObj(m)) {
//                        continue;
//                    }
//                    container.ajouterTexteDansZone(StringUtil.concat(container.pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(m,lg_)));
//                    allowedSelectedMiseres_.add(m);
//                }
//                partie_.setAnnoncesMiseres(DealTarot.NUMERO_UTILISATEUR,container.getOwner().baseWindow().getIa().getTarot().misere(allowedSelectedMiseres_));
//            }
//            if(partie_.autorise(getCarteVerif())) {
//                if (container.getScrollDeclaringHandful().isVisible()) {
//                    container.getScrollDeclaringHandful().setVisible(false);
//                    container.pack();
//                }
////                container.setaJoueCarte(true);
//                container.finPliTarot(getCarteVerif());
//            }else{
//                String mes_ = StringUtil.simpleStringsFormat(container.file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(getCarteVerif(),lg_));
//                String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,Games.autoriseTarot(partie_, lg_));
//                String title_ = container.getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE);
//                container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(),finalMessage_,title_, GuiConstants.ERROR_MESSAGE);
//            }
//        }else{
//            String finalMessage_ = StringUtil.concat(container.file().getVal(MessagesGuiCards.MAIN_CANT_PLAY),container.getRaisonCourante());
//            String title_ = container.getMessages().getVal(WindowCards.TOO_GAME);
//            container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(),finalMessage_,title_, GuiConstants.ERROR_MESSAGE);
//        }
    }
}
