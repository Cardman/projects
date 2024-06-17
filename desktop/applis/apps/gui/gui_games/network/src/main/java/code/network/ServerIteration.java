package code.network;

public abstract class ServerIteration implements Runnable{
    private final String input;
    private final NetCommon com;

    protected ServerIteration(String _i, NetCommon _c) {
        this.input = _i;
        this.com = _c;
    }

    public String getInput() {
        return input;
    }

    public NetCommon getCom() {
        return com;
    }
}
