package sian.xml.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * Used in {@link GenericMapAdapter}.
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
final class GenericMapType<K, V> {
    private final List<GenericMapEntry<K, V>> entries = new ArrayList<GenericMapEntry<K, V>>();
}