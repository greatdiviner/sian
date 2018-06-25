package sian.xml.adapters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * A generic {@link XmlAdapter} for generic {@link Set}-valued {@link Map}s.
 */
public class GenericSetValuedMapAdapter<K, V> extends XmlAdapter<GenericSetValuedMapType<K, V>, Map<K, Set<V>>> {

    @Override
    public final GenericSetValuedMapType<K, V> marshal(final Map<K, Set<V>> map) throws Exception {
        if (map == null) {
            return null;
        }
        GenericSetValuedMapType<K, V> container = new GenericSetValuedMapType<K, V>();
        List<GenericSetValuedMapEntry<K, V>> entries = container.getEntries();
        for (Map.Entry<K, Set<V>> entry : map.entrySet()) {
            entries.add(new GenericSetValuedMapEntry<K, V>(entry.getKey(), entry.getValue()));
        }
        return container;
    }

    @Override
    public final Map<K, Set<V>> unmarshal(final GenericSetValuedMapType<K, V> container) throws Exception {
        if (container == null) {
            return null;
        }
        List<GenericSetValuedMapEntry<K, V>> entries = container.getEntries();
        Map<K, Set<V>> map = new HashMap<>();
        for (GenericSetValuedMapEntry<K, V> entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}