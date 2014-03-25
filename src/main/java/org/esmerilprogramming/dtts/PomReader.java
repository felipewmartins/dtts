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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Utility class to read all pom.xml files.
 * 
 * @author eprogramming
 * </pre>
 */
public class PomReader {

    private List<String> deps = new ArrayList<>();
    private List<String> plugs = new ArrayList<>();

    public static final int DEP = 1;
    public static final int PLUG = 2;

    /**
     * <pre>
     * Iterates over list of Paths (pom.xml) files and save all lines of all 
     * pom files on List<String>.
     *  
     * @param poms List<Path>
     * </pre>
     */
    public void readPomLines(List<Path> poms) {
        deps = readByType(poms, DEP);
        plugs = readByType(poms, PLUG);
    }

    /**
     * <pre>
     * Fills a list with deps or plugins based on type parameter.
     * @param poms List<Path> poms
     * @param type int
     * @return List<String>
     * </pre>
     */
    private List<String> readByType(List<Path> poms, int type) {

        List<String> list = new ArrayList<>();
        String typeStart = "<plugin>";
        String typeEnd = "</plugin>";
        if (type == DEP) {
            typeStart = "<dependency>";
            typeEnd = "</dependency>";
        }

        for (Path p : poms) {
            try {
                List<String> pomLines = Files.readAllLines(p, StandardCharsets.UTF_8);
                boolean valid = false;
                boolean save = false;
                for (String s : pomLines) {

                    String z = s.trim();

                    if (z.equals(typeStart)) {
                        valid = true;
                    }

                    if (valid) {
                        if (z.contains(DttsProcessor.GROUP_ID) || z.contains(DttsProcessor.ARTIFACT_ID) || z.contains(DttsProcessor.VERSION)) {
                            save = true;
                        }
                        if (save) {
                            list.add(z);
                            save = false;
                        }
                    }

                    if (z.equals(typeEnd)) {
                        valid = false;
                        save = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public List<String> getDeps() {
        return deps;
    }

    public List<String> getPlugs() {
        return plugs;
    }

}
