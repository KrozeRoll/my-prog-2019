package expression;

public interface Operand extends Expression, TripleExpression {
    String toString();
    String toMiniString();
    int getPriority();
    Types getType();
}
