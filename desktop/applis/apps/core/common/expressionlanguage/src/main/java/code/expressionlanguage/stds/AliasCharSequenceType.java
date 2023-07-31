package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasCharSequenceType {

    private static final String REPLACEMENT="125";
    private static final String CHAR_SEQUENCE="123";
    private static final String STRING="24";
    private static final String STRING_BUILDER="124";
    private static final String GET_NEW_STRING="126";
    private static final String GET_OLD_STRING="127";
    private static final String CHAR_AT="128";
    private static final String CHAR_SEQUENCE_EQUALS="129";
    private static final String CHAR_SEQUENCE_COMPARE_TO="130";
    private static final String CONTAINS="131";
    private static final String ENDS_WITH="132";
    private static final String FORMAT="133";
    private static final String GET_BYTES="134";
    private static final String INDEX_OF="135";
    private static final String IS_EMPTY="136";
    private static final String LAST_INDEX_OF="137";
    private static final String LENGTH="138";
    private static final String REGION_MATCHES="139";
    private static final String SPLIT="140";
    private static final String SPLIT_CHARS="141";
    private static final String SPLIT_STRINGS="142";
    private static final String STARTS_WITH="143";
    private static final String SUB_SEQUENCE="144";
    private static final String SUBSTRING="145";
    private static final String TO_CHAR_ARRAY="146";
    private static final String CHAR_SEQUENCE_TO_STRING="147";
    private static final String TRIM="148";
    private static final String REPLACE_STRING="149";
    private static final String STRING_COMPARE="150";
    private static final String COMPARE_TO_IGNORE_CASE="151";
    private static final String EQUALS_IGNORE_CASE="152";
    private static final String REPLACE_MULTIPLE="153";
    private static final String TO_LOWER_CASE="154";
    private static final String TO_UPPER_CASE="155";
    private static final String STRING_VALUE_OF="156";
    private static final String REPLACE="157";
    private static final String APPEND="158";
    private static final String CAPACITY="159";
    private static final String CLEAR="160";
    private static final String DELETE="161";
    private static final String DELETE_CHAR_AT="162";
    private static final String ENSURE_CAPACITY="163";
    private static final String INSERT="164";
    private static final String REVERSE="165";
    private static final String SET_CHAR_AT="166";
    private static final String SET_LENGTH="167";
    private static final String SAME="168";
    private static final String TRIM_TO_SIZE="169";

    private String aliasCharSequence;
    private String aliasCharSequenceToString;
    private String aliasCharSequenceCompareTo;
    private String aliasCharSequenceEquals;
    private String aliasString;
    private String aliasStringCompare;
    private String aliasStringValueOf;
    private String aliasLength;
    private String aliasCharAt;
    private String aliasToCharArray;
    private String aliasSplit;
    private String aliasSplitStrings;
    private String aliasSplitChars;
    private String aliasReplace;
    private String aliasReplaceString;
    private String aliasReplaceMultiple;
    private String aliasEqualsIgnoreCase;
    private String aliasCompareToIgnoreCase;
    private String aliasContains;
    private String aliasEndsWith;
    private String aliasStartsWith;
    private String aliasIndexOf;
    private String aliasFormat;
    private String aliasGetBytes;
    private String aliasIsEmpty;
    private String aliasLastIndexOf;
    private String aliasRegionMatches;
    private String aliasSubstring;
    private String aliasSubSequence;
    private String aliasToLowerCase;
    private String aliasToUpperCase;
    private String aliasTrim;

    private String aliasStringBuilder;
    private String aliasAppend;
    private String aliasCapacity;
    private String aliasClear;
    private String aliasDelete;
    private String aliasDeleteCharAt;
    private String aliasEnsureCapacity;
    private String aliasInsert;
    private String aliasReverse;
    private String aliasSetCharAt;
    private String aliasSetLength;
    private String aliasSame;
    private String aliasTrimToSize;

    private String aliasReplacement;
    private String aliasGetOldString;
    private String aliasGetNewString;
    private final AliasParamCharSequence params = new AliasParamCharSequence();

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasCharSequence(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE)));
        setAliasIndexOf(LgNamesContent.get(_util,_cust,_mapping.getVal(INDEX_OF)));
        setAliasString(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING)));
        setAliasStringValueOf(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_VALUE_OF)));
        setAliasStringCompare(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_COMPARE)));
        setAliasIsEmpty(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_EMPTY)));
        setAliasTrim(LgNamesContent.get(_util,_cust,_mapping.getVal(TRIM)));
        setAliasGetBytes(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_BYTES)));
        setAliasContains(LgNamesContent.get(_util,_cust,_mapping.getVal(CONTAINS)));
        setAliasReplace(LgNamesContent.get(_util,_cust,_mapping.getVal(REPLACE)));
        setAliasReplaceString(LgNamesContent.get(_util,_cust,_mapping.getVal(REPLACE_STRING)));
        setAliasFormat(LgNamesContent.get(_util,_cust,_mapping.getVal(FORMAT)));
        setAliasEndsWith(LgNamesContent.get(_util,_cust,_mapping.getVal(ENDS_WITH)));
        setAliasCapacity(LgNamesContent.get(_util,_cust,_mapping.getVal(CAPACITY)));
        setAliasSplit(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT)));
        setAliasAppend(LgNamesContent.get(_util,_cust,_mapping.getVal(APPEND)));
        setAliasLength(LgNamesContent.get(_util,_cust,_mapping.getVal(LENGTH)));
        setAliasCharAt(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_AT)));
        setAliasSame(LgNamesContent.get(_util,_cust,_mapping.getVal(SAME)));
        setAliasReverse(LgNamesContent.get(_util,_cust,_mapping.getVal(REVERSE)));
        setAliasInsert(LgNamesContent.get(_util,_cust,_mapping.getVal(INSERT)));
        setAliasDelete(LgNamesContent.get(_util,_cust,_mapping.getVal(DELETE)));
        setAliasClear(LgNamesContent.get(_util,_cust,_mapping.getVal(CLEAR)));
        setAliasSubstring(LgNamesContent.get(_util,_cust,_mapping.getVal(SUBSTRING)));
        setAliasSetCharAt(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_CHAR_AT)));
        setAliasEqualsIgnoreCase(LgNamesContent.get(_util,_cust,_mapping.getVal(EQUALS_IGNORE_CASE)));
        setAliasDeleteCharAt(LgNamesContent.get(_util,_cust,_mapping.getVal(DELETE_CHAR_AT)));
        setAliasStartsWith(LgNamesContent.get(_util,_cust,_mapping.getVal(STARTS_WITH)));
        setAliasLastIndexOf(LgNamesContent.get(_util,_cust,_mapping.getVal(LAST_INDEX_OF)));
        setAliasRegionMatches(LgNamesContent.get(_util,_cust,_mapping.getVal(REGION_MATCHES)));
        setAliasToLowerCase(LgNamesContent.get(_util,_cust,_mapping.getVal(TO_LOWER_CASE)));
        setAliasStringBuilder(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER)));
        setAliasToUpperCase(LgNamesContent.get(_util,_cust,_mapping.getVal(TO_UPPER_CASE)));
        setAliasEnsureCapacity(LgNamesContent.get(_util,_cust,_mapping.getVal(ENSURE_CAPACITY)));
        setAliasSetLength(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_LENGTH)));
        setAliasTrimToSize(LgNamesContent.get(_util,_cust,_mapping.getVal(TRIM_TO_SIZE)));
        setAliasReplacement(LgNamesContent.get(_util,_cust,_mapping.getVal(REPLACEMENT)));
        setAliasGetOldString(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_OLD_STRING)));
        setAliasGetNewString(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_NEW_STRING)));
        setAliasSubSequence(LgNamesContent.get(_util,_cust,_mapping.getVal(SUB_SEQUENCE)));
        setAliasCompareToIgnoreCase(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPARE_TO_IGNORE_CASE)));
        setAliasToCharArray(LgNamesContent.get(_util,_cust,_mapping.getVal(TO_CHAR_ARRAY)));
        setAliasCharSequenceToString(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_TO_STRING)));
        setAliasCharSequenceEquals(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_EQUALS)));
        setAliasCharSequenceCompareTo(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_COMPARE_TO)));
        setAliasReplaceMultiple(LgNamesContent.get(_util,_cust,_mapping.getVal(REPLACE_MULTIPLE)));
        setAliasSplitStrings(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_STRINGS)));
        setAliasSplitChars(LgNamesContent.get(_util,_cust,_mapping.getVal(SPLIT_CHARS)));
    }
    public static void en(TranslationsFile _en){
        _en.add(REPLACEMENT,"Replacement=$core.Replacement");
        _en.add(CHAR_SEQUENCE,"CharSequence=$core.CharSequence");
        _en.add(STRING,"String=$core.String");
        _en.add(STRING_BUILDER,"StringBuilder=$core.StringBuilder");
        _en.add(GET_NEW_STRING,"GetNewString=getNewString");
        _en.add(GET_OLD_STRING,"GetOldString=getOldString");
        _en.add(CHAR_AT,"CharAt=charAt");
        _en.add(CHAR_SEQUENCE_EQUALS,"CharSequenceEquals=equals");
        _en.add(CHAR_SEQUENCE_COMPARE_TO,"CharSequenceCompareTo=compareTo");
        _en.add(CONTAINS,"Contains=contains");
        _en.add(ENDS_WITH,"EndsWith=endsWith");
        _en.add(FORMAT,"Format=format");
        _en.add(GET_BYTES,"GetBytes=getBytes");
        _en.add(INDEX_OF,"IndexOf=indexOf");
        _en.add(IS_EMPTY,"IsEmpty=isEmpty");
        _en.add(LAST_INDEX_OF,"LastIndexOf=lastIndexOf");
        _en.add(LENGTH,"Length=length");
        _en.add(REGION_MATCHES,"RegionMatches=regionMatches");
        _en.add(SPLIT,"Split=split");
        _en.add(SPLIT_CHARS,"SplitChars=splitChars");
        _en.add(SPLIT_STRINGS,"SplitStrings=splitStrings");
        _en.add(STARTS_WITH,"StartsWith=startsWith");
        _en.add(SUB_SEQUENCE,"SubSequence=subSequence");
        _en.add(SUBSTRING,"Substring=substring");
        _en.add(TO_CHAR_ARRAY,"ToCharArray=toCharArray");
        _en.add(CHAR_SEQUENCE_TO_STRING,"CharSequenceToString=toString");
        _en.add(TRIM,"Trim=trim");
        _en.add(REPLACE_STRING,"ReplaceString=replace");
        _en.add(STRING_COMPARE,"StringCompare=compare");
        _en.add(COMPARE_TO_IGNORE_CASE,"CompareToIgnoreCase=compareToIgnoreCase");
        _en.add(EQUALS_IGNORE_CASE,"EqualsIgnoreCase=equalsIgnoreCase");
        _en.add(REPLACE_MULTIPLE,"ReplaceMultiple=replaceMultiple");
        _en.add(TO_LOWER_CASE,"ToLowerCase=toLowerCase");
        _en.add(TO_UPPER_CASE,"ToUpperCase=toUpperCase");
        _en.add(STRING_VALUE_OF,"StringValueOf=valueOf");
        _en.add(REPLACE,"Replace=replace");
        _en.add(APPEND,"Append=append");
        _en.add(CAPACITY,"Capacity=capacity");
        _en.add(CLEAR,"Clear=clear");
        _en.add(DELETE,"Delete=delete");
        _en.add(DELETE_CHAR_AT,"DeleteCharAt=deleteCharAt");
        _en.add(ENSURE_CAPACITY,"EnsureCapacity=ensureCapacity");
        _en.add(INSERT,"Insert=insert");
        _en.add(REVERSE,"Reverse=reverse");
        _en.add(SET_CHAR_AT,"SetCharAt=setCharAt");
        _en.add(SET_LENGTH,"SetLength=setLength");
        _en.add(SAME,"Same=same");
        _en.add(TRIM_TO_SIZE,"TrimToSize=trimToSize");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(REPLACEMENT,"Replacement=$coeur.Remplacement");
        _fr.add(CHAR_SEQUENCE,"CharSequence=$coeur.SequenceCaractere");
        _fr.add(STRING,"String=$coeur.Chaine");
        _fr.add(STRING_BUILDER,"StringBuilder=$coeur.ConstructeurChaine");
        _fr.add(GET_NEW_STRING,"GetNewString=valChaineApres");
        _fr.add(GET_OLD_STRING,"GetOldString=valChaineAvant");
        _fr.add(CHAR_AT,"CharAt=car");
        _fr.add(CHAR_SEQUENCE_EQUALS,"CharSequenceEquals=egal");
        _fr.add(CHAR_SEQUENCE_COMPARE_TO,"CharSequenceCompareTo=comparer");
        _fr.add(CONTAINS,"Contains=contient");
        _fr.add(ENDS_WITH,"EndsWith=terminePar");
        _fr.add(FORMAT,"Format=formatter");
        _fr.add(GET_BYTES,"GetBytes=valOctets");
        _fr.add(INDEX_OF,"IndexOf=indiceDe");
        _fr.add(IS_EMPTY,"IsEmpty=estVide");
        _fr.add(LAST_INDEX_OF,"LastIndexOf=dernierIndiceDe");
        _fr.add(LENGTH,"Length=longueur");
        _fr.add(REGION_MATCHES,"RegionMatches=correspondRegions");
        _fr.add(SPLIT,"Split=separer");
        _fr.add(SPLIT_CHARS,"SplitChars=separerCaracteres");
        _fr.add(SPLIT_STRINGS,"SplitStrings=separerChaines");
        _fr.add(STARTS_WITH,"StartsWith=commencePar");
        _fr.add(SUB_SEQUENCE,"SubSequence=sousSequence");
        _fr.add(SUBSTRING,"Substring=sousChaine");
        _fr.add(TO_CHAR_ARRAY,"ToCharArray=versTableauCaracter");
        _fr.add(CHAR_SEQUENCE_TO_STRING,"CharSequenceToString=chaine");
        _fr.add(TRIM,"Trim=trimmer");
        _fr.add(REPLACE_STRING,"ReplaceString=remplacer");
        _fr.add(STRING_COMPARE,"StringCompare=compare");
        _fr.add(COMPARE_TO_IGNORE_CASE,"CompareToIgnoreCase=compareIgnorantCasse");
        _fr.add(EQUALS_IGNORE_CASE,"EqualsIgnoreCase=egalIgnorantCasse");
        _fr.add(REPLACE_MULTIPLE,"ReplaceMultiple=remplacerMultiple");
        _fr.add(TO_LOWER_CASE,"ToLowerCase=versMinuscule");
        _fr.add(TO_UPPER_CASE,"ToUpperCase=versMajuscule");
        _fr.add(STRING_VALUE_OF,"StringValueOf=valeurDe");
        _fr.add(REPLACE,"Replace=remplacer");
        _fr.add(APPEND,"Append=ajouter");
        _fr.add(CAPACITY,"Capacity=capacite");
        _fr.add(CLEAR,"Clear=vider");
        _fr.add(DELETE,"Delete=supprimer");
        _fr.add(DELETE_CHAR_AT,"DeleteCharAt=supprimerCaractere");
        _fr.add(ENSURE_CAPACITY,"EnsureCapacity=assurerCapacite");
        _fr.add(INSERT,"Insert=inserer");
        _fr.add(REVERSE,"Reverse=inverser");
        _fr.add(SET_CHAR_AT,"SetCharAt=majCaractere");
        _fr.add(SET_LENGTH,"SetLength=majLongueur");
        _fr.add(SAME,"Same=meme");
        _fr.add(TRIM_TO_SIZE,"TrimToSize=trimmerTaille");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(REPLACEMENT,"Replacement");
        _m.addEntry(CHAR_SEQUENCE,"CharSequence");
        _m.addEntry(STRING,"String");
        _m.addEntry(STRING_BUILDER,"StringBuilder");
        _m.addEntry(GET_NEW_STRING,"GetNewString");
        _m.addEntry(GET_OLD_STRING,"GetOldString");
        _m.addEntry(CHAR_AT,"CharAt");
        _m.addEntry(CHAR_SEQUENCE_EQUALS,"CharSequenceEquals");
        _m.addEntry(CHAR_SEQUENCE_COMPARE_TO,"CharSequenceCompareTo");
        _m.addEntry(CONTAINS,"Contains");
        _m.addEntry(ENDS_WITH,"EndsWith");
        _m.addEntry(FORMAT,"Format");
        _m.addEntry(GET_BYTES,"GetBytes");
        _m.addEntry(INDEX_OF,"IndexOf");
        _m.addEntry(IS_EMPTY,"IsEmpty");
        _m.addEntry(LAST_INDEX_OF,"LastIndexOf");
        _m.addEntry(LENGTH,"Length");
        _m.addEntry(REGION_MATCHES,"RegionMatches");
        _m.addEntry(SPLIT,"Split");
        _m.addEntry(SPLIT_CHARS,"SplitChars");
        _m.addEntry(SPLIT_STRINGS,"SplitStrings");
        _m.addEntry(STARTS_WITH,"StartsWith");
        _m.addEntry(SUB_SEQUENCE,"SubSequence");
        _m.addEntry(SUBSTRING,"Substring");
        _m.addEntry(TO_CHAR_ARRAY,"ToCharArray");
        _m.addEntry(CHAR_SEQUENCE_TO_STRING,"CharSequenceToString");
        _m.addEntry(TRIM,"Trim");
        _m.addEntry(REPLACE_STRING,"ReplaceString");
        _m.addEntry(STRING_COMPARE,"StringCompare");
        _m.addEntry(COMPARE_TO_IGNORE_CASE,"CompareToIgnoreCase");
        _m.addEntry(EQUALS_IGNORE_CASE,"EqualsIgnoreCase");
        _m.addEntry(REPLACE_MULTIPLE,"ReplaceMultiple");
        _m.addEntry(TO_LOWER_CASE,"ToLowerCase");
        _m.addEntry(TO_UPPER_CASE,"ToUpperCase");
        _m.addEntry(STRING_VALUE_OF,"StringValueOf");
        _m.addEntry(REPLACE,"Replace");
        _m.addEntry(APPEND,"Append");
        _m.addEntry(CAPACITY,"Capacity");
        _m.addEntry(CLEAR,"Clear");
        _m.addEntry(DELETE,"Delete");
        _m.addEntry(DELETE_CHAR_AT,"DeleteCharAt");
        _m.addEntry(ENSURE_CAPACITY,"EnsureCapacity");
        _m.addEntry(INSERT,"Insert");
        _m.addEntry(REVERSE,"Reverse");
        _m.addEntry(SET_CHAR_AT,"SetCharAt");
        _m.addEntry(SET_LENGTH,"SetLength");
        _m.addEntry(SAME,"Same");
        _m.addEntry(TRIM_TO_SIZE,"TrimToSize");
    }
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(_mapping.getVal(REPLACEMENT), getAliasReplacement());
        list_.addEntry(_mapping.getVal(CHAR_SEQUENCE), getAliasCharSequence());
        list_.addEntry(_mapping.getVal(STRING),getAliasString());
        list_.addEntry(_mapping.getVal(STRING_BUILDER),getAliasStringBuilder());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasReplacement(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_NEW_STRING), getAliasGetNewString()),
                new KeyValueMemberName(_mapping.getVal(GET_OLD_STRING),getAliasGetOldString())));
        map_.addEntry(getAliasCharSequence(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CHAR_AT), getAliasCharAt()),
                new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_EQUALS), getAliasCharSequenceEquals()),
                new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_COMPARE_TO), getAliasCharSequenceCompareTo()),
                new KeyValueMemberName(_mapping.getVal(CONTAINS),getAliasContains()),
                new KeyValueMemberName(_mapping.getVal(ENDS_WITH),getAliasEndsWith()),
                new KeyValueMemberName(_mapping.getVal(FORMAT),getAliasFormat()),
                new KeyValueMemberName(_mapping.getVal(GET_BYTES),getAliasGetBytes()),
                new KeyValueMemberName(_mapping.getVal(INDEX_OF),getAliasIndexOf()),
                new KeyValueMemberName(_mapping.getVal(IS_EMPTY),getAliasIsEmpty()),
                new KeyValueMemberName(_mapping.getVal(LAST_INDEX_OF),getAliasLastIndexOf()),
                new KeyValueMemberName(_mapping.getVal(LENGTH),getAliasLength()),
                new KeyValueMemberName(_mapping.getVal(REGION_MATCHES),getAliasRegionMatches()),
                new KeyValueMemberName(_mapping.getVal(SPLIT),getAliasSplit()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_CHARS),getAliasSplitChars()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_STRINGS),getAliasSplitStrings()),
                new KeyValueMemberName(_mapping.getVal(STARTS_WITH),getAliasStartsWith()),
                new KeyValueMemberName(_mapping.getVal(SUB_SEQUENCE),getAliasSubSequence()),
                new KeyValueMemberName(_mapping.getVal(SUBSTRING),getAliasSubstring()),
                new KeyValueMemberName(_mapping.getVal(TO_CHAR_ARRAY),getAliasToCharArray()),
                new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_TO_STRING), getAliasCharSequenceToString()),
                new KeyValueMemberName(_mapping.getVal(TRIM),getAliasTrim())));
        map_.addEntry(getAliasString(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_EQUALS),getAliasCharSequenceEquals()),
                new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_COMPARE_TO), getAliasCharSequenceCompareTo()),
                new KeyValueMemberName(_mapping.getVal(CHAR_AT), getAliasCharAt()),
                new KeyValueMemberName(_mapping.getVal(CONTAINS),getAliasContains()),
                new KeyValueMemberName(_mapping.getVal(ENDS_WITH),getAliasEndsWith()),
                new KeyValueMemberName(_mapping.getVal(FORMAT),getAliasFormat()),
                new KeyValueMemberName(_mapping.getVal(GET_BYTES),getAliasGetBytes()),
                new KeyValueMemberName(_mapping.getVal(INDEX_OF),getAliasIndexOf()),
                new KeyValueMemberName(_mapping.getVal(IS_EMPTY),getAliasIsEmpty()),
                new KeyValueMemberName(_mapping.getVal(LAST_INDEX_OF),getAliasLastIndexOf()),
                new KeyValueMemberName(_mapping.getVal(LENGTH),getAliasLength()),
                new KeyValueMemberName(_mapping.getVal(REGION_MATCHES),getAliasRegionMatches()),
                new KeyValueMemberName(_mapping.getVal(REPLACE_STRING),getAliasReplaceString()),
                new KeyValueMemberName(_mapping.getVal(SPLIT),getAliasSplit()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_CHARS),getAliasSplitChars()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_STRINGS),getAliasSplitStrings()),
                new KeyValueMemberName(_mapping.getVal(STARTS_WITH),getAliasStartsWith()),
                new KeyValueMemberName(_mapping.getVal(SUB_SEQUENCE),getAliasSubSequence()),
                new KeyValueMemberName(_mapping.getVal(SUBSTRING),getAliasSubstring()),
                new KeyValueMemberName(_mapping.getVal(TO_CHAR_ARRAY),getAliasToCharArray()),
                new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_TO_STRING), getAliasCharSequenceToString()),
                new KeyValueMemberName(_mapping.getVal(TRIM),getAliasTrim()),
                new KeyValueMemberName(_mapping.getVal(STRING_COMPARE), getAliasStringCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO_IGNORE_CASE),getAliasCompareToIgnoreCase()),
                new KeyValueMemberName(_mapping.getVal(EQUALS_IGNORE_CASE),getAliasEqualsIgnoreCase()),
                new KeyValueMemberName(_mapping.getVal(REPLACE_MULTIPLE),getAliasReplaceMultiple()),
                new KeyValueMemberName(_mapping.getVal(TO_LOWER_CASE),getAliasToLowerCase()),
                new KeyValueMemberName(_mapping.getVal(TO_UPPER_CASE),getAliasToUpperCase()),
                new KeyValueMemberName(_mapping.getVal(STRING_VALUE_OF), getAliasStringValueOf())));
        map_.addEntry(getAliasStringBuilder(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_EQUALS), getAliasCharSequenceEquals()),
                new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_COMPARE_TO), getAliasCharSequenceCompareTo()),
                new KeyValueMemberName(_mapping.getVal(CHAR_AT),getAliasCharAt()),
                new KeyValueMemberName(_mapping.getVal(CONTAINS),getAliasContains()),
                new KeyValueMemberName(_mapping.getVal(ENDS_WITH),getAliasEndsWith()),
                new KeyValueMemberName(_mapping.getVal(FORMAT),getAliasFormat()),
                new KeyValueMemberName(_mapping.getVal(GET_BYTES),getAliasGetBytes()),
                new KeyValueMemberName(_mapping.getVal(INDEX_OF),getAliasIndexOf()),
                new KeyValueMemberName(_mapping.getVal(IS_EMPTY),getAliasIsEmpty()),
                new KeyValueMemberName(_mapping.getVal(LAST_INDEX_OF),getAliasLastIndexOf()),
                new KeyValueMemberName(_mapping.getVal(LENGTH),getAliasLength()),
                new KeyValueMemberName(_mapping.getVal(REGION_MATCHES),getAliasRegionMatches()),
                new KeyValueMemberName(_mapping.getVal(REPLACE),getAliasReplace()),
                new KeyValueMemberName(_mapping.getVal(SPLIT),getAliasSplit()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_CHARS),getAliasSplitChars()),
                new KeyValueMemberName(_mapping.getVal(SPLIT_STRINGS),getAliasSplitStrings()),
                new KeyValueMemberName(_mapping.getVal(STARTS_WITH),getAliasStartsWith()),
                new KeyValueMemberName(_mapping.getVal(SUB_SEQUENCE),getAliasSubSequence()),
                new KeyValueMemberName(_mapping.getVal(SUBSTRING),getAliasSubstring()),
                new KeyValueMemberName(_mapping.getVal(TO_CHAR_ARRAY),getAliasToCharArray()),
                new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_TO_STRING), getAliasCharSequenceToString()),
                new KeyValueMemberName(_mapping.getVal(TRIM),getAliasTrim()),
                new KeyValueMemberName(_mapping.getVal(APPEND),getAliasAppend()),
                new KeyValueMemberName(_mapping.getVal(CAPACITY),getAliasCapacity()),
                new KeyValueMemberName(_mapping.getVal(CLEAR),getAliasClear()),
                new KeyValueMemberName(_mapping.getVal(DELETE),getAliasDelete()),
                new KeyValueMemberName(_mapping.getVal(DELETE_CHAR_AT),getAliasDeleteCharAt()),
                new KeyValueMemberName(_mapping.getVal(ENSURE_CAPACITY),getAliasEnsureCapacity()),
                new KeyValueMemberName(_mapping.getVal(INSERT),getAliasInsert()),
                new KeyValueMemberName(_mapping.getVal(REVERSE),getAliasReverse()),
                new KeyValueMemberName(_mapping.getVal(SET_CHAR_AT),getAliasSetCharAt()),
                new KeyValueMemberName(_mapping.getVal(SET_LENGTH),getAliasSetLength()),
                new KeyValueMemberName(_mapping.getVal(SAME),getAliasSame()),
                new KeyValueMemberName(_mapping.getVal(TRIM_TO_SIZE),getAliasTrimToSize())));
        return map_;
    }
    private StandardType chTypeType(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        StringList noTypes_ = new StringList();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        StandardType std_ = new StandardInterface(aliasCharSequence, methods_);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasPrimChar_ = _lgNames.getContent().getPrimTypes().getAliasPrimChar();
        StringList params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        StandardMethod method_ = new StandardMethod(aliasSubSequence, params_, aliasString, false, MethodModifier.NORMAL, new StringList(params.getAliasCharSequence0SubSequence0(), params.getAliasCharSequence0SubSequence1()), new FctCharSeqSubstring1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0CharAt0()),new FctCharSeqCharAt());
        methods_.add( method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger_, false, MethodModifier.NORMAL,new FctCharSeqLength());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0Substring0(),params.getAliasCharSequence0Substring1()),new FctCharSeqSubstring1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1Substring0()),new FctCharSeqSubstring0());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasCharSequenceCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0CompareTo0()),new FctCharSeqCompareTo());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasContains, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0Contains0()),new FctCharSeqContains());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0StartsWith0()),new FctCharSeqStartsWith0());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1StartsWith0(),params.getAliasCharSequence1StartsWith1()),new FctCharSeqStartsWith1());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasEndsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0EndsWith0()),new FctCharSeqEndsWith());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0IndexOf0()),new FctCharSeqIndexOf1());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1IndexOf0(),params.getAliasCharSequence1IndexOf1()),new FctCharSeqIndexOf3());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence2IndexOf0()),new FctCharSeqIndexOf0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence3IndexOf0(),params.getAliasCharSequence3IndexOf1()),new FctCharSeqIndexOf2());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0LastIndexOf0()),new FctCharSeqLastIndexOf1());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1LastIndexOf0(),params.getAliasCharSequence1LastIndexOf1()),new FctCharSeqLastIndexOf3());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence2LastIndexOf0()),new FctCharSeqLastIndexOf0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence3LastIndexOf0(),params.getAliasCharSequence3LastIndexOf1()),new FctCharSeqLastIndexOf2());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEmpty, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new FctCharSeqIsEmpty());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToCharArray, params_, StringExpUtil.getPrettyArrayType(aliasPrimChar_), false, MethodModifier.NORMAL,new FctCharSeqToCharArray());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBytes, params_, StringExpUtil.getPrettyArrayType(_lgNames.getContent().getPrimTypes().getAliasPrimByte()), false, MethodModifier.NORMAL,new FctCharSeqGetBytes());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasFormat, params_, aliasString, true, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0Format0()),new FctCharSeqFormat());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0Split0()),new FctCharSeqSplit1());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1Split0(),params.getAliasCharSequence1Split1()),new FctCharSeqSplit3());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence2Split0()),new FctCharSeqSplit0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasSplit, params_, StringExpUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence3Split0(),params.getAliasCharSequence3Split1()),new FctCharSeqSplit2());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasSplitStrings, params_, StringExpUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0SplitStrings0()),new FctCharSeqSplitStrings0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasCharSequence);
        method_ = new StandardMethod(aliasSplitStrings, params_, StringExpUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence1SplitStrings0(),params.getAliasCharSequence1SplitStrings1()),new FctCharSeqSplitStrings1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasSplitChars, params_, StringExpUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0SplitChars0()),new FctCharSeqSplitChars());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasCharSequence, aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharSequence0RegionMatches0(),params.getAliasCharSequence0RegionMatches1(),params.getAliasCharSequence0RegionMatches2(),params.getAliasCharSequence0RegionMatches3()),new FctCharSeqRegionMatches());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrim, params_, aliasString, false, MethodModifier.NORMAL, new FctCharSeqTrim());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCharSequenceToString, params_, aliasString, false, MethodModifier.NORMAL, new FctCharSeqStr());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence,aliasCharSequence);
        method_ = new StandardMethod(aliasCharSequenceEquals, params_, _lgNames.getContent().getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(params.getAliasCharSequence0Equals0(),params.getAliasCharSequence0Equals1()),new FctCharSeqEquals());
        methods_.add( method_);
        standards_.addEntry(aliasCharSequence, std_);
        return std_;
    }
    private void strType(LgNames _lgNames, StandardType _t) {
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasPrimChar_ = _lgNames.getContent().getPrimTypes().getAliasPrimChar();
        String aliasObject_ = _lgNames.getContent().getCoreNames().getAliasObject();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass std_ = new StandardClass(aliasString, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL, new DfString());
        std_.addSuperStdTypes(_t);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        StringList params_ = new StringList(aliasString);
        StandardMethod method_ = new StandardMethod(aliasEqualsIgnoreCase, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasString0EqualsIgnoreCase0()),new FctStringEqualsIgnoreCase());
        methods_.add( method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(aliasStringCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasString0Compare0(),params.getAliasString0Compare1()),new FctStringCompare());
        methods_.add( method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasCompareToIgnoreCase, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasString0CompareToIgnoreCase0()),new FctStringCompareToIgnoreCase());
        methods_.add( method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(aliasReplaceString, params_, aliasString, false, MethodModifier.NORMAL,new StringList(params.getAliasString0ReplaceString0(),params.getAliasString0ReplaceString1()),new FctStringReplaceString1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimChar_);
        method_ = new StandardMethod(aliasReplaceString, params_, aliasString, false, MethodModifier.NORMAL,new StringList(params.getAliasString1ReplaceString0(),params.getAliasString1ReplaceString1()),new FctStringReplaceString0());
        methods_.add( method_);
        params_ = new StringList(aliasReplacement);
        method_ = new StandardMethod(aliasReplaceMultiple, params_, aliasString, true, MethodModifier.NORMAL,new StringList(params.getAliasString0ReplaceMultiple0()),new FctStringReplaceMultiple());
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_, aliasPrimInteger_, aliasString, aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasString0RegionMatches0(),params.getAliasString0RegionMatches1(),params.getAliasString0RegionMatches2(),params.getAliasString0RegionMatches3(),params.getAliasString0RegionMatches4()),new FctStringRegionMatches());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToLowerCase, params_, aliasString, false, MethodModifier.NORMAL, new FctStringToLowerCase());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToUpperCase, params_, aliasString, false, MethodModifier.NORMAL, new FctStringToUpperCase());
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString0ValueOfMethod0()),new FctNbToStr1());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimByte());
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString1ValueOfMethod0()),new FctNbToStr1());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimShort());
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString2ValueOfMethod0()),new FctNbToStr1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString3ValueOfMethod0()),new FctNbToStr1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString4ValueOfMethod0()),new FctNbToStr1());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString5ValueOfMethod0()),new FctNbToStr1());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimFloat());
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString6ValueOfMethod0()),new FctNbToStr1());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimDouble());
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, false, MethodModifier.STATIC,new StringList(params.getAliasString7ValueOfMethod0()),new FctNbToStr1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, true, MethodModifier.STATIC,new StringList(params.getAliasString8ValueOfMethod0()),new FctStringValueOf0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_,aliasPrimChar_);
        method_ = new StandardMethod(aliasStringValueOf, params_, aliasString, true, MethodModifier.STATIC,new StringList(params.getAliasString9ValueOfMethod0(),params.getAliasString9ValueOfMethod1(),params.getAliasString9ValueOfMethod2()),new FctStringValueOf1());
        methods_.add( method_);
        params_ = new StringList();
        StandardConstructor ctor_ = new StandardConstructor(params_, false, new FctString0());
        constructors_.add(ctor_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimByte());
        ctor_ = new StandardConstructor(params_, true,new StringList(params.getAliasString0String0()),new FctString3());
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_, _lgNames.getContent().getPrimTypes().getAliasPrimByte());
        ctor_ = new StandardConstructor(params_, true,new StringList(params.getAliasString1String0(),params.getAliasString1String1(),params.getAliasString1String2()),new FctString5());
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimChar_);
        ctor_ = new StandardConstructor(params_, true,new StringList(params.getAliasString2String0()),new FctString2());
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_, aliasPrimChar_);
        ctor_ = new StandardConstructor(params_, true,new StringList(params.getAliasString3String0(),params.getAliasString3String1(),params.getAliasString3String2()),new FctString4());
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasString4String0()),new FctString1());
        constructors_.add(ctor_);
        std_.getDirectInterfaces().add(aliasCharSequence);
        standards_.addEntry(aliasString, std_);
    }
    private void strBuildType(LgNames _lgNames, StandardType _t) {
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasPrimChar_ = _lgNames.getContent().getPrimTypes().getAliasPrimChar();
        String aliasObject_ = _lgNames.getContent().getCoreNames().getAliasObject();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass std_ = new StandardClass(aliasStringBuilder, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL, new DfStringBuilder());
        std_.addSuperStdTypes(_t);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        StringList params_ = new StringList(aliasPrimBoolean_);
        StandardMethod method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimByte());
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder1Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimShort());
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder2Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder3Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder4Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder5Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimFloat());
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder6Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimDouble());
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder7Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder8Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(aliasString,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder9Append0(),params.getAliasStringBuilder9Append1(),params.getAliasStringBuilder9Append2()),new FctStringBuilderAppend2());
        methods_.add( method_);
        params_ = new StringList(aliasStringBuilder);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder10Append0()),new FctStringBuilderAppend0());
        methods_.add( method_);
        params_ = new StringList(aliasStringBuilder,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder11Append0(),params.getAliasStringBuilder11Append1(),params.getAliasStringBuilder11Append2()),new FctStringBuilderAppend2());
        methods_.add( method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(aliasPrimChar_));
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder12Append0()),new FctStringBuilderAppend1());
        methods_.add( method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(aliasPrimChar_),aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder13Append0(),params.getAliasStringBuilder13Append1(),params.getAliasStringBuilder13Append2()),new FctStringBuilderAppend3());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCapacity, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new FctStringBuilderCapacity());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasDelete, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0Delete0(),params.getAliasStringBuilder0Delete1()),new FctStringBuilderDelete());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasDeleteCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0DeleteCharAt0()),new FctStringBuilderDeleteCharAt());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasClear, params_, aliasStringBuilder, false, MethodModifier.NORMAL, new FctStringBuilderClear());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimBoolean_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0Insert0(),params.getAliasStringBuilder0Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, _lgNames.getContent().getPrimTypes().getAliasPrimByte());
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder1Insert0(),params.getAliasStringBuilder1Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, _lgNames.getContent().getPrimTypes().getAliasPrimShort());
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder2Insert0(),params.getAliasStringBuilder2Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimChar_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder3Insert0(),params.getAliasStringBuilder3Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder4Insert0(),params.getAliasStringBuilder4Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, _lgNames.getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder5Insert0(),params.getAliasStringBuilder5Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, _lgNames.getContent().getPrimTypes().getAliasPrimFloat());
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder6Insert0(),params.getAliasStringBuilder6Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, _lgNames.getContent().getPrimTypes().getAliasPrimDouble());
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder7Insert0(),params.getAliasStringBuilder7Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasString);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder8Insert0(),params.getAliasStringBuilder8Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasString,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder9Insert0(),params.getAliasStringBuilder9Insert1(),params.getAliasStringBuilder9Insert2(),params.getAliasStringBuilder9Insert3()),new FctStringBuilderInsert2());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasStringBuilder);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder10Insert0(),params.getAliasStringBuilder10Insert1()),new FctStringBuilderInsert0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasStringBuilder,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder11Insert0(),params.getAliasStringBuilder11Insert1(),params.getAliasStringBuilder11Insert2(),params.getAliasStringBuilder11Insert3()),new FctStringBuilderInsert2());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, StringExpUtil.getPrettyArrayType(aliasPrimChar_));
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder12Insert0(),params.getAliasStringBuilder12Insert1()),new FctStringBuilderInsert1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, StringExpUtil.getPrettyArrayType(aliasPrimChar_),aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder13Insert0(),params.getAliasStringBuilder13Insert1(),params.getAliasStringBuilder13Insert2(),params.getAliasStringBuilder13Insert3()),new FctStringBuilderInsert3());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReverse, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new FctStringBuilderReverse());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_,aliasCharSequence);
        method_ = new StandardMethod(aliasReplace, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0Replace0(),params.getAliasStringBuilder0Replace1(),params.getAliasStringBuilder0Replace2()),new FctStringBuilderReplace());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimChar_);
        method_ = new StandardMethod(aliasSetCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0SetCharAt0(),params.getAliasStringBuilder0SetCharAt1()),new FctStringBuilderSetCharAt());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasSetLength, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0SetLength0()),new FctStringBuilderSetLength());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrimToSize, params_, aliasStringBuilder, false, MethodModifier.NORMAL, new FctStringBuilderTrimToSize());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasEnsureCapacity, params_, aliasStringBuilder, false, MethodModifier.NORMAL,new StringList(params.getAliasStringBuilder0EnsureCapacity0()),new FctStringBuilderEnsureCapacity());
        methods_.add( method_);
        params_ = new StringList(aliasStringBuilder,aliasStringBuilder);
        method_ = new StandardMethod(aliasSame, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasStringBuilder0Same0(),params.getAliasStringBuilder0Same1()),new FctStringBuilderSame());
        methods_.add( method_);
        params_ = new StringList();
        StandardConstructor ctor_ = new StandardConstructor(params_, false, new FctStringBuilder0());
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasStringBuilder0StringBuilder0()), new FctStringBuilder2());
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasStringBuilder1StringBuilder0()), new FctStringBuilder1());
        constructors_.add(ctor_);
        params_ = new StringList(aliasString);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasStringBuilder2StringBuilder0()), new FctStringBuilder2());
        constructors_.add(ctor_);
        std_.getDirectInterfaces().add(aliasCharSequence);
        standards_.addEntry(aliasStringBuilder, std_);
    }
    public void build(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        StringList params_;
        CustList<StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        CustList<CstFieldInfo> fields_;
        StandardMethod method_;
        StandardConstructor ctor_;
        StandardType std_;
        std_ = chTypeType(_lgNames);
        strType(_lgNames,std_);
        strBuildType(_lgNames,std_);
        String aliasObject_ = _lgNames.getContent().getCoreNames().getAliasObject();
        fields_ = new CustList<CstFieldInfo>();
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasReplacement, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL, new DfReplacement());
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetOldString, params_, aliasString, false, MethodModifier.NORMAL, new FctReplOld());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetNewString, params_, aliasString, false, MethodModifier.NORMAL, new FctReplNew());
        methods_.add( method_);
        params_ = new StringList(aliasCharSequence,aliasCharSequence);
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasReplacement0Replacement0(),params.getAliasReplacement0Replacement1()),new FctReplacement());
        constructors_.add(ctor_);
        standards_.addEntry(aliasReplacement, std_);
    }

    public String getAliasCharSequence() {
        return aliasCharSequence;
    }

    public void setAliasCharSequence(String _aliasCharSequence) {
        aliasCharSequence = _aliasCharSequence;
    }

    public String getAliasCharSequenceToString() {
        return aliasCharSequenceToString;
    }

    public void setAliasCharSequenceToString(String _aliasCharSequenceToString) {
        this.aliasCharSequenceToString = _aliasCharSequenceToString;
    }

    public String getAliasCharSequenceCompareTo() {
        return aliasCharSequenceCompareTo;
    }

    public void setAliasCharSequenceCompareTo(String _aliasCharSequenceCompareTo) {
        this.aliasCharSequenceCompareTo = _aliasCharSequenceCompareTo;
    }

    public String getAliasCharSequenceEquals() {
        return aliasCharSequenceEquals;
    }

    public void setAliasCharSequenceEquals(String _aliasCharSequenceEquals) {
        this.aliasCharSequenceEquals = _aliasCharSequenceEquals;
    }

    public String getAliasString() {
        return aliasString;
    }

    public void setAliasString(String _aliasString) {
        aliasString = _aliasString;
    }

    public String getAliasStringCompare() {
        return aliasStringCompare;
    }

    public void setAliasStringCompare(String _aliasStringCompare) {
        this.aliasStringCompare = _aliasStringCompare;
    }

    public String getAliasStringValueOf() {
        return aliasStringValueOf;
    }

    public void setAliasStringValueOf(String _aliasStringValueOf) {
        this.aliasStringValueOf = _aliasStringValueOf;
    }

    public String getAliasLength() {
        return aliasLength;
    }

    public void setAliasLength(String _aliasLength) {
        aliasLength = _aliasLength;
    }

    public String getAliasCharAt() {
        return aliasCharAt;
    }

    public void setAliasCharAt(String _aliasCharAt) {
        aliasCharAt = _aliasCharAt;
    }

    public String getAliasToCharArray() {
        return aliasToCharArray;
    }

    public void setAliasToCharArray(String _aliasToCharArray) {
        aliasToCharArray = _aliasToCharArray;
    }

    public String getAliasSplit() {
        return aliasSplit;
    }

    public void setAliasSplit(String _aliasSplit) {
        aliasSplit = _aliasSplit;
    }

    public String getAliasSplitStrings() {
        return aliasSplitStrings;
    }

    public void setAliasSplitStrings(String _aliasSplitStrings) {
        aliasSplitStrings = _aliasSplitStrings;
    }

    public String getAliasSplitChars() {
        return aliasSplitChars;
    }

    public void setAliasSplitChars(String _aliasSplitChars) {
        aliasSplitChars = _aliasSplitChars;
    }

    public String getAliasReplace() {
        return aliasReplace;
    }

    public void setAliasReplace(String _aliasReplace) {
        aliasReplace = _aliasReplace;
    }

    public String getAliasReplaceString() {
        return aliasReplaceString;
    }

    public void setAliasReplaceString(String _aliasReplace) {
        aliasReplaceString = _aliasReplace;
    }

    public String getAliasReplaceMultiple() {
        return aliasReplaceMultiple;
    }

    public void setAliasReplaceMultiple(String _aliasReplaceMultiple) {
        aliasReplaceMultiple = _aliasReplaceMultiple;
    }

    public String getAliasEqualsIgnoreCase() {
        return aliasEqualsIgnoreCase;
    }

    public void setAliasEqualsIgnoreCase(String _aliasEqualsIgnoreCase) {
        aliasEqualsIgnoreCase = _aliasEqualsIgnoreCase;
    }

    public String getAliasCompareToIgnoreCase() {
        return aliasCompareToIgnoreCase;
    }

    public void setAliasCompareToIgnoreCase(String _aliasCompareToIgnoreCase) {
        aliasCompareToIgnoreCase = _aliasCompareToIgnoreCase;
    }

    public String getAliasContains() {
        return aliasContains;
    }

    public void setAliasContains(String _aliasContains) {
        aliasContains = _aliasContains;
    }

    public String getAliasEndsWith() {
        return aliasEndsWith;
    }

    public void setAliasEndsWith(String _aliasEndsWith) {
        aliasEndsWith = _aliasEndsWith;
    }

    public String getAliasStartsWith() {
        return aliasStartsWith;
    }

    public void setAliasStartsWith(String _aliasStartsWith) {
        aliasStartsWith = _aliasStartsWith;
    }

    public String getAliasIndexOf() {
        return aliasIndexOf;
    }

    public void setAliasIndexOf(String _aliasIndexOf) {
        aliasIndexOf = _aliasIndexOf;
    }

    public String getAliasFormat() {
        return aliasFormat;
    }

    public void setAliasFormat(String _aliasFormat) {
        aliasFormat = _aliasFormat;
    }

    public String getAliasGetBytes() {
        return aliasGetBytes;
    }

    public void setAliasGetBytes(String _aliasGetBytes) {
        aliasGetBytes = _aliasGetBytes;
    }

    public String getAliasIsEmpty() {
        return aliasIsEmpty;
    }

    public void setAliasIsEmpty(String _aliasIsEmpty) {
        aliasIsEmpty = _aliasIsEmpty;
    }

    public String getAliasLastIndexOf() {
        return aliasLastIndexOf;
    }

    public void setAliasLastIndexOf(String _aliasLastIndexOf) {
        aliasLastIndexOf = _aliasLastIndexOf;
    }

    public String getAliasRegionMatches() {
        return aliasRegionMatches;
    }

    public void setAliasRegionMatches(String _aliasRegionMatches) {
        aliasRegionMatches = _aliasRegionMatches;
    }

    public String getAliasSubstring() {
        return aliasSubstring;
    }

    public void setAliasSubstring(String _aliasSubstring) {
        aliasSubstring = _aliasSubstring;
    }

    public String getAliasSubSequence() {
        return aliasSubSequence;
    }

    public void setAliasSubSequence(String _aliasSubSequence) {
        aliasSubSequence = _aliasSubSequence;
    }

    public String getAliasToLowerCase() {
        return aliasToLowerCase;
    }

    public void setAliasToLowerCase(String _aliasToLowerCase) {
        aliasToLowerCase = _aliasToLowerCase;
    }

    public String getAliasToUpperCase() {
        return aliasToUpperCase;
    }

    public void setAliasToUpperCase(String _aliasToUpperCase) {
        aliasToUpperCase = _aliasToUpperCase;
    }

    public String getAliasTrim() {
        return aliasTrim;
    }

    public void setAliasTrim(String _aliasTrim) {
        aliasTrim = _aliasTrim;
    }

    public String getAliasStringBuilder() {
        return aliasStringBuilder;
    }

    public void setAliasStringBuilder(String _aliasStringBuilder) {
        aliasStringBuilder = _aliasStringBuilder;
    }

    public String getAliasAppend() {
        return aliasAppend;
    }

    public void setAliasAppend(String _aliasAppend) {
        aliasAppend = _aliasAppend;
    }

    public String getAliasCapacity() {
        return aliasCapacity;
    }

    public void setAliasCapacity(String _aliasCapacity) {
        aliasCapacity = _aliasCapacity;
    }

    public String getAliasClear() {
        return aliasClear;
    }

    public void setAliasClear(String _aliasClear) {
        aliasClear = _aliasClear;
    }

    public String getAliasDelete() {
        return aliasDelete;
    }

    public void setAliasDelete(String _aliasDelete) {
        aliasDelete = _aliasDelete;
    }

    public String getAliasDeleteCharAt() {
        return aliasDeleteCharAt;
    }

    public void setAliasDeleteCharAt(String _aliasDeleteCharAt) {
        aliasDeleteCharAt = _aliasDeleteCharAt;
    }

    public String getAliasEnsureCapacity() {
        return aliasEnsureCapacity;
    }

    public void setAliasEnsureCapacity(String _aliasEnsureCapacity) {
        aliasEnsureCapacity = _aliasEnsureCapacity;
    }

    public String getAliasInsert() {
        return aliasInsert;
    }

    public void setAliasInsert(String _aliasInsert) {
        aliasInsert = _aliasInsert;
    }

    public String getAliasReverse() {
        return aliasReverse;
    }

    public void setAliasReverse(String _aliasReverse) {
        aliasReverse = _aliasReverse;
    }

    public String getAliasSetCharAt() {
        return aliasSetCharAt;
    }

    public void setAliasSetCharAt(String _aliasSetCharAt) {
        aliasSetCharAt = _aliasSetCharAt;
    }

    public String getAliasSetLength() {
        return aliasSetLength;
    }

    public void setAliasSetLength(String _aliasSetLength) {
        aliasSetLength = _aliasSetLength;
    }

    public String getAliasSame() {
        return aliasSame;
    }

    public void setAliasSame(String _aliasSame) {
        aliasSame = _aliasSame;
    }

    public String getAliasTrimToSize() {
        return aliasTrimToSize;
    }

    public void setAliasTrimToSize(String _aliasTrimToSize) {
        aliasTrimToSize = _aliasTrimToSize;
    }

    public String getAliasReplacement() {
        return aliasReplacement;
    }

    public void setAliasReplacement(String _aliasReplacement) {
        aliasReplacement = _aliasReplacement;
    }

    public String getAliasGetOldString() {
        return aliasGetOldString;
    }

    public void setAliasGetOldString(String _aliasGetOldString) {
        aliasGetOldString = _aliasGetOldString;
    }

    public String getAliasGetNewString() {
        return aliasGetNewString;
    }

    public void setAliasGetNewString(String _aliasGetNewString) {
        aliasGetNewString = _aliasGetNewString;
    }


    public AliasParamCharSequence getParams() {
        return params;
    }

}
