package aiki.comparators;
import aiki.db.DataBase;
import code.util.StringMap;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorTrStrings implements Comparing<String> {

    private final StringMap<String> translator;

    public ComparatorTrStrings(StringMap<String> _translator) {
        translator = _translator;
    }

    @Override
    public int compare(String _e1, String _e2) {
        return compare(translator, _e1, _e2);
    }

    public static int compare(StringMap<String> _trStrings, String _e1, String _e2) {
        String trOne_ = tr(_trStrings, _e1);
        String trTwo_ = tr(_trStrings, _e2);
        return StringUtil.compareStrings(trOne_,trTwo_);
    }

    private static String tr(StringMap<String> _translator, String _e) {
        String tr_;
        if (_translator.contains(_e)) {
            tr_ = _translator.getVal(_e);
        } else {
            tr_ = DataBase.EMPTY_STRING;
        }
        return tr_;
    }
}
