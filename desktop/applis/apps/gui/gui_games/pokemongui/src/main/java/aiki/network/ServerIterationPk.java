package aiki.network;

import code.sml.Document;

public final class ServerIterationPk implements Runnable {

    private final NetAiki instance;
    private final String input;
    private final Document object;

    public ServerIterationPk(NetAiki _ins, String _in, Document _o) {
        this.instance = _ins;
        this.input = _in;
        this.object = _o;
    }

    @Override
    public void run() {
        SendReceiveServerAiki.loop(input,object,instance);
    }
}
