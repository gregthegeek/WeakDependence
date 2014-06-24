package en.gregthegeek.gen.query;

import static en.gregthegeek.gen.query.Constants.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import en.gregthegeek.util.BufferedWriter;

public class QueryGen {
    private static final String DIR = "/Users/greg/Documents/School/Science_Research/workspace/ODRA_enums_mbleja/greg/queries/";
    
    private static final HashMap<String,Query> queries = new HashMap<String,Query>();
    
    public static void main(String[] args) throws IOException {
        
        Query add = new Query("%d + %d" , NUM_PAIRS);
        Query sub = new Query("%d - %d" , NUM_PAIRS);
        Query mul = new Query("%d * %d" , NUM_PAIRS);
        Query div = new Query("%d / %d" , NUM_PAIRS);
        Query mod = new Query("%d %% %d", NUM_PAIRS);
        Query neg = new Query("-%d"     , NUM_SINGS);
        
        p("add", add);
        p("sub", sub);
        p("mul", mul);
        p("div", div);
        p("mod", mod);
        p("neg", neg);
        
        Query and = new Query("%s and %s", LOGIC_PAIRS);
        Query or  = new Query("%s or %s" , LOGIC_PAIRS);
        Query not = new Query("not %s"   , LOGIC_SINGS);
        
        p("and", and);
        p("or", or);
        p("not", not);
        
        Query cat = new Query("%s + %s"  , STR_PAIRS);
        
        p("cat", cat);
        
        Query eqNum  = new Query("%d = %d" , NUM_PAIRS);
        Query eqStr  = new Query("%s = %s" , STR_PAIRS);
        Query neqNum = new Query("%d <> %d", NUM_PAIRS);
        Query neqStr = new Query("%s <> %s", STR_PAIRS);
        
        p("eqNum" , eqNum);
        p("eqStr" , eqStr);
        p("neqNum", neqNum);
        p("neqStr", neqStr);
        
        Query gr  = new Query("%d > %d" , NUM_PAIRS);
        Query ls  = new Query("%d < %d" , NUM_PAIRS);
        Query gre = new Query("%d >= %d", NUM_PAIRS);
        Query lse = new Query("%d <= %d", NUM_PAIRS);
        
        p("gr" , gr);
        p("ls" , ls);
        p("gre", gre);
        p("lse", lse);
        
        Query regexSing  = new Query("%s ~~ %s", STR_REGEX_SING);
        Query regexMult  = new Query("%s ~~ %s", STR_REGEX_MULT);
        Query nRegexSing = new Query("%s !~ %s", STR_REGEX_SING);
        Query nRegexMult = new Query("%s !~ %s", STR_REGEX_MULT);
        
        p("regexSing" , regexSing);
        p("regexMult" , regexMult);
        p("nRegexSing", nRegexSing);
        p("nRegexMult", nRegexMult);
        
        Query as      = new Query("bag(%s) as x"     , STRUCT_SINGS);
        Query groupas = new Query("bag(%s) groupas x", STRUCT_SINGS);
        
        p("as"     , as);
        p("groupas", groupas);
        
        Query now   = new Query("now()"); // check runtime based on time of day?
        Query dform = new Query("dateprec(1900-01-01, %s)", DATE_OPTS);
        
        p("now", now);
        p("dform", dform);
        
        Query sum = new Query("sum %s"  , STRUCT_SINGS);
        Query cnt = new Query("count %s", STRUCT_SINGS);
        Query min = new Query("min %s"  , STRUCT_SINGS);
        Query max = new Query("max %s"  , STRUCT_SINGS);
        Query avg = new Query("avg %s"  , STRUCT_SINGS);
        
        p("sum", sum);
        p("cnt", cnt);
        p("min", min);
        p("max", max);
        p("avg", avg);
        
        Query bag    = new Query("bag(%s)"              , STRUCT_SINGS);
        Query union  = new Query("bag(%s) union bag(%s)", STRUCT_PAIRS);
        Query struct = new Query("struct(%s)"           , STRUCT_SINGS);
        Query comma  = new Query("%s"                   , STRUCT_SINGS);
        
        p("bag"   , bag);
        p("union" , union);
        p("struct", struct);
        p("comma" , comma);
        
        Query subtract  = new Query("bag(%s) subtract bag(%s)" , STRUCT_PAIRS);
        Query in        = new Query("63 in bag(%s)"            , STRUCT_SINGS);
        Query contains  = new Query("bag(%s) contains 63"      , STRUCT_SINGS);
        Query intersect = new Query("bag(%s) intersect bag(%s)", STRUCT_PAIRS);
        Query unique    = new Query("unique(bag(%s))"          , STRUCT_SINGS);
        
        p("subtract" , subtract);
        p("in"       , in);
        p("contains" , contains);
        p("intersect", intersect);
        p("unique"   , unique);
        
        Query uniqueref = new Query("uniqueref(bag(%s))", EMP_STRUCT_SINGS);
        // TODO exists
        
        p("uniqueref", uniqueref);
        
        Query dot     = new Query("bag(%s).sal"                    , EMP_STRUCT_SINGS);
        Query join    = new Query("bag(Emp[1],Emp[2]) join bag(%s)", EMP_STRUCT_SINGS);
        // TODO where
        Query forall  = new Query("forall(bag(%s) as x) (x < 63)"  , STRUCT_SINGS);
        Query forsome = new Query("forsome(bag(%s) as x) (x < 63)" , STRUCT_SINGS);
        
        p("dot"    , dot);
        p("join"   , join);
        //p("where"  , where);
        p("forall" , forall);
        p("forsome", forsome);
        
        Query orderbyNum  = new Query("bag(%s) orderby sal"     , EMP_STRUCT_SINGS);
        Query orderbyEnum = new Query("bag(%s) orderby position", EMP_STRUCT_SINGS);
        Query orderbyDate = new Query("bag(%s) orderby birthday", EMP_STRUCT_SINGS);
        Query orderbyStr  = new Query("bag(%s) orderby lname"   , EMP_STRUCT_SINGS);
        
        p("orderbyNum" , orderbyNum);
        p("orderbyEnum", orderbyEnum);
        p("orderbyDate", orderbyDate);
        p("orderbyStr" , orderbyStr);
        
        // test on depth/breadth?
        Query closeBy   = new Query("(bag(%s)) close by (component.leadsTo.Part)"       , PART_STRUCT_SINGS);
        Query leavesBy  = new Query("(bag(%s)) leaves by (component.leadsTo.Part)"      , PART_STRUCT_SINGS);
        Query closeUBy  = new Query("(bag(%s)) close unique by (component.leadsTo.Part" , PART_STRUCT_SINGS);
        Query leavesUBy = new Query("(bag(%s)) leaves unique by (component.leadsTo.Part", PART_STRUCT_SINGS);
        
        p("closeby"       , closeBy);
        p("leavesby"      , leavesBy);
        p("closeuniqueby" , closeUBy);
        p("leavesuniqueby", leavesUBy);
        
        Query ref   = new Query("ref(bag(%s))"  , EMP_STRUCT_SINGS);
        Query deref = new Query("deref(bag(%s))", EMP_STRUCT_SINGS);
        
        p("ref"  , ref);
        p("deref", deref);
        
        // TODO if then else
        
        Query indexBag = new Query("(bag(%s))[1]", EMP_STRUCT_SINGS);
        Query indexNum = new Query("(bag(" + EMP_STRUCT_SINGS[EMP_STRUCT_SINGS.length-1] + "))[%d]", NUM_SINGS_P);
        Query rangeas  = new Query("bag(%s) rangeas x", EMP_STRUCT_SINGS);
        
        p("indexBag", indexBag);
        p("indexNum", indexNum);
        p("rangeas" , rangeas);
        
        BufferedWriter bw = new BufferedWriter(DIR + "all.txt");
        bw.writeln("$encoding = utf-8");
        bw.newLine();
        bw.writeln("set test plaintimes");
        bw.newLine();
        for(Entry<String,Query> e : queries.entrySet()) {
            String name = e.getKey();
            String fileName = name + ".txt";
            
            QueryWriter qw = new QueryWriter(DIR + fileName, 10, name);
            qw.write(e.getValue());
            qw.close();
            
            bw.write("batch " + fileName);
            bw.newLine();
        }
        bw.close();
    }
    
    private static final void p(String key, Query value) {
        queries.put(key, value);
    }
}
