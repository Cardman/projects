package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class OwnerResultInfo {
    private final String ownerName;
    private final RootBlock owner;
    private final String simpleName;
    private final String ownedName;
    private final RootBlock owned;

    public OwnerResultInfo(String _ownerName, RootBlock _owner, String _simple, String _ownedName, RootBlock _owned) {
        this.ownerName = _ownerName;
        this.owner = _owner;
        this.simpleName = _simple;
        this.ownedName = _ownedName;
        this.owned = _owned;
    }
    public String ownedName(){
        return ownerName + ".."+simpleName;
    }
    public String ownedEltName(){
        return ownerName + "-"+simpleName;
    }

    public RootBlock getOwned() {
        return owned;
    }

    public RootBlock getOwner() {
        return owner;
    }

    public String getOwnedName() {
        return ownedName;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
