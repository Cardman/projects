package cards.gui.labels;

import code.threads.IntCallable;
import code.util.StringMap;

public final class LoadMiniCardsDef implements IntCallable<StringMap<StringMap<int[][]>>> {
    @Override
    public StringMap<StringMap<int[][]>> call() {
        return AbsMetaLabelCard.defs();
    }
}
