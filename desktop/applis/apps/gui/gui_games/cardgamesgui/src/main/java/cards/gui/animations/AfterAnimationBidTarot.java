package cards.gui.animations;
import cards.consts.Role;
import cards.facade.Games;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.PlayingDog;
import code.gui.MenuItemUtils;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.StringList;
import code.util.core.StringUtil;

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
        StringList pseudosTarot_ = container.pseudosTarot();
        GameTarot gameTarot_=container.partieTarot();
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(container.getPause(),false);
        container.getPanneauBoutonsJeu().removeAll();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if(gameTarot_.keepBidding()) {
            //Activer les conseils
            MenuItemUtils.setEnabledMenu(container.getConsulting(),true);
            container.setCanBid(true);
            for(BidTarot b:gameTarot_.allowedBids()) {
                container.ajouterBoutonContratTarot(Games.toString(b,lg_),b,b.estDemandable(gameTarot_.getContrat()));
            }
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
                    } else {
                        gameTarot_.initDefense();
                    }
                    casSansAppel();
                } else if(!gameTarot_.callableCards().estVide()) {
                    if (gameTarot_.getRegles().getDiscardAfterCall()) {
                        casAvecAppel(pseudosTarot_.get(gameTarot_.getPreneur()));
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
    private void casAvecAppel(String _pseudo) {
        GameTarot partie_=container.partieTarot();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            container.placerBoutonsAppel();
        } else {
            partie_.intelligenceArtificielleAppel(container.getOwner().baseWindow().getIa().getTarot());
            if(!partie_.getCarteAppelee().estVide()) {
                container.ajouterTexteDansZone(StringUtil.concat(_pseudo,ContainerGame.INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),ContainerGame.RETURN_LINE));
            }
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
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                container.addButtonSeeDogTarot(container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
            } else {
                container.placerBoutonsAppel();
            }
        } else {
            if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                container.addButtonSeeDogTarot(container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
            } else {
                partie_.gererChienInconnu();
                partie_.intelligenceArtificielleAppel(container.getOwner().baseWindow().getIa().getTarot());
                if(!partie_.getCarteAppelee().estVide()) {
                    container.ajouterTexteDansZone(StringUtil.concat(container.pseudosTarot().get(partie_.getPreneur()),
                            ContainerGame.INTRODUCTION_PTS,Games.toString(partie_.getCarteAppelee(),lg_),ContainerGame.RETURN_LINE));
                }
                container.addButtonNextTrickTarot(container.file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
            }
        }
    }
}
