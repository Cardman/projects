package code.vi.sys;

import cards.gui.WindowCards;
import cards.main.LaunchingCards;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingCardsSys extends LaunchingCards {
    public LaunchingCardsSys() {
        super(DefProgramInfos.build());
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingCardsSys().loadLanguage(WindowCards.TEMP_FOLDER,_args);
    }
}
