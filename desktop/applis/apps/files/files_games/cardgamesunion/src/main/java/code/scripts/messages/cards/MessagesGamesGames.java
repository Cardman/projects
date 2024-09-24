package code.scripts.messages.cards;
import cards.facade.enumerations.GameEnum;
import code.sml.util.TranslationsFile;
public final class MessagesGamesGames{
    private MessagesGamesGames(){}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(GameEnum.BELOTE.getNumber(), "Belote");
        e_.add(GameEnum.PRESIDENT.getNumber(), "President");
        e_.add(GameEnum.TAROT.getNumber(), "Tarot");
        e_.add(GameEnum.CLASSIC.getNumber(), "Solitaire");
        e_.add(GameEnum.FREECELL.getNumber(), "Free Cell");
        e_.add(GameEnum.SPIDER.getNumber(), "Spider");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(GameEnum.BELOTE.getNumber(), "Belote");
        f_.add(GameEnum.PRESIDENT.getNumber(),"Président");
        f_.add(GameEnum.TAROT.getNumber(), "Tarot");
        f_.add(GameEnum.CLASSIC.getNumber(), "Solitaire");
        f_.add(GameEnum.FREECELL.getNumber(), "Free Cell");
        f_.add(GameEnum.SPIDER.getNumber(), "Spider");
        return f_;
    }
}
