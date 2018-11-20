package tests.github;

import base.BaseUtil;
import org.testng.annotations.Test;

public class SignUpPage extends BaseUtil {

    @Test
    public void validate_Create_Your_Personal_Account_Elements() {
        in.Visual.verify(driver,"TestPage");
        System.out.println(in.Visual.getLastResultUrl());
    }

}
