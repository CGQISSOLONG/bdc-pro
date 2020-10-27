package com.bdc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {
    // 过期时间 24 小时
    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000 ;
    // 密钥
   // private static final String SECRET = "chen";

    /**
     * 校验 token 是否正确
     */
    public static boolean verify(String token, String userId,String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", userId)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     */
//    public static String getUsername(String token) {
//        try {
//            DecodedJWT jwt = JWT.decode(token);
//            return jwt.getClaim("username").asString();
//        } catch (JWTDecodeException e) {
//            return null;
//        }
//    }

    /**
     * 获得token中的用户ID信息无需secret解密也能获得
     * @param token
     * @return
     */
    public static String getUserIdByToken(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        }catch(JWTDecodeException e){
            return null;
        }
    }

    /**
     * 生成 token,指定时间后过期，一经生成不可修改，令牌在指定时间内一直有效。
     */
    public static String sign(String userId,Integer userType,String password){
        final long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis +EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(password);
        // 附带username信息
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("userType", userType)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static Integer getUserTypeByToken(String token) {
        try{
            DecodedJWT jwt = JWT.decode(token);
            final Claim userType = jwt.getClaim("userType");
            return userType.asInt();
        }catch(JWTDecodeException e){
            return null;
        }
    }
}
