package code.scripts.messages.cards;

import cards.president.CheckerGamePresidentWithRules;
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
    public static TranslationsFile enCheck(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CheckerGamePresidentWithRules.ERROR_RULES,"Invalid rules");
        e_.add(CheckerGamePresidentWithRules.HANDS_COUNT,"Bad hand count");
        e_.add(CheckerGamePresidentWithRules.SCORES_COUNT,"Bad scores points count");
        e_.add(CheckerGamePresidentWithRules.TRICK_EVENTS,"Either no played card or the first group is not empty");
        e_.add(CheckerGamePresidentWithRules.EMPTY_TRICK,"Empty trick");
        e_.add(CheckerGamePresidentWithRules.NOT_PLAYABLE,"Not playable");
        e_.add(CheckerGamePresidentWithRules.BAD_RANK_COUNT,"Bad rank count");
        e_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT,"Bad switch card group count");
        e_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_OTHER,"Other players cannot switch any card");
        e_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_WINNER,"Winner players must switch exactly the required count");
        e_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_LOOSER,"Looser players must switch exactly the required count");
        e_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_WINNER_CONTENT,"Cards do not come from looser players to winner players");
        e_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_LOOSER_CONTENT,"Looser players must give their best cards");
        e_.add(CheckerGamePresidentWithRules.DUPLICATE_RANK_COUNT,"Duplicate ranks");
        e_.add(CheckerGamePresidentWithRules.BAD_CARD_COUNT,"Bad card count for player");
        e_.add(CheckerGamePresidentWithRules.BAD_CARD_UNIT_COUNT,"The number of each different card is the following (because of stack count):");
        e_.add(CheckerGamePresidentWithRules.NOT_ALLOWED_PLAYED_CARD,"Cards must be switched before played");
        e_.add(CheckerGamePresidentWithRules.MISS_MATCH_TRICK_EVENTS_NOT_EMPTY_GROUP,"There is a mismatch between events count and the possible first played cards group");
        e_.add(CheckerGamePresidentWithRules.MISS_MATCH_STRENGTH,"Played card must have the same strength in a same group");
        e_.add(CheckerGamePresidentWithRules.BAD_PLAYED_CARD,"Played cards are not allowed or bad played count cards");
        e_.add(CheckerGamePresidentWithRules.FIRST_GROUP_CANNOT_BE_EMPTY,"First card group cannot be empty");
        e_.add(CheckerGamePresidentWithRules.CANNOT_PASS,"There must be played card");
        e_.add(CheckerGamePresidentWithRules.NO_CARD_AFTER_FINISHED_DIRECTLY_CARD,"There must not be any card after directly finished trick");
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
    public static TranslationsFile frCheck(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CheckerGamePresidentWithRules.ERROR_RULES,"Règles invalides");
        f_.add(CheckerGamePresidentWithRules.HANDS_COUNT,"Mauvais nombre de mains");
        f_.add(CheckerGamePresidentWithRules.SCORES_COUNT,"Mauvais nombre de scores à points");
        f_.add(CheckerGamePresidentWithRules.TRICK_EVENTS,"Soit aucune carte n'est jouée soit le premier group n'est pas vide");
        f_.add(CheckerGamePresidentWithRules.EMPTY_TRICK,"Pli vide");
        f_.add(CheckerGamePresidentWithRules.NOT_PLAYABLE,"Non jouable");
        f_.add(CheckerGamePresidentWithRules.BAD_RANK_COUNT,"Mauvais nombre de rangs");
        f_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT,"Mauvais nombre de groupe de cartes échangées");
        f_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_OTHER,"Les autres joueurs ne peuvent échanger de cartes");
        f_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_WINNER,"Les joueurs gagnants doivent échanger exactement le nombre requis");
        f_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_LOOSER,"Les joueurs perdants doivent échanger exactement le nombre requis");
        f_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_WINNER_CONTENT,"Les cartes ne viennent pas des joueurs perdants vers les joueurs gagnants");
        f_.add(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_LOOSER_CONTENT,"Les joueurs perdants doivent donner leurs meilleures cartes");
        f_.add(CheckerGamePresidentWithRules.DUPLICATE_RANK_COUNT,"Rangs dupliqués");
        f_.add(CheckerGamePresidentWithRules.BAD_CARD_COUNT,"Mauvais nombre de cartes pour les joueurs");
        f_.add(CheckerGamePresidentWithRules.BAD_CARD_UNIT_COUNT,"Le nombre de chaque de chaque carte différente est le suivant (à cause du nombre de pile):");
        f_.add(CheckerGamePresidentWithRules.NOT_ALLOWED_PLAYED_CARD,"Les cartes doivent être échangées avant d'être jouées");
        f_.add(CheckerGamePresidentWithRules.MISS_MATCH_TRICK_EVENTS_NOT_EMPTY_GROUP,"Il y a une incohérence entre le nombre d'événements et l'éventuel premier groupe de cartes jouées");
        f_.add(CheckerGamePresidentWithRules.MISS_MATCH_STRENGTH,"Les cartes jouées doivent avoir la même force dans un même groupe");
        f_.add(CheckerGamePresidentWithRules.BAD_PLAYED_CARD,"Les cartes jouées ne sont pas autorisées ou le nombre de cartes jouées est mauvais");
        f_.add(CheckerGamePresidentWithRules.FIRST_GROUP_CANNOT_BE_EMPTY,"Le premier group de cartes ne peut pas être vide");
        f_.add(CheckerGamePresidentWithRules.CANNOT_PASS,"Il doit y avoir une carte jouée");
        f_.add(CheckerGamePresidentWithRules.NO_CARD_AFTER_FINISHED_DIRECTLY_CARD,"Il ne doit pas y avoir de carte après un pli directement fini");
        return f_;
    }
}
