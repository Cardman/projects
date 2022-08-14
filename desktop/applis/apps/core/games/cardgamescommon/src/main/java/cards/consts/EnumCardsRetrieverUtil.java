package cards.consts;

import code.util.core.StringUtil;

public final class EnumCardsRetrieverUtil {
    private EnumCardsRetrieverUtil() {
    }
    public static Role toRole(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"TAKER")) {
            return Role.TAKER;
        }
        if (StringUtil.quickEq(r_,"CALLED_PLAYER")) {
            return Role.CALLED_PLAYER;
        }
        return Role.DEFENDER;
    }
    public static PossibleTrickWinner toPossibleTrickWinner(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"TEAM")) {
            return PossibleTrickWinner.TEAM;
        }
        if (StringUtil.quickEq(r_,"FOE_TEAM")) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }
    public static EndGameState toEndGameState(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"WIN")) {
            return EndGameState.WIN;
        }
        if (StringUtil.quickEq(r_,"EQUALLITY")) {
            return EndGameState.EQUALLITY;
        }
        return EndGameState.LOOSE;
    }
    public static Suit toSuit(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"TRUMP")) {
            return Suit.TRUMP;
        }
        if (StringUtil.quickEq(r_,"HEART")) {
            return Suit.HEART;
        }
        if (StringUtil.quickEq(r_,"SPADE")) {
            return Suit.SPADE;
        }
        if (StringUtil.quickEq(r_,"DIAMOND")) {
            return Suit.DIAMOND;
        }
        if (StringUtil.quickEq(r_,"CLUB")) {
            return Suit.CLUB;
        }
        return Suit.UNDEFINED;
    }
    public static GameType toGameType(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"EDIT")) {
            return GameType.EDIT;
        }
        if (StringUtil.quickEq(r_,"TRAINING")) {
            return GameType.TRAINING;
        }
        return GameType.RANDOM;
    }
    public static Order toOrder(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"TRUMP")) {
            return Order.TRUMP;
        }
        if (StringUtil.quickEq(r_,"SUIT")) {
            return Order.SUIT;
        }
        return Order.NOTHING;
    }
    public static MixCardsChoice toMixCardsChoice(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        if (StringUtil.quickEq(r_,"EACH_DEAL")) {
            return MixCardsChoice.EACH_DEAL;
        }
        if (StringUtil.quickEq(r_,"ONCE_ONLY")) {
            return MixCardsChoice.ONCE_ONLY;
        }
        if (StringUtil.quickEq(r_,"EACH_LAUNCHING")) {
            return MixCardsChoice.EACH_LAUNCHING;
        }
        return MixCardsChoice.NEVER;
    }
    public static boolean toBool(Hypothesis _role) {
        return _role == Hypothesis.SURE;
    }
}
