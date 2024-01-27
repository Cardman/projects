package code.vi.sys;

import code.player.main.LaunchingPlayer;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingPlayerSys extends LaunchingPlayer {
    public LaunchingPlayerSys() {
        super(DefProgramInfos.build());
    }
    public static void loadLaungage(String[] _args) {
        LaunchingPlayer.loadLaungage(_args,new LaunchingPlayerSys());
    }
}
