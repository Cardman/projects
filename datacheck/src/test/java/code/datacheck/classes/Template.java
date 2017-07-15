package code.datacheck.classes;
import code.util.ints.Listable;

public class Template<T, V> {

    private Listable<T> ts;

    private Listable<V> vs;

    public Listable<T> getTs() {
        return ts;
    }

    public void setTs(Listable<T> _ts) {
        ts = _ts;
    }

    public Listable<V> getVs() {
        return vs;
    }

    public void setVs(Listable<V> _vs) {
        vs = _vs;
    }
}
