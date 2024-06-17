package cards.network.threads;

import code.gui.initialize.*;
import code.network.*;
import code.threads.*;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SendReceiveServerCards extends BasicServer {

//    private static final String EMPTY_STRING = "";

    private final AbstractBaseExecutorService lock;

    private final Net instance;
    /**This class thread is independant from EDT*/
    public SendReceiveServerCards(AbstractSocket _socket, NetGroupFrame _net, Net _instance) {
        super(_socket, _net);
        lock = _net.getLock();
        instance = _instance;
    }

    @Override
    public void loopServer(String _input) {
        lock.execute(new ServerIterationCards(_input, instance,getCurrentThreadFactory(),getSockets()));
    }

//    private static void processShowDogTarot(PlayerActionGame _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
//        Net.setReceivedForPlayer(_readObject.getPlace(), _instance);
//        if (Net.allReceived(_instance)) {
//            Net.initAllReceived(_instance, _common);
////            GameTarot game_ = Net.getGames(_instance).partieTarot();
////            ContainerSingleTarot.ecart(game_, _instance.getIa());
////            if (game_.getRegles().getDiscardAfterCall()) {
////                game_.ecarter(_instance.getIa().getTarot());
////            } else {
////                game_.appelApresEcart(_instance.getIa().getTarot());
////            }
//            ThreadUtil.sleep(_fct,5000);
////            if (!game_.getPliEnCours().getCartes().couleur(Suit.TRUMP).estVide()) {
////                DiscardedTrumps discarded_ = new DiscardedTrumps();
////                discarded_.setTrumps(game_.getPliEnCours().getCartes().couleur(Suit.TRUMP));
////                Net.initAllReceived(_instance, _common);
////                for (byte p: Net.activePlayers(_instance, _common)) {
////                    Net.sendObject(Net.getSocketByPlace(p, _common), discarded_);
////                }
////                return;
////            }
////            if (game_.chelemAnnonce()) {
////                PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
////                bid_.setPlace(game_.getPreneur());
////                //bid_.setLocale(Constants.getDefaultLanguage());
////                bid_.setLocale("");
////                for (byte p: Net.activePlayers(_instance, _common)) {
////                    Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
////                }
////                return;
////            }
////            byte dealer_=game_.getDistribution().getDealer();
////            /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
////            game_.setEntameur(game_.playerAfter(dealer_));
////            game_.setPliEnCours(true);
////            game_.firstLead();
//            playingTarotCard(_instance,_fct, _common);
//            return;
//        }
//        return;
//    }

    //    private static void processDiscardingTrumps(SeenDiscardedTrumps _readObject, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
//        Net.setReceivedForPlayer(_readObject.getPlace(), _instance);
//        if (Net.allReceived(_instance)) {
//            GameTarot game_ = Net.getGames(_instance).partieTarot();
//            if (Net.isHumanPlayer(game_.getPreneur(), _instance, _common)) {
//                if (_readObject.isDeclaringSlam()) {
//                    Net.initAllReceived(_instance, _common);
//                    PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
//                    //bid_.setLocale(Constants.getDefaultLanguage());
//                    bid_.setLocale("");
//                    bid_.setPlace(game_.getPreneur());
//                    for (byte p: Net.activePlayers(_instance, _common)) {
//                        Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
//                    }
//                    return;
//                }
////                byte donneur_=game_.getDistribution().getDealer();
////                if(!game_.chelemAnnonce()) {
////                    /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
////                    game_.setEntameur(game_.playerAfter(donneur_));
////                }
////                game_.setPliEnCours(true);
//                game_.firstLead();
//                playingTarotCard(_instance,_fct, _common);
//                return;
//            }
//            if (game_.chelemAnnonce()) {
//                PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
//                bid_.setPlace(game_.getPreneur());
//                //bid_.setLocale(Constants.getDefaultLanguage());
//                bid_.setLocale("");
//                for (byte p: Net.activePlayers(_instance, _common)) {
//                    Net.sendObject(Net.getSocketByPlace(p, _common), bid_);
//                }
//                return;
//            }
////            byte donneur_=game_.getDistribution().getDealer();
////            /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
////            game_.setEntameur(game_.playerAfter(donneur_));
////            game_.setPliEnCours(true);
//            game_.firstLead();
//            playingTarotCard(_instance,_fct, _common);
//            return;
//        }
//        return;
//    }

}
