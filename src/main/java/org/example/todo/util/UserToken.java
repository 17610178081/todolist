package org.example.todo.util;


import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.time.LocalDateTime;

public class UserToken {
    private static String privateKey="fdto34ljfr好sja@\\#8$%df栖kl;js&4*dak君lfdortfire;akflist342";

 /** @Author 好栖君(lyj)
  * @Description //TODO
  * @Date 13:52 2021/8/11
  * @Param 
  * @return 
  **/
    public static String makeToken(String username) {
        return Hashing.md5().newHasher().putString(username, Charsets.UTF_8).putString(privateKey, Charsets.UTF_8).putString(LocalDateTime.now().toString(), Charsets.UTF_8).hash().toString();
    }

}
