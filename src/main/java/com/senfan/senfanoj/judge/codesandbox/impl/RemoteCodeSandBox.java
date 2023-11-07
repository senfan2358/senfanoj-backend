package com.senfan.senfanoj.judge.codesandbox.impl;

import com.senfan.senfanoj.judge.codesandbox.CodeSandBox;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.senfan.senfanoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
public class RemoteCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest excuteCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
