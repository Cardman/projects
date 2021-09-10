package code.vi.sys;

import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.main.CardFactories;
import cards.main.LaunchingCards;
import cards.president.enumerations.CardPresident;
import cards.tarot.enumerations.CardTarot;
import code.vi.sys.impl.variant.DefProgramInfos;
import code.vi.sys.impl.variant.GraphicListGenerator;

public final class LaunchingCardsSys extends LaunchingCards {
    public LaunchingCardsSys() {
        super(new DefProgramInfos(),new CardFactories(new GraphicListGenerator<CardBelote>(), new GraphicListGenerator<CardPresident>(), new GraphicListGenerator<CardTarot>(), new GraphicListGenerator<Suit>()));
    }
    public static void loadLaungage(String[] _args) {
        LaunchingCards.loadLaungage(_args,new LaunchingCardsSys());
    }
}
