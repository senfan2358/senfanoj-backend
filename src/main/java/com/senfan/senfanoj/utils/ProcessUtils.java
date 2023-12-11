package com.senfan.senfanoj.utils;

import com.senfan.senfanoj.model.dto.codeExecute.ExecuteMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @create: 2023-12-11 13:52
 **/
public class ProcessUtils {

    public static ExecuteMessage runProcessAndGetMessage(Process runProcess, String 编译) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
            // 正常退出
            // 1. 分批获取进程的正常输出
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            List<String> outputStrList = new ArrayList<>();
            // 1.1 逐行读取
            String compileOutputLine ;
            while((compileOutputLine = bufferedReader.readLine()) != null){
                outputStrList.add(compileOutputLine);
            }
            executeMessage.setMessage(StringUtils.join(outputStrList,"\n"));
            if (exitValue != 0){
                // 异常退出
                System.out.println("编译异常，错误码："+exitValue);
                // 1. 获取错误信息
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                List<String> errorStrList = new ArrayList<>();
                String line;
                while((line = errorReader.readLine()) != null){
                    errorStrList.add(line);
                }
                executeMessage.setErrorMessage(new String(StringUtils.join(errorStrList, "\n").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
            }
            stopWatch.stop();
            executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        }catch (Exception e){

        }
        return executeMessage;
    }
}
