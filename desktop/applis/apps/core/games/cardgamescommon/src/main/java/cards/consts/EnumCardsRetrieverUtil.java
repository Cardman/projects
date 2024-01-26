package cards.consts;

import code.util.core.StringUtil;

public final class EnumCardsRetrieverUtil {
    private EnumCardsRetrieverUtil() {
    }
    public static Role toRole(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (Role e: Role.all()) {
            if (StringUtil.quickEq(r_,e.getRoleSt())) {
                return e;
            }
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
        for (Suit s: Suit.toutesCouleurs()) {
            if (StringUtil.quickEq(r_,s.getSuitSt())) {
                return s;
            }
        }
        return Suit.UNDEFINED;
    }
    public static GameType toGameType(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (GameType s: GameType.all()) {
            if (StringUtil.quickEq(r_,s.getGameTypeSt())) {
                return s;
            }
        }
        return GameType.RANDOM;
    }
    public static Order toOrder(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (Order s: Order.all()) {
            if (StringUtil.quickEq(r_,s.getOrderSt())) {
                return s;
            }
        }
        return Order.NOTHING;
    }
    public static MixCardsChoice toMixCardsChoice(String _role) {
        String r_ = StringUtil.nullToEmpty(_role);
        for (MixCardsChoice e: MixCardsChoice.all()) {
            if (StringUtil.quickEq(r_,e.getMixSt())) {
                return e;
            }
        }
        return MixCardsChoice.NEVER;
    }
    public static boolean toBool(Hypothesis _role) {
        return _role == Hypothesis.SURE;
    }
}
