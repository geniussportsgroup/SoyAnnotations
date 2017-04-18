package com.geniussports.soy.converters;

import com.geniussports.soy.converters.soydata.ClassToSoyMapDataConverter;
import com.google.common.collect.ImmutableMap;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import example.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClassToSoyMapDataConverterTest {

    @Mock
    private Function<Object, SoyData> mockNameConverter;

    @Mock
    private Function<Object, SoyData> mockEmailConverter;

    @Mock
    private User mockUser;

    @Mock
    private SoyData mockNameData;

    @Mock
    private SoyData mockEmailData;

    private ClassToSoyMapDataConverter converter;

    @Before
    public void setUp() throws Exception {
        when(mockNameConverter.apply(mockUser)).thenReturn(mockNameData);
        when(mockEmailConverter.apply(mockUser)).thenReturn(mockEmailData);
        ImmutableMap.Builder<String, Function<Object, ? extends SoyData>> membersConverters = ImmutableMap.builder();
        membersConverters.put("Name", mockNameConverter);
        membersConverters.put("Email", mockEmailConverter);
        converter = new ClassToSoyMapDataConverter(membersConverters.build(), false);
    }

    @Test
    public void testConvert() throws Exception {
        SoyMapData soyMapData = converter.apply(mockUser);
        assertNotNull(soyMapData);
        assertEquals(2, soyMapData.getKeys().size());
        assertEquals(mockNameData, soyMapData.getSingle("Name"));
        assertEquals(mockEmailData, soyMapData.getSingle("Email"));
    }
}
