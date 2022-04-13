package cards.gui.animations;

import cards.facade.Games;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.BidTarot;
import code.gui.FrameUtil;
import code.gui.ThreadInvoker;
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
        container.setThreadAnime(true);
        GameTarot partie_=container.partieTarot();
        String lg_ = container.getOwner().getLanguageKey();
        if (partie_.playerHavingToBid() == DealTarot.NUMERO_UTILISATEUR) {
            BidTarot contrat_=container.getContratUtilisateur();
            partie_.ajouterContrat(contrat_,DealTarot.NUMERO_UTILISATEUR);
            String event_ = StringUtil.concat(pseudos_.get(DealTarot.NUMERO_UTILISATEUR),ContainerGame.INTRODUCTION_PTS,Games.toString(contrat_,lg_),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(pseudos_.get(DealTarot.NUMERO_UTILISATEUR)+ContainerGame.INTRODUCTION_PTS+contrat_+ContainerTarot.RETURN_LINE_CHAR);
        }
        //Activer le menu Partie/Pause
//        container.getPause().setEnabled(true);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new ChangingPause(container, true), container.getOwner().getFrames());
        loopBid(container);
    }

    static void loopBid(ContainerSingleTarot _container) {
        StringList pseudos_ = _container.pseudosTarot();
        long delaiContrat_= _container.getParametres().getDelaiAttenteContrats();
        GameTarot partie_= _container.partieTarot();
        String lg_ = _container.getOwner().getLanguageKey();
        while (partie_.keepBidding()) {
            byte player_ = partie_.playerHavingToBid();
            if (player_ == DealTarot.NUMERO_UTILISATEUR) {
                break;
            }
            //Les Fenetre.ROBOTS precedant l'utilisateur annoncent leur contrat
            ThreadUtil.sleep(_container.getOwner().getThreadFactory(),delaiContrat_);
            BidTarot contrat_=partie_.strategieContrat();
            partie_.ajouterContrat(contrat_,player_);
            String event_ = StringUtil.concat(pseudos_.get(player_),ContainerGame.INTRODUCTION_PTS,Games.toString(contrat_,lg_),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(_container.getOwner().getThreadFactory(),new AddTextEvents(_container, event_), _container.getOwner().getFrames());
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(pseudos_.get(player_)+ContainerGame.INTRODUCTION_PTS+contrat_+ContainerTarot.RETURN_LINE_CHAR);
            if (_container.isPasse()) {
                _container.setState(CardAnimState.BID_TAROT);
                return;
            }
        }
        FrameUtil.invokeLater(new AfterAnimationBidTarot(_container), _container.getOwner().getFrames());
    }
}
