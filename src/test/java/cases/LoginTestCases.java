package cases;

import data.builder.login.Login;
import data.model.Credentials;
import org.testng.annotations.DataProvider;

public class LoginTestCases {

    /**
     * Provides valid data for login test
     *
     * @return Array of valid cases
     */
    @DataProvider
    public Object[] validCases() {
        return new Object[]{validLoginCase, validLoginCase2};
    }

    /**
     * Provides invalid email formats for login test
     *
     * @return Array of invalid cases
     */
    @DataProvider
    public Object[] invalidEmailCases() {
        return new Object[]{invalidEmailFormatCase, invalidEmailFormatCase2, invalidEmailFormatCase3};
    }

    /**
     * Provides invalid email formats for login test
     *
     * @return Array of invalid cases
     */
    @DataProvider
    public Object[] invalidCredentialsCases() {
        return new Object[]{invalidCredentialsCase};
    }

    /**
     * Valid login credentials
     */
    private static final Login validLoginCase = Login.builder()
            .credentials(Credentials.builder().email("validemail@cleevio.com").password("password").build())
            .build();


    /**
     * Valid login credentials
     */
    private static final Login validLoginCase2 = Login.builder()
            .credentials(Credentials.builder().email("validemail2@cleevio.com").password("password").build())
            .build();

    /**
     * Invalid login credentials - invalid login format
     */
    private static final Login invalidEmailFormatCase = Login.builder()
            .credentials(Credentials.builder().email("invalidemail").password("password").build())
            .build();

    /**
     * Invalid login credentials - invalid login format
     */
    private static final Login invalidEmailFormatCase2 = Login.builder()
            .credentials(Credentials.builder().email("@test@test").password("password").build())
            .build();

    /**
     * Invalid login credentials - invalid login format
     */
    private static final Login invalidEmailFormatCase3 = Login.builder()
            .credentials(Credentials.builder().email("фварпол@test").password("password").build())
            .build();

    /**
     * Invalid login credentials
     */
    public static final Login invalidCredentialsCase = Login.builder()
            .credentials(Credentials.builder().email("validemail@cleevio.com").password("password").build())
            .build();
}