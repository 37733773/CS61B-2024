import java.util.ArrayDeque;
import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    public Comparator<T> comp ;
    public MaxArrayDeque61B(Comparator<T> c) {
        comp  =  c;
    }

    public T max() {
        return getT(comp);
    }

    public T max(Comparator<T> c) {
        return getT(c);
    }

    private T getT(Comparator<T> comp) {
        if (size() == 0) return null;
        int MaxPosition = 0;
        for (int i = 0; i < this.size(); i++) {
            if (comp.compare(get(i), get(MaxPosition)) > 0) {
                MaxPosition = i;
            }
        }
        return get(MaxPosition);
    }

}



