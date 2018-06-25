package sian.xml.adapters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * A generic {@link XmlAdapter} for generic {@link List}-valued {@link Map}s.
 */
public class GenericListValuedMapAdapter<K, V> extends XmlAdapter<GenericListValuedMapType<K, V>, Map<K, List<V>>> {

    @Override
    public final GenericListValuedMapType<K, V> marshal(final Map<K, List<V>> map) throws Exception {
        if (map == null) {
            return null;
        }
        GenericListValuedMapType<K, V> container = new GenericListValuedMapType<K, V>();
        List<GenericListValuedMapEntry<K, V>> entries = container.getEntries();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            entries.add(new GenericListValuedMapEntry<K, V>(entry.getKey(), entry.getValue()));
        }
        return container;
    }

    @Override
    public final Map<K, List<V>> unmarshal(final GenericListValuedMapType<K, V> container) throws Exception {
        if (container == null) {
            return null;
        }
        List<GenericListValuedMapEntry<K, V>> entries = container.getEntries();
        Map<K, List<V>> map = new HashMap<>();
        for (GenericListValuedMapEntry<K, V> entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}