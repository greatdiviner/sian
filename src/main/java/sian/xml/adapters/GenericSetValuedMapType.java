package sian.xml.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * Used in {@link GenericSetValuedMapAdapter}.
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
final class GenericSetValuedMapType<K, V> {
    private final List<GenericSetValuedMapEntry<K, V>> entries = new ArrayList<GenericSetValuedMapEntry<K, V>>();
}