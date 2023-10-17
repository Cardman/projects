package code.vi.sys;

import cards.main.LaunchingCards;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingCardsSys extends LaunchingCards {
    public LaunchingCardsSys() {
        super(new DefProgramInfos());
    }
    public static void loadLaungage(String[] _args) {
        LaunchingCards.loadLaungage(_args,new LaunchingCardsSys());
    }
}
