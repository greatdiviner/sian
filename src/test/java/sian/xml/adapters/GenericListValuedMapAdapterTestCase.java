package sian.xml.adapters;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException;

import lombok.Data;

public class GenericListValuedMapAdapterTestCase {

    @Data
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class AdaptedListValuedMapContainer {
        @XmlJavaTypeAdapter(GenericListValuedMapAdapter.class)
        Map<Integer, List<String>> map;
    }

    @Data
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class UnadapetedListValuedMapContainer {
        Map<Integer, List<String>> map;
    }

    @Test
    public void marshalAndUnmarshalAdaptedListValuedMapContainer() throws Exception {
        AdaptedListValuedMapContainer mapContainer = new AdaptedListValuedMapContainer();

        Map<Integer, List<String>> map = new HashMap<>();
        map.put(1, Lists.newArrayList("A"));
        map.put(2, Lists.newArrayList("B"));
        mapContainer.setMap(map);

        JAXBContext jc = JAXBContext.newInstance(AdaptedListValuedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        AdaptedListValuedMapContainer mapContainer2 = (AdaptedListValuedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

    @Test
    public void marshalAndUnmarshalAdaptedListValuedMapContainerWithNulllInternalMap() throws Exception {
        AdaptedListValuedMapContainer mapContainer = new AdaptedListValuedMapContainer();

        JAXBContext jc = JAXBContext.newInstance(AdaptedListValuedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        AdaptedListValuedMapContainer mapContainer2 = (AdaptedListValuedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

    // Proof that we need GenericListValuedMapAdapter, JAXB cannot handle a List-valued Map correctly.
    @Test(expected = IllegalAnnotationsException.class)
    public void marshalAndUnmarshalUnadaptedListValuedMapContainer() throws Exception {
        UnadapetedListValuedMapContainer mapContainer = new UnadapetedListValuedMapContainer();

        Map<Integer, List<String>> map = new HashMap<>();
        map.put(1, Lists.newArrayList("A"));
        map.put(2, Lists.newArrayList("B"));
        mapContainer.setMap(map);

        JAXBContext jc = JAXBContext.newInstance(UnadapetedListValuedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        UnadapetedListValuedMapContainer mapContainer2 = (UnadapetedListValuedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

    // Proof that we need GenericListValuedMapAdapter, JAXB cannot handle a List-valued Map correctly.
    @Test(expected = IllegalAnnotationsException.class)
    public void marshalAndUnmarshalUnadaptedListValuedMapContainerWithNulllInternalMap() throws Exception {
        UnadapetedListValuedMapContainer mapContainer = new UnadapetedListValuedMapContainer();

        JAXBContext jc = JAXBContext.newInstance(UnadapetedListValuedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        UnadapetedListValuedMapContainer mapContainer2 = (UnadapetedListValuedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

}
