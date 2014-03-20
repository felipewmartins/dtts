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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * Test class DttsProcessorTest.
 * 
 * @author eprogramming
 * 
 */
public class DttsProcessorTest {

    private static final String AT = "@";

    @Test
    public void shouldPrintDifferentDtts() {

        Set<Dtts> strangers = new LinkedHashSet<>();

        List<Dtts> hulkList = new ArrayList<>();
        hulkList.add(new Dtts("org", "junit", "4.8.1")); // 1
        hulkList.add(new Dtts("org", "junit", "4.8.5")); // 2
        hulkList.add(new Dtts("o1rg", "junit", "4.8.3"));
        hulkList.add(new Dtts("org", "junit", "4.8.7")); // 3
        hulkList.add(new Dtts("o2rg", "ju4nit", "4.8.4"));
        hulkList.add(new Dtts("o4rg", "j4unit", "4.8.6"));
        hulkList.add(new Dtts("oarg", "junit", "4.8.8"));
        hulkList.add(new Dtts("oarg", "junit", "4.8.8"));
        hulkList.add(new Dtts("org", "junit", "4.8.1"));
        hulkList.add(new Dtts("oarg", "junit", "4.8.8"));
        hulkList.add(new Dtts("org", "junit", "4.8.9")); // 4
        hulkList.add(new Dtts("oarg", "junit", "4.8.8"));
        
        Collections.sort(hulkList, new Comparator<Dtts>() {
            @Override
            public int compare(final Dtts d1, final Dtts d2) {
                int x = d1.getGroup().compareTo(d2.getGroup());
                if (x == 0) {
                    x = d1.getArtifact().compareTo(d2.getArtifact());
                }
                return x;
            }
        });

        String y = ""; // will hold only group and artifact.
        String x = ""; // dot not discard the first.
        for (Dtts d : hulkList) {
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

        System.out.println(strangers2);

    }

}
