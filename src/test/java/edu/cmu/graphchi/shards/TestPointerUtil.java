/**
 * @author  Aapo Kyrola <akyrola@cs.cmu.edu>
 * @version 1.0
 *
 * @section LICENSE
 *
 * Copyright [2014] [Aapo Kyrola / Carnegie Mellon University]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Publication to cite:  http://arxiv.org/abs/1403.0701
 */
package edu.cmu.graphchi.shards;

import edu.cmu.graphchi.shards.PointerUtil;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @author Aapo Kyrola
 */
public class TestPointerUtil {

    @Test
    public void testEncodingDecoding() {
        int shardNum = 49;
        int shardPos = 29193134;

        Random r = new Random(260379);
        for(int i=0; i<1000; i++) {
            long pointer = PointerUtil.encodePointer(shardNum, shardPos);
            assertEquals(shardNum, PointerUtil.decodeShardNum(pointer));
            assertEquals(shardPos, PointerUtil.decodeShardPos(pointer));

            shardNum = Math.abs(r.nextInt() % 1024);
            shardPos = Math.abs(r.nextInt());
        }
    }
}
