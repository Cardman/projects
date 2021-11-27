package code.vi.sys;

import aiki.game.fight.BallNumberRate;
import aiki.game.fight.Fighter;
import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import aiki.map.pokemon.UsablePokemon;
import code.vi.sys.impl.variant.DefProgramInfos;
import code.vi.prot.impl.variant.GraphicListGenerator;

public final class LaunchingPokemonSys extends LaunchingPokemon {
    public LaunchingPokemonSys() {
        super(new DefProgramInfos(), new AikiFactory(new GraphicListGenerator<BallNumberRate>(), new GraphicListGenerator<Fighter>(), new GraphicListGenerator<String>(), new GraphicListGenerator<String>(), new GraphicListGenerator<String>(), new GraphicListGenerator<UsablePokemon>()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingPokemon.loadLaungage(_args,new LaunchingPokemonSys());
    }
}
