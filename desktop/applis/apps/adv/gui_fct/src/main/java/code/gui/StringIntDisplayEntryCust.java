package code.gui;

public final class StringIntDisplayEntryCust implements DisplayEntryCust<String,Integer> {
    @Override
    public String display(String _k, Integer _v) {
        return _k+":"+_v;
    }
}
