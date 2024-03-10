package cards.gui.animations;
import cards.belote.DealBelote;
import cards.belote.GameBelote;
import cards.consts.Role;
import cards.gui.containers.ContainerSingleBelote;
import code.gui.MenuItemUtils;
import code.scripts.messages.cards.MessagesGuiCards;
import code.util.Bytes;

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
        buttons(container);
//        //Desactiver le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(container.getPause(),false);
//        GameBelote gameBelote_=container.partieBelote();
//        container.getPanneauBoutonsJeu().removeAll();
//        container.getBids().clear();
//        if(gameBelote_.keepBidding()) {
//            //Activer les conseils
//            MenuItemUtils.setEnabledMenu(container.getConsulting(),true);
//            container.bidButtons();
//        } else if(gameBelote_.getBid().jouerDonne()) {
//            container.getMini().setStatus(container.getWindow().getImageFactory(), Role.TAKER, gameBelote_.getPreneur());
//            container.getMini().setStatus(container.getWindow().getImageFactory(), Role.CALLED_PLAYER, gameBelote_.getTeamsRelation().partenaires(gameBelote_.getPreneur()).first());
//            container.addButtonNextTrickBelote(container.file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME), true);
//        } else {
//            container.addButtonEndDealBelote(container.file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
//        }
//        container.setThreadAnime(false);
//        container.pack();
    }
    public static void buttons(ContainerSingleBelote _container) {
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(_container.getPause(),false);
        MenuItemUtils.setEnabledMenu(_container.getConsulting(),false);
        GameBelote gameBelote_=_container.partieBelote();
        _container.clearBids();
        if(gameBelote_.keepBidding()) {
            GameBelote partie_=_container.partieBelote();
            byte debut_= partie_.playerHavingToBid();
            if(debut_ != DealBelote.NUMERO_UTILISATEUR) {
                _container.pack();
                _container.thread(new AnimationBidBelote(_container));
            } else {
                //Activer les conseils
                MenuItemUtils.setEnabledMenu(_container.getConsulting(),true);
                _container.bidButtons();
//                _container.setThreadAnime(false);
                _container.window().changeStreamsMenusEnabled(true);
                _container.pack();
            }
        } else if(gameBelote_.getBid().jouerDonne()) {
            if (gameBelote_.getRegles().getDealing().getDiscarded() > 0) {
                _container.addButtonSeeDiscardBelote(_container.file().getVal(MessagesGuiCards.MAIN_SEE_DOG),true);
                _container.window().changeStreamsMenusEnabled(true);
                _container.pack();
                return;
            }
            MenuItemUtils.setEnabledMenu(_container.getOwner().getTeams(),true);
            _container.getMini().setStatus(_container.getWindow().getImageFactory(), Role.TAKER, gameBelote_.getPreneur());
            Bytes partenaires_ = gameBelote_.getTeamsRelation().partenaires(gameBelote_.getPreneur());
            if (!partenaires_.isEmpty()) {
                _container.getMini().setStatus(_container.getWindow().getImageFactory(), Role.CALLED_PLAYER, partenaires_.first());
            }
            _container.addMainCardGameBelote(true);
//            _container.setThreadAnime(false);
            _container.window().changeStreamsMenusEnabled(true);
            _container.pack();
        } else {
            MenuItemUtils.setEnabledMenu(_container.getOwner().getTeams(),true);
            _container.addButtonEndDealBelote(_container.file().getVal(MessagesGuiCards.MAIN_END_DEAL), true);
//            _container.setThreadAnime(false);
            _container.window().changeStreamsMenusEnabled(true);
            _container.pack();
        }

    }
}
