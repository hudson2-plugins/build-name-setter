/*
 * The MIT License
 * 
 * Copyright (c) 2012, Oracle Corporation, Christine Lu
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.hudsonci.plugins;

import hudson.Launcher;
import hudson.Extension;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.model.Build;
import hudson.tasks.Builder;
import hudson.tasks.BuildWrapper;
import hudson.tasks.BuildWrapperDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import java.io.IOException;
import java.util.Map;

/**
 * Display Name and Description Setter {@link Builder}.
 *
 * @author Christine Lu
 */
public class BuildNameSetter extends BuildWrapper {

    private final String name;
    private final String description;
    private final Evaluator evaluator;
   

    @DataBoundConstructor
    public BuildNameSetter(String name, String description) {
        this.name = name;
        this.description = description;
        evaluator = new Evaluator();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    

    @Override
    public Environment setUp(Build build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
   
        Map<String, String> paramMap = build.getBuildVariables();
        String newDisplayName = build.getDisplayName();
        if (name.length() != 0) {
            String extra = evaluator.evaluateField(paramMap, name);
            if (extra != null) {
              newDisplayName = newDisplayName + " " + extra;
            }
        }
        String newDesc = null;
        String[] st2 = description.split("\\s");
        if (description.length() != 0) {
            String extra = evaluator.evaluateField(paramMap, description);
            if (extra != null) {
              newDesc = extra;
            }
        }
        try {
            build.setDisplayName(newDisplayName);
            listener.getLogger().println("Set DisplayName to " + newDisplayName);
            if (newDesc != null && newDesc.length() != 0) {
                build.setDescription(newDesc);
                listener.getLogger().println("Set Description to " + newDesc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return super.setUp(build, launcher, listener);
        return new Environment(){};
    }

  
    @Extension
    public static final class DescriptorImpl extends BuildWrapperDescriptor {

        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types
            return true;
        }

        public String getDisplayName() {
            return "Set Build Display Name and/or Description";
        }

        @Override
        public boolean isApplicable(AbstractProject<?, ?> ap) {
            return true;
        }
    }
}
