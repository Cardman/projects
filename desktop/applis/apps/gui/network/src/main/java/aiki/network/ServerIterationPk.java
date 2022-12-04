package aiki.network;

import code.network.NetCommon;
import code.network.ServerIteration;
import code.sml.Document;

public final class ServerIterationPk extends ServerIteration {

    private final NetAiki instance;

    public ServerIterationPk(NetAiki _ins, String _in, Document _o, NetCommon _common) {
        super(_in,_o,_common);
        this.instance = _ins;
    }

    @Override
    public void run() {
        SendReceiveServerAiki.loop(getInput(), getObject(),instance, getCom());
    }
}
