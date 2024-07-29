package code.vi.sys;

import aiki.gui.WindowAiki;
import applications.main.LaunchingPokemon;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingPokemonSys extends LaunchingPokemon {
    public LaunchingPokemonSys() {
        super(DefProgramInfos.build());
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingPokemonSys().loadLanguage(WindowAiki.TEMP_FOLDER,_args);
    }
}
