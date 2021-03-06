/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.remoteServer.agent.util;

import com.intellij.remoteServer.agent.RemoteAgent;
import com.intellij.remoteServer.agent.annotation.ChildCall;
import com.intellij.remoteServer.agent.annotation.FinalCall;

/**
 * @author michael.golubev
 */
public interface CloudGitAgent<C extends CloudAgentConfigBase, D extends CloudGitAgentDeployment> extends RemoteAgent {

  void connect(C config, CloudAgentErrorHandler errorHandler, CloudAgentLogger logger);

  @FinalCall
  void disconnect();

  @ChildCall
  CloudApplication[] getApplications();

  @ChildCall
  D createDeployment(String deploymentName, CloudLoggingHandler loggingHandler);
}
