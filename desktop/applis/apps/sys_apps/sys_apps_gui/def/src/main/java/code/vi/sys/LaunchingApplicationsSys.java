package code.vi.sys;

import aiki.game.fight.BallNumberRate;
import aiki.game.fight.Fighter;
import aiki.main.AikiFactory;
import aiki.map.pokemon.UsablePokemon;
import applications.main.LaunchingApplications;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.main.CardFactories;
import cards.president.enumerations.CardPresident;
import cards.tarot.enumerations.CardTarot;
import code.gui.CdmFactory;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;
import code.vi.prot.impl.variant.GraphicListGeneratorStr;
import code.vi.sys.impl.variant.DefProgramInfos;
import code.vi.prot.impl.variant.GraphicListGenerator;

public final class LaunchingApplicationsSys extends LaunchingApplications {

    public LaunchingApplicationsSys() {
        super(new CardFactories(new GraphicListGenerator<CardBelote>(), new GraphicListGenerator<CardPresident>(), new GraphicListGenerator<CardTarot>(), new GraphicListGenerator<Suit>()), new AikiFactory(new GraphicListGenerator<BallNumberRate>(), new GraphicListGenerator<Fighter>(), new GraphicListGenerator<String>(), new GraphicListGenerator<String>(), new GraphicListGenerator<String>(), new GraphicListGenerator<UsablePokemon>()),new CdmFactory(new DefProgramInfos(),new DefInterceptor(new DefErrGenerator()),new GraphicListGeneratorStr()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new LaunchingApplicationsSys());
    }
}
