package com.utilities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CalculatorTest {

    @Mock
    private Calculator calculator;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTest() {
        when(calculator.add(2, 5)).thenReturn(7);
        assertEquals(7, calculator.add(2, 5));
    }
}