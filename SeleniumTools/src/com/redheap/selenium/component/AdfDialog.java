package com.redheap.selenium.component;

import org.openqa.selenium.WebDriver;

public class AdfDialog extends AdfComponent {

    // see http://jdevadf.oracle.com/adf-richclient-demo/docs/js-subids.html
    // some subids return only html tags, some return tags that are full adf components
    private static final String SUBID_close_icon = "close_icon"; // <a> tag
    private static final String SUBID_content = "content"; // <td> tag
    private static final String SUBID_title = "title"; // <div> element
    private static final String SUBID_title_icon = "title_icon"; // ??, probably <a> tag

    private static final String SUBID_ok_button = "ok_button"; // RichButton
    private static final String SUBID_cancel_button = "cancel_button"; // RichButton
    private static final String SUBID_yes_button = "yes_button"; // RichButton
    private static final String SUBID_no_button = "no_button"; // RichButton

    private static final String JS_GET_TITLE = JS_FIND_COMPONENT + "return comp.getTitle();";

    public AdfDialog(WebDriver webDriver, String clientid) {
        super(webDriver, clientid);
    }

    public String getTitle() {
        return (String) executeScript(JS_GET_TITLE, getClientId());
    }

    public AdfButton findOkButton() {
        return findSubIdComponent(SUBID_ok_button);
    }

    public AdfButton findCancelButton() {
        return findSubIdComponent(SUBID_cancel_button);
    }

    public AdfButton findYesButton() {
        return findSubIdComponent(SUBID_yes_button);
    }

    public AdfButton findNoButton() {
        return findSubIdComponent(SUBID_no_button);
    }

}
