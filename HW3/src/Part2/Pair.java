package Part2;

/**
 * my Pair class
 * @param <F> key
 * @param <S> value
 */
public class Pair<F, S> {
    private F first;
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getKey() {
        return first;
    }

    public S getValue() {
        return second;
    }
}