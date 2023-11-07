package com.senfan.senfanoj.judge.codesandbox;


import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱
 */
public interface CodeSandBox {
    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
