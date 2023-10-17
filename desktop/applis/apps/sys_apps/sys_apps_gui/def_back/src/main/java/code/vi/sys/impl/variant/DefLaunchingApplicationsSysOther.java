package code.vi.sys.impl.variant;

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
import code.gui.initialize.AbstractProgramInfos;
import code.vi.prot.impl.DefErrGenerator;
import code.vi.prot.impl.DefInterceptor;
import code.vi.prot.impl.variant.DefAdvGraphicListGenerator;

public final class DefLaunchingApplicationsSysOther extends LaunchingApplications {
    public DefLaunchingApplicationsSysOther() {
        this(new DefOtherProgramInfos());
    }
    public DefLaunchingApplicationsSysOther(AbstractProgramInfos _p) {
        super(_p,new CardFactories(new DefAdvGraphicListGenerator<CardBelote>(), new DefAdvGraphicListGenerator<CardPresident>(), new DefAdvGraphicListGenerator<CardTarot>(), new DefAdvGraphicListGenerator<Suit>()), new AikiFactory(new DefAdvGraphicListGenerator<BallNumberRate>(), new DefAdvGraphicListGenerator<Fighter>(), new DefAdvGraphicListGenerator<String>(), new DefAdvGraphicListGenerator<String>(), new DefAdvGraphicListGenerator<String>(), new DefAdvGraphicListGenerator<UsablePokemon>()),new CdmFactory(_p.light(),new DefInterceptor(new DefErrGenerator())));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new DefLaunchingApplicationsSysOther());
    }
}
