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

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;


/**
 * Test class DttsProcessorTest. 
 * @author eprogramming
 *
 */
public class DttsProcessorTest {

    @Test
    public void shouldPrintDifferentDtts() {
        
        Set<Dtts> strangers = new LinkedHashSet<>();
        
        String groupAndArtifactAgain = "";
        String x = "";
        Set<Dtts> dset = new LinkedHashSet<>();
        dset.add(new Dtts("org", "junit", "4.8.1"));
        dset.add(new Dtts("org", "junit", "4.8.5"));
        dset.add(new Dtts("o1rg", "junit", "4.8.3"));
        dset.add(new Dtts("org", "junit", "4.8.7"));
        dset.add(new Dtts("o2rg", "ju4nit", "4.8.4"));
        
        dset.add(new Dtts("o4rg", "j4unit", "4.8.6"));
        dset.add(new Dtts("oarg", "junit", "4.8.8"));
//        dset.add(new Dtts("esmeril", "programming", "4.8.1"));
//        dset.add(new Dtts("org", "junit", "4.11.1"));
//        dset.add(new Dtts("maven", "plugin", "1.2.3"));
//        dset.add(new Dtts("org", "junit", "4.8.1"));
        
        for (Dtts d : dset) {
            if (!(d.getGroup() + d.getArtifact()).equals(groupAndArtifactAgain)) {
                groupAndArtifactAgain = (d.getGroup() + d.getArtifact());
                x = (d.getGroup() + "@" + d.getArtifact() + "@" + d.getVersion());
            } else {
                
                Dtts stranger = new Dtts(x.split("@")[0], x.split("@")[1], x.split("@")[2]);
                strangers.add(stranger);
                
                String other = d.getGroup() + "@" + d.getArtifact() + "@" + d.getVersion();
                stranger = new Dtts(other.split("@")[0], other.split("@")[1], other.split("@")[2]);
                strangers.add(stranger);
                
            }
        }
        
        System.out.println(strangers);
        
    }
    
}
