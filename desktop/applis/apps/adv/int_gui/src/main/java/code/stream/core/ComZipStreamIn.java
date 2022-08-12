package code.stream.core;

public class ComZipStreamIn {
    private String name = "";
    private long time;
    private long size;
    private boolean directory;

    public String getName() {
        return name;
    }

    public void setName(String _n) {
        this.name = _n;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long _s) {
        this.size = _s;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long _t) {
        this.time = _t;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean _d) {
        this.directory = _d;
    }
}
