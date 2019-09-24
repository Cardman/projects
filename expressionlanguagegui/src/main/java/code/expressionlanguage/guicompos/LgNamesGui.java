package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.LgNamesUtils;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.variables.VariableSuffix;
import code.gui.OtherConfirmDialog;
import code.gui.OtherFrame;
import code.gui.OtherDialog;
import code.resources.ResourceFiles;
import code.sml.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

import java.awt.event.WindowListener;


public class LgNamesGui extends LgNamesUtils {

    private String aliasActionListener;
    private String aliasActionPerformed;
    private String aliasActionEvent;
    private String aliasWheelListener;
    private String aliasWheelMove;
    private String aliasWheelEvent;
    private String aliasWheelRotatedClicks;
    private String aliasAddChange;
    private String aliasTreeListener;
    private String aliasTreeListenerValueChanged;
    private String aliasTreeNode;
    private String aliasTreeNodeAdd;
    private String aliasTreeNodeInsert;
    private String aliasTreeNodeRemove;
    private String aliasTreeNodeRemoveFromParent;
    private String aliasTreeNodeRemoveAllChildren;
    private String aliasTreeNodeSetUserObject;
    private String aliasTreeNodeNb;
    private String aliasTreeNodeGetFirstChild;
    private String aliasTreeNodeGetLastChild;
    private String aliasTreeNodeGetNextSibling;
    private String aliasTreeNodeGetPreviousSibling;
    private String aliasTreeNodeGetParentNode;
    private String aliasTreeNodeGetUserObject;
    private String aliasTreeNodeIsAncestor;
    private String aliasTreeNodeIsDescendant;
    private String aliasTree;
    private String aliasTreeSetRootVisible;
    private String aliasTreeIsRootVisible;
    private String aliasTreeGetSelected;
    private String aliasTreeAddTreeListener;
    private String aliasTreeReload;
    private String aliasTableListener;
    private String aliasTableValueTableChanged;
    private String aliasTableGui;
    private String aliasTableGetSelectedRow;
    private String aliasTableGetSelectedRows;
    private String aliasTableGetSelectedRowCount;
    private String aliasTableGetRowCount;
    private String aliasTableSetRowCount;
    private String aliasTableGetColumnCount;
    private String aliasTableSetColumns;
    private String aliasTableGetColumnName;
    private String aliasTableGetValue;
    private String aliasTableSetValue;
    private String aliasTableGetRowAtPoint;
    private String aliasTableGetColumnAtPoint;
    private String aliasTableIsMultiple;
    private String aliasTableSetMultiple;
    private String aliasTableIsReorder;
    private String aliasTableSetReorder;
    private String aliasTableMoveColumn;
    private String aliasTableAddInterval;
    private String aliasTableRemoveInterval;
    private String aliasTableApplyChanges;
    private String aliasTableAddHeader;
    private String aliasTableAddSelect;
    private String aliasChangeListener;
    private String aliasStateChanged;
    private String aliasMouseListener;
    private String aliasMouseClicked;
    private String aliasMousePressed;
    private String aliasMouseReleased;
    private String aliasMouseEntered;
    private String aliasMouseExited;
    private String aliasMouseDragged;
    private String aliasMouseMoved;
    private String aliasMouseEvent;
    private String aliasMouseEventGetFirst;
    private String aliasMouseEventGetSecond;
    private String aliasMouseEventGetClicks;
    private String aliasMouseEventIsAlt;
    private String aliasMouseEventIsCtrl;
    private String aliasMouseEventIsShift;
    private String aliasMouseEventIsLeft;
    private String aliasMouseEventIsMiddle;
    private String aliasMouseEventIsRight;
    private String aliasWindowListener;
    private String aliasWindowOpened;
    private String aliasWindowClosed;
    private String aliasWindowClosing;
    private String aliasWindowIconified;
    private String aliasWindowDeiconified;
    private String aliasWindowActivated;
    private String aliasWindowDeactivated;
    private String aliasWindowEvent;
    private String aliasListSelection;
    private String aliasValueChanged;
    private String aliasRequestFocus;
    private String aliasCompToolTip;
    private String aliasCompFocusable;
    private String aliasCompOpaque;
    private String aliasCompBack;
    private String aliasCompFore;
    private String aliasCompGetFirstPos;
    private String aliasCompGetSecondPos;
    private String aliasCompLoc;
    private String aliasCompBorLine;
    private String aliasCompBorTitle;
    private String aliasCompBorLower;
    private String aliasCompBorRaise;
    private String aliasAddWheelListener;
    private String aliasAddKeyListener;
    private String aliasKeyListener;
    private String aliasKeyPressed;
    private String aliasKeyTyped;
    private String aliasKeyReleased;
    private String aliasKeyEvent;
    private String aliasKeyEventChar;
    private String aliasKeyEventCode;
    private String aliasKeyEventIsShift;
    private String aliasKeyEventIsAlt;
    private String aliasKeyEventIsCtrl;

    private String aliasConfirm;
    private String aliasConfirmMessage;
    private String aliasConfirmField;
    private String aliasConfirmOk;
    private String aliasConfirmYesNo;
    private String aliasConfirmFull;
    private String aliasConfirmFieldOk;
    private String aliasConfirmFieldCancel;
    private String aliasConfirmFieldYes;
    private String aliasConfirmFieldNo;
    private String aliasFrame;
    private String aliasDialog;
    private String aliasDialogIsModal;
    private String aliasDialogSetModal;
    private String aliasWindowType;
    private String aliasWindowTypeRelative;
    private String aliasRemoveWindowListener;
    private String aliasGetWindowListeners;
    private String aliasPanel;
    private String aliasPanelFlow;
    private String aliasPanelAbsolute;
    private String aliasPanelBorder;
    private String aliasPanelBorderNorth;
    private String aliasPanelBorderSouth;
    private String aliasPanelBorderEast;
    private String aliasPanelBorderWest;
    private String aliasPanelBorderCenter;
    private String aliasPanelBorderBeforeFirst;
    private String aliasPanelBorderBeforeLineBegins;
    private String aliasPanelBorderAfterLast;
    private String aliasPanelBorderAfterLineEnds;
    private String aliasPanelGrid;
    private String aliasPanelPageBox;
    private String aliasPanelValidate;
    private String aliasTabbedPane;
    private String aliasTabbedPaneNb;
    private String aliasTabbedPaneAdd;
    private String aliasTabbedPaneGet;
    private String aliasTabbedPaneGetTitle;
    private String aliasTabbedPaneSet;
    private String aliasTabbedPaneSetTitle;
    private String aliasTabbedPaneRemove;
    private String aliasTabbedPaneIndex;
    private String aliasTabbedPaneSelIndex;
    private String aliasButton;
    private String aliasProgBar;
    private String aliasProgBarValue;
    private String aliasProgBarMax;
    private String aliasProgBarMin;
    private String aliasProgBarOr;
    private String aliasImageLabel;
    private String aliasTextLabel;
    private String aliasScrollPane;
    private String aliasScrollPaneGetView;
    private String aliasScrollPaneSetView;
    private String aliasScrollPaneHorizontalValue;
    private String aliasScrollPaneVerticalValue;
    private String aliasScrollPaneValidate;
    private String aliasSplitPane;
    private String aliasSplitPaneGetDividerLocation;
    private String aliasSplitPaneSetDividerLocation;
    private String aliasSplitPaneGetDividerSize;
    private String aliasSplitPaneSetDividerSize;
    private String aliasSplitPaneIsContinuousLayout;
    private String aliasSplitPaneSetContinuousLayout;
    private String aliasSplitPaneIsOneTouchExpandable;
    private String aliasSplitPaneSetOneTouchExpandable;
    private String aliasSplitPaneGetLeft;
    private String aliasSplitPaneSetLeft;
    private String aliasSplitPaneGetRight;
    private String aliasSplitPaneSetRight;
    private String aliasSplitPaneValidate;
    private String aliasGetFont;
    private String aliasSetFont;
    private String aliasFont;
    private String aliasFontGetName;
    private String aliasFontGetSize;
    private String aliasFontIsBold;
    private String aliasFontIsItalic;
    private String aliasFontStringWidth;
    private String aliasImage;
    private String aliasImageGetWidth;
    private String aliasImageGetHeight;
    private String aliasImageGet;
    private String aliasImageSet;
    private String aliasImageIsWithAlpha;
    private String aliasImageGetColor;
    private String aliasImageSetColor;
    private String aliasImageGetFont;
    private String aliasImageSetFont;
    private String aliasImageDraw;
    private String aliasImageDrawLine;
    private String aliasImageDrawRect;
    private String aliasImageDrawOval;
    private String aliasImageFillRect;
    private String aliasImageFillOval;
    private String aliasImageDrawPolygon;
    private String aliasImageFillPolygon;
    private String aliasImageDispose;
    private String aliasColor;
    private String aliasColorRed;
    private String aliasColorGreen;
    private String aliasColorBlue;
    private String aliasColorAlpha;
    private String aliasColorIsTransparent;
    private String aliasComponentSetPaint;
    private String aliasComponentGetPaint;
    private String aliasComponentRepaint;
    private String aliasComponentSetAutoscrolls;
    private String aliasComponentIsAutoscrolls;
    private String aliasComponentGetWidth;
    private String aliasComponentGetHeight;
    private String aliasComponentGetPreferredSize;
    private String aliasComponentSetPreferredSize;
    private String aliasComponentSetSize;
    private String aliasComponentIsVisible;
    private String aliasComponentSetVisible;
    private String aliasComponentInvokeLater;
    private String aliasComponent;
    private String aliasDimension;
    private String aliasDimensionGetHeight;
    private String aliasDimensionGetWidth;
    private String aliasSetContent;
    private String aliasAddCompo;
    private String aliasGetParentCompo;
    private String aliasGetNextCompo;
    private String aliasGetIndexCompo;
    private String aliasRemoveAll;
    private String aliasAddListener;
    private String aliasAddWindowListener;
    private String aliasSetLabelText;
    private String aliasSetLabelImage;
    private String aliasPaint;
    private String aliasPaintMethod;
    private String aliasPaintAdd;
    private String aliasPaintRefresh;
    private String aliasRemoveCompo;
    private String aliasCount;
    private String aliasIsVisible;
    private String aliasSetVisible;
    private String aliasWindow;
    private String aliasArgs;
    private String aliasPack;
    private String aliasDispose;
    private String aliasInput;
    private String aliasInputIsEnabled;
    private String aliasInputSetEnabled;

    private String aliasRender;
    private String aliasRenderGetHeight;
    private String aliasRenderGetWidth;
    private String aliasRenderGetPaint;
    private String aliasRenderSetHeight;
    private String aliasRenderSetWidth;
    private String aliasRenderSetPaint;
    private String aliasGrList;
    private String aliasGrListAdd;
    private String aliasGrListSet;
    private String aliasGrListGetListView;
    private String aliasGrListGetSelectedIndexes;
    private String aliasGrListSetSelectedIndexes;
    private String aliasGrListClearSelection;
    private String aliasGrListClear;
    private String aliasGrListRemove;
    private String aliasGrListUpdateGraphics;
    private String aliasGrListGetRender;
    private String aliasGrListSetRender;
    private String aliasGrListGetSelection;
    private String aliasGrListSetSelection;
    private String aliasGrListGetVisibleRowCount;
    private String aliasGrListSetVisibleRowCount;

    private String aliasCombo;
    private String aliasComboGetSelectedItem;
    private String aliasComboAddItem;
    private String aliasComboGetItemCount;
    private String aliasComboSelectItem;
    private String aliasComboSetListener;
    private String aliasComboGetListener;
    private String aliasComboGetSelectedIndexes;
    private String aliasComboGetSelectedIndex;
    private String aliasComboRemoveAllItems;
    private String aliasComboRemoveItem;

    private String aliasButtonGroup;
    private String aliasButtonGroupAdd;
    private String aliasRadio;
    private String aliasRadioIsSelected;
    private String aliasRadioSetSelected;
    private String aliasRadioGetText;
    private String aliasRadioSetText;
    private String aliasPopupMenu;
    private String aliasPopupMenuAdd;
    private String aliasPopupMenuGetComp;
    private String aliasPopupMenuRemoveComp;
    private String aliasPopupMenuNbComp;
    private String aliasPopupMenuAddMenu;
    private String aliasPopupMenuGetMenu;
    private String aliasPopupMenuRemoveMenu;
    private String aliasPopupMenuNbMenu;
    private String aliasPopupMenuShow;
    private String aliasTextField;
    private String aliasTextFieldGetText;
    private String aliasTextFieldSetText;
    private String aliasTextFieldAuto;
    private String aliasTextFieldAddAction;
    private String aliasTextFieldAddDocument;
    private String aliasTextFieldAddPopup;
    private String aliasTextArea;
    private String aliasTextAreaGetText;
    private String aliasTextAreaSetText;
    private String aliasTextAreaGetTabSize;
    private String aliasTextAreaSetTabSize;
    private String aliasTextAreaAppend;
    private String aliasTextAreaInsert;
    private String aliasTextAreaReplaceRange;
    private String aliasTextAreaReplaceSelection;
    private String aliasTextAreaGetSelectedText;
    private String aliasTextAreaSetSelectionStart;
    private String aliasTextAreaSetSelectionEnd;
    private String aliasTextAreaSelect;
    private String aliasTextAreaSelectAll;
    private String aliasCheckBox;
    private String aliasCheckBoxGetText;
    private String aliasCheckBoxSetText;
    private String aliasCheckBoxIsSelected;
    private String aliasCheckBoxSetSelected;
    private String aliasCheckBoxAddAction;
    private String aliasSpinner;
    private String aliasSpinnerSetRange;
    private String aliasSpinnerSetRangeValue;
    private String aliasSpinnerGetValue;
    private String aliasSpinnerSetValue;
    private String aliasSpinnerGetMax;
    private String aliasSpinnerSetMax;
    private String aliasSpinnerGetMin;
    private String aliasSpinnerSetMin;
    private String aliasSpinnerGetStep;
    private String aliasSpinnerSetStep;
    private String aliasSlider;
    private String aliasSliderGetValue;
    private String aliasSliderSetValue;
    private String aliasSliderGetMax;
    private String aliasSliderSetMax;
    private String aliasSliderGetMin;
    private String aliasSliderSetMin;
    private String aliasSliderGetOrientation;
    private String aliasSliderSetOrientation;
    private String aliasGetMenuBar;
    private String aliasSetMenuBar;
    private String aliasMenuBar;
    private String aliasMenuBarAdd;
    private String aliasMenuBarGet;
    private String aliasMenuBarRemove;
    private String aliasMenuBarNb;
    private String aliasAbsMenu;
    private String aliasAbsMenuGetParent;
    private String aliasAbsMenuIsEnabled;
    private String aliasAbsMenuSetEnabled;
    private String aliasAbsMenuSetDeepEnabled;
    private String aliasAbsMenuGetText;
    private String aliasAbsMenuSetText;
    private String aliasMenu;
    private String aliasMenuAdd;
    private String aliasMenuGet;
    private String aliasMenuRemove;
    private String aliasMenuNb;
    private String aliasMenuAddSeparator;
    private String aliasAbsMenuItem;
    private String aliasAbsMenuItemAddAction;
    private String aliasMenuItem;
    private String aliasMenuItemCheck;
    private String aliasMenuItemCheckIsSelected;
    private String aliasMenuItemCheckSetSelected;
    public void buildOther() {
        super.buildOther();
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasWindowType, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasPanel);
        method_ = new StandardMethod(aliasSetContent, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasWindowTypeRelative, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPack, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVisible, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetVisible, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMenuBar, params_, aliasMenuBar, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasMenuBar);
        method_ = new StandardMethod(aliasSetMenuBar, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasWindowListener);
        method_ = new StandardMethod(aliasAddWindowListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasWindowType, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasConfirm, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList(aliasImage,aliasWindowType,getAliasString(),getAliasString(),getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmField, params_, getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasWindowType,getAliasString(),getAliasString(),getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmField, params_, getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasImage,aliasWindowType,getAliasString(),getAliasString(),getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmFull, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasWindowType,getAliasString(),getAliasString(),getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmFull, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasImage,aliasWindowType,getAliasString(),getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmYesNo, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasWindowType,getAliasString(),getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmYesNo, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasImage,aliasWindowType,getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmOk, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasWindowType,getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmOk, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasImage,aliasWindowType,getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmMessage, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasWindowType,getAliasString(),getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasConfirmMessage, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        fields_.add(aliasConfirmFieldOk,new StandardField(aliasConfirmFieldOk,getAliasPrimInteger(),true,true,stdcl_));
        fields_.add(aliasConfirmFieldYes,new StandardField(aliasConfirmFieldYes,getAliasPrimInteger(),true,true,stdcl_));
        fields_.add(aliasConfirmFieldNo,new StandardField(aliasConfirmFieldNo,getAliasPrimInteger(),true,true,stdcl_));
        fields_.add(aliasConfirmFieldCancel,new StandardField(aliasConfirmFieldCancel,getAliasPrimInteger(),true,true,stdcl_));
        std_ = stdcl_;
        getStandards().put(aliasConfirm, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasFrame, fields_, constructors_, methods_, aliasWindowType, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDispose, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWindow, params_, aliasFrame, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasArgs, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasString()), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        StandardConstructor ctor_;
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasFrame, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasDialog, fields_, constructors_, methods_, aliasWindowType, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDialogIsModal, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasDialogSetModal, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasDialog, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasComponent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParentCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetNextCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetFont, params_, aliasFont, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasFont);
        method_ = new StandardMethod(aliasSetFont, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompBack, params_, aliasColor, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasCompBack, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompFore, params_, aliasColor, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasCompFore, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompFocusable, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompFocusable, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompOpaque, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompOpaque, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompToolTip, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasCompToolTip, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompGetFirstPos, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompGetSecondPos, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompLoc, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasCompBorLine, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasColor,getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompBorLine, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasCompBorTitle, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompBorLower, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompBorRaise, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);

        params_ = new StringList();
        String type_ = StringList.concat(getAliasFct(),"<",aliasComponent,",?>");
        method_ = new StandardMethod(aliasComponentGetPaint, params_, type_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(type_);
        method_ = new StandardMethod(aliasComponentSetPaint, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentRepaint, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        type_ = getAliasPrimBoolean();
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentIsAutoscrolls, params_, type_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(type_);
        method_ = new StandardMethod(aliasComponentSetAutoscrolls, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetPreferredSize, params_, aliasDimension, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasDimension);
        method_ = new StandardMethod(aliasComponentSetPreferredSize, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentIsVisible, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasComponentSetVisible, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasRunnable());
        method_ = new StandardMethod(aliasComponentInvokeLater, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasKeyListener);
        method_ = new StandardMethod(aliasAddKeyListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasWheelListener);
        method_ = new StandardMethod(aliasAddWheelListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasAddListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRequestFocus, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetHeight, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetWidth, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasComponent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasDimension, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDimensionGetHeight, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDimensionGetWidth, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasDimension);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasDimension, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTreeNode, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeInsert, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTreeNodeRemove, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeRemove, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeRemoveFromParent, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeRemoveAllChildren, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTreeNodeSetUserObject, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetUserObject, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeNb, params_,getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetFirstChild, params_,aliasTreeNode, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetLastChild, params_,aliasTreeNode, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetPreviousSibling, params_,aliasTreeNode, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetNextSibling, params_,aliasTreeNode, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetParentNode, params_,aliasTreeNode, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeIsAncestor, params_,getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeIsDescendant, params_,getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasTreeNode, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTree, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasTreeListener);
        method_ = new StandardMethod(aliasTreeAddTreeListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasTreeSetRootVisible, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeIsRootVisible, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeGetSelected, params_, aliasTreeNode, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeGetSelected, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeReload, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasTreeNode);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasTree, std_);


        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTableGui, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasTableAddHeader, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasTableListener);
        method_ = new StandardMethod(aliasTableAddSelect, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableIsMultiple, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasTableSetMultiple, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableIsReorder, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasTableSetReorder, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetColumnName, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetColumnCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetColumnAtPoint, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetRowAtPoint, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetRowCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableSetRowCount, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTableSetColumns, params_, getAliasVoid(), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetSelectedRow, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetSelectedRowCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetSelectedRows, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger()), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableMoveColumn, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableAddInterval, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableRemoveInterval, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableApplyChanges, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableSetValue, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetValue, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,true,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasTableGui, std_);

        buildEvents();

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPanel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasAddCompo, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent,getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddCompo, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasRemoveCompo, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasRemoveCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetIndexCompo, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelFlow, params_, aliasPanel, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelAbsolute, params_, aliasPanel, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelPageBox, params_, aliasPanel, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasPanelGrid, params_, aliasPanel, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRemoveAll, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelValidate, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasPanel, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPanelBorder, fields_, constructors_, methods_, aliasPanel, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent,getAliasString());
        method_ = new StandardMethod(aliasAddCompo, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        fields_.add(aliasPanelBorderNorth,new StandardField(aliasPanelBorderNorth,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderSouth,new StandardField(aliasPanelBorderSouth,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderWest,new StandardField(aliasPanelBorderWest,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderEast,new StandardField(aliasPanelBorderEast,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderCenter,new StandardField(aliasPanelBorderCenter,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderBeforeFirst,new StandardField(aliasPanelBorderBeforeFirst,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderBeforeLineBegins,new StandardField(aliasPanelBorderBeforeLineBegins,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderAfterLineEnds,new StandardField(aliasPanelBorderAfterLineEnds,getAliasString(),true,true,stdcl_));
        fields_.add(aliasPanelBorderAfterLast,new StandardField(aliasPanelBorderAfterLast,getAliasString(),true,true,stdcl_));
        std_ = stdcl_;
        getStandards().put(aliasPanelBorder, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTabbedPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(getAliasString(),aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTabbedPaneSelIndex, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneSelIndex, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneIndex, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneRemove, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneRemove, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneSet, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasString());
        method_ = new StandardMethod(aliasTabbedPaneSetTitle, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneGet, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneGetTitle, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTabbedPaneNb, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRemoveAll, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasTabbedPane, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasScrollPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneHorizontalValue, params_,getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasScrollPaneHorizontalValue, params_,getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneVerticalValue, params_,getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasScrollPaneVerticalValue, params_,getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneGetView, params_,aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneValidate, params_,getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasScrollPaneSetView, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasComponent);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasScrollPane, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasSplitPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetLeft, params_,aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasSplitPaneSetLeft, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetRight, params_,aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasSplitPaneSetRight, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetDividerLocation, params_,getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSplitPaneSetDividerLocation, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetDividerSize, params_,getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSplitPaneSetDividerSize, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneIsOneTouchExpandable, params_,getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSplitPaneSetOneTouchExpandable, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneIsContinuousLayout, params_,getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSplitPaneSetContinuousLayout, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneValidate, params_,getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),aliasComponent,aliasComponent);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasSplitPane, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasInput, fields_, constructors_, methods_, aliasComponent, MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInputIsEnabled, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasInputSetEnabled, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasInput, std_);


        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasButton, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasAddListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasButton, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasProgBar, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasProgBarMin, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarMin, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasProgBarValue, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarValue, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasProgBarMax, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarMax, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasProgBarOr, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarOr, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasProgBar, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTextLabel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasSetLabelText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasTextLabel, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasImageLabel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasImage);
        method_ = new StandardMethod(aliasSetLabelImage, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasImage);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasImageLabel, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasFont, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontGetName, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontGetSize, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontIsBold, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontIsItalic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFontStringWidth, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasImage,getAliasString());
        method_ = new StandardMethod(aliasFontStringWidth, params_, getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimBoolean(),getAliasPrimBoolean(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasFont, stdcl_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasColor, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorAlpha, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorBlue, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorRed, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorGreen, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorIsTransparent, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasColor, stdcl_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasImage, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageIsWithAlpha, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetHeight, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetWidth, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageGet, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(), getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageSet, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetColor, params_, aliasColor, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasImageSetColor, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetFont, params_, aliasFont, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasFont);
        method_ = new StandardMethod(aliasImageSetFont, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);

        params_ = new StringList(aliasImage,getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDraw, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDraw, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);

        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger());
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawLine, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawRect, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawOval, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(arrInt_,arrInt_);
        method_ = new StandardMethod(aliasImageDrawPolygon, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageFillRect, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageFillOval, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(arrInt_,arrInt_);
        method_ = new StandardMethod(aliasImageFillPolygon, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageDispose, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);

        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasImage, stdcl_);
        buildInputs();
        buildMenus();
    }
    private void buildEvents() {
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        StandardConstructor ctor_;
        StringMap<StandardField> fields_;
        StandardClass stdcl_;
        StringList params_;
        StandardMethod method_;
        StandardClass std_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasActionEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        std_ = stdcl_;
        getStandards().put(aliasActionEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasWindowEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        std_ = stdcl_;
        getStandards().put(aliasWindowEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMouseEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsAlt, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsCtrl, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsShift, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsLeft, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsMiddle, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsRight, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventGetClicks, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventGetFirst, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventGetSecond, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),
                getAliasPrimBoolean(),getAliasPrimBoolean(),getAliasPrimBoolean(),
                getAliasPrimBoolean(),getAliasPrimBoolean(),getAliasPrimBoolean(),
                getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasMouseEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasWheelEvent, fields_, constructors_, methods_, aliasMouseEvent, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWheelRotatedClicks, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),
                getAliasPrimBoolean(),getAliasPrimBoolean(),getAliasPrimBoolean(),
                getAliasPrimBoolean(),getAliasPrimBoolean(),getAliasPrimBoolean(),
                getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasWheelEvent, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasKeyEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventIsAlt, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventIsCtrl, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventIsShift, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventChar, params_, getAliasPrimChar(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventCode, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(
                getAliasPrimBoolean(),getAliasPrimBoolean(),getAliasPrimBoolean(),
                getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);

        std_ = stdcl_;
        getStandards().put(aliasKeyEvent, std_);
    }
    private void buildMenus() {
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        StringMap<StandardField> fields_;
        StandardClass stdcl_;
        StringList params_;
        StandardMethod method_;
        StandardConstructor ctor_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMenuBar, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasMenu);
        method_ = new StandardMethod(aliasMenuBarAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasMenu);
        method_ = new StandardMethod(aliasMenuBarRemove, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasMenuBarGet, params_, aliasMenu, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuBarNb, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasMenuBar, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasAbsMenu, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasAbsMenuGetParent, params_, aliasMenu, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasAbsMenuSetText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasAbsMenuGetText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasAbsMenuSetEnabled, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasAbsMenuIsEnabled, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasAbsMenuSetDeepEnabled, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasAbsMenu, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMenu, fields_, constructors_, methods_, aliasAbsMenu, MethodModifier.FINAL);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasMenuAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasMenuRemove, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasMenuGet, params_, aliasAbsMenu, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuNb, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuAddSeparator, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasMenu, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasAbsMenuItem, fields_, constructors_, methods_, aliasAbsMenu, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasAbsMenuItemAddAction, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasAbsMenuItem, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMenuItem, fields_, constructors_, methods_, aliasAbsMenuItem, MethodModifier.FINAL);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasMenuItem, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasMenuItemCheck, fields_, constructors_, methods_, aliasAbsMenuItem, MethodModifier.FINAL);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasMenuItemCheckSetSelected, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuItemCheckIsSelected, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasMenuItemCheck, stdcl_);

    }
    private void buildInputs() {
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        StringMap<StandardField> fields_;
        StandardClass stdcl_;
        StringList params_;
        StandardMethod method_;
        StandardConstructor ctor_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasRender, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        String typeHeight_ = StringList.concat(getAliasFct(),"<",getAliasObject(),",",getAliasPrimInteger(),">");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetHeight, params_, typeHeight_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(typeHeight_);
        method_ = new StandardMethod(aliasRenderSetHeight, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        String typeWidth_ = StringList.concat(getAliasFct(),"<",getAliasObject(),",",getAliasPrimInteger(),",",getAliasPrimInteger(),">");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetWidth, params_, typeWidth_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(typeWidth_);
        method_ = new StandardMethod(aliasRenderSetWidth, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        String typePaint_ = StringList.concat(getAliasFct(),"<",aliasGrList,",",getAliasObject(),",",getAliasPrimInteger(),",",getAliasPrimBoolean(),",",aliasImage,",?>");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetPaint, params_, typePaint_, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(typePaint_);
        method_ = new StandardMethod(aliasRenderSetPaint, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasRender, stdcl_);


        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasGrList, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetRender, params_, aliasRender, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasRender);
        method_ = new StandardMethod(aliasGrListSetRender, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetSelection, params_, aliasListSelection, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasGrListSetSelection, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetVisibleRowCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListSetVisibleRowCount, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasObject());
        method_ = new StandardMethod(aliasGrListAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),aliasImageLabel,getAliasObject());
        method_ = new StandardMethod(aliasGrListAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),aliasImageLabel,getAliasObject());
        method_ = new StandardMethod(aliasGrListSet, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetListView, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasObject()), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetSelectedIndexes, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger()), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListSetSelectedIndexes, params_, getAliasVoid(), true, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListClearSelection, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListUpdateGraphics, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListRemove, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListClear, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasGrList, stdcl_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCombo, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetListener, params_, aliasListSelection, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasComboSetListener, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasComboAddItem, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasComboSelectItem, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetItemCount, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedIndex, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedIndexes, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger()), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedItem, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasComboRemoveItem, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboRemoveAllItems, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,true,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasString());
        ctor_ = new StandardConstructor(params_,true,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasCombo, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasButtonGroup, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasRadio);
        method_ = new StandardMethod(aliasButtonGroupAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasButtonGroup, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasPopupMenu, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasPopupMenuAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuGetComp, params_, aliasComponent, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasPopupMenuRemoveComp, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPopupMenuNbComp, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasPopupMenuAddMenu, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuGetMenu, params_, aliasAbsMenu, false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasPopupMenuRemoveMenu, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPopupMenuNbMenu, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasComponent,getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuShow, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasPopupMenu, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasRadio, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRadioIsSelected, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasRadioSetSelected, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRadioGetText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasRadioSetText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasChangeListener);
        method_ = new StandardMethod(aliasAddChange, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasRadio, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCheckBox, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCheckBoxIsSelected, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCheckBoxSetSelected, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCheckBoxGetText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasCheckBoxSetText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasCheckBox, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTextField, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextFieldGetText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTextFieldSetText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasTextFieldAddAction, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasTextField, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasTextArea, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTextAreaSetText, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTextAreaAppend, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaInsert, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetSelectedText, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetSelectionStart, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetSelectionEnd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetTabSize, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetTabSize, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaReplaceRange, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasTextAreaReplaceSelection, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSelect, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaSelectAll, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasString(),getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasTextArea, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasSpinner, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetMax, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetMax, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetMin, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetMin, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetValue, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetValue, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetStep, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetStep, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetRange, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetRangeValue, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasChangeListener);
        method_ = new StandardMethod(aliasAddChange, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasSpinner, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasSlider, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetMax, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetMax, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetMin, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetMin, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetValue, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetValue, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetOrientation, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetOrientation, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasChangeListener);
        method_ = new StandardMethod(aliasAddChange, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger(),getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        getStandards().put(aliasSlider, stdcl_);
    }

    @Override
    public ResultErrorStd getSimpleResult(Analyzable _conf, ClassField _classField) {
        if (StringList.quickEq(_classField.getClassName(),aliasConfirm)) {
            ResultErrorStd res_ = new ResultErrorStd();
            String fieldName_ = _classField.getFieldName();
            if (StringList.quickEq(fieldName_,aliasConfirmFieldOk)) {
                res_.setResult(new IntStruct(OtherConfirmDialog.OK_OPTION));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasConfirmFieldYes)) {
                res_.setResult(new IntStruct(OtherConfirmDialog.YES_OPTION));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasConfirmFieldNo)) {
                res_.setResult(new IntStruct(OtherConfirmDialog.NO_OPTION));
                return res_;
            }
            res_.setResult(new IntStruct(OtherConfirmDialog.CANCEL_OPTION));
            return res_;
        }
        if (StringList.quickEq(_classField.getClassName(),aliasPanelBorder)) {
            ResultErrorStd res_ = new ResultErrorStd();
            String fieldName_ = _classField.getFieldName();
            if (StringList.quickEq(fieldName_,aliasPanelBorderNorth)) {
                res_.setResult(new StringStruct(PanelBorderStruct.NORTH));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderSouth)) {
                res_.setResult(new StringStruct(PanelBorderStruct.SOUTH));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderWest)) {
                res_.setResult(new StringStruct(PanelBorderStruct.WEST));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderEast)) {
                res_.setResult(new StringStruct(PanelBorderStruct.EAST));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderCenter)) {
                res_.setResult(new StringStruct(PanelBorderStruct.CENTER));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderBeforeFirst)) {
                res_.setResult(new StringStruct(PanelBorderStruct.BEFORE_FIRST_LINE));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderBeforeLineBegins)) {
                res_.setResult(new StringStruct(PanelBorderStruct.BEFORE_LINE_BEGINS));
                return res_;
            }
            if (StringList.quickEq(fieldName_,aliasPanelBorderAfterLineEnds)) {
                res_.setResult(new StringStruct(PanelBorderStruct.AFTER_LINE_ENDS));
                return res_;
            }
            res_.setResult(new StringStruct(PanelBorderStruct.AFTER_LAST_LINE));
            return res_;
        }
        return super.getSimpleResult(_conf, _classField);
    }
    @Override
    public Struct getStringOfObject(ContextEl _cont, Struct _arg) {
        if (_arg instanceof EventStruct) {
            String className_ = _arg.getClassName(_cont);
            String id_ = Templates.getIdFromAllTypes(className_);
            RootBlock clBody_ = _cont.getClasses().getClassBody(id_);
            if (!ContextEl.isEnumType(clBody_)) {
                return new StringStruct(_arg.getClassName(_cont));
            }
        }
        return super.getStringOfObject(_cont,_arg);
    }
    public Argument defaultInstance(ExecutableCode _cont, String _id) {
        Argument arg_ = super.defaultInstance(_cont, _id);
        if (!arg_.isNull() || _cont.getContextEl().hasExceptionOrFailInit()) {
            return arg_;
        }
        if (StringList.quickEq(_id,aliasFont)) {
            return new Argument(new FontStruct());
        }
        if (_cont.getContextEl().isInitEnums()) {
            _cont.getContextEl().failInitEnums();
            return new Argument();
        }
        if (StringList.quickEq(_id,aliasFrame)) {
            return new Argument(new FrameStruct(new OtherFrame()));
        }
        if (StringList.quickEq(_id,aliasDialog)) {
            return new Argument(new DialogStruct(new OtherDialog()));
        }
        if (StringList.quickEq(_id,aliasPanel)) {
            return new Argument(PanelStruct.newFlow(aliasPanel));
        }
        if (StringList.quickEq(_id,aliasTabbedPane)) {
            return new Argument(new TabbedPaneStruct(aliasTabbedPane));
        }
        if (StringList.quickEq(_id,aliasScrollPane)) {
            return new Argument(ScrollPaneStruct.newScroll(aliasScrollPane));
        }
        if (StringList.quickEq(_id,aliasPanelBorder)) {
            return new Argument(PanelBorderStruct.newBorder(aliasPanelBorder));
        }
        if (StringList.quickEq(_id,aliasButton)) {
            return new Argument(new PlainButtonStruct(aliasButton));
        }
        if (StringList.quickEq(_id,aliasTextLabel)) {
            return new Argument(new TextLabelStruct(aliasTextLabel));
        }
        if (StringList.quickEq(_id,aliasImageLabel)) {
            return new Argument(new PreparedLabelStruct(aliasImageLabel));
        }
        if (StringList.quickEq(_id,aliasRender)) {
            return new Argument(new RenderStruct());
        }
        if (StringList.quickEq(_id,aliasGrList)) {
            return new Argument(new GraphicListStruct((GuiContextEl)_cont,aliasGrList,true));
        }
        if (StringList.quickEq(_id,aliasCombo)) {
            return new Argument(new GraphicComboStruct(aliasCombo));
        }
        return arg_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        String name_ = _method.getName();
        ResultErrorStd r_ = new ResultErrorStd();
        if (StringList.quickEq(name_,aliasActionEvent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new ActionEventStruct(aliasActionEvent));
            return r_;
        }
        if (StringList.quickEq(name_,aliasWindowEvent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new WindowEventStruct(aliasWindowEvent));
            return r_;
        }
        if (StringList.quickEq(name_,aliasMouseEvent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            MouseEventStruct res_ = new MouseEventStruct(aliasMouseEvent);
            res_.setFirst(_args[0]);
            res_.setSecond(_args[1]);
            res_.setAlt(_args[2]);
            res_.setCtrl(_args[3]);
            res_.setShift(_args[4]);
            res_.setLeft(_args[5]);
            res_.setMiddle(_args[6]);
            res_.setRight(_args[7]);
            res_.setClicks(_args[8]);
            r_.setResult(res_);
            return r_;
        }
        if (StringList.quickEq(name_,aliasWheelEvent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            MouseWheelEventStruct res_ = new MouseWheelEventStruct(aliasWheelEvent);
            res_.setFirst(_args[0]);
            res_.setSecond(_args[1]);
            res_.setAlt(_args[2]);
            res_.setCtrl(_args[3]);
            res_.setShift(_args[4]);
            res_.setLeft(_args[5]);
            res_.setMiddle(_args[6]);
            res_.setRight(_args[7]);
            res_.setClicks(_args[8]);
            res_.setRotated(_args[9]);
            r_.setResult(res_);
            return r_;
        }
        if (StringList.quickEq(name_,aliasKeyEvent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            KeyEventStruct res_ = new KeyEventStruct(aliasKeyEvent);
            res_.setAlt(_args[0]);
            res_.setCtrl(_args[1]);
            res_.setShift(_args[2]);
            res_.setKeyChar(_args[3]);
            res_.setKeyCode(_args[4]);
            r_.setResult(res_);
            return r_;
        }
        if (StringList.quickEq(name_,aliasFrame)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new FrameStruct(new OtherFrame()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasDialog)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new DialogStruct(new OtherDialog()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasPanel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(PanelStruct.newFlow(aliasPanel));
            return r_;
        }
        if (StringList.quickEq(name_,aliasPanelBorder)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(PanelBorderStruct.newBorder(aliasPanelBorder));
            return r_;
        }
        if (StringList.quickEq(name_,aliasTabbedPane)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new TabbedPaneStruct(aliasTabbedPane));
            return r_;
        }
        if (StringList.quickEq(name_,aliasScrollPane)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(ScrollPaneStruct.newScroll(_args[0], aliasButton));
            } else {
                r_.setResult(ScrollPaneStruct.newScroll(aliasButton));
            }
            return r_;
        }
        if (StringList.quickEq(name_,aliasSplitPane)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (!(_args[1] instanceof CustComponentStruct)) {
                r_.setError(getAliasNullPe());
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (!(_args[2] instanceof CustComponentStruct)) {
                r_.setError(getAliasNullPe());
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            CustComponentStruct first_ = (CustComponentStruct) _args[1];
            CustComponentStruct second_ = (CustComponentStruct) _args[2];
            if (first_.getParentComponent() != NullStruct.NULL_VALUE) {
                r_.setError(getAliasIllegalArg());
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (second_.getParentComponent() != NullStruct.NULL_VALUE) {
                r_.setError(getAliasIllegalArg());
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new SplitPaneStruct(aliasSplitPane,_args[0],_args[1],_args[2]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasProgBar)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new ProgressBarStruct(aliasProgBar));
            return r_;
        }
        if (StringList.quickEq(name_,aliasButton)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new PlainButtonStruct(_args[0],aliasButton));
            } else {
                r_.setResult(new PlainButtonStruct(aliasButton));
            }
            return r_;
        }
        if (StringList.quickEq(name_,aliasTextLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new TextLabelStruct(_args[0],aliasTextLabel));
            } else {
                r_.setResult(new TextLabelStruct(aliasTextLabel));
            }
            return r_;
        }
        if (StringList.quickEq(name_,aliasImageLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new PreparedLabelStruct(_args[0],aliasImageLabel));
            } else {
                r_.setResult(new PreparedLabelStruct(aliasImageLabel));
            }
            return r_;
        }
        if (StringList.quickEq(name_,aliasFont)) {
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new FontStruct());
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new FontStruct(((NumberStruct)_args[0]).intStruct()));
                return r_;
            }
            r_.setResult(new FontStruct(_args[0],((BooleanStruct)_args[1]).getInstance(),((BooleanStruct)_args[2]).getInstance(),((NumberStruct)_args[3]).intStruct()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasColor)) {
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct()));
                return r_;
            }
            if (_method.getParametersTypes().size() == 2) {
                r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct(),((BooleanStruct)_args[1]).getInstance()));
                return r_;
            }
            if (_method.getParametersTypes().size() == 3) {
                r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct()));
                return r_;
            }
            r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasImage)) {
            r_.setResult(new ImageStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((BooleanStruct)_args[2]).getInstance()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasDimension)) {
            if (_method.getParametersTypes().size() == 1) {
                if (!(_args[0] instanceof DimensionStruct)) {
                    r_.setResult(new DimensionStruct(0,0));
                    return r_;
                }
                r_.setResult(new DimensionStruct((DimensionStruct)_args[0]));
                return r_;
            }
            r_.setResult(new DimensionStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct()));
            return r_;
        }
        if (StringList.quickEq(name_,aliasTreeNode)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new TreeNodeStruct());
                return r_;
            }
            r_.setResult(new TreeNodeStruct(_args[0]));
            return r_;
        }
        if (StringList.quickEq(name_,aliasTree)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (!(_args[0] instanceof TreeNodeStruct)) {
                r_.setError(getAliasNullPe());
                return r_;
            }
            r_.setResult(new TreeStruct(aliasTree, (TreeNodeStruct) _args[0]));
            return r_;
        }
        if (StringList.quickEq(name_,aliasTableGui)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new TableStruct(aliasTableGui, _args[0]));
            return r_;
        }
        if (StringList.quickEq(name_,aliasRender)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new RenderStruct());
            return r_;
        }
        if (StringList.quickEq(name_,aliasGrList)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new GraphicListStruct((GuiContextEl)_cont,aliasGrList,((BooleanStruct)_args[0]).getInstance()));
            return r_;
        }
        if (StringList.quickEq(name_, aliasCombo)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new GraphicComboStruct(aliasCombo));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new GraphicComboStruct(aliasCombo,_args[0]));
                return r_;
            }
            r_.setResult(new GraphicComboStruct(aliasCombo,((NumberStruct)_args[0]).intStruct(),_args[1]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasButtonGroup)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new CustButtonGroupStruct());
            return r_;
        }
        if (StringList.quickEq(name_, aliasRadio)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new RadioButtonStruct(aliasRadio));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new RadioButtonStruct(aliasRadio,_args[0]));
                return r_;
            }
            r_.setResult(new RadioButtonStruct(aliasRadio,_args[0],_args[1]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasCheckBox)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new CheckBoxStruct(aliasCheckBox));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new CheckBoxStruct(aliasCheckBox,_args[0]));
                return r_;
            }
            r_.setResult(new CheckBoxStruct(aliasCheckBox,_args[0],_args[1]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasPopupMenu)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new PopupStruct(aliasPopupMenu));
            return r_;
        }
        if (StringList.quickEq(name_, aliasTextField)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new TextFieldStruct(aliasTextField));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                if (!(_args[0] instanceof NumberStruct)) {
                    r_.setResult(new TextFieldStruct(aliasTextField,_args[0]));
                    return r_;
                }
                r_.setResult(new TextFieldStruct(aliasTextField,(NumberStruct) _args[0]));
                return r_;
            }
            r_.setResult(new TextFieldStruct(aliasTextField,_args[0],_args[1]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasTextArea)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new TextAreaStruct(aliasTextArea));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new TextAreaStruct(aliasTextArea,_args[0]));
                return r_;
            }
            if (_method.getParametersTypes().size() == 2) {
                r_.setResult(new TextAreaStruct(aliasTextArea,_args[0],_args[1]));
                return r_;
            }
            r_.setResult(new TextAreaStruct(aliasTextArea,_args[0],_args[1],_args[2]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasSpinner)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new SpinnerStruct(aliasSpinner,_args[0],_args[1],_args[2],_args[3]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasSlider)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new SliderStruct(aliasSlider));
                return r_;
            }
            if (_method.getParametersTypes().size() == 1) {
                r_.setResult(new SliderStruct(aliasSlider,_args[0]));
                return r_;
            }
            if (_method.getParametersTypes().size() == 2) {
                r_.setResult(new SliderStruct(aliasSlider,_args[0],_args[1]));
                return r_;
            }
            if (_method.getParametersTypes().size() == 3) {
                r_.setResult(new SliderStruct(aliasSlider,_args[0],_args[1],_args[2]));
                return r_;
            }
            r_.setResult(new SliderStruct(aliasSlider,_args[0],_args[1],_args[2],_args[3]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasMenuBar)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new MenuBarStruct());
            return r_;
        }
        if (StringList.quickEq(name_, aliasMenu)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new MenuStruct());
                return r_;
            }
            r_.setResult(new MenuStruct(_args[0]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasMenuItem)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new MenuItemStruct());
                return r_;
            }
            r_.setResult(new MenuItemStruct(_args[0]));
            return r_;
        }
        if (StringList.quickEq(name_, aliasMenuItemCheck)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypes().size() == 0) {
                r_.setResult(new MenuItemCheckStruct());
                return r_;
            }
            r_.setResult(new MenuItemCheckStruct(_args[0]));
            return r_;
        }
        return super.getOtherResult(_cont,_method,_args);
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        if (StringList.quickEq(type_, aliasWindowType)) {
            WindowStruct inst_ = (WindowStruct) _instance;
            if (StringList.quickEq(name_, aliasSetMenuBar)) {
                inst_.setMenuBar(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGetMenuBar)) {
                res_.setResult(inst_.getMenuBar());
                return res_;
            }
            if (StringList.quickEq(name_, aliasPack)) {
                inst_.pack();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasWindowTypeRelative)) {
                inst_.setLocationRelativeTo(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasAddWindowListener)) {
                ((GuiContextEl)_cont).addWindowListener(inst_,_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRemoveWindowListener)) {
                ((GuiContextEl)_cont).removeWindowListener(inst_,_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGetWindowListeners)) {
                WindowListener[] listeners_ = inst_.getWindowListeners();
                int len_ = listeners_.length;
                ArrayStruct arr_ = new ArrayStruct(new Struct[len_],PrimitiveTypeUtil.getPrettyArrayType(aliasWindowListener));
                for (int i = 0; i< len_; i++) {
                    arr_.getInstance()[i] = (Struct)listeners_[i];
                }
                res_.setResult(arr_);
                return res_;
            }
            if (StringList.quickEq(name_, aliasIsVisible)) {
                res_.setResult(new BooleanStruct(inst_.isVisible()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasSetVisible)) {
                inst_.setVisible(((BooleanStruct)_args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_args[0] instanceof PanelStruct) {
                inst_.setContentPane(((PanelStruct)_args[0]).getPanel());
            }
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasConfirm)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasConfirmField)) {
                if (_method.getConstraints().getParametersTypes().size() == 7) {
                    res_.setResult(((GuiContextEl)_cont).showTextField(_args[0],_args[1],_args[2],_args[3],_args[4],_args[5],_args[6]));
                    return res_;
                }
                res_.setResult(((GuiContextEl)_cont).showTextField(_args[0],_args[1],_args[2],_args[3],_args[4],_args[5]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasConfirmFull)) {
                if (_method.getConstraints().getParametersTypes().size() == 7) {
                    res_.setResult(((GuiContextEl)_cont).getAnswer(_args[0],_args[1],_args[2],_args[3],_args[4],_args[5],_args[6]));
                    return res_;
                }
                res_.setResult(((GuiContextEl)_cont).getAnswer(_args[0],_args[1],_args[2],_args[3],_args[4],_args[5]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasConfirmYesNo)) {
                if (_method.getConstraints().getParametersTypes().size() == 6) {
                    res_.setResult(((GuiContextEl)_cont).getAnswerYesNo(_args[0],_args[1],_args[2],_args[3],_args[4],_args[5]));
                    return res_;
                }
                res_.setResult(((GuiContextEl)_cont).getAnswerYesNo(_args[0],_args[1],_args[2],_args[3],_args[4]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasConfirmOk)) {
                if (_method.getConstraints().getParametersTypes().size() == 5) {
                    res_.setResult(((GuiContextEl)_cont).getAnswerOk(_args[0],_args[1],_args[2],_args[3],_args[4]));
                    return res_;
                }
                res_.setResult(((GuiContextEl)_cont).getAnswerOk(_args[0],_args[1],_args[2],_args[3]));
                return res_;
            }
            if (_method.getConstraints().getParametersTypes().size() == 5) {
                ((GuiContextEl)_cont).showMessage(_args[0],_args[1],_args[2],_args[3],_args[4]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            ((GuiContextEl)_cont).showMessage(_args[0],_args[1],_args[2],_args[3]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasFrame)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasWindow)) {
                res_.setResult(((GuiContextEl)_cont).getFrame());
                return res_;
            }
            if (StringList.quickEq(name_, aliasArgs)) {
                StringList mainArgs_ = ((GuiContextEl) _cont).getMainArgs();
                String typeStr_ = getAliasString();
                typeStr_ = PrimitiveTypeUtil.getPrettyArrayType(typeStr_);
                int len_ = mainArgs_.size();
                Struct[] struct_ = new Struct[len_];
                for (int i = 0; i < len_; i++) {
                    struct_[i] = new StringStruct(mainArgs_.get(i));
                }
                res_.setResult(new ArrayStruct(struct_,typeStr_));
                return res_;
            }
            ((FrameStruct) _instance).dispose();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasDialog)) {
            OtherDialog inst_ = ((DialogStruct)_instance).getDialog();
            if (StringList.quickEq(name_, aliasDialogSetModal)) {
                inst_.setModal(((BooleanStruct)_args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new BooleanStruct(inst_.isModal()));
            return res_;
        }
        if (StringList.quickEq(type_, aliasDimension)) {
            DimensionStruct inst_ = (DimensionStruct)_instance;
            if (StringList.quickEq(name_, aliasDimensionGetHeight)) {
                res_.setResult(inst_.getHeight());
                return res_;
            }
            res_.setResult(inst_.getWidth());
            return res_;
        }
        if (StringList.quickEq(type_, aliasComponent)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentInvokeLater)) {
                CustComponentStruct.invokeLater((RunnableContextEl) _cont,_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            CustComponentStruct inst_ = (CustComponentStruct)_instance;
            if (StringList.quickEq(name_, aliasAddKeyListener)) {
                inst_.addKeyListener(_args[0]);
                return res_;
            }
            if (StringList.quickEq(name_, aliasAddWheelListener)) {
                inst_.addWheel(_args[0]);
                return res_;
            }
            if (StringList.quickEq(name_, aliasAddListener)) {
                inst_.addMouse(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompBack)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.setBackground(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getBackground());
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompFore)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.setForeground(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getForeground());
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompFocusable)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.setFocusable(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.isFocusable());
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompOpaque)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.setOpaque(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.isOpaque());
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompToolTip)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.setToolTipText(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getToolTipText());
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompGetFirstPos)) {
                res_.setResult(inst_.getXcoords());
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompGetSecondPos)) {
                res_.setResult(inst_.getYcoords());
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompLoc)) {
                inst_.setLocation(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompBorLine)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.setLineBorder(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                inst_.setLineBorder(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompBorTitle)) {
                inst_.setTitledBorder(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompBorLower)) {
                inst_.setLoweredBorder();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasCompBorRaise)) {
                inst_.setRaisedBorder();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRequestFocus)) {
                inst_.requestFocus();
                return res_;
            }
            if (StringList.quickEq(name_, aliasGetFont)) {
                res_.setResult(inst_.getFont());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSetFont)) {
                inst_.setFont(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGetNextCompo)) {
                res_.setResult(inst_.getNext());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentGetPaint)) {
                res_.setResult(inst_.getPaintEvent());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentSetPaint)) {
                inst_.setPaintEvent(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentGetHeight)) {
                res_.setResult(new IntStruct(inst_.getHeight()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentGetWidth)) {
                res_.setResult(new IntStruct(inst_.getWidth()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentGetPreferredSize)) {
                res_.setResult(inst_.getPreferredSize());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentSetSize)) {
                inst_.setSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentSetPreferredSize)) {
                inst_.setPreferredSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentIsVisible)) {
                res_.setResult(inst_.isVisible());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentSetVisible)) {
                inst_.setVisible(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentIsAutoscrolls)) {
                res_.setResult(inst_.isAutoscrolls());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentSetAutoscrolls)) {
                inst_.setAutoscrolls(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComponentRepaint)) {
                ClassMethodId polymorph_ = new ClassMethodId(aliasPaint,new MethodId(true,aliasPaintMethod,new StringList(aliasComponent)));
                String className_ = polymorph_.getClassName();
                MethodId ct_ = polymorph_.getConstraints();
                Argument arg_ = new Argument(inst_);
                _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_,new CustList<Argument>(arg_),null));
                return res_;
            }
            res_.setResult(inst_.getParentComponent());
            return res_;
        }
        if (StringList.quickEq(type_, aliasPanel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasPanelAbsolute)) {
                res_.setResult(PanelStruct.newAbsolute(aliasPanel));
                return res_;
            }
            if (StringList.quickEq(name_, aliasPanelFlow)) {
                res_.setResult(PanelStruct.newFlow(aliasPanel));
                return res_;
            }
            if (StringList.quickEq(name_, aliasPanelGrid)) {
                res_.setResult(PanelStruct.newGrid(aliasPanel,((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasPanelPageBox)) {
                res_.setResult(PanelStruct.newPageBox(aliasPanel));
                return res_;
            }
            PanelStruct strPan_ = (PanelStruct) _instance;
            if (StringList.quickEq(name_, aliasCount)) {
                res_.setResult(new IntStruct(strPan_.getComponentCount()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasPanelValidate)) {
                strPan_.validate();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRemoveAll)) {
                strPan_.removeAll();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasAddCompo)) {
                if (!(_args[0] instanceof CustComponentStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (_method.getConstraints().getParametersTypes().size() == 2) {
                    strPan_.add((CustComponentStruct)_args[0],((NumberStruct)_args[1]).intStruct());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                strPan_.add((CustComponentStruct)_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGetIndexCompo)) {
                res_.setResult(strPan_.getComponent(((NumberStruct)_args[0]).intStruct()));
                return res_;
            }
            if (StringList.quickEq(getAliasPrimInteger(),_method.getConstraints().getParametersTypes().first())) {
                res_.setResult(strPan_.remove(((NumberStruct)_args[0]).intStruct()));
                return res_;
            }
            res_.setResult(strPan_.remove(_args[0]));
            return res_;
        }
        if (StringList.quickEq(type_, aliasPanelBorder)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (!(_args[0] instanceof CustComponentStruct)) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PanelBorderStruct strPan_ = (PanelBorderStruct) _instance;
            strPan_.add((CustComponentStruct)_args[0],_args[1]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasTabbedPane)) {
            TabbedPaneStruct strPan_ = (TabbedPaneStruct) _instance;
            if (StringList.quickEq(name_, aliasTabbedPaneNb)) {
                res_.setResult(strPan_.getComponentCount());
                return res_;
            }
            if (StringList.quickEq(name_, aliasRemoveAll)) {
                strPan_.removeAll();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTabbedPaneAdd)) {
                if (!(_args[1] instanceof CustComponentStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                strPan_.add(_args[0],(CustComponentStruct)_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTabbedPaneSet)) {
                if (!(_args[1] instanceof CustComponentStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                strPan_.setTab(_args[0],(CustComponentStruct)_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTabbedPaneSetTitle)) {
                strPan_.setTitle(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTabbedPaneGet)) {
                res_.setResult(strPan_.getComponent(_args[0]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasTabbedPaneGetTitle)) {
                res_.setResult(strPan_.getTitle(_args[0]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasTabbedPaneSelIndex)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    strPan_.setSelectedIndex(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(strPan_.getSelectedIndex());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTabbedPaneIndex)) {
                if (!(_args[0] instanceof CustComponentStruct)) {
                    res_.setResult(new IntStruct(-1));
                    return res_;
                }
                res_.setResult(strPan_.index((CustComponentStruct)_args[0]));
                return res_;
            }
            if (StringList.quickEq(getAliasPrimInteger(),_method.getConstraints().getParametersTypes().first())) {
                res_.setResult(strPan_.remove(_args[0]));
                return res_;
            }
            if (!(_args[0] instanceof CustComponentStruct)) {
                res_.setResult(new IntStruct(-1));
                return res_;
            }
            res_.setResult(strPan_.remove((CustComponentStruct) _args[0]));
            return res_;
        }
        if (StringList.quickEq(type_,aliasScrollPane)) {
            ScrollPaneStruct strPan_ = (ScrollPaneStruct) _instance;
            if (StringList.quickEq(name_, aliasScrollPaneHorizontalValue)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    strPan_.setHorizontalValue(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(strPan_.getHorizontalValue());
                return res_;
            }
            if (StringList.quickEq(name_, aliasScrollPaneVerticalValue)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    strPan_.setVerticalValue(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(strPan_.getVerticalValue());
                return res_;
            }
            if (StringList.quickEq(name_, aliasScrollPaneGetView)) {
                res_.setResult(strPan_.getView());
                return res_;
            }
            if (StringList.quickEq(name_, aliasScrollPaneValidate)) {
                strPan_.revalidate();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            strPan_.setViewportView(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_,aliasSplitPane)) {
            SplitPaneStruct strPan_ = (SplitPaneStruct) _instance;
            if (StringList.quickEq(name_, aliasScrollPaneValidate)) {
                strPan_.revalidate();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneIsOneTouchExpandable)) {
                res_.setResult(strPan_.isOneTouchExpandable());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneSetOneTouchExpandable)) {
                strPan_.setOneTouchExpandable(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneIsContinuousLayout)) {
                res_.setResult(strPan_.isContinuousLayout());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneSetContinuousLayout)) {
                strPan_.setContinuousLayout(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneGetLeft)) {
                res_.setResult(strPan_.getLeftComponent());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneSetLeft)) {
                strPan_.setLeftComponent(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneGetRight)) {
                res_.setResult(strPan_.getRightComponent());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneSetRight)) {
                strPan_.setRightComponent(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneGetDividerSize)) {
                res_.setResult(strPan_.getDividerSize());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneSetDividerSize)) {
                strPan_.setDividerSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSplitPaneGetDividerLocation)) {
                res_.setResult(strPan_.getDividerLocation());
                return res_;
            }
            strPan_.setDividerLocation(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasTextLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            TextLabelStruct txt_ = (TextLabelStruct) _instance;
            txt_.setText(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasImageLabel)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PreparedLabelStruct txt_ = (PreparedLabelStruct) _instance;
            txt_.setImage(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasMouseEvent)) {
            MouseEventStruct event_ = (MouseEventStruct)_instance;
            if (StringList.quickEq(name_, aliasMouseEventIsAlt)) {
                res_.setResult(event_.isAlt());
                return res_;
            }
            if (StringList.quickEq(name_, aliasMouseEventIsCtrl)) {
                res_.setResult(event_.isCtrl());
                return res_;
            }
            if (StringList.quickEq(name_, aliasMouseEventIsShift)) {
                res_.setResult(event_.isShift());
                return res_;
            }
            if (StringList.quickEq(name_, aliasMouseEventIsLeft)) {
                res_.setResult(event_.isLeft());
                return res_;
            }
            if (StringList.quickEq(name_, aliasMouseEventIsMiddle)) {
                res_.setResult(event_.isMiddle());
                return res_;
            }
            if (StringList.quickEq(name_, aliasMouseEventIsRight)) {
                res_.setResult(event_.isRight());
                return res_;
            }
            if (StringList.quickEq(name_, aliasMouseEventGetClicks)) {
                res_.setResult(event_.getClicks());
                return res_;
            }
            if (StringList.quickEq(name_, aliasMouseEventGetFirst)) {
                res_.setResult(event_.getFirst());
                return res_;
            }
            res_.setResult(event_.getSecond());
            return res_;
        }
        if (StringList.quickEq(type_, aliasWheelEvent)) {
            MouseWheelEventStruct event_ = (MouseWheelEventStruct)_instance;
            res_.setResult(event_.getRotated());
            return res_;
        }
        if (StringList.quickEq(type_, aliasKeyEvent)) {
            KeyEventStruct event_ = (KeyEventStruct)_instance;
            if (StringList.quickEq(name_, aliasKeyEventIsAlt)) {
                res_.setResult(event_.isAlt());
                return res_;
            }
            if (StringList.quickEq(name_, aliasKeyEventIsCtrl)) {
                res_.setResult(event_.isCtrl());
                return res_;
            }
            if (StringList.quickEq(name_, aliasKeyEventIsShift)) {
                res_.setResult(event_.isShift());
                return res_;
            }
            if (StringList.quickEq(name_, aliasKeyEventChar)) {
                res_.setResult(event_.getKeyChar());
                return res_;
            }
            res_.setResult(event_.getKeyCode());
            return res_;
        }
        if (StringList.quickEq(type_, aliasInput)) {
            InputStruct in_ = (InputStruct) _instance;
            if (StringList.quickEq(name_, aliasInputSetEnabled)) {
                in_.setEnabled(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(in_.isEnabled());
            return res_;
        }
        if (StringList.quickEq(type_, aliasProgBar)) {
            ProgressBarStruct inst_ = (ProgressBarStruct) _instance;
            if (StringList.quickEq(name_, aliasProgBarMin)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.setMinimum(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getMinimum());
                return res_;
            }
            if (StringList.quickEq(name_, aliasProgBarMax)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.setMaximum(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getMaximum());
                return res_;
            }
            if (StringList.quickEq(name_, aliasProgBarValue)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.setValue(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getValue());
                return res_;
            }
            if (_method.getConstraints().getParametersTypes().size() == 1) {
                inst_.setHorizontal(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.isHorizontal());
            return res_;
        }
        if (StringList.quickEq(type_, aliasButton)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PlainButtonStruct pl_ = (PlainButtonStruct) _instance;
            pl_.addActionListener(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasFont)) {
            if (_method.getConstraints().isStaticMethod()) {
                Struct first_ = _args[0];
                if (!(first_ instanceof ImageStruct)) {
                    res_.setResult(new IntStruct(-1));
                    return res_;
                }
                Struct font_ = ((ImageStruct) first_).getFont();
                if (!(font_ instanceof FontStruct)) {
                    res_.setResult(new IntStruct(-1));
                    return res_;
                }
                FontStruct f_ = (FontStruct) font_;
                res_.setResult(((GuiContextEl)_cont).stringWidth(f_,_args[1]));
                return res_;
            }
            FontStruct f_ = (FontStruct) _instance;
            if (StringList.quickEq(name_, aliasFontGetName)) {
                res_.setResult(f_.getName());
                return res_;
            }
            if (StringList.quickEq(name_, aliasFontGetSize)) {
                res_.setResult(f_.getSize());
                return res_;
            }
            if (StringList.quickEq(name_, aliasFontIsBold)) {
                res_.setResult(f_.isBold());
                return res_;
            }
            if (StringList.quickEq(name_, aliasFontIsItalic)) {
                res_.setResult(f_.isItalic());
                return res_;
            }
            res_.setResult(((GuiContextEl)_cont).stringWidth(f_,_args[0]));
            return res_;
        }
        if (StringList.quickEq(type_, aliasColor)) {
            ColorStruct c_ = (ColorStruct) _instance;
            if (StringList.quickEq(name_, aliasColorAlpha)) {
                res_.setResult(c_.getAlpha());
                return res_;
            }
            if (StringList.quickEq(name_, aliasColorRed)) {
                res_.setResult(c_.getRed());
                return res_;
            }
            if (StringList.quickEq(name_, aliasColorGreen)) {
                res_.setResult(c_.getGreen());
                return res_;
            }
            if (StringList.quickEq(name_, aliasColorBlue)) {
                res_.setResult(c_.getBlue());
                return res_;
            }
            res_.setResult(c_.isTransparent());
            return res_;
        }
        if (StringList.quickEq(type_,aliasImage)) {
            ImageStruct image_ = (ImageStruct) _instance;
            if (StringList.quickEq(name_, aliasImageGetHeight)) {
                res_.setResult(image_.getHeight());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageGetWidth)) {
                res_.setResult(image_.getWidth());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageSet)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.setPixel(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageGet)) {
                res_.setResult(image_.getPixel(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct()));
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageIsWithAlpha)) {
                res_.setResult(image_.isWithAlpha());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageGetColor)) {
                res_.setResult(image_.getColor());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageSetColor)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.setColor(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageGetFont)) {
                res_.setResult(image_.getFont());
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageSetFont)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.setFont(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDispose)) {
                image_.dispose();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDraw)) {
                if (StringList.quickEq(getAliasImage(),_method.getConstraints().getParametersTypes().first())) {
                    image_.drawImage(_args[0],((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.drawString(_args[0],((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDrawLine)) {
                image_.drawLine(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDrawRect)) {
                image_.drawRect(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDrawOval)) {
                image_.drawOval(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageDrawPolygon)) {
                image_.drawPolygon(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageFillRect)) {
                image_.fillRect(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasImageFillOval)) {
                image_.fillOval(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            image_.fillPolygon(_args[0],_args[1]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasTreeNode)) {
            TreeNodeStruct inst_ = (TreeNodeStruct) _instance;
            if (StringList.quickEq(name_, aliasTreeNodeAdd)) {
                inst_.add(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeInsert)) {
                inst_.insert(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeRemove)) {
                if (StringList.quickEq(getAliasPrimInteger(),_method.getConstraints().getParametersTypes().first())) {
                    inst_.remove(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                inst_.removeNode(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeRemoveFromParent)) {
                inst_.removeFromParent();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeRemoveAllChildren)) {
                inst_.removeAllChildren();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeSetUserObject)) {
                inst_.setUserObject(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeGetUserObject)) {
                res_.setResult(inst_.getUserObject());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeNb)) {
                res_.setResult(inst_.getChildCount());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeGetFirstChild)) {
                res_.setResult(inst_.getFirstChild());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeGetLastChild)) {
                res_.setResult(inst_.getLastChild());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeGetNextSibling)) {
                res_.setResult(inst_.getNextSibling());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeGetPreviousSibling)) {
                res_.setResult(inst_.getPreviousSibling());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeGetParentNode)) {
                res_.setResult(inst_.getParentNode());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeNodeIsAncestor)) {
                res_.setResult(inst_.isAncestorMethod(_args[0]));
                return res_;
            }
            res_.setResult(inst_.isDescendantMethod(_args[0]));
            return res_;
        }
        if (StringList.quickEq(type_, aliasTree)) {
            TreeStruct inst_ = (TreeStruct) _instance;
            if (StringList.quickEq(name_, aliasTreeAddTreeListener)) {
                inst_.addTreeSelectionListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeGetSelected)) {
                if (_method.getConstraints().getParametersTypes().size() == 1) {
                    inst_.select(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getLastSelectedPathComponent());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeIsRootVisible)) {
                res_.setResult(inst_.isRootVisible());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTreeSetRootVisible)) {
                inst_.setRootVisible(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            inst_.reload();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasTableGui)) {
            TableStruct inst_ = (TableStruct) _instance;
            if (StringList.quickEq(name_, aliasTableAddSelect)) {
                inst_.addListSelectionListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableAddHeader)) {
                inst_.addHeaderListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableMoveColumn)) {
                inst_.moveColumn(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableAddInterval)) {
                inst_.addSelectInterval(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableRemoveInterval)) {
                inst_.removeSelectInterval(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableApplyChanges)) {
                inst_.applyChanges();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableGetColumnAtPoint)) {
                res_.setResult(inst_.columnAtPoint(_args[0],_args[1]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableGetRowAtPoint)) {
                res_.setResult(inst_.rowAtPoint(_args[0],_args[1]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableGetColumnCount)) {
                res_.setResult(inst_.getColumnCount());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableGetRowCount)) {
                res_.setResult(inst_.getRowCount());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableGetColumnName)) {
                res_.setResult(inst_.getColumnName(_args[0]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableGetSelectedRow)) {
                res_.setResult(inst_.getSelectedRow());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableGetSelectedRows)) {
                res_.setResult(inst_.getSelectedRows(_cont));
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableGetSelectedRowCount)) {
                res_.setResult(inst_.getSelectedRowCount());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableGetValue)) {
                res_.setResult(inst_.getValueAt(_args[0],_args[1]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableSetValue)) {
                inst_.setValueAt(_args[0],_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableSetColumns)) {
                inst_.setColumnIdentifiers(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableSetMultiple)) {
                inst_.setMultiSelect(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableSetReorder)) {
                inst_.setReorderingAllowed(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableSetRowCount)) {
                inst_.setRowCount(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTableIsMultiple)) {
                res_.setResult(inst_.isMultiSelect());
                return res_;
            }
            res_.setResult(inst_.isReorderingAllowed());
            return res_;
        }
        if (StringList.quickEq(type_, aliasRender)) {
            RenderStruct image_ = (RenderStruct) _instance;
            if (StringList.quickEq(name_, aliasRenderSetPaint)) {
                image_.setPaint(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRenderSetWidth)) {
                image_.setWidth(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRenderSetHeight)) {
                image_.setHeight(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRenderGetPaint)) {
                res_.setResult(image_.getPaint());
                return res_;
            }
            if (StringList.quickEq(name_, aliasRenderGetHeight)) {
                res_.setResult(image_.getHeight());
                return res_;
            }
            res_.setResult(image_.getWidth());
            return res_;
        }
        if (StringList.quickEq(type_, aliasGrList)) {
            GraphicListStruct inst_ = (GraphicListStruct) _instance;
            if (StringList.quickEq(name_, aliasGrListSetSelection)) {
                inst_.setListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetSelection)) {
                res_.setResult(inst_.getListener());
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListSetVisibleRowCount)) {
                inst_.setVisibleRowCount(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetVisibleRowCount)) {
                res_.setResult(inst_.getVisibleRowCount());
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListSetRender)) {
                inst_.setRender(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetRender)) {
                res_.setResult(inst_.getRender());
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetListView)) {
                res_.setResult(inst_.getListView(_cont));
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListGetSelectedIndexes)) {
                res_.setResult(inst_.getSelectedIndexes(_cont));
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListRemove)){
                inst_.remove(((NumberStruct)_args[0]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListClear)){
                inst_.clear();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListSetSelectedIndexes)) {
                inst_.setSelectedIndexes(_args[0]);
                if (_args[0] instanceof ArrayStruct) {
                    StringList params_ = new StringList(aliasGrList);
                    ClassMethodId polymorph_ = new ClassMethodId(aliasPaint,new MethodId(true,aliasPaintRefresh, params_));
                    String className_ = polymorph_.getClassName();
                    MethodId ct_ = polymorph_.getConstraints();
                    Argument arg_ = new Argument(inst_);
                    CustList<Argument> args_ = new CustList<Argument>(arg_);
                    _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_, args_,null));
                }
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListClearSelection)) {
                inst_.clearSelection();
                StringList params_ = new StringList(aliasGrList);
                ClassMethodId polymorph_ = new ClassMethodId(aliasPaint,new MethodId(true,aliasPaintRefresh, params_));
                String className_ = polymorph_.getClassName();
                MethodId ct_ = polymorph_.getConstraints();
                Argument arg_ = new Argument(inst_);
                CustList<Argument> args_ = new CustList<Argument>(arg_);
                _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_, args_,null));
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListUpdateGraphics)) {
                inst_.updateGraphics();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasGrListSet)) {
                inst_.set(_cont,((NumberStruct)_args[0]).intStruct(),_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_method.getConstraints().getParametersTypes().size() == 3) {
                inst_.add(_cont,((NumberStruct)_args[0]).intStruct(),_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            StringList params_ = new StringList(aliasGrList,getAliasPrimInteger(),getAliasObject());
            ClassMethodId polymorph_ = new ClassMethodId(aliasPaint,new MethodId(true,aliasPaintAdd, params_));
            String className_ = polymorph_.getClassName();
            MethodId ct_ = polymorph_.getConstraints();
            Argument arg_ = new Argument(inst_);
            CustList<Argument> args_ = new CustList<Argument>(arg_);
            args_.add(new Argument(_args[0]));
            args_.add(new Argument(_args[1]));
            _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_, args_,null));
            return res_;
        }
        if (StringList.quickEq(type_, aliasCombo)) {
            GraphicComboStruct inst_ = (GraphicComboStruct) _instance;
            if (StringList.quickEq(name_, aliasComboSetListener)) {
                inst_.setListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboGetListener)) {
                res_.setResult(inst_.getListener());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboGetSelectedIndexes)) {
                res_.setResult(inst_.getSelectedIndexes(_cont));
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboGetSelectedIndex)) {
                res_.setResult(inst_.getSelectedIndex());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboGetSelectedItem)) {
                res_.setResult(inst_.getSelectedItem());
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboAddItem)) {
                inst_.addItem(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboSelectItem)) {
                inst_.selectItem((RunnableContextEl) _cont,(NumberStruct) _args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboRemoveItem)) {
                inst_.removeItem(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasComboRemoveAllItems)) {
                inst_.removeAllItems();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getItemCount());
            return res_;
        }
        if (StringList.quickEq(type_, aliasRadio)) {
            RadioButtonStruct inst_ = (RadioButtonStruct) _instance;
            if (StringList.quickEq(name_, aliasAddChange)) {
                inst_.addChangeListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRadioSetSelected)) {
                inst_.setSelected(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasRadioIsSelected)) {
                res_.setResult(inst_.isSelected());
                return res_;
            }
            if (StringList.quickEq(name_, aliasRadioSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringList.quickEq(type_, aliasCheckBox)) {
            CheckBoxStruct inst_ = (CheckBoxStruct) _instance;
            if (StringList.quickEq(name_, aliasCheckBoxSetSelected)) {
                inst_.setSelected(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasCheckBoxIsSelected)) {
                res_.setResult(inst_.isSelected());
                return res_;
            }
            if (StringList.quickEq(name_, aliasCheckBoxSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringList.quickEq(type_, aliasPopupMenu)) {
            PopupStruct inst_ = (PopupStruct) _instance;
            if (StringList.quickEq(name_, aliasPopupMenuAdd)) {
                inst_.add(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasPopupMenuGetComp)) {
                res_.setResult(inst_.getCompo(_args[0]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasPopupMenuRemoveComp)) {
                inst_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasPopupMenuNbComp)) {
                res_.setResult(inst_.getCompoCount());
                return res_;
            }
            if (StringList.quickEq(name_, aliasPopupMenuAddMenu)) {
                inst_.addMenu(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasPopupMenuGetMenu)) {
                res_.setResult(inst_.getMenu(_args[0]));
                return res_;
            }
            if (StringList.quickEq(name_, aliasPopupMenuRemoveMenu)) {
                inst_.removeMenu(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasPopupMenuNbMenu)) {
                res_.setResult(inst_.getMenuCount());
                return res_;
            }
            inst_.show(_args[0],_args[1],_args[2]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasTextField)) {
            TextFieldStruct inst_ = (TextFieldStruct) _instance;
            if (StringList.quickEq(name_, aliasTextFieldSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextFieldAddAction)) {
                inst_.addActionListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringList.quickEq(type_, aliasTextArea)) {
            TextAreaStruct inst_ = (TextAreaStruct) _instance;
            if (StringList.quickEq(name_, aliasTextAreaAppend)) {
                inst_.append(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaInsert)) {
                inst_.insert(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaReplaceRange)) {
                inst_.replaceRange(_args[0],_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaReplaceSelection)) {
                inst_.replaceSelection(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSelect)) {
                inst_.select(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSelectAll)) {
                inst_.selectAll();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaGetSelectedText)) {
                res_.setResult(inst_.getSelectedText());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSetSelectionStart)) {
                inst_.setSelectionStart(_args[0]);
                res_.setResult(inst_.getSelectedText());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSetSelectionEnd)) {
                inst_.setSelectionEnd(_args[0]);
                res_.setResult(inst_.getSelectedText());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSetTabSize)) {
                inst_.setTabSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaGetTabSize)) {
                res_.setResult(inst_.getTabSize());
                return res_;
            }
            if (StringList.quickEq(name_, aliasTextAreaSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringList.quickEq(type_, aliasSpinner)) {
            SpinnerStruct inst_ = (SpinnerStruct) _instance;
            if (StringList.quickEq(name_, aliasAddChange)) {
                inst_.addChangeListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSpinnerSetRange)) {
                inst_.setRange(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSpinnerSetRangeValue)) {
                inst_.setRangeValue(_args[0],_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSpinnerGetMin)) {
                res_.setResult(inst_.getMin());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSpinnerSetMin)) {
                inst_.setMin(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSpinnerGetMax)) {
                res_.setResult(inst_.getMax());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSpinnerSetMax)) {
                inst_.setMax(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSpinnerGetValue)) {
                res_.setResult(inst_.getValue());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSpinnerSetValue)) {
                inst_.setValue(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSpinnerGetStep)) {
                res_.setResult(inst_.getStep());
                return res_;
            }
            inst_.setStep(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasSlider)) {
            SliderStruct inst_ = (SliderStruct) _instance;
            if (StringList.quickEq(name_, aliasAddChange)) {
                inst_.addChangeListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSliderGetMin)) {
                res_.setResult(inst_.getMin());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSliderSetMin)) {
                inst_.setMin(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSliderGetMax)) {
                res_.setResult(inst_.getMax());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSliderSetMax)) {
                inst_.setMax(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSliderGetValue)) {
                res_.setResult(inst_.getValue());
                return res_;
            }
            if (StringList.quickEq(name_, aliasSliderSetValue)) {
                inst_.setValue(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasSliderGetOrientation)) {
                res_.setResult(inst_.getOrientation());
                return res_;
            }
            inst_.setOrientation(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasMenuBar)) {
            MenuBarStruct inst_ = (MenuBarStruct)_instance;
            if (StringList.quickEq(name_, aliasMenuBarAdd)) {
                inst_.add(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasMenuBarRemove)) {
                inst_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasMenuBarGet)) {
                res_.setResult(inst_.getMenu(_args[0]));
                return res_;
            }
            res_.setResult(inst_.getMenuCount());
            return res_;
        }
        if (StringList.quickEq(type_, aliasAbsMenu)) {
            AbsMenuStruct inst_ = (AbsMenuStruct)_instance;
            if (StringList.quickEq(name_, aliasAbsMenuGetParent)) {
                res_.setResult(inst_.getParentMenu());
                return res_;
            }
            if (StringList.quickEq(name_, aliasAbsMenuGetText)) {
                res_.setResult(inst_.getText());
                return res_;
            }
            if (StringList.quickEq(name_, aliasAbsMenuSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasAbsMenuIsEnabled)) {
                res_.setResult(inst_.isEnabled());
                return res_;
            }
            if (StringList.quickEq(name_, aliasAbsMenuSetEnabled)) {
                inst_.setEnabled(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            inst_.setDeepEnabled(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasMenu)) {
            MenuStruct inst_ = (MenuStruct) _instance;
            if (StringList.quickEq(name_, aliasMenuAdd)) {
                inst_.add(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasMenuRemove)) {
                inst_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasMenuAddSeparator)) {
                inst_.addSeparator();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_, aliasMenuGet)) {
                res_.setResult(inst_.getMenu(_args[0]));
                return res_;
            }
            res_.setResult(inst_.getMenuCount());
            return res_;
        }
        if (StringList.quickEq(type_, aliasAbsMenuItem)) {
            AbsMenuItemStruct inst_ = (AbsMenuItemStruct) _instance;
            inst_.addActionListener(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(type_, aliasMenuItemCheck)) {
            MenuItemCheckStruct inst_ = (MenuItemCheckStruct) _instance;
            if (StringList.quickEq(name_, aliasMenuItemCheckIsSelected)) {
                res_.setResult(inst_.isSelected());
                return res_;
            }
            inst_.setSelected(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return super.getOtherResult(_cont,_instance,_method,_args);
    }
    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> stds_ = super.buildFiles(_context);
        String content_ = ResourceFiles.ressourceFichier("resources_lg_gui/action_event.txt");
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String class_ = keyWords_.getKeyWordClass();
        String abstract_ = keyWords_.getKeyWordAbstract();
        String static_ = keyWords_.getKeyWordStatic();
        String if_ = keyWords_.getKeyWordIf();
        String elseif_ = keyWords_.getKeyWordElseif();
        String while_ = keyWords_.getKeyWordWhile();
        String final_ = keyWords_.getKeyWordFinal();
        String return_ = keyWords_.getKeyWordReturn();
        String continue_ = keyWords_.getKeyWordContinue();
        String break_ = keyWords_.getKeyWordBreak();
        String for_ = keyWords_.getKeyWordFor();
        String new_ = keyWords_.getKeyWordNew();
        String null_ = keyWords_.getKeyWordNull();
        String cast_ = keyWords_.getKeyWordCast();
        String true_ = keyWords_.getKeyWordTrue();
        String false_ = keyWords_.getKeyWordFalse();
        String is_ = keyWords_.getKeyWordInstanceof();
        String endLine_ = String.valueOf(_context.getOptions().getEndLine());
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{ActionListener}", aliasActionListener);
        map_.put("{actionPerformed}", aliasActionPerformed);
        map_.put("{ActionEvent}", aliasActionEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasActionListener);
        stds_.put(aliasActionListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasActionListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/change_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{ChangeListener}", aliasChangeListener);
        map_.put("{stateChanged}", aliasStateChanged);
        map_.put("{void}", getAliasVoid());
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasChangeListener);
        stds_.put(aliasChangeListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasChangeListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/tree_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{TreeListener}", aliasTreeListener);
        map_.put("{valueChanged}", aliasTreeListenerValueChanged);
        map_.put("{TreeNode}", aliasTreeNode);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasTreeListener);
        stds_.put(aliasTreeListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasTreeListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/table_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{TableListener}", aliasTableListener);
        map_.put("{valueChanged}", aliasTableValueTableChanged);
        map_.put("{int}", getAliasPrimInteger());
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{f}", tr("f",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasTableListener);
        stds_.put(aliasTableListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasTableListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/mouse_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{MouseListener}", aliasMouseListener);
        map_.put("{mouseClicked}", aliasMouseClicked);
        map_.put("{mousePressed}", aliasMousePressed);
        map_.put("{mouseReleased}", aliasMouseReleased);
        map_.put("{mouseEntered}", aliasMouseEntered);
        map_.put("{mouseExited}", aliasMouseExited);
        map_.put("{mouseDragged}", aliasMouseDragged);
        map_.put("{mouseMoved}", aliasMouseMoved);
        map_.put("{MouseEvent}", aliasMouseEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasMouseListener);
        stds_.put(aliasMouseListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasMouseListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/wheel_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{WheelListener}", aliasWheelListener);
        map_.put("{mouseMoveWheel}", aliasWheelMove);
        map_.put("{MouseWheelEvent}", aliasWheelEvent);
        map_.put("{e}", tr("e",_context));
        map_.put("{void}", getAliasVoid());
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasWheelListener);
        stds_.put(aliasWheelListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasWheelListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/key_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{KeyListener}", aliasKeyListener);
        map_.put("{keyPressed}", aliasKeyPressed);
        map_.put("{keyTyped}", aliasKeyTyped);
        map_.put("{keyReleased}", aliasKeyReleased);
        map_.put("{KeyEvent}", aliasKeyEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasKeyListener);
        stds_.put(aliasKeyListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasKeyListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/window_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{WindowListener}", aliasWindowListener);
        map_.put("{windowOpened}", aliasWindowOpened);
        map_.put("{windowClosing}", aliasWindowClosing);
        map_.put("{windowClosed}", aliasWindowClosed);
        map_.put("{windowIconified}", aliasWindowIconified);
        map_.put("{windowDeiconified}", aliasWindowDeiconified);
        map_.put("{windowActivated}", aliasWindowActivated);
        map_.put("{windowDeactivated}", aliasWindowDeactivated);
        map_.put("{WindowEvent}", aliasWindowEvent);
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasWindowListener);
        stds_.put(aliasWindowListener, content_);
        getPredefinedInterfacesInitOrder().add(aliasWindowListener);
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/list_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{ListSelection}", aliasListSelection);
        map_.put("{valueChanged}", aliasValueChanged);
        map_.put("{int}", getAliasPrimInteger());
        map_.put("{void}", getAliasVoid());
        map_.put("{e}", tr("e",_context));
        map_.put("{f}", tr("f",_context));
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasListSelection);
        stds_.put(aliasListSelection, content_);
        getPredefinedInterfacesInitOrder().add(aliasListSelection);
        String suffix_ = String.valueOf(_context.getOptions().getSuffix());
        String suffixLocal_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixLocal_ = StringList.concat(suffix_,".");
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixLocal_ = suffix_;
        }
        String suffixParam_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixParam_ = StringList.concat(suffix_,".",suffix_);
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixParam_ = suffix_;
        }
        String suffixLoop_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixLoop_ = suffix_;
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixLoop_ = suffix_;
        }
        content_ = ResourceFiles.ressourceFichier("resources_lg_gui/repaint.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{abstract}", abstract_);
        map_.put("{final}", final_);
        map_.put("{class}", class_);
        map_.put("{Paint}", aliasPaint);
        map_.put("{paint}", aliasPaintMethod);
        map_.put("{add}", aliasPaintAdd);
        map_.put("{refresh}", aliasPaintRefresh);
        map_.put("{static}", static_);
        map_.put("{if}", if_);
        map_.put("{elseif}", elseif_);
        map_.put("{true}", true_);
        map_.put("{false}", false_);
        map_.put("{return}", return_);
        map_.put("{break}", break_);
        map_.put("{continue}", continue_);
        map_.put("{is}", is_);
        map_.put("{while}", while_);
        map_.put("{null}", null_);
        map_.put("{cast}", cast_);
        map_.put("{param}", suffixParam_);
        map_.put("{local}", suffixLocal_);
        map_.put("{loop}", suffixLoop_);
        map_.put("{Component}", aliasComponent);
        map_.put("{Panel}", aliasPanel);
        map_.put("{Fct}", getAliasFct());
        map_.put("{void}", getAliasVoid());
        map_.put("{call}", getAliasCall());
        map_.put("{getComponent}", aliasGetIndexCompo);
        map_.put("{next}", aliasGetNextCompo);
        map_.put("{getParent}", aliasGetParentCompo);
        map_.put("{getPainting}", aliasComponentGetPaint);
        map_.put("{e}", tr("e",_context));
        map_.put("{c}", tr("c",_context));
        map_.put("{p}", tr("p",_context));
        map_.put("{r}", tr("r",_context));
        map_.put("{o}", tr("o",_context));
        map_.put("{i}", tr("i",_context));
        map_.put("{l}", tr("l",_context));
        map_.put("{la}", tr("la",_context));
        map_.put("{h}", tr("h",_context));
        map_.put("{hf}", tr("hf",_context));
        map_.put("{lf}", tr("lf",_context));
        map_.put("{pf}", tr("pf",_context));
        map_.put("{par}", tr("par",_context));
        map_.put("{pan}", tr("pan",_context));
        map_.put("{fct}", tr("fct",_context));
        map_.put("{nb}", tr("nb",_context));
        map_.put("{img}", tr("img",_context));
        map_.put("{endLine}", endLine_);
        map_.put("{suffix}", suffix_);
        map_.put("{getRender}",aliasGrListGetRender);
        map_.put("{getHeight}",aliasRenderGetHeight);
        map_.put("{getWidth}",aliasRenderGetWidth);
        map_.put("{getPaint}",aliasRenderGetPaint);
        map_.put("{Image}",aliasImage);
        map_.put("{ImageLabel}",aliasImageLabel);
        map_.put("{addCompo}",aliasGrListAdd);
        map_.put("{setCompo}",aliasGrListSet);
        map_.put("{getView}",aliasGrListGetListView);
        map_.put("{getSelected}",aliasGrListGetSelectedIndexes);
        map_.put("{updateGraphics}",aliasGrListUpdateGraphics);
        map_.put("{length}",getAliasLength());
        map_.put("{for}",for_);
        map_.put("{new}",new_);
        map_.put("{var}",keyWords_.getKeyWordVar());
        map_.put("{int}",getAliasPrimInteger());
        map_.put("{GrList}",aliasGrList);
        map_.put("{Object}",getAliasObject());
        map_.put("{dispose}",aliasImageDispose);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasPaint);
        stds_.put(aliasPaint, content_);
        getPredefinedInterfacesInitOrder().add(aliasPaint);
        //Paint
        //paint
        return stds_;
    }

    public String getAliasActionListener() {
        return aliasActionListener;
    }

    public void setAliasActionListener(String aliasActionListener) {
        this.aliasActionListener = aliasActionListener;
    }

    public String getAliasActionPerformed() {
        return aliasActionPerformed;
    }

    public void setAliasActionPerformed(String aliasActionPerformed) {
        this.aliasActionPerformed = aliasActionPerformed;
    }

    public String getAliasActionEvent() {
        return aliasActionEvent;
    }

    public void setAliasActionEvent(String aliasActionEvent) {
        this.aliasActionEvent = aliasActionEvent;
    }

    public String getAliasAddChange() {
        return aliasAddChange;
    }

    public void setAliasAddChange(String aliasAddChange) {
        this.aliasAddChange = aliasAddChange;
    }

    public String getAliasChangeListener() {
        return aliasChangeListener;
    }

    public void setAliasChangeListener(String aliasChangeListener) {
        this.aliasChangeListener = aliasChangeListener;
    }

    public String getAliasStateChanged() {
        return aliasStateChanged;
    }

    public void setAliasStateChanged(String aliasStateChanged) {
        this.aliasStateChanged = aliasStateChanged;
    }

    public String getAliasTreeListener() {
        return aliasTreeListener;
    }

    public void setAliasTreeListener(String aliasTreeListener) {
        this.aliasTreeListener = aliasTreeListener;
    }

    public String getAliasTreeListenerValueChanged() {
        return aliasTreeListenerValueChanged;
    }

    public void setAliasTreeListenerValueChanged(String aliasTreeListenerValueChanged) {
        this.aliasTreeListenerValueChanged = aliasTreeListenerValueChanged;
    }

    public String getAliasTreeNode() {
        return aliasTreeNode;
    }

    public void setAliasTreeNode(String aliasTreeNode) {
        this.aliasTreeNode = aliasTreeNode;
    }

    public String getAliasTreeNodeAdd() {
        return aliasTreeNodeAdd;
    }

    public void setAliasTreeNodeAdd(String aliasTreeNodeAdd) {
        this.aliasTreeNodeAdd = aliasTreeNodeAdd;
    }

    public String getAliasTreeNodeInsert() {
        return aliasTreeNodeInsert;
    }

    public void setAliasTreeNodeInsert(String aliasTreeNodeInsert) {
        this.aliasTreeNodeInsert = aliasTreeNodeInsert;
    }

    public String getAliasTreeNodeRemove() {
        return aliasTreeNodeRemove;
    }

    public void setAliasTreeNodeRemove(String aliasTreeNodeRemove) {
        this.aliasTreeNodeRemove = aliasTreeNodeRemove;
    }

    public String getAliasTreeNodeRemoveFromParent() {
        return aliasTreeNodeRemoveFromParent;
    }

    public void setAliasTreeNodeRemoveFromParent(String aliasTreeNodeRemoveFromParent) {
        this.aliasTreeNodeRemoveFromParent = aliasTreeNodeRemoveFromParent;
    }

    public String getAliasTreeNodeRemoveAllChildren() {
        return aliasTreeNodeRemoveAllChildren;
    }

    public void setAliasTreeNodeRemoveAllChildren(String aliasTreeNodeRemoveAllChildren) {
        this.aliasTreeNodeRemoveAllChildren = aliasTreeNodeRemoveAllChildren;
    }

    public String getAliasTreeNodeSetUserObject() {
        return aliasTreeNodeSetUserObject;
    }

    public void setAliasTreeNodeSetUserObject(String aliasTreeNodeSetUserObject) {
        this.aliasTreeNodeSetUserObject = aliasTreeNodeSetUserObject;
    }

    public String getAliasTreeNodeNb() {
        return aliasTreeNodeNb;
    }

    public void setAliasTreeNodeNb(String aliasTreeNodeNb) {
        this.aliasTreeNodeNb = aliasTreeNodeNb;
    }

    public String getAliasTreeNodeGetFirstChild() {
        return aliasTreeNodeGetFirstChild;
    }

    public void setAliasTreeNodeGetFirstChild(String aliasTreeNodeGetFirstChild) {
        this.aliasTreeNodeGetFirstChild = aliasTreeNodeGetFirstChild;
    }

    public String getAliasTreeNodeGetLastChild() {
        return aliasTreeNodeGetLastChild;
    }

    public void setAliasTreeNodeGetLastChild(String aliasTreeNodeGetLastChild) {
        this.aliasTreeNodeGetLastChild = aliasTreeNodeGetLastChild;
    }

    public String getAliasTreeNodeGetNextSibling() {
        return aliasTreeNodeGetNextSibling;
    }

    public void setAliasTreeNodeGetNextSibling(String aliasTreeNodeGetNextSibling) {
        this.aliasTreeNodeGetNextSibling = aliasTreeNodeGetNextSibling;
    }

    public String getAliasTreeNodeGetPreviousSibling() {
        return aliasTreeNodeGetPreviousSibling;
    }

    public void setAliasTreeNodeGetPreviousSibling(String aliasTreeNodeGetPreviousSibling) {
        this.aliasTreeNodeGetPreviousSibling = aliasTreeNodeGetPreviousSibling;
    }

    public String getAliasTreeNodeGetParentNode() {
        return aliasTreeNodeGetParentNode;
    }

    public void setAliasTreeNodeGetParentNode(String aliasTreeNodeGetParentNode) {
        this.aliasTreeNodeGetParentNode = aliasTreeNodeGetParentNode;
    }

    public String getAliasTreeNodeGetUserObject() {
        return aliasTreeNodeGetUserObject;
    }

    public void setAliasTreeNodeGetUserObject(String aliasTreeNodeGetUserObject) {
        this.aliasTreeNodeGetUserObject = aliasTreeNodeGetUserObject;
    }

    public String getAliasTreeNodeIsAncestor() {
        return aliasTreeNodeIsAncestor;
    }

    public void setAliasTreeNodeIsAncestor(String aliasTreeNodeIsAncestor) {
        this.aliasTreeNodeIsAncestor = aliasTreeNodeIsAncestor;
    }

    public String getAliasTreeNodeIsDescendant() {
        return aliasTreeNodeIsDescendant;
    }

    public void setAliasTreeNodeIsDescendant(String aliasTreeNodeIsDescendant) {
        this.aliasTreeNodeIsDescendant = aliasTreeNodeIsDescendant;
    }

    public String getAliasTree() {
        return aliasTree;
    }

    public void setAliasTree(String aliasTree) {
        this.aliasTree = aliasTree;
    }

    public String getAliasTreeSetRootVisible() {
        return aliasTreeSetRootVisible;
    }

    public void setAliasTreeSetRootVisible(String aliasTreeSetRootVisible) {
        this.aliasTreeSetRootVisible = aliasTreeSetRootVisible;
    }

    public String getAliasTreeIsRootVisible() {
        return aliasTreeIsRootVisible;
    }

    public void setAliasTreeIsRootVisible(String aliasTreeIsRootVisible) {
        this.aliasTreeIsRootVisible = aliasTreeIsRootVisible;
    }

    public String getAliasTreeGetSelected() {
        return aliasTreeGetSelected;
    }

    public void setAliasTreeGetSelected(String aliasTreeGetSelected) {
        this.aliasTreeGetSelected = aliasTreeGetSelected;
    }

    public String getAliasTreeAddTreeListener() {
        return aliasTreeAddTreeListener;
    }

    public void setAliasTreeAddTreeListener(String aliasTreeAddTreeListener) {
        this.aliasTreeAddTreeListener = aliasTreeAddTreeListener;
    }

    public String getAliasTreeReload() {
        return aliasTreeReload;
    }

    public void setAliasTreeReload(String aliasTreeReload) {
        this.aliasTreeReload = aliasTreeReload;
    }

    public String getAliasTableListener() {
        return aliasTableListener;
    }

    public void setAliasTableListener(String aliasTableListener) {
        this.aliasTableListener = aliasTableListener;
    }

    public String getAliasTableValueTableChanged() {
        return aliasTableValueTableChanged;
    }

    public void setAliasTableValueTableChanged(String aliasTableValueTableChanged) {
        this.aliasTableValueTableChanged = aliasTableValueTableChanged;
    }

    public String getAliasTableGui() {
        return aliasTableGui;
    }

    public void setAliasTableGui(String aliasTable) {
        this.aliasTableGui = aliasTable;
    }

    public String getAliasTableGetSelectedRow() {
        return aliasTableGetSelectedRow;
    }

    public void setAliasTableGetSelectedRow(String aliasTableGetSelectedRow) {
        this.aliasTableGetSelectedRow = aliasTableGetSelectedRow;
    }

    public String getAliasTableGetSelectedRows() {
        return aliasTableGetSelectedRows;
    }

    public void setAliasTableGetSelectedRows(String aliasTableGetSelectedRows) {
        this.aliasTableGetSelectedRows = aliasTableGetSelectedRows;
    }

    public String getAliasTableGetSelectedRowCount() {
        return aliasTableGetSelectedRowCount;
    }

    public void setAliasTableGetSelectedRowCount(String aliasTableGetSelectedRowCount) {
        this.aliasTableGetSelectedRowCount = aliasTableGetSelectedRowCount;
    }

    public String getAliasTableGetRowCount() {
        return aliasTableGetRowCount;
    }

    public void setAliasTableGetRowCount(String aliasTableGetRowCount) {
        this.aliasTableGetRowCount = aliasTableGetRowCount;
    }

    public String getAliasTableSetRowCount() {
        return aliasTableSetRowCount;
    }

    public void setAliasTableSetRowCount(String aliasTableSetRowCount) {
        this.aliasTableSetRowCount = aliasTableSetRowCount;
    }

    public String getAliasTableGetColumnCount() {
        return aliasTableGetColumnCount;
    }

    public void setAliasTableGetColumnCount(String aliasTableGetColumnCount) {
        this.aliasTableGetColumnCount = aliasTableGetColumnCount;
    }

    public String getAliasTableSetColumns() {
        return aliasTableSetColumns;
    }

    public void setAliasTableSetColumns(String aliasTableSetColumns) {
        this.aliasTableSetColumns = aliasTableSetColumns;
    }

    public String getAliasTableGetColumnName() {
        return aliasTableGetColumnName;
    }

    public void setAliasTableGetColumnName(String aliasTableGetColumnName) {
        this.aliasTableGetColumnName = aliasTableGetColumnName;
    }

    public String getAliasTableGetValue() {
        return aliasTableGetValue;
    }

    public void setAliasTableGetValue(String aliasTableGetValue) {
        this.aliasTableGetValue = aliasTableGetValue;
    }

    public String getAliasTableSetValue() {
        return aliasTableSetValue;
    }

    public void setAliasTableSetValue(String aliasTableSetValue) {
        this.aliasTableSetValue = aliasTableSetValue;
    }

    public String getAliasTableGetRowAtPoint() {
        return aliasTableGetRowAtPoint;
    }

    public void setAliasTableGetRowAtPoint(String aliasTableGetRowAtPoint) {
        this.aliasTableGetRowAtPoint = aliasTableGetRowAtPoint;
    }

    public String getAliasTableGetColumnAtPoint() {
        return aliasTableGetColumnAtPoint;
    }

    public void setAliasTableGetColumnAtPoint(String aliasTableGetColumnAtPoint) {
        this.aliasTableGetColumnAtPoint = aliasTableGetColumnAtPoint;
    }

    public String getAliasTableIsMultiple() {
        return aliasTableIsMultiple;
    }

    public void setAliasTableIsMultiple(String aliasTableIsMultiple) {
        this.aliasTableIsMultiple = aliasTableIsMultiple;
    }

    public String getAliasTableSetMultiple() {
        return aliasTableSetMultiple;
    }

    public void setAliasTableSetMultiple(String aliasTableSetMultiple) {
        this.aliasTableSetMultiple = aliasTableSetMultiple;
    }

    public String getAliasTableIsReorder() {
        return aliasTableIsReorder;
    }

    public void setAliasTableIsReorder(String aliasTableIsReorder) {
        this.aliasTableIsReorder = aliasTableIsReorder;
    }

    public String getAliasTableSetReorder() {
        return aliasTableSetReorder;
    }

    public void setAliasTableSetReorder(String aliasTableSetReorder) {
        this.aliasTableSetReorder = aliasTableSetReorder;
    }

    public String getAliasTableMoveColumn() {
        return aliasTableMoveColumn;
    }

    public void setAliasTableMoveColumn(String aliasTableMoveColumn) {
        this.aliasTableMoveColumn = aliasTableMoveColumn;
    }

    public String getAliasTableAddInterval() {
        return aliasTableAddInterval;
    }

    public void setAliasTableAddInterval(String aliasTableAddInterval) {
        this.aliasTableAddInterval = aliasTableAddInterval;
    }

    public String getAliasTableRemoveInterval() {
        return aliasTableRemoveInterval;
    }

    public void setAliasTableRemoveInterval(String aliasTableRemoveInterval) {
        this.aliasTableRemoveInterval = aliasTableRemoveInterval;
    }

    public String getAliasTableApplyChanges() {
        return aliasTableApplyChanges;
    }

    public void setAliasTableApplyChanges(String aliasTableApplyChanges) {
        this.aliasTableApplyChanges = aliasTableApplyChanges;
    }

    public String getAliasTableAddHeader() {
        return aliasTableAddHeader;
    }

    public void setAliasTableAddHeader(String aliasTableAddHeader) {
        this.aliasTableAddHeader = aliasTableAddHeader;
    }

    public String getAliasTableAddSelect() {
        return aliasTableAddSelect;
    }

    public void setAliasTableAddSelect(String aliasTableAddSelect) {
        this.aliasTableAddSelect = aliasTableAddSelect;
    }

    public String getAliasConfirm() {
        return aliasConfirm;
    }

    public void setAliasConfirm(String aliasConfirm) {
        this.aliasConfirm = aliasConfirm;
    }

    public String getAliasConfirmMessage() {
        return aliasConfirmMessage;
    }

    public void setAliasConfirmMessage(String aliasConfirmMessage) {
        this.aliasConfirmMessage = aliasConfirmMessage;
    }

    public String getAliasConfirmField() {
        return aliasConfirmField;
    }

    public void setAliasConfirmField(String aliasConfirmField) {
        this.aliasConfirmField = aliasConfirmField;
    }

    public String getAliasConfirmOk() {
        return aliasConfirmOk;
    }

    public void setAliasConfirmOk(String aliasConfirmOk) {
        this.aliasConfirmOk = aliasConfirmOk;
    }

    public String getAliasConfirmYesNo() {
        return aliasConfirmYesNo;
    }

    public void setAliasConfirmYesNo(String aliasConfirmYesNo) {
        this.aliasConfirmYesNo = aliasConfirmYesNo;
    }

    public String getAliasConfirmFull() {
        return aliasConfirmFull;
    }

    public void setAliasConfirmFull(String aliasConfirmFull) {
        this.aliasConfirmFull = aliasConfirmFull;
    }

    public String getAliasConfirmFieldOk() {
        return aliasConfirmFieldOk;
    }

    public void setAliasConfirmFieldOk(String aliasConfirmFieldOk) {
        this.aliasConfirmFieldOk = aliasConfirmFieldOk;
    }

    public String getAliasConfirmFieldCancel() {
        return aliasConfirmFieldCancel;
    }

    public void setAliasConfirmFieldCancel(String aliasConfirmFieldCancel) {
        this.aliasConfirmFieldCancel = aliasConfirmFieldCancel;
    }

    public String getAliasConfirmFieldYes() {
        return aliasConfirmFieldYes;
    }

    public void setAliasConfirmFieldYes(String aliasConfirmFieldYes) {
        this.aliasConfirmFieldYes = aliasConfirmFieldYes;
    }

    public String getAliasConfirmFieldNo() {
        return aliasConfirmFieldNo;
    }

    public void setAliasConfirmFieldNo(String aliasConfirmFieldNo) {
        this.aliasConfirmFieldNo = aliasConfirmFieldNo;
    }

    public String getAliasFrame() {
        return aliasFrame;
    }

    public void setAliasFrame(String aliasFrame) {
        this.aliasFrame = aliasFrame;
    }

    public String getAliasDialog() {
        return aliasDialog;
    }

    public void setAliasDialog(String aliasDialog) {
        this.aliasDialog = aliasDialog;
    }

    public String getAliasDialogIsModal() {
        return aliasDialogIsModal;
    }

    public void setAliasDialogIsModal(String aliasDialogIsModal) {
        this.aliasDialogIsModal = aliasDialogIsModal;
    }

    public String getAliasDialogSetModal() {
        return aliasDialogSetModal;
    }

    public void setAliasDialogSetModal(String aliasDialogSetModal) {
        this.aliasDialogSetModal = aliasDialogSetModal;
    }

    public String getAliasWindowType() {
        return aliasWindowType;
    }

    public void setAliasWindowType(String aliasWindowType) {
        this.aliasWindowType = aliasWindowType;
    }

    public String getAliasWindowTypeRelative() {
        return aliasWindowTypeRelative;
    }

    public void setAliasWindowTypeRelative(String aliasWindowTypeRelative) {
        this.aliasWindowTypeRelative = aliasWindowTypeRelative;
    }

    public String getAliasRemoveWindowListener() {
        return aliasRemoveWindowListener;
    }

    public void setAliasRemoveWindowListener(String aliasRemoveWindowListener) {
        this.aliasRemoveWindowListener = aliasRemoveWindowListener;
    }

    public String getAliasGetWindowListeners() {
        return aliasGetWindowListeners;
    }

    public void setAliasGetWindowListeners(String aliasGetWindowListeners) {
        this.aliasGetWindowListeners = aliasGetWindowListeners;
    }

    public String getAliasPanel() {
        return aliasPanel;
    }

    public void setAliasPanel(String aliasPanel) {
        this.aliasPanel = aliasPanel;
    }

    public String getAliasPanelBorder() {
        return aliasPanelBorder;
    }

    public void setAliasPanelBorder(String _aliasPanelBorder) {
        this.aliasPanelBorder = _aliasPanelBorder;
    }

    public String getAliasPanelBorderNorth() {
        return aliasPanelBorderNorth;
    }

    public void setAliasPanelBorderNorth(String aliasPanelBorderNorth) {
        this.aliasPanelBorderNorth = aliasPanelBorderNorth;
    }

    public String getAliasPanelBorderSouth() {
        return aliasPanelBorderSouth;
    }

    public void setAliasPanelBorderSouth(String aliasPanelBorderSouth) {
        this.aliasPanelBorderSouth = aliasPanelBorderSouth;
    }

    public String getAliasPanelBorderEast() {
        return aliasPanelBorderEast;
    }

    public void setAliasPanelBorderEast(String aliasPanelBorderEast) {
        this.aliasPanelBorderEast = aliasPanelBorderEast;
    }

    public String getAliasPanelBorderWest() {
        return aliasPanelBorderWest;
    }

    public void setAliasPanelBorderWest(String aliasPanelBorderWest) {
        this.aliasPanelBorderWest = aliasPanelBorderWest;
    }

    public String getAliasPanelBorderCenter() {
        return aliasPanelBorderCenter;
    }

    public void setAliasPanelBorderCenter(String aliasPanelBorderCenter) {
        this.aliasPanelBorderCenter = aliasPanelBorderCenter;
    }

    public String getAliasPanelBorderBeforeFirst() {
        return aliasPanelBorderBeforeFirst;
    }

    public void setAliasPanelBorderBeforeFirst(String aliasPanelBorderBeforeFirst) {
        this.aliasPanelBorderBeforeFirst = aliasPanelBorderBeforeFirst;
    }

    public String getAliasPanelBorderBeforeLineBegins() {
        return aliasPanelBorderBeforeLineBegins;
    }

    public void setAliasPanelBorderBeforeLineBegins(String aliasPanelBorderBeforeLineBegins) {
        this.aliasPanelBorderBeforeLineBegins = aliasPanelBorderBeforeLineBegins;
    }

    public String getAliasPanelBorderAfterLast() {
        return aliasPanelBorderAfterLast;
    }

    public void setAliasPanelBorderAfterLast(String aliasPanelBorderAfterLast) {
        this.aliasPanelBorderAfterLast = aliasPanelBorderAfterLast;
    }

    public String getAliasPanelBorderAfterLineEnds() {
        return aliasPanelBorderAfterLineEnds;
    }

    public void setAliasPanelBorderAfterLineEnds(String aliasPanelBorderAfterLineEnds) {
        this.aliasPanelBorderAfterLineEnds = aliasPanelBorderAfterLineEnds;
    }

    public String getAliasProgBar() {
        return aliasProgBar;
    }

    public void setAliasProgBar(String aliasProgBar) {
        this.aliasProgBar = aliasProgBar;
    }

    public String getAliasProgBarValue() {
        return aliasProgBarValue;
    }

    public void setAliasProgBarValue(String aliasProgBarValue) {
        this.aliasProgBarValue = aliasProgBarValue;
    }

    public String getAliasProgBarMax() {
        return aliasProgBarMax;
    }

    public void setAliasProgBarMax(String aliasProgBarMax) {
        this.aliasProgBarMax = aliasProgBarMax;
    }

    public String getAliasProgBarMin() {
        return aliasProgBarMin;
    }

    public void setAliasProgBarMin(String aliasProgBarMin) {
        this.aliasProgBarMin = aliasProgBarMin;
    }

    public String getAliasProgBarOr() {
        return aliasProgBarOr;
    }

    public void setAliasProgBarOr(String aliasProgBarOr) {
        this.aliasProgBarOr = aliasProgBarOr;
    }

    public String getAliasButton() {
        return aliasButton;
    }

    public void setAliasButton(String aliasButton) {
        this.aliasButton = aliasButton;
    }

    public String getAliasTextLabel() {
        return aliasTextLabel;
    }

    public void setAliasTextLabel(String aliasTextLabel) {
        this.aliasTextLabel = aliasTextLabel;
    }

    public String getAliasImageLabel() {
        return aliasImageLabel;
    }

    public void setAliasImageLabel(String aliasImageLabel) {
        this.aliasImageLabel = aliasImageLabel;
    }

    public String getAliasGetFont() {
        return aliasGetFont;
    }

    public void setAliasGetFont(String aliasGetFont) {
        this.aliasGetFont = aliasGetFont;
    }

    public String getAliasSetFont() {
        return aliasSetFont;
    }

    public void setAliasSetFont(String aliasSetFont) {
        this.aliasSetFont = aliasSetFont;
    }

    public String getAliasFont() {
        return aliasFont;
    }

    public void setAliasFont(String aliasFont) {
        this.aliasFont = aliasFont;
    }

    public String getAliasFontGetName() {
        return aliasFontGetName;
    }

    public void setAliasFontGetName(String aliasFontGetName) {
        this.aliasFontGetName = aliasFontGetName;
    }

    public String getAliasFontGetSize() {
        return aliasFontGetSize;
    }

    public void setAliasFontGetSize(String aliasFontGetSize) {
        this.aliasFontGetSize = aliasFontGetSize;
    }

    public String getAliasFontIsBold() {
        return aliasFontIsBold;
    }

    public void setAliasFontIsBold(String aliasFontIsBold) {
        this.aliasFontIsBold = aliasFontIsBold;
    }

    public String getAliasFontIsItalic() {
        return aliasFontIsItalic;
    }

    public void setAliasFontIsItalic(String aliasFontIsItalic) {
        this.aliasFontIsItalic = aliasFontIsItalic;
    }

    public String getAliasFontStringWidth() {
        return aliasFontStringWidth;
    }

    public void setAliasFontStringWidth(String aliasFontStringWidth) {
        this.aliasFontStringWidth = aliasFontStringWidth;
    }

    public String getAliasDimension() {
        return aliasDimension;
    }

    public void setAliasDimension(String aliasDimension) {
        this.aliasDimension = aliasDimension;
    }

    public String getAliasDimensionGetHeight() {
        return aliasDimensionGetHeight;
    }

    public void setAliasDimensionGetHeight(String aliasDimensionGetHeight) {
        this.aliasDimensionGetHeight = aliasDimensionGetHeight;
    }

    public String getAliasDimensionGetWidth() {
        return aliasDimensionGetWidth;
    }

    public void setAliasDimensionGetWidth(String aliasDimensionGetWidth) {
        this.aliasDimensionGetWidth = aliasDimensionGetWidth;
    }

    public String getAliasComponent() {
        return aliasComponent;
    }

    public void setAliasComponent(String aliasComponent) {
        this.aliasComponent = aliasComponent;
    }

    public String getAliasComponentSetAutoscrolls() {
        return aliasComponentSetAutoscrolls;
    }

    public void setAliasComponentSetAutoscrolls(String aliasComponentSetAutoscrolls) {
        this.aliasComponentSetAutoscrolls = aliasComponentSetAutoscrolls;
    }

    public String getAliasComponentIsAutoscrolls() {
        return aliasComponentIsAutoscrolls;
    }

    public void setAliasComponentIsAutoscrolls(String aliasComponentIsAutoscrolls) {
        this.aliasComponentIsAutoscrolls = aliasComponentIsAutoscrolls;
    }

    public String getAliasComponentGetWidth() {
        return aliasComponentGetWidth;
    }

    public void setAliasComponentGetWidth(String aliasComponentGetWidth) {
        this.aliasComponentGetWidth = aliasComponentGetWidth;
    }

    public String getAliasComponentGetHeight() {
        return aliasComponentGetHeight;
    }

    public void setAliasComponentGetHeight(String aliasComponentGetHeight) {
        this.aliasComponentGetHeight = aliasComponentGetHeight;
    }

    public String getAliasComponentGetPreferredSize() {
        return aliasComponentGetPreferredSize;
    }

    public void setAliasComponentGetPreferredSize(String aliasComponentGetPreferredSize) {
        this.aliasComponentGetPreferredSize = aliasComponentGetPreferredSize;
    }

    public String getAliasComponentSetPreferredSize() {
        return aliasComponentSetPreferredSize;
    }

    public void setAliasComponentSetPreferredSize(String aliasComponentSetPreferredSize) {
        this.aliasComponentSetPreferredSize = aliasComponentSetPreferredSize;
    }

    public String getAliasComponentSetSize() {
        return aliasComponentSetSize;
    }

    public void setAliasComponentSetSize(String aliasComponentSetSize) {
        this.aliasComponentSetSize = aliasComponentSetSize;
    }

    public String getAliasComponentIsVisible() {
        return aliasComponentIsVisible;
    }

    public void setAliasComponentIsVisible(String aliasComponentIsVisible) {
        this.aliasComponentIsVisible = aliasComponentIsVisible;
    }

    public String getAliasComponentSetVisible() {
        return aliasComponentSetVisible;
    }

    public void setAliasComponentSetVisible(String aliasComponentSetVisible) {
        this.aliasComponentSetVisible = aliasComponentSetVisible;
    }

    public String getAliasComponentInvokeLater() {
        return aliasComponentInvokeLater;
    }

    public void setAliasComponentInvokeLater(String aliasComponentInvokeLater) {
        this.aliasComponentInvokeLater = aliasComponentInvokeLater;
    }

    public String getAliasImage() {
        return aliasImage;
    }

    public void setAliasImage(String aliasImage) {
        this.aliasImage = aliasImage;
    }

    public String getAliasImageGetWidth() {
        return aliasImageGetWidth;
    }

    public void setAliasImageGetWidth(String aliasImageGetWidth) {
        this.aliasImageGetWidth = aliasImageGetWidth;
    }

    public String getAliasImageGetHeight() {
        return aliasImageGetHeight;
    }

    public void setAliasImageGetHeight(String aliasImageGetHeight) {
        this.aliasImageGetHeight = aliasImageGetHeight;
    }

    public String getAliasImageGet() {
        return aliasImageGet;
    }

    public void setAliasImageGet(String aliasImageGet) {
        this.aliasImageGet = aliasImageGet;
    }

    public String getAliasImageSet() {
        return aliasImageSet;
    }

    public void setAliasImageSet(String aliasImageSet) {
        this.aliasImageSet = aliasImageSet;
    }

    public String getAliasImageIsWithAlpha() {
        return aliasImageIsWithAlpha;
    }

    public void setAliasImageIsWithAlpha(String aliasImageIsWithAlpha) {
        this.aliasImageIsWithAlpha = aliasImageIsWithAlpha;
    }

    public String getAliasImageGetColor() {
        return aliasImageGetColor;
    }

    public void setAliasImageGetColor(String aliasImageGetColor) {
        this.aliasImageGetColor = aliasImageGetColor;
    }

    public String getAliasImageSetColor() {
        return aliasImageSetColor;
    }

    public void setAliasImageSetColor(String aliasImageSetColor) {
        this.aliasImageSetColor = aliasImageSetColor;
    }

    public String getAliasImageGetFont() {
        return aliasImageGetFont;
    }

    public void setAliasImageGetFont(String aliasImageGetFont) {
        this.aliasImageGetFont = aliasImageGetFont;
    }

    public String getAliasImageSetFont() {
        return aliasImageSetFont;
    }

    public void setAliasImageSetFont(String aliasImageSetFont) {
        this.aliasImageSetFont = aliasImageSetFont;
    }

    public String getAliasImageDraw() {
        return aliasImageDraw;
    }

    public void setAliasImageDraw(String aliasImageDraw) {
        this.aliasImageDraw = aliasImageDraw;
    }

    public String getAliasImageDrawLine() {
        return aliasImageDrawLine;
    }

    public void setAliasImageDrawLine(String aliasImageDrawLine) {
        this.aliasImageDrawLine = aliasImageDrawLine;
    }

    public String getAliasImageDrawRect() {
        return aliasImageDrawRect;
    }

    public void setAliasImageDrawRect(String aliasImageDrawRect) {
        this.aliasImageDrawRect = aliasImageDrawRect;
    }

    public String getAliasImageDrawOval() {
        return aliasImageDrawOval;
    }

    public void setAliasImageDrawOval(String aliasImageDrawOval) {
        this.aliasImageDrawOval = aliasImageDrawOval;
    }

    public String getAliasImageFillRect() {
        return aliasImageFillRect;
    }

    public void setAliasImageFillRect(String aliasImageFillRect) {
        this.aliasImageFillRect = aliasImageFillRect;
    }

    public String getAliasImageFillOval() {
        return aliasImageFillOval;
    }

    public void setAliasImageFillOval(String aliasImageFillOval) {
        this.aliasImageFillOval = aliasImageFillOval;
    }

    public String getAliasImageDrawPolygon() {
        return aliasImageDrawPolygon;
    }

    public void setAliasImageDrawPolygon(String aliasImageDrawPolygon) {
        this.aliasImageDrawPolygon = aliasImageDrawPolygon;
    }

    public String getAliasImageFillPolygon() {
        return aliasImageFillPolygon;
    }

    public void setAliasImageFillPolygon(String aliasImageFillPolygon) {
        this.aliasImageFillPolygon = aliasImageFillPolygon;
    }

    public String getAliasImageDispose() {
        return aliasImageDispose;
    }

    public void setAliasImageDispose(String aliasImageDispose) {
        this.aliasImageDispose = aliasImageDispose;
    }

    public String getAliasColor() {
        return aliasColor;
    }

    public void setAliasColor(String aliasColor) {
        this.aliasColor = aliasColor;
    }

    public String getAliasColorRed() {
        return aliasColorRed;
    }

    public void setAliasColorRed(String aliasColorRed) {
        this.aliasColorRed = aliasColorRed;
    }

    public String getAliasColorGreen() {
        return aliasColorGreen;
    }

    public void setAliasColorGreen(String aliasColorGreen) {
        this.aliasColorGreen = aliasColorGreen;
    }

    public String getAliasColorBlue() {
        return aliasColorBlue;
    }

    public void setAliasColorBlue(String aliasColorBlue) {
        this.aliasColorBlue = aliasColorBlue;
    }

    public String getAliasColorAlpha() {
        return aliasColorAlpha;
    }

    public void setAliasColorAlpha(String aliasColorAlpha) {
        this.aliasColorAlpha = aliasColorAlpha;
    }

    public String getAliasColorIsTransparent() {
        return aliasColorIsTransparent;
    }

    public void setAliasColorIsTransparent(String aliasColorIsTransparent) {
        this.aliasColorIsTransparent = aliasColorIsTransparent;
    }

    public String getAliasComponentSetPaint() {
        return aliasComponentSetPaint;
    }

    public void setAliasComponentSetPaint(String aliasComponentSetPaint) {
        this.aliasComponentSetPaint = aliasComponentSetPaint;
    }

    public String getAliasComponentGetPaint() {
        return aliasComponentGetPaint;
    }

    public void setAliasComponentGetPaint(String aliasComponentGetPaint) {
        this.aliasComponentGetPaint = aliasComponentGetPaint;
    }

    public String getAliasComponentRepaint() {
        return aliasComponentRepaint;
    }

    public void setAliasComponentRepaint(String aliasComponentRepaint) {
        this.aliasComponentRepaint = aliasComponentRepaint;
    }

    public String getAliasSetContent() {
        return aliasSetContent;
    }

    public void setAliasSetContent(String aliasSetContent) {
        this.aliasSetContent = aliasSetContent;
    }

    public String getAliasAddCompo() {
        return aliasAddCompo;
    }

    public void setAliasAddCompo(String aliasAddCompo) {
        this.aliasAddCompo = aliasAddCompo;
    }

    public String getAliasRemoveAll() {
        return aliasRemoveAll;
    }

    public void setAliasRemoveAll(String _aliasRemoveAll) {
        aliasRemoveAll = _aliasRemoveAll;
    }

    public String getAliasGetParentCompo() {
        return aliasGetParentCompo;
    }

    public void setAliasGetParentCompo(String aliasGetParentCompo) {
        this.aliasGetParentCompo = aliasGetParentCompo;
    }

    public String getAliasGetNextCompo() {
        return aliasGetNextCompo;
    }

    public void setAliasGetNextCompo(String aliasGetNextCompo) {
        this.aliasGetNextCompo = aliasGetNextCompo;
    }

    public String getAliasGetIndexCompo() {
        return aliasGetIndexCompo;
    }

    public void setAliasGetIndexCompo(String aliasGetIndexCompo) {
        this.aliasGetIndexCompo = aliasGetIndexCompo;
    }

    public String getAliasPanelFlow() {
        return aliasPanelFlow;
    }

    public void setAliasPanelFlow(String aliasPanelFlow) {
        this.aliasPanelFlow = aliasPanelFlow;
    }

    public String getAliasPanelAbsolute() {
        return aliasPanelAbsolute;
    }

    public void setAliasPanelAbsolute(String aliasPanelAbsolute) {
        this.aliasPanelAbsolute = aliasPanelAbsolute;
    }

    public String getAliasPanelGrid() {
        return aliasPanelGrid;
    }

    public void setAliasPanelGrid(String aliasPanelGrid) {
        this.aliasPanelGrid = aliasPanelGrid;
    }

    public String getAliasPanelPageBox() {
        return aliasPanelPageBox;
    }

    public void setAliasPanelPageBox(String aliasPanelPageBox) {
        this.aliasPanelPageBox = aliasPanelPageBox;
    }

    public String getAliasPanelValidate() {
        return aliasPanelValidate;
    }

    public void setAliasPanelValidate(String aliasPanelValidate) {
        this.aliasPanelValidate = aliasPanelValidate;
    }

    public String getAliasTabbedPane() {
        return aliasTabbedPane;
    }

    public void setAliasTabbedPane(String aliasTabbedPane) {
        this.aliasTabbedPane = aliasTabbedPane;
    }

    public String getAliasTabbedPaneNb() {
        return aliasTabbedPaneNb;
    }

    public void setAliasTabbedPaneNb(String aliasTabbedPaneNb) {
        this.aliasTabbedPaneNb = aliasTabbedPaneNb;
    }

    public String getAliasTabbedPaneAdd() {
        return aliasTabbedPaneAdd;
    }

    public void setAliasTabbedPaneAdd(String aliasTabbedPaneAdd) {
        this.aliasTabbedPaneAdd = aliasTabbedPaneAdd;
    }

    public String getAliasTabbedPaneGet() {
        return aliasTabbedPaneGet;
    }

    public void setAliasTabbedPaneGet(String aliasTabbedPaneGet) {
        this.aliasTabbedPaneGet = aliasTabbedPaneGet;
    }

    public String getAliasTabbedPaneGetTitle() {
        return aliasTabbedPaneGetTitle;
    }

    public void setAliasTabbedPaneGetTitle(String aliasTabbedPaneGetTitle) {
        this.aliasTabbedPaneGetTitle = aliasTabbedPaneGetTitle;
    }

    public String getAliasTabbedPaneSet() {
        return aliasTabbedPaneSet;
    }

    public void setAliasTabbedPaneSet(String aliasTabbedPaneSet) {
        this.aliasTabbedPaneSet = aliasTabbedPaneSet;
    }

    public String getAliasTabbedPaneSetTitle() {
        return aliasTabbedPaneSetTitle;
    }

    public void setAliasTabbedPaneSetTitle(String aliasTabbedPaneSetTitle) {
        this.aliasTabbedPaneSetTitle = aliasTabbedPaneSetTitle;
    }

    public String getAliasTabbedPaneRemove() {
        return aliasTabbedPaneRemove;
    }

    public void setAliasTabbedPaneRemove(String aliasTabbedPaneRemove) {
        this.aliasTabbedPaneRemove = aliasTabbedPaneRemove;
    }

    public String getAliasTabbedPaneIndex() {
        return aliasTabbedPaneIndex;
    }

    public void setAliasTabbedPaneIndex(String aliasTabbedPaneIndex) {
        this.aliasTabbedPaneIndex = aliasTabbedPaneIndex;
    }

    public String getAliasTabbedPaneSelIndex() {
        return aliasTabbedPaneSelIndex;
    }

    public void setAliasTabbedPaneSelIndex(String aliasTabbedPaneSelIndex) {
        this.aliasTabbedPaneSelIndex = aliasTabbedPaneSelIndex;
    }

    public String getAliasAddListener() {
        return aliasAddListener;
    }

    public void setAliasAddListener(String aliasAddListener) {
        this.aliasAddListener = aliasAddListener;
    }

    public String getAliasAddWindowListener() {
        return aliasAddWindowListener;
    }

    public void setAliasAddWindowListener(String aliasAddWindowListener) {
        this.aliasAddWindowListener = aliasAddWindowListener;
    }

    public String getAliasSetLabelText() {
        return aliasSetLabelText;
    }

    public void setAliasSetLabelText(String aliasSetLabelText) {
        this.aliasSetLabelText = aliasSetLabelText;
    }

    public String getAliasSetLabelImage() {
        return aliasSetLabelImage;
    }

    public void setAliasSetLabelImage(String aliasSetLabelImage) {
        this.aliasSetLabelImage = aliasSetLabelImage;
    }

    public String getAliasPaint() {
        return aliasPaint;
    }

    public void setAliasPaint(String aliasPaint) {
        this.aliasPaint = aliasPaint;
    }

    public String getAliasPaintMethod() {
        return aliasPaintMethod;
    }

    public void setAliasPaintMethod(String aliasPaintMethod) {
        this.aliasPaintMethod = aliasPaintMethod;
    }

    public String getAliasPaintAdd() {
        return aliasPaintAdd;
    }

    public void setAliasPaintAdd(String aliasPaintAdd) {
        this.aliasPaintAdd = aliasPaintAdd;
    }

    public String getAliasPaintRefresh() {
        return aliasPaintRefresh;
    }

    public void setAliasPaintRefresh(String aliasPaintRefresh) {
        this.aliasPaintRefresh = aliasPaintRefresh;
    }

    public String getAliasRemoveCompo() {
        return aliasRemoveCompo;
    }

    public void setAliasRemoveCompo(String aliasRemoveCompo) {
        this.aliasRemoveCompo = aliasRemoveCompo;
    }

    public String getAliasCount() {
        return aliasCount;
    }

    public void setAliasCount(String aliasCount) {
        this.aliasCount = aliasCount;
    }

    public String getAliasScrollPane() {
        return aliasScrollPane;
    }

    public void setAliasScrollPane(String aliasScrollPane) {
        this.aliasScrollPane = aliasScrollPane;
    }

    public String getAliasScrollPaneGetView() {
        return aliasScrollPaneGetView;
    }

    public void setAliasScrollPaneGetView(String aliasScrollPaneGetView) {
        this.aliasScrollPaneGetView = aliasScrollPaneGetView;
    }

    public String getAliasScrollPaneSetView() {
        return aliasScrollPaneSetView;
    }

    public void setAliasScrollPaneSetView(String aliasScrollPaneSetView) {
        this.aliasScrollPaneSetView = aliasScrollPaneSetView;
    }

    public String getAliasScrollPaneHorizontalValue() {
        return aliasScrollPaneHorizontalValue;
    }

    public void setAliasScrollPaneHorizontalValue(String aliasScrollPaneHorizontalValue) {
        this.aliasScrollPaneHorizontalValue = aliasScrollPaneHorizontalValue;
    }

    public String getAliasScrollPaneVerticalValue() {
        return aliasScrollPaneVerticalValue;
    }

    public void setAliasScrollPaneVerticalValue(String aliasScrollPaneVerticalValue) {
        this.aliasScrollPaneVerticalValue = aliasScrollPaneVerticalValue;
    }

    public String getAliasScrollPaneValidate() {
        return aliasScrollPaneValidate;
    }

    public void setAliasScrollPaneValidate(String aliasScrollPaneValidate) {
        this.aliasScrollPaneValidate = aliasScrollPaneValidate;
    }

    public String getAliasSplitPane() {
        return aliasSplitPane;
    }

    public void setAliasSplitPane(String aliasSplitPane) {
        this.aliasSplitPane = aliasSplitPane;
    }

    public String getAliasSplitPaneGetDividerLocation() {
        return aliasSplitPaneGetDividerLocation;
    }

    public void setAliasSplitPaneGetDividerLocation(String aliasSplitPaneGetDividerLocation) {
        this.aliasSplitPaneGetDividerLocation = aliasSplitPaneGetDividerLocation;
    }

    public String getAliasSplitPaneSetDividerLocation() {
        return aliasSplitPaneSetDividerLocation;
    }

    public void setAliasSplitPaneSetDividerLocation(String aliasSplitPaneSetDividerLocation) {
        this.aliasSplitPaneSetDividerLocation = aliasSplitPaneSetDividerLocation;
    }

    public String getAliasSplitPaneGetDividerSize() {
        return aliasSplitPaneGetDividerSize;
    }

    public void setAliasSplitPaneGetDividerSize(String aliasSplitPaneGetDividerSize) {
        this.aliasSplitPaneGetDividerSize = aliasSplitPaneGetDividerSize;
    }

    public String getAliasSplitPaneSetDividerSize() {
        return aliasSplitPaneSetDividerSize;
    }

    public void setAliasSplitPaneSetDividerSize(String aliasSplitPaneSetDividerSize) {
        this.aliasSplitPaneSetDividerSize = aliasSplitPaneSetDividerSize;
    }

    public String getAliasSplitPaneIsContinuousLayout() {
        return aliasSplitPaneIsContinuousLayout;
    }

    public void setAliasSplitPaneIsContinuousLayout(String aliasSplitPaneIsContinuousLayout) {
        this.aliasSplitPaneIsContinuousLayout = aliasSplitPaneIsContinuousLayout;
    }

    public String getAliasSplitPaneSetContinuousLayout() {
        return aliasSplitPaneSetContinuousLayout;
    }

    public void setAliasSplitPaneSetContinuousLayout(String aliasSplitPaneSetContinuousLayout) {
        this.aliasSplitPaneSetContinuousLayout = aliasSplitPaneSetContinuousLayout;
    }

    public String getAliasSplitPaneIsOneTouchExpandable() {
        return aliasSplitPaneIsOneTouchExpandable;
    }

    public void setAliasSplitPaneIsOneTouchExpandable(String aliasSplitPaneIsOneTouchExpandable) {
        this.aliasSplitPaneIsOneTouchExpandable = aliasSplitPaneIsOneTouchExpandable;
    }

    public String getAliasSplitPaneSetOneTouchExpandable() {
        return aliasSplitPaneSetOneTouchExpandable;
    }

    public void setAliasSplitPaneSetOneTouchExpandable(String aliasSplitPaneSetOneTouchExpandable) {
        this.aliasSplitPaneSetOneTouchExpandable = aliasSplitPaneSetOneTouchExpandable;
    }

    public String getAliasSplitPaneGetLeft() {
        return aliasSplitPaneGetLeft;
    }

    public void setAliasSplitPaneGetLeft(String aliasSplitPaneGetLeft) {
        this.aliasSplitPaneGetLeft = aliasSplitPaneGetLeft;
    }

    public String getAliasSplitPaneSetLeft() {
        return aliasSplitPaneSetLeft;
    }

    public void setAliasSplitPaneSetLeft(String aliasSplitPaneSetLeft) {
        this.aliasSplitPaneSetLeft = aliasSplitPaneSetLeft;
    }

    public String getAliasSplitPaneGetRight() {
        return aliasSplitPaneGetRight;
    }

    public void setAliasSplitPaneGetRight(String aliasSplitPaneGetRight) {
        this.aliasSplitPaneGetRight = aliasSplitPaneGetRight;
    }

    public String getAliasSplitPaneSetRight() {
        return aliasSplitPaneSetRight;
    }

    public void setAliasSplitPaneSetRight(String aliasSplitPaneSetRight) {
        this.aliasSplitPaneSetRight = aliasSplitPaneSetRight;
    }

    public String getAliasSplitPaneValidate() {
        return aliasSplitPaneValidate;
    }

    public void setAliasSplitPaneValidate(String aliasSplitPaneValidate) {
        this.aliasSplitPaneValidate = aliasSplitPaneValidate;
    }

    public String getAliasPack() {
        return aliasPack;
    }

    public void setAliasPack(String aliasPack) {
        this.aliasPack = aliasPack;
    }

    public String getAliasDispose() {
        return aliasDispose;
    }

    public void setAliasDispose(String aliasDispose) {
        this.aliasDispose = aliasDispose;
    }

    public String getAliasIsVisible() {
        return aliasIsVisible;
    }

    public void setAliasIsVisible(String aliasIsVisible) {
        this.aliasIsVisible = aliasIsVisible;
    }

    public String getAliasSetVisible() {
        return aliasSetVisible;
    }

    public void setAliasSetVisible(String aliasSetVisible) {
        this.aliasSetVisible = aliasSetVisible;
    }

    public String getAliasWindow() {
        return aliasWindow;
    }

    public void setAliasWindow(String aliasWindow) {
        this.aliasWindow = aliasWindow;
    }

    public String getAliasArgs() {
        return aliasArgs;
    }

    public void setAliasArgs(String aliasArgs) {
        this.aliasArgs = aliasArgs;
    }

    public String getAliasMouseListener() {
        return aliasMouseListener;
    }

    public void setAliasMouseListener(String aliasMouseListener) {
        this.aliasMouseListener = aliasMouseListener;
    }

    public String getAliasMouseClicked() {
        return aliasMouseClicked;
    }

    public void setAliasMouseClicked(String aliasMouseClicked) {
        this.aliasMouseClicked = aliasMouseClicked;
    }

    public String getAliasMousePressed() {
        return aliasMousePressed;
    }

    public void setAliasMousePressed(String aliasMousePressed) {
        this.aliasMousePressed = aliasMousePressed;
    }

    public String getAliasMouseReleased() {
        return aliasMouseReleased;
    }

    public void setAliasMouseReleased(String aliasMouseReleased) {
        this.aliasMouseReleased = aliasMouseReleased;
    }

    public String getAliasMouseEntered() {
        return aliasMouseEntered;
    }

    public void setAliasMouseEntered(String aliasMouseEntered) {
        this.aliasMouseEntered = aliasMouseEntered;
    }

    public String getAliasMouseExited() {
        return aliasMouseExited;
    }

    public void setAliasMouseExited(String aliasMouseExited) {
        this.aliasMouseExited = aliasMouseExited;
    }

    public String getAliasMouseDragged() {
        return aliasMouseDragged;
    }

    public void setAliasMouseDragged(String aliasMouseDragged) {
        this.aliasMouseDragged = aliasMouseDragged;
    }

    public String getAliasMouseMoved() {
        return aliasMouseMoved;
    }

    public void setAliasMouseMoved(String aliasMouseMoved) {
        this.aliasMouseMoved = aliasMouseMoved;
    }

    public String getAliasMouseEvent() {
        return aliasMouseEvent;
    }

    public void setAliasMouseEvent(String aliasMouseEvent) {
        this.aliasMouseEvent = aliasMouseEvent;
    }

    public String getAliasMouseEventGetFirst() {
        return aliasMouseEventGetFirst;
    }

    public void setAliasMouseEventGetFirst(String aliasMouseEventGetFirst) {
        this.aliasMouseEventGetFirst = aliasMouseEventGetFirst;
    }

    public String getAliasMouseEventGetSecond() {
        return aliasMouseEventGetSecond;
    }

    public void setAliasMouseEventGetSecond(String aliasMouseEventGetSecond) {
        this.aliasMouseEventGetSecond = aliasMouseEventGetSecond;
    }

    public String getAliasMouseEventGetClicks() {
        return aliasMouseEventGetClicks;
    }

    public void setAliasMouseEventGetClicks(String aliasMouseEventGetClicks) {
        this.aliasMouseEventGetClicks = aliasMouseEventGetClicks;
    }

    public String getAliasMouseEventIsAlt() {
        return aliasMouseEventIsAlt;
    }

    public void setAliasMouseEventIsAlt(String aliasMouseEventIsAlt) {
        this.aliasMouseEventIsAlt = aliasMouseEventIsAlt;
    }

    public String getAliasMouseEventIsCtrl() {
        return aliasMouseEventIsCtrl;
    }

    public void setAliasMouseEventIsCtrl(String aliasMouseEventIsCtrl) {
        this.aliasMouseEventIsCtrl = aliasMouseEventIsCtrl;
    }

    public String getAliasMouseEventIsShift() {
        return aliasMouseEventIsShift;
    }

    public void setAliasMouseEventIsShift(String aliasMouseEventIsShift) {
        this.aliasMouseEventIsShift = aliasMouseEventIsShift;
    }

    public String getAliasMouseEventIsLeft() {
        return aliasMouseEventIsLeft;
    }

    public void setAliasMouseEventIsLeft(String aliasMouseEventIsLeft) {
        this.aliasMouseEventIsLeft = aliasMouseEventIsLeft;
    }

    public String getAliasMouseEventIsMiddle() {
        return aliasMouseEventIsMiddle;
    }

    public void setAliasMouseEventIsMiddle(String aliasMouseEventIsMiddle) {
        this.aliasMouseEventIsMiddle = aliasMouseEventIsMiddle;
    }

    public String getAliasMouseEventIsRight() {
        return aliasMouseEventIsRight;
    }

    public void setAliasMouseEventIsRight(String aliasMouseEventIsRight) {
        this.aliasMouseEventIsRight = aliasMouseEventIsRight;
    }

    public String getAliasWheelListener() {
        return aliasWheelListener;
    }

    public void setAliasWheelListener(String aliasWheelListener) {
        this.aliasWheelListener = aliasWheelListener;
    }

    public String getAliasWheelMove() {
        return aliasWheelMove;
    }

    public void setAliasWheelMove(String aliasWheelMove) {
        this.aliasWheelMove = aliasWheelMove;
    }

    public String getAliasWheelEvent() {
        return aliasWheelEvent;
    }

    public void setAliasWheelEvent(String aliasWheelEvent) {
        this.aliasWheelEvent = aliasWheelEvent;
    }

    public String getAliasWheelRotatedClicks() {
        return aliasWheelRotatedClicks;
    }

    public void setAliasWheelRotatedClicks(String aliasWheelRotatedClicks) {
        this.aliasWheelRotatedClicks = aliasWheelRotatedClicks;
    }

    public String getAliasRequestFocus() {
        return aliasRequestFocus;
    }

    public void setAliasRequestFocus(String aliasRequestFocus) {
        this.aliasRequestFocus = aliasRequestFocus;
    }

    public String getAliasCompToolTip() {
        return aliasCompToolTip;
    }

    public void setAliasCompToolTip(String aliasCompToolTip) {
        this.aliasCompToolTip = aliasCompToolTip;
    }

    public String getAliasCompFocusable() {
        return aliasCompFocusable;
    }

    public void setAliasCompFocusable(String aliasCompFocusable) {
        this.aliasCompFocusable = aliasCompFocusable;
    }

    public String getAliasCompOpaque() {
        return aliasCompOpaque;
    }

    public void setAliasCompOpaque(String aliasCompOpaque) {
        this.aliasCompOpaque = aliasCompOpaque;
    }

    public String getAliasCompBack() {
        return aliasCompBack;
    }

    public void setAliasCompBack(String aliasCompBack) {
        this.aliasCompBack = aliasCompBack;
    }

    public String getAliasCompFore() {
        return aliasCompFore;
    }

    public void setAliasCompFore(String aliasCompFore) {
        this.aliasCompFore = aliasCompFore;
    }

    public String getAliasCompGetFirstPos() {
        return aliasCompGetFirstPos;
    }

    public void setAliasCompGetFirstPos(String aliasCompGetFirstPos) {
        this.aliasCompGetFirstPos = aliasCompGetFirstPos;
    }

    public String getAliasCompGetSecondPos() {
        return aliasCompGetSecondPos;
    }

    public void setAliasCompGetSecondPos(String aliasCompGetSecondPos) {
        this.aliasCompGetSecondPos = aliasCompGetSecondPos;
    }

    public String getAliasCompLoc() {
        return aliasCompLoc;
    }

    public void setAliasCompLoc(String aliasCompLoc) {
        this.aliasCompLoc = aliasCompLoc;
    }

    public String getAliasCompBorLine() {
        return aliasCompBorLine;
    }

    public void setAliasCompBorLine(String aliasCompBorLine) {
        this.aliasCompBorLine = aliasCompBorLine;
    }

    public String getAliasCompBorTitle() {
        return aliasCompBorTitle;
    }

    public void setAliasCompBorTitle(String aliasCompBorTitle) {
        this.aliasCompBorTitle = aliasCompBorTitle;
    }

    public String getAliasCompBorLower() {
        return aliasCompBorLower;
    }

    public void setAliasCompBorLower(String aliasCompBorLower) {
        this.aliasCompBorLower = aliasCompBorLower;
    }

    public String getAliasCompBorRaise() {
        return aliasCompBorRaise;
    }

    public void setAliasCompBorRaise(String aliasCompBorRaise) {
        this.aliasCompBorRaise = aliasCompBorRaise;
    }

    public String getAliasAddKeyListener() {
        return aliasAddKeyListener;
    }

    public void setAliasAddKeyListener(String aliasAddKeyListener) {
        this.aliasAddKeyListener = aliasAddKeyListener;
    }

    public String getAliasAddWheelListener() {
        return aliasAddWheelListener;
    }

    public void setAliasAddWheelListener(String aliasAddWheelListener) {
        this.aliasAddWheelListener = aliasAddWheelListener;
    }

    public String getAliasKeyListener() {
        return aliasKeyListener;
    }

    public void setAliasKeyListener(String aliasKeyListener) {
        this.aliasKeyListener = aliasKeyListener;
    }

    public String getAliasKeyPressed() {
        return aliasKeyPressed;
    }

    public void setAliasKeyPressed(String aliasKeyPressed) {
        this.aliasKeyPressed = aliasKeyPressed;
    }

    public String getAliasKeyTyped() {
        return aliasKeyTyped;
    }

    public void setAliasKeyTyped(String aliasKeyTyped) {
        this.aliasKeyTyped = aliasKeyTyped;
    }

    public String getAliasKeyReleased() {
        return aliasKeyReleased;
    }

    public void setAliasKeyReleased(String aliasKeyReleased) {
        this.aliasKeyReleased = aliasKeyReleased;
    }

    public String getAliasKeyEvent() {
        return aliasKeyEvent;
    }

    public void setAliasKeyEvent(String aliasKeyEvent) {
        this.aliasKeyEvent = aliasKeyEvent;
    }

    public String getAliasKeyEventChar() {
        return aliasKeyEventChar;
    }

    public void setAliasKeyEventChar(String aliasKeyEventChar) {
        this.aliasKeyEventChar = aliasKeyEventChar;
    }

    public String getAliasKeyEventCode() {
        return aliasKeyEventCode;
    }

    public void setAliasKeyEventCode(String aliasKeyEventCode) {
        this.aliasKeyEventCode = aliasKeyEventCode;
    }

    public String getAliasKeyEventIsShift() {
        return aliasKeyEventIsShift;
    }

    public void setAliasKeyEventIsShift(String aliasKeyEventIsShift) {
        this.aliasKeyEventIsShift = aliasKeyEventIsShift;
    }

    public String getAliasKeyEventIsAlt() {
        return aliasKeyEventIsAlt;
    }

    public void setAliasKeyEventIsAlt(String aliasKeyEventIsAlt) {
        this.aliasKeyEventIsAlt = aliasKeyEventIsAlt;
    }

    public String getAliasKeyEventIsCtrl() {
        return aliasKeyEventIsCtrl;
    }

    public void setAliasKeyEventIsCtrl(String aliasKeyEventIsCtrl) {
        this.aliasKeyEventIsCtrl = aliasKeyEventIsCtrl;
    }

    public String getAliasWindowListener() {
        return aliasWindowListener;
    }

    public void setAliasWindowListener(String aliasWindowListener) {
        this.aliasWindowListener = aliasWindowListener;
    }

    public String getAliasWindowOpened() {
        return aliasWindowOpened;
    }

    public void setAliasWindowOpened(String aliasWindowOpened) {
        this.aliasWindowOpened = aliasWindowOpened;
    }

    public String getAliasWindowClosed() {
        return aliasWindowClosed;
    }

    public void setAliasWindowClosed(String aliasWindowClosed) {
        this.aliasWindowClosed = aliasWindowClosed;
    }

    public String getAliasWindowClosing() {
        return aliasWindowClosing;
    }

    public void setAliasWindowClosing(String aliasWindowClosing) {
        this.aliasWindowClosing = aliasWindowClosing;
    }

    public String getAliasWindowIconified() {
        return aliasWindowIconified;
    }

    public void setAliasWindowIconified(String aliasWindowIconified) {
        this.aliasWindowIconified = aliasWindowIconified;
    }

    public String getAliasWindowDeiconified() {
        return aliasWindowDeiconified;
    }

    public void setAliasWindowDeiconified(String aliasWindowDeiconified) {
        this.aliasWindowDeiconified = aliasWindowDeiconified;
    }

    public String getAliasWindowActivated() {
        return aliasWindowActivated;
    }

    public void setAliasWindowActivated(String aliasWindowActivated) {
        this.aliasWindowActivated = aliasWindowActivated;
    }

    public String getAliasWindowDeactivated() {
        return aliasWindowDeactivated;
    }

    public void setAliasWindowDeactivated(String aliasWindowDeactivated) {
        this.aliasWindowDeactivated = aliasWindowDeactivated;
    }

    public String getAliasWindowEvent() {
        return aliasWindowEvent;
    }

    public void setAliasWindowEvent(String aliasWindowEvent) {
        this.aliasWindowEvent = aliasWindowEvent;
    }

    public String getAliasListSelection() {
        return aliasListSelection;
    }

    public void setAliasListSelection(String aliasListSelection) {
        this.aliasListSelection = aliasListSelection;
    }

    public String getAliasValueChanged() {
        return aliasValueChanged;
    }

    public void setAliasValueChanged(String aliasValueChanged) {
        this.aliasValueChanged = aliasValueChanged;
    }

    public String getAliasInput() {
        return aliasInput;
    }

    public void setAliasInput(String _aliasInput) {
        aliasInput = _aliasInput;
    }

    public String getAliasInputIsEnabled() {
        return aliasInputIsEnabled;
    }

    public void setAliasInputIsEnabled(String _aliasInputIsEnabled) {
        aliasInputIsEnabled = _aliasInputIsEnabled;
    }

    public String getAliasInputSetEnabled() {
        return aliasInputSetEnabled;
    }

    public void setAliasInputSetEnabled(String _aliasInputSetEnabled) {
        aliasInputSetEnabled = _aliasInputSetEnabled;
    }

    public String getAliasRender() {
        return aliasRender;
    }

    public void setAliasRender(String aliasRender) {
        this.aliasRender = aliasRender;
    }

    public String getAliasRenderGetHeight() {
        return aliasRenderGetHeight;
    }

    public void setAliasRenderGetHeight(String aliasRenderGetHeight) {
        this.aliasRenderGetHeight = aliasRenderGetHeight;
    }

    public String getAliasRenderSetHeight() {
        return aliasRenderSetHeight;
    }

    public void setAliasRenderSetHeight(String aliasRenderSetHeight) {
        this.aliasRenderSetHeight = aliasRenderSetHeight;
    }

    public String getAliasRenderGetWidth() {
        return aliasRenderGetWidth;
    }

    public void setAliasRenderGetWidth(String aliasRenderGetWidth) {
        this.aliasRenderGetWidth = aliasRenderGetWidth;
    }

    public String getAliasRenderSetWidth() {
        return aliasRenderSetWidth;
    }

    public void setAliasRenderSetWidth(String aliasRenderSetWidth) {
        this.aliasRenderSetWidth = aliasRenderSetWidth;
    }

    public String getAliasRenderGetPaint() {
        return aliasRenderGetPaint;
    }

    public void setAliasRenderGetPaint(String aliasRenderGetPaint) {
        this.aliasRenderGetPaint = aliasRenderGetPaint;
    }

    public String getAliasRenderSetPaint() {
        return aliasRenderSetPaint;
    }

    public void setAliasRenderSetPaint(String aliasRenderSetPaint) {
        this.aliasRenderSetPaint = aliasRenderSetPaint;
    }

    public String getAliasGrList() {
        return aliasGrList;
    }

    public void setAliasGrList(String aliasGrList) {
        this.aliasGrList = aliasGrList;
    }

    public String getAliasGrListAdd() {
        return aliasGrListAdd;
    }

    public void setAliasGrListAdd(String aliasGrListAdd) {
        this.aliasGrListAdd = aliasGrListAdd;
    }

    public String getAliasGrListSet() {
        return aliasGrListSet;
    }

    public void setAliasGrListSet(String aliasGrListSet) {
        this.aliasGrListSet = aliasGrListSet;
    }

    public String getAliasGrListGetListView() {
        return aliasGrListGetListView;
    }

    public void setAliasGrListGetListView(String aliasGrListGetListView) {
        this.aliasGrListGetListView = aliasGrListGetListView;
    }

    public String getAliasGrListGetSelectedIndexes() {
        return aliasGrListGetSelectedIndexes;
    }

    public void setAliasGrListGetSelectedIndexes(String aliasGrListGetSelectedIndexes) {
        this.aliasGrListGetSelectedIndexes = aliasGrListGetSelectedIndexes;
    }

    public String getAliasGrListSetSelectedIndexes() {
        return aliasGrListSetSelectedIndexes;
    }

    public void setAliasGrListSetSelectedIndexes(String aliasGrListSetSelectedIndexes) {
        this.aliasGrListSetSelectedIndexes = aliasGrListSetSelectedIndexes;
    }

    public String getAliasGrListClearSelection() {
        return aliasGrListClearSelection;
    }

    public void setAliasGrListClearSelection(String aliasGrListClearSelection) {
        this.aliasGrListClearSelection = aliasGrListClearSelection;
    }

    public String getAliasGrListUpdateGraphics() {
        return aliasGrListUpdateGraphics;
    }

    public void setAliasGrListUpdateGraphics(String aliasGrListUpdateGraphics) {
        this.aliasGrListUpdateGraphics = aliasGrListUpdateGraphics;
    }

    public String getAliasGrListGetRender() {
        return aliasGrListGetRender;
    }

    public void setAliasGrListGetRender(String aliasGrListGetRender) {
        this.aliasGrListGetRender = aliasGrListGetRender;
    }

    public String getAliasGrListSetRender() {
        return aliasGrListSetRender;
    }

    public void setAliasGrListSetRender(String aliasGrListSetRender) {
        this.aliasGrListSetRender = aliasGrListSetRender;
    }

    public String getAliasGrListGetSelection() {
        return aliasGrListGetSelection;
    }

    public void setAliasGrListGetSelection(String aliasGrListGetSelection) {
        this.aliasGrListGetSelection = aliasGrListGetSelection;
    }

    public String getAliasGrListSetSelection() {
        return aliasGrListSetSelection;
    }

    public void setAliasGrListSetSelection(String aliasGrListSetSelection) {
        this.aliasGrListSetSelection = aliasGrListSetSelection;
    }

    public String getAliasGrListGetVisibleRowCount() {
        return aliasGrListGetVisibleRowCount;
    }

    public void setAliasGrListGetVisibleRowCount(String aliasGrListGetVisibleRowCount) {
        this.aliasGrListGetVisibleRowCount = aliasGrListGetVisibleRowCount;
    }

    public String getAliasGrListSetVisibleRowCount() {
        return aliasGrListSetVisibleRowCount;
    }

    public void setAliasGrListSetVisibleRowCount(String aliasGrListSetVisibleRowCount) {
        this.aliasGrListSetVisibleRowCount = aliasGrListSetVisibleRowCount;
    }

    public String getAliasGrListClear() {
        return aliasGrListClear;
    }

    public void setAliasGrListClear(String aliasGrListClear) {
        this.aliasGrListClear = aliasGrListClear;
    }

    public String getAliasGrListRemove() {
        return aliasGrListRemove;
    }

    public void setAliasGrListRemove(String aliasGrListRemove) {
        this.aliasGrListRemove = aliasGrListRemove;
    }

    public String getAliasCombo() {
        return aliasCombo;
    }

    public void setAliasCombo(String aliasCombo) {
        this.aliasCombo = aliasCombo;
    }

    public String getAliasComboGetSelectedItem() {
        return aliasComboGetSelectedItem;
    }

    public void setAliasComboGetSelectedItem(String aliasComboGetSelectedItem) {
        this.aliasComboGetSelectedItem = aliasComboGetSelectedItem;
    }

    public String getAliasComboAddItem() {
        return aliasComboAddItem;
    }

    public void setAliasComboAddItem(String aliasComboAddItem) {
        this.aliasComboAddItem = aliasComboAddItem;
    }

    public String getAliasComboGetItemCount() {
        return aliasComboGetItemCount;
    }

    public void setAliasComboGetItemCount(String aliasComboGetItemCount) {
        this.aliasComboGetItemCount = aliasComboGetItemCount;
    }

    public String getAliasComboSelectItem() {
        return aliasComboSelectItem;
    }

    public void setAliasComboSelectItem(String aliasComboSelectItem) {
        this.aliasComboSelectItem = aliasComboSelectItem;
    }

    public String getAliasComboSetListener() {
        return aliasComboSetListener;
    }

    public void setAliasComboSetListener(String aliasComboSetListener) {
        this.aliasComboSetListener = aliasComboSetListener;
    }

    public String getAliasComboGetListener() {
        return aliasComboGetListener;
    }

    public void setAliasComboGetListener(String aliasComboGetListener) {
        this.aliasComboGetListener = aliasComboGetListener;
    }

    public String getAliasComboGetSelectedIndexes() {
        return aliasComboGetSelectedIndexes;
    }

    public void setAliasComboGetSelectedIndexes(String aliasComboGetSelectedIndexes) {
        this.aliasComboGetSelectedIndexes = aliasComboGetSelectedIndexes;
    }

    public String getAliasComboGetSelectedIndex() {
        return aliasComboGetSelectedIndex;
    }

    public void setAliasComboGetSelectedIndex(String aliasComboGetSelectedIndex) {
        this.aliasComboGetSelectedIndex = aliasComboGetSelectedIndex;
    }

    public String getAliasComboRemoveAllItems() {
        return aliasComboRemoveAllItems;
    }

    public void setAliasComboRemoveAllItems(String aliasComboRemoveAllItems) {
        this.aliasComboRemoveAllItems = aliasComboRemoveAllItems;
    }

    public String getAliasComboRemoveItem() {
        return aliasComboRemoveItem;
    }

    public void setAliasComboRemoveItem(String aliasComboRemoveItem) {
        this.aliasComboRemoveItem = aliasComboRemoveItem;
    }

    public String getAliasButtonGroup() {
        return aliasButtonGroup;
    }

    public void setAliasButtonGroup(String aliasButtonGroup) {
        this.aliasButtonGroup = aliasButtonGroup;
    }

    public String getAliasButtonGroupAdd() {
        return aliasButtonGroupAdd;
    }

    public void setAliasButtonGroupAdd(String aliasButtonGroupAdd) {
        this.aliasButtonGroupAdd = aliasButtonGroupAdd;
    }

    public String getAliasRadio() {
        return aliasRadio;
    }

    public void setAliasRadio(String aliasRadio) {
        this.aliasRadio = aliasRadio;
    }

    public String getAliasRadioIsSelected() {
        return aliasRadioIsSelected;
    }

    public void setAliasRadioIsSelected(String aliasRadioIsSelected) {
        this.aliasRadioIsSelected = aliasRadioIsSelected;
    }

    public String getAliasRadioSetSelected() {
        return aliasRadioSetSelected;
    }

    public void setAliasRadioSetSelected(String aliasRadioSetSelected) {
        this.aliasRadioSetSelected = aliasRadioSetSelected;
    }

    public String getAliasRadioGetText() {
        return aliasRadioGetText;
    }

    public void setAliasRadioGetText(String aliasRadioGetText) {
        this.aliasRadioGetText = aliasRadioGetText;
    }

    public String getAliasRadioSetText() {
        return aliasRadioSetText;
    }

    public void setAliasRadioSetText(String aliasRadioSetText) {
        this.aliasRadioSetText = aliasRadioSetText;
    }

    public String getAliasPopupMenu() {
        return aliasPopupMenu;
    }

    public void setAliasPopupMenu(String aliasPopupMenu) {
        this.aliasPopupMenu = aliasPopupMenu;
    }

    public String getAliasPopupMenuAdd() {
        return aliasPopupMenuAdd;
    }

    public void setAliasPopupMenuAdd(String aliasPopupMenuAdd) {
        this.aliasPopupMenuAdd = aliasPopupMenuAdd;
    }

    public String getAliasPopupMenuGetComp() {
        return aliasPopupMenuGetComp;
    }

    public void setAliasPopupMenuGetComp(String aliasPopupMenuGetComp) {
        this.aliasPopupMenuGetComp = aliasPopupMenuGetComp;
    }

    public String getAliasPopupMenuRemoveComp() {
        return aliasPopupMenuRemoveComp;
    }

    public void setAliasPopupMenuRemoveComp(String aliasPopupMenuRemoveComp) {
        this.aliasPopupMenuRemoveComp = aliasPopupMenuRemoveComp;
    }

    public String getAliasPopupMenuNbComp() {
        return aliasPopupMenuNbComp;
    }

    public void setAliasPopupMenuNbComp(String aliasPopupMenuNbComp) {
        this.aliasPopupMenuNbComp = aliasPopupMenuNbComp;
    }

    public String getAliasPopupMenuAddMenu() {
        return aliasPopupMenuAddMenu;
    }

    public void setAliasPopupMenuAddMenu(String aliasPopupMenuAddMenu) {
        this.aliasPopupMenuAddMenu = aliasPopupMenuAddMenu;
    }

    public String getAliasPopupMenuGetMenu() {
        return aliasPopupMenuGetMenu;
    }

    public void setAliasPopupMenuGetMenu(String aliasPopupMenuGetMenu) {
        this.aliasPopupMenuGetMenu = aliasPopupMenuGetMenu;
    }

    public String getAliasPopupMenuRemoveMenu() {
        return aliasPopupMenuRemoveMenu;
    }

    public void setAliasPopupMenuRemoveMenu(String aliasPopupMenuRemoveMenu) {
        this.aliasPopupMenuRemoveMenu = aliasPopupMenuRemoveMenu;
    }

    public String getAliasPopupMenuNbMenu() {
        return aliasPopupMenuNbMenu;
    }

    public void setAliasPopupMenuNbMenu(String aliasPopupMenuNbMenu) {
        this.aliasPopupMenuNbMenu = aliasPopupMenuNbMenu;
    }

    public String getAliasPopupMenuShow() {
        return aliasPopupMenuShow;
    }

    public void setAliasPopupMenuShow(String aliasPopupMenuShow) {
        this.aliasPopupMenuShow = aliasPopupMenuShow;
    }

    public String getAliasTextField() {
        return aliasTextField;
    }

    public void setAliasTextField(String aliasTextField) {
        this.aliasTextField = aliasTextField;
    }

    public String getAliasTextFieldGetText() {
        return aliasTextFieldGetText;
    }

    public void setAliasTextFieldGetText(String aliasTextFieldGetText) {
        this.aliasTextFieldGetText = aliasTextFieldGetText;
    }

    public String getAliasTextFieldSetText() {
        return aliasTextFieldSetText;
    }

    public void setAliasTextFieldSetText(String aliasTextFieldSetText) {
        this.aliasTextFieldSetText = aliasTextFieldSetText;
    }

    public String getAliasTextFieldAuto() {
        return aliasTextFieldAuto;
    }

    public void setAliasTextFieldAuto(String aliasTextFieldAuto) {
        this.aliasTextFieldAuto = aliasTextFieldAuto;
    }

    public String getAliasTextFieldAddAction() {
        return aliasTextFieldAddAction;
    }

    public void setAliasTextFieldAddAction(String aliasTextFieldAddAction) {
        this.aliasTextFieldAddAction = aliasTextFieldAddAction;
    }

    public String getAliasTextFieldAddDocument() {
        return aliasTextFieldAddDocument;
    }

    public void setAliasTextFieldAddDocument(String aliasTextFieldAddDocument) {
        this.aliasTextFieldAddDocument = aliasTextFieldAddDocument;
    }

    public String getAliasTextFieldAddPopup() {
        return aliasTextFieldAddPopup;
    }

    public void setAliasTextFieldAddPopup(String aliasTextFieldAddPopup) {
        this.aliasTextFieldAddPopup = aliasTextFieldAddPopup;
    }

    public String getAliasTextArea() {
        return aliasTextArea;
    }

    public void setAliasTextArea(String aliasTextArea) {
        this.aliasTextArea = aliasTextArea;
    }

    public String getAliasTextAreaGetText() {
        return aliasTextAreaGetText;
    }

    public void setAliasTextAreaGetText(String aliasTextAreaGetText) {
        this.aliasTextAreaGetText = aliasTextAreaGetText;
    }

    public String getAliasTextAreaSetText() {
        return aliasTextAreaSetText;
    }

    public void setAliasTextAreaSetText(String aliasTextAreaSetText) {
        this.aliasTextAreaSetText = aliasTextAreaSetText;
    }

    public String getAliasTextAreaGetTabSize() {
        return aliasTextAreaGetTabSize;
    }

    public void setAliasTextAreaGetTabSize(String aliasTextAreaGetTabSize) {
        this.aliasTextAreaGetTabSize = aliasTextAreaGetTabSize;
    }

    public String getAliasTextAreaSetTabSize() {
        return aliasTextAreaSetTabSize;
    }

    public void setAliasTextAreaSetTabSize(String aliasTextAreaSetTabSize) {
        this.aliasTextAreaSetTabSize = aliasTextAreaSetTabSize;
    }

    public String getAliasTextAreaAppend() {
        return aliasTextAreaAppend;
    }

    public void setAliasTextAreaAppend(String aliasTextAreaAppend) {
        this.aliasTextAreaAppend = aliasTextAreaAppend;
    }

    public String getAliasTextAreaInsert() {
        return aliasTextAreaInsert;
    }

    public void setAliasTextAreaInsert(String aliasTextAreaInsert) {
        this.aliasTextAreaInsert = aliasTextAreaInsert;
    }

    public String getAliasTextAreaReplaceRange() {
        return aliasTextAreaReplaceRange;
    }

    public void setAliasTextAreaReplaceRange(String aliasTextAreaReplaceRange) {
        this.aliasTextAreaReplaceRange = aliasTextAreaReplaceRange;
    }

    public String getAliasTextAreaReplaceSelection() {
        return aliasTextAreaReplaceSelection;
    }

    public void setAliasTextAreaReplaceSelection(String aliasTextAreaReplaceSelection) {
        this.aliasTextAreaReplaceSelection = aliasTextAreaReplaceSelection;
    }

    public String getAliasTextAreaGetSelectedText() {
        return aliasTextAreaGetSelectedText;
    }

    public void setAliasTextAreaGetSelectedText(String aliasTextAreaGetSelectedText) {
        this.aliasTextAreaGetSelectedText = aliasTextAreaGetSelectedText;
    }

    public String getAliasTextAreaSetSelectionStart() {
        return aliasTextAreaSetSelectionStart;
    }

    public void setAliasTextAreaSetSelectionStart(String aliasTextAreaSetSelectionStart) {
        this.aliasTextAreaSetSelectionStart = aliasTextAreaSetSelectionStart;
    }

    public String getAliasTextAreaSetSelectionEnd() {
        return aliasTextAreaSetSelectionEnd;
    }

    public void setAliasTextAreaSetSelectionEnd(String aliasTextAreaSetSelectionEnd) {
        this.aliasTextAreaSetSelectionEnd = aliasTextAreaSetSelectionEnd;
    }

    public String getAliasTextAreaSelect() {
        return aliasTextAreaSelect;
    }

    public void setAliasTextAreaSelect(String aliasTextAreaSelect) {
        this.aliasTextAreaSelect = aliasTextAreaSelect;
    }

    public String getAliasTextAreaSelectAll() {
        return aliasTextAreaSelectAll;
    }

    public void setAliasTextAreaSelectAll(String aliasTextAreaSelectAll) {
        this.aliasTextAreaSelectAll = aliasTextAreaSelectAll;
    }

    public String getAliasCheckBox() {
        return aliasCheckBox;
    }

    public void setAliasCheckBox(String aliasCheckBox) {
        this.aliasCheckBox = aliasCheckBox;
    }

    public String getAliasCheckBoxGetText() {
        return aliasCheckBoxGetText;
    }

    public void setAliasCheckBoxGetText(String aliasCheckBoxGetText) {
        this.aliasCheckBoxGetText = aliasCheckBoxGetText;
    }

    public String getAliasCheckBoxSetText() {
        return aliasCheckBoxSetText;
    }

    public void setAliasCheckBoxSetText(String aliasCheckBoxSetText) {
        this.aliasCheckBoxSetText = aliasCheckBoxSetText;
    }

    public String getAliasCheckBoxIsSelected() {
        return aliasCheckBoxIsSelected;
    }

    public void setAliasCheckBoxIsSelected(String aliasCheckBoxIsSelected) {
        this.aliasCheckBoxIsSelected = aliasCheckBoxIsSelected;
    }

    public String getAliasCheckBoxSetSelected() {
        return aliasCheckBoxSetSelected;
    }

    public void setAliasCheckBoxSetSelected(String aliasCheckBoxSetSelected) {
        this.aliasCheckBoxSetSelected = aliasCheckBoxSetSelected;
    }

    public String getAliasCheckBoxAddAction() {
        return aliasCheckBoxAddAction;
    }

    public void setAliasCheckBoxAddAction(String aliasCheckBoxAddAction) {
        this.aliasCheckBoxAddAction = aliasCheckBoxAddAction;
    }

    public String getAliasSpinner() {
        return aliasSpinner;
    }

    public void setAliasSpinner(String aliasSpinner) {
        this.aliasSpinner = aliasSpinner;
    }

    public String getAliasSpinnerSetRange() {
        return aliasSpinnerSetRange;
    }

    public void setAliasSpinnerSetRange(String aliasSpinnerSetRange) {
        this.aliasSpinnerSetRange = aliasSpinnerSetRange;
    }

    public String getAliasSpinnerSetRangeValue() {
        return aliasSpinnerSetRangeValue;
    }

    public void setAliasSpinnerSetRangeValue(String aliasSpinnerSetRangeValue) {
        this.aliasSpinnerSetRangeValue = aliasSpinnerSetRangeValue;
    }

    public String getAliasSpinnerGetValue() {
        return aliasSpinnerGetValue;
    }

    public void setAliasSpinnerGetValue(String aliasSpinnerGetValue) {
        this.aliasSpinnerGetValue = aliasSpinnerGetValue;
    }

    public String getAliasSpinnerSetValue() {
        return aliasSpinnerSetValue;
    }

    public void setAliasSpinnerSetValue(String aliasSpinnerSetValue) {
        this.aliasSpinnerSetValue = aliasSpinnerSetValue;
    }

    public String getAliasSpinnerGetMax() {
        return aliasSpinnerGetMax;
    }

    public void setAliasSpinnerGetMax(String aliasSpinnerGetMax) {
        this.aliasSpinnerGetMax = aliasSpinnerGetMax;
    }

    public String getAliasSpinnerSetMax() {
        return aliasSpinnerSetMax;
    }

    public void setAliasSpinnerSetMax(String aliasSpinnerSetMax) {
        this.aliasSpinnerSetMax = aliasSpinnerSetMax;
    }

    public String getAliasSpinnerGetMin() {
        return aliasSpinnerGetMin;
    }

    public void setAliasSpinnerGetMin(String aliasSpinnerGetMin) {
        this.aliasSpinnerGetMin = aliasSpinnerGetMin;
    }

    public String getAliasSpinnerSetMin() {
        return aliasSpinnerSetMin;
    }

    public void setAliasSpinnerSetMin(String aliasSpinnerSetMin) {
        this.aliasSpinnerSetMin = aliasSpinnerSetMin;
    }

    public String getAliasSpinnerGetStep() {
        return aliasSpinnerGetStep;
    }

    public void setAliasSpinnerGetStep(String aliasSpinnerGetStep) {
        this.aliasSpinnerGetStep = aliasSpinnerGetStep;
    }

    public String getAliasSpinnerSetStep() {
        return aliasSpinnerSetStep;
    }

    public void setAliasSpinnerSetStep(String aliasSpinnerSetStep) {
        this.aliasSpinnerSetStep = aliasSpinnerSetStep;
    }

    public String getAliasSlider() {
        return aliasSlider;
    }

    public void setAliasSlider(String aliasSlider) {
        this.aliasSlider = aliasSlider;
    }

    public String getAliasSliderGetValue() {
        return aliasSliderGetValue;
    }

    public void setAliasSliderGetValue(String aliasSliderGetValue) {
        this.aliasSliderGetValue = aliasSliderGetValue;
    }

    public String getAliasSliderSetValue() {
        return aliasSliderSetValue;
    }

    public void setAliasSliderSetValue(String aliasSliderSetValue) {
        this.aliasSliderSetValue = aliasSliderSetValue;
    }

    public String getAliasSliderGetMax() {
        return aliasSliderGetMax;
    }

    public void setAliasSliderGetMax(String aliasSliderGetMax) {
        this.aliasSliderGetMax = aliasSliderGetMax;
    }

    public String getAliasSliderSetMax() {
        return aliasSliderSetMax;
    }

    public void setAliasSliderSetMax(String aliasSliderSetMax) {
        this.aliasSliderSetMax = aliasSliderSetMax;
    }

    public String getAliasSliderGetMin() {
        return aliasSliderGetMin;
    }

    public void setAliasSliderGetMin(String aliasSliderGetMin) {
        this.aliasSliderGetMin = aliasSliderGetMin;
    }

    public String getAliasSliderSetMin() {
        return aliasSliderSetMin;
    }

    public void setAliasSliderSetMin(String aliasSliderSetMin) {
        this.aliasSliderSetMin = aliasSliderSetMin;
    }

    public String getAliasSliderGetOrientation() {
        return aliasSliderGetOrientation;
    }

    public void setAliasSliderGetOrientation(String aliasSliderGetOrientation) {
        this.aliasSliderGetOrientation = aliasSliderGetOrientation;
    }

    public String getAliasSliderSetOrientation() {
        return aliasSliderSetOrientation;
    }

    public void setAliasSliderSetOrientation(String aliasSliderSetOrientation) {
        this.aliasSliderSetOrientation = aliasSliderSetOrientation;
    }

    public String getAliasGetMenuBar() {
        return aliasGetMenuBar;
    }

    public void setAliasGetMenuBar(String aliasGetMenuBar) {
        this.aliasGetMenuBar = aliasGetMenuBar;
    }

    public String getAliasSetMenuBar() {
        return aliasSetMenuBar;
    }

    public void setAliasSetMenuBar(String aliasSetMenuBar) {
        this.aliasSetMenuBar = aliasSetMenuBar;
    }

    public String getAliasMenuBar() {
        return aliasMenuBar;
    }

    public void setAliasMenuBar(String aliasMenuBar) {
        this.aliasMenuBar = aliasMenuBar;
    }

    public String getAliasMenuBarAdd() {
        return aliasMenuBarAdd;
    }

    public void setAliasMenuBarAdd(String aliasMenuBarAdd) {
        this.aliasMenuBarAdd = aliasMenuBarAdd;
    }

    public String getAliasMenuBarGet() {
        return aliasMenuBarGet;
    }

    public void setAliasMenuBarGet(String aliasMenuBarGet) {
        this.aliasMenuBarGet = aliasMenuBarGet;
    }

    public String getAliasMenuBarRemove() {
        return aliasMenuBarRemove;
    }

    public void setAliasMenuBarRemove(String aliasMenuBarRemove) {
        this.aliasMenuBarRemove = aliasMenuBarRemove;
    }

    public String getAliasMenuBarNb() {
        return aliasMenuBarNb;
    }

    public void setAliasMenuBarNb(String aliasMenuBarNb) {
        this.aliasMenuBarNb = aliasMenuBarNb;
    }

    public String getAliasAbsMenu() {
        return aliasAbsMenu;
    }

    public void setAliasAbsMenu(String aliasAbsMenu) {
        this.aliasAbsMenu = aliasAbsMenu;
    }

    public String getAliasAbsMenuGetParent() {
        return aliasAbsMenuGetParent;
    }

    public void setAliasAbsMenuGetParent(String aliasAbsMenuGetParent) {
        this.aliasAbsMenuGetParent = aliasAbsMenuGetParent;
    }

    public String getAliasAbsMenuIsEnabled() {
        return aliasAbsMenuIsEnabled;
    }

    public void setAliasAbsMenuIsEnabled(String aliasAbsMenuIsEnabled) {
        this.aliasAbsMenuIsEnabled = aliasAbsMenuIsEnabled;
    }

    public String getAliasMenu() {
        return aliasMenu;
    }

    public void setAliasMenu(String aliasMenu) {
        this.aliasMenu = aliasMenu;
    }

    public String getAliasMenuAdd() {
        return aliasMenuAdd;
    }

    public void setAliasMenuAdd(String aliasMenuAdd) {
        this.aliasMenuAdd = aliasMenuAdd;
    }

    public String getAliasMenuGet() {
        return aliasMenuGet;
    }

    public void setAliasMenuGet(String aliasMenuGet) {
        this.aliasMenuGet = aliasMenuGet;
    }

    public String getAliasMenuRemove() {
        return aliasMenuRemove;
    }

    public void setAliasMenuRemove(String aliasMenuRemove) {
        this.aliasMenuRemove = aliasMenuRemove;
    }

    public String getAliasMenuNb() {
        return aliasMenuNb;
    }

    public void setAliasMenuNb(String aliasMenuNb) {
        this.aliasMenuNb = aliasMenuNb;
    }

    public String getAliasAbsMenuSetEnabled() {
        return aliasAbsMenuSetEnabled;
    }

    public void setAliasAbsMenuSetEnabled(String aliasAbsMenuSetEnabled) {
        this.aliasAbsMenuSetEnabled = aliasAbsMenuSetEnabled;
    }

    public String getAliasAbsMenuSetDeepEnabled() {
        return aliasAbsMenuSetDeepEnabled;
    }

    public void setAliasAbsMenuSetDeepEnabled(String aliasAbsMenuSetDeepEnabled) {
        this.aliasAbsMenuSetDeepEnabled = aliasAbsMenuSetDeepEnabled;
    }

    public String getAliasAbsMenuGetText() {
        return aliasAbsMenuGetText;
    }

    public void setAliasAbsMenuGetText(String aliasAbsMenuGetText) {
        this.aliasAbsMenuGetText = aliasAbsMenuGetText;
    }

    public String getAliasAbsMenuSetText() {
        return aliasAbsMenuSetText;
    }

    public void setAliasAbsMenuSetText(String aliasAbsMenuSetText) {
        this.aliasAbsMenuSetText = aliasAbsMenuSetText;
    }

    public String getAliasMenuAddSeparator() {
        return aliasMenuAddSeparator;
    }

    public void setAliasMenuAddSeparator(String aliasMenuAddSeparator) {
        this.aliasMenuAddSeparator = aliasMenuAddSeparator;
    }

    public String getAliasAbsMenuItem() {
        return aliasAbsMenuItem;
    }

    public void setAliasAbsMenuItem(String aliasAbsMenuItem) {
        this.aliasAbsMenuItem = aliasAbsMenuItem;
    }

    public String getAliasAbsMenuItemAddAction() {
        return aliasAbsMenuItemAddAction;
    }

    public void setAliasAbsMenuItemAddAction(String aliasAbsMenuItemAddAction) {
        this.aliasAbsMenuItemAddAction = aliasAbsMenuItemAddAction;
    }

    public String getAliasMenuItem() {
        return aliasMenuItem;
    }

    public void setAliasMenuItem(String aliasMenuItem) {
        this.aliasMenuItem = aliasMenuItem;
    }

    public String getAliasMenuItemCheck() {
        return aliasMenuItemCheck;
    }

    public void setAliasMenuItemCheck(String aliasMenuItemCheck) {
        this.aliasMenuItemCheck = aliasMenuItemCheck;
    }

    public String getAliasMenuItemCheckIsSelected() {
        return aliasMenuItemCheckIsSelected;
    }

    public void setAliasMenuItemCheckIsSelected(String aliasMenuItemCheckIsSelected) {
        this.aliasMenuItemCheckIsSelected = aliasMenuItemCheckIsSelected;
    }

    public String getAliasMenuItemCheckSetSelected() {
        return aliasMenuItemCheckSetSelected;
    }

    public void setAliasMenuItemCheckSetSelected(String aliasMenuItemCheckSetSelected) {
        this.aliasMenuItemCheckSetSelected = aliasMenuItemCheckSetSelected;
    }

    public void otherAlias(String _lang, StringMap<String> _cust) {
        super.otherAlias(_lang,_cust);
        StringMap<String> util_ = ExtractFromFiles.getMessagesFromLocaleClass("resources_lg_gui/aliases",_lang,"typesgui");
        otherAliasGui(util_,_cust);
    }

    public void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        super.allAlias(_util,_cust);
        otherAliasGui(_util,_cust);
    }
    private void otherAliasGui(StringMap<String> _util, StringMap<String> _cust) {
        setAliasTreeNode(get(_util,_cust,"TreeNode"));
        setAliasTree(get(_util,_cust,"Tree"));
        setAliasTableGui(get(_util,_cust,"TableGui"));
        setAliasButton(get(_util,_cust,"Button"));
        setAliasConfirm(get(_util,_cust,"Confirm"));
        setAliasFrame(get(_util,_cust,"Frame"));
        setAliasDialog(get(_util,_cust,"Dialog"));
        setAliasPanel(get(_util,_cust,"Panel"));
        setAliasProgBar(get(_util,_cust,"ProgBar"));
        setAliasImageSet(get(_util,_cust,"ImageSet"));
        setAliasArgs(get(_util,_cust,"Args"));
        setAliasKeyEvent(get(_util,_cust,"KeyEvent"));
        setAliasRadio(get(_util,_cust,"Radio"));
        setAliasCheckBox(get(_util,_cust,"CheckBox"));
        setAliasSetFont(get(_util,_cust,"SetFont"));
        setAliasSpinner(get(_util,_cust,"Spinner"));
        setAliasDispose(get(_util,_cust,"Dispose"));
        setAliasGrList(get(_util,_cust,"GrList"));
        setAliasGetFont(get(_util,_cust,"GetFont"));
        setAliasImage(get(_util,_cust,"Image"));
        setAliasSlider(get(_util,_cust,"Slider"));
        setAliasPaintAdd(get(_util,_cust,"PaintAdd"));
        setAliasCount(get(_util,_cust,"Count"));
        setAliasPack(get(_util,_cust,"Pack"));
        setAliasColor(get(_util,_cust,"Color"));
        setAliasWindow(get(_util,_cust,"Window"));
        setAliasCompBack(get(_util,_cust,"CompBack"));
        setAliasPaint(get(_util,_cust,"Paint"));
        setAliasCompLoc(get(_util,_cust,"CompLoc"));
        setAliasImageGet(get(_util,_cust,"ImageGet"));
        setAliasRender(get(_util,_cust,"Render"));
        setAliasCombo(get(_util,_cust,"Combo"));
        setAliasAddCompo(get(_util,_cust,"AddCompo"));
        setAliasInput(get(_util,_cust,"Input"));
        setAliasFont(get(_util,_cust,"Font"));
        setAliasCompFore(get(_util,_cust,"CompFore"));
        setAliasKeyTyped(get(_util,_cust,"KeyTyped"));
        setAliasColorRed(get(_util,_cust,"ColorRed"));
        setAliasTextArea(get(_util,_cust,"TextArea"));
        setAliasFontGetSize(get(_util,_cust,"FontGetSize"));
        setAliasDimension(get(_util,_cust,"Dimension"));
        setAliasProgBarOr(get(_util,_cust,"ProgBarOr"));
        setAliasFontIsBold(get(_util,_cust,"FontIsBold"));
        setAliasDimensionGetHeight(get(_util,_cust,"DimensionGetHeight"));
        setAliasPanelBorderNorth(get(_util,_cust,"PanelBorderNorth"));
        setAliasPanelBorderSouth(get(_util,_cust,"PanelBorderSouth"));
        setAliasRemoveWindowListener(get(_util,_cust,"RemoveWindowListener"));
        setAliasWindowTypeRelative(get(_util,_cust,"WindowTypeRelative"));
        setAliasPanelBorderAfterLast(get(_util,_cust,"PanelBorderAfterLast"));
        setAliasFontGetName(get(_util,_cust,"FontGetName"));
        setAliasDimensionGetWidth(get(_util,_cust,"DimensionGetWidth"));
        setAliasComponent(get(_util,_cust,"Component"));
        setAliasPanelBorderAfterLineEnds(get(_util,_cust,"PanelBorderAfterLineEnds"));
        setAliasComponentSetAutoscrolls(get(_util,_cust,"ComponentSetAutoscrolls"));
        setAliasComponentIsAutoscrolls(get(_util,_cust,"ComponentIsAutoscrolls"));
        setAliasComponentGetWidth(get(_util,_cust,"ComponentGetWidth"));
        setAliasComponentGetHeight(get(_util,_cust,"ComponentGetHeight"));
        setAliasPanelBorderEast(get(_util,_cust,"PanelBorderEast"));
        setAliasProgBarMax(get(_util,_cust,"ProgBarMax"));
        setAliasGetWindowListeners(get(_util,_cust,"GetWindowListeners"));
        setAliasPanelBorderBeforeFirst(get(_util,_cust,"PanelBorderBeforeFirst"));
        setAliasTextLabel(get(_util,_cust,"TextLabel"));
        setAliasPanelBorderWest(get(_util,_cust,"PanelBorderWest"));
        setAliasProgBarMin(get(_util,_cust,"ProgBarMin"));
        setAliasProgBarValue(get(_util,_cust,"ProgBarValue"));
        setAliasPanelBorder(get(_util,_cust,"PanelBorder"));
        setAliasFontIsItalic(get(_util,_cust,"FontIsItalic"));
        setAliasImageLabel(get(_util,_cust,"ImageLabel"));
        setAliasPanelBorderCenter(get(_util,_cust,"PanelBorderCenter"));
        setAliasFontStringWidth(get(_util,_cust,"FontStringWidth"));
        setAliasTableSetColumns(get(_util,_cust,"TableSetColumns"));
        setAliasTreeIsRootVisible(get(_util,_cust,"TreeIsRootVisible"));
        setAliasTreeNodeRemoveFromParent(get(_util,_cust,"TreeNodeRemoveFromParent"));
        setAliasTreeNodeSetUserObject(get(_util,_cust,"TreeNodeSetUserObject"));
        setAliasTreeReload(get(_util,_cust,"TreeReload"));
        setAliasTreeAddTreeListener(get(_util,_cust,"TreeAddTreeListener"));
        setAliasTreeNodeNb(get(_util,_cust,"TreeNodeNb"));
        setAliasTableIsMultiple(get(_util,_cust,"TableIsMultiple"));
        setAliasTableListener(get(_util,_cust,"TableListener"));
        setAliasTableGetRowCount(get(_util,_cust,"TableGetRowCount"));
        setAliasTreeNodeIsAncestor(get(_util,_cust,"TreeNodeIsAncestor"));
        setAliasTreeNodeGetNextSibling(get(_util,_cust,"TreeNodeGetNextSibling"));
        setAliasTreeNodeGetLastChild(get(_util,_cust,"TreeNodeGetLastChild"));
        setAliasTreeSetRootVisible(get(_util,_cust,"TreeSetRootVisible"));
        setAliasTableSetMultiple(get(_util,_cust,"TableSetMultiple"));
        setAliasTreeNodeGetParentNode(get(_util,_cust,"TreeNodeGetParentNode"));
        setAliasTreeNodeRemove(get(_util,_cust,"TreeNodeRemove"));
        setAliasTableGetSelectedRow(get(_util,_cust,"TableGetSelectedRow"));
        setAliasTreeNodeGetFirstChild(get(_util,_cust,"TreeNodeGetFirstChild"));
        setAliasTreeNodeGetUserObject(get(_util,_cust,"TreeNodeGetUserObject"));
        setAliasTableGetSelectedRows(get(_util,_cust,"TableGetSelectedRows"));
        setAliasTableValueTableChanged(get(_util,_cust,"TableValueTableChanged"));
        setAliasTableGetColumnCount(get(_util,_cust,"TableGetColumnCount"));
        setAliasTableGetRowAtPoint(get(_util,_cust,"TableGetRowAtPoint"));
        setAliasTableSetRowCount(get(_util,_cust,"TableSetRowCount"));
        setAliasTableGetColumnName(get(_util,_cust,"TableGetColumnName"));
        setAliasTableGetValue(get(_util,_cust,"TableGetValue"));
        setAliasTreeNodeIsDescendant(get(_util,_cust,"TreeNodeIsDescendant"));
        setAliasTableSetValue(get(_util,_cust,"TableSetValue"));
        setAliasTreeGetSelected(get(_util,_cust,"TreeGetSelected"));
        setAliasTableGetSelectedRowCount(get(_util,_cust,"TableGetSelectedRowCount"));
        setAliasTableGetColumnAtPoint(get(_util,_cust,"TableGetColumnAtPoint"));
        setAliasConfirmOk(get(_util,_cust,"ConfirmOk"));
        setAliasConfirmYesNo(get(_util,_cust,"ConfirmYesNo"));
        setAliasTableRemoveInterval(get(_util,_cust,"TableRemoveInterval"));
        setAliasConfirmFieldOk(get(_util,_cust,"ConfirmFieldOk"));
        setAliasDialogSetModal(get(_util,_cust,"DialogSetModal"));
        setAliasTableApplyChanges(get(_util,_cust,"TableApplyChanges"));
        setAliasTableIsReorder(get(_util,_cust,"TableIsReorder"));
        setAliasConfirmFieldYes(get(_util,_cust,"ConfirmFieldYes"));
        setAliasTableSetReorder(get(_util,_cust,"TableSetReorder"));
        setAliasConfirmField(get(_util,_cust,"ConfirmField"));
        setAliasConfirmFieldNo(get(_util,_cust,"ConfirmFieldNo"));
        setAliasTableAddSelect(get(_util,_cust,"TableAddSelect"));
        setAliasConfirmMessage(get(_util,_cust,"ConfirmMessage"));
        setAliasTableAddHeader(get(_util,_cust,"TableAddHeader"));
        setAliasConfirmFull(get(_util,_cust,"ConfirmFull"));
        setAliasDialogIsModal(get(_util,_cust,"DialogIsModal"));
        setAliasWindowType(get(_util,_cust,"WindowType"));
        setAliasTableAddInterval(get(_util,_cust,"TableAddInterval"));
        setAliasTableMoveColumn(get(_util,_cust,"TableMoveColumn"));
        setAliasConfirmFieldCancel(get(_util,_cust,"ConfirmFieldCancel"));
        setAliasTextAreaSetSelectionStart(get(_util,_cust,"TextAreaSetSelectionStart"));
        setAliasTreeListener(get(_util,_cust,"TreeListener"));
        setAliasTreeNodeAdd(get(_util,_cust,"TreeNodeAdd"));
        setAliasTreeListenerValueChanged(get(_util,_cust,"TreeListenerValueChanged"));
        setAliasTreeNodeInsert(get(_util,_cust,"TreeNodeInsert"));
        setAliasActionEvent(get(_util,_cust,"ActionEvent"));
        setAliasChangeListener(get(_util,_cust,"ChangeListener"));
        setAliasActionListener(get(_util,_cust,"ActionListener"));
        setAliasActionPerformed(get(_util,_cust,"ActionPerformed"));
        setAliasAddChange(get(_util,_cust,"AddChange"));
        setAliasStateChanged(get(_util,_cust,"StateChanged"));
        setAliasTreeNodeGetPreviousSibling(get(_util,_cust,"TreeNodeGetPreviousSibling"));
        setAliasComponentGetPreferredSize(get(_util,_cust,"ComponentGetPreferredSize"));
        setAliasSplitPaneSetDividerLocation(get(_util,_cust,"SplitPaneSetDividerLocation"));
        setAliasSplitPaneIsOneTouchExpandable(get(_util,_cust,"SplitPaneIsOneTouchExpandable"));
        setAliasSplitPaneSetOneTouchExpandable(get(_util,_cust,"SplitPaneSetOneTouchExpandable"));
        setAliasSplitPaneIsContinuousLayout(get(_util,_cust,"SplitPaneIsContinuousLayout"));
        setAliasScrollPaneHorizontalValue(get(_util,_cust,"ScrollPaneHorizontalValue"));
        setAliasTreeNodeRemoveAllChildren(get(_util,_cust,"TreeNodeRemoveAllChildren"));
        setAliasPanelBorderBeforeLineBegins(get(_util,_cust,"PanelBorderBeforeLineBegins"));
        setAliasComponentSetPreferredSize(get(_util,_cust,"ComponentSetPreferredSize"));
        setAliasSplitPaneGetDividerLocation(get(_util,_cust,"SplitPaneGetDividerLocation"));
        setAliasSplitPaneSetContinuousLayout(get(_util,_cust,"SplitPaneSetContinuousLayout"));
        setAliasMouseEventIsCtrl(get(_util,_cust,"MouseEventIsCtrl"));
        setAliasMouseReleased(get(_util,_cust,"MouseReleased"));
        setAliasMouseEventIsShift(get(_util,_cust,"MouseEventIsShift"));
        setAliasScrollPaneGetView(get(_util,_cust,"ScrollPaneGetView"));
        setAliasPaintRefresh(get(_util,_cust,"PaintRefresh"));
        setAliasMouseExited(get(_util,_cust,"MouseExited"));
        setAliasSplitPane(get(_util,_cust,"SplitPane"));
        setAliasSplitPaneSetRight(get(_util,_cust,"SplitPaneSetRight"));
        setAliasSetVisible(get(_util,_cust,"SetVisible"));
        setAliasRemoveCompo(get(_util,_cust,"RemoveCompo"));
        setAliasMouseClicked(get(_util,_cust,"MouseClicked"));
        setAliasMouseMoved(get(_util,_cust,"MouseMoved"));
        setAliasMouseEvent(get(_util,_cust,"MouseEvent"));
        setAliasMouseEventGetClicks(get(_util,_cust,"MouseEventGetClicks"));
        setAliasMouseEventIsAlt(get(_util,_cust,"MouseEventIsAlt"));
        setAliasMouseEventGetSecond(get(_util,_cust,"MouseEventGetSecond"));
        setAliasScrollPane(get(_util,_cust,"ScrollPane"));
        setAliasScrollPaneValidate(get(_util,_cust,"ScrollPaneValidate"));
        setAliasSplitPaneSetDividerSize(get(_util,_cust,"SplitPaneSetDividerSize"));
        setAliasSplitPaneGetLeft(get(_util,_cust,"SplitPaneGetLeft"));
        setAliasSplitPaneGetDividerSize(get(_util,_cust,"SplitPaneGetDividerSize"));
        setAliasSplitPaneValidate(get(_util,_cust,"SplitPaneValidate"));
        setAliasMouseEntered(get(_util,_cust,"MouseEntered"));
        setAliasScrollPaneSetView(get(_util,_cust,"ScrollPaneSetView"));
        setAliasSplitPaneSetLeft(get(_util,_cust,"SplitPaneSetLeft"));
        setAliasMousePressed(get(_util,_cust,"MousePressed"));
        setAliasMouseListener(get(_util,_cust,"MouseListener"));
        setAliasIsVisible(get(_util,_cust,"IsVisible"));
        setAliasMouseDragged(get(_util,_cust,"MouseDragged"));
        setAliasScrollPaneVerticalValue(get(_util,_cust,"ScrollPaneVerticalValue"));
        setAliasSplitPaneGetRight(get(_util,_cust,"SplitPaneGetRight"));
        setAliasMouseEventGetFirst(get(_util,_cust,"MouseEventGetFirst"));
        setAliasGetIndexCompo(get(_util,_cust,"GetIndexCompo"));
        setAliasImageFillPolygon(get(_util,_cust,"ImageFillPolygon"));
        setAliasComponentIsVisible(get(_util,_cust,"ComponentIsVisible"));
        setAliasImageDrawRect(get(_util,_cust,"ImageDrawRect"));
        setAliasComponentSetVisible(get(_util,_cust,"ComponentSetVisible"));
        setAliasComponentSetSize(get(_util,_cust,"ComponentSetSize"));
        setAliasImageGetColor(get(_util,_cust,"ImageGetColor"));
        setAliasImageDrawOval(get(_util,_cust,"ImageDrawOval"));
        setAliasComponentInvokeLater(get(_util,_cust,"ComponentInvokeLater"));
        setAliasImageGetHeight(get(_util,_cust,"ImageGetHeight"));
        setAliasImageSetFont(get(_util,_cust,"ImageSetFont"));
        setAliasImageDispose(get(_util,_cust,"ImageDispose"));
        setAliasImageGetFont(get(_util,_cust,"ImageGetFont"));
        setAliasColorIsTransparent(get(_util,_cust,"ColorIsTransparent"));
        setAliasImageDrawLine(get(_util,_cust,"ImageDrawLine"));
        setAliasImageFillRect(get(_util,_cust,"ImageFillRect"));
        setAliasColorBlue(get(_util,_cust,"ColorBlue"));
        setAliasImageDrawPolygon(get(_util,_cust,"ImageDrawPolygon"));
        setAliasComponentRepaint(get(_util,_cust,"ComponentRepaint"));
        setAliasImageIsWithAlpha(get(_util,_cust,"ImageIsWithAlpha"));
        setAliasImageGetWidth(get(_util,_cust,"ImageGetWidth"));
        setAliasColorGreen(get(_util,_cust,"ColorGreen"));
        setAliasComponentSetPaint(get(_util,_cust,"ComponentSetPaint"));
        setAliasRemoveAll(get(_util,_cust,"RemoveAll"));
        setAliasImageFillOval(get(_util,_cust,"ImageFillOval"));
        setAliasColorAlpha(get(_util,_cust,"ColorAlpha"));
        setAliasImageDraw(get(_util,_cust,"ImageDraw"));
        setAliasImageSetColor(get(_util,_cust,"ImageSetColor"));
        setAliasSetContent(get(_util,_cust,"SetContent"));
        setAliasGetNextCompo(get(_util,_cust,"GetNextCompo"));
        setAliasComponentGetPaint(get(_util,_cust,"ComponentGetPaint"));
        setAliasGetParentCompo(get(_util,_cust,"GetParentCompo"));
        setAliasSetLabelText(get(_util,_cust,"SetLabelText"));
        setAliasSetLabelImage(get(_util,_cust,"SetLabelImage"));
        setAliasTabbedPaneSetTitle(get(_util,_cust,"TabbedPaneSetTitle"));
        setAliasTabbedPaneAdd(get(_util,_cust,"TabbedPaneAdd"));
        setAliasTabbedPaneNb(get(_util,_cust,"TabbedPaneNb"));
        setAliasPanelFlow(get(_util,_cust,"PanelFlow"));
        setAliasTabbedPaneRemove(get(_util,_cust,"TabbedPaneRemove"));
        setAliasAddWindowListener(get(_util,_cust,"AddWindowListener"));
        setAliasPaintMethod(get(_util,_cust,"PaintMethod"));
        setAliasPanelPageBox(get(_util,_cust,"PanelPageBox"));
        setAliasPanelAbsolute(get(_util,_cust,"PanelAbsolute"));
        setAliasPanelGrid(get(_util,_cust,"PanelGrid"));
        setAliasTabbedPaneGet(get(_util,_cust,"TabbedPaneGet"));
        setAliasTabbedPane(get(_util,_cust,"TabbedPane"));
        setAliasTabbedPaneGetTitle(get(_util,_cust,"TabbedPaneGetTitle"));
        setAliasPanelValidate(get(_util,_cust,"PanelValidate"));
        setAliasTabbedPaneIndex(get(_util,_cust,"TabbedPaneIndex"));
        setAliasTabbedPaneSet(get(_util,_cust,"TabbedPaneSet"));
        setAliasTabbedPaneSelIndex(get(_util,_cust,"TabbedPaneSelIndex"));
        setAliasAddListener(get(_util,_cust,"AddListener"));
        setAliasMenuItemCheck(get(_util,_cust,"MenuItemCheck"));
        setAliasMenuAddSeparator(get(_util,_cust,"MenuAddSeparator"));
        setAliasAbsMenuItem(get(_util,_cust,"AbsMenuItem"));
        setAliasMenuItemCheckIsSelected(get(_util,_cust,"MenuItemCheckIsSelected"));
        setAliasAbsMenuSetEnabled(get(_util,_cust,"AbsMenuSetEnabled"));
        setAliasMenuRemove(get(_util,_cust,"MenuRemove"));
        setAliasAbsMenuItemAddAction(get(_util,_cust,"AbsMenuItemAddAction"));
        setAliasAbsMenuSetText(get(_util,_cust,"AbsMenuSetText"));
        setAliasAbsMenuGetText(get(_util,_cust,"AbsMenuGetText"));
        setAliasMenuItemCheckSetSelected(get(_util,_cust,"MenuItemCheckSetSelected"));
        setAliasAbsMenuSetDeepEnabled(get(_util,_cust,"AbsMenuSetDeepEnabled"));
        setAliasCompToolTip(get(_util,_cust,"CompToolTip"));
        setAliasWindowClosed(get(_util,_cust,"WindowClosed"));
        setAliasMouseEventIsRight(get(_util,_cust,"MouseEventIsRight"));
        setAliasKeyPressed(get(_util,_cust,"KeyPressed"));
        setAliasMouseEventIsLeft(get(_util,_cust,"MouseEventIsLeft"));
        setAliasWheelListener(get(_util,_cust,"WheelListener"));
        setAliasCompBorLine(get(_util,_cust,"CompBorLine"));
        setAliasKeyEventCode(get(_util,_cust,"KeyEventCode"));
        setAliasRequestFocus(get(_util,_cust,"RequestFocus"));
        setAliasCompBorRaise(get(_util,_cust,"CompBorRaise"));
        setAliasWheelEvent(get(_util,_cust,"WheelEvent"));
        setAliasKeyEventIsShift(get(_util,_cust,"KeyEventIsShift"));
        setAliasCompGetFirstPos(get(_util,_cust,"CompGetFirstPos"));
        setAliasCompBorTitle(get(_util,_cust,"CompBorTitle"));
        setAliasAddKeyListener(get(_util,_cust,"AddKeyListener"));
        setAliasKeyEventIsAlt(get(_util,_cust,"KeyEventIsAlt"));
        setAliasKeyListener(get(_util,_cust,"KeyListener"));
        setAliasKeyEventChar(get(_util,_cust,"KeyEventChar"));
        setAliasWindowListener(get(_util,_cust,"WindowListener"));
        setAliasWheelMove(get(_util,_cust,"WheelMove"));
        setAliasCompGetSecondPos(get(_util,_cust,"CompGetSecondPos"));
        setAliasKeyEventIsCtrl(get(_util,_cust,"KeyEventIsCtrl"));
        setAliasMouseEventIsMiddle(get(_util,_cust,"MouseEventIsMiddle"));
        setAliasWheelRotatedClicks(get(_util,_cust,"WheelRotatedClicks"));
        setAliasCompBorLower(get(_util,_cust,"CompBorLower"));
        setAliasWindowOpened(get(_util,_cust,"WindowOpened"));
        setAliasCompFocusable(get(_util,_cust,"CompFocusable"));
        setAliasCompOpaque(get(_util,_cust,"CompOpaque"));
        setAliasWindowIconified(get(_util,_cust,"WindowIconified"));
        setAliasKeyReleased(get(_util,_cust,"KeyReleased"));
        setAliasAddWheelListener(get(_util,_cust,"AddWheelListener"));
        setAliasWindowClosing(get(_util,_cust,"WindowClosing"));
        setAliasGrListSet(get(_util,_cust,"GrListSet"));
        setAliasWindowActivated(get(_util,_cust,"WindowActivated"));
        setAliasWindowDeactivated(get(_util,_cust,"WindowDeactivated"));
        setAliasRenderSetHeight(get(_util,_cust,"RenderSetHeight"));
        setAliasListSelection(get(_util,_cust,"ListSelection"));
        setAliasRenderGetWidth(get(_util,_cust,"RenderGetWidth"));
        setAliasRenderGetHeight(get(_util,_cust,"RenderGetHeight"));
        setAliasGrListGetSelectedIndexes(get(_util,_cust,"GrListGetSelectedIndexes"));
        setAliasGrListGetSelection(get(_util,_cust,"GrListGetSelection"));
        setAliasRenderSetWidth(get(_util,_cust,"RenderSetWidth"));
        setAliasInputIsEnabled(get(_util,_cust,"InputIsEnabled"));
        setAliasGrListUpdateGraphics(get(_util,_cust,"GrListUpdateGraphics"));
        setAliasGrListGetVisibleRowCount(get(_util,_cust,"GrListGetVisibleRowCount"));
        setAliasGrListClearSelection(get(_util,_cust,"GrListClearSelection"));
        setAliasRenderGetPaint(get(_util,_cust,"RenderGetPaint"));
        setAliasWindowEvent(get(_util,_cust,"WindowEvent"));
        setAliasGrListClear(get(_util,_cust,"GrListClear"));
        setAliasGrListSetRender(get(_util,_cust,"GrListSetRender"));
        setAliasGrListSetSelection(get(_util,_cust,"GrListSetSelection"));
        setAliasGrListRemove(get(_util,_cust,"GrListRemove"));
        setAliasInputSetEnabled(get(_util,_cust,"InputSetEnabled"));
        setAliasWindowDeiconified(get(_util,_cust,"WindowDeiconified"));
        setAliasValueChanged(get(_util,_cust,"ValueChanged"));
        setAliasGrListGetListView(get(_util,_cust,"GrListGetListView"));
        setAliasRenderSetPaint(get(_util,_cust,"RenderSetPaint"));
        setAliasGrListAdd(get(_util,_cust,"GrListAdd"));
        setAliasGrListGetRender(get(_util,_cust,"GrListGetRender"));
        setAliasGrListSetSelectedIndexes(get(_util,_cust,"GrListSetSelectedIndexes"));
        setAliasGrListSetVisibleRowCount(get(_util,_cust,"GrListSetVisibleRowCount"));
        setAliasComboGetSelectedItem(get(_util,_cust,"ComboGetSelectedItem"));
        setAliasComboAddItem(get(_util,_cust,"ComboAddItem"));
        setAliasPopupMenuGetComp(get(_util,_cust,"PopupMenuGetComp"));
        setAliasComboGetListener(get(_util,_cust,"ComboGetListener"));
        setAliasComboGetSelectedIndex(get(_util,_cust,"ComboGetSelectedIndex"));
        setAliasPopupMenu(get(_util,_cust,"PopupMenu"));
        setAliasPopupMenuAddMenu(get(_util,_cust,"PopupMenuAddMenu"));
        setAliasPopupMenuNbMenu(get(_util,_cust,"PopupMenuNbMenu"));
        setAliasTextFieldAddPopup(get(_util,_cust,"TextFieldAddPopup"));
        setAliasPopupMenuNbComp(get(_util,_cust,"PopupMenuNbComp"));
        setAliasComboRemoveAllItems(get(_util,_cust,"ComboRemoveAllItems"));
        setAliasPopupMenuShow(get(_util,_cust,"PopupMenuShow"));
        setAliasPopupMenuGetMenu(get(_util,_cust,"PopupMenuGetMenu"));
        setAliasTextFieldAuto(get(_util,_cust,"TextFieldAuto"));
        setAliasRadioGetText(get(_util,_cust,"RadioGetText"));
        setAliasButtonGroupAdd(get(_util,_cust,"ButtonGroupAdd"));
        setAliasButtonGroup(get(_util,_cust,"ButtonGroup"));
        setAliasPopupMenuAdd(get(_util,_cust,"PopupMenuAdd"));
        setAliasRadioIsSelected(get(_util,_cust,"RadioIsSelected"));
        setAliasComboSetListener(get(_util,_cust,"ComboSetListener"));
        setAliasTextField(get(_util,_cust,"TextField"));
        setAliasComboRemoveItem(get(_util,_cust,"ComboRemoveItem"));
        setAliasRadioSetSelected(get(_util,_cust,"RadioSetSelected"));
        setAliasTextFieldGetText(get(_util,_cust,"TextFieldGetText"));
        setAliasPopupMenuRemoveMenu(get(_util,_cust,"PopupMenuRemoveMenu"));
        setAliasTextFieldAddDocument(get(_util,_cust,"TextFieldAddDocument"));
        setAliasComboGetSelectedIndexes(get(_util,_cust,"ComboGetSelectedIndexes"));
        setAliasTextFieldAddAction(get(_util,_cust,"TextFieldAddAction"));
        setAliasTextAreaGetText(get(_util,_cust,"TextAreaGetText"));
        setAliasRadioSetText(get(_util,_cust,"RadioSetText"));
        setAliasComboSelectItem(get(_util,_cust,"ComboSelectItem"));
        setAliasComboGetItemCount(get(_util,_cust,"ComboGetItemCount"));
        setAliasPopupMenuRemoveComp(get(_util,_cust,"PopupMenuRemoveComp"));
        setAliasTextFieldSetText(get(_util,_cust,"TextFieldSetText"));
        setAliasCheckBoxSetSelected(get(_util,_cust,"CheckBoxSetSelected"));
        setAliasCheckBoxAddAction(get(_util,_cust,"CheckBoxAddAction"));
        setAliasTextAreaSetSelectionEnd(get(_util,_cust,"TextAreaSetSelectionEnd"));
        setAliasSpinnerGetValue(get(_util,_cust,"SpinnerGetValue"));
        setAliasSliderGetMax(get(_util,_cust,"SliderGetMax"));
        setAliasTextAreaSelect(get(_util,_cust,"TextAreaSelect"));
        setAliasTextAreaReplaceRange(get(_util,_cust,"TextAreaReplaceRange"));
        setAliasSpinnerSetStep(get(_util,_cust,"SpinnerSetStep"));
        setAliasCheckBoxIsSelected(get(_util,_cust,"CheckBoxIsSelected"));
        setAliasTextAreaReplaceSelection(get(_util,_cust,"TextAreaReplaceSelection"));
        setAliasSpinnerSetValue(get(_util,_cust,"SpinnerSetValue"));
        setAliasSliderGetValue(get(_util,_cust,"SliderGetValue"));
        setAliasSliderSetValue(get(_util,_cust,"SliderSetValue"));
        setAliasSliderGetMin(get(_util,_cust,"SliderGetMin"));
        setAliasSliderSetMin(get(_util,_cust,"SliderSetMin"));
        setAliasTextAreaSetTabSize(get(_util,_cust,"TextAreaSetTabSize"));
        setAliasSpinnerGetMin(get(_util,_cust,"SpinnerGetMin"));
        setAliasCheckBoxSetText(get(_util,_cust,"CheckBoxSetText"));
        setAliasCheckBoxGetText(get(_util,_cust,"CheckBoxGetText"));
        setAliasTextAreaInsert(get(_util,_cust,"TextAreaInsert"));
        setAliasTextAreaSelectAll(get(_util,_cust,"TextAreaSelectAll"));
        setAliasTextAreaAppend(get(_util,_cust,"TextAreaAppend"));
        setAliasSpinnerGetMax(get(_util,_cust,"SpinnerGetMax"));
        setAliasSpinnerSetMax(get(_util,_cust,"SpinnerSetMax"));
        setAliasTextAreaGetTabSize(get(_util,_cust,"TextAreaGetTabSize"));
        setAliasTextAreaSetText(get(_util,_cust,"TextAreaSetText"));
        setAliasSpinnerSetMin(get(_util,_cust,"SpinnerSetMin"));
        setAliasSpinnerGetStep(get(_util,_cust,"SpinnerGetStep"));
        setAliasSpinnerSetRangeValue(get(_util,_cust,"SpinnerSetRangeValue"));
        setAliasTextAreaGetSelectedText(get(_util,_cust,"TextAreaGetSelectedText"));
        setAliasSliderSetMax(get(_util,_cust,"SliderSetMax"));
        setAliasSpinnerSetRange(get(_util,_cust,"SpinnerSetRange"));
        setAliasMenuBarGet(get(_util,_cust,"MenuBarGet"));
        setAliasMenuBarRemove(get(_util,_cust,"MenuBarRemove"));
        setAliasAbsMenuGetParent(get(_util,_cust,"AbsMenuGetParent"));
        setAliasAbsMenuIsEnabled(get(_util,_cust,"AbsMenuIsEnabled"));
        setAliasMenuBarAdd(get(_util,_cust,"MenuBarAdd"));
        setAliasGetMenuBar(get(_util,_cust,"GetMenuBar"));
        setAliasSliderGetOrientation(get(_util,_cust,"SliderGetOrientation"));
        setAliasMenuBarNb(get(_util,_cust,"MenuBarNb"));
        setAliasSetMenuBar(get(_util,_cust,"SetMenuBar"));
        setAliasSliderSetOrientation(get(_util,_cust,"SliderSetOrientation"));
        setAliasMenuBar(get(_util,_cust,"MenuBar"));
        setAliasMenuNb(get(_util,_cust,"MenuNb"));
        setAliasMenuAdd(get(_util,_cust,"MenuAdd"));
        setAliasMenu(get(_util,_cust,"Menu"));
        setAliasMenuItem(get(_util,_cust,"MenuItem"));
        setAliasAbsMenu(get(_util,_cust,"AbsMenu"));
        setAliasMenuGet(get(_util,_cust,"MenuGet"));
    }
    @Override
    public StringMap<StringList> allTableTypeFieldNames() {
        StringMap<StringList> f_ = super.allTableTypeFieldNames();
        f_.put(getAliasConfirm(),new StringList(
             getAliasConfirmFieldOk(),
             getAliasConfirmFieldYes(),
             getAliasConfirmFieldNo(),
             getAliasConfirmFieldCancel()
        ));
        f_.put(getAliasPanelBorder(),new StringList(
            getAliasPanelBorderAfterLast(),
            getAliasPanelBorderAfterLineEnds(),
            getAliasPanelBorderBeforeFirst(),
            getAliasPanelBorderBeforeLineBegins(),
            getAliasPanelBorderEast(),
            getAliasPanelBorderWest(),
            getAliasPanelBorderNorth(),
            getAliasPanelBorderSouth(),
            getAliasPanelBorderCenter()
        ));
        return f_;
    }

    @Override
    public StringMap<StringList> allTableTypeMethodNames() {
        StringMap<StringList> m_ = super.allTableTypeMethodNames();
        m_.put(getAliasConfirm(), new StringList(
                getAliasConfirmField(),
                getAliasConfirmFull(),
                getAliasConfirmMessage(),
                getAliasConfirmOk(),
                getAliasConfirmYesNo()));
        m_.put(getAliasFrame(), new StringList(
                getAliasPack(),
                getAliasWindow(),
                getAliasArgs(),
                getAliasAddWindowListener(),
                getAliasRemoveWindowListener(),
                getAliasGetWindowListeners(),
                getAliasDispose(),
                getAliasIsVisible(),
                getAliasSetVisible(),
                getAliasSetContent()));
        m_.put(getAliasDialog(), new StringList(
                getAliasPack(),
                getAliasWindow(),
                getAliasArgs(),
                getAliasAddWindowListener(),
                getAliasRemoveWindowListener(),
                getAliasGetWindowListeners(),
                getAliasDispose(),
                getAliasDialogIsModal(),
                getAliasDialogSetModal(),
                getAliasIsVisible(),
                getAliasSetVisible(),
                getAliasSetContent()));
        m_.put(getAliasWindowType(), new StringList(
                getAliasPack(),
                getAliasAddWindowListener(),
                getAliasRemoveWindowListener(),
                getAliasGetWindowListeners(),
                getAliasDispose(),
                getAliasWindowTypeRelative(),
                getAliasIsVisible(),
                getAliasSetVisible(),
                getAliasSetContent(),
                getAliasGetMenuBar(),
                getAliasSetMenuBar()));
        m_.put(getAliasPanel(), new StringList(
                getAliasCount(),
                getAliasGetIndexCompo(),
                getAliasAddCompo(),
                getAliasRemoveCompo(),
                getAliasPanelAbsolute(),
                getAliasPanelFlow(),
                getAliasPanelPageBox(),
                getAliasPanelGrid(),
                getAliasPanelValidate(),
                getAliasRemoveAll()));
        m_.put(getAliasTabbedPane(), new StringList(
                getAliasTabbedPaneNb(),
                getAliasTabbedPaneAdd(),
                getAliasTabbedPaneRemove(),
                getAliasTabbedPaneSelIndex(),
                getAliasTabbedPaneIndex(),
                getAliasTabbedPaneGet(),
                getAliasTabbedPaneGetTitle(),
                getAliasTabbedPaneSet(),
                getAliasTabbedPaneSetTitle(),
                getAliasRemoveAll()));
        m_.put(getAliasPanelBorder(), new StringList(
                getAliasCount(),
                getAliasGetIndexCompo(),
                getAliasAddCompo(),
                getAliasRemoveCompo(),
                getAliasPanelAbsolute(),
                getAliasPanelFlow(),
                getAliasPanelPageBox(),
                getAliasPanelGrid(),
                getAliasRemoveAll()));
        m_.put(getAliasMouseEvent(), new StringList(
                getAliasMouseEventGetFirst(),
                getAliasMouseEventGetSecond(),
                getAliasMouseEventGetClicks(),
                getAliasMouseEventIsAlt(),
                getAliasMouseEventIsShift(),
                getAliasMouseEventIsCtrl(),
                getAliasMouseEventIsLeft(),
                getAliasMouseEventIsRight(),
                getAliasMouseEventIsMiddle()));
        m_.put(getAliasWheelEvent(), new StringList(
                getAliasWheelRotatedClicks())
        );
        m_.put(getAliasKeyEvent(), new StringList(
                getAliasKeyEventCode(),
                getAliasKeyEventChar(),
                getAliasKeyEventIsAlt(),
                getAliasKeyEventIsShift(),
                getAliasKeyEventIsCtrl()));
        m_.put(getAliasComponent(), new StringList(
                getAliasGetParentCompo(),
                getAliasGetNextCompo(),
                getAliasComponentRepaint(),
                getAliasComponentGetPaint(),
                getAliasComponentSetPaint(),
                getAliasGetFont(),
                getAliasSetFont(),
                getAliasComponentGetHeight(),
                getAliasComponentGetWidth(),
                getAliasComponentIsAutoscrolls(),
                getAliasComponentSetAutoscrolls(),
                getAliasComponentGetPreferredSize(),
                getAliasComponentSetPreferredSize(),
                getAliasComponentSetSize(),
                getAliasComponentIsVisible(),
                getAliasComponentSetVisible(),
                getAliasComponentInvokeLater(),
                getAliasAddKeyListener(),
                getAliasAddWheelListener(),
                getAliasAddListener(),
                getAliasRequestFocus(),
                getAliasCompBack(),
                getAliasCompFocusable(),
                getAliasCompFore(),
                getAliasCompGetFirstPos(),
                getAliasCompGetSecondPos(),
                getAliasCompOpaque(),
                getAliasCompToolTip(),
                getAliasCompLoc(),
                getAliasCompBorLine(),
                getAliasCompBorLower(),
                getAliasCompBorRaise(),
                getAliasCompBorTitle()
        ));
        m_.put(getAliasDimension(), new StringList(
                getAliasDimensionGetHeight(),
                getAliasDimensionGetWidth()));
        m_.put(getAliasTextLabel(), new StringList(
                getAliasSetLabelText(),
                getAliasAddListener()));
        m_.put(getAliasImageLabel(), new StringList(
                getAliasSetLabelImage()));
        m_.put(getAliasFont(), new StringList(
                getAliasFontGetName(),
                getAliasFontGetSize(),
                getAliasFontIsBold(),
                getAliasFontIsItalic(),
                getAliasFontStringWidth()));
        m_.put(getAliasButton(), new StringList(
                getAliasAddListener()));
        m_.put(getAliasProgBar(), new StringList(
                getAliasProgBarOr(),
                getAliasProgBarValue(),
                getAliasProgBarMin(),
                getAliasProgBarMax()));
        m_.put(getAliasScrollPane(), new StringList(
                getAliasScrollPaneHorizontalValue(),
                getAliasScrollPaneVerticalValue(),
                getAliasScrollPaneGetView(),
                getAliasScrollPaneSetView(),
                getAliasScrollPaneValidate()));
        m_.put(getAliasSplitPane(), new StringList(
                getAliasSplitPaneGetDividerLocation(),
                getAliasSplitPaneSetDividerLocation(),
                getAliasSplitPaneGetDividerSize(),
                getAliasSplitPaneSetDividerSize(),
                getAliasSplitPaneGetLeft(),
                getAliasSplitPaneSetLeft(),
                getAliasSplitPaneGetRight(),
                getAliasSplitPaneSetRight(),
                getAliasSplitPaneIsContinuousLayout(),
                getAliasSplitPaneSetContinuousLayout(),
                getAliasSplitPaneIsOneTouchExpandable(),
                getAliasSplitPaneSetOneTouchExpandable(),
                getAliasSplitPaneValidate()));
        m_.put(getAliasInput(), new StringList(
                getAliasInputIsEnabled(),
                getAliasInputSetEnabled()));
        m_.put(getAliasCheckBox(), new StringList(
                getAliasCheckBoxAddAction(),
                getAliasCheckBoxGetText(),
                getAliasCheckBoxSetText(),
                getAliasCheckBoxIsSelected(),
                getAliasCheckBoxSetSelected())
        );
        m_.put(getAliasSpinner(), new StringList(
                getAliasSpinnerGetMax(),
                getAliasSpinnerGetMin(),
                getAliasSpinnerGetStep(),
                getAliasSpinnerGetValue(),
                getAliasSpinnerSetMax(),
                getAliasSpinnerSetMin(),
                getAliasSpinnerSetStep(),
                getAliasSpinnerSetValue(),
                getAliasSpinnerSetRange(),
                getAliasSpinnerSetRangeValue(),
                getAliasAddChange())
        );
        m_.put(getAliasSlider(), new StringList(
                getAliasSliderGetMax(),
                getAliasSliderGetMin(),
                getAliasSliderGetOrientation(),
                getAliasSliderGetValue(),
                getAliasSliderSetMax(),
                getAliasSliderSetMin(),
                getAliasSliderSetOrientation(),
                getAliasSliderSetValue(),
                getAliasAddChange())
        );
        m_.put(getAliasRadio(), new StringList(
                getAliasRadioGetText(),
                getAliasRadioSetText(),
                getAliasRadioIsSelected(),
                getAliasRadioSetSelected(),
                getAliasAddChange())
        );
        m_.put(getAliasButtonGroup(), new StringList(
                getAliasButtonGroupAdd())
        );
        m_.put(getAliasTextField(), new StringList(
                getAliasTextFieldAddAction(),
                getAliasTextFieldAddPopup(),
                getAliasTextFieldGetText(),
                getAliasTextFieldSetText())
        );
        m_.put(getAliasTextArea(), new StringList(
                getAliasTextAreaAppend(),
                getAliasTextAreaInsert(),
                getAliasTextAreaReplaceRange(),
                getAliasTextAreaReplaceSelection(),
                getAliasTextAreaGetSelectedText(),
                getAliasTextAreaSetSelectionStart(),
                getAliasTextAreaSetSelectionEnd(),
                getAliasTextAreaGetTabSize(),
                getAliasTextAreaSetTabSize(),
                getAliasTextAreaGetText(),
                getAliasTextAreaSetText(),
                getAliasTextAreaSelect(),
                getAliasTextAreaSelectAll())
        );
        m_.put(getAliasCombo(), new StringList(
                getAliasComboAddItem(),
                getAliasComboGetItemCount(),
                getAliasComboGetListener(),
                getAliasComboGetSelectedIndex(),
                getAliasComboGetSelectedIndexes(),
                getAliasComboGetSelectedItem(),
                getAliasComboRemoveAllItems(),
                getAliasComboRemoveItem(),
                getAliasComboSelectItem(),
                getAliasComboSetListener())
        );
        m_.put(getAliasGrList(), new StringList(
                getAliasGrListAdd(),
                getAliasGrListClear(),
                getAliasGrListClearSelection(),
                getAliasGrListGetListView(),
                getAliasGrListGetRender(),
                getAliasGrListGetSelectedIndexes(),
                getAliasGrListGetSelection(),
                getAliasGrListGetVisibleRowCount(),
                getAliasGrListRemove(),
                getAliasGrListSet(),
                getAliasGrListSetRender(),
                getAliasGrListSetSelectedIndexes(),
                getAliasGrListSetSelection(),
                getAliasGrListSetVisibleRowCount(),
                getAliasGrListUpdateGraphics())
        );
        m_.put(getAliasRender(), new StringList(
                getAliasRenderGetHeight(),
                getAliasRenderGetPaint(),
                getAliasRenderGetWidth(),
                getAliasRenderSetHeight(),
                getAliasRenderSetPaint(),
                getAliasRenderSetWidth()
        ));
        m_.put(getAliasPopupMenu(), new StringList(
                getAliasPopupMenuAdd(),
                getAliasPopupMenuAddMenu(),
                getAliasPopupMenuGetComp(),
                getAliasPopupMenuRemoveComp(),
                getAliasPopupMenuNbComp(),
                getAliasPopupMenuGetMenu(),
                getAliasPopupMenuRemoveMenu(),
                getAliasPopupMenuNbMenu(),
                getAliasPopupMenuShow())
        );
        m_.put(getAliasColor(), new StringList(
                getAliasColorAlpha(),
                getAliasColorBlue(),
                getAliasColorRed(),
                getAliasColorGreen(),
                getAliasColorIsTransparent())
        );
        m_.put(getAliasImage(), new StringList(
                getAliasImageDraw(),
                getAliasImageDrawLine(),
                getAliasImageDrawOval(),
                getAliasImageDrawPolygon(),
                getAliasImageDrawRect(),
                getAliasImageFillOval(),
                getAliasImageFillPolygon(),
                getAliasImageFillRect(),
                getAliasImageGet(),
                getAliasImageSet(),
                getAliasImageGetColor(),
                getAliasImageSetColor(),
                getAliasImageGetFont(),
                getAliasImageSetFont(),
                getAliasImageGetHeight(),
                getAliasImageGetWidth(),
                getAliasImageIsWithAlpha(),
                getAliasImageDispose())
        );
        m_.put(getAliasActionListener(), new StringList(
                getAliasActionPerformed())
        );
        m_.put(getAliasChangeListener(), new StringList(
                getAliasStateChanged())
        );
        m_.put(getAliasWheelListener(), new StringList(
                getAliasWheelMove())
        );
        m_.put(getAliasMouseListener(), new StringList(
                getAliasMouseClicked(),
                getAliasMouseEntered(),
                getAliasMouseExited(),
                getAliasMousePressed(),
                getAliasMouseReleased())
        );
        m_.put(getAliasKeyListener(), new StringList(
                getAliasKeyTyped(),
                getAliasKeyReleased(),
                getAliasKeyPressed())
        );
        m_.put(getAliasWindowListener(), new StringList(
                getAliasWindowActivated(),
                getAliasWindowIconified(),
                getAliasWindowDeactivated(),
                getAliasWindowDeiconified(),
                getAliasWindowOpened(),
                getAliasWindowClosing(),
                getAliasWindowClosed())
        );
        m_.put(getAliasListSelection(), new StringList(
                getAliasValueChanged())
        );
        m_.put(getAliasTreeListener(), new StringList(
                getAliasTreeListenerValueChanged())
        );
        m_.put(getAliasTreeNode(), new StringList(
                getAliasTreeNodeAdd(),
                getAliasTreeNodeInsert(),
                getAliasTreeNodeRemove(),
                getAliasTreeNodeRemoveFromParent(),
                getAliasTreeNodeRemoveAllChildren(),
                getAliasTreeNodeSetUserObject(),
                getAliasTreeNodeGetUserObject(),
                getAliasTreeNodeNb(),
                getAliasTreeNodeGetFirstChild(),
                getAliasTreeNodeGetLastChild(),
                getAliasTreeNodeGetNextSibling(),
                getAliasTreeNodeGetPreviousSibling(),
                getAliasTreeNodeGetParentNode(),
                getAliasTreeNodeIsAncestor(),
                getAliasTreeNodeIsDescendant())
        );
        m_.put(getAliasTree(), new StringList(
                getAliasTreeAddTreeListener(),
                getAliasTreeGetSelected(),
                getAliasTreeIsRootVisible(),
                getAliasTreeSetRootVisible(),
                getAliasTreeReload())
        );
        m_.put(getAliasTableListener(), new StringList(
                getAliasTableValueTableChanged())
        );
        m_.put(getAliasTableGui(), new StringList(
                getAliasTableAddHeader(),
                getAliasTableAddSelect(),
                getAliasTableApplyChanges(),
                getAliasTableAddInterval(),
                getAliasTableRemoveInterval(),
                getAliasTableMoveColumn(),
                getAliasTableGetColumnAtPoint(),
                getAliasTableGetColumnCount(),
                getAliasTableGetColumnName(),
                getAliasTableGetRowAtPoint(),
                getAliasTableGetRowCount(),
                getAliasTableGetSelectedRow(),
                getAliasTableGetSelectedRowCount(),
                getAliasTableGetSelectedRows(),
                getAliasTableGetValue(),
                getAliasTableSetColumns(),
                getAliasTableSetMultiple(),
                getAliasTableSetReorder(),
                getAliasTableSetRowCount(),
                getAliasTableSetValue(),
                getAliasTableIsMultiple(),
                getAliasTableIsReorder())
        );
        m_.put(getAliasPaint(), new StringList(
                getAliasPaintMethod(),
                getAliasPaintAdd(),
                getAliasPaintRefresh())
        );
        m_.put(getAliasMenuBar(),new StringList(
                getAliasMenuBarAdd(),
                getAliasMenuBarGet(),
                getAliasMenuBarRemove(),
                getAliasMenuBarNb()
        ));
        m_.put(getAliasAbsMenu(),new StringList(
                getAliasAbsMenuGetParent(),
                getAliasAbsMenuGetText(),
                getAliasAbsMenuIsEnabled(),
                getAliasAbsMenuSetDeepEnabled(),
                getAliasAbsMenuSetEnabled(),
                getAliasAbsMenuSetText()
        ));
        m_.put(getAliasMenu(),new StringList(
                getAliasMenuAdd(),
                getAliasMenuGet(),
                getAliasMenuRemove(),
                getAliasMenuNb(),
                getAliasMenuAddSeparator()
        ));
        m_.put(getAliasAbsMenuItem(),new StringList(
                getAliasAbsMenuItemAddAction()
        ));
        m_.put(getAliasMenuItemCheck(),new StringList(
                getAliasMenuItemCheckIsSelected(),
                getAliasMenuItemCheckSetSelected()
        ));
        return m_;
    }

    @Override
    public StringList allRefTypes() {
        StringList ref_ =  super.allRefTypes();
        ref_.add(getAliasFrame());
        ref_.add(getAliasConfirm());
        ref_.add(getAliasDialog());
        ref_.add(getAliasWindowType());
        ref_.add(getAliasComponent());
        ref_.add(getAliasActionEvent());
        ref_.add(getAliasMouseEvent());
        ref_.add(getAliasTableListener());
        ref_.add(getAliasTableGui());
        ref_.add(getAliasTreeListener());
        ref_.add(getAliasTree());
        ref_.add(getAliasTreeNode());
        ref_.add(getAliasKeyEvent());
        ref_.add(getAliasWindowEvent());
        ref_.add(getAliasPanel());
        ref_.add(getAliasTabbedPane());
        ref_.add(getAliasPanelBorder());
        ref_.add(getAliasButton());
        ref_.add(getAliasProgBar());
        ref_.add(getAliasCheckBox());
        ref_.add(getAliasRadio());
        ref_.add(getAliasTextLabel());
        ref_.add(getAliasImage());
        ref_.add(getAliasImageLabel());
        ref_.add(getAliasColor());
        ref_.add(getAliasInput());
        ref_.add(getAliasFont());
        ref_.add(getAliasTextArea());
        ref_.add(getAliasTextField());
        ref_.add(getAliasGrList());
        ref_.add(getAliasCombo());
        ref_.add(getAliasButtonGroup());
        ref_.add(getAliasRender());
        ref_.add(getAliasPopupMenu());
        ref_.add(getAliasDimension());
        ref_.add(getAliasKeyListener());
        ref_.add(getAliasMouseListener());
        ref_.add(getAliasWheelListener());
        ref_.add(getAliasWheelEvent());
        ref_.add(getAliasActionListener());
        ref_.add(getAliasChangeListener());
        ref_.add(getAliasWindowListener());
        ref_.add(getAliasScrollPane());
        ref_.add(getAliasSplitPane());
        ref_.add(getAliasListSelection());
        ref_.add(getAliasPaint());
        ref_.add(getAliasMenuBar());
        ref_.add(getAliasAbsMenu());
        ref_.add(getAliasMenu());
        ref_.add(getAliasAbsMenuItem());
        ref_.add(getAliasMenuItem());
        ref_.add(getAliasMenuItemCheck());
        ref_.add(getAliasSpinner());
        ref_.add(getAliasSlider());
        return ref_;
    }
}