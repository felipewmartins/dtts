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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
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
    private static final String AT = "@";

    private Set<Dtts> dttsSet = new LinkedHashSet<>();
    private List<Dtts> dttsList = new ArrayList<>();

    public void fillSet(List<List<String>> allPomLines) {
        Dtts dtts = new Dtts();
        for (List<String> pomLines : allPomLines) {
            for (String s : pomLines) {
                
                if (s.contains(GROUP_ID)) {
                    dtts.setGroup(s.trim());
                } else  if (s.contains(ARTIFACT_ID)) {
                    dtts.setArtifact(s.trim());
                } else if (s.contains(VERSION)) {
                    dtts.setVersion(s.trim());
                }

                if (Validator.INSTANCE.isDttsCompleteAndValid(dtts)) {
                    dttsList.add(dtts);
                    dtts = new Dtts();
                }
            }
        }
        
        Collections.sort(dttsList, Dtts.GroupComparator);
    }

    public Set<Dtts> getDeps() {
        Set<Dtts> dttsTypeDep = new LinkedHashSet<>();
        for (Dtts d : dttsList) {
            if (!d.getArtifact().contains(PLUGIN)) {
                dttsTypeDep.add(d);
            }
        }
        return dttsTypeDep;
    }

    public Set<Dtts> getPlugins() {
        Set<Dtts> dttsTypePlug = new LinkedHashSet<>();
        for (Dtts d : dttsList) {
            if (d.getArtifact().contains(PLUGIN)) {
                dttsTypePlug.add(d);
            }
        }
        return dttsTypePlug;
    }

    public Set<Dtts> getPluginsStrangers() {

        Set<Dtts> strangers = new LinkedHashSet<>();
        String xFactor = "";
        String xFactorFull = "";
        
        for (Dtts d : getPlugins()) {
            
            if (!(d.getGroup() + d.getArtifact()).equals(xFactor)) {
                xFactor = (d.getGroup() + d.getArtifact());
                xFactorFull = (d.getGroup() + AT + d.getArtifact() + AT + d.getVersion());
            } else {

                Dtts stranger = new Dtts(xFactorFull.split(AT)[0], xFactorFull.split(AT)[1], xFactorFull.split(AT)[2]);
                strangers.add(stranger);

                String other = d.getGroup() + AT + d.getArtifact() + AT + d.getVersion();
                Dtts stranger2 = new Dtts(other.split(AT)[0], other.split(AT)[1], other.split(AT)[2]);
                strangers.add(stranger2);

            }
        }

        return strangers;
    }

    public Set<Dtts> getDepStrangers() {
        Set<Dtts> strangers = new LinkedHashSet<>();
        String xFactor = "";
        String xFactorFull = "";
        
        Set<Dtts> dset = getDeps();
        
        for (Dtts d : dset) {

            if (!(d.getGroup() + d.getArtifact()).equals(xFactor)) {
                xFactor = (d.getGroup() + d.getArtifact());
                xFactorFull = (d.getGroup() + AT + d.getArtifact() + AT + d.getVersion());
            } else {
                
                Dtts stranger = new Dtts(xFactorFull.split(AT)[0], xFactorFull.split(AT)[1], xFactorFull.split(AT)[2]);
                strangers.add(stranger);

                String other = d.getGroup() + AT + d.getArtifact() + AT + d.getVersion();
                Dtts stranger2 = new Dtts(other.split(AT)[0], other.split(AT)[1], other.split(AT)[2]);
                strangers.add(stranger2);

            }
        }

        return strangers;
    }

}
