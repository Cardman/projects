package cards.network.threads;

import cards.belote.GameBelote;
import cards.belote.enumerations.CardBelote;
import cards.network.belote.actions.DiscardedCardBelote;
import code.network.NetCommon;
import code.threads.AbstractThreadFactory;
import code.util.CustList;

public final class ServerActLoopCardsDiscardedCardBelote implements IntServerActLoopCards {

    @Override
    public void loop(CustList<String> _input, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        DiscardedCardBelote discarded_ = Net.importDiscardedCardBelote(_input);
        GameBelote game_ = Net.getGames(_instance).partieBelote();
        if (game_.getPliEnCours().getCartes().contient(discarded_.getCard())) {
            CardBelote r_ = _instance.getIa().getBelote().restore(discarded_.getCard());
            game_.invaliderAjoutCarteEcart(r_);
            return;
        }
        game_.ajouterUneCarteDansPliEnCoursPreneur(_instance.getIa().getBelote().discard(discarded_.getCard()));
    }
}
