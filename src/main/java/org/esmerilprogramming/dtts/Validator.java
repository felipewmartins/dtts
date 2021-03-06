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
    private static final String DOT_VERSION = ".version";
    

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
    public boolean isDttsComplete(Dtts dtts) {
        boolean complete = false;
        if (dtts.getGroup() != null && dtts.getArtifact() != null && dtts.getVersion() != null) {
            complete = true;
        }
        return complete;
    }

    /**
     * <pre>
     * Check if tag is valid.
     * @param tag String 
     * @return boolean
     * </pre>
     */
    public boolean isValidTag(String tag) {
        boolean valid = false;
        if (!tag.contains(BRACE) && !tag.contains(COMMENT) 
                && !tag.contains(VERSION_DOT) && !tag.contains(DOT_VERSION)) {
            valid = true;
        }
        return valid;
    }
}
