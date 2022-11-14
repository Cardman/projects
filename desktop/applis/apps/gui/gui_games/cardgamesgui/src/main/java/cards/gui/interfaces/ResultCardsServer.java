package cards.gui.interfaces;

import code.network.enums.IpType;

public final class ResultCardsServer {
    private String ip="";
    private boolean create;
    private int nbPlayers;
    private IpType ipType;

    public int getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(int _n) {
        this.nbPlayers = _n;
    }

    public IpType getIpType() {
        return ipType;
    }

    public void setIpType(IpType _i) {
        this.ipType = _i;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String _i) {
        this.ip = _i;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean _c) {
        this.create = _c;
    }

}
