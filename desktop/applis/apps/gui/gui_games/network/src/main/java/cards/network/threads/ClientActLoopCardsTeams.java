package cards.network.threads;

import cards.gui.TeamsPlayers;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerMultiBelote;
import cards.gui.containers.ContainerMultiTarot;
import code.gui.initialize.AbstractSocket;
import code.network.WindowNetWork;
import code.util.CustList;

public final class ClientActLoopCardsTeams implements IntClientActLoopCards {
    @Override
    public void loop(WindowNetWork _window, CustList<String> _parts, AbstractSocket _socket) {
        ContainerGame cg_ = _window.getNetg().getContainerGame();
        if (cg_ instanceof ContainerMultiBelote) {
            TeamsPlayers t_ = new TeamsPlayers();
            t_.setTeams(Net.importTeams(_parts.get(0)));
            ((ContainerMultiBelote)cg_).showTeams(t_);
        }
        if (cg_ instanceof ContainerMultiTarot) {
            TeamsPlayers t_ = new TeamsPlayers();
            t_.setTeams(Net.importTeams(_parts.get(0)));
            ((ContainerMultiTarot)cg_).showTeams(t_);
        }
    }
}
