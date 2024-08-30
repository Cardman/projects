package code.vi.sys;

import aiki.sml.MessagesPkGame;
import applications.main.LaunchingPokemon;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingPokemonSys extends LaunchingPokemon {
    public LaunchingPokemonSys() {
        super(DefProgramInfos.build(MessagesPkGame.PK));
    }
    public static void loadLaungage(String[] _args) {
        new LaunchingPokemonSys().loadLanguage(_args);
    }
}
