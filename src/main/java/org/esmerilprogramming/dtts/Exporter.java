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

import java.util.List;
import java.util.Set;

/**
 * Utility enum to export results to file and console.
 * 
 * @author eprogramming
 * 
 */
public enum Exporter {

    INSTANCE;

    public void printDepGroupArtifactVersion(Set<Dtts> dttsSet) {
        System.out.println("Items type dependency found: " + dttsSet.size());
        System.out.println("------------------------------------------------------------------------");
        for (Dtts dtts: dttsSet) {
            System.out.println(dtts.getGroup());
            System.out.println(dtts.getArtifact());
            System.out.println(dtts.getVersion());
            System.out.println("------------------------------------------------------------------------");
        }
    }
    
    public void printPlugGroupArtifactVersion(Set<Dtts> dttsSet) {
        System.out.println("Items type plugin found: " + dttsSet.size());
        System.out.println("------------------------------------------------------------------------");
        for (Dtts dtts: dttsSet) {
            System.out.println(dtts.getGroup());
            System.out.println(dtts.getArtifact());
            System.out.println(dtts.getVersion());
            System.out.println("------------------------------------------------------------------------");
        }
    }
    
    public void printPlugStrangersGroupArtifactVersion(Set<Dtts> dttsSet) {
        System.out.println("Items type plugin strangers found: " + dttsSet.size());
        System.out.println("------------------------------------------------------------------------");
        for (Dtts dtts: dttsSet) {
            System.out.println(dtts.getGroup());
            System.out.println(dtts.getArtifact());
            System.out.println(dtts.getVersion());
            System.out.println("------------------------------------------------------------------------");
        }
    }
    
    public void printDepStrangersGroupArtifactVersion(Set<Dtts> dttsSet) {
        
        System.out.println("Items type dep strangers found: " + dttsSet.size());
        System.out.println("------------------------------------------------------------------------");
        for (Dtts dtts: dttsSet) {
            
            System.out.println(dtts.getGroup());
            System.out.println(dtts.getArtifact());
            System.out.println(dtts.getVersion());
            System.out.println("------------------------------------------------------------------------");
        }
    }

    public void exportGroupArtifactVersion(List<String> pomLines) {
        System.out.println(pomLines);
    }

}