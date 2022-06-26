package cards.network.threads;

import code.network.NetCommon;
import code.network.ServerIteration;
import code.sml.Document;
import code.threads.AbstractThreadFactory;

public final class ServerIterationCards extends ServerIteration {
    private final Net instance;
    private final AbstractThreadFactory fct;

    public ServerIterationCards(String _i, Document _d, Net _in, AbstractThreadFactory _f, NetCommon _common) {
        super(_i,_d,_common);
        this.instance = _in;
        this.fct = _f;
    }

    @Override
    public void run() {
        SendReceiveServerCards.loop(getInput(),getObject(),instance,fct, getCom());
    }
}
