package code.vi.sys;

import applications.code.player.main.LaunchingPlayer;
import code.player.gui.MessagesSongs;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingPlayerSys extends LaunchingPlayer {
    public LaunchingPlayerSys() {
        super(DefProgramInfos.build(MessagesSongs.SONGS_APP));
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingPlayerSys().loadLanguage(_args);
    }
}
