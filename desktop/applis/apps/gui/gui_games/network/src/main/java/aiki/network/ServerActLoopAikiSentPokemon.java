package aiki.network;

import aiki.network.stream.SentPokemon;
import code.network.NetCommon;
import code.network.NetGroupFrame;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ServerActLoopAikiSentPokemon implements IntServerActLoopAiki {
    @Override
    public void loop(CustList<String> _input, NetAiki _instance, NetCommon _common) {
        SentPokemon sent_ = NetAiki.importSentPokemon(_input);
        if (sent_.getIndex() == IndexConstants.FIRST_INDEX) {
            NetGroupFrame.trySendString(NetAiki.exportPokemonPlayer(sent_.getPokemon()), _common.getSockets().getVal(IndexConstants.SECOND_INDEX));
        }
        if (sent_.getIndex() == IndexConstants.SECOND_INDEX) {
            NetGroupFrame.trySendString(NetAiki.exportPokemonPlayer(sent_.getPokemon()), _common.getSockets().getVal(IndexConstants.FIRST_INDEX));
        }
    }
}
