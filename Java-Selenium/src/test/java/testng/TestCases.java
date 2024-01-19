package testng;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.ExcelUtility;


import java.util.HashMap;

public class TestCases {

    public void setup() {
    	RestAssured.baseURI = "https://restcountries.com/v3.1/translation/";
    }

    @DataProvider(name="DP")
    public String[][] dataProvider() throws Exception{
        String[][] dp = new String[3][3];
        for (int i=1; i<4; i++) {
            HashMap<String, String> hm = ExcelUtility.getData("TestData", "Sheet1", i);
            if (hm != null) {
                int j = 0;
                for (String key : hm.keySet()) {
                    dp[i-1][j] = hm.getOrDefault(key, "");
                    j++;
                }
            }
        }
        return dp;
    }

    @Test(dataProvider = "DP")
    public void test1(String[] value) {
        Response response = RestAssured.given().get("https://restcountries.com/v3.1/translation/" + value[0]);
        Assert.assertEquals(response.statusCode(), Integer.valueOf(value[2]));
    }
}
