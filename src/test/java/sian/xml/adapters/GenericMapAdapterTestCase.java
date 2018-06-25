package sian.xml.adapters;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.junit.Test;

import lombok.Data;

public class GenericMapAdapterTestCase {

    @Data
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class AdaptedMapContainer {
        @XmlJavaTypeAdapter(GenericMapAdapter.class)
        Map<Integer, String> map;
    }

    @Data
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class UnadapetedMapContainer {
        Map<Integer, String> map;
    }

    @Test
    public void marshalAndUnmarshalAdaptedMultiValuedMapContainer() throws Exception {
        AdaptedMapContainer mapContainer = new AdaptedMapContainer();

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        mapContainer.setMap(map);

        JAXBContext jc = JAXBContext.newInstance(AdaptedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        AdaptedMapContainer mapContainer2 = (AdaptedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

    @Test
    public void marshalAndUnmarshalAdaptedMultiValuedMapContainerWithNullInternalMap() throws Exception {
        AdaptedMapContainer mapContainer = new AdaptedMapContainer();

        JAXBContext jc = JAXBContext.newInstance(AdaptedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        AdaptedMapContainer mapContainer2 = (AdaptedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

    // Proof that we don't need GenericMapAdaper, JAXB can handle a simple Map correctly.
    @Test
    public void marshalAndUnmarshalUnadaptedMultiValuedMapContainer() throws Exception {
        UnadapetedMapContainer mapContainer = new UnadapetedMapContainer();

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        mapContainer.setMap(map);

        JAXBContext jc = JAXBContext.newInstance(UnadapetedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        UnadapetedMapContainer mapContainer2 = (UnadapetedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

    // Proof that we don't need GenericMapAdaper, JAXB can handle a simple Map correctly.
    @Test
    public void marshalAndUnmarshalUnadaptedMultiValuedMapContainerNullInternalMap() throws Exception {
        UnadapetedMapContainer mapContainer = new UnadapetedMapContainer();

        JAXBContext jc = JAXBContext.newInstance(UnadapetedMapContainer.class);
        StringWriter sw = new StringWriter();
        jc.createMarshaller().marshal(mapContainer, sw);

        String result = sw.toString();
        assertNotNull(result);

        StringReader sr = new StringReader(result);
        UnadapetedMapContainer mapContainer2 = (UnadapetedMapContainer) jc.createUnmarshaller().unmarshal(sr);

        assertEquals(mapContainer, mapContainer2);
    }

}
