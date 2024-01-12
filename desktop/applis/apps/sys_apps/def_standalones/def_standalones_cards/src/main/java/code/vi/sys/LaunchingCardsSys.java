package code.vi.sys;

import cards.main.CardFactories;
import cards.main.LaunchingCards;
import code.util.StringMap;
import code.vi.prot.impl.DefaultExecutorServiceParam;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingCardsSys extends LaunchingCards {
    public LaunchingCardsSys() {
        super(new DefProgramInfos(),new CardFactories(new DefaultExecutorServiceParam<StringMap<StringMap<int[][]>>>()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingCards.loadLaungage(_args,new LaunchingCardsSys());
    }
}
