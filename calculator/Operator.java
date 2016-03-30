package calculator;

public interface Operator {

    public String symbol();
    
    public int arity();

    public double evaluate(double [] operands);
}