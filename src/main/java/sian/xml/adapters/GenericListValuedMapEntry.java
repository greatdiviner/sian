/**
 *
 */
package sian.xml.adapters;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Used in {@link GenericListValuedMapAdapter}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
final class GenericListValuedMapEntry<K, V> {
    private K key;
    private List<V> value;
}
