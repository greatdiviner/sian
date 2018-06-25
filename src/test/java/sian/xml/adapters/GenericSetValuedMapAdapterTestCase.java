package sian.xml.adapters;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.junit.Test;

import com.google.common.collect.Sets;
import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException;

import lombok.Data;

public class GenericSetValuedMapAdapterTestCase {

    @Data
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class AdaptedMultiValuedMapContainer {
        @XmlJavaTypeAdapter(GenericSetValuedMapAdapter.class)
        Map<Integer, Set<String>> map;
    }

    @Data
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class UnadapetedMultiValuedMapContainer {
        Map<Integer, Set<String>> map;
    }

    @Test
    public void marshalAndUnmarshalAdaptedMultiValuedMapContainer() throws Exception {
        AdaptedMultiValuedMapContainer mapContainer = new AdaptedMultiValuedMapContainer();

        Map<Integer, Set<String>> map = new HashMap<>();
        map.put(1, Sets.newHashSet("A"));
        map.put(2, Sets.newHashSet("B"));
        mapContainer.setMap(map);

        JAXBContext jc = JAXBContext.newInstance(AdaptedMultiValuedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        AdaptedMultiValuedMapContainer mapContainer2 = (AdaptedMultiValuedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

    @Test
    public void marshalAndUnmarshalAdaptedMultiValuedMapContainerWithNullInternalMap() throws Exception {
        AdaptedMultiValuedMapContainer mapContainer = new AdaptedMultiValuedMapContainer();

        Map<Integer, Set<String>> map = new HashMap<>();
        map.put(1, Sets.newHashSet("A"));
        map.put(2, Sets.newHashSet("B"));
        mapContainer.setMap(map);

        JAXBContext jc = JAXBContext.newInstance(AdaptedMultiValuedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        AdaptedMultiValuedMapContainer mapContainer2 = (AdaptedMultiValuedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

    // Proof that we need GenericMultiValuedMapAdapter, JAXB cannot handle a Set-valued Map correctly.
    @Test(expected = IllegalAnnotationsException.class)
    public void marshalAndUnmarshalUnadaptedMultiValuedMapContainer() throws Exception {
        UnadapetedMultiValuedMapContainer mapContainer = new UnadapetedMultiValuedMapContainer();

        Map<Integer, Set<String>> map = new HashMap<>();
        map.put(1, Sets.newHashSet("A"));
        map.put(2, Sets.newHashSet("B"));
        mapContainer.setMap(map);

        JAXBContext jc = JAXBContext.newInstance(UnadapetedMultiValuedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        UnadapetedMultiValuedMapContainer mapContainer2 =
                (UnadapetedMultiValuedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

    // Proof that we need GenericMultiValuedMapAdapter, JAXB cannot handle a Set-valued Map correctly.
    @Test(expected = IllegalAnnotationsException.class)
    public void marshalAndUnmarshalUnadaptedMultiValuedMapContainerWithNullInternalMap() throws Exception {
        UnadapetedMultiValuedMapContainer mapContainer = new UnadapetedMultiValuedMapContainer();

        JAXBContext jc = JAXBContext.newInstance(UnadapetedMultiValuedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        UnadapetedMultiValuedMapContainer mapContainer2 =
                (UnadapetedMultiValuedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }
}
