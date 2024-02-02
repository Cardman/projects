package cards.gui.animations;
import cards.belote.GameBelote;
import cards.consts.Role;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerSingleBelote;
import code.gui.MenuItemUtils;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterAnimationBidBelote implements Runnable {

    private final ContainerSingleBelote container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterAnimationBidBelote(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void run() {
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(container.getPause(),false);
        GameBelote gameBelote_=container.partieBelote();
        container.getPanneauBoutonsJeu().removeAll();
        container.getBids().clear();
        if(gameBelote_.keepBidding()) {
            //Activer les conseils
            MenuItemUtils.setEnabledMenu(container.getConsulting(),true);
            container.bidButtons();
        } else if(gameBelote_.getBid().jouerDonne()) {
            container.getMini().setStatus(container.getWindow().getImageFactory(), Role.TAKER, gameBelote_.getPreneur());
            container.getMini().setStatus(container.getWindow().getImageFactory(), Role.CALLED_PLAYER, gameBelote_.getTeamsRelation().partenaires(gameBelote_.getPreneur()).first());
            container.addButtonNextTrickBelote(container.getMessages().getVal(WindowCards.GO_CARD_GAME), true);
        } else {
            container.addButtonEndDealBelote(container.getMessages().getVal(WindowCards.END_DEAL), true);
        }
        container.setThreadAnime(false);
        container.pack();
    }
}
