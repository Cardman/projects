package aiki.network;

import code.network.NetCommon;
import code.network.ServerIteration;

public final class ServerIterationPk extends ServerIteration {

    private final NetAiki instance;

    public ServerIterationPk(NetAiki _ins, String _in, NetCommon _common) {
        super(_in, _common);
        this.instance = _ins;
    }

    @Override
    public void run() {
        NetAiki.loopServer(getInput(),instance,getCom());
    }
}
