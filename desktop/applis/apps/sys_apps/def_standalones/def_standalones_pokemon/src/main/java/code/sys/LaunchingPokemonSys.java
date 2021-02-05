package code.sys;

import aiki.game.fight.BallNumberRate;
import aiki.game.fight.Fighter;
import aiki.main.AikiFactory;
import aiki.main.LaunchingPokemon;
import aiki.map.pokemon.UsablePokemon;
import code.sys.impl.GraphicListGenerator;
import code.sys.impl.ProgramInfos;

public class LaunchingPokemonSys extends LaunchingPokemon {
    public LaunchingPokemonSys() {
        super(new ProgramInfos(), new AikiFactory(new GraphicListGenerator<BallNumberRate>(), new GraphicListGenerator<Fighter>(), new GraphicListGenerator<String>(), new GraphicListGenerator<String>(), new GraphicListGenerator<String>(), new GraphicListGenerator<UsablePokemon>()));
    }
}
