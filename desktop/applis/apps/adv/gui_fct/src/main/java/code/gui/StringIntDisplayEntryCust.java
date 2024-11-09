package code.gui;

public final class StringIntDisplayEntryCust implements DisplayEntryCust<Integer,EditedCrudPair<String,Integer>> {
    @Override
    public String display(Integer _k, EditedCrudPair<String,Integer> _v) {
        return _v.getKey()+":"+_v.getValue();
    }
}
