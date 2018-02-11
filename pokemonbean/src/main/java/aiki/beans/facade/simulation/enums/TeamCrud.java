package aiki.beans.facade.simulation.enums;

import code.util.StringList;

public enum TeamCrud {
    ADD,EDIT,REMOVE,NOTHING;
    public static TeamCrud getTeamCrudByName(String _env) {
        for (TeamCrud e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return null;
    }
}