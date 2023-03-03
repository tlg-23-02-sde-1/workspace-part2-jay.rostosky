/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.entertainment.catalog;

import static org.junit.Assert.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;
import com.entertainment.Television;

public class CatalogTest {

    @Test
    public void findByBrands_shouldReturnMapWithEntries_whenBrandsPassed() {
        Map<String,Integer> brandAndCountMap = new LinkedHashMap<>();
        brandAndCountMap.put("Sony",   7);
        brandAndCountMap.put("Zenith", 9);
        brandAndCountMap.put("LG",     0);

        String[] brandsPassed = brandAndCountMap.keySet().toArray(new String[0]);

        Map<String,Collection<Television>> tvMap = Catalog.findByBrands(brandsPassed);
        assertEquals(3, tvMap.size());

        for (var entry : brandAndCountMap.entrySet()) {
            verifyTelevisionCollection(tvMap.get(entry.getKey()), entry.getKey(), entry.getValue());
        }
    }

    private void verifyTelevisionCollection(Collection<Television> tvs, String brand, int size) {
        assertEquals(size, tvs.size());
        for (Television tv : tvs) {
            assertEquals(brand, tv.getBrand());
        }
    }

    @Test
    public void findByBrands_shouldReturnEmptyMap_whenNoBrandsPassed() {
        Map<String,Collection<Television>> tvMap = Catalog.findByBrands();
        assertTrue(tvMap.isEmpty());
    }

    @Test
    public void findByBrand_shouldReturnCollectionWithThatBrand_whenBrandFound() {
        Collection<Television> tvs = Catalog.findByBrand("Sony");

        assertEquals(7, tvs.size());
        for (Television tv : tvs) {
            assertEquals("Sony", tv.getBrand());
        }
    }

    /**
     * Contract: a no-matches result should be an empty collection (not null).
     */
    @Test
    public void findByBrand_shouldReturnEmptyCollection_whenBrandNotFound() {
        Collection<Television> tvs = Catalog.findByBrand("NO-MATCHES");
        assertTrue(tvs.isEmpty());
    }
}