package com.senfan.senfanoj.judge.codesandbox.impl;

import com.senfan.senfanoj.judge.codesandbox.CodeSandBox;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.senfan.senfanoj.model.dto.questionsubmit.JudgeInfo;
import com.senfan.senfanoj.model.enums.JudgeInfoMessageEnum;
import com.senfan.senfanoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱（仅为了跑通业务流程）
 */
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest excuteCodeRequest) {
        List<String> inputList = excuteCodeRequest.getInputList();
        String code = excuteCodeRequest.getCode();
        String language = excuteCodeRequest.getLanguage();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}
