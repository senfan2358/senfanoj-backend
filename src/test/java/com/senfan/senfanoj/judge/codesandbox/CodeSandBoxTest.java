package com.senfan.senfanoj.judge.codesandbox;

import com.senfan.senfanoj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.senfan.senfanoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CodeSandBoxTest {
    @Value("${codesandbox.type:example}")
    private String type;

    @Test
    void executeCode() {
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
}