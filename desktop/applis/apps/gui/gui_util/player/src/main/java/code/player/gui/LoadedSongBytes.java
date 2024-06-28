package code.player.gui;

public final class LoadedSongBytes {
    private final String name;
    private final byte[] data;

    public LoadedSongBytes(String _n, byte[] _d) {
        this.name = _n;
        this.data = _d;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }
}
