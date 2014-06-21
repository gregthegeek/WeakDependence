package en.gregthegeek.gen.query;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class QueryWriter extends BufferedWriter {
    private final int tests;
    private final String prefix;

    public QueryWriter(Writer out, int tests, String prefix) throws IOException {
        super(out);
        this.tests = tests;
        this.prefix = prefix;
        init();
    }

    public QueryWriter(Writer out, int tests, String prefix, int sz) throws IOException {
        super(out, sz);
        this.tests = tests;
        this.prefix = prefix;
        init();
    }
    
    private final void init() throws IOException {
        writeln("#batch res/optimiser/batch/bench.cli");
        newLine();
        writeln("$encoding = utf-8");
        newLine();
        
        // load data here
        /*writeln("batch <x>.cli");
        writeln("cm <y>");
        writeln("batch data.txt");
        newLine();*/
        
        /*writeln("set refoptimization none | dead | independent");
        writeln("set optimization none | dead | weaklydependent | independent");
        newLine();
        writeln("set test compare");
        
        newLine();
        newLine();*/
        
        writeln("set test plaintimes");
        newLine();
    }
    
    public void writeln(String line) throws IOException {
        write(line);
        newLine();
    }
    
    public void write(Query q) throws IOException {
        for(String line : q.getLines()) {
            writeln("benchmark " + tests + " " + prefix + ":" + line);
        }
    }
}
