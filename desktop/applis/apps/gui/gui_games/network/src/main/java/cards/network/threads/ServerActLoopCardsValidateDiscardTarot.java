package cards.network.threads;

import cards.gui.containers.ContainerSingleTarot;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsValidateDiscardTarot implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        char ch_ = _input.get(0).charAt(0);
        if (Net.VALIDATE_DISCARD_CALL_BEFORE == ch_) {
            ServerActLoopCardsActedByClientReceived.processCallingTarot(Net.importHandTarot(_input.get(1),Net.SEP_1),_instance,_fct,_common);
            return;
        }
        if (Net.VALIDATE_DISCARD_SLAM == ch_) {
            Net.getGames(_instance).partieTarot().ajouterChelemUtilisateur();
        }
        if (_input.size() > 1) {
            ContainerSingleTarot.playerCall(Net.getGames(_instance).partieTarot(),Net.importHandTarot(_input.get(1),Net.SEP_1),_instance.getIa());
        }
        ContainerSingleTarot.possibleAddFirstTrick(Net.getGames(_instance).partieTarot());
        ServerActLoopCardsActedByClientReceived.playingTarotCard(_instance,_fct, _common);
    }
}
