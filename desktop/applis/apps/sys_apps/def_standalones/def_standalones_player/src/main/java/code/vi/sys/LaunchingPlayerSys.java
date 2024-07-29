package code.vi.sys;

import applications.code.player.main.LaunchingPlayer;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingPlayerSys extends LaunchingPlayer {
    public LaunchingPlayerSys() {
        super(DefProgramInfos.build());
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingPlayerSys().loadLanguage(TEMP_FOLDER,_args);
    }
}
