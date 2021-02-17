package code.gui;

public final class FileCount {
    private long search;
    private long found;
    public void incrSearch() {
        search++;
    }
    public void incrFound() {
        found++;
    }

    public long getSearch() {
        return search;
    }

    public long getFound() {
        return found;
    }
}
