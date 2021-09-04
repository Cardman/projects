package code.sys;

import code.player.main.LaunchingPlayer;
import code.sys.impl.DefProgramInfos;
import code.sys.impl.GraphicComboBoxGenerator;
import code.sys.impl.GraphicStringListGenerator;
import code.sys.impl.ProgramInfos;

public final class LaunchingPlayerSys extends LaunchingPlayer {
    public LaunchingPlayerSys() {
        super(new DefProgramInfos());
    }
    public static void loadLaungage(String[] _args) {
        LaunchingPlayer.loadLaungage(_args,new LaunchingPlayerSys());
    }
}
