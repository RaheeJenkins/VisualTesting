package tests.github;

import base.BaseUtil;
import org.testng.annotations.Test;
public class LoginPage extends BaseUtil {

    @Test
    public void validate_Input_Fields_And_Surrounding_Elements() {
        in.Visual.verify(driver,"GitHub Login Page");
        System.out.println(in.Visual.getLastResultUrl());
    }

}
