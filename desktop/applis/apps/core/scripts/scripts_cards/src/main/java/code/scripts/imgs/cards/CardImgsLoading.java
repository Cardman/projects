package code.scripts.imgs.cards;

import code.threads.IntCallable;
import code.util.*;

public final class CardImgsLoading implements IntCallable<StringMap<StringMap<String>>> {
    @Override
    public StringMap<StringMap<String>> call() {
        return CardsInit.ms();
    }
}
