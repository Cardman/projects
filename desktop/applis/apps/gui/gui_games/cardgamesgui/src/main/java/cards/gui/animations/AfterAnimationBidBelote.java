package cards.gui.animations;
import cards.belote.BidBeloteSuit;
import cards.belote.GameBelote;
import cards.consts.Status;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerSingleBelote;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterAnimationBidBelote implements Runnable {

    private ContainerSingleBelote container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterAnimationBidBelote(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void run() {
        //Desactiver le menu Partie/Pause
        container.getPause().setEnabledMenu(false);
        GameBelote gameBelote_=container.partieBelote();
        container.getPanneauBoutonsJeu().removeAll();
        String lg_ = container.getOwner().getLanguageKey();
        if(gameBelote_.keepBidding()) {
            //Activer les conseils
            container.getConsulting().setEnabledMenu(true);
            container.setCanBid(true);
            if (!gameBelote_.getRegles().dealAll()) {
                for(BidBeloteSuit e:gameBelote_.getGameBeloteBid().allowedBids()){
                    container.ajouterBoutonContratBelote(Games.toString(e,lg_),e,e.estDemandable(gameBelote_.getContrat()));
                }
            } else {
                container.addButtonsForCoinche(gameBelote_);
            }
        } else if(gameBelote_.getContrat().jouerDonne()) {
            container.getMini().setStatus(Status.TAKER, gameBelote_.getPreneur());
            container.getMini().setStatus(Status.CALLED_PLAYER, gameBelote_.getTeamsRelation().partenaires(gameBelote_.getPreneur()).first());
            container.addButtonNextTrickBelote(container.getMessages().getVal(MainWindow.GO_CARD_GAME), true);
        } else {
            container.addButtonEndDealBelote(container.getMessages().getVal(MainWindow.END_DEAL), true);
        }
        container.setThreadAnime(false);
        container.pack();
    }
}
