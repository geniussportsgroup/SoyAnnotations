package com.geniussports.soy.helpers;

import example.models.ReflectiveManExample;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ReflectionSoyAnnotationHelperTest {

    private ReflectionFactoryHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new ReflectionFactoryHelper();
    }

    @Test
    public void testIsSoyAnnotated() throws Exception {
        assertFalse(helper.isFactoryClass(String.class));
        assertFalse(helper.isFactoryClass(HashMap.class));
        assertFalse(helper.isFactoryClass(ArrayList.class));
    }

    @Test
    public void testIsSoyMethod() throws Exception {
        assertTrue(helper.isFactoryMethod(ReflectiveManExample.class.getDeclaredMethod("getEmail")));
        assertFalse(helper.isFactoryMethod(ReflectiveManExample.class.getDeclaredMethod("getName")));
        assertFalse(helper.isFactoryMethod(ReflectiveManExample.class.getDeclaredMethod("clearName")));
        assertFalse(helper.isFactoryMethod(ReflectiveManExample.class.getDeclaredMethod("setName", String.class)));
        assertFalse(helper.isFactoryMethod(ReflectiveManExample.class.getDeclaredMethod("setEmail", String.class)));
        assertFalse(helper.isFactoryMethod(ReflectiveManExample.class.getDeclaredMethod("getRace")));
    }

    @Test
    public void testIsSoyField() throws Exception {
        assertTrue(helper.isFactoryField(ReflectiveManExample.class.getDeclaredField("name")));
        assertFalse(helper.isFactoryField(ReflectiveManExample.class.getDeclaredField("RACE")));
        assertFalse(helper.isFactoryField(ReflectiveManExample.class.getDeclaredField("email")));
    }

    @Test
    public void testIsDynamicSoyField() throws Exception {
        assertFalse(helper.isDynamicFactoryField(ReflectiveManExample.class.getDeclaredField("name")));
    }

    @Test
    public void testIsDynamicSoyMethod() throws Exception {
        assertFalse(helper.isDynamicFactoryMethod(ReflectiveManExample.class.getDeclaredMethod("getName")));
    }

    @Test
    public void testIsDynamicSoyClass() throws Exception {
        assertTrue(helper.isDynamicFactoryClass(ReflectiveManExample.class));
    }

    @Test
    public void testHasSoyTemplate() throws Exception {
        assertFalse(helper.hasTemplate(ReflectiveManExample.class));
    }

    @Test(expected = RuntimeException.class)
    public void testGetSoyTemplateName() throws Exception {
        helper.getTemplateName(ReflectiveManExample.class);
    }

    @Test
    public void testGetFieldKey() throws Exception {
        assertEquals("name", helper.getFieldKey(ReflectiveManExample.class.getDeclaredField("name")));
    }

    @Test
    public void testGetMethodKey() throws Exception {
        assertEquals("Name", helper.getMethodKey(ReflectiveManExample.class.getDeclaredMethod("getName")));
    }
}
