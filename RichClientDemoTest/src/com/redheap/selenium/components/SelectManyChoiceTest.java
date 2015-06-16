package com.redheap.selenium.components;

import com.redheap.selenium.component.AdfSelectManyChoice;
import com.redheap.selenium.junit.PageProvider;
import com.redheap.selenium.junit.SavePageSourceOnFailure;
import com.redheap.selenium.junit.ScreenshotOnFailure;
import com.redheap.selenium.junit.WebDriverResource;
import com.redheap.selenium.pages.SelectManyChoiceDemoPage;

import java.io.File;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class SelectManyChoiceTest {

    @ClassRule
    public static WebDriverResource driver = new WebDriverResource();
    @Rule
    public PageProvider<SelectManyChoiceDemoPage> pages =
        new PageProvider(SelectManyChoiceDemoPage.class, HOME_PAGE, driver.getDriver());
    @Rule
    public TestWatcher screenshotOnFailure = new ScreenshotOnFailure(driver.getDriver(), new File("errors"));
    @Rule
    public TestWatcher saveSourceOnFailure = new SavePageSourceOnFailure(driver.getDriver(), new File("errors"));

    private static final String HOME_PAGE =
        "http://localhost:7101/adf-richclient-demo/faces/components/selectManyChoice.jspx";

    @Test
    public void testItems() {
        AdfSelectManyChoice choice = pages.goHome().findDrinksSelectManyChoice();
        assertEquals("coffee", choice.getItemLabel(0));
        assertEquals("0", choice.getItemValue(0));
        assertEquals(0, choice.getItemIndexByLabel("coffee"));
        assertEquals("tea", choice.getItemLabel(1));
        assertEquals("1", choice.getItemValue(1));
        assertEquals(1, choice.getItemIndexByLabel("tea"));
        // milk (value "2") is rendered=false
        assertEquals("fizz", choice.getItemLabel(2));
        assertEquals("3", choice.getItemValue(2));
        assertEquals(2, choice.getItemIndexByLabel("fizz"));
        // item "4" is rendered=false
        assertEquals("beer", choice.getItemLabel(3));
        assertEquals("5", choice.getItemValue(3));
        assertEquals(3, choice.getItemIndexByLabel("beer"));
        assertEquals("lemonade", choice.getItemLabel(4));
        assertEquals("6", choice.getItemValue(4));
        assertEquals(4, choice.getItemIndexByLabel("lemonade"));
    }

    @Test
    public void testSetValue() {
        AdfSelectManyChoice choice = pages.goHome().findDrinksSelectManyChoice();
        choice.clickItemsByIndices(1, 3, 4); // idx=3 is disabled so should not work
        assertEquals( "tea; lemonade", choice.getContent());
        // selected values
        List<Object> selectedValues = choice.getValue();
        assertEquals(2, selectedValues.size());
        assertEquals("1", selectedValues.get(0));
        assertEquals("6", selectedValues.get(1));
        // selected indices
        long[] selectedIndices = choice.getValueIndices();
        assertEquals(2, selectedIndices.length);
        assertEquals(1, selectedIndices[0]);
        assertEquals(4, selectedIndices[1]);
        List<String> selectedLabels = choice.getValueLabels();
        assertEquals(2, selectedLabels.size());
        assertEquals("tea", selectedLabels.get(0));
        assertEquals("lemonade", selectedLabels.get(1));
        choice.clickItemsByLabels("fizz", "item6");
        assertEquals("tea; fizz; lemonade; item6", choice.getContent());
        selectedLabels = choice.getValueLabels();
        assertEquals(4, selectedLabels.size());
        assertEquals("tea", selectedLabels.get(0));
        assertEquals("fizz", selectedLabels.get(1));
        assertEquals("lemonade", selectedLabels.get(2));
        assertEquals("item6", selectedLabels.get(3));
    }

    public static void main(String[] args) {
        String[] args2 = { SelectManyChoiceTest.class.getName() };
        org.junit.runner.JUnitCore.main(args2);
    }

}
