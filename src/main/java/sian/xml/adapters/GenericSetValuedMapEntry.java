/**
 *
 */
package sian.xml.adapters;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Used in {@link GenericSetValuedMapAdapter}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
final class GenericSetValuedMapEntry<K, V> {
    private K key;
    private Set<V> value;
}
