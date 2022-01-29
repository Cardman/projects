package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class OwnerResultInfo {
    private final String ownerName;
    private final RootBlock owner;
    private final String ownedGeneName;
    private final String ownedName;
    private final RootBlock owned;

    public OwnerResultInfo(String _ownerName, RootBlock _owner, String _sep, String _simple, String _ownedName, RootBlock _owned) {
        this.ownerName = _ownerName;
        this.owner = _owner;
        this.ownedName = _ownedName;
        this.owned = _owned;
        this.ownedGeneName = _ownerName + _sep + _simple;
    }

    public String getOwnedGeneName() {
        return ownedGeneName;
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
