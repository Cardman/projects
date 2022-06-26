package code.network;

import code.sml.Document;

public abstract class ServerIteration implements Runnable{
    private final String input;
    private final Document object;
    private final NetCommon com;

    protected ServerIteration(String _i, Document _d, NetCommon _c) {
        this.input = _i;
        this.object = _d;
        this.com = _c;
    }

    public String getInput() {
        return input;
    }

    public Document getObject() {
        return object;
    }

    public NetCommon getCom() {
        return com;
    }
}
