package sian.xml.adapters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * A generic {@link XmlAdapter} for generic {@link Map}s.
 */
public class GenericMapAdapter<K, V> extends XmlAdapter<GenericMapType<K, V>, Map<K, V>> {

    @Override
    public final GenericMapType<K, V> marshal(final Map<K, V> map) throws Exception {
        if (map == null) {
            return null;
        }
        GenericMapType<K, V> container = new GenericMapType<K, V>();
        List<GenericMapEntry<K, V>> entries = container.getEntries();
        for (Entry<K, V> entry : map.entrySet()) {
            entries.add(new GenericMapEntry<K, V>(entry.getKey(), entry.getValue()));
        }
        return container;
    }

    @Override
    public final Map<K, V> unmarshal(final GenericMapType<K, V> container) throws Exception {
        if (container == null) {
            return null;
        }
        List<GenericMapEntry<K, V>> entries = container.getEntries();
        Map<K, V> map = new HashMap<K, V>();
        for (GenericMapEntry<K, V> entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}
