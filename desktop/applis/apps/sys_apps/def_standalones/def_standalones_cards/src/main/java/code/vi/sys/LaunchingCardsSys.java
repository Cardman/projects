package code.vi.sys;

import cards.facade.MessagesCardGames;
import applications.main.LaunchingCards;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingCardsSys extends LaunchingCards {
    public LaunchingCardsSys() {
        super(DefProgramInfos.build(MessagesCardGames.CARDS));
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingCardsSys().loadLanguage(_args);
    }
}
