package com.senfan.senfanoj.judge.codesandbox;

import cn.hutool.core.io.FileUtil;
import com.senfan.senfanoj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.senfan.senfanoj.model.dto.codeExecute.ExecuteMessage;
import com.senfan.senfanoj.model.enums.QuestionSubmitLanguageEnum;
import com.senfan.senfanoj.utils.ProcessUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CodeSandBoxTest {
    public static final String GLOBAL_CODE_DIR_NAME = "code";
    public static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";
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

    /**
     * 1. 将用户代码保存为文件
     * @param code
     * @return
     */
    public static File saveCodeToFile(String code) {
        // 获取项目路径
        String userDir = System.getProperty("user.dir");
        String globalCodePathName = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        // 判断全局代码目录是否存在，没有则创建
        if (!FileUtil.exist(globalCodePathName)) {
            FileUtil.mkdir(globalCodePathName);
        }
        // 把用户的代码隔离存放
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + File.separator + GLOBAL_JAVA_CLASS_NAME;
        File userCodeFile = FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
        return userCodeFile;
    }

    public static ExecuteMessage compileFile(File userCodeFile) {
        String compileCmd = String.format("javac -encoding utf-8 %s",userCodeFile.getAbsoluteFile());
        ExecuteMessage executeMessage = null;
        try{
            Process compileProcess = Runtime.getRuntime().exec(compileCmd);
            executeMessage = ProcessUtils.runProcessAndGetMessage(compileProcess, "编译");
        }catch (Exception e){

        }
        return executeMessage;
    }
    public static void main(String[] args) {
        File test = saveCodeToFile("public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a =1;\n" +
                "        int b =1;\n" +
                "        System.out.println(a+b);\n" +
                "    }\n" +
                "}");
        ExecuteMessage executeMessage = compileFile(test);
        System.out.println(executeMessage);
    }
}