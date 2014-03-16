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
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * Test class PomFinderTest. 
 * @author eprogramming
 *
 */
public class PomFinderTest {

    @Test
    public void shouldFindAllPomFiles() {
        String ROOT = ".";
        FileVisitor<Path> fileVisitor = new PomFinder();
        try {
            Files.walkFileTree(Paths.get(ROOT), fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
