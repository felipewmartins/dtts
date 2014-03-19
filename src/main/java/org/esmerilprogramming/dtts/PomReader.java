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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to read all pom.xml files.
 * 
 * @author eprogramming
 * 
 */
public class PomReader {

    private List<List<String>> allLines = new ArrayList<>();
    
    public List<List<String>> readAll(List<Path> poms) {
        for (Path p : poms) {
            try {
                List<String> pomLines = Files.readAllLines(p, StandardCharsets.UTF_8);
                allLines.add(pomLines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return allLines;
    }

    public List<List<String>> getAllLines() {
        return allLines;
    }
    
}
