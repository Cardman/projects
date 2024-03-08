package cards.gui.animations;

import cards.facade.Games;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleImpl;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.BidTarot;
import code.gui.MenuItemUtils;
import code.sml.util.TranslationsLg;
import code.threads.ThreadUtil;
import code.util.StringList;
import code.util.core.StringUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationBidTarot implements Runnable {

    private final ContainerSingleTarot container;

    /**This class thread is independant from EDT*/
    public AnimationBidTarot(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void run() {
        StringList pseudos_ = container.pseudosTarot();
//        container.setThreadAnime(true);
        MenuItemUtils.setEnabledMenu(container.getConsulting(),false);
        GameTarot partie_=container.partieTarot();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if (partie_.playerHavingToBid() == DealTarot.NUMERO_UTILISATEUR) {
            BidTarot contrat_=container.getOwner().baseWindow().getIa().getTarot().strategieContratUser(container.getContratUtilisateur());
            partie_.ajouterContrat(contrat_);
            String event_ = StringUtil.concat(pseudos_.get(DealTarot.NUMERO_UTILISATEUR),ContainerGame.INTRODUCTION_PTS,Games.toString(contrat_,lg_),ContainerGame.RETURN_LINE);
            container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(pseudos_.get(DealTarot.NUMERO_UTILISATEUR)+ContainerGame.INTRODUCTION_PTS+contrat_+ContainerTarot.RETURN_LINE_CHAR);
        }
        //Activer le menu Partie/Pause
//        container.getPause().setEnabled(true);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new ChangingPause(container));
        loopBid(container);
    }

    static void loopBid(ContainerSingleTarot _container) {
        StringList pseudos_ = _container.pseudosTarot();
        long delaiContrat_= _container.getParametres().getDelaiAttenteContrats();
        GameTarot partie_= _container.partieTarot();
        TranslationsLg lg_ = _container.getOwner().getFrames().currentLg();
        while (partie_.keepBidding()) {
            if (_container.window().getPausingCardsAnims().state(_container) == ContainerSingleImpl.PAUSE_STOPPED) {
                _container.getOwner().getFrames().getCompoFactory().invokeNow(new ChangingPauseAfter(_container,CardAnimState.BID_TAROT));
                return;
            }
            byte player_ = partie_.playerHavingToBid();
            if (player_ == DealTarot.NUMERO_UTILISATEUR) {
                break;
            }
            //Les Fenetre.ROBOTS precedant l'utilisateur annoncent leur contrat
            ThreadUtil.sleep(_container.getOwner().getThreadFactory(),delaiContrat_);
            BidTarot contrat_=_container.getOwner().baseWindow().getIa().getTarot().strategieContrat(partie_);
            partie_.ajouterContrat(contrat_);
            String event_ = StringUtil.concat(pseudos_.get(player_),ContainerGame.INTRODUCTION_PTS,Games.toString(contrat_,lg_),ContainerGame.RETURN_LINE);
            _container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(_container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(pseudos_.get(player_)+ContainerGame.INTRODUCTION_PTS+contrat_+ContainerTarot.RETURN_LINE_CHAR);
//            if (_container.isPasse()) {
//                _container.setState(CardAnimState.BID_TAROT);
//                return;
//            }
        }
        _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationBidTarot(_container));
    }
}
