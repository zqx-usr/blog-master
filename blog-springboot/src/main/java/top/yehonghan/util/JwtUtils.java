package top.yehonghan.util;

import io.jsonwebtoken.*;
import top.yehonghan.constant.JwtConstant;
import top.yehonghan.entity.CheckResult;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * ClassName: JwtUtils
 * Package: top.yehonghan.util
 * Description: 加密解密jwt
 *
 * @Author: yehonghan
 * @Create: 2022/12/11 15:49
 * @Version: V1.0
 */
public class JwtUtils {
    /**
     * 签发jwt
     * @param id
     * @param subject 可以是json数据 尽量少
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id,String subject,long ttlMillis){
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        long nowMillis=System.currentTimeMillis();
        Date now=new Date(nowMillis);
        SecretKey secretKey=generalKey();
        JwtBuilder builder= Jwts.builder()
                .setId(id)
                //主题
                .setSubject(subject)
                //签发者
                .setIssuer("YHH_cs")
                //签发时间
                .setIssuedAt(now)
                //签名算法与密匙
                .signWith(signatureAlgorithm,secretKey);
        if(ttlMillis>=0){
            long expMillis=nowMillis+ttlMillis;
            Date expDate=new Date(expMillis);
            //过期时间
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    /**
     * 生成JWT Token
     * @param username
     * @return
     */
    public static String getJwtToken(String username){
        return createJWT(username, username, 60*60*1000);
    }

    /**
     * 验证JWT
     * @param jwtStr
     * @return
     */
    public static CheckResult validateJWT(String jwtStr){
        CheckResult checkResult=new CheckResult();
        Claims claims=null;
        try {
            claims=parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e){
            e.printStackTrace();
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }catch (Exception e){
            e.printStackTrace();
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }


    /**
     * 生成加密key
     * @return
     */
    public static SecretKey generalKey(){
        byte[] encodeKey= Base64.getDecoder().decode(JwtConstant.JWT_SECERT);
        SecretKey key=new SecretKeySpec(encodeKey, 0,encodeKey.length,"AES");
        return key;
    }

    /**
     * 解析JWT 字符串
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){
        SecretKey secretKey=generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
