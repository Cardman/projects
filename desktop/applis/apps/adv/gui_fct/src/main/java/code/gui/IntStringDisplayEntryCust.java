package code.gui;

public final class IntStringDisplayEntryCust implements DisplayEntryCust<Integer,String> {
    @Override
    public String display(Integer _k, String _v) {
        return _k+":"+_v;
    }
}
