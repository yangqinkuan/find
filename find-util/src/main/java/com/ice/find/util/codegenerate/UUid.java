/**
 * FileName: UUid
 * Author:   yangqinkuan
 * Date:     2019-1-13 17:59
 * Description:
 */

package com.ice.find.util.codegenerate;

import java.util.UUID;

public class UUid {
    public final static synchronized String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
