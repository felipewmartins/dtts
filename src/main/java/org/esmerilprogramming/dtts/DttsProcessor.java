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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
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

    public static final String GROUP_ID = "groupId";
    public static final String ARTIFACT_ID = "artifactId";
    public static final String VERSION = "version";
    private static final String AT = "@";

    private List<Dtts> dttsDeps = new ArrayList<>();
    private List<Dtts> dttsPlugs = new ArrayList<>();

    /**
     * <pre>
     * Process all lines and save only dependencies.
     * @param deps List<String>
     * </pre>
     */
    public void processDeps(List<String> deps) {
        Dtts dtts = new Dtts();

        for (String s : deps) {

            if (Validator.INSTANCE.isValidTag(s)) {

                if (s.contains(GROUP_ID)) {
                    dtts.setGroup(s);
                } else if (s.contains(ARTIFACT_ID)) {
                    dtts.setArtifact(s);
                } else if (s.contains(VERSION)) {
                    dtts.setVersion(s);
                }

                if (Validator.INSTANCE.isDttsComplete(dtts)) {
                    dttsDeps.add(dtts);
                    dtts = new Dtts();
                }

            }
        }

        Collections.sort(dttsDeps, new Comparator<Dtts>() {
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

    /**
     * <pre>
     * Process all lines and save only plugins.
     * @param plugs List<String>
     * </pre>
     */
    public void processPlugins(List<String> plugs) {
        Dtts dtts = new Dtts();

        for (String s : plugs) {

            if (Validator.INSTANCE.isValidTag(s)) {

                if (s.contains(GROUP_ID)) {
                    dtts.setGroup(s);
                } else if (s.contains(ARTIFACT_ID)) {
                    dtts.setArtifact(s);
                } else if (s.contains(VERSION)) {
                    dtts.setVersion(s);
                }

                if (Validator.INSTANCE.isDttsComplete(dtts)) {
                    dttsPlugs.add(dtts);
                    dtts = new Dtts();
                }

            }
        }

        Collections.sort(dttsPlugs, new Comparator<Dtts>() {
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

    /**
     * <pre>
     * Find and save same dtts with different versions.
     * @param type int
     * @return Set<Dtts>
     * </pre>
     */
    public Set<Dtts> getStrangersByType(int type) {
        Set<Dtts> strangers = new LinkedHashSet<>();
        String y = "";
        String x = "";

        List<Dtts> list = dttsDeps;
        if (type == PomReader.PLUG) {
            list = dttsPlugs;
        }

        for (Dtts d : list) {
            if (!(d.getGroup() + d.getArtifact()).equals(y)) {
                y = (d.getGroup() + d.getArtifact());
                x = (d.getGroup() + AT + d.getArtifact() + AT + d.getVersion());
            } else {
                Dtts stranger = new Dtts(x.split(AT)[0], x.split(AT)[1], x.split(AT)[2]);
                strangers.add(stranger);
                strangers.add(d);
            }
        }
        return sanitize(strangers);
    }

    /**
     * <pre>
     * Do the same thing compared with the caller but removes the first "different" found.
     * @param set Set<Dtts>
     * @return Set<Dtts>
     * </pre>
     */
    private Set<Dtts> sanitize(Set<Dtts> set) {
        String z = "";
        String k = "";
        Set<Dtts> strangers = new LinkedHashSet<>();
        for (Dtts d : set) {
            if (!(d.getGroup() + d.getArtifact()).equals(z)) {
                z = (d.getGroup() + d.getArtifact());
                k = (d.getGroup() + AT + d.getArtifact() + AT + d.getVersion());
            } else {
                Dtts stranger = new Dtts(k.split(AT)[0], k.split(AT)[1], k.split(AT)[2]);
                strangers.add(stranger);
                strangers.add(d);
            }
        }
        return strangers;
    }

}
