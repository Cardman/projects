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
import code.expressionlanguage.guicompos.GuiFactroy;
import code.vi.prot.impl.variant.DefAdvGraphicListGenerator;
import code.vi.prot.impl.variant.DefAdvGraphicListGeneratorStr;

public final class DefLaunchingApplicationsSysOther extends LaunchingApplications {
    public DefLaunchingApplicationsSysOther() {
        super(new DefOtherProgramInfos(), new CardFactories(new DefAdvGraphicListGenerator<CardBelote>(), new DefAdvGraphicListGenerator<CardPresident>(), new DefAdvGraphicListGenerator<CardTarot>(), new DefAdvGraphicListGenerator<Suit>()), new AikiFactory(new DefAdvGraphicListGenerator<BallNumberRate>(), new DefAdvGraphicListGenerator<Fighter>(), new DefAdvGraphicListGenerator<String>(), new DefAdvGraphicListGenerator<String>(), new DefAdvGraphicListGenerator<String>(), new DefAdvGraphicListGenerator<UsablePokemon>()), new GuiFactroy(new DefAdvGraphicListGeneratorStr()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingApplications.loadLaungage(_args,new DefLaunchingApplicationsSysOther());
    }
}
