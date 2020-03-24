package expression;

import java.util.Objects;

public class Const implements Operand {
    private final int value;
    private static final Types type = Types.VARIABLE;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public String toMiniString() {
        return Integer.toString(value);
    }

    @Override
    public int getPriority() {
        return type.getPriority();
    }

    @Override
    public Types getType() {
        return type;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Const aConst = (Const) obj;
        return value == aConst.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
