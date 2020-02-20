import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class MyIterator<E> implements Iterator<E> {
    private Vector<E> vect;
    private int i;
    public MyIterator(Collection<E> collection){
        vect= new Vector<>(collection);
        i=0;
    }
    public boolean hasNext() {
        if(i<vect.size())
            return true;
        else return false;
    }

    @Override
    public E next() {
        i++;
        return vect.get(i-1);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
