package cards.gui.animations;
import cards.consts.Role;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.PlayingDog;
import code.gui.MenuItemUtils;
import code.scripts.messages.cards.MessagesGuiCards;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterAnimationBidTarot implements Runnable {

    private final ContainerSingleTarot container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterAnimationBidTarot(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void run() {
        GameTarot gameTarot_=container.partieTarot();
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(container.getPause(),false);
        container.getPanneauBoutonsJeu().removeAll();
        container.getBids().clear();
//        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if(gameTarot_.keepBidding()) {
            //Activer les conseils
            MenuItemUtils.setEnabledMenu(container.getConsulting(),true);
//            container.setCanBid(true);
//            for(BidTarot b:gameTarot_.allowedBids()) {
//                container.ajouterBoutonContratTarot(Games.toString(b,lg_),b,b.estDemandable(gameTarot_.getContrat()));
//            }
            container.bidButtons();
        } else {
            if(gameTarot_.getContrat().isJouerDonne()) {
                container.getMini().setStatus(container.getWindow().getImageFactory(), Role.TAKER, gameTarot_.getPreneur());
                CallingCard appel_= gameTarot_.getRegles().getDealing().getAppel();
                if(appel_==CallingCard.DEFINED||appel_==CallingCard.WITHOUT) {
                    if(appel_==CallingCard.DEFINED) {
                        gameTarot_.initEquipeDeterminee();
                        for (byte c: gameTarot_.getAppele()) {
                            container.getMini().setStatus(container.getWindow().getImageFactory(), Role.CALLED_PLAYER, c);
                        }
//                    } else {
//                        gameTarot_.initDefense();
                    }
                    casSansAppel();
                } else if(!gameTarot_.callableCards().estVide()) {
                    if (gameTarot_.getRegles().getDiscardAfterCall()) {
                        casAvecAppel();
                    } else {
                        callAfterDiscard();
                    }
                } else {
                    casSansAppel();
                }
            } else {
                if(gameTarot_.pasJeuApresPasse()) {
                    container.addButtonEndDealTarot(container.file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
                } else {
                    gameTarot_.initPlayWithoutBid();
                    container.addButtonNextTrickTarot(container.file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
                }
            }
        }

        container.setThreadAnime(false);
        container.pack();
    }

    private void casSansAppel() {
        GameTarot partie_=container.partieTarot();
        if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            container.addButtonSeeDogTarot(container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
        } else {
            partie_.gererChienInconnu();
            if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR&&partie_.getContrat()!=BidTarot.SLAM) {
//                container.ajouterBoutonJeuChelemTarot(BidTarot.SLAM.toString(),true);
                container.getSlamButton().setEnabled(true);
                container.getSlamButton().setVisible(true);
                container.getPanneauBoutonsJeu().add(container.getSlamButton());
            } else {
                if (partie_.getPreneur()!=DealTarot.NUMERO_UTILISATEUR) {
                    partie_.slam(container.getOwner().baseWindow().getIa().getTarot());
                }
            }
            container.addButtonNextTrickTarot(container.file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
        }
    }
    private void casAvecAppel() {
        GameTarot partie_=container.partieTarot();
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            container.placerBoutonsAppel();
        } else {
            partie_.intelligenceArtificielleAppel(container.getOwner().baseWindow().getIa().getTarot());
            container.called();
            if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                container.addButtonSeeDogTarot(container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
            } else {
                partie_.gererChienInconnu();
                partie_.slam(container.getOwner().baseWindow().getIa().getTarot());
                container.addButtonNextTrickTarot(container.file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
            }
        }
    }
    private void callAfterDiscard() {
        GameTarot partie_=container.partieTarot();
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                container.addButtonSeeDogTarot(container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
            } else {
                container.placerBoutonsAppelApres();
            }
        } else {
            if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                container.addButtonSeeDogTarot(container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
            } else {
                partie_.gererChienInconnu();
                partie_.intelligenceArtificielleAppel(container.getOwner().baseWindow().getIa().getTarot());
                container.called();
                container.addButtonNextTrickTarot(container.file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
            }
        }
    }
}
