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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Utility enum to export results to file and console.
 * 
 * @author eprogramming
 * </pre>
 */
public enum Exporter {

    INSTANCE;

    private static final String SEP = "-----------------------------------------------------------------------------";
    private static final String TITLE = "#############################################################################";

    /**
     * <pre>
     * Just send to default output console.
     * @param dttsSet Set<Dtts>
     * </pre>
     */
    public void printStrangers(Set<Dtts> dttsSet, int type) {

        String message = "Dependencies ";
        if (type == PomReader.PLUG) {
            message = "Plugins ";
        }

        System.out.println(TITLE);
        System.out.println(message + "strangers found: " + dttsSet.size());
        System.out.println(TITLE);

        System.out.println(SEP);
        for (Dtts dtts : dttsSet) {
            System.out.println(dtts);
            System.out.println(SEP);
        }
    }

    /**
     * <pre>
     * Show files and lines.
     * @param dttsSet Set<Dtts>
     * @param poms List<Path>
     * @param type int
     * </pre>
     */
    public void grepMode(Set<Dtts> dttsSet, List<Path> poms, int type) {

        System.out.println(TITLE);
        if (type == PomReader.DEP) {
            System.out.println("DEPENDENCIES: Files and lines...");    
        } else {
            System.out.println("PLUGINS: Files and lines...");
        }
        System.out.println(TITLE);

        Iterator<Dtts> it = dttsSet.iterator();
        while (it.hasNext()) {
            Dtts dtts = it.next();
            for (Path p : poms) {
                try {
                    List<String> pomLines = Files.readAllLines(p, StandardCharsets.UTF_8);
                    int line = 0;
                    boolean valid = false;
                    for (String s : pomLines) {
                        line = line + 1;
                        String z = s.trim();

                        if (valid) {
                            if (z.contains(dtts.getVersion())) {
                                System.out.println(dtts);
                                System.out.println(p.toFile().getAbsolutePath() + " - line " + line);
                                System.out.println(SEP);
                                valid = false;
                            }
                        }

                        if (z.contains(dtts.getArtifact())) {
                            valid = true;
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
