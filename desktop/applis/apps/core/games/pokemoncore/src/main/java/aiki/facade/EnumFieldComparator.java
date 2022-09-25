package aiki.facade;
import code.util.IdMap;
import aiki.facade.enums.SelectedBoolean;
import code.util.core.SortConstants;
import code.util.core.StringUtil;

public final class EnumFieldComparator<E> {

    private SelectedBoolean increasing = SelectedBoolean.YES_AND_NO;

    private int priority;

    private IdMap<E,String> translations;

    public int compare(E _o1, E _o2) {
        if (increasing == SelectedBoolean.YES) {
            return StringUtil.compareStrings(translations.getVal(_o1),translations.getVal(_o2));
        }
        if (increasing == SelectedBoolean.NO) {
            return StringUtil.compareStrings(translations.getVal(_o2),translations.getVal(_o1));
        }
        return SortConstants.EQ_CMP;
    }

    public void setIncreasing(SelectedBoolean _increasing) {
        increasing = _increasing;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int _priority) {
        priority = _priority;
    }

    public void setTranslations(IdMap<E, String> _translations) {
        translations = _translations;
    }
}
