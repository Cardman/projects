package cards.gui.animations;

import cards.belote.BidBeloteSuit;
import cards.belote.DealBelote;
import cards.belote.GameBelote;
import cards.facade.Games;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleBelote;
import code.gui.FrameUtil;
import code.gui.ThreadInvoker;
import code.threads.ThreadUtil;
import code.util.StringList;
import code.util.core.StringUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationBidBelote implements Runnable {

    private final ContainerSingleBelote container;

    /**This class thread is independant from EDT*/
    public AnimationBidBelote(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void run() {
        String lg_ = container.getOwner().getLanguageKey();
        container.setThreadAnime(true);
        GameBelote partie_=container.partieBelote();
        if (partie_.playerHavingToBid() == DealBelote.NUMERO_UTILISATEUR) {
            BidBeloteSuit contrat_=container.getContratUtilisateurBelote();
            partie_.ajouterContrat(contrat_,DealBelote.NUMERO_UTILISATEUR);
            String event_ = StringUtil.concat(container.pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(contrat_,lg_),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(container.pseudo()+ContainerGame.INTRODUCTION_PTS+contrat_.toString()+ContainerBelote.RETURN_LINE_CHAR);
        }
        //Activer le menu Partie/Pause
//        container.getPause().setEnabled(true);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new ChangingPause(container, true), container.getOwner().getFrames());
        loopBid(container);
//        container.getPanneauBoutonsJeu().removeAll();
//        if(partie_.keepBidding()) {
//            container.setCanBid(true);
//            if (!partie_.getRegles().dealAll()) {
//                for(BidBeloteSuit e:partie_.allowedBids()){
//                    container.ajouterBoutonContratBelote(e.toString(),e,e.estDemandable(partie_.getContrat()));
//                }
//            } else {
//                container.addButtonsForCoinche(partie_);
//            }
//        } else if(partie_.getContrat().jouerDonne()) {
//            container.getMini().setStatus(Status.TAKER, partie_.getPreneur());
//            container.getMini().setStatus(Status.CALLED_PLAYER, partie_.partenaires(partie_.getPreneur()).first());
//            container.addButtonNextTrickBelote(container.getMessages().getVal(MainWindow.GO_CARD_GAME), true);
//        } else {
//            container.addButtonEndDealBelote(container.getMessages().getVal(MainWindow.END_DEAL), true);
//        }
//        container.setThreadAnime(false);
//        PackingWindowAfter.pack(container);
    }

    static void loopBid(ContainerSingleBelote _container) {
        long delaiContrat_= _container.getParametres().getDelaiAttenteContrats();
        GameBelote partie_= _container.partieBelote();
        String lg_ = _container.getOwner().getLanguageKey();
        StringList pseudos_ = _container.pseudosBelote();
        while (partie_.keepBidding()) {
            byte player_ = partie_.playerHavingToBid();
            if (player_ == DealBelote.NUMERO_UTILISATEUR) {
                break;
            }
            //Les Fenetre.ROBOTS precedant l'utilisateur annoncent leur contrat
            ThreadUtil.sleep(_container.getOwner().getThreadFactory(), delaiContrat_);
            BidBeloteSuit contrat_= partie_.strategieContrat();
            partie_.ajouterContrat(contrat_, player_);
//            container.ajouterTexteDansZone(pseudos_.get(player_)+ContainerGame.INTRODUCTION_PTS+contrat_+ContainerBelote.RETURN_LINE_CHAR);
            String event_ = StringUtil.concat(pseudos_.get(player_),ContainerGame.INTRODUCTION_PTS,Games.toString(contrat_, lg_),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(_container.getOwner().getThreadFactory(),new AddTextEvents(_container, event_), _container.getOwner().getFrames());
//            container.ajouterTexteDansZone(event_);
            if (partie_.tailleContrats() == partie_.getNombreDeJoueurs()) {
                partie_.finEncherePremierTour();
            }
//            if (_container.isPasse()) {
//                _container.setState(CardAnimState.BID_BELOTE);
//                return;
//            }
        }
        FrameUtil.invokeLater(new AfterAnimationBidBelote(_container), _container.getOwner().getFrames());
    }
}
