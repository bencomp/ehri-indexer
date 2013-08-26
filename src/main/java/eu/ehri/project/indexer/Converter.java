package eu.ehri.project.indexer;

/**
 * @author Mike Bryant (http://github.com/mikesname)
 */
public interface Converter<T> {
    public Iterable<T> convert(T t);
}
