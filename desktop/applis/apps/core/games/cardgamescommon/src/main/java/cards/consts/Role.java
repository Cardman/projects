package cards.consts;

import code.util.IdList;

/**Statut du joueur*/
public enum Role {
TAKER("0"),CALLED_PLAYER("1"),DEFENDER("2");
private final String roleSt;

    Role(String _r) {
        this.roleSt = _r;
    }

    public String getRoleSt() {
        return roleSt;
    }
    public static IdList<Role> all() {
        IdList<Role> role_ = new IdList<Role>();
        role_.add(TAKER);
        role_.add(CALLED_PLAYER);
        role_.add(DEFENDER);
        return role_;
    }
}
