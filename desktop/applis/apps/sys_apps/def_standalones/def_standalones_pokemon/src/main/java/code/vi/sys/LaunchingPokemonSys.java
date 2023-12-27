package code.vi.sys;

import aiki.db.DataBase;
import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import code.vi.prot.impl.DefaultExecutorServiceParam;
import code.vi.sys.impl.variant.DefProgramInfos;

public final class LaunchingPokemonSys extends LaunchingPokemon {
    public LaunchingPokemonSys() {
        super(new DefProgramInfos(),new AikiFactory(new DefaultExecutorServiceParam<DataBase>()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingPokemon.loadLaungage(_args,new LaunchingPokemonSys());
    }
}
