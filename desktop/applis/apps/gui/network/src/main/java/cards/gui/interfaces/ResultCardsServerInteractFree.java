package cards.gui.interfaces;

import cards.facade.enumerations.GameEnum;
import code.network.WindowNetWork;
import code.network.enums.IpType;

public final class ResultCardsServerInteractFree implements ResultCardsServerInteract {

    private final ResultCardsServer server;

    public ResultCardsServerInteractFree(boolean _create, String _ip, IpType _ipType, int _players) {
        ResultCardsServer s_ = new ResultCardsServer();
        s_.setCreate(_create);
        s_.setIp(_ip);
        s_.setIpType(_ipType);
        s_.setNbPlayers(_players);
        server = s_;
    }

    @Override
    public ResultCardsServer interact(WindowNetWork _app, GameEnum _jeuBouton) {
        return server;
    }
}
