package aiki.beans.facade.simulation.enums;

import code.util.IdList;
import code.util.core.StringUtil;

public enum TeamCrud {
    ADD("0"),EDIT("1"),REMOVE("2"),NOTHING("3");
    private final String teamCrudString;
    TeamCrud(String _t){
        teamCrudString= _t;
    }

    public String getTeamCrudString() {
        return teamCrudString;
    }

    public static TeamCrud getTeamCrudByName(String _env) {
        for (TeamCrud e: allExceptNothing()) {
            if (StringUtil.quickEq(e.teamCrudString, _env)) {
                return e;
            }
        }
        return NOTHING;
    }
    public static IdList<TeamCrud> allExceptNothing() {
        IdList<TeamCrud> l_ = new IdList<TeamCrud>();
        l_.add(ADD);
        l_.add(EDIT);
        l_.add(REMOVE);
        return l_;
    }
}