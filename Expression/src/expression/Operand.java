package expression;

public interface Operand extends Expression, DoubleExpression, TripleExpression {
    String toString();
    String toMiniString();
    int getPriority();
}
