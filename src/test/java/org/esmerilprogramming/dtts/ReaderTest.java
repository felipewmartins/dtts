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
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * <pre>
 * Test class ReaderTest. 
 * @author eprogramming
 * </pre>
 */
public class ReaderTest {

    List<Path> poms;

    @Before
    public void setup() {
        String ROOT = "./src/test/resources";
        PomFinder pf = new PomFinder();
        try {
            Files.walkFileTree(Paths.get(ROOT), pf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        poms = pf.getPoms();
    }

    @Test
    public void shouldReadOnlyPluginFromPoms() {

        for (Path p : poms) {
            try {
                List<String> pomLines = Files.readAllLines(p, StandardCharsets.UTF_8);
                boolean valid = false;
                boolean print = false;
                for (String s : pomLines) {

                    String z = s.trim();

                    if (z.equals("<plugin>")) {
                        valid = true;
                    }

                    if (valid) {
                        if (z.contains("<groupId>") || z.contains("<artifactId>") || z.contains("<version>")) {
                            print = true;
                        }
                        if (print) {
                            System.out.println(z);
                            print = false;
                        }
                    }

                    if (z.equals("</plugin>")) {
                        valid = false;
                        print = false;
                    }

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void shouldReadOnlyDependencyFromPoms() {

        for (Path p : poms) {
            try {
                List<String> pomLines = Files.readAllLines(p, StandardCharsets.UTF_8);
                boolean valid = false;
                boolean print = false;
                for (String s : pomLines) {

                    String z = s.trim();

                    if (z.equals("<dependency>")) {
                        valid = true;
                    }

                    if (valid) {
                        if (z.contains("<groupId>") || z.contains("<artifactId>") || z.contains("<version>")) {
                            print = true;
                        }
                        if (print) {
                            System.out.println(z);
                            print = false;
                        }
                    }

                    if (z.equals("</dependency>")) {
                        valid = false;
                        print = false;
                    }

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
