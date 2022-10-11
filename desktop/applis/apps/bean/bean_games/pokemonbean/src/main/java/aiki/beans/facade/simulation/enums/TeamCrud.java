package aiki.beans.facade.simulation.enums;

import code.util.IdList;
import code.util.core.StringUtil;

public enum TeamCrud {
    ADD("ADD"),EDIT("EDIT"),REMOVE("REMOVE"),NOTHING("NOTHING");
    private final String teamCrudString;
    TeamCrud(String _t){
        teamCrudString= _t;
    }

    public String getTeamCrudString() {
        return teamCrudString;
    }

    public static TeamCrud getTeamCrudByName(String _env) {
        for (TeamCrud e: all()) {
            if (StringUtil.quickEq(e.teamCrudString, _env)) {
                return e;
            }
        }
        return NOTHING;
    }
    public static IdList<TeamCrud> all() {
        IdList<TeamCrud> l_ = new IdList<TeamCrud>();
        l_.add(ADD);
        l_.add(EDIT);
        l_.add(REMOVE);
        l_.add(NOTHING);
        return l_;
    }
}