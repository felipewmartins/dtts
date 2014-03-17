/*
 * Copyright 2014 EsmerilProgramming
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.esmerilprogramming.dtts;

import java.io.File;
import java.nio.file.Path;

/**
 * Utility enum.
 * 
 * @author eprogramming
 * 
 */
public enum Validator {

    INSTANCE;
    
    private static final String KEY_1 = "pom.xml";
    private static final String KEY_2 = "target";
    
    public boolean isValidPom(Path path) {
        boolean valid = false;
        if (path.endsWith(KEY_1) && !path.toString().contains(KEY_2)) {
            valid = true;
        }
        return valid;
    }
    
    public boolean isValidDir(String path) {
        boolean valid = false;
        File f = new File(path);
        if (f.exists()) {
            if (f.isDirectory() && f.canRead()) {
                valid = true;
            }
        }
        return valid;
    }
    
    public boolean isDttsCompleteAndValid(Dtts dtts) {
        boolean completeAndValid = false;
        if (dtts.getGroup() != null && dtts.getArtifact() != null && dtts.getVersion() != null) {
            
            // remove <!-- too.
            
            if (!dtts.getVersion().contains("${")) {
                completeAndValid = true;
            }
        }
        return completeAndValid;
    }
}
