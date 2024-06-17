package cards.network.threads;

import code.network.NetCommon;
import code.network.ServerIteration;
import code.threads.AbstractThreadFactory;

public final class ServerIterationCards extends ServerIteration {
    private final Net instance;
    private final AbstractThreadFactory fct;

    public ServerIterationCards(String _i, Net _in, AbstractThreadFactory _f, NetCommon _common) {
        super(_i, _common);
        this.instance = _in;
        this.fct = _f;
    }

    @Override
    public void run() {
        Net.loopServer(getInput(),instance,fct,getCom());
    }
}
