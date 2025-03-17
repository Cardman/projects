package cards.network.threads;

import cards.belote.GameBelote;
import cards.president.GamePresident;
import cards.tarot.GameTarot;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.threads.AbstractThreadFactory;
import code.threads.ThreadUtil;
import code.util.CustList;

public final class ServerActLoopCardsDonePlaying extends ServerActLoopCardsActedByClientAll {

    @Override
    protected void loopBelote(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        if (!game_.keepPlayingCurrentTrick()) {
            game_.ajouterDixDeDerPliEnCours();

            if (!game_.keepPlayingCurrentGame()) {
                ThreadUtil.sleep(_fct,1000);
                endGameBelote(_instance, _common);
                return;
            }
            ThreadUtil.sleep(_fct,3000);
            Net.initAllReceived(_instance, _common);
            for (int p: Net.activePlayers(_instance, _common)) {
                NetGroupFrame.trySendString(Net.exportDonePause(), Net.getSocketByPlace(p, _common));
            }
            return;
        }

        playingBeloteCard(_instance,_fct, _common);
    }

    @Override
    protected void loopPresident(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GamePresident game_ = Net.getGames(_instance).partiePresident();
        if (game_.getProgressingTrick().estVide()) {

            if (!game_.keepPlayingCurrentGame()) {
                ThreadUtil.sleep(_fct,1000);
                endGamePresident(_instance, _common);
                return;
            }
            ThreadUtil.sleep(_fct,3000);
            Net.initAllReceived(_instance, _common);
            for (int p: Net.activePlayers(_instance, _common)) {
                NetGroupFrame.trySendString(Net.exportDonePause(), Net.getSocketByPlace(p, _common));
            }
            return;
        }

        playingPresidentCard(_instance,_fct, _common);
    }

    @Override
    protected void loopTarot(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        if (!game_.keepPlayingCurrentTrick()) {
            game_.ajouterPetitAuBoutPliEnCours();

            if (!game_.keepPlayingCurrentGame()) {
                ThreadUtil.sleep(_fct,1000);
                endGameTarot(_instance, _common);
                return;
            }
            ThreadUtil.sleep(_fct,3000);
            Net.initAllReceived(_instance, _common);
            for (int p: Net.activePlayers(_instance, _common)) {
                NetGroupFrame.trySendString(Net.exportDonePause(), Net.getSocketByPlace(p, _common));
            }
            return;
        }

        playingTarotCard(_instance,_fct,_common);
    }
}
