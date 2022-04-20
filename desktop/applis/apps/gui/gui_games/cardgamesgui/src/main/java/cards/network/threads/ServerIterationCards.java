package cards.network.threads;

import code.sml.Document;
import code.threads.AbstractThreadFactory;

public final class ServerIterationCards implements Runnable {
    private final String input;
    private final Document doc;
    private final Net instance;
    private final AbstractThreadFactory fct;

    public ServerIterationCards(String _i, Document _d, Net _in, AbstractThreadFactory _f) {
        this.input = _i;
        this.doc = _d;
        this.instance = _in;
        this.fct = _f;
    }

    @Override
    public void run() {
        SendReceiveServerCards.loop(input,doc,instance,fct);
    }
}
