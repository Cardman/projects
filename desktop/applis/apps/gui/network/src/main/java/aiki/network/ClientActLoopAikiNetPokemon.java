package aiki.network;

import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopAikiNetPokemon implements IntClientActLoopAiki {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        _window.netNetPokemon(NetAiki.importNetPokemon(_parts));
    }
}
