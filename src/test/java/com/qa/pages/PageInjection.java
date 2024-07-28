package com.qa.pages;

import com.qa.pages.transfer.InquiryPage;
import com.qa.pages.transfer.TypeTransferPage;
import com.qa.pages.transfer.VerifyPage;
import com.utils.TestUtils;

public class PageInjection {
    public TestUtils utils = new TestUtils();
    public IntroPage introPage = new IntroPage();
    public HomePage homePage = new HomePage();
    public TypeTransferPage typeTransferPage = new TypeTransferPage();
    public InquiryPage inquiryPage = new InquiryPage();
    public VerifyPage verifyPage = new VerifyPage();
    public ProfilePage profilePage = new ProfilePage();
    public SettingPage settingPage = new SettingPage();
    public DigitalOTPPage digitalOTPpage = new DigitalOTPPage();
}
