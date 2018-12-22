package cards.network.common.select;
import code.util.CustList;
import code.util.Numbers;


public final class TeamsPlayers {

    private CustList<Numbers<Byte>> teams;

    public CustList<Numbers<Byte>> getTeams() {
        return teams;
    }

    public void setTeams(CustList<Numbers<Byte>> _teams) {
        teams = _teams;
    }
}
