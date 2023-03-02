package com.entertainment;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TelevisionTest {
    private Television tv;
    private Television tv2;

    @Before
    public void setUp() {
        tv  = new Television("Samsung", 32, DisplayType.LED);
        tv2 = new Television("Samsung", 32, DisplayType.LED);
    }

    @Test
    public void setVolume_shouldStoreVolume_whenValidValue_lowerBound() {
        tv.setVolume(0);
        assertEquals(0, tv.getVolume());
    }

    @Test
    public void setVolume_shouldStoreVolume_whenValidValue_upperBound() {
        tv.setVolume(100);
        assertEquals(100, tv.getVolume());
    }

    @Test(expected=IllegalArgumentException.class)
    public void setVolume_shouldThrowIllegalArgumentException_whenInvalidValue_lowerBound() {
        tv.setVolume(-1);  // should trigger exception
    }

    @Test(expected=IllegalArgumentException.class)
    public void setVolume_shouldThrowIllegalArgumentException_whenInvalidValue_upperBound() {
        tv.setVolume(101); // should trigger exception
    }

    @Test
    public void changeChannel_shouldStoreChannel_whenValidValue_lowerBound()
    throws InvalidChannelException {
        tv.changeChannel(1);
        assertEquals(1, tv.getCurrentChannel());
    }

    @Test
    public void changeChannel_shouldStoreChannel_whenValidValue_upperBound()
    throws InvalidChannelException {
        tv.changeChannel(999);
        assertEquals(999, tv.getCurrentChannel());
    }

    @Test(expected=InvalidChannelException.class)
    public void changeChannel_shouldThrowIllegalArgumentException_whenInvalidValue_lowerBound()
    throws InvalidChannelException {
        try {
            tv.changeChannel(0);
            fail("Should have thrown InvalidChannelException");
        }
        catch (InvalidChannelException e) {
            assertEquals("Invalid channel: 0. Allowed range: [1,999].", e.getMessage());
            throw e;
        }
    }

    @Test
    public void changeChannel_shouldThrowIllegalArgumentException_whenInvalidValue_upperBound() {
        try {
            tv.changeChannel(1000);
            fail("Should have thrown InvalidChannelException");
        }
        catch (InvalidChannelException e) {
            assertEquals("Invalid channel: 1000. Allowed range: [1,999].", e.getMessage());
        }
    }

    @Test
    public void compareTo_shouldReturnZero_whenSameBrand() {
        assertEquals(0, tv.compareTo(tv2));
        assertTrue(tv.compareTo(tv2) == 0);
    }

    @Test
    public void compareTo_shouldReturnNegativeNumber_when1stBrandLessThan2ndBrand() {
        tv.setBrand("A_lessThan");
        tv2.setBrand("B_greaterThan");
        assertTrue(tv.compareTo(tv2) < 0);
    }

    @Test
    public void compareTo_shouldReturnPositiveNumber_when1stBrandGreaterThan2ndBrand() {
        tv.setBrand("B_greaterThan");
        tv2.setBrand("A_lessThan");
        assertTrue(tv.compareTo(tv2) > 0);
    }

    @Test
    public void equals_shouldReturnTrue_allPropertiesSame() {
        assertEquals(tv, tv2);
    }

    @Test
    public void equals_shouldReturnFalse_sameBrand_sameVolume_differentDisplay() {
        tv2.setDisplay(DisplayType.PLASMA);
        assertNotEquals(tv, tv2);
    }

    @Test
    public void equals_shouldReturnFalse_sameBrand_differentVolume_sameDisplay() {
        tv2.setVolume(50);
        assertNotEquals(tv, tv2);
    }

    @Test
    public void equals_shouldReturnFalse_differentBrand_sameVolume_sameDisplay() {
        tv2.setBrand("DIFFERENT");
        assertNotEquals(tv, tv2);
    }

    @Test
    public void hashCode_shouldReturnSameValue_equalObjects() {
        assertEquals(tv.hashCode(), tv2.hashCode());
    }
}