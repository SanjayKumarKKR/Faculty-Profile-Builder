package com.example.facultyprofile;


import com.example.facultyprofile.Activity.ProfileActivity;
import com.example.facultyprofile.Activity.SearchActivity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void validationtest_searchactivityforstrings() {
        SearchActivity s=new SearchActivity();
        assertTrue(s.validateallstring("Sanjay"));
    }
    @Test
    public void validationtestfalse_searchactivityforstrings() {
        SearchActivity s=new SearchActivity();
        assertFalse(s.validateallstring("Sanjay123"));
    }
    @Test
    public void validationtest_searchactivityfornumbers() {
        ProfileActivity p = new ProfileActivity();
        assertTrue(p.validateallnumber("1"));
    }
    @Test
    public void validationtestfalse_searchactivityfornumbers() {
        ProfileActivity p = new ProfileActivity();
        assertFalse(p.validateallnumber("abc"));
    }
}