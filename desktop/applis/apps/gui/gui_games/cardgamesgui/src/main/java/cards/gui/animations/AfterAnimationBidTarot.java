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
import code.util.core.BoolVal;

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
            container.window().changeStreamsMenusEnabled(true);
            container.pack();
            return;
        }
        afterBid(container, BoolVal.FALSE);
    }

    public static void afterBid(ContainerSingleTarot _container, BoolVal _loading) {
        GameTarot gameTarot_=_container.partieTarot();
        if (!gameTarot_.getContrat().isJouerDonne()) {
            if(gameTarot_.pasJeuApresPasse()) {
                _container.addButtonEndDealTarot(_container.file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
            } else {
                gameTarot_.initPlayWithoutBid();
                _container.addMainCardGameTarot(true);
            }
            _container.window().changeStreamsMenusEnabled(true);
            _container.pack();
            return;
        }
        _container.getMini().setStatus(_container.getWindow().getImageFactory(), Role.TAKER, gameTarot_.getPreneur());
        CallingCard appel_= gameTarot_.getRegles().getDealing().getAppel();
        if(!gameTarot_.callableCards().estVide()) {
            if (gameTarot_.getRegles().getDiscardAfterCall()) {
                discardAfterCall(_container);
            } else {
                callAfterDiscard(_container);
            }
        } else {
//                } else if(appel_==CallingCard.DEFINED||appel_==CallingCard.WITHOUT) {
            if(appel_==CallingCard.DEFINED) {
                gameTarot_.initEquipeDeterminee();
                for (byte c: gameTarot_.getAppele()) {
                    _container.getMini().setStatus(_container.getWindow().getImageFactory(), Role.CALLED_PLAYER, c);
                }
//                    } else {
//                        gameTarot_.initDefense();
            }
            casSansAppel(_container, _loading);
//                } else {
//                    casSansAppel();
        }

//        container.setThreadAnime(false);
        _container.window().changeStreamsMenusEnabled(true);
        _container.pack();
    }

    private static void casSansAppel(ContainerSingleTarot _container, BoolVal _loading) {
        GameTarot partie_= _container.partieTarot();
        if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            _container.addButtonSeeDogTarot(_container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
        } else {
//            partie_.gererChienInconnu();
            if(partie_.getContrat() != BidTarot.SLAM) {
                if (partie_.getPreneur()!=DealTarot.NUMERO_UTILISATEUR) {
                    partie_.slam(_container.getOwner().baseWindow().getIa().getTarot());
                    _container.addMainCardGameTarot(true);
                } else {
                    _container.getSlamButton().setEnabled(true);
                    _container.getPanneauBoutonsJeu().add(_container.getSlamButton());
                    _container.addMainCardGameTarot(true);
                }
            } else {
                if (_loading == BoolVal.TRUE) {
                    _container.firstTrick();
                } else {
                    _container.addMainCardGameTarot(true);
                }
            }
        }
    }
    private static void discardAfterCall(ContainerSingleTarot _container) {
        GameTarot partie_= _container.partieTarot();
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            if (partie_.isCallingState()) {
                _container.placerBoutonsAppel();
            } else {
                postCall(_container);
            }
        } else {
            discardAfterCallIa(_container);
        }
    }

    private static void discardAfterCallIa(ContainerSingleTarot _container) {
        GameTarot partie_= _container.partieTarot();
        intelligenceArtificielleAppel(_container);
        if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            _container.addButtonSeeDogTarot(_container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
        } else {
            _container.addMainCardGameTarot(true);
        }
    }

    private static void callAfterDiscard(ContainerSingleTarot _container) {
        GameTarot partie_= _container.partieTarot();
        if(partie_.getPreneur()==DealTarot.NUMERO_UTILISATEUR) {
            if (partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                _container.addButtonSeeDogTarot(_container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
            } else {
                if (partie_.isCallingState()) {
                    _container.placerBoutonsAppelApres();
                } else {
                    postCall(_container);
                }
            }
        } else {
            callAfterDiscardIa(_container);
        }
    }

    private static void postCall(ContainerSingleTarot _container) {
        GameTarot partie_= _container.partieTarot();
        if(partie_.getContrat() != BidTarot.SLAM) {
            _container.getSlamButton().setEnabled(true);
            _container.getPanneauBoutonsJeu().add(_container.getSlamButton());
            _container.addMainCardGameTarot(true);
        } else {
            _container.firstTrick();
        }
    }

    private static void callAfterDiscardIa(ContainerSingleTarot _container) {
        GameTarot partie_= _container.partieTarot();
        if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            _container.addButtonSeeDogTarot(_container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG), true);
        } else {
            intelligenceArtificielleAppel(_container);
            _container.addMainCardGameTarot(true);
        }
    }

    private static void intelligenceArtificielleAppel(ContainerSingleTarot _container) {
        GameTarot partie_= _container.partieTarot();
//        if(partie_.getContrat().getJeuChien() != PlayingDog.WITH) {
//            partie_.gererChienInconnu();
//        }
        if (partie_.isCallingState()) {
            partie_.intelligenceArtificielleAppel(_container.getOwner().baseWindow().getIa().getTarot());
            if(partie_.getContrat().getJeuChien() != PlayingDog.WITH) {
                partie_.slam(_container.getOwner().baseWindow().getIa().getTarot());
            }
        }
//        partie_.firstLead();
        _container.called();
    }

}
