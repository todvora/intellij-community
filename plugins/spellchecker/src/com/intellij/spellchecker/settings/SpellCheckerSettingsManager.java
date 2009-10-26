/*
 * Copyright 2000-2009 JetBrains s.r.o.
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
package com.intellij.spellchecker.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.spellchecker.util.SpellCheckerBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SpellCheckerSettingsManager implements Configurable {
  private SpellCheckerSettingsPane settingsPane;
  private SpellCheckerSettings settings;
  private Project project;

  public static SpellCheckerSettingsManager getInstance(Project project) {
    return ShowSettingsUtil.getInstance().findProjectConfigurable(project, SpellCheckerSettingsManager.class);
  }

  public SpellCheckerSettingsManager(Project project) {
    this.project = project;
    settings = SpellCheckerSettings.getInstance(project);
  }

  @Nls
   public String getDisplayName() {
     return SpellCheckerBundle.message("spelling");
   }

   @Nullable
   public Icon getIcon() {
     return null;
   }

   @Nullable
   @NonNls
   public String getHelpTopic() {
     return "reference.settings.ide.settings.spelling";
   }


  public JComponent createComponent() {
    if (settingsPane == null) {
      settingsPane = new SpellCheckerSettingsPane(settings,project);
    }
    return settingsPane.getPane();
  }

  public boolean isModified() {
    return settingsPane == null || settingsPane.isModified();
  }

  public void apply() throws ConfigurationException {
    if (settingsPane != null) {
      settingsPane.apply();
    }
  }

  public void reset() {
    if (settingsPane != null) {
      settingsPane.reset();
    }
  }

  public void disposeUIResources() {
    settingsPane = null;
  }
}