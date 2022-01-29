package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;

public final class OwnerListResultInfo {
    private final CustList<OwnerResultInfo> list = new CustList<OwnerResultInfo>();
    private final IdTypeList<RootBlock> listOwners = new IdTypeList<RootBlock>();
    public void add(String _ownerName, RootBlock _owner, String _simpleName, RootBlock _owned) {
        if (_owned.withoutInstance()) {
            list.add(0,new OwnerResultInfo(_ownerName,_owner,"..", _simpleName,StringExpUtil.getIdFromAllTypes(_ownerName)+".."+_simpleName,_owned));
        } else {
            list.add(0,new OwnerResultInfo(_ownerName,_owner,"..", _simpleName,_ownerName+".."+_simpleName,_owned));
        }
        listOwners.add(0,_owner);
    }
    public void addInnElt(String _ownerName, RootBlock _owner, String _simpleName, RootBlock _owned) {
        list.add(0,new OwnerResultInfo(_ownerName,_owner,"-", _simpleName,_ownerName+"-"+_simpleName,_owned));
        listOwners.add(0,_owner);
    }
    public OwnerListResultInfo getSubclasses() {
        OwnerListResultInfo out_ = new OwnerListResultInfo();
        for (OwnerResultInfo i: list) {
            boolean sub_ = true;
            for (OwnerResultInfo j: list) {
                RootBlock supType_ = i.getOwner();
                RootBlock subType_ = j.getOwner();
                if (supType_ != subType_ && subType_.isSubTypeOf(supType_)) {
                    sub_ = false;
                    break;
                }
            }
            if (!sub_) {
                continue;
            }
            out_.list.add(i);
            out_.listOwners.add(i.getOwner());
        }
        return out_;
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public boolean onlyOneElt() {
        return listOwners.onlyOneElt();
    }

    public OwnerResultInfo firstElt() {
        return list.first();
    }
    public RootBlock firstOwned() {
        return list.first().getOwned();
    }
    public String firstOwnedName() {
        return list.first().getOwnedName();
    }
    public CustList<ResolvedIdTypeContent> found() {
        CustList<ResolvedIdTypeContent> f_ = new CustList<ResolvedIdTypeContent>();
        for (OwnerResultInfo o: list) {
            f_.add(new ResolvedIdTypeContent(o.getOwnedName(),o.getOwned()));
        }
        return f_;
    }
}
