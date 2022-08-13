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
    public static boolean toBool(Hypothesis _role) {
        return _role == Hypothesis.SURE;
    }
}
