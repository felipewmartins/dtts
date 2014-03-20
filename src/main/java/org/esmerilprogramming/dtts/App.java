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
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The main class.
 * 
 * @author eprogramming
 * 
 */
public class App {

    public static void main(String[] args) {

        //String dirToScan = "/home/hf/java/projetos/tomeex";
        String dirToScan = ".";

        if (args.length > 0) {
            if (Validator.INSTANCE.isValidDir(args[0])) {
                dirToScan = args[0];
            }
        }

        PomFinder pomFinder = new PomFinder();

        try {
            Files.walkFileTree(Paths.get(dirToScan), pomFinder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PomReader pomReader = new PomReader();
        pomReader.readAllHulkMode(pomFinder.getPoms());
        
        //DttsProcessor.INSTANCE.fillSet(pomReader.getAllLines());
        DttsProcessor.INSTANCE.fill(pomReader.getAllLinesHulkMode());
        
        Exporter.INSTANCE.printDepGroupArtifactVersion(DttsProcessor.INSTANCE.getDeps());
        Exporter.INSTANCE.printPlugGroupArtifactVersion(DttsProcessor.INSTANCE.getPlugins());
        Exporter.INSTANCE.printDepStrangersGroupArtifactVersion(DttsProcessor.INSTANCE.getDepStrangers());
        Exporter.INSTANCE.printPlugStrangersGroupArtifactVersion(DttsProcessor.INSTANCE.getPlugStrangers());
        
    }
    
}
