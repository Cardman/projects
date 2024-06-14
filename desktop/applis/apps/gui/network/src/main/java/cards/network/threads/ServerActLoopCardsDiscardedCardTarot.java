package cards.network.threads;

import cards.network.tarot.actions.DiscardedCardTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.CardTarot;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsDiscardedCardTarot implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        DiscardedCardTarot discarded_ = Net.importDiscardedCardTarot(_input);
        GameTarot game_ = Net.getGames(_instance).partieTarot();
        if (game_.getPliEnCours().getCartes().contient(discarded_.getCard())) {
            CardTarot r_ = _instance.getIa().getTarot().restore(discarded_.getCard());
            game_.retirerUneCarteDuChien(r_);
            return;
        }
        game_.ajouterUneCarteDansPliEnCoursPreneur(_instance.getIa().getTarot().discard(discarded_.getCard()));
    }
}
