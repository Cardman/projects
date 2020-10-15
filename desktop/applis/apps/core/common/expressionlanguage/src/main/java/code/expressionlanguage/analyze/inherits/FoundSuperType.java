package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class FoundSuperType {
    private RootBlock type;
    private String name;
    private int location;

    public RootBlock getType() {
        return type;
    }

    public void setType(RootBlock _type) {
        this.type = _type;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int _location) {
        this.location = _location;
    }
}
