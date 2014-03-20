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
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Utility enum with the main logic.
 * 
 * @author eprogramming
 * </pre>
 */
public enum DttsProcessor {

    INSTANCE;

    private static final String GROUP_ID = "/groupId";
    private static final String ARTIFACT_ID = "/artifactId";
    private static final String VERSION = "/version";
    private static final String PLUGIN = "plugin";
    private static final String AT = "@";

    private List<Dtts> dttsList = new ArrayList<>();

    public void fill(List<String> hulkPom) {
        Dtts dtts = new Dtts();
        
        for (String s : hulkPom) {
            if (s.contains(GROUP_ID)) {
                dtts.setGroup(s.trim());
            } else if (s.contains(ARTIFACT_ID)) {
                dtts.setArtifact(s.trim());
            } else if (s.contains(VERSION)) {
                dtts.setVersion(s.trim());
            }
            if (Validator.INSTANCE.isDttsCompleteAndValid(dtts)) {
                dttsList.add(dtts);
                dtts = new Dtts();
            }
        }
        
        Collections.sort(dttsList, new Comparator<Dtts>() {
            @Override
            public int compare(final Dtts d1, final Dtts d2) {
                int x = d1.getGroup().compareTo(d2.getGroup());
                if (x == 0) {
                    x = d1.getArtifact().compareTo(d2.getArtifact());
                }
                return x;
            }
        });
        
    }

    public Set<Dtts> getDeps() {
        Set<Dtts> list = new HashSet<>();
        for (Dtts d : dttsList) {
            if (!d.getArtifact().contains(PLUGIN)) {
                list.add(d);
            }
        }
        return list;
    }
    
    public List<Dtts> getDepsList() {
        List<Dtts> list = new LinkedList<>();
        for (Dtts d : dttsList) {
            if (!d.getArtifact().contains(PLUGIN)) {
                list.add(d);
            }
        }
        return list;
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
    
    public List<Dtts> getPluginsList() {
        List<Dtts> list = new LinkedList<>();
        for (Dtts d : dttsList) {
            if (d.getArtifact().contains(PLUGIN)) {
                list.add(d);
            }
        }
        return list;
    }

    public Set<Dtts> getDepStrangers() {
        
        Set<Dtts> strangers = new LinkedHashSet<>();
        String y = ""; // will hold only group and artifact.
        String x = ""; // dot not discard the first.
        for (Dtts d : getDepsList()) {
            if (!(d.getGroup() + d.getArtifact()).equals(y)) {
                y = (d.getGroup() + d.getArtifact());
                x = (d.getGroup() + AT + d.getArtifact() + AT + d.getVersion());
            } else {
                // Add the first.
                Dtts stranger = new Dtts(x.split(AT)[0], x.split(AT)[1], x.split(AT)[2]);
                strangers.add(stranger);
                // add other.
                strangers.add(d);
            }
        }
        
        String z = ""; // will hold only group and artifact.
        String k = ""; // dot not discard the first.
        Set<Dtts> strangers2 = new LinkedHashSet<>();
        for (Dtts d : strangers) {
            if (!(d.getGroup() + d.getArtifact()).equals(z)) {
                z = (d.getGroup() + d.getArtifact());
                k = (d.getGroup() + AT + d.getArtifact() + AT + d.getVersion());
            } else {
                // Add the first.
                Dtts stranger = new Dtts(k.split(AT)[0], k.split(AT)[1], k.split(AT)[2]);
                strangers2.add(stranger);
                // add other.
                strangers2.add(d);
            }
        }
        
        return strangers2;
    }
    
    public Set<Dtts> getPlugStrangers() {
        
        Set<Dtts> strangers = new LinkedHashSet<>();
        String y = ""; // will hold only group and artifact.
        String x = ""; // dot not discard the first.
        for (Dtts d : getPluginsList()) {
            if (!(d.getGroup() + d.getArtifact()).equals(y)) {
                y = (d.getGroup() + d.getArtifact());
                x = (d.getGroup() + AT + d.getArtifact() + AT + d.getVersion());
            } else {
                // Add the first.
                Dtts stranger = new Dtts(x.split(AT)[0], x.split(AT)[1], x.split(AT)[2]);
                strangers.add(stranger);
                // add other.
                strangers.add(d);
            }
        }
        
        String z = ""; // will hold only group and artifact.
        String k = ""; // dot not discard the first.
        Set<Dtts> strangers2 = new LinkedHashSet<>();
        for (Dtts d : strangers) {
            if (!(d.getGroup() + d.getArtifact()).equals(z)) {
                z = (d.getGroup() + d.getArtifact());
                k = (d.getGroup() + AT + d.getArtifact() + AT + d.getVersion());
            } else {
                // Add the first.
                Dtts stranger = new Dtts(k.split(AT)[0], k.split(AT)[1], k.split(AT)[2]);
                strangers2.add(stranger);
                // add other.
                strangers2.add(d);
            }
        }
        
        return strangers2;
    }

}
