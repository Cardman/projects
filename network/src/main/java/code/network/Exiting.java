package code.network;


public abstract class Exiting {

    public abstract boolean isBusy();

    public abstract boolean isForced();

    public abstract boolean isClosing();

    public abstract boolean isServer();

    public abstract boolean isTooManyPlayers();
}
