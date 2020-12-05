package statistics;

import statistics.matcher.*;

public class QueryBuilder {

    Matcher m;
    
    public QueryBuilder() {
        this.m = new All();
    }

    public QueryBuilder playsIn(String s) {
        Matcher temp = new And(new PlaysIn(s), m);
        this.m = temp;
        return this;
    }    

    public QueryBuilder hasAtLeast(int i, String s) {
        Matcher temp = new And(new HasAtLeast(i, s), m);
        this.m = temp;
        return this;
    }    
    
    public QueryBuilder hasFewerThan(int i, String s) {
        Matcher temp = new And(new HasFewerThan(i, s), m);
        this.m = temp;
        return this;
    }    

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        Matcher temp = new Or(m1, m2);
        this.m = temp;
        return this;
    }    

    public Matcher build() {
        Matcher temp = this.m;
        this.m = new All();
        return temp;
    }

}
