package sian.xml.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * Used in {@link GenericListValuedMapAdapter}.
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
final class GenericListValuedMapType<K, V> {
    private final List<GenericListValuedMapEntry<K, V>> entries = new ArrayList<GenericListValuedMapEntry<K, V>>();
}