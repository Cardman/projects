package code.scripts.messages.cards;

import cards.president.enumerations.*;
import code.sml.util.TranslationsFile;

public final class MessagesPresident {
    public static final String PRESIDENT_CANNOT_PASS = "0";
    public static final String PRESIDENT_CANNOT_USE_LOWER = "1";
    public static final String PRESIDENT_CANNOT_USE_LOWER_OR_EQ = "2";
    public static final String PRESIDENT_HAS_TO_EQUAL_OR_SKIP = "3";
    public static final String PRESIDENT_HAVE_PASSED = "4";
    public static final String PRESIDENT_HAVE_PLAY_GIVEN_NUMBER_CARDS = "5";
    public static final String PRESIDENT_SKIPPED = "6";
    private MessagesPresident() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.FORBIDDEN),"No possible equality");
        e_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_ALWAYS_NEXT),"Skip the \"able\" next player");
        e_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_DIFF_NEXT_STOP),"The \"able\" next player has to pass or equal");
        e_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.NO_SKIP),"Possible equality which does not skip the \"able\" next player");
        e_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL),"All \"able\" next player in the remain loop has to pass or equal");
        e_.add(PresidentCardsExporterUtil.PLAYING+Playing.CAN_PLAY.getPlay(),"Can play");
        e_.add(PresidentCardsExporterUtil.PLAYING+Playing.SKIPPED.getPlay(),"Skipped");
        e_.add(PresidentCardsExporterUtil.PLAYING+Playing.HAS_TO_EQUAL.getPlay(),"Has to equal");
        e_.add(PresidentCardsExporterUtil.PLAYING+Playing.PASS.getPlay(),"Pass");
        e_.add(PresidentCardsExporterUtil.PLAYING+Playing.FINISH.getPlay(),"Finish");
        e_.add(PresidentCardsExporterUtil.PLAYING+Playing.DO_NOT_EQUAL.getPlay(),"Does not equal");
        return e_;
    }
    public static TranslationsFile enGame(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(PRESIDENT_CANNOT_PASS,"You have to play over {0}.");
        e_.add(PRESIDENT_CANNOT_USE_LOWER,"If you want to play, you have to use cards whose value is greater than or equals to {0}.");
        e_.add(PRESIDENT_CANNOT_USE_LOWER_OR_EQ,"If you want to play, you have to use cards whose value is greater than {0}.");
        e_.add(PRESIDENT_HAS_TO_EQUAL_OR_SKIP,"You have to play cards while same value as {0} or pass.");
        e_.add(PRESIDENT_HAVE_PASSED,"You cannot play any cards while this trick because you had passed before.");
        e_.add(PRESIDENT_HAVE_PLAY_GIVEN_NUMBER_CARDS,"If you want to play, you have to use exactly {0} cards.");
        e_.add(PRESIDENT_SKIPPED,"You cannot play any cards at this moment because two players have consecutively played card groups of same strength.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.FORBIDDEN),"Aucune égalité possible");
        f_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_ALWAYS_NEXT),"Saute le joueur \"apte\" suivant");
        f_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_DIFF_NEXT_STOP),"Le joueur \"apte\" suivant doit passer ou égaler");
        f_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.NO_SKIP),"Égalité possible qui ne saute pas le joueur \"apte\" suivant");
        f_.add(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL),"Tous les joueurs \"aptes\" dans le reste de la boucle doit passer ou égaler");
        f_.add(PresidentCardsExporterUtil.PLAYING+Playing.CAN_PLAY.getPlay(),"Peut jouer");
        f_.add(PresidentCardsExporterUtil.PLAYING+Playing.SKIPPED.getPlay(),"Sauté");
        f_.add(PresidentCardsExporterUtil.PLAYING+Playing.HAS_TO_EQUAL.getPlay(),"Doit égaler");
        f_.add(PresidentCardsExporterUtil.PLAYING+Playing.PASS.getPlay(),"Passe");
        f_.add(PresidentCardsExporterUtil.PLAYING+Playing.FINISH.getPlay(),"Fini");
        f_.add(PresidentCardsExporterUtil.PLAYING+Playing.DO_NOT_EQUAL.getPlay(),"N'égale pas");
        return f_;
    }
    public static TranslationsFile frGame(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(PRESIDENT_CANNOT_PASS,"Vous devez jouer sur {0}.");
        f_.add(PRESIDENT_CANNOT_USE_LOWER,"Si vous voulez jouer, vous devez utiliser des cartes dont la valeur est supérieure ou égale à {0}.");
        f_.add(PRESIDENT_CANNOT_USE_LOWER_OR_EQ,"Si vous voulez jouer, vous devez utiliser des cartes dont la valeur est supérieure à {0}.");
        f_.add(PRESIDENT_HAS_TO_EQUAL_OR_SKIP,"Vous devez jouez des cartes de même valeur que {0} ou passer.");
        f_.add(PRESIDENT_HAVE_PASSED,"Vous ne pouvez pas jouer de cartes pendant ce pli car vous avez passé avant.");
        f_.add(PRESIDENT_HAVE_PLAY_GIVEN_NUMBER_CARDS,"Si vous voulez jouer, vous devez utiliser exactement {0} cartes.");
        f_.add(PRESIDENT_SKIPPED,"Vous ne pouvez pas jouer de cartes sur ce coup car deux joueurs ont joué consécutivement des groupes de cartes de même force.");
        return f_;
    }
}
