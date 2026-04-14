package com.srm.qualityhr.dataproviders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srm.qualityhr.model.LoginData;
import com.srm.qualityhr.utils.ConfigReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = TestDataProvider.class.getClassLoader()
                .getResourceAsStream("testdata/login-data.json")) {
            if (inputStream == null) {
                throw new IllegalStateException("login-data.json not found");
            }
            List<LoginData> loginData = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            Object[][] data = new Object[loginData.size()][5];
            for (int index = 0; index < loginData.size(); index++) {
                LoginData current = loginData.get(index);
                data[index][0] = current.getScenario();
                data[index][1] = ConfigReader.get(current.getUsernameKey());
                data[index][2] = ConfigReader.get(current.getPasswordKey());
                data[index][3] = current.isValid();
                data[index][4] = current.getExpectedMessage();
            }
            return data;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to read login data", exception);
        }
    }
}
