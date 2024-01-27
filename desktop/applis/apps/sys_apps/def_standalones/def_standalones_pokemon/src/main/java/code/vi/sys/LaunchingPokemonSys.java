package code.vi.sys;

import aiki.main.LaunchingPokemon;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingPokemonSys extends LaunchingPokemon {
    public LaunchingPokemonSys() {
        super(DefProgramInfos.build());
    }
    public static void loadLaungage(String[] _args) {
        LaunchingPokemon.loadLaungage(_args,new LaunchingPokemonSys());
    }
}
