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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility enum with the main logic.
 * 
 * @author eprogramming
 * 
 */
public enum DttsProcessor {

    INSTANCE;
    
    private static final String GROUP_ID = "/groupId";
    private static final String ARTIFACT_ID = "/artifactId";
    private static final String VERSION = "/version";
    private static final String PLUGIN = "plugin";

    private Set<Dtts> dttsSet = new HashSet<>();

    public void fillSet(List<List<String>> allPomLines) {
        for (List<String> pomLines : allPomLines) {
            Dtts dtts = new Dtts();
            for (String s : pomLines) {
                if (s.contains(GROUP_ID)) {
                    dtts.setGroup(s.trim());
                }
                if (s.contains(ARTIFACT_ID)) {
                    dtts.setArtifact(s.trim());
                }
                if (s.contains(VERSION)) {
                    dtts.setVersion(s.trim());
                }

                if (Validator.INSTANCE.isDttsCompleteAndValid(dtts)) {
                    dttsSet.add(dtts);
                    dtts = new Dtts();
                }
            }
        }
    }
    
    public Set<Dtts> getDeps() {
        Set<Dtts> dttsTypeDep = new HashSet<>();
        for (Dtts d : dttsSet) {
            if (!d.getArtifact().contains(PLUGIN)) {
                dttsTypeDep.add(d);
            }
        }
        return dttsTypeDep;
    }
    
    public Set<Dtts> getPlugins() {
        Set<Dtts> dttsTypePlug = new HashSet<>();
        for (Dtts d : dttsSet) {
            if (d.getArtifact().contains(PLUGIN)) {
                dttsTypePlug.add(d);
            }
        }
        return dttsTypePlug;
    }
    
    public Set<Dtts> getPluginsStrangers() {
        // find same groupid and artifactid and different version.
        
        return null;
    }
    
    public Set<Dtts> getDepStrangers() {
        // find same groupid and artifactid and different version.
        
        return null;
    }

}
