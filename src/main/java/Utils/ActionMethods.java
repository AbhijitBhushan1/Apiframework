package Utils;

import Enviornment.BaseUrl;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.Map;

public class ActionMethods {
    @Step("calling get user method list")
    public static Response getMethod(Map<String,String> header) throws IOException {
       return BaseApi.sendGet(BaseUrl.url+Endpoints.getUser,header);
    }
}
