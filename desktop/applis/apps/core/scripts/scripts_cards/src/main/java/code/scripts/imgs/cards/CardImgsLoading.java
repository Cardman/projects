package code.scripts.imgs.cards;

import code.threads.IntCallable;
import code.util.*;

public final class CardImgsLoading implements IntCallable<StringMap<StringMap<int[][]>>> {
    @Override
    public StringMap<StringMap<int[][]>> call() {
        return CardsInit.ms();
    }
}
