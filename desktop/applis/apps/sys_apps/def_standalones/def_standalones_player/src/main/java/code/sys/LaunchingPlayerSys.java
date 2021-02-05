package code.sys;

import code.player.main.LaunchingPlayer;
import code.sys.impl.GraphicComboBoxGenerator;
import code.sys.impl.GraphicStringListGenerator;
import code.sys.impl.ProgramInfos;

public class LaunchingPlayerSys extends LaunchingPlayer {
    public LaunchingPlayerSys() {
        super(new ProgramInfos(new GraphicStringListGenerator(), new GraphicComboBoxGenerator()));
    }
}
