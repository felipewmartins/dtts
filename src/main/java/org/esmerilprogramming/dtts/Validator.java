/*-
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
 * <pre>
 * Utility enum Validator.
 * 
 * @author eprogramming
 * </pre>
 */
public enum Validator {

    INSTANCE;
    
    private static final String POM = "pom.xml";
    private static final String TARGET = "target";
    private static final String BRACE = "${";
    private static final String COMMENT = "<!-";
    private static final String VERSION_DOT = "version.";
    
    /**
     * <pre>
     * Checks if is a valid pom.xml
     * @param path Path
     * @return boolean
     * </pre>
     */
    public boolean isValidPom(Path path) {
        boolean valid = false;
        if (path.endsWith(POM) && !path.toString().contains(TARGET)) {
            valid = true;
        }
        return valid;
    }
    
    /**
     * <pre>
     * Checks if is a valid directory to scan.
     * @param path String
     * @return boolean
     * </pre>
     */
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
    
    /**
     * <pre>
     * Check if object Dtts has correct values.
     * @param dtts Dtts
     * @return boolean
     * </pre>
     */
    public boolean isDttsCompleteAndValid(Dtts dtts) {
        boolean completeAndValid = false;
        if (dtts.getGroup() != null && dtts.getArtifact() != null && dtts.getVersion() != null) {
            String dttsStr = dtts.toString();
            if (dttsStr.indexOf(BRACE) < 0 && dttsStr.indexOf(COMMENT) < 0 && dttsStr.indexOf(VERSION_DOT) < 0) {
                completeAndValid = true;
            }
        }
        return completeAndValid;
    }
}
