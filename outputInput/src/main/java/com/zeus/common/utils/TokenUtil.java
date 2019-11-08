//package com.zeus.common.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import javax.crypto.spec.SecretKeySpec;
//import java.security.Key;
//import java.util.Date;
//
//
///**
// * @author:fusheng
// * @desc:TOKEN创建
// * @date:2018/12/04
// * @ver:1.0
// **/
//public class TokenUtil {
//    /**
//     * @param id
//     * @param issuer
//     * @param subject
//     * @param ttlMillis
//     * @return java.lang.String
//     * @methodName createJWT
//     * @author fusheng
//     * @date 2019-03-07
//     */
//    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
//
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("JAVABASE");
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//        JwtBuilder builder = Jwts.builder().setId(id)
//                /**
//                 * 签发时间
//                 * */
//                .setIssuedAt(now)
//                /**
//                 * 主题
//                 * */
//                .setSubject(subject)
//                /**
//                 * 签发者
//                 * */
//                .setIssuer(issuer)
//                /**
//                 * 签名算法以及密匙
//                 * */
//                .signWith(signatureAlgorithm, signingKey);
//
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            /**
//             * exp 过期时间
//             * */
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }
//        return builder.compact();
//    }
//
//    /**
//     * 解密
//     *
//     * @param jwt
//     */
//    public static Claims parseJWT(String jwt) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(DatatypeConverter.parseBase64Binary("JAVABASE"))
//                .parseClaimsJws(jwt).getBody();
//        return claims;
//    }
//
//
//    public static void main(String[] args) {
//        String str = TokenUtil.createJWT("12", "watch", "13074149273", 10000);
//        System.out.println(str);
//
//        parseJWT(str);
//    }
//}
